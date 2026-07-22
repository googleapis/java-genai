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

/** Reinforcement tuning spec for tuning. */
@AutoValue
@JsonDeserialize(builder = ReinforcementTuningSpec.Builder.class)
public abstract class ReinforcementTuningSpec extends JsonSerializable {
  /** Composite reward function configuration for reinforcement tuning. */
  @JsonProperty("compositeRewardConfig")
  public abstract Optional<CompositeReinforcementTuningRewardConfig> compositeRewardConfig();

  /** Single reward function configuration for reinforcement tuning. */
  @JsonProperty("singleRewardConfig")
  public abstract Optional<SingleReinforcementTuningRewardConfig> singleRewardConfig();

  /** Additional hyper-parameters to use during tuning. */
  @JsonProperty("hyperParameters")
  public abstract Optional<ReinforcementTuningHyperParameters> hyperParameters();

  /**
   * Cloud Storage path to the file containing training dataset for tuning. The dataset must be
   * formatted as a JSONL file.
   */
  @JsonProperty("trainingDatasetUri")
  public abstract Optional<String> trainingDatasetUri();

  /**
   * Cloud Storage path to the file containing validation dataset for tuning. The dataset must be
   * formatted as a JSONL file.
   */
  @JsonProperty("validationDatasetUri")
  public abstract Optional<String> validationDatasetUri();

  /** Instantiates a builder for ReinforcementTuningSpec. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ReinforcementTuningSpec.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ReinforcementTuningSpec. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `ReinforcementTuningSpec.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ReinforcementTuningSpec.Builder();
    }

    /**
     * Setter for compositeRewardConfig.
     *
     * <p>compositeRewardConfig: Composite reward function configuration for reinforcement tuning.
     */
    @JsonProperty("compositeRewardConfig")
    public abstract Builder compositeRewardConfig(
        CompositeReinforcementTuningRewardConfig compositeRewardConfig);

    /**
     * Setter for compositeRewardConfig builder.
     *
     * <p>compositeRewardConfig: Composite reward function configuration for reinforcement tuning.
     */
    @CanIgnoreReturnValue
    public Builder compositeRewardConfig(
        CompositeReinforcementTuningRewardConfig.Builder compositeRewardConfigBuilder) {
      return compositeRewardConfig(compositeRewardConfigBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder compositeRewardConfig(
        Optional<CompositeReinforcementTuningRewardConfig> compositeRewardConfig);

    /** Clears the value of compositeRewardConfig field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearCompositeRewardConfig() {
      return compositeRewardConfig(Optional.empty());
    }

    /**
     * Setter for singleRewardConfig.
     *
     * <p>singleRewardConfig: Single reward function configuration for reinforcement tuning.
     */
    @JsonProperty("singleRewardConfig")
    public abstract Builder singleRewardConfig(
        SingleReinforcementTuningRewardConfig singleRewardConfig);

    /**
     * Setter for singleRewardConfig builder.
     *
     * <p>singleRewardConfig: Single reward function configuration for reinforcement tuning.
     */
    @CanIgnoreReturnValue
    public Builder singleRewardConfig(
        SingleReinforcementTuningRewardConfig.Builder singleRewardConfigBuilder) {
      return singleRewardConfig(singleRewardConfigBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder singleRewardConfig(
        Optional<SingleReinforcementTuningRewardConfig> singleRewardConfig);

    /** Clears the value of singleRewardConfig field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearSingleRewardConfig() {
      return singleRewardConfig(Optional.empty());
    }

    /**
     * Setter for hyperParameters.
     *
     * <p>hyperParameters: Additional hyper-parameters to use during tuning.
     */
    @JsonProperty("hyperParameters")
    public abstract Builder hyperParameters(ReinforcementTuningHyperParameters hyperParameters);

    /**
     * Setter for hyperParameters builder.
     *
     * <p>hyperParameters: Additional hyper-parameters to use during tuning.
     */
    @CanIgnoreReturnValue
    public Builder hyperParameters(
        ReinforcementTuningHyperParameters.Builder hyperParametersBuilder) {
      return hyperParameters(hyperParametersBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder hyperParameters(Optional<ReinforcementTuningHyperParameters> hyperParameters);

    /** Clears the value of hyperParameters field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearHyperParameters() {
      return hyperParameters(Optional.empty());
    }

    /**
     * Setter for trainingDatasetUri.
     *
     * <p>trainingDatasetUri: Cloud Storage path to the file containing training dataset for tuning.
     * The dataset must be formatted as a JSONL file.
     */
    @JsonProperty("trainingDatasetUri")
    public abstract Builder trainingDatasetUri(String trainingDatasetUri);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder trainingDatasetUri(Optional<String> trainingDatasetUri);

    /** Clears the value of trainingDatasetUri field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTrainingDatasetUri() {
      return trainingDatasetUri(Optional.empty());
    }

    /**
     * Setter for validationDatasetUri.
     *
     * <p>validationDatasetUri: Cloud Storage path to the file containing validation dataset for
     * tuning. The dataset must be formatted as a JSONL file.
     */
    @JsonProperty("validationDatasetUri")
    public abstract Builder validationDatasetUri(String validationDatasetUri);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder validationDatasetUri(Optional<String> validationDatasetUri);

    /** Clears the value of validationDatasetUri field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearValidationDatasetUri() {
      return validationDatasetUri(Optional.empty());
    }

    public abstract ReinforcementTuningSpec build();
  }

  /** Deserializes a JSON string to a ReinforcementTuningSpec object. */
  @ExcludeFromGeneratedCoverageReport
  public static ReinforcementTuningSpec fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ReinforcementTuningSpec.class);
  }
}
