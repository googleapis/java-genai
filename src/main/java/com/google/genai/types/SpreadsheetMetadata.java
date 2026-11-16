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

import static com.google.common.collect.ImmutableList.toImmutableList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableList;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Metadata describes the input spreadsheet content.
 *
 * <p>Set this in `Part.spreadsheet_metadata` or `FunctionResponsePart.spreadsheet_metadata` when
 * `inline_data` or `file_data` contains a spreadsheet to configure static spreadsheet
 * preprocessing.
 */
@AutoValue
@JsonDeserialize(builder = SpreadsheetMetadata.Builder.class)
public abstract class SpreadsheetMetadata extends JsonSerializable {
  /**
   * The range of cells to extract, in A1 notation (e.g. "A1:D10"). If not set, the full occupied
   * area of the sheet is used.
   */
  @JsonProperty("visibleCells")
  public abstract Optional<String> visibleCells();

  /**
   * The 1-indexed index of the sheet to extract, in workbook order. If not set, the first sheet is
   * used.
   */
  @JsonProperty("sheetIndex")
  public abstract Optional<Integer> sheetIndex();

  /**
   * Content layers to return. If empty, defaults to [DATA, FORMULAS] when SpreadsheetMetadata is
   * set.
   */
  @JsonProperty("contentLayers")
  public abstract Optional<List<SpreadsheetMetadataContentLayer>> contentLayers();

  /** Instantiates a builder for SpreadsheetMetadata. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_SpreadsheetMetadata.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for SpreadsheetMetadata. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `SpreadsheetMetadata.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_SpreadsheetMetadata.Builder();
    }

    /**
     * Setter for visibleCells.
     *
     * <p>visibleCells: The range of cells to extract, in A1 notation (e.g. "A1:D10"). If not set,
     * the full occupied area of the sheet is used.
     */
    @JsonProperty("visibleCells")
    public abstract Builder visibleCells(String visibleCells);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder visibleCells(Optional<String> visibleCells);

    /** Clears the value of visibleCells field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearVisibleCells() {
      return visibleCells(Optional.empty());
    }

    /**
     * Setter for sheetIndex.
     *
     * <p>sheetIndex: The 1-indexed index of the sheet to extract, in workbook order. If not set,
     * the first sheet is used.
     */
    @JsonProperty("sheetIndex")
    public abstract Builder sheetIndex(Integer sheetIndex);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder sheetIndex(Optional<Integer> sheetIndex);

    /** Clears the value of sheetIndex field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearSheetIndex() {
      return sheetIndex(Optional.empty());
    }

    /**
     * Setter for contentLayers.
     *
     * <p>contentLayers: Content layers to return. If empty, defaults to [DATA, FORMULAS] when
     * SpreadsheetMetadata is set.
     */
    @JsonProperty("contentLayers")
    public abstract Builder contentLayers(List<SpreadsheetMetadataContentLayer> contentLayers);

    /**
     * Setter for contentLayers.
     *
     * <p>contentLayers: Content layers to return. If empty, defaults to [DATA, FORMULAS] when
     * SpreadsheetMetadata is set.
     */
    @CanIgnoreReturnValue
    public Builder contentLayers(SpreadsheetMetadataContentLayer... contentLayers) {
      return contentLayers(Arrays.asList(contentLayers));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder contentLayers(Optional<List<SpreadsheetMetadataContentLayer>> contentLayers);

    /** Clears the value of contentLayers field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearContentLayers() {
      return contentLayers(Optional.empty());
    }

    /**
     * Setter for contentLayers given a varargs of strings.
     *
     * <p>contentLayers: Content layers to return. If empty, defaults to [DATA, FORMULAS] when
     * SpreadsheetMetadata is set.
     */
    @CanIgnoreReturnValue
    public Builder contentLayers(String... contentLayers) {
      return contentLayersFromString(Arrays.asList(contentLayers));
    }

    /**
     * Setter for contentLayers given a varargs of known enums.
     *
     * <p>contentLayers: Content layers to return. If empty, defaults to [DATA, FORMULAS] when
     * SpreadsheetMetadata is set.
     */
    @CanIgnoreReturnValue
    public Builder contentLayers(SpreadsheetMetadataContentLayer.Known... knownTypes) {
      return contentLayersFromKnown(Arrays.asList(knownTypes));
    }

    /**
     * Setter for contentLayers given a list of known enums.
     *
     * <p>contentLayers: Content layers to return. If empty, defaults to [DATA, FORMULAS] when
     * SpreadsheetMetadata is set.
     */
    @CanIgnoreReturnValue
    public Builder contentLayersFromKnown(List<SpreadsheetMetadataContentLayer.Known> knownTypes) {
      ImmutableList<SpreadsheetMetadataContentLayer> listItems =
          knownTypes.stream().map(SpreadsheetMetadataContentLayer::new).collect(toImmutableList());
      return contentLayers(listItems);
    }

    /**
     * Setter for contentLayers given a list of strings.
     *
     * <p>contentLayers: Content layers to return. If empty, defaults to [DATA, FORMULAS] when
     * SpreadsheetMetadata is set.
     */
    @CanIgnoreReturnValue
    public Builder contentLayersFromString(List<String> contentLayers) {
      ImmutableList<SpreadsheetMetadataContentLayer> listItems =
          contentLayers.stream()
              .map(SpreadsheetMetadataContentLayer::new)
              .collect(toImmutableList());
      return contentLayers(listItems);
    }

    public abstract SpreadsheetMetadata build();
  }

  /** Deserializes a JSON string to a SpreadsheetMetadata object. */
  @ExcludeFromGeneratedCoverageReport
  public static SpreadsheetMetadata fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, SpreadsheetMetadata.class);
  }
}
