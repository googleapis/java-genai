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

/** Enum representing the Vertex embedding API to use. */
public class EmbeddingApiType {

  /** Enum representing the known values for EmbeddingApiType. */
  public enum Known {
    /** predict API endpoint (default) */
    PREDICT,

    /** embedContent API Endpoint */
    EMBED_CONTENT,

    EMBEDDING_API_TYPE_UNSPECIFIED
  }

  private Known embeddingApiTypeEnum;
  private final String value;

  @JsonCreator
  public EmbeddingApiType(String value) {
    this.value = value;
    for (Known embeddingApiTypeEnum : Known.values()) {
      if (Ascii.equalsIgnoreCase(embeddingApiTypeEnum.toString(), value)) {
        this.embeddingApiTypeEnum = embeddingApiTypeEnum;
        break;
      }
    }
    if (this.embeddingApiTypeEnum == null) {
      this.embeddingApiTypeEnum = Known.EMBEDDING_API_TYPE_UNSPECIFIED;
    }
  }

  public EmbeddingApiType(Known knownValue) {
    this.embeddingApiTypeEnum = knownValue;
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

    if (!(o instanceof EmbeddingApiType)) {
      return false;
    }

    EmbeddingApiType other = (EmbeddingApiType) o;

    if (this.embeddingApiTypeEnum != Known.EMBEDDING_API_TYPE_UNSPECIFIED
        && other.embeddingApiTypeEnum != Known.EMBEDDING_API_TYPE_UNSPECIFIED) {
      return this.embeddingApiTypeEnum == other.embeddingApiTypeEnum;
    } else if (this.embeddingApiTypeEnum == Known.EMBEDDING_API_TYPE_UNSPECIFIED
        && other.embeddingApiTypeEnum == Known.EMBEDDING_API_TYPE_UNSPECIFIED) {
      return this.value.equals(other.value);
    }
    return false;
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  public int hashCode() {
    if (this.embeddingApiTypeEnum != Known.EMBEDDING_API_TYPE_UNSPECIFIED) {
      return this.embeddingApiTypeEnum.hashCode();
    } else {
      return Objects.hashCode(this.value);
    }
  }

  @ExcludeFromGeneratedCoverageReport
  public Known knownEnum() {
    return this.embeddingApiTypeEnum;
  }
}
