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

/** Defines which input is included in the user's turn. This enum is not supported in Vertex AI. */
public class TurnCoverage {

  /** Enum representing the known values for TurnCoverage. */
  public enum Known {
    /**
     * If unspecified, a default behavior is selected based on the model. E.g., for Gemini 2.5, the
     * default is `TURN_INCLUDES_ONLY_ACTIVITY`, while for Gemini 3.1 and onwards, it's
     * `TURN_INCLUDES_AUDIO_ACTIVITY_AND_ALL_VIDEO`.
     */
    TURN_COVERAGE_UNSPECIFIED,

    /**
     * Includes activity since the last turn, excluding inactivity (e.g. silence on the audio
     * stream).
     */
    TURN_INCLUDES_ONLY_ACTIVITY,

    /**
     * Includes all realtime input since the last turn, including inactivity (e.g. silence on the
     * audio stream).
     */
    TURN_INCLUDES_ALL_INPUT,

    /**
     * Includes audio activity and all video since the last turn. With automatic activity detection,
     * audio activity means speech and excludes silence.
     */
    TURN_INCLUDES_AUDIO_ACTIVITY_AND_ALL_VIDEO
  }

  private Known turnCoverageEnum;
  private final String value;

  @JsonCreator
  public TurnCoverage(String value) {
    this.value = value;
    for (Known turnCoverageEnum : Known.values()) {
      if (Ascii.equalsIgnoreCase(turnCoverageEnum.toString(), value)) {
        this.turnCoverageEnum = turnCoverageEnum;
        break;
      }
    }
    if (this.turnCoverageEnum == null) {
      this.turnCoverageEnum = Known.TURN_COVERAGE_UNSPECIFIED;
    }
  }

  public TurnCoverage(Known knownValue) {
    this.turnCoverageEnum = knownValue;
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

    if (!(o instanceof TurnCoverage)) {
      return false;
    }

    TurnCoverage other = (TurnCoverage) o;

    if (this.turnCoverageEnum != Known.TURN_COVERAGE_UNSPECIFIED
        && other.turnCoverageEnum != Known.TURN_COVERAGE_UNSPECIFIED) {
      return this.turnCoverageEnum == other.turnCoverageEnum;
    } else if (this.turnCoverageEnum == Known.TURN_COVERAGE_UNSPECIFIED
        && other.turnCoverageEnum == Known.TURN_COVERAGE_UNSPECIFIED) {
      return this.value.equals(other.value);
    }
    return false;
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  public int hashCode() {
    if (this.turnCoverageEnum != Known.TURN_COVERAGE_UNSPECIFIED) {
      return this.turnCoverageEnum.hashCode();
    } else {
      return Objects.hashCode(this.value);
    }
  }

  @ExcludeFromGeneratedCoverageReport
  public Known knownEnum() {
    return this.turnCoverageEnum;
  }
}
