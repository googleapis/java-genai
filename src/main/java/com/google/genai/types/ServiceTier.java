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

/** Pricing and performance service tier. */
public class ServiceTier {

  /** Enum representing the known values for ServiceTier. */
  public enum Known {
    /** Default service tier, which is standard. */
    SERVICE_TIER_UNSPECIFIED,

    /** Flex service tier. */
    SERVICE_TIER_FLEX,

    /** Standard service tier. */
    SERVICE_TIER_STANDARD,

    /** Priority service tier. */
    SERVICE_TIER_PRIORITY
  }

  private Known serviceTierEnum;
  private final String value;

  @JsonCreator
  public ServiceTier(String value) {
    this.value = value;
    for (Known serviceTierEnum : Known.values()) {
      if (Ascii.equalsIgnoreCase(serviceTierEnum.toString(), value)) {
        this.serviceTierEnum = serviceTierEnum;
        break;
      }
    }
    if (this.serviceTierEnum == null) {
      this.serviceTierEnum = Known.SERVICE_TIER_UNSPECIFIED;
    }
  }

  public ServiceTier(Known knownValue) {
    this.serviceTierEnum = knownValue;
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

    if (!(o instanceof ServiceTier)) {
      return false;
    }

    ServiceTier other = (ServiceTier) o;

    if (this.serviceTierEnum != Known.SERVICE_TIER_UNSPECIFIED
        && other.serviceTierEnum != Known.SERVICE_TIER_UNSPECIFIED) {
      return this.serviceTierEnum == other.serviceTierEnum;
    } else if (this.serviceTierEnum == Known.SERVICE_TIER_UNSPECIFIED
        && other.serviceTierEnum == Known.SERVICE_TIER_UNSPECIFIED) {
      return this.value.equals(other.value);
    }
    return false;
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  public int hashCode() {
    if (this.serviceTierEnum != Known.SERVICE_TIER_UNSPECIFIED) {
      return this.serviceTierEnum.hashCode();
    } else {
      return Objects.hashCode(this.value);
    }
  }

  @ExcludeFromGeneratedCoverageReport
  public Known knownEnum() {
    return this.serviceTierEnum;
  }
}
