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
import com.google.common.base.Ascii;
import com.google.genai.Common.BuiltRequest;
import com.google.genai.errors.GenAiIOException;
import com.google.genai.types.CreateFileSearchStoreConfig;
import com.google.genai.types.CreateFileSearchStoreParameters;
import com.google.genai.types.DeleteFileSearchStoreConfig;
import com.google.genai.types.DeleteFileSearchStoreParameters;
import com.google.genai.types.FileSearchStore;
import com.google.genai.types.GetFileSearchStoreConfig;
import com.google.genai.types.GetFileSearchStoreParameters;
import com.google.genai.types.HttpOptions;
import com.google.genai.types.HttpResponse;
import com.google.genai.types.ImportFileConfig;
import com.google.genai.types.ImportFileOperation;
import com.google.genai.types.ImportFileParameters;
import com.google.genai.types.ListFileSearchStoresConfig;
import com.google.genai.types.ListFileSearchStoresParameters;
import com.google.genai.types.ListFileSearchStoresResponse;
import com.google.genai.types.UploadToFileSearchStoreConfig;
import com.google.genai.types.UploadToFileSearchStoreOperation;
import com.google.genai.types.UploadToFileSearchStoreParameters;
import com.google.genai.types.UploadToFileSearchStoreResumableResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import okhttp3.Headers;
import okhttp3.ResponseBody;

public final class FileSearchStores {
  public final Documents documents;

  final ApiClient apiClient;

  private final UploadClient uploadClient;

