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
 * Sites with confidence level chosen & above this value will be blocked from the search results.
 * This enum is not supported in Gemini API.
 */
public class PhishBlockThreshold {

  /** Enum representing the known values for PhishBlockThreshold. */
  public enum Known {
    /** Defaults to unspecified. */
    PHISH_BLOCK_THRESHOLD_UNSPECIFIED,

    /** Blocks Low and above confidence URL that is risky. */
    BLOCK_LOW_AND_ABOVE,

    /** Blocks Medium and above confidence URL that is risky. */
    BLOCK_MEDIUM_AND_ABOVE,

    /** Blocks High and above confidence URL that is risky. */
    BLOCK_HIGH_AND_ABOVE,

    /** Blocks Higher and above confidence URL that is risky. */
    BLOCK_HIGHER_AND_ABOVE,

    /** Blocks Very high and above confidence URL that is risky. */
    BLOCK_VERY_HIGH_AND_ABOVE,

    /** Blocks Extremely high confidence URL that is risky. */
    BLOCK_ONLY_EXTREMELY_HIGH
  }

  private Known phishBlockThresholdEnum;
  private final String value;

  @JsonCreator
  public PhishBlockThreshold(String value) {
    this.value = value;
    for (Known phishBlockThresholdEnum : Known.values()) {
      if (Ascii.equalsIgnoreCase(phishBlockThresholdEnum.toString(), value)) {
        this.phishBlockThresholdEnum = phishBlockThresholdEnum;
        break;
      }
    }
    if (this.phishBlockThresholdEnum == null) {
      this.phishBlockThresholdEnum = Known.PHISH_BLOCK_THRESHOLD_UNSPECIFIED;
    }
  }

  public PhishBlockThreshold(Known knownValue) {
    this.phishBlockThresholdEnum = knownValue;
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

    if (!(o instanceof PhishBlockThreshold)) {
      return false;
    }

    PhishBlockThreshold other = (PhishBlockThreshold) o;

    if (this.phishBlockThresholdEnum != Known.PHISH_BLOCK_THRESHOLD_UNSPECIFIED
        && other.phishBlockThresholdEnum != Known.PHISH_BLOCK_THRESHOLD_UNSPECIFIED) {
      return this.phishBlockThresholdEnum == other.phishBlockThresholdEnum;
    } else if (this.phishBlockThresholdEnum == Known.PHISH_BLOCK_THRESHOLD_UNSPECIFIED
        && other.phishBlockThresholdEnum == Known.PHISH_BLOCK_THRESHOLD_UNSPECIFIED) {
      return this.value.equals(other.value);
    }
    return false;
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  public int hashCode() {
    if (this.phishBlockThresholdEnum != Known.PHISH_BLOCK_THRESHOLD_UNSPECIFIED) {
      return this.phishBlockThresholdEnum.hashCode();
    } else {
      return Objects.hashCode(this.value);
    }
  }

  @ExcludeFromGeneratedCoverageReport
  public Known knownEnum() {
    return this.phishBlockThresholdEnum;
  }
}
