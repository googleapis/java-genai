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

import com.google.genai.types.Candidate;
import com.google.genai.types.Citation;
import com.google.genai.types.CitationMetadata;
import com.google.genai.types.Content;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.GenerateContentResponsePromptFeedback;
import com.google.genai.types.GenerateContentResponseUsageMetadata;
import com.google.genai.types.Part;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * A chat session with a generative model.
 *
 * <p>This class provides a way to interact with a generative model in a multi-turn chat session. It
 * keeps track of the chat history and uses it to provide context for subsequent messages.
 */
public final class Chat extends ChatBase {
  private final ApiClient apiClient;
  private final Models models;
  private final String model;
  private final GenerateContentConfig config;
  private ResponseStream<GenerateContentResponse> currentResponseStream;
  private List<Content> currentUserMessage;

  Chat(ApiClient apiClient, String model, GenerateContentConfig config) {
    super(new ArrayList<>(), new ArrayList<>());
    this.apiClient = apiClient;
    this.models = new Models(apiClient);
    this.model = model;
    this.config = config;
  }

  /**
   * Sends a message to the model in the current multi-turn chat session and returns the model's
   * response.
   *
   * <p>This appends the message and the model's response to the chat history, which is sent back to
   * the model in subsequent calls to sendMessage. Be sure to initialize a chat session using
   * chats.create() before calling sendMessage.
   *
   * @param text the text to send to the generative model
   * @param config a {@link com.google.genai.types.GenerateContentConfig} instance that specifies
   *     the optional configurations
   */
  public GenerateContentResponse sendMessage(String text, GenerateContentConfig config) {
    return privateSendMessage(Transformers.tContents(this.apiClient, (Object) text), config);
  }

  /**
   * Sends a message to the model in the current multi-turn chat session and returns the model's
   * response.
   *
   * <p>This appends the message and the model's response to the chat history, which is sent back to
   * the model in subsequent calls to sendMessage. Be sure to initialize a chat session using
   * chats.create() before calling sendMessage.
   *
   * @param text the text to send to the generative model
   */
  public GenerateContentResponse sendMessage(String text) {
    return privateSendMessage(Transformers.tContents(this.apiClient, (Object) text), null);
  }

  /**
   * Sends a message to the model in the current multi-turn chat session and returns the model's
   * response.
   *
   * <p>This appends the message and the model's response to the chat history, which is sent back to
   * the model in subsequent calls to sendMessage. Be sure to initialize a chat session using
   * chats.create() before calling sendMessage.
   *
   * @param content a {@link com.google.genai.types.Content} to send to the generative model
   * @param config a {@link com.google.genai.types.GenerateContentConfig} instance that specifies
   *     the optional configurations
   */
  public GenerateContentResponse sendMessage(Content content, GenerateContentConfig config) {
    return privateSendMessage(Transformers.tContents(this.apiClient, (Object) content), config);
  }

  /**
   * Sends a message to the model in the current multi-turn chat session and returns the model's
   * response.
   *
   * <p>This appends the message and the model's response to the chat history, which is sent back to
   * the model in subsequent calls to sendMessage. Be sure to initialize a chat session using
   * chats.create() before calling sendMessage.
   *
   * @param content a {@link com.google.genai.types.Content} to send to the generative model
   */
  public GenerateContentResponse sendMessage(Content content) {
    return privateSendMessage(Transformers.tContents(this.apiClient, (Object) content), null);
  }

  /**
   * Sends a message to the model in the current multi-turn chat session and returns the model's
   * response.
   *
   * <p>This appends the message and the model's response to the chat history, which is sent back to
   * the model in subsequent calls to sendMessage. Be sure to initialize a chat session using
   * chats.create() before calling sendMessage.
   *
   * @param contents a {@link List<com.google.genai.types.Content>} to send to the generative model
   * @param config a {@link com.google.genai.types.GenerateContentConfig} instance that specifies
   *     the optional configurations
   */
  public GenerateContentResponse sendMessage(List<Content> contents, GenerateContentConfig config) {
    return privateSendMessage(contents, config);
  }

  /**
   * Sends a message to the model in the current multi-turn chat session and returns the model's
   * response.
   *
   * <p>This appends the message and the model's response to the chat history, which is sent back to
   * the model in subsequent calls to sendMessage. Be sure to initialize a chat session using
   * chats.create() before calling sendMessage.
   *
   * @param contents a {@link List<com.google.genai.types.Content>} to send to the generative model
   */
  public GenerateContentResponse sendMessage(List<Content> contents) {
    return privateSendMessage(contents, null);
  }

