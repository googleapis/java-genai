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

import static com.google.common.base.Preconditions.checkNotNull;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.base.Ascii;
import com.google.genai.errors.GenAiIOException;
import com.google.genai.types.CreateFileConfig;
import com.google.genai.types.CreateFileParameters;
import com.google.genai.types.CreateFileResponse;
import com.google.genai.types.DeleteFileConfig;
import com.google.genai.types.DeleteFileParameters;
import com.google.genai.types.DeleteFileResponse;
import com.google.genai.types.DownloadFileConfig;
import com.google.genai.types.File;
import com.google.genai.types.GeneratedVideo;
import com.google.genai.types.GetFileConfig;
import com.google.genai.types.GetFileParameters;
import com.google.genai.types.HttpOptions;
import com.google.genai.types.HttpResponse;
import com.google.genai.types.ListFilesConfig;
import com.google.genai.types.ListFilesParameters;
import com.google.genai.types.ListFilesResponse;
import com.google.genai.types.UploadFileConfig;
import com.google.genai.types.Video;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import okhttp3.Headers;
import okhttp3.ResponseBody;

/**
 * Provides methods for interacting with the available GenAI files. Instantiating this class is not
 * required. After instantiating a {@link Client}, access methods through
 * `client.files.methodName(...)` directly.
 */
public final class Files {
  final ApiClient apiClient;

  private final UploadClient uploadClient;

