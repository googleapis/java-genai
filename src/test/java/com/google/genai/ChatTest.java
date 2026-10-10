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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.genai.types.AutomaticFunctionCallingConfig;
import com.google.genai.types.Candidate;
import com.google.genai.types.Content;
import com.google.genai.types.FinishReason;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.GenerateContentResponseUsageMetadata;
import com.google.genai.types.Part;
import com.google.genai.types.Tool;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

public class ChatTest {

  ApiClient mockedClient;
  ApiResponse mockedResponse;
  ApiResponse mockedResponse1;
  ApiResponse mockedResponse2;
  ApiResponse mockedResponse3;
  ResponseBody mockedBody1;
  ResponseBody mockedBody2;
  ResponseBody mockedBody3;
  Client client;
  Chats chatSession;

  private static final String MODEL_ID = "gemini-2.5-flash";
  private static final String STREAMING_RESPONSE_CHUNK_1 = "Once upon ";
  private static final String STREAMING_RESPONSE_CHUNK_2 = "a time, in a land";
  private static final String STREAMING_RESPONSE_CHUNK_3 = " far, far away...";
  private static final String NON_STREAMING_RESPONSE = "This is a non-streaming response.";
  private static final String CONTINUATION_REPLAYS =
      "tests/models/generate_content_continuation_token/";
  private static final String LONG_PROMPT =
      "Write an exhaustive, multi-chapter textbook on compiler design that is around 40,000 tokens"
          + " long.";
  private static final byte[] CONTINUATION_TOKEN = "token".getBytes(StandardCharsets.UTF_8);

  /**
   * The model in the continuation recordings. It has no public name yet, so the recordings name it
   * this way.
   */
  private static String longDecodingModel(boolean vertexAI) {
    return vertexAI ? "test-model1" : "test-model2";
  }

  static int findTheatersCallCount = 0;

  public static String findTheaters(String movie, String location, String time) {
    findTheatersCallCount++;
    return "AMC Metreon 16, AMC Kabuki 8, AMC Theater 11";
  }

  GenerateContentResponse responseChunk1 =
      GenerateContentResponse.builder()
          .candidates(
              Candidate.builder()
                  .content(
                      Content.builder()
                          .parts(Part.builder().text(STREAMING_RESPONSE_CHUNK_1))
                          .role("model")))
          .build();

  GenerateContentResponse responseChunk2 =
      GenerateContentResponse.builder()
          .candidates(
              Candidate.builder()
                  .content(
                      Content.builder()
                          .parts(Part.builder().text(STREAMING_RESPONSE_CHUNK_2))
                          .role("model")))
          .build();

  GenerateContentResponse responseChunk3 =
      GenerateContentResponse.builder()
          .candidates(
              Candidate.builder()
                  .content(
                      Content.builder()
                          .parts(Part.builder().text(STREAMING_RESPONSE_CHUNK_3))
                          .role("model"))
                  .finishReason(FinishReason.Known.STOP))
          .usageMetadata(
              GenerateContentResponseUsageMetadata.builder()
                  .promptTokenCount(10)
                  .candidatesTokenCount(25)
                  .totalTokenCount(35))
          .build();

  String jsonChunk1 = responseChunk1.toJson();
  String jsonChunk2 = responseChunk2.toJson();
  String jsonChunk3 = responseChunk3.toJson();

  String streamData =
      "data: "
          + jsonChunk1
          + "\n\n"
          + "data: "
          + jsonChunk2
          + "\n\n"
          + "data: "
          + jsonChunk3
          + "\n\n";
  String streamData2 = "data: " + jsonChunk1 + "\n\n" + "data: " + jsonChunk2 + "\n\n";

  GenerateContentResponse nonStreamingResponse =
      GenerateContentResponse.builder()
          .candidates(
              Candidate.builder()
                  .content(
                      Content.builder()
                          .parts(Part.builder().text(NON_STREAMING_RESPONSE))
                          .role("model")))
          .build();
  String nonStreamData = nonStreamingResponse.toJson();

