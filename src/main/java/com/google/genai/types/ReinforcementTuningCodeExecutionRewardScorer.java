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

/** Scores parsed responses for code execution use cases. */
@AutoValue
@JsonDeserialize(builder = ReinforcementTuningCodeExecutionRewardScorer.Builder.class)
public abstract class ReinforcementTuningCodeExecutionRewardScorer extends JsonSerializable {
  /**
   * Example python code snippet which assigns reward of 1 to answer matching user provided
   * reference answer in per prompt references map.
   */
  @JsonProperty("pythonCodeSnippet")
  public abstract Optional<String> pythonCodeSnippet();

  /** Instantiates a builder for ReinforcementTuningCodeExecutionRewardScorer. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ReinforcementTuningCodeExecutionRewardScorer.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ReinforcementTuningCodeExecutionRewardScorer. */
  @AutoValue.Builder
  public abstract static class Builder {
    /**
     * For internal usage. Please use `ReinforcementTuningCodeExecutionRewardScorer.builder()` for
     * instantiation.
     */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ReinforcementTuningCodeExecutionRewardScorer.Builder();
    }

    /**
     * Setter for pythonCodeSnippet.
     *
     * <p>pythonCodeSnippet: Example python code snippet which assigns reward of 1 to answer
     * matching user provided reference answer in per prompt references map.
     */
    @JsonProperty("pythonCodeSnippet")
    public abstract Builder pythonCodeSnippet(String pythonCodeSnippet);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder pythonCodeSnippet(Optional<String> pythonCodeSnippet);

    /** Clears the value of pythonCodeSnippet field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearPythonCodeSnippet() {
      return pythonCodeSnippet(Optional.empty());
    }

    public abstract ReinforcementTuningCodeExecutionRewardScorer build();
  }

  /** Deserializes a JSON string to a ReinforcementTuningCodeExecutionRewardScorer object. */
  @ExcludeFromGeneratedCoverageReport
  public static ReinforcementTuningCodeExecutionRewardScorer fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(
        jsonString, ReinforcementTuningCodeExecutionRewardScorer.class);
  }
}
