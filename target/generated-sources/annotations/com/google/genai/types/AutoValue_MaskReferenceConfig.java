package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_MaskReferenceConfig extends MaskReferenceConfig {

  private final Optional<String> maskMode;

  private final Optional<List<Integer>> segmentationClasses;

  private final Optional<Float> maskDilation;

  private AutoValue_MaskReferenceConfig(
      Optional<String> maskMode,
      Optional<List<Integer>> segmentationClasses,
      Optional<Float> maskDilation) {
    this.maskMode = maskMode;
    this.segmentationClasses = segmentationClasses;
    this.maskDilation = maskDilation;
  }

  @JsonProperty("maskMode")
  @Override
  public Optional<String> maskMode() {
    return maskMode;
  }

  @JsonProperty("segmentationClasses")
  @Override
  public Optional<List<Integer>> segmentationClasses() {
    return segmentationClasses;
  }

  @JsonProperty("maskDilation")
  @Override
  public Optional<Float> maskDilation() {
    return maskDilation;
  }

  @Override
  public String toString() {
    return "MaskReferenceConfig{"
        + "maskMode=" + maskMode + ", "
        + "segmentationClasses=" + segmentationClasses + ", "
        + "maskDilation=" + maskDilation
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof MaskReferenceConfig) {
      MaskReferenceConfig that = (MaskReferenceConfig) o;
      return this.maskMode.equals(that.maskMode())
          && this.segmentationClasses.equals(that.segmentationClasses())
          && this.maskDilation.equals(that.maskDilation());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= maskMode.hashCode();
    h$ *= 1000003;
    h$ ^= segmentationClasses.hashCode();
    h$ *= 1000003;
    h$ ^= maskDilation.hashCode();
    return h$;
  }

  @Override
  public MaskReferenceConfig.Builder toBuilder() {
    return new AutoValue_MaskReferenceConfig.Builder(this);
  }

  static final class Builder extends MaskReferenceConfig.Builder {
    private Optional<String> maskMode = Optional.empty();
    private Optional<List<Integer>> segmentationClasses = Optional.empty();
    private Optional<Float> maskDilation = Optional.empty();
    Builder() {
    }
    Builder(MaskReferenceConfig source) {
      this.maskMode = source.maskMode();
      this.segmentationClasses = source.segmentationClasses();
      this.maskDilation = source.maskDilation();
    }
    @Override
    public MaskReferenceConfig.Builder maskMode(String maskMode) {
      this.maskMode = Optional.of(maskMode);
      return this;
    }
    @Override
    public MaskReferenceConfig.Builder segmentationClasses(List<Integer> segmentationClasses) {
      this.segmentationClasses = Optional.of(segmentationClasses);
      return this;
    }
    @Override
    public MaskReferenceConfig.Builder maskDilation(Float maskDilation) {
      this.maskDilation = Optional.of(maskDilation);
      return this;
    }
    @Override
    public MaskReferenceConfig build() {
      return new AutoValue_MaskReferenceConfig(
          this.maskMode,
          this.segmentationClasses,
          this.maskDilation);
    }
  }

}
