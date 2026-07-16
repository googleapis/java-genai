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

/** Single reinforcement tuning reward config. */
@AutoValue
@JsonDeserialize(builder = SingleReinforcementTuningRewardConfig.Builder.class)
public abstract class SingleReinforcementTuningRewardConfig extends JsonSerializable {
  /** Scores parsed responses for autorater use cases by using a model to compute the reward. */
  @JsonProperty("autoraterScorer")
  public abstract Optional<ReinforcementTuningAutoraterScorer> autoraterScorer();

  /**
   * ReinforcementTuningCloudRunRewardScorer is used to score parsed responses by calling a Cloud
   * Run service.
   */
  @JsonProperty("cloudRunRewardScorer")
  public abstract Optional<ReinforcementTuningCloudRunRewardScorer> cloudRunRewardScorer();

  /**
   * ReinforcementTuningCodeExecutionRewardScorer is used to score parsed responses for code
   * execution use cases.
   */
  @JsonProperty("codeExecutionRewardScorer")
  public abstract Optional<ReinforcementTuningCodeExecutionRewardScorer>
      codeExecutionRewardScorer();

  /**
   * Defines how to parse sample response. For example, given a sample response for evaluating the
   * reward, users might want to extract the text only between `` and `` in the sample response, and
   * keeps only the last one in case there are multiple such tags. To achieve such a purpose, they
   * can define a regex `".*(.*?)"` using the
   * ReinforcementTuningParseResponseConfig.ResponseParseType.REGEX_EXTRACT parse type.
   */
  @JsonProperty("parseResponseConfig")
  public abstract Optional<ReinforcementTuningParseResponseConfig> parseResponseConfig();

  /** A unique reward name for identifying each single reinforcement tuning reward. */
  @JsonProperty("rewardName")
  public abstract Optional<String> rewardName();

  /**
   * ReinforcementTuningStringMatchRewardScorer is used to score parsed responses for simple string
   * matching use cases against reference answers.
   */
  @JsonProperty("stringMatchRewardScorer")
  public abstract Optional<ReinforcementTuningStringMatchRewardScorer> stringMatchRewardScorer();

