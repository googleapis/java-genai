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

/** The environment being operated. */
public class Environment {

  /** Enum representing the known values for Environment. */
  public enum Known {
    /** Defaults to browser. */
    ENVIRONMENT_UNSPECIFIED,

    /** Operates in a web browser. */
    ENVIRONMENT_BROWSER
  }

  private Known environmentEnum;
  private final String value;

  @JsonCreator
  public Environment(String value) {
    this.value = value;
    for (Known environmentEnum : Known.values()) {
      if (Ascii.equalsIgnoreCase(environmentEnum.toString(), value)) {
        this.environmentEnum = environmentEnum;
        break;
      }
    }
    if (this.environmentEnum == null) {
      this.environmentEnum = Known.ENVIRONMENT_UNSPECIFIED;
    }
  }

  public Environment(Known knownValue) {
    this.environmentEnum = knownValue;
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

    if (!(o instanceof Environment)) {
      return false;
    }

    Environment other = (Environment) o;

    if (this.environmentEnum != Known.ENVIRONMENT_UNSPECIFIED
        && other.environmentEnum != Known.ENVIRONMENT_UNSPECIFIED) {
      return this.environmentEnum == other.environmentEnum;
    } else if (this.environmentEnum == Known.ENVIRONMENT_UNSPECIFIED
        && other.environmentEnum == Known.ENVIRONMENT_UNSPECIFIED) {
      return this.value.equals(other.value);
    }
    return false;
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  public int hashCode() {
    if (this.environmentEnum != Known.ENVIRONMENT_UNSPECIFIED) {
      return this.environmentEnum.hashCode();
    } else {
      return Objects.hashCode(this.value);
    }
  }

  @ExcludeFromGeneratedCoverageReport
  public Known knownEnum() {
    return this.environmentEnum;
  }
}
