// Auto-generated code. Do not edit.

package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.List;
import java.util.Optional;

/** Configuration for a Mask reference image. */
@AutoValue
@JsonDeserialize(builder = AutoValue_MaskReferenceConfig.Builder.class)
public abstract class MaskReferenceConfig extends JsonSerializable {
  /**
   * Prompts the model to generate a mask instead of you needing to provide one (unless
   * MASK_MODE_USER_PROVIDED is used).
   */
  public abstract Optional<String> getMaskMode();

  /**
   * A list of up to 5 class ids to use for semantic segmentation. Automatically creates an image
   * mask based on specific objects.
   */
  public abstract Optional<List<Integer>> getSegmentationClasses();

  /** Dilation percentage of the mask provided. Float between 0 and 1. */
  public abstract Optional<Float> getMaskDilation();

  /** Instantiates a builder for MaskReferenceConfig. */
  public static Builder builder() {
    return new AutoValue_MaskReferenceConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for MaskReferenceConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("maskMode")
    public abstract Builder setMaskMode(String maskMode);

    @JsonProperty("segmentationClasses")
    public abstract Builder setSegmentationClasses(List<Integer> segmentationClasses);

    @JsonProperty("maskDilation")
    public abstract Builder setMaskDilation(Float maskDilation);

    public abstract MaskReferenceConfig build();
  }

  /** Deserializes a JSON string to a MaskReferenceConfig object. */
  public static MaskReferenceConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, MaskReferenceConfig.class);
  }
}
