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

package com.google.genai.types.interactions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.base.Ascii;
import com.google.genai.types.ExcludeFromGeneratedCoverageReport;
import java.util.Objects;

/**
 * The resolution of the media (images and videos).
 *
 * <p>Controls the quality/resolution of media content in interactions.
 *
 * <p>This class supports both known resolution values via the {@link Known} enum and unknown/custom
 * values via the string constructor. Known values are serialized to lowercase JSON strings (e.g.,
 * "low", "medium", "high", "ultra_high").
 *
 * <p>Example usage:
 *
 * <pre>{@code
 * // Using Known enum values
 * MediaResolution high = new MediaResolution(MediaResolution.Known.HIGH);
 * MediaResolution low = new MediaResolution(MediaResolution.Known.LOW);
 *
 * // Using string for unknown/custom values
 * MediaResolution custom = new MediaResolution("custom_resolution");
 * }</pre>
 */
public class MediaResolution {

  /** Enum representing the known values for MediaResolution. */
  public enum Known {
    /** Unspecified or unknown media resolution. */
    MEDIA_RESOLUTION_UNSPECIFIED("unspecified"),

    /** Low resolution. */
    LOW("low"),

    /** Medium resolution. */
    MEDIUM("medium"),

    /** High resolution. */
    HIGH("high"),

    /** Ultra high resolution. */
    ULTRA_HIGH("ultra_high");

    private final String value;

    Known(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return this.value;
    }
  }

  private Known resolutionEnum;
  private final String value;

  /**
   * Constructs a MediaResolution from a string value.
   *
   * @param value The resolution string (e.g., "high")
   */
  @JsonCreator
  public MediaResolution(String value) {
    this.value = value;
    for (Known resolutionEnum : Known.values()) {
      if (Ascii.equalsIgnoreCase(resolutionEnum.toString(), value)) {
        this.resolutionEnum = resolutionEnum;
        break;
      }
    }
    if (this.resolutionEnum == null) {
      this.resolutionEnum = Known.MEDIA_RESOLUTION_UNSPECIFIED;
    }
  }

  /**
   * Constructs a MediaResolution from a known enum value.
   *
   * @param knownValue The known resolution value
   */
  public MediaResolution(Known knownValue) {
    this.resolutionEnum = knownValue;
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

    if (!(o instanceof MediaResolution)) {
      return false;
    }

    MediaResolution other = (MediaResolution) o;

    if (this.resolutionEnum != Known.MEDIA_RESOLUTION_UNSPECIFIED
        && other.resolutionEnum != Known.MEDIA_RESOLUTION_UNSPECIFIED) {
      return this.resolutionEnum == other.resolutionEnum;
    } else if (this.resolutionEnum == Known.MEDIA_RESOLUTION_UNSPECIFIED
        && other.resolutionEnum == Known.MEDIA_RESOLUTION_UNSPECIFIED) {
      return this.value.equals(other.value);
    }
    return false;
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  public int hashCode() {
    if (this.resolutionEnum != Known.MEDIA_RESOLUTION_UNSPECIFIED) {
      return this.resolutionEnum.hashCode();
    } else {
      return Objects.hashCode(this.value);
    }
  }

  /**
   * Returns the known enum value if this is a recognized resolution.
   *
   * @return The known enum value
   */
  @ExcludeFromGeneratedCoverageReport
  public Known knownEnum() {
    return this.resolutionEnum;
  }
}
