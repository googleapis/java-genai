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

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.genai.Client;
import com.google.genai.gaos.models.interactions.CreateModelInteraction;
import com.google.genai.gaos.models.interactions.Interaction;
import com.google.genai.gaos.models.interactions.InteractionSSEStreamEvent;
import com.google.genai.gaos.models.interactions.InteractionsInput;
import com.google.genai.gaos.models.interactions.StepDelta;
import com.google.genai.gaos.models.interactions.TextDelta;
import com.google.genai.gaos.models.operations.CreateInteractionRequest;
import com.google.genai.gaos.models.operations.CreateInteractionRequestBody;
import com.google.genai.gaos.models.operations.CreateInteractionResponse;
import com.google.genai.gaos.models.operations.GetInteractionByIdRequest;
import com.google.genai.gaos.utils.BackoffStrategy;
import com.google.genai.gaos.utils.EventStream;
import com.google.genai.gaos.utils.HTTPClient;
import com.google.genai.gaos.utils.Headers;
import com.google.genai.gaos.utils.Options;
import com.google.genai.gaos.utils.RetryConfig;
import com.google.genai.gaos.utils.Utils;
import com.google.genai.gaos.utils.transport.HttpRequest;
import com.google.genai.gaos.utils.transport.HttpResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

public class InteractionContinuationTest {