  public Files(ApiClient apiClient) {
    this.apiClient = apiClient;
    this.uploadClient = new UploadClient(apiClient);
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode listFilesConfigToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();

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
  ObjectNode listFilesParametersToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"config"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"config"},
          listFilesConfigToMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"config"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode fileStatusToMldev(JsonNode fromObject, ObjectNode parentObject) {
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
  ObjectNode fileToMldev(JsonNode fromObject, ObjectNode parentObject) {
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
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"error"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode createFileParametersToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"file"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"file"},
          fileToMldev(
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
  ObjectNode getFileParametersToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"name"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"_url", "file"},
          Transformers.tFileName(Common.getValueByPath(fromObject, new String[] {"name"})));
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
  ObjectNode deleteFileParametersToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"name"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"_url", "file"},
          Transformers.tFileName(Common.getValueByPath(fromObject, new String[] {"name"})));
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
  ObjectNode fileStatusFromMldev(JsonNode fromObject, ObjectNode parentObject) {
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
  ObjectNode fileFromMldev(JsonNode fromObject, ObjectNode parentObject) {
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
          fileStatusFromMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"error"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode listFilesResponseFromMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
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

    if (Common.getValueByPath(fromObject, new String[] {"files"}) != null) {
      ArrayNode keyArray = (ArrayNode) Common.getValueByPath(fromObject, new String[] {"files"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      for (JsonNode item : keyArray) {
        result.add(fileFromMldev(JsonSerializable.toJsonNode(item), toObject));
      }
      Common.setValueByPath(toObject, new String[] {"files"}, result);
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode createFileResponseFromMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"sdkHttpResponse"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"sdkHttpResponse"},
          Common.getValueByPath(fromObject, new String[] {"sdkHttpResponse"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode deleteFileResponseFromMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"sdkHttpResponse"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"sdkHttpResponse"},
          Common.getValueByPath(fromObject, new String[] {"sdkHttpResponse"}));
    }

    return toObject;
  }

  /**
   * Lists all files from the service.
   *
   * @param config - Optional, configuration for the list method.
   * @return The ListFilesResponse, the response for the list method.
   */
  ListFilesResponse privateList(ListFilesConfig config) {

    ListFilesParameters.Builder parameterBuilder = ListFilesParameters.builder();

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
      body = listFilesParametersToMldev(parameterNode, null);
      if (body.get("_url") != null) {
        path = Common.formatMap("files", body.get("_url"));
      } else {
        path = "files";
      }
    }
    body.remove("_url");

    JsonNode queryParams = body.get("_query");
    if (queryParams != null) {
      body.remove("_query");
      path = String.format("%s?%s", path, Common.urlEncode((ObjectNode) queryParams));
    }

    // TODO: Remove the hack that removes config.
    body.remove("config");

    Optional<HttpOptions> requestHttpOptions = Optional.empty();
    if (config != null) {
      requestHttpOptions = config.httpOptions();
    }

    try (ApiResponse response =
        this.apiClient.request(
            "get", path, JsonSerializable.toJsonString(body), requestHttpOptions)) {
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
      } else {
        responseNode = listFilesResponseFromMldev(responseNode, null);
      }

      ListFilesResponse sdkResponse =
          JsonSerializable.fromJsonNode(responseNode, ListFilesResponse.class);
      Headers responseHeaders = response.getHeaders();
      if (responseHeaders == null) {
        return sdkResponse;
      }
      Map<String, String> headers = new HashMap<>();
      for (String headerName : responseHeaders.names()) {
        headers.put(headerName, responseHeaders.get(headerName));
      }
      return sdkResponse.toBuilder()
          .sdkHttpResponse(HttpResponse.builder().headers(headers))
          .build();
    }
  }

  CreateFileResponse privateCreate(File file, CreateFileConfig config) {

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
      throw new UnsupportedOperationException(
          "This method is only supported in the Gemini Developer client.");
    } else {
      body = createFileParametersToMldev(parameterNode, null);
      if (body.get("_url") != null) {
        path = Common.formatMap("upload/v1beta/files", body.get("_url"));
      } else {
        path = "upload/v1beta/files";
      }
    }
    body.remove("_url");

    JsonNode queryParams = body.get("_query");
    if (queryParams != null) {
      body.remove("_query");
      path = String.format("%s?%s", path, Common.urlEncode((ObjectNode) queryParams));
    }

    // TODO: Remove the hack that removes config.
    body.remove("config");

    Optional<HttpOptions> requestHttpOptions = Optional.empty();
    if (config != null) {
      requestHttpOptions = config.httpOptions();
    }

    try (ApiResponse response =
        this.apiClient.request(
            "post", path, JsonSerializable.toJsonString(body), requestHttpOptions)) {
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
          return CreateFileResponse.builder()
              .sdkHttpResponse(HttpResponse.builder().body(responseString))
              .build();
        }
        Map<String, String> headers = new HashMap<>();
        for (String headerName : responseHeaders.names()) {
          headers.put(headerName, responseHeaders.get(headerName));
        }
        return CreateFileResponse.builder()
            .sdkHttpResponse(HttpResponse.builder().headers(headers).body(responseString))
            .build();
      }

      JsonNode responseNode = JsonSerializable.stringToJsonNode(responseString);
      if (this.apiClient.vertexAI()) {
        throw new UnsupportedOperationException(
            "This method is only supported in the Gemini Developer client.");
      } else {
        responseNode = createFileResponseFromMldev(responseNode, null);
      }
      return JsonSerializable.fromJsonNode(responseNode, CreateFileResponse.class);
    }
  }

  /**
   * Retrieves the file information from the service.
   *
   * @param name - The name identifier for the file to retrieve.
   * @param config - Optional, configuration for the get method.
   * @return A File object representing the file.
   */
  public File get(String name, GetFileConfig config) {

    GetFileParameters.Builder parameterBuilder = GetFileParameters.builder();

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
      body = getFileParametersToMldev(parameterNode, null);
      if (body.get("_url") != null) {
        path = Common.formatMap("files/{file}", body.get("_url"));
      } else {
        path = "files/{file}";
      }
    }
    body.remove("_url");

    JsonNode queryParams = body.get("_query");
    if (queryParams != null) {
      body.remove("_query");
      path = String.format("%s?%s", path, Common.urlEncode((ObjectNode) queryParams));
    }

    // TODO: Remove the hack that removes config.
    body.remove("config");

    Optional<HttpOptions> requestHttpOptions = Optional.empty();
    if (config != null) {
      requestHttpOptions = config.httpOptions();
    }

    try (ApiResponse response =
        this.apiClient.request(
            "get", path, JsonSerializable.toJsonString(body), requestHttpOptions)) {
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
      } else {
        responseNode = fileFromMldev(responseNode, null);
      }
      return JsonSerializable.fromJsonNode(responseNode, File.class);
    }
  }

  /**
   * Deletes a remotely stored file.
   *
   * @param name - The name identifier for the file to delete.
   * @param config - Optional, configuration for the delete method.
   * @return The DeleteFileResponse, the response for the delete method.
   */
  public DeleteFileResponse delete(String name, DeleteFileConfig config) {

    DeleteFileParameters.Builder parameterBuilder = DeleteFileParameters.builder();

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
      body = deleteFileParametersToMldev(parameterNode, null);
      if (body.get("_url") != null) {
        path = Common.formatMap("files/{file}", body.get("_url"));
      } else {
        path = "files/{file}";
      }
    }
    body.remove("_url");

    JsonNode queryParams = body.get("_query");
    if (queryParams != null) {
      body.remove("_query");
      path = String.format("%s?%s", path, Common.urlEncode((ObjectNode) queryParams));
    }

    // TODO: Remove the hack that removes config.
    body.remove("config");

    Optional<HttpOptions> requestHttpOptions = Optional.empty();
    if (config != null) {
      requestHttpOptions = config.httpOptions();
    }

    try (ApiResponse response =
        this.apiClient.request(
            "delete", path, JsonSerializable.toJsonString(body), requestHttpOptions)) {
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
      } else {
        responseNode = deleteFileResponseFromMldev(responseNode, null);
      }

      DeleteFileResponse sdkResponse =
          JsonSerializable.fromJsonNode(responseNode, DeleteFileResponse.class);
      Headers responseHeaders = response.getHeaders();
      if (responseHeaders == null) {
        return sdkResponse;
      }
      Map<String, String> headers = new HashMap<>();
      for (String headerName : responseHeaders.names()) {
        headers.put(headerName, responseHeaders.get(headerName));
      }
      return sdkResponse.toBuilder()
          .sdkHttpResponse(HttpResponse.builder().headers(headers))
          .build();
    }
  }

  /**
   * Uploads a file to the API.
   *
   * @param file The file to upload.
   * @param config The configuration for the upload.
   * @return The uploaded file.
   */
  public File upload(java.io.File file, UploadFileConfig config) {
    try (InputStream inputStream = new FileInputStream(file)) {
      long size = file.length();
      String probedMimeType = java.nio.file.Files.probeContentType(file.toPath());
      Optional<String> mimeType;
      if (probedMimeType != null) {
        mimeType = Optional.of(probedMimeType);
      } else {
        mimeType = Optional.empty();
      }
      String uploadUrl = createFileInApi(config, mimeType, size);
      ResponseBody responseBody = uploadClient.upload(uploadUrl, inputStream, size);
      return fileFromUploadResponseBody(responseBody);
    } catch (IOException e) {
      throw new GenAiIOException("Failed to upload file.", e);
    }
  }

  /**
   * Uploads a file to the API.
   *
   * @param bytes The bytes of the file to upload.
   * @param config The configuration for the upload.
   * @return The uploaded file.
   */
  public File upload(byte[] bytes, UploadFileConfig config) {
    String uploadUrl = createFileInApi(config, Optional.<String>empty(), bytes.length);
    ResponseBody responseBody = uploadClient.upload(uploadUrl, bytes);
    return fileFromUploadResponseBody(responseBody);
  }

  /**
   * Uploads a file to the API.
   *
   * @param inputStream The input stream of the file to upload.
   * @param size The size of the file to upload.
   * @param config The configuration for the upload.
   * @return The uploaded file.
   */
  public File upload(InputStream inputStream, long size, UploadFileConfig config) {
    String uploadUrl = createFileInApi(config, Optional.<String>empty(), size);
    ResponseBody responseBody = uploadClient.upload(uploadUrl, inputStream, size);
    return fileFromUploadResponseBody(responseBody);
  }

  /**
   * Uploads a file to the API.
   *
   * @param filePath The path of the file to upload.
   * @param config The configuration for the upload.
   * @return The uploaded file.
   */
  public File upload(String filePath, UploadFileConfig config) {
    java.io.File file = new java.io.File(filePath);
    return upload(file, config);
  }

  private File fileFromUploadResponseBody(ResponseBody responseBody) {
    String responseString;
    try {
      responseString = responseBody.string();
    } catch (IOException e) {
      throw new GenAiIOException("Failed to read HTTP response.", e);
    }
    JsonNode responseNode = JsonSerializable.stringToJsonNode(responseString);
    responseNode = responseNode.get("file");
    responseNode = fileFromMldev(responseNode, null);

    return JsonSerializable.fromJsonNode(responseNode, File.class);
  }

  private String createFileInApi(UploadFileConfig config, Optional<String> mimeType, long size) {
    File.Builder apiFileBuilder = File.builder();
    if (config != null) {
      if (config.name().isPresent()) {
        apiFileBuilder.name(config.name().get());
      }
      if (config.mimeType().isPresent()) {
        apiFileBuilder.mimeType(config.mimeType().get());
      }
      if (config.displayName().isPresent()) {
        apiFileBuilder.displayName(config.displayName().get());
      }
    }

    File apiFile = apiFileBuilder.build();

    if (apiFile.name().isPresent() && !apiFile.name().get().startsWith("files/")) {
      apiFile = apiFile.toBuilder().name("files/" + apiFile.name().get()).build();
    }

    String actualMimeType =
        mimeType.orElse(
            apiFile
                .mimeType()
                .orElseThrow(
                    () ->
                        new IllegalArgumentException(
                            "Unknown mime type: Could not determine mime type for your file, please"
                                + " set the mimeType config argument")));

    Map<String, String> createFileHeaders = new HashMap<>();
    createFileHeaders.put("Content-Type", "application/json");
    createFileHeaders.put("X-Goog-Upload-Protocol", "resumable");
    createFileHeaders.put("X-Goog-Upload-Command", "start");
    createFileHeaders.put("X-Goog-Upload-Header-Content-Length", "" + size);
    createFileHeaders.put("X-Goog-Upload-Header-Content-Type", actualMimeType);

    HttpOptions createFileHttpOptions =
        HttpOptions.builder().apiVersion("").headers(createFileHeaders).build();

    CreateFileResponse createFileResponse =
        privateCreate(
            apiFile,
            CreateFileConfig.builder()
                .httpOptions(createFileHttpOptions)
                .shouldReturnHttpResponse(true)
                .build());

    return createFileResponse
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
                    "Failed to create file. Upload URL was not returned in the create file"
                        + " response."));
  }

  /**
   * Downloads a file from the API.
   *
   * @param fileName The name of the file to download.
   * @param downloadPath The path to download the file to.
   * @param config The configuration for the download.
   */
  public void download(String fileName, String downloadPath, DownloadFileConfig config) {
    checkNotNull(fileName);
    checkNotNull(downloadPath);
    String extractedFileName = Transformers.tFileName(fileName);
    downloadTo(extractedFileName, downloadPath, config);
  }

  /**
   * Downloads a video from the API.
   *
   * @param video The video to download.
   * @param downloadPath The path to download the video to.
   * @param config The configuration for the download.
   */
  public void download(Video video, String downloadPath, DownloadFileConfig config) {
    checkNotNull(video);
    checkNotNull(downloadPath);
    String extractedFileName = Transformers.tFileName(video);
    if (extractedFileName != null) {
      downloadTo(extractedFileName, downloadPath, config);
    } else {
      saveTo(
          video
              .videoBytes()
              .orElseThrow(() -> new IllegalArgumentException("Video bytes are required.")),
          downloadPath);
    }
  }

  /**
   * Downloads a file from the API.
   *
   * @param file The file to download.
   * @param downloadPath The path to download the file to.
   * @param config The configuration for the download.
   */
  public void download(File file, String downloadPath, DownloadFileConfig config) {
    checkNotNull(file);
    checkNotNull(downloadPath);
    String extractedFileName = Transformers.tFileName(file);
    downloadTo(extractedFileName, downloadPath, config);
  }

  /**
   * Downloads a generated video from the API.
   *
   * @param generatedVideo The generated video to download.
   * @param downloadPath The path to download the generated video to.
   * @param config The configuration for the download.
   */
  public void download(
      GeneratedVideo generatedVideo, String downloadPath, DownloadFileConfig config) {
    checkNotNull(generatedVideo);
    checkNotNull(downloadPath);
    String extractedFileName = Transformers.tFileName(generatedVideo);
    if (extractedFileName != null) {
      downloadTo(extractedFileName, downloadPath, config);
    } else {
      saveTo(
          generatedVideo
              .video()
              .orElseThrow(() -> new IllegalArgumentException("Video is required."))
              .videoBytes()
              .orElseThrow(() -> new IllegalArgumentException("Video bytes are required.")),
          downloadPath);
    }
  }

  private void downloadTo(String fileName, String downloadPath, DownloadFileConfig config) {
    Optional<HttpOptions> httpOptions = Optional.empty();
    if (config != null) {
      httpOptions = config.httpOptions();
    }
    ApiResponse response =
        this.apiClient.request(
            "get", String.format("files/%s:download?alt=media", fileName), "", httpOptions);
    try (FileOutputStream outputStream = new FileOutputStream(downloadPath)) {
      outputStream.write(response.getBody().bytes());
    } catch (IOException e) {
      throw new GenAiIOException("Failed to download file.", e);
    }
  }

  private void saveTo(byte[] bytes, String downloadPath) {
    try (FileOutputStream outputStream = new FileOutputStream(downloadPath)) {
      outputStream.write(bytes);
    } catch (IOException e) {
      throw new GenAiIOException("Failed to save file.", e);
    }
  }

  /**
   * makes an API request to list the available files.
   *
   * @param config A {@link ListFilesConfig} for configuring the list request.
   * @return A {@link Pager} object that contains the list of files. The pager is an iterable and
   *     automatically queries the next page once the current page is exhausted.
   */
  @SuppressWarnings("PatternMatchingInstanceof")
  public Pager<File> list(ListFilesConfig config) {
    Function<JsonSerializable, Object> request =
        requestConfig -> {
          if (!(requestConfig instanceof ListFilesConfig)) {
            throw new GenAiIOException(
                "Internal error: Pager expected ListFilesConfig but received "
                    + requestConfig.getClass().getName());
          }
          return this.privateList((ListFilesConfig) requestConfig);
        };
    return new Pager<>(
        Pager.PagedItem.FILES,
        request,
        (ObjectNode) JsonSerializable.toJsonNode(config),
        JsonSerializable.toJsonNode(privateList(config)));
  }
}
