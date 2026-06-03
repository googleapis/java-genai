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

/** Represents how much to think for the tuning job. */
public class ReinforcementTuningThinkingLevel {

  /** Enum representing the known values for ReinforcementTuningThinkingLevel. */
  public enum Known {
    /** Unspecified thinking level. */
    REINFORCEMENT_TUNING_THINKING_LEVEL_UNSPECIFIED,

    /** Little to no thinking. */
    MINIMAL,

    /** High thinking level. */
    HIGH
  }

  private Known reinforcementTuningThinkingLevelEnum;
  private final String value;

  @JsonCreator
  public ReinforcementTuningThinkingLevel(String value) {
    this.value = value;
    for (Known reinforcementTuningThinkingLevelEnum : Known.values()) {
      if (Ascii.equalsIgnoreCase(reinforcementTuningThinkingLevelEnum.toString(), value)) {
        this.reinforcementTuningThinkingLevelEnum = reinforcementTuningThinkingLevelEnum;
        break;
      }
    }
    if (this.reinforcementTuningThinkingLevelEnum == null) {
      this.reinforcementTuningThinkingLevelEnum =
          Known.REINFORCEMENT_TUNING_THINKING_LEVEL_UNSPECIFIED;
    }
  }

  public ReinforcementTuningThinkingLevel(Known knownValue) {
    this.reinforcementTuningThinkingLevelEnum = knownValue;
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

    if (!(o instanceof ReinforcementTuningThinkingLevel)) {
      return false;
    }

    ReinforcementTuningThinkingLevel other = (ReinforcementTuningThinkingLevel) o;

    if (this.reinforcementTuningThinkingLevelEnum
            != Known.REINFORCEMENT_TUNING_THINKING_LEVEL_UNSPECIFIED
        && other.reinforcementTuningThinkingLevelEnum
            != Known.REINFORCEMENT_TUNING_THINKING_LEVEL_UNSPECIFIED) {
      return this.reinforcementTuningThinkingLevelEnum
          == other.reinforcementTuningThinkingLevelEnum;
    } else if (this.reinforcementTuningThinkingLevelEnum
            == Known.REINFORCEMENT_TUNING_THINKING_LEVEL_UNSPECIFIED
        && other.reinforcementTuningThinkingLevelEnum
            == Known.REINFORCEMENT_TUNING_THINKING_LEVEL_UNSPECIFIED) {
      return this.value.equals(other.value);
    }
    return false;
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  public int hashCode() {
    if (this.reinforcementTuningThinkingLevelEnum
        != Known.REINFORCEMENT_TUNING_THINKING_LEVEL_UNSPECIFIED) {
      return this.reinforcementTuningThinkingLevelEnum.hashCode();
    } else {
      return Objects.hashCode(this.value);
    }
  }

  @ExcludeFromGeneratedCoverageReport
  public Known knownEnum() {
    return this.reinforcementTuningThinkingLevelEnum;
  }
}
