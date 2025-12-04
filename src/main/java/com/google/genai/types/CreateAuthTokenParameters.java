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
import com.google.api.core.InternalApi;
import com.google.auto.value.AutoValue;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** Config for auth_tokens.create parameters. */
@AutoValue
@InternalApi
@JsonDeserialize(builder = CreateAuthTokenParameters.Builder.class)
public abstract class CreateAuthTokenParameters extends JsonSerializable {
  /** Optional parameters for the request. */
  @JsonProperty("config")
  public abstract Optional<CreateAuthTokenConfig> config();

  /** Instantiates a builder for CreateAuthTokenParameters. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_CreateAuthTokenParameters.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for CreateAuthTokenParameters. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `CreateAuthTokenParameters.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_CreateAuthTokenParameters.Builder();
    }

    /**
     * Setter for config.
     *
     * <p>config: Optional parameters for the request.
     */
    @JsonProperty("config")
    public abstract Builder config(CreateAuthTokenConfig config);

    /**
     * Setter for config builder.
     *
     * <p>config: Optional parameters for the request.
     */
    @CanIgnoreReturnValue
    public Builder config(CreateAuthTokenConfig.Builder configBuilder) {
      return config(configBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder config(Optional<CreateAuthTokenConfig> config);

    /** Clears the value of config field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearConfig() {
      return config(Optional.empty());
    }

    public abstract CreateAuthTokenParameters build();
  }

  /** Deserializes a JSON string to a CreateAuthTokenParameters object. */
  @ExcludeFromGeneratedCoverageReport
  public static CreateAuthTokenParameters fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, CreateAuthTokenParameters.class);
  }
}
