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
import com.google.genai.types.HttpOptions;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;

/** Base client for the HTTP APIs. This is for internal use only. */
@InternalApi
public class HttpApiClient extends ApiClient {

  /** Constructs an ApiClient for Google AI APIs. */
  HttpApiClient(Optional<String> apiKey, Optional<HttpOptions> httpOptions) {
    super(apiKey, httpOptions);
  }

  /** Constructs an ApiClient for Vertex AI APIs. */
  HttpApiClient(
      Optional<String> project,
      Optional<String> location,
      Optional<GoogleCredentials> credentials,
      Optional<HttpOptions> httpOptions) {
    super(project, location, credentials, httpOptions);
  }

  /** Sends a Http request given the http method, path, request json string, and http options. */
  @Override
  public HttpApiResponse request(
      String httpMethod,
      String path,
      String requestJson,
      Optional<HttpOptions> requestHttpOptions) {
    boolean queryBaseModel =
        httpMethod.equalsIgnoreCase("GET") && path.startsWith("publishers/google/models/");
    if (this.vertexAI() && !path.startsWith("projects/") && !queryBaseModel) {
      path =
          String.format("projects/%s/locations/%s/", this.project.get(), this.location.get())
              + path;
    }

    HttpOptions mergedHttpOptions = httpOptions;
    if (requestHttpOptions.isPresent()) {
      mergedHttpOptions = mergeHttpOptions(requestHttpOptions.get());
    }

    String requestUrl;

    if (mergedHttpOptions.apiVersion().get().isEmpty()) {
      requestUrl = String.format("%s/%s", mergedHttpOptions.baseUrl().get(), path);
    } else {
      requestUrl =
          String.format(
              "%s/%s/%s",
              mergedHttpOptions.baseUrl().get(), mergedHttpOptions.apiVersion().get(), path);
    }

    if (httpMethod.equalsIgnoreCase("POST")) {
      HttpPost httpPost = new HttpPost(requestUrl);
      setHeaders(httpPost, mergedHttpOptions);
      httpPost.setEntity(new StringEntity(requestJson, ContentType.APPLICATION_JSON));
      return executeRequest(httpPost);
    } else if (httpMethod.equalsIgnoreCase("GET")) {
      HttpGet httpGet = new HttpGet(requestUrl);
      setHeaders(httpGet, mergedHttpOptions);
      return executeRequest(httpGet);
    } else if (httpMethod.equalsIgnoreCase("DELETE")) {
      HttpDelete httpDelete = new HttpDelete(requestUrl);
      setHeaders(httpDelete, mergedHttpOptions);
      return executeRequest(httpDelete);
    } else {
      throw new IllegalArgumentException("Unsupported HTTP method: " + httpMethod);
    }
  }

  @Override
  public ApiResponse request(
      String httpMethod,
      String url,
      byte[] requestBytes,
      Optional<HttpOptions> requestHttpOptions) {
    HttpOptions mergedHttpOptions = httpOptions;
    if (requestHttpOptions.isPresent()) {
      mergedHttpOptions = mergeHttpOptions(requestHttpOptions.get());
    }
    if (httpMethod.equalsIgnoreCase("POST")) {
      HttpPost httpPost = new HttpPost(url);
      setHeaders(httpPost, mergedHttpOptions);
      httpPost.setEntity(new ByteArrayEntity(requestBytes));
      return executeRequest(httpPost);
    } else {
      throw new IllegalArgumentException(
          "The request method with bytes is only supported for POST. Unsupported HTTP method: "
              + httpMethod);
    }
  }

  /** Sets the required headers (including auth) on the request object. */
  private void setHeaders(HttpRequestBase request, HttpOptions requestHttpOptions) {
    for (Map.Entry<String, String> header :
        requestHttpOptions.headers().orElse(ImmutableMap.of()).entrySet()) {
      request.setHeader(header.getKey(), header.getValue());
    }

    if (apiKey.isPresent()) {
      request.setHeader("x-goog-api-key", apiKey.get());
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
      request.setHeader("Authorization", "Bearer " + accessToken);

      if (cred.getQuotaProjectId() != null) {
        request.setHeader("x-goog-user-project", cred.getQuotaProjectId());
      }
    }
  }

  /** Executes the given HTTP request. */
  private HttpApiResponse executeRequest(HttpRequestBase request) {
    try {
      return new HttpApiResponse(httpClient.execute(request));
    } catch (IOException e) {
      throw new GenAiIOException("Failed to execute HTTP request.", e);
    }
  }
}
