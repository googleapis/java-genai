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
import com.google.genai.types.interactions.CancelInteractionParameters;
import com.google.genai.types.interactions.CreateInteractionConfig;
import com.google.genai.types.interactions.CreateInteractionParameters;
import com.google.genai.types.interactions.DeleteInteractionConfig;
import com.google.genai.types.interactions.DeleteInteractionParameters;
import com.google.genai.types.interactions.DeleteInteractionResponse;
import com.google.genai.types.interactions.GetInteractionConfig;
import com.google.genai.types.interactions.GetInteractionParameters;
import com.google.genai.types.interactions.Interaction;
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

  public Interactions(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Copies a field from source to target if it exists, without transformation.
   *
   * @param fromObject Source JSON node
   * @param parentObject Target ObjectNode to populate
   * @param fieldName Name of the field to copy
   */
  private void copyFieldIfPresent(JsonNode fromObject, ObjectNode parentObject, String fieldName) {
    Object value = Common.getValueByPath(fromObject, new String[] {fieldName});
    if (value != null) {
      Common.setValueByPath(parentObject, new String[] {fieldName}, value);
    }
  }

  /**
   * Copies a field from source to target with a transformation function.
   *
   * @param fromObject Source JSON node
   * @param parentObject Target ObjectNode to populate
   * @param fieldName Name of the field to copy
   * @param transformer Function to transform the value
   */
  private void copyFieldWithTransform(
      JsonNode fromObject,
      ObjectNode parentObject,
      String fieldName,
      java.util.function.Function<Object, Object> transformer) {
    Object value = Common.getValueByPath(fromObject, new String[] {fieldName});
    if (value != null) {
      Common.setValueByPath(parentObject, new String[] {fieldName}, transformer.apply(value));
    }
  }

  /**
   * Transforms CreateInteractionConfig to the request body format. This transformation is identical
   * for both Vertex AI and MLDev platforms. Only the endpoint path differs (handled by
   * ApiClient.buildMaybeVertexPath).
   *
   * @param fromObject Source JSON node containing the config
   * @param parentObject Target ObjectNode to populate (modified in place)
   */
  @ExcludeFromGeneratedCoverageReport
  void createInteractionConfig(JsonNode fromObject, ObjectNode parentObject) {
    // Simple fields - no transformation
    // Note: Field names must match @JsonProperty annotations (snake_case)
    copyFieldIfPresent(fromObject, parentObject, "input");
    copyFieldIfPresent(fromObject, parentObject, "agent");
    copyFieldIfPresent(fromObject, parentObject, "background");
    copyFieldIfPresent(fromObject, parentObject, "generation_config");
    copyFieldIfPresent(fromObject, parentObject, "agent_config");
    copyFieldIfPresent(fromObject, parentObject, "previous_interaction_id");
    copyFieldIfPresent(fromObject, parentObject, "response_format");
    copyFieldIfPresent(fromObject, parentObject, "response_mime_type");
    copyFieldIfPresent(fromObject, parentObject, "response_modalities");
    copyFieldIfPresent(fromObject, parentObject, "store");
    // Tool types serialize directly with their type discriminator
    copyFieldIfPresent(fromObject, parentObject, "tools");

    // Fields with transformation
    // Note: Interactions API expects raw model string, not transformed
    copyFieldIfPresent(fromObject, parentObject, "model");
    copyFieldWithTransform(
        fromObject,
        parentObject,
        "system_instruction",
        value -> JsonSerializable.toJsonNode(Transformers.tContent(value)));
  }

  /**
   * Transforms CreateInteractionParameters to the request body. Platform-agnostic - works for both
   * Vertex AI and MLDev.
   *
   * @param fromObject Source JSON node containing the parameters
   * @return Transformed ObjectNode ready for the request body
   */
  @ExcludeFromGeneratedCoverageReport
  ObjectNode createInteractionParameters(JsonNode fromObject) {
    ObjectNode toObject = JsonSerializable.objectMapper().createObjectNode();

    if (Common.getValueByPath(fromObject, new String[] {"config"}) != null) {
      createInteractionConfig(
          JsonSerializable.toJsonNode(Common.getValueByPath(fromObject, new String[] {"config"})),
          toObject);
    }

    return toObject;
  }

  /** A shared buildRequest method for both sync and async methods. */
  BuiltRequest buildRequestForCreate(CreateInteractionConfig config) {
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

    CreateInteractionParameters.Builder parameterBuilder = CreateInteractionParameters.builder();

    if (!Common.isZero(config)) {
      parameterBuilder.config(config);
    }
    JsonNode parameterNode = JsonSerializable.toJsonNode(parameterBuilder.build());

    ObjectNode body = createInteractionParameters(parameterNode);
    String path = "interactions";

    // The "_query" key is used internally to store query parameters
    // that will be appended to the URL
    JsonNode queryParams = body.get("_query");
    if (queryParams != null) {
      body.remove("_query");
      path = String.format("%s?%s", path, Common.urlEncode((ObjectNode) queryParams));
    }

    Optional<HttpOptions> requestHttpOptions = Optional.empty();
    if (config != null) {
      requestHttpOptions = config.httpOptions();
    }

    String requestBody = JsonSerializable.toJsonString(body);
    return new BuiltRequest(path, requestBody, requestHttpOptions);
  }

  /** A shared processResponse function for both sync and async methods. */
  Interaction processResponseForCreate(ApiResponse response, CreateInteractionConfig config) {
    ResponseBody responseBody = response.getBody();
    String responseString;
    try {
      responseString = responseBody.string();
    } catch (IOException e) {
      throw new GenAiIOException("Failed to read HTTP response.", e);
    }

    JsonNode responseNode = JsonSerializable.stringToJsonNode(responseString);

    Interaction sdkResponse = JsonSerializable.fromJsonNode(responseNode, Interaction.class);
    Headers responseHeaders = response.getHeaders();
    if (responseHeaders == null) {
      return sdkResponse;
    }
    Map<String, String> headers = new HashMap<>();
    for (String headerName : responseHeaders.names()) {
      headers.put(headerName, responseHeaders.get(headerName));
    }
    return sdkResponse.toBuilder().sdkHttpResponse(HttpResponse.builder().headers(headers)).build();
  }

  /** A shared buildRequest method for both sync and async methods. */
  BuiltRequest buildRequestForGet(String id, GetInteractionConfig config) {

    // Validation: id must be non-empty
    if (id == null || id.isEmpty()) {
      throw new IllegalArgumentException("Interaction ID must not be empty");
    }

    GetInteractionParameters.Builder parameterBuilder = GetInteractionParameters.builder();

    if (!Common.isZero(id)) {
      parameterBuilder.id(id);
    }
    if (!Common.isZero(config)) {
      parameterBuilder.config(config);
    }
    JsonNode parameterNode = JsonSerializable.toJsonNode(parameterBuilder.build());

    // Transform ID parameter into URL placeholder using special "_url" key
    // The "_url" key is used internally to store URL path parameters
    ObjectNode body = transformIdParameter(parameterNode);

    String path = body.get("_url") != null ? Common.formatMap("{id}", body.get("_url")) : "{id}";
    body.remove("_url");

    // The "_query" key is used internally to store query parameters
    // that will be appended to the URL
    JsonNode queryParams = body.get("_query");
    if (queryParams != null) {
      body.remove("_query");
      path = String.format("%s?%s", path, Common.urlEncode((ObjectNode) queryParams));
    }

    Optional<HttpOptions> requestHttpOptions = Optional.empty();
    if (config != null) {
      requestHttpOptions = config.httpOptions();
    }

    return new BuiltRequest(path, JsonSerializable.toJsonString(body), requestHttpOptions);
  }

  /** A shared processResponse function for both sync and async methods. */
  Interaction processResponseForGet(ApiResponse response, GetInteractionConfig config) {
    ResponseBody responseBody = response.getBody();
    String responseString;
    try {
      responseString = responseBody.string();
    } catch (IOException e) {
      throw new GenAiIOException("Failed to read HTTP response.", e);
    }

    JsonNode responseNode = JsonSerializable.stringToJsonNode(responseString);

    Interaction sdkResponse = JsonSerializable.fromJsonNode(responseNode, Interaction.class);
    Headers responseHeaders = response.getHeaders();
    if (responseHeaders == null) {
      return sdkResponse;
    }
    Map<String, String> headers = new HashMap<>();
    for (String headerName : responseHeaders.names()) {
      headers.put(headerName, responseHeaders.get(headerName));
    }
    return sdkResponse.toBuilder().sdkHttpResponse(HttpResponse.builder().headers(headers)).build();
  }

  /** A shared buildRequest method for both sync and async methods. */
  BuiltRequest buildRequestForCancel(String id, CancelInteractionConfig config) {

    // Validation: id must be non-empty
    if (id == null || id.isEmpty()) {
      throw new IllegalArgumentException("Interaction ID must not be empty");
    }

    CancelInteractionParameters.Builder parameterBuilder = CancelInteractionParameters.builder();

    if (!Common.isZero(id)) {
      parameterBuilder.id(id);
    }
    if (!Common.isZero(config)) {
      parameterBuilder.config(config);
    }
    JsonNode parameterNode = JsonSerializable.toJsonNode(parameterBuilder.build());

    // Transform ID parameter into URL placeholder using special "_url" key
    // The "_url" key is used internally to store URL path parameters
    ObjectNode body = transformIdParameter(parameterNode);

    // Cancel uses POST to /interactions/{id}/cancel
    String path =
        body.get("_url") != null
            ? Common.formatMap("{id}/cancel", body.get("_url"))
            : "{id}/cancel";
    body.remove("_url");

    // The "_query" key is used internally to store query parameters
    // that will be appended to the URL
    JsonNode queryParams = body.get("_query");
    if (queryParams != null) {
      body.remove("_query");
      path = String.format("%s?%s", path, Common.urlEncode((ObjectNode) queryParams));
    }

    Optional<HttpOptions> requestHttpOptions = Optional.empty();
    if (config != null) {
      requestHttpOptions = config.httpOptions();
    }

    return new BuiltRequest(path, JsonSerializable.toJsonString(body), requestHttpOptions);
  }

  /** A shared processResponse function for both sync and async methods. */
  Interaction processResponseForCancel(ApiResponse response, CancelInteractionConfig config) {
    ResponseBody responseBody = response.getBody();
    String responseString;
    try {
      responseString = responseBody.string();
    } catch (IOException e) {
      throw new GenAiIOException("Failed to read HTTP response.", e);
    }

    JsonNode responseNode = JsonSerializable.stringToJsonNode(responseString);

    Interaction sdkResponse = JsonSerializable.fromJsonNode(responseNode, Interaction.class);
    Headers responseHeaders = response.getHeaders();
    if (responseHeaders == null) {
      return sdkResponse;
    }
    Map<String, String> headers = new HashMap<>();
    for (String headerName : responseHeaders.names()) {
      headers.put(headerName, responseHeaders.get(headerName));
    }
    return sdkResponse.toBuilder().sdkHttpResponse(HttpResponse.builder().headers(headers)).build();
  }

  /**
   * Shared transformer for interaction ID parameters (used by GET, CANCEL, DELETE operations).
   * Transforms the "id" field from the input into a URL parameter.
   *
   * @param fromObject Source JSON node containing the ID
   * @return Transformed ObjectNode with ID in _url.id path
   */
  @ExcludeFromGeneratedCoverageReport
  private ObjectNode transformIdParameter(JsonNode fromObject) {
    ObjectNode toObject = JsonSerializable.objectMapper().createObjectNode();

    if (Common.getValueByPath(fromObject, new String[] {"id"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"_url", "id"},
          Transformers.tInteractionId(
              this.apiClient, Common.getValueByPath(fromObject, new String[] {"id"})));
    }

    return toObject;
  }

  /**
   * Shared transformer for delete interaction responses. Extracts sdkHttpResponse from the API
   * response.
   *
   * @param fromObject Source JSON node from the API response
   * @return Transformed ObjectNode with sdkHttpResponse field
   */
  @ExcludeFromGeneratedCoverageReport
  private ObjectNode transformDeleteInteractionResponse(JsonNode fromObject) {
    ObjectNode toObject = JsonSerializable.objectMapper().createObjectNode();

    if (Common.getValueByPath(fromObject, new String[] {"sdkHttpResponse"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"sdkHttpResponse"},
          Common.getValueByPath(fromObject, new String[] {"sdkHttpResponse"}));
    }

    return toObject;
  }

  /** A shared buildRequest method for both sync and async methods. */
  BuiltRequest buildRequestForDelete(String id, DeleteInteractionConfig config) {

    // Validation: id must be non-empty
    if (id == null || id.isEmpty()) {
      throw new IllegalArgumentException("Interaction ID must not be empty");
    }

    DeleteInteractionParameters.Builder parameterBuilder = DeleteInteractionParameters.builder();

    if (!Common.isZero(id)) {
      parameterBuilder.id(id);
    }
    if (!Common.isZero(config)) {
      parameterBuilder.config(config);
    }
    JsonNode parameterNode = JsonSerializable.toJsonNode(parameterBuilder.build());

    // Transform ID parameter into URL placeholder using special "_url" key
    // The "_url" key is used internally to store URL path parameters
    ObjectNode body = transformIdParameter(parameterNode);

    String path = body.get("_url") != null ? Common.formatMap("{id}", body.get("_url")) : "{id}";
    body.remove("_url");

    // The "_query" key is used internally to store query parameters
    // that will be appended to the URL
    JsonNode queryParams = body.get("_query");
    if (queryParams != null) {
      body.remove("_query");
      path = String.format("%s?%s", path, Common.urlEncode((ObjectNode) queryParams));
    }

    Optional<HttpOptions> requestHttpOptions = Optional.empty();
    if (config != null) {
      requestHttpOptions = config.httpOptions();
    }

    return new BuiltRequest(path, JsonSerializable.toJsonString(body), requestHttpOptions);
  }

  /** A shared processResponse function for both sync and async methods. */
  DeleteInteractionResponse processResponseForDelete(
      ApiResponse response, DeleteInteractionConfig config) {
    ResponseBody responseBody = response.getBody();
    String responseString;
    try {
      responseString = responseBody.string();
    } catch (IOException e) {
      throw new GenAiIOException("Failed to read HTTP response.", e);
    }

    JsonNode responseNode = JsonSerializable.stringToJsonNode(responseString);

    // Transform response - identical for both platforms
    responseNode = transformDeleteInteractionResponse(responseNode);

    DeleteInteractionResponse sdkResponse =
        JsonSerializable.fromJsonNode(responseNode, DeleteInteractionResponse.class);
    Headers responseHeaders = response.getHeaders();
    if (responseHeaders == null) {
      return sdkResponse;
    }
    Map<String, String> headers = new HashMap<>();
    for (String headerName : responseHeaders.names()) {
      headers.put(headerName, responseHeaders.get(headerName));
    }
    return sdkResponse.toBuilder().sdkHttpResponse(HttpResponse.builder().headers(headers)).build();
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
    BuiltRequest builtRequest = buildRequestForCreate(config);

    try (ApiResponse response =
        this.apiClient.request(
            "post", builtRequest.path(), builtRequest.body(), builtRequest.httpOptions())) {
      return processResponseForCreate(response, config);
    }
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
   * @param id The ID of the interaction to cancel
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
   * <p>Example usage:
   *
   * <pre>{@code
   * DeleteInteractionConfig config = DeleteInteractionConfig.builder().build();
   * DeleteInteractionResponse response = client.interactions.delete("interaction-id-123", config);
   * }</pre>
   *
   * <p>Note: The Interactions API is in beta and subject to change.
   *
   * @param id The ID of the interaction to delete
   * @param config The configuration for the delete request (can be null)
   * @return The delete response with HTTP headers
   * @throws IllegalArgumentException if the ID is empty
   * @throws GenAiIOException if the API request fails
   */
  public DeleteInteractionResponse delete(String id, DeleteInteractionConfig config) {
    BuiltRequest builtRequest = buildRequestForDelete(id, config);

    try (ApiResponse response =
        this.apiClient.request(
            "delete", builtRequest.path(), builtRequest.body(), builtRequest.httpOptions())) {
      return processResponseForDelete(response, config);
    }
  }
}
