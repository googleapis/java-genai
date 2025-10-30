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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/** The embedding generated from an input content. */
@AutoValue
@JsonDeserialize(builder = ContentEmbedding.Builder.class)
public abstract class ContentEmbedding extends JsonSerializable {
  /** A list of floats representing an embedding. */
  @JsonProperty("values")
  public abstract Optional<List<Float>> values();

  /** Vertex API only. Statistics of the input text associated with this embedding. */
  @JsonProperty("statistics")
  public abstract Optional<ContentEmbeddingStatistics> statistics();

  /** Instantiates a builder for ContentEmbedding. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ContentEmbedding.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ContentEmbedding. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `ContentEmbedding.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ContentEmbedding.Builder();
    }

    /**
     * Setter for values.
     *
     * <p>values: A list of floats representing an embedding.
     */
    @JsonProperty("values")
    public abstract Builder values(List<Float> values);

    /**
     * Setter for values.
     *
     * <p>values: A list of floats representing an embedding.
     */
    public Builder values(Float... values) {
      return values(Arrays.asList(values));
    }

    /**
     * Setter for statistics.
     *
     * <p>statistics: Vertex API only. Statistics of the input text associated with this embedding.
     */
    @JsonProperty("statistics")
    public abstract Builder statistics(ContentEmbeddingStatistics statistics);

    /**
     * Setter for statistics builder.
     *
     * <p>statistics: Vertex API only. Statistics of the input text associated with this embedding.
     */
    public Builder statistics(ContentEmbeddingStatistics.Builder statisticsBuilder) {
      return statistics(statisticsBuilder.build());
    }

    public abstract ContentEmbedding build();
  }

  /** Deserializes a JSON string to a ContentEmbedding object. */
  @ExcludeFromGeneratedCoverageReport
  public static ContentEmbedding fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ContentEmbedding.class);
  }
}
