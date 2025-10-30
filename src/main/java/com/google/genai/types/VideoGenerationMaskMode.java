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

/** Enum for the mask mode of a video generation mask. */
public class VideoGenerationMaskMode {

  /** Enum representing the known values for VideoGenerationMaskMode. */
  public enum Known {
    /**
     * The image mask contains a masked rectangular region which is applied on the first frame of
     * the input video. The object described in the prompt is inserted into this region and will
     * appear in subsequent frames.
     */
    INSERT,

    /**
     * The image mask is used to determine an object in the first video frame to track. This object
     * is removed from the video.
     */
    REMOVE,

    /**
     * The image mask is used to determine a region in the video. Objects in this region will be
     * removed.
     */
    REMOVE_STATIC,

    /**
     * The image mask contains a masked rectangular region where the input video will go. The
     * remaining area will be generated. Video masks are not supported.
     */
    OUTPAINT,

    VIDEO_GENERATION_MASK_MODE_UNSPECIFIED
  }

  private Known videoGenerationMaskModeEnum;
  private final String value;

  @JsonCreator
  public VideoGenerationMaskMode(String value) {
    this.value = value;
    for (Known videoGenerationMaskModeEnum : Known.values()) {
      if (Ascii.equalsIgnoreCase(videoGenerationMaskModeEnum.toString(), value)) {
        this.videoGenerationMaskModeEnum = videoGenerationMaskModeEnum;
        break;
      }
    }
    if (this.videoGenerationMaskModeEnum == null) {
      this.videoGenerationMaskModeEnum = Known.VIDEO_GENERATION_MASK_MODE_UNSPECIFIED;
    }
  }

  public VideoGenerationMaskMode(Known knownValue) {
    this.videoGenerationMaskModeEnum = knownValue;
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

    if (!(o instanceof VideoGenerationMaskMode)) {
      return false;
    }

    VideoGenerationMaskMode other = (VideoGenerationMaskMode) o;

    if (this.videoGenerationMaskModeEnum != Known.VIDEO_GENERATION_MASK_MODE_UNSPECIFIED
        && other.videoGenerationMaskModeEnum != Known.VIDEO_GENERATION_MASK_MODE_UNSPECIFIED) {
      return this.videoGenerationMaskModeEnum == other.videoGenerationMaskModeEnum;
    } else if (this.videoGenerationMaskModeEnum == Known.VIDEO_GENERATION_MASK_MODE_UNSPECIFIED
        && other.videoGenerationMaskModeEnum == Known.VIDEO_GENERATION_MASK_MODE_UNSPECIFIED) {
      return this.value.equals(other.value);
    }
    return false;
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  public int hashCode() {
    if (this.videoGenerationMaskModeEnum != Known.VIDEO_GENERATION_MASK_MODE_UNSPECIFIED) {
      return this.videoGenerationMaskModeEnum.hashCode();
    } else {
      return Objects.hashCode(this.value);
    }
  }

  @ExcludeFromGeneratedCoverageReport
  public Known knownEnum() {
    return this.videoGenerationMaskModeEnum;
  }
}
