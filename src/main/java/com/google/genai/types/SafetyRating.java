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
 * A safety rating for a piece of content. The safety rating contains the harm category and the harm
 * probability level.
 */
@AutoValue
@JsonDeserialize(builder = SafetyRating.Builder.class)
public abstract class SafetyRating extends JsonSerializable {
  /** Output only. Indicates whether the content was blocked because of this rating. */
  @JsonProperty("blocked")
  public abstract Optional<Boolean> blocked();

  /** Output only. The harm category of this rating. */
  @JsonProperty("category")
  public abstract Optional<HarmCategory> category();

  /**
   * Output only. The overwritten threshold for the safety category of Gemini 2.0 image out. If
   * minors are detected in the output image, the threshold of each safety category will be
   * overwritten if user sets a lower threshold. This field is not supported in Gemini API.
   */
  @JsonProperty("overwrittenThreshold")
  public abstract Optional<HarmBlockThreshold> overwrittenThreshold();

  /** Output only. The probability of harm for this category. */
  @JsonProperty("probability")
  public abstract Optional<HarmProbability> probability();

  /**
   * Output only. The probability score of harm for this category. This field is not supported in
   * Gemini API.
   */
  @JsonProperty("probabilityScore")
  public abstract Optional<Float> probabilityScore();

  /**
   * Output only. The severity of harm for this category. This field is not supported in Gemini API.
   */
  @JsonProperty("severity")
  public abstract Optional<HarmSeverity> severity();

  /**
   * Output only. The severity score of harm for this category. This field is not supported in
   * Gemini API.
   */
  @JsonProperty("severityScore")
  public abstract Optional<Float> severityScore();

  /** Instantiates a builder for SafetyRating. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_SafetyRating.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for SafetyRating. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `SafetyRating.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_SafetyRating.Builder();
    }

    /**
     * Setter for blocked.
     *
     * <p>blocked: Output only. Indicates whether the content was blocked because of this rating.
     */
    @JsonProperty("blocked")
    public abstract Builder blocked(boolean blocked);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder blocked(Optional<Boolean> blocked);

    /** Clears the value of blocked field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearBlocked() {
      return blocked(Optional.empty());
    }

    /**
     * Setter for category.
     *
     * <p>category: Output only. The harm category of this rating.
     */
    @JsonProperty("category")
    public abstract Builder category(HarmCategory category);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder category(Optional<HarmCategory> category);

    /** Clears the value of category field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearCategory() {
      return category(Optional.empty());
    }

    /**
     * Setter for category given a known enum.
     *
     * <p>category: Output only. The harm category of this rating.
     */
    @CanIgnoreReturnValue
    public Builder category(HarmCategory.Known knownType) {
      return category(new HarmCategory(knownType));
    }

    /**
     * Setter for category given a string.
     *
     * <p>category: Output only. The harm category of this rating.
     */
    @CanIgnoreReturnValue
    public Builder category(String category) {
      return category(new HarmCategory(category));
    }

    /**
     * Setter for overwrittenThreshold.
     *
     * <p>overwrittenThreshold: Output only. The overwritten threshold for the safety category of
     * Gemini 2.0 image out. If minors are detected in the output image, the threshold of each
     * safety category will be overwritten if user sets a lower threshold. This field is not
     * supported in Gemini API.
     */
    @JsonProperty("overwrittenThreshold")
    public abstract Builder overwrittenThreshold(HarmBlockThreshold overwrittenThreshold);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder overwrittenThreshold(Optional<HarmBlockThreshold> overwrittenThreshold);

    /** Clears the value of overwrittenThreshold field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearOverwrittenThreshold() {
      return overwrittenThreshold(Optional.empty());
    }

    /**
     * Setter for overwrittenThreshold given a known enum.
     *
     * <p>overwrittenThreshold: Output only. The overwritten threshold for the safety category of
     * Gemini 2.0 image out. If minors are detected in the output image, the threshold of each
     * safety category will be overwritten if user sets a lower threshold. This field is not
     * supported in Gemini API.
     */
    @CanIgnoreReturnValue
    public Builder overwrittenThreshold(HarmBlockThreshold.Known knownType) {
      return overwrittenThreshold(new HarmBlockThreshold(knownType));
    }

    /**
     * Setter for overwrittenThreshold given a string.
     *
     * <p>overwrittenThreshold: Output only. The overwritten threshold for the safety category of
     * Gemini 2.0 image out. If minors are detected in the output image, the threshold of each
     * safety category will be overwritten if user sets a lower threshold. This field is not
     * supported in Gemini API.
     */
    @CanIgnoreReturnValue
    public Builder overwrittenThreshold(String overwrittenThreshold) {
      return overwrittenThreshold(new HarmBlockThreshold(overwrittenThreshold));
    }

    /**
     * Setter for probability.
     *
     * <p>probability: Output only. The probability of harm for this category.
     */
    @JsonProperty("probability")
    public abstract Builder probability(HarmProbability probability);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder probability(Optional<HarmProbability> probability);

    /** Clears the value of probability field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearProbability() {
      return probability(Optional.empty());
    }

    /**
     * Setter for probability given a known enum.
     *
     * <p>probability: Output only. The probability of harm for this category.
     */
    @CanIgnoreReturnValue
    public Builder probability(HarmProbability.Known knownType) {
      return probability(new HarmProbability(knownType));
    }

    /**
     * Setter for probability given a string.
     *
     * <p>probability: Output only. The probability of harm for this category.
     */
    @CanIgnoreReturnValue
    public Builder probability(String probability) {
      return probability(new HarmProbability(probability));
    }

    /**
     * Setter for probabilityScore.
     *
     * <p>probabilityScore: Output only. The probability score of harm for this category. This field
     * is not supported in Gemini API.
     */
    @JsonProperty("probabilityScore")
    public abstract Builder probabilityScore(Float probabilityScore);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder probabilityScore(Optional<Float> probabilityScore);

    /** Clears the value of probabilityScore field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearProbabilityScore() {
      return probabilityScore(Optional.empty());
    }

    /**
     * Setter for severity.
     *
     * <p>severity: Output only. The severity of harm for this category. This field is not supported
     * in Gemini API.
     */
    @JsonProperty("severity")
    public abstract Builder severity(HarmSeverity severity);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder severity(Optional<HarmSeverity> severity);

    /** Clears the value of severity field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearSeverity() {
      return severity(Optional.empty());
    }

    /**
     * Setter for severity given a known enum.
     *
     * <p>severity: Output only. The severity of harm for this category. This field is not supported
     * in Gemini API.
     */
    @CanIgnoreReturnValue
    public Builder severity(HarmSeverity.Known knownType) {
      return severity(new HarmSeverity(knownType));
    }

    /**
     * Setter for severity given a string.
     *
     * <p>severity: Output only. The severity of harm for this category. This field is not supported
     * in Gemini API.
     */
    @CanIgnoreReturnValue
    public Builder severity(String severity) {
      return severity(new HarmSeverity(severity));
    }

    /**
     * Setter for severityScore.
     *
     * <p>severityScore: Output only. The severity score of harm for this category. This field is
     * not supported in Gemini API.
     */
    @JsonProperty("severityScore")
    public abstract Builder severityScore(Float severityScore);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder severityScore(Optional<Float> severityScore);

    /** Clears the value of severityScore field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearSeverityScore() {
      return severityScore(Optional.empty());
    }

    public abstract SafetyRating build();
  }

  /** Deserializes a JSON string to a SafetyRating object. */
  @ExcludeFromGeneratedCoverageReport
  public static SafetyRating fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, SafetyRating.class);
  }
}
