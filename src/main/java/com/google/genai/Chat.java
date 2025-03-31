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
import com.google.genai.types.Part;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.http.HttpException;

/**
 * A chat session with a generative model.
 *
 * <p>This class provides a way to interact with a generative model in a multi-turn chat session. It
 * keeps track of the chat history and uses it to provide context for subsequent messages.
 */
public final class Chat {
  private final ApiClient apiClient;
  private final Models models;
  private final String model;
  private final GenerateContentConfig config;
  private final List<Content> comprehensiveHistory;
  private final List<Content> curatedHistory;

  Chat(ApiClient apiClient, String model, GenerateContentConfig config, List<Content> history) {
    this.apiClient = apiClient;
    this.models = new Models(apiClient);
    this.model = model;
    this.config = config;
    this.comprehensiveHistory = new ArrayList<>();
    this.curatedHistory = new ArrayList<>();
    this.recordHistory(history, true); // TODO: check for presence of history
  }

  /**
   * Records the chat history.
   *
   * @param currentHistory The current history of messages.
   * @param isValidResponse Whether the response is valid.
   */
  private void recordHistory(List<Content> currentHistory, boolean isValidResponse) {
    this.comprehensiveHistory.addAll(currentHistory);

    if (isValidResponse) {
      List<Content> validatedHistory = validateHistory(currentHistory);
      this.curatedHistory.addAll(validatedHistory);
    }
  }

  /**
   * Validates the content of a {@link Content} object by checking that all parts are not null and
   * have text.
   *
   * @param content The {@link Content} object to validate.
   * @return True if the content is valid, false otherwise.
   */
  private boolean validateContent(Content content) {
    if (content.parts().isPresent()) {
      for (Part part : content.parts().get()) {
        if (part == null) {
          return false;
        }
        if (!part.text().isPresent()) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Validates the history of messages by checking that the first message is from the user, the
   * roles of subsequent messages are valid, and the content of the messages is valid.
   *
   * @param history The history of messages to validate.
   * @return The validated history of messages.
   * @throws IllegalArgumentException If the history is invalid.
   */
  private List<Content> validateHistory(List<Content> history) throws IllegalArgumentException {
    List<Content> validatedHistory = new ArrayList<>();
    List<Content> currentInput = new ArrayList<>();
    List<Content> currentOutput = new ArrayList<>();
    List<String> validRoles = Arrays.asList("user", "model");
    for (int i = 0; i < history.size(); i++) {
      if (i == 0
          && history.get(i).role().isPresent()
          && !history.get(i).role().get().equals("user")) {
        throw new IllegalArgumentException(
            "The first message in the history must be from the user.");
      }
      if (i < history.size() && !validRoles.contains(history.get(i).role().get())) {
        throw new IllegalArgumentException(
            "The role of the message must be either 'user' or 'model'.");
      }
      if (history.get(i).role().isPresent() && history.get(i).role().get().equals("user")) {
        currentInput.add(history.get(i));
      } else {
        boolean isValid = true;
        while (i < history.size()
            && history.get(i).role().isPresent()
            && history.get(i).role().get().equals("model")) {
          currentOutput.add(history.get(i));
          if (isValid && !validateContent(history.get(i))) {
            isValid = false;
          }
          i++;
        }
        if (isValid) {
          validatedHistory.addAll(currentInput);
          validatedHistory.addAll(currentOutput);
          currentInput = new ArrayList<>();
          currentOutput = new ArrayList<>();
        }
      }
    }
    return validatedHistory;
  }

  /**
   * Validates the response from the model by checking that the response is not null and has at
   * least one candidate with content.
   *
   * @param response The response from the model to validate.
   * @return True if the response is valid, false otherwise.
   */
  private Boolean validateResponse(GenerateContentResponse response) {
    if (!response.candidates().isPresent()) {
      return false;
    }
    if (response.candidates().get().size() > 0
        && !response.candidates().get().get(0).content().isPresent()) {
      return false;
    }
    return true;
  }

  /**
   * Returns the chat history.
   *
   * @param curated Whether to return the curated history or the comprehensive history.
   *     Comprehensive history includes all messages, including empty or invalid parts. Curated
   *     history excludes empty or invalid parts.
   */
  public List<Content> getHistory(boolean curated) {
    if (curated) {
      return this.curatedHistory;
    } else {
      return this.comprehensiveHistory;
    }
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
   * @throws IOException if an I/O error occurs while making the API call
   * @throws HttpException if an HTTP error occurs while making the API call
   */
  public GenerateContentResponse sendMessage(String text, GenerateContentConfig config)
      throws IOException, HttpException {
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
   * @param content a {@link com.google.genai.types.Content} to send to the generative model
   * @param config a {@link com.google.genai.types.GenerateContentConfig} instance that specifies
   *     the optional configurations
   * @throws IOException if an I/O error occurs while making the API call
   * @throws HttpException if an HTTP error occurs while making the API call
   */
  public GenerateContentResponse sendMessage(Content content, GenerateContentConfig config)
      throws IOException, HttpException {
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
   * @param contents a {@link List<com.google.genai.types.Content>} to send to the generative model
   * @param config a {@link com.google.genai.types.GenerateContentConfig} instance that specifies
   *     the optional configurations
   * @throws IOException if an I/O error occurs while making the API call
   * @throws HttpException if an HTTP error occurs while making the API call
   */
  public GenerateContentResponse sendMessage(List<Content> contents, GenerateContentConfig config)
      throws IOException, HttpException {
    return privateSendMessage(contents, config);
  }

  private GenerateContentResponse privateSendMessage(
      List<Content> contents, GenerateContentConfig config) throws IOException, HttpException {

    List<Content> requestContents = new ArrayList<>();
    requestContents.addAll(this.curatedHistory);
    requestContents.addAll(contents);

    if (this.config != null && config == null) {
      config = this.config;
    }

    GenerateContentResponse response =
        this.models.generateContent(this.model, requestContents, config);
    List<Content> responseContents = new ArrayList<>();
    for (Candidate candidate : response.candidates().get()) {
      responseContents.add(candidate.content().get());
    }
    List<Content> currentHistory = new ArrayList<>();
    currentHistory.addAll(contents);
    currentHistory.addAll(responseContents);
    this.recordHistory(currentHistory, validateResponse(response));
    return response;
  }
}
