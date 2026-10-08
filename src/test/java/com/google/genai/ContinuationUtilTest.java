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
import com.google.genai.types.Candidate;
import com.google.genai.types.Citation;
import com.google.genai.types.CitationMetadata;
import com.google.genai.types.Content;
import com.google.genai.types.FinishReason;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.GenerateContentResponseUsageMetadata;
import com.google.genai.types.GroundingMetadata;
import com.google.genai.types.HarmCategory;
import com.google.genai.types.HarmProbability;
import com.google.genai.types.HttpResponse;
import com.google.genai.types.LogprobsResult;
import com.google.genai.types.LogprobsResultCandidate;
import com.google.genai.types.MediaModality;
import com.google.genai.types.ModalityTokenCount;
import com.google.genai.types.Part;
import com.google.genai.types.SafetyRating;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
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
    String sse =
        Arrays.stream(chunks)
            .map(chunk -> "data: " + chunk.toJson() + "\n\n")
            .collect(Collectors.joining());
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

    assertSame(only, ContinuationUtil.generate(CONFIG, model::send));
    assertEquals(1, model.configs.size());
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

    assertEquals(ImmutableList.of("a", "b", "c", "d"), texts(first));
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

    assertEquals(ImmutableList.of("a", "b", "c"), texts(first));
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

    assertEquals(ImmutableList.of("a"), texts(first));
    assertTrue(configs.isEmpty());
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
  public void testMerge_sumsTokenCounts() {
    GenerateContentResponse first =
        response("Hello ", FinishReason.Known.CONTINUATION, TOKEN_1).toBuilder()
            .usageMetadata(
                GenerateContentResponseUsageMetadata.builder()
                    .promptTokenCount(10)
                    .candidatesTokenCount(100)
                    .thoughtsTokenCount(5)
                    .totalTokenCount(115)
                    .promptTokensDetails(modalityTokenCount(MediaModality.Known.TEXT, 10))
                    .candidatesTokensDetails(modalityTokenCount(MediaModality.Known.TEXT, 100)))
            .build();
    GenerateContentResponse second =
        response("world", FinishReason.Known.STOP, null).toBuilder()
            .usageMetadata(
                GenerateContentResponseUsageMetadata.builder()
                    .promptTokenCount(115)
                    .candidatesTokenCount(50)
                    .totalTokenCount(165)
                    .candidatesTokensDetails(
                        modalityTokenCount(MediaModality.Known.TEXT, 50),
                        modalityTokenCount(MediaModality.Known.IMAGE, 3)))
            .build();

    GenerateContentResponseUsageMetadata usage =
        ContinuationUtil.merge(ImmutableList.of(first, second)).usageMetadata().get();

    assertEquals(125, usage.promptTokenCount().get());
    assertEquals(150, usage.candidatesTokenCount().get());
    assertEquals(5, usage.thoughtsTokenCount().get());
    assertEquals(280, usage.totalTokenCount().get());
    assertEquals(
        ImmutableList.of(modalityTokenCount(MediaModality.Known.TEXT, 10)),
        usage.promptTokensDetails().get());
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
