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

/** Response for the list files method. */
@AutoValue
@JsonDeserialize(builder = ListFilesResponse.Builder.class)
public abstract class ListFilesResponse extends JsonSerializable {
  /** Used to retain the full HTTP response. */
  @JsonProperty("sdkHttpResponse")
  public abstract Optional<HttpResponse> sdkHttpResponse();

  /**
   * A token that can be sent as a `page_token` into a subsequent `ListFiles` call. This field is
   * not supported in Vertex AI.
   */
  @JsonProperty("nextPageToken")
  public abstract Optional<String> nextPageToken();

  /** The list of `File`s. This field is not supported in Vertex AI. */
  @JsonProperty("files")
  public abstract Optional<List<File>> files();

  /** The files. This field is not supported in Gemini API. */
  @JsonProperty("data")
  public abstract Optional<List<ClaudeFile>> data();

  /**
   * Opaque cursor for the next page. Unset on the last page, and always unset when `ids` was
   * supplied, since batch fetch is single-page. This field is not supported in Gemini API.
   */
  @JsonProperty("nextPage")
  public abstract Optional<String> nextPage();

  /** Instantiates a builder for ListFilesResponse. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ListFilesResponse.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ListFilesResponse. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `ListFilesResponse.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ListFilesResponse.Builder();
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
     * <p>nextPageToken: A token that can be sent as a `page_token` into a subsequent `ListFiles`
     * call. This field is not supported in Vertex AI.
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
     * Setter for files.
     *
     * <p>files: The list of `File`s. This field is not supported in Vertex AI.
     */
    @JsonProperty("files")
    public abstract Builder files(List<File> files);

    /**
     * Setter for files.
     *
     * <p>files: The list of `File`s. This field is not supported in Vertex AI.
     */
    @CanIgnoreReturnValue
    public Builder files(File... files) {
      return files(Arrays.asList(files));
    }

    /**
     * Setter for files builder.
     *
     * <p>files: The list of `File`s. This field is not supported in Vertex AI.
     */
    @CanIgnoreReturnValue
    public Builder files(File.Builder... filesBuilders) {
      return files(
          Arrays.asList(filesBuilders).stream()
              .map(File.Builder::build)
              .collect(toImmutableList()));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder files(Optional<List<File>> files);

    /** Clears the value of files field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearFiles() {
      return files(Optional.empty());
    }

    /**
     * Setter for data.
     *
     * <p>data: The files. This field is not supported in Gemini API.
     */
    @JsonProperty("data")
    public abstract Builder data(List<ClaudeFile> data);

    /**
     * Setter for data.
     *
     * <p>data: The files. This field is not supported in Gemini API.
     */
    @CanIgnoreReturnValue
    public Builder data(ClaudeFile... data) {
      return data(Arrays.asList(data));
    }

    /**
     * Setter for data builder.
     *
     * <p>data: The files. This field is not supported in Gemini API.
     */
    @CanIgnoreReturnValue
    public Builder data(ClaudeFile.Builder... dataBuilders) {
      return data(
          Arrays.asList(dataBuilders).stream()
              .map(ClaudeFile.Builder::build)
              .collect(toImmutableList()));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder data(Optional<List<ClaudeFile>> data);

    /** Clears the value of data field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearData() {
      return data(Optional.empty());
    }

    /**
     * Setter for nextPage.
     *
     * <p>nextPage: Opaque cursor for the next page. Unset on the last page, and always unset when
     * `ids` was supplied, since batch fetch is single-page. This field is not supported in Gemini
     * API.
     */
    @JsonProperty("nextPage")
    public abstract Builder nextPage(String nextPage);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder nextPage(Optional<String> nextPage);

    /** Clears the value of nextPage field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearNextPage() {
      return nextPage(Optional.empty());
    }

    public abstract ListFilesResponse build();
  }

  /** Deserializes a JSON string to a ListFilesResponse object. */
  @ExcludeFromGeneratedCoverageReport
  public static ListFilesResponse fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ListFilesResponse.class);
  }
}
