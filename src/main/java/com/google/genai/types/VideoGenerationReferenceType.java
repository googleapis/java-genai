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

/** Enum for the reference type of a video generation reference image. */
public class VideoGenerationReferenceType {

  /** Enum representing the known values for VideoGenerationReferenceType. */
  public enum Known {
    /**
     * A reference image that provides assets to the generated video, such as the scene, an object,
     * a character, etc.
     */
    ASSET,

    /**
     * A reference image that provides aesthetics including colors, lighting, texture, etc., to be
     * used as the style of the generated video, such as 'anime', 'photography', 'origami', etc.
     */
    STYLE,

    VIDEO_GENERATION_REFERENCE_TYPE_UNSPECIFIED
  }

  private Known videoGenerationReferenceTypeEnum;
  private final String value;

  @JsonCreator
  public VideoGenerationReferenceType(String value) {
    this.value = value;
    for (Known videoGenerationReferenceTypeEnum : Known.values()) {
      if (Ascii.equalsIgnoreCase(videoGenerationReferenceTypeEnum.toString(), value)) {
        this.videoGenerationReferenceTypeEnum = videoGenerationReferenceTypeEnum;
        break;
      }
    }
    if (this.videoGenerationReferenceTypeEnum == null) {
      this.videoGenerationReferenceTypeEnum = Known.VIDEO_GENERATION_REFERENCE_TYPE_UNSPECIFIED;
    }
  }

  public VideoGenerationReferenceType(Known knownValue) {
    this.videoGenerationReferenceTypeEnum = knownValue;
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

    if (!(o instanceof VideoGenerationReferenceType)) {
      return false;
    }

    VideoGenerationReferenceType other = (VideoGenerationReferenceType) o;

    if (this.videoGenerationReferenceTypeEnum != Known.VIDEO_GENERATION_REFERENCE_TYPE_UNSPECIFIED
        && other.videoGenerationReferenceTypeEnum
            != Known.VIDEO_GENERATION_REFERENCE_TYPE_UNSPECIFIED) {
      return this.videoGenerationReferenceTypeEnum == other.videoGenerationReferenceTypeEnum;
    } else if (this.videoGenerationReferenceTypeEnum
            == Known.VIDEO_GENERATION_REFERENCE_TYPE_UNSPECIFIED
        && other.videoGenerationReferenceTypeEnum
            == Known.VIDEO_GENERATION_REFERENCE_TYPE_UNSPECIFIED) {
      return this.value.equals(other.value);
    }
    return false;
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  public int hashCode() {
    if (this.videoGenerationReferenceTypeEnum
        != Known.VIDEO_GENERATION_REFERENCE_TYPE_UNSPECIFIED) {
      return this.videoGenerationReferenceTypeEnum.hashCode();
    } else {
      return Objects.hashCode(this.value);
    }
  }

  @ExcludeFromGeneratedCoverageReport
  public Known knownEnum() {
    return this.videoGenerationReferenceTypeEnum;
  }
}
