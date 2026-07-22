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

/** Autorater config used for evaluation. */
@AutoValue
@JsonDeserialize(builder = AutoraterConfig.Builder.class)
public abstract class AutoraterConfig extends JsonSerializable {
  /**
   * Number of samples for each instance in the dataset. If not specified, the default is 4. Minimum
   * value is 1, maximum value is 32.
   */
  @JsonProperty("samplingCount")
  public abstract Optional<Integer> samplingCount();

  /**
   * Optional. Default is true. Whether to flip the candidate and baseline responses. This is only
   * applicable to the pairwise metric. If enabled, also provide
   * PairwiseMetricSpec.candidate_response_field_name and
   * PairwiseMetricSpec.baseline_response_field_name. When rendering
   * PairwiseMetricSpec.metric_prompt_template, the candidate and baseline fields will be flipped
   * for half of the samples to reduce bias.
   */
  @JsonProperty("flipEnabled")
  public abstract Optional<Boolean> flipEnabled();

  /**
   * The fully qualified name of the publisher model or tuned autorater endpoint to use.
   *
   * <p>Publisher model format:
   * `projects/{project}/locations/{location}/publishers/{publisher}/models/{model}`
   *
   * <p>Tuned model endpoint format: `projects/{project}/locations/{location}/endpoints/{endpoint}`
   */
  @JsonProperty("autoraterModel")
  public abstract Optional<String> autoraterModel();

  /** Configuration options for model generation and outputs. */
  @JsonProperty("generationConfig")
  public abstract Optional<GenerationConfig> generationConfig();

  /** Instantiates a builder for AutoraterConfig. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_AutoraterConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for AutoraterConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `AutoraterConfig.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_AutoraterConfig.Builder();
    }

    /**
     * Setter for samplingCount.
     *
     * <p>samplingCount: Number of samples for each instance in the dataset. If not specified, the
     * default is 4. Minimum value is 1, maximum value is 32.
     */
    @JsonProperty("samplingCount")
    public abstract Builder samplingCount(Integer samplingCount);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder samplingCount(Optional<Integer> samplingCount);

    /** Clears the value of samplingCount field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearSamplingCount() {
      return samplingCount(Optional.empty());
    }

    /**
     * Setter for flipEnabled.
     *
     * <p>flipEnabled: Optional. Default is true. Whether to flip the candidate and baseline
     * responses. This is only applicable to the pairwise metric. If enabled, also provide
     * PairwiseMetricSpec.candidate_response_field_name and
     * PairwiseMetricSpec.baseline_response_field_name. When rendering
     * PairwiseMetricSpec.metric_prompt_template, the candidate and baseline fields will be flipped
     * for half of the samples to reduce bias.
     */
    @JsonProperty("flipEnabled")
    public abstract Builder flipEnabled(boolean flipEnabled);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder flipEnabled(Optional<Boolean> flipEnabled);

    /** Clears the value of flipEnabled field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearFlipEnabled() {
      return flipEnabled(Optional.empty());
    }

    /**
     * Setter for autoraterModel.
     *
     * <p>autoraterModel: The fully qualified name of the publisher model or tuned autorater
     * endpoint to use.
     *
     * <p>Publisher model format:
     * `projects/{project}/locations/{location}/publishers/{publisher}/models/{model}`
     *
     * <p>Tuned model endpoint format:
     * `projects/{project}/locations/{location}/endpoints/{endpoint}`
     */
    @JsonProperty("autoraterModel")
    public abstract Builder autoraterModel(String autoraterModel);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder autoraterModel(Optional<String> autoraterModel);

    /** Clears the value of autoraterModel field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearAutoraterModel() {
      return autoraterModel(Optional.empty());
    }

    /**
     * Setter for generationConfig.
     *
     * <p>generationConfig: Configuration options for model generation and outputs.
     */
    @JsonProperty("generationConfig")
    public abstract Builder generationConfig(GenerationConfig generationConfig);

    /**
     * Setter for generationConfig builder.
     *
     * <p>generationConfig: Configuration options for model generation and outputs.
     */
    @CanIgnoreReturnValue
    public Builder generationConfig(GenerationConfig.Builder generationConfigBuilder) {
      return generationConfig(generationConfigBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder generationConfig(Optional<GenerationConfig> generationConfig);

    /** Clears the value of generationConfig field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearGenerationConfig() {
      return generationConfig(Optional.empty());
    }

    public abstract AutoraterConfig build();
  }

  /** Deserializes a JSON string to a AutoraterConfig object. */
  @ExcludeFromGeneratedCoverageReport
  public static AutoraterConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, AutoraterConfig.class);
  }
}
