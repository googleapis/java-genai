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
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/** Config for file_search_stores.list return value. */
@AutoValue
@JsonDeserialize(builder = ListFileSearchStoresResponse.Builder.class)
public abstract class ListFileSearchStoresResponse extends JsonSerializable {
  /** Used to retain the full HTTP response. */
  @JsonProperty("sdkHttpResponse")
  public abstract Optional<HttpResponse> sdkHttpResponse();

  /**
   * A token, which can be sent as `page_token` to retrieve the next page. If this field is omitted,
   * there are no more pages.
   */
  @JsonProperty("nextPageToken")
  public abstract Optional<String> nextPageToken();

  /** The returned file search stores. */
  @JsonProperty("fileSearchStores")
  public abstract Optional<List<FileSearchStore>> fileSearchStores();

  /** Instantiates a builder for ListFileSearchStoresResponse. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ListFileSearchStoresResponse.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ListFileSearchStoresResponse. */
  @AutoValue.Builder
  public abstract static class Builder {
    /**
     * For internal usage. Please use `ListFileSearchStoresResponse.builder()` for instantiation.
     */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ListFileSearchStoresResponse.Builder();
    }

    /**
     * Setter for sdkHttpResponse.
     *
     * <p>sdkHttpResponse: Used to retain the full HTTP response.
     */
    @JsonProperty("sdkHttpResponse")
    public abstract Builder sdkHttpResponse(HttpResponse sdkHttpResponse);

    /**
     * Setter for sdkHttpResponse builder.
     *
     * <p>sdkHttpResponse: Used to retain the full HTTP response.
     */
    @CanIgnoreReturnValue
    public Builder sdkHttpResponse(HttpResponse.Builder sdkHttpResponseBuilder) {
      return sdkHttpResponse(sdkHttpResponseBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder sdkHttpResponse(Optional<HttpResponse> sdkHttpResponse);

    /** Clears the value of sdkHttpResponse field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearSdkHttpResponse() {
      return sdkHttpResponse(Optional.empty());
    }

    /**
     * Setter for nextPageToken.
     *
     * <p>nextPageToken: A token, which can be sent as `page_token` to retrieve the next page. If
     * this field is omitted, there are no more pages.
     */
    @JsonProperty("nextPageToken")
    public abstract Builder nextPageToken(String nextPageToken);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder nextPageToken(Optional<String> nextPageToken);

    /** Clears the value of nextPageToken field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearNextPageToken() {
      return nextPageToken(Optional.empty());
    }

    /**
     * Setter for fileSearchStores.
     *
     * <p>fileSearchStores: The returned file search stores.
     */
    @JsonProperty("fileSearchStores")
    public abstract Builder fileSearchStores(List<FileSearchStore> fileSearchStores);

    /**
     * Setter for fileSearchStores.
     *
     * <p>fileSearchStores: The returned file search stores.
     */
    @CanIgnoreReturnValue
    public Builder fileSearchStores(FileSearchStore... fileSearchStores) {
      return fileSearchStores(Arrays.asList(fileSearchStores));
    }

    /**
     * Setter for fileSearchStores builder.
     *
     * <p>fileSearchStores: The returned file search stores.
     */
    @CanIgnoreReturnValue
    public Builder fileSearchStores(FileSearchStore.Builder... fileSearchStoresBuilders) {
      return fileSearchStores(
          Arrays.asList(fileSearchStoresBuilders).stream()
              .map(FileSearchStore.Builder::build)
              .collect(toImmutableList()));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder fileSearchStores(Optional<List<FileSearchStore>> fileSearchStores);

    /** Clears the value of fileSearchStores field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearFileSearchStores() {
      return fileSearchStores(Optional.empty());
    }

    public abstract ListFileSearchStoresResponse build();
  }

  /** Deserializes a JSON string to a ListFileSearchStoresResponse object. */
  @ExcludeFromGeneratedCoverageReport
  public static ListFileSearchStoresResponse fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ListFileSearchStoresResponse.class);
  }
}
