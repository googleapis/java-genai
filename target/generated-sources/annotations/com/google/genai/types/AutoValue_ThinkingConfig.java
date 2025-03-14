package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_ThinkingConfig extends ThinkingConfig {

  private final Optional<Boolean> includeThoughts;

  private AutoValue_ThinkingConfig(
      Optional<Boolean> includeThoughts) {
    this.includeThoughts = includeThoughts;
  }

  @JsonProperty("includeThoughts")
  @Override
  public Optional<Boolean> includeThoughts() {
    return includeThoughts;
  }

  @Override
  public String toString() {
    return "ThinkingConfig{"
        + "includeThoughts=" + includeThoughts
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof ThinkingConfig) {
      ThinkingConfig that = (ThinkingConfig) o;
      return this.includeThoughts.equals(that.includeThoughts());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= includeThoughts.hashCode();
    return h$;
  }

  @Override
  public ThinkingConfig.Builder toBuilder() {
    return new AutoValue_ThinkingConfig.Builder(this);
  }

  static final class Builder extends ThinkingConfig.Builder {
    private Optional<Boolean> includeThoughts = Optional.empty();
    Builder() {
    }
    Builder(ThinkingConfig source) {
      this.includeThoughts = source.includeThoughts();
    }
    @Override
    public ThinkingConfig.Builder includeThoughts(boolean includeThoughts) {
      this.includeThoughts = Optional.of(includeThoughts);
      return this;
    }
    @Override
    public ThinkingConfig build() {
      return new AutoValue_ThinkingConfig(
          this.includeThoughts);
    }
  }

}
