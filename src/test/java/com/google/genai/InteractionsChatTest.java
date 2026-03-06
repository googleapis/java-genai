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
import static org.mockito.Mockito.when;

import com.google.genai.types.interactions.CreateInteractionConfig;
import com.google.genai.types.interactions.Interaction;
import com.google.genai.types.interactions.InteractionStatus;
import com.google.genai.types.interactions.content.Content;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * Chat-like multi-turn conversation tests for Interactions API.
 *
 * <p>Mirrors the pattern from ChatTest.java but specific to Interactions previousId-based
 * conversations.
 */
public class InteractionsChatTest {

  private static final String MODEL_ID = "gemini-2.5-flash";
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
  public void testSingleInteraction() throws Exception {
    // Arrange
    String responseJson =
        "{"
            + "\"id\":\"interaction-1\","
            + "\"status\":\"completed\","
            + "\"outputs\":[{\"type\":\"text\",\"text\":\"Hello!\"}]"
            + "}";
    ResponseBody body = ResponseBody.create(responseJson, MediaType.get("application/json"));
    when(mockedResponse.getBody()).thenReturn(body);
    when(mockedClient.request(anyString(), anyString(), anyString(), any()))
        .thenReturn(mockedResponse);

    CreateInteractionConfig config =
        CreateInteractionConfig.builder().model(MODEL_ID).input("Hi").build();

    // Act
    Interaction interaction = client.interactions.create(config);

    // Assert
    assertNotNull(interaction);
    assertEquals("interaction-1", interaction.id());
    assertEquals(new InteractionStatus(InteractionStatus.Known.COMPLETED), interaction.status());
  }

  @Test
  public void testTwoTurnConversation() throws Exception {
    // Arrange - First interaction
    String response1Json =
        "{"
            + "\"id\":\"interaction-1\","
            + "\"status\":\"completed\","
            + "\"outputs\":[{\"type\":\"text\",\"text\":\"Hello! How can I help?\"}]"
            + "}";
    ResponseBody body1 = ResponseBody.create(response1Json, MediaType.get("application/json"));
    ApiResponse mockedResponse1 = createMockedResponse(body1);

    // Arrange - Second interaction
    String response2Json =
        "{"
            + "\"id\":\"interaction-2\","
            + "\"status\":\"completed\","
            + "\"previous_interaction_id\":\"interaction-1\","
            + "\"outputs\":[{\"type\":\"text\",\"text\":\"The answer is 4.\"}]"
            + "}";
    ResponseBody body2 = ResponseBody.create(response2Json, MediaType.get("application/json"));
    ApiResponse mockedResponse2 = createMockedResponse(body2);

    when(mockedClient.request(anyString(), anyString(), anyString(), any()))
        .thenReturn(mockedResponse1, mockedResponse2);

    // Act - First turn
    CreateInteractionConfig config1 =
        CreateInteractionConfig.builder().model(MODEL_ID).input("Hello").build();
    Interaction interaction1 = client.interactions.create(config1);

    // Act - Second turn with previousId
    CreateInteractionConfig config2 =
        CreateInteractionConfig.builder()
            .model(MODEL_ID)
            .input("What is 2+2?")
            .previousInteractionId(interaction1.id())
            .build();
    Interaction interaction2 = client.interactions.create(config2);

    // Assert
    assertEquals("interaction-1", interaction1.id());
    assertEquals("interaction-2", interaction2.id());
    assertTrue(interaction2.previousInteractionId().isPresent());
    assertEquals("interaction-1", interaction2.previousInteractionId().get());
  }

