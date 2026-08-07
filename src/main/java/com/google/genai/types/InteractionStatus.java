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

/** The different activity states of the live session. */
public class InteractionStatus {

  /** Enum representing the known values for InteractionStatus. */
  public enum Known {
    /** Unspecified interaction status. */
    INTERACTION_STATUS_UNSPECIFIED,

    /**
     * The server is still actively processing user input or running background reasoning. More
     * model output may follow.
     */
    IN_PROGRESS,

    /** The server has completed all processing and background reasoning. */
    REQUIRES_ACTION
  }

  private Known interactionStatusEnum;
  private final String value;

  @JsonCreator
  public InteractionStatus(String value) {
    this.value = value;
    for (Known interactionStatusEnum : Known.values()) {
      if (Ascii.equalsIgnoreCase(interactionStatusEnum.toString(), value)) {
        this.interactionStatusEnum = interactionStatusEnum;
        break;
      }
    }
    if (this.interactionStatusEnum == null) {
      this.interactionStatusEnum = Known.INTERACTION_STATUS_UNSPECIFIED;
    }
  }

  public InteractionStatus(Known knownValue) {
    this.interactionStatusEnum = knownValue;
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

    if (!(o instanceof InteractionStatus)) {
      return false;
    }

    InteractionStatus other = (InteractionStatus) o;

    if (this.interactionStatusEnum != Known.INTERACTION_STATUS_UNSPECIFIED
        && other.interactionStatusEnum != Known.INTERACTION_STATUS_UNSPECIFIED) {
      return this.interactionStatusEnum == other.interactionStatusEnum;
    } else if (this.interactionStatusEnum == Known.INTERACTION_STATUS_UNSPECIFIED
        && other.interactionStatusEnum == Known.INTERACTION_STATUS_UNSPECIFIED) {
      return this.value.equals(other.value);
    }
    return false;
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  public int hashCode() {
    if (this.interactionStatusEnum != Known.INTERACTION_STATUS_UNSPECIFIED) {
      return this.interactionStatusEnum.hashCode();
    } else {
      return Objects.hashCode(this.value);
    }
  }

  @ExcludeFromGeneratedCoverageReport
  public Known knownEnum() {
    return this.interactionStatusEnum;
  }
}
