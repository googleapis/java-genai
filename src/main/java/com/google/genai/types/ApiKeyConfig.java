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
import java.util.Optional;

/** Config for authentication with API key. This data type is not supported in Gemini API. */
@AutoValue
@JsonDeserialize(builder = ApiKeyConfig.Builder.class)
public abstract class ApiKeyConfig extends JsonSerializable {
  /**
   * Optional. The name of the SecretManager secret version resource storing the API key. Format:
   * `projects/{project}/secrets/{secrete}/versions/{version}` - If both `api_key_secret` and
   * `api_key_string` are specified, this field takes precedence over `api_key_string`. - If
   * specified, the `secretmanager.versions.access` permission should be granted to Vertex AI
   * Extension Service Agent
   * (https://cloud.google.com/vertex-ai/docs/general/access-control#service-agents) on the
   * specified resource.
   */
  @JsonProperty("apiKeySecret")
  public abstract Optional<String> apiKeySecret();

  /** Optional. The API key to be used in the request directly. */
  @JsonProperty("apiKeyString")
  public abstract Optional<String> apiKeyString();

  /** Optional. The location of the API key. */
  @JsonProperty("httpElementLocation")
  public abstract Optional<HttpElementLocation> httpElementLocation();

  /**
   * Optional. The parameter name of the API key. E.g. If the API request is
   * "https://example.com/act?api_key=", "api_key" would be the parameter name.
   */
  @JsonProperty("name")
  public abstract Optional<String> name();

  /** Instantiates a builder for ApiKeyConfig. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ApiKeyConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ApiKeyConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `ApiKeyConfig.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ApiKeyConfig.Builder();
    }

    /**
     * Setter for apiKeySecret.
     *
     * <p>apiKeySecret: Optional. The name of the SecretManager secret version resource storing the
     * API key. Format: `projects/{project}/secrets/{secrete}/versions/{version}` - If both
     * `api_key_secret` and `api_key_string` are specified, this field takes precedence over
     * `api_key_string`. - If specified, the `secretmanager.versions.access` permission should be
     * granted to Vertex AI Extension Service Agent
     * (https://cloud.google.com/vertex-ai/docs/general/access-control#service-agents) on the
     * specified resource.
     */
    @JsonProperty("apiKeySecret")
    public abstract Builder apiKeySecret(String apiKeySecret);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder apiKeySecret(Optional<String> apiKeySecret);

    /** Clears the value of apiKeySecret field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearApiKeySecret() {
      return apiKeySecret(Optional.empty());
    }

    /**
     * Setter for apiKeyString.
     *
     * <p>apiKeyString: Optional. The API key to be used in the request directly.
     */
    @JsonProperty("apiKeyString")
    public abstract Builder apiKeyString(String apiKeyString);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder apiKeyString(Optional<String> apiKeyString);

    /** Clears the value of apiKeyString field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearApiKeyString() {
      return apiKeyString(Optional.empty());
    }

    /**
     * Setter for httpElementLocation.
     *
     * <p>httpElementLocation: Optional. The location of the API key.
     */
    @JsonProperty("httpElementLocation")
    public abstract Builder httpElementLocation(HttpElementLocation httpElementLocation);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder httpElementLocation(Optional<HttpElementLocation> httpElementLocation);

    /** Clears the value of httpElementLocation field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearHttpElementLocation() {
      return httpElementLocation(Optional.empty());
    }

    /**
     * Setter for httpElementLocation given a known enum.
     *
     * <p>httpElementLocation: Optional. The location of the API key.
     */
    @CanIgnoreReturnValue
    public Builder httpElementLocation(HttpElementLocation.Known knownType) {
      return httpElementLocation(new HttpElementLocation(knownType));
    }

    /**
     * Setter for httpElementLocation given a string.
     *
     * <p>httpElementLocation: Optional. The location of the API key.
     */
    @CanIgnoreReturnValue
    public Builder httpElementLocation(String httpElementLocation) {
      return httpElementLocation(new HttpElementLocation(httpElementLocation));
    }

    /**
     * Setter for name.
     *
     * <p>name: Optional. The parameter name of the API key. E.g. If the API request is
     * "https://example.com/act?api_key=", "api_key" would be the parameter name.
     */
    @JsonProperty("name")
    public abstract Builder name(String name);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder name(Optional<String> name);

    /** Clears the value of name field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearName() {
      return name(Optional.empty());
    }

    public abstract ApiKeyConfig build();
  }

  /** Deserializes a JSON string to a ApiKeyConfig object. */
  @ExcludeFromGeneratedCoverageReport
  public static ApiKeyConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ApiKeyConfig.class);
  }
}
