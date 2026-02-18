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

/** Response for the _register file method. */
@AutoValue
@JsonDeserialize(builder = RegisterFilesResponse.Builder.class)
public abstract class RegisterFilesResponse extends JsonSerializable {
  /** Used to retain the full HTTP response. */
  @JsonProperty("sdkHttpResponse")
  public abstract Optional<HttpResponse> sdkHttpResponse();

  /** The registered files. */
  @JsonProperty("files")
  public abstract Optional<List<File>> files();

  /** Instantiates a builder for RegisterFilesResponse. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_RegisterFilesResponse.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for RegisterFilesResponse. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `RegisterFilesResponse.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_RegisterFilesResponse.Builder();
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
     * Setter for files.
     *
     * <p>files: The registered files.
     */
    @JsonProperty("files")
    public abstract Builder files(List<File> files);

    /**
     * Setter for files.
     *
     * <p>files: The registered files.
     */
    @CanIgnoreReturnValue
    public Builder files(File... files) {
      return files(Arrays.asList(files));
    }

    /**
     * Setter for files builder.
     *
     * <p>files: The registered files.
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

    public abstract RegisterFilesResponse build();
  }

  /** Deserializes a JSON string to a RegisterFilesResponse object. */
  @ExcludeFromGeneratedCoverageReport
  public static RegisterFilesResponse fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, RegisterFilesResponse.class);
  }
}
