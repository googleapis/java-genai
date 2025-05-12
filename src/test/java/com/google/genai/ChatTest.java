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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.google.genai.types.Candidate;
import com.google.genai.types.Content;
import com.google.genai.types.FinishReason;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.GenerateContentResponseUsageMetadata;
import com.google.genai.types.Part;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ChatTest {

  ApiClient mockedClient;
  ApiResponse mockedResponse;
  HttpEntity mockedEntity;
  ApiResponse mockedResponse1;
  ApiResponse mockedResponse2;
  ApiResponse mockedResponse3;
  HttpEntity mockedEntity1;
  HttpEntity mockedEntity2;
  HttpEntity mockedEntity3;
  Client client;
  Chats chatSession;

  private static final String STREAMING_RESPONSE_CHUNK_1 = "Once upon ";
  private static final String STREAMING_RESPONSE_CHUNK_2 = "a time, in a land";
  private static final String STREAMING_RESPONSE_CHUNK_3 = " far, far away...";
  private static final String NON_STREAMING_RESPONSE = "This is a non-streaming response.";
  private Iterator<GenerateContentResponse> mockStreamIterator;

  GenerateContentResponse responseChunk1 =
      GenerateContentResponse.builder()
          .candidates(
              Arrays.asList(
                  Candidate.builder()
                      .content(
                          Content.builder()
                              .parts(
                                  Arrays.asList(
                                      Part.builder().text(STREAMING_RESPONSE_CHUNK_1).build()))
                              .role("model")
                              .build())
                      .build()))
          .build();

  GenerateContentResponse responseChunk2 =
      GenerateContentResponse.builder()
          .candidates(
              Arrays.asList(
                  Candidate.builder()
                      .content(
                          Content.builder()
                              .parts(
                                  Arrays.asList(
                                      Part.builder().text(STREAMING_RESPONSE_CHUNK_2).build()))
                              .role("model")
                              .build())
                      .build()))
          .build();

  GenerateContentResponse responseChunk3 =
      GenerateContentResponse.builder()
          .candidates(
              Arrays.asList(
                  Candidate.builder()
                      .content(
                          Content.builder()
                              .parts(
                                  Arrays.asList(
                                      Part.builder().text(STREAMING_RESPONSE_CHUNK_3).build()))
                              .role("model")
                              .build())
                      .finishReason(FinishReason.Known.STOP)
                      .build()))
          .usageMetadata(
              GenerateContentResponseUsageMetadata.builder()
                  .promptTokenCount(10)
                  .candidatesTokenCount(25)
                  .totalTokenCount(35)
                  .build())
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
              Arrays.asList(
                  Candidate.builder()
                      .content(
                          Content.builder()
                              .parts(
                                  Arrays.asList(
                                      Part.builder().text(NON_STREAMING_RESPONSE).build()))
                              .role("model")
                              .build())
                      .build()))
          .build();
  String nonStreamData = nonStreamingResponse.toJson();

  @BeforeEach
  void setUp() {
    mockedClient = Mockito.mock(ApiClient.class);
    mockedResponse = Mockito.mock(ApiResponse.class);
    when(mockedClient.request(anyString(), anyString(), anyString(), any()))
        .thenReturn(mockedResponse);
    mockedEntity = Mockito.mock(HttpEntity.class);

    client = Client.builder().build();

    mockedResponse1 = Mockito.mock(ApiResponse.class);
    mockedResponse2 = Mockito.mock(ApiResponse.class);
    mockedResponse3 = Mockito.mock(ApiResponse.class);
    mockedEntity1 = Mockito.mock(HttpEntity.class);
    mockedEntity2 = Mockito.mock(HttpEntity.class);
    mockedEntity3 = Mockito.mock(HttpEntity.class);

    client = Client.builder().build();

    mockStreamIterator = Mockito.mock(ResponseStream.ResponseStreamIterator.class);
  }

  @Test
  public void testCreateChatSession() throws Exception {

    // Make the apiClient field public so that it can be spied on in the tests. This is a
    // workaround for the fact that the ApiClient is a final class and cannot be spied on directly.
    Field apiClientField = Chats.class.getDeclaredField("apiClient");
    apiClientField.setAccessible(true);
    apiClientField.set(client.chats, mockedClient);

    Chat chat = client.chats.create("gemini-2.0-flash-exp", null);

    assertNotNull(chat);
  }

  @Test
  public void testGetHistory() throws Exception {

    StringEntity content =
        new StringEntity(
            "{\"candidates\":[{\"content\":{\"parts\":[{\"text\":\"All Too Well, 10 Minute"
                + " Version\"}], \"role\":\"model\"}}]}}");
    when(mockedResponse.getEntity()).thenReturn(content);
    // Make the apiClient field public so that it can be spied on in the tests. This is a
    // workaround for the fact that the ApiClient is a final class and cannot be spied on directly.
    Field apiClientField = Chats.class.getDeclaredField("apiClient");
    apiClientField.setAccessible(true);
    apiClientField.set(client.chats, mockedClient);

    Chat chatSession = client.chats.create("gemini-2.0-flash-exp", null);
    assert chatSession.getHistory(false).size() == 0;

    GenerateContentResponse response =
        chatSession.sendMessage("Which Taylor Swift song should I listen to next?", null);

    assert chatSession.getHistory(false).size() == 2;
  }

  @Test
  public void testMultiTurnChat() throws Exception {

    StringEntity content =
        new StringEntity(
            "{\"candidates\":[{\"content\":{\"parts\":[{\"text\":\"I am doing great!\"}],"
                + " \"role\":\"model\"}}]}}");
    when(mockedResponse.getEntity()).thenReturn(content);

    // Make the apiClient field public so that it can be spied on in the tests. This is a
    // workaround for the fact that the ApiClient is a final class and cannot be spied on directly.
    Field apiClientField = Chats.class.getDeclaredField("apiClient");
    apiClientField.setAccessible(true);
    apiClientField.set(client.chats, mockedClient);

    Chat chatSession = client.chats.create("gemini-2.0-flash-exp");
    assert chatSession.getHistory(false).isEmpty();

    GenerateContentResponse response = chatSession.sendMessage("How are you?");
    assert chatSession.getHistory(false).size() == 2;

    GenerateContentResponse response2 = chatSession.sendMessage("What should I do today?", null);
    assert chatSession.getHistory(false).size() == 4;
    assert chatSession.getHistory(true).size() == 4;
  }

  @Test
  public void testChatWithConfig() throws Exception {

    StringEntity content =
        new StringEntity(
            "{\"candidates\":[{\"content\":{\"parts\":[{\"text\":\"The Gouda Life\"}],"
                + " \"role\":\"model\"}}, {\"content\": {\"parts\":[{\"text\":\"Something"
                + " Bleu\"}], \"role\":\"model\"}}]}}");
    when(mockedResponse.getEntity()).thenReturn(content);

    // Make the apiClient field public so that it can be spied on in the tests. This is a
    // workaround for the fact that the ApiClient is a final class and cannot be spied on directly.
    Field apiClientField = Chats.class.getDeclaredField("apiClient");
    apiClientField.setAccessible(true);
    apiClientField.set(client.chats, mockedClient);

    Chat chatSession = client.chats.create("gemini-2.0-flash-exp", null);
    GenerateContentConfig config = GenerateContentConfig.builder().candidateCount(2).build();

    GenerateContentResponse response =
        chatSession.sendMessage("Can you give me possible names for a cheese shop?", config);
    assert response.candidates().get().size() == 2;
  }

  @Test
  public void testInitConfigIsUsedWhenSendMessageConfigIsNull() throws Exception {

    StringEntity content =
        new StringEntity(
            "{\"candidates\":[{\"content\":{\"parts\":[{\"text\":\"The Gouda Life\"}],"
                + " \"role\":\"model\"}}, {\"content\": {\"parts\":[{\"text\":\"Something"
                + " Bleu\"}], \"role\":\"model\"}}]}}");
    when(mockedResponse.getEntity()).thenReturn(content);

    // Make the apiClient field public so that it can be spied on in the tests. This is a
    // workaround for the fact that the ApiClient is a final class and cannot be spied on directly.
    Field apiClientField = Chats.class.getDeclaredField("apiClient");
    apiClientField.setAccessible(true);
    apiClientField.set(client.chats, mockedClient);
    GenerateContentConfig config = GenerateContentConfig.builder().candidateCount(2).build();
    Chat chatSession = client.chats.create("gemini-2.0-flash-exp", config);

    GenerateContentResponse response =
        chatSession.sendMessage("Can you give me possible names for a cheese shop?", null);
    assert response.candidates().get().size() == 2;
  }

  @Test
  public void testSendMessageContent() throws Exception {

    StringEntity content =
        new StringEntity(
            "{\"candidates\":[{\"content\":{\"parts\":[{\"text\":\"It's better with"
                + " cheddar\"}], \"role\":\"model\"}}]}}");
    when(mockedResponse.getEntity()).thenReturn(content);
    // Make the apiClient field public so that it can be spied on in the tests. This is a
    // workaround for the fact that the ApiClient is a final class and cannot be spied on directly.
    Field apiClientField = Chats.class.getDeclaredField("apiClient");
    apiClientField.setAccessible(true);
    apiClientField.set(client.chats, mockedClient);

    Chat chatSession = client.chats.create("gemini-2.0-flash-exp", null);

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

    StringEntity content =
        new StringEntity(
            "{\"candidates\":[{\"content\":{\"parts\":[{\"text\":\"It's better with"
                + " cheddar\"}], \"role\":\"model\"}}]}}");
    when(mockedResponse.getEntity()).thenReturn(content);
    // Make the apiClient field public so that it can be spied on in the tests. This is a
    // workaround for the fact that the ApiClient is a final class and cannot be spied on directly.
    Field apiClientField = Chats.class.getDeclaredField("apiClient");
    apiClientField.setAccessible(true);
    apiClientField.set(client.chats, mockedClient);

    Chat chatSession = client.chats.create("gemini-2.0-flash-exp", null);

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
    StringEntity content =
        new StringEntity(
            "{\"candidates\":[{\"content\":{\"parts\":[{\"text\":\"It's better with"
                + " cheddar\"}], \"role\":\"model\"}, \"finishReason\":\"BLOCKLIST\"}]}");
    when(mockedResponse.getEntity()).thenReturn(content);

    // Make the apiClient field public so that it can be spied on in the tests. This is a
    // workaround for the fact that the ApiClient is a final class and cannot be spied on directly.
    Field apiClientField = Chats.class.getDeclaredField("apiClient");
    apiClientField.setAccessible(true);
    apiClientField.set(client.chats, mockedClient);
    Chat chatSession = client.chats.create("gemini-2.0-flash-exp");

    List<Part> emptyParts = new ArrayList<>();
    emptyParts.add(Part.builder().build().fromText("Tell me something about cheese."));

    Content messageContent = Content.builder().parts(emptyParts).role("user").build();
    chatSession.sendMessage(messageContent);

    // Curated history should be empty because the response has a finish reason of BLOCKLIST.
    assert chatSession.getHistory(true).size() == 0;
    assert chatSession.getHistory(false).size() == 2;
  }

  @Test
  public void testInvalidRoleThrows() throws Exception {

    StringEntity content =
        new StringEntity(
            "{\"candidates\":[{\"content\":{\"parts\":[{\"text\":\"The Gouda Life\"}],"
                + " \"role\":\"Mr. Cheese\"}}, {\"content\": {\"parts\":[{\"text\":\"Something"
                + " Bleu\"}], \"role\":\"model\"}}]}}");
    when(mockedResponse.getEntity()).thenReturn(content);

    // Make the apiClient field public so that it can be spied on in the tests. This is a
    // workaround for the fact that the ApiClient is a final class and cannot be spied on directly.
    Field apiClientField = Chats.class.getDeclaredField("apiClient");
    apiClientField.setAccessible(true);
    apiClientField.set(client.chats, mockedClient);
    Chat chatSession = client.chats.create("gemini-2.0-flash-exp");

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

    StringEntity content =
        new StringEntity(
            "{\"candidates\":[{\"content\":{\"parts\":[{\"text\":\"The Gouda Life\"}],"
                + " \"role\":\"model\"}}, {\"content\": {\"parts\":[{\"text\":\"Something"
                + " Bleu\"}], \"role\":\"model\"}}]}}");
    when(mockedResponse.getEntity()).thenReturn(content);

    // Make the apiClient field public so that it can be spied on in the tests. This is a
    // workaround for the fact that the ApiClient is a final class and cannot be spied on directly.
    Field apiClientField = Chats.class.getDeclaredField("apiClient");
    apiClientField.setAccessible(true);
    apiClientField.set(client.chats, mockedClient);
    Chat chatSession = client.chats.create("gemini-2.0-flash-exp");

    List<Part> parts = new ArrayList<>();
    parts.add(Part.builder().text("Can you give me possible names for a cheese shop?").build());

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

    Chat chatSession = client.chats.create("gemini-2.0-flash-exp", null);

    InputStream inputStream1 =
        new ByteArrayInputStream(streamData.getBytes(StandardCharsets.UTF_8));
    InputStream inputStream2 =
        new ByteArrayInputStream(streamData2.getBytes(StandardCharsets.UTF_8));

    InputStream inputStream3 =
        new ByteArrayInputStream(nonStreamData.getBytes(StandardCharsets.UTF_8));

    when(mockedResponse1.getEntity()).thenReturn(mockedEntity1);
    when(mockedResponse2.getEntity()).thenReturn(mockedEntity2);
    when(mockedResponse3.getEntity()).thenReturn(mockedEntity3);
    when(mockedEntity1.getContent()).thenReturn(inputStream1);
    when(mockedEntity2.getContent()).thenReturn(inputStream2);
    when(mockedEntity3.getContent()).thenReturn(inputStream3);
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
    assert chatSession.getHistory(false).size() == 2;
    ResponseStream<GenerateContentResponse> responseStream2 =
        chatSession.sendMessageStream("Tell me another story.", null);

    // Iterate over the second stream so we can add it to history
    while (responseStream2.iterator().hasNext()) {
      GenerateContentResponse responseChunk = responseStream2.iterator().next();
      assertNotNull(responseChunk);
      assertNotNull(responseChunk.text());
    }

    List<Content> historyAfterSecondStreamCall = chatSession.getHistory(false);
    assert historyAfterSecondStreamCall.size() == 4;

    // Second item in history should be the aggregated model response from the stream chunks
    assert historyAfterSecondStreamCall
        .get(1)
        .parts()
        .get()
        .get(0)
        .text()
        .orElse(null)
        .equals(
            STREAMING_RESPONSE_CHUNK_1 + STREAMING_RESPONSE_CHUNK_2 + STREAMING_RESPONSE_CHUNK_3);

    // Test that subsequent non-streaming sendMessage calls also include updated history
    chatSession.sendMessage("Tell me a third story.", null);
    List<Content> historyAfterThirdMessageCall = chatSession.getHistory(false);

    // Since this was a non-streaming call, the history should include the second aggregated stream
    // response as well as the new non-streaming response.
    assert historyAfterThirdMessageCall.size() == 6;
    assert historyAfterThirdMessageCall
        .get(5)
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

    Chat chatSession = client.chats.create("gemini-2.0-flash-exp", null);

    InputStream inputStream1 =
        new ByteArrayInputStream(streamData.getBytes(StandardCharsets.UTF_8));
    InputStream inputStream2 =
        new ByteArrayInputStream(streamData2.getBytes(StandardCharsets.UTF_8));

    when(mockedResponse1.getEntity()).thenReturn(mockedEntity1);
    when(mockedResponse2.getEntity()).thenReturn(mockedEntity2);
    when(mockedEntity1.getContent()).thenReturn(inputStream1);
    when(mockedEntity2.getContent()).thenReturn(inputStream2);
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
