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

/**
 * The configuration for routing the request to a specific model. This can be used to control which
 * model is used for the generation, either automatically or by specifying a model name. This data
 * type is not supported in Gemini API.
 */
@AutoValue
@JsonDeserialize(builder = GenerationConfigRoutingConfig.Builder.class)
public abstract class GenerationConfigRoutingConfig extends JsonSerializable {
  /** In this mode, the model is selected automatically based on the content of the request. */
  @JsonProperty("autoMode")
  public abstract Optional<GenerationConfigRoutingConfigAutoRoutingMode> autoMode();

  /** In this mode, the model is specified manually. */
  @JsonProperty("manualMode")
  public abstract Optional<GenerationConfigRoutingConfigManualRoutingMode> manualMode();

  /** Instantiates a builder for GenerationConfigRoutingConfig. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_GenerationConfigRoutingConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GenerationConfigRoutingConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /**
     * For internal usage. Please use `GenerationConfigRoutingConfig.builder()` for instantiation.
     */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_GenerationConfigRoutingConfig.Builder();
    }

    /**
     * Setter for autoMode.
     *
     * <p>autoMode: In this mode, the model is selected automatically based on the content of the
     * request.
     */
    @JsonProperty("autoMode")
    public abstract Builder autoMode(GenerationConfigRoutingConfigAutoRoutingMode autoMode);

    /**
     * Setter for autoMode builder.
     *
     * <p>autoMode: In this mode, the model is selected automatically based on the content of the
     * request.
     */
    @CanIgnoreReturnValue
    public Builder autoMode(GenerationConfigRoutingConfigAutoRoutingMode.Builder autoModeBuilder) {
      return autoMode(autoModeBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder autoMode(Optional<GenerationConfigRoutingConfigAutoRoutingMode> autoMode);

    /** Clears the value of autoMode field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearAutoMode() {
      return autoMode(Optional.empty());
    }

    /**
     * Setter for manualMode.
     *
     * <p>manualMode: In this mode, the model is specified manually.
     */
    @JsonProperty("manualMode")
    public abstract Builder manualMode(GenerationConfigRoutingConfigManualRoutingMode manualMode);

    /**
     * Setter for manualMode builder.
     *
     * <p>manualMode: In this mode, the model is specified manually.
     */
    @CanIgnoreReturnValue
    public Builder manualMode(
        GenerationConfigRoutingConfigManualRoutingMode.Builder manualModeBuilder) {
      return manualMode(manualModeBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder manualMode(
        Optional<GenerationConfigRoutingConfigManualRoutingMode> manualMode);

    /** Clears the value of manualMode field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearManualMode() {
      return manualMode(Optional.empty());
    }

    public abstract GenerationConfigRoutingConfig build();
  }

  /** Deserializes a JSON string to a GenerationConfigRoutingConfig object. */
  @ExcludeFromGeneratedCoverageReport
  public static GenerationConfigRoutingConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GenerationConfigRoutingConfig.class);
  }
}
