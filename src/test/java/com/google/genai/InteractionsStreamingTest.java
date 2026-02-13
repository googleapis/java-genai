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
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.genai.types.interactions.CreateInteractionConfig;
import com.google.genai.types.interactions.GetInteractionConfig;
import com.google.genai.types.interactions.streaming.ContentDelta;
import com.google.genai.types.interactions.streaming.InteractionEvent;
import com.google.genai.types.interactions.streaming.InteractionSseEvent;
import com.google.genai.types.interactions.streaming.delta.TextDelta;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

/**
 * Unit tests for Interactions streaming methods (createStream and getStream).
 *
 * <p>Tests request building, streaming iteration, and stream resumption functionality.
 */
public class InteractionsStreamingTest {

  private static final String MODEL_ID = "gemini-2.5-flash";
  private static final String AGENT_ID = "deep-research-pro-preview-12-2025";
  private static final String INTERACTION_ID = "test-interaction-123";

  private ApiClient mockedClient;
  private ApiResponse mockedResponse;
  private ResponseBody mockedBody;
  private Client client;

  @BeforeEach
  void setUp() throws Exception {
    mockedClient = mock(ApiClient.class);
    mockedResponse = mock(ApiResponse.class);
    mockedBody = mock(ResponseBody.class);
    when(mockedResponse.getBody()).thenReturn(mockedBody);

    String apiKey = "test-key";
    client = Client.builder().apiKey(apiKey).vertexAI(false).build();

    // Use reflection to inject mocked client
    Field apiClientField = Interactions.class.getDeclaredField("apiClient");
    apiClientField.setAccessible(true);
    apiClientField.set(client.interactions, mockedClient);
  }

  private void mockSseResponse(String sseData) {
    InputStream inputStream = new ByteArrayInputStream(sseData.getBytes(StandardCharsets.UTF_8));
    when(mockedBody.byteStream()).thenReturn(inputStream);
    when(mockedClient.request(anyString(), anyString(), anyString(), any()))
        .thenReturn(mockedResponse);
  }

  // ==================== createStream Tests ====================

  @Test
  public void testCreateStreamWithModel() {
    String sseData =
        "data: {\"event_type\":\"interaction.start\",\"event_id\":\"e1\",\"interaction\":{\"id\":\"int-001\",\"status\":\"in_progress\"}}\n\n"
            + "data: {\"event_type\":\"content.delta\",\"event_id\":\"e2\",\"index\":0,\"delta\":{\"type\":\"text\",\"text\":\"Hello\"}}\n\n"
            + "data: {\"event_type\":\"interaction.complete\",\"event_id\":\"e3\",\"interaction\":{\"id\":\"int-001\",\"status\":\"completed\"}}\n\n"
            + "data: [DONE]\n";
    mockSseResponse(sseData);

    CreateInteractionConfig config =
        CreateInteractionConfig.builder().model(MODEL_ID).input("Test input").build();

    try (InteractionEventStream<InteractionSseEvent> stream =
        client.interactions.createStream(config)) {
      assertNotNull(stream);

      List<InteractionSseEvent> events = new ArrayList<>();
      for (InteractionSseEvent event : stream) {
        events.add(event);
      }

      assertEquals(3, events.size());
      assertInstanceOf(InteractionEvent.class, events.get(0));
      assertInstanceOf(ContentDelta.class, events.get(1));
      assertInstanceOf(InteractionEvent.class, events.get(2));
    }
  }

  @Test
  public void testCreateStreamWithAgent() {
    String sseData =
        "data: {\"event_type\":\"interaction.start\",\"event_id\":\"e1\",\"interaction\":{\"id\":\"int-002\",\"status\":\"in_progress\"}}\n\n"
            + "data: {\"event_type\":\"content.delta\",\"event_id\":\"e2\",\"index\":0,\"delta\":{\"type\":\"text\",\"text\":\"Research results\"}}\n\n"
            + "data: {\"event_type\":\"interaction.complete\",\"event_id\":\"e3\",\"interaction\":{\"id\":\"int-002\",\"status\":\"completed\"}}\n\n"
            + "data: [DONE]\n";
    mockSseResponse(sseData);

    CreateInteractionConfig config =
        CreateInteractionConfig.builder().agent(AGENT_ID).input("Research topic").build();

    try (InteractionEventStream<InteractionSseEvent> stream =
        client.interactions.createStream(config)) {
      assertNotNull(stream);

      List<InteractionSseEvent> events = new ArrayList<>();
      for (InteractionSseEvent event : stream) {
        events.add(event);
      }

      assertEquals(3, events.size());
    }
  }