  @BeforeEach
  void setUp() {
    mockedClient = Mockito.mock(ApiClient.class);
    mockedResponse = Mockito.mock(ApiResponse.class);
    when(mockedClient.request(anyString(), anyString(), anyString(), any()))
        .thenReturn(mockedResponse);

    String apiKey = Optional.ofNullable(ApiClient.getApiKeyFromEnv()).orElse("api-key");
    client = Client.builder().apiKey(apiKey).vertexAI(false).build();

    mockedResponse1 = Mockito.mock(ApiResponse.class);
    mockedResponse2 = Mockito.mock(ApiResponse.class);
    mockedResponse3 = Mockito.mock(ApiResponse.class);
    mockedBody1 = Mockito.mock(ResponseBody.class);
    mockedBody2 = Mockito.mock(ResponseBody.class);
    mockedBody3 = Mockito.mock(ResponseBody.class);
  }

  @Test
  public void testCreateChatSession() throws Exception {

    // Make the apiClient field public so that it can be spied on in the tests. This is a
    // workaround for the fact that the ApiClient is a final class and cannot be spied on directly.
    Field apiClientField = Chats.class.getDeclaredField("apiClient");
    apiClientField.setAccessible(true);
    apiClientField.set(client.chats, mockedClient);

    Chat chat = client.chats.create(MODEL_ID, null);

    assertNotNull(chat);
  }

  @Test
  public void testGetHistory() throws Exception {

    ResponseBody content =
        ResponseBody.create(
            "{\"candidates\":[{\"content\":{\"parts\":[{\"text\":\"All Too Well, 10 Minute"
                + " Version\"}], \"role\":\"model\"}, \"finishReason\":\"STOP\"}]}}",
            MediaType.get("application/json"));
    when(mockedResponse.getBody()).thenReturn(content);
    // Make the apiClient field public so that it can be spied on in the tests. This is a
    // workaround for the fact that the ApiClient is a final class and cannot be spied on directly.
    Field apiClientField = Chats.class.getDeclaredField("apiClient");
    apiClientField.setAccessible(true);
    apiClientField.set(client.chats, mockedClient);

    Chat chatSession = client.chats.create(MODEL_ID, null);
    assert chatSession.getHistory(false).size() == 0;

    GenerateContentResponse response =
        chatSession.sendMessage("Which Taylor Swift song should I listen to next?", null);

    assert chatSession.getHistory(false).size() == 2;
  }

  @Test
  public void testGetHistoryWithAfc() throws Exception {
    String userMessage = "Find theaters for Oppenheimer.";
    Content userMessageContent = Content.fromParts(Part.fromText(userMessage));
    Content functionCallContent =
        Content.fromParts(
            Part.fromFunctionCall(
                "findTheaters",
                ImmutableMap.of(
                    "movie", "Oppenheimer", "location", "New York, NY", "time", "10:00 PM")));

    GenerateContentResponse functionResponse =
        GenerateContentResponse.builder()
            .candidates(
                Candidate.builder()
                    .content(functionCallContent)
                    .finishReason(FinishReason.Known.STOP))
            .build();

    GenerateContentResponse finalResponse =
        GenerateContentResponse.builder()
            .candidates(
                Candidate.builder()
                    .content(
                        Content.builder()
                            .role("model")
                            .parts(
                                Part.fromText(
                                    "I found AMC Metreon 16, AMC Kabuki 8, AMC Theater" + " 11")))
                    .finishReason(FinishReason.Known.STOP))
            .build();

    when(mockedClient.request(anyString(), anyString(), anyString(), any()))
        .thenReturn(mockedResponse1, mockedResponse2);
    ResponseBody functionResponseBody =
        ResponseBody.create(functionResponse.toJson(), MediaType.get("application/json"));
    when(mockedResponse1.getBody()).thenReturn(functionResponseBody);
    ResponseBody finalResponseBody =
        ResponseBody.create(finalResponse.toJson(), MediaType.get("application/json"));
    when(mockedResponse2.getBody()).thenReturn(finalResponseBody);

    Field apiClientField = Chats.class.getDeclaredField("apiClient");
    apiClientField.setAccessible(true);
    apiClientField.set(client.chats, mockedClient);
    Method method =
        ChatTest.class.getDeclaredMethod("findTheaters", String.class, String.class, String.class);
    GenerateContentConfig config =
        GenerateContentConfig.builder().tools(Tool.builder().functions(method)).build();
    Chat chatSession = client.chats.create(MODEL_ID, config);
    assert chatSession.getHistory(false).size() == 0;

    GenerateContentResponse response = chatSession.sendMessage(userMessage, null);

    assertNotNull(response.automaticFunctionCallingHistory().get());
    // user input, function call, function response
    assert response.automaticFunctionCallingHistory().get().size() == 3;
    assert chatSession.getHistory(false).size()
        == 4; // user input, function call, function response, model response
    assert chatSession.getHistory(true).size() == 4;
  }

