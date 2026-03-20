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
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * The FileSearch tool that retrieves knowledge from Semantic Retrieval corpora. Files are imported
 * to Semantic Retrieval corpora using the ImportFile API. This data type is not supported in Vertex
 * AI.
 */
@AutoValue
@JsonDeserialize(builder = FileSearch.Builder.class)
public abstract class FileSearch extends JsonSerializable {
  /**
   * Required. The names of the file_search_stores to retrieve from. Example:
   * `fileSearchStores/my-file-search-store-123`
   */
  @JsonProperty("fileSearchStoreNames")
  public abstract Optional<List<String>> fileSearchStoreNames();

  /** Optional. The number of semantic retrieval chunks to retrieve. */
  @JsonProperty("topK")
  public abstract Optional<Integer> topK();

  /** Optional. Metadata filter to apply to the semantic retrieval documents and chunks. */
  @JsonProperty("metadataFilter")
  public abstract Optional<String> metadataFilter();

  /** Instantiates a builder for FileSearch. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_FileSearch.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for FileSearch. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `FileSearch.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_FileSearch.Builder();
    }

    /**
     * Setter for fileSearchStoreNames.
     *
     * <p>fileSearchStoreNames: Required. The names of the file_search_stores to retrieve from.
     * Example: `fileSearchStores/my-file-search-store-123`
     */
    @JsonProperty("fileSearchStoreNames")
    public abstract Builder fileSearchStoreNames(List<String> fileSearchStoreNames);

    /**
     * Setter for fileSearchStoreNames.
     *
     * <p>fileSearchStoreNames: Required. The names of the file_search_stores to retrieve from.
     * Example: `fileSearchStores/my-file-search-store-123`
     */
    @CanIgnoreReturnValue
    public Builder fileSearchStoreNames(String... fileSearchStoreNames) {
      return fileSearchStoreNames(Arrays.asList(fileSearchStoreNames));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder fileSearchStoreNames(Optional<List<String>> fileSearchStoreNames);

    /** Clears the value of fileSearchStoreNames field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearFileSearchStoreNames() {
      return fileSearchStoreNames(Optional.empty());
    }

    /**
     * Setter for topK.
     *
     * <p>topK: Optional. The number of semantic retrieval chunks to retrieve.
     */
    @JsonProperty("topK")
    public abstract Builder topK(Integer topK);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder topK(Optional<Integer> topK);

    /** Clears the value of topK field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTopK() {
      return topK(Optional.empty());
    }

    /**
     * Setter for metadataFilter.
     *
     * <p>metadataFilter: Optional. Metadata filter to apply to the semantic retrieval documents and
     * chunks.
     */
    @JsonProperty("metadataFilter")
    public abstract Builder metadataFilter(String metadataFilter);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder metadataFilter(Optional<String> metadataFilter);

    /** Clears the value of metadataFilter field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearMetadataFilter() {
      return metadataFilter(Optional.empty());
    }

    public abstract FileSearch build();
  }

  /** Deserializes a JSON string to a FileSearch object. */
  @ExcludeFromGeneratedCoverageReport
  public static FileSearch fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, FileSearch.class);
  }
}
