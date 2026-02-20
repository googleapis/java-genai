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
 * The type of tool choice behavior for the model in the Interactions API.
 *
 * <p>This class defines how the model should handle tool calls during generation. It supports both
 * simple type-based control (auto, any, none, validated) and can be used in conjunction with {@link
 * ToolChoiceConfig} for more fine-grained control over which specific tools are allowed.
 *
 * <p>The class follows the standard enum wrapper pattern used throughout the codebase (see {@code
 * HarmBlockThreshold}, {@code FunctionCallingConfigMode}). The {@code Known} enum contains all
 * recognized values from the OpenAPI specification, and the {@code TOOL_CHOICE_TYPE_UNSPECIFIED}
 * value serves as a fallback for forward compatibility with unknown values from the API.
 *
 * <p>Example usage:
 *
 * <pre>{@code
 * // Use AUTO to let the model decide
 * ToolChoice toolChoice = ToolChoice.ofType(new ToolChoiceType(ToolChoiceType.Known.AUTO));
 *
 * // Force the model to use a tool
 * ToolChoice toolChoice = ToolChoice.ofType(new ToolChoiceType(ToolChoiceType.Known.ANY));
 *
 * // Prevent the model from using tools
 * ToolChoice toolChoice = ToolChoice.ofType(new ToolChoiceType(ToolChoiceType.Known.NONE));
 * }</pre>
 */
public class ToolChoiceType {

  /**
   * Enum representing the known values for ToolChoiceType.
   *
   * <p>These values control how the model interacts with available tools during generation.
   */
  public enum Known {
    /**
     * Unspecified tool choice type.
     *
     * <p>This is the fallback value used when the API returns an unknown tool choice type not
     * recognized by this version of the SDK. This ensures forward compatibility when new tool
     * choice types are added to the API.
     */
    TOOL_CHOICE_TYPE_UNSPECIFIED("unspecified"),

    /**
     * The model decides whether to use tools based on the context.
     *
     * <p>In AUTO mode, the model will analyze the user's request and determine whether calling a
     * tool would be helpful. The model has full autonomy to use tools or generate a direct response
     * as appropriate.
     */
    AUTO("auto"),

    /**
     * The model must use at least one of the available tools.
     *
     * <p>In ANY mode, the model is required to make a tool call rather than generating a direct
     * text response. If multiple tools are available, the model chooses which one(s) to call based
     * on the context.
     */
    ANY("any"),

    /**
     * The model must not use any tools.
     *
     * <p>In NONE mode, the model is prohibited from making any tool calls and must generate a
     * direct text response. Use this when you want to ensure the model only produces natural
     * language output.
     */
    NONE("none"),

    /**
     * The model must use tools that have been validated.
     *
     * <p>In VALIDATED mode, the model is restricted to using only tools that have passed validation
     * checks. This provides an additional safety layer for tool usage.
     */
    VALIDATED("validated");

    private final String value;

    Known(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return this.value;
    }
  }

  private Known toolChoiceTypeEnum;
  private final String value;

  /**
   * Creates a ToolChoiceType from a string value.
   *
   * <p>This constructor is used by Jackson during JSON deserialization. It attempts to match the
   * provided string value to a known enum value (case-insensitive). If no match is found, it falls
   * back to {@code TOOL_CHOICE_TYPE_UNSPECIFIED}.
   *
   * @param value the string representation of the tool choice type
   */
  @JsonCreator
  public ToolChoiceType(String value) {
    this.value = value;
    for (Known toolChoiceTypeEnum : Known.values()) {
      if (Ascii.equalsIgnoreCase(toolChoiceTypeEnum.toString(), value)) {
        this.toolChoiceTypeEnum = toolChoiceTypeEnum;
        break;
      }
    }
    if (this.toolChoiceTypeEnum == null) {
      this.toolChoiceTypeEnum = Known.TOOL_CHOICE_TYPE_UNSPECIFIED;
    }
  }

  /**
   * Creates a ToolChoiceType from a known enum value.
   *
   * <p>This is the recommended constructor for creating ToolChoiceType instances in application
   * code.
   *
   * @param knownValue the known tool choice type enum value
   */
  public ToolChoiceType(Known knownValue) {
    this.toolChoiceTypeEnum = knownValue;
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

    if (!(o instanceof ToolChoiceType)) {
      return false;
    }

    ToolChoiceType other = (ToolChoiceType) o;

    if (this.toolChoiceTypeEnum != Known.TOOL_CHOICE_TYPE_UNSPECIFIED
        && other.toolChoiceTypeEnum != Known.TOOL_CHOICE_TYPE_UNSPECIFIED) {
      return this.toolChoiceTypeEnum == other.toolChoiceTypeEnum;
    } else if (this.toolChoiceTypeEnum == Known.TOOL_CHOICE_TYPE_UNSPECIFIED
        && other.toolChoiceTypeEnum == Known.TOOL_CHOICE_TYPE_UNSPECIFIED) {
      return this.value.equals(other.value);
    }
    return false;
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  public int hashCode() {
    if (this.toolChoiceTypeEnum != Known.TOOL_CHOICE_TYPE_UNSPECIFIED) {
      return this.toolChoiceTypeEnum.hashCode();
    } else {
      return Objects.hashCode(this.value);
    }
  }

  /**
   * Returns the known enum value for this tool choice type.
   *
   * <p>If the value was not recognized during deserialization, this will return {@code
   * TOOL_CHOICE_TYPE_UNSPECIFIED}.
   *
   * @return the known enum value
   */
  @ExcludeFromGeneratedCoverageReport
  public Known knownEnum() {
    return this.toolChoiceTypeEnum;
  }
}
