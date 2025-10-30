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

/** Enum that controls the compression quality of the generated videos. */
public class VideoCompressionQuality {

  /** Enum representing the known values for VideoCompressionQuality. */
  public enum Known {
    /**
     * Optimized video compression quality. This will produce videos with a compressed, smaller file
     * size.
     */
    OPTIMIZED,

    /** Lossless video compression quality. This will produce videos with a larger file size. */
    LOSSLESS,

    VIDEO_COMPRESSION_QUALITY_UNSPECIFIED
  }

  private Known videoCompressionQualityEnum;
  private final String value;

  @JsonCreator
  public VideoCompressionQuality(String value) {
    this.value = value;
    for (Known videoCompressionQualityEnum : Known.values()) {
      if (Ascii.equalsIgnoreCase(videoCompressionQualityEnum.toString(), value)) {
        this.videoCompressionQualityEnum = videoCompressionQualityEnum;
        break;
      }
    }
    if (this.videoCompressionQualityEnum == null) {
      this.videoCompressionQualityEnum = Known.VIDEO_COMPRESSION_QUALITY_UNSPECIFIED;
    }
  }

  public VideoCompressionQuality(Known knownValue) {
    this.videoCompressionQualityEnum = knownValue;
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

    if (!(o instanceof VideoCompressionQuality)) {
      return false;
    }

    VideoCompressionQuality other = (VideoCompressionQuality) o;

    if (this.videoCompressionQualityEnum != Known.VIDEO_COMPRESSION_QUALITY_UNSPECIFIED
        && other.videoCompressionQualityEnum != Known.VIDEO_COMPRESSION_QUALITY_UNSPECIFIED) {
      return this.videoCompressionQualityEnum == other.videoCompressionQualityEnum;
    } else if (this.videoCompressionQualityEnum == Known.VIDEO_COMPRESSION_QUALITY_UNSPECIFIED
        && other.videoCompressionQualityEnum == Known.VIDEO_COMPRESSION_QUALITY_UNSPECIFIED) {
      return this.value.equals(other.value);
    }
    return false;
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  public int hashCode() {
    if (this.videoCompressionQualityEnum != Known.VIDEO_COMPRESSION_QUALITY_UNSPECIFIED) {
      return this.videoCompressionQualityEnum.hashCode();
    } else {
      return Objects.hashCode(this.value);
    }
  }

  @ExcludeFromGeneratedCoverageReport
  public Known knownEnum() {
    return this.videoCompressionQualityEnum;
  }
}
