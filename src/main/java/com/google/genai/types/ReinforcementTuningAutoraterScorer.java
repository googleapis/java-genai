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

/** Reinforcement tuning autorater scorer. */
@AutoValue
@JsonDeserialize(builder = ReinforcementTuningAutoraterScorer.Builder.class)
public abstract class ReinforcementTuningAutoraterScorer extends JsonSerializable {
  /** Autorater config for evaluation. */
  @JsonProperty("autoraterConfig")
  public abstract Optional<AutoraterConfig> autoraterConfig();

  /**
   * The prompt for an autorater to scorer the parsed sample response. This field supports the
   * following placeholders that will be replaced before scoring: - {{prompt}} - {{response}} -
   * {{system_instruction}} - {{references.key}}
   */
  @JsonProperty("autoraterPrompt")
  public abstract Optional<String> autoraterPrompt();

  /**
   * Parses autorater returned response for scoring. For example, if the autorater response has
   * reward stored in the `2.0` block, defining a parsing response config using regex `".*(.*?)"`
   * will return a score `"2.0"`.
   */
  @JsonProperty("autoraterResponseParseConfig")
  public abstract Optional<ReinforcementTuningParseResponseConfig> autoraterResponseParseConfig();

  /** Scores autorater responses by using string match reward scorer. */
  @JsonProperty("exactMatchScorer")
  public abstract Optional<ReinforcementTuningAutoraterScorerExactMatchScorer> exactMatchScorer();

  /**
   * Scores autorater responses by directly converting parsed autorater response to a float reward.
   * Note: Reward is clipped to be within `[-1, 1]`, i.e., `reward = max(min(reward, 1.0), -1.0)`.
   */
  @JsonProperty("parsedResponseConversionScorer")
  public abstract Optional<ReinforcementTuningAutoraterScorerParsedResponseConversionScorer>
      parsedResponseConversionScorer();

  /** Instantiates a builder for ReinforcementTuningAutoraterScorer. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ReinforcementTuningAutoraterScorer.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ReinforcementTuningAutoraterScorer. */
  @AutoValue.Builder
  public abstract static class Builder {
    /**
     * For internal usage. Please use `ReinforcementTuningAutoraterScorer.builder()` for
     * instantiation.
     */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ReinforcementTuningAutoraterScorer.Builder();
    }

    /**
     * Setter for autoraterConfig.
     *
     * <p>autoraterConfig: Autorater config for evaluation.
     */
    @JsonProperty("autoraterConfig")
    public abstract Builder autoraterConfig(AutoraterConfig autoraterConfig);

