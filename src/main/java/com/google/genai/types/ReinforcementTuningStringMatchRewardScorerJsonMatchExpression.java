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
 * Converts parsed responses to JSON format, finds the first-level matching key, then performs
 * StringMatchExpression on the value.
 */
@AutoValue
@JsonDeserialize(
    builder = ReinforcementTuningStringMatchRewardScorerJsonMatchExpression.Builder.class)
public abstract class ReinforcementTuningStringMatchRewardScorerJsonMatchExpression
    extends JsonSerializable {
  /** Json key name to find the value to match against. */
  @JsonProperty("keyName")
  public abstract Optional<String> keyName();

  /** String match expression to match against the value of json key. */
  @JsonProperty("valueStringMatchExpression")
  public abstract Optional<ReinforcementTuningStringMatchRewardScorerStringMatchExpression>
      valueStringMatchExpression();

  /** Instantiates a builder for ReinforcementTuningStringMatchRewardScorerJsonMatchExpression. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ReinforcementTuningStringMatchRewardScorerJsonMatchExpression.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ReinforcementTuningStringMatchRewardScorerJsonMatchExpression. */
  @AutoValue.Builder
  public abstract static class Builder {
    /**
     * For internal usage. Please use
     * `ReinforcementTuningStringMatchRewardScorerJsonMatchExpression.builder()` for instantiation.
     */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ReinforcementTuningStringMatchRewardScorerJsonMatchExpression.Builder();
    }

    /**
     * Setter for keyName.
     *
     * <p>keyName: Json key name to find the value to match against.
     */
    @JsonProperty("keyName")
    public abstract Builder keyName(String keyName);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder keyName(Optional<String> keyName);

    /** Clears the value of keyName field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearKeyName() {
      return keyName(Optional.empty());
    }

    /**
     * Setter for valueStringMatchExpression.
     *
     * <p>valueStringMatchExpression: String match expression to match against the value of json
     * key.
     */
    @JsonProperty("valueStringMatchExpression")
    public abstract Builder valueStringMatchExpression(
        ReinforcementTuningStringMatchRewardScorerStringMatchExpression valueStringMatchExpression);

    /**
     * Setter for valueStringMatchExpression builder.
     *
     * <p>valueStringMatchExpression: String match expression to match against the value of json
     * key.
     */
    @CanIgnoreReturnValue
    public Builder valueStringMatchExpression(
        ReinforcementTuningStringMatchRewardScorerStringMatchExpression.Builder
            valueStringMatchExpressionBuilder) {
      return valueStringMatchExpression(valueStringMatchExpressionBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder valueStringMatchExpression(
        Optional<ReinforcementTuningStringMatchRewardScorerStringMatchExpression>
            valueStringMatchExpression);

    /** Clears the value of valueStringMatchExpression field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearValueStringMatchExpression() {
      return valueStringMatchExpression(Optional.empty());
    }

    public abstract ReinforcementTuningStringMatchRewardScorerJsonMatchExpression build();
  }

  /**
   * Deserializes a JSON string to a ReinforcementTuningStringMatchRewardScorerJsonMatchExpression
   * object.
   */
  @ExcludeFromGeneratedCoverageReport
  public static ReinforcementTuningStringMatchRewardScorerJsonMatchExpression fromJson(
      String jsonString) {
    return JsonSerializable.fromJsonString(
        jsonString, ReinforcementTuningStringMatchRewardScorerJsonMatchExpression.class);
  }
}
