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

/** The API spec that the external API implements. This enum is not supported in Gemini API. */
public class ApiSpec {

  /** Enum representing the known values for ApiSpec. */
  public enum Known {
    /** Unspecified API spec. This value should not be used. */
    API_SPEC_UNSPECIFIED,

    /** Simple search API spec. */
    SIMPLE_SEARCH,

    /** Elastic search API spec. */
    ELASTIC_SEARCH
  }

  private Known apiSpecEnum;
  private final String value;

  @JsonCreator
  public ApiSpec(String value) {
    this.value = value;
    for (Known apiSpecEnum : Known.values()) {
      if (Ascii.equalsIgnoreCase(apiSpecEnum.toString(), value)) {
        this.apiSpecEnum = apiSpecEnum;
        break;
      }
    }
    if (this.apiSpecEnum == null) {
      this.apiSpecEnum = Known.API_SPEC_UNSPECIFIED;
    }
  }

  public ApiSpec(Known knownValue) {
    this.apiSpecEnum = knownValue;
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

    if (!(o instanceof ApiSpec)) {
      return false;
    }

    ApiSpec other = (ApiSpec) o;

    if (this.apiSpecEnum != Known.API_SPEC_UNSPECIFIED
        && other.apiSpecEnum != Known.API_SPEC_UNSPECIFIED) {
      return this.apiSpecEnum == other.apiSpecEnum;
    } else if (this.apiSpecEnum == Known.API_SPEC_UNSPECIFIED
        && other.apiSpecEnum == Known.API_SPEC_UNSPECIFIED) {
      return this.value.equals(other.value);
    }
    return false;
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  public int hashCode() {
    if (this.apiSpecEnum != Known.API_SPEC_UNSPECIFIED) {
      return this.apiSpecEnum.hashCode();
    } else {
      return Objects.hashCode(this.value);
    }
  }

  @ExcludeFromGeneratedCoverageReport
  public Known knownEnum() {
    return this.apiSpecEnum;
  }
}