  private GenerateContentResponse privateSendMessage(
      List<Content> contents, GenerateContentConfig config) {

    // Validate user input before sending to the model.
    if (!validateContents(contents)) {
      throw new IllegalArgumentException("The content of the message is invalid.");
    }

    checkLastResponseAndUpdateHistory();

    List<Content> requestContents = new ArrayList<>();
    requestContents.addAll(this.curatedHistory);
    requestContents.addAll(contents);

    if (this.config != null && config == null) {
      config = this.config;
    }

    GenerateContentResponse response =
        this.models.generateContent(this.model, requestContents, config);

    response.checkFinishReason();

    List<Content> responseContents = new ArrayList<>();
    for (Candidate candidate : response.candidates().get()) {
      responseContents.add(candidate.content().get());
    }
    List<Content> currentHistory = new ArrayList<>();
    currentHistory.addAll(contents);
    currentHistory.addAll(responseContents);
    recordHistory(currentHistory);
    return response;
  }

  private GenerateContentResponse aggregateStreamingResponse(
      List<GenerateContentResponse> responseChunks) {
    // TODO: handle requests with > 1 candidate?
    if (responseChunks == null || responseChunks.isEmpty()) {
      return GenerateContentResponse.builder().build();
    }

    List<Part> aggregatedParts = new ArrayList<>();
    String aggregatedText = "";
    List<Citation> aggregatedCitations = new ArrayList<>();

    Optional<GenerateContentResponsePromptFeedback> finalPromptFeedback = Optional.empty();
    Optional<GenerateContentResponseUsageMetadata> finalUsageMetadata = Optional.empty();
    Optional<String> finalFinishReason = Optional.empty();

    // Take from the first chunk
    Optional<String> finalResponseId = Optional.empty();
    Optional<String> finalModelVersion = Optional.empty();
    Optional<String> finalCreateTime = Optional.empty();
    boolean firstChunkProcessed = false;

    for (GenerateContentResponse responseChunk : responseChunks) {
      if (responseChunk == null) {
        continue;
      }
      if (!firstChunkProcessed) {
        finalResponseId = responseChunk.responseId();
        finalModelVersion = responseChunk.modelVersion();
        finalCreateTime = responseChunk.createTime();
        firstChunkProcessed = true;
      }

      if (!finalPromptFeedback.isPresent() && responseChunk.promptFeedback().isPresent()) {
        finalPromptFeedback = responseChunk.promptFeedback();
      }

      if (!finalUsageMetadata.isPresent() && responseChunk.usageMetadata().isPresent()) {
        finalUsageMetadata = responseChunk.usageMetadata();
      }

      if (!finalFinishReason.isPresent()
          && responseChunk.candidates().get().get(0).finishReason().isPresent()) {
        finalFinishReason = responseChunk.candidates().get().get(0).finishReason();
      }
      Candidate candidate = responseChunk.candidates().get().get(0);
      if (candidate.content().isPresent() && candidate.content().get().parts().isPresent()) {
        List<Part> parts = candidate.content().get().parts().get();
        for (Part part : parts) {
          if (part.text().isPresent()) {
            aggregatedText += part.text().get();
          } else {
            boolean hasOtherContentParts =
                part.functionCall().isPresent()
                    || part.functionResponse().isPresent()
                    || part.codeExecutionResult().isPresent()
                    || part.executableCode().isPresent()
                    || part.fileData().isPresent()
                    || part.videoMetadata().isPresent()
                    || part.thought().isPresent()
                    || part.inlineData().isPresent();
            if (hasOtherContentParts) {
              aggregatedParts.add(part);
            }
          }
          if (candidate.citationMetadata().isPresent()) {
            for (Citation citation : candidate.citationMetadata().get().citations().get()) {
              aggregatedCitations.add(citation);
            }
          }
        }
      }
    }
    // Construct the final response
    aggregatedParts.add(Part.fromText(aggregatedText));

    Candidate finalCandidate =
        Candidate.builder()
            .content(Content.builder().parts(aggregatedParts).role("model").build())
            .citationMetadata(CitationMetadata.builder().citations(aggregatedCitations).build())
            .finishReason(finalFinishReason.orElse(""))
            .build();
    List<Candidate> finalCandidates = new ArrayList<>();
    finalCandidates.add(finalCandidate);

    GenerateContentResponse finalResponse =
        GenerateContentResponse.builder()
            .responseId(finalResponseId.orElse(""))
            .modelVersion(finalModelVersion.orElse(""))
            .createTime(finalCreateTime.orElse(""))
            .promptFeedback(
                finalPromptFeedback.orElse(GenerateContentResponsePromptFeedback.builder().build()))
            .usageMetadata(
                finalUsageMetadata.orElse(GenerateContentResponseUsageMetadata.builder().build()))
            .candidates(finalCandidates)
            .build();
    return finalResponse;
  }

  private void checkLastResponseAndUpdateHistory() {
    if (this.currentResponseStream != null && this.currentUserMessage != null) {
      if (!this.currentResponseStream.isConsumed()) {
        throw new IllegalStateException("Response stream is not consumed");
      } else {
        List<Content> streamingResponseContents = new ArrayList<>();
        streamingResponseContents.addAll(this.currentUserMessage);
        GenerateContentResponse aggregatedResponse =
            aggregateStreamingResponse(this.currentResponseStream.history);
        aggregatedResponse.checkFinishReason();
        for (Candidate candidate : aggregatedResponse.candidates().get()) {
          streamingResponseContents.add(candidate.content().get());
        }
        recordHistory(streamingResponseContents);
      }
      this.currentUserMessage = null;
      this.currentResponseStream = null;
    }
  }

