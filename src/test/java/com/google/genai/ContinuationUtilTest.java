/*
 * Copyright 2026 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.genai;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.genai.errors.ApiException;
import com.google.genai.errors.ServerException;
import com.google.genai.types.BlockedReason;
import com.google.genai.types.Candidate;
import com.google.genai.types.Citation;
import com.google.genai.types.CitationMetadata;
import com.google.genai.types.Content;
import com.google.genai.types.FinishReason;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.GenerateContentResponsePromptFeedback;
import com.google.genai.types.GenerateContentResponseUsageMetadata;
import com.google.genai.types.GroundingChunk;
import com.google.genai.types.GroundingChunkImage;
import com.google.genai.types.GroundingChunkWeb;
import com.google.genai.types.GroundingMetadata;
import com.google.genai.types.GroundingSupport;
import com.google.genai.types.HarmCategory;
import com.google.genai.types.HarmProbability;
import com.google.genai.types.HttpResponse;
import com.google.genai.types.LogprobsResult;
import com.google.genai.types.LogprobsResultCandidate;
import com.google.genai.types.MediaModality;
import com.google.genai.types.ModalityTokenCount;
import com.google.genai.types.Part;
import com.google.genai.types.SafetyRating;
import com.google.genai.types.Segment;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Test;

public final class ContinuationUtilTest {

  private static final byte[] TOKEN_1 = "token-1".getBytes(UTF_8);
  private static final byte[] TOKEN_2 = "token-2".getBytes(UTF_8);

  private static final GenerateContentConfig CONFIG =
      GenerateContentConfig.builder().automaticContinuation(true).temperature(0.5f).build();

  private static GenerateContentResponse response(
      String text, FinishReason.Known finishReason, byte[] token) {
    Candidate.Builder candidate =
        Candidate.builder().content(Content.builder().role("model").parts(Part.fromText(text)));
    if (finishReason != null) {
      candidate.finishReason(finishReason);
    }
    if (token != null) {
      candidate.continuationToken(token);
    }
    return GenerateContentResponse.builder().candidates(candidate.build()).build();
  }

  private static GenerateContentResponse response(String text) {
    return response(text, null, null);
  }

  /** Hands out {@code responses} in order and records the config of every request. */
  private static final class FakeModel {
    final List<GenerateContentConfig> configs = new ArrayList<>();
    private final Deque<GenerateContentResponse> pending;

    FakeModel(GenerateContentResponse... responses) {
      pending = new ArrayDeque<>(Arrays.asList(responses));
    }

    GenerateContentResponse send(GenerateContentConfig config) {
      configs.add(config);
      return pending.removeFirst();
    }
  }

  /** A stream over {@code chunks}, read from an SSE body the way a real response is. */
  private static ResponseStream<GenerateContentResponse> stream(GenerateContentResponse... chunks) {
    return sseStream(
        Arrays.stream(chunks)
            .map(chunk -> "data: " + chunk.toJson() + "\n\n")
            .collect(Collectors.joining()));
  }

  /** A stream over the SSE body {@code sse}. */
  private static ResponseStream<GenerateContentResponse> sseStream(String sse) {
    ResponseBody body =
        ResponseBody.create(sse.getBytes(UTF_8), MediaType.parse("text/event-stream"));
    return new ResponseStream<>(
        GenerateContentResponse.class,
        new FakeApiResponse(Headers.of(), body),
        new ResponseStreamTest.DummyConverter(),
        "convert");
  }

  private static List<String> texts(ResponseStream<GenerateContentResponse> stream) {
    List<String> texts = new ArrayList<>();
    for (GenerateContentResponse chunk : stream) {
      texts.add(chunk.text());
    }
    return texts;
  }

  private static List<GenerateContentResponse> chunks(
      ResponseStream<GenerateContentResponse> stream) {
    List<GenerateContentResponse> chunks = new ArrayList<>();
    for (GenerateContentResponse chunk : stream) {
      chunks.add(chunk);
    }
    return chunks;
  }

  private static List<String> texts(List<GenerateContentResponse> chunks) {
    return chunks.stream().map(GenerateContentResponse::text).collect(Collectors.toList());
  }

  private static List<FinishReason.Known> finishReasons(List<GenerateContentResponse> chunks) {
    List<FinishReason.Known> reasons = new ArrayList<>();
    for (GenerateContentResponse chunk : chunks) {
      reasons.add(
          ContinuationUtil.firstCandidate(chunk)
              .flatMap(Candidate::finishReason)
              .map(FinishReason::knownEnum)
              .orElse(null));
    }
    return reasons;
  }

  /** Hands out {@code streams} in order and records the config of every request. */
  private static final class FakeStreamingModel {
    final List<GenerateContentConfig> configs = new ArrayList<>();
    private final Deque<ResponseStream<GenerateContentResponse>> streams;

    @SafeVarargs
    FakeStreamingModel(ResponseStream<GenerateContentResponse>... streams) {
      this.streams = new ArrayDeque<>(Arrays.asList(streams));
    }

    ResponseStream<GenerateContentResponse> send(GenerateContentConfig config) {
      configs.add(config);
      return streams.removeFirst();
    }
  }

  /** A stream that emits {@code chunk}, then throws {@code error} on the next read. */
  private static ResponseStream<GenerateContentResponse> errorStream(
      GenerateContentResponse chunk, RuntimeException error) {
    return errorStream(error, chunk);
  }

  /** A stream that emits {@code chunks}, then throws {@code error} on the next read. */
  private static ResponseStream<GenerateContentResponse> errorStream(
      RuntimeException error, GenerateContentResponse... chunks) {
    String sse =
        Arrays.stream(chunks)
            .map(chunk -> "data: " + chunk.toJson() + "\n\n")
            .collect(Collectors.joining());
    byte[] bytes = sse.getBytes(UTF_8);
    java.io.InputStream in =
        new java.io.InputStream() {
          private int index = 0;

          @Override
          public int read() {
            if (index < bytes.length) {
              return bytes[index++] & 0xff;
            }
            throw error;
          }

          @Override
          public int read(byte[] b, int off, int len) {
            if (index < bytes.length) {
              int toRead = Math.min(len, bytes.length - index);
              System.arraycopy(bytes, index, b, off, toRead);
              index += toRead;
              return toRead;
            }
            throw error;
          }
        };
    ResponseBody body =
        new ResponseBody() {
          @Override
          public MediaType contentType() {
            return MediaType.parse("text/event-stream");
          }

          @Override
          public long contentLength() {
            return -1;
          }

          @Override
          public okio.BufferedSource source() {
            return okio.Okio.buffer(okio.Okio.source(in));
          }
        };
    return new ResponseStream<>(
        GenerateContentResponse.class,
        new FakeApiResponse(Headers.of(), body),
        new ResponseStreamTest.DummyConverter(),
        "convert");
  }

  @Test
  public void testIsEnabled() {
    assertTrue(ContinuationUtil.isEnabled(null));
    assertTrue(ContinuationUtil.isEnabled(GenerateContentConfig.builder().build()));
    assertTrue(ContinuationUtil.isEnabled(CONFIG));
    assertFalse(
        ContinuationUtil.isEnabled(
            GenerateContentConfig.builder().automaticContinuation(false).build()));
  }

  @Test
  public void testIsEnabled_maxOutputTokensDoesNotTurnItOff() {
    GenerateContentConfig config = GenerateContentConfig.builder().maxOutputTokens(100).build();

    assertTrue(ContinuationUtil.isEnabled(config));
  }

  @Test
  public void testGenerate_continuesWithEachTokenUntilTheModelFinishes() {
    FakeModel model =
        new FakeModel(
            response("Hello ", FinishReason.Known.CONTINUATION, TOKEN_1),
            response("big ", FinishReason.Known.CONTINUATION, TOKEN_2),
            response("world", FinishReason.Known.STOP, null));

    GenerateContentResponse merged = ContinuationUtil.generate(CONFIG, model::send);

    assertEquals("Hello big world", merged.text());
    assertEquals(3, model.configs.size());
    assertSame(CONFIG, model.configs.get(0));
    assertArrayEquals(TOKEN_1, model.configs.get(1).continuationToken().get());
    assertArrayEquals(TOKEN_2, model.configs.get(2).continuationToken().get());
    // Apart from the token, every request is the original one.
    assertEquals(0.5f, model.configs.get(2).temperature().get());
    assertTrue(model.configs.get(2).automaticContinuation().get());
  }

  @Test
  public void testGenerate_doesNotContinueOnMaxTokens() {
    GenerateContentResponse only = response("Hello", FinishReason.Known.MAX_TOKENS, TOKEN_1);
    FakeModel model = new FakeModel(only);

    assertSame(only, ContinuationUtil.generate(CONFIG, model::send));
    assertEquals(1, model.configs.size());
  }

  @Test
  public void testGenerate_sendsMaxOutputTokensWithEveryRequest() {
    GenerateContentConfig config = CONFIG.toBuilder().maxOutputTokens(100).build();
    FakeModel model =
        new FakeModel(
            response("Hello ", FinishReason.Known.CONTINUATION, TOKEN_1),
            response("world", FinishReason.Known.STOP, null));

    ContinuationUtil.generate(config, model::send);

    assertEquals(2, model.configs.size());
    assertEquals(100, model.configs.get(1).maxOutputTokens().get());
  }

  @Test
  public void testGenerate_stopsWhenTheResponseHasNoToken() {
    GenerateContentResponse only = response("Hello", FinishReason.Known.CONTINUATION, null);
    FakeModel model = new FakeModel(only);

    GenerateContentResponse response = ContinuationUtil.generate(CONFIG, model::send);
    assertSame(only, response);
    assertEquals(FinishReason.Known.CONTINUATION, response.finishReason().knownEnum());
    assertEquals(1, model.configs.size());

    FakeStreamingModel streamingModel =
        new FakeStreamingModel(stream(response("Hello", FinishReason.Known.CONTINUATION, null)));
    ResponseStream<GenerateContentResponse> first = streamingModel.send(CONFIG);
    first.setContinuation(ContinuationUtil.streamContinuation(CONFIG, streamingModel::send));
    List<GenerateContentResponse> chunks = chunks(first);
    assertEquals(Collections.singletonList(FinishReason.Known.CONTINUATION), finishReasons(chunks));
    assertEquals(1, streamingModel.configs.size());
  }

  @Test
  public void testGenerate_stopsOnOtherFinishReasons() {
    FakeModel model = new FakeModel(response("Hello", FinishReason.Known.SAFETY, TOKEN_1));

    ContinuationUtil.generate(CONFIG, model::send);

    assertEquals(1, model.configs.size());
  }

  @Test
  public void testGenerate_sendsTheFirstRequestWithoutAConfigWhenGivenNone() {
    FakeModel model =
        new FakeModel(
            response("Hello ", FinishReason.Known.CONTINUATION, TOKEN_1),
            response("world", FinishReason.Known.STOP, null));

    ContinuationUtil.generate(null, model::send);

    assertNull(model.configs.get(0));
    assertArrayEquals(TOKEN_1, model.configs.get(1).continuationToken().get());
  }

  @Test
  public void testGenerateAsync_continuesUntilTheModelFinishes() throws Exception {
    FakeModel model =
        new FakeModel(
            response("Hello ", FinishReason.Known.CONTINUATION, TOKEN_1),
            response("world", FinishReason.Known.STOP, null));

    GenerateContentResponse merged =
        ContinuationUtil.generateAsync(
                CONFIG, config -> CompletableFuture.completedFuture(model.send(config)))
            .get();

    assertEquals("Hello world", merged.text());
    assertArrayEquals(TOKEN_1, model.configs.get(1).continuationToken().get());
  }

  @Test
  public void testStreamContinuation_emitsTheChunksOfEveryRequestInOrder() {
    List<GenerateContentConfig> configs = new ArrayList<>();
    ResponseStream<GenerateContentResponse> first =
        stream(
            response("a"),
            // A checkpoint in the middle of a long stream.
            response("b", null, TOKEN_1),
            response("c", FinishReason.Known.CONTINUATION, TOKEN_2));
    first.setContinuation(
        ContinuationUtil.streamContinuation(
            CONFIG,
            config -> {
              configs.add(config);
              return stream(response("d", FinishReason.Known.STOP, null));
            }));

    List<GenerateContentResponse> chunks = chunks(first);
    assertEquals(ImmutableList.of("a", "b", "c", "d"), texts(chunks));
    assertEquals(Arrays.asList(null, null, null, FinishReason.Known.STOP), finishReasons(chunks));
    assertEquals(1, configs.size());
    assertArrayEquals(TOKEN_2, configs.get(0).continuationToken().get());
  }

  @Test
  public void testStreamContinuation_resumesFromATokenSentBeforeTheFinishReason() {
    List<GenerateContentConfig> configs = new ArrayList<>();
    ResponseStream<GenerateContentResponse> first =
        stream(response("a", null, TOKEN_1), response("b", FinishReason.Known.CONTINUATION, null));
    first.setContinuation(
        ContinuationUtil.streamContinuation(
            CONFIG,
            config -> {
              configs.add(config);
              return stream(response("c", FinishReason.Known.STOP, null));
            }));

    List<GenerateContentResponse> chunks = chunks(first);
    assertEquals(ImmutableList.of("a", "b", "c"), texts(chunks));
    assertEquals(Arrays.asList(null, null, FinishReason.Known.STOP), finishReasons(chunks));
    assertArrayEquals(TOKEN_1, configs.get(0).continuationToken().get());
  }

  @Test
  public void testStreamContinuation_stopsAtACheckpointWhenTheModelFinishes() {
    List<GenerateContentConfig> configs = new ArrayList<>();
    ResponseStream<GenerateContentResponse> first =
        stream(response("a", null, TOKEN_1), response("b", FinishReason.Known.STOP, null));
    first.setContinuation(
        ContinuationUtil.streamContinuation(
            CONFIG,
            config -> {
              configs.add(config);
              return stream();
            }));

    assertEquals(ImmutableList.of("a", "b"), texts(first));
    assertTrue(configs.isEmpty());
  }

  @Test
  public void testStreamContinuation_doesNotContinueOnMaxTokens() {
    List<GenerateContentConfig> configs = new ArrayList<>();
    ResponseStream<GenerateContentResponse> first =
        stream(response("a", FinishReason.Known.MAX_TOKENS, TOKEN_1));
    first.setContinuation(
        ContinuationUtil.streamContinuation(
            CONFIG,
            config -> {
              configs.add(config);
              return stream();
            }));

    List<GenerateContentResponse> chunks = chunks(first);
    assertEquals(ImmutableList.of("a"), texts(chunks));
    assertEquals(Collections.singletonList(FinishReason.Known.MAX_TOKENS), finishReasons(chunks));
    assertTrue(configs.isEmpty());
  }

  @Test
  public void testStreamResumesFromCheckpointTokenOnMidStreamCutoffOrError() {
    FakeStreamingModel model =
        new FakeStreamingModel(
            // Hop 1: intermediate checkpoint chunk (finishReason == null), then stream cuts off
            // early.
            stream(response("a", null, TOKEN_1)),
            // Hop 2: intermediate checkpoint chunk, then mid-stream exception.
            errorStream(response("b", null, TOKEN_2), new IllegalStateException("stream dropped")),
            // Hop 3: completes with STOP.
            stream(response("c", FinishReason.Known.STOP, null)));

    ResponseStream<GenerateContentResponse> first = model.send(CONFIG);
    first.setContinuation(ContinuationUtil.streamContinuation(CONFIG, model::send));

    assertEquals(ImmutableList.of("a", "b", "c"), texts(first));
    assertEquals(3, model.configs.size());
    assertArrayEquals(TOKEN_1, model.configs.get(1).continuationToken().get());
    assertArrayEquals(TOKEN_2, model.configs.get(2).continuationToken().get());
  }

  @Test
  public void testStreamRethrowsMidStreamErrorWhenNoCheckpointTokenWasReceived() {
    FakeStreamingModel model =
        new FakeStreamingModel(
            errorStream(response("a"), new IllegalStateException("unrecoverable error")));

    ResponseStream<GenerateContentResponse> first = model.send(CONFIG);
    first.setContinuation(ContinuationUtil.streamContinuation(CONFIG, model::send));

    assertThrows(IllegalStateException.class, () -> texts(first));
    assertEquals(1, model.configs.size());
  }

  private static GenerateContentResponse blocked() {
    return GenerateContentResponse.builder()
        .promptFeedback(
            GenerateContentResponsePromptFeedback.builder()
                .blockReason(BlockedReason.Known.SAFETY)
                .blockReasonMessage("Blocked on continuation"))
        .build();
  }

  @Test
  public void testStreamDoesNotResumeFromACheckpointAfterABlockedChunk() {
    FakeStreamingModel model =
        new FakeStreamingModel(stream(response("a"), response("b", null, TOKEN_1), blocked()));

    ResponseStream<GenerateContentResponse> first = model.send(CONFIG);
    first.setContinuation(ContinuationUtil.streamContinuation(CONFIG, model::send));

    List<GenerateContentResponse> chunks = chunks(first);
    assertEquals(3, chunks.size());
    assertEquals(ImmutableList.of("a", "b"), texts(chunks.subList(0, 2)));
    assertFalse(chunks.get(2).candidates().isPresent());
    assertEquals(
        BlockedReason.Known.SAFETY,
        chunks.get(2).promptFeedback().get().blockReason().get().knownEnum());
    assertEquals(1, model.configs.size());
    assertTrue(first.isConsumed());
  }

  @Test
  public void testStreamRethrowsAnErrorAfterABlockedChunkInsteadOfResuming() {
    FakeStreamingModel model =
        new FakeStreamingModel(
            errorStream(
                new IllegalStateException("stream dropped"),
                response("a", null, TOKEN_1),
                blocked()));

    ResponseStream<GenerateContentResponse> first = model.send(CONFIG);
    first.setContinuation(ContinuationUtil.streamContinuation(CONFIG, model::send));

    assertThrows(IllegalStateException.class, () -> chunks(first));
    assertEquals(1, model.configs.size());
  }

  @Test
  public void testStreamDoesNotResumeAfterPromptFeedbackOnAResumedRequest() {
    FakeStreamingModel model =
        new FakeStreamingModel(
            stream(response("a", FinishReason.Known.CONTINUATION, TOKEN_1)),
            stream(blocked()),
            stream(response("never sent", FinishReason.Known.STOP, null)));

    ResponseStream<GenerateContentResponse> first = model.send(CONFIG);
    first.setContinuation(ContinuationUtil.streamContinuation(CONFIG, model::send));

    List<GenerateContentResponse> chunks = chunks(first);
    assertEquals(2, chunks.size());
    assertEquals("a", chunks.get(0).text());
    assertTrue(chunks.get(1).promptFeedback().isPresent());
    assertEquals(2, model.configs.size());
  }

  @Test
  public void testStreamDoesNotResumeFromATokenOnAChunkWithPromptFeedback() {
    GenerateContentResponse withFeedback =
        response("a", FinishReason.Known.CONTINUATION, TOKEN_1).toBuilder()
            .promptFeedback(GenerateContentResponsePromptFeedback.builder().build())
            .build();
    FakeStreamingModel model = new FakeStreamingModel(stream(withFeedback));

    ResponseStream<GenerateContentResponse> first = model.send(CONFIG);
    first.setContinuation(ContinuationUtil.streamContinuation(CONFIG, model::send));

    List<GenerateContentResponse> chunks = chunks(first);
    // The finish reason is kept because the stream is not continued.
    assertEquals(Collections.singletonList(FinishReason.Known.CONTINUATION), finishReasons(chunks));
    assertEquals(1, model.configs.size());
  }

  @Test
  public void testStreamRetriesARequestThatDropsBeforeAnyContentFromItsToken() {
    FakeStreamingModel model =
        new FakeStreamingModel(
            stream(response("a", FinishReason.Known.CONTINUATION, TOKEN_1)),
            errorStream(new IllegalStateException("dropped during prefill")),
            // Ends without a chunk, as a connection closed during prefill does.
            stream(),
            stream(response("b", FinishReason.Known.STOP, null)));

    ResponseStream<GenerateContentResponse> first = model.send(CONFIG);
    first.setContinuation(ContinuationUtil.streamContinuation(CONFIG, model::send));

    assertEquals(ImmutableList.of("a", "b"), texts(first));
    assertEquals(4, model.configs.size());
    for (int i = 1; i < 4; i++) {
      assertArrayEquals(TOKEN_1, model.configs.get(i).continuationToken().get());
    }
  }

  @Test
  public void testStreamRetriesARequestThatFailsToOpen() {
    List<GenerateContentConfig> configs = new ArrayList<>();
    ResponseStream<GenerateContentResponse> first =
        stream(response("a", FinishReason.Known.CONTINUATION, TOKEN_1));
    first.setContinuation(
        ContinuationUtil.streamContinuation(
            CONFIG,
            config -> {
              configs.add(config);
              if (configs.size() == 1) {
                // A transport error, not one the API returned, so the request is retried.
                throw new IllegalStateException("connection refused");
              }
              return stream(response("b", FinishReason.Known.STOP, null));
            }));

    assertEquals(ImmutableList.of("a", "b"), texts(first));
    assertEquals(2, configs.size());
    assertArrayEquals(TOKEN_1, configs.get(1).continuationToken().get());
  }

  @Test
  public void testStreamGivesUpAfterMaxReconnectsWithoutProgress() {
    IllegalStateException error = new IllegalStateException("dropped during prefill");
    FakeStreamingModel model =
        new FakeStreamingModel(
            stream(response("a", FinishReason.Known.CONTINUATION, TOKEN_1)),
            errorStream(error),
            errorStream(error),
            errorStream(error),
            errorStream(error),
            stream(response("never sent", FinishReason.Known.STOP, null)));

    ResponseStream<GenerateContentResponse> first = model.send(CONFIG);
    first.setContinuation(ContinuationUtil.streamContinuation(CONFIG, model::send));

    assertSame(error, assertThrows(IllegalStateException.class, () -> texts(first)));
    // The first request, the resumed one and MAX_STREAM_RECONNECTS_WITHOUT_PROGRESS retries.
    assertEquals(2 + ContinuationUtil.MAX_STREAM_RECONNECTS_WITHOUT_PROGRESS, model.configs.size());
  }

  @Test
  public void testStreamDoesNotRetryAResumedRequestTheApiRejects() {
    // Retrying a request the API rejects is left to the HTTP client's retry options.
    ServerException error = new ServerException(503, "UNAVAILABLE", "overloaded");
    List<GenerateContentConfig> configs = new ArrayList<>();
    ResponseStream<GenerateContentResponse> first =
        stream(response("a", FinishReason.Known.CONTINUATION, TOKEN_1));
    first.setContinuation(
        ContinuationUtil.streamContinuation(
            CONFIG,
            config -> {
              configs.add(config);
              if (configs.size() == 1) {
                throw error;
              }
              return stream(response("never sent", FinishReason.Known.STOP, null));
            }));

    List<String> texts = new ArrayList<>();
    assertSame(
        error,
        assertThrows(
            ServerException.class,
            () -> {
              for (GenerateContentResponse chunk : first) {
                texts.add(chunk.text());
              }
            }));
    assertEquals(ImmutableList.of("a"), texts);
    // The resumed request was sent once (the first request is not sent through the function).
    assertEquals(1, configs.size());
  }

  @Test
  public void testStreamDoesNotRetryAResumedRequestWhoseStreamStartsWithAnError() {
    FakeStreamingModel model =
        new FakeStreamingModel(
            stream(response("a", FinishReason.Known.CONTINUATION, TOKEN_1)),
            sseStream(
                "data: {\"error\": {\"code\": 503, \"message\": \"overloaded\","
                    + " \"status\": \"UNAVAILABLE\"}}\n\n"),
            stream(response("never sent", FinishReason.Known.STOP, null)));

    ResponseStream<GenerateContentResponse> first = model.send(CONFIG);
    first.setContinuation(ContinuationUtil.streamContinuation(CONFIG, model::send));

    ApiException error = assertThrows(ApiException.class, () -> texts(first));
    assertEquals(503, error.code());
    assertEquals(2, model.configs.size());
  }

  @Test
  public void testStreamResumesAfterAnApiErrorOnceAHopReceivedAChunk() {
    FakeStreamingModel model =
        new FakeStreamingModel(
            sseStream(
                "data: "
                    + response("a", null, TOKEN_1).toJson()
                    + "\n\n"
                    + "data: {\"error\": {\"code\": 503, \"message\": \"overloaded\","
                    + " \"status\": \"UNAVAILABLE\"}}\n\n"),
            stream(response("b", FinishReason.Known.STOP, null)));

    ResponseStream<GenerateContentResponse> first = model.send(CONFIG);
    first.setContinuation(ContinuationUtil.streamContinuation(CONFIG, model::send));

    assertEquals(ImmutableList.of("a", "b"), texts(first));
    assertEquals(2, model.configs.size());
    assertArrayEquals(TOKEN_1, model.configs.get(1).continuationToken().get());
  }

  @Test
  public void testStreamThrowsADropWhenRetriesThenKeepEndingEmpty() {
    IllegalStateException error = new IllegalStateException("dropped during prefill");
    FakeStreamingModel model =
        new FakeStreamingModel(
            stream(response("a", FinishReason.Known.CONTINUATION, TOKEN_1)),
            errorStream(error),
            stream(),
            stream(),
            stream(),
            stream(response("never sent", FinishReason.Known.STOP, null)));

    ResponseStream<GenerateContentResponse> first = model.send(CONFIG);
    first.setContinuation(ContinuationUtil.streamContinuation(CONFIG, model::send));

    List<String> texts = new ArrayList<>();
    assertSame(
        error,
        assertThrows(
            IllegalStateException.class,
            () -> {
              for (GenerateContentResponse chunk : first) {
                texts.add(chunk.text());
              }
            }));
    assertEquals(ImmutableList.of("a"), texts);
    // The first request, the resumed one and MAX_STREAM_RECONNECTS_WITHOUT_PROGRESS retries.
    assertEquals(2 + ContinuationUtil.MAX_STREAM_RECONNECTS_WITHOUT_PROGRESS, model.configs.size());
  }

  @Test
  public void testStreamDoesNotThrowAnEarlierDropWhenTheApiEndsTheStream() {
    FakeStreamingModel model =
        new FakeStreamingModel(
            stream(response("a", FinishReason.Known.CONTINUATION, TOKEN_1)),
            errorStream(new IllegalStateException("dropped during prefill")),
            stream(blocked()));

    ResponseStream<GenerateContentResponse> first = model.send(CONFIG);
    first.setContinuation(ContinuationUtil.streamContinuation(CONFIG, model::send));

    List<GenerateContentResponse> chunks = chunks(first);
    assertEquals(2, chunks.size());
    assertTrue(chunks.get(1).promptFeedback().isPresent());
    assertEquals(3, model.configs.size());
  }

  @Test
  public void testStreamDoesNotReplayARequestThatAlreadyHandedOutContent() {
    FakeStreamingModel model =
        new FakeStreamingModel(
            stream(response("a", FinishReason.Known.CONTINUATION, TOKEN_1)),
            errorStream(new IllegalStateException("stream dropped"), response("b")),
            stream(response("never sent", FinishReason.Known.STOP, null)));

    ResponseStream<GenerateContentResponse> first = model.send(CONFIG);
    first.setContinuation(ContinuationUtil.streamContinuation(CONFIG, model::send));

    List<String> texts = new ArrayList<>();
    assertThrows(
        IllegalStateException.class,
        () -> {
          for (GenerateContentResponse chunk : first) {
            texts.add(chunk.text());
          }
        });
    assertEquals(ImmutableList.of("a", "b"), texts);
    assertEquals(2, model.configs.size());
  }

  @Test
  public void testStreamEndsNormallyWhenAResumedRequestEndsWithoutAFinishReason() {
    FakeStreamingModel model =
        new FakeStreamingModel(
            stream(response("a", FinishReason.Known.CONTINUATION, TOKEN_1)), stream(response("b")));

    ResponseStream<GenerateContentResponse> first = model.send(CONFIG);
    first.setContinuation(ContinuationUtil.streamContinuation(CONFIG, model::send));

    // The API returned no error, so the stream just ends with what it handed out.
    assertEquals(ImmutableList.of("a", "b"), texts(first));
    assertEquals(2, model.configs.size());
  }

  @Test
  public void testStreamEndsNormallyWhenResumedRequestsKeepEndingEmpty() {
    FakeStreamingModel model =
        new FakeStreamingModel(
            stream(response("a", FinishReason.Known.CONTINUATION, TOKEN_1)),
            stream(),
            stream(),
            stream(),
            stream(),
            stream(response("never sent", FinishReason.Known.STOP, null)));

    ResponseStream<GenerateContentResponse> first = model.send(CONFIG);
    first.setContinuation(ContinuationUtil.streamContinuation(CONFIG, model::send));

    assertEquals(ImmutableList.of("a"), texts(first));
    // The first request, the resumed one and MAX_STREAM_RECONNECTS_WITHOUT_PROGRESS retries.
    assertEquals(2 + ContinuationUtil.MAX_STREAM_RECONNECTS_WITHOUT_PROGRESS, model.configs.size());
  }

  @Test
  public void testStreamAccumulatesUsageMetadataAcrossRequests() {
    FakeStreamingModel model =
        new FakeStreamingModel(
            // Hop 1: checkpoint chunk with usage, then stream drops mid-hop.
            errorStream(
                response("a", null, TOKEN_1).toBuilder()
                    .usageMetadata(
                        GenerateContentResponseUsageMetadata.builder()
                            .promptTokenCount(10)
                            .cachedContentTokenCount(4)
                            .candidatesTokenCount(40)
                            .thoughtsTokenCount(5)
                            .totalTokenCount(55)
                            .promptTokensDetails(modalityTokenCount(MediaModality.Known.TEXT, 10))
                            .candidatesTokensDetails(
                                modalityTokenCount(MediaModality.Known.TEXT, 40))
                            .build())
                    .build(),
                new IllegalStateException("stream dropped")),
            // Hop 2: chunk without usage, then chunk with CONTINUATION, TOKEN_2, and usage.
            stream(
                response("b"),
                response("c", FinishReason.Known.CONTINUATION, TOKEN_2).toBuilder()
                    .usageMetadata(
                        GenerateContentResponseUsageMetadata.builder()
                            .promptTokenCount(55)
                            .cachedContentTokenCount(50)
                            .candidatesTokenCount(60)
                            .thoughtsTokenCount(3)
                            .totalTokenCount(118)
                            .promptTokensDetails(modalityTokenCount(MediaModality.Known.TEXT, 55))
                            .candidatesTokensDetails(
                                modalityTokenCount(MediaModality.Known.TEXT, 60))
                            .build())
                    .build()),
            // Hop 3: finishes with STOP and final usage.
            stream(
                response("d", FinishReason.Known.STOP, null).toBuilder()
                    .usageMetadata(
                        GenerateContentResponseUsageMetadata.builder()
                            .promptTokenCount(118)
                            .cachedContentTokenCount(110)
                            .candidatesTokenCount(25)
                            .thoughtsTokenCount(2)
                            .totalTokenCount(145)
                            .promptTokensDetails(modalityTokenCount(MediaModality.Known.TEXT, 118))
                            .candidatesTokensDetails(
                                modalityTokenCount(MediaModality.Known.TEXT, 25))
                            .build())
                    .build()));

    ResponseStream<GenerateContentResponse> first = model.send(CONFIG);
    first.setContinuation(ContinuationUtil.streamContinuation(CONFIG, model::send));
    List<GenerateContentResponse> chunks = chunks(first);

    assertEquals(ImmutableList.of("a", "b", "c", "d"), texts(chunks));

    // Chunk 0 (Hop 1): unchanged initial hop usage
    GenerateContentResponseUsageMetadata chunk0Usage = chunks.get(0).usageMetadata().get();
    assertEquals(10, chunk0Usage.promptTokenCount().get());
    assertEquals(10, chunk0Usage.initialPromptTokenCount().get());
    assertEquals(10, chunks.get(0).initialPromptTokenCount().get());
    assertEquals(4, chunk0Usage.cachedContentTokenCount().get());
    assertEquals(40, chunk0Usage.candidatesTokenCount().get());
    assertEquals(5, chunk0Usage.thoughtsTokenCount().get());
    assertEquals(55, chunk0Usage.totalTokenCount().get());

    // Chunk 1 (Hop 2 first chunk): no usageMetadata
    assertFalse(chunks.get(1).usageMetadata().isPresent());
    assertFalse(chunks.get(1).initialPromptTokenCount().isPresent());

    // Chunk 2 (Hop 2 final chunk): accumulated across Hop 1 + Hop 2
    GenerateContentResponseUsageMetadata chunk2Usage = chunks.get(2).usageMetadata().get();
    assertEquals(65, chunk2Usage.promptTokenCount().get());
    assertEquals(10, chunk2Usage.initialPromptTokenCount().get());
    assertEquals(10, chunks.get(2).initialPromptTokenCount().get());
    assertEquals(54, chunk2Usage.cachedContentTokenCount().get());
    assertEquals(100, chunk2Usage.candidatesTokenCount().get());
    assertEquals(8, chunk2Usage.thoughtsTokenCount().get());
    assertEquals(173, chunk2Usage.totalTokenCount().get());

    // Chunk 3 (Hop 3 final chunk): accumulated across Hop 1 + Hop 2 + Hop 3
    GenerateContentResponseUsageMetadata finalUsage = chunks.get(3).usageMetadata().get();
    assertEquals(183, finalUsage.promptTokenCount().get());
    assertEquals(10, finalUsage.initialPromptTokenCount().get());
    assertEquals(10, chunks.get(3).initialPromptTokenCount().get());
    assertEquals(164, finalUsage.cachedContentTokenCount().get());
    assertEquals(125, finalUsage.candidatesTokenCount().get());
    assertEquals(10, finalUsage.thoughtsTokenCount().get());
    assertEquals(318, finalUsage.totalTokenCount().get());
    assertEquals(
        ImmutableList.of(modalityTokenCount(MediaModality.Known.TEXT, 183)),
        finalUsage.promptTokensDetails().get());
    assertEquals(
        ImmutableList.of(modalityTokenCount(MediaModality.Known.TEXT, 125)),
        finalUsage.candidatesTokensDetails().get());
  }

  @Test
  public void testInitialPromptTokenCount_defaultsToPromptTokenCountWhenNotContinued() {
    GenerateContentResponse response =
        response("Hello", FinishReason.Known.STOP, null).toBuilder()
            .usageMetadata(
                GenerateContentResponseUsageMetadata.builder()
                    .promptTokenCount(42)
                    .candidatesTokenCount(10)
                    .totalTokenCount(52)
                    .build())
            .build();

    assertEquals(42, response.initialPromptTokenCount().get());
    assertEquals(42, response.usageMetadata().get().initialPromptTokenCount().get());
    assertEquals(42, response.usageMetadata().get().promptTokenCount().get());
  }

  @Test
  public void testInitialPromptTokenCount_isEmptyWhenUsageMetadataAbsent() {
    GenerateContentResponse response = response("Hello", FinishReason.Known.STOP, null);

    assertFalse(response.initialPromptTokenCount().isPresent());
  }

  @Test
  public void testJoin_rethrowsAnUncheckedFailureAsItself() {
    CompletableFuture<String> failed = new CompletableFuture<>();
    failed.completeExceptionally(new IllegalStateException("boom"));

    IllegalStateException thrown =
        assertThrows(IllegalStateException.class, () -> ContinuationUtil.join(failed));
    assertEquals("boom", thrown.getMessage());
  }

  @Test
  public void testMerge_returnsASingleResponseAsIs() {
    GenerateContentResponse only = response("Hello", FinishReason.Known.STOP, null);

    assertSame(only, ContinuationUtil.merge(ImmutableList.of(only)));
  }

  @Test
  public void testMerge_concatenatesPartsAndOtherLists() {
    GenerateContentResponse first =
        withFirstCandidate(
            response("Hello ", FinishReason.Known.CONTINUATION, TOKEN_1),
            candidate ->
                candidate.citationMetadata(
                    CitationMetadata.builder().citations(Citation.builder().uri("a"))));
    GenerateContentResponse second =
        withFirstCandidate(
            response("world", FinishReason.Known.STOP, null),
            candidate ->
                candidate.citationMetadata(
                    CitationMetadata.builder().citations(Citation.builder().uri("b"))));

    GenerateContentResponse merged = ContinuationUtil.merge(ImmutableList.of(first, second));

    assertEquals(
        ImmutableList.of("Hello ", "world"),
        merged.parts().stream().map(part -> part.text().get()).collect(Collectors.toList()));
    assertEquals(
        ImmutableList.of("a", "b"),
        merged.candidates().get().get(0).citationMetadata().get().citations().get().stream()
            .map(citation -> citation.uri().get())
            .collect(Collectors.toList()));
  }

  @Test
  public void testMerge_shiftsGroundingSupportChunkAndPartIndices() {
    GenerateContentResponse first =
        GenerateContentResponse.builder()
            .candidates(
                Candidate.builder()
                    .content(
                        Content.builder()
                            .role("model")
                            .parts(
                                Part.builder().text("Thinking...").thought(true).build(),
                                Part.builder().text("part 1 ").build()))
                    .finishReason(FinishReason.Known.CONTINUATION)
                    .continuationToken(TOKEN_1)
                    .groundingMetadata(
                        GroundingMetadata.builder()
                            .groundingChunks(
                                GroundingChunk.builder()
                                    .image(
                                        GroundingChunkImage.builder()
                                            .imageUri("https://img1.example/a.png")
                                            .sourceUri("https://source1.example/img")
                                            .title("Image 1")
                                            .domain("source1.example"))
                                    .build(),
                                GroundingChunk.builder()
                                    .web(
                                        GroundingChunkWeb.builder()
                                            .uri("https://source1.example")
                                            .title("Source 1"))
                                    .build())
                            .groundingSupports(
                                GroundingSupport.builder()
                                    .groundingChunkIndices(0, 1)
                                    .confidenceScores(0.9f, 0.8f)
                                    .segment(
                                        Segment.builder()
                                            .partIndex(1)
                                            .startIndex(0)
                                            .endIndex(7)
                                            .text("part 1 "))
                                    .build())))
            .build();
    GenerateContentResponse second =
        GenerateContentResponse.builder()
            .candidates(
                Candidate.builder()
                    .content(
                        Content.builder()
                            .role("model")
                            .parts(Part.builder().text("part 2 ").build()))
                    .finishReason(FinishReason.Known.STOP)
                    .groundingMetadata(
                        GroundingMetadata.builder()
                            .groundingChunks(
                                GroundingChunk.builder()
                                    .web(
                                        GroundingChunkWeb.builder()
                                            .uri("https://source2.example")
                                            .title("Source 2"))
                                    .build())
                            .groundingSupports(
                                GroundingSupport.builder()
                                    .groundingChunkIndices(0)
                                    .confidenceScores(0.95f)
                                    .segment(
                                        Segment.builder()
                                            .partIndex(0)
                                            .startIndex(0)
                                            .endIndex(7)
                                            .text("part 2 "))
                                    .build(),
                                GroundingSupport.builder()
                                    .groundingChunkIndices(0)
                                    .segment(
                                        Segment.builder().startIndex(0).endIndex(7).text("part 2 "))
                                    .build())))
            .build();

    GenerateContentResponse merged = ContinuationUtil.merge(ImmutableList.of(first, second));
    Candidate cand = merged.candidates().get().get(0);
    GroundingMetadata gm = cand.groundingMetadata().get();

    assertEquals(3, gm.groundingChunks().get().size());
    assertEquals(3, gm.groundingSupports().get().size());

    GroundingSupport s0 = gm.groundingSupports().get().get(0);
    assertEquals(ImmutableList.of(0, 1), s0.groundingChunkIndices().get());
    assertEquals(1, s0.segment().get().partIndex().get());

    GroundingSupport s1 = gm.groundingSupports().get().get(1);
    assertEquals(ImmutableList.of(2), s1.groundingChunkIndices().get());
    assertEquals(2, s1.segment().get().partIndex().get());
    assertEquals(
        "https://source2.example",
        gm.groundingChunks()
            .get()
            .get(s1.groundingChunkIndices().get().get(0))
            .web()
            .get()
            .uri()
            .get());
    assertEquals(
        "part 2 ",
        cand.content().get().parts().get().get(s1.segment().get().partIndex().get()).text().get());

    GroundingSupport s2 = gm.groundingSupports().get().get(2);
    assertEquals(ImmutableList.of(2), s2.groundingChunkIndices().get());
    assertEquals(2, s2.segment().get().partIndex().get());
  }

  @Test
  public void testMerge_groundingSupportsCornerCases() {
    GenerateContentResponse hop1 =
        GenerateContentResponse.builder()
            .candidates(
                ImmutableList.of(
                    Candidate.builder()
                        .index(0)
                        .content(
                            Content.builder()
                                .role("model")
                                .parts(
                                    ImmutableList.of(
                                        Part.fromText("c0-h1-p0 "), Part.fromText("c0-h1-p1 "))))
                        .finishReason(FinishReason.Known.CONTINUATION)
                        .continuationToken(TOKEN_1)
                        .build(),
                    Candidate.builder()
                        .index(1)
                        .content(
                            Content.builder()
                                .role("model")
                                .parts(ImmutableList.of(Part.fromText("c1-h1-p0 "))))
                        .groundingMetadata(
                            GroundingMetadata.builder()
                                .groundingChunks(
                                    ImmutableList.of(
                                        GroundingChunk.builder()
                                            .web(
                                                GroundingChunkWeb.builder()
                                                    .uri("https://c1-s0.example"))
                                            .build())))
                        .finishReason(FinishReason.Known.CONTINUATION)
                        .continuationToken(TOKEN_1)
                        .build()))
            .build();

    GenerateContentResponse hop2 =
        GenerateContentResponse.builder()
            .candidates(
                ImmutableList.of(
                    Candidate.builder()
                        .index(0)
                        .content(
                            Content.builder()
                                .role("model")
                                .parts(ImmutableList.of(Part.fromText("c0-h2-p0 "))))
                        .groundingMetadata(
                            GroundingMetadata.builder()
                                .groundingChunks(
                                    ImmutableList.of(
                                        GroundingChunk.builder()
                                            .web(
                                                GroundingChunkWeb.builder()
                                                    .uri("https://c0-s0.example"))
                                            .build(),
                                        GroundingChunk.builder()
                                            .web(
                                                GroundingChunkWeb.builder()
                                                    .uri("https://c0-s1.example"))
                                            .build())))
                        .finishReason(FinishReason.Known.CONTINUATION)
                        .continuationToken(TOKEN_2)
                        .build(),
                    Candidate.builder()
                        .index(1)
                        .content(
                            Content.builder()
                                .role("model")
                                .parts(ImmutableList.of(Part.fromText("c1-h2-p0 "))))
                        .groundingMetadata(
                            GroundingMetadata.builder()
                                .groundingChunks(
                                    ImmutableList.of(
                                        GroundingChunk.builder()
                                            .web(
                                                GroundingChunkWeb.builder()
                                                    .uri("https://c1-s1.example"))
                                            .build()))
                                .groundingSupports(
                                    ImmutableList.of(
                                        GroundingSupport.builder()
                                            .groundingChunkIndices(0)
                                            .segment(
                                                Segment.builder().partIndex(0).text("c1-h2-p0 "))
                                            .build())))
                        .finishReason(FinishReason.Known.STOP)
                        .build()))
            .build();

    GenerateContentResponse hop3 =
        GenerateContentResponse.builder()
            .candidates(
                ImmutableList.of(
                    Candidate.builder()
                        .index(0)
                        .content(
                            Content.builder()
                                .role("model")
                                .parts(ImmutableList.of(Part.fromText("c0-h3-p0."))))
                        .groundingMetadata(
                            GroundingMetadata.builder()
                                .groundingChunks(
                                    ImmutableList.of(
                                        GroundingChunk.builder()
                                            .web(
                                                GroundingChunkWeb.builder()
                                                    .uri("https://c0-s2.example"))
                                            .build()))
                                .groundingSupports(
                                    ImmutableList.of(
                                        GroundingSupport.builder().groundingChunkIndices(0).build(),
                                        GroundingSupport.builder()
                                            .segment(
                                                Segment.builder().partIndex(0).text("c0-h3-p0."))
                                            .build(),
                                        GroundingSupport.builder()
                                            .groundingChunkIndices(0)
                                            .segment(
                                                Segment.builder().partIndex(0).text("c0-h3-p0."))
                                            .build())))
                        .finishReason(FinishReason.Known.STOP)
                        .build()))
            .build();

    GenerateContentResponse merged = ContinuationUtil.merge(ImmutableList.of(hop1, hop2, hop3));

    Candidate cand0 = merged.candidates().get().get(0);
    GroundingMetadata gm0 = cand0.groundingMetadata().get();
    assertEquals(4, cand0.content().get().parts().get().size());
    assertEquals(3, gm0.groundingChunks().get().size());
    assertEquals(3, gm0.groundingSupports().get().size());
    assertFalse(gm0.groundingSupports().get().get(0).segment().isPresent());
    assertEquals(
        ImmutableList.of(2), gm0.groundingSupports().get().get(0).groundingChunkIndices().get());
    assertFalse(gm0.groundingSupports().get().get(1).groundingChunkIndices().isPresent());
    assertEquals(3, gm0.groundingSupports().get().get(1).segment().get().partIndex().get());
    GroundingSupport s2 = gm0.groundingSupports().get().get(2);
    assertEquals(
        "https://c0-s2.example",
        gm0.groundingChunks()
            .get()
            .get(s2.groundingChunkIndices().get().get(0))
            .web()
            .get()
            .uri()
            .get());
    assertEquals(
        "c0-h3-p0.",
        cand0.content().get().parts().get().get(s2.segment().get().partIndex().get()).text().get());

    Candidate cand1 = merged.candidates().get().get(1);
    GroundingMetadata gm1 = cand1.groundingMetadata().get();
    assertEquals(
        ImmutableList.of(1), gm1.groundingSupports().get().get(0).groundingChunkIndices().get());
    assertEquals(1, gm1.groundingSupports().get().get(0).segment().get().partIndex().get());
  }

  @Test
  public void testGenerate_mergesGroundingSupportsAcrossHops() {
    GenerateContentResponse hop1 =
        GenerateContentResponse.builder()
            .candidates(
                Candidate.builder()
                    .content(Content.builder().role("model").parts(Part.fromText("part 1 ")))
                    .finishReason(FinishReason.Known.CONTINUATION)
                    .continuationToken(TOKEN_1)
                    .groundingMetadata(
                        GroundingMetadata.builder()
                            .groundingChunks(
                                GroundingChunk.builder()
                                    .web(GroundingChunkWeb.builder().uri("https://source1.example"))
                                    .build())
                            .groundingSupports(
                                GroundingSupport.builder()
                                    .groundingChunkIndices(0)
                                    .segment(Segment.builder().partIndex(0).text("part 1 "))
                                    .build())))
            .build();
    GenerateContentResponse hop2 =
        GenerateContentResponse.builder()
            .candidates(
                Candidate.builder()
                    .content(Content.builder().role("model").parts(Part.fromText("part 2 ")))
                    .finishReason(FinishReason.Known.STOP)
                    .groundingMetadata(
                        GroundingMetadata.builder()
                            .groundingChunks(
                                GroundingChunk.builder()
                                    .web(GroundingChunkWeb.builder().uri("https://source2.example"))
                                    .build())
                            .groundingSupports(
                                GroundingSupport.builder()
                                    .groundingChunkIndices(0)
                                    .segment(Segment.builder().partIndex(0).text("part 2 "))
                                    .build())))
            .build();

    FakeModel model = new FakeModel(hop1, hop2);
    GenerateContentResponse merged = ContinuationUtil.generate(CONFIG, model::send);

    Candidate cand = merged.candidates().get().get(0);
    GroundingMetadata gm = cand.groundingMetadata().get();
    GroundingSupport s = gm.groundingSupports().get().get(1);
    assertEquals(
        "https://source2.example",
        gm.groundingChunks()
            .get()
            .get(s.groundingChunkIndices().get().get(0))
            .web()
            .get()
            .uri()
            .get());
    assertEquals(
        "part 2 ",
        cand.content().get().parts().get().get(s.segment().get().partIndex().get()).text().get());
  }

  @Test
  public void testStreamContinuation_preservesPerChunkGroundingAndMergesAcrossHops() {
    GenerateContentResponse hop1Chunk1 = response("hop 1 intro, ");
    GenerateContentResponse hop1Chunk2 =
        GenerateContentResponse.builder()
            .candidates(
                Candidate.builder()
                    .content(
                        Content.builder().role("model").parts(Part.fromText("hop 1 grounded. ")))
                    .finishReason(FinishReason.Known.CONTINUATION)
                    .continuationToken(TOKEN_1)
                    .groundingMetadata(
                        GroundingMetadata.builder()
                            .groundingChunks(
                                GroundingChunk.builder()
                                    .web(GroundingChunkWeb.builder().uri("https://source1.example"))
                                    .build())
                            .groundingSupports(
                                GroundingSupport.builder()
                                    .groundingChunkIndices(0)
                                    .segment(
                                        Segment.builder().partIndex(0).text("hop 1 grounded. "))
                                    .build())))
            .build();
    GenerateContentResponse hop2Chunk1 = response("hop 2 intro, ");
    GenerateContentResponse hop2Chunk2 =
        GenerateContentResponse.builder()
            .candidates(
                Candidate.builder()
                    .content(
                        Content.builder().role("model").parts(Part.fromText("hop 2 grounded.")))
                    .finishReason(FinishReason.Known.STOP)
                    .groundingMetadata(
                        GroundingMetadata.builder()
                            .groundingChunks(
                                GroundingChunk.builder()
                                    .web(GroundingChunkWeb.builder().uri("https://source2.example"))
                                    .build())
                            .groundingSupports(
                                GroundingSupport.builder()
                                    .groundingChunkIndices(0)
                                    .segment(Segment.builder().partIndex(0).text("hop 2 grounded."))
                                    .build())))
            .build();

    FakeStreamingModel model =
        new FakeStreamingModel(stream(hop1Chunk1, hop1Chunk2), stream(hop2Chunk1, hop2Chunk2));
    ResponseStream<GenerateContentResponse> first = model.send(CONFIG);
    first.setContinuation(ContinuationUtil.streamContinuation(CONFIG, model::send));
    List<GenerateContentResponse> streamed = chunks(first);

    assertEquals(4, streamed.size());
    // Each yielded chunk keeps per-chunk indices resolving to its own chunk and part.
    GroundingSupport sHop1 =
        streamed
            .get(1)
            .candidates()
            .get()
            .get(0)
            .groundingMetadata()
            .get()
            .groundingSupports()
            .get()
            .get(0);
    assertEquals(
        "https://source1.example",
        streamed
            .get(1)
            .candidates()
            .get()
            .get(0)
            .groundingMetadata()
            .get()
            .groundingChunks()
            .get()
            .get(sHop1.groundingChunkIndices().get().get(0))
            .web()
            .get()
            .uri()
            .get());
    GroundingSupport sHop2 =
        streamed
            .get(3)
            .candidates()
            .get()
            .get(0)
            .groundingMetadata()
            .get()
            .groundingSupports()
            .get()
            .get(0);
    assertEquals(
        "https://source2.example",
        streamed
            .get(3)
            .candidates()
            .get()
            .get(0)
            .groundingMetadata()
            .get()
            .groundingChunks()
            .get()
            .get(sHop2.groundingChunkIndices().get().get(0))
            .web()
            .get()
            .uri()
            .get());

    // Merging the streamed chunks shifts indices across all 4 chunks.
    GenerateContentResponse merged = ContinuationUtil.merge(streamed);
    Candidate cand = merged.candidates().get().get(0);
    GroundingMetadata gm = cand.groundingMetadata().get();
    assertEquals(2, gm.groundingSupports().get().size());
    GroundingSupport m0 = gm.groundingSupports().get().get(0);
    assertEquals(
        "https://source1.example",
        gm.groundingChunks()
            .get()
            .get(m0.groundingChunkIndices().get().get(0))
            .web()
            .get()
            .uri()
            .get());
    assertEquals(
        "hop 1 grounded. ",
        cand.content().get().parts().get().get(m0.segment().get().partIndex().get()).text().get());
    GroundingSupport m1 = gm.groundingSupports().get().get(1);
    assertEquals(
        "https://source2.example",
        gm.groundingChunks()
            .get()
            .get(m1.groundingChunkIndices().get().get(0))
            .web()
            .get()
            .uri()
            .get());
    assertEquals(
        "hop 2 grounded.",
        cand.content().get().parts().get().get(m1.segment().get().partIndex().get()).text().get());
  }

  @Test
  public void testMerge_sumsTokenCounts() {
    GenerateContentResponse first =
        response("Hello ", FinishReason.Known.CONTINUATION, TOKEN_1).toBuilder()
            .usageMetadata(
                GenerateContentResponseUsageMetadata.builder()
                    .promptTokenCount(10)
                    .cachedContentTokenCount(4)
                    .candidatesTokenCount(100)
                    .thoughtsTokenCount(5)
                    .totalTokenCount(115)
                    .promptTokensDetails(modalityTokenCount(MediaModality.Known.TEXT, 10))
                    .cacheTokensDetails(modalityTokenCount(MediaModality.Known.TEXT, 4))
                    .candidatesTokensDetails(modalityTokenCount(MediaModality.Known.TEXT, 100)))
            .build();
    GenerateContentResponse second =
        response("world", FinishReason.Known.STOP, null).toBuilder()
            .usageMetadata(
                GenerateContentResponseUsageMetadata.builder()
                    .promptTokenCount(110)
                    .cachedContentTokenCount(100)
                    .candidatesTokenCount(50)
                    .totalTokenCount(160)
                    .promptTokensDetails(modalityTokenCount(MediaModality.Known.TEXT, 110))
                    .cacheTokensDetails(modalityTokenCount(MediaModality.Known.TEXT, 100))
                    .candidatesTokensDetails(
                        modalityTokenCount(MediaModality.Known.TEXT, 50),
                        modalityTokenCount(MediaModality.Known.IMAGE, 3)))
            .build();

    GenerateContentResponse merged = ContinuationUtil.merge(ImmutableList.of(first, second));
    GenerateContentResponseUsageMetadata usage = merged.usageMetadata().get();

    assertEquals(120, usage.promptTokenCount().get());
    assertEquals(10, merged.initialPromptTokenCount().get());
    assertEquals(10, usage.initialPromptTokenCount().get());
    assertEquals(104, usage.cachedContentTokenCount().get());
    assertEquals(150, usage.candidatesTokenCount().get());
    assertEquals(5, usage.thoughtsTokenCount().get());
    assertEquals(275, usage.totalTokenCount().get());
    assertEquals(
        ImmutableList.of(modalityTokenCount(MediaModality.Known.TEXT, 120)),
        usage.promptTokensDetails().get());
    assertEquals(
        ImmutableList.of(modalityTokenCount(MediaModality.Known.TEXT, 104)),
        usage.cacheTokensDetails().get());
    assertEquals(
        ImmutableList.of(
            modalityTokenCount(MediaModality.Known.TEXT, 150),
            modalityTokenCount(MediaModality.Known.IMAGE, 3)),
        usage.candidatesTokensDetails().get());
  }

  @Test
  public void testMerge_takesHowTheGenerationEndedFromTheLastResponse() {
    GenerateContentResponse first =
        withFirstCandidate(
            response("Hello ", FinishReason.Known.CONTINUATION, TOKEN_1),
            candidate -> candidate.finishMessage("Stopped early."));
    GenerateContentResponse second = response("world", FinishReason.Known.STOP, null);

    Candidate candidate =
        ContinuationUtil.merge(ImmutableList.of(first, second)).candidates().get().get(0);

    assertEquals(FinishReason.Known.STOP, candidate.finishReason().get().knownEnum());
    assertFalse(candidate.continuationToken().isPresent());
    assertFalse(candidate.finishMessage().isPresent());
  }

  @Test
  public void testMerge_clearsFinishReasonAndKeepsPromptFeedbackWhenAResponseHasNoCandidates() {
    GenerateContentResponse first =
        withFirstCandidate(
                response("Partial output before block.", FinishReason.Known.CONTINUATION, TOKEN_1),
                candidate -> candidate.finishMessage("Continuing"))
            .toBuilder()
            .usageMetadata(
                GenerateContentResponseUsageMetadata.builder()
                    .promptTokenCount(10)
                    .candidatesTokenCount(100)
                    .totalTokenCount(110))
            .build();

    for (List<Candidate> emptyCandidates : Arrays.asList(null, ImmutableList.<Candidate>of())) {
      GenerateContentResponse.Builder secondBuilder =
          GenerateContentResponse.builder()
              .promptFeedback(
                  GenerateContentResponsePromptFeedback.builder()
                      .blockReason(BlockedReason.Known.OTHER)
                      .blockReasonMessage("Blocked on continuation hop"))
              .usageMetadata(
                  GenerateContentResponseUsageMetadata.builder()
                      .promptTokenCount(110)
                      .totalTokenCount(110));
      if (emptyCandidates != null) {
        secondBuilder.candidates(emptyCandidates);
      }
      GenerateContentResponse second = secondBuilder.build();

      GenerateContentResponse merged = ContinuationUtil.merge(ImmutableList.of(first, second));

      assertEquals("Partial output before block.", merged.text());
      assertEquals(1, merged.candidates().get().size());
      Candidate candidate = merged.candidates().get().get(0);
      assertFalse(candidate.finishReason().isPresent());
      assertFalse(candidate.continuationToken().isPresent());
      assertFalse(candidate.finishMessage().isPresent());
      assertEquals(
          BlockedReason.Known.OTHER, merged.promptFeedback().get().blockReason().get().knownEnum());
      assertEquals(
          "Blocked on continuation hop", merged.promptFeedback().get().blockReasonMessage().get());
      assertEquals(120, merged.usageMetadata().get().promptTokenCount().get());
      assertEquals(10, merged.usageMetadata().get().initialPromptTokenCount().get());
    }
  }

  @Test
  public void testMerge_keepsTheLatestSafetyRatingPerCategory() {
    GenerateContentResponse first =
        withFirstCandidate(
            response("Hello ", FinishReason.Known.CONTINUATION, TOKEN_1),
            candidate ->
                candidate.safetyRatings(
                    safetyRating(
                        HarmCategory.Known.HARM_CATEGORY_HARASSMENT, HarmProbability.Known.LOW),
                    safetyRating(
                        HarmCategory.Known.HARM_CATEGORY_HATE_SPEECH, HarmProbability.Known.LOW)));
    GenerateContentResponse second =
        withFirstCandidate(
            response("world", FinishReason.Known.STOP, null),
            candidate ->
                candidate.safetyRatings(
                    safetyRating(
                        HarmCategory.Known.HARM_CATEGORY_HARASSMENT,
                        HarmProbability.Known.MEDIUM)));

    List<SafetyRating> ratings =
        ContinuationUtil.merge(ImmutableList.of(first, second))
            .candidates()
            .get()
            .get(0)
            .safetyRatings()
            .get();

    assertEquals(
        ImmutableList.of(
            safetyRating(HarmCategory.Known.HARM_CATEGORY_HARASSMENT, HarmProbability.Known.MEDIUM),
            safetyRating(HarmCategory.Known.HARM_CATEGORY_HATE_SPEECH, HarmProbability.Known.LOW)),
        ratings);
  }

  @Test
  public void testMerge_takesOtherValuesFromTheLastResponseUnlessUnset() {
    GenerateContentResponse first =
        response("Hello ", FinishReason.Known.CONTINUATION, TOKEN_1).toBuilder()
            .modelVersion("model-1")
            .responseId("first")
            .sdkHttpResponse(HttpResponse.builder().headers(ImmutableMap.of("request", "first")))
            .build();
    GenerateContentResponse second =
        response("world", FinishReason.Known.STOP, null).toBuilder()
            .responseId("second")
            .sdkHttpResponse(HttpResponse.builder().headers(ImmutableMap.of("request", "second")))
            .build();

    GenerateContentResponse merged = ContinuationUtil.merge(ImmutableList.of(first, second));

    assertEquals("model-1", merged.modelVersion().get());
    assertEquals("second", merged.responseId().get());
    assertEquals(
        ImmutableMap.of("request", "second"), merged.sdkHttpResponse().get().headers().get());
  }

  @Test
  public void testMerge_mergesEachCandidateWithTheOneOfTheSameIndex() {
    GenerateContentResponse first =
        GenerateContentResponse.builder()
            .candidates(
                candidate(0, "Hello ", FinishReason.Known.CONTINUATION),
                candidate(1, "Bonjour ", FinishReason.Known.CONTINUATION),
                candidate(2, "Hola", FinishReason.Known.STOP))
            .build();
    // Listed out of order, so the candidates can only be matched by index.
    GenerateContentResponse second =
        GenerateContentResponse.builder()
            .candidates(
                candidate(1, "le monde", FinishReason.Known.STOP),
                candidate(0, "world", FinishReason.Known.STOP))
            .build();

    List<Candidate> candidates =
        ContinuationUtil.merge(ImmutableList.of(first, second)).candidates().get();

    assertEquals(
        ImmutableList.of("Hello world", "Bonjour le monde", "Hola"),
        candidates.stream()
            .map(candidate -> candidate.content().get().text())
            .collect(Collectors.toList()));
    assertEquals(FinishReason.Known.STOP, candidates.get(1).finishReason().get().knownEnum());
  }

  @Test
  public void testMerge_keepsAnEmptyTextPartFromARequestSpentThinking() {
    byte[] signature = "signature".getBytes(UTF_8);
    GenerateContentResponse thinking =
        GenerateContentResponse.builder()
            .candidates(
                Candidate.builder()
                    .content(
                        Content.builder()
                            .role("model")
                            .parts(Part.builder().text("").thoughtSignature(signature)))
                    .finishReason(FinishReason.Known.CONTINUATION)
                    .continuationToken(TOKEN_1))
            .build();
    GenerateContentResponse answer = response("Final answer.", FinishReason.Known.STOP, null);

    List<Part> parts = ContinuationUtil.merge(ImmutableList.of(thinking, answer)).parts();

    assertEquals(2, parts.size());
    assertEquals("", parts.get(0).text().get());
    assertArrayEquals(signature, parts.get(0).thoughtSignature().get());
    assertEquals("Final answer.", parts.get(1).text().get());
  }

  @Test
  public void testGenerate_stopsWhenTheResponseHasNoCandidates() {
    GenerateContentResponse empty =
        GenerateContentResponse.builder().candidates(ImmutableList.of()).build();
    FakeModel model = new FakeModel(empty);

    assertSame(empty, ContinuationUtil.generate(CONFIG, model::send));
    assertEquals(1, model.configs.size());
  }

  @Test
  public void testMerge_keepsACandidateOnlyALaterResponseHas() {
    GenerateContentResponse first =
        GenerateContentResponse.builder()
            .candidates(candidate(0, "Hello ", FinishReason.Known.CONTINUATION))
            .build();
    GenerateContentResponse second =
        GenerateContentResponse.builder()
            .candidates(
                candidate(0, "world", FinishReason.Known.STOP),
                candidate(1, "Hola", FinishReason.Known.STOP))
            .build();

    List<Candidate> candidates =
        ContinuationUtil.merge(ImmutableList.of(first, second)).candidates().get();

    assertEquals(
        ImmutableList.of("Hello world", "Hola"),
        candidates.stream()
            .map(candidate -> candidate.content().get().text())
            .collect(Collectors.toList()));
  }

  @Test
  public void testMerge_sumsLogProbabilitiesAndConcatenatesNestedLists() {
    GenerateContentResponse first =
        withFirstCandidate(
            response("Hello ", FinishReason.Known.CONTINUATION, TOKEN_1),
            candidate ->
                candidate
                    .logprobsResult(logprobs("Hello", -1.5f))
                    .groundingMetadata(GroundingMetadata.builder().webSearchQueries("query 1")));
    GenerateContentResponse second =
        withFirstCandidate(
            response("world", FinishReason.Known.STOP, null),
            candidate ->
                candidate
                    .logprobsResult(logprobs("world", -2.0f))
                    .groundingMetadata(GroundingMetadata.builder().webSearchQueries("query 2")));

    Candidate candidate =
        ContinuationUtil.merge(ImmutableList.of(first, second)).candidates().get().get(0);

    LogprobsResult logprobs = candidate.logprobsResult().get();
    assertEquals(-3.5f, logprobs.logProbabilitySum().get());
    assertEquals(
        ImmutableList.of("Hello", "world"),
        logprobs.chosenCandidates().get().stream()
            .map(chosen -> chosen.token().get())
            .collect(Collectors.toList()));
    assertEquals(
        ImmutableList.of("query 1", "query 2"),
        candidate.groundingMetadata().get().webSearchQueries().get());
  }

  @Test
  public void testMerge_sumsModalityTokenCountsIntoAnEmptyList() {
    GenerateContentResponse first =
        response("Hello ", FinishReason.Known.CONTINUATION, TOKEN_1).toBuilder()
            .usageMetadata(
                GenerateContentResponseUsageMetadata.builder()
                    .promptTokensDetails(ImmutableList.of()))
            .build();
    GenerateContentResponse second =
        response("world", FinishReason.Known.STOP, null).toBuilder()
            .usageMetadata(
                GenerateContentResponseUsageMetadata.builder()
                    .promptTokensDetails(modalityTokenCount(MediaModality.Known.TEXT, 10)))
            .build();

    GenerateContentResponseUsageMetadata usage =
        ContinuationUtil.merge(ImmutableList.of(first, second)).usageMetadata().get();

    assertEquals(
        ImmutableList.of(modalityTokenCount(MediaModality.Known.TEXT, 10)),
        usage.promptTokensDetails().get());
  }

  private static LogprobsResult logprobs(String token, float logProbability) {
    return LogprobsResult.builder()
        .logProbabilitySum(logProbability)
        .chosenCandidates(
            LogprobsResultCandidate.builder().token(token).logProbability(logProbability))
        .build();
  }

  private interface CandidateChange {
    Candidate.Builder apply(Candidate.Builder candidate);
  }

  private static GenerateContentResponse withFirstCandidate(
      GenerateContentResponse response, CandidateChange change) {
    Candidate first = response.candidates().get().get(0);
    return response.toBuilder().candidates(change.apply(first.toBuilder()).build()).build();
  }

  private static Candidate candidate(int index, String text, FinishReason.Known finishReason) {
    return Candidate.builder()
        .index(index)
        .content(Content.builder().role("model").parts(Part.fromText(text)))
        .finishReason(finishReason)
        .build();
  }

  private static ModalityTokenCount modalityTokenCount(MediaModality.Known modality, int count) {
    return ModalityTokenCount.builder().modality(modality).tokenCount(count).build();
  }

  private static SafetyRating safetyRating(
      HarmCategory.Known category, HarmProbability.Known probability) {
    return SafetyRating.builder().category(category).probability(probability).build();
  }
}
