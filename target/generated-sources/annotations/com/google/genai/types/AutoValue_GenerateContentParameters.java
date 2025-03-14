package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_GenerateContentParameters extends GenerateContentParameters {

  private final Optional<String> model;

  private final Optional<List<Content>> contents;

  private final Optional<GenerateContentConfig> config;

  private AutoValue_GenerateContentParameters(
      Optional<String> model,
      Optional<List<Content>> contents,
      Optional<GenerateContentConfig> config) {
    this.model = model;
    this.contents = contents;
    this.config = config;
  }

  @JsonProperty("model")
  @Override
  public Optional<String> model() {
    return model;
  }

  @JsonProperty("contents")
  @Override
  public Optional<List<Content>> contents() {
    return contents;
  }

  @JsonProperty("config")
  @Override
  public Optional<GenerateContentConfig> config() {
    return config;
  }

  @Override
  public String toString() {
    return "GenerateContentParameters{"
        + "model=" + model + ", "
        + "contents=" + contents + ", "
        + "config=" + config
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof GenerateContentParameters) {
      GenerateContentParameters that = (GenerateContentParameters) o;
      return this.model.equals(that.model())
          && this.contents.equals(that.contents())
          && this.config.equals(that.config());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= model.hashCode();
    h$ *= 1000003;
    h$ ^= contents.hashCode();
    h$ *= 1000003;
    h$ ^= config.hashCode();
    return h$;
  }

  @Override
  public GenerateContentParameters.Builder toBuilder() {
    return new AutoValue_GenerateContentParameters.Builder(this);
  }

  static final class Builder extends GenerateContentParameters.Builder {
    private Optional<String> model = Optional.empty();
    private Optional<List<Content>> contents = Optional.empty();
    private Optional<GenerateContentConfig> config = Optional.empty();
    Builder() {
    }
    Builder(GenerateContentParameters source) {
      this.model = source.model();
      this.contents = source.contents();
      this.config = source.config();
    }
    @Override
    public GenerateContentParameters.Builder model(String model) {
      this.model = Optional.of(model);
      return this;
    }
    @Override
    public GenerateContentParameters.Builder contents(List<Content> contents) {
      this.contents = Optional.of(contents);
      return this;
    }
    @Override
    public GenerateContentParameters.Builder config(GenerateContentConfig config) {
      this.config = Optional.of(config);
      return this;
    }
    @Override
    public GenerateContentParameters build() {
      return new AutoValue_GenerateContentParameters(
          this.model,
          this.contents,
          this.config);
    }
  }

}