  public FileSearchStores(ApiClient apiClient) {
    this.apiClient = apiClient;
    this.uploadClient = new UploadClient(apiClient);
    this.documents = new Documents(apiClient);
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode createFileSearchStoreConfigToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper().createObjectNode();

    if (Common.getValueByPath(fromObject, new String[] {"displayName"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"displayName"},
          Common.getValueByPath(fromObject, new String[] {"displayName"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode createFileSearchStoreParametersToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper().createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"config"}) != null) {
      JsonNode unused =
          createFileSearchStoreConfigToMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"config"})),
              toObject);
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode deleteFileSearchStoreConfigToMldev(JsonNode fromObject, ObjectNode parentObject) {
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
  ObjectNode deleteFileSearchStoreParametersToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper().createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"name"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"_url", "name"},
          Common.getValueByPath(fromObject, new String[] {"name"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"config"}) != null) {
      JsonNode unused =
          deleteFileSearchStoreConfigToMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"config"})),
              toObject);
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode getFileSearchStoreParametersToMldev(JsonNode fromObject, ObjectNode parentObject) {
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
  ObjectNode importFileConfigToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper().createObjectNode();

    if (Common.getValueByPath(fromObject, new String[] {"customMetadata"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"customMetadata"},
          Common.getValueByPath(fromObject, new String[] {"customMetadata"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"chunkingConfig"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"chunkingConfig"},
          Common.getValueByPath(fromObject, new String[] {"chunkingConfig"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode importFileOperationFromMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper().createObjectNode();
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
          importFileResponseFromMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"response"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode importFileParametersToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper().createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"fileSearchStoreName"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"_url", "file_search_store_name"},
          Common.getValueByPath(fromObject, new String[] {"fileSearchStoreName"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"fileName"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"fileName"},
          Common.getValueByPath(fromObject, new String[] {"fileName"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"config"}) != null) {
      JsonNode unused =
          importFileConfigToMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"config"})),
              toObject);
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode importFileResponseFromMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper().createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"sdkHttpResponse"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"sdkHttpResponse"},
          Common.getValueByPath(fromObject, new String[] {"sdkHttpResponse"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"parent"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"parent"},
          Common.getValueByPath(fromObject, new String[] {"parent"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"documentName"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"documentName"},
          Common.getValueByPath(fromObject, new String[] {"documentName"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode listFileSearchStoresConfigToMldev(JsonNode fromObject, ObjectNode parentObject) {
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
  ObjectNode listFileSearchStoresParametersToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper().createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"config"}) != null) {
      JsonNode unused =
          listFileSearchStoresConfigToMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"config"})),
              toObject);
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode listFileSearchStoresResponseFromMldev(JsonNode fromObject, ObjectNode parentObject) {
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

    if (Common.getValueByPath(fromObject, new String[] {"fileSearchStores"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"fileSearchStores"},
          Common.getValueByPath(fromObject, new String[] {"fileSearchStores"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode uploadToFileSearchStoreConfigToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper().createObjectNode();

    if (Common.getValueByPath(fromObject, new String[] {"mimeType"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"mimeType"},
          Common.getValueByPath(fromObject, new String[] {"mimeType"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"displayName"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"displayName"},
          Common.getValueByPath(fromObject, new String[] {"displayName"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"customMetadata"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"customMetadata"},
          Common.getValueByPath(fromObject, new String[] {"customMetadata"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"chunkingConfig"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"chunkingConfig"},
          Common.getValueByPath(fromObject, new String[] {"chunkingConfig"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode uploadToFileSearchStoreParametersToMldev(
      JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper().createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"fileSearchStoreName"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"_url", "file_search_store_name"},
          Common.getValueByPath(fromObject, new String[] {"fileSearchStoreName"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"config"}) != null) {
      JsonNode unused =
          uploadToFileSearchStoreConfigToMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"config"})),
              toObject);
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode uploadToFileSearchStoreResumableResponseFromMldev(
      JsonNode fromObject, ObjectNode parentObject) {
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
  BuiltRequest buildRequestForCreate(CreateFileSearchStoreConfig config) {

    CreateFileSearchStoreParameters.Builder parameterBuilder =
        CreateFileSearchStoreParameters.builder();

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
      body = createFileSearchStoreParametersToMldev(parameterNode, null);
      if (body.get("_url") != null) {
        path = Common.formatMap("fileSearchStores", body.get("_url"));
      } else {
        path = "fileSearchStores";
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
  FileSearchStore processResponseForCreate(
      ApiResponse response, CreateFileSearchStoreConfig config) {
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

    return JsonSerializable.fromJsonNode(responseNode, FileSearchStore.class);
  }

  public FileSearchStore create(CreateFileSearchStoreConfig config) {
    BuiltRequest builtRequest = buildRequestForCreate(config);

    try (ApiResponse response =
        this.apiClient.request(
            "post", builtRequest.path(), builtRequest.body(), builtRequest.httpOptions())) {
      return processResponseForCreate(response, config);
    }
  }

  /** A shared buildRequest method for both sync and async methods. */
  BuiltRequest buildRequestForGet(String name, GetFileSearchStoreConfig config) {

    GetFileSearchStoreParameters.Builder parameterBuilder = GetFileSearchStoreParameters.builder();

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
      body = getFileSearchStoreParametersToMldev(parameterNode, null);
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
  FileSearchStore processResponseForGet(ApiResponse response, GetFileSearchStoreConfig config) {
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

    return JsonSerializable.fromJsonNode(responseNode, FileSearchStore.class);
  }

  public FileSearchStore get(String name, GetFileSearchStoreConfig config) {
    BuiltRequest builtRequest = buildRequestForGet(name, config);

    try (ApiResponse response =
        this.apiClient.request(
            "get", builtRequest.path(), builtRequest.body(), builtRequest.httpOptions())) {
      return processResponseForGet(response, config);
    }
  }

  /** A shared buildRequest method for both sync and async methods. */
  BuiltRequest buildRequestForDelete(String name, DeleteFileSearchStoreConfig config) {

    DeleteFileSearchStoreParameters.Builder parameterBuilder =
        DeleteFileSearchStoreParameters.builder();

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
      body = deleteFileSearchStoreParametersToMldev(parameterNode, null);
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

  public void delete(String name, DeleteFileSearchStoreConfig config) {
    BuiltRequest builtRequest = buildRequestForDelete(name, config);

    try (ApiResponse response =
        this.apiClient.request(
            "delete", builtRequest.path(), builtRequest.body(), builtRequest.httpOptions())) {
      return;
    }
  }

  /** A shared buildRequest method for both sync and async methods. */
  BuiltRequest buildRequestForPrivateList(ListFileSearchStoresConfig config) {

    ListFileSearchStoresParameters.Builder parameterBuilder =
        ListFileSearchStoresParameters.builder();

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
      body = listFileSearchStoresParametersToMldev(parameterNode, null);
      if (body.get("_url") != null) {
        path = Common.formatMap("fileSearchStores", body.get("_url"));
      } else {
        path = "fileSearchStores";
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
  ListFileSearchStoresResponse processResponseForPrivateList(
      ApiResponse response, ListFileSearchStoresConfig config) {
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
      responseNode = listFileSearchStoresResponseFromMldev(responseNode, null);
    }

    return JsonSerializable.fromJsonNode(responseNode, ListFileSearchStoresResponse.class);
  }

  ListFileSearchStoresResponse privateList(ListFileSearchStoresConfig config) {
    BuiltRequest builtRequest = buildRequestForPrivateList(config);

    try (ApiResponse response =
        this.apiClient.request(
            "get", builtRequest.path(), builtRequest.body(), builtRequest.httpOptions())) {
      return processResponseForPrivateList(response, config);
    }
  }

  /** A shared buildRequest method for both sync and async methods. */
  BuiltRequest buildRequestForPrivateUploadToFileSearchStore(
      String fileSearchStoreName, UploadToFileSearchStoreConfig config) {

    UploadToFileSearchStoreParameters.Builder parameterBuilder =
        UploadToFileSearchStoreParameters.builder();

    if (!Common.isZero(fileSearchStoreName)) {
      parameterBuilder.fileSearchStoreName(fileSearchStoreName);
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
      body = uploadToFileSearchStoreParametersToMldev(parameterNode, null);
      if (body.get("_url") != null) {
        path =
            Common.formatMap(
                "upload/v1beta/{file_search_store_name}:uploadToFileSearchStore", body.get("_url"));
      } else {
        path = "upload/v1beta/{file_search_store_name}:uploadToFileSearchStore";
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
  UploadToFileSearchStoreResumableResponse processResponseForPrivateUploadToFileSearchStore(
      ApiResponse response, UploadToFileSearchStoreConfig config) {
    ResponseBody responseBody = response.getBody();
    String responseString;
    try {
      responseString = responseBody.string();
    } catch (IOException e) {
      throw new GenAiIOException("Failed to read HTTP response.", e);
    }

    if (config != null && config.shouldReturnHttpResponse().orElse(false)) {
      Headers responseHeaders = response.getHeaders();
      if (responseHeaders == null) {
        return UploadToFileSearchStoreResumableResponse.builder()
            .sdkHttpResponse(HttpResponse.builder().body(responseString))
            .build();
      }
      Map<String, String> headers = new HashMap<>();
      for (String headerName : responseHeaders.names()) {
        headers.put(headerName, responseHeaders.get(headerName));
      }
      return UploadToFileSearchStoreResumableResponse.builder()
          .sdkHttpResponse(HttpResponse.builder().headers(headers).body(responseString))
          .build();
    }

    JsonNode responseNode = JsonSerializable.stringToJsonNode(responseString);

    if (this.apiClient.vertexAI()) {
      throw new UnsupportedOperationException(
          "This method is only supported in the Gemini Developer client.");
    }

    if (!this.apiClient.vertexAI()) {
      responseNode = uploadToFileSearchStoreResumableResponseFromMldev(responseNode, null);
    }

    return JsonSerializable.fromJsonNode(
        responseNode, UploadToFileSearchStoreResumableResponse.class);
  }

  UploadToFileSearchStoreResumableResponse privateUploadToFileSearchStore(
      String fileSearchStoreName, UploadToFileSearchStoreConfig config) {
    BuiltRequest builtRequest =
        buildRequestForPrivateUploadToFileSearchStore(fileSearchStoreName, config);

    try (ApiResponse response =
        this.apiClient.request(
            "post", builtRequest.path(), builtRequest.body(), builtRequest.httpOptions())) {
      return processResponseForPrivateUploadToFileSearchStore(response, config);
    }
  }

  /** A shared buildRequest method for both sync and async methods. */
  BuiltRequest buildRequestForImportFile(
      String fileSearchStoreName, String fileName, ImportFileConfig config) {

    ImportFileParameters.Builder parameterBuilder = ImportFileParameters.builder();

    if (!Common.isZero(fileSearchStoreName)) {
      parameterBuilder.fileSearchStoreName(fileSearchStoreName);
    }
    if (!Common.isZero(fileName)) {
      parameterBuilder.fileName(fileName);
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
      body = importFileParametersToMldev(parameterNode, null);
      if (body.get("_url") != null) {
        path = Common.formatMap("{file_search_store_name}:importFile", body.get("_url"));
      } else {
        path = "{file_search_store_name}:importFile";
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
  ImportFileOperation processResponseForImportFile(ApiResponse response, ImportFileConfig config) {
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
      responseNode = importFileOperationFromMldev(responseNode, null);
    }

    return JsonSerializable.fromJsonNode(responseNode, ImportFileOperation.class);
  }

  public ImportFileOperation importFile(
      String fileSearchStoreName, String fileName, ImportFileConfig config) {
    BuiltRequest builtRequest = buildRequestForImportFile(fileSearchStoreName, fileName, config);

    try (ApiResponse response =
        this.apiClient.request(
            "post", builtRequest.path(), builtRequest.body(), builtRequest.httpOptions())) {
      return processResponseForImportFile(response, config);
    }
  }

  /**
   * Makes an API request to list the available file search stores.
   *
   * @param config A {@link ListFileSearchStoresConfig} for configuring the list request.
   * @return A {@link Pager} object that contains the list of file search stores. The pager is an
   *     iterable and automatically queries the next page once the current page is exhausted.
   */
  @SuppressWarnings("PatternMatchingInstanceof")
  public Pager<FileSearchStore> list(ListFileSearchStoresConfig config) {
    if (config == null) {
      config = ListFileSearchStoresConfig.builder().build();
    }
    Function<JsonSerializable, Object> request =
        requestConfig -> {
          if (!(requestConfig instanceof ListFileSearchStoresConfig)) {
            throw new GenAiIOException(
                "Internal error: Pager expected ListFileSearchStoresConfig but received "
                    + requestConfig.getClass().getName());
          }
          return this.privateList((ListFileSearchStoresConfig) requestConfig);
        };
    return new Pager<>(
        Pager.PagedItem.FILE_SEARCH_STORES,
        request,
        (ObjectNode) JsonSerializable.toJsonNode(config),
        JsonSerializable.toJsonNode(privateList(config)));
  }

  static UploadToFileSearchStoreOperation operationFromResponse(ResponseBody responseBody) {
    String responseString;
    try {
      responseString = responseBody.string();
    } catch (IOException e) {
      throw new GenAiIOException("Failed to read HTTP response.", e);
    }
    JsonNode responseNode = JsonSerializable.stringToJsonNode(responseString);
    return JsonSerializable.fromJsonNode(responseNode, UploadToFileSearchStoreOperation.class);
  }

  private String getUploadUrl(
      String fileSearchStoreName,
      UploadToFileSearchStoreConfig config,
      Optional<String> mimeType,
      Optional<String> fileName,
      long size) {
    Optional<String> mimeTypeToUse =
        mimeType.isPresent()
            ? mimeType
            : Optional.ofNullable(config).flatMap(UploadToFileSearchStoreConfig::mimeType);
    Optional<HttpOptions> userHttpOptions =
        Optional.ofNullable(config).flatMap(UploadToFileSearchStoreConfig::httpOptions);
    HttpOptions httpOptions =
        UploadClient.buildResumableUploadHttpOptions(
            userHttpOptions, mimeTypeToUse, fileName, size);
    UploadToFileSearchStoreResumableResponse response =
        privateUploadToFileSearchStore(
            fileSearchStoreName,
            UploadToFileSearchStoreConfig.builder()
                .httpOptions(httpOptions)
                .shouldReturnHttpResponse(true)
                .build());

    return response
        .sdkHttpResponse()
        .flatMap(HttpResponse::headers)
        .flatMap(
            headers ->
                headers.entrySet().stream()
                    .filter(entry -> Ascii.equalsIgnoreCase("x-goog-upload-url", entry.getKey()))
                    .map(entry -> entry.getValue())
                    .findFirst())
        .orElseThrow(
            () ->
                new IllegalStateException(
                    "Failed to upload to file search store. Upload URL was not returned in the"
                        + " resumable response."));
  }

  /**
   * Uploads a file to the file search store.
   *
   * @param fileSearchStoreName The name of the file search store to upload to.
   * @param file The file to upload.
   * @param config The configuration for the upload.
   * @return The long running operation of uploading.
   */
  public UploadToFileSearchStoreOperation uploadToFileSearchStore(
      String fileSearchStoreName, java.io.File file, UploadToFileSearchStoreConfig config) {
    try (InputStream inputStream = new FileInputStream(file)) {
      long size = file.length();
      String fileName = file.getName();
      String probedMimeType = java.nio.file.Files.probeContentType(file.toPath());
      Optional<String> mimeType;
      if (probedMimeType != null) {
        mimeType = Optional.of(probedMimeType);
      } else {
        mimeType = Optional.empty();
      }
      String uploadUrl =
          getUploadUrl(fileSearchStoreName, config, mimeType, Optional.of(fileName), size);
      ResponseBody responseBody = uploadClient.upload(uploadUrl, inputStream, size);
      return operationFromResponse(responseBody);
    } catch (IOException e) {
      throw new GenAiIOException("Failed to upload file.", e);
    }
  }

  /**
   * Uploads a file in bytes format to the file search store.
   *
   * @param fileSearchStoreName The name of the file search store to upload to.
   * @param bytes The bytes of the file to upload.
   * @param config The configuration for the upload.
   * @return The long running operation of uploading.
   */
  public UploadToFileSearchStoreOperation uploadToFileSearchStore(
      String fileSearchStoreName, byte[] bytes, UploadToFileSearchStoreConfig config) {
    String uploadUrl =
        getUploadUrl(
            fileSearchStoreName,
            config,
            Optional.<String>empty(),
            Optional.<String>empty(),
            bytes.length);
    ResponseBody responseBody = uploadClient.upload(uploadUrl, bytes);
    return operationFromResponse(responseBody);
  }

  /**
   * Uploads a file as input stream to the API.
   *
   * @param fileSearchStoreName The name of the file search store to upload to.
   * @param inputStream The input stream of the file to upload.
   * @param size The size of the file to upload.
   * @param config The configuration for the upload.
   * @return The uploaded file.
   */
  public UploadToFileSearchStoreOperation uploadToFileSearchStore(
      String fileSearchStoreName,
      InputStream inputStream,
      long size,
      UploadToFileSearchStoreConfig config) {
    String uploadUrl =
        getUploadUrl(
            fileSearchStoreName, config, Optional.<String>empty(), Optional.<String>empty(), size);
    ResponseBody responseBody = uploadClient.upload(uploadUrl, inputStream, size);
    return operationFromResponse(responseBody);
  }

  /**
   * Uploads a file to the API.
   *
   * @param fileSearchStoreName The name of the file search store to upload to.
   * @param filePath The path of the file to upload.
   * @param config The configuration for the upload.
   * @return The uploaded file.
   */
  public UploadToFileSearchStoreOperation uploadToFileSearchStore(
      String fileSearchStoreName, String filePath, UploadToFileSearchStoreConfig config) {
    java.io.File file = new java.io.File(filePath);
    return uploadToFileSearchStore(fileSearchStoreName, file, config);
  }
}
