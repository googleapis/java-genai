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

/** Single reinforcement tuning reward config. */
@AutoValue
@JsonDeserialize(builder = SingleReinforcementTuningRewardConfig.Builder.class)
public abstract class SingleReinforcementTuningRewardConfig extends JsonSerializable {
  /** */
  @JsonProperty("autoraterScorer")
  public abstract Optional<ReinforcementTuningAutoraterScorer> autoraterScorer();

  /** Instantiates a builder for SingleReinforcementTuningRewardConfig. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_SingleReinforcementTuningRewardConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for SingleReinforcementTuningRewardConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /**
     * For internal usage. Please use `SingleReinforcementTuningRewardConfig.builder()` for
     * instantiation.
     */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_SingleReinforcementTuningRewardConfig.Builder();
    }

    /**
     * Setter for autoraterScorer.
     *
     * <p>autoraterScorer:
     */
    @JsonProperty("autoraterScorer")
    public abstract Builder autoraterScorer(ReinforcementTuningAutoraterScorer autoraterScorer);

    /**
     * Setter for autoraterScorer builder.
     *
     * <p>autoraterScorer:
     */
    @CanIgnoreReturnValue
    public Builder autoraterScorer(
        ReinforcementTuningAutoraterScorer.Builder autoraterScorerBuilder) {
      return autoraterScorer(autoraterScorerBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder autoraterScorer(Optional<ReinforcementTuningAutoraterScorer> autoraterScorer);

    /** Clears the value of autoraterScorer field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearAutoraterScorer() {
      return autoraterScorer(Optional.empty());
    }

    public abstract SingleReinforcementTuningRewardConfig build();
  }

  /** Deserializes a JSON string to a SingleReinforcementTuningRewardConfig object. */
  @ExcludeFromGeneratedCoverageReport
  public static SingleReinforcementTuningRewardConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, SingleReinforcementTuningRewardConfig.class);
  }
}
