package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_StyleReferenceConfig extends StyleReferenceConfig {

  private final Optional<String> styleDescription;

  private AutoValue_StyleReferenceConfig(
      Optional<String> styleDescription) {
    this.styleDescription = styleDescription;
  }

  @JsonProperty("styleDescription")
  @Override
  public Optional<String> styleDescription() {
    return styleDescription;
  }

  @Override
  public String toString() {
    return "StyleReferenceConfig{"
        + "styleDescription=" + styleDescription
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof StyleReferenceConfig) {
      StyleReferenceConfig that = (StyleReferenceConfig) o;
      return this.styleDescription.equals(that.styleDescription());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= styleDescription.hashCode();
    return h$;
  }

  @Override
  public StyleReferenceConfig.Builder toBuilder() {
    return new AutoValue_StyleReferenceConfig.Builder(this);
  }

  static final class Builder extends StyleReferenceConfig.Builder {
    private Optional<String> styleDescription = Optional.empty();
    Builder() {
    }
    Builder(StyleReferenceConfig source) {
      this.styleDescription = source.styleDescription();
    }
    @Override
    public StyleReferenceConfig.Builder styleDescription(String styleDescription) {
      this.styleDescription = Optional.of(styleDescription);
      return this;
    }
    @Override
    public StyleReferenceConfig build() {
      return new AutoValue_StyleReferenceConfig(
          this.styleDescription);
    }
  }

}
