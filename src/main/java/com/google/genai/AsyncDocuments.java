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
import com.google.genai.types.Document;
import com.google.genai.types.GetDocumentConfig;
import com.google.genai.types.ListDocumentsConfig;
import com.google.genai.types.ListDocumentsResponse;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/** Async module of {@link Documents} */
public final class AsyncDocuments {

  Documents documents;
  ApiClient apiClient;

  public AsyncDocuments(ApiClient apiClient) {
    this.apiClient = apiClient;
    this.documents = new Documents(apiClient);
  }

  public CompletableFuture<Document> get(String name, GetDocumentConfig config) {
    BuiltRequest builtRequest = documents.buildRequestForGet(name, config);
    return this.apiClient
        .asyncRequest("get", builtRequest.path, builtRequest.body, builtRequest.httpOptions)
        .thenApplyAsync(
            response -> {
              try (ApiResponse res = response) {
                return documents.processResponseForGet(res, config);
              }
            });
  }

  public CompletableFuture<Void> delete(String name, DeleteDocumentConfig config) {
    BuiltRequest builtRequest = documents.buildRequestForDelete(name, config);
    return this.apiClient
        .asyncRequest("delete", builtRequest.path, builtRequest.body, builtRequest.httpOptions)
        .thenAccept(
            response -> {
              try (ApiResponse res = response) {}
            });
  }

  CompletableFuture<ListDocumentsResponse> privateList(String parent, ListDocumentsConfig config) {
    BuiltRequest builtRequest = documents.buildRequestForPrivateList(parent, config);
    return this.apiClient
        .asyncRequest("get", builtRequest.path, builtRequest.body, builtRequest.httpOptions)
        .thenApplyAsync(
            response -> {
              try (ApiResponse res = response) {
                return documents.processResponseForPrivateList(res, config);
              }
            });
  }

  /**
   * Asynchronously makes an API request to list the available documents.
   *
   * @param parent The name of the RagStore containing the Documents.
   * @param config A {@link ListDocumentsConfig} for configuring the list request.
   * @return A CompletableFuture that resolves to a {@link AsyncPager}. The AsyncPager has a
   *     `forEach` method that can be used to asynchronously process items in the page and
   *     automatically query the next page once the current page is exhausted.
   */
  @SuppressWarnings("PatternMatchingInstanceof")
  public CompletableFuture<AsyncPager<Document>> list(String parent, ListDocumentsConfig config) {
    if (config == null) {
      config = ListDocumentsConfig.builder().build();
    }
    ListDocumentsConfig finalConfig = config;
    Function<JsonSerializable, CompletableFuture<JsonNode>> request =
        requestConfig -> {
          if (!(requestConfig instanceof ListDocumentsConfig)) {
            throw new GenAiIOException(
                "Internal error: Pager expected ListDocumentsConfig but received "
                    + requestConfig.getClass().getName());
          }
          return this.privateList(parent, (ListDocumentsConfig) requestConfig)
              .thenApply(JsonSerializable::toJsonNode);
        };
    return CompletableFuture.supplyAsync(
        () ->
            new AsyncPager<Document>(
                Pager.PagedItem.DOCUMENTS,
                request,
                (ObjectNode) JsonSerializable.toJsonNode(finalConfig),
                request.apply(finalConfig)));
  }
}
