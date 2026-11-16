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
 * Metadata describes the input document content.
 *
 * <p>Set this in `Part.document_metadata` or `FunctionResponsePart.document_metadata` when
 * `inline_data` or `file_data` contains a document to configure static document preprocessing.
 */
@AutoValue
@JsonDeserialize(builder = DocumentMetadata.Builder.class)
public abstract class DocumentMetadata extends JsonSerializable {
  /** 1-indexed inclusive start page. */
  @JsonProperty("startPage")
  public abstract Optional<Integer> startPage();

  /** 1-indexed inclusive end page. */
  @JsonProperty("endPage")
  public abstract Optional<Integer> endPage();

  /**
   * Content layers to return. If empty, defaults to [PAGE_IMAGE] when DocumentMetadata is set. When
   * DocumentMetadata is not set, default document processing is used (for example, page images for
   * PDFs).
   */
  @JsonProperty("contentLayers")
  public abstract Optional<List<DocumentMetadataContentLayer>> contentLayers();

  /** Instantiates a builder for DocumentMetadata. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_DocumentMetadata.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for DocumentMetadata. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `DocumentMetadata.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_DocumentMetadata.Builder();
    }

    /**
     * Setter for startPage.
     *
     * <p>startPage: 1-indexed inclusive start page.
     */
    @JsonProperty("startPage")
    public abstract Builder startPage(Integer startPage);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder startPage(Optional<Integer> startPage);

    /** Clears the value of startPage field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearStartPage() {
      return startPage(Optional.empty());
    }

    /**
     * Setter for endPage.
     *
     * <p>endPage: 1-indexed inclusive end page.
     */
    @JsonProperty("endPage")
    public abstract Builder endPage(Integer endPage);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder endPage(Optional<Integer> endPage);

    /** Clears the value of endPage field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearEndPage() {
      return endPage(Optional.empty());
    }

    /**
     * Setter for contentLayers.
     *
     * <p>contentLayers: Content layers to return. If empty, defaults to [PAGE_IMAGE] when
     * DocumentMetadata is set. When DocumentMetadata is not set, default document processing is
     * used (for example, page images for PDFs).
     */
    @JsonProperty("contentLayers")
    public abstract Builder contentLayers(List<DocumentMetadataContentLayer> contentLayers);

    /**
     * Setter for contentLayers.
     *
     * <p>contentLayers: Content layers to return. If empty, defaults to [PAGE_IMAGE] when
     * DocumentMetadata is set. When DocumentMetadata is not set, default document processing is
     * used (for example, page images for PDFs).
     */
    @CanIgnoreReturnValue
    public Builder contentLayers(DocumentMetadataContentLayer... contentLayers) {
      return contentLayers(Arrays.asList(contentLayers));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder contentLayers(Optional<List<DocumentMetadataContentLayer>> contentLayers);

    /** Clears the value of contentLayers field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearContentLayers() {
      return contentLayers(Optional.empty());
    }

    /**
     * Setter for contentLayers given a varargs of strings.
     *
     * <p>contentLayers: Content layers to return. If empty, defaults to [PAGE_IMAGE] when
     * DocumentMetadata is set. When DocumentMetadata is not set, default document processing is
     * used (for example, page images for PDFs).
     */
    @CanIgnoreReturnValue
    public Builder contentLayers(String... contentLayers) {
      return contentLayersFromString(Arrays.asList(contentLayers));
    }

    /**
     * Setter for contentLayers given a varargs of known enums.
     *
     * <p>contentLayers: Content layers to return. If empty, defaults to [PAGE_IMAGE] when
     * DocumentMetadata is set. When DocumentMetadata is not set, default document processing is
     * used (for example, page images for PDFs).
     */
    @CanIgnoreReturnValue
    public Builder contentLayers(DocumentMetadataContentLayer.Known... knownTypes) {
      return contentLayersFromKnown(Arrays.asList(knownTypes));
    }

    /**
     * Setter for contentLayers given a list of known enums.
     *
     * <p>contentLayers: Content layers to return. If empty, defaults to [PAGE_IMAGE] when
     * DocumentMetadata is set. When DocumentMetadata is not set, default document processing is
     * used (for example, page images for PDFs).
     */
    @CanIgnoreReturnValue
    public Builder contentLayersFromKnown(List<DocumentMetadataContentLayer.Known> knownTypes) {
      ImmutableList<DocumentMetadataContentLayer> listItems =
          knownTypes.stream().map(DocumentMetadataContentLayer::new).collect(toImmutableList());
      return contentLayers(listItems);
    }

    /**
     * Setter for contentLayers given a list of strings.
     *
     * <p>contentLayers: Content layers to return. If empty, defaults to [PAGE_IMAGE] when
     * DocumentMetadata is set. When DocumentMetadata is not set, default document processing is
     * used (for example, page images for PDFs).
     */
    @CanIgnoreReturnValue
    public Builder contentLayersFromString(List<String> contentLayers) {
      ImmutableList<DocumentMetadataContentLayer> listItems =
          contentLayers.stream().map(DocumentMetadataContentLayer::new).collect(toImmutableList());
      return contentLayers(listItems);
    }

    public abstract DocumentMetadata build();
  }

  /** Deserializes a JSON string to a DocumentMetadata object. */
  @ExcludeFromGeneratedCoverageReport
  public static DocumentMetadata fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, DocumentMetadata.class);
  }
}