  @Test
  public void testSpentAfcBudgetLeavesTheFunctionCallUnanswered() throws Exception {
    findTheatersCallCount = 0;
    String userMessage = "Find theaters for Oppenheimer.";
    Content functionCallContent =
        Content.fromParts(
            Part.fromFunctionCall(
                "findTheaters",
                ImmutableMap.of(
                    "movie", "Oppenheimer", "location", "New York, NY", "time", "10:00 PM")));

    GenerateContentResponse functionResponse =
        GenerateContentResponse.builder()
            .candidates(
                Candidate.builder()
                    .content(functionCallContent)
                    .finishReason(FinishReason.Known.STOP))
            .build();

    when(mockedClient.request(anyString(), anyString(), anyString(), any()))
        .thenReturn(mockedResponse1);
    ResponseBody functionResponseBody =
        ResponseBody.create(functionResponse.toJson(), MediaType.get("application/json"));
    when(mockedResponse1.getBody()).thenReturn(functionResponseBody);

    Field apiClientField = Chats.class.getDeclaredField("apiClient");
    apiClientField.setAccessible(true);
    apiClientField.set(client.chats, mockedClient);
    Method method =
        ChatTest.class.getDeclaredMethod("findTheaters", String.class, String.class, String.class);
    GenerateContentConfig config =
        GenerateContentConfig.builder()
            .tools(Tool.builder().functions(method))
            .automaticFunctionCalling(
                AutomaticFunctionCallingConfig.builder().maximumRemoteCalls(1))
            .build();
    Chat chatSession = client.chats.create(MODEL_ID, config);

    GenerateContentResponse response = chatSession.sendMessage(userMessage, null);

    // The one request the budget allows is spent being asked, leaving nothing to send a result
    // with, so the function is never called.
    assert findTheatersCallCount == 0;
    // The model's function call is recorded once, not twice, and the turn ends on it so the
    // caller can answer it themselves.
    assert chatSession.getHistory(false).size() == 2; // user input, function call
    assertNotNull(response.functionCalls());
    assert response.functionCalls().size() == 1;
  }

  @Test
  public void testMultiTurnChat() throws Exception {

    ResponseBody content1 =
        ResponseBody.create(
            "{\"candidates\":[{\"content\":{\"parts\":[{\"text\":\"I am doing"
                + " great!\"}],\"role\":\"model\"},\"finishReason\":\"STOP\"}]}}",
            MediaType.get("application/json"));
    ResponseBody content2 =
        ResponseBody.create(
            "{\"candidates\":[{\"content\":{\"parts\":[{\"text\":\"I am doing"
                + " great!\"}],\"role\":\"model\"},\"finishReason\":\"STOP\"}]}}",
            MediaType.get("application/json"));
    when(mockedResponse1.getBody()).thenReturn(content1);
    when(mockedResponse2.getBody()).thenReturn(content2);
    when(mockedClient.request(anyString(), anyString(), anyString(), any()))
        .thenReturn(mockedResponse1, mockedResponse2);

    // Make the apiClient field public so that it can be spied on in the tests. This is a
    // workaround for the fact that the ApiClient is a final class and cannot be spied on directly.
    Field apiClientField = Chats.class.getDeclaredField("apiClient");
    apiClientField.setAccessible(true);
    apiClientField.set(client.chats, mockedClient);

    Chat chatSession = client.chats.create(MODEL_ID);
    assert chatSession.getHistory(false).isEmpty();

    GenerateContentResponse response = chatSession.sendMessage("How are you?");
    assert chatSession.getHistory(false).size() == 2;

    GenerateContentResponse response2 = chatSession.sendMessage("What should I do today?", null);
    assert chatSession.getHistory(false).size() == 4;
    assert chatSession.getHistory(true).size() == 4;
  }

