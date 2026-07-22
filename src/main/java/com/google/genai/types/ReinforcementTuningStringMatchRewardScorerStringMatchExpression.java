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
 * Evaluates parsed response using match type against the expression. Returns `true` if
 * `MatchOperation(target, expression)` evaluates to `true`, and `false` otherwise. This data type
 * is not supported in Gemini API.
 */
@AutoValue
@JsonDeserialize(
    builder = ReinforcementTuningStringMatchRewardScorerStringMatchExpression.Builder.class)
public abstract class ReinforcementTuningStringMatchRewardScorerStringMatchExpression
    extends JsonSerializable {
  /**
   * A string or a regular expression to match against for evaluating rewards. Users can also
   * provide a references map of `{key: value}` whose `value` will be used to replace the
   * placeholder {{references.key}} in the expression. For example, if the following `references`
   * are defined in the training / validation dataset: ``` { "systemInstruction": ..., "contents":
   * ..., "references": { "concise_answer": "Yes", "verbose_answer": "The answer is Yes" } } ``` and
   * if users define the following StringMatchExpression: { "matchOperation": "REGEX_CONTAINS",
   * "expression": ".*{{references.concise_answer}}.*" } On evaluating the reward for each sample
   * response, this StringMatchExpression will be substituted as: ``` { "matchOperation":
   * "REGEX_CONTAINS", "expression": ".*Yes.*" } ```
   */
  @JsonProperty("expression")
  public abstract Optional<String> expression();

  /** Match operation to use for evaluating rewards. */
  @JsonProperty("matchOperation")
  public abstract Optional<MatchOperation> matchOperation();

  /** Instantiates a builder for ReinforcementTuningStringMatchRewardScorerStringMatchExpression. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ReinforcementTuningStringMatchRewardScorerStringMatchExpression.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ReinforcementTuningStringMatchRewardScorerStringMatchExpression. */
  @AutoValue.Builder
  public abstract static class Builder {
    /**
     * For internal usage. Please use
     * `ReinforcementTuningStringMatchRewardScorerStringMatchExpression.builder()` for
     * instantiation.
     */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ReinforcementTuningStringMatchRewardScorerStringMatchExpression
          .Builder();
    }

    /**
     * Setter for expression.
     *
     * <p>expression: A string or a regular expression to match against for evaluating rewards.
     * Users can also provide a references map of `{key: value}` whose `value` will be used to
     * replace the placeholder {{references.key}} in the expression. For example, if the following
     * `references` are defined in the training / validation dataset: ``` { "systemInstruction":
     * ..., "contents": ..., "references": { "concise_answer": "Yes", "verbose_answer": "The answer
     * is Yes" } } ``` and if users define the following StringMatchExpression: { "matchOperation":
     * "REGEX_CONTAINS", "expression": ".*{{references.concise_answer}}.*" } On evaluating the
     * reward for each sample response, this StringMatchExpression will be substituted as: ``` {
     * "matchOperation": "REGEX_CONTAINS", "expression": ".*Yes.*" } ```
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
     * Setter for matchOperation.
     *
     * <p>matchOperation: Match operation to use for evaluating rewards.
     */
    @JsonProperty("matchOperation")
    public abstract Builder matchOperation(MatchOperation matchOperation);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder matchOperation(Optional<MatchOperation> matchOperation);

    /** Clears the value of matchOperation field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearMatchOperation() {
      return matchOperation(Optional.empty());
    }

    /**
     * Setter for matchOperation given a known enum.
     *
     * <p>matchOperation: Match operation to use for evaluating rewards.
     */
    @CanIgnoreReturnValue
    public Builder matchOperation(MatchOperation.Known knownType) {
      return matchOperation(new MatchOperation(knownType));
    }

    /**
     * Setter for matchOperation given a string.
     *
     * <p>matchOperation: Match operation to use for evaluating rewards.
     */
    @CanIgnoreReturnValue
    public Builder matchOperation(String matchOperation) {
      return matchOperation(new MatchOperation(matchOperation));
    }

    public abstract ReinforcementTuningStringMatchRewardScorerStringMatchExpression build();
  }

  /**
   * Deserializes a JSON string to a ReinforcementTuningStringMatchRewardScorerStringMatchExpression
   * object.
   */
  @ExcludeFromGeneratedCoverageReport
  public static ReinforcementTuningStringMatchRewardScorerStringMatchExpression fromJson(
      String jsonString) {
    return JsonSerializable.fromJsonString(
        jsonString, ReinforcementTuningStringMatchRewardScorerStringMatchExpression.class);
  }
}