  /** Instantiates a builder for SingleReinforcementTuningRewardConfig. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_SingleReinforcementTuningRewardConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for SingleReinforcementTuningRewardConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /**
     * For internal usage. Please use `SingleReinforcementTuningRewardConfig.builder()` for
     * instantiation.
     */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_SingleReinforcementTuningRewardConfig.Builder();
    }

    /**
     * Setter for autoraterScorer.
     *
     * <p>autoraterScorer: Scores parsed responses for autorater use cases by using a model to
     * compute the reward.
     */
    @JsonProperty("autoraterScorer")
    public abstract Builder autoraterScorer(ReinforcementTuningAutoraterScorer autoraterScorer);

    /**
     * Setter for autoraterScorer builder.
     *
     * <p>autoraterScorer: Scores parsed responses for autorater use cases by using a model to
     * compute the reward.
     */
    @CanIgnoreReturnValue
    public Builder autoraterScorer(
        ReinforcementTuningAutoraterScorer.Builder autoraterScorerBuilder) {
      return autoraterScorer(autoraterScorerBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder autoraterScorer(Optional<ReinforcementTuningAutoraterScorer> autoraterScorer);

    /** Clears the value of autoraterScorer field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearAutoraterScorer() {
      return autoraterScorer(Optional.empty());
    }

    /**
     * Setter for cloudRunRewardScorer.
     *
     * <p>cloudRunRewardScorer: ReinforcementTuningCloudRunRewardScorer is used to score parsed
     * responses by calling a Cloud Run service.
     */
    @JsonProperty("cloudRunRewardScorer")
    public abstract Builder cloudRunRewardScorer(
        ReinforcementTuningCloudRunRewardScorer cloudRunRewardScorer);

    /**
     * Setter for cloudRunRewardScorer builder.
     *
     * <p>cloudRunRewardScorer: ReinforcementTuningCloudRunRewardScorer is used to score parsed
     * responses by calling a Cloud Run service.
     */
    @CanIgnoreReturnValue
    public Builder cloudRunRewardScorer(
        ReinforcementTuningCloudRunRewardScorer.Builder cloudRunRewardScorerBuilder) {
      return cloudRunRewardScorer(cloudRunRewardScorerBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder cloudRunRewardScorer(
        Optional<ReinforcementTuningCloudRunRewardScorer> cloudRunRewardScorer);

    /** Clears the value of cloudRunRewardScorer field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearCloudRunRewardScorer() {
      return cloudRunRewardScorer(Optional.empty());
    }

    /**
     * Setter for codeExecutionRewardScorer.
     *
     * <p>codeExecutionRewardScorer: ReinforcementTuningCodeExecutionRewardScorer is used to score
     * parsed responses for code execution use cases.
     */
    @JsonProperty("codeExecutionRewardScorer")
    public abstract Builder codeExecutionRewardScorer(
        ReinforcementTuningCodeExecutionRewardScorer codeExecutionRewardScorer);

    /**
     * Setter for codeExecutionRewardScorer builder.
     *
     * <p>codeExecutionRewardScorer: ReinforcementTuningCodeExecutionRewardScorer is used to score
     * parsed responses for code execution use cases.
     */
    @CanIgnoreReturnValue
    public Builder codeExecutionRewardScorer(
        ReinforcementTuningCodeExecutionRewardScorer.Builder codeExecutionRewardScorerBuilder) {
      return codeExecutionRewardScorer(codeExecutionRewardScorerBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder codeExecutionRewardScorer(
        Optional<ReinforcementTuningCodeExecutionRewardScorer> codeExecutionRewardScorer);

    /** Clears the value of codeExecutionRewardScorer field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearCodeExecutionRewardScorer() {
      return codeExecutionRewardScorer(Optional.empty());
    }

    /**
     * Setter for parseResponseConfig.
     *
     * <p>parseResponseConfig: Defines how to parse sample response. For example, given a sample
     * response for evaluating the reward, users might want to extract the text only between `` and
     * `` in the sample response, and keeps only the last one in case there are multiple such tags.
     * To achieve such a purpose, they can define a regex `".*(.*?)"` using the
     * ReinforcementTuningParseResponseConfig.ResponseParseType.REGEX_EXTRACT parse type.
     */
    @JsonProperty("parseResponseConfig")
    public abstract Builder parseResponseConfig(
        ReinforcementTuningParseResponseConfig parseResponseConfig);

    /**
     * Setter for parseResponseConfig builder.
     *
     * <p>parseResponseConfig: Defines how to parse sample response. For example, given a sample
     * response for evaluating the reward, users might want to extract the text only between `` and
     * `` in the sample response, and keeps only the last one in case there are multiple such tags.
     * To achieve such a purpose, they can define a regex `".*(.*?)"` using the
     * ReinforcementTuningParseResponseConfig.ResponseParseType.REGEX_EXTRACT parse type.
     */
    @CanIgnoreReturnValue
    public Builder parseResponseConfig(
        ReinforcementTuningParseResponseConfig.Builder parseResponseConfigBuilder) {
      return parseResponseConfig(parseResponseConfigBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder parseResponseConfig(
        Optional<ReinforcementTuningParseResponseConfig> parseResponseConfig);

    /** Clears the value of parseResponseConfig field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearParseResponseConfig() {
      return parseResponseConfig(Optional.empty());
    }

    /**
     * Setter for rewardName.
     *
     * <p>rewardName: A unique reward name for identifying each single reinforcement tuning reward.
     */
    @JsonProperty("rewardName")
    public abstract Builder rewardName(String rewardName);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder rewardName(Optional<String> rewardName);

    /** Clears the value of rewardName field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearRewardName() {
      return rewardName(Optional.empty());
    }

    /**
     * Setter for stringMatchRewardScorer.
     *
     * <p>stringMatchRewardScorer: ReinforcementTuningStringMatchRewardScorer is used to score
     * parsed responses for simple string matching use cases against reference answers.
     */
    @JsonProperty("stringMatchRewardScorer")
    public abstract Builder stringMatchRewardScorer(
        ReinforcementTuningStringMatchRewardScorer stringMatchRewardScorer);

    /**
     * Setter for stringMatchRewardScorer builder.
     *
     * <p>stringMatchRewardScorer: ReinforcementTuningStringMatchRewardScorer is used to score
     * parsed responses for simple string matching use cases against reference answers.
     */
    @CanIgnoreReturnValue
    public Builder stringMatchRewardScorer(
        ReinforcementTuningStringMatchRewardScorer.Builder stringMatchRewardScorerBuilder) {
      return stringMatchRewardScorer(stringMatchRewardScorerBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder stringMatchRewardScorer(
        Optional<ReinforcementTuningStringMatchRewardScorer> stringMatchRewardScorer);

    /** Clears the value of stringMatchRewardScorer field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearStringMatchRewardScorer() {
      return stringMatchRewardScorer(Optional.empty());
    }

    public abstract SingleReinforcementTuningRewardConfig build();
  }

  /** Deserializes a JSON string to a SingleReinforcementTuningRewardConfig object. */
  @ExcludeFromGeneratedCoverageReport
  public static SingleReinforcementTuningRewardConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, SingleReinforcementTuningRewardConfig.class);
  }
}
