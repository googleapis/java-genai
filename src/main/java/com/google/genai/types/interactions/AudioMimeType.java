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
 * MIME type for audio content.
 *
 * <p>Supports known audio MIME types with extensibility for future formats.
 */
public class AudioMimeType {

  /** Enum representing the known audio MIME types. */
  public enum Known {
    /** Unspecified or unknown audio MIME type. */
    AUDIO_MIME_TYPE_UNSPECIFIED("unspecified"),

    /** WAV audio format. */
    AUDIO_WAV("audio/wav"),

    /** MP3 audio format. */
    AUDIO_MP3("audio/mp3"),

    /** AIFF audio format. */
    AUDIO_AIFF("audio/aiff"),

    /** AAC audio format. */
    AUDIO_AAC("audio/aac"),

    /** OGG audio format. */
    AUDIO_OGG("audio/ogg"),

    /** FLAC audio format. */
    AUDIO_FLAC("audio/flac");

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
   * Constructs an AudioMimeType from a string value.
   *
   * @param value The MIME type string (e.g., "audio/mp3")
   */
  @JsonCreator
  public AudioMimeType(String value) {
    this.value = value;
    for (Known mimeTypeEnum : Known.values()) {
      if (Ascii.equalsIgnoreCase(mimeTypeEnum.toString(), value)) {
        this.mimeTypeEnum = mimeTypeEnum;
        break;
      }
    }
    if (this.mimeTypeEnum == null) {
      this.mimeTypeEnum = Known.AUDIO_MIME_TYPE_UNSPECIFIED;
    }
  }

  /**
   * Constructs an AudioMimeType from a known enum value.
   *
   * @param knownValue The known MIME type
   */
  public AudioMimeType(Known knownValue) {
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

    if (!(o instanceof AudioMimeType)) {
      return false;
    }

    AudioMimeType other = (AudioMimeType) o;

    if (this.mimeTypeEnum != Known.AUDIO_MIME_TYPE_UNSPECIFIED
        && other.mimeTypeEnum != Known.AUDIO_MIME_TYPE_UNSPECIFIED) {
      return this.mimeTypeEnum == other.mimeTypeEnum;
    } else if (this.mimeTypeEnum == Known.AUDIO_MIME_TYPE_UNSPECIFIED
        && other.mimeTypeEnum == Known.AUDIO_MIME_TYPE_UNSPECIFIED) {
      return this.value.equals(other.value);
    }
    return false;
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  public int hashCode() {
    if (this.mimeTypeEnum != Known.AUDIO_MIME_TYPE_UNSPECIFIED) {
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
