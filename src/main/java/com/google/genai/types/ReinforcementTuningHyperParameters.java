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
import com.google.api.core.InternalApi;
import com.google.auto.value.AutoValue;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** Hyperparameters for Reinforcement Tuning. */
@AutoValue
@InternalApi
@JsonDeserialize(builder = ReinforcementTuningHyperParameters.Builder.class)
public abstract class ReinforcementTuningHyperParameters extends JsonSerializable {
  /** Number of training epochs for the tuning job. */
  @JsonProperty("epochCount")
  public abstract Optional<Long> epochCount();

  /** Learning rate multiplier for Reinforcement Learning. */
  @JsonProperty("learningRateMultiplier")
  public abstract Optional<Float> learningRateMultiplier();

  /** Adapter size for Reinforcement Tuning. */
  @JsonProperty("adapterSize")
  public abstract Optional<AdapterSize> adapterSize();

  /** Number of different responses to generate per prompt during tuning. */
  @JsonProperty("samplesPerPrompt")
  public abstract Optional<Integer> samplesPerPrompt();

  /**
   * Batch size for the tuning job. How many prompts to process at a train step. If not set, the
   * batch size will be determined automatically.
   */
  @JsonProperty("batchSize")
  public abstract Optional<Integer> batchSize();

  /**
   * How often (in steps) to evaluate the tuning job during training. If not set, evaluation will
   * run per epoch.
   */
  @JsonProperty("evaluateInterval")
  public abstract Optional<Integer> evaluateInterval();

  /**
   * How often (in steps) to save checkpoints during training. If not set, one checkpoint per epoch
   * will be saved.
   */
  @JsonProperty("checkpointInterval")
  public abstract Optional<Integer> checkpointInterval();

  /** The maximum number of tokens to generate per prompt. If not set, defaults to 32768. */
  @JsonProperty("maxOutputTokens")
  public abstract Optional<Integer> maxOutputTokens();

  /** Indicates the maximum thinking depth. Use with earlier models shall result in error. */
  @JsonProperty("thinkingLevel")
  public abstract Optional<ReinforcementTuningThinkingLevel> thinkingLevel();

  /** Instantiates a builder for ReinforcementTuningHyperParameters. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ReinforcementTuningHyperParameters.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ReinforcementTuningHyperParameters. */
  @AutoValue.Builder
  public abstract static class Builder {
    /**
     * For internal usage. Please use `ReinforcementTuningHyperParameters.builder()` for
     * instantiation.
     */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ReinforcementTuningHyperParameters.Builder();
    }

    /**
     * Setter for epochCount.
     *
     * <p>epochCount: Number of training epochs for the tuning job.
     */
    @JsonProperty("epochCount")
    public abstract Builder epochCount(Long epochCount);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder epochCount(Optional<Long> epochCount);

    /** Clears the value of epochCount field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearEpochCount() {
      return epochCount(Optional.empty());
    }

    /**
     * Setter for learningRateMultiplier.
     *
     * <p>learningRateMultiplier: Learning rate multiplier for Reinforcement Learning.
     */
    @JsonProperty("learningRateMultiplier")
    public abstract Builder learningRateMultiplier(Float learningRateMultiplier);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder learningRateMultiplier(Optional<Float> learningRateMultiplier);

    /** Clears the value of learningRateMultiplier field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearLearningRateMultiplier() {
      return learningRateMultiplier(Optional.empty());
    }

    /**
     * Setter for adapterSize.
     *
     * <p>adapterSize: Adapter size for Reinforcement Tuning.
     */
    @JsonProperty("adapterSize")
    public abstract Builder adapterSize(AdapterSize adapterSize);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder adapterSize(Optional<AdapterSize> adapterSize);

    /** Clears the value of adapterSize field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearAdapterSize() {
      return adapterSize(Optional.empty());
    }

    /**
     * Setter for adapterSize given a known enum.
     *
     * <p>adapterSize: Adapter size for Reinforcement Tuning.
     */
    @CanIgnoreReturnValue
    public Builder adapterSize(AdapterSize.Known knownType) {
      return adapterSize(new AdapterSize(knownType));
    }

