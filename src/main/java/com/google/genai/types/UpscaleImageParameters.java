// Auto-generated code. Do not edit.

package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** User-facing config UpscaleImageParameters. */
@AutoValue
@JsonDeserialize(builder = AutoValue_UpscaleImageParameters.Builder.class)
public abstract class UpscaleImageParameters extends JsonSerializable {
  /** The model to use. */
  public abstract Optional<String> getModel();

  /** The input image to upscale. */
  public abstract Optional<Image> getImage();

  /** The factor to upscale the image (x2 or x4). */
  public abstract Optional<String> getUpscaleFactor();

  /** Configuration for upscaling. */
  public abstract Optional<UpscaleImageConfig> getConfig();

  /** Instantiates a builder for UpscaleImageParameters. */
  public static Builder builder() {
    return new AutoValue_UpscaleImageParameters.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for UpscaleImageParameters. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("model")
    public abstract Builder setModel(String model);

    @JsonProperty("image")
    public abstract Builder setImage(Image image);

    @JsonProperty("upscaleFactor")
    public abstract Builder setUpscaleFactor(String upscaleFactor);

    @JsonProperty("config")
    public abstract Builder setConfig(UpscaleImageConfig config);

    public abstract UpscaleImageParameters build();
  }

  /** Deserializes a JSON string to a UpscaleImageParameters object. */
  public static UpscaleImageParameters fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, UpscaleImageParameters.class);
  }
}
