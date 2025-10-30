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

import com.google.api.core.InternalApi;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.genai.errors.GenAiIOException;
import com.google.genai.types.ClientOptions;
import com.google.genai.types.HttpOptions;
import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/** Base client for the HTTP APIs. This is for internal use only. */
@InternalApi
public class HttpApiClient extends ApiClient {

  /** Constructs an ApiClient for Google AI APIs. */
  HttpApiClient(
      Optional<String> apiKey,
      Optional<HttpOptions> httpOptions,
      Optional<ClientOptions> clientOptions) {
    super(apiKey, httpOptions, clientOptions);
  }

  /** Constructs an ApiClient for Vertex AI APIs. */
  HttpApiClient(
      Optional<String> apiKey,
      Optional<String> project,
      Optional<String> location,
      Optional<GoogleCredentials> credentials,
      Optional<HttpOptions> httpOptions,
      Optional<ClientOptions> clientOptions) {
    super(apiKey, project, location, credentials, httpOptions, clientOptions);
  }

  /**
   * Sends a Http request given the http method, path, request json string, and http options.
   * Request bodies included for non-POST/PUT/PATCH methods will be ignored.
   */
  @Override
  public HttpApiResponse request(
      String httpMethod,
      String path,
      String requestJson,
      Optional<HttpOptions> requestHttpOptions) {
    return executeRequest(buildRequest(httpMethod, path, requestJson, requestHttpOptions));
  }

  /** Sends a Http request given the http method, path, request bytes, and http options. */
  @Override
  public HttpApiResponse request(
      String httpMethod,
      String url,
      byte[] requestBytes,
      Optional<HttpOptions> requestHttpOptions) {
    return executeRequest(buildRequest(httpMethod, url, requestBytes, requestHttpOptions));
  }

  /** Executes the given HTTP request. */
  private HttpApiResponse executeRequest(Request request) {
    try {
      return new HttpApiResponse(httpClient.newCall(request).execute());
    } catch (IOException e) {
      throw new GenAiIOException("Failed to execute HTTP request.", e);
    }
  }

  /**
   * Sends an asynchronous Http request given the http method, path, request json string, and http
   * options.
   */
  @Override
  public CompletableFuture<ApiResponse> asyncRequest(
      String httpMethod,
      String path,
      String requestJson,
      Optional<HttpOptions> requestHttpOptions) {
    return asyncExecuteRequest(buildRequest(httpMethod, path, requestJson, requestHttpOptions));
  }

  /**
   * Sends an asynchronous Http request given the http method, path, request bytes, and http
   * options.
   */
  @Override
  public CompletableFuture<ApiResponse> asyncRequest(
      String httpMethod,
      String url,
      byte[] requestBytes,
      Optional<HttpOptions> requestHttpOptions) {
    return asyncExecuteRequest(buildRequest(httpMethod, url, requestBytes, requestHttpOptions));
  }

  /** Executes the given HTTP request asynchronously, this method is non-blocking. */
  private CompletableFuture<ApiResponse> asyncExecuteRequest(Request request) {
    CompletableFuture<ApiResponse> future = new CompletableFuture<>();

    httpClient
        .newCall(request)
        .enqueue(
            new Callback() {

              @Override
              public void onFailure(Call call, IOException e) {
                future.completeExceptionally(
                    new GenAiIOException("Failed to execute HTTP request.", e));
              }

              @Override
              public void onResponse(Call call, Response response) {
                future.complete(new HttpApiResponse(response));
              }
            });

    return future;
  }
}
