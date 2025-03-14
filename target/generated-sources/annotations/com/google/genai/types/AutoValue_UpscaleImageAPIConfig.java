package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_UpscaleImageAPIConfig extends UpscaleImageAPIConfig {

  private final Optional<Boolean> includeRaiReason;

  private final Optional<String> outputMimeType;

  private final Optional<Integer> outputCompressionQuality;

  private final Optional<Integer> numberOfImages;

  private final Optional<String> mode;

  private AutoValue_UpscaleImageAPIConfig(
      Optional<Boolean> includeRaiReason,
      Optional<String> outputMimeType,
      Optional<Integer> outputCompressionQuality,
      Optional<Integer> numberOfImages,
      Optional<String> mode) {
    this.includeRaiReason = includeRaiReason;
    this.outputMimeType = outputMimeType;
    this.outputCompressionQuality = outputCompressionQuality;
    this.numberOfImages = numberOfImages;
    this.mode = mode;
  }

  @JsonProperty("includeRaiReason")
  @Override
  public Optional<Boolean> includeRaiReason() {
    return includeRaiReason;
  }

  @JsonProperty("outputMimeType")
  @Override
  public Optional<String> outputMimeType() {
    return outputMimeType;
  }

  @JsonProperty("outputCompressionQuality")
  @Override
  public Optional<Integer> outputCompressionQuality() {
    return outputCompressionQuality;
  }

  @JsonProperty("numberOfImages")
  @Override
  public Optional<Integer> numberOfImages() {
    return numberOfImages;
  }

  @JsonProperty("mode")
  @Override
  public Optional<String> mode() {
    return mode;
  }

  @Override
  public String toString() {
    return "UpscaleImageAPIConfig{"
        + "includeRaiReason=" + includeRaiReason + ", "
        + "outputMimeType=" + outputMimeType + ", "
        + "outputCompressionQuality=" + outputCompressionQuality + ", "
        + "numberOfImages=" + numberOfImages + ", "
        + "mode=" + mode
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof UpscaleImageAPIConfig) {
      UpscaleImageAPIConfig that = (UpscaleImageAPIConfig) o;
      return this.includeRaiReason.equals(that.includeRaiReason())
          && this.outputMimeType.equals(that.outputMimeType())
          && this.outputCompressionQuality.equals(that.outputCompressionQuality())
          && this.numberOfImages.equals(that.numberOfImages())
          && this.mode.equals(that.mode());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= includeRaiReason.hashCode();
    h$ *= 1000003;
    h$ ^= outputMimeType.hashCode();
    h$ *= 1000003;
    h$ ^= outputCompressionQuality.hashCode();
    h$ *= 1000003;
    h$ ^= numberOfImages.hashCode();
    h$ *= 1000003;
    h$ ^= mode.hashCode();
    return h$;
  }

  @Override
  public UpscaleImageAPIConfig.Builder toBuilder() {
    return new AutoValue_UpscaleImageAPIConfig.Builder(this);
  }

  static final class Builder extends UpscaleImageAPIConfig.Builder {
    private Optional<Boolean> includeRaiReason = Optional.empty();
    private Optional<String> outputMimeType = Optional.empty();
    private Optional<Integer> outputCompressionQuality = Optional.empty();
    private Optional<Integer> numberOfImages = Optional.empty();
    private Optional<String> mode = Optional.empty();
    Builder() {
    }
    Builder(UpscaleImageAPIConfig source) {
      this.includeRaiReason = source.includeRaiReason();
      this.outputMimeType = source.outputMimeType();
      this.outputCompressionQuality = source.outputCompressionQuality();
      this.numberOfImages = source.numberOfImages();
      this.mode = source.mode();
    }
    @Override
    public UpscaleImageAPIConfig.Builder includeRaiReason(boolean includeRaiReason) {
      this.includeRaiReason = Optional.of(includeRaiReason);
      return this;
    }
    @Override
    public UpscaleImageAPIConfig.Builder outputMimeType(String outputMimeType) {
      this.outputMimeType = Optional.of(outputMimeType);
      return this;
    }
    @Override
    public UpscaleImageAPIConfig.Builder outputCompressionQuality(Integer outputCompressionQuality) {
      this.outputCompressionQuality = Optional.of(outputCompressionQuality);
      return this;
    }
    @Override
    public UpscaleImageAPIConfig.Builder numberOfImages(Integer numberOfImages) {
      this.numberOfImages = Optional.of(numberOfImages);
      return this;
    }
    @Override
    public UpscaleImageAPIConfig.Builder mode(String mode) {
      this.mode = Optional.of(mode);
      return this;
    }
    @Override
    public UpscaleImageAPIConfig build() {
      return new AutoValue_UpscaleImageAPIConfig(
          this.includeRaiReason,
          this.outputMimeType,
          this.outputCompressionQuality,
          this.numberOfImages,
          this.mode);
    }
  }

}