    /**
     * Setter for autoraterConfig builder.
     *
     * <p>autoraterConfig: Autorater config for evaluation.
     */
    @CanIgnoreReturnValue
    public Builder autoraterConfig(AutoraterConfig.Builder autoraterConfigBuilder) {
      return autoraterConfig(autoraterConfigBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder autoraterConfig(Optional<AutoraterConfig> autoraterConfig);

    /** Clears the value of autoraterConfig field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearAutoraterConfig() {
      return autoraterConfig(Optional.empty());
    }

    /**
     * Setter for autoraterPrompt.
     *
     * <p>autoraterPrompt: The prompt for an autorater to scorer the parsed sample response. This
     * field supports the following placeholders that will be replaced before scoring: - {{prompt}}
     * - {{response}} - {{system_instruction}} - {{references.key}}
     */
    @JsonProperty("autoraterPrompt")
    public abstract Builder autoraterPrompt(String autoraterPrompt);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder autoraterPrompt(Optional<String> autoraterPrompt);

    /** Clears the value of autoraterPrompt field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearAutoraterPrompt() {
      return autoraterPrompt(Optional.empty());
    }

    /**
     * Setter for autoraterResponseParseConfig.
     *
     * <p>autoraterResponseParseConfig: Parses autorater returned response for scoring. For example,
     * if the autorater response has reward stored in the `2.0` block, defining a parsing response
     * config using regex `".*(.*?)"` will return a score `"2.0"`.
     */
    @JsonProperty("autoraterResponseParseConfig")
    public abstract Builder autoraterResponseParseConfig(
        ReinforcementTuningParseResponseConfig autoraterResponseParseConfig);

    /**
     * Setter for autoraterResponseParseConfig builder.
     *
     * <p>autoraterResponseParseConfig: Parses autorater returned response for scoring. For example,
     * if the autorater response has reward stored in the `2.0` block, defining a parsing response
     * config using regex `".*(.*?)"` will return a score `"2.0"`.
     */
    @CanIgnoreReturnValue
    public Builder autoraterResponseParseConfig(
        ReinforcementTuningParseResponseConfig.Builder autoraterResponseParseConfigBuilder) {
      return autoraterResponseParseConfig(autoraterResponseParseConfigBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder autoraterResponseParseConfig(
        Optional<ReinforcementTuningParseResponseConfig> autoraterResponseParseConfig);

    /** Clears the value of autoraterResponseParseConfig field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearAutoraterResponseParseConfig() {
      return autoraterResponseParseConfig(Optional.empty());
    }

    /**
     * Setter for exactMatchScorer.
     *
     * <p>exactMatchScorer: Scores autorater responses by using string match reward scorer.
     */
    @JsonProperty("exactMatchScorer")
    public abstract Builder exactMatchScorer(
        ReinforcementTuningAutoraterScorerExactMatchScorer exactMatchScorer);

    /**
     * Setter for exactMatchScorer builder.
     *
     * <p>exactMatchScorer: Scores autorater responses by using string match reward scorer.
     */
    @CanIgnoreReturnValue
    public Builder exactMatchScorer(
        ReinforcementTuningAutoraterScorerExactMatchScorer.Builder exactMatchScorerBuilder) {
      return exactMatchScorer(exactMatchScorerBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder exactMatchScorer(
        Optional<ReinforcementTuningAutoraterScorerExactMatchScorer> exactMatchScorer);

    /** Clears the value of exactMatchScorer field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearExactMatchScorer() {
      return exactMatchScorer(Optional.empty());
    }

    /**
     * Setter for parsedResponseConversionScorer.
     *
     * <p>parsedResponseConversionScorer: Scores autorater responses by directly converting parsed
     * autorater response to a float reward. Note: Reward is clipped to be within `[-1, 1]`, i.e.,
     * `reward = max(min(reward, 1.0), -1.0)`.
     */
    @JsonProperty("parsedResponseConversionScorer")
    public abstract Builder parsedResponseConversionScorer(
        ReinforcementTuningAutoraterScorerParsedResponseConversionScorer
            parsedResponseConversionScorer);

    /**
     * Setter for parsedResponseConversionScorer builder.
     *
     * <p>parsedResponseConversionScorer: Scores autorater responses by directly converting parsed
     * autorater response to a float reward. Note: Reward is clipped to be within `[-1, 1]`, i.e.,
     * `reward = max(min(reward, 1.0), -1.0)`.
     */
    @CanIgnoreReturnValue
    public Builder parsedResponseConversionScorer(
        ReinforcementTuningAutoraterScorerParsedResponseConversionScorer.Builder
            parsedResponseConversionScorerBuilder) {
      return parsedResponseConversionScorer(parsedResponseConversionScorerBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder parsedResponseConversionScorer(
        Optional<ReinforcementTuningAutoraterScorerParsedResponseConversionScorer>
            parsedResponseConversionScorer);

    /** Clears the value of parsedResponseConversionScorer field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearParsedResponseConversionScorer() {
      return parsedResponseConversionScorer(Optional.empty());
    }

    public abstract ReinforcementTuningAutoraterScorer build();
  }

  /** Deserializes a JSON string to a ReinforcementTuningAutoraterScorer object. */
  @ExcludeFromGeneratedCoverageReport
  public static ReinforcementTuningAutoraterScorer fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ReinforcementTuningAutoraterScorer.class);
  }
}
