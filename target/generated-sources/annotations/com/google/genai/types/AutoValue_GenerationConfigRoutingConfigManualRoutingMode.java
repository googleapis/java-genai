package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_GenerationConfigRoutingConfigManualRoutingMode extends GenerationConfigRoutingConfigManualRoutingMode {

  private final Optional<String> modelName;

  private AutoValue_GenerationConfigRoutingConfigManualRoutingMode(
      Optional<String> modelName) {
    this.modelName = modelName;
  }

  @JsonProperty("modelName")
  @Override
  public Optional<String> modelName() {
    return modelName;
  }

  @Override
  public String toString() {
    return "GenerationConfigRoutingConfigManualRoutingMode{"
        + "modelName=" + modelName
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof GenerationConfigRoutingConfigManualRoutingMode) {
      GenerationConfigRoutingConfigManualRoutingMode that = (GenerationConfigRoutingConfigManualRoutingMode) o;
      return this.modelName.equals(that.modelName());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= modelName.hashCode();
    return h$;
  }

  @Override
  public GenerationConfigRoutingConfigManualRoutingMode.Builder toBuilder() {
    return new AutoValue_GenerationConfigRoutingConfigManualRoutingMode.Builder(this);
  }

  static final class Builder extends GenerationConfigRoutingConfigManualRoutingMode.Builder {
    private Optional<String> modelName = Optional.empty();
    Builder() {
    }
    Builder(GenerationConfigRoutingConfigManualRoutingMode source) {
      this.modelName = source.modelName();
    }
    @Override
    public GenerationConfigRoutingConfigManualRoutingMode.Builder modelName(String modelName) {
      this.modelName = Optional.of(modelName);
      return this;
    }
    @Override
    public GenerationConfigRoutingConfigManualRoutingMode build() {
      return new AutoValue_GenerationConfigRoutingConfigManualRoutingMode(
          this.modelName);
    }
  }

}
