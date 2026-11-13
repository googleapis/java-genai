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

/** Content layers to extract from a document. */
public class DocumentMetadataContentLayer {

  /** Enum representing the known values for DocumentMetadataContentLayer. */
  public enum Known {
    /** Default value. */
    CONTENT_LAYER_UNSPECIFIED,

    /** Rendered page images. */
    PAGE_IMAGE,

    /** Extracted page text. */
    PAGE_TEXT,

    DOCUMENT_METADATA_CONTENT_LAYER_UNSPECIFIED
  }

  private Known documentMetadataContentLayerEnum;
  private final String value;

  @JsonCreator
  public DocumentMetadataContentLayer(String value) {
    this.value = value;
    for (Known documentMetadataContentLayerEnum : Known.values()) {
      if (Ascii.equalsIgnoreCase(documentMetadataContentLayerEnum.toString(), value)) {
        this.documentMetadataContentLayerEnum = documentMetadataContentLayerEnum;
        break;
      }
    }
    if (this.documentMetadataContentLayerEnum == null) {
      this.documentMetadataContentLayerEnum = Known.DOCUMENT_METADATA_CONTENT_LAYER_UNSPECIFIED;
    }
  }

  public DocumentMetadataContentLayer(Known knownValue) {
    this.documentMetadataContentLayerEnum = knownValue;
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

    if (!(o instanceof DocumentMetadataContentLayer)) {
      return false;
    }

    DocumentMetadataContentLayer other = (DocumentMetadataContentLayer) o;

    if (this.documentMetadataContentLayerEnum != Known.DOCUMENT_METADATA_CONTENT_LAYER_UNSPECIFIED
        && other.documentMetadataContentLayerEnum
            != Known.DOCUMENT_METADATA_CONTENT_LAYER_UNSPECIFIED) {
      return this.documentMetadataContentLayerEnum == other.documentMetadataContentLayerEnum;
    } else if (this.documentMetadataContentLayerEnum
            == Known.DOCUMENT_METADATA_CONTENT_LAYER_UNSPECIFIED
        && other.documentMetadataContentLayerEnum
            == Known.DOCUMENT_METADATA_CONTENT_LAYER_UNSPECIFIED) {
      return this.value.equals(other.value);
    }
    return false;
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  public int hashCode() {
    if (this.documentMetadataContentLayerEnum
        != Known.DOCUMENT_METADATA_CONTENT_LAYER_UNSPECIFIED) {
      return this.documentMetadataContentLayerEnum.hashCode();
    } else {
      return Objects.hashCode(this.value);
    }
  }

  @ExcludeFromGeneratedCoverageReport
  public Known knownEnum() {
    return this.documentMetadataContentLayerEnum;
  }
}