  @Test
  public void testChatWithConfig() throws Exception {

    ResponseBody content =
        ResponseBody.create(
            "{\"candidates\":[{\"content\":{\"parts\":[{\"text\":\"The Gouda Life\"}],"
                + " \"role\":\"model\"}}, {\"content\": {\"parts\":[{\"text\":\"Something"
                + " Bleu\"}], \"role\":\"model\"}}]}}",
            MediaType.get("application/json"));
    when(mockedResponse.getBody()).thenReturn(content);

    // Make the apiClient field public so that it can be spied on in the tests. This is a
    // workaround for the fact that the ApiClient is a final class and cannot be spied on directly.
    Field apiClientField = Chats.class.getDeclaredField("apiClient");
    apiClientField.setAccessible(true);
    apiClientField.set(client.chats, mockedClient);

    Chat chatSession = client.chats.create(MODEL_ID, null);
    GenerateContentConfig config = GenerateContentConfig.builder().candidateCount(2).build();

    GenerateContentResponse response =
        chatSession.sendMessage("Can you give me possible names for a cheese shop?", config);
    assert response.candidates().get().size() == 2;
  }

  @Test
  public void testInitConfigIsUsedWhenSendMessageConfigIsNull() throws Exception {

    ResponseBody content =
        ResponseBody.create(
            "{\"candidates\":[{\"content\":{\"parts\":[{\"text\":\"The Gouda Life\"}],"
                + " \"role\":\"model\"}}, {\"content\": {\"parts\":[{\"text\":\"Something"
                + " Bleu\"}], \"role\":\"model\"}}]}}",
            MediaType.get("application/json"));
    when(mockedResponse.getBody()).thenReturn(content);

    // Make the apiClient field public so that it can be spied on in the tests. This is a
    // workaround for the fact that the ApiClient is a final class and cannot be spied on directly.
    Field apiClientField = Chats.class.getDeclaredField("apiClient");
    apiClientField.setAccessible(true);
    apiClientField.set(client.chats, mockedClient);
    GenerateContentConfig config = GenerateContentConfig.builder().candidateCount(2).build();
    Chat chatSession = client.chats.create(MODEL_ID, config);

    GenerateContentResponse response =
        chatSession.sendMessage("Can you give me possible names for a cheese shop?", null);
    assert response.candidates().get().size() == 2;
  }

  @Test
  public void testSendMessageContent() throws Exception {

    ResponseBody content1 =
        ResponseBody.create(
            "{\"candidates\":[{\"content\":{\"parts\":[{\"text\":\"It's better with"
                + " cheddar\"}],\"role\":\"model\"},\"finishReason\":\"STOP\"}]}}",
            MediaType.get("application/json"));
    ResponseBody content2 =
        ResponseBody.create(
            "{\"candidates\":[{\"content\":{\"parts\":[{\"text\":\"It's better with"
                + " cheddar\"}],\"role\":\"model\"},\"finishReason\":\"STOP\"}]}}",
            MediaType.get("application/json"));

    when(mockedResponse1.getBody()).thenReturn(content1);
    when(mockedResponse2.getBody()).thenReturn(content2);
    when(mockedClient.request(anyString(), anyString(), anyString(), any()))
        .thenReturn(mockedResponse1, mockedResponse2);
    // Make the apiClient field public so that it can be spied on in the tests. This is a
    // workaround for the fact that the ApiClient is a final class and cannot be spied on directly.
    Field apiClientField = Chats.class.getDeclaredField("apiClient");
    apiClientField.setAccessible(true);
    apiClientField.set(client.chats, mockedClient);

    Chat chatSession = client.chats.create(MODEL_ID, null);

    List<Part> parts = new ArrayList<>();
    parts.add(Part.builder().text("Can you give me possible names for a cheese shop?").build());

    Content messageContent = Content.builder().role("user").parts(parts).build();

    GenerateContentResponse response = chatSession.sendMessage(messageContent);
    GenerateContentResponse response2WithConfig = chatSession.sendMessage(messageContent, null);

    assertNotNull(response);
    assertNotNull(response2WithConfig);
  }