  @Test
  public void testCreateStreamValidation_noModelOrAgent() {
    CreateInteractionConfig config =
        CreateInteractionConfig.builder().input("Test").build();

    assertThrows(
        IllegalArgumentException.class, () -> client.interactions.createStream(config));
  }

  @Test
  public void testCreateStreamValidation_bothModelAndAgent() {
    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model(MODEL_ID)
            .agent(AGENT_ID)
            .input("Test")
            .build();

    assertThrows(
        IllegalArgumentException.class, () -> client.interactions.createStream(config));
  }

  @Test
  public void testCreateStreamIterateEvents() {
    String sseData =
        "data: {\"event_type\":\"content.delta\",\"event_id\":\"e1\",\"index\":0,\"delta\":{\"type\":\"text\",\"text\":\"Hello \"}}\n\n"
            + "data: {\"event_type\":\"content.delta\",\"event_id\":\"e2\",\"index\":0,\"delta\":{\"type\":\"text\",\"text\":\"World!\"}}\n\n"
            + "data: [DONE]\n";
    mockSseResponse(sseData);

    CreateInteractionConfig config =
        CreateInteractionConfig.builder().model(MODEL_ID).input("Test").build();

    StringBuilder text = new StringBuilder();
    try (InteractionEventStream<InteractionSseEvent> stream =
        client.interactions.createStream(config)) {
      for (InteractionSseEvent event : stream) {
        if (event instanceof ContentDelta) {
          ContentDelta delta = (ContentDelta) event;
          if (delta.delta().isPresent() && delta.delta().get() instanceof TextDelta) {
            TextDelta textDelta = (TextDelta) delta.delta().get();
            if (textDelta.text().isPresent()) {
              text.append(textDelta.text().get());
            }
          }
        }
      }
    }

    assertEquals("Hello World!", text.toString());
  }

  @Test
  public void testCreateStreamHttpMethod() throws Exception {
    String sseData = "data: [DONE]\n";
    mockSseResponse(sseData);

    ArgumentCaptor<String> methodCaptor = ArgumentCaptor.forClass(String.class);
    when(mockedClient.request(methodCaptor.capture(), anyString(), anyString(), any()))
        .thenReturn(mockedResponse);

    CreateInteractionConfig config =
        CreateInteractionConfig.builder().model(MODEL_ID).input("Test").build();

    try (InteractionEventStream<InteractionSseEvent> stream =
        client.interactions.createStream(config)) {
      // Consume stream
      for (InteractionSseEvent ignored : stream) {}
    }

    assertEquals("post", methodCaptor.getValue());
  }

  // ==================== getStream Tests ====================

  @Test
  public void testGetStreamById() {
    String sseData =
        "data: {\"event_type\":\"interaction.start\",\"event_id\":\"e1\",\"interaction\":{\"id\":\"int-123\",\"status\":\"in_progress\"}}\n\n"
            + "data: {\"event_type\":\"content.delta\",\"event_id\":\"e2\",\"index\":0,\"delta\":{\"type\":\"text\",\"text\":\"Resumed content\"}}\n\n"
            + "data: {\"event_type\":\"interaction.complete\",\"event_id\":\"e3\",\"interaction\":{\"id\":\"int-123\",\"status\":\"completed\"}}\n\n"
            + "data: [DONE]\n";
    mockSseResponse(sseData);

    GetInteractionConfig config = GetInteractionConfig.builder().build();

    try (InteractionEventStream<InteractionSseEvent> stream =
        client.interactions.getStream(INTERACTION_ID, config)) {
      assertNotNull(stream);

      List<InteractionSseEvent> events = new ArrayList<>();
      for (InteractionSseEvent event : stream) {
        events.add(event);
      }

      assertEquals(3, events.size());
    }
  }

  @Test
  public void testGetStreamWithNullId() {
    GetInteractionConfig config = GetInteractionConfig.builder().build();

    assertThrows(
        IllegalArgumentException.class, () -> client.interactions.getStream(null, config));
  }

  @Test
  public void testGetStreamWithEmptyId() {
    GetInteractionConfig config = GetInteractionConfig.builder().build();

    assertThrows(
        IllegalArgumentException.class, () -> client.interactions.getStream("", config));
  }

