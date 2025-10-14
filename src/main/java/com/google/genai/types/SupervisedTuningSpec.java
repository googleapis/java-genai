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

/** Supervised tuning spec for tuning. */
@AutoValue
@JsonDeserialize(builder = SupervisedTuningSpec.Builder.class)
public abstract class SupervisedTuningSpec extends JsonSerializable {
  /**
   * Optional. If set to true, disable intermediate checkpoints for SFT and only the last checkpoint
   * will be exported. Otherwise, enable intermediate checkpoints for SFT. Default is false.
   */
  @JsonProperty("exportLastCheckpointOnly")
  public abstract Optional<Boolean> exportLastCheckpointOnly();

  /** Optional. Hyperparameters for SFT. */
  @JsonProperty("hyperParameters")
  public abstract Optional<SupervisedHyperParameters> hyperParameters();

  /**
   * Required. Training dataset used for tuning. The dataset can be specified as either a Cloud
   * Storage path to a JSONL file or as the resource name of a Vertex Multimodal Dataset.
   */
  @JsonProperty("trainingDatasetUri")
  public abstract Optional<String> trainingDatasetUri();

  /** Tuning mode. */
  @JsonProperty("tuningMode")
  public abstract Optional<TuningMode> tuningMode();

  /**
   * Optional. Validation dataset used for tuning. The dataset can be specified as either a Cloud
   * Storage path to a JSONL file or as the resource name of a Vertex Multimodal Dataset.
   */
  @JsonProperty("validationDatasetUri")
  public abstract Optional<String> validationDatasetUri();

  /** Instantiates a builder for SupervisedTuningSpec. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_SupervisedTuningSpec.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for SupervisedTuningSpec. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `SupervisedTuningSpec.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_SupervisedTuningSpec.Builder();
    }

    /**
     * Setter for exportLastCheckpointOnly.
     *
     * <p>exportLastCheckpointOnly: Optional. If set to true, disable intermediate checkpoints for
     * SFT and only the last checkpoint will be exported. Otherwise, enable intermediate checkpoints
     * for SFT. Default is false.
     */
    @JsonProperty("exportLastCheckpointOnly")
    public abstract Builder exportLastCheckpointOnly(boolean exportLastCheckpointOnly);

    /**
     * Setter for hyperParameters.
     *
     * <p>hyperParameters: Optional. Hyperparameters for SFT.
     */
    @JsonProperty("hyperParameters")
    public abstract Builder hyperParameters(SupervisedHyperParameters hyperParameters);

    /**
     * Setter for hyperParameters builder.
     *
     * <p>hyperParameters: Optional. Hyperparameters for SFT.
     */
    public Builder hyperParameters(SupervisedHyperParameters.Builder hyperParametersBuilder) {
      return hyperParameters(hyperParametersBuilder.build());
    }

    /**
     * Setter for trainingDatasetUri.
     *
     * <p>trainingDatasetUri: Required. Training dataset used for tuning. The dataset can be
     * specified as either a Cloud Storage path to a JSONL file or as the resource name of a Vertex
     * Multimodal Dataset.
     */
    @JsonProperty("trainingDatasetUri")
    public abstract Builder trainingDatasetUri(String trainingDatasetUri);

    /**
     * Setter for tuningMode.
     *
     * <p>tuningMode: Tuning mode.
     */
    @JsonProperty("tuningMode")
    public abstract Builder tuningMode(TuningMode tuningMode);

    /**
     * Setter for tuningMode given a known enum.
     *
     * <p>tuningMode: Tuning mode.
     */
    @CanIgnoreReturnValue
    public Builder tuningMode(TuningMode.Known knownType) {
      return tuningMode(new TuningMode(knownType));
    }

    /**
     * Setter for tuningMode given a string.
     *
     * <p>tuningMode: Tuning mode.
     */
    @CanIgnoreReturnValue
    public Builder tuningMode(String tuningMode) {
      return tuningMode(new TuningMode(tuningMode));
    }

    /**
     * Setter for validationDatasetUri.
     *
     * <p>validationDatasetUri: Optional. Validation dataset used for tuning. The dataset can be
     * specified as either a Cloud Storage path to a JSONL file or as the resource name of a Vertex
     * Multimodal Dataset.
     */
    @JsonProperty("validationDatasetUri")
    public abstract Builder validationDatasetUri(String validationDatasetUri);

    public abstract SupervisedTuningSpec build();
  }

  /** Deserializes a JSON string to a SupervisedTuningSpec object. */
  @ExcludeFromGeneratedCoverageReport
  public static SupervisedTuningSpec fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, SupervisedTuningSpec.class);
  }
}
