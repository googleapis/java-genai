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
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.base.Ascii;
import java.util.Objects;

/** The type of tool in the function call. */
public class ToolType {

  /** Enum representing the known values for ToolType. */
  public enum Known {
    /** Unspecified tool type. */
    TOOL_TYPE_UNSPECIFIED,

    /** Google search tool, maps to Tool.google_search.search_types.web_search. */
    GOOGLE_SEARCH_WEB,

    /** Image search tool, maps to Tool.google_search.search_types.image_search. */
    GOOGLE_SEARCH_IMAGE,

    /** URL context tool, maps to Tool.url_context. */
    URL_CONTEXT,

    /** Google maps tool, maps to Tool.google_maps. */
    GOOGLE_MAPS,

    /** File search tool, maps to Tool.file_search. */
    FILE_SEARCH
  }

  private Known toolTypeEnum;
  private final String value;

  @JsonCreator
  public ToolType(String value) {
    this.value = value;
    for (Known toolTypeEnum : Known.values()) {
      if (Ascii.equalsIgnoreCase(toolTypeEnum.toString(), value)) {
        this.toolTypeEnum = toolTypeEnum;
        break;
      }
    }
    if (this.toolTypeEnum == null) {
      this.toolTypeEnum = Known.TOOL_TYPE_UNSPECIFIED;
    }
  }

  public ToolType(Known knownValue) {
    this.toolTypeEnum = knownValue;
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

    if (!(o instanceof ToolType)) {
      return false;
    }

    ToolType other = (ToolType) o;

    if (this.toolTypeEnum != Known.TOOL_TYPE_UNSPECIFIED
        && other.toolTypeEnum != Known.TOOL_TYPE_UNSPECIFIED) {
      return this.toolTypeEnum == other.toolTypeEnum;
    } else if (this.toolTypeEnum == Known.TOOL_TYPE_UNSPECIFIED
        && other.toolTypeEnum == Known.TOOL_TYPE_UNSPECIFIED) {
      return this.value.equals(other.value);
    }
    return false;
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  public int hashCode() {
    if (this.toolTypeEnum != Known.TOOL_TYPE_UNSPECIFIED) {
      return this.toolTypeEnum.hashCode();
    } else {
      return Objects.hashCode(this.value);
    }
  }

  @ExcludeFromGeneratedCoverageReport
  public Known knownEnum() {
    return this.toolTypeEnum;
  }
}
