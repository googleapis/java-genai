// Auto-generated code. Do not edit.

package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** Class that represents an image. */
@AutoValue
@JsonDeserialize(builder = AutoValue_Image.Builder.class)
public abstract class Image extends JsonSerializable {
  /**
   * The Cloud Storage URI of the image. ``Image`` can contain a value for this field or the
   * ``image_bytes`` field but not both.
   */
  public abstract Optional<String> getGcsUri();

  /**
   * The image bytes data. ``Image`` can contain a value for this field or the ``gcs_uri`` field but
   * not both.
   */
  public abstract Optional<String> getImageBytes();

  /** Instantiates a builder for Image. */
  public static Builder builder() {
    return new AutoValue_Image.Builder();
  }

  /** Builder for Image. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("gcsUri")
    public abstract Builder setGcsUri(String gcsUri);

    @JsonProperty("imageBytes")
    public abstract Builder setImageBytes(String imageBytes);

    public abstract Image build();
  }

  /** Serializes the Image object to a JSON string. */
  public String toJson() {
    return JsonSerializable.toJsonString(this);
  }

  /** Deserializes a JSON string to a Image object. */
  public static Image fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, Image.class);
  }
}