  @Test
  public void testGetStreamPathFormat() throws Exception {
    String sseData = "data: [DONE]\n";
    mockSseResponse(sseData);

    ArgumentCaptor<String> pathCaptor = ArgumentCaptor.forClass(String.class);
    when(mockedClient.request(anyString(), pathCaptor.capture(), anyString(), any()))
        .thenReturn(mockedResponse);

    GetInteractionConfig config = GetInteractionConfig.builder().build();

    try (InteractionEventStream<InteractionSseEvent> stream =
        client.interactions.getStream(INTERACTION_ID, config)) {
      for (InteractionSseEvent ignored : stream) {}
    }

    String path = pathCaptor.getValue();
    assertNotNull(path);
    // Path should contain the interaction ID and stream parameter
    assertTrue(path.contains(INTERACTION_ID) || path.contains("interactions"));
    assertTrue(path.contains("stream=true"));
  }

  // ==================== Stream Resumption Tests ====================

  @Test
  public void testGetStreamWithLastEventId() throws Exception {
    String sseData =
        "data: {\"event_type\":\"content.delta\",\"event_id\":\"e5\",\"index\":0,\"delta\":{\"type\":\"text\",\"text\":\"Continued\"}}\n\n"
            + "data: {\"event_type\":\"interaction.complete\",\"event_id\":\"e6\",\"interaction\":{\"id\":\"int-123\",\"status\":\"completed\"}}\n\n"
            + "data: [DONE]\n";
    mockSseResponse(sseData);

    ArgumentCaptor<String> pathCaptor = ArgumentCaptor.forClass(String.class);
    when(mockedClient.request(anyString(), pathCaptor.capture(), anyString(), any()))
        .thenReturn(mockedResponse);

    GetInteractionConfig config =
        GetInteractionConfig.builder().lastEventId("e4").build();

    try (InteractionEventStream<InteractionSseEvent> stream =
        client.interactions.getStream(INTERACTION_ID, config)) {
      List<InteractionSseEvent> events = new ArrayList<>();
      for (InteractionSseEvent event : stream) {
        events.add(event);
      }

      assertEquals(2, events.size());
    }

    String path = pathCaptor.getValue();
    assertTrue(path.contains("last_event_id=e4"));
  }

  @Test
  public void testLastEventIdInQueryParam() throws Exception {
    String sseData = "data: [DONE]\n";
    mockSseResponse(sseData);

    ArgumentCaptor<String> pathCaptor = ArgumentCaptor.forClass(String.class);
    when(mockedClient.request(anyString(), pathCaptor.capture(), anyString(), any()))
        .thenReturn(mockedResponse);

    GetInteractionConfig config =
        GetInteractionConfig.builder().lastEventId("evt-123-456").build();

    try (InteractionEventStream<InteractionSseEvent> stream =
        client.interactions.getStream(INTERACTION_ID, config)) {
      for (InteractionSseEvent ignored : stream) {}
    }

    String path = pathCaptor.getValue();
    assertTrue(path.contains("last_event_id"));
    assertTrue(path.contains("evt-123-456") || path.contains("evt-123"));
  }

  @Test
  public void testResumptionContinuesFromEvent() {
    // Simulate resuming from event e3, should get e4, e5, e6
    String sseData =
        "data: {\"event_type\":\"content.delta\",\"event_id\":\"e4\",\"index\":0,\"delta\":{\"type\":\"text\",\"text\":\"More \"}}\n\n"
            + "data: {\"event_type\":\"content.delta\",\"event_id\":\"e5\",\"index\":0,\"delta\":{\"type\":\"text\",\"text\":\"content\"}}\n\n"
            + "data: {\"event_type\":\"content.stop\",\"event_id\":\"e6\",\"index\":0}\n\n"
            + "data: [DONE]\n";
    mockSseResponse(sseData);

    GetInteractionConfig config =
        GetInteractionConfig.builder().lastEventId("e3").build();

    try (InteractionEventStream<InteractionSseEvent> stream =
        client.interactions.getStream(INTERACTION_ID, config)) {
      List<InteractionSseEvent> events = new ArrayList<>();
      String lastEventId = null;

      for (InteractionSseEvent event : stream) {
        events.add(event);
        lastEventId = event.eventId().orElse(lastEventId);
      }

      assertEquals(3, events.size());
      assertEquals("e6", lastEventId);
    }
  }

