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

/** Output only. The probability of harm for this category. */
public class HarmProbability {

  /** Enum representing the known values for HarmProbability. */
  public enum Known {
    /** The harm probability is unspecified. */
    HARM_PROBABILITY_UNSPECIFIED,

    /** The harm probability is negligible. */
    NEGLIGIBLE,

    /** The harm probability is low. */
    LOW,

    /** The harm probability is medium. */
    MEDIUM,

    /** The harm probability is high. */
    HIGH
  }

  private Known harmProbabilityEnum;
  private final String value;

  @JsonCreator
  public HarmProbability(String value) {
    this.value = value;
    for (Known harmProbabilityEnum : Known.values()) {
      if (Ascii.equalsIgnoreCase(harmProbabilityEnum.toString(), value)) {
        this.harmProbabilityEnum = harmProbabilityEnum;
        break;
      }
    }
    if (this.harmProbabilityEnum == null) {
      this.harmProbabilityEnum = Known.HARM_PROBABILITY_UNSPECIFIED;
    }
  }

  public HarmProbability(Known knownValue) {
    this.harmProbabilityEnum = knownValue;
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

    if (!(o instanceof HarmProbability)) {
      return false;
    }

    HarmProbability other = (HarmProbability) o;

    if (this.harmProbabilityEnum != Known.HARM_PROBABILITY_UNSPECIFIED
        && other.harmProbabilityEnum != Known.HARM_PROBABILITY_UNSPECIFIED) {
      return this.harmProbabilityEnum == other.harmProbabilityEnum;
    } else if (this.harmProbabilityEnum == Known.HARM_PROBABILITY_UNSPECIFIED
        && other.harmProbabilityEnum == Known.HARM_PROBABILITY_UNSPECIFIED) {
      return this.value.equals(other.value);
    }
    return false;
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  public int hashCode() {
    if (this.harmProbabilityEnum != Known.HARM_PROBABILITY_UNSPECIFIED) {
      return this.harmProbabilityEnum.hashCode();
    } else {
      return Objects.hashCode(this.value);
    }
  }

  @ExcludeFromGeneratedCoverageReport
  public Known knownEnum() {
    return this.harmProbabilityEnum;
  }
}
