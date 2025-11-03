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

/** Enum representing the tuning method. */
public class TuningMethod {

  /** Enum representing the known values for TuningMethod. */
  public enum Known {
    /** Supervised fine tuning. */
    SUPERVISED_FINE_TUNING,

    /** Preference optimization tuning. */
    PREFERENCE_TUNING,

    TUNING_METHOD_UNSPECIFIED
  }

  private Known tuningMethodEnum;
  private final String value;

  @JsonCreator
  public TuningMethod(String value) {
    this.value = value;
    for (Known tuningMethodEnum : Known.values()) {
      if (Ascii.equalsIgnoreCase(tuningMethodEnum.toString(), value)) {
        this.tuningMethodEnum = tuningMethodEnum;
        break;
      }
    }
    if (this.tuningMethodEnum == null) {
      this.tuningMethodEnum = Known.TUNING_METHOD_UNSPECIFIED;
    }
  }

  public TuningMethod(Known knownValue) {
    this.tuningMethodEnum = knownValue;
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

    if (!(o instanceof TuningMethod)) {
      return false;
    }

    TuningMethod other = (TuningMethod) o;

    if (this.tuningMethodEnum != Known.TUNING_METHOD_UNSPECIFIED
        && other.tuningMethodEnum != Known.TUNING_METHOD_UNSPECIFIED) {
      return this.tuningMethodEnum == other.tuningMethodEnum;
    } else if (this.tuningMethodEnum == Known.TUNING_METHOD_UNSPECIFIED
        && other.tuningMethodEnum == Known.TUNING_METHOD_UNSPECIFIED) {
      return this.value.equals(other.value);
    }
    return false;
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  public int hashCode() {
    if (this.tuningMethodEnum != Known.TUNING_METHOD_UNSPECIFIED) {
      return this.tuningMethodEnum.hashCode();
    } else {
      return Objects.hashCode(this.value);
    }
  }

  @ExcludeFromGeneratedCoverageReport
  public Known knownEnum() {
    return this.tuningMethodEnum;
  }
}
