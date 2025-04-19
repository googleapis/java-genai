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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.google.genai.types.Candidate;
import com.google.genai.types.Content;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.GenerateContentResponseUsageMetadata;
import com.google.genai.types.Part;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.apache.http.HttpEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class StreamingChatTest {

  ApiClient mockedClient;
  ApiResponse mockedResponse1;
  ApiResponse mockedResponse2;
  ApiResponse mockedResponse3;
  HttpEntity mockedEntity1;
  HttpEntity mockedEntity2;
  HttpEntity mockedEntity3;
  Client client;
  ChatSession chatSession;

  private static final String STREAMING_RESPONSE_CHUNK_1 = "Once upon ";
  private static final String STREAMING_RESPONSE_CHUNK_2 = "a time, in a land";
  private static final String STREAMING_RESPONSE_CHUNK_3 = " far, far away...";
  private static final String NON_STREAMING_RESPONSE = "This is a non-streaming response.";
  private Iterator<GenerateContentResponse> mockStreamIterator;

  @BeforeEach
  void setUp() {
    mockedClient = Mockito.mock(ApiClient.class);
    chatSession = Mockito.mock(ChatSession.class);
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
  public void testIterateOverResponseStream() throws Exception {

    Field apiClientField = ChatSession.class.getDeclaredField("apiClient");
    apiClientField.setAccessible(true);
    apiClientField.set(client.chats, mockedClient);

    Chat chatSession = client.chats.create("gemini-2.0-flash-exp", null);
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
                        .finishReason("STOP")
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
    when(mockedClient.post(anyString(), anyString()))
        .thenReturn(mockedResponse1, mockedResponse2, mockedResponse3);

    assert chatSession.getHistory(false).size() == 0;

    ResponseStream<GenerateContentResponse> responseStream =
        chatSession.sendMessageStream("Tell me a story.", null);

    assertNotNull(responseStream);

    int chunkCount = 0;
    // Iterate over the stream
    while (responseStream.iterator().hasNext()) {
      GenerateContentResponse responseChunk = responseStream.iterator().next();
      assertNotNull(responseChunk);
      assertNotNull(responseChunk.text());
      if (chunkCount == 0) {
        assert (responseChunk.text().equals(STREAMING_RESPONSE_CHUNK_1));
      }
      chunkCount++;
    }

    assert chunkCount == 3;

    // History is updated on subsequent sendMessage* calls for streaming so it should still be empty
    // here
    assert chatSession.getHistory(false).size() == 0;
    ResponseStream<GenerateContentResponse> responseStream2 =
        chatSession.sendMessageStream("Tell me another story.", null);

    List<Content> historyAfterSecondStreamCall = chatSession.getHistory(false);
    assert historyAfterSecondStreamCall.size() == 2;

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

    // Iterate over the second stream so we can add it to history in the third call
    while (responseStream2.iterator().hasNext()) {
      GenerateContentResponse responseChunk = responseStream2.iterator().next();
      assertNotNull(responseChunk);
      assertNotNull(responseChunk.text());
    }

    // Test that subsequent non-streaming sendMessage calls also update the history
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
}
