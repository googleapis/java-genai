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

/** Autorater config used for evaluation. */
@AutoValue
@JsonDeserialize(builder = AutoraterConfig.Builder.class)
public abstract class AutoraterConfig extends JsonSerializable {
  /** Optional. Configuration options for model generation and outputs. */
  @JsonProperty("generationConfig")
  public abstract Optional<GenerationConfig> generationConfig();

  /** Instantiates a builder for AutoraterConfig. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_AutoraterConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for AutoraterConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `AutoraterConfig.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_AutoraterConfig.Builder();
    }

    /**
     * Setter for generationConfig.
     *
     * <p>generationConfig: Optional. Configuration options for model generation and outputs.
     */
    @JsonProperty("generationConfig")
    public abstract Builder generationConfig(GenerationConfig generationConfig);

    /**
     * Setter for generationConfig builder.
     *
     * <p>generationConfig: Optional. Configuration options for model generation and outputs.
     */
    @CanIgnoreReturnValue
    public Builder generationConfig(GenerationConfig.Builder generationConfigBuilder) {
      return generationConfig(generationConfigBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder generationConfig(Optional<GenerationConfig> generationConfig);

    /** Clears the value of generationConfig field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearGenerationConfig() {
      return generationConfig(Optional.empty());
    }

    public abstract AutoraterConfig build();
  }

  /** Deserializes a JSON string to a AutoraterConfig object. */
  @ExcludeFromGeneratedCoverageReport
  public static AutoraterConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, AutoraterConfig.class);
  }
}
