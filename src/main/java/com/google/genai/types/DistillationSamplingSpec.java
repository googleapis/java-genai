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

/**
 * Spec for creating a distilled dataset in Vertex Dataset. This data type is not supported in
 * Gemini API.
 */
@AutoValue
@JsonDeserialize(builder = DistillationSamplingSpec.Builder.class)
public abstract class DistillationSamplingSpec extends JsonSerializable {
  /**
   * Optional. The base teacher model that is being distilled. See [Supported
   * models](https://cloud.google.com/vertex-ai/generative-ai/docs/model-reference/tuning#supported_models).
   */
  @JsonProperty("baseTeacherModel")
  public abstract Optional<String> baseTeacherModel();

  /**
   * Optional. The resource name of the Tuned teacher model. Format:
   * `projects/{project}/locations/{location}/models/{model}`.
   */
  @JsonProperty("tunedTeacherModelSource")
  public abstract Optional<String> tunedTeacherModelSource();

  /**
   * Optional. Cloud Storage path to file containing validation dataset for distillation. The
   * dataset must be formatted as a JSONL file.
   */
  @JsonProperty("validationDatasetUri")
  public abstract Optional<String> validationDatasetUri();

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
     * Setter for baseTeacherModel.
     *
     * <p>baseTeacherModel: Optional. The base teacher model that is being distilled. See [Supported
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
     * <p>tunedTeacherModelSource: Optional. The resource name of the Tuned teacher model. Format:
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
     * for distillation. The dataset must be formatted as a JSONL file.
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

    public abstract DistillationSamplingSpec build();
  }

  /** Deserializes a JSON string to a DistillationSamplingSpec object. */
  @ExcludeFromGeneratedCoverageReport
  public static DistillationSamplingSpec fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, DistillationSamplingSpec.class);
  }
}
