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
 * Represents the status of an interaction.
 *
 * <p>This class follows the standard enum wrapper pattern used throughout the codebase. The {@code
 * Known} enum contains all recognized values from the API specification, and {@code
 * INTERACTION_STATUS_UNSPECIFIED} serves as a fallback for forward compatibility.
 *
 * <p>Example usage:
 *
 * <pre>{@code
 * // Create from known enum value
 * InteractionStatus status = new InteractionStatus(InteractionStatus.Known.COMPLETED);
 *
 * // Check status
 * if (status.knownEnum() == InteractionStatus.Known.IN_PROGRESS) {
 *     // Handle in-progress interaction
 * }
 * }</pre>
 */
public class InteractionStatus {

  /**
   * Enum representing the known values for InteractionStatus.
   *
   * <p>These values represent the various states an interaction can be in.
   */
  public enum Known {
    /**
     * Unspecified interaction status.
     *
     * <p>This is the fallback value used when the API returns an unknown status not recognized by
     * this version of the SDK. This ensures forward compatibility when new statuses are added to
     * the API.
     */
    INTERACTION_STATUS_UNSPECIFIED("unspecified"),

    /** The interaction is currently in progress. */
    IN_PROGRESS("in_progress"),

    /** The interaction requires user action to continue. */
    REQUIRES_ACTION("requires_action"),

    /** The interaction has completed successfully. */
    COMPLETED("completed"),

    /** The interaction has failed. */
    FAILED("failed"),

    /** The interaction has been cancelled. */
    CANCELLED("cancelled");

    private final String value;

    Known(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return this.value;
    }
  }

  private Known statusEnum;
  private final String value;

  /**
   * Creates an InteractionStatus from a string value.
   *
   * <p>This constructor is used by Jackson during JSON deserialization. It attempts to match the
   * provided string value to a known enum value (case-insensitive). If no match is found, it falls
   * back to {@code INTERACTION_STATUS_UNSPECIFIED}.
   *
   * @param value the string representation of the interaction status
   */
  @JsonCreator
  public InteractionStatus(String value) {
    this.value = value;
    for (Known statusEnum : Known.values()) {
      if (Ascii.equalsIgnoreCase(statusEnum.toString(), value)) {
        this.statusEnum = statusEnum;
        break;
      }
    }
    if (this.statusEnum == null) {
      this.statusEnum = Known.INTERACTION_STATUS_UNSPECIFIED;
    }
  }

  /**
   * Creates an InteractionStatus from a known enum value.
   *
   * <p>This is the recommended constructor for creating InteractionStatus instances in application
   * code.
   *
   * @param knownValue the known interaction status enum value
   */
  public InteractionStatus(Known knownValue) {
    this.statusEnum = knownValue;
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

    if (this.statusEnum != Known.INTERACTION_STATUS_UNSPECIFIED
        && other.statusEnum != Known.INTERACTION_STATUS_UNSPECIFIED) {
      return this.statusEnum == other.statusEnum;
    } else if (this.statusEnum == Known.INTERACTION_STATUS_UNSPECIFIED
        && other.statusEnum == Known.INTERACTION_STATUS_UNSPECIFIED) {
      return this.value.equals(other.value);
    }
    return false;
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  public int hashCode() {
    if (this.statusEnum != Known.INTERACTION_STATUS_UNSPECIFIED) {
      return this.statusEnum.hashCode();
    } else {
      return Objects.hashCode(this.value);
    }
  }

  /**
   * Returns the known enum value for this interaction status.
   *
   * <p>If the value was not recognized during deserialization, this will return {@code
   * INTERACTION_STATUS_UNSPECIFIED}.
   *
   * @return the known enum value
   */
  @ExcludeFromGeneratedCoverageReport
  public Known knownEnum() {
    return this.statusEnum;
  }
}
