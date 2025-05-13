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
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.genai.errors.GenAiIOException;
import com.google.genai.types.CreateFileConfig;
import com.google.genai.types.CreateFileParameters;
import com.google.genai.types.CreateFileResponse;
import com.google.genai.types.File;
import com.google.genai.types.HttpOptions;
import com.google.genai.types.HttpResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;

/**
 * Provides methods for interacting with the available GenAI files. Instantiating this class is not
 * required. After instantiating a {@link Client}, access methods through
 * `client.files.methodName(...)` directly.
 */
public final class Files {
  private final ApiClient apiClient;

  public Files(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode fileStatusToMldev(ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"details"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"details"},
          Common.getValueByPath(fromObject, new String[] {"details"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"message"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"message"},
          Common.getValueByPath(fromObject, new String[] {"message"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"code"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"code"},
          Common.getValueByPath(fromObject, new String[] {"code"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode fileToMldev(ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"name"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"name"},
          Common.getValueByPath(fromObject, new String[] {"name"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"displayName"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"displayName"},
          Common.getValueByPath(fromObject, new String[] {"displayName"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"mimeType"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"mimeType"},
          Common.getValueByPath(fromObject, new String[] {"mimeType"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"sizeBytes"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"sizeBytes"},
          Common.getValueByPath(fromObject, new String[] {"sizeBytes"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"createTime"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"createTime"},
          Common.getValueByPath(fromObject, new String[] {"createTime"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"expirationTime"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"expirationTime"},
          Common.getValueByPath(fromObject, new String[] {"expirationTime"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"updateTime"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"updateTime"},
          Common.getValueByPath(fromObject, new String[] {"updateTime"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"sha256Hash"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"sha256Hash"},
          Common.getValueByPath(fromObject, new String[] {"sha256Hash"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"uri"}) != null) {
      Common.setValueByPath(
          toObject, new String[] {"uri"}, Common.getValueByPath(fromObject, new String[] {"uri"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"downloadUri"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"downloadUri"},
          Common.getValueByPath(fromObject, new String[] {"downloadUri"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"state"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"state"},
          Common.getValueByPath(fromObject, new String[] {"state"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"source"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"source"},
          Common.getValueByPath(fromObject, new String[] {"source"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"videoMetadata"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"videoMetadata"},
          Common.getValueByPath(fromObject, new String[] {"videoMetadata"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"error"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"error"},
          fileStatusToMldev(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"error"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode createFileParametersToMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"file"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"file"},
          fileToMldev(
              apiClient,
              JsonSerializable.toJsonNode(Common.getValueByPath(fromObject, new String[] {"file"})),
              toObject));
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
  ObjectNode createFileResponseFromMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();

    return toObject;
  }

  private CreateFileResponse privateCreate(File file, CreateFileConfig config) {

    CreateFileParameters.Builder parameterBuilder = CreateFileParameters.builder();

    if (!Common.isZero(file)) {
      parameterBuilder.file(file);
    }
    if (!Common.isZero(config)) {
      parameterBuilder.config(config);
    }
    JsonNode parameterNode = JsonSerializable.toJsonNode(parameterBuilder.build());

    ObjectNode body;
    String path;
    if (this.apiClient.vertexAI()) {
      throw new UnsupportedOperationException("This method is not supported by the Vertex AI.");
    } else {
      body = createFileParametersToMldev(this.apiClient, parameterNode, null);
      path = Common.formatMap("upload/v1beta/files", body.get("_url"));
    }
    body.remove("_url");

    // TODO: Handle "_query" in the body (for list support).

    // TODO: Remove the hack that removes config.
    body.remove("config");

    HttpOptions httpOptions = null;
    if (config != null) {
      httpOptions = config.httpOptions().orElse(null);
    }

    try (ApiResponse response =
        this.apiClient.request("post", path, JsonSerializable.toJsonString(body), httpOptions)) {
      HttpEntity entity = response.getEntity();
      String responseString;
      try {
        responseString = EntityUtils.toString(entity);
      } catch (IOException e) {
        throw new GenAiIOException("Failed to read HTTP response.", e);
      }

      if (config.shouldReturnHttpResponse().orElse(false)) {
        Map<String, String> headers = new HashMap<>();
        for (Header header : response.getHeaders()) {
          headers.put(header.getName(), header.getValue());
        }
        return CreateFileResponse.builder()
            .sdkHttpResponse(HttpResponse.builder().headers(headers).body(responseString).build())
            .build();
      }

      JsonNode responseNode = JsonSerializable.stringToJsonNode(responseString);
      if (this.apiClient.vertexAI()) {
        throw new UnsupportedOperationException("This method is not supported by the Vertex AI.");
      } else {
        responseNode = createFileResponseFromMldev(this.apiClient, responseNode, null);
      }
      return JsonSerializable.fromJsonNode(responseNode, CreateFileResponse.class);
    }
  }
}
