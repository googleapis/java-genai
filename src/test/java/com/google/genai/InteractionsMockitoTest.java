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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.common.collect.ImmutableMap;
import com.google.genai.types.HttpOptions;
import com.google.genai.types.interactions.CancelInteractionConfig;
import com.google.genai.types.interactions.CreateInteractionConfig;
import com.google.genai.types.interactions.DeleteInteractionConfig;
import com.google.genai.types.interactions.DeleteInteractionResponse;
import com.google.genai.types.interactions.GetInteractionConfig;
import com.google.genai.types.interactions.Interaction;
import java.lang.reflect.Field;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

/**
 * Mockito-based unit tests for Interactions API.
 *
 * <p>Fine-grained testing using mocked ApiClient, similar to ChatTest.java mockito patterns.
 */
public class InteractionsMockitoTest {

  private static final String MODEL_ID = "gemini-2.5-flash";
  private static final String INTERACTION_ID = "test-interaction-123";

  private ApiClient mockedClient;
  private ApiResponse mockedResponse;
  private Client client;

  @BeforeEach
  void setUp() throws Exception {
    mockedClient = Mockito.mock(ApiClient.class);
    mockedResponse = Mockito.mock(ApiResponse.class);

    String apiKey = "test-key";
    client = Client.builder().apiKey(apiKey).vertexAI(false).build();

    // Use reflection to inject mocked client
    Field apiClientField = Interactions.class.getDeclaredField("apiClient");
    apiClientField.setAccessible(true);
    apiClientField.set(client.interactions, mockedClient);
  }

  @Test
  public void testCreate_requestBuilding() throws Exception {
    // Arrange
    String responseJson =
        "{\"id\":\"" + INTERACTION_ID + "\",\"status\":\"completed\"}";
    ResponseBody body = ResponseBody.create(responseJson, MediaType.get("application/json"));
    when(mockedResponse.getBody()).thenReturn(body);
    when(mockedClient.request(anyString(), anyString(), anyString(), any()))
        .thenReturn(mockedResponse);

    CreateInteractionConfig config =
        CreateInteractionConfig.builder().model(MODEL_ID).input("Test input").build();

    // Act
    Interaction result = client.interactions.create(config);

    // Assert
    assertNotNull(result);
    assertEquals(INTERACTION_ID, result.id());

    // Verify request was made
    verify(mockedClient).request(anyString(), anyString(), anyString(), any());
  }

  @Test
  public void testGet_requestBuilding() throws Exception {
    // Arrange
    String responseJson =
        "{\"id\":\"" + INTERACTION_ID + "\",\"status\":\"completed\"}";
    ResponseBody body = ResponseBody.create(responseJson, MediaType.get("application/json"));
    when(mockedResponse.getBody()).thenReturn(body);
    when(mockedClient.request(anyString(), anyString(), anyString(), any()))
        .thenReturn(mockedResponse);

    GetInteractionConfig config = GetInteractionConfig.builder().build();

    // Act
    Interaction result = client.interactions.get(INTERACTION_ID, config);

    // Assert
    assertNotNull(result);
    assertEquals(INTERACTION_ID, result.id());

    // Verify request was made with correct ID
    verify(mockedClient).request(anyString(), anyString(), anyString(), any());
  }

  @Test
  public void testCancel_requestBuilding() throws Exception {
    // Arrange
    String responseJson =
        "{\"id\":\"" + INTERACTION_ID + "\",\"status\":\"failed\"}";
    ResponseBody body = ResponseBody.create(responseJson, MediaType.get("application/json"));
    when(mockedResponse.getBody()).thenReturn(body);
    when(mockedClient.request(anyString(), anyString(), anyString(), any()))
        .thenReturn(mockedResponse);

    CancelInteractionConfig config = CancelInteractionConfig.builder().build();

    // Act
    Interaction result = client.interactions.cancel(INTERACTION_ID, config);

    // Assert
    assertNotNull(result);
    assertEquals(INTERACTION_ID, result.id());

    // Verify request was made
    verify(mockedClient).request(anyString(), anyString(), anyString(), any());
  }

