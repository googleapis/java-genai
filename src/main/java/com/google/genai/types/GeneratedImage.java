// Auto-generated code. Do not edit.

package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** Class that represents an output image. */
@AutoValue
@JsonDeserialize(builder = AutoValue_GeneratedImage.Builder.class)
public abstract class GeneratedImage extends JsonSerializable {
  /** The output image data. */
  public abstract Optional<Image> getImage();

  /** Responsible AI filter reason if the image is filtered out of the response. */
  public abstract Optional<String> getRaiFilteredReason();

  /** Instantiates a builder for GeneratedImage. */
  public static Builder builder() {
    return new AutoValue_GeneratedImage.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GeneratedImage. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("image")
    public abstract Builder setImage(Image image);

    @JsonProperty("raiFilteredReason")
    public abstract Builder setRaiFilteredReason(String raiFilteredReason);

    public abstract GeneratedImage build();
  }

  /** Deserializes a JSON string to a GeneratedImage object. */
  public static GeneratedImage fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GeneratedImage.class);
  }
}
