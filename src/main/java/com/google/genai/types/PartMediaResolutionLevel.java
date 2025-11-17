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

/** The tokenization quality used for given media. */
public class PartMediaResolutionLevel {

  /** Enum representing the known values for PartMediaResolutionLevel. */
  public enum Known {
    /** Media resolution has not been set. */
    MEDIA_RESOLUTION_UNSPECIFIED,

    /** Media resolution set to low. */
    MEDIA_RESOLUTION_LOW,

    /** Media resolution set to medium. */
    MEDIA_RESOLUTION_MEDIUM,

    /** Media resolution set to high. */
    MEDIA_RESOLUTION_HIGH,

    PART_MEDIA_RESOLUTION_LEVEL_UNSPECIFIED
  }

  private Known partMediaResolutionLevelEnum;
  private final String value;

  @JsonCreator
  public PartMediaResolutionLevel(String value) {
    this.value = value;
    for (Known partMediaResolutionLevelEnum : Known.values()) {
      if (Ascii.equalsIgnoreCase(partMediaResolutionLevelEnum.toString(), value)) {
        this.partMediaResolutionLevelEnum = partMediaResolutionLevelEnum;
        break;
      }
    }
    if (this.partMediaResolutionLevelEnum == null) {
      this.partMediaResolutionLevelEnum = Known.PART_MEDIA_RESOLUTION_LEVEL_UNSPECIFIED;
    }
  }

  public PartMediaResolutionLevel(Known knownValue) {
    this.partMediaResolutionLevelEnum = knownValue;
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

    if (!(o instanceof PartMediaResolutionLevel)) {
      return false;
    }

    PartMediaResolutionLevel other = (PartMediaResolutionLevel) o;

    if (this.partMediaResolutionLevelEnum != Known.PART_MEDIA_RESOLUTION_LEVEL_UNSPECIFIED
        && other.partMediaResolutionLevelEnum != Known.PART_MEDIA_RESOLUTION_LEVEL_UNSPECIFIED) {
      return this.partMediaResolutionLevelEnum == other.partMediaResolutionLevelEnum;
    } else if (this.partMediaResolutionLevelEnum == Known.PART_MEDIA_RESOLUTION_LEVEL_UNSPECIFIED
        && other.partMediaResolutionLevelEnum == Known.PART_MEDIA_RESOLUTION_LEVEL_UNSPECIFIED) {
      return this.value.equals(other.value);
    }
    return false;
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  public int hashCode() {
    if (this.partMediaResolutionLevelEnum != Known.PART_MEDIA_RESOLUTION_LEVEL_UNSPECIFIED) {
      return this.partMediaResolutionLevelEnum.hashCode();
    } else {
      return Objects.hashCode(this.value);
    }
  }

  @ExcludeFromGeneratedCoverageReport
  public Known knownEnum() {
    return this.partMediaResolutionLevelEnum;
  }
}
