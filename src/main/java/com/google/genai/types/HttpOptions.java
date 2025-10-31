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

package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import java.net.Proxy;
import java.util.Map;
import java.util.Optional;

/** HTTP options to be used in each of the requests. */
@AutoValue
@JsonDeserialize(builder = HttpOptions.Builder.class)
public abstract class HttpOptions extends JsonSerializable {
  /** The base URL for the AI platform service endpoint. */
  @JsonProperty("baseUrl")
  public abstract Optional<String> baseUrl();

  /** Specifies the version of the API to use. */
  @JsonProperty("apiVersion")
  public abstract Optional<String> apiVersion();

  /** Additional HTTP headers to be sent with the request. */
  @JsonProperty("headers")
  public abstract Optional<Map<String, String>> headers();

  /** Timeout for the request in milliseconds. */
  @JsonProperty("timeout")
  public abstract Optional<Integer> timeout();

  /** Proxy configuration for the HTTP requests. */
  @JsonProperty("proxy")
  public abstract Optional<Proxy> proxy();

  /**
   * Extra parameters to add to the request body. The structure must match the backend API's request
   * structure. - VertexAI backend API docs: https://cloud.google.com/vertex-ai/docs/reference/rest
   * - GeminiAPI backend API docs: https://ai.google.dev/api/rest
   */
  @JsonProperty("extraBody")
  public abstract Optional<Map<String, Object>> extraBody();

  /** HTTP retry options for the request. */
  @JsonProperty("retryOptions")
  public abstract Optional<HttpRetryOptions> retryOptions();

  /** Instantiates a builder for HttpOptions. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_HttpOptions.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for HttpOptions. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `HttpOptions.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_HttpOptions.Builder();
    }

    /**
     * Setter for baseUrl.
     *
     * <p>baseUrl: The base URL for the AI platform service endpoint.
     */
    @JsonProperty("baseUrl")
    public abstract Builder baseUrl(String baseUrl);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder baseUrl(Optional<String> baseUrl);

    /** Clears the value of baseUrl field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearBaseUrl() {
      return baseUrl(Optional.empty());
    }

    /**
     * Setter for apiVersion.
     *
     * <p>apiVersion: Specifies the version of the API to use.
     */
    @JsonProperty("apiVersion")
    public abstract Builder apiVersion(String apiVersion);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder apiVersion(Optional<String> apiVersion);

    /** Clears the value of apiVersion field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearApiVersion() {
      return apiVersion(Optional.empty());
    }

    /**
     * Setter for headers.
     *
     * <p>headers: Additional HTTP headers to be sent with the request.
     */
    @JsonProperty("headers")
    public abstract Builder headers(Map<String, String> headers);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder headers(Optional<Map<String, String>> headers);

    /** Clears the value of headers field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearHeaders() {
      return headers(Optional.empty());
    }

    /**
     * Setter for timeout.
     *
     * <p>timeout: Timeout for the request in milliseconds.
     */
    @JsonProperty("timeout")
    public abstract Builder timeout(Integer timeout);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder timeout(Optional<Integer> timeout);

    /** Clears the value of timeout field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTimeout() {
      return timeout(Optional.empty());
    }

    /**
     * Setter for proxy.
     *
     * <p>proxy: Proxy configuration for the HTTP requests.
     */
    @JsonProperty("proxy")
    public abstract Builder proxy(Proxy proxy);

    /**
     * Setter for extraBody.
     *
     * <p>extraBody: Extra parameters to add to the request body. The structure must match the
     * backend API's request structure. - VertexAI backend API docs:
     * https://cloud.google.com/vertex-ai/docs/reference/rest - GeminiAPI backend API docs:
     * https://ai.google.dev/api/rest
     */
    @JsonProperty("extraBody")
    public abstract Builder extraBody(Map<String, Object> extraBody);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder extraBody(Optional<Map<String, Object>> extraBody);

    /** Clears the value of extraBody field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearExtraBody() {
      return extraBody(Optional.empty());
    }

    /**
     * Setter for retryOptions.
     *
     * <p>retryOptions: HTTP retry options for the request.
     */
    @JsonProperty("retryOptions")
    public abstract Builder retryOptions(HttpRetryOptions retryOptions);

    /**
     * Setter for retryOptions builder.
     *
     * <p>retryOptions: HTTP retry options for the request.
     */
    @CanIgnoreReturnValue
    public Builder retryOptions(HttpRetryOptions.Builder retryOptionsBuilder) {
      return retryOptions(retryOptionsBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder retryOptions(Optional<HttpRetryOptions> retryOptions);

    /** Clears the value of retryOptions field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearRetryOptions() {
      return retryOptions(Optional.empty());
    }

    public abstract HttpOptions build();
  }

  /** Deserializes a JSON string to a HttpOptions object. */
  @ExcludeFromGeneratedCoverageReport
  public static HttpOptions fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, HttpOptions.class);
  }
}
