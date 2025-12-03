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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.google.common.collect.ImmutableMap;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

  public static String findTheaters(String movie, String location, String time) {
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
      "data: " + jsonChunk1 + "\n" + "data: " + jsonChunk2 + "\n" + "data: " + jsonChunk3 + "\n";
  String streamData2 = "data: " + jsonChunk1 + "\n" + "data: " + jsonChunk2 + "\n";

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
}
