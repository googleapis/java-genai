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

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.ImmutableList;
import com.google.genai.types.Candidate;
import com.google.genai.types.GenerateContentResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.Okio;
import okio.Source;
import okio.Timeout;
import org.junit.jupiter.api.Test;

public final class ResponseStreamTest {

  public static class DummyConverter {
    public JsonNode convert(JsonNode fromObject, ObjectNode parentObject) {
      return fromObject;
    }
  }

  @Test
  public void testMultiLineSseParsing() throws Exception {
    String sseData =
        "data: {\"candidates\": [{\"content\": {\"parts\": [{\"text\": \"line1\\n\"}]}},\n"
            + "data: {\"content\": {\"parts\": [{\"text\": \"line2\"}]}}]}\n"
            + "\n"; // End of event

    ResponseBody body =
        ResponseBody.create(
            sseData.getBytes(StandardCharsets.UTF_8), MediaType.parse("text/event-stream"));
    FakeApiResponse response = new FakeApiResponse(Headers.of(), body);

    DummyConverter converter = new DummyConverter();
    ResponseStream<GenerateContentResponse> responseStream =
        new ResponseStream<>(GenerateContentResponse.class, response, converter, "convert");

    Iterator<GenerateContentResponse> iterator = responseStream.iterator();

    assertTrue(iterator.hasNext());
    GenerateContentResponse response1 = iterator.next();

    assertTrue(response1.candidates().isPresent());
    assertEquals(2, response1.candidates().get().size());

    Candidate c1 = response1.candidates().get().get(0);
    assertEquals("line1\n", c1.content().get().text());

    Candidate c2 = response1.candidates().get().get(1);
    assertEquals("line2", c2.content().get().text());

    assertTrue(!iterator.hasNext());
  }

  @Test
  public void testIgnoreNonSseLines() throws Exception {
    String sseData =
        "data: {\"candidates\": [{\"content\": {\"parts\": [{\"text\": \"valid data\"}]}}]}\n"
            + ": some comment line\n"
            + "ignored field: some value\n"
            + "\n"; // End of event

    ResponseBody body =
        ResponseBody.create(
            sseData.getBytes(StandardCharsets.UTF_8), MediaType.parse("text/event-stream"));
    FakeApiResponse response = new FakeApiResponse(Headers.of(), body);

    DummyConverter converter = new DummyConverter();
    ResponseStream<GenerateContentResponse> responseStream =
        new ResponseStream<>(GenerateContentResponse.class, response, converter, "convert");

    Iterator<GenerateContentResponse> iterator = responseStream.iterator();

    assertTrue(iterator.hasNext());
    GenerateContentResponse response1 = iterator.next();

    assertEquals("valid data", response1.text());

    assertTrue(!iterator.hasNext());
  }

  @Test
  public void testStreamErrorHandling() throws Exception {
    String sseData =
        "data: {\"candidates\": [{\"content\": {\"parts\": [{\"text\": \"valid data\"}]}}]}\n"
            + "\n"
            + "data: {\"error\": {\"code\": 429, \"message\": \"Quota exceeded\", \"status\":"
            + " \"RESOURCE_EXHAUSTED\"}}\n"
            + "\n";

    ResponseBody body =
        ResponseBody.create(
            sseData.getBytes(StandardCharsets.UTF_8), MediaType.parse("text/event-stream"));
    FakeApiResponse response = new FakeApiResponse(Headers.of(), body);

    DummyConverter converter = new DummyConverter();
    ResponseStream<GenerateContentResponse> responseStream =
        new ResponseStream<>(GenerateContentResponse.class, response, converter, "convert");

    Iterator<GenerateContentResponse> iterator = responseStream.iterator();

    assertTrue(iterator.hasNext());
    GenerateContentResponse response1 = iterator.next();
    assertEquals("valid data", response1.text());

    assertTrue(iterator.hasNext());

    try {
      iterator.next();
      org.junit.jupiter.api.Assertions.fail("Expected ApiException was not thrown");
    } catch (com.google.genai.errors.ApiException e) {
      assertEquals(429, e.code());
      assertEquals("RESOURCE_EXHAUSTED", e.status());
      assertTrue(e.getMessage().contains("Quota exceeded"));
    }
  }

  @Test
  public void testMultipleValidEvents() throws Exception {
    String sseData =
        "data: {\"candidates\": [{\"content\": {\"parts\": [{\"text\": \"chunk1\"}]}}]}\n"
            + "\n"
            + "data: {\"candidates\": [{\"content\": {\"parts\": [{\"text\": \"chunk2\"}]}}]}\n"
            + "\n";

    ResponseBody body =
        ResponseBody.create(
            sseData.getBytes(StandardCharsets.UTF_8), MediaType.parse("text/event-stream"));
    FakeApiResponse response = new FakeApiResponse(Headers.of(), body);

    DummyConverter converter = new DummyConverter();
    ResponseStream<GenerateContentResponse> responseStream =
        new ResponseStream<>(GenerateContentResponse.class, response, converter, "convert");

    Iterator<GenerateContentResponse> iterator = responseStream.iterator();

    assertTrue(iterator.hasNext());
    GenerateContentResponse response1 = iterator.next();
    assertEquals("chunk1", response1.text());

    assertTrue(iterator.hasNext());
    GenerateContentResponse response2 = iterator.next();
    assertEquals("chunk2", response2.text());

    assertTrue(!iterator.hasNext());
  }

