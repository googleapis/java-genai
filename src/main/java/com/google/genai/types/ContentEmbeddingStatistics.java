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

/** Statistics of the input text associated with the result of content embedding. */
@AutoValue
@JsonDeserialize(builder = ContentEmbeddingStatistics.Builder.class)
public abstract class ContentEmbeddingStatistics extends JsonSerializable {
  /**
   * Vertex API only. If the input text was truncated due to having a length longer than the allowed
   * maximum input.
   */
  @JsonProperty("truncated")
  public abstract Optional<Boolean> truncated();

  /** Vertex API only. Number of tokens of the input text. */
  @JsonProperty("tokenCount")
  public abstract Optional<Float> tokenCount();

  /** Instantiates a builder for ContentEmbeddingStatistics. */
  public static Builder builder() {
    return new AutoValue_ContentEmbeddingStatistics.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ContentEmbeddingStatistics. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `ContentEmbeddingStatistics.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ContentEmbeddingStatistics.Builder();
    }

    /**
     * Setter for truncated.
     *
     * <p>truncated: Vertex API only. If the input text was truncated due to having a length longer
     * than the allowed maximum input.
     */
    @JsonProperty("truncated")
    public abstract Builder truncated(boolean truncated);

    /**
     * Setter for tokenCount.
     *
     * <p>tokenCount: Vertex API only. Number of tokens of the input text.
     */
    @JsonProperty("tokenCount")
    public abstract Builder tokenCount(Float tokenCount);

    public abstract ContentEmbeddingStatistics build();
  }

  /** Deserializes a JSON string to a ContentEmbeddingStatistics object. */
  public static ContentEmbeddingStatistics fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ContentEmbeddingStatistics.class);
  }
}
