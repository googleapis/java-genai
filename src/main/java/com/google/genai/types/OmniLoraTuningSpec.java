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

/** Tuning Spec for Omni LoRA Model Tuning. This data type is not supported in Gemini API. */
@AutoValue
@JsonDeserialize(builder = OmniLoraTuningSpec.Builder.class)
public abstract class OmniLoraTuningSpec extends JsonSerializable {
  /** Optional. Hyperparameters for Omni LoRA tuning. */
  @JsonProperty("hyperParameters")
  public abstract Optional<OmniHyperParameters> hyperParameters();

  /**
   * Required. Cloud Storage path to the file containing training dataset for Omni LoRA tuning. The
   * dataset must be formatted as a JSONL file.
   */
  @JsonProperty("trainingDatasetUri")
  public abstract Optional<String> trainingDatasetUri();

  /** Instantiates a builder for OmniLoraTuningSpec. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_OmniLoraTuningSpec.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for OmniLoraTuningSpec. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `OmniLoraTuningSpec.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_OmniLoraTuningSpec.Builder();
    }

    /**
     * Setter for hyperParameters.
     *
     * <p>hyperParameters: Optional. Hyperparameters for Omni LoRA tuning.
     */
    @JsonProperty("hyperParameters")
    public abstract Builder hyperParameters(OmniHyperParameters hyperParameters);

    /**
     * Setter for hyperParameters builder.
     *
     * <p>hyperParameters: Optional. Hyperparameters for Omni LoRA tuning.
     */
    @CanIgnoreReturnValue
    public Builder hyperParameters(OmniHyperParameters.Builder hyperParametersBuilder) {
      return hyperParameters(hyperParametersBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder hyperParameters(Optional<OmniHyperParameters> hyperParameters);

    /** Clears the value of hyperParameters field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearHyperParameters() {
      return hyperParameters(Optional.empty());
    }

    /**
     * Setter for trainingDatasetUri.
     *
     * <p>trainingDatasetUri: Required. Cloud Storage path to the file containing training dataset
     * for Omni LoRA tuning. The dataset must be formatted as a JSONL file.
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

    public abstract OmniLoraTuningSpec build();
  }

  /** Deserializes a JSON string to a OmniLoraTuningSpec object. */
  @ExcludeFromGeneratedCoverageReport
  public static OmniLoraTuningSpec fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, OmniLoraTuningSpec.class);
  }
}
