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

import com.google.genai.types.Content;
import com.google.genai.types.GenerateContentConfig;
import java.util.List;

/** A class for creating chat sessions. */
public final class ChatSession {
  private final ApiClient apiClient;

  ChatSession(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Creates a new chat session.
   *
   * @param model The model to use for the chat session.
   * @param config The configuration for the chat session.
   * @param history The history of previous messages in the chat session.
   * @return The chat session.
   */
  public Chat create(String model, GenerateContentConfig config, List<Content> history) {
    return new Chat(apiClient, model, config, history);
  }
}
