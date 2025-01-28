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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Map;
import java.util.Optional;

/** HTTP options to be used in each of the requests. */
@AutoValue
@JsonDeserialize(builder = AutoValue_HttpOptions.Builder.class)
public abstract class HttpOptions extends JsonSerializable {
  /** The base URL for the AI platform service endpoint. */
  public abstract Optional<String> getBaseUrl();

  /** Specifies the version of the API to use. */
  public abstract Optional<String> getApiVersion();

  /** Additional HTTP headers to be sent with the request. */
  public abstract Optional<Map<String, String>> getHeaders();

  /** Timeout for the request in milliseconds. */
  public abstract Optional<Integer> getTimeout();

  /** Instantiates a builder for HttpOptions. */
  public static Builder builder() {
    return new AutoValue_HttpOptions.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for HttpOptions. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("baseUrl")
    public abstract Builder setBaseUrl(String baseUrl);

    @JsonProperty("apiVersion")
    public abstract Builder setApiVersion(String apiVersion);

    @JsonProperty("headers")
    public abstract Builder setHeaders(Map<String, String> headers);

    @JsonProperty("timeout")
    public abstract Builder setTimeout(Integer timeout);

    public abstract HttpOptions build();
  }

  /** Deserializes a JSON string to a HttpOptions object. */
  public static HttpOptions fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, HttpOptions.class);
  }
}
