package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_ToolConfig extends ToolConfig {

  private final Optional<FunctionCallingConfig> functionCallingConfig;

  private AutoValue_ToolConfig(
      Optional<FunctionCallingConfig> functionCallingConfig) {
    this.functionCallingConfig = functionCallingConfig;
  }

  @JsonProperty("functionCallingConfig")
  @Override
  public Optional<FunctionCallingConfig> functionCallingConfig() {
    return functionCallingConfig;
  }

  @Override
  public String toString() {
    return "ToolConfig{"
        + "functionCallingConfig=" + functionCallingConfig
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof ToolConfig) {
      ToolConfig that = (ToolConfig) o;
      return this.functionCallingConfig.equals(that.functionCallingConfig());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= functionCallingConfig.hashCode();
    return h$;
  }

  @Override
  public ToolConfig.Builder toBuilder() {
    return new AutoValue_ToolConfig.Builder(this);
  }

  static final class Builder extends ToolConfig.Builder {
    private Optional<FunctionCallingConfig> functionCallingConfig = Optional.empty();
    Builder() {
    }
    Builder(ToolConfig source) {
      this.functionCallingConfig = source.functionCallingConfig();
    }
    @Override
    public ToolConfig.Builder functionCallingConfig(FunctionCallingConfig functionCallingConfig) {
      this.functionCallingConfig = Optional.of(functionCallingConfig);
      return this;
    }
    @Override
    public ToolConfig build() {
      return new AutoValue_ToolConfig(
          this.functionCallingConfig);
    }
  }

}
