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

/** Tuning Spec for Full Fine Tuning. This data type is not supported in Gemini API. */
@AutoValue
@JsonDeserialize(builder = FullFineTuningSpec.Builder.class)
public abstract class FullFineTuningSpec extends JsonSerializable {
  /** Optional. Hyperparameters for Full Fine Tuning. */
  @JsonProperty("hyperParameters")
  public abstract Optional<SupervisedHyperParameters> hyperParameters();

  /**
   * Required. Training dataset used for tuning. The dataset can be specified as either a Cloud
   * Storage path to a JSONL file or as the resource name of a Vertex Multimodal Dataset.
   */
  @JsonProperty("trainingDatasetUri")
  public abstract Optional<String> trainingDatasetUri();

  /**
   * Optional. Validation dataset used for tuning. The dataset can be specified as either a Cloud
   * Storage path to a JSONL file or as the resource name of a Vertex Multimodal Dataset.
   */
  @JsonProperty("validationDatasetUri")
  public abstract Optional<String> validationDatasetUri();

  /** Instantiates a builder for FullFineTuningSpec. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_FullFineTuningSpec.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for FullFineTuningSpec. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `FullFineTuningSpec.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_FullFineTuningSpec.Builder();
    }

    /**
     * Setter for hyperParameters.
     *
     * <p>hyperParameters: Optional. Hyperparameters for Full Fine Tuning.
     */
    @JsonProperty("hyperParameters")
    public abstract Builder hyperParameters(SupervisedHyperParameters hyperParameters);

    /**
     * Setter for hyperParameters builder.
     *
     * <p>hyperParameters: Optional. Hyperparameters for Full Fine Tuning.
     */
    @CanIgnoreReturnValue
    public Builder hyperParameters(SupervisedHyperParameters.Builder hyperParametersBuilder) {
      return hyperParameters(hyperParametersBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder hyperParameters(Optional<SupervisedHyperParameters> hyperParameters);

    /** Clears the value of hyperParameters field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearHyperParameters() {
      return hyperParameters(Optional.empty());
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
     * <p>validationDatasetUri: Optional. Validation dataset used for tuning. The dataset can be
     * specified as either a Cloud Storage path to a JSONL file or as the resource name of a Vertex
     * Multimodal Dataset.
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

    public abstract FullFineTuningSpec build();
  }

  /** Deserializes a JSON string to a FullFineTuningSpec object. */
  @ExcludeFromGeneratedCoverageReport
  public static FullFineTuningSpec fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, FullFineTuningSpec.class);
  }
}