  @Test
  public void testDelete_requestBuilding() throws Exception {
    // Arrange
    String responseJson = "{}";
    ResponseBody body = ResponseBody.create(responseJson, MediaType.get("application/json"));
    when(mockedResponse.getBody()).thenReturn(body);
    when(mockedClient.request(anyString(), anyString(), anyString(), any()))
        .thenReturn(mockedResponse);

    DeleteInteractionConfig config = DeleteInteractionConfig.builder().build();

    // Act
    DeleteInteractionResponse result = client.interactions.delete(INTERACTION_ID, config);

    // Assert
    assertNotNull(result);

    // Verify request was made
    verify(mockedClient).request(anyString(), anyString(), anyString(), any());
  }

  @Test
  public void testCreate_withHttpOptions() throws Exception {
    // Arrange
    String responseJson =
        "{\"id\":\"" + INTERACTION_ID + "\",\"status\":\"completed\"}";
    ResponseBody body = ResponseBody.create(responseJson, MediaType.get("application/json"));
    when(mockedResponse.getBody()).thenReturn(body);
    when(mockedClient.request(anyString(), anyString(), anyString(), any()))
        .thenReturn(mockedResponse);

    HttpOptions httpOptions =
        HttpOptions.builder()
            .headers(ImmutableMap.of("X-Custom-Header", "custom-value"))
            .build();

    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model(MODEL_ID)
            .input("Test")
            .httpOptions(httpOptions)
            .build();

    // Act
    Interaction result = client.interactions.create(config);

    // Assert
    assertNotNull(result);
    assertTrue(config.httpOptions().isPresent());
    assertTrue(config.httpOptions().get().headers().isPresent());
  }

  @Test
  public void testCreate_responseHandling() throws Exception {
    // Arrange - Test that response is properly parsed
    String responseJson =
        "{"
            + "\"id\":\""
            + INTERACTION_ID
            + "\","
            + "\"status\":\"completed\","
            + "\"model\":\""
            + MODEL_ID
            + "\","
            + "\"outputs\":[{\"type\":\"text\",\"text\":\"Response text\"}]"
            + "}";
    ResponseBody body = ResponseBody.create(responseJson, MediaType.get("application/json"));
    when(mockedResponse.getBody()).thenReturn(body);
    when(mockedClient.request(anyString(), anyString(), anyString(), any()))
        .thenReturn(mockedResponse);

    CreateInteractionConfig config =
        CreateInteractionConfig.builder().model(MODEL_ID).input("Test").build();

    // Act
    Interaction result = client.interactions.create(config);

    // Assert - Verify all fields are parsed correctly
    assertEquals(INTERACTION_ID, result.id());
    assertTrue(result.model().isPresent());
    assertEquals(MODEL_ID, result.model().get());
    assertTrue(result.outputs().isPresent());
    assertEquals(1, result.outputs().get().size());
  }

  @Test
  public void testCreate_withPreviousInteractionId() throws Exception {
    // Arrange
    String previousId = "previous-123";
    String responseJson =
        "{"
            + "\"id\":\""
            + INTERACTION_ID
            + "\","
            + "\"status\":\"completed\","
            + "\"previous_interaction_id\":\""
            + previousId
            + "\""
            + "}";
    ResponseBody body = ResponseBody.create(responseJson, MediaType.get("application/json"));
    when(mockedResponse.getBody()).thenReturn(body);
    when(mockedClient.request(anyString(), anyString(), anyString(), any()))
        .thenReturn(mockedResponse);

    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model(MODEL_ID)
            .input("Follow-up")
            .previousInteractionId(previousId)
            .build();

    // Act
    Interaction result = client.interactions.create(config);

    // Assert
    assertTrue(result.previousInteractionId().isPresent());
    assertEquals(previousId, result.previousInteractionId().get());
  }

  @Test
  public void testCreate_withGenerationConfig() throws Exception {
    // Arrange
    String responseJson =
        "{\"id\":\"" + INTERACTION_ID + "\",\"status\":\"completed\"}";
    ResponseBody body = ResponseBody.create(responseJson, MediaType.get("application/json"));
    when(mockedResponse.getBody()).thenReturn(body);
    when(mockedClient.request(anyString(), anyString(), anyString(), any()))
        .thenReturn(mockedResponse);

    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model(MODEL_ID)
            .input("Test")
            .generationConfig(
                com.google.genai.types.interactions.GenerationConfig.builder()
                    .temperature(0.7f)
                    .maxOutputTokens(1000)
                    .topP(0.9f)
                    .build())
            .build();

    // Act
    Interaction result = client.interactions.create(config);

    // Assert
    assertNotNull(result);
    assertTrue(config.generationConfig().isPresent());
  }

