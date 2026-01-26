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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.genai.Common.BuiltRequest;
import com.google.genai.errors.GenAiIOException;
import com.google.genai.types.HttpOptions;
import com.google.genai.types.HttpResponse;
import com.google.genai.types.interactions.CancelInteractionConfig;
import com.google.genai.types.interactions.CreateInteractionConfig;
import com.google.genai.types.interactions.DeleteInteractionConfig;
import com.google.genai.types.interactions.GetInteractionConfig;
import com.google.genai.types.interactions.Input;
import com.google.genai.types.interactions.Interaction;
import com.google.genai.types.interactions.streaming.InteractionSseEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import okhttp3.Headers;
import okhttp3.ResponseBody;

/**
 * Provides methods for managing interactions. Instantiating this class is not required. After
 * instantiating a {@link Client}, access methods through `client.interactions.methodName(...)`
 * directly.
 *
 * <p>Note: The Interactions API is in beta and subject to change.
 */
public final class Interactions {

  final ApiClient apiClient;

  // Constants for streaming mode clarity
  static final boolean STREAMING = true;
  static final boolean NON_STREAMING = false;

  public Interactions(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Shared buildRequest method for both sync and async, streaming and non-streaming.
   *
   * @param config The interaction configuration
   * @param stream Whether to enable streaming mode
   * @return The built request ready for execution
   */
  BuiltRequest buildRequestForCreate(CreateInteractionConfig config, boolean stream) {

    // Validation: exactly one of model or agent must be specified
    boolean hasModel = config.model().isPresent() && !config.model().get().isEmpty();
    boolean hasAgent = config.agent().isPresent() && !config.agent().get().isEmpty();

    if (hasModel && hasAgent) {
      throw new IllegalArgumentException(
          "Cannot specify both 'model' and 'agent'. Please provide only one.");
    }

    if (!hasModel && !hasAgent) {
      throw new IllegalArgumentException(
          "Must specify either 'model' or 'agent' in CreateInteractionConfig.");
    }

    // Validation: agentConfig only with agent, generationConfig only with model
    if (hasAgent && config.generationConfig().isPresent()) {
      throw new IllegalArgumentException(
          "Cannot use 'generationConfig' with agent-based interactions. "
              + "Use 'agentConfig' instead.");
    }

    if (hasModel && config.agentConfig().isPresent()) {
      throw new IllegalArgumentException(
          "Cannot use 'agentConfig' with model-based interactions. "
              + "Use 'generationConfig' instead.");
    }

    // Build request body - serialize config directly
    ObjectNode body = (ObjectNode) JsonSerializable.toJsonNode(config);

    // Add stream parameter if needed
    if (stream) {
      body.put("stream", true);
    }

    String path = "interactions";

    // Build HTTP options with API version override if specified
    Optional<HttpOptions> requestHttpOptions = buildHttpOptions(config.apiVersion());

    String requestBody = JsonSerializable.toJsonString(body);
    return new BuiltRequest(path, requestBody, requestHttpOptions);
  }

  /**
   * Builds a request for streaming get (resumption or monitoring).
   *
   * <p>This method supports stream resumption via lastEventId.
   */
  BuiltRequest buildRequestForGetStream(String id, GetInteractionConfig config) {
    validateInteractionId(id);

    // Path construction - ApiClient handles Vertex AI or Gemini API prefixes
    String basePath = "interactions/" + id;

    // Build query parameters for streaming
    ObjectNode queryParams = JsonSerializable.objectMapper().createObjectNode();
    queryParams.put("stream", "true");

    // Add last_event_id for stream resumption (optional)
    if (config != null && config.lastEventId().isPresent()) {
      queryParams.put("last_event_id", config.lastEventId().get());
    }

    // Combine path with query parameters (e.g., "interactions/abc?stream=true&last_event_id=xyz")
    String path = String.format("%s?%s", basePath, Common.urlEncode(queryParams));

    // Build HTTP options with API version override if specified
    Optional<HttpOptions> httpOptions =
        buildHttpOptions(config != null ? config.apiVersion() : Optional.empty());

    return new BuiltRequest(path, "{}", httpOptions);
  }

  /** A shared processResponse function for both sync and async methods. */
  Interaction processResponseForCreate(ApiResponse response, CreateInteractionConfig config) {
    try {
      String responseString = response.getBody().string();
      return JsonSerializable.fromJsonNode(
          JsonSerializable.stringToJsonNode(responseString), Interaction.class);
    } catch (IOException e) {
      throw new GenAiIOException("Failed to read HTTP response.", e);
    }
  }

  /** A shared buildRequest method for both sync and async methods. */
  BuiltRequest buildRequestForGet(String id, GetInteractionConfig config) {
    validateInteractionId(id);

    // Path construction - ApiClient handles Vertex AI or Gemini API prefixes
    String path = "interactions/" + id;

    // Build HTTP options with API version override if specified
    Optional<HttpOptions> httpOptions =
        buildHttpOptions(config != null ? config.apiVersion() : Optional.empty());

    return new BuiltRequest(path, "{}", httpOptions);
  }

  /** A shared processResponse function for both sync and async methods. */
  Interaction processResponseForGet(ApiResponse response, GetInteractionConfig config) {
    try {
      String responseString = response.getBody().string();
      return JsonSerializable.fromJsonNode(
          JsonSerializable.stringToJsonNode(responseString), Interaction.class);
    } catch (IOException e) {
      throw new GenAiIOException("Failed to read HTTP response.", e);
    }
  }

  /** A shared buildRequest method for both sync and async methods. */
  BuiltRequest buildRequestForCancel(String id, CancelInteractionConfig config) {
    validateInteractionId(id);

    // Path construction - ApiClient handles Vertex AI or Gemini API prefixes
    String path = "interactions/" + id + "/cancel";

    // Build HTTP options with API version override if specified
    Optional<HttpOptions> httpOptions =
        buildHttpOptions(config != null ? config.apiVersion() : Optional.empty());

    return new BuiltRequest(path, "{}", httpOptions);
  }

  /** A shared processResponse function for both sync and async methods. */
  Interaction processResponseForCancel(ApiResponse response, CancelInteractionConfig config) {
    try {
      String responseString = response.getBody().string();
      return JsonSerializable.fromJsonNode(
          JsonSerializable.stringToJsonNode(responseString), Interaction.class);
    } catch (IOException e) {
      throw new GenAiIOException("Failed to read HTTP response.", e);
    }
  }


  /**
   * Validates that an interaction ID is not null or empty.
   *
   * @param id The interaction ID to validate
   * @throws IllegalArgumentException if the ID is null or empty
   */
  private void validateInteractionId(String id) {
    if (id == null || id.isEmpty()) {
      throw new IllegalArgumentException("Interaction ID must not be empty");
    }
  }

  /**
   * Builds HTTP options from an optional API version string.
   *
   * <p>Currently, only {@code apiVersion} is extracted from interaction configs to build {@link
   * HttpOptions}. This method can be extended if additional parameters are required.
   *
   * @param apiVersion The optional API version from any interaction config
   * @return Optional containing HttpOptions with apiVersion if present, empty otherwise
   */
  private Optional<HttpOptions> buildHttpOptions(Optional<String> apiVersion) {
    if (apiVersion != null && apiVersion.isPresent()) {
      return Optional.of(HttpOptions.builder().apiVersion(apiVersion.get()).build());
    }
    return Optional.empty();
  }

  /** A shared buildRequest method for both sync and async methods. */
  BuiltRequest buildRequestForDelete(String id, DeleteInteractionConfig config) {
    validateInteractionId(id);

    // Path construction - ApiClient handles Vertex AI or Gemini API prefixes
    String path = "interactions/" + id;

    // Build HTTP options with API version override if specified
    Optional<HttpOptions> httpOptions =
        buildHttpOptions(config != null ? config.apiVersion() : Optional.empty());

    return new BuiltRequest(path, "{}", httpOptions);
  }


  /**
   * Creates a new interaction with the specified configuration.
   *
   * <p>Either {@code model} or {@code agent} must be specified in the config, but not both.
   *
   * <p>When using function calling tools, the application must handle the function execution loop
   * manually. If the interaction returns {@code status: "requires_action"}, extract the function
   * calls from the outputs, execute them, and create a new interaction with the results using
   * {@code previousInteractionId}.
   *
   * <p>Example usage for model-based interaction:
   *
   * <pre>{@code
   * CreateInteractionConfig config = CreateInteractionConfig.builder()
   *     .model("gemini-2.5-flash")
   *     .input("What is the capital of France?")
   *     .build();
   * Interaction response = client.interactions.create(config);
   * }</pre>
   *
   * <p>Example usage for agent-based interaction:
   *
   * <pre>{@code
   * CreateInteractionConfig config = CreateInteractionConfig.builder()
   *     .agent("deep-research-pro-preview-12-2025")
   *     .input("Research the history of quantum computing")
   *     .build();
   * Interaction response = client.interactions.create(config);
   * }</pre>
   *
   * <p>Example usage with manual function calling:
   *
   * <pre>{@code
   * CreateInteractionConfig config = CreateInteractionConfig.builder()
   *     .model("gemini-2.5-flash")
   *     .input("What's the weather in Paris?")
   *     .tools(Function.of("getWeather", "Gets weather for a city", schema))
   *     .build();
   *
   * Interaction interaction = client.interactions.create(config);
   *
   * // Manual loop for function calling
   * while ("requires_action".equals(interaction.status().toString())) {
   *   // Extract and execute functions manually
   *   List<FunctionResultContent> results = executeFunctions(interaction);
   *
   *   // Continue conversation with results
   *   interaction = client.interactions.create(
   *     config.toBuilder()
   *       .previousInteractionId(interaction.id())
   *       .inputFromContents(results)
   *       .build()
   *   );
   * }
   * }</pre>
   *
   * <p>Note: The Interactions API is in beta and subject to change.
   *
   * @param config The configuration for creating the interaction
   * @return The created Interaction with outputs
   * @throws IllegalArgumentException if both model and agent are specified, or neither is specified
   * @throws GenAiIOException if the API request fails
   */
  public Interaction create(CreateInteractionConfig config) {
    BuiltRequest builtRequest = buildRequestForCreate(config, NON_STREAMING);

    try (ApiResponse response =
        this.apiClient.request(
            "post", builtRequest.path(), builtRequest.body(), builtRequest.httpOptions())) {
      return processResponseForCreate(response, config);
    }
  }

  /**
   * Creates an interaction with a simple text input.
   *
   * <p>This is a convenience method for the most common use case. For more complex scenarios, use
   * {@link #create(CreateInteractionConfig)}.
   *
   * <p>Example usage:
   *
   * <pre>{@code
   * Interaction response = client.interactions.create("gemini-2.5-flash", "What is 2+2?");
   * System.out.println(response.outputs());
   * }</pre>
   *
   * <p>Note: The Interactions API is in beta and subject to change.
   *
   * @param model The model name (e.g., "gemini-2.5-flash", "gemini-3-flash-preview")
   * @param text The input text for the interaction
   * @return The created Interaction with outputs
   * @throws IllegalArgumentException if model or text is null or empty
   * @throws GenAiIOException if the API request fails
   */
  public Interaction create(String model, String text) {
    return create(CreateInteractionConfig.builder().model(model).input(text).build());
  }

  /**
   * Creates an interaction with an Input object (turns, contents, or complex input).
   *
   * <p>Use this for multi-turn conversations or complex input types.
   *
   * <p>Example usage with multi-turn conversation:
   *
   * <pre>{@code
   * Input conversation = Input.fromTurns(
   *     Turn.user("Hello! My name is Alice."),
   *     Turn.model("Hello Alice! Nice to meet you."),
   *     Turn.user("What's my name?")
   * );
   * Interaction response = client.interactions.create("gemini-2.5-flash", conversation);
   * }</pre>
   *
   * <p>Example usage with multimodal content:
   *
   * <pre>{@code
   * Input input = Input.fromContents(
   *     TextContent.of("Describe this image in detail."),
   *     ImageContent.fromUri("https://example.com/image.jpg", "image/jpeg")
   * );
   * Interaction response = client.interactions.create("gemini-2.5-flash", input);
   * }</pre>
   *
   * <p>Note: The Interactions API is in beta and subject to change.
   *
   * @param model The model name
   * @param input The Input object (from turns, contents, or string)
   * @return The created Interaction with outputs
   * @throws IllegalArgumentException if model or input is null
   * @throws GenAiIOException if the API request fails
   */
  public Interaction create(String model, Input input) {
    return create(CreateInteractionConfig.builder().model(model).input(input).build());
  }

  /**
   * Retrieves an interaction by its ID.
   *
   * <p>Example usage:
   *
   * <pre>{@code
   * GetInteractionConfig config = GetInteractionConfig.builder().build();
   * Interaction interaction = client.interactions.get("interaction-id-123", config);
   * System.out.println("Status: " + interaction.status());
   * }</pre>
   *
   * <p>Note: The Interactions API is in beta and subject to change.
   *
   * @param id The ID of the interaction to retrieve
   * @param config The configuration for the get request (can be null)
   * @return The retrieved Interaction
   * @throws IllegalArgumentException if the ID is empty
   * @throws GenAiIOException if the API request fails
   */
  public Interaction get(String id, GetInteractionConfig config) {
    BuiltRequest builtRequest = buildRequestForGet(id, config);

    try (ApiResponse response =
        this.apiClient.request(
            "get", builtRequest.path(), builtRequest.body(), builtRequest.httpOptions())) {
      return processResponseForGet(response, config);
    }
  }

  /**
   * Cancels a background interaction that is still in progress.
   *
   * <p>Only applies to interactions created with background=true that are in IN_PROGRESS status.
   *
   * <p>Example usage:
   *
   * <pre>{@code
   * CancelInteractionConfig config = CancelInteractionConfig.builder().build();
   * Interaction cancelled = client.interactions.cancel("interaction-id-123", config);
   * System.out.println("New status: " + cancelled.status()); // Should be CANCELLED
   * }</pre>
   *
   * <p>Note: The Interactions API is in beta and subject to change.
   *
   * @param id The ID of the interaction to retrieve
   * @param config The configuration for the cancel request (can be null)
   * @return The updated Interaction with CANCELLED status
   * @throws IllegalArgumentException if the ID is empty
   * @throws GenAiIOException if the API request fails
   */
  public Interaction cancel(String id, CancelInteractionConfig config) {
    BuiltRequest builtRequest = buildRequestForCancel(id, config);

    try (ApiResponse response =
        this.apiClient.request(
            "post", builtRequest.path(), builtRequest.body(), builtRequest.httpOptions())) {
      return processResponseForCancel(response, config);
    }
  }

  /**
   * Deletes an interaction by its ID.
   *
   * <p>The API returns an empty response on successful deletion. Success is indicated by the
   * method completing without throwing an exception.
   *
   * <p>Example usage:
   *
   * <pre>{@code
   * DeleteInteractionConfig config = DeleteInteractionConfig.builder().build();
   * client.interactions.delete("interaction-id-123", config);
   * System.out.println("Interaction deleted successfully.");
   * }</pre>
   *
   * <p>Note: The Interactions API is in beta and subject to change.
   *
   * @param id The ID of the interaction to delete
   * @param config The configuration for the delete request (can be null)
   * @throws IllegalArgumentException if the ID is empty
   * @throws GenAiIOException if the API request fails
   */
  public void delete(String id, DeleteInteractionConfig config) {
    BuiltRequest builtRequest = buildRequestForDelete(id, config);

    try (ApiResponse response =
        this.apiClient.request(
            "delete", builtRequest.path(), builtRequest.body(), builtRequest.httpOptions())) {
      // Delete API returns empty JSON object {}
      // Success is indicated by no exception thrown
    }
  }

  /**
   * Retrieves an interaction by its ID using default configuration.
   *
   * <p>This is a convenience method equivalent to calling {@code get(id,
   * GetInteractionConfig.builder().build())}. For advanced options like API version override or
   * stream resumption, use {@link #get(String, GetInteractionConfig)}.
   *
   * <p>Example usage:
   *
   * <pre>{@code
   * Interaction interaction = client.interactions.get("interaction-id-123");
   * System.out.println("Status: " + interaction.status());
   * }</pre>
   *
   * @param id The ID of the interaction to retrieve
   * @return The retrieved Interaction
   * @throws IllegalArgumentException if the ID is empty
   * @throws GenAiIOException if the API request fails
   */
  public Interaction get(String id) {
    return get(id, GetInteractionConfig.builder().build());
  }

  /**
   * Cancels a background interaction that is still in progress using default configuration.
   *
   * <p>This is a convenience method equivalent to calling {@code cancel(id,
   * CancelInteractionConfig.builder().build())}. Only applies to interactions created with
   * background=true that are in IN_PROGRESS status.
   *
   * <p>Example usage:
   *
   * <pre>{@code
   * Interaction cancelled = client.interactions.cancel("interaction-id-123");
   * System.out.println("New status: " + cancelled.status());
   * }</pre>
   *
   * @param id The ID of the interaction to cancel
   * @return The updated Interaction with CANCELLED status
   * @throws IllegalArgumentException if the ID is empty
   * @throws GenAiIOException if the API request fails
   */
  public Interaction cancel(String id) {
    return cancel(id, CancelInteractionConfig.builder().build());
  }

  /**
   * Deletes an interaction by its ID using default configuration.
   *
   * <p>This is a convenience method equivalent to calling {@code delete(id,
   * DeleteInteractionConfig.builder().build())}. For API version override, use {@link
   * #delete(String, DeleteInteractionConfig)}.
   *
   * <p>Example usage:
   *
   * <pre>{@code
   * client.interactions.delete("interaction-id-123");
   * System.out.println("Interaction deleted successfully.");
   * }</pre>
   *
   * @param id The ID of the interaction to delete
   * @throws IllegalArgumentException if the ID is empty
   * @throws GenAiIOException if the API request fails
   */
  public void delete(String id) {
    delete(id, DeleteInteractionConfig.builder().build());
  }

  /**
   * Creates a new streaming interaction with the specified configuration.
   *
   * <p>Returns a stream of events that can be iterated over to receive real-time updates as the
   * model generates its response. This is useful for displaying responses incrementally or
   * processing large outputs without waiting for the complete response.
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
   * try (ResponseStream<InteractionSseEvent> stream =
   *         client.interactions.createStream(config)) {
   *     for (InteractionSseEvent event : stream) {
   *         if (event instanceof ContentDelta deltaEvent) {
   *             Delta delta = deltaEvent.delta().orElse(null);
   *             if (delta instanceof TextDelta textDelta) {
   *                 System.out.print(textDelta.text().orElse(""));
   *             }
   *         }
   *     }
   * }
   * }</pre>
   *
   * <p>Event sequence for a typical streaming interaction:
   *
   * <ol>
   *   <li>{@code InteractionEvent} with {@code eventType="interaction.start"} - Interaction has begun
   *   <li>{@code ContentStart} - A new content item is starting
   *   <li>{@code ContentDelta} - Incremental content updates (multiple)
   *   <li>{@code ContentStop} - Content item has completed
   *   <li>{@code InteractionEvent} with {@code eventType="interaction.complete"} - Interaction has finished
   * </ol>
   *
   * <p>Note: The Interactions API is in beta and subject to change.
   *
   * @param config The configuration for creating the interaction
   * @return A InteractionEventStream of InteractionSseEvent that can be iterated over
   * @throws IllegalArgumentException if both model and agent are specified, or neither is specified
   * @throws GenAiIOException if the API request fails
   */
  public InteractionEventStream<InteractionSseEvent> createStream(
      CreateInteractionConfig config) {
    BuiltRequest builtRequest = buildRequestForCreate(config, STREAMING);

    ApiResponse response =
        this.apiClient.request(
            "post", builtRequest.path(), builtRequest.body(), builtRequest.httpOptions());
    return new InteractionEventStream<>(InteractionSseEvent.class, response);
  }

  /**
   * Creates a streaming interaction with a simple text input.
   *
   * <p>This is a convenience method for the most common streaming use case. For more complex
   * scenarios, use {@link #createStream(CreateInteractionConfig)}.
   *
   * <p>Example usage:
   *
   * <pre>{@code
   * try (InteractionEventStream<InteractionSseEvent> stream =
   *     client.interactions.createStream("gemini-2.5-flash", "Tell me a story.")) {
   *   for (InteractionSseEvent event : stream) {
   *     if (event instanceof ContentDelta deltaEvent) {
   *       if (deltaEvent.delta().orElse(null) instanceof TextDelta textDelta) {
   *         System.out.print(textDelta.text().orElse(""));
   *       }
   *     }
   *   }
   * }
   * }</pre>
   *
   * <p>Note: The Interactions API is in beta and subject to change.
   *
   * @param model The model name (e.g., "gemini-2.5-flash", "gemini-3-flash-preview")
   * @param text The input text for the interaction
   * @return A stream of InteractionSseEvent objects
   * @throws IllegalArgumentException if model or text is null or empty
   * @throws GenAiIOException if the API request fails
   */
  public InteractionEventStream<InteractionSseEvent> createStream(String model, String text) {
    return createStream(CreateInteractionConfig.builder().model(model).input(text).build());
  }

  /**
   * Creates a streaming interaction with an Input object.
   *
   * <p>Use this for streaming multi-turn conversations or complex input types.
   *
   * <p>Example usage with multi-turn conversation:
   *
   * <pre>{@code
   * Input conversation = Input.fromTurns(
   *     Turn.user("You are a helpful assistant."),
   *     Turn.model("I'm here to help!"),
   *     Turn.user("Tell me about Java.")
   * );
   * try (InteractionEventStream<InteractionSseEvent> stream =
   *     client.interactions.createStream("gemini-2.5-flash", conversation)) {
   *   for (InteractionSseEvent event : stream) {
   *     // Process streaming events
   *   }
   * }
   * }</pre>
   *
   * <p>Example usage with multimodal content:
   *
   * <pre>{@code
   * Input input = Input.fromContents(
   *     TextContent.of("Describe this image."),
   *     ImageContent.fromUri("https://example.com/image.jpg", "image/jpeg")
   * );
   * try (InteractionEventStream<InteractionSseEvent> stream =
   *     client.interactions.createStream("gemini-2.5-flash", input)) {
   *   for (InteractionSseEvent event : stream) {
   *     // Process streaming events
   *   }
   * }
   * }</pre>
   *
   * <p>Note: The Interactions API is in beta and subject to change.
   *
   * @param model The model name
   * @param input The Input object (from turns, contents, or string)
   * @return A stream of InteractionSseEvent objects
   * @throws IllegalArgumentException if model or input is null
   * @throws GenAiIOException if the API request fails
   */
  public InteractionEventStream<InteractionSseEvent> createStream(String model, Input input) {
    return createStream(CreateInteractionConfig.builder().model(model).input(input).build());
  }

  /**
   * Retrieves a streaming interaction by its ID.
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
   * String lastEventId = null;
   * String interactionId = null;
   *
   * // First attempt - connection may be interrupted
   * try (var stream = client.interactions.createStream(config)) {
   *     for (InteractionSseEvent event : stream) {
   *         lastEventId = event.eventId().orElse(lastEventId);
   *         if (event instanceof InteractionEvent interactionEvent && interactionEvent.isStart()) {
   *             interactionId = interactionEvent.interaction()
   *                 .map(Interaction::id)
   *                 .orElse(null);
   *         }
   *         // Process event...
   *     }
   * } catch (Exception e) {
   *     // Connection lost - resume from last known event
   *     GetInteractionConfig resumeConfig = GetInteractionConfig.builder()
   *         .lastEventId(lastEventId)
   *         .build();
   *     try (var stream = client.interactions.getStream(interactionId, resumeConfig)) {
   *         for (InteractionSseEvent event : stream) {
   *             // Continue processing from where we left off...
   *         }
   *     }
   * }
   * }</pre>
   *
   * <p>Note: The Interactions API is in beta and subject to change.
   *
   * @param id The ID of the interaction to retrieve
   * @param config The configuration for the stream request (can be null)
   * @return A InteractionEventStream of InteractionSseEvent that can be iterated over
   * @throws IllegalArgumentException if the ID is empty
   * @throws GenAiIOException if the API request fails
   */
  public InteractionEventStream<InteractionSseEvent> getStream(
      String id, GetInteractionConfig config) {
    BuiltRequest builtRequest = buildRequestForGetStream(id, config);

    ApiResponse response =
        this.apiClient.request(
            "get", builtRequest.path(), builtRequest.body(), builtRequest.httpOptions());
    return new InteractionEventStream<>(InteractionSseEvent.class, response);
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
   * <p>Example usage:
   *
   * <pre>{@code
   * // Stream a background interaction
   * try (var stream = client.interactions.getStream(interactionId)) {
   *     for (InteractionSseEvent event : stream) {
   *         // Process streaming events...
   *     }
   * }
   * }</pre>
   *
   * @param id The ID of the interaction to retrieve as a stream
   * @return A InteractionEventStream of InteractionSseEvent that can be iterated over
   * @throws IllegalArgumentException if the ID is empty
   * @throws GenAiIOException if the API request fails
   */
  public InteractionEventStream<InteractionSseEvent> getStream(String id) {
    return getStream(id, GetInteractionConfig.builder().build());
  }
}
