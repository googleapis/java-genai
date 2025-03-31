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
import com.google.api.core.BetaApi;
import com.google.genai.errors.GenAiIOException;
import com.google.genai.types.FetchPredictOperationConfig;
import com.google.genai.types.FetchPredictOperationParameters;
import com.google.genai.types.GenerateVideosOperation;
import com.google.genai.types.GetOperationConfig;
import com.google.genai.types.GetOperationParameters;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;

public final class Operations {
  private final ApiClient apiClient;

  public Operations(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode GetOperationParametersToMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"operationName"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"_url", "operationName"},
          Common.getValueByPath(fromObject, new String[] {"operationName"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"config"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"config"},
          Common.getValueByPath(fromObject, new String[] {"config"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode GetOperationParametersToVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"operationName"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"_url", "operationName"},
          Common.getValueByPath(fromObject, new String[] {"operationName"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"config"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"config"},
          Common.getValueByPath(fromObject, new String[] {"config"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode FetchPredictOperationParametersToVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
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

    if (Common.getValueByPath(fromObject, new String[] {"config"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"config"},
          Common.getValueByPath(fromObject, new String[] {"config"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode VideoFromMldev(ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"video", "uri"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"uri"},
          Common.getValueByPath(fromObject, new String[] {"video", "uri"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"video", "encodedVideo"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"videoBytes"},
          Transformers.tBytes(
              this.apiClient,
              Common.getValueByPath(fromObject, new String[] {"video", "encodedVideo"})));
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
  ObjectNode GeneratedVideoFromMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"_self"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"video"},
          VideoFromMldev(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"_self"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode GenerateVideosResponseFromMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"generatedSamples"}) != null) {
      ArrayNode keyArray =
          (ArrayNode) Common.getValueByPath(fromObject, new String[] {"generatedSamples"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      keyArray.forEach(
          item -> {
            result.add(
                GeneratedVideoFromMldev(apiClient, JsonSerializable.toJsonNode(item), toObject));
          });
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
  ObjectNode GenerateVideosOperationFromMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
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
          GenerateVideosResponseFromMldev(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(
                      fromObject, new String[] {"response", "generateVideoResponse"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"response", "generateVideoResponse"})
        != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"result"},
          GenerateVideosResponseFromMldev(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(
                      fromObject, new String[] {"response", "generateVideoResponse"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode VideoFromVertex(ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
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
              this.apiClient,
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

  @ExcludeFromGeneratedCoverageReport
  ObjectNode GeneratedVideoFromVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"_self"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"video"},
          VideoFromVertex(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"_self"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode GenerateVideosResponseFromVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"videos"}) != null) {
      ArrayNode keyArray = (ArrayNode) Common.getValueByPath(fromObject, new String[] {"videos"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      keyArray.forEach(
          item -> {
            result.add(
                GeneratedVideoFromVertex(apiClient, JsonSerializable.toJsonNode(item), toObject));
          });
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
  ObjectNode GenerateVideosOperationFromVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
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
          GenerateVideosResponseFromVertex(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"response"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"response"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"result"},
          GenerateVideosResponseFromVertex(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"response"})),
              toObject));
    }

    return toObject;
  }

  private GenerateVideosOperation privateGetVideosOperation(
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
      body = GetOperationParametersToVertex(this.apiClient, parameterNode, null);
      path = Common.formatMap("{operationName}", body.get("_url"));
    } else {
      body = GetOperationParametersToMldev(this.apiClient, parameterNode, null);
      path = Common.formatMap("{operationName}", body.get("_url"));
    }
    body.remove("_url");
    // TODO: Remove the hack that removes config.
    body.remove("config");

    try (ApiResponse response = this.apiClient.post(path, JsonSerializable.toJsonString(body))) {
      HttpEntity entity = response.getEntity();
      String responseString;
      try {
        responseString = EntityUtils.toString(entity);
      } catch (IOException e) {
        throw new GenAiIOException("Failed to read HTTP response.", e);
      }
      JsonNode responseNode = JsonSerializable.stringToJsonNode(responseString);
      if (this.apiClient.vertexAI()) {
        responseNode = GenerateVideosOperationFromVertex(this.apiClient, responseNode, null);
      } else {
        responseNode = GenerateVideosOperationFromMldev(this.apiClient, responseNode, null);
      }
      return JsonSerializable.fromJsonNode(responseNode, GenerateVideosOperation.class);
    }
  }

  private GenerateVideosOperation privateFetchPredictVideosOperation(
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
      body = FetchPredictOperationParametersToVertex(this.apiClient, parameterNode, null);
      path = Common.formatMap("{resourceName}:fetchPredictOperation", body.get("_url"));
    } else {
      throw new UnsupportedOperationException(
          "This method is only supported by the Gemini Developer API.");
    }
    body.remove("_url");
    // TODO: Remove the hack that removes config.
    body.remove("config");

    try (ApiResponse response = this.apiClient.post(path, JsonSerializable.toJsonString(body))) {
      HttpEntity entity = response.getEntity();
      String responseString;
      try {
        responseString = EntityUtils.toString(entity);
      } catch (IOException e) {
        throw new GenAiIOException("Failed to read HTTP response.", e);
      }
      JsonNode responseNode = JsonSerializable.stringToJsonNode(responseString);
      if (this.apiClient.vertexAI()) {
        responseNode = GenerateVideosOperationFromVertex(this.apiClient, responseNode, null);
      } else {
        throw new UnsupportedOperationException(
            "This method is only supported by the Gemini Developer API.");
      }
      return JsonSerializable.fromJsonNode(responseNode, GenerateVideosOperation.class);
    }
  }

  /**
   * Gets the status of a GenerateVideosOperation.
   *
   * @param operation A GenerateVideosOperation.
   * @param config The configuration for getting the operation.
   * @return A GenerateVideosOperation with the updated status of the operation.
   */
  @BetaApi
  public GenerateVideosOperation getVideoOperation(
      GenerateVideosOperation operation, GetOperationConfig config) {

    if (!operation.name().isPresent()) {
      throw new Error("Operation name is required.");
    }

    if (this.apiClient.vertexAI()) {
      String resourceName = operation.name().get().split("/operations/")[0];

      FetchPredictOperationConfig fetchConfig = FetchPredictOperationConfig.builder().build();

      return this.privateFetchPredictVideosOperation(
          operation.name().get(), resourceName, fetchConfig);
    } else {
      return this.privateGetVideosOperation(operation.name().get(), config);
    }
  }
}
