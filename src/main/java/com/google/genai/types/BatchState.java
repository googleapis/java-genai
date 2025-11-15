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

/** Output only. The state of the batch. This enum is not supported in Vertex AI. */
public class BatchState {

  /** Enum representing the known values for BatchState. */
  public enum Known {
    /** The batch state is unspecified. */
    BATCH_STATE_UNSPECIFIED,

    /** The service is preparing to run the batch. */
    BATCH_STATE_PENDING,

    /** The batch is in progress. */
    BATCH_STATE_RUNNING,

    /** The batch completed successfully. */
    BATCH_STATE_SUCCEEDED,

    /** The batch failed. */
    BATCH_STATE_FAILED,

    /** The batch has been cancelled. */
    BATCH_STATE_CANCELLED,

    /** The batch has expired. */
    BATCH_STATE_EXPIRED
  }

  private Known batchStateEnum;
  private final String value;

  @JsonCreator
  public BatchState(String value) {
    this.value = value;
    for (Known batchStateEnum : Known.values()) {
      if (Ascii.equalsIgnoreCase(batchStateEnum.toString(), value)) {
        this.batchStateEnum = batchStateEnum;
        break;
      }
    }
    if (this.batchStateEnum == null) {
      this.batchStateEnum = Known.BATCH_STATE_UNSPECIFIED;
    }
  }

  public BatchState(Known knownValue) {
    this.batchStateEnum = knownValue;
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

    if (!(o instanceof BatchState)) {
      return false;
    }

    BatchState other = (BatchState) o;

    if (this.batchStateEnum != Known.BATCH_STATE_UNSPECIFIED
        && other.batchStateEnum != Known.BATCH_STATE_UNSPECIFIED) {
      return this.batchStateEnum == other.batchStateEnum;
    } else if (this.batchStateEnum == Known.BATCH_STATE_UNSPECIFIED
        && other.batchStateEnum == Known.BATCH_STATE_UNSPECIFIED) {
      return this.value.equals(other.value);
    }
    return false;
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  public int hashCode() {
    if (this.batchStateEnum != Known.BATCH_STATE_UNSPECIFIED) {
      return this.batchStateEnum.hashCode();
    } else {
      return Objects.hashCode(this.value);
    }
  }

  @ExcludeFromGeneratedCoverageReport
  public Known knownEnum() {
    return this.batchStateEnum;
  }
}
