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

import com.google.common.collect.ImmutableList;
import com.google.genai.types.Candidate;
import com.google.genai.types.Content;
import com.google.genai.types.FinishReason;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.GenerateContentResponseUsageMetadata;
import com.google.genai.types.Part;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

public class AsyncChatTest {

  ApiClient mockedClient;
  ApiResponse mockedResponse;
  ApiResponse mockedResponse1;
  ApiResponse mockedResponse2;
  ApiResponse mockedResponse3;
  Client client;

  private static final String MODEL_ID = "gemini-2.5-flash";
  private static final String STREAMING_RESPONSE_CHUNK_1 = "Once upon ";
  private static final String STREAMING_RESPONSE_CHUNK_2 = "a time, in a land";
  private static final String STREAMING_RESPONSE_CHUNK_3 = " far, far away...";
  private static final String NON_STREAMING_RESPONSE = "This is a non-streaming response.";
  private static final byte[] CONTINUATION_TOKEN = "token".getBytes(StandardCharsets.UTF_8);

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
    when(mockedClient.asyncRequest(anyString(), anyString(), anyString(), any()))
        .thenReturn(CompletableFuture.completedFuture(mockedResponse));

    String apiKey = Optional.ofNullable(ApiClient.getApiKeyFromEnv()).orElse("api-key");
    client = Client.builder().apiKey(apiKey).vertexAI(false).build();

