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
import com.google.genai.types.DeleteFileSearchStoreConfig;
import com.google.genai.types.FileSearchStore;
import com.google.genai.types.GetFileSearchStoreConfig;
import com.google.genai.types.HttpOptions;
import com.google.genai.types.HttpResponse;
import com.google.genai.types.ImportFileConfig;
import com.google.genai.types.ImportFileOperation;
import com.google.genai.types.ListFileSearchStoresConfig;
import com.google.genai.types.ListFileSearchStoresResponse;
import com.google.genai.types.UploadToFileSearchStoreConfig;
import com.google.genai.types.UploadToFileSearchStoreOperation;
import com.google.genai.types.UploadToFileSearchStoreResumableResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/** Async module of {@link FileSearchStores} */
public final class AsyncFileSearchStores {
  public final AsyncDocuments documents;

  FileSearchStores fileSearchStores;
  ApiClient apiClient;
  private final UploadClient uploadClient;

  public AsyncFileSearchStores(ApiClient apiClient) {
    this.apiClient = apiClient;
    this.fileSearchStores = new FileSearchStores(apiClient);
    this.uploadClient = new UploadClient(apiClient);
    this.documents = new AsyncDocuments(apiClient);
  }

  public CompletableFuture<FileSearchStore> create(CreateFileSearchStoreConfig config) {

    BuiltRequest builtRequest = fileSearchStores.buildRequestForCreate(config);
    return this.apiClient
        .asyncRequest("post", builtRequest.path(), builtRequest.body(), builtRequest.httpOptions())
        .thenApplyAsync(
            response -> {
              try (ApiResponse res = response) {
                return fileSearchStores.processResponseForCreate(res, config);
              }
            });
  }

  public CompletableFuture<FileSearchStore> get(String name, GetFileSearchStoreConfig config) {

    BuiltRequest builtRequest = fileSearchStores.buildRequestForGet(name, config);
    return this.apiClient
        .asyncRequest("get", builtRequest.path(), builtRequest.body(), builtRequest.httpOptions())
        .thenApplyAsync(
            response -> {
              try (ApiResponse res = response) {
                return fileSearchStores.processResponseForGet(res, config);
              }
            });
  }

  public CompletableFuture<Void> delete(String name, DeleteFileSearchStoreConfig config) {

    BuiltRequest builtRequest = fileSearchStores.buildRequestForDelete(name, config);
    return this.apiClient
        .asyncRequest(
            "delete", builtRequest.path(), builtRequest.body(), builtRequest.httpOptions())
        .thenAccept(
            response -> {
              try (ApiResponse res = response) {}
            });
  }

  CompletableFuture<ListFileSearchStoresResponse> privateList(ListFileSearchStoresConfig config) {

    BuiltRequest builtRequest = fileSearchStores.buildRequestForPrivateList(config);
    return this.apiClient
        .asyncRequest("get", builtRequest.path(), builtRequest.body(), builtRequest.httpOptions())
        .thenApplyAsync(
            response -> {
              try (ApiResponse res = response) {
                return fileSearchStores.processResponseForPrivateList(res, config);
              }
            });
  }

  CompletableFuture<UploadToFileSearchStoreResumableResponse> privateUploadToFileSearchStore(
      String fileSearchStoreName, UploadToFileSearchStoreConfig config) {

    BuiltRequest builtRequest =
        fileSearchStores.buildRequestForPrivateUploadToFileSearchStore(fileSearchStoreName, config);
    return this.apiClient
        .asyncRequest("post", builtRequest.path(), builtRequest.body(), builtRequest.httpOptions())
        .thenApplyAsync(
            response -> {
              try (ApiResponse res = response) {
                return fileSearchStores.processResponseForPrivateUploadToFileSearchStore(
                    res, config);
              }
            });
  }

  public CompletableFuture<ImportFileOperation> importFile(
      String fileSearchStoreName, String fileName, ImportFileConfig config) {

    BuiltRequest builtRequest =
        fileSearchStores.buildRequestForImportFile(fileSearchStoreName, fileName, config);
    return this.apiClient
        .asyncRequest("post", builtRequest.path(), builtRequest.body(), builtRequest.httpOptions())
        .thenApplyAsync(
            response -> {
              try (ApiResponse res = response) {
                return fileSearchStores.processResponseForImportFile(res, config);
              }
            });
  }

