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

/** Enum that represents the segmentation mode. */
public class SegmentMode {

  /** Enum representing the known values for SegmentMode. */
  public enum Known {
    FOREGROUND,

    BACKGROUND,

    PROMPT,

    SEMANTIC,

    INTERACTIVE,

    SEGMENT_MODE_UNSPECIFIED
  }

  private Known segmentModeEnum;
  private final String value;

  @JsonCreator
  public SegmentMode(String value) {
    this.value = value;
    for (Known segmentModeEnum : Known.values()) {
      if (Ascii.equalsIgnoreCase(segmentModeEnum.toString(), value)) {
        this.segmentModeEnum = segmentModeEnum;
        break;
      }
    }
    if (this.segmentModeEnum == null) {
      this.segmentModeEnum = Known.SEGMENT_MODE_UNSPECIFIED;
    }
  }

  public SegmentMode(Known knownValue) {
    this.segmentModeEnum = knownValue;
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

    if (!(o instanceof SegmentMode)) {
      return false;
    }

    SegmentMode other = (SegmentMode) o;

    if (this.segmentModeEnum != Known.SEGMENT_MODE_UNSPECIFIED
        && other.segmentModeEnum != Known.SEGMENT_MODE_UNSPECIFIED) {
      return this.segmentModeEnum == other.segmentModeEnum;
    } else if (this.segmentModeEnum == Known.SEGMENT_MODE_UNSPECIFIED
        && other.segmentModeEnum == Known.SEGMENT_MODE_UNSPECIFIED) {
      return this.value.equals(other.value);
    }
    return false;
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  public int hashCode() {
    if (this.segmentModeEnum != Known.SEGMENT_MODE_UNSPECIFIED) {
      return this.segmentModeEnum.hashCode();
    } else {
      return Objects.hashCode(this.value);
    }
  }

  @ExcludeFromGeneratedCoverageReport
  public Known knownEnum() {
    return this.segmentModeEnum;
  }
}
