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
 * Controls the depth and extent of the model's reasoning process for the Interactions API.
 *
 * <p>The thinking level determines how many internal reasoning tokens the model should generate
 * before producing its final response. Higher thinking levels allow the model to engage in more
 * thorough analysis, exploration of alternative approaches, and step-by-step reasoning, which can
 * lead to more accurate and well-reasoned responses for complex tasks.
 *
 * <p>This class is specific to the Interactions API and serializes enum values to lowercase (e.g.,
 * "low", "medium", "high") per the OpenAPI specification. The {@code Known} enum contains all
 * recognized values from the OpenAPI specification, and {@code THINKING_LEVEL_UNSPECIFIED} serves
 * as a fallback for forward compatibility.
 *
 * <p>Example usage:
 *
 * <pre>{@code
 * // For simple tasks, use minimal thinking
 * ThinkingLevel level = new ThinkingLevel(ThinkingLevel.Known.MINIMAL);
 *
 * // For complex reasoning tasks, use high thinking
 * ThinkingLevel level = new ThinkingLevel(ThinkingLevel.Known.HIGH);
 *
 * // Include in generation config
 * GenerationConfig config = GenerationConfig.builder()
 *     .thinkingLevel(new ThinkingLevel(ThinkingLevel.Known.MEDIUM))
 *     .build();
 * }</pre>
 */
public class ThinkingLevel {

  /**
   * Enum representing the known values for ThinkingLevel.
   *
   * <p>These values control the amount of internal reasoning the model performs before generating
   * its response.
   */
  public enum Known {
    /**
     * Unspecified thinking level.
     *
     * <p>This is the fallback value used when the API returns an unknown thinking level not
     * recognized by this version of the SDK. This ensures forward compatibility when new thinking
     * levels are added to the API.
     */
    THINKING_LEVEL_UNSPECIFIED("unspecified"),

    /**
     * Low thinking level.
     *
     * <p>The model performs a limited amount of internal reasoning. Use this for tasks that
     * benefit from some deliberation but don't require extensive analysis. Generates fewer
     * thinking tokens than MEDIUM or HIGH.
     */
    LOW("low"),

    /**
     * Medium thinking level.
     *
     * <p>The model performs a moderate amount of internal reasoning. This is a balanced option
     * suitable for tasks of average complexity that benefit from careful consideration. Generates
     * more thinking tokens than LOW but fewer than HIGH.
     */
    MEDIUM("medium"),

    /**
     * High thinking level.
     *
     * <p>The model performs extensive internal reasoning. Use this for complex tasks that require
     * deep analysis, multi-step reasoning, or exploration of multiple approaches. Generates the
     * most thinking tokens, which may increase latency but can significantly improve response
     * quality for challenging problems.
     */
    HIGH("high"),

    /**
     * Minimal thinking level.
     *
     * <p>The model performs the least amount of internal reasoning. Use this for simple,
     * straightforward tasks where immediate responses are preferred and extensive deliberation
     * would not improve the output quality. Generates the fewest thinking tokens.
     */
    MINIMAL("minimal");

    private final String value;

    Known(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return this.value;
    }
  }

  private Known thinkingLevelEnum;
  private final String value;

  /**
   * Creates a ThinkingLevel from a string value.
   *
   * <p>This constructor is used by Jackson during JSON deserialization. It attempts to match the
   * provided string value to a known enum value (case-insensitive). If no match is found, it falls
   * back to {@code THINKING_LEVEL_UNSPECIFIED}.
   *
   * @param value the string representation of the thinking level
   */
  @JsonCreator
  public ThinkingLevel(String value) {
    this.value = value;
    for (Known thinkingLevelEnum : Known.values()) {
      if (Ascii.equalsIgnoreCase(thinkingLevelEnum.toString(), value)) {
        this.thinkingLevelEnum = thinkingLevelEnum;
        break;
      }
    }
    if (this.thinkingLevelEnum == null) {
      this.thinkingLevelEnum = Known.THINKING_LEVEL_UNSPECIFIED;
    }
  }

  /**
   * Creates a ThinkingLevel from a known enum value.
   *
   * <p>This is the recommended constructor for creating ThinkingLevel instances in application
   * code.
   *
   * @param knownValue the known thinking level enum value
   */
  public ThinkingLevel(Known knownValue) {
    this.thinkingLevelEnum = knownValue;
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

    if (!(o instanceof ThinkingLevel)) {
      return false;
    }

    ThinkingLevel other = (ThinkingLevel) o;

    if (this.thinkingLevelEnum != Known.THINKING_LEVEL_UNSPECIFIED
        && other.thinkingLevelEnum != Known.THINKING_LEVEL_UNSPECIFIED) {
      return this.thinkingLevelEnum == other.thinkingLevelEnum;
    } else if (this.thinkingLevelEnum == Known.THINKING_LEVEL_UNSPECIFIED
        && other.thinkingLevelEnum == Known.THINKING_LEVEL_UNSPECIFIED) {
      return this.value.equals(other.value);
    }
    return false;
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  public int hashCode() {
    if (this.thinkingLevelEnum != Known.THINKING_LEVEL_UNSPECIFIED) {
      return this.thinkingLevelEnum.hashCode();
    } else {
      return Objects.hashCode(this.value);
    }
  }

  /**
   * Returns the known enum value for this thinking level.
   *
   * <p>If the value was not recognized during deserialization, this will return {@code
   * THINKING_LEVEL_UNSPECIFIED}.
   *
   * @return the known enum value
   */
  @ExcludeFromGeneratedCoverageReport
  public Known knownEnum() {
    return this.thinkingLevelEnum;
  }
}
