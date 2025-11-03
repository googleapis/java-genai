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

/** Config for filters. This data type is not supported in Gemini API. */
@AutoValue
@JsonDeserialize(builder = RagRetrievalConfigFilter.Builder.class)
public abstract class RagRetrievalConfigFilter extends JsonSerializable {
  /** Optional. String for metadata filtering. */
  @JsonProperty("metadataFilter")
  public abstract Optional<String> metadataFilter();

  /** Optional. Only returns contexts with vector distance smaller than the threshold. */
  @JsonProperty("vectorDistanceThreshold")
  public abstract Optional<Double> vectorDistanceThreshold();

  /** Optional. Only returns contexts with vector similarity larger than the threshold. */
  @JsonProperty("vectorSimilarityThreshold")
  public abstract Optional<Double> vectorSimilarityThreshold();

  /** Instantiates a builder for RagRetrievalConfigFilter. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_RagRetrievalConfigFilter.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for RagRetrievalConfigFilter. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `RagRetrievalConfigFilter.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_RagRetrievalConfigFilter.Builder();
    }

    /**
     * Setter for metadataFilter.
     *
     * <p>metadataFilter: Optional. String for metadata filtering.
     */
    @JsonProperty("metadataFilter")
    public abstract Builder metadataFilter(String metadataFilter);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder metadataFilter(Optional<String> metadataFilter);

    /** Clears the value of metadataFilter field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearMetadataFilter() {
      return metadataFilter(Optional.empty());
    }

    /**
     * Setter for vectorDistanceThreshold.
     *
     * <p>vectorDistanceThreshold: Optional. Only returns contexts with vector distance smaller than
     * the threshold.
     */
    @JsonProperty("vectorDistanceThreshold")
    public abstract Builder vectorDistanceThreshold(Double vectorDistanceThreshold);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder vectorDistanceThreshold(Optional<Double> vectorDistanceThreshold);

    /** Clears the value of vectorDistanceThreshold field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearVectorDistanceThreshold() {
      return vectorDistanceThreshold(Optional.empty());
    }

    /**
     * Setter for vectorSimilarityThreshold.
     *
     * <p>vectorSimilarityThreshold: Optional. Only returns contexts with vector similarity larger
     * than the threshold.
     */
    @JsonProperty("vectorSimilarityThreshold")
    public abstract Builder vectorSimilarityThreshold(Double vectorSimilarityThreshold);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder vectorSimilarityThreshold(Optional<Double> vectorSimilarityThreshold);

    /** Clears the value of vectorSimilarityThreshold field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearVectorSimilarityThreshold() {
      return vectorSimilarityThreshold(Optional.empty());
    }

    public abstract RagRetrievalConfigFilter build();
  }

  /** Deserializes a JSON string to a RagRetrievalConfigFilter object. */
  @ExcludeFromGeneratedCoverageReport
  public static RagRetrievalConfigFilter fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, RagRetrievalConfigFilter.class);
  }
}
