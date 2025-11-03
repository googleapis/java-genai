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

/** The tuning task. Either I2V or T2V. This enum is not supported in Gemini API. */
public class TuningTask {

  /** Enum representing the known values for TuningTask. */
  public enum Known {
    /** Default value. This value is unused. */
    TUNING_TASK_UNSPECIFIED,

    /** Tuning task for image to video. */
    TUNING_TASK_I2V,

    /** Tuning task for text to video. */
    TUNING_TASK_T2V
  }

  private Known tuningTaskEnum;
  private final String value;

  @JsonCreator
  public TuningTask(String value) {
    this.value = value;
    for (Known tuningTaskEnum : Known.values()) {
      if (Ascii.equalsIgnoreCase(tuningTaskEnum.toString(), value)) {
        this.tuningTaskEnum = tuningTaskEnum;
        break;
      }
    }
    if (this.tuningTaskEnum == null) {
      this.tuningTaskEnum = Known.TUNING_TASK_UNSPECIFIED;
    }
  }

  public TuningTask(Known knownValue) {
    this.tuningTaskEnum = knownValue;
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

    if (!(o instanceof TuningTask)) {
      return false;
    }

    TuningTask other = (TuningTask) o;

    if (this.tuningTaskEnum != Known.TUNING_TASK_UNSPECIFIED
        && other.tuningTaskEnum != Known.TUNING_TASK_UNSPECIFIED) {
      return this.tuningTaskEnum == other.tuningTaskEnum;
    } else if (this.tuningTaskEnum == Known.TUNING_TASK_UNSPECIFIED
        && other.tuningTaskEnum == Known.TUNING_TASK_UNSPECIFIED) {
      return this.value.equals(other.value);
    }
    return false;
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  public int hashCode() {
    if (this.tuningTaskEnum != Known.TUNING_TASK_UNSPECIFIED) {
      return this.tuningTaskEnum.hashCode();
    } else {
      return Objects.hashCode(this.value);
    }
  }

  @ExcludeFromGeneratedCoverageReport
  public Known knownEnum() {
    return this.tuningTaskEnum;
  }
}
