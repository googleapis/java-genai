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

import com.google.genai.types.Candidate;
import com.google.genai.types.Content;
import com.google.genai.types.FinishReason;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.GenerateContentResponseUsageMetadata;
import com.google.genai.types.Part;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class AsyncChatTest {

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

  private static final String STREAMING_RESPONSE_CHUNK_1 = "Once upon ";
  private static final String STREAMING_RESPONSE_CHUNK_2 = "a time, in a land";
  private static final String STREAMING_RESPONSE_CHUNK_3 = " far, far away...";
  private static final String NON_STREAMING_RESPONSE = "This is a non-streaming response.";
  private Iterator<GenerateContentResponse> mockStreamIterator;

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
    mockedEntity = Mockito.mock(HttpEntity.class);

    client = Client.builder().build();

    mockedResponse1 = Mockito.mock(ApiResponse.class);
    mockedResponse2 = Mockito.mock(ApiResponse.class);
    mockedResponse3 = Mockito.mock(ApiResponse.class);
    mockedEntity1 = Mockito.mock(HttpEntity.class);
    mockedEntity2 = Mockito.mock(HttpEntity.class);
    mockedEntity3 = Mockito.mock(HttpEntity.class);
  }

  @Test
  public void testCreateAsyncChatSession() throws Exception {
    Field apiClientField = AsyncChats.class.getDeclaredField("apiClient");
    apiClientField.setAccessible(true);
    apiClientField.set(client.async.chats, mockedClient);

    AsyncChat chat = client.async.chats.create("gemini-2.0-flash-exp", null);

    assertNotNull(chat);
  }

  @Test
  public void testGetAsyncChatMessage() throws Exception {
    Field apiClientField = AsyncChats.class.getDeclaredField("apiClient");
    apiClientField.setAccessible(true);
    apiClientField.set(client.async.chats, mockedClient);

    StringEntity content =
        new StringEntity(
            "{\"candidates\":[{\"content\":{\"parts\":[{\"text\":\"Once upon a time, there was a"
                + " cheese shop\"}], \"role\":\"model\"}}]}}");

    when(mockedResponse.getEntity()).thenReturn(content);

    AsyncChat chat = client.async.chats.create("gemini-2.0-flash-exp", null);

    CompletableFuture<GenerateContentResponse> responseFuture =
        chat.sendMessage("Can you tell me a story?");

    responseFuture.thenAccept(
        response -> {
          assertNotNull(response.text());
        });
  }

  @Test
  public void testGetHistoryAsync() throws Exception {
    Field apiClientField = AsyncChats.class.getDeclaredField("apiClient");
    apiClientField.setAccessible(true);
    apiClientField.set(client.async.chats, mockedClient);

    StringEntity content =
        new StringEntity(
            "{\"candidates\":[{\"content\":{\"parts\":[{\"text\":\"Once upon a time, there was a"
                + " cheese shop\"}], \"role\":\"model\"}}]}}");

    when(mockedResponse.getEntity()).thenReturn(content);

    AsyncChat chat = client.async.chats.create("gemini-2.0-flash-exp", null);

    CompletableFuture<GenerateContentResponse> responseFuture =
        chat.sendMessage("Can you tell me a story?");

    CompletableFuture<GenerateContentResponse> responseFuture2 =
        chat.sendMessage("Can you tell me another story?");

    CompletableFuture.allOf(responseFuture, responseFuture2).join();

    List<Content> history = chat.getHistory(true);
    assert history.size() == 4;
  }

  @Test
  public void testIterateOverAsyncResponseStream() throws Exception {

    Field apiClientField = AsyncChats.class.getDeclaredField("apiClient");
    apiClientField.setAccessible(true);
    apiClientField.set(client.async.chats, mockedClient);

    AsyncChat chatSession = client.async.chats.create("gemini-2.0-flash-exp", null);

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

    CompletableFuture<ResponseStream<GenerateContentResponse>> responseStreamFuture =
        chatSession.sendMessageStream("Tell me a story.", null);

    assertNotNull(responseStreamFuture);

    AtomicInteger chunkCount = new AtomicInteger(0);

    // Iterate over the stream
    responseStreamFuture
        .thenAccept(
            responseStream -> {
              Iterator<GenerateContentResponse> iterator = responseStream.iterator();
              while (iterator.hasNext()) {
                GenerateContentResponse responseChunk = iterator.next();
                assertNotNull(responseChunk.text());
                int currentChunkIndex = chunkCount.getAndIncrement();
                if (currentChunkIndex == 0) {
                  assert responseChunk.text().equals(STREAMING_RESPONSE_CHUNK_1);
                }
              }
            })
        .join();

    assert chunkCount.get() == 3;

    // History is updated after the stream is consumed
    assert chatSession.getHistory(false).size() == 2;
    CompletableFuture<ResponseStream<GenerateContentResponse>> responseStreamFuture2 =
        chatSession.sendMessageStream("Tell me another story.");

    // Iterate over the second stream so we can add it to history
    responseStreamFuture2
        .thenAccept(
            responseStream -> {
              Iterator<GenerateContentResponse> iterator = responseStream.iterator();
              while (iterator.hasNext()) {
                GenerateContentResponse responseChunk = iterator.next();
                assertNotNull(responseChunk.text());
              }
            })
        .join();

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
  }

  @Test
  public void testThrowsIfAsyncStreamResponseIsNotConsumed() throws Exception {
    /* Tests that an exception is thrown if the async response stream is not consumed before calling
     * getHistory() or sendMessage* again. */

    Field apiClientField = AsyncChats.class.getDeclaredField("apiClient");
    apiClientField.setAccessible(true);
    apiClientField.set(client.async.chats, mockedClient);

    AsyncChat chatSession = client.async.chats.create("gemini-2.0-flash-exp", null);

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

    ResponseStream<GenerateContentResponse> responseStreamFuture =
        chatSession.sendMessageStream("Tell me a story.", null).join();

    IllegalStateException exception =
        assertThrows(IllegalStateException.class, () -> chatSession.getHistory(false));

    assert exception.getMessage().equals("Response stream is not consumed");

    IllegalStateException exception2 =
        assertThrows(
            IllegalStateException.class,
            () -> chatSession.sendMessageStream("Tell me another story."));
  }
}
