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
 * Defines how to parse sample response config for reinforcement tuning. The parsed response (i.e.,
 * substring) will be passed to the reward functions. For example, the input prompt might be: &gt;
 * "Perform step-by-step thoughts first to problem A, finally output answer in the &lt;ans&gt;
 * &lt;/ans&gt; block." The sample response from the model under tuning might look like: &gt;
 * "&lt;ans&gt;Yes&lt;/ans&gt;" Here, users can define the following parse config: ``` {
 * "parseType": "REGEX_EXTRACT", "regexExtractExpression": ".*(.*?)" } ``` The resulting parsed
 * response would be `"Yes"` and will be passed to the reward functions for evaluating rewards. This
 * data type is not supported in Gemini API.
 */
@AutoValue
@JsonDeserialize(builder = ReinforcementTuningParseResponseConfig.Builder.class)
public abstract class ReinforcementTuningParseResponseConfig extends JsonSerializable {
  /** Defines the type for parsing sample response. */
  @JsonProperty("parseType")
  public abstract Optional<ResponseParseType> parseType();

  /**
   * Defines the regex for extracting the important part of sample response. This field is only used
   * when parse_type is ResponseParseType.REGEX_EXTRACT.
   */
  @JsonProperty("regexExtractExpression")
  public abstract Optional<String> regexExtractExpression();

  /** Instantiates a builder for ReinforcementTuningParseResponseConfig. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ReinforcementTuningParseResponseConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ReinforcementTuningParseResponseConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /**
     * For internal usage. Please use `ReinforcementTuningParseResponseConfig.builder()` for
     * instantiation.
     */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ReinforcementTuningParseResponseConfig.Builder();
    }

    /**
     * Setter for parseType.
     *
     * <p>parseType: Defines the type for parsing sample response.
     */
    @JsonProperty("parseType")
    public abstract Builder parseType(ResponseParseType parseType);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder parseType(Optional<ResponseParseType> parseType);

    /** Clears the value of parseType field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearParseType() {
      return parseType(Optional.empty());
    }

    /**
     * Setter for parseType given a known enum.
     *
     * <p>parseType: Defines the type for parsing sample response.
     */
    @CanIgnoreReturnValue
    public Builder parseType(ResponseParseType.Known knownType) {
      return parseType(new ResponseParseType(knownType));
    }

    /**
     * Setter for parseType given a string.
     *
     * <p>parseType: Defines the type for parsing sample response.
     */
    @CanIgnoreReturnValue
    public Builder parseType(String parseType) {
      return parseType(new ResponseParseType(parseType));
    }

    /**
     * Setter for regexExtractExpression.
     *
     * <p>regexExtractExpression: Defines the regex for extracting the important part of sample
     * response. This field is only used when parse_type is ResponseParseType.REGEX_EXTRACT.
     */
    @JsonProperty("regexExtractExpression")
    public abstract Builder regexExtractExpression(String regexExtractExpression);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder regexExtractExpression(Optional<String> regexExtractExpression);

    /** Clears the value of regexExtractExpression field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearRegexExtractExpression() {
      return regexExtractExpression(Optional.empty());
    }

    public abstract ReinforcementTuningParseResponseConfig build();
  }

  /** Deserializes a JSON string to a ReinforcementTuningParseResponseConfig object. */
  @ExcludeFromGeneratedCoverageReport
  public static ReinforcementTuningParseResponseConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(
        jsonString, ReinforcementTuningParseResponseConfig.class);
  }
}
