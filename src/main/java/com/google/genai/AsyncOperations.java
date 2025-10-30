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
import com.google.genai.Common.BuiltRequest;
import com.google.genai.types.FetchPredictOperationConfig;
import com.google.genai.types.GenerateVideosOperation;
import com.google.genai.types.GetOperationConfig;
import com.google.genai.types.Operation;
import java.util.concurrent.CompletableFuture;

/** Async module of {@link Operations} */
public final class AsyncOperations {

  Operations operations;
  ApiClient apiClient;

  public AsyncOperations(ApiClient apiClient) {
    this.apiClient = apiClient;
    this.operations = new Operations(apiClient);
  }

  CompletableFuture<JsonNode> privateGetVideosOperation(
      String operationName, GetOperationConfig config) {
    BuiltRequest builtRequest =
        operations.buildRequestForPrivateGetVideosOperation(operationName, config);
    return this.apiClient
        .asyncRequest("get", builtRequest.path, builtRequest.body, builtRequest.httpOptions)
        .thenApplyAsync(
            response -> {
              try (ApiResponse res = response) {
                return operations.processResponseForPrivateGetVideosOperation(res, config);
              }
            });
  }

  CompletableFuture<JsonNode> privateFetchPredictVideosOperation(
      String operationName, String resourceName, FetchPredictOperationConfig config) {
    BuiltRequest builtRequest =
        operations.buildRequestForPrivateFetchPredictVideosOperation(
            operationName, resourceName, config);
    return this.apiClient
        .asyncRequest("post", builtRequest.path, builtRequest.body, builtRequest.httpOptions)
        .thenApplyAsync(
            response -> {
              try (ApiResponse res = response) {
                return operations.processResponseForPrivateFetchPredictVideosOperation(res, config);
              }
            });
  }

  /**
   * Gets the status of a GenerateVideosOperation.
   *
   * @param operation A GenerateVideosOperation.
   * @param config The configuration for getting the operation.
   * @return A GenerateVideosOperation with the updated status of the operation.
   */
  public CompletableFuture<GenerateVideosOperation> getVideosOperation(
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
  public <T, U extends Operation<T, U>> CompletableFuture<U> get(
      U operation, GetOperationConfig config) {
    if (!operation.name().isPresent()) {
      throw new IllegalArgumentException("Operation name is required.");
    }

    if (this.apiClient.vertexAI()) {
      String resourceName = operation.name().get().split("/operations/")[0];
      return this.privateFetchPredictVideosOperation(operation.name().get(), resourceName, null)
          .thenApplyAsync(response -> operation.fromApiResponse(response, true));
    } else {
      return this.privateGetVideosOperation(operation.name().get(), config)
          .thenApplyAsync(response -> operation.fromApiResponse(response, false));
    }
  }
}
