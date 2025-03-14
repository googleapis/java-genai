package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_ControlReferenceConfig extends ControlReferenceConfig {

  private final Optional<String> controlType;

  private final Optional<Boolean> enableControlImageComputation;

  private AutoValue_ControlReferenceConfig(
      Optional<String> controlType,
      Optional<Boolean> enableControlImageComputation) {
    this.controlType = controlType;
    this.enableControlImageComputation = enableControlImageComputation;
  }

  @JsonProperty("controlType")
  @Override
  public Optional<String> controlType() {
    return controlType;
  }

  @JsonProperty("enableControlImageComputation")
  @Override
  public Optional<Boolean> enableControlImageComputation() {
    return enableControlImageComputation;
  }

  @Override
  public String toString() {
    return "ControlReferenceConfig{"
        + "controlType=" + controlType + ", "
        + "enableControlImageComputation=" + enableControlImageComputation
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof ControlReferenceConfig) {
      ControlReferenceConfig that = (ControlReferenceConfig) o;
      return this.controlType.equals(that.controlType())
          && this.enableControlImageComputation.equals(that.enableControlImageComputation());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= controlType.hashCode();
    h$ *= 1000003;
    h$ ^= enableControlImageComputation.hashCode();
    return h$;
  }

  @Override
  public ControlReferenceConfig.Builder toBuilder() {
    return new AutoValue_ControlReferenceConfig.Builder(this);
  }

  static final class Builder extends ControlReferenceConfig.Builder {
    private Optional<String> controlType = Optional.empty();
    private Optional<Boolean> enableControlImageComputation = Optional.empty();
    Builder() {
    }
    Builder(ControlReferenceConfig source) {
      this.controlType = source.controlType();
      this.enableControlImageComputation = source.enableControlImageComputation();
    }
    @Override
    public ControlReferenceConfig.Builder controlType(String controlType) {
      this.controlType = Optional.of(controlType);
      return this;
    }
    @Override
    public ControlReferenceConfig.Builder enableControlImageComputation(boolean enableControlImageComputation) {
      this.enableControlImageComputation = Optional.of(enableControlImageComputation);
      return this;
    }
    @Override
    public ControlReferenceConfig build() {
      return new AutoValue_ControlReferenceConfig(
          this.controlType,
          this.enableControlImageComputation);
    }
  }

}
