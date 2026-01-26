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
 * MIME type for video content.
 *
 * <p>Supports known video MIME types with extensibility for future formats.
 */
public class VideoMimeType {

  /** Enum representing the known video MIME types. */
  public enum Known {
    /** Unspecified or unknown video MIME type. */
    VIDEO_MIME_TYPE_UNSPECIFIED("unspecified"),

    /** MP4 video format. */
    VIDEO_MP4("video/mp4"),

    /** MPEG video format. */
    VIDEO_MPEG("video/mpeg"),

    /** MOV video format. */
    VIDEO_MOV("video/mov"),

    /** AVI video format. */
    VIDEO_AVI("video/avi"),

    /** FLV video format. */
    VIDEO_X_FLV("video/x-flv"),

    /** MPG video format. */
    VIDEO_MPG("video/mpg"),

    /** WebM video format. */
    VIDEO_WEBM("video/webm"),

    /** WMV video format. */
    VIDEO_WMV("video/wmv"),

    /** 3GPP video format. */
    VIDEO_3GPP("video/3gpp");

    private final String value;

    Known(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return this.value;
    }
  }

  private Known mimeTypeEnum;
  private final String value;

  /**
   * Constructs a VideoMimeType from a string value.
   *
   * @param value The MIME type string (e.g., "video/mp4")
   */
  @JsonCreator
  public VideoMimeType(String value) {
    this.value = value;
    for (Known mimeTypeEnum : Known.values()) {
      if (Ascii.equalsIgnoreCase(mimeTypeEnum.toString(), value)) {
        this.mimeTypeEnum = mimeTypeEnum;
        break;
      }
    }
    if (this.mimeTypeEnum == null) {
      this.mimeTypeEnum = Known.VIDEO_MIME_TYPE_UNSPECIFIED;
    }
  }

  /**
   * Constructs a VideoMimeType from a known enum value.
   *
   * @param knownValue The known MIME type
   */
  public VideoMimeType(Known knownValue) {
    this.mimeTypeEnum = knownValue;
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

    if (!(o instanceof VideoMimeType)) {
      return false;
    }

    VideoMimeType other = (VideoMimeType) o;

    if (this.mimeTypeEnum != Known.VIDEO_MIME_TYPE_UNSPECIFIED
        && other.mimeTypeEnum != Known.VIDEO_MIME_TYPE_UNSPECIFIED) {
      return this.mimeTypeEnum == other.mimeTypeEnum;
    } else if (this.mimeTypeEnum == Known.VIDEO_MIME_TYPE_UNSPECIFIED
        && other.mimeTypeEnum == Known.VIDEO_MIME_TYPE_UNSPECIFIED) {
      return this.value.equals(other.value);
    }
    return false;
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  public int hashCode() {
    if (this.mimeTypeEnum != Known.VIDEO_MIME_TYPE_UNSPECIFIED) {
      return this.mimeTypeEnum.hashCode();
    } else {
      return Objects.hashCode(this.value);
    }
  }

  /**
   * Returns the known enum value if this is a recognized MIME type.
   *
   * @return The known enum value
   */
  @ExcludeFromGeneratedCoverageReport
  public Known knownEnum() {
    return this.mimeTypeEnum;
  }
}
