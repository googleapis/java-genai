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
 * Configuration for parsing the LLM response using custom code. This data type is not supported in
 * Gemini API.
 */
@AutoValue
@JsonDeserialize(builder = EvaluationParserConfigCustomCodeParserConfig.Builder.class)
public abstract class EvaluationParserConfigCustomCodeParserConfig extends JsonSerializable {
  /**
   * Required. Python function for parsing results. The function should be defined within this
   * string. The function takes a list of strings (LLM responses) and should return either a list of
   * dictionaries (for rubrics) or a single dictionary (for a metric result). Example function
   * signature: def parse(responses: list[str]) -> list[dict[str, Any]] | dict[str, Any]: When
   * parsing rubrics, return a list of dictionaries, where each dictionary represents a Rubric.
   * Example for rubrics: [ { "content": {"property": {"description": "The response is factual."}},
   * "type": "FACTUALITY", "importance": "HIGH" }, { "content": {"property": {"description": "The
   * response is fluent."}}, "type": "FLUENCY", "importance": "MEDIUM" } ] When parsing critique
   * results, return a dictionary representing a MetricResult. Example for a metric result: {
   * "score": 0.8, "explanation": "The model followed most instructions.", "rubric_verdicts": [...]
   * } ... code for result extraction and aggregation
   */
  @JsonProperty("parsingFunction")
  public abstract Optional<String> parsingFunction();

  /** Instantiates a builder for EvaluationParserConfigCustomCodeParserConfig. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_EvaluationParserConfigCustomCodeParserConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for EvaluationParserConfigCustomCodeParserConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /**
     * For internal usage. Please use `EvaluationParserConfigCustomCodeParserConfig.builder()` for
     * instantiation.
     */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_EvaluationParserConfigCustomCodeParserConfig.Builder();
    }

    /**
     * Setter for parsingFunction.
     *
     * <p>parsingFunction: Required. Python function for parsing results. The function should be
     * defined within this string. The function takes a list of strings (LLM responses) and should
     * return either a list of dictionaries (for rubrics) or a single dictionary (for a metric
     * result). Example function signature: def parse(responses: list[str]) -> list[dict[str, Any]]
     * | dict[str, Any]: When parsing rubrics, return a list of dictionaries, where each dictionary
     * represents a Rubric. Example for rubrics: [ { "content": {"property": {"description": "The
     * response is factual."}}, "type": "FACTUALITY", "importance": "HIGH" }, { "content":
     * {"property": {"description": "The response is fluent."}}, "type": "FLUENCY", "importance":
     * "MEDIUM" } ] When parsing critique results, return a dictionary representing a MetricResult.
     * Example for a metric result: { "score": 0.8, "explanation": "The model followed most
     * instructions.", "rubric_verdicts": [...] } ... code for result extraction and aggregation
     */
    @JsonProperty("parsingFunction")
    public abstract Builder parsingFunction(String parsingFunction);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder parsingFunction(Optional<String> parsingFunction);

    /** Clears the value of parsingFunction field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearParsingFunction() {
      return parsingFunction(Optional.empty());
    }

    public abstract EvaluationParserConfigCustomCodeParserConfig build();
  }

  /** Deserializes a JSON string to a EvaluationParserConfigCustomCodeParserConfig object. */
  @ExcludeFromGeneratedCoverageReport
  public static EvaluationParserConfigCustomCodeParserConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(
        jsonString, EvaluationParserConfigCustomCodeParserConfig.class);
  }
}
