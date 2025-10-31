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

/** Preference optimization tuning spec for tuning. */
@AutoValue
@JsonDeserialize(builder = PreferenceOptimizationSpec.Builder.class)
public abstract class PreferenceOptimizationSpec extends JsonSerializable {
  /**
   * Optional. If set to true, disable intermediate checkpoints for Preference Optimization and only
   * the last checkpoint will be exported. Otherwise, enable intermediate checkpoints for Preference
   * Optimization. Default is false.
   */
  @JsonProperty("exportLastCheckpointOnly")
  public abstract Optional<Boolean> exportLastCheckpointOnly();

  /** Optional. Hyperparameters for Preference Optimization. */
  @JsonProperty("hyperParameters")
  public abstract Optional<PreferenceOptimizationHyperParameters> hyperParameters();

  /**
   * Required. Cloud Storage path to file containing training dataset for preference optimization
   * tuning. The dataset must be formatted as a JSONL file.
   */
  @JsonProperty("trainingDatasetUri")
  public abstract Optional<String> trainingDatasetUri();

  /**
   * Optional. Cloud Storage path to file containing validation dataset for preference optimization
   * tuning. The dataset must be formatted as a JSONL file.
   */
  @JsonProperty("validationDatasetUri")
  public abstract Optional<String> validationDatasetUri();

  /** Instantiates a builder for PreferenceOptimizationSpec. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_PreferenceOptimizationSpec.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for PreferenceOptimizationSpec. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `PreferenceOptimizationSpec.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_PreferenceOptimizationSpec.Builder();
    }

    /**
     * Setter for exportLastCheckpointOnly.
     *
     * <p>exportLastCheckpointOnly: Optional. If set to true, disable intermediate checkpoints for
     * Preference Optimization and only the last checkpoint will be exported. Otherwise, enable
     * intermediate checkpoints for Preference Optimization. Default is false.
     */
    @JsonProperty("exportLastCheckpointOnly")
    public abstract Builder exportLastCheckpointOnly(boolean exportLastCheckpointOnly);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder exportLastCheckpointOnly(Optional<Boolean> exportLastCheckpointOnly);

    /** Clears the value of exportLastCheckpointOnly field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearExportLastCheckpointOnly() {
      return exportLastCheckpointOnly(Optional.empty());
    }

    /**
     * Setter for hyperParameters.
     *
     * <p>hyperParameters: Optional. Hyperparameters for Preference Optimization.
     */
    @JsonProperty("hyperParameters")
    public abstract Builder hyperParameters(PreferenceOptimizationHyperParameters hyperParameters);

    /**
     * Setter for hyperParameters builder.
     *
     * <p>hyperParameters: Optional. Hyperparameters for Preference Optimization.
     */
    @CanIgnoreReturnValue
    public Builder hyperParameters(
        PreferenceOptimizationHyperParameters.Builder hyperParametersBuilder) {
      return hyperParameters(hyperParametersBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder hyperParameters(
        Optional<PreferenceOptimizationHyperParameters> hyperParameters);

    /** Clears the value of hyperParameters field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearHyperParameters() {
      return hyperParameters(Optional.empty());
    }

    /**
     * Setter for trainingDatasetUri.
     *
     * <p>trainingDatasetUri: Required. Cloud Storage path to file containing training dataset for
     * preference optimization tuning. The dataset must be formatted as a JSONL file.
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
     * <p>validationDatasetUri: Optional. Cloud Storage path to file containing validation dataset
     * for preference optimization tuning. The dataset must be formatted as a JSONL file.
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

    public abstract PreferenceOptimizationSpec build();
  }

  /** Deserializes a JSON string to a PreferenceOptimizationSpec object. */
  @ExcludeFromGeneratedCoverageReport
  public static PreferenceOptimizationSpec fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, PreferenceOptimizationSpec.class);
  }
}
