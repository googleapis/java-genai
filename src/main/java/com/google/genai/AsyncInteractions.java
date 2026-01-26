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
import com.google.genai.types.interactions.GetInteractionConfig;
import com.google.genai.types.interactions.Input;
import com.google.genai.types.interactions.Interaction;
import com.google.genai.types.interactions.streaming.InteractionSseEvent;
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
    BuiltRequest builtRequest = interactions.buildRequestForCreate(config, Interactions.NON_STREAMING);

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
   * Asynchronously creates an interaction with a simple text input.
   *
   * <p>This is a convenience overload that wraps the text in a CreateInteractionConfig.
   *
   * <p>Example usage:
   *
   * <pre>{@code
   * CompletableFuture<Interaction> future = client.async.interactions.create(
   *     "gemini-2.5-flash",
   *     "What is the capital of France?");
   * future.thenAccept(interaction -> {
   *     System.out.println("Response: " + interaction.outputs());
   * });
   * }</pre>
   *
   * <p>Note: The Interactions API is in beta and subject to change.
   *
   * @param model The model name (e.g., "gemini-2.5-flash")
   * @param text The input text for the interaction
   * @return A CompletableFuture containing the created Interaction with outputs
   * @throws IllegalArgumentException if model or text is null or empty
   */
  public CompletableFuture<Interaction> create(String model, String text) {
    return create(CreateInteractionConfig.builder().model(model).input(text).build());
  }

  /**
   * Asynchronously creates an interaction with an Input object.
   *
   * <p>This is a convenience overload for multi-turn conversations and complex inputs.
   *
   * <p>Example usage:
   *
   * <pre>{@code
   * Input conversation = Input.fromTurns(
   *     Turn.user("What's the weather?"),
   *     Turn.model("I don't have access to weather data."),
   *     Turn.user("Then tell me a joke."));
   *
   * CompletableFuture<Interaction> future = client.async.interactions.create(
   *     "gemini-2.5-flash", conversation);
   * }</pre>
   *
   * <p>Note: The Interactions API is in beta and subject to change.
   *
   * @param model The model name (e.g., "gemini-2.5-flash")
   * @param input The Input object (from turns, contents, or string)
   * @return A CompletableFuture containing the created Interaction with outputs
   * @throws IllegalArgumentException if model or input is null
   */
  public CompletableFuture<Interaction> create(String model, Input input) {
    return create(CreateInteractionConfig.builder().model(model).input(input).build());
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
   * <p>The API returns an empty response on successful deletion. Success is indicated by the
   * CompletableFuture completing without exception.
   *
   * <p>Example usage:
   *
   * <pre>{@code
   * DeleteInteractionConfig config = DeleteInteractionConfig.builder().build();
   * CompletableFuture<Void> future =
   *     client.async.interactions.delete("interaction-id-123", config);
   * future.thenRun(() -> System.out.println("Interaction deleted successfully."));
   * }</pre>
   *
   * <p>Note: The Interactions API is in beta and subject to change.
   *
   * @param id The ID of the interaction to delete
   * @param config The configuration for the delete request (can be null)
   * @return A CompletableFuture that completes when the deletion is successful
   */
  public CompletableFuture<Void> delete(String id, DeleteInteractionConfig config) {
    BuiltRequest builtRequest = interactions.buildRequestForDelete(id, config);

    return this.apiClient
        .asyncRequest(
            "delete", builtRequest.path(), builtRequest.body(), builtRequest.httpOptions())
        .thenApplyAsync(
            response -> {
              try (ApiResponse res = response) {
                // Delete API returns empty JSON object {}
                // Success is indicated by no exception thrown
                return null;
              }
            });
  }

  /**
   * Asynchronously retrieves an interaction by its ID using default configuration.
   *
   * <p>This is a convenience overload equivalent to calling {@code get(id,
   * GetInteractionConfig.builder().build())}.
   *
   * <p>Example usage:
   *
   * <pre>{@code
   * CompletableFuture<Interaction> future = client.async.interactions.get("interaction-id-123");
   * future.thenAccept(interaction -> {
   *     System.out.println("Status: " + interaction.status());
   * });
   * }</pre>
   *
   * @param id The ID of the interaction to retrieve
   * @return A CompletableFuture containing the retrieved Interaction
   */
  public CompletableFuture<Interaction> get(String id) {
    return get(id, GetInteractionConfig.builder().build());
  }

  /**
   * Asynchronously cancels a background interaction that is still in progress using default
   * configuration.
   *
   * <p>This is a convenience overload equivalent to calling {@code cancel(id,
   * CancelInteractionConfig.builder().build())}.
   *
   * <p>Example usage:
   *
   * <pre>{@code
   * CompletableFuture<Interaction> future = client.async.interactions.cancel("interaction-id-123");
   * future.thenAccept(cancelled -> {
   *     System.out.println("New status: " + cancelled.status());
   * });
   * }</pre>
   *
   * @param id The ID of the interaction to cancel
   * @return A CompletableFuture containing the updated Interaction
   */
  public CompletableFuture<Interaction> cancel(String id) {
    return cancel(id, CancelInteractionConfig.builder().build());
  }

  /**
   * Asynchronously deletes an interaction by its ID using default configuration.
   *
   * <p>This is a convenience overload equivalent to calling {@code delete(id,
   * DeleteInteractionConfig.builder().build())}.
   *
   * <p>Example usage:
   *
   * <pre>{@code
   * CompletableFuture<Void> future =
   *     client.async.interactions.delete("interaction-id-123");
   * future.thenRun(() -> System.out.println("Interaction deleted successfully."));
   * }</pre>
   *
   * @param id The ID of the interaction to delete
   * @return A CompletableFuture that completes when the deletion is successful
   */
  public CompletableFuture<Void> delete(String id) {
    return delete(id, DeleteInteractionConfig.builder().build());
  }

  /**
   * Asynchronously creates a new streaming interaction with the specified configuration.
   *
   * <p>Returns a CompletableFuture that resolves to a stream of events that can be iterated over to
   * receive real-time updates as the model generates its response.
   *
   * <p>Either {@code model} or {@code agent} must be specified in the config, but not both.
   *
   * <p>Example usage:
   *
   * <pre>{@code
   * CreateInteractionConfig config = CreateInteractionConfig.builder()
   *     .model("gemini-2.5-flash")
   *     .input("Tell me a story")
   *     .build();
   *
   * client.async.interactions.createStream(config)
   *     .thenAccept(stream -> {
   *         try (stream) {
   *             for (InteractionSseEvent event : stream) {
   *                 if (event instanceof ContentDelta deltaEvent) {
   *                     Delta delta = deltaEvent.delta().orElse(null);
   *                     if (delta instanceof TextDelta textDelta) {
   *                         System.out.print(textDelta.text().orElse(""));
   *                     }
   *                 }
   *             }
   *         }
   *     });
   * }</pre>
   *
   * <p>Note: The Interactions API is in beta and subject to change.
   *
   * @param config The configuration for creating the interaction
   * @return A CompletableFuture containing a InteractionEventStream of InteractionSseEvent
   * @throws IllegalArgumentException if both model and agent are specified, or neither is specified
   */
  public CompletableFuture<InteractionEventStream<InteractionSseEvent>> createStream(
      CreateInteractionConfig config) {
    BuiltRequest builtRequest = interactions.buildRequestForCreate(config, Interactions.STREAMING);

    return this.apiClient
        .asyncRequest("post", builtRequest.path(), builtRequest.body(), builtRequest.httpOptions())
        .thenApplyAsync(
            response ->
                new InteractionEventStream<>(InteractionSseEvent.class, response));
  }

  /**
   * Asynchronously creates a streaming interaction with a simple text input.
   *
   * <p>This is a convenience overload that wraps the text in a CreateInteractionConfig.
   *
   * <p>Example usage:
   *
   * <pre>{@code
   * client.async.interactions.createStream("gemini-2.5-flash", "Tell me a story")
   *     .thenAccept(stream -> {
   *         try (stream) {
   *             for (InteractionSseEvent event : stream) {
   *                 if (event instanceof ContentDelta deltaEvent) {
   *                     Delta delta = deltaEvent.delta().orElse(null);
   *                     if (delta instanceof TextDelta textDelta) {
   *                         System.out.print(textDelta.text().orElse(""));
   *                     }
   *                 }
   *             }
   *         }
   *     });
   * }</pre>
   *
   * <p>Note: The Interactions API is in beta and subject to change.
   *
   * @param model The model name (e.g., "gemini-2.5-flash")
   * @param text The input text for the interaction
   * @return A CompletableFuture containing a InteractionEventStream of InteractionSseEvent
   * @throws IllegalArgumentException if model or text is null or empty
   */
  public CompletableFuture<InteractionEventStream<InteractionSseEvent>> createStream(
      String model, String text) {
    return createStream(CreateInteractionConfig.builder().model(model).input(text).build());
  }

  /**
   * Asynchronously creates a streaming interaction with an Input object.
   *
   * <p>This is a convenience overload for multi-turn conversations and complex inputs.
   *
   * <p>Example usage:
   *
   * <pre>{@code
   * Input conversation = Input.fromTurns(
   *     Turn.user("What's the weather?"),
   *     Turn.model("I don't have access to weather data."),
   *     Turn.user("Then tell me a joke."));
   *
   * client.async.interactions.createStream("gemini-2.5-flash", conversation)
   *     .thenAccept(stream -> {
   *         // Process streaming events...
   *     });
   * }</pre>
   *
   * <p>Note: The Interactions API is in beta and subject to change.
   *
   * @param model The model name (e.g., "gemini-2.5-flash")
   * @param input The Input object (from turns, contents, or string)
   * @return A CompletableFuture containing a InteractionEventStream of InteractionSseEvent
   * @throws IllegalArgumentException if model or input is null
   */
  public CompletableFuture<InteractionEventStream<InteractionSseEvent>> createStream(
      String model, Input input) {
    return createStream(CreateInteractionConfig.builder().model(model).input(input).build());
  }

  /**
   * Asynchronously retrieves a streaming interaction by its ID.
   *
   * <p>This method is primarily used for:
   *
   * <ul>
   *   <li>Stream resumption after connection interruption using {@code lastEventId}
   *   <li>Monitoring background interactions that were created with {@code background=true}
   * </ul>
   *
   * <p>Example usage for stream resumption:
   *
   * <pre>{@code
   * GetInteractionConfig resumeConfig = GetInteractionConfig.builder()
   *     .lastEventId(lastEventId)
   *     .build();
   *
   * client.async.interactions.getStream(interactionId, resumeConfig)
   *     .thenAccept(stream -> {
   *         try (stream) {
   *             for (InteractionSseEvent event : stream) {
   *                 // Continue processing from where we left off...
   *             }
   *         }
   *     });
   * }</pre>
   *
   * <p>Note: The Interactions API is in beta and subject to change.
   *
   * @param id The ID of the interaction to retrieve as a stream
   * @param config The configuration for the stream request (can be null)
   * @return A CompletableFuture containing a InteractionEventStream of InteractionSseEvent
   * @throws IllegalArgumentException if the ID is empty
   */
  public CompletableFuture<InteractionEventStream<InteractionSseEvent>> getStream(
      String id, GetInteractionConfig config) {
    BuiltRequest builtRequest = interactions.buildRequestForGetStream(id, config);

    return this.apiClient
        .asyncRequest("get", builtRequest.path(), builtRequest.body(), builtRequest.httpOptions())
        .thenApplyAsync(
            response ->
                new InteractionEventStream<>(InteractionSseEvent.class, response));
  }

  /**
   * Retrieves a streaming interaction by its ID (convenience overload).
   *
   * <p>This is a convenience method that calls {@link #getStream(String, GetInteractionConfig)}
   * with an empty configuration.
   *
   * <p>Use this when you don't need to specify additional configuration like lastEventId for
   * stream resumption.
   *
   * @param id The ID of the interaction to retrieve as a stream
   * @return A CompletableFuture containing a InteractionEventStream of InteractionSseEvent
   * @throws IllegalArgumentException if the ID is empty
   */
  public CompletableFuture<InteractionEventStream<InteractionSseEvent>> getStream(String id) {
    return getStream(id, GetInteractionConfig.builder().build());
  }
}