  /**
   * Asynchronously makes an API request to list the available file search stores.
   *
   * @param config A {@link ListFileSearchStoresConfig} for configuring the list request.
   * @return A CompletableFuture that resolves to a {@link AsyncPager}. The AsyncPager has a
   *     `forEach` method that can be used to asynchronously process items in the page and
   *     automatically query the next page once the current page is exhausted.
   */
  @SuppressWarnings("PatternMatchingInstanceof")
  public CompletableFuture<AsyncPager<FileSearchStore>> list(ListFileSearchStoresConfig config) {
    if (config == null) {
      config = ListFileSearchStoresConfig.builder().build();
    }
    ListFileSearchStoresConfig finalConfig = config;
    Function<JsonSerializable, CompletableFuture<JsonNode>> request =
        requestConfig -> {
          if (!(requestConfig instanceof ListFileSearchStoresConfig)) {
            throw new GenAiIOException(
                "Internal error: Pager expected ListFileSearchStoresConfig but received "
                    + requestConfig.getClass().getName());
          }
          return this.privateList((ListFileSearchStoresConfig) requestConfig)
              .thenApply(JsonSerializable::toJsonNode);
        };
    return CompletableFuture.supplyAsync(
        () ->
            new AsyncPager<FileSearchStore>(
                Pager.PagedItem.FILE_SEARCH_STORES,
                request,
                (ObjectNode) JsonSerializable.toJsonNode(finalConfig),
                request.apply(finalConfig)));
  }

  private CompletableFuture<String> getUploadUrl(
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
    return privateUploadToFileSearchStore(
            fileSearchStoreName,
            UploadToFileSearchStoreConfig.builder()
                .httpOptions(httpOptions)
                .shouldReturnHttpResponse(true)
                .build())
        .thenApply(
            response ->
                response
                    .sdkHttpResponse()
                    .flatMap(HttpResponse::headers)
                    .flatMap(
                        headers ->
                            headers.entrySet().stream()
                                .filter(
                                    entry ->
                                        Ascii.equalsIgnoreCase("x-goog-upload-url", entry.getKey()))
                                .map(entry -> entry.getValue())
                                .findFirst())
                    .orElseThrow(
                        () ->
                            new IllegalStateException(
                                "Failed to upload to file search store. Upload URL was not returned"
                                    + " in the resumable response.")));
  }

  /**
   * Uploads a file to the file search store.
   *
   * @param fileSearchStoreName The name of the file search store to upload to.
   * @param file The file to upload.
   * @param config The configuration for the upload.
   * @return The long running operation of uploading.
   */
  public CompletableFuture<UploadToFileSearchStoreOperation> uploadToFileSearchStore(
      String fileSearchStoreName, java.io.File file, UploadToFileSearchStoreConfig config) {
    long size = file.length();
    String fileName = file.getName();
    Optional<String> mimeType;
    try {
      String probedMimeType = java.nio.file.Files.probeContentType(file.toPath());
      mimeType = Optional.ofNullable(probedMimeType);
    } catch (IOException e) {
      mimeType = Optional.empty();
    }
    return getUploadUrl(fileSearchStoreName, config, mimeType, Optional.of(fileName), size)
        .thenCompose(
            uploadUrl ->
                CompletableFuture.supplyAsync(
                    () -> {
                      try (InputStream inputStream = new FileInputStream(file)) {
                        return uploadClient.upload(uploadUrl, inputStream, size);
                      } catch (IOException e) {
                        throw new GenAiIOException("Failed to upload file.", e);
                      }
                    }))
        .thenApply(FileSearchStores::operationFromResponse);
  }

  /**
   * Uploads a file in bytes format to the file search store.
   *
   * @param fileSearchStoreName The name of the file search store to upload to.
   * @param bytes The bytes of the file to upload.
   * @param config The configuration for the upload.
   * @return The long running operation of uploading.
   */
  public CompletableFuture<UploadToFileSearchStoreOperation> uploadToFileSearchStore(
      String fileSearchStoreName, byte[] bytes, UploadToFileSearchStoreConfig config) {
    return getUploadUrl(
            fileSearchStoreName,
            config,
            Optional.<String>empty(),
            Optional.<String>empty(),
            bytes.length)
        .thenCompose(
            uploadUrl -> CompletableFuture.supplyAsync(() -> uploadClient.upload(uploadUrl, bytes)))
        .thenApply(FileSearchStores::operationFromResponse);
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
  public CompletableFuture<UploadToFileSearchStoreOperation> uploadToFileSearchStore(
      String fileSearchStoreName,
      InputStream inputStream,
      long size,
      UploadToFileSearchStoreConfig config) {
    return getUploadUrl(
            fileSearchStoreName, config, Optional.<String>empty(), Optional.<String>empty(), size)
        .thenCompose(
            uploadUrl ->
                CompletableFuture.supplyAsync(
                    () -> uploadClient.upload(uploadUrl, inputStream, size)))
        .thenApply(FileSearchStores::operationFromResponse);
  }

  /**
   * Uploads a file to the API.
   *
   * @param fileSearchStoreName The name of the file search store to upload to.
   * @param filePath The path of the file to upload.
   * @param config The configuration for the upload.
   * @return The uploaded file.
   */
  public CompletableFuture<UploadToFileSearchStoreOperation> uploadToFileSearchStore(
      String fileSearchStoreName, String filePath, UploadToFileSearchStoreConfig config) {
    java.io.File file = new java.io.File(filePath);
    return uploadToFileSearchStore(fileSearchStoreName, file, config);
  }
}
