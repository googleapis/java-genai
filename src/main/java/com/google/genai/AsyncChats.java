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

import com.google.common.collect.ImmutableList;
import com.google.genai.types.Content;
import com.google.genai.types.GenerateContentConfig;
import java.util.List;

/** A class for creating async chat sessions. */
public class AsyncChats {
  private final ApiClient apiClient;

  AsyncChats(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Creates a new async chat session that starts with the given history.
   *
   * <p>The history is sent to the model with every message, as if the turns in it had already taken
   * place in this session. It must start with a {@code user} turn, and every turn must have the
   * role {@code user} or {@code model}.
   *
   * @param model The model to use for the async chat session.
   * @param config The configuration for the async chat session, or null.
   * @param history The turns to start the session with.
   * @return The async chat session.
   * @throws IllegalArgumentException if the history does not start with a {@code user} turn, or has
   *     a turn whose role is not {@code user} or {@code model}.
   */
  public AsyncChat create(String model, GenerateContentConfig config, List<Content> history) {
    return privateCreate(model, config, history);
  }

  /**
   * Creates a new async chat session.
   *
   * @param model The model to use for the async chat session.
   * @param config The configuration for the async chat session.
   * @return The async chat session.
   */
  public AsyncChat create(String model, GenerateContentConfig config) {
    return privateCreate(model, config, ImmutableList.of());
  }

  /**
   * Creates a new async chat session.
   *
   * @param model The model to use for the async chat session.
   * @return The async chat session.
   */
  public AsyncChat create(String model) {
    return privateCreate(model, null, ImmutableList.of());
  }

  private AsyncChat privateCreate(
      String model, GenerateContentConfig config, List<Content> history) {
    return new AsyncChat(apiClient, model, config, history);
  }
}
