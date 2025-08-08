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

/** Tuning mode. */
public class TuningMode {

  /** Enum representing the known values for TuningMode. */
  public enum Known {
    /** Tuning mode is unspecified. */
    TUNING_MODE_UNSPECIFIED,

    /** Full fine-tuning mode. */
    TUNING_MODE_FULL,

    /** PEFT adapter tuning mode. */
    TUNING_MODE_PEFT_ADAPTER
  }

  private Known tuningModeEnum;
  private final String value;

  @JsonCreator
  public TuningMode(String value) {
    this.value = value;
    for (Known tuningModeEnum : Known.values()) {
      if (Ascii.equalsIgnoreCase(tuningModeEnum.toString(), value)) {
        this.tuningModeEnum = tuningModeEnum;
        break;
      }
    }
    if (this.tuningModeEnum == null) {
      this.tuningModeEnum = Known.TUNING_MODE_UNSPECIFIED;
    }
  }

  public TuningMode(Known knownValue) {
    this.tuningModeEnum = knownValue;
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

    if (!(o instanceof TuningMode)) {
      return false;
    }

    TuningMode other = (TuningMode) o;

    if (this.tuningModeEnum != Known.TUNING_MODE_UNSPECIFIED
        && other.tuningModeEnum != Known.TUNING_MODE_UNSPECIFIED) {
      return this.tuningModeEnum == other.tuningModeEnum;
    } else if (this.tuningModeEnum == Known.TUNING_MODE_UNSPECIFIED
        && other.tuningModeEnum == Known.TUNING_MODE_UNSPECIFIED) {
      return this.value.equals(other.value);
    }
    return false;
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  public int hashCode() {
    if (this.tuningModeEnum != Known.TUNING_MODE_UNSPECIFIED) {
      return this.tuningModeEnum.hashCode();
    } else {
      return Objects.hashCode(this.value);
    }
  }

  @ExcludeFromGeneratedCoverageReport
  public Known knownEnum() {
    return this.tuningModeEnum;
  }
}

