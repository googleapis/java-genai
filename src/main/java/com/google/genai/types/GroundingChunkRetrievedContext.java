// Auto-generated code. Do not edit.

package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** Chunk from context retrieved by the retrieval tools. */
@AutoValue
@JsonDeserialize(builder = AutoValue_GroundingChunkRetrievedContext.Builder.class)
public abstract class GroundingChunkRetrievedContext extends JsonSerializable {
  /** Text of the attribution. */
  public abstract Optional<String> getText();

  /** Title of the attribution. */
  public abstract Optional<String> getTitle();

  /** URI reference of the attribution. */
  public abstract Optional<String> getUri();

  /** Instantiates a builder for GroundingChunkRetrievedContext. */
  public static Builder builder() {
    return new AutoValue_GroundingChunkRetrievedContext.Builder();
  }

  /** Builder for GroundingChunkRetrievedContext. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("text")
    public abstract Builder setText(String text);

    @JsonProperty("title")
    public abstract Builder setTitle(String title);

    @JsonProperty("uri")
    public abstract Builder setUri(String uri);

    public abstract GroundingChunkRetrievedContext build();
  }

  /** Serializes the GroundingChunkRetrievedContext object to a JSON string. */
  public String toJson() {
    return JsonSerializable.toJsonString(this);
  }

  /** Deserializes a JSON string to a GroundingChunkRetrievedContext object. */
  public static GroundingChunkRetrievedContext fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GroundingChunkRetrievedContext.class);
  }
}