  // ==================== Request Building Tests ====================

  @Test
  public void testBuildRequestForCreateStream() throws Exception {
    String sseData = "data: [DONE]\n";
    mockSseResponse(sseData);

    ArgumentCaptor<String> bodyCaptor = ArgumentCaptor.forClass(String.class);
    when(mockedClient.request(anyString(), anyString(), bodyCaptor.capture(), any()))
        .thenReturn(mockedResponse);

    CreateInteractionConfig config =
        CreateInteractionConfig.builder().model(MODEL_ID).input("Test input").build();

    try (InteractionEventStream<InteractionSseEvent> stream =
        client.interactions.createStream(config)) {
      for (InteractionSseEvent ignored : stream) {}
    }

    String body = bodyCaptor.getValue();
    assertNotNull(body);
    assertTrue(body.contains("\"model\""));
    assertTrue(body.contains(MODEL_ID));
    assertTrue(body.contains("\"stream\":true"));
  }

  @Test
  public void testBuildRequestForGetStream() throws Exception {
    String sseData = "data: [DONE]\n";
    mockSseResponse(sseData);

    ArgumentCaptor<String> methodCaptor = ArgumentCaptor.forClass(String.class);
    when(mockedClient.request(methodCaptor.capture(), anyString(), anyString(), any()))
        .thenReturn(mockedResponse);

    GetInteractionConfig config = GetInteractionConfig.builder().build();

    try (InteractionEventStream<InteractionSseEvent> stream =
        client.interactions.getStream(INTERACTION_ID, config)) {
      for (InteractionSseEvent ignored : stream) {}
    }

    assertEquals("get", methodCaptor.getValue());
  }

  @Test
  public void testStreamParamInRequest() throws Exception {
    String sseData = "data: [DONE]\n";
    mockSseResponse(sseData);

    ArgumentCaptor<String> bodyCaptor = ArgumentCaptor.forClass(String.class);
    when(mockedClient.request(anyString(), anyString(), bodyCaptor.capture(), any()))
        .thenReturn(mockedResponse);

    CreateInteractionConfig config =
        CreateInteractionConfig.builder().model(MODEL_ID).input("Test").build();

    try (InteractionEventStream<InteractionSseEvent> stream =
        client.interactions.createStream(config)) {
      for (InteractionSseEvent ignored : stream) {}
    }

    String body = bodyCaptor.getValue();
    assertTrue(body.contains("\"stream\":true"));
  }

  // ==================== API Version Tests ====================

  @Test
  public void testCreateStreamWithApiVersion() {
    String sseData =
        "data: {\"event_type\":\"interaction.complete\",\"interaction\":{\"id\":\"int-001\",\"status\":\"completed\"}}\n\n"
            + "data: [DONE]\n";
    mockSseResponse(sseData);

    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model(MODEL_ID)
            .input("Test")
            .apiVersion("v1alpha")
            .build();

    try (InteractionEventStream<InteractionSseEvent> stream =
        client.interactions.createStream(config)) {
      List<InteractionSseEvent> events = new ArrayList<>();
      for (InteractionSseEvent event : stream) {
        events.add(event);
      }
      assertEquals(1, events.size());
    }

    verify(mockedClient).request(anyString(), anyString(), anyString(), any());
  }

  @Test
  public void testGetStreamWithApiVersion() {
    String sseData =
        "data: {\"event_type\":\"interaction.complete\",\"interaction\":{\"id\":\"int-001\",\"status\":\"completed\"}}\n\n"
            + "data: [DONE]\n";
    mockSseResponse(sseData);

    GetInteractionConfig config =
        GetInteractionConfig.builder().apiVersion("v1beta").build();

    try (InteractionEventStream<InteractionSseEvent> stream =
        client.interactions.getStream(INTERACTION_ID, config)) {
      List<InteractionSseEvent> events = new ArrayList<>();
      for (InteractionSseEvent event : stream) {
        events.add(event);
      }
      assertEquals(1, events.size());
    }

    verify(mockedClient).request(anyString(), anyString(), anyString(), any());
  }

  // ==================== Edge Cases ====================

