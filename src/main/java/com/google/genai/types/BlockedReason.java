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

/** Output only. The reason why the prompt was blocked. */
public class BlockedReason {

  /** Enum representing the known values for BlockedReason. */
  public enum Known {
    /** The blocked reason is unspecified. */
    BLOCKED_REASON_UNSPECIFIED,

    /** The prompt was blocked for safety reasons. */
    SAFETY,

    /**
     * The prompt was blocked for other reasons. For example, it may be due to the prompt's
     * language, or because it contains other harmful content.
     */
    OTHER,

    /** The prompt was blocked because it contains a term from the terminology blocklist. */
    BLOCKLIST,

    /** The prompt was blocked because it contains prohibited content. */
    PROHIBITED_CONTENT,

    /** The prompt was blocked because it contains content that is unsafe for image generation. */
    IMAGE_SAFETY,

    /** The prompt was blocked by Model Armor. This enum value is not supported in Gemini API. */
    MODEL_ARMOR,

    /**
     * The prompt was blocked as a jailbreak attempt. This enum value is not supported in Gemini
     * API.
     */
    JAILBREAK
  }

  private Known blockedReasonEnum;
  private final String value;

  @JsonCreator
  public BlockedReason(String value) {
    this.value = value;
    for (Known blockedReasonEnum : Known.values()) {
      if (Ascii.equalsIgnoreCase(blockedReasonEnum.toString(), value)) {
        this.blockedReasonEnum = blockedReasonEnum;
        break;
      }
    }
    if (this.blockedReasonEnum == null) {
      this.blockedReasonEnum = Known.BLOCKED_REASON_UNSPECIFIED;
    }
  }

  public BlockedReason(Known knownValue) {
    this.blockedReasonEnum = knownValue;
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

    if (!(o instanceof BlockedReason)) {
      return false;
    }

    BlockedReason other = (BlockedReason) o;

    if (this.blockedReasonEnum != Known.BLOCKED_REASON_UNSPECIFIED
        && other.blockedReasonEnum != Known.BLOCKED_REASON_UNSPECIFIED) {
      return this.blockedReasonEnum == other.blockedReasonEnum;
    } else if (this.blockedReasonEnum == Known.BLOCKED_REASON_UNSPECIFIED
        && other.blockedReasonEnum == Known.BLOCKED_REASON_UNSPECIFIED) {
      return this.value.equals(other.value);
    }
    return false;
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  public int hashCode() {
    if (this.blockedReasonEnum != Known.BLOCKED_REASON_UNSPECIFIED) {
      return this.blockedReasonEnum.hashCode();
    } else {
      return Objects.hashCode(this.value);
    }
  }

  @ExcludeFromGeneratedCoverageReport
  public Known knownEnum() {
    return this.blockedReasonEnum;
  }
}
