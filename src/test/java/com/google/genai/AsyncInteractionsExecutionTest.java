/*
 * Copyright 2025 Google LLC
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.genai.types.interactions.CancelInteractionConfig;
import com.google.genai.types.interactions.CreateInteractionConfig;
import com.google.genai.types.interactions.DeleteInteractionConfig;
import com.google.genai.types.interactions.DeleteInteractionResponse;
import com.google.genai.types.interactions.GetInteractionConfig;
import com.google.genai.types.interactions.Interaction;
import com.google.genai.types.interactions.streaming.ContentDelta;
import com.google.genai.types.interactions.streaming.InteractionSseEvent;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for AsyncInteractions execution methods.
 *
 * <p>Tests async execution, CompletableFuture behavior, and streaming functionality.
 */
public class AsyncInteractionsExecutionTest {

  private static final String MODEL_ID = "gemini-2.5-flash";
  private static final String INTERACTION_ID = "test-interaction-123";

  private ApiClient mockedClient;
  private Client client;

  @BeforeEach
  void setUp() throws Exception {
    mockedClient = mock(ApiClient.class);

    String apiKey = "test-key";
    client = Client.builder().apiKey(apiKey).vertexAI(false).build();

    // Use reflection to inject mocked client into both sync and async interactions
    Field apiClientField = Interactions.class.getDeclaredField("apiClient");
    apiClientField.setAccessible(true);
    apiClientField.set(client.interactions, mockedClient);

    Field asyncApiClientField = AsyncInteractions.class.getDeclaredField("apiClient");
    asyncApiClientField.setAccessible(true);
    asyncApiClientField.set(client.async.interactions, mockedClient);

    // Also inject into the interactions field of AsyncInteractions
    Field interactionsField = AsyncInteractions.class.getDeclaredField("interactions");
    interactionsField.setAccessible(true);
    Interactions syncInteractions = (Interactions) interactionsField.get(client.async.interactions);
    apiClientField.set(syncInteractions, mockedClient);
  }

  private ApiResponse createMockedJsonResponse(String json) {
    ApiResponse response = mock(ApiResponse.class);
    ResponseBody body = ResponseBody.create(json, MediaType.get("application/json"));
    when(response.getBody()).thenReturn(body);
    return response;
  }

  private ApiResponse createMockedSseResponse(String sseData) {
    ApiResponse response = mock(ApiResponse.class);
    ResponseBody body = mock(ResponseBody.class);
    InputStream inputStream = new ByteArrayInputStream(sseData.getBytes(StandardCharsets.UTF_8));
    when(body.byteStream()).thenReturn(inputStream);
    when(response.getBody()).thenReturn(body);
    return response;
  }

  // ==================== Async Create Tests ====================

  @Test
  public void testAsyncCreateReturnsCompletableFuture() {
    String responseJson = "{\"id\":\"" + INTERACTION_ID + "\",\"status\":\"completed\"}";
    CompletableFuture<ApiResponse> futureResponse =
        CompletableFuture.completedFuture(createMockedJsonResponse(responseJson));
    when(mockedClient.asyncRequest(anyString(), anyString(), anyString(), any()))
        .thenReturn(futureResponse);

    CreateInteractionConfig config =
        CreateInteractionConfig.builder().model(MODEL_ID).input("Test").build();

    CompletableFuture<Interaction> result = client.async.interactions.create(config);

    assertNotNull(result);
    assertInstanceOf(CompletableFuture.class, result);
  }

  @Test
  public void testAsyncCreateCompletesWithInteraction() throws Exception {
    String responseJson =
        "{\"id\":\""
            + INTERACTION_ID
            + "\",\"status\":\"completed\",\"model\":\""
            + MODEL_ID
            + "\"}";
    CompletableFuture<ApiResponse> futureResponse =
        CompletableFuture.completedFuture(createMockedJsonResponse(responseJson));
    when(mockedClient.asyncRequest(anyString(), anyString(), anyString(), any()))
        .thenReturn(futureResponse);

    CreateInteractionConfig config =
        CreateInteractionConfig.builder().model(MODEL_ID).input("Test").build();

    Interaction interaction = client.async.interactions.create(config).get(5, TimeUnit.SECONDS);

    assertNotNull(interaction);
    assertEquals(INTERACTION_ID, interaction.id());
    assertTrue(interaction.model().isPresent());
    assertEquals(MODEL_ID, interaction.model().get());
  }