  private static final ScheduledExecutorService SHARED_SCHEDULER =
      Executors.newSingleThreadScheduledExecutor(
          r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
          });

  private static void assertEquals(Object expected, Object actual) {
    org.junit.jupiter.api.Assertions.assertEquals(expected, actual);
  }

  private static void assertEquals(long expected, long actual) {
    org.junit.jupiter.api.Assertions.assertEquals(expected, actual);
  }

  private static void assertEquals(String message, Object expected, Object actual) {
    org.junit.jupiter.api.Assertions.assertEquals(expected, actual, message);
  }

  private static void assertEquals(String message, long expected, long actual) {
    org.junit.jupiter.api.Assertions.assertEquals(expected, actual, message);
  }

  private static void assertTrue(boolean condition) {
    org.junit.jupiter.api.Assertions.assertTrue(condition);
  }

  private static void assertTrue(String message, boolean condition) {
    org.junit.jupiter.api.Assertions.assertTrue(condition, message);
  }

  @AfterAll
  public static void tearDownClass() {
    SHARED_SCHEDULER.shutdownNow();
  }

  private static void setMockGaosClient(Client client, HTTPClient mockClient) throws Exception {
    Field sdkConfigField = client.interactions.getClass().getDeclaredField("sdkConfiguration");
    sdkConfigField.setAccessible(true);
    SDKConfiguration sdkConfig = (SDKConfiguration) sdkConfigField.get(client.interactions);

    HTTPClient existingClient = sdkConfig.client();
    Class<?> gaosHttpClientClass = Class.forName("com.google.genai.Client$GenAiGaosHttpClient");
    Method authorizeMethod = gaosHttpClientClass.getDeclaredMethod("authorize", HttpRequest.class);
    authorizeMethod.setAccessible(true);

    HTTPClient delegatingClient =
        new HTTPClient() {
          @Override
          public HttpResponse<InputStream> send(HttpRequest request) {
            try {
              HttpRequest authorized =
                  (HttpRequest) authorizeMethod.invoke(existingClient, request);
              return mockClient.send(authorized);
            } catch (Exception e) {
              if (e instanceof RuntimeException) {
                throw (RuntimeException) e;
              }
              throw new RuntimeException(e);
            }
          }

          @Override
          public CompletableFuture<HttpResponse<InputStream>> sendAsync(HttpRequest request) {
            try {
              HttpRequest authorized =
                  (HttpRequest) authorizeMethod.invoke(existingClient, request);
              return mockClient.sendAsync(authorized);
            } catch (Exception e) {
              CompletableFuture<HttpResponse<InputStream>> future = new CompletableFuture<>();
              future.completeExceptionally(e);
              return future;
            }
          }
        };

    sdkConfig.setClient(delegatingClient);
  }

  private static byte[] readAllBytes(InputStream is) throws IOException {
    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    byte[] data = new byte[4096];
    for (int nRead = is.read(data, 0, data.length);
        nRead != -1;
        nRead = is.read(data, 0, data.length)) {
      buffer.write(data, 0, nRead);
    }
    return buffer.toByteArray();
  }

  private static Client makeReplayClient(JsonNode recordingNode, List<JsonNode> sentRequests)
      throws Exception {
    Client client =
        Client.builder().apiKey("test-key").asyncRetryScheduler(SHARED_SCHEDULER).build();

    ObjectMapper mapper = Utils.mapper();
    JsonNode interactionsNode = recordingNode.get("interactions");
    List<JsonNode> hops = new ArrayList<>();
    for (JsonNode hop : interactionsNode) {
      hops.add(hop);
    }
    AtomicInteger hopIdx = new AtomicInteger(0);

    HTTPClient mockClient =
        new HTTPClient() {
          @Override
          public HttpResponse<InputStream> send(HttpRequest request) {
            int currentHop = hopIdx.getAndIncrement();
            if (currentHop >= hops.size()) {
              throw new AssertionError("Unexpected extra HTTP request #" + (currentHop + 1));
            }
            if (request.body().isPresent()) {
              try (InputStream is = request.body().get().stream()) {
                byte[] bytes = readAllBytes(is);
                if (bytes.length > 0) {
                  sentRequests.add(mapper.readTree(bytes));
                } else {
                  sentRequests.add(mapper.createObjectNode());
                }
              } catch (IOException e) {
                throw new UncheckedIOException(e);
              }
            } else {
              sentRequests.add(mapper.createObjectNode());
            }

            JsonNode respSpec = hops.get(currentHop).get("response");
            int statusCode =
                respSpec.has("status_code") ? respSpec.get("status_code").asInt() : 200;
            String contentType =
                respSpec.has("headers") && respSpec.get("headers").has("content-type")
                    ? respSpec.get("headers").get("content-type").asText()
                    : "application/json";

            byte[] rawBytes;
            if (contentType.contains("text/event-stream")) {
              StringBuilder sseBuilder = new StringBuilder();
              for (JsonNode seg : respSpec.get("body_segments")) {
                String evType = seg.has("event_type") ? seg.get("event_type").asText() : "message";
                sseBuilder.append("event: ").append(evType).append("\n");
                sseBuilder.append("data: ").append(seg.toString()).append("\n\n");
              }
              rawBytes = sseBuilder.toString().getBytes(StandardCharsets.UTF_8);
            } else {
              JsonNode body0 = respSpec.get("body_segments").get(0);
              rawBytes = body0.toString().getBytes(StandardCharsets.UTF_8);
            }

            Headers headers = new Headers();
            headers.add("content-type", contentType);
            return new HttpResponse<>(
                request, statusCode, headers, new ByteArrayInputStream(rawBytes));
          }

          @Override
          public CompletableFuture<HttpResponse<InputStream>> sendAsync(HttpRequest request) {
            return CompletableFuture.completedFuture(send(request));
          }
        };

    setMockGaosClient(client, mockClient);
    return client;
  }

  // --- Helpers for programmatic mock recordings ---

  private static ObjectNode createUnaryHop(
      ObjectMapper mapper, String id, String status, String token, String text, Long outTokens) {
    ObjectNode interaction = mapper.createObjectNode();
    interaction.put("id", id);
    interaction.put("status", status);
    if (token != null) {
      interaction.put("continuation_token", token);
    }
    if (text != null) {
      interaction.put("output_text", text);
      ArrayNode steps = mapper.createArrayNode();
      ObjectNode modelOutput = mapper.createObjectNode();
      modelOutput.put("type", "model_output");
      ArrayNode content = mapper.createArrayNode();
      ObjectNode textPart = mapper.createObjectNode();
      textPart.put("type", "text");
      textPart.put("text", text);
      content.add(textPart);
      modelOutput.set("content", content);
      steps.add(modelOutput);
      interaction.set("steps", steps);
    }
    if (outTokens != null) {
      ObjectNode usage = mapper.createObjectNode();
      usage.put("total_output_tokens", outTokens);
      usage.put("total_tokens", outTokens);
      interaction.set("usage", usage);
    }

    ObjectNode hop = mapper.createObjectNode();
    ObjectNode resp = mapper.createObjectNode();
    resp.put("status_code", 200);
    ObjectNode headers = mapper.createObjectNode();
    headers.put("content-type", "application/json");
    resp.set("headers", headers);
    ArrayNode bodySegments = mapper.createArrayNode();
    bodySegments.add(interaction);
    resp.set("body_segments", bodySegments);
    hop.set("response", resp);
    return hop;
  }

  private static ObjectNode createStreamingHop(
      ObjectMapper mapper, String id, String status, String token, String text, Long outTokens) {
    ArrayNode bodySegments = mapper.createArrayNode();

    ObjectNode createdEvent = mapper.createObjectNode();
    createdEvent.put("event_type", "interaction.created");
    ObjectNode createdInter = mapper.createObjectNode();
    createdInter.put("id", id);
    createdInter.put("status", "in_progress");
    createdEvent.set("interaction", createdInter);
    bodySegments.add(createdEvent);

    if (text != null) {
      ObjectNode deltaEvent = mapper.createObjectNode();
      deltaEvent.put("event_type", "step.delta");
      deltaEvent.put("index", 0);
      ObjectNode delta = mapper.createObjectNode();
      delta.put("type", "text");
      delta.put("text", text);
      deltaEvent.set("delta", delta);
      bodySegments.add(deltaEvent);
    }

    ObjectNode completedEvent = mapper.createObjectNode();
    completedEvent.put("event_type", "interaction.completed");
    ObjectNode completedInter = mapper.createObjectNode();
    completedInter.put("id", id);
    completedInter.put("status", status);
    if (token != null) {
      completedInter.put("continuation_token", token);
    }
    if (outTokens != null) {
      ObjectNode usage = mapper.createObjectNode();
      usage.put("total_output_tokens", outTokens);
      usage.put("total_tokens", outTokens);
      completedInter.set("usage", usage);
    }
    completedEvent.set("interaction", completedInter);
    bodySegments.add(completedEvent);

    ObjectNode hop = mapper.createObjectNode();
    ObjectNode resp = mapper.createObjectNode();
    resp.put("status_code", 200);
    ObjectNode headers = mapper.createObjectNode();
    headers.put("content-type", "text/event-stream");
    resp.set("headers", headers);
    resp.set("body_segments", bodySegments);
    hop.set("response", resp);
    return hop;
  }

  private static ObjectNode createMockRecording(JsonNode... hops) {
    ObjectMapper mapper = Utils.mapper();
    ObjectNode recording = mapper.createObjectNode();
    ArrayNode interactions = mapper.createArrayNode();
    for (JsonNode hop : hops) {
      interactions.add(hop);
    }
    recording.set("interactions", interactions);
    return recording;
  }

  private static String collectStreamingText(
      EventStream<InteractionSSEStreamEvent> stream, List<InteractionSSEStreamEvent> outEvents)
      throws IOException {
    StringBuilder sb = new StringBuilder();
    for (Optional<InteractionSSEStreamEvent> ev = stream.next();
        ev.isPresent();
        ev = stream.next()) {
      InteractionSSEStreamEvent event = ev.get();
      outEvents.add(event);
      if (event.data().isPresent() && event.data().get() instanceof StepDelta) {
        StepDelta sd = (StepDelta) event.data().get();
        if (sd.delta().isPresent() && sd.delta().get() instanceof TextDelta) {
          TextDelta td = (TextDelta) sd.delta().get();
          sb.append(td.text().orElse(""));
        }
      }
    }
    return sb.toString();
  }

  // --- 2.0 Core multi-hop continuation, builder, and default behavior tests ---

  @Test
  public void testCreateRequestBuilderAndRequestOverloads_syncAndAsync() throws Exception {
    ObjectMapper mapper = Utils.mapper();
    ObjectNode hop1 =
        createUnaryHop(mapper, "int_builder", "continuation_required", "tok_b1", "Part 1 ", 20L);
    ObjectNode hop2 =
        createUnaryHop(mapper, "int_builder", "completed", null, "Part 1 Part 2", 40L);

    CreateInteractionRequestBody reqBody =
        CreateInteractionRequestBody.of(
            CreateModelInteraction.builder()
                .model("gemini-2.5-pro")
                .input(InteractionsInput.of("Builder test"))
                .build());

    // 1. Sync request builder:
    // client.interactions.create().body(...).automaticContinuation(true).call()
    {
      List<JsonNode> sentRequests = new ArrayList<>();
      Client client = makeReplayClient(createMockRecording(hop1, hop2), sentRequests);

      CreateInteractionResponse resp =
          client.interactions.create().body(reqBody).automaticContinuation(true).call();
      assertEquals(2, sentRequests.size());
      assertEquals("tok_b1", sentRequests.get(1).get("continuation_token").asText());
      assertEquals("Part 1 Part 2", resp.interaction().get().outputText().orElse(null));
    }

    // 2. Async request builder:
    // client.async.interactions.create().body(...).automaticContinuation(true).call()
    ObjectNode streamHop1 =
        createStreamingHop(
            mapper, "int_builder_s", "continuation_required", "tok_bs1", "Part 1 ", 20L);
    ObjectNode streamHop2 =
        createStreamingHop(mapper, "int_builder_s", "completed", null, "Part 2", 40L);
    CreateInteractionRequestBody streamReqBody =
        CreateInteractionRequestBody.of(
            CreateModelInteraction.builder()
                .model("gemini-2.5-pro")
                .input(InteractionsInput.of("Async builder test"))
                .stream(true)
                .build());
    {
      List<JsonNode> sentRequests = new ArrayList<>();
      Client client = makeReplayClient(createMockRecording(streamHop1, streamHop2), sentRequests);

      EventStream<InteractionSSEStreamEvent> stream =
          client
              .async
              .interactions
              .create()
              .body(streamReqBody)
              .automaticContinuation(true)
              .call()
              .get();
      List<InteractionSSEStreamEvent> events = new ArrayList<>();
      String fullText = collectStreamingText(stream, events);
      assertEquals(2, sentRequests.size());
      assertEquals("tok_bs1", sentRequests.get(1).get("continuation_token").asText());
      assertEquals("Part 1 Part 2", fullText);
    }

    // 3. CreateInteractionRequest overload (sync and async)
    {
      CreateInteractionRequest syncRequest =
          CreateInteractionRequest.builder().body(reqBody).automaticContinuation(true).build();

      List<JsonNode> sentSync = new ArrayList<>();
      Client syncClient = makeReplayClient(createMockRecording(hop1, hop2), sentSync);
      CreateInteractionResponse syncResp = syncClient.interactions.create(syncRequest);
      assertEquals(2, sentSync.size());
      assertEquals("Part 1 Part 2", syncResp.interaction().get().outputText().orElse(null));

      CreateInteractionRequest asyncRequest =
          CreateInteractionRequest.builder()
              .body(streamReqBody)
              .automaticContinuation(true)
              .build();

      List<JsonNode> sentAsync = new ArrayList<>();
      Client asyncClient = makeReplayClient(createMockRecording(streamHop1, streamHop2), sentAsync);
      EventStream<InteractionSSEStreamEvent> asyncStream =
          asyncClient.async.interactions.create(asyncRequest).get();
      List<InteractionSSEStreamEvent> asyncEvents = new ArrayList<>();
      String asyncText = collectStreamingText(asyncStream, asyncEvents);
      assertEquals(2, sentAsync.size());
      assertEquals("Part 1 Part 2", asyncText);
    }
  }

  // --- 2.1 Background and store validation tests ---

  @Test
  public void testAutomaticContinuationWithBackgroundThrowsException_unarySync() {
    Client client =
        Client.builder().apiKey("test-key").asyncRetryScheduler(SHARED_SCHEDULER).build();
    CreateInteractionRequestBody body =
        CreateInteractionRequestBody.of(
            CreateModelInteraction.builder()
                .model("gemini-2.5-pro")
                .input(InteractionsInput.of("Hello"))
                .background(true)
                .build());

    IllegalArgumentException e =
        assertThrows(IllegalArgumentException.class, () -> client.interactions.create(body, false));
    assertTrue(
        e.getMessage().contains("automaticContinuation=false cannot be used with background=true"));
  }

  @Test
  public void testAutomaticContinuationWithBackgroundThrowsException_streamingSync() {
    Client client =
        Client.builder().apiKey("test-key").asyncRetryScheduler(SHARED_SCHEDULER).build();
    CreateInteractionRequestBody body =
        CreateInteractionRequestBody.of(
            CreateModelInteraction.builder()
                .model("gemini-2.5-pro")
                .input(InteractionsInput.of("Hello"))
                .background(true)
                .stream(true)
                .build());

    IllegalArgumentException e =
        assertThrows(IllegalArgumentException.class, () -> client.interactions.create(body, false));
    assertTrue(
        e.getMessage().contains("automaticContinuation=false cannot be used with background=true"));
  }

  @Test
  public void testAutomaticContinuationWithBackgroundThrowsException_async() {
    Client client =
        Client.builder().apiKey("test-key").asyncRetryScheduler(SHARED_SCHEDULER).build();
    CreateInteractionRequestBody body =
        CreateInteractionRequestBody.of(
            CreateModelInteraction.builder()
                .model("gemini-2.5-pro")
                .input(InteractionsInput.of("Hello"))
                .background(true)
                .build());

    IllegalArgumentException e =
        assertThrows(
            IllegalArgumentException.class, () -> client.async.interactions.create(body, false));
    assertTrue(
        e.getMessage().contains("automaticContinuation=false cannot be used with background=true"));
  }

  @Test
  public void testAutomaticContinuationWithBackgroundThrowsException_viaRequest() {
    Client client =
        Client.builder().apiKey("test-key").asyncRetryScheduler(SHARED_SCHEDULER).build();
    CreateInteractionRequestBody body =
        CreateInteractionRequestBody.of(
            CreateModelInteraction.builder()
                .model("gemini-2.5-pro")
                .input(InteractionsInput.of("Hello"))
                .background(true)
                .build());
    CreateInteractionRequest request = new CreateInteractionRequest(null, body, false);

    IllegalArgumentException e =
        assertThrows(IllegalArgumentException.class, () -> client.interactions.create(request));
    assertTrue(
        e.getMessage().contains("automaticContinuation=false cannot be used with background=true"));
  }

  @Test
  public void testAutomaticContinuationWithStoreFalseUnaryThrowsException() {
    Client client =
        Client.builder().apiKey("test-key").asyncRetryScheduler(SHARED_SCHEDULER).build();

    CreateInteractionRequestBody unaryBody =
        CreateInteractionRequestBody.of(
            CreateModelInteraction.builder()
                .model("gemini-2.5-pro")
                .input(InteractionsInput.of("Hello"))
                .store(false)
                .build());

    IllegalArgumentException e1 =
        assertThrows(
            IllegalArgumentException.class, () -> client.interactions.create(unaryBody, true));
    assertTrue(
        e1.getMessage()
            .contains("automaticContinuation=true cannot be used with store=false in unary mode"));

    CreateInteractionRequest request = new CreateInteractionRequest(null, unaryBody, true);
    IllegalArgumentException e2 =
        assertThrows(IllegalArgumentException.class, () -> client.interactions.create(request));
    assertTrue(
        e2.getMessage()
            .contains("automaticContinuation=true cannot be used with store=false in unary mode"));
  }

  // --- 2.2 Stop condition tests ---

  @Test
  public void testUnaryAutomaticContinuationStopsWhenTokenMissingOrEmpty() throws Exception {
    ObjectMapper mapper = Utils.mapper();

    // Case A: continuation_required status with null continuation_token
    {
      ObjectNode hop1 =
          createUnaryHop(
              mapper,
              "int_1",
              "continuation_required",
              null,
              "Continuation required without token",
              20L);
      List<JsonNode> sentRequests = new ArrayList<>();
      Client client = makeReplayClient(createMockRecording(hop1), sentRequests);
      CreateInteractionRequestBody reqBody =
          CreateInteractionRequestBody.of(
              CreateModelInteraction.builder()
                  .model("gemini-2.5-pro")
                  .input(InteractionsInput.of("Test 1"))
                  .build());

      CreateInteractionResponse response = client.interactions.create(reqBody, true);
      Interaction res =
          response.interaction().orElseThrow(() -> new AssertionError("Expected interaction"));

      assertEquals(1, sentRequests.size());
      assertEquals("continuation_required", res.status().map(st -> st.value()).orElse(null));
      assertEquals("Continuation required without token", res.outputText().orElse(null));
    }

    // Case B: continuation_required status with empty continuation_token
    {
      ObjectNode hop1 =
          createUnaryHop(
              mapper,
              "int_2",
              "continuation_required",
              "",
              "Continuation required with empty token",
              20L);
      List<JsonNode> sentRequests = new ArrayList<>();
      Client client = makeReplayClient(createMockRecording(hop1), sentRequests);
      CreateInteractionRequestBody reqBody =
          CreateInteractionRequestBody.of(
              CreateModelInteraction.builder()
                  .model("gemini-2.5-pro")
                  .input(InteractionsInput.of("Test 2"))
                  .build());

      CreateInteractionResponse response = client.interactions.create(reqBody, true);
      Interaction res =
          response.interaction().orElseThrow(() -> new AssertionError("Expected interaction"));

      assertEquals(1, sentRequests.size());
      assertEquals("continuation_required", res.status().map(st -> st.value()).orElse(null));
      assertEquals("Continuation required with empty token", res.outputText().orElse(null));
    }
  }

  @Test
  public void testStreamAutomaticContinuationStopsOnRequiresActionOrMissingToken()
      throws Exception {
    ObjectMapper mapper = Utils.mapper();

    // Case A: requires_action status with continuation_token present stops after 1 hop
    {
      ObjectNode hop1 =
          createStreamingHop(
              mapper,
              "int_1",
              "requires_action",
              "tok_should_not_continue",
              "Calling tool...",
              20L);
      List<JsonNode> sentRequests = new ArrayList<>();
      Client client = makeReplayClient(createMockRecording(hop1), sentRequests);
      CreateInteractionRequestBody reqBody =
          CreateInteractionRequestBody.of(
              CreateModelInteraction.builder()
                  .model("gemini-2.5-pro")
                  .input(InteractionsInput.of("Tool call"))
                  .stream(true)
                  .build());

      CreateInteractionResponse response = client.interactions.create(reqBody, true);
      EventStream<InteractionSSEStreamEvent> stream = response.events();
      List<InteractionSSEStreamEvent> events = new ArrayList<>();
      for (Optional<InteractionSSEStreamEvent> ev = stream.next();
          ev.isPresent();
          ev = stream.next()) {
        events.add(ev.get());
      }

      assertEquals(1, sentRequests.size());
      assertEquals(3, events.size());
    }

    // Case B: continuation_required status with null continuation_token stops after 1 hop
    {
      ObjectNode hop1 =
          createStreamingHop(mapper, "int_2", "continuation_required", null, "Partial...", 20L);
      List<JsonNode> sentRequests = new ArrayList<>();
      Client client = makeReplayClient(createMockRecording(hop1), sentRequests);
      CreateInteractionRequestBody reqBody =
          CreateInteractionRequestBody.of(
              CreateModelInteraction.builder()
                  .model("gemini-2.5-pro")
                  .input(InteractionsInput.of("Missing token"))
                  .stream(true)
                  .build());

      CreateInteractionResponse response = client.interactions.create(reqBody, true);
      EventStream<InteractionSSEStreamEvent> stream = response.events();
      List<InteractionSSEStreamEvent> events = new ArrayList<>();
      for (Optional<InteractionSSEStreamEvent> ev = stream.next();
          ev.isPresent();
          ev = stream.next()) {
        events.add(ev.get());
      }

      assertEquals(1, sentRequests.size());
      assertEquals(3, events.size());
    }

    // Case C: continuation_required status with empty continuation_token stops after 1 hop
    {
      ObjectNode hop1 =
          createStreamingHop(mapper, "int_3", "continuation_required", "", "Partial empty...", 20L);
      List<JsonNode> sentRequests = new ArrayList<>();
      Client client = makeReplayClient(createMockRecording(hop1), sentRequests);
      CreateInteractionRequestBody reqBody =
          CreateInteractionRequestBody.of(
              CreateModelInteraction.builder()
                  .model("gemini-2.5-pro")
                  .input(InteractionsInput.of("Empty token"))
                  .stream(true)
                  .build());

      CreateInteractionResponse response = client.interactions.create(reqBody, true);
      EventStream<InteractionSSEStreamEvent> stream = response.events();
      List<InteractionSSEStreamEvent> events = new ArrayList<>();
      for (Optional<InteractionSSEStreamEvent> ev = stream.next();
          ev.isPresent();
          ev = stream.next()) {
        events.add(ev.get());
      }

      assertEquals(1, sentRequests.size());
      assertEquals(3, events.size());
    }
  }

  // --- 2.3 Initial caller continuation token tests ---

  @Test
  public void testUnaryAutomaticContinuationWithInitialCallerContinuationToken() throws Exception {
    ObjectMapper mapper = Utils.mapper();
    ObjectNode hop1 =
        createUnaryHop(
            mapper, "int_1", "continuation_required", "tok_from_hop1", "Resumed hop 1 ", 50L);
    ObjectNode hop2 =
        createUnaryHop(mapper, "int_1", "completed", null, "Resumed hop 1 Resumed hop 2", 50L);

    List<JsonNode> sentRequests = new ArrayList<>();
    Client client = makeReplayClient(createMockRecording(hop1, hop2), sentRequests);
    CreateInteractionRequestBody reqBody =
        CreateInteractionRequestBody.of(
            CreateModelInteraction.builder()
                .model("gemini-2.5-pro")
                .input(InteractionsInput.of("Resume"))
                .continuationToken("user_initial_tok")
                .build());

    CreateInteractionResponse response = client.interactions.create(reqBody, true);
    Interaction res =
        response.interaction().orElseThrow(() -> new AssertionError("Expected interaction"));

    assertEquals(2, sentRequests.size());
    assertEquals("user_initial_tok", sentRequests.get(0).get("continuation_token").asText());
    assertEquals("tok_from_hop1", sentRequests.get(1).get("continuation_token").asText());
    assertEquals("completed", res.status().map(st -> st.value()).orElse(null));
    assertEquals("Resumed hop 1 Resumed hop 2", res.outputText().orElse(null));
  }

  @Test
  public void testStreamAutomaticContinuationWithInitialCallerContinuationToken() throws Exception {
    ObjectMapper mapper = Utils.mapper();
    ObjectNode hop1 =
        createStreamingHop(
            mapper, "int_1", "continuation_required", "tok_from_hop1", "Resumed stream 1 ", 50L);
    ObjectNode hop2 =
        createStreamingHop(mapper, "int_1", "completed", null, "Resumed stream 2", 50L);

    List<JsonNode> sentRequests = new ArrayList<>();
    Client client = makeReplayClient(createMockRecording(hop1, hop2), sentRequests);
    CreateInteractionRequestBody reqBody =
        CreateInteractionRequestBody.of(
            CreateModelInteraction.builder()
                .model("gemini-2.5-pro")
                .input(InteractionsInput.of("Resume stream"))
                .continuationToken("user_initial_tok")
                .stream(true)
                .build());

    CreateInteractionResponse response = client.interactions.create(reqBody, true);
    EventStream<InteractionSSEStreamEvent> stream = response.events();
    List<InteractionSSEStreamEvent> events = new ArrayList<>();
    for (Optional<InteractionSSEStreamEvent> ev = stream.next();
        ev.isPresent();
        ev = stream.next()) {
      events.add(ev.get());
    }

    assertEquals(2, sentRequests.size());
    assertEquals("user_initial_tok", sentRequests.get(0).get("continuation_token").asText());
    assertEquals("tok_from_hop1", sentRequests.get(1).get("continuation_token").asText());
    assertTrue(events.size() > 0);
  }

  // --- 2.4 Early stream close & dead connection resume tests ---

  @Test
  public void testStreamAutomaticContinuationCloseOnSecondHop() throws Exception {
    ObjectMapper mapper = Utils.mapper();
    ObjectNode hop1 =
        createStreamingHop(mapper, "int_1", "continuation_required", "tok_hop1", null, 10L);
    ObjectNode hop2 = createStreamingHop(mapper, "int_1", "completed", null, "Hop 2 text", 10L);

    AtomicBoolean hop1Closed = new AtomicBoolean(false);
    AtomicBoolean hop2Closed = new AtomicBoolean(false);

    JsonNode recordingNode = createMockRecording(hop1, hop2);
    JsonNode interactionsNode = recordingNode.get("interactions");
    List<JsonNode> hops = new ArrayList<>();
    for (JsonNode hop : interactionsNode) {
      hops.add(hop);
    }
    AtomicInteger hopIdx = new AtomicInteger(0);

    Client client =
        Client.builder().apiKey("test-key").asyncRetryScheduler(SHARED_SCHEDULER).build();

    HTTPClient mockClient =
        new HTTPClient() {
          @Override
          public HttpResponse<InputStream> send(HttpRequest request) {
            int currentHop = hopIdx.getAndIncrement();
            JsonNode respSpec = hops.get(currentHop).get("response");
            int statusCode =
                respSpec.has("status_code") ? respSpec.get("status_code").asInt() : 200;
            String contentType = respSpec.get("headers").get("content-type").asText();

            StringBuilder sseBuilder = new StringBuilder();
            for (JsonNode seg : respSpec.get("body_segments")) {
              String evType = seg.has("event_type") ? seg.get("event_type").asText() : "message";
              sseBuilder.append("event: ").append(evType).append("\n");
              sseBuilder.append("data: ").append(seg.toString()).append("\n\n");
            }
            byte[] rawBytes = sseBuilder.toString().getBytes(StandardCharsets.UTF_8);

            InputStream rawIn = new ByteArrayInputStream(rawBytes);
            InputStream trackingIn =
                new FilterInputStream(rawIn) {
                  @Override
                  public void close() throws IOException {
                    if (currentHop == 0) {
                      hop1Closed.set(true);
                    } else if (currentHop == 1) {
                      hop2Closed.set(true);
                    }
                    super.close();
                  }
                };

            Headers headers = new Headers();
            headers.add("content-type", contentType);
            return new HttpResponse<>(request, statusCode, headers, trackingIn);
          }

          @Override
          public CompletableFuture<HttpResponse<InputStream>> sendAsync(HttpRequest request) {
            return CompletableFuture.completedFuture(send(request));
          }
        };

    setMockGaosClient(client, mockClient);

    CreateInteractionRequestBody reqBody =
        CreateInteractionRequestBody.of(
            CreateModelInteraction.builder()
                .model("gemini-2.5-pro")
                .input(InteractionsInput.of("Hello"))
                .stream(true)
                .build());

    CreateInteractionResponse response = client.interactions.create(reqBody, true);
    EventStream<InteractionSSEStreamEvent> stream = response.events();

    // Consume Hop 1 (2 events: created, completed)
    assertTrue(stream.next().isPresent());
    assertTrue(stream.next().isPresent());

    // Consume first event of Hop 2 (created event)
    assertTrue(stream.next().isPresent());
    assertTrue(hop1Closed.get());
    assertTrue(!hop2Closed.get());

    // Early close during Hop 2
    stream.close();

    assertTrue(hop2Closed.get());
    assertTrue(stream.isClosed());
    assertTrue(!stream.next().isPresent());
  }

  @Test
  public void testStreamAutomaticContinuationResumesOnDeadConnectionWithLastToken()
      throws Exception {
    ObjectMapper mapper = Utils.mapper();

    // Build Hop 1 SSE events with two status_update checkpoints and a text delta before disconnect
    ArrayNode hop1Events = mapper.createArrayNode();
    ObjectNode createdEv = mapper.createObjectNode();
    createdEv.put("event_type", "interaction.created");
    ObjectNode createdInter = mapper.createObjectNode();
    createdInter.put("id", "int_dead_conn");
    createdInter.put("status", "in_progress");
    createdEv.set("interaction", createdInter);
    hop1Events.add(createdEv);

    ObjectNode statusEv1 = mapper.createObjectNode();
    statusEv1.put("event_type", "interaction.status_update");
    statusEv1.put("interaction_id", "int_dead_conn");
    statusEv1.put("status", "in_progress");
    statusEv1.put("continuation_token", "tok_checkpoint_1");
    hop1Events.add(statusEv1);

    ObjectNode deltaEv1 = mapper.createObjectNode();
    deltaEv1.put("event_type", "step.delta");
    deltaEv1.put("index", 0);
    ObjectNode textDelta1 = mapper.createObjectNode();
    textDelta1.put("type", "text");
    textDelta1.put("text", "First part ");
    deltaEv1.set("delta", textDelta1);
    hop1Events.add(deltaEv1);

    ObjectNode statusEv2 = mapper.createObjectNode();
    statusEv2.put("event_type", "interaction.status_update");
    statusEv2.put("interaction_id", "int_dead_conn");
    statusEv2.put("status", "in_progress");
    statusEv2.put("continuation_token", "tok_checkpoint_2");
    hop1Events.add(statusEv2);

    ObjectNode hop2 =
        createStreamingHop(mapper, "int_dead_conn", "completed", null, "resumed part", 20L);

    CreateInteractionRequestBody reqBody =
        CreateInteractionRequestBody.of(
            CreateModelInteraction.builder()
                .model("gemini-2.5-pro")
                .input(InteractionsInput.of("Resume after dead connection"))
                .stream(true)
                .build());

    // 1. Mid-stream IOException after receiving tok_checkpoint_2 -> resumes with tok_checkpoint_2
    // (sync & async)
    for (boolean isAsync : new boolean[] {false, true}) {
      List<JsonNode> sentRequests = new ArrayList<>();
      AtomicInteger hopIdx = new AtomicInteger(0);
      Client client =
          Client.builder().apiKey("test-key").asyncRetryScheduler(SHARED_SCHEDULER).build();

      HTTPClient mockClient =
          new HTTPClient() {
            @Override
            public HttpResponse<InputStream> send(HttpRequest request) {
              if (request.body().isPresent()) {
                try (InputStream is = request.body().get().stream()) {
                  byte[] bytes = readAllBytes(is);
                  if (bytes.length > 0) {
                    sentRequests.add(mapper.readTree(bytes));
                  } else {
                    sentRequests.add(mapper.createObjectNode());
                  }
                } catch (IOException e) {
                  throw new UncheckedIOException(e);
                }
              } else {
                sentRequests.add(mapper.createObjectNode());
              }

              int currentHop = hopIdx.getAndIncrement();
              Headers headers = new Headers();
              headers.add("content-type", "text/event-stream");

              if (currentHop == 0) {
                StringBuilder sseBuilder = new StringBuilder();
                for (JsonNode seg : hop1Events) {
                  sseBuilder
                      .append("event: ")
                      .append(seg.get("event_type").asText())
                      .append("\n")
                      .append("data: ")
                      .append(seg.toString())
                      .append("\n\n");
                }
                byte[] validBytes = sseBuilder.toString().getBytes(StandardCharsets.UTF_8);
                InputStream failingStream =
                    new InputStream() {
                      private int pos = 0;

                      @Override
                      public int read() throws IOException {
                        if (pos < validBytes.length) {
                          return validBytes[pos++] & 0xFF;
                        }
                        throw new IOException("Connection reset by peer");
                      }

                      @Override
                      public int read(byte[] b, int off, int len) throws IOException {
                        if (pos < validBytes.length) {
                          int toCopy = Math.min(len, validBytes.length - pos);
                          System.arraycopy(validBytes, pos, b, off, toCopy);
                          pos += toCopy;
                          return toCopy;
                        }
                        throw new IOException("Connection reset by peer");
                      }
                    };
                return new HttpResponse<>(request, 200, headers, failingStream);
              } else {
                JsonNode respSpec = hop2.get("response");
                StringBuilder sseBuilder = new StringBuilder();
                for (JsonNode seg : respSpec.get("body_segments")) {
                  sseBuilder
                      .append("event: ")
                      .append(seg.get("event_type").asText())
                      .append("\n")
                      .append("data: ")
                      .append(seg.toString())
                      .append("\n\n");
                }
                byte[] rawBytes = sseBuilder.toString().getBytes(StandardCharsets.UTF_8);
                return new HttpResponse<>(
                    request, 200, headers, new ByteArrayInputStream(rawBytes));
              }
            }

            @Override
            public CompletableFuture<HttpResponse<InputStream>> sendAsync(HttpRequest request) {
              return CompletableFuture.completedFuture(send(request));
            }
          };

      setMockGaosClient(client, mockClient);

      EventStream<InteractionSSEStreamEvent> stream =
          isAsync
              ? client.async.interactions.create(reqBody, true).get()
              : client.interactions.create(reqBody, true).events();

      List<InteractionSSEStreamEvent> events = new ArrayList<>();
      String fullText = collectStreamingText(stream, events);

      assertEquals(2, sentRequests.size());
      assertTrue(!sentRequests.get(0).has("continuation_token"));
      assertEquals("tok_checkpoint_2", sentRequests.get(1).get("continuation_token").asText());
      assertEquals("First part resumed part", fullText);
      assertTrue(stream.isClosed());
    }

    // 2. Premature EOF (stream ends after status_update without interaction.completed) -> resumes
    // with tok_checkpoint_2
    {
      ObjectNode hop1PrematureEof = mapper.createObjectNode();
      ObjectNode hop1Resp = mapper.createObjectNode();
      hop1Resp.put("status_code", 200);
      ObjectNode hop1Headers = mapper.createObjectNode();
      hop1Headers.put("content-type", "text/event-stream");
      hop1Resp.set("headers", hop1Headers);
      hop1Resp.set("body_segments", hop1Events);
      hop1PrematureEof.set("response", hop1Resp);

      List<JsonNode> sentRequests = new ArrayList<>();
      Client client = makeReplayClient(createMockRecording(hop1PrematureEof, hop2), sentRequests);

      EventStream<InteractionSSEStreamEvent> stream =
          client.interactions.create(reqBody, true).events();
      List<InteractionSSEStreamEvent> events = new ArrayList<>();
      String fullText = collectStreamingText(stream, events);

      assertEquals(2, sentRequests.size());
      assertEquals("tok_checkpoint_2", sentRequests.get(1).get("continuation_token").asText());
      assertEquals("First part resumed part", fullText);
    }

    // 3. Error before any continuation_token is received -> propagates IOException immediately (1
    // request)
    {
      List<JsonNode> sentRequests = new ArrayList<>();
      Client client =
          Client.builder().apiKey("test-key").asyncRetryScheduler(SHARED_SCHEDULER).build();

      HTTPClient failingMockClient =
          new HTTPClient() {
            @Override
            public HttpResponse<InputStream> send(HttpRequest request) {
              sentRequests.add(mapper.createObjectNode());
              Headers headers = new Headers();
              headers.add("content-type", "text/event-stream");
              InputStream immediateFailStream =
                  new InputStream() {
                    @Override
                    public int read() throws IOException {
                      throw new IOException("Dropped before any token");
                    }
                  };
              return new HttpResponse<>(request, 200, headers, immediateFailStream);
            }

            @Override
            public CompletableFuture<HttpResponse<InputStream>> sendAsync(HttpRequest request) {
              return CompletableFuture.completedFuture(send(request));
            }
          };

      setMockGaosClient(client, failingMockClient);

      EventStream<InteractionSSEStreamEvent> stream =
          client.interactions.create(reqBody, true).events();
      IOException e = assertThrows(IOException.class, () -> stream.next());
      assertTrue(e.getMessage().contains("Dropped before any token"));
      assertEquals(1, sentRequests.size());
    }

    // 4. Error when automaticContinuation=false even if token was received -> propagates
    // IOException (1 request)
    {
      List<JsonNode> sentRequests = new ArrayList<>();
      Client client =
          Client.builder().apiKey("test-key").asyncRetryScheduler(SHARED_SCHEDULER).build();

      HTTPClient singleHopFailingClient =
          new HTTPClient() {
            @Override
            public HttpResponse<InputStream> send(HttpRequest request) {
              sentRequests.add(mapper.createObjectNode());
              Headers headers = new Headers();
              headers.add("content-type", "text/event-stream");
              StringBuilder sseBuilder = new StringBuilder();
              for (JsonNode seg : hop1Events) {
                sseBuilder
                    .append("event: ")
                    .append(seg.get("event_type").asText())
                    .append("\n")
                    .append("data: ")
                    .append(seg.toString())
                    .append("\n\n");
              }
              byte[] validBytes = sseBuilder.toString().getBytes(StandardCharsets.UTF_8);
              InputStream failingStream =
                  new InputStream() {
                    private int pos = 0;

                    @Override
                    public int read() throws IOException {
                      if (pos < validBytes.length) {
                        return validBytes[pos++] & 0xFF;
                      }
                      throw new IOException("Connection reset with autoContinuation=false");
                    }

                    @Override
                    public int read(byte[] b, int off, int len) throws IOException {
                      if (pos < validBytes.length) {
                        int toCopy = Math.min(len, validBytes.length - pos);
                        System.arraycopy(validBytes, pos, b, off, toCopy);
                        pos += toCopy;
                        return toCopy;
                      }
                      throw new IOException("Connection reset with autoContinuation=false");
                    }
                  };
              return new HttpResponse<>(request, 200, headers, failingStream);
            }

            @Override
            public CompletableFuture<HttpResponse<InputStream>> sendAsync(HttpRequest request) {
              return CompletableFuture.completedFuture(send(request));
            }
          };

      setMockGaosClient(client, singleHopFailingClient);

      EventStream<InteractionSSEStreamEvent> stream =
          client.interactions.create(reqBody, false).events();
      List<InteractionSSEStreamEvent> events = new ArrayList<>();
      IOException e = assertThrows(IOException.class, () -> collectStreamingText(stream, events));
      assertTrue(e.getMessage().contains("Connection reset with autoContinuation=false"));
      assertEquals(1, sentRequests.size());
    }

    // 5. Resumed hop (Hop 2) also drops before emitting a new token -> propagates IOException (2
    // requests, no infinite loop)
    {
      List<JsonNode> sentRequests = new ArrayList<>();
      AtomicInteger hopIdx = new AtomicInteger(0);
      Client client =
          Client.builder().apiKey("test-key").asyncRetryScheduler(SHARED_SCHEDULER).build();

      HTTPClient consecutiveFailClient =
          new HTTPClient() {
            @Override
            public HttpResponse<InputStream> send(HttpRequest request) {
              sentRequests.add(mapper.createObjectNode());
              int currentHop = hopIdx.getAndIncrement();
              Headers headers = new Headers();
              headers.add("content-type", "text/event-stream");
              if (currentHop == 0) {
                StringBuilder sseBuilder = new StringBuilder();
                for (JsonNode seg : hop1Events) {
                  sseBuilder
                      .append("event: ")
                      .append(seg.get("event_type").asText())
                      .append("\n")
                      .append("data: ")
                      .append(seg.toString())
                      .append("\n\n");
                }
                byte[] validBytes = sseBuilder.toString().getBytes(StandardCharsets.UTF_8);
                InputStream failingStream =
                    new InputStream() {
                      private int pos = 0;

                      @Override
                      public int read() throws IOException {
                        if (pos < validBytes.length) {
                          return validBytes[pos++] & 0xFF;
                        }
                        throw new IOException("Hop 1 dropped");
                      }

                      @Override
                      public int read(byte[] b, int off, int len) throws IOException {
                        if (pos < validBytes.length) {
                          int toCopy = Math.min(len, validBytes.length - pos);
                          System.arraycopy(validBytes, pos, b, off, toCopy);
                          pos += toCopy;
                          return toCopy;
                        }
                        throw new IOException("Hop 1 dropped");
                      }
                    };
                return new HttpResponse<>(request, 200, headers, failingStream);
              } else {
                InputStream immediateFailStream =
                    new InputStream() {
                      @Override
                      public int read() throws IOException {
                        throw new IOException("Hop 2 dropped before token");
                      }
                    };
                return new HttpResponse<>(request, 200, headers, immediateFailStream);
              }
            }

            @Override
            public CompletableFuture<HttpResponse<InputStream>> sendAsync(HttpRequest request) {
              return CompletableFuture.completedFuture(send(request));
            }
          };

      setMockGaosClient(client, consecutiveFailClient);

      EventStream<InteractionSSEStreamEvent> stream =
          client.interactions.create(reqBody, true).events();
      List<InteractionSSEStreamEvent> events = new ArrayList<>();
      IOException e = assertThrows(IOException.class, () -> collectStreamingText(stream, events));
      assertTrue(e.getMessage().contains("Hop 2 dropped before token"));
      assertEquals(2, sentRequests.size());
    }

    // 6. Server-sent SSE ErrorEvent after checkpoint token -> stops on EOF without reconnecting
    {
      ArrayNode hop1WithError = hop1Events.deepCopy();
      ObjectNode errorEv = mapper.createObjectNode();
      errorEv.put("event_type", "error");
      ObjectNode errDetail = mapper.createObjectNode();
      errDetail.put("code", "resource_exhausted");
      errDetail.put("message", "Quota exceeded mid-stream");
      errorEv.set("error", errDetail);
      hop1WithError.add(errorEv);

      ObjectNode errorHop = mapper.createObjectNode();
      ObjectNode errorResp = mapper.createObjectNode();
      errorResp.put("status_code", 200);
      ObjectNode errorHeaders = mapper.createObjectNode();
      errorHeaders.put("content-type", "text/event-stream");
      errorResp.set("headers", errorHeaders);
      errorResp.set("body_segments", hop1WithError);
      errorHop.set("response", errorResp);

      List<JsonNode> sentRequests = new ArrayList<>();
      Client client = makeReplayClient(createMockRecording(errorHop), sentRequests);

      EventStream<InteractionSSEStreamEvent> stream =
          client.interactions.create(reqBody, true).events();
      List<InteractionSSEStreamEvent> events = new ArrayList<>();
      String partialText = collectStreamingText(stream, events);

      assertEquals("First part ", partialText);
      assertEquals(1, sentRequests.size());
      assertEquals(5, events.size());
    }
  }

  // --- 2.5 Output text derivation tests ---

  @Test
  public void testOutputTextSkipsThoughtStepsAndStopsAtNonTextBoundaries() throws Exception {
    ObjectMapper mapper = Utils.mapper();

    // Case 1: Skips ThoughtSteps between ModelOutputSteps
    ObjectNode inter1 = mapper.createObjectNode();
    inter1.put("id", "id_thought_skip");
    inter1.put("status", "completed");
    ArrayNode steps1 = mapper.createArrayNode();
    steps1.add(mapper.createObjectNode().put("type", "thought").put("signature", "sig1"));
    ObjectNode m1 = mapper.createObjectNode().put("type", "model_output");
    m1.set(
        "content",
        mapper
            .createArrayNode()
            .add(mapper.createObjectNode().put("type", "text").put("text", "Part 1 ")));
    steps1.add(m1);
    steps1.add(mapper.createObjectNode().put("type", "thought").put("signature", "sig2"));
    ObjectNode m2 = mapper.createObjectNode().put("type", "model_output");
    m2.set(
        "content",
        mapper
            .createArrayNode()
            .add(mapper.createObjectNode().put("type", "text").put("text", "Part 2")));
    steps1.add(m2);
    inter1.set("steps", steps1);

    // Case 2: Stops at user_input boundary before the final turn's ModelOutputSteps
    ObjectNode inter2 = mapper.createObjectNode();
    inter2.put("id", "id_user_boundary");
    inter2.put("status", "completed");
    ArrayNode steps2 = mapper.createArrayNode();
    ObjectNode earlierOutput = mapper.createObjectNode().put("type", "model_output");
    earlierOutput.set(
        "content",
        mapper
            .createArrayNode()
            .add(mapper.createObjectNode().put("type", "text").put("text", "Earlier turn ")));
    steps2.add(earlierOutput);
    ObjectNode userInput = mapper.createObjectNode().put("type", "user_input");
    userInput.set(
        "content",
        mapper
            .createArrayNode()
            .add(mapper.createObjectNode().put("type", "text").put("text", "Follow-up")));
    steps2.add(userInput);
    steps2.addAll(steps1);
    inter2.set("steps", steps2);

    // Case 3: Stops at function_call boundary before the final ModelOutputStep
    ObjectNode inter3 = mapper.createObjectNode();
    inter3.put("id", "id_fn_boundary");
    inter3.put("status", "completed");
    ArrayNode steps3 = mapper.createArrayNode();
    steps3.add(m1);
    ObjectNode fnCall =
        mapper
            .createObjectNode()
            .put("type", "function_call")
            .put("id", "call_1")
            .put("name", "do_work");
    fnCall.set("arguments", mapper.createObjectNode());
    steps3.add(fnCall);
    steps3.add(mapper.createObjectNode().put("type", "thought").put("signature", "sig2"));
    steps3.add(m2);
    inter3.set("steps", steps3);

    List<ObjectNode> hops = new ArrayList<>();
    for (ObjectNode inter : new ObjectNode[] {inter1, inter2, inter3}) {
      ObjectNode hop = mapper.createObjectNode();
      ObjectNode resp = mapper.createObjectNode();
      resp.put("status_code", 200);
      resp.set("headers", mapper.createObjectNode().put("content-type", "application/json"));
      resp.set("body_segments", mapper.createArrayNode().add(inter));
      hop.set("response", resp);
      hops.add(hop);
    }

    List<JsonNode> sentRequests = new ArrayList<>();
    Client client =
        makeReplayClient(createMockRecording(hops.get(0), hops.get(1), hops.get(2)), sentRequests);
    CreateInteractionRequestBody reqBody =
        CreateInteractionRequestBody.of(
            CreateModelInteraction.builder()
                .model("gemini-2.5-pro")
                .input(InteractionsInput.of("Test outputText boundaries"))
                .build());

    Interaction res1 = client.interactions.create(reqBody, false).interaction().get();
    assertEquals("Part 1 Part 2", res1.outputText().orElse(null));

    Interaction res2 = client.interactions.create(reqBody, false).interaction().get();
    assertEquals("Part 1 Part 2", res2.outputText().orElse(null));

    Interaction res3 = client.interactions.create(reqBody, false).interaction().get();
    assertEquals("Part 2", res3.outputText().orElse(null));
  }

  // --- 2.5b Transient HTTP retry tests ---

  @Test
  public void testUnaryAutomaticContinuationTransientRetryOnHop2() throws Exception {
    ObjectMapper mapper = Utils.mapper();

    ObjectNode hop1 =
        createUnaryHop(
            mapper, "int_retry_1", "continuation_required", "tok_retry_1", "Hop 1 ", 20L);

    ObjectNode hop2Err = mapper.createObjectNode();
    ObjectNode hop2ErrResp = mapper.createObjectNode();
    hop2ErrResp.put("status_code", 503);
    ObjectNode hop2ErrHeaders = mapper.createObjectNode();
    hop2ErrHeaders.put("content-type", "application/json");
    hop2ErrResp.set("headers", hop2ErrHeaders);
    ArrayNode errBody = mapper.createArrayNode();
    ObjectNode errObj = mapper.createObjectNode();
    errObj.put("error", "Service unavailable");
    errBody.add(errObj);
    hop2ErrResp.set("body_segments", errBody);
    hop2Err.set("response", hop2ErrResp);

    ObjectNode hop2Ok =
        createUnaryHop(mapper, "int_retry_1", "completed", null, "Hop 1 Hop 2", 40L);

    List<JsonNode> sentRequests = new ArrayList<>();
    Client client = makeReplayClient(createMockRecording(hop1, hop2Err, hop2Ok), sentRequests);

    Options options =
        Options.builder()
            .retryConfig(
                RetryConfig.builder()
                    .attemptCountBackoff(
                        2,
                        BackoffStrategy.builder()
                            .initialInterval(1, TimeUnit.MILLISECONDS)
                            .maxInterval(2, TimeUnit.MILLISECONDS)
                            .baseFactor(2.0)
                            .maxElapsedTime(1000, TimeUnit.MILLISECONDS)
                            .retryConnectError(true)
                            .build())
                    .build())
            .build();

    CreateInteractionRequestBody reqBody =
        CreateInteractionRequestBody.of(
            CreateModelInteraction.builder()
                .model("gemini-2.5-pro")
                .input(InteractionsInput.of("Retry test"))
                .build());
    CreateInteractionRequest req = new CreateInteractionRequest(null, reqBody, true);

    CreateInteractionResponse response = client.interactions.create(req, options);
    Interaction res =
        response.interaction().orElseThrow(() -> new AssertionError("Expected interaction"));

    assertEquals(3, sentRequests.size());
    assertTrue(!sentRequests.get(0).has("continuation_token"));
    assertEquals("tok_retry_1", sentRequests.get(1).get("continuation_token").asText());
    assertEquals("tok_retry_1", sentRequests.get(2).get("continuation_token").asText());
    assertEquals("completed", res.status().map(st -> st.value()).orElse(null));
    assertEquals("Hop 1 Hop 2", res.outputText().orElse(null));
  }

  // --- 2.6 Warning behavior tests ---

  @Test
  public void testWarningBehavior_incompleteUnaryWarnsOnceAndSuppresses() throws Exception {
    ObjectMapper mapper = Utils.mapper();
    Interactions._resetContinuationWarningsForTesting();

    List<String> warnings = new ArrayList<>();
    Handler handler =
        new Handler() {
          @Override
          public void publish(LogRecord record) {
            if (Level.WARNING.equals(record.getLevel())) {
              warnings.add(record.getMessage());
            }
          }

          @Override
          public void flush() {}

          @Override
          public void close() {}
        };
    Logger logger = Logger.getLogger(Interactions.class.getName());
    logger.addHandler(handler);

    try {
      // 1. When automaticContinuation=false and status=continuation_required, warns once
      ObjectNode hop1 =
          createUnaryHop(mapper, "int_1", "continuation_required", "tok_1", "Part 1", 20L);
      List<JsonNode> sentRequests1 = new ArrayList<>();
      Client client1 = makeReplayClient(createMockRecording(hop1), sentRequests1);
      CreateInteractionRequestBody reqBody1 =
          CreateInteractionRequestBody.of(
              CreateModelInteraction.builder()
                  .model("gemini-2.5-pro")
                  .input(InteractionsInput.of("Test 1"))
                  .build());
      client1.interactions.create(reqBody1, false);

      assertEquals(1, warnings.size());
      assertTrue(warnings.get(0).contains("status='continuation_required'"));

      // 2. Second call does not warn again (one-time per process)
      ObjectNode hop2 =
          createUnaryHop(mapper, "int_2", "continuation_required", "tok_2", "Part 2", 20L);
      List<JsonNode> sentRequests2 = new ArrayList<>();
      Client client2 = makeReplayClient(createMockRecording(hop2), sentRequests2);
      CreateInteractionRequestBody reqBody2 =
          CreateInteractionRequestBody.of(
              CreateModelInteraction.builder()
                  .model("gemini-2.5-pro")
                  .input(InteractionsInput.of("Test 2"))
                  .build());
      client2.interactions.create(reqBody2, false);

      assertEquals(1, warnings.size());

      // 3. Reset warnings and test suppression when caller explicitly passes continuationToken
      Interactions._resetContinuationWarningsForTesting();
      warnings.clear();

      ObjectNode hop3 =
          createUnaryHop(mapper, "int_3", "continuation_required", "tok_3", "Part 3", 20L);
      List<JsonNode> sentRequests3 = new ArrayList<>();
      Client client3 = makeReplayClient(createMockRecording(hop3), sentRequests3);
      CreateInteractionRequestBody reqBody3 =
          CreateInteractionRequestBody.of(
              CreateModelInteraction.builder()
                  .model("gemini-2.5-pro")
                  .input(InteractionsInput.of("Test 3"))
                  .continuationToken("caller_tok")
                  .build());
      client3.interactions.create(reqBody3, false);

      assertEquals(0, warnings.size());
    } finally {
      logger.removeHandler(handler);
    }
  }

  @Test
  public void testWarningBehavior_incompleteStreamingWarnsOnce() throws Exception {
    ObjectMapper mapper = Utils.mapper();
    Interactions._resetContinuationWarningsForTesting();

    List<String> warnings = new ArrayList<>();
    Handler handler =
        new Handler() {
          @Override
          public void publish(LogRecord record) {
            if (Level.WARNING.equals(record.getLevel())) {
              warnings.add(record.getMessage());
            }
          }

          @Override
          public void flush() {}

          @Override
          public void close() {}
        };
    Logger logger = Logger.getLogger(Interactions.class.getName());
    logger.addHandler(handler);

    try {
      ObjectNode hop1 =
          createStreamingHop(mapper, "int_1", "continuation_required", "tok_1", "Stream 1", 20L);
      List<JsonNode> sentRequests = new ArrayList<>();
      Client client = makeReplayClient(createMockRecording(hop1), sentRequests);
      CreateInteractionRequestBody reqBody =
          CreateInteractionRequestBody.of(
              CreateModelInteraction.builder()
                  .model("gemini-2.5-pro")
                  .input(InteractionsInput.of("Stream test"))
                  .stream(true)
                  .build());

      CreateInteractionResponse response = client.interactions.create(reqBody, false);
      EventStream<InteractionSSEStreamEvent> stream = response.events();
      while (stream.next().isPresent()) {}

      assertEquals(1, warnings.size());
      assertTrue(warnings.get(0).contains("status='continuation_required'"));
    } finally {
      logger.removeHandler(handler);
    }
  }

  @Test
  public void testStreamingAutomaticContinuationUsesCheckpointTokenFromStatusUpdate()
      throws Exception {
    ObjectMapper mapper = Utils.mapper();

    ArrayNode hop1Events = mapper.createArrayNode();
    ObjectNode statusUpdateEv = mapper.createObjectNode();
    statusUpdateEv.put("event_type", "interaction.status_update");
    statusUpdateEv.put("interaction_id", "int_status_upd");
    statusUpdateEv.put("status", "in_progress");
    statusUpdateEv.put("continuation_token", "tok_from_status_update");
    hop1Events.add(statusUpdateEv);

    ObjectNode hop1CompletedEv = mapper.createObjectNode();
    hop1CompletedEv.put("event_type", "interaction.completed");
    ObjectNode hop1Inter = mapper.createObjectNode();
    hop1Inter.put("id", "int_status_upd");
    hop1Inter.put("status", "continuation_required");
    hop1CompletedEv.set("interaction", hop1Inter);
    hop1Events.add(hop1CompletedEv);

    ObjectNode hop1 = mapper.createObjectNode();
    ObjectNode hop1Resp = mapper.createObjectNode();
    hop1Resp.put("status_code", 200);
    ObjectNode hop1Headers = mapper.createObjectNode();
    hop1Headers.put("content-type", "text/event-stream");
    hop1Resp.set("headers", hop1Headers);
    hop1Resp.set("body_segments", hop1Events);
    hop1.set("response", hop1Resp);

    ObjectNode hop2 = createStreamingHop(mapper, "int_status_upd", "completed", null, "Done", 10L);

    List<JsonNode> sentRequests = new ArrayList<>();
    Client client = makeReplayClient(createMockRecording(hop1, hop2), sentRequests);
    CreateInteractionRequestBody reqBody =
        CreateInteractionRequestBody.of(
            CreateModelInteraction.builder()
                .model("gemini-2.5-pro")
                .input(InteractionsInput.of("Status update checkpoint test"))
                .stream(true)
                .build());

    CreateInteractionResponse response = client.interactions.create(reqBody, true);
    EventStream<InteractionSSEStreamEvent> stream = response.events();
    List<InteractionSSEStreamEvent> gotEvents = new ArrayList<>();
    for (Optional<InteractionSSEStreamEvent> item = stream.next();
        item.isPresent();
        item = stream.next()) {
      gotEvents.add(item.get());
    }

    assertEquals(2, sentRequests.size());
    assertEquals(5, gotEvents.size());
    assertTrue(!sentRequests.get(0).has("continuation_token"));
    assertEquals("tok_from_status_update", sentRequests.get(1).get("continuation_token").asText());
  }

  @Test
  public void testGetAndCancelDeriveOutputText() throws Exception {
    ObjectMapper mapper = Utils.mapper();

    ObjectNode hopGet =
        createUnaryHop(mapper, "int_get", "completed", null, "Derived get text", 5L);
    ((ObjectNode) hopGet.get("response").get("body_segments").get(0)).remove("output_text");

    ObjectNode hopCancel =
        createUnaryHop(mapper, "int_cancel", "cancelled", null, "Derived cancel text", 5L);
    ((ObjectNode) hopCancel.get("response").get("body_segments").get(0)).remove("output_text");

    ObjectNode hopAsyncCancel =
        createUnaryHop(
            mapper, "int_async_cancel", "cancelled", null, "Derived async cancel text", 5L);
    ((ObjectNode) hopAsyncCancel.get("response").get("body_segments").get(0)).remove("output_text");

    ObjectNode hopPreserve = createUnaryHop(mapper, "int_preserve", "completed", null, null, 5L);
    ((ObjectNode) hopPreserve.get("response").get("body_segments").get(0))
        .put("output_text", "Existing output text");

    List<JsonNode> sentRequests = new ArrayList<>();
    Client client =
        makeReplayClient(
            createMockRecording(hopGet, hopCancel, hopAsyncCancel, hopPreserve), sentRequests);

    Interaction gotGet =
        client
            .interactions
            .get(new GetInteractionByIdRequest("int_get"))
            .interaction()
            .orElseThrow(() -> new AssertionError("Expected interaction from get"));
    assertEquals("Derived get text", gotGet.outputText().orElse(null));

    Interaction gotCancel =
        client
            .interactions
            .cancel("int_cancel")
            .interaction()
            .orElseThrow(() -> new AssertionError("Expected interaction from cancel"));
    assertEquals("Derived cancel text", gotCancel.outputText().orElse(null));

    Interaction gotAsyncCancel =
        client
            .async
            .interactions
            .cancel("int_async_cancel")
            .get()
            .interaction()
            .orElseThrow(() -> new AssertionError("Expected interaction from async cancel"));
    assertEquals("Derived async cancel text", gotAsyncCancel.outputText().orElse(null));

    Interaction gotPreserve =
        client
            .interactions
            .get(new GetInteractionByIdRequest("int_preserve"))
            .interaction()
            .orElseThrow(() -> new AssertionError("Expected interaction from get"));
    assertEquals("Existing output text", gotPreserve.outputText().orElse(null));
    Path workDir = Paths.get(".");
    assertTrue(Files.exists(workDir));
  }
}