  @Test
  public void testMultiTurnConversationChain() throws Exception {
    // Create a conversation chain with 3 interactions
    List<Interaction> interactions = new ArrayList<>();
    String previousId = null;

    for (int i = 1; i <= 3; i++) {
      String interactionId = "interaction-" + i;
      String prevIdField = previousId != null ? ",\"previous_interaction_id\":\"" + previousId + "\"" : "";
      String responseJson =
          "{"
              + "\"id\":\""
              + interactionId
              + "\","
              + "\"status\":\"completed\""
              + prevIdField
              + ","
              + "\"outputs\":[{\"type\":\"text\",\"text\":\"Response "
              + i
              + "\"}]"
              + "}";

      ResponseBody body = ResponseBody.create(responseJson, MediaType.get("application/json"));
      ApiResponse mockedResp = createMockedResponse(body);
      when(mockedClient.request(anyString(), anyString(), anyString(), any()))
          .thenReturn(mockedResp);

      CreateInteractionConfig.Builder configBuilder =
          CreateInteractionConfig.builder().model(MODEL_ID).input("Question " + i);

      if (previousId != null) {
        configBuilder.previousInteractionId(previousId);
      }

      Interaction interaction = client.interactions.create(configBuilder.build());
      interactions.add(interaction);
      previousId = interaction.id();
    }

    // Assert conversation chain
    assertEquals(3, interactions.size());
    assertEquals("interaction-1", interactions.get(0).id());
    assertEquals("interaction-2", interactions.get(1).id());
    assertEquals("interaction-3", interactions.get(2).id());

    // Verify previousId chain
    assertTrue(interactions.get(1).previousInteractionId().isPresent());
    assertEquals("interaction-1", interactions.get(1).previousInteractionId().get());

    assertTrue(interactions.get(2).previousInteractionId().isPresent());
    assertEquals("interaction-2", interactions.get(2).previousInteractionId().get());
  }

  @Test
  public void testConversationWithSameModel() throws Exception {
    // Ensure all interactions in a conversation use the same model
    String response1Json =
        "{\"id\":\"int-1\",\"status\":\"completed\",\"model\":\""
            + MODEL_ID
            + "\",\"outputs\":[{\"type\":\"text\",\"text\":\"Response 1\"}]}";
    String response2Json =
        "{\"id\":\"int-2\",\"status\":\"completed\",\"model\":\""
            + MODEL_ID
            + "\",\"previous_interaction_id\":\"int-1\",\"outputs\":[{\"type\":\"text\",\"text\":\"Response 2\"}]}";

    ApiResponse resp1 = createMockedResponse(ResponseBody.create(response1Json, MediaType.get("application/json")));
    ApiResponse resp2 = createMockedResponse(ResponseBody.create(response2Json, MediaType.get("application/json")));

    when(mockedClient.request(anyString(), anyString(), anyString(), any()))
        .thenReturn(resp1, resp2);

    CreateInteractionConfig config1 =
        CreateInteractionConfig.builder().model(MODEL_ID).input("First").build();
    Interaction int1 = client.interactions.create(config1);

    CreateInteractionConfig config2 =
        CreateInteractionConfig.builder()
            .model(MODEL_ID)
            .input("Second")
            .previousInteractionId(int1.id())
            .build();
    Interaction int2 = client.interactions.create(config2);

    // Both should have the same model
    assertEquals(MODEL_ID, int1.model().get());
    assertEquals(MODEL_ID, int2.model().get());
  }

  @Test
  public void testClearPreviousIdStartsNewConversation() {
    // Test that clearing previousId starts a new conversation thread
    CreateInteractionConfig.Builder builder =
        CreateInteractionConfig.builder()
            .model(MODEL_ID)
            .input("test")
            .previousInteractionId("some-id");

    // Clear the previousId
    CreateInteractionConfig config = builder.clearPreviousInteractionId().build();

    // Assert previousId is not present
    assertTrue(!config.previousInteractionId().isPresent());
  }

  @Test
  public void testConversationContextPreservation() throws Exception {
    // Simulate a conversation where context is preserved
    String response1Json =
        "{\"id\":\"ctx-1\",\"status\":\"completed\",\"outputs\":[{\"type\":\"text\",\"text\":\"My name is Claude.\"}]}";
    String response2Json =
        "{\"id\":\"ctx-2\",\"status\":\"completed\",\"previous_interaction_id\":\"ctx-1\",\"outputs\":[{\"type\":\"text\",\"text\":\"I told you, I'm Claude.\"}]}";

    ApiResponse ctx1 = createMockedResponse(ResponseBody.create(response1Json, MediaType.get("application/json")));
    ApiResponse ctx2 = createMockedResponse(ResponseBody.create(response2Json, MediaType.get("application/json")));

    when(mockedClient.request(anyString(), anyString(), anyString(), any()))
        .thenReturn(ctx1, ctx2);

    CreateInteractionConfig config1 =
        CreateInteractionConfig.builder().model(MODEL_ID).input("What is your name?").build();
    Interaction int1 = client.interactions.create(config1);

    CreateInteractionConfig config2 =
        CreateInteractionConfig.builder()
            .model(MODEL_ID)
            .input("What did you just say?")
            .previousInteractionId(int1.id())
            .build();
    Interaction int2 = client.interactions.create(config2);

    // The second response references the first (context preserved)
    assertTrue(int2.previousInteractionId().isPresent());
    assertEquals(int1.id(), int2.previousInteractionId().get());
  }

