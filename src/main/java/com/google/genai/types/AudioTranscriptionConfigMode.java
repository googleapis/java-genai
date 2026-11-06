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
 * Configures transcription mode. Supported values: `VERBATIM`, `SMART`. If unspecified, defaults to
 * `VERBATIM` transcription. In `SMART` mode, the model performs disfluency removal (eliminating
 * filler words, repetitions, and false starts), light grammatical cleanup, automatic formatting
 * (paragraphs, bullet points, numbered lists), and minor user edits (inline self-corrections).
 * Timestamps and diarization are incompatible with mode `SMART`.
 */
public class AudioTranscriptionConfigMode {

  /** Enum representing the known values for AudioTranscriptionConfigMode. */
  public enum Known {
    /** Unspecified transcription mode. */
    MODE_UNSPECIFIED,

    /** Verbatim transcription mode. */
    VERBATIM,

    /** Smart transcription mode. */
    SMART,

    AUDIO_TRANSCRIPTION_CONFIG_MODE_UNSPECIFIED
  }

  private Known audioTranscriptionConfigModeEnum;
  private final String value;

  @JsonCreator
  public AudioTranscriptionConfigMode(String value) {
    this.value = value;
    for (Known audioTranscriptionConfigModeEnum : Known.values()) {
      if (Ascii.equalsIgnoreCase(audioTranscriptionConfigModeEnum.toString(), value)) {
        this.audioTranscriptionConfigModeEnum = audioTranscriptionConfigModeEnum;
        break;
      }
    }
    if (this.audioTranscriptionConfigModeEnum == null) {
      this.audioTranscriptionConfigModeEnum = Known.AUDIO_TRANSCRIPTION_CONFIG_MODE_UNSPECIFIED;
    }
  }

  public AudioTranscriptionConfigMode(Known knownValue) {
    this.audioTranscriptionConfigModeEnum = knownValue;
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

    if (!(o instanceof AudioTranscriptionConfigMode)) {
      return false;
    }

    AudioTranscriptionConfigMode other = (AudioTranscriptionConfigMode) o;

    if (this.audioTranscriptionConfigModeEnum != Known.AUDIO_TRANSCRIPTION_CONFIG_MODE_UNSPECIFIED
        && other.audioTranscriptionConfigModeEnum
            != Known.AUDIO_TRANSCRIPTION_CONFIG_MODE_UNSPECIFIED) {
      return this.audioTranscriptionConfigModeEnum == other.audioTranscriptionConfigModeEnum;
    } else if (this.audioTranscriptionConfigModeEnum
            == Known.AUDIO_TRANSCRIPTION_CONFIG_MODE_UNSPECIFIED
        && other.audioTranscriptionConfigModeEnum
            == Known.AUDIO_TRANSCRIPTION_CONFIG_MODE_UNSPECIFIED) {
      return this.value.equals(other.value);
    }
    return false;
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  public int hashCode() {
    if (this.audioTranscriptionConfigModeEnum
        != Known.AUDIO_TRANSCRIPTION_CONFIG_MODE_UNSPECIFIED) {
      return this.audioTranscriptionConfigModeEnum.hashCode();
    } else {
      return Objects.hashCode(this.value);
    }
  }

  @ExcludeFromGeneratedCoverageReport
  public Known knownEnum() {
    return this.audioTranscriptionConfigModeEnum;
  }
}
