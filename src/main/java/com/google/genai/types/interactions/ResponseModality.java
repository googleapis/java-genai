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
 * The modality of the response content.
 *
 * <p>Controls the type of content that the model can return in the response.
 *
 * <p>This class supports both known modality values via the {@link Known} enum and unknown/custom
 * values via the string constructor. Known values are serialized to lowercase JSON strings (e.g.,
 * "text", "image", "audio").
 *
 * <p>Example usage:
 *
 * <pre>{@code
 * // Using Known enum values
 * ResponseModality text = new ResponseModality(ResponseModality.Known.TEXT);
 * ResponseModality image = new ResponseModality(ResponseModality.Known.IMAGE);
 *
 * // Using string for unknown/custom values
 * ResponseModality custom = new ResponseModality("custom_modality");
 * }</pre>
 */
public class ResponseModality {

  /** Enum representing the known values for ResponseModality. */
  public enum Known {
    /** Unspecified or unknown response modality. */
    RESPONSE_MODALITY_UNSPECIFIED("unspecified"),

    /** Text content. */
    TEXT("text"),

    /** Image content. */
    IMAGE("image"),

    /** Audio content. */
    AUDIO("audio");

    private final String value;

    Known(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return this.value;
    }
  }

  private Known modalityEnum;
  private final String value;

  /**
   * Constructs a ResponseModality from a string value.
   *
   * @param value The modality string (e.g., "text")
   */
  @JsonCreator
  public ResponseModality(String value) {
    this.value = value;
    for (Known modalityEnum : Known.values()) {
      if (Ascii.equalsIgnoreCase(modalityEnum.toString(), value)) {
        this.modalityEnum = modalityEnum;
        break;
      }
    }
    if (this.modalityEnum == null) {
      this.modalityEnum = Known.RESPONSE_MODALITY_UNSPECIFIED;
    }
  }

  /**
   * Constructs a ResponseModality from a known enum value.
   *
   * @param knownValue The known modality value
   */
  public ResponseModality(Known knownValue) {
    this.modalityEnum = knownValue;
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

    if (!(o instanceof ResponseModality)) {
      return false;
    }

    ResponseModality other = (ResponseModality) o;

    if (this.modalityEnum != Known.RESPONSE_MODALITY_UNSPECIFIED
        && other.modalityEnum != Known.RESPONSE_MODALITY_UNSPECIFIED) {
      return this.modalityEnum == other.modalityEnum;
    } else if (this.modalityEnum == Known.RESPONSE_MODALITY_UNSPECIFIED
        && other.modalityEnum == Known.RESPONSE_MODALITY_UNSPECIFIED) {
      return this.value.equals(other.value);
    }
    return false;
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  public int hashCode() {
    if (this.modalityEnum != Known.RESPONSE_MODALITY_UNSPECIFIED) {
      return this.modalityEnum.hashCode();
    } else {
      return Objects.hashCode(this.value);
    }
  }

  /**
   * Returns the known enum value if this is a recognized modality.
   *
   * @return The known enum value
   */
  @ExcludeFromGeneratedCoverageReport
  public Known knownEnum() {
    return this.modalityEnum;
  }
}
