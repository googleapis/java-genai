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

/** Enum that controls the generation of people. */
public class PersonGeneration {

  /** Enum representing the known values for PersonGeneration. */
  public enum Known {
    /** Block generation of images of people. */
    DONT_ALLOW,

    /** Generate images of adults, but not children. */
    ALLOW_ADULT,

    /** Generate images that include adults and children. */
    ALLOW_ALL,

    PERSON_GENERATION_UNSPECIFIED
  }

  private Known personGenerationEnum;
  private final String value;

  @JsonCreator
  public PersonGeneration(String value) {
    this.value = value;
    for (Known personGenerationEnum : Known.values()) {
      if (Ascii.equalsIgnoreCase(personGenerationEnum.toString(), value)) {
        this.personGenerationEnum = personGenerationEnum;
        break;
      }
    }
    if (this.personGenerationEnum == null) {
      this.personGenerationEnum = Known.PERSON_GENERATION_UNSPECIFIED;
    }
  }

  public PersonGeneration(Known knownValue) {
    this.personGenerationEnum = knownValue;
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

    if (!(o instanceof PersonGeneration)) {
      return false;
    }

    PersonGeneration other = (PersonGeneration) o;

    if (this.personGenerationEnum != Known.PERSON_GENERATION_UNSPECIFIED
        && other.personGenerationEnum != Known.PERSON_GENERATION_UNSPECIFIED) {
      return this.personGenerationEnum == other.personGenerationEnum;
    } else if (this.personGenerationEnum == Known.PERSON_GENERATION_UNSPECIFIED
        && other.personGenerationEnum == Known.PERSON_GENERATION_UNSPECIFIED) {
      return this.value.equals(other.value);
    }
    return false;
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  public int hashCode() {
    if (this.personGenerationEnum != Known.PERSON_GENERATION_UNSPECIFIED) {
      return this.personGenerationEnum.hashCode();
    } else {
      return Objects.hashCode(this.value);
    }
  }

  @ExcludeFromGeneratedCoverageReport
  public Known knownEnum() {
    return this.personGenerationEnum;
  }
}
