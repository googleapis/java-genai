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

/** Content layers to extract from a video. */
public class VideoMetadataContentLayer {

  /** Enum representing the known values for VideoMetadataContentLayer. */
  public enum Known {
    /** Default value. */
    CONTENT_LAYER_UNSPECIFIED,

    /** Extract video frames. */
    FRAMES,

    /** Extract audio track. */
    AUDIO,

    /** Extract audio transcript. */
    TRANSCRIPT,

    VIDEO_METADATA_CONTENT_LAYER_UNSPECIFIED
  }

  private Known videoMetadataContentLayerEnum;
  private final String value;

  @JsonCreator
  public VideoMetadataContentLayer(String value) {
    this.value = value;
    for (Known videoMetadataContentLayerEnum : Known.values()) {
      if (Ascii.equalsIgnoreCase(videoMetadataContentLayerEnum.toString(), value)) {
        this.videoMetadataContentLayerEnum = videoMetadataContentLayerEnum;
        break;
      }
    }
    if (this.videoMetadataContentLayerEnum == null) {
      this.videoMetadataContentLayerEnum = Known.VIDEO_METADATA_CONTENT_LAYER_UNSPECIFIED;
    }
  }

  public VideoMetadataContentLayer(Known knownValue) {
    this.videoMetadataContentLayerEnum = knownValue;
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

    if (!(o instanceof VideoMetadataContentLayer)) {
      return false;
    }

    VideoMetadataContentLayer other = (VideoMetadataContentLayer) o;

    if (this.videoMetadataContentLayerEnum != Known.VIDEO_METADATA_CONTENT_LAYER_UNSPECIFIED
        && other.videoMetadataContentLayerEnum != Known.VIDEO_METADATA_CONTENT_LAYER_UNSPECIFIED) {
      return this.videoMetadataContentLayerEnum == other.videoMetadataContentLayerEnum;
    } else if (this.videoMetadataContentLayerEnum == Known.VIDEO_METADATA_CONTENT_LAYER_UNSPECIFIED
        && other.videoMetadataContentLayerEnum == Known.VIDEO_METADATA_CONTENT_LAYER_UNSPECIFIED) {
      return this.value.equals(other.value);
    }
    return false;
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  public int hashCode() {
    if (this.videoMetadataContentLayerEnum != Known.VIDEO_METADATA_CONTENT_LAYER_UNSPECIFIED) {
      return this.videoMetadataContentLayerEnum.hashCode();
    } else {
      return Objects.hashCode(this.value);
    }
  }

  @ExcludeFromGeneratedCoverageReport
  public Known knownEnum() {
    return this.videoMetadataContentLayerEnum;
  }
}
