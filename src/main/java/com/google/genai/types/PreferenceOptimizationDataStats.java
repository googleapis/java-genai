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

import static com.google.common.collect.ImmutableList.toImmutableList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Statistics computed for datasets used for preference optimization. This data type is not
 * supported in Gemini API.
 */
@AutoValue
@JsonDeserialize(builder = PreferenceOptimizationDataStats.Builder.class)
public abstract class PreferenceOptimizationDataStats extends JsonSerializable {
  /** Output only. Dataset distributions for scores variance per example. */
  @JsonProperty("scoreVariancePerExampleDistribution")
  public abstract Optional<DatasetDistribution> scoreVariancePerExampleDistribution();

  /** Output only. Dataset distributions for scores. */
  @JsonProperty("scoresDistribution")
  public abstract Optional<DatasetDistribution> scoresDistribution();

  /** Output only. Number of billable tokens in the tuning dataset. */
  @JsonProperty("totalBillableTokenCount")
  public abstract Optional<Long> totalBillableTokenCount();

  /** Output only. Number of examples in the tuning dataset. */
  @JsonProperty("tuningDatasetExampleCount")
  public abstract Optional<Long> tuningDatasetExampleCount();

  /** Output only. Number of tuning steps for this Tuning Job. */
  @JsonProperty("tuningStepCount")
  public abstract Optional<Long> tuningStepCount();

  /** Output only. Sample user examples in the training dataset. */
  @JsonProperty("userDatasetExamples")
  public abstract Optional<List<GeminiPreferenceExample>> userDatasetExamples();

  /** Output only. Dataset distributions for the user input tokens. */
  @JsonProperty("userInputTokenDistribution")
  public abstract Optional<DatasetDistribution> userInputTokenDistribution();

  /** Output only. Dataset distributions for the user output tokens. */
  @JsonProperty("userOutputTokenDistribution")
  public abstract Optional<DatasetDistribution> userOutputTokenDistribution();

  /** Instantiates a builder for PreferenceOptimizationDataStats. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_PreferenceOptimizationDataStats.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for PreferenceOptimizationDataStats. */
  @AutoValue.Builder
  public abstract static class Builder {
    /**
     * For internal usage. Please use `PreferenceOptimizationDataStats.builder()` for instantiation.
     */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_PreferenceOptimizationDataStats.Builder();
    }

    /**
     * Setter for scoreVariancePerExampleDistribution.
     *
     * <p>scoreVariancePerExampleDistribution: Output only. Dataset distributions for scores
     * variance per example.
     */
    @JsonProperty("scoreVariancePerExampleDistribution")
    public abstract Builder scoreVariancePerExampleDistribution(
        DatasetDistribution scoreVariancePerExampleDistribution);

    /**
     * Setter for scoreVariancePerExampleDistribution builder.
     *
     * <p>scoreVariancePerExampleDistribution: Output only. Dataset distributions for scores
     * variance per example.
     */
    public Builder scoreVariancePerExampleDistribution(
        DatasetDistribution.Builder scoreVariancePerExampleDistributionBuilder) {
      return scoreVariancePerExampleDistribution(
          scoreVariancePerExampleDistributionBuilder.build());
    }

    /**
     * Setter for scoresDistribution.
     *
     * <p>scoresDistribution: Output only. Dataset distributions for scores.
     */
    @JsonProperty("scoresDistribution")
    public abstract Builder scoresDistribution(DatasetDistribution scoresDistribution);

    /**
     * Setter for scoresDistribution builder.
     *
     * <p>scoresDistribution: Output only. Dataset distributions for scores.
     */
    public Builder scoresDistribution(DatasetDistribution.Builder scoresDistributionBuilder) {
      return scoresDistribution(scoresDistributionBuilder.build());
    }

    /**
     * Setter for totalBillableTokenCount.
     *
     * <p>totalBillableTokenCount: Output only. Number of billable tokens in the tuning dataset.
     */
    @JsonProperty("totalBillableTokenCount")
    public abstract Builder totalBillableTokenCount(Long totalBillableTokenCount);

    /**
     * Setter for tuningDatasetExampleCount.
     *
     * <p>tuningDatasetExampleCount: Output only. Number of examples in the tuning dataset.
     */
    @JsonProperty("tuningDatasetExampleCount")
    public abstract Builder tuningDatasetExampleCount(Long tuningDatasetExampleCount);

    /**
     * Setter for tuningStepCount.
     *
     * <p>tuningStepCount: Output only. Number of tuning steps for this Tuning Job.
     */
    @JsonProperty("tuningStepCount")
    public abstract Builder tuningStepCount(Long tuningStepCount);

    /**
     * Setter for userDatasetExamples.
     *
     * <p>userDatasetExamples: Output only. Sample user examples in the training dataset.
     */
    @JsonProperty("userDatasetExamples")
    public abstract Builder userDatasetExamples(List<GeminiPreferenceExample> userDatasetExamples);

    /**
     * Setter for userDatasetExamples.
     *
     * <p>userDatasetExamples: Output only. Sample user examples in the training dataset.
     */
    public Builder userDatasetExamples(GeminiPreferenceExample... userDatasetExamples) {
      return userDatasetExamples(Arrays.asList(userDatasetExamples));
    }

    /**
     * Setter for userDatasetExamples builder.
     *
     * <p>userDatasetExamples: Output only. Sample user examples in the training dataset.
     */
    public Builder userDatasetExamples(
        GeminiPreferenceExample.Builder... userDatasetExamplesBuilders) {
      return userDatasetExamples(
          Arrays.asList(userDatasetExamplesBuilders).stream()
              .map(GeminiPreferenceExample.Builder::build)
              .collect(toImmutableList()));
    }

    /**
     * Setter for userInputTokenDistribution.
     *
     * <p>userInputTokenDistribution: Output only. Dataset distributions for the user input tokens.
     */
    @JsonProperty("userInputTokenDistribution")
    public abstract Builder userInputTokenDistribution(
        DatasetDistribution userInputTokenDistribution);

    /**
     * Setter for userInputTokenDistribution builder.
     *
     * <p>userInputTokenDistribution: Output only. Dataset distributions for the user input tokens.
     */
    public Builder userInputTokenDistribution(
        DatasetDistribution.Builder userInputTokenDistributionBuilder) {
      return userInputTokenDistribution(userInputTokenDistributionBuilder.build());
    }

    /**
     * Setter for userOutputTokenDistribution.
     *
     * <p>userOutputTokenDistribution: Output only. Dataset distributions for the user output
     * tokens.
     */
    @JsonProperty("userOutputTokenDistribution")
    public abstract Builder userOutputTokenDistribution(
        DatasetDistribution userOutputTokenDistribution);

    /**
     * Setter for userOutputTokenDistribution builder.
     *
     * <p>userOutputTokenDistribution: Output only. Dataset distributions for the user output
     * tokens.
     */
    public Builder userOutputTokenDistribution(
        DatasetDistribution.Builder userOutputTokenDistributionBuilder) {
      return userOutputTokenDistribution(userOutputTokenDistributionBuilder.build());
    }

    public abstract PreferenceOptimizationDataStats build();
  }

  /** Deserializes a JSON string to a PreferenceOptimizationDataStats object. */
  @ExcludeFromGeneratedCoverageReport
  public static PreferenceOptimizationDataStats fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, PreferenceOptimizationDataStats.class);
  }
}