  @Test
  public void testAsyncCreateExceptionPropagation() {
    CompletableFuture<ApiResponse> futureResponse = new CompletableFuture<>();
    futureResponse.completeExceptionally(new RuntimeException("API Error"));
    when(mockedClient.asyncRequest(anyString(), anyString(), anyString(), any()))
        .thenReturn(futureResponse);

    CreateInteractionConfig config =
        CreateInteractionConfig.builder().model(MODEL_ID).input("Test").build();

    CompletableFuture<Interaction> result = client.async.interactions.create(config);

    ExecutionException exception =
        assertThrows(ExecutionException.class, () -> result.get(5, TimeUnit.SECONDS));
    assertInstanceOf(RuntimeException.class, exception.getCause());
    assertTrue(exception.getCause().getMessage().contains("API Error"));
  }

  // ==================== Async Get Tests ====================

  @Test
  public void testAsyncGetReturnsCompletableFuture() {
    String responseJson = "{\"id\":\"" + INTERACTION_ID + "\",\"status\":\"completed\"}";
    CompletableFuture<ApiResponse> futureResponse =
        CompletableFuture.completedFuture(createMockedJsonResponse(responseJson));
    when(mockedClient.asyncRequest(anyString(), anyString(), anyString(), any()))
        .thenReturn(futureResponse);

    GetInteractionConfig config = GetInteractionConfig.builder().build();

    CompletableFuture<Interaction> result = client.async.interactions.get(INTERACTION_ID, config);

    assertNotNull(result);
    assertInstanceOf(CompletableFuture.class, result);
  }

  @Test
  public void testAsyncGetCompletesWithInteraction() throws Exception {
    String responseJson =
        "{\"id\":\""
            + INTERACTION_ID
            + "\",\"status\":\"in_progress\",\"outputs\":[{\"type\":\"text\",\"text\":\"Response\"}]}";
    CompletableFuture<ApiResponse> futureResponse =
        CompletableFuture.completedFuture(createMockedJsonResponse(responseJson));
    when(mockedClient.asyncRequest(anyString(), anyString(), anyString(), any()))
        .thenReturn(futureResponse);

    GetInteractionConfig config = GetInteractionConfig.builder().build();

    Interaction interaction =
        client.async.interactions.get(INTERACTION_ID, config).get(5, TimeUnit.SECONDS);

    assertNotNull(interaction);
    assertEquals(INTERACTION_ID, interaction.id());
    assertTrue(interaction.outputs().isPresent());
    assertEquals(1, interaction.outputs().get().size());
  }

  // ==================== Async Cancel Tests ====================

  @Test
  public void testAsyncCancelReturnsCompletableFuture() {
    String responseJson = "{\"id\":\"" + INTERACTION_ID + "\",\"status\":\"failed\"}";
    CompletableFuture<ApiResponse> futureResponse =
        CompletableFuture.completedFuture(createMockedJsonResponse(responseJson));
    when(mockedClient.asyncRequest(anyString(), anyString(), anyString(), any()))
        .thenReturn(futureResponse);

    CancelInteractionConfig config = CancelInteractionConfig.builder().build();

    CompletableFuture<Interaction> result =
        client.async.interactions.cancel(INTERACTION_ID, config);

    assertNotNull(result);
    assertInstanceOf(CompletableFuture.class, result);
  }

  @Test
  public void testAsyncCancelCompletes() throws Exception {
    String responseJson = "{\"id\":\"" + INTERACTION_ID + "\",\"status\":\"failed\"}";
    CompletableFuture<ApiResponse> futureResponse =
        CompletableFuture.completedFuture(createMockedJsonResponse(responseJson));
    when(mockedClient.asyncRequest(anyString(), anyString(), anyString(), any()))
        .thenReturn(futureResponse);

    CancelInteractionConfig config = CancelInteractionConfig.builder().build();

    Interaction interaction =
        client.async.interactions.cancel(INTERACTION_ID, config).get(5, TimeUnit.SECONDS);

    assertNotNull(interaction);
    assertEquals(INTERACTION_ID, interaction.id());
  }

  // ==================== Async Delete Tests ====================

