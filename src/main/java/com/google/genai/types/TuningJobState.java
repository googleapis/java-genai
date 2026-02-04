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
 * Output only. The detail state of the tuning job (while the overall `JobState` is running). This
 * enum is not supported in Gemini API.
 */
public class TuningJobState {

  /** Enum representing the known values for TuningJobState. */
  public enum Known {
    /** Default tuning job state. */
    TUNING_JOB_STATE_UNSPECIFIED,

    /** Tuning job is waiting for job quota. */
    TUNING_JOB_STATE_WAITING_FOR_QUOTA,

    /** Tuning job is validating the dataset. */
    TUNING_JOB_STATE_PROCESSING_DATASET,

    /** Tuning job is waiting for hardware capacity. */
    TUNING_JOB_STATE_WAITING_FOR_CAPACITY,

    /** Tuning job is running. */
    TUNING_JOB_STATE_TUNING,

    /** Tuning job is doing some post processing steps. */
    TUNING_JOB_STATE_POST_PROCESSING
  }

  private Known tuningJobStateEnum;
  private final String value;

  @JsonCreator
  public TuningJobState(String value) {
    this.value = value;
    for (Known tuningJobStateEnum : Known.values()) {
      if (Ascii.equalsIgnoreCase(tuningJobStateEnum.toString(), value)) {
        this.tuningJobStateEnum = tuningJobStateEnum;
        break;
      }
    }
    if (this.tuningJobStateEnum == null) {
      this.tuningJobStateEnum = Known.TUNING_JOB_STATE_UNSPECIFIED;
    }
  }

  public TuningJobState(Known knownValue) {
    this.tuningJobStateEnum = knownValue;
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

    if (!(o instanceof TuningJobState)) {
      return false;
    }

    TuningJobState other = (TuningJobState) o;

    if (this.tuningJobStateEnum != Known.TUNING_JOB_STATE_UNSPECIFIED
        && other.tuningJobStateEnum != Known.TUNING_JOB_STATE_UNSPECIFIED) {
      return this.tuningJobStateEnum == other.tuningJobStateEnum;
    } else if (this.tuningJobStateEnum == Known.TUNING_JOB_STATE_UNSPECIFIED
        && other.tuningJobStateEnum == Known.TUNING_JOB_STATE_UNSPECIFIED) {
      return this.value.equals(other.value);
    }
    return false;
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  public int hashCode() {
    if (this.tuningJobStateEnum != Known.TUNING_JOB_STATE_UNSPECIFIED) {
      return this.tuningJobStateEnum.hashCode();
    } else {
      return Objects.hashCode(this.value);
    }
  }

  @ExcludeFromGeneratedCoverageReport
  public Known knownEnum() {
    return this.tuningJobStateEnum;
  }
}
