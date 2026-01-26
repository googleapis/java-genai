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

package com.google.genai.types.interactions.tools;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import com.google.genai.types.ExcludeFromGeneratedCoverageReport;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * File search tool for the Interactions API.
 *
 * <p>Enables the model to search through file stores.
 *
 * <p>Example usage:
 *
 * <pre>{@code
 * FileSearch fileSearchTool = FileSearch.builder()
 *     .fileSearchStoreNames("my-store-1", "my-store-2")
 *     .topK(10)
 *     .build();
 * }</pre>
 *
 * <p>The Interactions API is available in both Vertex AI and Gemini API.
 *
 * <p>Note: The Interactions API is in beta and subject to change.
 */
@AutoValue
@JsonDeserialize(builder = FileSearch.Builder.class)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonTypeName("file_search")
public abstract class FileSearch extends JsonSerializable implements Tool {

  /** The names of the file search stores to search. */
  @JsonProperty("file_search_store_names")
  public abstract Optional<List<String>> fileSearchStoreNames();

  /** The maximum number of results to return. */
  @JsonProperty("top_k")
  public abstract Optional<Integer> topK();

  /** Optional metadata filter for the search. */
  @JsonProperty("metadata_filter")
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
    /** For internal usage. Please use {@code FileSearch.builder()} for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_FileSearch.Builder();
    }

    /**
     * Setter for fileSearchStoreNames.
     *
     * <p>fileSearchStoreNames: The names of the file search stores to search.
     */
    @JsonProperty("file_search_store_names")
    public abstract Builder fileSearchStoreNames(List<String> fileSearchStoreNames);

    /**
     * Setter for fileSearchStoreNames (varargs convenience method).
     *
     * <p>fileSearchStoreNames: The names of the file search stores to search.
     */
    @CanIgnoreReturnValue
    public Builder fileSearchStoreNames(String... fileSearchStoreNames) {
      return fileSearchStoreNames(Arrays.asList(fileSearchStoreNames));
    }

    abstract Builder fileSearchStoreNames(Optional<List<String>> fileSearchStoreNames);

    /** Clears the value of fileSearchStoreNames field. */
    @CanIgnoreReturnValue
    public Builder clearFileSearchStoreNames() {
      return fileSearchStoreNames(Optional.empty());
    }

    /**
     * Setter for topK.
     *
     * <p>topK: The maximum number of results to return.
     */
    @JsonProperty("top_k")
    public abstract Builder topK(Integer topK);

    abstract Builder topK(Optional<Integer> topK);

    /** Clears the value of topK field. */
    @CanIgnoreReturnValue
    public Builder clearTopK() {
      return topK(Optional.empty());
    }

    /**
     * Setter for metadataFilter.
     *
     * <p>metadataFilter: Optional metadata filter for the search.
     */
    @JsonProperty("metadata_filter")
    public abstract Builder metadataFilter(String metadataFilter);

    abstract Builder metadataFilter(Optional<String> metadataFilter);

    /** Clears the value of metadataFilter field. */
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
