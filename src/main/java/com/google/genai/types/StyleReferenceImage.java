// Auto-generated code. Do not edit.

package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/**
 * A style reference image.
 *
 * <p>This encapsulates a style reference image provided by the user, and additionally optional
 * config parameters for the style reference image.
 *
 * <p>A raw reference image can also be provided as a destination for the style to be applied to.
 */
@AutoValue
@JsonDeserialize(builder = AutoValue_StyleReferenceImage.Builder.class)
public abstract class StyleReferenceImage extends JsonSerializable {
  /** The reference image for the editing operation. */
  public abstract Optional<Image> getReferenceImage();

  /** The id of the reference image. */
  public abstract Optional<Integer> getReferenceId();

  /** The type of the reference image. */
  public abstract Optional<String> getReferenceType();

  /** Configuration for the style reference image. */
  public abstract Optional<StyleReferenceConfig> getConfig();

  /** Instantiates a builder for StyleReferenceImage. */
  public static Builder builder() {
    return new AutoValue_StyleReferenceImage.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for StyleReferenceImage. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("referenceImage")
    public abstract Builder setReferenceImage(Image referenceImage);

    @JsonProperty("referenceId")
    public abstract Builder setReferenceId(Integer referenceId);

    @JsonProperty("referenceType")
    public abstract Builder setReferenceType(String referenceType);

    @JsonProperty("config")
    public abstract Builder setConfig(StyleReferenceConfig config);

    public abstract StyleReferenceImage build();
  }

  /** Deserializes a JSON string to a StyleReferenceImage object. */
  public static StyleReferenceImage fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, StyleReferenceImage.class);
  }
}
