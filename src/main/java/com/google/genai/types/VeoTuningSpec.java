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
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** Tuning Spec for Veo Model Tuning. This data type is not supported in Gemini API. */
@AutoValue
@JsonDeserialize(builder = VeoTuningSpec.Builder.class)
public abstract class VeoTuningSpec extends JsonSerializable {
  /** Optional. Hyperparameters for Veo. */
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

  /** Instantiates a builder for VeoTuningSpec. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_VeoTuningSpec.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for VeoTuningSpec. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `VeoTuningSpec.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_VeoTuningSpec.Builder();
    }

    /**
     * Setter for hyperParameters.
     *
     * <p>hyperParameters: Optional. Hyperparameters for Veo.
     */
    @JsonProperty("hyperParameters")
    public abstract Builder hyperParameters(VeoHyperParameters hyperParameters);

    /**
     * Setter for hyperParameters builder.
     *
     * <p>hyperParameters: Optional. Hyperparameters for Veo.
     */
    public Builder hyperParameters(VeoHyperParameters.Builder hyperParametersBuilder) {
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
     * Setter for validationDatasetUri.
     *
     * <p>validationDatasetUri: Optional. Validation dataset used for tuning. The dataset can be
     * specified as either a Cloud Storage path to a JSONL file or as the resource name of a Vertex
     * Multimodal Dataset.
     */
    @JsonProperty("validationDatasetUri")
    public abstract Builder validationDatasetUri(String validationDatasetUri);

    public abstract VeoTuningSpec build();
  }

  /** Deserializes a JSON string to a VeoTuningSpec object. */
  @ExcludeFromGeneratedCoverageReport
  public static VeoTuningSpec fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, VeoTuningSpec.class);
  }
}
