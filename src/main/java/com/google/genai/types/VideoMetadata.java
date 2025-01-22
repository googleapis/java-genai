// Auto-generated code. Do not edit.

package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** Metadata describes the input video content. */
@AutoValue
@JsonDeserialize(builder = AutoValue_VideoMetadata.Builder.class)
public abstract class VideoMetadata extends JsonSerializable {
  /** Optional. The end offset of the video. */
  public abstract Optional<String> getEndOffset();

  /** Optional. The start offset of the video. */
  public abstract Optional<String> getStartOffset();

  /** Instantiates a builder for VideoMetadata. */
  public static Builder builder() {
    return new AutoValue_VideoMetadata.Builder();
  }

  /** Builder for VideoMetadata. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("endOffset")
    public abstract Builder setEndOffset(String endOffset);

    @JsonProperty("startOffset")
    public abstract Builder setStartOffset(String startOffset);

    public abstract VideoMetadata build();
  }

  /** Serializes the VideoMetadata object to a JSON string. */
  public String toJson() {
    return JsonSerializable.toJsonString(this);
  }

  /** Deserializes a JSON string to a VideoMetadata object. */
  public static VideoMetadata fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, VideoMetadata.class);
  }
}
