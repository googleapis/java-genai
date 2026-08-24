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

/** How the model processes input media for understanding. */
public class MediaProcessing {

  /** Enum representing the known values for MediaProcessing. */
  public enum Known {
    /** Default. Uses model-specific processing */
    MEDIA_PROCESSING_UNSPECIFIED,

    /** Fixed-rate frame extraction. All frames placed in context. */
    STATIC,

    /** Model-driven dynamic navigation. Recommended for most use cases. */
    AGENTIC
  }

  private Known mediaProcessingEnum;
  private final String value;

  @JsonCreator
  public MediaProcessing(String value) {
    this.value = value;
    for (Known mediaProcessingEnum : Known.values()) {
      if (Ascii.equalsIgnoreCase(mediaProcessingEnum.toString(), value)) {
        this.mediaProcessingEnum = mediaProcessingEnum;
        break;
      }
    }
    if (this.mediaProcessingEnum == null) {
      this.mediaProcessingEnum = Known.MEDIA_PROCESSING_UNSPECIFIED;
    }
  }

  public MediaProcessing(Known knownValue) {
    this.mediaProcessingEnum = knownValue;
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

    if (!(o instanceof MediaProcessing)) {
      return false;
    }

    MediaProcessing other = (MediaProcessing) o;

    if (this.mediaProcessingEnum != Known.MEDIA_PROCESSING_UNSPECIFIED
        && other.mediaProcessingEnum != Known.MEDIA_PROCESSING_UNSPECIFIED) {
      return this.mediaProcessingEnum == other.mediaProcessingEnum;
    } else if (this.mediaProcessingEnum == Known.MEDIA_PROCESSING_UNSPECIFIED
        && other.mediaProcessingEnum == Known.MEDIA_PROCESSING_UNSPECIFIED) {
      return this.value.equals(other.value);
    }
    return false;
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  public int hashCode() {
    if (this.mediaProcessingEnum != Known.MEDIA_PROCESSING_UNSPECIFIED) {
      return this.mediaProcessingEnum.hashCode();
    } else {
      return Objects.hashCode(this.value);
    }
  }

  @ExcludeFromGeneratedCoverageReport
  public Known knownEnum() {
    return this.mediaProcessingEnum;
  }
}
