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

/** The harm category to be blocked. */
public class HarmCategory {

  /** Enum representing the known values for HarmCategory. */
  public enum Known {
    /** Default value. This value is unused. */
    HARM_CATEGORY_UNSPECIFIED,

    /** Abusive, threatening, or content intended to bully, torment, or ridicule. */
    HARM_CATEGORY_HARASSMENT,

    /**
     * Content that promotes violence or incites hatred against individuals or groups based on
     * certain attributes.
     */
    HARM_CATEGORY_HATE_SPEECH,

    /** Content that contains sexually explicit material. */
    HARM_CATEGORY_SEXUALLY_EXPLICIT,

    /** Content that promotes, facilitates, or enables dangerous activities. */
    HARM_CATEGORY_DANGEROUS_CONTENT,

    /**
     * Deprecated: Election filter is not longer supported. The harm category is civic integrity.
     */
    HARM_CATEGORY_CIVIC_INTEGRITY,

    /** Images that contain hate speech. This enum value is not supported in Gemini API. */
    HARM_CATEGORY_IMAGE_HATE,

    /** Images that contain dangerous content. This enum value is not supported in Gemini API. */
    HARM_CATEGORY_IMAGE_DANGEROUS_CONTENT,

    /** Images that contain harassment. This enum value is not supported in Gemini API. */
    HARM_CATEGORY_IMAGE_HARASSMENT,

    /**
     * Images that contain sexually explicit content. This enum value is not supported in Gemini
     * API.
     */
    HARM_CATEGORY_IMAGE_SEXUALLY_EXPLICIT,

    /**
     * Prompts designed to bypass safety filters. This enum value is not supported in Gemini API.
     */
    HARM_CATEGORY_JAILBREAK
  }

  private Known harmCategoryEnum;
  private final String value;

  @JsonCreator
  public HarmCategory(String value) {
    this.value = value;
    for (Known harmCategoryEnum : Known.values()) {
      if (Ascii.equalsIgnoreCase(harmCategoryEnum.toString(), value)) {
        this.harmCategoryEnum = harmCategoryEnum;
        break;
      }
    }
    if (this.harmCategoryEnum == null) {
      this.harmCategoryEnum = Known.HARM_CATEGORY_UNSPECIFIED;
    }
  }

  public HarmCategory(Known knownValue) {
    this.harmCategoryEnum = knownValue;
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

    if (!(o instanceof HarmCategory)) {
      return false;
    }

    HarmCategory other = (HarmCategory) o;

    if (this.harmCategoryEnum != Known.HARM_CATEGORY_UNSPECIFIED
        && other.harmCategoryEnum != Known.HARM_CATEGORY_UNSPECIFIED) {
      return this.harmCategoryEnum == other.harmCategoryEnum;
    } else if (this.harmCategoryEnum == Known.HARM_CATEGORY_UNSPECIFIED
        && other.harmCategoryEnum == Known.HARM_CATEGORY_UNSPECIFIED) {
      return this.value.equals(other.value);
    }
    return false;
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  public int hashCode() {
    if (this.harmCategoryEnum != Known.HARM_CATEGORY_UNSPECIFIED) {
      return this.harmCategoryEnum.hashCode();
    } else {
      return Objects.hashCode(this.value);
    }
  }

  @ExcludeFromGeneratedCoverageReport
  public Known knownEnum() {
    return this.harmCategoryEnum;
  }
}
