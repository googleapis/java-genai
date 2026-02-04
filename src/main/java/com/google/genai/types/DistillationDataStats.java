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
 * Statistics computed for datasets used for distillation. This data type is not supported in Gemini
 * API.
 */
@AutoValue
@JsonDeserialize(builder = DistillationDataStats.Builder.class)
public abstract class DistillationDataStats extends JsonSerializable {
  /** Output only. Statistics computed for the training dataset. */
  @JsonProperty("trainingDatasetStats")
  public abstract Optional<DatasetStats> trainingDatasetStats();

  /** Instantiates a builder for DistillationDataStats. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_DistillationDataStats.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for DistillationDataStats. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `DistillationDataStats.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_DistillationDataStats.Builder();
    }

    /**
     * Setter for trainingDatasetStats.
     *
     * <p>trainingDatasetStats: Output only. Statistics computed for the training dataset.
     */
    @JsonProperty("trainingDatasetStats")
    public abstract Builder trainingDatasetStats(DatasetStats trainingDatasetStats);

    /**
     * Setter for trainingDatasetStats builder.
     *
     * <p>trainingDatasetStats: Output only. Statistics computed for the training dataset.
     */
    @CanIgnoreReturnValue
    public Builder trainingDatasetStats(DatasetStats.Builder trainingDatasetStatsBuilder) {
      return trainingDatasetStats(trainingDatasetStatsBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder trainingDatasetStats(Optional<DatasetStats> trainingDatasetStats);

    /** Clears the value of trainingDatasetStats field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTrainingDatasetStats() {
      return trainingDatasetStats(Optional.empty());
    }

    public abstract DistillationDataStats build();
  }

  /** Deserializes a JSON string to a DistillationDataStats object. */
  @ExcludeFromGeneratedCoverageReport
  public static DistillationDataStats fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, DistillationDataStats.class);
  }
}
