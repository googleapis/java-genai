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

/** The status of the URL retrieval. */
public class UrlRetrievalStatus {

  /** Enum representing the known values for UrlRetrievalStatus. */
  public enum Known {
    /** Default value. This value is unused. */
    URL_RETRIEVAL_STATUS_UNSPECIFIED,

    /** The URL was retrieved successfully. */
    URL_RETRIEVAL_STATUS_SUCCESS,

    /** The URL retrieval failed. */
    URL_RETRIEVAL_STATUS_ERROR,

    /**
     * Url retrieval is failed because the content is behind paywall. This enum value is not
     * supported in Vertex AI.
     */
    URL_RETRIEVAL_STATUS_PAYWALL,

    /**
     * Url retrieval is failed because the content is unsafe. This enum value is not supported in
     * Vertex AI.
     */
    URL_RETRIEVAL_STATUS_UNSAFE
  }

  private Known urlRetrievalStatusEnum;
  private final String value;

  @JsonCreator
  public UrlRetrievalStatus(String value) {
    this.value = value;
    for (Known urlRetrievalStatusEnum : Known.values()) {
      if (Ascii.equalsIgnoreCase(urlRetrievalStatusEnum.toString(), value)) {
        this.urlRetrievalStatusEnum = urlRetrievalStatusEnum;
        break;
      }
    }
    if (this.urlRetrievalStatusEnum == null) {
      this.urlRetrievalStatusEnum = Known.URL_RETRIEVAL_STATUS_UNSPECIFIED;
    }
  }

  public UrlRetrievalStatus(Known knownValue) {
    this.urlRetrievalStatusEnum = knownValue;
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

    if (!(o instanceof UrlRetrievalStatus)) {
      return false;
    }

    UrlRetrievalStatus other = (UrlRetrievalStatus) o;

    if (this.urlRetrievalStatusEnum != Known.URL_RETRIEVAL_STATUS_UNSPECIFIED
        && other.urlRetrievalStatusEnum != Known.URL_RETRIEVAL_STATUS_UNSPECIFIED) {
      return this.urlRetrievalStatusEnum == other.urlRetrievalStatusEnum;
    } else if (this.urlRetrievalStatusEnum == Known.URL_RETRIEVAL_STATUS_UNSPECIFIED
        && other.urlRetrievalStatusEnum == Known.URL_RETRIEVAL_STATUS_UNSPECIFIED) {
      return this.value.equals(other.value);
    }
    return false;
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  public int hashCode() {
    if (this.urlRetrievalStatusEnum != Known.URL_RETRIEVAL_STATUS_UNSPECIFIED) {
      return this.urlRetrievalStatusEnum.hashCode();
    } else {
      return Objects.hashCode(this.value);
    }
  }

  @ExcludeFromGeneratedCoverageReport
  public Known knownEnum() {
    return this.urlRetrievalStatusEnum;
  }
}