  @Test
  public void testAsyncDeleteReturnsCompletableFuture() {
    String responseJson = "{}";
    CompletableFuture<ApiResponse> futureResponse =
        CompletableFuture.completedFuture(createMockedJsonResponse(responseJson));
    when(mockedClient.asyncRequest(anyString(), anyString(), anyString(), any()))
        .thenReturn(futureResponse);

    DeleteInteractionConfig config = DeleteInteractionConfig.builder().build();

    CompletableFuture<Void> result =
        client.async.interactions.delete(INTERACTION_ID, config);

    assertNotNull(result);
    assertInstanceOf(CompletableFuture.class, result);
  }

  @Test
  public void testAsyncDeleteCompletes() throws Exception {
    String responseJson = "{}";
    CompletableFuture<ApiResponse> futureResponse =
        CompletableFuture.completedFuture(createMockedJsonResponse(responseJson));
    when(mockedClient.asyncRequest(anyString(), anyString(), anyString(), any()))
        .thenReturn(futureResponse);

    DeleteInteractionConfig config = DeleteInteractionConfig.builder().build();

    client.async.interactions.delete(INTERACTION_ID, config).get(5, TimeUnit.SECONDS);

    // Assert - Success is indicated by no exception thrown
    // (No assertions needed - if get() completes without exception, delete was successful)
  }

  // ==================== Async Streaming Tests ====================

  @Test
  public void testAsyncCreateStreamReturnsCompletableFuture() {
    String sseData =
        "data: {\"event_type\":\"interaction.complete\",\"interaction\":{\"id\":\"int-001\",\"status\":\"completed\"}}\n\n"
            + "data: [DONE]\n";
    CompletableFuture<ApiResponse> futureResponse =
        CompletableFuture.completedFuture(createMockedSseResponse(sseData));
    when(mockedClient.asyncRequest(anyString(), anyString(), anyString(), any()))
        .thenReturn(futureResponse);

    CreateInteractionConfig config =
        CreateInteractionConfig.builder().model(MODEL_ID).input("Test").build();

    CompletableFuture<InteractionEventStream<InteractionSseEvent>> result =
        client.async.interactions.createStream(config);

    assertNotNull(result);
    assertInstanceOf(CompletableFuture.class, result);
  }

  @Test
  public void testAsyncGetStreamReturnsCompletableFuture() {
    String sseData =
        "data: {\"event_type\":\"interaction.complete\",\"interaction\":{\"id\":\"int-001\",\"status\":\"completed\"}}\n\n"
            + "data: [DONE]\n";
    CompletableFuture<ApiResponse> futureResponse =
        CompletableFuture.completedFuture(createMockedSseResponse(sseData));
    when(mockedClient.asyncRequest(anyString(), anyString(), anyString(), any()))
        .thenReturn(futureResponse);

    GetInteractionConfig config = GetInteractionConfig.builder().build();

    CompletableFuture<InteractionEventStream<InteractionSseEvent>> result =
        client.async.interactions.getStream(INTERACTION_ID, config);

    assertNotNull(result);
    assertInstanceOf(CompletableFuture.class, result);
  }

  @Test
  public void testAsyncCreateStreamIterateEvents() throws Exception {
    String sseData =
        "data: {\"event_type\":\"content.delta\",\"event_id\":\"e1\",\"index\":0,\"delta\":{\"type\":\"text\",\"text\":\"Hello \"}}\n\n"
            + "data: {\"event_type\":\"content.delta\",\"event_id\":\"e2\",\"index\":0,\"delta\":{\"type\":\"text\",\"text\":\"World!\"}}\n\n"
            + "data: [DONE]\n";
    CompletableFuture<ApiResponse> futureResponse =
        CompletableFuture.completedFuture(createMockedSseResponse(sseData));
    when(mockedClient.asyncRequest(anyString(), anyString(), anyString(), any()))
        .thenReturn(futureResponse);

    CreateInteractionConfig config =
        CreateInteractionConfig.builder().model(MODEL_ID).input("Test").build();

    try (InteractionEventStream<InteractionSseEvent> stream =
        client.async.interactions.createStream(config).get(5, TimeUnit.SECONDS)) {
      List<InteractionSseEvent> events = new ArrayList<>();
      for (InteractionSseEvent event : stream) {
        events.add(event);
      }

      assertEquals(2, events.size());
      assertInstanceOf(ContentDelta.class, events.get(0));
      assertInstanceOf(ContentDelta.class, events.get(1));
    }
  }