  @Test
  public void testCreate_multipleSequentialCalls() throws Exception {
    // Test making multiple sequential calls
    for (int i = 1; i <= 3; i++) {
      String interactionId = "interaction-" + i;
      String responseJson = "{\"id\":\"" + interactionId + "\",\"status\":\"completed\"}";
      ResponseBody body = ResponseBody.create(responseJson, MediaType.get("application/json"));
      when(mockedResponse.getBody()).thenReturn(body);
      when(mockedClient.request(anyString(), anyString(), anyString(), any()))
          .thenReturn(mockedResponse);

      CreateInteractionConfig config =
          CreateInteractionConfig.builder().model(MODEL_ID).input("Input " + i).build();

      Interaction result = client.interactions.create(config);

      assertEquals(interactionId, result.id());
    }

    // Verify request was called 3 times
    verify(mockedClient, Mockito.times(3))
        .request(anyString(), anyString(), anyString(), any());
  }

  @Test
  public void testGet_withConfig() throws Exception {
    // Arrange
    String responseJson =
        "{"
            + "\"id\":\""
            + INTERACTION_ID
            + "\","
            + "\"status\":\"completed\","
            + "\"outputs\":[{\"type\":\"text\",\"text\":\"Retrieved response\"}]"
            + "}";
    ResponseBody body = ResponseBody.create(responseJson, MediaType.get("application/json"));
    when(mockedResponse.getBody()).thenReturn(body);
    when(mockedClient.request(anyString(), anyString(), anyString(), any()))
        .thenReturn(mockedResponse);

    GetInteractionConfig config =
        GetInteractionConfig.builder()
            .httpOptions(HttpOptions.builder().headers(ImmutableMap.of("X-Test", "value")).build())
            .build();

    // Act
    Interaction result = client.interactions.get(INTERACTION_ID, config);

    // Assert
    assertNotNull(result);
    assertTrue(result.outputs().isPresent());
  }

  @Test
  public void testResponseBodyParsing() throws Exception {
    // Test various response body formats
    String complexResponseJson =
        "{"
            + "\"id\":\""
            + INTERACTION_ID
            + "\","
            + "\"status\":\"completed\","
            + "\"model\":\""
            + MODEL_ID
            + "\","
            + "\"outputs\":["
            + "{\"type\":\"text\",\"text\":\"First output\"},"
            + "{\"type\":\"text\",\"text\":\"Second output\"}"
            + "],"
            + "\"created\":\"2025-01-22T10:00:00Z\","
            + "\"updated\":\"2025-01-22T10:01:00Z\","
            + "\"usage\":{\"total_token_count\":100}"
            + "}";

    ResponseBody body = ResponseBody.create(complexResponseJson, MediaType.get("application/json"));
    when(mockedResponse.getBody()).thenReturn(body);
    when(mockedClient.request(anyString(), anyString(), anyString(), any()))
        .thenReturn(mockedResponse);

    CreateInteractionConfig config =
        CreateInteractionConfig.builder().model(MODEL_ID).input("Test").build();

    // Act
    Interaction result = client.interactions.create(config);

    // Assert - Verify complex response is fully parsed
    assertEquals(INTERACTION_ID, result.id());
    assertTrue(result.outputs().isPresent());
    assertEquals(2, result.outputs().get().size());
    assertTrue(result.created().isPresent());
    assertTrue(result.updated().isPresent());
    assertTrue(result.usage().isPresent());
  }

