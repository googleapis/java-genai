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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.genai.Common.BuiltRequest;
import com.google.genai.errors.GenAiIOException;
import com.google.genai.types.FetchPredictOperationConfig;
import com.google.genai.types.FetchPredictOperationParameters;
import com.google.genai.types.GenerateVideosOperation;
import com.google.genai.types.GetOperationConfig;
import com.google.genai.types.GetOperationParameters;
import com.google.genai.types.HttpOptions;
import com.google.genai.types.Operation;
import java.io.IOException;
import java.util.Optional;
import okhttp3.ResponseBody;

/**
 * Provides methods for managing the long-running operations. Instantiating this class is not
 * required. After instantiating a {@link Client}, access methods through
 * `client.operations.methodName(...)` directly.
 *
 * <p>This module is experimental.
 */
public final class Operations {

  final ApiClient apiClient;

  public Operations(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode fetchPredictOperationParametersToVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"operationName"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"operationName"},
          Common.getValueByPath(fromObject, new String[] {"operationName"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"resourceName"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"_url", "resourceName"},
          Common.getValueByPath(fromObject, new String[] {"resourceName"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode generateVideosOperationFromMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"name"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"name"},
          Common.getValueByPath(fromObject, new String[] {"name"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"metadata"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"metadata"},
          Common.getValueByPath(fromObject, new String[] {"metadata"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"done"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"done"},
          Common.getValueByPath(fromObject, new String[] {"done"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"error"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"error"},
          Common.getValueByPath(fromObject, new String[] {"error"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"response", "generateVideoResponse"})
        != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"response"},
          generateVideosResponseFromMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(
                      fromObject, new String[] {"response", "generateVideoResponse"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode generateVideosOperationFromVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"name"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"name"},
          Common.getValueByPath(fromObject, new String[] {"name"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"metadata"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"metadata"},
          Common.getValueByPath(fromObject, new String[] {"metadata"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"done"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"done"},
          Common.getValueByPath(fromObject, new String[] {"done"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"error"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"error"},
          Common.getValueByPath(fromObject, new String[] {"error"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"response"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"response"},
          generateVideosResponseFromVertex(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"response"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode generateVideosResponseFromMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"generatedSamples"}) != null) {
      ArrayNode keyArray =
          (ArrayNode) Common.getValueByPath(fromObject, new String[] {"generatedSamples"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      for (JsonNode item : keyArray) {
        result.add(generatedVideoFromMldev(JsonSerializable.toJsonNode(item), toObject));
      }
      Common.setValueByPath(toObject, new String[] {"generatedVideos"}, result);
    }

    if (Common.getValueByPath(fromObject, new String[] {"raiMediaFilteredCount"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"raiMediaFilteredCount"},
          Common.getValueByPath(fromObject, new String[] {"raiMediaFilteredCount"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"raiMediaFilteredReasons"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"raiMediaFilteredReasons"},
          Common.getValueByPath(fromObject, new String[] {"raiMediaFilteredReasons"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode generateVideosResponseFromVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"videos"}) != null) {
      ArrayNode keyArray = (ArrayNode) Common.getValueByPath(fromObject, new String[] {"videos"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      for (JsonNode item : keyArray) {
        result.add(generatedVideoFromVertex(JsonSerializable.toJsonNode(item), toObject));
      }
      Common.setValueByPath(toObject, new String[] {"generatedVideos"}, result);
    }

    if (Common.getValueByPath(fromObject, new String[] {"raiMediaFilteredCount"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"raiMediaFilteredCount"},
          Common.getValueByPath(fromObject, new String[] {"raiMediaFilteredCount"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"raiMediaFilteredReasons"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"raiMediaFilteredReasons"},
          Common.getValueByPath(fromObject, new String[] {"raiMediaFilteredReasons"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode generatedVideoFromMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"video"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"video"},
          videoFromMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"video"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode generatedVideoFromVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"_self"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"video"},
          videoFromVertex(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"_self"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode getOperationParametersToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"operationName"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"_url", "operationName"},
          Common.getValueByPath(fromObject, new String[] {"operationName"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode getOperationParametersToVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"operationName"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"_url", "operationName"},
          Common.getValueByPath(fromObject, new String[] {"operationName"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode videoFromMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"uri"}) != null) {
      Common.setValueByPath(
          toObject, new String[] {"uri"}, Common.getValueByPath(fromObject, new String[] {"uri"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"encodedVideo"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"videoBytes"},
          Transformers.tBytes(Common.getValueByPath(fromObject, new String[] {"encodedVideo"})));
    }

    if (Common.getValueByPath(fromObject, new String[] {"encoding"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"mimeType"},
          Common.getValueByPath(fromObject, new String[] {"encoding"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode videoFromVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"gcsUri"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"uri"},
          Common.getValueByPath(fromObject, new String[] {"gcsUri"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"bytesBase64Encoded"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"videoBytes"},
          Transformers.tBytes(
              Common.getValueByPath(fromObject, new String[] {"bytesBase64Encoded"})));
    }

    if (Common.getValueByPath(fromObject, new String[] {"mimeType"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"mimeType"},
          Common.getValueByPath(fromObject, new String[] {"mimeType"}));
    }

    return toObject;
  }

  /** A shared buildRequest method for both sync and async methods. */
  BuiltRequest buildRequestForPrivateGetVideosOperation(
      String operationName, GetOperationConfig config) {

    GetOperationParameters.Builder parameterBuilder = GetOperationParameters.builder();

    if (!Common.isZero(operationName)) {
      parameterBuilder.operationName(operationName);
    }
    if (!Common.isZero(config)) {
      parameterBuilder.config(config);
    }
    JsonNode parameterNode = JsonSerializable.toJsonNode(parameterBuilder.build());

    ObjectNode body;
    String path;
    if (this.apiClient.vertexAI()) {
      body = getOperationParametersToVertex(parameterNode, null);
      path = Common.formatMap("{operationName}", body.get("_url"));
    } else {
      body = getOperationParametersToMldev(parameterNode, null);
      if (body.get("_url") != null) {
        path = Common.formatMap("{operationName}", body.get("_url"));
      } else {
        path = "{operationName}";
      }
    }
    body.remove("_url");

    JsonNode queryParams = body.get("_query");
    if (queryParams != null) {
      body.remove("_query");
      path = String.format("%s?%s", path, Common.urlEncode((ObjectNode) queryParams));
    }

    // TODO: Remove the hack that removes config.
    Optional<HttpOptions> requestHttpOptions = Optional.empty();
    if (config != null) {
      requestHttpOptions = config.httpOptions();
    }

    return new BuiltRequest(path, JsonSerializable.toJsonString(body), requestHttpOptions);
  }

  /** A shared processResponse function for both sync and async methods. */
  JsonNode processResponseForPrivateGetVideosOperation(
      ApiResponse response, GetOperationConfig config) {
    ResponseBody responseBody = response.getBody();
    String responseString;
    try {
      responseString = responseBody.string();
    } catch (IOException e) {
      throw new GenAiIOException("Failed to read HTTP response.", e);
    }

    return JsonSerializable.stringToJsonNode(responseString);
  }

  JsonNode privateGetVideosOperation(String operationName, GetOperationConfig config) {
    BuiltRequest builtRequest = buildRequestForPrivateGetVideosOperation(operationName, config);

    try (ApiResponse response =
        this.apiClient.request(
            "get", builtRequest.path, builtRequest.body, builtRequest.httpOptions)) {
      return processResponseForPrivateGetVideosOperation(response, config);
    }
  }

  /** A shared buildRequest method for both sync and async methods. */
  BuiltRequest buildRequestForPrivateFetchPredictVideosOperation(
      String operationName, String resourceName, FetchPredictOperationConfig config) {

    FetchPredictOperationParameters.Builder parameterBuilder =
        FetchPredictOperationParameters.builder();

    if (!Common.isZero(operationName)) {
      parameterBuilder.operationName(operationName);
    }
    if (!Common.isZero(resourceName)) {
      parameterBuilder.resourceName(resourceName);
    }
    if (!Common.isZero(config)) {
      parameterBuilder.config(config);
    }
    JsonNode parameterNode = JsonSerializable.toJsonNode(parameterBuilder.build());

    ObjectNode body;
    String path;
    if (this.apiClient.vertexAI()) {
      body = fetchPredictOperationParametersToVertex(parameterNode, null);
      path = Common.formatMap("{resourceName}:fetchPredictOperation", body.get("_url"));
    } else {
      throw new UnsupportedOperationException(
          "This method is only supported in the Vertex AI client.");
    }
    body.remove("_url");

    JsonNode queryParams = body.get("_query");
    if (queryParams != null) {
      body.remove("_query");
      path = String.format("%s?%s", path, Common.urlEncode((ObjectNode) queryParams));
    }

    // TODO: Remove the hack that removes config.
    Optional<HttpOptions> requestHttpOptions = Optional.empty();
    if (config != null) {
      requestHttpOptions = config.httpOptions();
    }

    return new BuiltRequest(path, JsonSerializable.toJsonString(body), requestHttpOptions);
  }

  /** A shared processResponse function for both sync and async methods. */
  JsonNode processResponseForPrivateFetchPredictVideosOperation(
      ApiResponse response, FetchPredictOperationConfig config) {
    ResponseBody responseBody = response.getBody();
    String responseString;
    try {
      responseString = responseBody.string();
    } catch (IOException e) {
      throw new GenAiIOException("Failed to read HTTP response.", e);
    }

    return JsonSerializable.stringToJsonNode(responseString);
  }

  JsonNode privateFetchPredictVideosOperation(
      String operationName, String resourceName, FetchPredictOperationConfig config) {
    BuiltRequest builtRequest =
        buildRequestForPrivateFetchPredictVideosOperation(operationName, resourceName, config);

    try (ApiResponse response =
        this.apiClient.request(
            "post", builtRequest.path, builtRequest.body, builtRequest.httpOptions)) {
      return processResponseForPrivateFetchPredictVideosOperation(response, config);
    }
  }

  /**
   * Gets the status of a GenerateVideosOperation.
   *
   * @param operation A GenerateVideosOperation.
   * @param config The configuration for getting the operation.
   * @return A GenerateVideosOperation with the updated status of the operation.
   */
  public GenerateVideosOperation getVideosOperation(
      GenerateVideosOperation operation, GetOperationConfig config) {
    return get(operation, config);
  }

  /**
   * Gets the status of an Operation.
   *
   * @param operation An Operation.
   * @param config The configuration for getting the operation.
   * @return An Operation with the updated status of the operation.
   */
  public <T, U extends Operation<T, U>> U get(U operation, GetOperationConfig config) {
    if (!operation.name().isPresent()) {
      throw new IllegalArgumentException("Operation name is required.");
    }

    if (this.apiClient.vertexAI()) {
      String resourceName = operation.name().get().split("/operations/")[0];
      JsonNode response =
          this.privateFetchPredictVideosOperation(operation.name().get(), resourceName, null);
      return operation.fromApiResponse(response, true);
    } else {
      JsonNode response = this.privateGetVideosOperation(operation.name().get(), config);
      return operation.fromApiResponse(response, false);
    }
  }
}
