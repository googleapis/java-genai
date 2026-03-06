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

package com.google.genai.types.interactions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.base.Ascii;
import com.google.genai.types.ExcludeFromGeneratedCoverageReport;
import java.util.Objects;

/**
 * Controls whether thinking summaries are included in the response.
 *
 * <p>When the model uses thinking tokens (controlled by {@link ThinkingLevel}), it can optionally
 * provide summaries of its reasoning
 * process. These summaries give insight into how the model arrived at its response, which can be
 * valuable for understanding the model's decision-making process, debugging unexpected outputs, or
 * building trust in the model's reasoning.
 *
 * <p>This class follows the standard enum wrapper pattern used throughout the codebase. The {@code
 * Known} enum contains all recognized values from the OpenAPI specification, and {@code
 * THINKING_SUMMARIES_UNSPECIFIED} serves as a fallback for forward compatibility.
 *
 * <p>Example usage:
 *
 * <pre>{@code
 * // Automatically include thinking summaries when appropriate
 * ThinkingSummaries summaries = new ThinkingSummaries(ThinkingSummaries.Known.AUTO);
 *
 * // Exclude thinking summaries from the response
 * ThinkingSummaries summaries = new ThinkingSummaries(ThinkingSummaries.Known.NONE);
 *
 * // Include in generation config with thinking level
 * GenerationConfig config = GenerationConfig.builder()
 *     .thinkingLevel(new ThinkingLevel(ThinkingLevel.Known.HIGH))
 *     .thinkingSummaries(new ThinkingSummaries(ThinkingSummaries.Known.AUTO))
 *     .build();
 * }</pre>
 */
public class ThinkingSummaries {

  /**
   * Enum representing the known values for ThinkingSummaries.
   *
   * <p>These values control whether the model's reasoning process is summarized in the response.
   */
  public enum Known {
    /**
     * Unspecified thinking summaries mode.
     *
     * <p>This is the fallback value used when the API returns an unknown thinking summaries mode
     * not recognized by this version of the SDK. This ensures forward compatibility when new modes
     * are added to the API.
     */
    THINKING_SUMMARIES_UNSPECIFIED("unspecified"),

    /**
     * Automatically include thinking summaries when appropriate.
     *
     * <p>In AUTO mode, the model determines whether to include summaries of its thinking process
     * based on the context and the complexity of the reasoning performed. Summaries are more likely
     * to be included for complex tasks where the reasoning process provides valuable insight.
     */
    AUTO("auto"),

    /**
     * Do not include thinking summaries in the response.
     *
     * <p>In NONE mode, the model will not provide summaries of its thinking process, even if it
     * uses thinking tokens internally. Use this when you only need the final response and want to
     * minimize response size.
     */
    NONE("none");

    private final String value;

    Known(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return this.value;
    }
  }

  private Known thinkingSummariesEnum;
  private final String value;

  /**
   * Creates a ThinkingSummaries from a string value.
   *
   * <p>This constructor is used by Jackson during JSON deserialization. It attempts to match the
   * provided string value to a known enum value (case-insensitive). If no match is found, it falls
   * back to {@code THINKING_SUMMARIES_UNSPECIFIED}.
   *
   * @param value the string representation of the thinking summaries mode
   */
  @JsonCreator
  public ThinkingSummaries(String value) {
    this.value = value;
    for (Known thinkingSummariesEnum : Known.values()) {
      if (Ascii.equalsIgnoreCase(thinkingSummariesEnum.toString(), value)) {
        this.thinkingSummariesEnum = thinkingSummariesEnum;
        break;
      }
    }
    if (this.thinkingSummariesEnum == null) {
      this.thinkingSummariesEnum = Known.THINKING_SUMMARIES_UNSPECIFIED;
    }
  }

  /**
   * Creates a ThinkingSummaries from a known enum value.
   *
   * <p>This is the recommended constructor for creating ThinkingSummaries instances in application
   * code.
   *
   * @param knownValue the known thinking summaries enum value
   */
  public ThinkingSummaries(Known knownValue) {
    this.thinkingSummariesEnum = knownValue;
    this.value = knownValue.toString();
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  @JsonValue
  public String toString() {
    return this.value;
  }

  @ExcludeFromGeneratedCoverageReport
  @SuppressWarnings("PatternMatchingInstanceof")
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null) {
      return false;
    }

    if (!(o instanceof ThinkingSummaries)) {
      return false;
    }

    ThinkingSummaries other = (ThinkingSummaries) o;

    if (this.thinkingSummariesEnum != Known.THINKING_SUMMARIES_UNSPECIFIED
        && other.thinkingSummariesEnum != Known.THINKING_SUMMARIES_UNSPECIFIED) {
      return this.thinkingSummariesEnum == other.thinkingSummariesEnum;
    } else if (this.thinkingSummariesEnum == Known.THINKING_SUMMARIES_UNSPECIFIED
        && other.thinkingSummariesEnum == Known.THINKING_SUMMARIES_UNSPECIFIED) {
      return this.value.equals(other.value);
    }
    return false;
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  public int hashCode() {
    if (this.thinkingSummariesEnum != Known.THINKING_SUMMARIES_UNSPECIFIED) {
      return this.thinkingSummariesEnum.hashCode();
    } else {
      return Objects.hashCode(this.value);
    }
  }

  /**
   * Returns the known enum value for this thinking summaries mode.
   *
   * <p>If the value was not recognized during deserialization, this will return {@code
   * THINKING_SUMMARIES_UNSPECIFIED}.
   *
   * @return the known enum value
   */
  @ExcludeFromGeneratedCoverageReport
  public Known knownEnum() {
    return this.thinkingSummariesEnum;
  }
}