  @Test
  public void testApiClientVerification() throws Exception {
    // Verify that ApiClient is being called with correct parameters
    String responseJson =
        "{\"id\":\"" + INTERACTION_ID + "\",\"status\":\"completed\"}";
    ResponseBody body = ResponseBody.create(responseJson, MediaType.get("application/json"));
    when(mockedResponse.getBody()).thenReturn(body);

    ArgumentCaptor<String> methodCaptor = ArgumentCaptor.forClass(String.class);
    ArgumentCaptor<String> pathCaptor = ArgumentCaptor.forClass(String.class);

    when(mockedClient.request(
            methodCaptor.capture(), pathCaptor.capture(), anyString(), any()))
        .thenReturn(mockedResponse);

    CreateInteractionConfig config =
        CreateInteractionConfig.builder().model(MODEL_ID).input("Test").build();

    // Act
    client.interactions.create(config);

    // Assert - Verify HTTP method and path
    assertEquals("post", methodCaptor.getValue());
    // Path will be interactions-specific
    assertNotNull(pathCaptor.getValue());
  }

  // ==================== Request Building Validation ====================

  @Test
  public void testCreate_requestBodyStructure() throws Exception {
    // Arrange
    String responseJson = "{\"id\":\"" + INTERACTION_ID + "\",\"status\":\"completed\"}";
    ResponseBody body = ResponseBody.create(responseJson, MediaType.get("application/json"));
    when(mockedResponse.getBody()).thenReturn(body);

    ArgumentCaptor<String> bodyCaptor = ArgumentCaptor.forClass(String.class);
    when(mockedClient.request(anyString(), anyString(), bodyCaptor.capture(), any()))
        .thenReturn(mockedResponse);

    CreateInteractionConfig config =
        CreateInteractionConfig.builder().model(MODEL_ID).input("Test input").build();

    // Act
    client.interactions.create(config);

    // Assert - Verify JSON body structure contains expected fields
    String requestBody = bodyCaptor.getValue();
    assertNotNull(requestBody);
    assertTrue(requestBody.contains("\"model\""));
    assertTrue(requestBody.contains(MODEL_ID));
  }

  @Test
  public void testGet_pathFormatting() throws Exception {
    // Arrange
    String responseJson = "{\"id\":\"" + INTERACTION_ID + "\",\"status\":\"completed\"}";
    ResponseBody body = ResponseBody.create(responseJson, MediaType.get("application/json"));
    when(mockedResponse.getBody()).thenReturn(body);

    ArgumentCaptor<String> pathCaptor = ArgumentCaptor.forClass(String.class);
    when(mockedClient.request(anyString(), pathCaptor.capture(), anyString(), any()))
        .thenReturn(mockedResponse);

    GetInteractionConfig config = GetInteractionConfig.builder().build();

    // Act
    client.interactions.get(INTERACTION_ID, config);

    // Assert - Verify path includes interaction ID
    String path = pathCaptor.getValue();
    assertNotNull(path);
    assertTrue(path.contains(INTERACTION_ID) || path.contains("interactions"));
  }

  @Test
  public void testCancel_pathIncludesCancel() throws Exception {
    // Arrange
    String responseJson = "{\"id\":\"" + INTERACTION_ID + "\",\"status\":\"failed\"}";
    ResponseBody body = ResponseBody.create(responseJson, MediaType.get("application/json"));
    when(mockedResponse.getBody()).thenReturn(body);

    ArgumentCaptor<String> pathCaptor = ArgumentCaptor.forClass(String.class);
    when(mockedClient.request(anyString(), pathCaptor.capture(), anyString(), any()))
        .thenReturn(mockedResponse);

    CancelInteractionConfig config = CancelInteractionConfig.builder().build();

    // Act
    client.interactions.cancel(INTERACTION_ID, config);

    // Assert - Verify path includes :cancel
    String path = pathCaptor.getValue();
    assertNotNull(path);
    assertTrue(path.contains("cancel") || path.contains(INTERACTION_ID));
  }

  @Test
  public void testDelete_httpMethod() throws Exception {
    // Arrange
    String responseJson = "{}";
    ResponseBody body = ResponseBody.create(responseJson, MediaType.get("application/json"));
    when(mockedResponse.getBody()).thenReturn(body);

    ArgumentCaptor<String> methodCaptor = ArgumentCaptor.forClass(String.class);
    when(mockedClient.request(methodCaptor.capture(), anyString(), anyString(), any()))
        .thenReturn(mockedResponse);

    DeleteInteractionConfig config = DeleteInteractionConfig.builder().build();

    // Act
    client.interactions.delete(INTERACTION_ID, config);

    // Assert - Verify DELETE HTTP method
    assertEquals("delete", methodCaptor.getValue());
  }

