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
 * JsonMatchExpression supports converting the parsed responses to JSON format, finding the value in
 * the JSON response that matches the key_name in the first level, and performing
 * StringMatchExpression operation on the matched JSON value. This data type is not supported in
 * Gemini API.
 */
@AutoValue
@JsonDeserialize(
    builder = ReinforcementTuningStringMatchRewardScorerJsonMatchExpression.Builder.class)
public abstract class ReinforcementTuningStringMatchRewardScorerJsonMatchExpression
    extends JsonSerializable {
  /**
   * The key name to find the value in the parsed response that's in JSON format. Only first-level
   * key matching is supported.
   */
  @JsonProperty("keyName")
  public abstract Optional<String> keyName();

  /**
   * String match expression to match against the extracted value from the JSON representation of
   * the parsed response.
   */
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
     * <p>keyName: The key name to find the value in the parsed response that's in JSON format. Only
     * first-level key matching is supported.
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
     * <p>valueStringMatchExpression: String match expression to match against the extracted value
     * from the JSON representation of the parsed response.
     */
    @JsonProperty("valueStringMatchExpression")
    public abstract Builder valueStringMatchExpression(
        ReinforcementTuningStringMatchRewardScorerStringMatchExpression valueStringMatchExpression);

    /**
     * Setter for valueStringMatchExpression builder.
     *
     * <p>valueStringMatchExpression: String match expression to match against the extracted value
     * from the JSON representation of the parsed response.
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
