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
 * The orientation of the video. Defaults to LANDSCAPE. This enum is not supported in Gemini API.
 */
public class VideoOrientation {

  /** Enum representing the known values for VideoOrientation. */
  public enum Known {
    /** Unspecified video orientation. Defaults to landscape. */
    VIDEO_ORIENTATION_UNSPECIFIED,

    /** Landscape orientation (e.g. 16:9, 1280x720). */
    LANDSCAPE,

    /** Portrait orientation (e.g. 9:16, 720x1280). */
    PORTRAIT
  }

  private Known videoOrientationEnum;
  private final String value;

  @JsonCreator
  public VideoOrientation(String value) {
    this.value = value;
    for (Known videoOrientationEnum : Known.values()) {
      if (Ascii.equalsIgnoreCase(videoOrientationEnum.toString(), value)) {
        this.videoOrientationEnum = videoOrientationEnum;
        break;
      }
    }
    if (this.videoOrientationEnum == null) {
      this.videoOrientationEnum = Known.VIDEO_ORIENTATION_UNSPECIFIED;
    }
  }

  public VideoOrientation(Known knownValue) {
    this.videoOrientationEnum = knownValue;
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

    if (!(o instanceof VideoOrientation)) {
      return false;
    }

    VideoOrientation other = (VideoOrientation) o;

    if (this.videoOrientationEnum != Known.VIDEO_ORIENTATION_UNSPECIFIED
        && other.videoOrientationEnum != Known.VIDEO_ORIENTATION_UNSPECIFIED) {
      return this.videoOrientationEnum == other.videoOrientationEnum;
    } else if (this.videoOrientationEnum == Known.VIDEO_ORIENTATION_UNSPECIFIED
        && other.videoOrientationEnum == Known.VIDEO_ORIENTATION_UNSPECIFIED) {
      return this.value.equals(other.value);
    }
    return false;
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  public int hashCode() {
    if (this.videoOrientationEnum != Known.VIDEO_ORIENTATION_UNSPECIFIED) {
      return this.videoOrientationEnum.hashCode();
    } else {
      return Objects.hashCode(this.value);
    }
  }

  @ExcludeFromGeneratedCoverageReport
  public Known knownEnum() {
    return this.videoOrientationEnum;
  }
}
