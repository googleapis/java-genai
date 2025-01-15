// Auto-generated code. Do not edit.

package com.google.genai.datatypes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** Grounding chunk. */
@AutoValue
@JsonDeserialize(builder = AutoValue_GroundingChunk.Builder.class)
public abstract class GroundingChunk extends JsonSerializable {
  /** Grounding chunk from context retrieved by the retrieval tools. */
  public abstract Optional<GroundingChunkRetrievedContext> getRetrievedContext();

  /** Grounding chunk from the web. */
  public abstract Optional<GroundingChunkWeb> getWeb();

  /** Instantiates a builder for GroundingChunk. */
  public static Builder builder() {
    return new AutoValue_GroundingChunk.Builder();
  }

  /** Builder for GroundingChunk. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("retrievedContext")
    public abstract Builder setRetrievedContext(GroundingChunkRetrievedContext retrievedContext);

    @JsonProperty("web")
    public abstract Builder setWeb(GroundingChunkWeb web);

    public abstract GroundingChunk build();
  }

  /** Serializes the GroundingChunk object to a JSON string. */
  public String toJson() {
    return JsonSerializable.toJsonString(this);
  }

  /** Deserializes a JSON string to a GroundingChunk object. */
  public static GroundingChunk fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GroundingChunk.class);
  }
}
