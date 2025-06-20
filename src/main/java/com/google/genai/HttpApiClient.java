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
import com.google.common.collect.ImmutableMap;
import com.google.genai.errors.GenAiIOException;
import com.google.genai.types.ClientOptions;
import com.google.genai.types.HttpOptions;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

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
      Optional<String> project,
      Optional<String> location,
      Optional<GoogleCredentials> credentials,
      Optional<HttpOptions> httpOptions,
      Optional<ClientOptions> clientOptions) {
    super(project, location, credentials, httpOptions, clientOptions);
  }

  /** Sends a Http request given the http method, path, request json string, and http options. */
  @Override
  public HttpApiResponse request(
      String httpMethod,
      String path,
      String requestJson,
      Optional<HttpOptions> requestHttpOptions) {
    boolean queryBaseModel =
        httpMethod.equalsIgnoreCase("GET") && path.startsWith("publishers/google/models");
    if (this.vertexAI() && !path.startsWith("projects/") && !queryBaseModel) {
      path =
          String.format("projects/%s/locations/%s/", this.project.get(), this.location.get())
              + path;
    }

    HttpOptions mergedHttpOptions = mergeHttpOptions(requestHttpOptions.orElse(null));

    String requestUrl;

    if (mergedHttpOptions.apiVersion().get().isEmpty()) {
      requestUrl = String.format("%s/%s", mergedHttpOptions.baseUrl().get(), path);
    } else {
      requestUrl =
          String.format(
              "%s/%s/%s",
              mergedHttpOptions.baseUrl().get(), mergedHttpOptions.apiVersion().get(), path);
    }

    RequestBody body = RequestBody.create(requestJson, MediaType.parse("application/json"));

    Request.Builder requestBuilder = new Request.Builder().url(requestUrl).method(httpMethod, body);

    setHeaders(requestBuilder, mergedHttpOptions);
    return executeRequest(requestBuilder.build());
  }

  @Override
  public ApiResponse request(
      String httpMethod,
      String url,
      byte[] requestBytes,
      Optional<HttpOptions> requestHttpOptions) {
    HttpOptions mergedHttpOptions = mergeHttpOptions(requestHttpOptions.orElse(null));
    if (httpMethod.equalsIgnoreCase("POST")) {
      RequestBody body = RequestBody.create(requestBytes);
      Request.Builder requestBuilder = new Request.Builder().url(url).post(body);
      setHeaders(requestBuilder, mergedHttpOptions);
      return executeRequest(requestBuilder.build());
    } else {
      throw new IllegalArgumentException(
          "The request method with bytes is only supported for POST. Unsupported HTTP method: "
              + httpMethod);
    }
  }

  /** Sets the required headers (including auth) on the request object. */
  private void setHeaders(Request.Builder request, HttpOptions requestHttpOptions) {
    for (Map.Entry<String, String> header :
        requestHttpOptions.headers().orElse(ImmutableMap.of()).entrySet()) {
      request.header(header.getKey(), header.getValue());
    }

    if (apiKey.isPresent()) {
      request.header("x-goog-api-key", apiKey.get());
    } else {
      GoogleCredentials cred =
          credentials.orElseThrow(() -> new IllegalStateException("credentials is required"));
      try {
        cred.refreshIfExpired();
      } catch (IOException e) {
        throw new GenAiIOException("Failed to refresh credentials.", e);
      }
      String accessToken;
      try {
        accessToken = cred.getAccessToken().getTokenValue();
      } catch (NullPointerException e) {
        // For test cases where the access token is not available.
        if (e.getMessage()
            .contains(
                "because the return value of"
                    + " \"com.google.auth.oauth2.GoogleCredentials.getAccessToken()\" is null")) {
          accessToken = "";
        } else {
          throw e;
        }
      }
      request.header("Authorization", "Bearer " + accessToken);

      if (cred.getQuotaProjectId() != null) {
        request.header("x-goog-user-project", cred.getQuotaProjectId());
      }
    }
  }

  /** Executes the given HTTP request. */
  private HttpApiResponse executeRequest(Request request) {
    try {
      return new HttpApiResponse(httpClient.newCall(request).execute());
    } catch (IOException e) {
      throw new GenAiIOException("Failed to execute HTTP request.", e);
    }
  }
}
