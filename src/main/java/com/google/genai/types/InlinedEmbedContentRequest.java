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

/** The request to be processed in the batch. This data type is not supported in Vertex AI. */
@AutoValue
@JsonDeserialize(builder = InlinedEmbedContentRequest.Builder.class)
public abstract class InlinedEmbedContentRequest extends JsonSerializable {
  /** Optional. The metadata to be associated with the request. */
  @JsonProperty("metadata")
  public abstract Optional<Map<String, Object>> metadata();

  /** Required. The request to be processed in the batch. */
  @JsonProperty("request")
  public abstract Optional<EmbedContentRequest> request();

  /** Instantiates a builder for InlinedEmbedContentRequest. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_InlinedEmbedContentRequest.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for InlinedEmbedContentRequest. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `InlinedEmbedContentRequest.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_InlinedEmbedContentRequest.Builder();
    }

    /**
     * Setter for metadata.
     *
     * <p>metadata: Optional. The metadata to be associated with the request.
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

    /**
     * Setter for request.
     *
     * <p>request: Required. The request to be processed in the batch.
     */
    @JsonProperty("request")
    public abstract Builder request(EmbedContentRequest request);

    /**
     * Setter for request builder.
     *
     * <p>request: Required. The request to be processed in the batch.
     */
    @CanIgnoreReturnValue
    public Builder request(EmbedContentRequest.Builder requestBuilder) {
      return request(requestBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder request(Optional<EmbedContentRequest> request);

    /** Clears the value of request field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearRequest() {
      return request(Optional.empty());
    }

    public abstract InlinedEmbedContentRequest build();
  }

  /** Deserializes a JSON string to a InlinedEmbedContentRequest object. */
  @ExcludeFromGeneratedCoverageReport
  public static InlinedEmbedContentRequest fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, InlinedEmbedContentRequest.class);
  }
}
