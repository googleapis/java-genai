package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_GenerateImagesParameters extends GenerateImagesParameters {

  private final Optional<String> model;

  private final Optional<String> prompt;

  private final Optional<GenerateImagesConfig> config;

  private AutoValue_GenerateImagesParameters(
      Optional<String> model,
      Optional<String> prompt,
      Optional<GenerateImagesConfig> config) {
    this.model = model;
    this.prompt = prompt;
    this.config = config;
  }

  @JsonProperty("model")
  @Override
  public Optional<String> model() {
    return model;
  }

  @JsonProperty("prompt")
  @Override
  public Optional<String> prompt() {
    return prompt;
  }

  @JsonProperty("config")
  @Override
  public Optional<GenerateImagesConfig> config() {
    return config;
  }

  @Override
  public String toString() {
    return "GenerateImagesParameters{"
        + "model=" + model + ", "
        + "prompt=" + prompt + ", "
        + "config=" + config
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof GenerateImagesParameters) {
      GenerateImagesParameters that = (GenerateImagesParameters) o;
      return this.model.equals(that.model())
          && this.prompt.equals(that.prompt())
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
    h$ ^= prompt.hashCode();
    h$ *= 1000003;
    h$ ^= config.hashCode();
    return h$;
  }

  @Override
  public GenerateImagesParameters.Builder toBuilder() {
    return new AutoValue_GenerateImagesParameters.Builder(this);
  }

  static final class Builder extends GenerateImagesParameters.Builder {
    private Optional<String> model = Optional.empty();
    private Optional<String> prompt = Optional.empty();
    private Optional<GenerateImagesConfig> config = Optional.empty();
    Builder() {
    }
    Builder(GenerateImagesParameters source) {
      this.model = source.model();
      this.prompt = source.prompt();
      this.config = source.config();
    }
    @Override
    public GenerateImagesParameters.Builder model(String model) {
      this.model = Optional.of(model);
      return this;
    }
    @Override
    public GenerateImagesParameters.Builder prompt(String prompt) {
      this.prompt = Optional.of(prompt);
      return this;
    }
    @Override
    public GenerateImagesParameters.Builder config(GenerateImagesConfig config) {
      this.config = Optional.of(config);
      return this;
    }
    @Override
    public GenerateImagesParameters build() {
      return new AutoValue_GenerateImagesParameters(
          this.model,
          this.prompt,
          this.config);
    }
  }

}
