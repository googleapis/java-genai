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

/** Optional. Adapter size for tuning. */
public class AdapterSize {

  /** Enum representing the known values for AdapterSize. */
  public enum Known {
    /** Adapter size is unspecified. */
    ADAPTER_SIZE_UNSPECIFIED,

    /** Adapter size 1. */
    ADAPTER_SIZE_ONE,

    /** Adapter size 2. */
    ADAPTER_SIZE_TWO,

    /** Adapter size 4. */
    ADAPTER_SIZE_FOUR,

    /** Adapter size 8. */
    ADAPTER_SIZE_EIGHT,

    /** Adapter size 16. */
    ADAPTER_SIZE_SIXTEEN,

    /** Adapter size 32. */
    ADAPTER_SIZE_THIRTY_TWO
  }

  private Known adapterSizeEnum;
  private final String value;

  @JsonCreator
  public AdapterSize(String value) {
    this.value = value;
    for (Known adapterSizeEnum : Known.values()) {
      if (Ascii.equalsIgnoreCase(adapterSizeEnum.toString(), value)) {
        this.adapterSizeEnum = adapterSizeEnum;
        break;
      }
    }
    if (this.adapterSizeEnum == null) {
      this.adapterSizeEnum = Known.ADAPTER_SIZE_UNSPECIFIED;
    }
  }

  public AdapterSize(Known knownValue) {
    this.adapterSizeEnum = knownValue;
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

    if (!(o instanceof AdapterSize)) {
      return false;
    }

    AdapterSize other = (AdapterSize) o;

    if (this.adapterSizeEnum != Known.ADAPTER_SIZE_UNSPECIFIED
        && other.adapterSizeEnum != Known.ADAPTER_SIZE_UNSPECIFIED) {
      return this.adapterSizeEnum == other.adapterSizeEnum;
    } else if (this.adapterSizeEnum == Known.ADAPTER_SIZE_UNSPECIFIED
        && other.adapterSizeEnum == Known.ADAPTER_SIZE_UNSPECIFIED) {
      return this.value.equals(other.value);
    }
    return false;
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  public int hashCode() {
    if (this.adapterSizeEnum != Known.ADAPTER_SIZE_UNSPECIFIED) {
      return this.adapterSizeEnum.hashCode();
    } else {
      return Objects.hashCode(this.value);
    }
  }

  @ExcludeFromGeneratedCoverageReport
  public Known knownEnum() {
    return this.adapterSizeEnum;
  }
}

