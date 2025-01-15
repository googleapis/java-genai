// Auto-generated code. Do not edit.

package com.google.genai.datatypes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** Metadata related to retrieval in the grounding flow. */
@AutoValue
@JsonDeserialize(builder = AutoValue_RetrievalMetadata.Builder.class)
public abstract class RetrievalMetadata extends JsonSerializable {
  /**
   * Optional. Score indicating how likely information from Google Search could help answer the
   * prompt. The score is in the range `[0, 1]`, where 0 is the least likely and 1 is the most
   * likely. This score is only populated when Google Search grounding and dynamic retrieval is
   * enabled. It will be compared to the threshold to determine whether to trigger Google Search.
   */
  public abstract Optional<Float> getGoogleSearchDynamicRetrievalScore();

  /** Instantiates a builder for RetrievalMetadata. */
  public static Builder builder() {
    return new AutoValue_RetrievalMetadata.Builder();
  }

  /** Builder for RetrievalMetadata. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("googleSearchDynamicRetrievalScore")
    public abstract Builder setGoogleSearchDynamicRetrievalScore(
        Float googleSearchDynamicRetrievalScore);

    public abstract RetrievalMetadata build();
  }

  /** Serializes the RetrievalMetadata object to a JSON string. */
  public String toJson() {
    return JsonSerializable.toJsonString(this);
  }

  /** Deserializes a JSON string to a RetrievalMetadata object. */
  public static RetrievalMetadata fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, RetrievalMetadata.class);
  }
}
