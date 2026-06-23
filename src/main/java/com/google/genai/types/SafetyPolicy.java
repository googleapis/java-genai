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

/** Disabled safety policies for computer use. */
public class SafetyPolicy {

  /** Enum representing the known values for SafetyPolicy. */
  public enum Known {
    /** Unspecified safety policy. This value should not be used. */
    SAFETY_POLICY_UNSPECIFIED,

    /** Financial transactions safety policy. */
    FINANCIAL_TRANSACTIONS,

    /** Sensitive data modification safety policy. */
    SENSITIVE_DATA_MODIFICATION,

    /** Communication tool safety policy. */
    COMMUNICATION_TOOL,

    /** Account creation safety policy. */
    ACCOUNT_CREATION,

    /** Data modification safety policy. */
    DATA_MODIFICATION,

    /** User consent management safety policy. */
    USER_CONSENT_MANAGEMENT,

    /** Legal terms and agreements safety policy. */
    LEGAL_TERMS_AND_AGREEMENTS
  }

  private Known safetyPolicyEnum;
  private final String value;

  @JsonCreator
  public SafetyPolicy(String value) {
    this.value = value;
    for (Known safetyPolicyEnum : Known.values()) {
      if (Ascii.equalsIgnoreCase(safetyPolicyEnum.toString(), value)) {
        this.safetyPolicyEnum = safetyPolicyEnum;
        break;
      }
    }
    if (this.safetyPolicyEnum == null) {
      this.safetyPolicyEnum = Known.SAFETY_POLICY_UNSPECIFIED;
    }
  }

  public SafetyPolicy(Known knownValue) {
    this.safetyPolicyEnum = knownValue;
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

    if (!(o instanceof SafetyPolicy)) {
      return false;
    }

    SafetyPolicy other = (SafetyPolicy) o;

    if (this.safetyPolicyEnum != Known.SAFETY_POLICY_UNSPECIFIED
        && other.safetyPolicyEnum != Known.SAFETY_POLICY_UNSPECIFIED) {
      return this.safetyPolicyEnum == other.safetyPolicyEnum;
    } else if (this.safetyPolicyEnum == Known.SAFETY_POLICY_UNSPECIFIED
        && other.safetyPolicyEnum == Known.SAFETY_POLICY_UNSPECIFIED) {
      return this.value.equals(other.value);
    }
    return false;
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  public int hashCode() {
    if (this.safetyPolicyEnum != Known.SAFETY_POLICY_UNSPECIFIED) {
      return this.safetyPolicyEnum.hashCode();
    } else {
      return Objects.hashCode(this.value);
    }
  }

  @ExcludeFromGeneratedCoverageReport
  public Known knownEnum() {
    return this.safetyPolicyEnum;
  }
}
