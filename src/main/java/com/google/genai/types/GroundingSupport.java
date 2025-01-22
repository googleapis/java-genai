// Auto-generated code. Do not edit.

package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.List;
import java.util.Optional;

/** Grounding support. */
@AutoValue
@JsonDeserialize(builder = AutoValue_GroundingSupport.Builder.class)
public abstract class GroundingSupport extends JsonSerializable {
  /**
   * Confidence score of the support references. Ranges from 0 to 1. 1 is the most confident. This
   * list must have the same size as the grounding_chunk_indices.
   */
  public abstract Optional<List<Float>> getConfidenceScores();

  /**
   * A list of indices (into 'grounding_chunk') specifying the citations associated with the claim.
   * For instance [1,3,4] means that grounding_chunk[1], grounding_chunk[3], grounding_chunk[4] are
   * the retrieved content attributed to the claim.
   */
  public abstract Optional<List<Integer>> getGroundingChunkIndices();

  /** Segment of the content this support belongs to. */
  public abstract Optional<Segment> getSegment();

  /** Instantiates a builder for GroundingSupport. */
  public static Builder builder() {
    return new AutoValue_GroundingSupport.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GroundingSupport. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("confidenceScores")
    public abstract Builder setConfidenceScores(List<Float> confidenceScores);

    @JsonProperty("groundingChunkIndices")
    public abstract Builder setGroundingChunkIndices(List<Integer> groundingChunkIndices);

    @JsonProperty("segment")
    public abstract Builder setSegment(Segment segment);

    public abstract GroundingSupport build();
  }

  /** Deserializes a JSON string to a GroundingSupport object. */
  public static GroundingSupport fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GroundingSupport.class);
  }
}
