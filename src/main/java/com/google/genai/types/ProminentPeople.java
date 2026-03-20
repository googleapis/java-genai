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
 * Controls whether prominent people (celebrities) generation is allowed. If used with
 * personGeneration, personGeneration enum would take precedence. For instance, if ALLOW_NONE is
 * set, all person generation would be blocked. If this field is unspecified, the default behavior
 * is to allow prominent people. This enum is not supported in Gemini API.
 */
public class ProminentPeople {

  /** Enum representing the known values for ProminentPeople. */
  public enum Known {
    /**
     * Unspecified value. The model will proceed with the default behavior, which is to allow
     * generation of prominent people.
     */
    PROMINENT_PEOPLE_UNSPECIFIED,

    /** Allows the model to generate images of prominent people. */
    ALLOW_PROMINENT_PEOPLE,

    /** Prevents the model from generating images of prominent people. */
    BLOCK_PROMINENT_PEOPLE
  }

  private Known prominentPeopleEnum;
  private final String value;

  @JsonCreator
  public ProminentPeople(String value) {
    this.value = value;
    for (Known prominentPeopleEnum : Known.values()) {
      if (Ascii.equalsIgnoreCase(prominentPeopleEnum.toString(), value)) {
        this.prominentPeopleEnum = prominentPeopleEnum;
        break;
      }
    }
    if (this.prominentPeopleEnum == null) {
      this.prominentPeopleEnum = Known.PROMINENT_PEOPLE_UNSPECIFIED;
    }
  }

  public ProminentPeople(Known knownValue) {
    this.prominentPeopleEnum = knownValue;
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

    if (!(o instanceof ProminentPeople)) {
      return false;
    }

    ProminentPeople other = (ProminentPeople) o;

    if (this.prominentPeopleEnum != Known.PROMINENT_PEOPLE_UNSPECIFIED
        && other.prominentPeopleEnum != Known.PROMINENT_PEOPLE_UNSPECIFIED) {
      return this.prominentPeopleEnum == other.prominentPeopleEnum;
    } else if (this.prominentPeopleEnum == Known.PROMINENT_PEOPLE_UNSPECIFIED
        && other.prominentPeopleEnum == Known.PROMINENT_PEOPLE_UNSPECIFIED) {
      return this.value.equals(other.value);
    }
    return false;
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  public int hashCode() {
    if (this.prominentPeopleEnum != Known.PROMINENT_PEOPLE_UNSPECIFIED) {
      return this.prominentPeopleEnum.hashCode();
    } else {
      return Objects.hashCode(this.value);
    }
  }

  @ExcludeFromGeneratedCoverageReport
  public Known knownEnum() {
    return this.prominentPeopleEnum;
  }
}