  @Test
  public void testContinuationGoesOnIntoTheNextStream() throws Exception {
    ResponseStream<GenerateContentResponse> first = textStream("a", "b");
    ResponseStream<GenerateContentResponse> second = textStream("c");
    List<String> observed = new ArrayList<>();
    first.recordingHistory = true;
    first.setContinuation(
        new ResponseStream.Continuation<GenerateContentResponse>() {
          private boolean handedOut = false;

          @Override
          public void observe(GenerateContentResponse element) {
            observed.add(element.text());
          }

          @Override
          public ResponseStream<GenerateContentResponse> next() {
            if (handedOut) {
              return null;
            }
            handedOut = true;
            return second;
          }
        });

    List<String> texts = new ArrayList<>();
    Iterator<GenerateContentResponse> iterator = first.iterator();
    while (iterator.hasNext()) {
      assertFalse(first.isConsumed());
      texts.add(iterator.next().text());
    }

    assertEquals(ImmutableList.of("a", "b", "c"), texts);
    assertEquals(texts, observed);
    // A chat session reads the history of the stream it was handed, so it holds every request's
    // chunks, and the stream counts as consumed only once the last request is.
    assertEquals(3, first.history.size());
    assertTrue(first.isConsumed());
  }

  /**
   * Pins that close() from another thread releases a read that is blocked waiting for bytes, by
   * closing the HTTP response before it waits for the reader's lock.
   */
  @Test
  public void testCloseClosesResponseWhileAnotherThreadIsReading() throws Exception {
    BlockingSource source = new BlockingSource();
    ResponseBody body =
        ResponseBody.create(Okio.buffer(source), MediaType.parse("text/event-stream"), -1);
    HttpApiResponse response =
        new HttpApiResponse(
            new Response.Builder()
                .request(new Request.Builder().url("https://example.com/stream").build())
                .protocol(Protocol.HTTP_1_1)
                .code(200)
                .message("OK")
                .body(body)
                .build());
    ResponseStream<GenerateContentResponse> stream =
        new ResponseStream<>(
            GenerateContentResponse.class, response, new DummyConverter(), "convert");
    ExecutorService executor = Executors.newFixedThreadPool(2);
    try {
      Future<Boolean> reading = executor.submit(() -> stream.iterator().hasNext());
      // While the source blocks, BufferedReader.readLine() holds the reader lock.
      assertTrue(source.readStarted.await(5, SECONDS), "The stream read did not start");

      Future<?> closing = executor.submit(stream::close);

      assertTrue(
          source.closed.await(5, SECONDS),
          "close() did not close the HTTP response while another thread was reading");
      closing.get(5, SECONDS);
      assertFalse(reading.get(5, SECONDS));
    } finally {
      // Release the read so a failing run does not leave threads blocked.
      source.releaseRead.countDown();
      executor.shutdownNow();
      assertTrue(executor.awaitTermination(5, SECONDS));
    }
  }

  /** Pins that close() still closes the reader when closing the HTTP response throws. */
  @Test
  public void testCloseClosesReaderWhenResponseCloseThrows() {
    BlockingSource source = new BlockingSource();
    ResponseBody body =
        ResponseBody.create(Okio.buffer(source), MediaType.parse("text/event-stream"), -1);
    ApiResponse response =
        new ApiResponse() {
          @Override
          public ResponseBody getBody() {
            return body;
          }

          @Override
          public Headers getHeaders() {
            return Headers.of();
          }

          @Override
          public void close() {
            throw new IllegalStateException("response close failed");
          }
        };
    ResponseStream<GenerateContentResponse> stream =
        new ResponseStream<>(
            GenerateContentResponse.class, response, new DummyConverter(), "convert");

    IllegalStateException e =
        assertTimeoutPreemptively(
            Duration.ofSeconds(5), () -> assertThrows(IllegalStateException.class, stream::close));

    assertEquals("response close failed", e.getMessage());
    assertEquals(0, source.closed.getCount(), "close() did not close the reader");
  }

  /** A response body source whose read blocks until the source is closed. */
  private static final class BlockingSource implements Source {
    final CountDownLatch readStarted = new CountDownLatch(1);
    final CountDownLatch releaseRead = new CountDownLatch(1);
    final CountDownLatch closed = new CountDownLatch(1);

    @Override
    public long read(Buffer sink, long byteCount) throws IOException {
      readStarted.countDown();
      try {
        releaseRead.await();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        throw new IOException(e);
      }
      return -1;
    }

    @Override
    public Timeout timeout() {
      return Timeout.NONE;
    }

    @Override
    public void close() {
      closed.countDown();
      releaseRead.countDown();
    }
  }

  private static ResponseStream<GenerateContentResponse> textStream(String... texts) {
    StringBuilder sseData = new StringBuilder();
    for (String text : texts) {
      sseData
          .append("data: {\"candidates\": [{\"content\": {\"parts\": [{\"text\": \"")
          .append(text)
          .append("\"}]}}]}\n\n");
    }
    ResponseBody body =
        ResponseBody.create(
            sseData.toString().getBytes(StandardCharsets.UTF_8),
            MediaType.parse("text/event-stream"));
    return new ResponseStream<>(
        GenerateContentResponse.class,
        new FakeApiResponse(Headers.of(), body),
        new DummyConverter(),
        "convert");
  }
}
