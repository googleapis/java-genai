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
import com.google.genai.Common;
import com.google.genai.JsonSerializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/** Config for documents.list return value. */
@AutoValue
@JsonDeserialize(builder = ListDocumentsResponse.Builder.class)
public abstract class ListDocumentsResponse extends JsonSerializable {
  /** Used to retain the full HTTP response. */
  @JsonProperty("sdkHttpResponse")
  public abstract Optional<HttpResponse> sdkHttpResponse();

  /**
   * A token, which can be sent as `page_token` to retrieve the next page. If this field is omitted,
   * there are no more pages.
   */
  @JsonProperty("nextPageToken")
  public abstract Optional<String> nextPageToken();

  /** The returned `Document`s. */
  @JsonProperty("documents")
  public abstract Optional<List<Document>> documents();

  /** Instantiates a builder for ListDocumentsResponse. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ListDocumentsResponse.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ListDocumentsResponse. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `ListDocumentsResponse.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ListDocumentsResponse.Builder();
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
     * Setter for documents.
     *
     * <p>documents: The returned `Document`s.
     */
    @JsonProperty("documents")
    public abstract Builder documents(List<Document> documents);

    /**
     * Setter for documents.
     *
     * <p>documents: The returned `Document`s.
     */
    @CanIgnoreReturnValue
    public Builder documents(Document... documents) {
      return documents(Arrays.asList(documents));
    }

    /**
     * Setter for documents builder.
     *
     * <p>documents: The returned `Document`s.
     */
    @CanIgnoreReturnValue
    public Builder documents(Document.Builder... documentsBuilders) {
      return documents(
          Arrays.asList(documentsBuilders).stream()
              .map(Document.Builder::build)
              .collect(toImmutableList()));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder documents(Optional<List<Document>> documents);

    /** Clears the value of documents field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearDocuments() {
      return documents(Optional.empty());
    }

    public abstract ListDocumentsResponse build();
  }

  /** Deserializes a JSON string to a ListDocumentsResponse object. */
  @ExcludeFromGeneratedCoverageReport
  public static ListDocumentsResponse fromJson(String jsonString) {
    return Common.fromJsonString(jsonString, ListDocumentsResponse.class);
  }
}
