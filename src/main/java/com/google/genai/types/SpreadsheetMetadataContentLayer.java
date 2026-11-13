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

/** Content layers to extract from a spreadsheet. */
public class SpreadsheetMetadataContentLayer {

  /** Enum representing the known values for SpreadsheetMetadataContentLayer. */
  public enum Known {
    /** Default value. */
    CONTENT_LAYER_UNSPECIFIED,

    /** Cell values, merges, and formats. */
    DATA,

    /** Formula expressions and their computed results. */
    FORMULAS,

    /** A rendered image of the sheet. */
    VISUAL,

    SPREADSHEET_METADATA_CONTENT_LAYER_UNSPECIFIED
  }

  private Known spreadsheetMetadataContentLayerEnum;
  private final String value;

  @JsonCreator
  public SpreadsheetMetadataContentLayer(String value) {
    this.value = value;
    for (Known spreadsheetMetadataContentLayerEnum : Known.values()) {
      if (Ascii.equalsIgnoreCase(spreadsheetMetadataContentLayerEnum.toString(), value)) {
        this.spreadsheetMetadataContentLayerEnum = spreadsheetMetadataContentLayerEnum;
        break;
      }
    }
    if (this.spreadsheetMetadataContentLayerEnum == null) {
      this.spreadsheetMetadataContentLayerEnum =
          Known.SPREADSHEET_METADATA_CONTENT_LAYER_UNSPECIFIED;
    }
  }

  public SpreadsheetMetadataContentLayer(Known knownValue) {
    this.spreadsheetMetadataContentLayerEnum = knownValue;
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

    if (!(o instanceof SpreadsheetMetadataContentLayer)) {
      return false;
    }

    SpreadsheetMetadataContentLayer other = (SpreadsheetMetadataContentLayer) o;

    if (this.spreadsheetMetadataContentLayerEnum
            != Known.SPREADSHEET_METADATA_CONTENT_LAYER_UNSPECIFIED
        && other.spreadsheetMetadataContentLayerEnum
            != Known.SPREADSHEET_METADATA_CONTENT_LAYER_UNSPECIFIED) {
      return this.spreadsheetMetadataContentLayerEnum == other.spreadsheetMetadataContentLayerEnum;
    } else if (this.spreadsheetMetadataContentLayerEnum
            == Known.SPREADSHEET_METADATA_CONTENT_LAYER_UNSPECIFIED
        && other.spreadsheetMetadataContentLayerEnum
            == Known.SPREADSHEET_METADATA_CONTENT_LAYER_UNSPECIFIED) {
      return this.value.equals(other.value);
    }
    return false;
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  public int hashCode() {
    if (this.spreadsheetMetadataContentLayerEnum
        != Known.SPREADSHEET_METADATA_CONTENT_LAYER_UNSPECIFIED) {
      return this.spreadsheetMetadataContentLayerEnum.hashCode();
    } else {
      return Objects.hashCode(this.value);
    }
  }

  @ExcludeFromGeneratedCoverageReport
  public Known knownEnum() {
    return this.spreadsheetMetadataContentLayerEnum;
  }
}
