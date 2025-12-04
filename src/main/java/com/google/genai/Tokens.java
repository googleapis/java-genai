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
import com.google.common.collect.ImmutableList;
import com.google.genai.Common.BuiltRequest;
import com.google.genai.errors.GenAiIOException;
import com.google.genai.types.AuthToken;
import com.google.genai.types.CreateAuthTokenConfig;
import com.google.genai.types.CreateAuthTokenParameters;
import com.google.genai.types.HttpOptions;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import okhttp3.ResponseBody;

/** Provides methods for managing the ephemeral auth tokens. The tokens module is experimental. */
public class Tokens {

  private final ApiClient apiClient;

  Tokens(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Returns a comma-separated list of field masks from a given object.
   *
   * @param setup The object to extract field masks from.
   * @return A comma-separated list of field masks.
   */
  static String getFieldMasks(ObjectNode setup) {
    List<String> fields = new ArrayList<>();

    Iterator<Map.Entry<String, JsonNode>> fieldsIterator = setup.fields();
    while (fieldsIterator.hasNext()) {
      Map.Entry<String, JsonNode> entry = fieldsIterator.next();
      String key = entry.getKey();
      JsonNode value = entry.getValue();

      // 2nd layer, recursively get field masks
      if (value.isObject() && value.size() > 0) {
        Iterator<String> subKeys = value.fieldNames();
        while (subKeys.hasNext()) {
          String kk = subKeys.next();
          fields.add(key + "." + kk);
        }
      } else {
        fields.add(key); // 1st layer
      }
    }
    return String.join(",", fields);
  }

  /**
   * Converts the bidi setup in the config to the token setup.
   *
   * @param body The request body.
   * @param config The config of the create auth token request.
   * @return The modified request body.
   */
  ObjectNode convertBidiSetupToTokenSetup(ObjectNode body, CreateAuthTokenConfig config) {
    ObjectNode transformedBody = body.deepCopy();
    ObjectNode setupForMaskGeneration = null;
    JsonNode bidiVal = body.get("bidiGenerateContentSetup");

    // Handle extraction of the inner 'setup'
    // We assume the value in the map is compatible with ObjectNode per the requirements
    if (bidiVal != null && bidiVal.get("setup") != null) {
      JsonNode innerSetup = bidiVal.get("setup");
      if (innerSetup != null) {
        // Valid inner setup found.
        transformedBody.put("bidiGenerateContentSetup", innerSetup);
        setupForMaskGeneration = (ObjectNode) innerSetup;
      }
    } else {
      transformedBody.remove("bidiGenerateContentSetup");
    }

    // Extract Pre-existing field mask
    JsonNode preExistingFieldMask = body.get("fieldMask");
    List<String> preExistingFieldMaskList = new ArrayList<>();
    if (preExistingFieldMask != null && preExistingFieldMask.isArray()) {
      for (JsonNode element : preExistingFieldMask) {
        if (element.isTextual()) {
          preExistingFieldMaskList.add(element.asText());
        }
      }
    }

    // Handle mask generation setup.
    if (setupForMaskGeneration != null) {
      String generatedMaskFromBidi = getFieldMasks(setupForMaskGeneration);

      boolean configLockExists = config != null && config.lockAdditionalFields().isPresent();
      boolean preExistingValid =
          preExistingFieldMaskList != null && !preExistingFieldMaskList.isEmpty();

      if (configLockExists
          && config.lockAdditionalFields().isPresent()
          && config.lockAdditionalFields().get().isEmpty()) {
        // Case 1: lockAdditionalFields is an empty array. Lock only fields from bidi setup.
        if (generatedMaskFromBidi != null && !generatedMaskFromBidi.isEmpty()) {
          transformedBody.put("fieldMask", generatedMaskFromBidi);
        } else {
          transformedBody.remove("fieldMask");
        }

      } else if (configLockExists
          && config.lockAdditionalFields().isPresent()
          && !config.lockAdditionalFields().get().isEmpty()
          && preExistingValid) {
        // Case 2: Lock fields from bidi setup + additional fields.

        List<String> generationConfigFields =
            ImmutableList.of(
                "temperature",
                "topK",
                "top_k",
                "topP",
                "top_p",
                "maxOutputTokens",
                "max_output_tokens",
                "responseModalities",
                "response_modalities",
                "seed",
                "speechConfig",
                "speech_config");

        List<String> mappedFieldsFromPreExisting = new ArrayList<>();
        for (String field : preExistingFieldMaskList) {
          if (generationConfigFields.contains(field)) {
            mappedFieldsFromPreExisting.add("generationConfig." + field);
          } else {
            mappedFieldsFromPreExisting.add(field);
          }
        }

        List<String> finalMaskParts = new ArrayList<>();
        if (generatedMaskFromBidi != null && !generatedMaskFromBidi.isEmpty()) {
          finalMaskParts.add(generatedMaskFromBidi);
        }
        finalMaskParts.addAll(mappedFieldsFromPreExisting);

        if (!finalMaskParts.isEmpty()) {
          transformedBody.put("fieldMask", String.join(",", finalMaskParts));
        } else {
          transformedBody.remove("fieldMask");
        }

      } else {
        // Case 3: "Lock all fields"
        transformedBody.remove("fieldMask");
      }
    } else {
      // No valid `bidiGenerateContentSetup` found.
      if (preExistingFieldMaskList != null && !preExistingFieldMaskList.isEmpty()) {
        transformedBody.put("fieldMask", String.join(",", preExistingFieldMaskList));
      } else {
        transformedBody.remove("fieldMask");
      }
    }
    return transformedBody;
  }

  /** A shared buildRequest method for both sync and async methods. */
  BuiltRequest buildRequestForCreate(CreateAuthTokenConfig config) {

    CreateAuthTokenParameters.Builder parameterBuilder = CreateAuthTokenParameters.builder();
    if (!Common.isZero(config)) {
      parameterBuilder.config(config);
    }
    JsonNode parameterNode = JsonSerializable.toJsonNode(parameterBuilder.build());

    ObjectNode body;
    String path;
    TokensConverters tokensConverters = new TokensConverters(apiClient);
    if (this.apiClient.vertexAI()) {
      throw new UnsupportedOperationException(
          "This method is only supported in the Gemini Developer client.");
    } else {
      body = tokensConverters.createAuthTokenParametersToMldev(this.apiClient, parameterNode, null);
      if (body.get("_url") != null) {
        path = Common.formatMap("auth_tokens", body.get("_url"));
      } else {
        path = "auth_tokens";
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

    ObjectNode transformedBody = convertBidiSetupToTokenSetup(body, config);
    transformedBody.remove("config");
    return new BuiltRequest(
        path, JsonSerializable.toJsonString(transformedBody), requestHttpOptions);
  }

  /** A shared processResponse function for both sync and async methods. */
  AuthToken processResponseForCreate(ApiResponse response, CreateAuthTokenConfig config) {
    ResponseBody responseBody = response.getBody();
    String responseString;
    try {
      responseString = responseBody.string();
    } catch (IOException e) {
      throw new GenAiIOException("Failed to read HTTP response.", e);
    }

    JsonNode responseNode = JsonSerializable.stringToJsonNode(responseString);

    return JsonSerializable.fromJsonNode(responseNode, AuthToken.class);
  }

  /**
   * Creates an ephemeral auth token resource.
   *
   * @param config A {@link CreateAuthTokenConfig} for configuring the create request.
   * @return A {@link AuthToken} object that contains the info of the created resource.
   */
  public AuthToken create(CreateAuthTokenConfig config) {
    BuiltRequest builtRequest = buildRequestForCreate(config);

    try (ApiResponse response =
        this.apiClient.request(
            "post", builtRequest.path, builtRequest.body, builtRequest.httpOptions)) {
      return processResponseForCreate(response, config);
    }
  }
}
