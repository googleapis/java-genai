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
import com.google.genai.types.Content;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

/**
 * An async chat session with a generative model.
 *
 * <p>This class provides a way to interact with a generative model in an async multi-turn chat
 * session. It keeps track of the chat history and uses it to provide context for subsequent
 * messages.
 */
public class AsyncChat extends ChatBase {
  private final ApiClient apiClient;
  private final AsyncModels models;
  private final String model;
  private final GenerateContentConfig config;

  private static final Logger logger = Logger.getLogger(AsyncChat.class.getName());

  AsyncChat(ApiClient apiClient, String model, GenerateContentConfig config) {
    super(new ArrayList<>(), new ArrayList<>());
    this.apiClient = apiClient;
    this.models = new AsyncModels(apiClient);
    this.model = model;
    this.config = config;
  }

  /**
   * Asynchronously sends a message to the model in the current multi-turn chat session and returns
   * the model's response.
   *
   * <p>This appends the message and the model's response to the chat history, which is sent back to
   * the model in subsequent calls to sendMessage. Be sure to initialize a chat session using
   * client.async.chats.create() before calling sendMessage.
   *
   * @param text the text to send to the generative model
   * @param config a {@link com.google.genai.types.GenerateContentConfig} instance that specifies
   *     the optional configurations
   */
  public CompletableFuture<GenerateContentResponse> sendMessage(
      String text, GenerateContentConfig config) {
    return privateSendMessage(Transformers.tContents(this.apiClient, (Object) text), config);
  }

  /**
   * Asynchronously sends a message to the model in the current multi-turn chat session and returns
   * the model's response.
   *
   * <p>This appends the message and the model's response to the chat history, which is sent back to
   * the model in subsequent calls to sendMessage. Be sure to initialize a chat session using
   * client.async.chats.create() before calling sendMessage.
   *
   * @param text the text to send to the generative model
   */
  public CompletableFuture<GenerateContentResponse> sendMessage(String text) {
    return privateSendMessage(Transformers.tContents(this.apiClient, (Object) text), null);
  }

  /**
   * Asynchronously sends a message to the model in the current multi-turn chat session and returns
   * the model's response.
   *
   * <p>This appends the message and the model's response to the chat history, which is sent back to
   * the model in subsequent calls to sendMessage. Be sure to initialize a chat session using
   * cilent.async.chats.create() before calling sendMessage.
   *
   * @param content a {@link com.google.genai.types.Content} to send to the generative model
   * @param config a {@link com.google.genai.types.GenerateContentConfig} instance that specifies
   *     the optional configurations
   */
  public CompletableFuture<GenerateContentResponse> sendMessage(
      Content content, GenerateContentConfig config) {
    return privateSendMessage(Transformers.tContents(this.apiClient, (Object) content), config);
  }

  /**
   * Asynchronously sends a message to the model in the current multi-turn chat session and returns
   * the model's response.
   *
   * <p>This appends the message and the model's response to the chat history, which is sent back to
   * the model in subsequent calls to sendMessage. Be sure to initialize a chat session using
   * client.async.chats.create() before calling sendMessage.
   *
   * @param content a {@link com.google.genai.types.Content} to send to the generative model
   */
  public CompletableFuture<GenerateContentResponse> sendMessage(Content content) {
    return privateSendMessage(Transformers.tContents(this.apiClient, (Object) content), null);
  }

  /**
   * Asynchronously sends a message to the model in the current multi-turn chat session and returns
   * the model's response.
   *
   * <p>This appends the message and the model's response to the chat history, which is sent back to
   * the model in subsequent calls to sendMessage. Be sure to initialize a chat session using
   * client.async.chats.create() before calling sendMessage.
   *
   * @param contents a {@link List<com.google.genai.types.Content>} to send to the generative model
   * @param config a {@link com.google.genai.types.GenerateContentConfig} instance that specifies
   *     the optional configurations
   */
  public CompletableFuture<GenerateContentResponse> sendMessage(
      List<Content> contents, GenerateContentConfig config) {
    return privateSendMessage(contents, config);
  }

  /**
   * Asynchronously sends a message to the model in the current multi-turn chat session and returns
   * the model's response.
   *
   * <p>This appends the message and the model's response to the chat history, which is sent back to
   * the model in subsequent calls to sendMessage. Be sure to initialize a chat session using
   * client.async.chats.create() before calling sendMessage.
   *
   * @param contents a {@link List<com.google.genai.types.Content>} to send to the generative model
   */
  public CompletableFuture<GenerateContentResponse> sendMessage(List<Content> contents) {
    return privateSendMessage(contents, null);
  }

  private CompletableFuture<GenerateContentResponse> privateSendMessage(
      List<Content> contents, GenerateContentConfig config) {

    // Validate user input before sending to the model.
    if (!validateContents(contents)) {
      throw new IllegalArgumentException("The content of the message is invalid.");
    }

    List<Content> requestContents = new ArrayList<>();
    requestContents.addAll(this.curatedHistory);
    requestContents.addAll(contents);

    if (this.config != null && config == null) {
      config = this.config;
    }

    final GenerateContentConfig finalConfig = config;

    CompletableFuture<GenerateContentResponse> responseFuture =
        this.models.generateContent(this.model, requestContents, finalConfig);

    responseFuture
        .thenAccept(
            response -> {
              List<Content> responseContents = new ArrayList<>();
              for (Candidate candidate : response.candidates().get()) {
                responseContents.add(candidate.content().get());
              }
              List<Content> currentHistory = new ArrayList<>();
              currentHistory.addAll(contents);
              currentHistory.addAll(responseContents);
              recordHistory(currentHistory, response);
            })
        .exceptionally(
            exception -> {
              logger.warning("Async chat response failed with exception: " + exception);
              return null;
            })
        .join();
    ;

    return responseFuture;
  }
}
