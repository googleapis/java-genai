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
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** The API secret. This data type is not supported in Gemini API. */
@AutoValue
@JsonDeserialize(builder = ApiAuthApiKeyConfig.Builder.class)
public abstract class ApiAuthApiKeyConfig extends JsonSerializable {
  /**
   * Required. The SecretManager secret version resource name storing API key. e.g.
   * projects/{project}/secrets/{secret}/versions/{version}
   */
  @JsonProperty("apiKeySecretVersion")
  public abstract Optional<String> apiKeySecretVersion();

  /** The API key string. Either this or `api_key_secret_version` must be set. */
  @JsonProperty("apiKeyString")
  public abstract Optional<String> apiKeyString();

  /** Instantiates a builder for ApiAuthApiKeyConfig. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ApiAuthApiKeyConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ApiAuthApiKeyConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `ApiAuthApiKeyConfig.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ApiAuthApiKeyConfig.Builder();
    }

    /**
     * Setter for apiKeySecretVersion.
     *
     * <p>apiKeySecretVersion: Required. The SecretManager secret version resource name storing API
     * key. e.g. projects/{project}/secrets/{secret}/versions/{version}
     */
    @JsonProperty("apiKeySecretVersion")
    public abstract Builder apiKeySecretVersion(String apiKeySecretVersion);

    /**
     * Setter for apiKeyString.
     *
     * <p>apiKeyString: The API key string. Either this or `api_key_secret_version` must be set.
     */
    @JsonProperty("apiKeyString")
    public abstract Builder apiKeyString(String apiKeyString);

    public abstract ApiAuthApiKeyConfig build();
  }

  /** Deserializes a JSON string to a ApiAuthApiKeyConfig object. */
  @ExcludeFromGeneratedCoverageReport
  public static ApiAuthApiKeyConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ApiAuthApiKeyConfig.class);
  }
}
