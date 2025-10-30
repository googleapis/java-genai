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
import java.util.Map;
import java.util.Optional;

/** Tuning spec for Partner models. This data type is not supported in Gemini API. */
@AutoValue
@JsonDeserialize(builder = PartnerModelTuningSpec.Builder.class)
public abstract class PartnerModelTuningSpec extends JsonSerializable {
  /**
   * Hyperparameters for tuning. The accepted hyper_parameters and their valid range of values will
   * differ depending on the base model.
   */
  @JsonProperty("hyperParameters")
  public abstract Optional<Map<String, Object>> hyperParameters();

  /**
   * Required. Cloud Storage path to file containing training dataset for tuning. The dataset must
   * be formatted as a JSONL file.
   */
  @JsonProperty("trainingDatasetUri")
  public abstract Optional<String> trainingDatasetUri();

  /**
   * Optional. Cloud Storage path to file containing validation dataset for tuning. The dataset must
   * be formatted as a JSONL file.
   */
  @JsonProperty("validationDatasetUri")
  public abstract Optional<String> validationDatasetUri();

  /** Instantiates a builder for PartnerModelTuningSpec. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_PartnerModelTuningSpec.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for PartnerModelTuningSpec. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `PartnerModelTuningSpec.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_PartnerModelTuningSpec.Builder();
    }

    /**
     * Setter for hyperParameters.
     *
     * <p>hyperParameters: Hyperparameters for tuning. The accepted hyper_parameters and their valid
     * range of values will differ depending on the base model.
     */
    @JsonProperty("hyperParameters")
    public abstract Builder hyperParameters(Map<String, Object> hyperParameters);

    /**
     * Setter for trainingDatasetUri.
     *
     * <p>trainingDatasetUri: Required. Cloud Storage path to file containing training dataset for
     * tuning. The dataset must be formatted as a JSONL file.
     */
    @JsonProperty("trainingDatasetUri")
    public abstract Builder trainingDatasetUri(String trainingDatasetUri);

    /**
     * Setter for validationDatasetUri.
     *
     * <p>validationDatasetUri: Optional. Cloud Storage path to file containing validation dataset
     * for tuning. The dataset must be formatted as a JSONL file.
     */
    @JsonProperty("validationDatasetUri")
    public abstract Builder validationDatasetUri(String validationDatasetUri);

    public abstract PartnerModelTuningSpec build();
  }

  /** Deserializes a JSON string to a PartnerModelTuningSpec object. */
  @ExcludeFromGeneratedCoverageReport
  public static PartnerModelTuningSpec fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, PartnerModelTuningSpec.class);
  }
}