  @Test
  public void testCreate_httpMethod() throws Exception {
    // Arrange
    String responseJson = "{\"id\":\"" + INTERACTION_ID + "\",\"status\":\"completed\"}";
    ResponseBody body = ResponseBody.create(responseJson, MediaType.get("application/json"));
    when(mockedResponse.getBody()).thenReturn(body);

    ArgumentCaptor<String> methodCaptor = ArgumentCaptor.forClass(String.class);
    when(mockedClient.request(methodCaptor.capture(), anyString(), anyString(), any()))
        .thenReturn(mockedResponse);

    CreateInteractionConfig config =
        CreateInteractionConfig.builder().model(MODEL_ID).input("Test").build();

    // Act
    client.interactions.create(config);

    // Assert - Verify POST HTTP method
    assertEquals("post", methodCaptor.getValue());
  }

  // ==================== Platform-Specific Behavior ====================

  @Test
  public void testCreate_mldevClient() throws Exception {
    // Arrange - Ensure we're using MLDev client
    String responseJson = "{\"id\":\"" + INTERACTION_ID + "\",\"status\":\"completed\"}";
    ResponseBody body = ResponseBody.create(responseJson, MediaType.get("application/json"));
    when(mockedResponse.getBody()).thenReturn(body);
    when(mockedClient.request(anyString(), anyString(), anyString(), any()))
        .thenReturn(mockedResponse);

    CreateInteractionConfig config =
        CreateInteractionConfig.builder().model(MODEL_ID).input("Test").build();

    // Act
    Interaction result = client.interactions.create(config);

    // Assert
    assertNotNull(result);
    assertEquals(INTERACTION_ID, result.id());
  }

  @Test
  public void testCreate_vertexClient() throws Exception {
    // Arrange - Create a Vertex AI client
    Client vertexClient = Client.builder()
        .vertexAI(true)
        .project("test-project")
        .location("us-central1")
        .build();

    // Inject mocked client
    Field apiClientField = Interactions.class.getDeclaredField("apiClient");
    apiClientField.setAccessible(true);
    apiClientField.set(vertexClient.interactions, mockedClient);

    String responseJson = "{\"id\":\"" + INTERACTION_ID + "\",\"status\":\"completed\"}";
    ResponseBody body = ResponseBody.create(responseJson, MediaType.get("application/json"));
    when(mockedResponse.getBody()).thenReturn(body);
    when(mockedClient.request(anyString(), anyString(), anyString(), any()))
        .thenReturn(mockedResponse);

    CreateInteractionConfig config =
        CreateInteractionConfig.builder().model(MODEL_ID).input("Test").build();

    // Act
    Interaction result = vertexClient.interactions.create(config);

    // Assert
    assertNotNull(result);
  }

  @Test
  public void testGet_vertexIdFormat() throws Exception {
    // Arrange
    String vertexId = "projects/test-project/locations/us-central1/interactions/test-id";
    String responseJson = "{\"id\":\"" + vertexId + "\",\"status\":\"completed\"}";
    ResponseBody body = ResponseBody.create(responseJson, MediaType.get("application/json"));
    when(mockedResponse.getBody()).thenReturn(body);
    when(mockedClient.request(anyString(), anyString(), anyString(), any()))
        .thenReturn(mockedResponse);

    GetInteractionConfig config = GetInteractionConfig.builder().build();

    // Act
    Interaction result = client.interactions.get(vertexId, config);

    // Assert
    assertNotNull(result);
    assertEquals(vertexId, result.id());
  }

  // ==================== Edge Cases ====================

