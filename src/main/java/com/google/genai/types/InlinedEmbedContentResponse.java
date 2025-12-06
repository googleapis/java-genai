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
import java.util.Map;
import java.util.Optional;

/** Config for `inlined_embedding_responses` parameter. */
@AutoValue
@JsonDeserialize(builder = InlinedEmbedContentResponse.Builder.class)
public abstract class InlinedEmbedContentResponse extends JsonSerializable {
  /** The response to the request. */
  @JsonProperty("response")
  public abstract Optional<SingleEmbedContentResponse> response();

  /** The error encountered while processing the request. */
  @JsonProperty("error")
  public abstract Optional<JobError> error();

  /** Output only. The metadata associated with the request. */
  @JsonProperty("metadata")
  public abstract Optional<Map<String, Object>> metadata();

  /** Instantiates a builder for InlinedEmbedContentResponse. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_InlinedEmbedContentResponse.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for InlinedEmbedContentResponse. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `InlinedEmbedContentResponse.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_InlinedEmbedContentResponse.Builder();
    }

    /**
     * Setter for response.
     *
     * <p>response: The response to the request.
     */
    @JsonProperty("response")
    public abstract Builder response(SingleEmbedContentResponse response);

    /**
     * Setter for response builder.
     *
     * <p>response: The response to the request.
     */
    @CanIgnoreReturnValue
    public Builder response(SingleEmbedContentResponse.Builder responseBuilder) {
      return response(responseBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder response(Optional<SingleEmbedContentResponse> response);

    /** Clears the value of response field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearResponse() {
      return response(Optional.empty());
    }

    /**
     * Setter for error.
     *
     * <p>error: The error encountered while processing the request.
     */
    @JsonProperty("error")
    public abstract Builder error(JobError error);

    /**
     * Setter for error builder.
     *
     * <p>error: The error encountered while processing the request.
     */
    @CanIgnoreReturnValue
    public Builder error(JobError.Builder errorBuilder) {
      return error(errorBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder error(Optional<JobError> error);

    /** Clears the value of error field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearError() {
      return error(Optional.empty());
    }

    /**
     * Setter for metadata.
     *
     * <p>metadata: Output only. The metadata associated with the request.
     */
    @JsonProperty("metadata")
    public abstract Builder metadata(Map<String, Object> metadata);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder metadata(Optional<Map<String, Object>> metadata);

    /** Clears the value of metadata field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearMetadata() {
      return metadata(Optional.empty());
    }

    public abstract InlinedEmbedContentResponse build();
  }

  /** Deserializes a JSON string to a InlinedEmbedContentResponse object. */
  @ExcludeFromGeneratedCoverageReport
  public static InlinedEmbedContentResponse fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, InlinedEmbedContentResponse.class);
  }
}
