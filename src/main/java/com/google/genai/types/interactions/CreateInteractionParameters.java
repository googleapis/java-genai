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

package com.google.genai.types.interactions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.api.core.InternalApi;
import com.google.auto.value.AutoValue;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import com.google.genai.types.ExcludeFromGeneratedCoverageReport;
import java.util.Optional;

/** Parameters for interactions.create method. */
@AutoValue
@InternalApi
@JsonDeserialize(builder = CreateInteractionParameters.Builder.class)
public abstract class CreateInteractionParameters extends JsonSerializable {
  /** Configuration that contains parameters for the interaction. */
  @JsonProperty("config")
  public abstract Optional<CreateInteractionConfig> config();

  /** Instantiates a builder for CreateInteractionParameters. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_CreateInteractionParameters.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for CreateInteractionParameters. */
  @AutoValue.Builder
  public abstract static class Builder {
    /**
     * For internal usage. Please use `CreateInteractionParameters.builder()` for instantiation.
     */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_CreateInteractionParameters.Builder();
    }

    /**
     * Setter for config.
     *
     * <p>config: Configuration that contains parameters for the interaction.
     */
    @JsonProperty("config")
    public abstract Builder config(CreateInteractionConfig config);

    /**
     * Setter for config builder.
     *
     * <p>config: Configuration that contains parameters for the interaction.
     */
    @CanIgnoreReturnValue
    public Builder config(CreateInteractionConfig.Builder configBuilder) {
      return config(configBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder config(Optional<CreateInteractionConfig> config);

    /** Clears the value of config field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearConfig() {
      return config(Optional.empty());
    }

    /** Builds the CreateInteractionParameters instance. */
    public abstract CreateInteractionParameters build();
  }

  /** Deserializes a CreateInteractionParameters from a JSON string. */
  @ExcludeFromGeneratedCoverageReport
  public static CreateInteractionParameters fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, CreateInteractionParameters.class);
  }
}
