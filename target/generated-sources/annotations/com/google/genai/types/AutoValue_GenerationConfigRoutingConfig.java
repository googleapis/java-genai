package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_GenerationConfigRoutingConfig extends GenerationConfigRoutingConfig {

  private final Optional<GenerationConfigRoutingConfigAutoRoutingMode> autoMode;

  private final Optional<GenerationConfigRoutingConfigManualRoutingMode> manualMode;

  private AutoValue_GenerationConfigRoutingConfig(
      Optional<GenerationConfigRoutingConfigAutoRoutingMode> autoMode,
      Optional<GenerationConfigRoutingConfigManualRoutingMode> manualMode) {
    this.autoMode = autoMode;
    this.manualMode = manualMode;
  }

  @JsonProperty("autoMode")
  @Override
  public Optional<GenerationConfigRoutingConfigAutoRoutingMode> autoMode() {
    return autoMode;
  }

  @JsonProperty("manualMode")
  @Override
  public Optional<GenerationConfigRoutingConfigManualRoutingMode> manualMode() {
    return manualMode;
  }

  @Override
  public String toString() {
    return "GenerationConfigRoutingConfig{"
        + "autoMode=" + autoMode + ", "
        + "manualMode=" + manualMode
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof GenerationConfigRoutingConfig) {
      GenerationConfigRoutingConfig that = (GenerationConfigRoutingConfig) o;
      return this.autoMode.equals(that.autoMode())
          && this.manualMode.equals(that.manualMode());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= autoMode.hashCode();
    h$ *= 1000003;
    h$ ^= manualMode.hashCode();
    return h$;
  }

  @Override
  public GenerationConfigRoutingConfig.Builder toBuilder() {
    return new AutoValue_GenerationConfigRoutingConfig.Builder(this);
  }

  static final class Builder extends GenerationConfigRoutingConfig.Builder {
    private Optional<GenerationConfigRoutingConfigAutoRoutingMode> autoMode = Optional.empty();
    private Optional<GenerationConfigRoutingConfigManualRoutingMode> manualMode = Optional.empty();
    Builder() {
    }
    Builder(GenerationConfigRoutingConfig source) {
      this.autoMode = source.autoMode();
      this.manualMode = source.manualMode();
    }
    @Override
    public GenerationConfigRoutingConfig.Builder autoMode(GenerationConfigRoutingConfigAutoRoutingMode autoMode) {
      this.autoMode = Optional.of(autoMode);
      return this;
    }
    @Override
    public GenerationConfigRoutingConfig.Builder manualMode(GenerationConfigRoutingConfigManualRoutingMode manualMode) {
      this.manualMode = Optional.of(manualMode);
      return this;
    }
    @Override
    public GenerationConfigRoutingConfig build() {
      return new AutoValue_GenerationConfigRoutingConfig(
          this.autoMode,
          this.manualMode);
    }
  }

}