    mockedResponse1 = Mockito.mock(ApiResponse.class);
    mockedResponse2 = Mockito.mock(ApiResponse.class);
    mockedResponse3 = Mockito.mock(ApiResponse.class);
  }

  @Test
  public void testCreateAsyncChatSession() throws Exception {
    Field apiClientField = AsyncChats.class.getDeclaredField("apiClient");
    apiClientField.setAccessible(true);
    apiClientField.set(client.async.chats, mockedClient);

    AsyncChat chat = client.async.chats.create(MODEL_ID, null);

    assertNotNull(chat);
  }

  @Test
  public void testGetAsyncChatMessage() throws Exception {
    Field apiClientField = AsyncChats.class.getDeclaredField("apiClient");
    apiClientField.setAccessible(true);
    apiClientField.set(client.async.chats, mockedClient);

    ResponseBody content1 =
        ResponseBody.create(
            "{\"candidates\":[{\"content\":{\"parts\":[{\"text\":\"Once upon a time, there was a"
                + " cheese shop\"}], \"role\":\"model\"}, \"finishReason\":\"STOP\"}]}",
            MediaType.get("application/json"));
    ResponseBody content2 =
        ResponseBody.create(
            "{\"candidates\":[{\"content\":{\"parts\":[{\"text\":\"Once upon a time, there was a"
                + " cheese shop\"}], \"role\":\"model\"}, \"finishReason\":\"STOP\"}]}",
            MediaType.get("application/json"));

    when(mockedResponse1.getBody()).thenReturn(content1);
    when(mockedResponse2.getBody()).thenReturn(content2);
    when(mockedClient.asyncRequest(anyString(), anyString(), anyString(), any()))
        .thenReturn(
            CompletableFuture.completedFuture(mockedResponse1),
            CompletableFuture.completedFuture(mockedResponse2));

    AsyncChat chat = client.async.chats.create(MODEL_ID, null);

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

    ResponseBody content1 =
        ResponseBody.create(
            "{\"candidates\":[{\"content\":{\"parts\":[{\"text\":\"Once upon a time, there was a"
                + " cheese shop\"}], \"role\":\"model\"}, \"finishReason\":\"STOP\"}]}",
            MediaType.get("application/json"));
    ResponseBody content2 =
        ResponseBody.create(
            "{\"candidates\":[{\"content\":{\"parts\":[{\"text\":\"Once upon a time, there was a"
                + " cheese shop\"}], \"role\":\"model\"}, \"finishReason\":\"STOP\"}]}",
            MediaType.get("application/json"));

    when(mockedResponse1.getBody()).thenReturn(content1);
    when(mockedResponse2.getBody()).thenReturn(content2);
    when(mockedClient.asyncRequest(anyString(), anyString(), anyString(), any()))
        .thenReturn(
            CompletableFuture.completedFuture(mockedResponse1),
            CompletableFuture.completedFuture(mockedResponse2));

    AsyncChat chat = client.async.chats.create(MODEL_ID, null);

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

    AsyncChat chatSession = client.async.chats.create(MODEL_ID, null);

    ResponseBody body1 = ResponseBody.create(streamData, MediaType.get("application/json"));
    ResponseBody body2 = ResponseBody.create(streamData2, MediaType.get("application/json"));
    ResponseBody body3 = ResponseBody.create(nonStreamData, MediaType.get("application/json"));

    when(mockedResponse1.getBody()).thenReturn(body1);
    when(mockedResponse2.getBody()).thenReturn(body2);
    when(mockedResponse3.getBody()).thenReturn(body3);
    when(mockedClient.asyncRequest(anyString(), anyString(), anyString(), any()))
        .thenReturn(
            CompletableFuture.completedFuture(mockedResponse1),
            CompletableFuture.completedFuture(mockedResponse2),
            CompletableFuture.completedFuture(mockedResponse3));

    assert chatSession.getHistory(false).isEmpty();

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
    assert chatSession.getHistory(false).size() == 4;
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
  }

  @Test
  public void testThrowsIfAsyncStreamResponseIsNotConsumed() throws Exception {
    /* Tests that an exception is thrown if the async response stream is not consumed before calling
     * getHistory() or sendMessage* again. */

    Field apiClientField = AsyncChats.class.getDeclaredField("apiClient");
    apiClientField.setAccessible(true);
    apiClientField.set(client.async.chats, mockedClient);

    AsyncChat chatSession = client.async.chats.create(MODEL_ID, null);

    ResponseBody body1 = ResponseBody.create(streamData, MediaType.get("application/json"));
    ResponseBody body2 = ResponseBody.create(streamData2, MediaType.get("application/json"));
    when(mockedResponse1.getBody()).thenReturn(body1);
    when(mockedResponse2.getBody()).thenReturn(body2);
    when(mockedClient.asyncRequest(anyString(), anyString(), anyString(), any()))
        .thenReturn(
            CompletableFuture.completedFuture(mockedResponse1),
            CompletableFuture.completedFuture(mockedResponse2));

    assert chatSession.getHistory(false).isEmpty();

    ResponseStream<GenerateContentResponse> responseStream =
        chatSession.sendMessageStream("Tell me a story.", null).join();

    IllegalStateException exception =
        assertThrows(IllegalStateException.class, () -> chatSession.getHistory(false));

    assert exception.getMessage().equals("Response stream is not consumed");

    IllegalStateException exception2 =
        assertThrows(
            IllegalStateException.class,
            () -> chatSession.sendMessageStream("Tell me another story."));

    assert exception2.getMessage().equals("Response stream is not consumed");
  }

  @Test
  public void testSendMessage_continuesByDefault() {
    ApiClient apiClient = Mockito.mock(ApiClient.class);
    when(apiClient.asyncRequest(anyString(), anyString(), anyString(), any()))
        .thenReturn(
            CompletableFuture.completedFuture(
                jsonResponse(
                    textResponse(
                        "Once upon ", FinishReason.Known.CONTINUATION, CONTINUATION_TOKEN))),
            CompletableFuture.completedFuture(
                jsonResponse(textResponse("a time.", FinishReason.Known.STOP, null))));
    AsyncChat chat = new AsyncChat(apiClient, MODEL_ID, null);

    GenerateContentResponse response = chat.sendMessage("Tell me a story.").join();

    assertEquals("Once upon a time.", response.text());
    List<String> bodies = sentBodies(apiClient, 2);
    // A chat without a config sends its first request without one.
    assertFalse(JsonSerializable.stringToJsonNode(bodies.get(0)).has("generationConfig"));
    assertTrue(JsonSerializable.stringToJsonNode(bodies.get(1)).has("continuationToken"));
    ImmutableList<Content> history = chat.getHistory(true);
    assertEquals(2, history.size());
    assertEquals("Once upon a time.", history.get(1).text());
  }

  @Test
  public void testSendMessageStream_continuesByDefault() {
    ApiClient apiClient = Mockito.mock(ApiClient.class);
    when(apiClient.asyncRequest(anyString(), anyString(), anyString(), any()))
        .thenReturn(
            CompletableFuture.completedFuture(
                streamResponse(
                    textResponse("Once upon ", null, null),
                    textResponse("a time", FinishReason.Known.CONTINUATION, CONTINUATION_TOKEN))),
            CompletableFuture.completedFuture(
                streamResponse(textResponse(", the end.", FinishReason.Known.STOP, null))));
    AsyncChat chat = new AsyncChat(apiClient, MODEL_ID, null);

    List<String> texts = new ArrayList<>();
    try (ResponseStream<GenerateContentResponse> stream =
        chat.sendMessageStream("Tell me a story.").join()) {
      for (GenerateContentResponse chunk : stream) {
        texts.add(chunk.text());
      }
    }

    assertEquals(ImmutableList.of("Once upon ", "a time", ", the end."), texts);
    assertTrue(
        JsonSerializable.stringToJsonNode(sentBodies(apiClient, 2).get(1))
            .has("continuationToken"));
    // The user's message, then the chunks of both requests.
    assertEquals(
        ImmutableList.of("Tell me a story.", "Once upon ", "a time", ", the end."),
        chat.getHistory(true).stream().map(Content::text).collect(Collectors.toList()));
  }

  @Test
  public void testSendMessage_doesNotContinueWhenAutomaticContinuationIsFalse() {
    ApiClient apiClient = Mockito.mock(ApiClient.class);
    when(apiClient.asyncRequest(anyString(), anyString(), anyString(), any()))
        .thenReturn(
            CompletableFuture.completedFuture(
                jsonResponse(
                    textResponse(
                        "Once upon ", FinishReason.Known.CONTINUATION, CONTINUATION_TOKEN))));
    AsyncChat chat = new AsyncChat(apiClient, MODEL_ID, null);

    GenerateContentResponse response =
        chat.sendMessage(
                "Tell me a story.",
                GenerateContentConfig.builder().automaticContinuation(false).build())
            .join();

    assertEquals(FinishReason.Known.CONTINUATION, response.finishReason().knownEnum());
    sentBodies(apiClient, 1);
    assertEquals(
        ImmutableList.of("Tell me a story.", "Once upon "),
        chat.getHistory(true).stream().map(Content::text).collect(Collectors.toList()));
  }

  @Test
  public void testSendMessageStream_doesNotContinueWhenAutomaticContinuationIsFalse() {
    ApiClient apiClient = Mockito.mock(ApiClient.class);
    when(apiClient.asyncRequest(anyString(), anyString(), anyString(), any()))
        .thenReturn(
            CompletableFuture.completedFuture(
                streamResponse(
                    textResponse(
                        "Once upon ", FinishReason.Known.CONTINUATION, CONTINUATION_TOKEN))));
    AsyncChat chat = new AsyncChat(apiClient, MODEL_ID, null);

    List<String> texts = new ArrayList<>();
    try (ResponseStream<GenerateContentResponse> stream =
        chat.sendMessageStream(
                "Tell me a story.",
                GenerateContentConfig.builder().automaticContinuation(false).build())
            .join()) {
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
        .asyncRequest(anyString(), anyString(), bodies.capture(), any());
    return bodies.getAllValues();
  }
}