    /**
     * Setter for adapterSize given a string.
     *
     * <p>adapterSize: Adapter size for Reinforcement Tuning.
     */
    @CanIgnoreReturnValue
    public Builder adapterSize(String adapterSize) {
      return adapterSize(new AdapterSize(adapterSize));
    }

    /**
     * Setter for samplesPerPrompt.
     *
     * <p>samplesPerPrompt: Number of different responses to generate per prompt during tuning.
     */
    @JsonProperty("samplesPerPrompt")
    public abstract Builder samplesPerPrompt(Integer samplesPerPrompt);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder samplesPerPrompt(Optional<Integer> samplesPerPrompt);

    /** Clears the value of samplesPerPrompt field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearSamplesPerPrompt() {
      return samplesPerPrompt(Optional.empty());
    }

    /**
     * Setter for batchSize.
     *
     * <p>batchSize: Batch size for the tuning job. How many prompts to process at a train step. If
     * not set, the batch size will be determined automatically.
     */
    @JsonProperty("batchSize")
    public abstract Builder batchSize(Integer batchSize);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder batchSize(Optional<Integer> batchSize);

    /** Clears the value of batchSize field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearBatchSize() {
      return batchSize(Optional.empty());
    }

    /**
     * Setter for evaluateInterval.
     *
     * <p>evaluateInterval: How often (in steps) to evaluate the tuning job during training. If not
     * set, evaluation will run per epoch.
     */
    @JsonProperty("evaluateInterval")
    public abstract Builder evaluateInterval(Integer evaluateInterval);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder evaluateInterval(Optional<Integer> evaluateInterval);

    /** Clears the value of evaluateInterval field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearEvaluateInterval() {
      return evaluateInterval(Optional.empty());
    }

    /**
     * Setter for checkpointInterval.
     *
     * <p>checkpointInterval: How often (in steps) to save checkpoints during training. If not set,
     * one checkpoint per epoch will be saved.
     */
    @JsonProperty("checkpointInterval")
    public abstract Builder checkpointInterval(Integer checkpointInterval);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder checkpointInterval(Optional<Integer> checkpointInterval);

    /** Clears the value of checkpointInterval field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearCheckpointInterval() {
      return checkpointInterval(Optional.empty());
    }

    /**
     * Setter for maxOutputTokens.
     *
     * <p>maxOutputTokens: The maximum number of tokens to generate per prompt. If not set, defaults
     * to 32768.
     */
    @JsonProperty("maxOutputTokens")
    public abstract Builder maxOutputTokens(Integer maxOutputTokens);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder maxOutputTokens(Optional<Integer> maxOutputTokens);

    /** Clears the value of maxOutputTokens field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearMaxOutputTokens() {
      return maxOutputTokens(Optional.empty());
    }

    /**
     * Setter for thinkingLevel.
     *
     * <p>thinkingLevel: Indicates the maximum thinking depth. Use with earlier models shall result
     * in error.
     */
    @JsonProperty("thinkingLevel")
    public abstract Builder thinkingLevel(ReinforcementTuningThinkingLevel thinkingLevel);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder thinkingLevel(Optional<ReinforcementTuningThinkingLevel> thinkingLevel);

    /** Clears the value of thinkingLevel field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearThinkingLevel() {
      return thinkingLevel(Optional.empty());
    }

    /**
     * Setter for thinkingLevel given a known enum.
     *
     * <p>thinkingLevel: Indicates the maximum thinking depth. Use with earlier models shall result
     * in error.
     */
    @CanIgnoreReturnValue
    public Builder thinkingLevel(ReinforcementTuningThinkingLevel.Known knownType) {
      return thinkingLevel(new ReinforcementTuningThinkingLevel(knownType));
    }

    /**
     * Setter for thinkingLevel given a string.
     *
     * <p>thinkingLevel: Indicates the maximum thinking depth. Use with earlier models shall result
     * in error.
     */
    @CanIgnoreReturnValue
    public Builder thinkingLevel(String thinkingLevel) {
      return thinkingLevel(new ReinforcementTuningThinkingLevel(thinkingLevel));
    }

    public abstract ReinforcementTuningHyperParameters build();
  }

  /** Deserializes a JSON string to a ReinforcementTuningHyperParameters object. */
  @ExcludeFromGeneratedCoverageReport
  public static ReinforcementTuningHyperParameters fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ReinforcementTuningHyperParameters.class);
  }
}
