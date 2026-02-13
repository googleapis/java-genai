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
 * The tuning data statistic values for TuningJob. This data type is not supported in Gemini API.
 */
@AutoValue
@JsonDeserialize(builder = TuningDataStats.Builder.class)
public abstract class TuningDataStats extends JsonSerializable {
  /**
   * Output only. Statistics for distillation prompt dataset. These statistics do not include the
   * responses sampled from the teacher model.
   */
  @JsonProperty("distillationDataStats")
  public abstract Optional<DistillationDataStats> distillationDataStats();

  /** Output only. Statistics for preference optimization. */
  @JsonProperty("preferenceOptimizationDataStats")
  public abstract Optional<PreferenceOptimizationDataStats> preferenceOptimizationDataStats();

  /** The SFT Tuning data stats. */
  @JsonProperty("supervisedTuningDataStats")
  public abstract Optional<SupervisedTuningDataStats> supervisedTuningDataStats();

  /** Instantiates a builder for TuningDataStats. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_TuningDataStats.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for TuningDataStats. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `TuningDataStats.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_TuningDataStats.Builder();
    }

    /**
     * Setter for distillationDataStats.
     *
     * <p>distillationDataStats: Output only. Statistics for distillation prompt dataset. These
     * statistics do not include the responses sampled from the teacher model.
     */
    @JsonProperty("distillationDataStats")
    public abstract Builder distillationDataStats(DistillationDataStats distillationDataStats);

    /**
     * Setter for distillationDataStats builder.
     *
     * <p>distillationDataStats: Output only. Statistics for distillation prompt dataset. These
     * statistics do not include the responses sampled from the teacher model.
     */
    @CanIgnoreReturnValue
    public Builder distillationDataStats(
        DistillationDataStats.Builder distillationDataStatsBuilder) {
      return distillationDataStats(distillationDataStatsBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder distillationDataStats(Optional<DistillationDataStats> distillationDataStats);

    /** Clears the value of distillationDataStats field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearDistillationDataStats() {
      return distillationDataStats(Optional.empty());
    }

    /**
     * Setter for preferenceOptimizationDataStats.
     *
     * <p>preferenceOptimizationDataStats: Output only. Statistics for preference optimization.
     */
    @JsonProperty("preferenceOptimizationDataStats")
    public abstract Builder preferenceOptimizationDataStats(
        PreferenceOptimizationDataStats preferenceOptimizationDataStats);

    /**
     * Setter for preferenceOptimizationDataStats builder.
     *
     * <p>preferenceOptimizationDataStats: Output only. Statistics for preference optimization.
     */
    @CanIgnoreReturnValue
    public Builder preferenceOptimizationDataStats(
        PreferenceOptimizationDataStats.Builder preferenceOptimizationDataStatsBuilder) {
      return preferenceOptimizationDataStats(preferenceOptimizationDataStatsBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder preferenceOptimizationDataStats(
        Optional<PreferenceOptimizationDataStats> preferenceOptimizationDataStats);

    /** Clears the value of preferenceOptimizationDataStats field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearPreferenceOptimizationDataStats() {
      return preferenceOptimizationDataStats(Optional.empty());
    }

    /**
     * Setter for supervisedTuningDataStats.
     *
     * <p>supervisedTuningDataStats: The SFT Tuning data stats.
     */
    @JsonProperty("supervisedTuningDataStats")
    public abstract Builder supervisedTuningDataStats(
        SupervisedTuningDataStats supervisedTuningDataStats);

    /**
     * Setter for supervisedTuningDataStats builder.
     *
     * <p>supervisedTuningDataStats: The SFT Tuning data stats.
     */
    @CanIgnoreReturnValue
    public Builder supervisedTuningDataStats(
        SupervisedTuningDataStats.Builder supervisedTuningDataStatsBuilder) {
      return supervisedTuningDataStats(supervisedTuningDataStatsBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder supervisedTuningDataStats(
        Optional<SupervisedTuningDataStats> supervisedTuningDataStats);

    /** Clears the value of supervisedTuningDataStats field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearSupervisedTuningDataStats() {
      return supervisedTuningDataStats(Optional.empty());
    }

    public abstract TuningDataStats build();
  }

  /** Deserializes a JSON string to a TuningDataStats object. */
  @ExcludeFromGeneratedCoverageReport
  public static TuningDataStats fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, TuningDataStats.class);
  }
}
