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

package com.google.genai.gaos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.genai.Client;
import com.google.genai.gaos.models.errors.ContinuationInterruptedException;
import com.google.genai.gaos.models.interactions.CreateModelInteraction;
import com.google.genai.gaos.models.interactions.GenerationConfig;
import com.google.genai.gaos.models.interactions.Interaction;
import com.google.genai.gaos.models.interactions.InteractionCompletedEvent;
import com.google.genai.gaos.models.interactions.InteractionCreatedEvent;
import com.google.genai.gaos.models.interactions.InteractionSSEEvent;
import com.google.genai.gaos.models.interactions.InteractionSSEStreamEvent;
import com.google.genai.gaos.models.interactions.InteractionsInput;
import com.google.genai.gaos.models.interactions.StepDelta;
import com.google.genai.gaos.models.operations.CreateInteractionRequest;
import com.google.genai.gaos.models.operations.CreateInteractionRequestBody;
import com.google.genai.gaos.models.operations.CreateInteractionResponse;
import com.google.genai.gaos.utils.EventStream;
import com.google.genai.types.ClientOptions;
import com.google.genai.types.HttpOptions;
import com.google.genai.types.HttpRetryOptions;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.function.LongSupplier;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.Okio;
import okio.Source;
import okio.Timeout;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for Interactions automatic continuation behaviors that the shared conformance spec
 * does not cover: the whole-call {@code timeout} (with a fake clock) and the {@code
 * CreateInteractionRequest} / builder surfaces.
 */
public final class InteractionContinuationTest {

  private static final ObjectMapper MAPPER = new ObjectMapper();
  private static final MediaType JSON_MEDIA_TYPE = MediaType.get("application/json");
  private static final MediaType SSE_MEDIA_TYPE = MediaType.get("text/event-stream");

  /** A manual clock for deadline tests, in nanoseconds. */
  private static final class FakeClock implements LongSupplier {
    private long nowNanos = Duration.ofSeconds(1000).toNanos();

    @Override
    public long getAsLong() {
      return nowNanos;
    }

    void advance(Duration duration) {
      nowNanos += duration.toNanos();
    }
  }

  private final FakeClock clock = new FakeClock();
  private final LongSupplier realClock = InteractionContinuation.clock;

  @AfterEach
  void restoreClock() {
    InteractionContinuation.clock = realClock;
  }

  private void useFakeClock() {
    InteractionContinuation.clock = clock;
  }

  // --- Fake server -----------------------------------------------------------------------------

  /** One scripted HTTP response: a JSON interaction, or SSE events. */
  private static final class Hop {
    final String json;
    final List<String> sseEvents;
    final int advanceClockBeforeEvent;
    final Duration advance;

    private Hop(String json, List<String> sseEvents, int advanceClockBeforeEvent, Duration advance) {
      this.json = json;
      this.sseEvents = sseEvents;
      this.advanceClockBeforeEvent = advanceClockBeforeEvent;
      this.advance = advance;
    }

    static Hop unary(String json, Duration advance) {
      return new Hop(json, null, -1, advance);
    }

    static Hop sse(List<String> events, int advanceClockBeforeEvent, Duration advance) {
      return new Hop(null, events, advanceClockBeforeEvent, advance);
    }
  }

  private static String unaryJson(String id, String status, String token, String text, int out) {
    StringBuilder json =
        new StringBuilder("{\"id\":\"")
            .append(id)
            .append("\",\"status\":\"")
            .append(status)
            .append("\"");
    if (token != null) {
      json.append(",\"continuation_token\":\"").append(token).append("\"");
    }
    json.append(",\"steps\":[{\"type\":\"model_output\",\"content\":[{\"type\":\"text\",\"text\":\"")
        .append(text)
        .append("\"}]}]");
    json.append(",\"usage\":{\"total_input_tokens\":10,\"total_output_tokens\":")
        .append(out)
        .append(",\"total_tokens\":")
        .append(10 + out)
        .append("}}");
    return json.toString();
  }

  private static String created(String id) {
    return "event: interaction.created\ndata: {\"event_type\":\"interaction.created\","
        + "\"interaction\":{\"id\":\""
        + id
        + "\",\"status\":\"in_progress\"}}\n\n";
  }