  @Test
  public void testInProgressInteractionInConversation() throws Exception {
    // Test conversation where one interaction is still in progress
    String response1Json =
        "{\"id\":\"prog-1\",\"status\":\"completed\",\"outputs\":[{\"type\":\"text\",\"text\":\"First response\"}]}";
    String response2Json =
        "{\"id\":\"prog-2\",\"status\":\"in_progress\",\"previous_interaction_id\":\"prog-1\"}";

    ApiResponse prog1 = createMockedResponse(ResponseBody.create(response1Json, MediaType.get("application/json")));
    ApiResponse prog2 = createMockedResponse(ResponseBody.create(response2Json, MediaType.get("application/json")));

    when(mockedClient.request(anyString(), anyString(), anyString(), any()))
        .thenReturn(prog1, prog2);

    CreateInteractionConfig config1 =
        CreateInteractionConfig.builder().model(MODEL_ID).input("Question 1").build();
    Interaction int1 = client.interactions.create(config1);

    CreateInteractionConfig config2 =
        CreateInteractionConfig.builder()
            .model(MODEL_ID)
            .input("Question 2")
            .previousInteractionId(int1.id())
            .build();
    Interaction int2 = client.interactions.create(config2);

    // First is completed, second is in progress
    assertEquals(new InteractionStatus(InteractionStatus.Known.COMPLETED), int1.status());
    assertEquals(new InteractionStatus(InteractionStatus.Known.IN_PROGRESS), int2.status());
  }

  @Test
  public void testBranchingConversations() throws Exception {
    // Test creating multiple follow-ups from the same interaction (branching)
    String baseJson =
        "{\"id\":\"base\",\"status\":\"completed\",\"outputs\":[{\"type\":\"text\",\"text\":\"Base response\"}]}";
    String branch1Json =
        "{\"id\":\"branch-1\",\"status\":\"completed\",\"previous_interaction_id\":\"base\",\"outputs\":[{\"type\":\"text\",\"text\":\"Branch 1\"}]}";
    String branch2Json =
        "{\"id\":\"branch-2\",\"status\":\"completed\",\"previous_interaction_id\":\"base\",\"outputs\":[{\"type\":\"text\",\"text\":\"Branch 2\"}]}";

    ApiResponse baseResp = createMockedResponse(ResponseBody.create(baseJson, MediaType.get("application/json")));
    ApiResponse branch1Resp = createMockedResponse(ResponseBody.create(branch1Json, MediaType.get("application/json")));
    ApiResponse branch2Resp = createMockedResponse(ResponseBody.create(branch2Json, MediaType.get("application/json")));

    when(mockedClient.request(anyString(), anyString(), anyString(), any()))
        .thenReturn(baseResp, branch1Resp, branch2Resp);

    // Create base interaction
    CreateInteractionConfig baseConfig =
        CreateInteractionConfig.builder().model(MODEL_ID).input("Base question").build();
    Interaction base = client.interactions.create(baseConfig);

    // Create two branches from the same base
    CreateInteractionConfig branch1Config =
        CreateInteractionConfig.builder()
            .model(MODEL_ID)
            .input("Follow-up A")
            .previousInteractionId(base.id())
            .build();
    Interaction branch1 = client.interactions.create(branch1Config);

    CreateInteractionConfig branch2Config =
        CreateInteractionConfig.builder()
            .model(MODEL_ID)
            .input("Follow-up B")
            .previousInteractionId(base.id())
            .build();
    Interaction branch2 = client.interactions.create(branch2Config);

    // Both branches reference the same parent
    assertEquals(base.id(), branch1.previousInteractionId().get());
    assertEquals(base.id(), branch2.previousInteractionId().get());
  }

