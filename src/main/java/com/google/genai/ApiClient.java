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

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.common.collect.ImmutableMap;
import com.google.genai.types.HttpOptions;
import com.google.genai.types.HttpOptions.Builder;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

/** Interface for an API client which issues HTTP requests to the GenAI APIs. */
abstract class ApiClient {
  CloseableHttpClient httpClient;
  // For Google AI APIs
  final Optional<String> apiKey;
  // For Vertex AI APIs
  final Optional<String> project;
  final Optional<String> location;
  final Optional<GoogleCredentials> credentials;
  HttpOptions httpOptions;
  final boolean vertexAI;

  /** Constructs an ApiClient for Google AI APIs. */
  ApiClient(Optional<String> apiKey, Optional<HttpOptions> customHttpOptions) {
    checkNotNull(apiKey, "API Key cannot be null");
    checkNotNull(customHttpOptions, "customHttpOptions cannot be null");

    try {
      this.apiKey = Optional.of(apiKey.orElse(System.getenv("GOOGLE_API_KEY")));
    } catch (NullPointerException e) {
      throw new IllegalArgumentException(
          "API key must either be provided or set in the environment variable" + " GOOGLE_API_KEY.",
          e);
    }

    this.project = Optional.empty();
    this.location = Optional.empty();
    this.credentials = Optional.empty();
    this.vertexAI = false;

    this.httpOptions = defaultHttpOptions(/* isVertexAI= */ false, this.location);

    if (customHttpOptions.isPresent()) {
      applyHttpOptions(customHttpOptions.get());
    }

    this.httpClient = createHttpClient(httpOptions.timeout());
  }

  ApiClient(
      Optional<String> project,
      Optional<String> location,
      Optional<GoogleCredentials> credentials,
      Optional<HttpOptions> customHttpOptions) {
    checkNotNull(project, "project cannot be null");
    checkNotNull(location, "location cannot be null");
    checkNotNull(credentials, "credentials cannot be null");
    checkNotNull(customHttpOptions, "customHttpOptions cannot be null");

    try {
      this.project = Optional.of(project.orElse(System.getenv("GOOGLE_CLOUD_PROJECT")));
    } catch (NullPointerException e) {
      throw new IllegalArgumentException(
          "Project must either be provided or set in the environment variable"
              + " GOOGLE_CLOUD_PROJECT.",
          e);
    }
    if (this.project.get().isEmpty()) {
      throw new IllegalArgumentException("Project must not be empty.");
    }

    try {
      this.location = Optional.of(location.orElse(System.getenv("GOOGLE_CLOUD_LOCATION")));
    } catch (NullPointerException e) {
      throw new IllegalArgumentException(
          "Location must either be provided or set in the environment variable"
              + " GOOGLE_CLOUD_LOCATION.",
          e);
    }
    if (this.location.get().isEmpty()) {
      throw new IllegalArgumentException("Location must not be empty.");
    }

    try {
      this.credentials = Optional.of(credentials.orElse(GoogleCredentials.getApplicationDefault()));
    } catch (IOException e) {
      throw new IllegalArgumentException(
          "Failed to get application default credentials, please explicitly provide credentials.",
          e);
    }

    this.httpOptions = defaultHttpOptions(/* isVertexAI= */ true, this.location);

    if (customHttpOptions.isPresent()) {
      applyHttpOptions(customHttpOptions.get());
    }
    this.apiKey = Optional.empty();
    this.vertexAI = true;
    this.httpClient = createHttpClient(httpOptions.timeout());
  }

  private CloseableHttpClient createHttpClient(Optional<Integer> timeout) {
    if (!timeout.isPresent()) {
      return HttpClients.createDefault();
    }
    RequestConfig config =
        RequestConfig.custom()
            .setConnectTimeout(timeout.get())
            .setConnectionRequestTimeout(timeout.get())
            .setSocketTimeout(timeout.get())
            .build();
    return HttpClientBuilder.create().setDefaultRequestConfig(config).build();
  }

  /** Sends a Http Post request given the path and request json string. */
  public abstract ApiResponse post(String path, String requestJson) throws IOException;

  /** Returns the library version. */
  String libraryVersion() {
    // TODO: Automate revisions to the SDK library version.
    String libraryLabel = "google-genai-sdk/0.1.0";
    String languageLabel = "gl-java/" + System.getProperty("java.version");
    return libraryLabel + " " + languageLabel;
  }

  /** Returns whether the client is using Vertex AI APIs. */
  public boolean isVertexAI() {
    return vertexAI;
  }

  /** Returns the project ID for Vertex AI APIs. */
  public String project() {
    return project.get();
  }

  /** Returns the location for Vertex AI APIs. */
  public String location() {
    return location.get();
  }

  /** Returns the API key for Google AI APIs. */
  public String apiKey() {
    return apiKey.get();
  }

  /** Returns the HttpClient for API calls. */
  CloseableHttpClient httpClient() {
    return httpClient;
  }

  private void applyHttpOptions(HttpOptions httpOptionsToApply) {
    HttpOptions.Builder mergedHttpOptionsBuilder = HttpOptions.builder();
    if (httpOptionsToApply.baseUrl().isPresent()) {
      mergedHttpOptionsBuilder.baseUrl(httpOptionsToApply.baseUrl().get());
    }
    if (httpOptionsToApply.apiVersion().isPresent()) {
      mergedHttpOptionsBuilder.apiVersion(httpOptionsToApply.apiVersion().get());
    }
    if (httpOptionsToApply.timeout().isPresent()) {
      mergedHttpOptionsBuilder.timeout(httpOptionsToApply.timeout().get());
    }
    if (httpOptionsToApply.headers().isPresent()) {
      Map<String, String> mergedHeaders =
          ImmutableMap.<String, String>builder()
              .putAll(httpOptionsToApply.headers().get())
              .putAll(this.httpOptions.headers().get())
              .build();
      mergedHttpOptionsBuilder.headers(mergedHeaders);
    }
    this.httpOptions = mergedHttpOptionsBuilder.build();
  }

  private HttpOptions defaultHttpOptions(Boolean isVertexAI, Optional<String> location) {
    ImmutableMap.Builder<String, String> defaultHeaders = ImmutableMap.builder();
    defaultHeaders.put("Content-Type", "application/json");
    defaultHeaders.put("user-agent", libraryVersion());
    defaultHeaders.put("x-goog-api-client", libraryVersion());

    HttpOptions.Builder defaultHttpOptionsBuilder =
        HttpOptions.builder().headers(defaultHeaders.build());

    if (isVertexAI && location.isPresent()) {
      defaultHttpOptionsBuilder
          .baseUrl(String.format("https://%s-aiplatform.googleapis.com/", location.get()))
          .apiVersion("v1beta1");
    } else if (isVertexAI && !location.isPresent()) {
      throw new IllegalArgumentException("Location must be provided for Vertex AI APIs.");
    } else {
      defaultHttpOptionsBuilder
          .baseUrl("https://generativelanguage.googleapis.com/")
          .apiVersion("v1beta");
    }
    return defaultHttpOptionsBuilder.build();
  }
}
