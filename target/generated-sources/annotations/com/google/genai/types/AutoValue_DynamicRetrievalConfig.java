package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_DynamicRetrievalConfig extends DynamicRetrievalConfig {

  private final Optional<String> mode;

  private final Optional<Float> dynamicThreshold;

  private AutoValue_DynamicRetrievalConfig(
      Optional<String> mode,
      Optional<Float> dynamicThreshold) {
    this.mode = mode;
    this.dynamicThreshold = dynamicThreshold;
  }

  @JsonProperty("mode")
  @Override
  public Optional<String> mode() {
    return mode;
  }

  @JsonProperty("dynamicThreshold")
  @Override
  public Optional<Float> dynamicThreshold() {
    return dynamicThreshold;
  }

  @Override
  public String toString() {
    return "DynamicRetrievalConfig{"
        + "mode=" + mode + ", "
        + "dynamicThreshold=" + dynamicThreshold
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof DynamicRetrievalConfig) {
      DynamicRetrievalConfig that = (DynamicRetrievalConfig) o;
      return this.mode.equals(that.mode())
          && this.dynamicThreshold.equals(that.dynamicThreshold());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= mode.hashCode();
    h$ *= 1000003;
    h$ ^= dynamicThreshold.hashCode();
    return h$;
  }

  @Override
  public DynamicRetrievalConfig.Builder toBuilder() {
    return new AutoValue_DynamicRetrievalConfig.Builder(this);
  }

  static final class Builder extends DynamicRetrievalConfig.Builder {
    private Optional<String> mode = Optional.empty();
    private Optional<Float> dynamicThreshold = Optional.empty();
    Builder() {
    }
    Builder(DynamicRetrievalConfig source) {
      this.mode = source.mode();
      this.dynamicThreshold = source.dynamicThreshold();
    }
    @Override
    public DynamicRetrievalConfig.Builder mode(String mode) {
      this.mode = Optional.of(mode);
      return this;
    }
    @Override
    public DynamicRetrievalConfig.Builder dynamicThreshold(Float dynamicThreshold) {
      this.dynamicThreshold = Optional.of(dynamicThreshold);
      return this;
    }
    @Override
    public DynamicRetrievalConfig build() {
      return new AutoValue_DynamicRetrievalConfig(
          this.mode,
          this.dynamicThreshold);
    }
  }

}
