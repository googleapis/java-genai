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

/** The number of thoughts tokens that the model should generate. */
public class ThinkingLevel {

  /** Enum representing the known values for ThinkingLevel. */
  public enum Known {
    /** Unspecified thinking level. */
    THINKING_LEVEL_UNSPECIFIED,

    /** Low thinking level. */
    LOW,

    /** Medium thinking level. */
    MEDIUM,

    /** High thinking level. */
    HIGH,

    /** MINIMAL thinking level. */
    MINIMAL
  }

  private Known thinkingLevelEnum;
  private final String value;

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

  @ExcludeFromGeneratedCoverageReport
  public Known knownEnum() {
    return this.thinkingLevelEnum;
  }
}
