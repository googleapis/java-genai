package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_FunctionCallingConfig extends FunctionCallingConfig {

  private final Optional<String> mode;

  private final Optional<List<String>> allowedFunctionNames;

  private AutoValue_FunctionCallingConfig(
      Optional<String> mode,
      Optional<List<String>> allowedFunctionNames) {
    this.mode = mode;
    this.allowedFunctionNames = allowedFunctionNames;
  }

  @JsonProperty("mode")
  @Override
  public Optional<String> mode() {
    return mode;
  }

  @JsonProperty("allowedFunctionNames")
  @Override
  public Optional<List<String>> allowedFunctionNames() {
    return allowedFunctionNames;
  }

  @Override
  public String toString() {
    return "FunctionCallingConfig{"
        + "mode=" + mode + ", "
        + "allowedFunctionNames=" + allowedFunctionNames
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof FunctionCallingConfig) {
      FunctionCallingConfig that = (FunctionCallingConfig) o;
      return this.mode.equals(that.mode())
          && this.allowedFunctionNames.equals(that.allowedFunctionNames());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= mode.hashCode();
    h$ *= 1000003;
    h$ ^= allowedFunctionNames.hashCode();
    return h$;
  }

  @Override
  public FunctionCallingConfig.Builder toBuilder() {
    return new AutoValue_FunctionCallingConfig.Builder(this);
  }

  static final class Builder extends FunctionCallingConfig.Builder {
    private Optional<String> mode = Optional.empty();
    private Optional<List<String>> allowedFunctionNames = Optional.empty();
    Builder() {
    }
    Builder(FunctionCallingConfig source) {
      this.mode = source.mode();
      this.allowedFunctionNames = source.allowedFunctionNames();
    }
    @Override
    public FunctionCallingConfig.Builder mode(String mode) {
      this.mode = Optional.of(mode);
      return this;
    }
    @Override
    public FunctionCallingConfig.Builder allowedFunctionNames(List<String> allowedFunctionNames) {
      this.allowedFunctionNames = Optional.of(allowedFunctionNames);
      return this;
    }
    @Override
    public FunctionCallingConfig build() {
      return new AutoValue_FunctionCallingConfig(
          this.mode,
          this.allowedFunctionNames);
    }
  }

}
