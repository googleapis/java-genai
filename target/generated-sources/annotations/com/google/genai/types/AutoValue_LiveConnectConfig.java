package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_LiveConnectConfig extends LiveConnectConfig {

  private final Optional<GenerationConfig> generationConfig;

  private final Optional<List<String>> responseModalities;

  private final Optional<SpeechConfig> speechConfig;

  private final Optional<Content> systemInstruction;

  private final Optional<List<Tool>> tools;

  private AutoValue_LiveConnectConfig(
      Optional<GenerationConfig> generationConfig,
      Optional<List<String>> responseModalities,
      Optional<SpeechConfig> speechConfig,
      Optional<Content> systemInstruction,
      Optional<List<Tool>> tools) {
    this.generationConfig = generationConfig;
    this.responseModalities = responseModalities;
    this.speechConfig = speechConfig;
    this.systemInstruction = systemInstruction;
    this.tools = tools;
  }

  @JsonProperty("generationConfig")
  @Override
  public Optional<GenerationConfig> generationConfig() {
    return generationConfig;
  }

  @JsonProperty("responseModalities")
  @Override
  public Optional<List<String>> responseModalities() {
    return responseModalities;
  }

  @JsonProperty("speechConfig")
  @Override
  public Optional<SpeechConfig> speechConfig() {
    return speechConfig;
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
    return "LiveConnectConfig{"
        + "generationConfig=" + generationConfig + ", "
        + "responseModalities=" + responseModalities + ", "
        + "speechConfig=" + speechConfig + ", "
        + "systemInstruction=" + systemInstruction + ", "
        + "tools=" + tools
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof LiveConnectConfig) {
      LiveConnectConfig that = (LiveConnectConfig) o;
      return this.generationConfig.equals(that.generationConfig())
          && this.responseModalities.equals(that.responseModalities())
          && this.speechConfig.equals(that.speechConfig())
          && this.systemInstruction.equals(that.systemInstruction())
          && this.tools.equals(that.tools());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= generationConfig.hashCode();
    h$ *= 1000003;
    h$ ^= responseModalities.hashCode();
    h$ *= 1000003;
    h$ ^= speechConfig.hashCode();
    h$ *= 1000003;
    h$ ^= systemInstruction.hashCode();
    h$ *= 1000003;
    h$ ^= tools.hashCode();
    return h$;
  }

  @Override
  public LiveConnectConfig.Builder toBuilder() {
    return new AutoValue_LiveConnectConfig.Builder(this);
  }

  static final class Builder extends LiveConnectConfig.Builder {
    private Optional<GenerationConfig> generationConfig = Optional.empty();
    private Optional<List<String>> responseModalities = Optional.empty();
    private Optional<SpeechConfig> speechConfig = Optional.empty();
    private Optional<Content> systemInstruction = Optional.empty();
    private Optional<List<Tool>> tools = Optional.empty();
    Builder() {
    }
    Builder(LiveConnectConfig source) {
      this.generationConfig = source.generationConfig();
      this.responseModalities = source.responseModalities();
      this.speechConfig = source.speechConfig();
      this.systemInstruction = source.systemInstruction();
      this.tools = source.tools();
    }
    @Override
    public LiveConnectConfig.Builder generationConfig(GenerationConfig generationConfig) {
      this.generationConfig = Optional.of(generationConfig);
      return this;
    }
    @Override
    public LiveConnectConfig.Builder responseModalities(List<String> responseModalities) {
      this.responseModalities = Optional.of(responseModalities);
      return this;
    }
    @Override
    public LiveConnectConfig.Builder speechConfig(SpeechConfig speechConfig) {
      this.speechConfig = Optional.of(speechConfig);
      return this;
    }
    @Override
    public LiveConnectConfig.Builder systemInstruction(Content systemInstruction) {
      this.systemInstruction = Optional.of(systemInstruction);
      return this;
    }
    @Override
    public LiveConnectConfig.Builder tools(List<Tool> tools) {
      this.tools = Optional.of(tools);
      return this;
    }
    @Override
    public LiveConnectConfig build() {
      return new AutoValue_LiveConnectConfig(
          this.generationConfig,
          this.responseModalities,
          this.speechConfig,
          this.systemInstruction,
          this.tools);
    }
  }

}
