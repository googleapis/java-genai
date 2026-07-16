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

/** Distillation sampling spec for tuning. */
@AutoValue
@JsonDeserialize(builder = DistillationSamplingSpec.Builder.class)
public abstract class DistillationSamplingSpec extends JsonSerializable {
  /**
   * Cloud Storage path to file containing prompt dataset for distillation. The dataset must be
   * formatted as a JSONL file.
   */
  @JsonProperty("promptDatasetUri")
  public abstract Optional<String> promptDatasetUri();

  /**
   * Cloud Storage path to file containing validation dataset for distillation. The dataset must be
   * formatted as a JSONL file.
   */
  @JsonProperty("validationDatasetUri")
  public abstract Optional<String> validationDatasetUri();

  /**
   * The base teacher model that is being distilled. See [Supported
   * models](https://cloud.google.com/vertex-ai/generative-ai/docs/model-reference/tuning#supported_models).
   */
  @JsonProperty("baseTeacherModel")
  public abstract Optional<String> baseTeacherModel();

  /**
   * The resource name of the Tuned teacher model. Format:
   * `projects/{project}/locations/{location}/models/{model}`.
   */
  @JsonProperty("tunedTeacherModelSource")
  public abstract Optional<String> tunedTeacherModelSource();

  /** Hyperparameters for distillation tuning. */
  @JsonProperty("hyperparameters")
  public abstract Optional<DistillationHyperParameters> hyperparameters();

  /** Instantiates a builder for DistillationSamplingSpec. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_DistillationSamplingSpec.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for DistillationSamplingSpec. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `DistillationSamplingSpec.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_DistillationSamplingSpec.Builder();
    }

    /**
     * Setter for promptDatasetUri.
     *
     * <p>promptDatasetUri: Cloud Storage path to file containing prompt dataset for distillation.
     * The dataset must be formatted as a JSONL file.
     */
    @JsonProperty("promptDatasetUri")
    public abstract Builder promptDatasetUri(String promptDatasetUri);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder promptDatasetUri(Optional<String> promptDatasetUri);

    /** Clears the value of promptDatasetUri field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearPromptDatasetUri() {
      return promptDatasetUri(Optional.empty());
    }

    /**
     * Setter for validationDatasetUri.
     *
     * <p>validationDatasetUri: Cloud Storage path to file containing validation dataset for
     * distillation. The dataset must be formatted as a JSONL file.
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

    /**
     * Setter for baseTeacherModel.
     *
     * <p>baseTeacherModel: The base teacher model that is being distilled. See [Supported
     * models](https://cloud.google.com/vertex-ai/generative-ai/docs/model-reference/tuning#supported_models).
     */
    @JsonProperty("baseTeacherModel")
    public abstract Builder baseTeacherModel(String baseTeacherModel);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder baseTeacherModel(Optional<String> baseTeacherModel);

    /** Clears the value of baseTeacherModel field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearBaseTeacherModel() {
      return baseTeacherModel(Optional.empty());
    }

    /**
     * Setter for tunedTeacherModelSource.
     *
     * <p>tunedTeacherModelSource: The resource name of the Tuned teacher model. Format:
     * `projects/{project}/locations/{location}/models/{model}`.
     */
    @JsonProperty("tunedTeacherModelSource")
    public abstract Builder tunedTeacherModelSource(String tunedTeacherModelSource);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder tunedTeacherModelSource(Optional<String> tunedTeacherModelSource);

    /** Clears the value of tunedTeacherModelSource field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTunedTeacherModelSource() {
      return tunedTeacherModelSource(Optional.empty());
    }

    /**
     * Setter for hyperparameters.
     *
     * <p>hyperparameters: Hyperparameters for distillation tuning.
     */
    @JsonProperty("hyperparameters")
    public abstract Builder hyperparameters(DistillationHyperParameters hyperparameters);

    /**
     * Setter for hyperparameters builder.
     *
     * <p>hyperparameters: Hyperparameters for distillation tuning.
     */
    @CanIgnoreReturnValue
    public Builder hyperparameters(DistillationHyperParameters.Builder hyperparametersBuilder) {
      return hyperparameters(hyperparametersBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder hyperparameters(Optional<DistillationHyperParameters> hyperparameters);

    /** Clears the value of hyperparameters field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearHyperparameters() {
      return hyperparameters(Optional.empty());
    }

    public abstract DistillationSamplingSpec build();
  }

  /** Deserializes a JSON string to a DistillationSamplingSpec object. */
  @ExcludeFromGeneratedCoverageReport
  public static DistillationSamplingSpec fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, DistillationSamplingSpec.class);
  }
}