  /**
   * Sends a message to the model in the current multi-turn chat session and returns a stream of
   * responses.
   *
   * <p>This appends the message and the model's response to the chat history in *subsequent* calls
   * to sendMessage or sendMessageStream. Be sure to initialize a chat session using chats.create()
   * before calling sendMessageStream.
   *
   * @param text the text to send to the generative model
   * @param config a {@link com.google.genai.types.GenerateContentConfig} instance that specifies
   *     the optional configurations *
   */
  public ResponseStream<GenerateContentResponse> sendMessageStream(
      String text, GenerateContentConfig config) {
    return privateSendMessageStream(Transformers.tContents(this.apiClient, (Object) text), config);
  }

  /**
   * Sends a message to the model in the current multi-turn chat session and returns a stream of
   * responses.
   *
   * <p>This appends the message and the model's response to the chat history in *subsequent* calls
   * to sendMessage or sendMessageStream. Be sure to initialize a chat session using chats.create()
   * before calling sendMessageStream.
   *
   * @param text the text to send to the generative model
   * @param config a {@link com.google.genai.types.GenerateContentConfig} instance that specifies
   *     the optional configurations *
   */
  public ResponseStream<GenerateContentResponse> sendMessageStream(String text) {
    return privateSendMessageStream(Transformers.tContents(this.apiClient, (Object) text), null);
  }

  /**
   * Sends a message to the model in the current multi-turn chat session and returns a stream of
   * responses.
   *
   * <p>This appends the message and the model's response to the chat history in *subsequent* calls
   * to sendMessage or sendMessageStream. Be sure to initialize a chat session using chats.create()
   * before calling sendMessageStream.
   *
   * @param content a {@link com.google.genai.types.Content} to send to the generative model
   * @param config a {@link com.google.genai.types.GenerateContentConfig} instance that specifies
   *     the optional configurations *
   */
  public ResponseStream<GenerateContentResponse> sendMessageStream(
      Content content, GenerateContentConfig config) {
    return privateSendMessageStream(
        Transformers.tContents(this.apiClient, (Object) content), config);
  }

  /**
   * Sends a message to the model in the current multi-turn chat session and returns a stream of
   * responses.
   *
   * <p>This appends the message and the model's response to the chat history, which is sent back to
   * the model in subsequent calls to sendMessage or sendMessageStream. Be sure to initialize a chat
   * session using chats.create() before calling sendMessageStream.
   *
   * @param content a {@link com.google.genai.types.Content} to send to the generative model
   */
  public ResponseStream<GenerateContentResponse> sendMessageStream(Content content) {
    return privateSendMessageStream(Transformers.tContents(this.apiClient, (Object) content), null);
  }

  /**
   * Sends a message to the model in the current multi-turn chat session and returns a stream of
   * responses.
   *
   * <p>This appends the message and the model's response to the chat history, which is sent back to
   * the model in subsequent calls to sendMessage or sendMessageStream. Be sure to initialize a chat
   * session using chats.create() before calling sendMessageStream.
   *
   * @param contents a {@link List<com.google.genai.types.Content>} to send to the generative model
   * @param config a {@link com.google.genai.types.GenerateContentConfig} instance that specifies
   *     the optional configurations
   */
  public ResponseStream<GenerateContentResponse> sendMessageStream(
      List<Content> contents, GenerateContentConfig config) {
    return privateSendMessageStream(contents, config);
  }

  /**
   * Sends a message to the model in the current multi-turn chat session and returns the model's
   * response.
   *
   * <p>This appends the message and the model's response to the chat history, which is sent back to
   * the model in subsequent calls to sendMessage. Be sure to initialize a chat session using
   * chats.create() before calling sendMessage.
   *
   * @param contents a {@link List<com.google.genai.types.Content>} to send to the generative model
   *     the optional configurations
   */
  public ResponseStream<GenerateContentResponse> sendMessageStream(List<Content> contents) {
    return privateSendMessageStream(contents, null);
  }

  private ResponseStream<GenerateContentResponse> privateSendMessageStream(
      List<Content> contents, GenerateContentConfig config) throws IllegalArgumentException {

    // Validate user input before sending to the model.
    if (!validateContents(contents)) {
      throw new IllegalArgumentException("The content of the message is invalid.");
    }

    // Add history from previous streaming call if it exists.
    checkLastResponseAndUpdateHistory();

    List<Content> requestContents = new ArrayList<>();
    requestContents.addAll(this.curatedHistory);
    requestContents.addAll(contents);

    if (this.config != null && config == null) {
      config = this.config;
    }

    ResponseStream<GenerateContentResponse> responseStream =
        this.models.generateContentStream(this.model, requestContents, config);
    responseStream.recordingHistory = true;
    this.currentUserMessage = contents;
    this.currentResponseStream = responseStream;

    return responseStream;
  }
}