  @Test
  public void testLongConversationChain() throws Exception {
    // Test a long conversation chain (5+ turns)
    int conversationLength = 5;
    List<Interaction> chain = new ArrayList<>();
    String previousId = null;

    for (int i = 1; i <= conversationLength; i++) {
      String id = "long-" + i;
      String prevField = previousId != null ? ",\"previous_interaction_id\":\"" + previousId + "\"" : "";
      String json =
          "{\"id\":\"" + id + "\",\"status\":\"completed\"" + prevField + ",\"outputs\":[{\"type\":\"text\",\"text\":\"Turn " + i + "\"}]}";

      ApiResponse turnResp = createMockedResponse(ResponseBody.create(json, MediaType.get("application/json")));
      when(mockedClient.request(anyString(), anyString(), anyString(), any()))
          .thenReturn(turnResp);

      CreateInteractionConfig.Builder builder =
          CreateInteractionConfig.builder().model(MODEL_ID).input("Message " + i);
      if (previousId != null) {
        builder.previousInteractionId(previousId);
      }

      Interaction interaction = client.interactions.create(builder.build());
      chain.add(interaction);
      previousId = interaction.id();
    }

    // Verify chain integrity
    assertEquals(conversationLength, chain.size());
    for (int i = 1; i < conversationLength; i++) {
      assertTrue(chain.get(i).previousInteractionId().isPresent());
      assertEquals(chain.get(i - 1).id(), chain.get(i).previousInteractionId().get());
    }
  }

  @Test
  public void testConversationHistoryTracking() throws Exception {
    // Manually track conversation history (similar to Chat.getHistory)
    List<Interaction> history = new ArrayList<>();

    String json1 =
        "{\"id\":\"hist-1\",\"status\":\"completed\",\"outputs\":[{\"type\":\"text\",\"text\":\"Response 1\"}]}";
    String json2 =
        "{\"id\":\"hist-2\",\"status\":\"completed\",\"previous_interaction_id\":\"hist-1\",\"outputs\":[{\"type\":\"text\",\"text\":\"Response 2\"}]}";
    String json3 =
        "{\"id\":\"hist-3\",\"status\":\"completed\",\"previous_interaction_id\":\"hist-2\",\"outputs\":[{\"type\":\"text\",\"text\":\"Response 3\"}]}";

    ApiResponse hist1 = createMockedResponse(ResponseBody.create(json1, MediaType.get("application/json")));
    ApiResponse hist2 = createMockedResponse(ResponseBody.create(json2, MediaType.get("application/json")));
    ApiResponse hist3 = createMockedResponse(ResponseBody.create(json3, MediaType.get("application/json")));

    when(mockedClient.request(anyString(), anyString(), anyString(), any()))
        .thenReturn(hist1, hist2, hist3);

    // Build conversation and track history
    CreateInteractionConfig config1 =
        CreateInteractionConfig.builder().model(MODEL_ID).input("Turn 1").build();
    Interaction int1 = client.interactions.create(config1);
    history.add(int1);

    CreateInteractionConfig config2 =
        CreateInteractionConfig.builder()
            .model(MODEL_ID)
            .input("Turn 2")
            .previousInteractionId(int1.id())
            .build();
    Interaction int2 = client.interactions.create(config2);
    history.add(int2);

    CreateInteractionConfig config3 =
        CreateInteractionConfig.builder()
            .model(MODEL_ID)
            .input("Turn 3")
            .previousInteractionId(int2.id())
            .build();
    Interaction int3 = client.interactions.create(config3);
    history.add(int3);

    // Verify history
    assertEquals(3, history.size());
    assertEquals("hist-1", history.get(0).id());
    assertEquals("hist-2", history.get(1).id());
    assertEquals("hist-3", history.get(2).id());
  }

  // Helper method
  private ApiResponse createMockedResponse(ResponseBody body) {
    ApiResponse response = Mockito.mock(ApiResponse.class);
    when(response.getBody()).thenReturn(body);
    return response;
  }
}
