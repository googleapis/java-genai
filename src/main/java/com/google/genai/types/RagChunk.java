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

/**
 * A RagChunk includes the content of a chunk of a RagFile, and associated metadata. This data type
 * is not supported in Gemini API.
 */
@AutoValue
@JsonDeserialize(builder = RagChunk.Builder.class)
public abstract class RagChunk extends JsonSerializable {
  /** If populated, represents where the chunk starts and ends in the document. */
  @JsonProperty("pageSpan")
  public abstract Optional<RagChunkPageSpan> pageSpan();

  /** The content of the chunk. */
  @JsonProperty("text")
  public abstract Optional<String> text();

  /** Instantiates a builder for RagChunk. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_RagChunk.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for RagChunk. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `RagChunk.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_RagChunk.Builder();
    }

    /**
     * Setter for pageSpan.
     *
     * <p>pageSpan: If populated, represents where the chunk starts and ends in the document.
     */
    @JsonProperty("pageSpan")
    public abstract Builder pageSpan(RagChunkPageSpan pageSpan);

    /**
     * Setter for pageSpan builder.
     *
     * <p>pageSpan: If populated, represents where the chunk starts and ends in the document.
     */
    @CanIgnoreReturnValue
    public Builder pageSpan(RagChunkPageSpan.Builder pageSpanBuilder) {
      return pageSpan(pageSpanBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder pageSpan(Optional<RagChunkPageSpan> pageSpan);

    /** Clears the value of pageSpan field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearPageSpan() {
      return pageSpan(Optional.empty());
    }

    /**
     * Setter for text.
     *
     * <p>text: The content of the chunk.
     */
    @JsonProperty("text")
    public abstract Builder text(String text);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder text(Optional<String> text);

    /** Clears the value of text field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearText() {
      return text(Optional.empty());
    }

    public abstract RagChunk build();
  }

  /** Deserializes a JSON string to a RagChunk object. */
  @ExcludeFromGeneratedCoverageReport
  public static RagChunk fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, RagChunk.class);
  }
}
