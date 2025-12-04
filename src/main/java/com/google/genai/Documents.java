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
import com.google.genai.Common.BuiltRequest;
import com.google.genai.errors.GenAiIOException;
import com.google.genai.types.DeleteDocumentConfig;
import com.google.genai.types.DeleteDocumentParameters;
import com.google.genai.types.Document;
import com.google.genai.types.GetDocumentConfig;
import com.google.genai.types.GetDocumentParameters;
import com.google.genai.types.HttpOptions;
import com.google.genai.types.ListDocumentsConfig;
import com.google.genai.types.ListDocumentsParameters;
import com.google.genai.types.ListDocumentsResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.function.Function;
import okhttp3.ResponseBody;

public final class Documents {

  final ApiClient apiClient;

  public Documents(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode deleteDocumentConfigToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper().createObjectNode();

    if (Common.getValueByPath(fromObject, new String[] {"force"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"_query", "force"},
          Common.getValueByPath(fromObject, new String[] {"force"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode deleteDocumentParametersToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper().createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"name"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"_url", "name"},
          Common.getValueByPath(fromObject, new String[] {"name"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"config"}) != null) {
      JsonNode unused =
          deleteDocumentConfigToMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"config"})),
              toObject);
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode getDocumentParametersToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper().createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"name"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"_url", "name"},
          Common.getValueByPath(fromObject, new String[] {"name"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode listDocumentsConfigToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper().createObjectNode();

    if (Common.getValueByPath(fromObject, new String[] {"pageSize"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"_query", "pageSize"},
          Common.getValueByPath(fromObject, new String[] {"pageSize"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"pageToken"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"_query", "pageToken"},
          Common.getValueByPath(fromObject, new String[] {"pageToken"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode listDocumentsParametersToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper().createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"parent"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"_url", "parent"},
          Common.getValueByPath(fromObject, new String[] {"parent"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"config"}) != null) {
      JsonNode unused =
          listDocumentsConfigToMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"config"})),
              toObject);
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode listDocumentsResponseFromMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper().createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"sdkHttpResponse"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"sdkHttpResponse"},
          Common.getValueByPath(fromObject, new String[] {"sdkHttpResponse"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"nextPageToken"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"nextPageToken"},
          Common.getValueByPath(fromObject, new String[] {"nextPageToken"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"documents"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"documents"},
          Common.getValueByPath(fromObject, new String[] {"documents"}));
    }

    return toObject;
  }

  /** A shared buildRequest method for both sync and async methods. */
  BuiltRequest buildRequestForGet(String name, GetDocumentConfig config) {

    GetDocumentParameters.Builder parameterBuilder = GetDocumentParameters.builder();

    if (!Common.isZero(name)) {
      parameterBuilder.name(name);
    }
    if (!Common.isZero(config)) {
      parameterBuilder.config(config);
    }
    JsonNode parameterNode = JsonSerializable.toJsonNode(parameterBuilder.build());

    ObjectNode body;
    String path;
    if (this.apiClient.vertexAI()) {
      throw new UnsupportedOperationException(
          "This method is only supported in the Gemini Developer client.");
    } else {
      body = getDocumentParametersToMldev(parameterNode, null);
      if (body.get("_url") != null) {
        path = Common.formatMap("{name}", body.get("_url"));
      } else {
        path = "{name}";
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
  Document processResponseForGet(ApiResponse response, GetDocumentConfig config) {
    ResponseBody responseBody = response.getBody();
    String responseString;
    try {
      responseString = responseBody.string();
    } catch (IOException e) {
      throw new GenAiIOException("Failed to read HTTP response.", e);
    }

    JsonNode responseNode = JsonSerializable.stringToJsonNode(responseString);

    if (this.apiClient.vertexAI()) {
      throw new UnsupportedOperationException(
          "This method is only supported in the Gemini Developer client.");
    }

    return JsonSerializable.fromJsonNode(responseNode, Document.class);
  }

  public Document get(String name, GetDocumentConfig config) {
    BuiltRequest builtRequest = buildRequestForGet(name, config);

    try (ApiResponse response =
        this.apiClient.request(
            "get", builtRequest.path(), builtRequest.body(), builtRequest.httpOptions())) {
      return processResponseForGet(response, config);
    }
  }

  /** A shared buildRequest method for both sync and async methods. */
  BuiltRequest buildRequestForDelete(String name, DeleteDocumentConfig config) {

    DeleteDocumentParameters.Builder parameterBuilder = DeleteDocumentParameters.builder();

    if (!Common.isZero(name)) {
      parameterBuilder.name(name);
    }
    if (!Common.isZero(config)) {
      parameterBuilder.config(config);
    }
    JsonNode parameterNode = JsonSerializable.toJsonNode(parameterBuilder.build());

    ObjectNode body;
    String path;
    if (this.apiClient.vertexAI()) {
      throw new UnsupportedOperationException(
          "This method is only supported in the Gemini Developer client.");
    } else {
      body = deleteDocumentParametersToMldev(parameterNode, null);
      if (body.get("_url") != null) {
        path = Common.formatMap("{name}", body.get("_url"));
      } else {
        path = "{name}";
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

  public void delete(String name, DeleteDocumentConfig config) {
    BuiltRequest builtRequest = buildRequestForDelete(name, config);

    try (ApiResponse response =
        this.apiClient.request(
            "delete", builtRequest.path(), builtRequest.body(), builtRequest.httpOptions())) {
      return;
    }
  }

  /** A shared buildRequest method for both sync and async methods. */
  BuiltRequest buildRequestForPrivateList(String parent, ListDocumentsConfig config) {

    ListDocumentsParameters.Builder parameterBuilder = ListDocumentsParameters.builder();

    if (!Common.isZero(parent)) {
      parameterBuilder.parent(parent);
    }
    if (!Common.isZero(config)) {
      parameterBuilder.config(config);
    }
    JsonNode parameterNode = JsonSerializable.toJsonNode(parameterBuilder.build());

    ObjectNode body;
    String path;
    if (this.apiClient.vertexAI()) {
      throw new UnsupportedOperationException(
          "This method is only supported in the Gemini Developer client.");
    } else {
      body = listDocumentsParametersToMldev(parameterNode, null);
      if (body.get("_url") != null) {
        path = Common.formatMap("{parent}/documents", body.get("_url"));
      } else {
        path = "{parent}/documents";
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
  ListDocumentsResponse processResponseForPrivateList(
      ApiResponse response, ListDocumentsConfig config) {
    ResponseBody responseBody = response.getBody();
    String responseString;
    try {
      responseString = responseBody.string();
    } catch (IOException e) {
      throw new GenAiIOException("Failed to read HTTP response.", e);
    }

    JsonNode responseNode = JsonSerializable.stringToJsonNode(responseString);

    if (this.apiClient.vertexAI()) {
      throw new UnsupportedOperationException(
          "This method is only supported in the Gemini Developer client.");
    }

    if (!this.apiClient.vertexAI()) {
      responseNode = listDocumentsResponseFromMldev(responseNode, null);
    }

    return JsonSerializable.fromJsonNode(responseNode, ListDocumentsResponse.class);
  }

  ListDocumentsResponse privateList(String parent, ListDocumentsConfig config) {
    BuiltRequest builtRequest = buildRequestForPrivateList(parent, config);

    try (ApiResponse response =
        this.apiClient.request(
            "get", builtRequest.path(), builtRequest.body(), builtRequest.httpOptions())) {
      return processResponseForPrivateList(response, config);
    }
  }

  /**
   * Makes an API request to list the available documents.
   *
   * @param parent The name of the RagStore containing the Documents.
   * @param config A {@link ListDocumentsConfig} for configuring the list request.
   * @return A {@link Pager} object that contains the list of documents. The pager is an iterable
   *     and automatically queries the next page once the current page is exhausted.
   */
  @SuppressWarnings("PatternMatchingInstanceof")
  public Pager<Document> list(String parent, ListDocumentsConfig config) {
    if (config == null) {
      config = ListDocumentsConfig.builder().build();
    }
    Function<JsonSerializable, Object> request =
        requestConfig -> {
          if (!(requestConfig instanceof ListDocumentsConfig)) {
            throw new GenAiIOException(
                "Internal error: Pager expected ListDocumentsConfig but received "
                    + requestConfig.getClass().getName());
          }
          return this.privateList(parent, (ListDocumentsConfig) requestConfig);
        };
    return new Pager<>(
        Pager.PagedItem.DOCUMENTS,
        request,
        (ObjectNode) JsonSerializable.toJsonNode(config),
        JsonSerializable.toJsonNode(privateList(parent, config)));
  }
}
