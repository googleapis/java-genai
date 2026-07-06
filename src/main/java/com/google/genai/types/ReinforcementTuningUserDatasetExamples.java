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

import static com.google.common.collect.ImmutableList.toImmutableList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Sample reinforcement tuning user data in the training dataset. The contents are truncated for
 * better UI showing. This data type is not supported in Gemini API.
 */
@AutoValue
@JsonDeserialize(builder = ReinforcementTuningUserDatasetExamples.Builder.class)
public abstract class ReinforcementTuningUserDatasetExamples extends JsonSerializable {
  /** List of user datasset examples showing to user. */
  @JsonProperty("userDatasetExamples")
  public abstract Optional<List<ReinforcementTuningExample>> userDatasetExamples();

  /** Instantiates a builder for ReinforcementTuningUserDatasetExamples. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ReinforcementTuningUserDatasetExamples.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ReinforcementTuningUserDatasetExamples. */
  @AutoValue.Builder
  public abstract static class Builder {
    /**
     * For internal usage. Please use `ReinforcementTuningUserDatasetExamples.builder()` for
     * instantiation.
     */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ReinforcementTuningUserDatasetExamples.Builder();
    }

    /**
     * Setter for userDatasetExamples.
     *
     * <p>userDatasetExamples: List of user datasset examples showing to user.
     */
    @JsonProperty("userDatasetExamples")
    public abstract Builder userDatasetExamples(
        List<ReinforcementTuningExample> userDatasetExamples);

    /**
     * Setter for userDatasetExamples.
     *
     * <p>userDatasetExamples: List of user datasset examples showing to user.
     */
    @CanIgnoreReturnValue
    public Builder userDatasetExamples(ReinforcementTuningExample... userDatasetExamples) {
      return userDatasetExamples(Arrays.asList(userDatasetExamples));
    }

    /**
     * Setter for userDatasetExamples builder.
     *
     * <p>userDatasetExamples: List of user datasset examples showing to user.
     */
    @CanIgnoreReturnValue
    public Builder userDatasetExamples(
        ReinforcementTuningExample.Builder... userDatasetExamplesBuilders) {
      return userDatasetExamples(
          Arrays.asList(userDatasetExamplesBuilders).stream()
              .map(ReinforcementTuningExample.Builder::build)
              .collect(toImmutableList()));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder userDatasetExamples(
        Optional<List<ReinforcementTuningExample>> userDatasetExamples);

    /** Clears the value of userDatasetExamples field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearUserDatasetExamples() {
      return userDatasetExamples(Optional.empty());
    }

    public abstract ReinforcementTuningUserDatasetExamples build();
  }

  /** Deserializes a JSON string to a ReinforcementTuningUserDatasetExamples object. */
  @ExcludeFromGeneratedCoverageReport
  public static ReinforcementTuningUserDatasetExamples fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(
        jsonString, ReinforcementTuningUserDatasetExamples.class);
  }
}
