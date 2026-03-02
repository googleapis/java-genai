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

/** Distillation tuning spec for tuning. */
@AutoValue
@JsonDeserialize(builder = DistillationSpec.Builder.class)
public abstract class DistillationSpec extends JsonSerializable {
  /** The GCS URI of the prompt dataset to use during distillation. */
  @JsonProperty("promptDatasetUri")
  public abstract Optional<String> promptDatasetUri();

  /**
   * The base teacher model that is being distilled. See [Supported
   * models](https://cloud.google.com/vertex-ai/generative-ai/docs/model-reference/tuning#supported_models).
   */
  @JsonProperty("baseTeacherModel")
  public abstract Optional<String> baseTeacherModel();

  /** Optional. Hyperparameters for Distillation. */
  @JsonProperty("hyperParameters")
  public abstract Optional<DistillationHyperParameters> hyperParameters();

  /**
   * Deprecated. A path in a Cloud Storage bucket, which will be treated as the root output
   * directory of the distillation pipeline. It is used by the system to generate the paths of
   * output artifacts.
   */
  @JsonProperty("pipelineRootDirectory")
  public abstract Optional<String> pipelineRootDirectory();

  /**
   * The student model that is being tuned, e.g., "google/gemma-2b-1.1-it". Deprecated. Use
   * base_model instead.
   */
  @JsonProperty("studentModel")
  public abstract Optional<String> studentModel();

  /**
   * Deprecated. Cloud Storage path to file containing training dataset for tuning. The dataset must
   * be formatted as a JSONL file.
   */
  @JsonProperty("trainingDatasetUri")
  public abstract Optional<String> trainingDatasetUri();

  /**
   * The resource name of the Tuned teacher model. Format:
   * `projects/{project}/locations/{location}/models/{model}`.
   */
  @JsonProperty("tunedTeacherModelSource")
  public abstract Optional<String> tunedTeacherModelSource();

  /**
   * Optional. Cloud Storage path to file containing validation dataset for tuning. The dataset must
   * be formatted as a JSONL file.
   */
  @JsonProperty("validationDatasetUri")
  public abstract Optional<String> validationDatasetUri();

  /** Instantiates a builder for DistillationSpec. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_DistillationSpec.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for DistillationSpec. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `DistillationSpec.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_DistillationSpec.Builder();
    }

    /**
     * Setter for promptDatasetUri.
     *
     * <p>promptDatasetUri: The GCS URI of the prompt dataset to use during distillation.
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
     * Setter for hyperParameters.
     *
     * <p>hyperParameters: Optional. Hyperparameters for Distillation.
     */
    @JsonProperty("hyperParameters")
    public abstract Builder hyperParameters(DistillationHyperParameters hyperParameters);

    /**
     * Setter for hyperParameters builder.
     *
     * <p>hyperParameters: Optional. Hyperparameters for Distillation.
     */
    @CanIgnoreReturnValue
    public Builder hyperParameters(DistillationHyperParameters.Builder hyperParametersBuilder) {
      return hyperParameters(hyperParametersBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder hyperParameters(Optional<DistillationHyperParameters> hyperParameters);

    /** Clears the value of hyperParameters field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearHyperParameters() {
      return hyperParameters(Optional.empty());
    }

    /**
     * Setter for pipelineRootDirectory.
     *
     * <p>pipelineRootDirectory: Deprecated. A path in a Cloud Storage bucket, which will be treated
     * as the root output directory of the distillation pipeline. It is used by the system to
     * generate the paths of output artifacts.
     */
    @JsonProperty("pipelineRootDirectory")
    public abstract Builder pipelineRootDirectory(String pipelineRootDirectory);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder pipelineRootDirectory(Optional<String> pipelineRootDirectory);

    /** Clears the value of pipelineRootDirectory field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearPipelineRootDirectory() {
      return pipelineRootDirectory(Optional.empty());
    }

    /**
     * Setter for studentModel.
     *
     * <p>studentModel: The student model that is being tuned, e.g., "google/gemma-2b-1.1-it".
     * Deprecated. Use base_model instead.
     */
    @JsonProperty("studentModel")
    public abstract Builder studentModel(String studentModel);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder studentModel(Optional<String> studentModel);

    /** Clears the value of studentModel field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearStudentModel() {
      return studentModel(Optional.empty());
    }

    /**
     * Setter for trainingDatasetUri.
     *
     * <p>trainingDatasetUri: Deprecated. Cloud Storage path to file containing training dataset for
     * tuning. The dataset must be formatted as a JSONL file.
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
     * Setter for validationDatasetUri.
     *
     * <p>validationDatasetUri: Optional. Cloud Storage path to file containing validation dataset
     * for tuning. The dataset must be formatted as a JSONL file.
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

    public abstract DistillationSpec build();
  }

  /** Deserializes a JSON string to a DistillationSpec object. */
  @ExcludeFromGeneratedCoverageReport
  public static DistillationSpec fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, DistillationSpec.class);
  }
}
