// Auto-generated code. Do not edit.

package com.google.genai.datatypes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/**
 * Class that represents a Mask reference image.
 *
 * <p>This encapsulates either a mask image provided by the user and configs for the user provided
 * mask, or only config parameters for the model to generate a mask.
 *
 * <p>A mask image is an image whose non-zero values indicate where to edit the base image. If the
 * user provides a mask image, the mask must be in the same dimensions as the raw image.
 */
@AutoValue
@JsonDeserialize(builder = AutoValue_MaskReferenceImage.Builder.class)
public abstract class MaskReferenceImage extends JsonSerializable {
  /** The reference image for the editing operation. */
  public abstract Optional<Image> getReferenceImage();

  /** The id of the reference image. */
  public abstract Optional<Integer> getReferenceId();

  /** The type of the reference image. */
  public abstract Optional<String> getReferenceType();

  /** Configuration for the mask reference image. */
  public abstract Optional<MaskReferenceConfig> getConfig();

  /** Instantiates a builder for MaskReferenceImage. */
  public static Builder builder() {
    return new AutoValue_MaskReferenceImage.Builder();
  }

  /** Builder for MaskReferenceImage. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("referenceImage")
    public abstract Builder setReferenceImage(Image referenceImage);

    @JsonProperty("referenceId")
    public abstract Builder setReferenceId(Integer referenceId);

    @JsonProperty("referenceType")
    public abstract Builder setReferenceType(String referenceType);

    @JsonProperty("config")
    public abstract Builder setConfig(MaskReferenceConfig config);

    public abstract MaskReferenceImage build();
  }

  /** Serializes the MaskReferenceImage object to a JSON string. */
  public String toJson() {
    return JsonSerializable.toJsonString(this);
  }

  /** Deserializes a JSON string to a MaskReferenceImage object. */
  public static MaskReferenceImage fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, MaskReferenceImage.class);
  }
}
