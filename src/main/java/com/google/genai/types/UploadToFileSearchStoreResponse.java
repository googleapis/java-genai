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
import com.google.genai.Common;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** The response when long-running operation for uploading a file to a FileSearchStore complete. */
@AutoValue
@JsonDeserialize(builder = UploadToFileSearchStoreResponse.Builder.class)
public abstract class UploadToFileSearchStoreResponse extends JsonSerializable {
  /** Used to retain the full HTTP response. */
  @JsonProperty("sdkHttpResponse")
  public abstract Optional<HttpResponse> sdkHttpResponse();

  /** The name of the FileSearchStore containing Documents. */
  @JsonProperty("parent")
  public abstract Optional<String> parent();

  /** The identifier for the Document imported. */
  @JsonProperty("documentName")
  public abstract Optional<String> documentName();

  /** Instantiates a builder for UploadToFileSearchStoreResponse. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_UploadToFileSearchStoreResponse.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for UploadToFileSearchStoreResponse. */
  @AutoValue.Builder
  public abstract static class Builder {
    /**
     * For internal usage. Please use `UploadToFileSearchStoreResponse.builder()` for instantiation.
     */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_UploadToFileSearchStoreResponse.Builder();
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
     * Setter for parent.
     *
     * <p>parent: The name of the FileSearchStore containing Documents.
     */
    @JsonProperty("parent")
    public abstract Builder parent(String parent);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder parent(Optional<String> parent);

    /** Clears the value of parent field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearParent() {
      return parent(Optional.empty());
    }

    /**
     * Setter for documentName.
     *
     * <p>documentName: The identifier for the Document imported.
     */
    @JsonProperty("documentName")
    public abstract Builder documentName(String documentName);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder documentName(Optional<String> documentName);

    /** Clears the value of documentName field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearDocumentName() {
      return documentName(Optional.empty());
    }

    public abstract UploadToFileSearchStoreResponse build();
  }

  /** Deserializes a JSON string to a UploadToFileSearchStoreResponse object. */
  @ExcludeFromGeneratedCoverageReport
  public static UploadToFileSearchStoreResponse fromJson(String jsonString) {
    return Common.fromJsonString(jsonString, UploadToFileSearchStoreResponse.class);
  }
}
