package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_GenerateImagesResponse extends GenerateImagesResponse {

  private final Optional<List<GeneratedImage>> generatedImages;

  private AutoValue_GenerateImagesResponse(
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
    return "GenerateImagesResponse{"
        + "generatedImages=" + generatedImages
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof GenerateImagesResponse) {
      GenerateImagesResponse that = (GenerateImagesResponse) o;
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
  public GenerateImagesResponse.Builder toBuilder() {
    return new AutoValue_GenerateImagesResponse.Builder(this);
  }

  static final class Builder extends GenerateImagesResponse.Builder {
    private Optional<List<GeneratedImage>> generatedImages = Optional.empty();
    Builder() {
    }
    Builder(GenerateImagesResponse source) {
      this.generatedImages = source.generatedImages();
    }
    @Override
    public GenerateImagesResponse.Builder generatedImages(List<GeneratedImage> generatedImages) {
      this.generatedImages = Optional.of(generatedImages);
      return this;
    }
    @Override
    public GenerateImagesResponse build() {
      return new AutoValue_GenerateImagesResponse(
          this.generatedImages);
    }
  }

}
