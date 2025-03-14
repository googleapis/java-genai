package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_LiveClientSetup extends LiveClientSetup {

  private final Optional<String> model;

  private final Optional<GenerationConfig> generationConfig;

  private final Optional<Content> systemInstruction;

  private final Optional<List<Tool>> tools;

  private AutoValue_LiveClientSetup(
      Optional<String> model,
      Optional<GenerationConfig> generationConfig,
      Optional<Content> systemInstruction,
      Optional<List<Tool>> tools) {
    this.model = model;
    this.generationConfig = generationConfig;
    this.systemInstruction = systemInstruction;
    this.tools = tools;
  }

  @JsonProperty("model")
  @Override
  public Optional<String> model() {
    return model;
  }

  @JsonProperty("generationConfig")
  @Override
  public Optional<GenerationConfig> generationConfig() {
    return generationConfig;
  }

  @JsonProperty("systemInstruction")
  @Override
  public Optional<Content> systemInstruction() {
    return systemInstruction;
  }

  @JsonProperty("tools")
  @Override
  public Optional<List<Tool>> tools() {
    return tools;
  }

  @Override
  public String toString() {
    return "LiveClientSetup{"
        + "model=" + model + ", "
        + "generationConfig=" + generationConfig + ", "
        + "systemInstruction=" + systemInstruction + ", "
        + "tools=" + tools
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof LiveClientSetup) {
      LiveClientSetup that = (LiveClientSetup) o;
      return this.model.equals(that.model())
          && this.generationConfig.equals(that.generationConfig())
          && this.systemInstruction.equals(that.systemInstruction())
          && this.tools.equals(that.tools());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= model.hashCode();
    h$ *= 1000003;
    h$ ^= generationConfig.hashCode();
    h$ *= 1000003;
    h$ ^= systemInstruction.hashCode();
    h$ *= 1000003;
    h$ ^= tools.hashCode();
    return h$;
  }

  @Override
  public LiveClientSetup.Builder toBuilder() {
    return new AutoValue_LiveClientSetup.Builder(this);
  }

  static final class Builder extends LiveClientSetup.Builder {
    private Optional<String> model = Optional.empty();
    private Optional<GenerationConfig> generationConfig = Optional.empty();
    private Optional<Content> systemInstruction = Optional.empty();
    private Optional<List<Tool>> tools = Optional.empty();
    Builder() {
    }
    Builder(LiveClientSetup source) {
      this.model = source.model();
      this.generationConfig = source.generationConfig();
      this.systemInstruction = source.systemInstruction();
      this.tools = source.tools();
    }
    @Override
    public LiveClientSetup.Builder model(String model) {
      this.model = Optional.of(model);
      return this;
    }
    @Override
    public LiveClientSetup.Builder generationConfig(GenerationConfig generationConfig) {
      this.generationConfig = Optional.of(generationConfig);
      return this;
    }
    @Override
    public LiveClientSetup.Builder systemInstruction(Content systemInstruction) {
      this.systemInstruction = Optional.of(systemInstruction);
      return this;
    }
    @Override
    public LiveClientSetup.Builder tools(List<Tool> tools) {
      this.tools = Optional.of(tools);
      return this;
    }
    @Override
    public LiveClientSetup build() {
      return new AutoValue_LiveClientSetup(
          this.model,
          this.generationConfig,
          this.systemInstruction,
          this.tools);
    }
  }

}
