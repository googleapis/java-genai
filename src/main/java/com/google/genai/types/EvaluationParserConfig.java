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
 * Config for parsing LLM responses. It can be used to parse the LLM response to be evaluated, or
 * the LLM response from LLM-based metrics/Autoraters. This data type is not supported in Gemini
 * API.
 */
@AutoValue
@JsonDeserialize(builder = EvaluationParserConfig.Builder.class)
public abstract class EvaluationParserConfig extends JsonSerializable {
  /** Optional. Use custom code to parse the LLM response. */
  @JsonProperty("customCodeParserConfig")
  public abstract Optional<EvaluationParserConfigCustomCodeParserConfig> customCodeParserConfig();

  /** Instantiates a builder for EvaluationParserConfig. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_EvaluationParserConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for EvaluationParserConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `EvaluationParserConfig.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_EvaluationParserConfig.Builder();
    }

    /**
     * Setter for customCodeParserConfig.
     *
     * <p>customCodeParserConfig: Optional. Use custom code to parse the LLM response.
     */
    @JsonProperty("customCodeParserConfig")
    public abstract Builder customCodeParserConfig(
        EvaluationParserConfigCustomCodeParserConfig customCodeParserConfig);

    /**
     * Setter for customCodeParserConfig builder.
     *
     * <p>customCodeParserConfig: Optional. Use custom code to parse the LLM response.
     */
    @CanIgnoreReturnValue
    public Builder customCodeParserConfig(
        EvaluationParserConfigCustomCodeParserConfig.Builder customCodeParserConfigBuilder) {
      return customCodeParserConfig(customCodeParserConfigBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder customCodeParserConfig(
        Optional<EvaluationParserConfigCustomCodeParserConfig> customCodeParserConfig);

    /** Clears the value of customCodeParserConfig field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearCustomCodeParserConfig() {
      return customCodeParserConfig(Optional.empty());
    }

    public abstract EvaluationParserConfig build();
  }

  /** Deserializes a JSON string to a EvaluationParserConfig object. */
  @ExcludeFromGeneratedCoverageReport
  public static EvaluationParserConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, EvaluationParserConfig.class);
  }
}
