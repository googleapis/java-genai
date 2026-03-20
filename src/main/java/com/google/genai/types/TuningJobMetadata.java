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

/** Tuning job metadata. This data type is not supported in Gemini API. */
@AutoValue
@JsonDeserialize(builder = TuningJobMetadata.Builder.class)
public abstract class TuningJobMetadata extends JsonSerializable {
  /** Output only. The number of epochs that have been completed. */
  @JsonProperty("completedEpochCount")
  public abstract Optional<Long> completedEpochCount();

  /** Output only. The number of steps that have been completed. Set for Multi-Step RL. */
  @JsonProperty("completedStepCount")
  public abstract Optional<Long> completedStepCount();

  /** Instantiates a builder for TuningJobMetadata. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_TuningJobMetadata.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for TuningJobMetadata. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `TuningJobMetadata.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_TuningJobMetadata.Builder();
    }

    /**
     * Setter for completedEpochCount.
     *
     * <p>completedEpochCount: Output only. The number of epochs that have been completed.
     */
    @JsonProperty("completedEpochCount")
    public abstract Builder completedEpochCount(Long completedEpochCount);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder completedEpochCount(Optional<Long> completedEpochCount);

    /** Clears the value of completedEpochCount field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearCompletedEpochCount() {
      return completedEpochCount(Optional.empty());
    }

    /**
     * Setter for completedStepCount.
     *
     * <p>completedStepCount: Output only. The number of steps that have been completed. Set for
     * Multi-Step RL.
     */
    @JsonProperty("completedStepCount")
    public abstract Builder completedStepCount(Long completedStepCount);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder completedStepCount(Optional<Long> completedStepCount);

    /** Clears the value of completedStepCount field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearCompletedStepCount() {
      return completedStepCount(Optional.empty());
    }

    public abstract TuningJobMetadata build();
  }

  /** Deserializes a JSON string to a TuningJobMetadata object. */
  @ExcludeFromGeneratedCoverageReport
  public static TuningJobMetadata fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, TuningJobMetadata.class);
  }
}
