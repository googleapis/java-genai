// Auto-generated code. Do not edit.

package com.google.genai.datatypes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** Chunk from the web. */
@AutoValue
@JsonDeserialize(builder = AutoValue_GroundingChunkWeb.Builder.class)
public abstract class GroundingChunkWeb extends JsonSerializable {
  /** Title of the chunk. */
  public abstract Optional<String> getTitle();

  /** URI reference of the chunk. */
  public abstract Optional<String> getUri();

  /** Instantiates a builder for GroundingChunkWeb. */
  public static Builder builder() {
    return new AutoValue_GroundingChunkWeb.Builder();
  }

  /** Builder for GroundingChunkWeb. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("title")
    public abstract Builder setTitle(String title);

    @JsonProperty("uri")
    public abstract Builder setUri(String uri);

    public abstract GroundingChunkWeb build();
  }

  /** Serializes the GroundingChunkWeb object to a JSON string. */
  public String toJson() {
    return JsonSerializable.toJsonString(this);
  }

  /** Deserializes a JSON string to a GroundingChunkWeb object. */
  public static GroundingChunkWeb fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GroundingChunkWeb.class);
  }
}
