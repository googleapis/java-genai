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

/**
 * The generic reusable api auth config. Deprecated. Please use AuthConfig
 * (google/cloud/aiplatform/master/auth.proto) instead.
 */
@AutoValue
@JsonDeserialize(builder = ApiAuth.Builder.class)
public abstract class ApiAuth extends JsonSerializable {
  /** The API secret. */
  @JsonProperty("apiKeyConfig")
  public abstract Optional<ApiAuthApiKeyConfig> apiKeyConfig();

  /** Instantiates a builder for ApiAuth. */
  public static Builder builder() {
    return new AutoValue_ApiAuth.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ApiAuth. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `ApiAuth.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ApiAuth.Builder();
    }

    /**
     * Setter for apiKeyConfig.
     *
     * <p>apiKeyConfig: The API secret.
     */
    @JsonProperty("apiKeyConfig")
    public abstract Builder apiKeyConfig(ApiAuthApiKeyConfig apiKeyConfig);

    /**
     * Setter for apiKeyConfig builder.
     *
     * <p>apiKeyConfig: The API secret.
     */
    public Builder apiKeyConfig(ApiAuthApiKeyConfig.Builder apiKeyConfigBuilder) {
      return apiKeyConfig(apiKeyConfigBuilder.build());
    }

    public abstract ApiAuth build();
  }

  /** Deserializes a JSON string to a ApiAuth object. */
  public static ApiAuth fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ApiAuth.class);
  }
}
