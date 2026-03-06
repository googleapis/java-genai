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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.genai.types.interactions.CreateInteractionConfig;
import com.google.genai.types.interactions.Input;
import com.google.genai.types.interactions.Turn;
import com.google.genai.types.interactions.content.TextContent;
import java.lang.reflect.Field;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

/** Tests for the Interactions resource. */
public class InteractionsTest {

  @Test
  public void testValidationBothModelAndAgent() {
    // Arrange
    Client client = Client.builder().apiKey("test-key").build();
    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model("gemini-2.5-flash")
            .agent("deep-research-pro-preview-12-2025")
            .input("Test input")
            .build();

    // Act & Assert
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> client.interactions.create(config));

    assertNotNull(exception.getMessage());
    assert exception.getMessage().contains("Cannot specify both 'model' and 'agent'");
  }

  @Test
  public void testValidationNeitherModelNorAgent() {
    // Arrange
    Client client = Client.builder().apiKey("test-key").build();
    CreateInteractionConfig config =
        CreateInteractionConfig.builder().input("Test input").build();

    // Act & Assert
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> client.interactions.create(config));

    assertNotNull(exception.getMessage());
    assert exception.getMessage().contains("Must specify either 'model' or 'agent'");
  }

  @Test
  public void testValidationAgentWithGenerationConfig() {
    // Arrange
    Client client = Client.builder().apiKey("test-key").build();
    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .agent("deep-research-pro-preview-12-2025")
            .input("Test input")
            .generationConfig(
                com.google.genai.types.interactions.GenerationConfig.builder()
                    .temperature(0.5f)
                    .build())
            .build();

    // Act & Assert
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> client.interactions.create(config));

    assertNotNull(exception.getMessage());
    assert exception.getMessage().contains("Cannot use 'generationConfig' with agent-based");
  }

  @Test
  public void testValidationModelWithAgentConfig() {
    // Arrange
    Client client = Client.builder().apiKey("test-key").build();
    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model("gemini-2.5-flash")
            .input("Test input")
            .agentConfig(com.google.genai.types.interactions.DynamicAgentConfig.create())
            .build();

    // Act & Assert
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> client.interactions.create(config));

    assertNotNull(exception.getMessage());
    assert exception.getMessage().contains("Cannot use 'agentConfig' with model-based");
  }

  // Note: Vertex AI support was added in PR3.
  // The testVertexAIUnsupported test has been removed since Vertex AI is now supported.
  // Integration tests for Vertex AI are in examples/VertexAIInteractionTest.java

  // ==================== Convenience Overload Tests ====================

  @Test
  public void testCreate_StringModelStringText_delegatesToConfig() throws Exception {
    // Arrange
    Client client = Client.builder().apiKey("test-key").build();
    ApiClient mockedClient = Mockito.mock(ApiClient.class);
    ApiResponse mockedResponse = Mockito.mock(ApiResponse.class);

    // Use reflection to inject mocked client
    Field apiClientField = Interactions.class.getDeclaredField("apiClient");
    apiClientField.setAccessible(true);
    apiClientField.set(client.interactions, mockedClient);

    String responseJson = "{\"id\":\"test-id\",\"status\":\"completed\"}";
    ResponseBody body = ResponseBody.create(responseJson, MediaType.get("application/json"));
    when(mockedResponse.getBody()).thenReturn(body);

    ArgumentCaptor<String> bodyCaptor = ArgumentCaptor.forClass(String.class);
    when(mockedClient.request(anyString(), anyString(), bodyCaptor.capture(), any()))
        .thenReturn(mockedResponse);

    // Act
    client.interactions.create("gemini-2.5-flash", "Hello, world!");

    // Assert - Verify request was made with correct model and input
    String requestBody = bodyCaptor.getValue();
    assertNotNull(requestBody);
    assertTrue(requestBody.contains("\"model\""));
    assertTrue(requestBody.contains("gemini-2.5-flash"));
    assertTrue(requestBody.contains("Hello, world!"));
    verify(mockedClient).request(anyString(), anyString(), anyString(), any());
  }

  @Test
  public void testCreate_StringModelInput_delegatesToConfig() throws Exception {
    // Arrange
    Client client = Client.builder().apiKey("test-key").build();
    ApiClient mockedClient = Mockito.mock(ApiClient.class);
    ApiResponse mockedResponse = Mockito.mock(ApiResponse.class);

    Field apiClientField = Interactions.class.getDeclaredField("apiClient");
    apiClientField.setAccessible(true);
    apiClientField.set(client.interactions, mockedClient);

    String responseJson = "{\"id\":\"test-id\",\"status\":\"completed\"}";
    ResponseBody body = ResponseBody.create(responseJson, MediaType.get("application/json"));
    when(mockedResponse.getBody()).thenReturn(body);

    ArgumentCaptor<String> bodyCaptor = ArgumentCaptor.forClass(String.class);
    when(mockedClient.request(anyString(), anyString(), bodyCaptor.capture(), any()))
        .thenReturn(mockedResponse);

    // Create Input from turns
    Input conversation = Input.fromTurns(
        Turn.builder()
            .role("user")
            .content(TextContent.builder().text("Hello!").build())
            .build(),
        Turn.builder()
            .role("model")
            .content(TextContent.builder().text("Hi there!").build())
            .build(),
        Turn.builder()
            .role("user")
            .content(TextContent.builder().text("What's 2+2?").build())
            .build()
    );

    // Act
    client.interactions.create("gemini-2.5-flash", conversation);

    // Assert - Verify request was made with model and turns input
    String requestBody = bodyCaptor.getValue();
    assertNotNull(requestBody);
    assertTrue(requestBody.contains("\"model\""));
    assertTrue(requestBody.contains("gemini-2.5-flash"));
    // Input object should be serialized (turns will appear in request)
    verify(mockedClient).request(anyString(), anyString(), anyString(), any());
  }

  @Test
  public void testCreateStream_StringModelStringText_delegatesToConfig() throws Exception {
    // Arrange
    Client client = Client.builder().apiKey("test-key").build();
    ApiClient mockedClient = Mockito.mock(ApiClient.class);
    ApiResponse mockedResponse = Mockito.mock(ApiResponse.class);

    Field apiClientField = Interactions.class.getDeclaredField("apiClient");
    apiClientField.setAccessible(true);
    apiClientField.set(client.interactions, mockedClient);

    // Mock a streaming response (empty body for this test)
    ResponseBody body = ResponseBody.create("", MediaType.get("text/event-stream"));
    when(mockedResponse.getBody()).thenReturn(body);

    ArgumentCaptor<String> bodyCaptor = ArgumentCaptor.forClass(String.class);
    when(mockedClient.request(anyString(), anyString(), bodyCaptor.capture(), any()))
        .thenReturn(mockedResponse);

    // Act
    try (InteractionEventStream<?> stream =
        client.interactions.createStream("gemini-2.5-flash", "Tell me a story")) {
      // Just verify the stream was created - don't iterate
    }

    // Assert - Verify request was made with correct model, input, and stream=true
    String requestBody = bodyCaptor.getValue();
    assertNotNull(requestBody);
    assertTrue(requestBody.contains("\"model\""));
    assertTrue(requestBody.contains("gemini-2.5-flash"));
    assertTrue(requestBody.contains("Tell me a story"));
    assertTrue(requestBody.contains("\"stream\":true"));
    verify(mockedClient).request(anyString(), anyString(), anyString(), any());
  }

  @Test
  public void testCreateStream_StringModelInput_delegatesToConfig() throws Exception {
    // Arrange
    Client client = Client.builder().apiKey("test-key").build();
    ApiClient mockedClient = Mockito.mock(ApiClient.class);
    ApiResponse mockedResponse = Mockito.mock(ApiResponse.class);

    Field apiClientField = Interactions.class.getDeclaredField("apiClient");
    apiClientField.setAccessible(true);
    apiClientField.set(client.interactions, mockedClient);

    ResponseBody body = ResponseBody.create("", MediaType.get("text/event-stream"));
    when(mockedResponse.getBody()).thenReturn(body);

    ArgumentCaptor<String> bodyCaptor = ArgumentCaptor.forClass(String.class);
    when(mockedClient.request(anyString(), anyString(), bodyCaptor.capture(), any()))
        .thenReturn(mockedResponse);

    // Create Input from turns
    Input conversation = Input.fromTurns(
        Turn.builder()
            .role("user")
            .content(TextContent.builder().text("Remember my name is Bob.").build())
            .build(),
        Turn.builder()
            .role("model")
            .content(TextContent.builder().text("Got it, Bob!").build())
            .build(),
        Turn.builder()
            .role("user")
            .content(TextContent.builder().text("What's my name?").build())
            .build()
    );

    // Act
    try (InteractionEventStream<?> stream =
        client.interactions.createStream("gemini-2.5-flash", conversation)) {
      // Just verify the stream was created - don't iterate
    }

    // Assert - Verify request was made with model, input, and stream=true
    String requestBody = bodyCaptor.getValue();
    assertNotNull(requestBody);
    assertTrue(requestBody.contains("\"model\""));
    assertTrue(requestBody.contains("gemini-2.5-flash"));
    assertTrue(requestBody.contains("\"stream\":true"));
    verify(mockedClient).request(anyString(), anyString(), anyString(), any());
  }

  // ==================== Turn Factory Method Tests ====================

  @Test
  public void testTurn_user_createsUserTurnWithText() {
    // Act
    Turn turn = Turn.user("Hello, world!");

    // Assert
    assertTrue(turn.role().isPresent());
    assertEquals("user", turn.role().get());
    assertTrue(turn.content().isPresent());
    assertEquals(1, turn.content().get().size());
    assertTrue(turn.content().get().get(0) instanceof TextContent);
    TextContent textContent = (TextContent) turn.content().get().get(0);
    assertTrue(textContent.text().isPresent());
    assertEquals("Hello, world!", textContent.text().get());
  }

  @Test
  public void testTurn_model_createsModelTurnWithText() {
    // Act
    Turn turn = Turn.model("I'm here to help!");

    // Assert
    assertTrue(turn.role().isPresent());
    assertEquals("model", turn.role().get());
    assertTrue(turn.content().isPresent());
    assertEquals(1, turn.content().get().size());
    assertTrue(turn.content().get().get(0) instanceof TextContent);
    TextContent textContent = (TextContent) turn.content().get().get(0);
    assertTrue(textContent.text().isPresent());
    assertEquals("I'm here to help!", textContent.text().get());
  }

  @Test
  public void testTurn_factoryMethods_workWithInputFromTurns() {
    // Act - Create a conversation using the new factory methods
    Input conversation = Input.fromTurns(
        Turn.user("Hello!"),
        Turn.model("Hi there!"),
        Turn.user("What's 2+2?")
    );

    // Assert - Verify conversation serializes correctly
    assertNotNull(conversation);
    String json = conversation.toJson();

    // Should serialize as array of turns
    assertTrue(json.startsWith("["));
    assertTrue(json.contains("\"role\":\"user\""));
    assertTrue(json.contains("\"role\":\"model\""));
    assertTrue(json.contains("Hello!"));
    assertTrue(json.contains("Hi there!"));
    assertTrue(json.contains("What's 2+2?"));
  }

  @Test
  public void testGetConvenienceOverload() throws Exception {
    // Arrange
    Client client = Client.builder().apiKey("test-key").build();
    ApiClient mockedClient = Mockito.mock(ApiClient.class);
    ApiResponse mockedResponse = Mockito.mock(ApiResponse.class);

    // Use reflection to inject mocked client
    Field apiClientField = Interactions.class.getDeclaredField("apiClient");
    apiClientField.setAccessible(true);
    apiClientField.set(client.interactions, mockedClient);

    String responseJson = "{\"id\":\"test-interaction-id\",\"status\":\"completed\"}";
    ResponseBody body = ResponseBody.create(responseJson, MediaType.get("application/json"));
    when(mockedResponse.getBody()).thenReturn(body);
    when(mockedClient.request(anyString(), anyString(), anyString(), any()))
        .thenReturn(mockedResponse);

    // Act
    client.interactions.get("test-interaction-id");

    // Assert - Verify request was made correctly
    verify(mockedClient).request(anyString(), anyString(), anyString(), any());
  }

  @Test
  public void testCancelConvenienceOverload() throws Exception {
    // Arrange
    Client client = Client.builder().apiKey("test-key").build();
    ApiClient mockedClient = Mockito.mock(ApiClient.class);
    ApiResponse mockedResponse = Mockito.mock(ApiResponse.class);

    // Use reflection to inject mocked client
    Field apiClientField = Interactions.class.getDeclaredField("apiClient");
    apiClientField.setAccessible(true);
    apiClientField.set(client.interactions, mockedClient);

    String responseJson = "{\"id\":\"test-interaction-id\",\"status\":\"cancelled\"}";
    ResponseBody body = ResponseBody.create(responseJson, MediaType.get("application/json"));
    when(mockedResponse.getBody()).thenReturn(body);
    when(mockedClient.request(anyString(), anyString(), anyString(), any()))
        .thenReturn(mockedResponse);

    // Act
    client.interactions.cancel("test-interaction-id");

    // Assert - Verify request was made correctly
    verify(mockedClient).request(anyString(), anyString(), anyString(), any());
  }

  @Test
  public void testDeleteConvenienceOverload() throws Exception {
    // Arrange
    Client client = Client.builder().apiKey("test-key").build();
    ApiClient mockedClient = Mockito.mock(ApiClient.class);
    ApiResponse mockedResponse = Mockito.mock(ApiResponse.class);

    // Use reflection to inject mocked client
    Field apiClientField = Interactions.class.getDeclaredField("apiClient");
    apiClientField.setAccessible(true);
    apiClientField.set(client.interactions, mockedClient);

    ResponseBody body = ResponseBody.create("", MediaType.get("application/json"));
    when(mockedResponse.getBody()).thenReturn(body);
    when(mockedClient.request(anyString(), anyString(), anyString(), any()))
        .thenReturn(mockedResponse);

    // Act
    client.interactions.delete("test-interaction-id");

    // Assert - Verify request was made correctly
    verify(mockedClient).request(anyString(), anyString(), anyString(), any());
  }
}
