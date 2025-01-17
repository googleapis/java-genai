// Auto-generated code. Do not edit.

package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** Segment of the content. */
@AutoValue
@JsonDeserialize(builder = AutoValue_Segment.Builder.class)
public abstract class Segment extends JsonSerializable {
  /**
   * Output only. End index in the given Part, measured in bytes. Offset from the start of the Part,
   * exclusive, starting at zero.
   */
  public abstract Optional<Integer> getEndIndex();

  /** Output only. The index of a Part object within its parent Content object. */
  public abstract Optional<Integer> getPartIndex();

  /**
   * Output only. Start index in the given Part, measured in bytes. Offset from the start of the
   * Part, inclusive, starting at zero.
   */
  public abstract Optional<Integer> getStartIndex();

  /** Output only. The text corresponding to the segment from the response. */
  public abstract Optional<String> getText();

  /** Instantiates a builder for Segment. */
  public static Builder builder() {
    return new AutoValue_Segment.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for Segment. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("endIndex")
    public abstract Builder setEndIndex(Integer endIndex);

    @JsonProperty("partIndex")
    public abstract Builder setPartIndex(Integer partIndex);

    @JsonProperty("startIndex")
    public abstract Builder setStartIndex(Integer startIndex);

    @JsonProperty("text")
    public abstract Builder setText(String text);

    public abstract Segment build();
  }

  /** Deserializes a JSON string to a Segment object. */
  public static Segment fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, Segment.class);
  }
}
