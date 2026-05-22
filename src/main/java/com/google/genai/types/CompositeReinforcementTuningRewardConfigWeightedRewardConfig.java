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

/** Composite reinforcement tuning reward config weighted reward config. */
@AutoValue
@JsonDeserialize(
    builder = CompositeReinforcementTuningRewardConfigWeightedRewardConfig.Builder.class)
public abstract class CompositeReinforcementTuningRewardConfigWeightedRewardConfig
    extends JsonSerializable {
  /** */
  @JsonProperty("rewardConfig")
  public abstract Optional<SingleReinforcementTuningRewardConfig> rewardConfig();

  /** How much this single reward contributes to the total overall reward. */
  @JsonProperty("weight")
  public abstract Optional<Float> weight();

  /** Instantiates a builder for CompositeReinforcementTuningRewardConfigWeightedRewardConfig. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_CompositeReinforcementTuningRewardConfigWeightedRewardConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for CompositeReinforcementTuningRewardConfigWeightedRewardConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /**
     * For internal usage. Please use
     * `CompositeReinforcementTuningRewardConfigWeightedRewardConfig.builder()` for instantiation.
     */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_CompositeReinforcementTuningRewardConfigWeightedRewardConfig.Builder();
    }

    /**
     * Setter for rewardConfig.
     *
     * <p>rewardConfig:
     */
    @JsonProperty("rewardConfig")
    public abstract Builder rewardConfig(SingleReinforcementTuningRewardConfig rewardConfig);

    /**
     * Setter for rewardConfig builder.
     *
     * <p>rewardConfig:
     */
    @CanIgnoreReturnValue
    public Builder rewardConfig(SingleReinforcementTuningRewardConfig.Builder rewardConfigBuilder) {
      return rewardConfig(rewardConfigBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder rewardConfig(Optional<SingleReinforcementTuningRewardConfig> rewardConfig);

    /** Clears the value of rewardConfig field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearRewardConfig() {
      return rewardConfig(Optional.empty());
    }

    /**
     * Setter for weight.
     *
     * <p>weight: How much this single reward contributes to the total overall reward.
     */
    @JsonProperty("weight")
    public abstract Builder weight(Float weight);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder weight(Optional<Float> weight);

    /** Clears the value of weight field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearWeight() {
      return weight(Optional.empty());
    }

    public abstract CompositeReinforcementTuningRewardConfigWeightedRewardConfig build();
  }

  /**
   * Deserializes a JSON string to a CompositeReinforcementTuningRewardConfigWeightedRewardConfig
   * object.
   */
  @ExcludeFromGeneratedCoverageReport
  public static CompositeReinforcementTuningRewardConfigWeightedRewardConfig fromJson(
      String jsonString) {
    return JsonSerializable.fromJsonString(
        jsonString, CompositeReinforcementTuningRewardConfigWeightedRewardConfig.class);
  }
}
