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

import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import com.google.gson.annotations.SerializedName;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;
import java.util.Map;
import java.util.Optional;

/** HTTP options to be used in each of the requests. */
@GenerateTypeAdapter
@AutoValue
public abstract class HttpOptions extends JsonSerializable {
  /** The base URL for the AI platform service endpoint. */
  @SerializedName("baseUrl")
  public abstract Optional<String> baseUrl();

  /** Specifies the version of the API to use. */
  @SerializedName("apiVersion")
  public abstract Optional<String> apiVersion();

  /** Additional HTTP headers to be sent with the request. */
  @SerializedName("headers")
  public abstract Optional<Map<String, String>> headers();

  /** Timeout for the request in milliseconds. */
  @SerializedName("timeout")
  public abstract Optional<Integer> timeout();

  /** Instantiates a builder for HttpOptions. */
  public static Builder builder() {
    return new AutoValue_HttpOptions.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for HttpOptions. */
  @AutoValue.Builder
  public abstract static class Builder {
    @SerializedName("baseUrl")
    public abstract Builder baseUrl(String baseUrl);

    @SerializedName("baseUrl")
    abstract Builder baseUrl(Optional<String> baseUrl);

    @SerializedName("apiVersion")
    public abstract Builder apiVersion(String apiVersion);

    @SerializedName("apiVersion")
    abstract Builder apiVersion(Optional<String> apiVersion);

    @SerializedName("headers")
    public abstract Builder headers(Map<String, String> headers);

    @SerializedName("headers")
    abstract Builder headers(Optional<Map<String, String>> headers);

    @SerializedName("timeout")
    public abstract Builder timeout(Integer timeout);

    @SerializedName("timeout")
    abstract Builder timeout(Optional<Integer> timeout);

    public abstract HttpOptions build();
  }

  /** Deserializes a JSON string to a HttpOptions object. */
  public static HttpOptions fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, HttpOptions.class);
  }
}