  @Test
  public void testEmptyStream() {
    String sseData = "data: [DONE]\n";
    mockSseResponse(sseData);

    CreateInteractionConfig config =
        CreateInteractionConfig.builder().model(MODEL_ID).input("Test").build();

    try (InteractionEventStream<InteractionSseEvent> stream =
        client.interactions.createStream(config)) {
      List<InteractionSseEvent> events = new ArrayList<>();
      for (InteractionSseEvent event : stream) {
        events.add(event);
      }
      assertTrue(events.isEmpty());
    }
  }

  @Test
  public void testStreamWithManyEvents() {
    StringBuilder sseBuilder = new StringBuilder();
    sseBuilder.append(
        "data: {\"event_type\":\"interaction.start\",\"interaction\":{\"id\":\"int-001\",\"status\":\"in_progress\"}}\n\n");
    sseBuilder.append("data: {\"event_type\":\"content.start\",\"index\":0}\n\n");

    // Add 50 content deltas
    for (int i = 0; i < 50; i++) {
      sseBuilder.append(
          String.format(
              "data: {\"event_type\":\"content.delta\",\"event_id\":\"e%d\",\"index\":0,\"delta\":{\"type\":\"text\",\"text\":\"chunk%d \"}}\n\n",
              i + 2, i));
    }

    sseBuilder.append("data: {\"event_type\":\"content.stop\",\"index\":0}\n\n");
    sseBuilder.append(
        "data: {\"event_type\":\"interaction.complete\",\"interaction\":{\"id\":\"int-001\",\"status\":\"completed\"}}\n\n");
    sseBuilder.append("data: [DONE]\n");

    mockSseResponse(sseBuilder.toString());

    CreateInteractionConfig config =
        CreateInteractionConfig.builder().model(MODEL_ID).input("Test").build();

    try (InteractionEventStream<InteractionSseEvent> stream =
        client.interactions.createStream(config)) {
      List<InteractionSseEvent> events = new ArrayList<>();
      for (InteractionSseEvent event : stream) {
        events.add(event);
      }

      // 1 start + 1 content.start + 50 deltas + 1 content.stop + 1 complete = 54
      assertEquals(54, events.size());
    }
  }

  @Test
  public void testGetStreamWithNullConfig() {
    String sseData =
        "data: {\"event_type\":\"interaction.complete\",\"interaction\":{\"id\":\"int-001\",\"status\":\"completed\"}}\n\n"
            + "data: [DONE]\n";
    mockSseResponse(sseData);

    // null config should be valid
    try (InteractionEventStream<InteractionSseEvent> stream =
        client.interactions.getStream(INTERACTION_ID, null)) {
      List<InteractionSseEvent> events = new ArrayList<>();
      for (InteractionSseEvent event : stream) {
        events.add(event);
      }
      assertEquals(1, events.size());
    }
  }

  // ==================== Validation Tests ====================

  @Test
  public void testCreateStreamValidation_agentConfigWithModel() {
    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model(MODEL_ID)
            .input("Test")
            .agentConfig(com.google.genai.types.interactions.DynamicAgentConfig.create())
            .build();

    assertThrows(
        IllegalArgumentException.class, () -> client.interactions.createStream(config));
  }

  @Test
  public void testCreateStreamValidation_generationConfigWithAgent() {
    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .agent("deep-research-pro-preview-12-2025")
            .input("Test")
            .generationConfig(
                com.google.genai.types.interactions.GenerationConfig.builder()
                    .temperature(0.7f)
                    .build())
            .build();

    assertThrows(
        IllegalArgumentException.class, () -> client.interactions.createStream(config));
  }

  // ==================== Response Modalities Tests ====================

  @Test
  public void testCreateStreamWithResponseModalities() throws Exception {
    String sseData =
        "data: {\"event_type\":\"interaction.complete\",\"interaction\":{\"id\":\"int-001\",\"status\":\"completed\"}}\n\n"
            + "data: [DONE]\n";
    mockSseResponse(sseData);

    ArgumentCaptor<String> bodyCaptor = ArgumentCaptor.forClass(String.class);
    when(mockedClient.request(anyString(), anyString(), bodyCaptor.capture(), any()))
        .thenReturn(mockedResponse);

    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model(MODEL_ID)
            .input("Test")
            .responseModalities("TEXT")
            .build();

    try (InteractionEventStream<InteractionSseEvent> stream =
        client.interactions.createStream(config)) {
      for (InteractionSseEvent ignored : stream) {}
    }

    String body = bodyCaptor.getValue();
    assertTrue(body.contains("response_modalities"));
  }
}
