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

/** The type of the voice activity signal. */
public class VoiceActivityType {

  /** Enum representing the known values for VoiceActivityType. */
  public enum Known {
    /** The default is VOICE_ACTIVITY_TYPE_UNSPECIFIED. */
    TYPE_UNSPECIFIED,

    /** Start of sentence signal. */
    ACTIVITY_START,

    /** End of sentence signal. */
    ACTIVITY_END,

    VOICE_ACTIVITY_TYPE_UNSPECIFIED
  }

  private Known voiceActivityTypeEnum;
  private final String value;

  @JsonCreator
  public VoiceActivityType(String value) {
    this.value = value;
    for (Known voiceActivityTypeEnum : Known.values()) {
      if (Ascii.equalsIgnoreCase(voiceActivityTypeEnum.toString(), value)) {
        this.voiceActivityTypeEnum = voiceActivityTypeEnum;
        break;
      }
    }
    if (this.voiceActivityTypeEnum == null) {
      this.voiceActivityTypeEnum = Known.VOICE_ACTIVITY_TYPE_UNSPECIFIED;
    }
  }

  public VoiceActivityType(Known knownValue) {
    this.voiceActivityTypeEnum = knownValue;
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

    if (!(o instanceof VoiceActivityType)) {
      return false;
    }

    VoiceActivityType other = (VoiceActivityType) o;

    if (this.voiceActivityTypeEnum != Known.VOICE_ACTIVITY_TYPE_UNSPECIFIED
        && other.voiceActivityTypeEnum != Known.VOICE_ACTIVITY_TYPE_UNSPECIFIED) {
      return this.voiceActivityTypeEnum == other.voiceActivityTypeEnum;
    } else if (this.voiceActivityTypeEnum == Known.VOICE_ACTIVITY_TYPE_UNSPECIFIED
        && other.voiceActivityTypeEnum == Known.VOICE_ACTIVITY_TYPE_UNSPECIFIED) {
      return this.value.equals(other.value);
    }
    return false;
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  public int hashCode() {
    if (this.voiceActivityTypeEnum != Known.VOICE_ACTIVITY_TYPE_UNSPECIFIED) {
      return this.voiceActivityTypeEnum.hashCode();
    } else {
      return Objects.hashCode(this.value);
    }
  }

  @ExcludeFromGeneratedCoverageReport
  public Known knownEnum() {
    return this.voiceActivityTypeEnum;
  }
}
