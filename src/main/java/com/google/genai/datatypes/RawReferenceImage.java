// Auto-generated code. Do not edit.

package com.google.genai.datatypes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/**
 * Class that represents a Raw reference image.
 *
 * <p>A raw reference image represents the base image to edit, provided by the user. It can
 * optionally be provided in addition to a mask reference image or a style reference image.
 */
@AutoValue
@JsonDeserialize(builder = AutoValue_RawReferenceImage.Builder.class)
public abstract class RawReferenceImage extends JsonSerializable {
  /** The reference image for the editing operation. */
  public abstract Optional<Image> getReferenceImage();

  /** The id of the reference image. */
  public abstract Optional<Integer> getReferenceId();

  /** The type of the reference image. */
  public abstract Optional<String> getReferenceType();

  /** Instantiates a builder for RawReferenceImage. */
  public static Builder builder() {
    return new AutoValue_RawReferenceImage.Builder();
  }

  /** Builder for RawReferenceImage. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("referenceImage")
    public abstract Builder setReferenceImage(Image referenceImage);

    @JsonProperty("referenceId")
    public abstract Builder setReferenceId(Integer referenceId);

    @JsonProperty("referenceType")
    public abstract Builder setReferenceType(String referenceType);

    public abstract RawReferenceImage build();
  }

  /** Serializes the RawReferenceImage object to a JSON string. */
  public String toJson() {
    return JsonSerializable.toJsonString(this);
  }

  /** Deserializes a JSON string to a RawReferenceImage object. */
  public static RawReferenceImage fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, RawReferenceImage.class);
  }
}
