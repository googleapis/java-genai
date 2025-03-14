package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_UpscaleImageConfig extends UpscaleImageConfig {

  private final Optional<Boolean> includeRaiReason;

  private final Optional<String> outputMimeType;

  private final Optional<Integer> outputCompressionQuality;

  private AutoValue_UpscaleImageConfig(
      Optional<Boolean> includeRaiReason,
      Optional<String> outputMimeType,
      Optional<Integer> outputCompressionQuality) {
    this.includeRaiReason = includeRaiReason;
    this.outputMimeType = outputMimeType;
    this.outputCompressionQuality = outputCompressionQuality;
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

  @Override
  public String toString() {
    return "UpscaleImageConfig{"
        + "includeRaiReason=" + includeRaiReason + ", "
        + "outputMimeType=" + outputMimeType + ", "
        + "outputCompressionQuality=" + outputCompressionQuality
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof UpscaleImageConfig) {
      UpscaleImageConfig that = (UpscaleImageConfig) o;
      return this.includeRaiReason.equals(that.includeRaiReason())
          && this.outputMimeType.equals(that.outputMimeType())
          && this.outputCompressionQuality.equals(that.outputCompressionQuality());
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
    return h$;
  }

  @Override
  public UpscaleImageConfig.Builder toBuilder() {
    return new AutoValue_UpscaleImageConfig.Builder(this);
  }

  static final class Builder extends UpscaleImageConfig.Builder {
    private Optional<Boolean> includeRaiReason = Optional.empty();
    private Optional<String> outputMimeType = Optional.empty();
    private Optional<Integer> outputCompressionQuality = Optional.empty();
    Builder() {
    }
    Builder(UpscaleImageConfig source) {
      this.includeRaiReason = source.includeRaiReason();
      this.outputMimeType = source.outputMimeType();
      this.outputCompressionQuality = source.outputCompressionQuality();
    }
    @Override
    public UpscaleImageConfig.Builder includeRaiReason(boolean includeRaiReason) {
      this.includeRaiReason = Optional.of(includeRaiReason);
      return this;
    }
    @Override
    public UpscaleImageConfig.Builder outputMimeType(String outputMimeType) {
      this.outputMimeType = Optional.of(outputMimeType);
      return this;
    }
    @Override
    public UpscaleImageConfig.Builder outputCompressionQuality(Integer outputCompressionQuality) {
      this.outputCompressionQuality = Optional.of(outputCompressionQuality);
      return this;
    }
    @Override
    public UpscaleImageConfig build() {
      return new AutoValue_UpscaleImageConfig(
          this.includeRaiReason,
          this.outputMimeType,
          this.outputCompressionQuality);
    }
  }

}
