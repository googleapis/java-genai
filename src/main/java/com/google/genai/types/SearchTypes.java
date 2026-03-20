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
import java.util.Optional;

/** Different types of search that can be enabled on the GoogleSearch tool. */
@AutoValue
@JsonDeserialize(builder = SearchTypes.Builder.class)
public abstract class SearchTypes extends JsonSerializable {
  /** Optional. Setting this field enables web search. Only text results are returned. */
  @JsonProperty("webSearch")
  public abstract Optional<WebSearch> webSearch();

  /** Optional. Setting this field enables image search. Image bytes are returned. */
  @JsonProperty("imageSearch")
  public abstract Optional<ImageSearch> imageSearch();

  /** Instantiates a builder for SearchTypes. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_SearchTypes.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for SearchTypes. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `SearchTypes.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_SearchTypes.Builder();
    }

    /**
     * Setter for webSearch.
     *
     * <p>webSearch: Optional. Setting this field enables web search. Only text results are
     * returned.
     */
    @JsonProperty("webSearch")
    public abstract Builder webSearch(WebSearch webSearch);

    /**
     * Setter for webSearch builder.
     *
     * <p>webSearch: Optional. Setting this field enables web search. Only text results are
     * returned.
     */
    @CanIgnoreReturnValue
    public Builder webSearch(WebSearch.Builder webSearchBuilder) {
      return webSearch(webSearchBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder webSearch(Optional<WebSearch> webSearch);

    /** Clears the value of webSearch field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearWebSearch() {
      return webSearch(Optional.empty());
    }

    /**
     * Setter for imageSearch.
     *
     * <p>imageSearch: Optional. Setting this field enables image search. Image bytes are returned.
     */
    @JsonProperty("imageSearch")
    public abstract Builder imageSearch(ImageSearch imageSearch);

    /**
     * Setter for imageSearch builder.
     *
     * <p>imageSearch: Optional. Setting this field enables image search. Image bytes are returned.
     */
    @CanIgnoreReturnValue
    public Builder imageSearch(ImageSearch.Builder imageSearchBuilder) {
      return imageSearch(imageSearchBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder imageSearch(Optional<ImageSearch> imageSearch);

    /** Clears the value of imageSearch field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearImageSearch() {
      return imageSearch(Optional.empty());
    }

    public abstract SearchTypes build();
  }

  /** Deserializes a JSON string to a SearchTypes object. */
  @ExcludeFromGeneratedCoverageReport
  public static SearchTypes fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, SearchTypes.class);
  }
}
