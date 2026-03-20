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

/** Output only. Current state of the `Document`. This enum is not supported in Vertex AI. */
public class DocumentState {

  /** Enum representing the known values for DocumentState. */
  public enum Known {
    /** The default value. This value is used if the state is omitted. */
    STATE_UNSPECIFIED,

    /** Some `Chunks` of the `Document` are being processed (embedding and vector storage). */
    STATE_PENDING,

    /** All `Chunks` of the `Document` is processed and available for querying. */
    STATE_ACTIVE,

    /** Some `Chunks` of the `Document` failed processing. */
    STATE_FAILED,

    DOCUMENT_STATE_UNSPECIFIED
  }

  private Known documentStateEnum;
  private final String value;

  @JsonCreator
  public DocumentState(String value) {
    this.value = value;
    for (Known documentStateEnum : Known.values()) {
      if (Ascii.equalsIgnoreCase(documentStateEnum.toString(), value)) {
        this.documentStateEnum = documentStateEnum;
        break;
      }
    }
    if (this.documentStateEnum == null) {
      this.documentStateEnum = Known.DOCUMENT_STATE_UNSPECIFIED;
    }
  }

  public DocumentState(Known knownValue) {
    this.documentStateEnum = knownValue;
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

    if (!(o instanceof DocumentState)) {
      return false;
    }

    DocumentState other = (DocumentState) o;

    if (this.documentStateEnum != Known.DOCUMENT_STATE_UNSPECIFIED
        && other.documentStateEnum != Known.DOCUMENT_STATE_UNSPECIFIED) {
      return this.documentStateEnum == other.documentStateEnum;
    } else if (this.documentStateEnum == Known.DOCUMENT_STATE_UNSPECIFIED
        && other.documentStateEnum == Known.DOCUMENT_STATE_UNSPECIFIED) {
      return this.value.equals(other.value);
    }
    return false;
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  public int hashCode() {
    if (this.documentStateEnum != Known.DOCUMENT_STATE_UNSPECIFIED) {
      return this.documentStateEnum.hashCode();
    } else {
      return Objects.hashCode(this.value);
    }
  }

  @ExcludeFromGeneratedCoverageReport
  public Known knownEnum() {
    return this.documentStateEnum;
  }
}
