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

/** None */
@AutoValue
@JsonDeserialize(builder = ListCachedContentsResponse.Builder.class)
public abstract class ListCachedContentsResponse extends JsonSerializable {
  /** Used to retain the full HTTP response. */
  @JsonProperty("sdkHttpResponse")
  public abstract Optional<HttpResponse> sdkHttpResponse();

  /** */
  @JsonProperty("nextPageToken")
  public abstract Optional<String> nextPageToken();

  /** List of cached contents. */
  @JsonProperty("cachedContents")
  public abstract Optional<List<CachedContent>> cachedContents();

  /** Instantiates a builder for ListCachedContentsResponse. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ListCachedContentsResponse.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ListCachedContentsResponse. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `ListCachedContentsResponse.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ListCachedContentsResponse.Builder();
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
     * <p>nextPageToken:
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
     * Setter for cachedContents.
     *
     * <p>cachedContents: List of cached contents.
     */
    @JsonProperty("cachedContents")
    public abstract Builder cachedContents(List<CachedContent> cachedContents);

    /**
     * Setter for cachedContents.
     *
     * <p>cachedContents: List of cached contents.
     */
    @CanIgnoreReturnValue
    public Builder cachedContents(CachedContent... cachedContents) {
      return cachedContents(Arrays.asList(cachedContents));
    }

    /**
     * Setter for cachedContents builder.
     *
     * <p>cachedContents: List of cached contents.
     */
    @CanIgnoreReturnValue
    public Builder cachedContents(CachedContent.Builder... cachedContentsBuilders) {
      return cachedContents(
          Arrays.asList(cachedContentsBuilders).stream()
              .map(CachedContent.Builder::build)
              .collect(toImmutableList()));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder cachedContents(Optional<List<CachedContent>> cachedContents);

    /** Clears the value of cachedContents field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearCachedContents() {
      return cachedContents(Optional.empty());
    }

    public abstract ListCachedContentsResponse build();
  }

  /** Deserializes a JSON string to a ListCachedContentsResponse object. */
  @ExcludeFromGeneratedCoverageReport
  public static ListCachedContentsResponse fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ListCachedContentsResponse.class);
  }
}
