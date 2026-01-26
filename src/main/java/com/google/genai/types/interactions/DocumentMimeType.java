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
 * MIME type for document content.
 *
 * <p>Supports known document MIME types with extensibility for future formats.
 */
public class DocumentMimeType {

  /** Enum representing the known document MIME types. */
  public enum Known {
    /** Unspecified or unknown document MIME type. */
    DOCUMENT_MIME_TYPE_UNSPECIFIED("unspecified"),

    /** PDF document format. */
    APPLICATION_PDF("application/pdf");

    private final String value;

    Known(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return this.value;
    }
  }

  private Known mimeTypeEnum;
  private final String value;

  /**
   * Constructs a DocumentMimeType from a string value.
   *
   * @param value The MIME type string (e.g., "application/pdf")
   */
  @JsonCreator
  public DocumentMimeType(String value) {
    this.value = value;
    for (Known mimeTypeEnum : Known.values()) {
      if (Ascii.equalsIgnoreCase(mimeTypeEnum.toString(), value)) {
        this.mimeTypeEnum = mimeTypeEnum;
        break;
      }
    }
    if (this.mimeTypeEnum == null) {
      this.mimeTypeEnum = Known.DOCUMENT_MIME_TYPE_UNSPECIFIED;
    }
  }

  /**
   * Constructs a DocumentMimeType from a known enum value.
   *
   * @param knownValue The known MIME type
   */
  public DocumentMimeType(Known knownValue) {
    this.mimeTypeEnum = knownValue;
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

    if (!(o instanceof DocumentMimeType)) {
      return false;
    }

    DocumentMimeType other = (DocumentMimeType) o;

    if (this.mimeTypeEnum != Known.DOCUMENT_MIME_TYPE_UNSPECIFIED
        && other.mimeTypeEnum != Known.DOCUMENT_MIME_TYPE_UNSPECIFIED) {
      return this.mimeTypeEnum == other.mimeTypeEnum;
    } else if (this.mimeTypeEnum == Known.DOCUMENT_MIME_TYPE_UNSPECIFIED
        && other.mimeTypeEnum == Known.DOCUMENT_MIME_TYPE_UNSPECIFIED) {
      return this.value.equals(other.value);
    }
    return false;
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  public int hashCode() {
    if (this.mimeTypeEnum != Known.DOCUMENT_MIME_TYPE_UNSPECIFIED) {
      return this.mimeTypeEnum.hashCode();
    } else {
      return Objects.hashCode(this.value);
    }
  }

  /**
   * Returns the known enum value if this is a recognized MIME type.
   *
   * @return The known enum value
   */
  @ExcludeFromGeneratedCoverageReport
  public Known knownEnum() {
    return this.mimeTypeEnum;
  }
}
