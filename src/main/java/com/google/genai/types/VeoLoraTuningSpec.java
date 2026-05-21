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

/** Tuning Spec for Veo LoRA Model Tuning. This data type is not supported in Gemini API. */
@AutoValue
@JsonDeserialize(builder = VeoLoraTuningSpec.Builder.class)
public abstract class VeoLoraTuningSpec extends JsonSerializable {
  /** Optional. Hyperparameters for Veo LoRA. */
  @JsonProperty("hyperParameters")
  public abstract Optional<VeoHyperParameters> hyperParameters();

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

  /** Optional. The orientation of the video. Defaults to LANDSCAPE. */
  @JsonProperty("videoOrientation")
  public abstract Optional<VideoOrientation> videoOrientation();

  /** Instantiates a builder for VeoLoraTuningSpec. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_VeoLoraTuningSpec.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for VeoLoraTuningSpec. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `VeoLoraTuningSpec.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_VeoLoraTuningSpec.Builder();
    }

    /**
     * Setter for hyperParameters.
     *
     * <p>hyperParameters: Optional. Hyperparameters for Veo LoRA.
     */
    @JsonProperty("hyperParameters")
    public abstract Builder hyperParameters(VeoHyperParameters hyperParameters);

    /**
     * Setter for hyperParameters builder.
     *
     * <p>hyperParameters: Optional. Hyperparameters for Veo LoRA.
     */
    @CanIgnoreReturnValue
    public Builder hyperParameters(VeoHyperParameters.Builder hyperParametersBuilder) {
      return hyperParameters(hyperParametersBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder hyperParameters(Optional<VeoHyperParameters> hyperParameters);

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

    /**
     * Setter for videoOrientation.
     *
     * <p>videoOrientation: Optional. The orientation of the video. Defaults to LANDSCAPE.
     */
    @JsonProperty("videoOrientation")
    public abstract Builder videoOrientation(VideoOrientation videoOrientation);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder videoOrientation(Optional<VideoOrientation> videoOrientation);

    /** Clears the value of videoOrientation field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearVideoOrientation() {
      return videoOrientation(Optional.empty());
    }

    /**
     * Setter for videoOrientation given a known enum.
     *
     * <p>videoOrientation: Optional. The orientation of the video. Defaults to LANDSCAPE.
     */
    @CanIgnoreReturnValue
    public Builder videoOrientation(VideoOrientation.Known knownType) {
      return videoOrientation(new VideoOrientation(knownType));
    }

    /**
     * Setter for videoOrientation given a string.
     *
     * <p>videoOrientation: Optional. The orientation of the video. Defaults to LANDSCAPE.
     */
    @CanIgnoreReturnValue
    public Builder videoOrientation(String videoOrientation) {
      return videoOrientation(new VideoOrientation(videoOrientation));
    }

    public abstract VeoLoraTuningSpec build();
  }

  /** Deserializes a JSON string to a VeoLoraTuningSpec object. */
  @ExcludeFromGeneratedCoverageReport
  public static VeoLoraTuningSpec fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, VeoLoraTuningSpec.class);
  }
}
