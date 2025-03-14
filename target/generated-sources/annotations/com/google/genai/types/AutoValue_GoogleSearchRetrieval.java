package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_GoogleSearchRetrieval extends GoogleSearchRetrieval {

  private final Optional<DynamicRetrievalConfig> dynamicRetrievalConfig;

  private AutoValue_GoogleSearchRetrieval(
      Optional<DynamicRetrievalConfig> dynamicRetrievalConfig) {
    this.dynamicRetrievalConfig = dynamicRetrievalConfig;
  }

  @JsonProperty("dynamicRetrievalConfig")
  @Override
  public Optional<DynamicRetrievalConfig> dynamicRetrievalConfig() {
    return dynamicRetrievalConfig;
  }

  @Override
  public String toString() {
    return "GoogleSearchRetrieval{"
        + "dynamicRetrievalConfig=" + dynamicRetrievalConfig
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof GoogleSearchRetrieval) {
      GoogleSearchRetrieval that = (GoogleSearchRetrieval) o;
      return this.dynamicRetrievalConfig.equals(that.dynamicRetrievalConfig());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= dynamicRetrievalConfig.hashCode();
    return h$;
  }

  @Override
  public GoogleSearchRetrieval.Builder toBuilder() {
    return new AutoValue_GoogleSearchRetrieval.Builder(this);
  }

  static final class Builder extends GoogleSearchRetrieval.Builder {
    private Optional<DynamicRetrievalConfig> dynamicRetrievalConfig = Optional.empty();
    Builder() {
    }
    Builder(GoogleSearchRetrieval source) {
      this.dynamicRetrievalConfig = source.dynamicRetrievalConfig();
    }
    @Override
    public GoogleSearchRetrieval.Builder dynamicRetrievalConfig(DynamicRetrievalConfig dynamicRetrievalConfig) {
      this.dynamicRetrievalConfig = Optional.of(dynamicRetrievalConfig);
      return this;
    }
    @Override
    public GoogleSearchRetrieval build() {
      return new AutoValue_GoogleSearchRetrieval(
          this.dynamicRetrievalConfig);
    }
  }

}
