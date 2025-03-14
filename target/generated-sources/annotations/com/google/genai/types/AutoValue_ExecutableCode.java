package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_ExecutableCode extends ExecutableCode {

  private final Optional<String> code;

  private final Optional<String> language;

  private AutoValue_ExecutableCode(
      Optional<String> code,
      Optional<String> language) {
    this.code = code;
    this.language = language;
  }

  @JsonProperty("code")
  @Override
  public Optional<String> code() {
    return code;
  }

  @JsonProperty("language")
  @Override
  public Optional<String> language() {
    return language;
  }

  @Override
  public String toString() {
    return "ExecutableCode{"
        + "code=" + code + ", "
        + "language=" + language
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof ExecutableCode) {
      ExecutableCode that = (ExecutableCode) o;
      return this.code.equals(that.code())
          && this.language.equals(that.language());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= code.hashCode();
    h$ *= 1000003;
    h$ ^= language.hashCode();
    return h$;
  }

  @Override
  public ExecutableCode.Builder toBuilder() {
    return new AutoValue_ExecutableCode.Builder(this);
  }

  static final class Builder extends ExecutableCode.Builder {
    private Optional<String> code = Optional.empty();
    private Optional<String> language = Optional.empty();
    Builder() {
    }
    Builder(ExecutableCode source) {
      this.code = source.code();
      this.language = source.language();
    }
    @Override
    public ExecutableCode.Builder code(String code) {
      this.code = Optional.of(code);
      return this;
    }
    @Override
    public ExecutableCode.Builder language(String language) {
      this.language = Optional.of(language);
      return this;
    }
    @Override
    public ExecutableCode build() {
      return new AutoValue_ExecutableCode(
          this.code,
          this.language);
    }
  }

}
