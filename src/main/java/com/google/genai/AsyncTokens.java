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

import com.google.genai.Common.BuiltRequest;
import com.google.genai.types.AuthToken;
import com.google.genai.types.CreateAuthTokenConfig;
import java.util.concurrent.CompletableFuture;

/** Async module of {@link AuthToken} */
public final class AsyncTokens {

  Tokens tokens;
  ApiClient apiClient;

  public AsyncTokens(ApiClient apiClient) {
    this.apiClient = apiClient;
    this.tokens = new Tokens(apiClient);
  }

  /**
   * Asynchronously creates an ephemeral auth token resource.
   *
   * @param config A {@link CreateAuthTokenConfig} for configuring the create request.
   * @return A {@link AuthToken} object that contains the info of the created resource.
   */
  public CompletableFuture<AuthToken> create(CreateAuthTokenConfig config) {
    BuiltRequest builtRequest = tokens.buildRequestForCreate(config);
    return this.apiClient
        .asyncRequest("post", builtRequest.path, builtRequest.body, builtRequest.httpOptions)
        .thenApplyAsync(
            response -> {
              try (ApiResponse res = response) {
                return tokens.processResponseForCreate(res, config);
              }
            });
  }
}
