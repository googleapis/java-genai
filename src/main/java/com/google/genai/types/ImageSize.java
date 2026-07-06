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

/** The size of the image output. */
public class ImageSize {

  /** Enum representing the known values for ImageSize. */
  public enum Known {
    /** Default value. This value is unused. */
    IMAGE_SIZE_UNSPECIFIED,

    /** 512px image size. */
    IMAGE_SIZE_FIVE_TWELVE,

    /** 1K image size. */
    IMAGE_SIZE_ONE_K,

    /** 2K image size. */
    IMAGE_SIZE_TWO_K,

    /** 4K image size. */
    IMAGE_SIZE_FOUR_K
  }

  private Known imageSizeEnum;
  private final String value;

  @JsonCreator
  public ImageSize(String value) {
    this.value = value;
    for (Known imageSizeEnum : Known.values()) {
      if (Ascii.equalsIgnoreCase(imageSizeEnum.toString(), value)) {
        this.imageSizeEnum = imageSizeEnum;
        break;
      }
    }
    if (this.imageSizeEnum == null) {
      this.imageSizeEnum = Known.IMAGE_SIZE_UNSPECIFIED;
    }
  }

  public ImageSize(Known knownValue) {
    this.imageSizeEnum = knownValue;
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

    if (!(o instanceof ImageSize)) {
      return false;
    }

    ImageSize other = (ImageSize) o;

    if (this.imageSizeEnum != Known.IMAGE_SIZE_UNSPECIFIED
        && other.imageSizeEnum != Known.IMAGE_SIZE_UNSPECIFIED) {
      return this.imageSizeEnum == other.imageSizeEnum;
    } else if (this.imageSizeEnum == Known.IMAGE_SIZE_UNSPECIFIED
        && other.imageSizeEnum == Known.IMAGE_SIZE_UNSPECIFIED) {
      return this.value.equals(other.value);
    }
    return false;
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  public int hashCode() {
    if (this.imageSizeEnum != Known.IMAGE_SIZE_UNSPECIFIED) {
      return this.imageSizeEnum.hashCode();
    } else {
      return Objects.hashCode(this.value);
    }
  }

  @ExcludeFromGeneratedCoverageReport
  public Known knownEnum() {
    return this.imageSizeEnum;
  }
}
