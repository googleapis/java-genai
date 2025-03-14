package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_UpscaleImageParameters extends UpscaleImageParameters {

  private final Optional<String> model;

  private final Optional<Image> image;

  private final Optional<String> upscaleFactor;

  private final Optional<UpscaleImageConfig> config;

  private AutoValue_UpscaleImageParameters(
      Optional<String> model,
      Optional<Image> image,
      Optional<String> upscaleFactor,
      Optional<UpscaleImageConfig> config) {
    this.model = model;
    this.image = image;
    this.upscaleFactor = upscaleFactor;
    this.config = config;
  }

  @JsonProperty("model")
  @Override
  public Optional<String> model() {
    return model;
  }

  @JsonProperty("image")
  @Override
  public Optional<Image> image() {
    return image;
  }

  @JsonProperty("upscaleFactor")
  @Override
  public Optional<String> upscaleFactor() {
    return upscaleFactor;
  }

  @JsonProperty("config")
  @Override
  public Optional<UpscaleImageConfig> config() {
    return config;
  }

  @Override
  public String toString() {
    return "UpscaleImageParameters{"
        + "model=" + model + ", "
        + "image=" + image + ", "
        + "upscaleFactor=" + upscaleFactor + ", "
        + "config=" + config
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof UpscaleImageParameters) {
      UpscaleImageParameters that = (UpscaleImageParameters) o;
      return this.model.equals(that.model())
          && this.image.equals(that.image())
          && this.upscaleFactor.equals(that.upscaleFactor())
          && this.config.equals(that.config());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= model.hashCode();
    h$ *= 1000003;
    h$ ^= image.hashCode();
    h$ *= 1000003;
    h$ ^= upscaleFactor.hashCode();
    h$ *= 1000003;
    h$ ^= config.hashCode();
    return h$;
  }

  @Override
  public UpscaleImageParameters.Builder toBuilder() {
    return new AutoValue_UpscaleImageParameters.Builder(this);
  }

  static final class Builder extends UpscaleImageParameters.Builder {
    private Optional<String> model = Optional.empty();
    private Optional<Image> image = Optional.empty();
    private Optional<String> upscaleFactor = Optional.empty();
    private Optional<UpscaleImageConfig> config = Optional.empty();
    Builder() {
    }
    Builder(UpscaleImageParameters source) {
      this.model = source.model();
      this.image = source.image();
      this.upscaleFactor = source.upscaleFactor();
      this.config = source.config();
    }
    @Override
    public UpscaleImageParameters.Builder model(String model) {
      this.model = Optional.of(model);
      return this;
    }
    @Override
    public UpscaleImageParameters.Builder image(Image image) {
      this.image = Optional.of(image);
      return this;
    }
    @Override
    public UpscaleImageParameters.Builder upscaleFactor(String upscaleFactor) {
      this.upscaleFactor = Optional.of(upscaleFactor);
      return this;
    }
    @Override
    public UpscaleImageParameters.Builder config(UpscaleImageConfig config) {
      this.config = Optional.of(config);
      return this;
    }
    @Override
    public UpscaleImageParameters build() {
      return new AutoValue_UpscaleImageParameters(
          this.model,
          this.image,
          this.upscaleFactor,
          this.config);
    }
  }

}
