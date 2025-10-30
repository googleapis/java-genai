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
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/** Tuning data statistics for Supervised Tuning. This data type is not supported in Gemini API. */
@AutoValue
@JsonDeserialize(builder = SupervisedTuningDataStats.Builder.class)
public abstract class SupervisedTuningDataStats extends JsonSerializable {
  /**
   * Output only. For each index in `truncated_example_indices`, the user-facing reason why the
   * example was dropped.
   */
  @JsonProperty("droppedExampleReasons")
  public abstract Optional<List<String>> droppedExampleReasons();

  /** Output only. Number of billable characters in the tuning dataset. */
  @JsonProperty("totalBillableCharacterCount")
  public abstract Optional<Long> totalBillableCharacterCount();

  /** Output only. Number of billable tokens in the tuning dataset. */
  @JsonProperty("totalBillableTokenCount")
  public abstract Optional<Long> totalBillableTokenCount();

  /**
   * Output only. The number of examples in the dataset that have been dropped. An example can be
   * dropped for reasons including: too many tokens, contains an invalid image, contains too many
   * images, etc.
   */
  @JsonProperty("totalTruncatedExampleCount")
  public abstract Optional<Long> totalTruncatedExampleCount();

  /** Output only. Number of tuning characters in the tuning dataset. */
  @JsonProperty("totalTuningCharacterCount")
  public abstract Optional<Long> totalTuningCharacterCount();

  /** Output only. A partial sample of the indices (starting from 1) of the dropped examples. */
  @JsonProperty("truncatedExampleIndices")
  public abstract Optional<List<Long>> truncatedExampleIndices();

  /** Output only. Number of examples in the tuning dataset. */
  @JsonProperty("tuningDatasetExampleCount")
  public abstract Optional<Long> tuningDatasetExampleCount();

  /** Output only. Number of tuning steps for this Tuning Job. */
  @JsonProperty("tuningStepCount")
  public abstract Optional<Long> tuningStepCount();

  /** Output only. Sample user messages in the training dataset uri. */
  @JsonProperty("userDatasetExamples")
  public abstract Optional<List<Content>> userDatasetExamples();

  /** Output only. Dataset distributions for the user input tokens. */
  @JsonProperty("userInputTokenDistribution")
  public abstract Optional<SupervisedTuningDatasetDistribution> userInputTokenDistribution();

  /** Output only. Dataset distributions for the messages per example. */
  @JsonProperty("userMessagePerExampleDistribution")
  public abstract Optional<SupervisedTuningDatasetDistribution> userMessagePerExampleDistribution();

  /** Output only. Dataset distributions for the user output tokens. */
  @JsonProperty("userOutputTokenDistribution")
  public abstract Optional<SupervisedTuningDatasetDistribution> userOutputTokenDistribution();

  /** Instantiates a builder for SupervisedTuningDataStats. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_SupervisedTuningDataStats.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for SupervisedTuningDataStats. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `SupervisedTuningDataStats.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_SupervisedTuningDataStats.Builder();
    }

    /**
     * Setter for droppedExampleReasons.
     *
     * <p>droppedExampleReasons: Output only. For each index in `truncated_example_indices`, the
     * user-facing reason why the example was dropped.
     */
    @JsonProperty("droppedExampleReasons")
    public abstract Builder droppedExampleReasons(List<String> droppedExampleReasons);