  @Test
  public void testSendMessageContentList() throws Exception {

    ResponseBody content1 =
        ResponseBody.create(
            "{\"candidates\":[{\"content\":{\"parts\":[{\"text\":\"It's better with"
                + " cheddar\"}],\"role\":\"model\"},\"finishReason\":\"STOP\"}]}}",
            MediaType.get("application/json"));
    ResponseBody content2 =
        ResponseBody.create(
            "{\"candidates\":[{\"content\":{\"parts\":[{\"text\":\"It's better with"
                + " cheddar\"}],\"role\":\"model\"},\"finishReason\":\"STOP\"}]}}",
            MediaType.get("application/json"));

    when(mockedResponse1.getBody()).thenReturn(content1);
    when(mockedResponse2.getBody()).thenReturn(content2);
    when(mockedClient.request(anyString(), anyString(), anyString(), any()))
        .thenReturn(mockedResponse1, mockedResponse2);
    // Make the apiClient field public so that it can be spied on in the tests. This is a
    // workaround for the fact that the ApiClient is a final class and cannot be spied on directly.
    Field apiClientField = Chats.class.getDeclaredField("apiClient");
    apiClientField.setAccessible(true);
    apiClientField.set(client.chats, mockedClient);

    Chat chatSession = client.chats.create(MODEL_ID, null);

    List<Part> parts = new ArrayList<>();
    parts.add(Part.builder().text("Can you give me possible names for a cheese shop?").build());

    Content messageContent = Content.builder().role("user").parts(parts).build();
    List<Content> messageContentList = new ArrayList<>();
    messageContentList.add(messageContent);

    GenerateContentResponse response = chatSession.sendMessage(messageContentList);
    GenerateContentResponse response2WithConfig = chatSession.sendMessage(messageContentList, null);

    assertNotNull(response);
    assertNotNull(response2WithConfig);
  }

  @Test
  public void testUnexpectedFinishReasonDoesNotAddToCuratedHistory() throws Exception {
    ResponseBody content =
        ResponseBody.create(
            "{\"candidates\":[{\"content\":{\"parts\":[{\"text\":\"It's better with"
                + " cheddar\"}], \"role\":\"model\"}, \"finishReason\":\"BLOCKLIST\"}]}",
            MediaType.get("application/json"));
    when(mockedResponse.getBody()).thenReturn(content);

    // Make the apiClient field public so that it can be spied on in the tests. This is a
    // workaround for the fact that the ApiClient is a final class and cannot be spied on directly.
    Field apiClientField = Chats.class.getDeclaredField("apiClient");
    apiClientField.setAccessible(true);
    apiClientField.set(client.chats, mockedClient);
    Chat chatSession = client.chats.create(MODEL_ID);

    List<Part> emptyParts = new ArrayList<>();
    emptyParts.add(Part.fromText("Tell me something about cheese."));

    Content messageContent = Content.builder().role("user").parts(emptyParts).build();
    chatSession.sendMessage(messageContent);

    // Curated history should be empty because the response has a finish reason of BLOCKLIST.
    assert chatSession.getHistory(true).size() == 0;
    assert chatSession.getHistory(false).size() == 2;
  }

  @Test
  public void testInvalidRoleThrows() throws Exception {

    ResponseBody content =
        ResponseBody.create(
            "{\"candidates\":[{\"content\":{\"parts\":[{\"text\":\"The Gouda Life\"}],"
                + " \"role\":\"Mr. Cheese\"}}, {\"content\": {\"parts\":[{\"text\":\"Something"
                + " Bleu\"}], \"role\":\"model\"}}]}}",
            MediaType.get("application/json"));
    when(mockedResponse.getBody()).thenReturn(content);

    // Make the apiClient field public so that it can be spied on in the tests. This is a
    // workaround for the fact that the ApiClient is a final class and cannot be spied on directly.
    Field apiClientField = Chats.class.getDeclaredField("apiClient");
    apiClientField.setAccessible(true);
    apiClientField.set(client.chats, mockedClient);
    Chat chatSession = client.chats.create(MODEL_ID);

    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> chatSession.sendMessage("Can you give me possible names for a cheese shop?"));

