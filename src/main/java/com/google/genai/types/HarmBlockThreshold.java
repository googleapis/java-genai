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

/**
 * The threshold for blocking content. If the harm probability exceeds this threshold, the content
 * will be blocked.
 */
public class HarmBlockThreshold {

  /** Enum representing the known values for HarmBlockThreshold. */
  public enum Known {
    /** The harm block threshold is unspecified. */
    HARM_BLOCK_THRESHOLD_UNSPECIFIED,

    /** Block content with a low harm probability or higher. */
    BLOCK_LOW_AND_ABOVE,

    /** Block content with a medium harm probability or higher. */
    BLOCK_MEDIUM_AND_ABOVE,

    /** Block content with a high harm probability. */
    BLOCK_ONLY_HIGH,

    /** Do not block any content, regardless of its harm probability. */
    BLOCK_NONE,

    /** Turn off the safety filter entirely. */
    OFF
  }

  private Known harmBlockThresholdEnum;
  private final String value;

  @JsonCreator
  public HarmBlockThreshold(String value) {
    this.value = value;
    for (Known harmBlockThresholdEnum : Known.values()) {
      if (Ascii.equalsIgnoreCase(harmBlockThresholdEnum.toString(), value)) {
        this.harmBlockThresholdEnum = harmBlockThresholdEnum;
        break;
      }
    }
    if (this.harmBlockThresholdEnum == null) {
      this.harmBlockThresholdEnum = Known.HARM_BLOCK_THRESHOLD_UNSPECIFIED;
    }
  }

  public HarmBlockThreshold(Known knownValue) {
    this.harmBlockThresholdEnum = knownValue;
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

    if (!(o instanceof HarmBlockThreshold)) {
      return false;
    }

    HarmBlockThreshold other = (HarmBlockThreshold) o;

    if (this.harmBlockThresholdEnum != Known.HARM_BLOCK_THRESHOLD_UNSPECIFIED
        && other.harmBlockThresholdEnum != Known.HARM_BLOCK_THRESHOLD_UNSPECIFIED) {
      return this.harmBlockThresholdEnum == other.harmBlockThresholdEnum;
    } else if (this.harmBlockThresholdEnum == Known.HARM_BLOCK_THRESHOLD_UNSPECIFIED
        && other.harmBlockThresholdEnum == Known.HARM_BLOCK_THRESHOLD_UNSPECIFIED) {
      return this.value.equals(other.value);
    }
    return false;
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  public int hashCode() {
    if (this.harmBlockThresholdEnum != Known.HARM_BLOCK_THRESHOLD_UNSPECIFIED) {
      return this.harmBlockThresholdEnum.hashCode();
    } else {
      return Objects.hashCode(this.value);
    }
  }

  @ExcludeFromGeneratedCoverageReport
  public Known knownEnum() {
    return this.harmBlockThresholdEnum;
  }
}