  // ==================== CompletableFuture Behavior Tests ====================

  @Test
  public void testAsyncOperationsNonBlocking() throws Exception {
    // Create a delayed response to simulate async behavior
    CompletableFuture<ApiResponse> futureResponse = new CompletableFuture<>();

    when(mockedClient.asyncRequest(anyString(), anyString(), anyString(), any()))
        .thenReturn(futureResponse);

    CreateInteractionConfig config =
        CreateInteractionConfig.builder().model(MODEL_ID).input("Test").build();

    long startTime = System.currentTimeMillis();
    CompletableFuture<Interaction> result = client.async.interactions.create(config);
    long endTime = System.currentTimeMillis();

    // The call should return immediately (non-blocking)
    assertTrue(endTime - startTime < 100, "Async call should return immediately");
    assertFalse(result.isDone(), "Future should not be completed yet");

    // Complete the future
    String responseJson = "{\"id\":\"" + INTERACTION_ID + "\",\"status\":\"completed\"}";
    futureResponse.complete(createMockedJsonResponse(responseJson));

    // Now it should complete
    Interaction interaction = result.get(5, TimeUnit.SECONDS);
    assertNotNull(interaction);
  }

  @Test
  public void testMultipleAsyncInParallel() throws Exception {
    AtomicInteger callCount = new AtomicInteger(0);

    when(mockedClient.asyncRequest(anyString(), anyString(), anyString(), any()))
        .thenAnswer(
            invocation -> {
              callCount.incrementAndGet();
              String responseJson =
                  "{\"id\":\"int-" + callCount.get() + "\",\"status\":\"completed\"}";
              return CompletableFuture.completedFuture(createMockedJsonResponse(responseJson));
            });

    CreateInteractionConfig config =
        CreateInteractionConfig.builder().model(MODEL_ID).input("Test").build();

    // Launch 3 async operations in parallel
    CompletableFuture<Interaction> future1 = client.async.interactions.create(config);
    CompletableFuture<Interaction> future2 = client.async.interactions.create(config);
    CompletableFuture<Interaction> future3 = client.async.interactions.create(config);

    // Wait for all to complete
    CompletableFuture.allOf(future1, future2, future3).get(5, TimeUnit.SECONDS);

    // Verify all completed
    assertTrue(future1.isDone());
    assertTrue(future2.isDone());
    assertTrue(future3.isDone());

    // Verify 3 calls were made
    verify(mockedClient, times(3)).asyncRequest(anyString(), anyString(), anyString(), any());
  }

  @Test
  public void testAsyncExceptionallyHandler() throws Exception {
    CompletableFuture<ApiResponse> futureResponse = new CompletableFuture<>();
    futureResponse.completeExceptionally(new RuntimeException("Network error"));
    when(mockedClient.asyncRequest(anyString(), anyString(), anyString(), any()))
        .thenReturn(futureResponse);

    CreateInteractionConfig config =
        CreateInteractionConfig.builder().model(MODEL_ID).input("Test").build();

    AtomicInteger handlerCalled = new AtomicInteger(0);
    CountDownLatch latch = new CountDownLatch(1);

    client
        .async
        .interactions
        .create(config)
        .exceptionally(
            ex -> {
              handlerCalled.incrementAndGet();
              latch.countDown();
              return null;
            });

    assertTrue(latch.await(5, TimeUnit.SECONDS));
    assertEquals(1, handlerCalled.get());
  }

  @Test
  public void testAsyncThenApplyChaining() throws Exception {
    String responseJson = "{\"id\":\"" + INTERACTION_ID + "\",\"status\":\"completed\"}";
    CompletableFuture<ApiResponse> futureResponse =
        CompletableFuture.completedFuture(createMockedJsonResponse(responseJson));
    when(mockedClient.asyncRequest(anyString(), anyString(), anyString(), any()))
        .thenReturn(futureResponse);

    CreateInteractionConfig config =
        CreateInteractionConfig.builder().model(MODEL_ID).input("Test").build();

    String result =
        client
            .async
            .interactions
            .create(config)
            .thenApply(Interaction::id)
            .thenApply(id -> "Processed: " + id)
            .get(5, TimeUnit.SECONDS);

    assertEquals("Processed: " + INTERACTION_ID, result);
  }

