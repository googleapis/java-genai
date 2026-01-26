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

// Auto-generated code. Do not edit.

package com.google.genai;

import com.google.genai.Common.BuiltRequest;
import com.google.genai.types.interactions.CancelInteractionConfig;
import com.google.genai.types.interactions.CreateInteractionConfig;
import com.google.genai.types.interactions.DeleteInteractionConfig;
import com.google.genai.types.interactions.DeleteInteractionResponse;
import com.google.genai.types.interactions.GetInteractionConfig;
import com.google.genai.types.interactions.Interaction;
import java.util.concurrent.CompletableFuture;

/**
 * Provides asynchronous methods for managing interactions. Instantiating this class is not
 * required. After instantiating a {@link Client}, access methods through
 * `client.async.interactions.methodName(...)` directly.
 *
 * <p>The Interactions API is available in both Vertex AI and Gemini API.
 *
 * <p>Note: The Interactions API is in beta and subject to change.
 */
public final class AsyncInteractions {
  Interactions interactions;
  ApiClient apiClient;

  public AsyncInteractions(ApiClient apiClient) {
    this.apiClient = apiClient;
    this.interactions = new Interactions(apiClient);
  }

  /**
   * Asynchronously creates a new interaction with the specified configuration.
   *
   * <p>Either {@code model} or {@code agent} must be specified in the config, but not both.
   *
   * <p>Example usage for model-based interaction:
   *
   * <pre>{@code
   * CreateInteractionConfig config = CreateInteractionConfig.builder()
   *     .model("gemini-2.5-flash")
   *     .input("What is the capital of France?")
   *     .build();
   * CompletableFuture<Interaction> future = client.async.interactions.create(config);
   * future.thenAccept(interaction -> {
   *     System.out.println("Response: " + interaction.outputs());
   * });
   * }</pre>
   *
   * <p>Example usage for agent-based interaction:
   *
   * <pre>{@code
   * CreateInteractionConfig config = CreateInteractionConfig.builder()
   *     .agent("deep-research-pro-preview-12-2025")
   *     .input("Research the history of quantum computing")
   *     .build();
   * CompletableFuture<Interaction> future = client.async.interactions.create(config);
   * }</pre>
   *
   * <p>Note: The Interactions API is in beta and subject to change.
   *
   * @param config The configuration for creating the interaction
   * @return A CompletableFuture containing the created Interaction with outputs
   * @throws IllegalArgumentException if both model and agent are specified, or neither is specified
   * @throws UnsupportedOperationException if using Vertex AI (not yet supported)
   */
  public CompletableFuture<Interaction> create(CreateInteractionConfig config) {
    BuiltRequest builtRequest = interactions.buildRequestForCreate(config);

    return this.apiClient
        .asyncRequest("post", builtRequest.path(), builtRequest.body(), builtRequest.httpOptions())
        .thenApplyAsync(
            response -> {
              try (ApiResponse res = response) {
                return interactions.processResponseForCreate(res, config);
              }
            });
  }

  /**
   * Asynchronously retrieves an interaction by its ID.
   *
   * <p>Example usage:
   *
   * <pre>{@code
   * GetInteractionConfig config = GetInteractionConfig.builder().build();
   * CompletableFuture<Interaction> future = client.async.interactions.get("interaction-id-123", config);
   * future.thenAccept(interaction -> {
   *     System.out.println("Status: " + interaction.status());
   * });
   * }</pre>
   *
   * <p>Note: The Interactions API is in beta and subject to change.
   *
   * @param id The ID of the interaction to retrieve
   * @param config The configuration for the get request (can be null)
   * @return A CompletableFuture containing the retrieved Interaction
   */
  public CompletableFuture<Interaction> get(String id, GetInteractionConfig config) {
    BuiltRequest builtRequest = interactions.buildRequestForGet(id, config);

    return this.apiClient
        .asyncRequest("get", builtRequest.path(), builtRequest.body(), builtRequest.httpOptions())
        .thenApplyAsync(
            response -> {
              try (ApiResponse res = response) {
                return interactions.processResponseForGet(res, config);
              }
            });
  }

  /**
   * Asynchronously cancels a background interaction that is still in progress.
   *
   * <p>Example usage:
   *
   * <pre>{@code
   * CancelInteractionConfig config = CancelInteractionConfig.builder().build();
   * CompletableFuture<Interaction> future = client.async.interactions.cancel("interaction-id-123", config);
   * future.thenAccept(cancelled -> {
   *     System.out.println("New status: " + cancelled.status());
   * });
   * }</pre>
   *
   * <p>Note: The Interactions API is in beta and subject to change.
   *
   * @param id The ID of the interaction to cancel
   * @param config The configuration for the cancel request (can be null)
   * @return A CompletableFuture containing the updated Interaction
   */
  public CompletableFuture<Interaction> cancel(String id, CancelInteractionConfig config) {
    BuiltRequest builtRequest = interactions.buildRequestForCancel(id, config);

    return this.apiClient
        .asyncRequest("post", builtRequest.path(), builtRequest.body(), builtRequest.httpOptions())
        .thenApplyAsync(
            response -> {
              try (ApiResponse res = response) {
                return interactions.processResponseForCancel(res, config);
              }
            });
  }

  /**
   * Asynchronously deletes an interaction by its ID.
   *
   * <p>Example usage:
   *
   * <pre>{@code
   * DeleteInteractionConfig config = DeleteInteractionConfig.builder().build();
   * CompletableFuture<DeleteInteractionResponse> future =
   *     client.async.interactions.delete("interaction-id-123", config);
   * }</pre>
   *
   * <p>Note: The Interactions API is in beta and subject to change.
   *
   * @param id The ID of the interaction to delete
   * @param config The configuration for the delete request (can be null)
   * @return A CompletableFuture containing the delete response
   */
  public CompletableFuture<DeleteInteractionResponse> delete(
      String id, DeleteInteractionConfig config) {
    BuiltRequest builtRequest = interactions.buildRequestForDelete(id, config);

    return this.apiClient
        .asyncRequest(
            "delete", builtRequest.path(), builtRequest.body(), builtRequest.httpOptions())
        .thenApplyAsync(
            response -> {
              try (ApiResponse res = response) {
                return interactions.processResponseForDelete(res, config);
              }
            });
  }
}
