package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_UpscaleImageResponse extends UpscaleImageResponse {

  private final Optional<List<GeneratedImage>> generatedImages;

  private AutoValue_UpscaleImageResponse(
      Optional<List<GeneratedImage>> generatedImages) {
    this.generatedImages = generatedImages;
  }

  @JsonProperty("generatedImages")
  @Override
  public Optional<List<GeneratedImage>> generatedImages() {
    return generatedImages;
  }

  @Override
  public String toString() {
    return "UpscaleImageResponse{"
        + "generatedImages=" + generatedImages
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof UpscaleImageResponse) {
      UpscaleImageResponse that = (UpscaleImageResponse) o;
      return this.generatedImages.equals(that.generatedImages());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= generatedImages.hashCode();
    return h$;
  }

  @Override
  public UpscaleImageResponse.Builder toBuilder() {
    return new AutoValue_UpscaleImageResponse.Builder(this);
  }

  static final class Builder extends UpscaleImageResponse.Builder {
    private Optional<List<GeneratedImage>> generatedImages = Optional.empty();
    Builder() {
    }
    Builder(UpscaleImageResponse source) {
      this.generatedImages = source.generatedImages();
    }
    @Override
    public UpscaleImageResponse.Builder generatedImages(List<GeneratedImage> generatedImages) {
      this.generatedImages = Optional.of(generatedImages);
      return this;
    }
    @Override
    public UpscaleImageResponse build() {
      return new AutoValue_UpscaleImageResponse(
          this.generatedImages);
    }
  }

}
