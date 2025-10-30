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
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** Config for `response` parameter. */
@AutoValue
@JsonDeserialize(builder = SingleEmbedContentResponse.Builder.class)
public abstract class SingleEmbedContentResponse extends JsonSerializable {
  /** The response to the request. */
  @JsonProperty("embedding")
  public abstract Optional<ContentEmbedding> embedding();

  /** The error encountered while processing the request. */
  @JsonProperty("tokenCount")
  public abstract Optional<Long> tokenCount();

  /** Instantiates a builder for SingleEmbedContentResponse. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_SingleEmbedContentResponse.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for SingleEmbedContentResponse. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `SingleEmbedContentResponse.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_SingleEmbedContentResponse.Builder();
    }

    /**
     * Setter for embedding.
     *
     * <p>embedding: The response to the request.
     */
    @JsonProperty("embedding")
    public abstract Builder embedding(ContentEmbedding embedding);

    /**
     * Setter for embedding builder.
     *
     * <p>embedding: The response to the request.
     */
    public Builder embedding(ContentEmbedding.Builder embeddingBuilder) {
      return embedding(embeddingBuilder.build());
    }

    /**
     * Setter for tokenCount.
     *
     * <p>tokenCount: The error encountered while processing the request.
     */
    @JsonProperty("tokenCount")
    public abstract Builder tokenCount(Long tokenCount);

    public abstract SingleEmbedContentResponse build();
  }

  /** Deserializes a JSON string to a SingleEmbedContentResponse object. */
  @ExcludeFromGeneratedCoverageReport
  public static SingleEmbedContentResponse fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, SingleEmbedContentResponse.class);
  }
}