    /**
     * Setter for droppedExampleReasons.
     *
     * <p>droppedExampleReasons: Output only. For each index in `truncated_example_indices`, the
     * user-facing reason why the example was dropped.
     */
    @CanIgnoreReturnValue
    public Builder droppedExampleReasons(String... droppedExampleReasons) {
      return droppedExampleReasons(Arrays.asList(droppedExampleReasons));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder droppedExampleReasons(Optional<List<String>> droppedExampleReasons);

    /** Clears the value of droppedExampleReasons field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearDroppedExampleReasons() {
      return droppedExampleReasons(Optional.empty());
    }

    /**
     * Setter for totalBillableCharacterCount.
     *
     * <p>totalBillableCharacterCount: Output only. Number of billable characters in the tuning
     * dataset.
     */
    @JsonProperty("totalBillableCharacterCount")
    public abstract Builder totalBillableCharacterCount(Long totalBillableCharacterCount);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder totalBillableCharacterCount(Optional<Long> totalBillableCharacterCount);

    /** Clears the value of totalBillableCharacterCount field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTotalBillableCharacterCount() {
      return totalBillableCharacterCount(Optional.empty());
    }

    /**
     * Setter for totalBillableTokenCount.
     *
     * <p>totalBillableTokenCount: Output only. Number of billable tokens in the tuning dataset.
     */
    @JsonProperty("totalBillableTokenCount")
    public abstract Builder totalBillableTokenCount(Long totalBillableTokenCount);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder totalBillableTokenCount(Optional<Long> totalBillableTokenCount);

    /** Clears the value of totalBillableTokenCount field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTotalBillableTokenCount() {
      return totalBillableTokenCount(Optional.empty());
    }

    /**
     * Setter for totalTruncatedExampleCount.
     *
     * <p>totalTruncatedExampleCount: Output only. The number of examples in the dataset that have
     * been dropped. An example can be dropped for reasons including: too many tokens, contains an
     * invalid image, contains too many images, etc.
     */
    @JsonProperty("totalTruncatedExampleCount")
    public abstract Builder totalTruncatedExampleCount(Long totalTruncatedExampleCount);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder totalTruncatedExampleCount(Optional<Long> totalTruncatedExampleCount);

    /** Clears the value of totalTruncatedExampleCount field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTotalTruncatedExampleCount() {
      return totalTruncatedExampleCount(Optional.empty());
    }

    /**
     * Setter for totalTuningCharacterCount.
     *
     * <p>totalTuningCharacterCount: Output only. Number of tuning characters in the tuning dataset.
     */
    @JsonProperty("totalTuningCharacterCount")
    public abstract Builder totalTuningCharacterCount(Long totalTuningCharacterCount);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder totalTuningCharacterCount(Optional<Long> totalTuningCharacterCount);

    /** Clears the value of totalTuningCharacterCount field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTotalTuningCharacterCount() {
      return totalTuningCharacterCount(Optional.empty());
    }

    /**
     * Setter for truncatedExampleIndices.
     *
     * <p>truncatedExampleIndices: Output only. A partial sample of the indices (starting from 1) of
     * the dropped examples.
     */
    @JsonProperty("truncatedExampleIndices")
    public abstract Builder truncatedExampleIndices(List<Long> truncatedExampleIndices);

    /**
     * Setter for truncatedExampleIndices.
     *
     * <p>truncatedExampleIndices: Output only. A partial sample of the indices (starting from 1) of
     * the dropped examples.
     */
    @CanIgnoreReturnValue
    public Builder truncatedExampleIndices(Long... truncatedExampleIndices) {
      return truncatedExampleIndices(Arrays.asList(truncatedExampleIndices));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder truncatedExampleIndices(Optional<List<Long>> truncatedExampleIndices);

    /** Clears the value of truncatedExampleIndices field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTruncatedExampleIndices() {
      return truncatedExampleIndices(Optional.empty());
    }

    /**
     * Setter for tuningDatasetExampleCount.
     *
     * <p>tuningDatasetExampleCount: Output only. Number of examples in the tuning dataset.
     */
    @JsonProperty("tuningDatasetExampleCount")
    public abstract Builder tuningDatasetExampleCount(Long tuningDatasetExampleCount);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder tuningDatasetExampleCount(Optional<Long> tuningDatasetExampleCount);

    /** Clears the value of tuningDatasetExampleCount field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTuningDatasetExampleCount() {
      return tuningDatasetExampleCount(Optional.empty());
    }

    /**
     * Setter for tuningStepCount.
     *
     * <p>tuningStepCount: Output only. Number of tuning steps for this Tuning Job.
     */
    @JsonProperty("tuningStepCount")
    public abstract Builder tuningStepCount(Long tuningStepCount);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder tuningStepCount(Optional<Long> tuningStepCount);

    /** Clears the value of tuningStepCount field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTuningStepCount() {
      return tuningStepCount(Optional.empty());
    }

    /**
     * Setter for userDatasetExamples.
     *
     * <p>userDatasetExamples: Output only. Sample user messages in the training dataset uri.
     */
    @JsonProperty("userDatasetExamples")
    public abstract Builder userDatasetExamples(List<Content> userDatasetExamples);

    /**
     * Setter for userDatasetExamples.
     *
     * <p>userDatasetExamples: Output only. Sample user messages in the training dataset uri.
     */
    @CanIgnoreReturnValue
    public Builder userDatasetExamples(Content... userDatasetExamples) {
      return userDatasetExamples(Arrays.asList(userDatasetExamples));
    }

    /**
     * Setter for userDatasetExamples builder.
     *
     * <p>userDatasetExamples: Output only. Sample user messages in the training dataset uri.
     */
    @CanIgnoreReturnValue
    public Builder userDatasetExamples(Content.Builder... userDatasetExamplesBuilders) {
      return userDatasetExamples(
          Arrays.asList(userDatasetExamplesBuilders).stream()
              .map(Content.Builder::build)
              .collect(toImmutableList()));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder userDatasetExamples(Optional<List<Content>> userDatasetExamples);

    /** Clears the value of userDatasetExamples field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearUserDatasetExamples() {
      return userDatasetExamples(Optional.empty());
    }

    /**
     * Setter for userInputTokenDistribution.
     *
     * <p>userInputTokenDistribution: Output only. Dataset distributions for the user input tokens.
     */
    @JsonProperty("userInputTokenDistribution")
    public abstract Builder userInputTokenDistribution(
        SupervisedTuningDatasetDistribution userInputTokenDistribution);

    /**
     * Setter for userInputTokenDistribution builder.
     *
     * <p>userInputTokenDistribution: Output only. Dataset distributions for the user input tokens.
     */
    @CanIgnoreReturnValue
    public Builder userInputTokenDistribution(
        SupervisedTuningDatasetDistribution.Builder userInputTokenDistributionBuilder) {
      return userInputTokenDistribution(userInputTokenDistributionBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder userInputTokenDistribution(
        Optional<SupervisedTuningDatasetDistribution> userInputTokenDistribution);

    /** Clears the value of userInputTokenDistribution field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearUserInputTokenDistribution() {
      return userInputTokenDistribution(Optional.empty());
    }

    /**
     * Setter for userMessagePerExampleDistribution.
     *
     * <p>userMessagePerExampleDistribution: Output only. Dataset distributions for the messages per
     * example.
     */
    @JsonProperty("userMessagePerExampleDistribution")
    public abstract Builder userMessagePerExampleDistribution(
        SupervisedTuningDatasetDistribution userMessagePerExampleDistribution);

    /**
     * Setter for userMessagePerExampleDistribution builder.
     *
     * <p>userMessagePerExampleDistribution: Output only. Dataset distributions for the messages per
     * example.
     */
    @CanIgnoreReturnValue
    public Builder userMessagePerExampleDistribution(
        SupervisedTuningDatasetDistribution.Builder userMessagePerExampleDistributionBuilder) {
      return userMessagePerExampleDistribution(userMessagePerExampleDistributionBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder userMessagePerExampleDistribution(
        Optional<SupervisedTuningDatasetDistribution> userMessagePerExampleDistribution);

    /** Clears the value of userMessagePerExampleDistribution field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearUserMessagePerExampleDistribution() {
      return userMessagePerExampleDistribution(Optional.empty());
    }

    /**
     * Setter for userOutputTokenDistribution.
     *
     * <p>userOutputTokenDistribution: Output only. Dataset distributions for the user output
     * tokens.
     */
    @JsonProperty("userOutputTokenDistribution")
    public abstract Builder userOutputTokenDistribution(
        SupervisedTuningDatasetDistribution userOutputTokenDistribution);

    /**
     * Setter for userOutputTokenDistribution builder.
     *
     * <p>userOutputTokenDistribution: Output only. Dataset distributions for the user output
     * tokens.
     */
    @CanIgnoreReturnValue
    public Builder userOutputTokenDistribution(
        SupervisedTuningDatasetDistribution.Builder userOutputTokenDistributionBuilder) {
      return userOutputTokenDistribution(userOutputTokenDistributionBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder userOutputTokenDistribution(
        Optional<SupervisedTuningDatasetDistribution> userOutputTokenDistribution);

    /** Clears the value of userOutputTokenDistribution field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearUserOutputTokenDistribution() {
      return userOutputTokenDistribution(Optional.empty());
    }

    public abstract SupervisedTuningDataStats build();
  }

  /** Deserializes a JSON string to a SupervisedTuningDataStats object. */
  @ExcludeFromGeneratedCoverageReport
  public static SupervisedTuningDataStats fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, SupervisedTuningDataStats.class);
  }
}
