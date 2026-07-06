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

/** The aspect ratio for the image output. */
public class AspectRatio {

  /** Enum representing the known values for AspectRatio. */
  public enum Known {
    /** Default value. This value is unused. */
    ASPECT_RATIO_UNSPECIFIED,

    /** 1:1 aspect ratio. */
    ASPECT_RATIO_ONE_BY_ONE,

    /** 2:3 aspect ratio. */
    ASPECT_RATIO_TWO_BY_THREE,

    /** 3:2 aspect ratio. */
    ASPECT_RATIO_THREE_BY_TWO,

    /** 3:4 aspect ratio. */
    ASPECT_RATIO_THREE_BY_FOUR,

    /** 4:3 aspect ratio. */
    ASPECT_RATIO_FOUR_BY_THREE,

    /** 4:5 aspect ratio. */
    ASPECT_RATIO_FOUR_BY_FIVE,

    /** 5:4 aspect ratio. */
    ASPECT_RATIO_FIVE_BY_FOUR,

    /** 9:16 aspect ratio. */
    ASPECT_RATIO_NINE_BY_SIXTEEN,

    /** 16:9 aspect ratio. */
    ASPECT_RATIO_SIXTEEN_BY_NINE,

    /** 21:9 aspect ratio. */
    ASPECT_RATIO_TWENTY_ONE_BY_NINE,

    /** 1:8 aspect ratio. */
    ASPECT_RATIO_ONE_BY_EIGHT,

    /** 8:1 aspect ratio. */
    ASPECT_RATIO_EIGHT_BY_ONE,

    /** 1:4 aspect ratio. */
    ASPECT_RATIO_ONE_BY_FOUR,

    /** 4:1 aspect ratio. */
    ASPECT_RATIO_FOUR_BY_ONE
  }

  private Known aspectRatioEnum;
  private final String value;

  @JsonCreator
  public AspectRatio(String value) {
    this.value = value;
    for (Known aspectRatioEnum : Known.values()) {
      if (Ascii.equalsIgnoreCase(aspectRatioEnum.toString(), value)) {
        this.aspectRatioEnum = aspectRatioEnum;
        break;
      }
    }
    if (this.aspectRatioEnum == null) {
      this.aspectRatioEnum = Known.ASPECT_RATIO_UNSPECIFIED;
    }
  }

  public AspectRatio(Known knownValue) {
    this.aspectRatioEnum = knownValue;
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

    if (!(o instanceof AspectRatio)) {
      return false;
    }

    AspectRatio other = (AspectRatio) o;

    if (this.aspectRatioEnum != Known.ASPECT_RATIO_UNSPECIFIED
        && other.aspectRatioEnum != Known.ASPECT_RATIO_UNSPECIFIED) {
      return this.aspectRatioEnum == other.aspectRatioEnum;
    } else if (this.aspectRatioEnum == Known.ASPECT_RATIO_UNSPECIFIED
        && other.aspectRatioEnum == Known.ASPECT_RATIO_UNSPECIFIED) {
      return this.value.equals(other.value);
    }
    return false;
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  public int hashCode() {
    if (this.aspectRatioEnum != Known.ASPECT_RATIO_UNSPECIFIED) {
      return this.aspectRatioEnum.hashCode();
    } else {
      return Objects.hashCode(this.value);
    }
  }

  @ExcludeFromGeneratedCoverageReport
  public Known knownEnum() {
    return this.aspectRatioEnum;
  }
}
