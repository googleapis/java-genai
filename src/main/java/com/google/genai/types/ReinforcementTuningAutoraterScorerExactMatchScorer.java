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
 * Scores autorater responses by using exact string match reward scorer. This data type is not
 * supported in Gemini API.
 */
@AutoValue
@JsonDeserialize(builder = ReinforcementTuningAutoraterScorerExactMatchScorer.Builder.class)
public abstract class ReinforcementTuningAutoraterScorerExactMatchScorer extends JsonSerializable {
  /** Assigns this reward score if the parsed response string equals the expression. */
  @JsonProperty("correctAnswerReward")
  public abstract Optional<Float> correctAnswerReward();

  /**
   * The string expression to match against for scoring. This field supports placeholders in the
   * format of {{references.key}} that will be replaced before matching. Regex is not supported for
   * this expression. For example, users can define an ExactMatchScorer as follows: {
   * "correctAnswerReward": 1.0, "wrongAnswerReward": -1.0, "expression":
   * "{{references.concise_answer}}" } When evaluating the reward for each parsed autorater
   * response, if the prompt references in the training/validation dataset has the following fields:
   * ``` { "example": ..., "references": { "concise_ansser": "Yes", "verbose_answer": "The answer is
   * Yes" } } ``` The above ExactMatchScorer will be replaced as follows for scoring: ``` {
   * "correctAnswerReward": 1.0, "wrongAnswerReward": -1.0, "expression": "Yes" } ``` If the
   * *parsed* autorater response is equal to the string `"Yes"`, then the reward is `1.0`, otherwise
   * the reward is `-1.0`.
   */
  @JsonProperty("expression")
  public abstract Optional<String> expression();

  /** Assigns this reward score if the parsed reward value does not equal the expression. */
  @JsonProperty("wrongAnswerReward")
  public abstract Optional<Float> wrongAnswerReward();

  /** Instantiates a builder for ReinforcementTuningAutoraterScorerExactMatchScorer. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ReinforcementTuningAutoraterScorerExactMatchScorer.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ReinforcementTuningAutoraterScorerExactMatchScorer. */
  @AutoValue.Builder
  public abstract static class Builder {
    /**
     * For internal usage. Please use `ReinforcementTuningAutoraterScorerExactMatchScorer.builder()`
     * for instantiation.
     */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ReinforcementTuningAutoraterScorerExactMatchScorer.Builder();
    }

    /**
     * Setter for correctAnswerReward.
     *
     * <p>correctAnswerReward: Assigns this reward score if the parsed response string equals the
     * expression.
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
     * Setter for expression.
     *
     * <p>expression: The string expression to match against for scoring. This field supports
     * placeholders in the format of {{references.key}} that will be replaced before matching. Regex
     * is not supported for this expression. For example, users can define an ExactMatchScorer as
     * follows: { "correctAnswerReward": 1.0, "wrongAnswerReward": -1.0, "expression":
     * "{{references.concise_answer}}" } When evaluating the reward for each parsed autorater
     * response, if the prompt references in the training/validation dataset has the following
     * fields: ``` { "example": ..., "references": { "concise_ansser": "Yes", "verbose_answer": "The
     * answer is Yes" } } ``` The above ExactMatchScorer will be replaced as follows for scoring:
     * ``` { "correctAnswerReward": 1.0, "wrongAnswerReward": -1.0, "expression": "Yes" } ``` If the
     * *parsed* autorater response is equal to the string `"Yes"`, then the reward is `1.0`,
     * otherwise the reward is `-1.0`.
     */
    @JsonProperty("expression")
    public abstract Builder expression(String expression);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder expression(Optional<String> expression);

    /** Clears the value of expression field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearExpression() {
      return expression(Optional.empty());
    }

    /**
     * Setter for wrongAnswerReward.
     *
     * <p>wrongAnswerReward: Assigns this reward score if the parsed reward value does not equal the
     * expression.
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

    public abstract ReinforcementTuningAutoraterScorerExactMatchScorer build();
  }

  /** Deserializes a JSON string to a ReinforcementTuningAutoraterScorerExactMatchScorer object. */
  @ExcludeFromGeneratedCoverageReport
  public static ReinforcementTuningAutoraterScorerExactMatchScorer fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(
        jsonString, ReinforcementTuningAutoraterScorerExactMatchScorer.class);
  }
}