  private static String delta(String text) {
    return "event: step.delta\ndata: {\"event_type\":\"step.delta\",\"index\":0,"
        + "\"delta\":{\"type\":\"text\",\"text\":\""
        + text
        + "\"}}\n\n";
  }

  private static String completed(String id, String status, String token) {
    return "event: interaction.completed\ndata: {\"event_type\":\"interaction.completed\","
        + "\"interaction\":{\"id\":\""
        + id
        + "\",\"status\":\""
        + status
        + "\""
        + (token == null ? "" : ",\"continuation_token\":\"" + token + "\"")
        + "}}\n\n";
  }

  /** Serves scripted hops, recording every request body, and advancing the fake clock. */
  private final class FakeServer implements Interceptor {
    private final List<Hop> hops;
    final List<JsonNode> requests = new ArrayList<>();

    FakeServer(Hop... hops) {
      this.hops = new ArrayList<>();
      for (Hop hop : hops) {
        this.hops.add(hop);
      }
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
      Request request = chain.request();
      Buffer buffer = new Buffer();
      request.body().writeTo(buffer);
      Hop hop;
      synchronized (this) {
        requests.add(MAPPER.readTree(buffer.readUtf8()));
        assertTrue(requests.size() <= hops.size(), "Unexpected request #" + requests.size());
        hop = hops.get(requests.size() - 1);
      }
      if (hop.json != null) {
        if (hop.advance != null) {
          clock.advance(hop.advance);
        }
        return new Response.Builder()
            .request(request)
            .protocol(Protocol.HTTP_1_1)
            .code(200)
            .message("OK")
            .header("Content-Type", "application/json")
            .body(ResponseBody.create(hop.json, JSON_MEDIA_TYPE))
            .build();
      }
      // One SSE event per read, so the clock can be advanced right before a given event.
      List<Buffer> chunks = new ArrayList<>();
      for (String event : hop.sseEvents) {
        chunks.add(new Buffer().writeUtf8(event));
      }
      Source source =
          new Source() {
            int index = 0;

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
              while (index < chunks.size() && chunks.get(index).size() == 0) {
                index++;
              }
              if (index >= chunks.size()) {
                return -1L;
              }
              if (index == hop.advanceClockBeforeEvent && hop.advance != null) {
                clock.advance(hop.advance);
              }
              return chunks.get(index).read(sink, byteCount);
            }

            @Override
            public Timeout timeout() {
              return Timeout.NONE;
            }

            @Override
            public void close() {}
          };
      BufferedSource buffered = Okio.buffer(source);
      ResponseBody body =
          new ResponseBody() {
            @Override
            public MediaType contentType() {
              return SSE_MEDIA_TYPE;
            }

            @Override
            public long contentLength() {
              return -1L;
            }

            @Override
            public BufferedSource source() {
              return buffered;
            }
          };
      return new Response.Builder()
          .request(request)
          .protocol(Protocol.HTTP_1_1)
          .code(200)
          .message("OK")
          .header("Content-Type", "text/event-stream")
          .body(body)
          .build();
    }
  }

  private static Client makeClient(Interceptor fake) {
    return Client.builder()
        .apiKey("test-key")
        .vertexAI(false)
        .httpOptions(
            HttpOptions.builder()
                .retryOptions(HttpRetryOptions.builder().attempts(1).build())
                .build())
        .clientOptions(
            ClientOptions.builder()
                .customHttpClient(new OkHttpClient.Builder().addInterceptor(fake).build())
                .build())
        .build();
  }

  private static CreateModelInteraction.Builder modelBody() {
    return CreateModelInteraction.builder().model("gemini-2.5-pro").input(InteractionsInput.of("Hello"));
  }

  private static String eventType(InteractionSSEStreamEvent event) {
    InteractionSSEEvent data = event.data().get();
    if (data instanceof InteractionCreatedEvent) {
      return "interaction.created";
    }
    if (data instanceof StepDelta) {
      return "step.delta";
    }
    if (data instanceof InteractionCompletedEvent) {
      return "interaction.completed";
    }
    return data.eventType();
  }

  // --- CallDeadline ----------------------------------------------------------------------------

  @Test
  public void callDeadlineBoundsTheWholeCallOnlyWhenEnabled() {
    useFakeClock();
    InteractionContinuation.CallDeadline deadline =
        new InteractionContinuation.CallDeadline(Duration.ofSeconds(10), true);
    clock.advance(Duration.ofSeconds(4));
    assertFalse(deadline.expired());
    deadline.check();
    clock.advance(Duration.ofSeconds(7));
    assertTrue(deadline.expired());
    assertThrows(InteractionContinuation.ContinuationTimeoutException.class, deadline::check);

    // No timeout, or continuation disabled (opt-out / background / agent calls): never expires.
    InteractionContinuation.CallDeadline unbounded =
        new InteractionContinuation.CallDeadline(null, true);
    InteractionContinuation.CallDeadline disabled =
        new InteractionContinuation.CallDeadline(Duration.ofSeconds(60), false);
    clock.advance(Duration.ofDays(365));
    assertFalse(unbounded.expired());
    unbounded.check();
    assertFalse(disabled.expired());
    disabled.check();
  }

  // --- Unary timeout ---------------------------------------------------------------------------

  @Test
  public void unaryTimeoutWithinBudgetCompletes() {
    useFakeClock();
    FakeServer fake =
        new FakeServer(
            Hop.unary(unaryJson("i1", "requires_continuation", "t1", "A ", 40), Duration.ofSeconds(10)),
            Hop.unary(unaryJson("i2", "requires_continuation", "t2", "B ", 30), Duration.ofSeconds(10)),
            Hop.unary(unaryJson("i3", "completed", null, "C", 20), Duration.ofSeconds(10)));
    Client client = makeClient(fake);
    try {
      Interaction result =
          client
              .interactions
              .create()
              .body(CreateInteractionRequestBody.of(modelBody().build()))
              .timeout(Duration.ofSeconds(60))
              .call()
              .interaction()
              .get();
      assertEquals("completed", result.status().get().value());
      assertEquals(Arrays.asList("i1", "i2", "i3"), result.continuationIds().get());
      assertEquals("A B C", result.outputText().get());
      assertEquals(3, fake.requests.size());
    } finally {
      client.close();
    }
  }

  @Test
  public void unaryTimeoutExpiryThrowsInterruptedWithPartialResult() {
    useFakeClock();
    FakeServer fake =
        new FakeServer(
            Hop.unary(unaryJson("i1", "requires_continuation", "t1", "A ", 40), Duration.ofSeconds(10)),
            Hop.unary(unaryJson("i2", "requires_continuation", "t2", "B ", 30), Duration.ofSeconds(10)),
            Hop.unary(unaryJson("i3", "completed", null, "C", 20), Duration.ofSeconds(10)));
    Client client = makeClient(fake);
    try {
      CreateInteractionRequest request =
          CreateInteractionRequest.builder()
              .body(CreateInteractionRequestBody.of(modelBody().build()))
              .timeout(Duration.ofSeconds(15))
              .build();
      ContinuationInterruptedException error =
          assertThrows(
              ContinuationInterruptedException.class, () -> client.interactions.create(request));
      // Hop 1 (10s) left 5s for hop 2, which used it up; hop 3 never started.
      assertEquals(2, fake.requests.size());
      assertTrue(
          error.getCause() instanceof InteractionContinuation.ContinuationTimeoutException,
          String.valueOf(error.getCause()));
      assertEquals("t2", error.continuationToken().get());
      assertEquals("i2", error.interactionId().get());
      assertEquals(Arrays.asList("i1", "i2"), error.continuationIds());
      Interaction partial = error.partialInteraction().get();
      assertEquals("requires_continuation", partial.status().get().value());
      assertEquals("A B ", partial.outputText().get());
      assertEquals(70, partial.usage().get().totalOutputTokens().get());
      assertEquals(10, partial.usage().get().initialInputTokens().get());
    } finally {
      client.close();
    }
  }

  @Test
  public void timeoutIsIgnoredWhenContinuationIsDisabled() {
    useFakeClock();
    FakeServer fake =
        new FakeServer(
            Hop.unary(
                unaryJson("i1", "requires_continuation", "t1", "A ", 40), Duration.ofDays(1)));
    Client client = makeClient(fake);
    try {
      CreateInteractionResponse response =
          client
              .interactions
              .create()
              .body(CreateInteractionRequestBody.of(modelBody().build()))
              .automaticContinuation(false)
              .timeout(Duration.ofSeconds(1))
              .call();
      assertEquals("requires_continuation", response.interaction().get().status().get().value());
      assertEquals(1, fake.requests.size());
    } finally {
      client.close();
    }
  }

  // --- Stream timeout --------------------------------------------------------------------------

  @Test
  public void streamTimeoutExpiryBetweenHopsThrowsInterrupted() {
    useFakeClock();
    FakeServer fake =
        new FakeServer(
            // Hop 1 outlives the 15s budget before its terminal event.
            Hop.sse(
                Arrays.asList(created("i1"), delta("Part 1 "), completed("i1", "requires_continuation", "t1")),
                /* advanceClockBeforeEvent= */ 2,
                Duration.ofSeconds(20)));
    Client client = makeClient(fake);
    try {
      EventStream<InteractionSSEStreamEvent> stream =
          client
              .interactions
              .create()
              .body(CreateInteractionRequestBody.of(modelBody().stream(true).build()))
              .timeout(Duration.ofSeconds(15))
              .call()
              .events();
      List<String> received = new ArrayList<>();
      ContinuationInterruptedException error =
          assertThrows(
              ContinuationInterruptedException.class,
              () -> {
                for (InteractionSSEStreamEvent event : stream) {
                  received.add(eventType(event));
                }
              });
      // Hop 1's events were all yielded; hop 2 never started.
      assertEquals(
          Arrays.asList("interaction.created", "step.delta", "interaction.completed"), received);
      assertEquals(1, fake.requests.size());
      assertTrue(
          error.getCause() instanceof InteractionContinuation.ContinuationTimeoutException,
          String.valueOf(error.getCause()));
      assertEquals("t1", error.continuationToken().get());
      assertEquals("i1", error.interactionId().get());
      assertFalse(error.partialInteraction().isPresent());
    } finally {
      client.close();
    }
  }

  @Test
  public void streamTimeoutExpiryMidHopThrowsInterruptedWithPreviousToken() {
    useFakeClock();
    FakeServer fake =
        new FakeServer(
            Hop.sse(
                Arrays.asList(created("i1"), delta("Part 1 "), completed("i1", "requires_continuation", "t1")),
                -1,
                null),
            // The deadline passes while waiting for the third event of hop 2.
            Hop.sse(
                Arrays.asList(
                    created("i2"),
                    delta("Part 2a "),
                    delta("Part 2b "),
                    completed("i2", "completed", null)),
                /* advanceClockBeforeEvent= */ 2,
                Duration.ofSeconds(100)));
    Client client = makeClient(fake);
    try {
      EventStream<InteractionSSEStreamEvent> stream =
          client
              .interactions
              .create()
              .body(CreateInteractionRequestBody.of(modelBody().stream(true).build()))
              .timeout(Duration.ofSeconds(60))
              .call()
              .events();
      List<String> received = new ArrayList<>();
      ContinuationInterruptedException error =
          assertThrows(
              ContinuationInterruptedException.class,
              () -> {
                for (InteractionSSEStreamEvent event : stream) {
                  received.add(eventType(event));
                }
              });
      // The event that arrived as the deadline passed is still delivered; the stream stops
      // before waiting for the next one.
      assertEquals(
          Arrays.asList(
              "interaction.created",
              "step.delta",
              "interaction.completed",
              "interaction.created",
              "step.delta",
              "step.delta"),
          received);
      assertEquals(2, fake.requests.size());
      assertTrue(
          error.getCause() instanceof InteractionContinuation.ContinuationTimeoutException,
          String.valueOf(error.getCause()));
      // Hop 2 produced output after hop 1's token, so resuming from t1 re-generates "Part 2a ".
      assertEquals("t1", error.continuationToken().get());
      assertEquals("i1", error.interactionId().get());
      assertEquals(Arrays.asList("i1", "i2"), error.continuationIds());
    } finally {
      client.close();
    }
  }

  // --- Request surface -------------------------------------------------------------------------

  @Test
  public void requestObjectOptOutIsAPassthroughAndDoesNotMutateTheBody() {
    FakeServer fake =
        new FakeServer(Hop.unary(unaryJson("i1", "requires_continuation", "t1", "A", 5), null));
    Client client = makeClient(fake);
    try {
      CreateModelInteraction body = modelBody().build();
      CreateInteractionRequest request =
          new CreateInteractionRequest(null, CreateInteractionRequestBody.of(body), false, null);
      Interaction result = client.interactions.create(request).interaction().get();
      assertEquals(1, fake.requests.size());
      assertFalse(fake.requests.get(0).has("generation_config"), fake.requests.get(0).toString());
      assertEquals("t1", result.continuationToken().get());
      assertFalse(result.continuationIds().isPresent());
      assertFalse(body.generationConfig().isPresent());
    } finally {
      client.close();
    }
  }

  @Test
  public void defaultContinuationLeavesMaxOutputTokensUnset() {
    FakeServer fake =
        new FakeServer(
            Hop.unary(unaryJson("i1", "requires_continuation", "t1", "A ", 5), null),
            Hop.unary(unaryJson("i2", "completed", null, "B", 5), null));
    Client client = makeClient(fake);
    try {
      Interaction result =
          client
              .interactions
              .create(CreateInteractionRequestBody.of(modelBody().build()))
              .interaction()
              .get();
      assertEquals("completed", result.status().get().value());
      assertEquals("A B", result.outputText().get());
      assertEquals(Arrays.asList("i1", "i2"), result.continuationIds().get());
      assertEquals(2, fake.requests.size());
      assertFalse(fake.requests.get(0).has("generation_config"), fake.requests.get(0).toString());
      assertFalse(fake.requests.get(1).has("generation_config"), fake.requests.get(1).toString());
    } finally {
      client.close();
    }
  }

  @Test
  public void generationConfigWithoutMaxOutputTokensIsPreservedWithoutMutatingCallersObjects() {
    FakeServer fake = new FakeServer(Hop.unary(unaryJson("i1", "completed", null, "A", 5), null));
    Client client = makeClient(fake);
    try {
      GenerationConfig generationConfig = GenerationConfig.builder().seed(7).build();
      CreateModelInteraction body = modelBody().generationConfig(generationConfig).build();
      Interaction result =
          client.interactions.create(CreateInteractionRequestBody.of(body)).interaction().get();
      assertEquals("completed", result.status().get().value());
      JsonNode sent = fake.requests.get(0).get("generation_config");
      assertEquals(7, sent.get("seed").asInt());
      assertFalse(sent.has("max_output_tokens"), sent.toString());
      assertFalse(generationConfig.maxOutputTokens().isPresent());
      assertSame(generationConfig, body.generationConfig().get());
    } finally {
      client.close();
    }
  }

  @Test
  public void bodyOverloadWithAutomaticContinuationFlag() {
    FakeServer fake =
        new FakeServer(Hop.unary(unaryJson("i1", "requires_continuation", "t1", "A", 5), null));
    Client client = makeClient(fake);
    try {
      Interaction result =
          client
              .interactions
              .create(CreateInteractionRequestBody.of(modelBody().build()), false)
              .interaction()
              .get();
      assertEquals(1, fake.requests.size());
      assertEquals("requires_continuation", result.status().get().value());
    } finally {
      client.close();
    }
  }

  @Test
  public void streamTimeoutExpiryBeforeProgressThrowsRawTimeout() {
    useFakeClock();
    FakeServer fake =
        new FakeServer(
            Hop.sse(
                Arrays.asList(
                    created("i1"),
                    delta("Part 1 "),
                    completed("i1", "requires_continuation", "t1")),
                /* advanceClockBeforeEvent= */ 1,
                Duration.ofSeconds(20)));
    Client client = makeClient(fake);
    try {
      EventStream<InteractionSSEStreamEvent> stream =
          client
              .interactions
              .create()
              .body(CreateInteractionRequestBody.of(modelBody().stream(true).build()))
              .timeout(Duration.ofSeconds(15))
              .call()
              .events();
      List<String> received = new ArrayList<>();
      assertThrows(
          InteractionContinuation.ContinuationTimeoutException.class,
          () -> {
            for (InteractionSSEStreamEvent event : stream) {
              received.add(eventType(event));
            }
          });
      // No checkpoint token had been issued yet, so the raw timeout error is thrown directly.
      assertEquals(Arrays.asList("interaction.created", "step.delta"), received);
      assertEquals(1, fake.requests.size());
    } finally {
      client.close();
    }
  }

  @Test
  public void asyncStreamTimeoutExpiryAfterProgressThrowsInterrupted() throws Exception {
    useFakeClock();
    FakeServer fake =
        new FakeServer(
            Hop.sse(
                Arrays.asList(
                    created("i1"),
                    delta("Part 1 "),
                    completed("i1", "requires_continuation", "t1")),
                /* advanceClockBeforeEvent= */ 2,
                Duration.ofSeconds(20)));
    ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    Client client =
        Client.builder()
            .apiKey("test-key")
            .vertexAI(false)
            .asyncRetryScheduler(scheduler)
            .httpOptions(
                HttpOptions.builder()
                    .retryOptions(HttpRetryOptions.builder().attempts(1).build())
                    .build())
            .clientOptions(
                ClientOptions.builder()
                    .customHttpClient(new OkHttpClient.Builder().addInterceptor(fake).build())
                    .build())
            .build();
    try {
      EventStream<InteractionSSEStreamEvent> stream =
          client
              .async
              .interactions
              .create()
              .body(CreateInteractionRequestBody.of(modelBody().stream(true).build()))
              .timeout(Duration.ofSeconds(15))
              .call()
              .get();
      ContinuationInterruptedException error =
          assertThrows(
              ContinuationInterruptedException.class,
              () -> {
                for (InteractionSSEStreamEvent ignored : stream) {}
              });
      assertTrue(error.getCause() instanceof InteractionContinuation.ContinuationTimeoutException);
      assertEquals("t1", error.continuationToken().get());
      assertEquals("i1", error.interactionId().get());
    } finally {
      client.close();
      scheduler.shutdownNow();
    }
  }

  @Test
  public void optOutEmitsOneTimeWarningOnlyWithoutCallerToken() throws Exception {
    Logger logger = Logger.getLogger(InteractionContinuation.class.getName());
    final List<String> warnings = new ArrayList<>();
    Handler handler =
        new Handler() {
          @Override
          public void publish(LogRecord record) {
            warnings.add(record.getMessage());
          }

          @Override
          public void flush() {}

          @Override
          public void close() {}
        };
    logger.addHandler(handler);
    InteractionContinuation.resetIncompleteContinuationWarningForTesting();
    FakeServer fake =
        new FakeServer(
            Hop.unary(unaryJson("i1", "requires_continuation", "t1", "A", 5), null),
            Hop.unary(unaryJson("i2", "requires_continuation", "t2", "B", 5), null),
            Hop.unary(unaryJson("i3", "requires_continuation", "t3", "C", 5), null),
            Hop.sse(
                Arrays.asList(
                    created("i4"), delta("D"), completed("i4", "requires_continuation", "t4")),
                -1,
                null));
    Client client = makeClient(fake);
    try {
      // Caller already passing continuation_token -> no warning.
      CreateInteractionResponse withToken =
          client.interactions.create(
              CreateInteractionRequestBody.of(modelBody().continuationToken("caller-tok").build()),
              false);
      assertTrue(withToken.interaction().isPresent());
      assertEquals(0, warnings.size());

      // First opt-out unary call stopping at requires_continuation -> warns once.
      CreateInteractionResponse firstOptOut =
          client.interactions.create(CreateInteractionRequestBody.of(modelBody().build()), false);
      assertTrue(firstOptOut.interaction().isPresent());
      assertEquals(1, warnings.size());
      assertEquals(InteractionContinuation.INCOMPLETE_CONTINUATION_WARNING, warnings.get(0));

      // Second opt-out call -> suppressed (process-wide one-time warning).
      CreateInteractionResponse secondOptOut =
          client.interactions.create(CreateInteractionRequestBody.of(modelBody().build()), false);
      assertTrue(secondOptOut.interaction().isPresent());
      assertEquals(1, warnings.size());

      // Reset and verify streaming opt-out also warns once.
      InteractionContinuation.resetIncompleteContinuationWarningForTesting();
      try (EventStream<InteractionSSEStreamEvent> stream =
          client
              .interactions
              .create(CreateInteractionRequestBody.of(modelBody().stream(true).build()), false)
              .events()) {
        for (InteractionSSEStreamEvent ignored : stream) {}
      }
      assertEquals(2, warnings.size());
    } finally {
      logger.removeHandler(handler);
      client.close();
    }
  }
}
