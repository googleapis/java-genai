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
import java.util.Map;
import java.util.Optional;

/**
 * User-facing format for Gemini Reinforcement Tuning examples on Vertex. This data type is not
 * supported in Gemini API.
 */
@AutoValue
@JsonDeserialize(builder = ReinforcementTuningExample.Builder.class)
public abstract class ReinforcementTuningExample extends JsonSerializable {
  /** Multi-turn contents that represents the Prompt. */
  @JsonProperty("contents")
  public abstract Optional<List<Content>> contents();

  /**
   * References for the given prompt. The key is the name of the reference, and the value is the
   * reference itself. Users can use this field together with the reward configurations to calculate
   * rewards for reinforcement tuning. For example, users can set the following references: ``` {
   * "concise_answer": "Yes", "verbose_answer": "The answer is Yes" } ``` Then in a
   * ReinforcementTuningCodeExecutionRewardScorer reward function config, for example, they can
   * define a python code snippet as follows: ``` def evaluate(example, response) -&gt; float:
   * response_str = response.get("parts", [])0 references = example.get("references", {}) if
   * response_str == references.get("concise_answer"): return 1.0 return -1.0 ``` In this case,
   * references can serve the purpose of holding the ground truth of this example in the
   * training/validation dataset.
   */
  @JsonProperty("references")
  public abstract Optional<Map<String, String>> references();

  /** Corresponds to system_instruction in user-facing GenerateContentRequest. */
  @JsonProperty("systemInstruction")
  public abstract Optional<Content> systemInstruction();

  /** Instantiates a builder for ReinforcementTuningExample. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ReinforcementTuningExample.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ReinforcementTuningExample. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `ReinforcementTuningExample.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ReinforcementTuningExample.Builder();
    }

    /**
     * Setter for contents.
     *
     * <p>contents: Multi-turn contents that represents the Prompt.
     */
    @JsonProperty("contents")
    public abstract Builder contents(List<Content> contents);

    /**
     * Setter for contents.
     *
     * <p>contents: Multi-turn contents that represents the Prompt.
     */
    @CanIgnoreReturnValue
    public Builder contents(Content... contents) {
      return contents(Arrays.asList(contents));
    }

    /**
     * Setter for contents builder.
     *
     * <p>contents: Multi-turn contents that represents the Prompt.
     */
    @CanIgnoreReturnValue
    public Builder contents(Content.Builder... contentsBuilders) {
      return contents(
          Arrays.asList(contentsBuilders).stream()
              .map(Content.Builder::build)
              .collect(toImmutableList()));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder contents(Optional<List<Content>> contents);

    /** Clears the value of contents field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearContents() {
      return contents(Optional.empty());
    }

    /**
     * Setter for references.
     *
     * <p>references: References for the given prompt. The key is the name of the reference, and the
     * value is the reference itself. Users can use this field together with the reward
     * configurations to calculate rewards for reinforcement tuning. For example, users can set the
     * following references: ``` { "concise_answer": "Yes", "verbose_answer": "The answer is Yes" }
     * ``` Then in a ReinforcementTuningCodeExecutionRewardScorer reward function config, for
     * example, they can define a python code snippet as follows: ``` def evaluate(example,
     * response) -&gt; float: response_str = response.get("parts", [])0 references =
     * example.get("references", {}) if response_str == references.get("concise_answer"): return 1.0
     * return -1.0 ``` In this case, references can serve the purpose of holding the ground truth of
     * this example in the training/validation dataset.
     */
    @JsonProperty("references")
    public abstract Builder references(Map<String, String> references);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder references(Optional<Map<String, String>> references);

    /** Clears the value of references field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearReferences() {
      return references(Optional.empty());
    }

    /**
     * Setter for systemInstruction.
     *
     * <p>systemInstruction: Corresponds to system_instruction in user-facing
     * GenerateContentRequest.
     */
    @JsonProperty("systemInstruction")
    public abstract Builder systemInstruction(Content systemInstruction);

    /**
     * Setter for systemInstruction builder.
     *
     * <p>systemInstruction: Corresponds to system_instruction in user-facing
     * GenerateContentRequest.
     */
    @CanIgnoreReturnValue
    public Builder systemInstruction(Content.Builder systemInstructionBuilder) {
      return systemInstruction(systemInstructionBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder systemInstruction(Optional<Content> systemInstruction);

    /** Clears the value of systemInstruction field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearSystemInstruction() {
      return systemInstruction(Optional.empty());
    }

    public abstract ReinforcementTuningExample build();
  }

  /** Deserializes a JSON string to a ReinforcementTuningExample object. */
  @ExcludeFromGeneratedCoverageReport
  public static ReinforcementTuningExample fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ReinforcementTuningExample.class);
  }
}
