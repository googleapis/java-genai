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

/** The type of the VAD signal. */
public class VadSignalType {

  /** Enum representing the known values for VadSignalType. */
  public enum Known {
    /** The default is VAD_SIGNAL_TYPE_UNSPECIFIED. */
    VAD_SIGNAL_TYPE_UNSPECIFIED,

    /** Start of sentence signal. */
    VAD_SIGNAL_TYPE_SOS,

    /** End of sentence signal. */
    VAD_SIGNAL_TYPE_EOS
  }

  private Known vadSignalTypeEnum;
  private final String value;

  @JsonCreator
  public VadSignalType(String value) {
    this.value = value;
    for (Known vadSignalTypeEnum : Known.values()) {
      if (Ascii.equalsIgnoreCase(vadSignalTypeEnum.toString(), value)) {
        this.vadSignalTypeEnum = vadSignalTypeEnum;
        break;
      }
    }
    if (this.vadSignalTypeEnum == null) {
      this.vadSignalTypeEnum = Known.VAD_SIGNAL_TYPE_UNSPECIFIED;
    }
  }

  public VadSignalType(Known knownValue) {
    this.vadSignalTypeEnum = knownValue;
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

    if (!(o instanceof VadSignalType)) {
      return false;
    }

    VadSignalType other = (VadSignalType) o;

    if (this.vadSignalTypeEnum != Known.VAD_SIGNAL_TYPE_UNSPECIFIED
        && other.vadSignalTypeEnum != Known.VAD_SIGNAL_TYPE_UNSPECIFIED) {
      return this.vadSignalTypeEnum == other.vadSignalTypeEnum;
    } else if (this.vadSignalTypeEnum == Known.VAD_SIGNAL_TYPE_UNSPECIFIED
        && other.vadSignalTypeEnum == Known.VAD_SIGNAL_TYPE_UNSPECIFIED) {
      return this.value.equals(other.value);
    }
    return false;
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  public int hashCode() {
    if (this.vadSignalTypeEnum != Known.VAD_SIGNAL_TYPE_UNSPECIFIED) {
      return this.vadSignalTypeEnum.hashCode();
    } else {
      return Objects.hashCode(this.value);
    }
  }

  @ExcludeFromGeneratedCoverageReport
  public Known knownEnum() {
    return this.vadSignalTypeEnum;
  }
}
