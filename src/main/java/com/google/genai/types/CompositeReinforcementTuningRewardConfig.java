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

import static com.google.common.collect.ImmutableList.toImmutableList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/** Composite reinforcement tuning reward config. */
@AutoValue
@JsonDeserialize(builder = CompositeReinforcementTuningRewardConfig.Builder.class)
public abstract class CompositeReinforcementTuningRewardConfig extends JsonSerializable {
  /** List of reward function configurations with weights. */
  @JsonProperty("weightedRewardConfigs")
  public abstract Optional<List<CompositeReinforcementTuningRewardConfigWeightedRewardConfig>>
      weightedRewardConfigs();

  /** Instantiates a builder for CompositeReinforcementTuningRewardConfig. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_CompositeReinforcementTuningRewardConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for CompositeReinforcementTuningRewardConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /**
     * For internal usage. Please use `CompositeReinforcementTuningRewardConfig.builder()` for
     * instantiation.
     */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_CompositeReinforcementTuningRewardConfig.Builder();
    }

    /**
     * Setter for weightedRewardConfigs.
     *
     * <p>weightedRewardConfigs: List of reward function configurations with weights.
     */
    @JsonProperty("weightedRewardConfigs")
    public abstract Builder weightedRewardConfigs(
        List<CompositeReinforcementTuningRewardConfigWeightedRewardConfig> weightedRewardConfigs);

    /**
     * Setter for weightedRewardConfigs.
     *
     * <p>weightedRewardConfigs: List of reward function configurations with weights.
     */
    @CanIgnoreReturnValue
    public Builder weightedRewardConfigs(
        CompositeReinforcementTuningRewardConfigWeightedRewardConfig... weightedRewardConfigs) {
      return weightedRewardConfigs(Arrays.asList(weightedRewardConfigs));
    }

    /**
     * Setter for weightedRewardConfigs builder.
     *
     * <p>weightedRewardConfigs: List of reward function configurations with weights.
     */
    @CanIgnoreReturnValue
    public Builder weightedRewardConfigs(
        CompositeReinforcementTuningRewardConfigWeightedRewardConfig.Builder...
            weightedRewardConfigsBuilders) {
      return weightedRewardConfigs(
          Arrays.asList(weightedRewardConfigsBuilders).stream()
              .map(CompositeReinforcementTuningRewardConfigWeightedRewardConfig.Builder::build)
              .collect(toImmutableList()));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder weightedRewardConfigs(
        Optional<List<CompositeReinforcementTuningRewardConfigWeightedRewardConfig>>
            weightedRewardConfigs);

    /** Clears the value of weightedRewardConfigs field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearWeightedRewardConfigs() {
      return weightedRewardConfigs(Optional.empty());
    }

    public abstract CompositeReinforcementTuningRewardConfig build();
  }

  /** Deserializes a JSON string to a CompositeReinforcementTuningRewardConfig object. */
  @ExcludeFromGeneratedCoverageReport
  public static CompositeReinforcementTuningRewardConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(
        jsonString, CompositeReinforcementTuningRewardConfig.class);
  }
}
