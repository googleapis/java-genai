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

/** Scores parsed responses for string matching use cases. */
@AutoValue
@JsonDeserialize(builder = ReinforcementTuningStringMatchRewardScorer.Builder.class)
public abstract class ReinforcementTuningStringMatchRewardScorer extends JsonSerializable {
  /**
   * Wrong answer reward is returned if evaluator evaluates to `false`. All wrong answers get the
   * same reward.
   */
  @JsonProperty("wrongAnswerReward")
  public abstract Optional<Float> wrongAnswerReward();

  /**
   * Correct answer reward is returned if evaluator evaluates to `true`. All correct answers get the
   * same reward.
   */
  @JsonProperty("correctAnswerReward")
  public abstract Optional<Float> correctAnswerReward();

  /** Uses string match expression to evaluate parsed response. */
  @JsonProperty("stringMatchExpression")
  public abstract Optional<ReinforcementTuningStringMatchRewardScorerStringMatchExpression>
      stringMatchExpression();

  /** Uses json match expression to evaluate parsed response. */
  @JsonProperty("jsonMatchExpression")
  public abstract Optional<ReinforcementTuningStringMatchRewardScorerJsonMatchExpression>
      jsonMatchExpression();

  /** Instantiates a builder for ReinforcementTuningStringMatchRewardScorer. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ReinforcementTuningStringMatchRewardScorer.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ReinforcementTuningStringMatchRewardScorer. */
  @AutoValue.Builder
  public abstract static class Builder {
    /**
     * For internal usage. Please use `ReinforcementTuningStringMatchRewardScorer.builder()` for
     * instantiation.
     */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ReinforcementTuningStringMatchRewardScorer.Builder();
    }

    /**
     * Setter for wrongAnswerReward.
     *
     * <p>wrongAnswerReward: Wrong answer reward is returned if evaluator evaluates to `false`. All
     * wrong answers get the same reward.
     */
    @JsonProperty("wrongAnswerReward")
    public abstract Builder wrongAnswerReward(Float wrongAnswerReward);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder wrongAnswerReward(Optional<Float> wrongAnswerReward);

    /** Clears the value of wrongAnswerReward field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearWrongAnswerReward() {
      return wrongAnswerReward(Optional.empty());
    }

    /**
     * Setter for correctAnswerReward.
     *
     * <p>correctAnswerReward: Correct answer reward is returned if evaluator evaluates to `true`.
     * All correct answers get the same reward.
     */
    @JsonProperty("correctAnswerReward")
    public abstract Builder correctAnswerReward(Float correctAnswerReward);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder correctAnswerReward(Optional<Float> correctAnswerReward);

    /** Clears the value of correctAnswerReward field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearCorrectAnswerReward() {
      return correctAnswerReward(Optional.empty());
    }

    /**
     * Setter for stringMatchExpression.
     *
     * <p>stringMatchExpression: Uses string match expression to evaluate parsed response.
     */
    @JsonProperty("stringMatchExpression")
    public abstract Builder stringMatchExpression(
        ReinforcementTuningStringMatchRewardScorerStringMatchExpression stringMatchExpression);

    /**
     * Setter for stringMatchExpression builder.
     *
     * <p>stringMatchExpression: Uses string match expression to evaluate parsed response.
     */
    @CanIgnoreReturnValue
    public Builder stringMatchExpression(
        ReinforcementTuningStringMatchRewardScorerStringMatchExpression.Builder
            stringMatchExpressionBuilder) {
      return stringMatchExpression(stringMatchExpressionBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder stringMatchExpression(
        Optional<ReinforcementTuningStringMatchRewardScorerStringMatchExpression>
            stringMatchExpression);

    /** Clears the value of stringMatchExpression field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearStringMatchExpression() {
      return stringMatchExpression(Optional.empty());
    }

    /**
     * Setter for jsonMatchExpression.
     *
     * <p>jsonMatchExpression: Uses json match expression to evaluate parsed response.
     */
    @JsonProperty("jsonMatchExpression")
    public abstract Builder jsonMatchExpression(
        ReinforcementTuningStringMatchRewardScorerJsonMatchExpression jsonMatchExpression);

    /**
     * Setter for jsonMatchExpression builder.
     *
     * <p>jsonMatchExpression: Uses json match expression to evaluate parsed response.
     */
    @CanIgnoreReturnValue
    public Builder jsonMatchExpression(
        ReinforcementTuningStringMatchRewardScorerJsonMatchExpression.Builder
            jsonMatchExpressionBuilder) {
      return jsonMatchExpression(jsonMatchExpressionBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder jsonMatchExpression(
        Optional<ReinforcementTuningStringMatchRewardScorerJsonMatchExpression>
            jsonMatchExpression);

    /** Clears the value of jsonMatchExpression field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearJsonMatchExpression() {
      return jsonMatchExpression(Optional.empty());
    }

    public abstract ReinforcementTuningStringMatchRewardScorer build();
  }

  /** Deserializes a JSON string to a ReinforcementTuningStringMatchRewardScorer object. */
  @ExcludeFromGeneratedCoverageReport
  public static ReinforcementTuningStringMatchRewardScorer fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(
        jsonString, ReinforcementTuningStringMatchRewardScorer.class);
  }
}
