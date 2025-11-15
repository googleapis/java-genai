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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/** A collection of supporting references for a segment of the model's response. */
@AutoValue
@JsonDeserialize(builder = GroundingSupport.Builder.class)
public abstract class GroundingSupport extends JsonSerializable {
  /**
   * The confidence scores for the support references. This list is parallel to the
   * `grounding_chunk_indices` list. A score is a value between 0.0 and 1.0, with a higher score
   * indicating a higher confidence that the reference supports the claim. For Gemini 2.0 and
   * before, this list has the same size as `grounding_chunk_indices`. For Gemini 2.5 and later,
   * this list is empty and should be ignored.
   */
  @JsonProperty("confidenceScores")
  public abstract Optional<List<Float>> confidenceScores();

  /**
   * A list of indices into the `grounding_chunks` field of the `GroundingMetadata` message. These
   * indices specify which grounding chunks support the claim made in the content segment. For
   * example, if this field has the values `[1, 3]`, it means that `grounding_chunks[1]` and
   * `grounding_chunks[3]` are the sources for the claim in the content segment.
   */
  @JsonProperty("groundingChunkIndices")
  public abstract Optional<List<Integer>> groundingChunkIndices();

  /** The content segment that this support message applies to. */
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
     * <p>confidenceScores: The confidence scores for the support references. This list is parallel
     * to the `grounding_chunk_indices` list. A score is a value between 0.0 and 1.0, with a higher
     * score indicating a higher confidence that the reference supports the claim. For Gemini 2.0
     * and before, this list has the same size as `grounding_chunk_indices`. For Gemini 2.5 and
     * later, this list is empty and should be ignored.
     */
    @JsonProperty("confidenceScores")
    public abstract Builder confidenceScores(List<Float> confidenceScores);

    /**
     * Setter for confidenceScores.
     *
     * <p>confidenceScores: The confidence scores for the support references. This list is parallel
     * to the `grounding_chunk_indices` list. A score is a value between 0.0 and 1.0, with a higher
     * score indicating a higher confidence that the reference supports the claim. For Gemini 2.0
     * and before, this list has the same size as `grounding_chunk_indices`. For Gemini 2.5 and
     * later, this list is empty and should be ignored.
     */
    @CanIgnoreReturnValue
    public Builder confidenceScores(Float... confidenceScores) {
      return confidenceScores(Arrays.asList(confidenceScores));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder confidenceScores(Optional<List<Float>> confidenceScores);

    /** Clears the value of confidenceScores field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearConfidenceScores() {
      return confidenceScores(Optional.empty());
    }

    /**
     * Setter for groundingChunkIndices.
     *
     * <p>groundingChunkIndices: A list of indices into the `grounding_chunks` field of the
     * `GroundingMetadata` message. These indices specify which grounding chunks support the claim
     * made in the content segment. For example, if this field has the values `[1, 3]`, it means
     * that `grounding_chunks[1]` and `grounding_chunks[3]` are the sources for the claim in the
     * content segment.
     */
    @JsonProperty("groundingChunkIndices")
    public abstract Builder groundingChunkIndices(List<Integer> groundingChunkIndices);

    /**
     * Setter for groundingChunkIndices.
     *
     * <p>groundingChunkIndices: A list of indices into the `grounding_chunks` field of the
     * `GroundingMetadata` message. These indices specify which grounding chunks support the claim
     * made in the content segment. For example, if this field has the values `[1, 3]`, it means
     * that `grounding_chunks[1]` and `grounding_chunks[3]` are the sources for the claim in the
     * content segment.
     */
    @CanIgnoreReturnValue
    public Builder groundingChunkIndices(Integer... groundingChunkIndices) {
      return groundingChunkIndices(Arrays.asList(groundingChunkIndices));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder groundingChunkIndices(Optional<List<Integer>> groundingChunkIndices);

    /** Clears the value of groundingChunkIndices field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearGroundingChunkIndices() {
      return groundingChunkIndices(Optional.empty());
    }

    /**
     * Setter for segment.
     *
     * <p>segment: The content segment that this support message applies to.
     */
    @JsonProperty("segment")
    public abstract Builder segment(Segment segment);

    /**
     * Setter for segment builder.
     *
     * <p>segment: The content segment that this support message applies to.
     */
    @CanIgnoreReturnValue
    public Builder segment(Segment.Builder segmentBuilder) {
      return segment(segmentBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder segment(Optional<Segment> segment);

    /** Clears the value of segment field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearSegment() {
      return segment(Optional.empty());
    }

    public abstract GroundingSupport build();
  }

  /** Deserializes a JSON string to a GroundingSupport object. */
  @ExcludeFromGeneratedCoverageReport
  public static GroundingSupport fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GroundingSupport.class);
  }
}
