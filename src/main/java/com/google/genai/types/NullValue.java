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

/** Optional. Represents a null value. */
public class NullValue {

  /** Enum representing the known values for NullValue. */
  public enum Known {
    NULL_VALUE,

    NULL_VALUE_UNSPECIFIED
  }

  private Known nullValueEnum;
  private final String value;

  @JsonCreator
  public NullValue(String value) {
    this.value = value;
    for (Known nullValueEnum : Known.values()) {
      if (Ascii.equalsIgnoreCase(nullValueEnum.toString(), value)) {
        this.nullValueEnum = nullValueEnum;
        break;
      }
    }
    if (this.nullValueEnum == null) {
      this.nullValueEnum = Known.NULL_VALUE_UNSPECIFIED;
    }
  }

  public NullValue(Known knownValue) {
    this.nullValueEnum = knownValue;
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

    if (!(o instanceof NullValue)) {
      return false;
    }

    NullValue other = (NullValue) o;

    if (this.nullValueEnum != Known.NULL_VALUE_UNSPECIFIED
        && other.nullValueEnum != Known.NULL_VALUE_UNSPECIFIED) {
      return this.nullValueEnum == other.nullValueEnum;
    } else if (this.nullValueEnum == Known.NULL_VALUE_UNSPECIFIED
        && other.nullValueEnum == Known.NULL_VALUE_UNSPECIFIED) {
      return this.value.equals(other.value);
    }
    return false;
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  public int hashCode() {
    if (this.nullValueEnum != Known.NULL_VALUE_UNSPECIFIED) {
      return this.nullValueEnum.hashCode();
    } else {
      return Objects.hashCode(this.value);
    }
  }

  @ExcludeFromGeneratedCoverageReport
  public Known knownEnum() {
    return this.nullValueEnum;
  }
}
