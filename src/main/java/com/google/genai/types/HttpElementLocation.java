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

/** The location of the API key. This enum is not supported in Gemini API. */
public class HttpElementLocation {

  /** Enum representing the known values for HttpElementLocation. */
  public enum Known {
    HTTP_IN_UNSPECIFIED,

    /** Element is in the HTTP request query. */
    HTTP_IN_QUERY,

    /** Element is in the HTTP request header. */
    HTTP_IN_HEADER,

    /** Element is in the HTTP request path. */
    HTTP_IN_PATH,

    /** Element is in the HTTP request body. */
    HTTP_IN_BODY,

    /** Element is in the HTTP request cookie. */
    HTTP_IN_COOKIE,

    HTTP_ELEMENT_LOCATION_UNSPECIFIED
  }

  private Known httpElementLocationEnum;
  private final String value;

  @JsonCreator
  public HttpElementLocation(String value) {
    this.value = value;
    for (Known httpElementLocationEnum : Known.values()) {
      if (Ascii.equalsIgnoreCase(httpElementLocationEnum.toString(), value)) {
        this.httpElementLocationEnum = httpElementLocationEnum;
        break;
      }
    }
    if (this.httpElementLocationEnum == null) {
      this.httpElementLocationEnum = Known.HTTP_ELEMENT_LOCATION_UNSPECIFIED;
    }
  }

  public HttpElementLocation(Known knownValue) {
    this.httpElementLocationEnum = knownValue;
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

    if (!(o instanceof HttpElementLocation)) {
      return false;
    }

    HttpElementLocation other = (HttpElementLocation) o;

    if (this.httpElementLocationEnum != Known.HTTP_ELEMENT_LOCATION_UNSPECIFIED
        && other.httpElementLocationEnum != Known.HTTP_ELEMENT_LOCATION_UNSPECIFIED) {
      return this.httpElementLocationEnum == other.httpElementLocationEnum;
    } else if (this.httpElementLocationEnum == Known.HTTP_ELEMENT_LOCATION_UNSPECIFIED
        && other.httpElementLocationEnum == Known.HTTP_ELEMENT_LOCATION_UNSPECIFIED) {
      return this.value.equals(other.value);
    }
    return false;
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  public int hashCode() {
    if (this.httpElementLocationEnum != Known.HTTP_ELEMENT_LOCATION_UNSPECIFIED) {
      return this.httpElementLocationEnum.hashCode();
    } else {
      return Objects.hashCode(this.value);
    }
  }

  @ExcludeFromGeneratedCoverageReport
  public Known knownEnum() {
    return this.httpElementLocationEnum;
  }
}
