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

/** Defines the type for parsing sample response. This enum is not supported in Gemini API. */
public class ResponseParseType {

  /** Enum representing the known values for ResponseParseType. */
  public enum Known {
    /** Default value. Fallback to IDENTITY */
    RESPONSE_PARSE_TYPE_UNSPECIFIED,

    /** Returns the sample response as is. */
    IDENTITY,

    /**
     * Uses regex to extract the important part of sample response. Similar to
     * [GoogleSQL](https://cloud.google.com/bigquery/docs/reference/standard-sql/string_functions#regexp_extract)
     * `REGEX_EXTRACT(response, regex_extract_expression)`, but different in that if there are
     * multiple matches, the last match will be returned.
     */
    REGEX_EXTRACT
  }

  private Known responseParseTypeEnum;
  private final String value;

  @JsonCreator
  public ResponseParseType(String value) {
    this.value = value;
    for (Known responseParseTypeEnum : Known.values()) {
      if (Ascii.equalsIgnoreCase(responseParseTypeEnum.toString(), value)) {
        this.responseParseTypeEnum = responseParseTypeEnum;
        break;
      }
    }
    if (this.responseParseTypeEnum == null) {
      this.responseParseTypeEnum = Known.RESPONSE_PARSE_TYPE_UNSPECIFIED;
    }
  }

  public ResponseParseType(Known knownValue) {
    this.responseParseTypeEnum = knownValue;
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

    if (!(o instanceof ResponseParseType)) {
      return false;
    }

    ResponseParseType other = (ResponseParseType) o;

    if (this.responseParseTypeEnum != Known.RESPONSE_PARSE_TYPE_UNSPECIFIED
        && other.responseParseTypeEnum != Known.RESPONSE_PARSE_TYPE_UNSPECIFIED) {
      return this.responseParseTypeEnum == other.responseParseTypeEnum;
    } else if (this.responseParseTypeEnum == Known.RESPONSE_PARSE_TYPE_UNSPECIFIED
        && other.responseParseTypeEnum == Known.RESPONSE_PARSE_TYPE_UNSPECIFIED) {
      return this.value.equals(other.value);
    }
    return false;
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  public int hashCode() {
    if (this.responseParseTypeEnum != Known.RESPONSE_PARSE_TYPE_UNSPECIFIED) {
      return this.responseParseTypeEnum.hashCode();
    } else {
      return Objects.hashCode(this.value);
    }
  }

  @ExcludeFromGeneratedCoverageReport
  public Known knownEnum() {
    return this.responseParseTypeEnum;
  }
}
