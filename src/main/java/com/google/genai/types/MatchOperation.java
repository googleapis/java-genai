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

/** Match operation to use for evaluating rewards. This enum is not supported in Gemini API. */
public class MatchOperation {

  /** Enum representing the known values for MatchOperation. */
  public enum Known {
    /** Default value. A user error will be returned if not set. */
    MATCH_OPERATION_UNSPECIFIED,

    /**
     * Equivalent to
     * [GoogleSQL](https://cloud.google.com/bigquery/docs/reference/standard-sql/string_functions#regexp_contains)
     * `REGEX_CONTAINS(target, expression)`.
     */
    REGEX_CONTAINS,

    /** The match operation returns `true` if expression is a substring of the target. */
    PARTIAL_MATCH,

    /** The match operation returns `true` expression is an exact match of the target. */
    EXACT_MATCH
  }

  private Known matchOperationEnum;
  private final String value;

  @JsonCreator
  public MatchOperation(String value) {
    this.value = value;
    for (Known matchOperationEnum : Known.values()) {
      if (Ascii.equalsIgnoreCase(matchOperationEnum.toString(), value)) {
        this.matchOperationEnum = matchOperationEnum;
        break;
      }
    }
    if (this.matchOperationEnum == null) {
      this.matchOperationEnum = Known.MATCH_OPERATION_UNSPECIFIED;
    }
  }

  public MatchOperation(Known knownValue) {
    this.matchOperationEnum = knownValue;
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

    if (!(o instanceof MatchOperation)) {
      return false;
    }

    MatchOperation other = (MatchOperation) o;

    if (this.matchOperationEnum != Known.MATCH_OPERATION_UNSPECIFIED
        && other.matchOperationEnum != Known.MATCH_OPERATION_UNSPECIFIED) {
      return this.matchOperationEnum == other.matchOperationEnum;
    } else if (this.matchOperationEnum == Known.MATCH_OPERATION_UNSPECIFIED
        && other.matchOperationEnum == Known.MATCH_OPERATION_UNSPECIFIED) {
      return this.value.equals(other.value);
    }
    return false;
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  public int hashCode() {
    if (this.matchOperationEnum != Known.MATCH_OPERATION_UNSPECIFIED) {
      return this.matchOperationEnum.hashCode();
    } else {
      return Objects.hashCode(this.value);
    }
  }

  @ExcludeFromGeneratedCoverageReport
  public Known knownEnum() {
    return this.matchOperationEnum;
  }
}