    assert (exception
        .getMessage()
        .equals("The role of the message must be either 'user' or 'model'."));
  }

  @Test
  public void testInvalidHistoryThrows() throws Exception {

    ResponseBody content =
        ResponseBody.create(
            "{\"candidates\":[{\"content\":{\"parts\":[{\"text\":\"The Gouda Life\"}],"
                + " \"role\":\"model\"}}, {\"content\": {\"parts\":[{\"text\":\"Something"
                + " Bleu\"}], \"role\":\"model\"}}]}}",
            MediaType.get("application/json"));
    when(mockedResponse.getBody()).thenReturn(content);

    // Make the apiClient field public so that it can be spied on in the tests. This is a
    // workaround for the fact that the ApiClient is a final class and cannot be spied on directly.
    Field apiClientField = Chats.class.getDeclaredField("apiClient");
    apiClientField.setAccessible(true);
    apiClientField.set(client.chats, mockedClient);
    Chat chatSession = client.chats.create(MODEL_ID);

    List<Part> parts = new ArrayList<>();
    parts.add(Part.fromText("Can you give me possible names for a cheese shop?"));

    Content messageContent = Content.builder().role("Cheesemonger").parts(parts).build();

    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> chatSession.sendMessage(messageContent));

    assert (exception
        .getMessage()
        .equals("The first message in the history must be from the user."));
  }

  @Test
  public void testIterateOverResponseStream() throws Exception {

    Field apiClientField = Chats.class.getDeclaredField("apiClient");
    apiClientField.setAccessible(true);
    apiClientField.set(client.chats, mockedClient);

    Chat chatSession = client.chats.create(MODEL_ID, null);

    ResponseBody body1 = ResponseBody.create(streamData, MediaType.get("application/json"));
    ResponseBody body2 = ResponseBody.create(streamData2, MediaType.get("application/json"));
    ResponseBody body3 = ResponseBody.create(nonStreamData, MediaType.get("application/json"));

    when(mockedResponse1.getBody()).thenReturn(body1);
    when(mockedResponse2.getBody()).thenReturn(body2);
    when(mockedResponse3.getBody()).thenReturn(body3);
    when(mockedClient.request(anyString(), anyString(), anyString(), any()))
        .thenReturn(mockedResponse1, mockedResponse2, mockedResponse3);

    assert chatSession.getHistory(false).size() == 0;

    ResponseStream<GenerateContentResponse> responseStream =
        chatSession.sendMessageStream("Tell me a story.", null);

    assertNotNull(responseStream);

    int chunkCount = 0;
    // Iterate over the stream
    while (responseStream.iterator().hasNext()) {
      GenerateContentResponse responseChunk = responseStream.iterator().next();
      assertNotNull(responseChunk.text());
      if (chunkCount == 0) {
        assert (responseChunk.text().equals(STREAMING_RESPONSE_CHUNK_1));
      }
      chunkCount++;
    }

    assert chunkCount == 3;

    // History is updated after the stream is consumed
    assert chatSession.getHistory(false).size() == 4;
    ResponseStream<GenerateContentResponse> responseStream2 =
        chatSession.sendMessageStream("Tell me another story.", null);

    // Iterate over the second stream so we can add it to history
    while (responseStream2.iterator().hasNext()) {
      GenerateContentResponse responseChunk = responseStream2.iterator().next();
      assertNotNull(responseChunk);
      assertNotNull(responseChunk.text());
    }

    List<Content> historyAfterSecondStreamCall = chatSession.getHistory(false);
    assert historyAfterSecondStreamCall.size() == 7;
    // Second item in history should be the aggregated model response from the stream chunks
    assert historyAfterSecondStreamCall
        .get(1)
        .parts()
        .get()
        .get(0)
        .text()
        .orElse(null)
        .equals(STREAMING_RESPONSE_CHUNK_1);
    assert historyAfterSecondStreamCall
        .get(2)
        .parts()
        .get()
        .get(0)
        .text()
        .orElse(null)
        .equals(STREAMING_RESPONSE_CHUNK_2);
    assert historyAfterSecondStreamCall
        .get(3)
        .parts()
        .get()
        .get(0)
        .text()
        .orElse(null)
        .equals(STREAMING_RESPONSE_CHUNK_3);

    // Test that subsequent non-streaming sendMessage calls also include updated history
    chatSession.sendMessage("Tell me a third story.", null);
    List<Content> historyAfterThirdMessageCall = chatSession.getHistory(false);

    // Since this was a non-streaming call, the history should include the second aggregated stream
    // response as well as the new non-streaming response.
    assert historyAfterThirdMessageCall.size() == 9;
    assert historyAfterThirdMessageCall
        .get(8)
        .parts()
        .get()
        .get(0)
        .text()
        .orElse(null)
        .equals(NON_STREAMING_RESPONSE);
  }

  @Test
  public void testThrowsIfStreamResponseIsNotConsumed() throws Exception {
    /* Tests that an exception is thrown if the response stream is not consumed before calling
     * getHistory() or sendMessage* again. */

    Field apiClientField = Chats.class.getDeclaredField("apiClient");
    apiClientField.setAccessible(true);
    apiClientField.set(client.chats, mockedClient);

    Chat chatSession = client.chats.create(MODEL_ID, null);

    ResponseBody body1 = ResponseBody.create(streamData, MediaType.get("application/json"));
    ResponseBody body2 = ResponseBody.create(streamData2, MediaType.get("application/json"));
    when(mockedResponse1.getBody()).thenReturn(body1);
    when(mockedResponse2.getBody()).thenReturn(body2);
    when(mockedClient.request(anyString(), anyString(), anyString(), any()))
        .thenReturn(mockedResponse1, mockedResponse2);

    assert chatSession.getHistory(false).size() == 0;

    ResponseStream<GenerateContentResponse> responseStream =
        chatSession.sendMessageStream("Tell me a story.", null);

    IllegalStateException exception =
        assertThrows(IllegalStateException.class, () -> chatSession.getHistory(false));

    assert (exception.getMessage().equals("Response stream is not consumed"));

    IllegalStateException exception2 =
        assertThrows(
            IllegalStateException.class,
            () -> chatSession.sendMessageStream("Tell me another story."));
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  @EnabledIfEnvironmentVariable(
      named = "GOOGLE_GENAI_REPLAYS_DIRECTORY",
      matches = ".*genai/replays.*")
  public void testSendMessage_continuesByDefault(boolean vertexAI) {
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI,
            CONTINUATION_REPLAYS
                + "test_gc_without_afc_continuation_token_opt_in."
                + suffix
                + ".json");
    Chat chat =
        client.chats.create(longDecodingModel(vertexAI), GenerateContentConfig.builder().build());

    GenerateContentResponse response = chat.sendMessage(LONG_PROMPT);

    // The recording holds two requests, so the replay fails unless the chat continues the response.
    assertEquals(FinishReason.Known.STOP, response.finishReason().knownEnum());
    ImmutableList<Content> history = chat.getHistory(true);
    assertEquals(2, history.size());
    assertEquals(response.candidates().get().get(0).content().get(), history.get(1));
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  @EnabledIfEnvironmentVariable(
      named = "GOOGLE_GENAI_REPLAYS_DIRECTORY",
      matches = ".*genai/replays.*")
  public void testSendMessage_doesNotContinueWhenAutomaticContinuationIsFalse(boolean vertexAI) {
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI,
            CONTINUATION_REPLAYS + "test_gc_without_afc_continuation_token." + suffix + ".json");
    Chat chat =
        client.chats.create(
            longDecodingModel(vertexAI),
            GenerateContentConfig.builder().automaticContinuation(false).build());

    GenerateContentResponse response = chat.sendMessage(LONG_PROMPT);

    // The recording holds one request, so a continuation request would fail the replay.
    assertEquals(FinishReason.Known.CONTINUATION, response.finishReason().knownEnum());
    assertEquals(2, chat.getHistory(true).size());
  }

  @Test
  public void testSendMessage_continuesWithoutAConfigAndLeavesTheFirstRequestUnchanged() {
    ApiClient apiClient = Mockito.mock(ApiClient.class);
    when(apiClient.request(anyString(), anyString(), anyString(), any()))
        .thenReturn(
            jsonResponse(
                textResponse("Once upon ", FinishReason.Known.CONTINUATION, CONTINUATION_TOKEN)),
            jsonResponse(textResponse("a time.", FinishReason.Known.STOP, null)));
    Chat chat = new Chat(apiClient, MODEL_ID, null);

    GenerateContentResponse response = chat.sendMessage("Tell me a story.");

    assertEquals("Once upon a time.", response.text());
    List<String> bodies = sentBodies(apiClient, 2);
    JsonNode first = JsonSerializable.stringToJsonNode(bodies.get(0));
    JsonNode second = JsonSerializable.stringToJsonNode(bodies.get(1));
    // The first request is the one the chat sent before it continued responses.
    assertFalse(first.has("generationConfig"));
    assertFalse(first.has("continuationToken"));
    assertEquals(first.get("contents"), second.get("contents"));
    assertEquals(
        Base64.getEncoder().encodeToString(CONTINUATION_TOKEN),
        second.get("continuationToken").asText());
    ImmutableList<Content> history = chat.getHistory(true);
    assertEquals(2, history.size());
    assertEquals("Once upon a time.", history.get(1).text());
  }

  @Test
  public void testSendMessage_sendsMaxOutputTokensUntilTheServerStops() {
    // The server counts maxOutputTokens across the requests and ends with MAX_TOKENS once it is
    // spent, which ends the continuation.
    ApiClient apiClient = Mockito.mock(ApiClient.class);
    when(apiClient.request(anyString(), anyString(), anyString(), any()))
        .thenReturn(
            jsonResponse(
                textResponse("Once upon ", FinishReason.Known.CONTINUATION, CONTINUATION_TOKEN)),
            jsonResponse(
                textResponse("a time", FinishReason.Known.MAX_TOKENS, CONTINUATION_TOKEN)));
    Chat chat =
        new Chat(apiClient, MODEL_ID, GenerateContentConfig.builder().maxOutputTokens(10).build());

    GenerateContentResponse response = chat.sendMessage("Tell me a story.");

    assertEquals(FinishReason.Known.MAX_TOKENS, response.finishReason().knownEnum());
    for (String body : sentBodies(apiClient, 2)) {
      assertEquals(
          10,
          JsonSerializable.stringToJsonNode(body)
              .get("generationConfig")
              .get("maxOutputTokens")
              .asInt());
    }
  }

  @Test
  public void testSendMessageStream_continuesAndRecordsTheWholeTurn() {
    ApiClient apiClient = Mockito.mock(ApiClient.class);
    when(apiClient.request(anyString(), anyString(), anyString(), any()))
        .thenReturn(
            streamResponse(
                textResponse("Once upon ", null, null),
                textResponse("a time", FinishReason.Known.CONTINUATION, CONTINUATION_TOKEN)),
            streamResponse(textResponse(", the end.", FinishReason.Known.STOP, null)));
    Chat chat = new Chat(apiClient, MODEL_ID, null);

    List<String> texts = new ArrayList<>();
    try (ResponseStream<GenerateContentResponse> stream =
        chat.sendMessageStream("Tell me a story.")) {
      for (GenerateContentResponse chunk : stream) {
        texts.add(chunk.text());
        if (texts.size() == 2) {
          // The first request is read, but the turn goes on into the next one.
          assertThrows(IllegalStateException.class, () -> chat.getHistory(true));
        }
      }
    }

    assertEquals(ImmutableList.of("Once upon ", "a time", ", the end."), texts);
    List<String> bodies = sentBodies(apiClient, 2);
    assertTrue(JsonSerializable.stringToJsonNode(bodies.get(1)).has("continuationToken"));
    // The user's message, then the chunks of both requests.
    assertEquals(
        ImmutableList.of("Tell me a story.", "Once upon ", "a time", ", the end."),
        chat.getHistory(true).stream().map(Content::text).collect(Collectors.toList()));
  }

  @Test
  public void testSendMessageStream_doesNotContinueWhenAutomaticContinuationIsFalse() {
    ApiClient apiClient = Mockito.mock(ApiClient.class);
    when(apiClient.request(anyString(), anyString(), anyString(), any()))
        .thenReturn(
            streamResponse(
                textResponse("Once upon ", FinishReason.Known.CONTINUATION, CONTINUATION_TOKEN)));
    Chat chat = new Chat(apiClient, MODEL_ID, null);

    List<String> texts = new ArrayList<>();
    try (ResponseStream<GenerateContentResponse> stream =
        chat.sendMessageStream(
            "Tell me a story.",
            GenerateContentConfig.builder().automaticContinuation(false).build())) {
      for (GenerateContentResponse chunk : stream) {
        texts.add(chunk.text());
      }
    }

    assertEquals(ImmutableList.of("Once upon "), texts);
    sentBodies(apiClient, 1);
    assertEquals(
        ImmutableList.of("Tell me a story.", "Once upon "),
        chat.getHistory(true).stream().map(Content::text).collect(Collectors.toList()));
  }

  private static GenerateContentResponse textResponse(
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

  private static ApiResponse jsonResponse(GenerateContentResponse response) {
    return new FakeApiResponse(
        Headers.of(), ResponseBody.create(response.toJson(), MediaType.get("application/json")));
  }

  private static ApiResponse streamResponse(GenerateContentResponse... chunks) {
    StringBuilder sse = new StringBuilder();
    for (GenerateContentResponse chunk : chunks) {
      sse.append("data: ").append(chunk.toJson()).append("\n\n");
    }
    return new FakeApiResponse(
        Headers.of(), ResponseBody.create(sse.toString(), MediaType.get("text/event-stream")));
  }

  /** The bodies of the requests sent through {@code apiClient}, checking how many there were. */
  private static List<String> sentBodies(ApiClient apiClient, int expectedRequests) {
    ArgumentCaptor<String> bodies = ArgumentCaptor.forClass(String.class);
    verify(apiClient, times(expectedRequests))
        .request(anyString(), anyString(), bodies.capture(), any());
    return bodies.getAllValues();
  }
}
