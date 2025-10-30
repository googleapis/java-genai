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

/** Grounding support. */
@AutoValue
@JsonDeserialize(builder = GroundingSupport.Builder.class)
public abstract class GroundingSupport extends JsonSerializable {
  /**
   * Confidence score of the support references. Ranges from 0 to 1. 1 is the most confident. For
   * Gemini 2.0 and before, this list must have the same size as the grounding_chunk_indices. For
   * Gemini 2.5 and after, this list will be empty and should be ignored.
   */
  @JsonProperty("confidenceScores")
  public abstract Optional<List<Float>> confidenceScores();

  /**
   * A list of indices (into 'grounding_chunk') specifying the citations associated with the claim.
   * For instance [1,3,4] means that grounding_chunk[1], grounding_chunk[3], grounding_chunk[4] are
   * the retrieved content attributed to the claim.
   */
  @JsonProperty("groundingChunkIndices")
  public abstract Optional<List<Integer>> groundingChunkIndices();

  /** Segment of the content this support belongs to. */
  @JsonProperty("segment")
  public abstract Optional<Segment> segment();

  /** Instantiates a builder for GroundingSupport. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_GroundingSupport.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GroundingSupport. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `GroundingSupport.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_GroundingSupport.Builder();
    }

    /**
     * Setter for confidenceScores.
     *
     * <p>confidenceScores: Confidence score of the support references. Ranges from 0 to 1. 1 is the
     * most confident. For Gemini 2.0 and before, this list must have the same size as the
     * grounding_chunk_indices. For Gemini 2.5 and after, this list will be empty and should be
     * ignored.
     */
    @JsonProperty("confidenceScores")
    public abstract Builder confidenceScores(List<Float> confidenceScores);

    /**
     * Setter for confidenceScores.
     *
     * <p>confidenceScores: Confidence score of the support references. Ranges from 0 to 1. 1 is the
     * most confident. For Gemini 2.0 and before, this list must have the same size as the
     * grounding_chunk_indices. For Gemini 2.5 and after, this list will be empty and should be
     * ignored.
     */
    public Builder confidenceScores(Float... confidenceScores) {
      return confidenceScores(Arrays.asList(confidenceScores));
    }

    /**
     * Setter for groundingChunkIndices.
     *
     * <p>groundingChunkIndices: A list of indices (into 'grounding_chunk') specifying the citations
     * associated with the claim. For instance [1,3,4] means that grounding_chunk[1],
     * grounding_chunk[3], grounding_chunk[4] are the retrieved content attributed to the claim.
     */
    @JsonProperty("groundingChunkIndices")
    public abstract Builder groundingChunkIndices(List<Integer> groundingChunkIndices);

    /**
     * Setter for groundingChunkIndices.
     *
     * <p>groundingChunkIndices: A list of indices (into 'grounding_chunk') specifying the citations
     * associated with the claim. For instance [1,3,4] means that grounding_chunk[1],
     * grounding_chunk[3], grounding_chunk[4] are the retrieved content attributed to the claim.
     */
    public Builder groundingChunkIndices(Integer... groundingChunkIndices) {
      return groundingChunkIndices(Arrays.asList(groundingChunkIndices));
    }

    /**
     * Setter for segment.
     *
     * <p>segment: Segment of the content this support belongs to.
     */
    @JsonProperty("segment")
    public abstract Builder segment(Segment segment);

    /**
     * Setter for segment builder.
     *
     * <p>segment: Segment of the content this support belongs to.
     */
    public Builder segment(Segment.Builder segmentBuilder) {
      return segment(segmentBuilder.build());
    }

    public abstract GroundingSupport build();
  }

  /** Deserializes a JSON string to a GroundingSupport object. */
  @ExcludeFromGeneratedCoverageReport
  public static GroundingSupport fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GroundingSupport.class);
  }
}