  @Test
  public void testAsyncThenComposeChaining() throws Exception {
    // First call returns an interaction
    String responseJson1 = "{\"id\":\"" + INTERACTION_ID + "\",\"status\":\"completed\"}";
    // Second call (simulating a get) returns updated interaction
    String responseJson2 =
        "{\"id\":\""
            + INTERACTION_ID
            + "\",\"status\":\"completed\",\"outputs\":[{\"type\":\"text\",\"text\":\"Result\"}]}";

    AtomicInteger callCount = new AtomicInteger(0);
    when(mockedClient.asyncRequest(anyString(), anyString(), anyString(), any()))
        .thenAnswer(
            invocation -> {
              String json = callCount.incrementAndGet() == 1 ? responseJson1 : responseJson2;
              return CompletableFuture.completedFuture(createMockedJsonResponse(json));
            });

    CreateInteractionConfig createConfig =
        CreateInteractionConfig.builder().model(MODEL_ID).input("Test").build();
    GetInteractionConfig getConfig = GetInteractionConfig.builder().build();

    Interaction result =
        client
            .async
            .interactions
            .create(createConfig)
            .thenCompose(
                interaction -> client.async.interactions.get(interaction.id(), getConfig))
            .get(5, TimeUnit.SECONDS);

    assertNotNull(result);
    assertTrue(result.outputs().isPresent());
    assertEquals(1, result.outputs().get().size());
  }

  // ==================== Validation Tests ====================

  @Test
  public void testAsyncCreateValidation_noModelOrAgent() {
    CreateInteractionConfig config = CreateInteractionConfig.builder().input("Test").build();

    // Validation happens synchronously before the async call
    assertThrows(IllegalArgumentException.class, () -> client.async.interactions.create(config));
  }

  @Test
  public void testAsyncCreateValidation_bothModelAndAgent() {
    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model(MODEL_ID)
            .agent("some-agent")
            .input("Test")
            .build();

    assertThrows(IllegalArgumentException.class, () -> client.async.interactions.create(config));
  }

  @Test
  public void testAsyncGetValidation_emptyId() {
    GetInteractionConfig config = GetInteractionConfig.builder().build();

    assertThrows(IllegalArgumentException.class, () -> client.async.interactions.get("", config));
  }

  @Test
  public void testAsyncCancelValidation_nullId() {
    CancelInteractionConfig config = CancelInteractionConfig.builder().build();

    assertThrows(
        IllegalArgumentException.class, () -> client.async.interactions.cancel(null, config));
  }

  @Test
  public void testAsyncDeleteValidation_emptyId() {
    DeleteInteractionConfig config = DeleteInteractionConfig.builder().build();

    assertThrows(
        IllegalArgumentException.class, () -> client.async.interactions.delete("", config));
  }

  // ==================== Timeout and Error Handling ====================

  @Test
  public void testAsyncTimeout() {
    // Create a future that never completes
    CompletableFuture<ApiResponse> neverCompletingFuture = new CompletableFuture<>();
    when(mockedClient.asyncRequest(anyString(), anyString(), anyString(), any()))
        .thenReturn(neverCompletingFuture);

    CreateInteractionConfig config =
        CreateInteractionConfig.builder().model(MODEL_ID).input("Test").build();

    CompletableFuture<Interaction> result = client.async.interactions.create(config);

    // Should timeout
    assertThrows(
        java.util.concurrent.TimeoutException.class, () -> result.get(100, TimeUnit.MILLISECONDS));
  }

  @Test
  public void testAsyncCompletionExceptionWrapping() {
    CompletableFuture<ApiResponse> futureResponse = new CompletableFuture<>();
    futureResponse.completeExceptionally(new IllegalStateException("Invalid state"));
    when(mockedClient.asyncRequest(anyString(), anyString(), anyString(), any()))
        .thenReturn(futureResponse);

    CreateInteractionConfig config =
        CreateInteractionConfig.builder().model(MODEL_ID).input("Test").build();

    CompletableFuture<Interaction> result = client.async.interactions.create(config);

    CompletionException exception = assertThrows(CompletionException.class, result::join);
    assertInstanceOf(IllegalStateException.class, exception.getCause());
  }
}