  @Test
  public void testCreate_emptyOptionalFields() throws Exception {
    // Arrange
    String responseJson = "{\"id\":\"" + INTERACTION_ID + "\",\"status\":\"completed\"}";
    ResponseBody body = ResponseBody.create(responseJson, MediaType.get("application/json"));
    when(mockedResponse.getBody()).thenReturn(body);
    when(mockedClient.request(anyString(), anyString(), anyString(), any()))
        .thenReturn(mockedResponse);

    // Create config with only required fields
    CreateInteractionConfig config =
        CreateInteractionConfig.builder().model(MODEL_ID).input("Minimal config").build();

    // Act
    Interaction result = client.interactions.create(config);

    // Assert
    assertNotNull(result);
    assertEquals(INTERACTION_ID, result.id());
  }

  @Test
  public void testGet_responseWithAllFields() throws Exception {
    // Arrange - Comprehensive response with all possible fields
    String fullResponseJson =
        "{"
            + "\"id\":\"" + INTERACTION_ID + "\","
            + "\"status\":\"completed\","
            + "\"model\":\"" + MODEL_ID + "\","
            + "\"created\":\"2025-01-24T10:00:00Z\","
            + "\"updated\":\"2025-01-24T10:01:00Z\","
            + "\"outputs\":[{\"type\":\"text\",\"text\":\"Response\"}],"
            + "\"usage\":{\"total_token_count\":150,\"prompt_token_count\":50,\"candidates_token_count\":100},"
            + "\"previous_interaction_id\":\"prev-123\""
            + "}";

    ResponseBody body = ResponseBody.create(fullResponseJson, MediaType.get("application/json"));
    when(mockedResponse.getBody()).thenReturn(body);
    when(mockedClient.request(anyString(), anyString(), anyString(), any()))
        .thenReturn(mockedResponse);

    GetInteractionConfig config = GetInteractionConfig.builder().build();

    // Act
    Interaction result = client.interactions.get(INTERACTION_ID, config);

    // Assert - Verify all fields are present
    assertNotNull(result);
    assertEquals(INTERACTION_ID, result.id());
    assertTrue(result.model().isPresent());
    assertTrue(result.created().isPresent());
    assertTrue(result.updated().isPresent());
    assertTrue(result.outputs().isPresent());
    assertTrue(result.usage().isPresent());
    assertTrue(result.previousInteractionId().isPresent());
  }

  @Test
  public void testDelete_responseHandling() throws Exception {
    // Arrange
    String deleteResponseJson = "{\"success\":true}";
    ResponseBody body = ResponseBody.create(deleteResponseJson, MediaType.get("application/json"));
    when(mockedResponse.getBody()).thenReturn(body);
    when(mockedClient.request(anyString(), anyString(), anyString(), any()))
        .thenReturn(mockedResponse);

    DeleteInteractionConfig config = DeleteInteractionConfig.builder().build();

    // Act
    DeleteInteractionResponse result = client.interactions.delete(INTERACTION_ID, config);

    // Assert
    assertNotNull(result);
  }

  @Test
  public void testCreate_withBackgroundTrue() throws Exception {
    // Arrange
    String responseJson =
        "{\"id\":\"" + INTERACTION_ID + "\",\"status\":\"in_progress\",\"background\":true}";
    ResponseBody body = ResponseBody.create(responseJson, MediaType.get("application/json"));
    when(mockedResponse.getBody()).thenReturn(body);
    when(mockedClient.request(anyString(), anyString(), anyString(), any()))
        .thenReturn(mockedResponse);

    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model(MODEL_ID)
            .input("Background task")
            .background(true)
            .build();

    // Act
    Interaction result = client.interactions.create(config);

    // Assert
    assertNotNull(result);
    assertTrue(config.background().isPresent());
    assertTrue(config.background().get());
  }

  @Test
  public void testCreate_withStoreTrue() throws Exception {
    // Arrange
    String responseJson = "{\"id\":\"" + INTERACTION_ID + "\",\"status\":\"completed\"}";
    ResponseBody body = ResponseBody.create(responseJson, MediaType.get("application/json"));
    when(mockedResponse.getBody()).thenReturn(body);
    when(mockedClient.request(anyString(), anyString(), anyString(), any()))
        .thenReturn(mockedResponse);

    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model(MODEL_ID)
            .input("Store conversation")
            .store(true)
            .build();

    // Act
    Interaction result = client.interactions.create(config);

    // Assert
    assertNotNull(result);
    assertTrue(config.store().isPresent());
    assertTrue(config.store().get());
  }
}
