package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_GenerateContentConfig extends GenerateContentConfig {

  private final Optional<Content> systemInstruction;

  private final Optional<Float> temperature;

  private final Optional<Float> topP;

  private final Optional<Float> topK;

  private final Optional<Integer> candidateCount;

  private final Optional<Integer> maxOutputTokens;

  private final Optional<List<String>> stopSequences;

  private final Optional<Boolean> responseLogprobs;

  private final Optional<Integer> logprobs;

  private final Optional<Float> presencePenalty;

  private final Optional<Float> frequencyPenalty;

  private final Optional<Integer> seed;

  private final Optional<String> responseMimeType;

  private final Optional<Schema> responseSchema;

  private final Optional<GenerationConfigRoutingConfig> routingConfig;

  private final Optional<List<SafetySetting>> safetySettings;

  private final Optional<List<Tool>> tools;

  private final Optional<ToolConfig> toolConfig;

  private final Optional<Map<String, String>> labels;

  private final Optional<String> cachedContent;

  private final Optional<List<String>> responseModalities;

  private final Optional<String> mediaResolution;

  private final Optional<SpeechConfig> speechConfig;

  private final Optional<Boolean> audioTimestamp;

  private final Optional<ThinkingConfig> thinkingConfig;

  private AutoValue_GenerateContentConfig(
      Optional<Content> systemInstruction,
      Optional<Float> temperature,
      Optional<Float> topP,
      Optional<Float> topK,
      Optional<Integer> candidateCount,
      Optional<Integer> maxOutputTokens,
      Optional<List<String>> stopSequences,
      Optional<Boolean> responseLogprobs,
      Optional<Integer> logprobs,
      Optional<Float> presencePenalty,
      Optional<Float> frequencyPenalty,
      Optional<Integer> seed,
      Optional<String> responseMimeType,
      Optional<Schema> responseSchema,
      Optional<GenerationConfigRoutingConfig> routingConfig,
      Optional<List<SafetySetting>> safetySettings,
      Optional<List<Tool>> tools,
      Optional<ToolConfig> toolConfig,
      Optional<Map<String, String>> labels,
      Optional<String> cachedContent,
      Optional<List<String>> responseModalities,
      Optional<String> mediaResolution,
      Optional<SpeechConfig> speechConfig,
      Optional<Boolean> audioTimestamp,
      Optional<ThinkingConfig> thinkingConfig) {
    this.systemInstruction = systemInstruction;
    this.temperature = temperature;
    this.topP = topP;
    this.topK = topK;
    this.candidateCount = candidateCount;
    this.maxOutputTokens = maxOutputTokens;
    this.stopSequences = stopSequences;
    this.responseLogprobs = responseLogprobs;
    this.logprobs = logprobs;
    this.presencePenalty = presencePenalty;
    this.frequencyPenalty = frequencyPenalty;
    this.seed = seed;
    this.responseMimeType = responseMimeType;
    this.responseSchema = responseSchema;
    this.routingConfig = routingConfig;
    this.safetySettings = safetySettings;
    this.tools = tools;
    this.toolConfig = toolConfig;
    this.labels = labels;
    this.cachedContent = cachedContent;
    this.responseModalities = responseModalities;
    this.mediaResolution = mediaResolution;
    this.speechConfig = speechConfig;
    this.audioTimestamp = audioTimestamp;
    this.thinkingConfig = thinkingConfig;
  }

  @JsonProperty("systemInstruction")
  @Override
  public Optional<Content> systemInstruction() {
    return systemInstruction;
  }

  @JsonProperty("temperature")
  @Override
  public Optional<Float> temperature() {
    return temperature;
  }

  @JsonProperty("topP")
  @Override
  public Optional<Float> topP() {
    return topP;
  }

  @JsonProperty("topK")
  @Override
  public Optional<Float> topK() {
    return topK;
  }

  @JsonProperty("candidateCount")
  @Override
  public Optional<Integer> candidateCount() {
    return candidateCount;
  }

  @JsonProperty("maxOutputTokens")
  @Override
  public Optional<Integer> maxOutputTokens() {
    return maxOutputTokens;
  }

  @JsonProperty("stopSequences")
  @Override
  public Optional<List<String>> stopSequences() {
    return stopSequences;
  }

  @JsonProperty("responseLogprobs")
  @Override
  public Optional<Boolean> responseLogprobs() {
    return responseLogprobs;
  }

  @JsonProperty("logprobs")
  @Override
  public Optional<Integer> logprobs() {
    return logprobs;
  }

  @JsonProperty("presencePenalty")
  @Override
  public Optional<Float> presencePenalty() {
    return presencePenalty;
  }

  @JsonProperty("frequencyPenalty")
  @Override
  public Optional<Float> frequencyPenalty() {
    return frequencyPenalty;
  }

  @JsonProperty("seed")
  @Override
  public Optional<Integer> seed() {
    return seed;
  }

  @JsonProperty("responseMimeType")
  @Override
  public Optional<String> responseMimeType() {
    return responseMimeType;
  }

  @JsonProperty("responseSchema")
  @Override
  public Optional<Schema> responseSchema() {
    return responseSchema;
  }

  @JsonProperty("routingConfig")
  @Override
  public Optional<GenerationConfigRoutingConfig> routingConfig() {
    return routingConfig;
  }

  @JsonProperty("safetySettings")
  @Override
  public Optional<List<SafetySetting>> safetySettings() {
    return safetySettings;
  }

  @JsonProperty("tools")
  @Override
  public Optional<List<Tool>> tools() {
    return tools;
  }

  @JsonProperty("toolConfig")
  @Override
  public Optional<ToolConfig> toolConfig() {
    return toolConfig;
  }

  @JsonProperty("labels")
  @Override
  public Optional<Map<String, String>> labels() {
    return labels;
  }

  @JsonProperty("cachedContent")
  @Override
  public Optional<String> cachedContent() {
    return cachedContent;
  }

  @JsonProperty("responseModalities")
  @Override
  public Optional<List<String>> responseModalities() {
    return responseModalities;
  }

  @JsonProperty("mediaResolution")
  @Override
  public Optional<String> mediaResolution() {
    return mediaResolution;
  }

  @JsonProperty("speechConfig")
  @Override
  public Optional<SpeechConfig> speechConfig() {
    return speechConfig;
  }

  @JsonProperty("audioTimestamp")
  @Override
  public Optional<Boolean> audioTimestamp() {
    return audioTimestamp;
  }

  @JsonProperty("thinkingConfig")
  @Override
  public Optional<ThinkingConfig> thinkingConfig() {
    return thinkingConfig;
  }

  @Override
  public String toString() {
    return "GenerateContentConfig{"
        + "systemInstruction=" + systemInstruction + ", "
        + "temperature=" + temperature + ", "
        + "topP=" + topP + ", "
        + "topK=" + topK + ", "
        + "candidateCount=" + candidateCount + ", "
        + "maxOutputTokens=" + maxOutputTokens + ", "
        + "stopSequences=" + stopSequences + ", "
        + "responseLogprobs=" + responseLogprobs + ", "
        + "logprobs=" + logprobs + ", "
        + "presencePenalty=" + presencePenalty + ", "
        + "frequencyPenalty=" + frequencyPenalty + ", "
        + "seed=" + seed + ", "
        + "responseMimeType=" + responseMimeType + ", "
        + "responseSchema=" + responseSchema + ", "
        + "routingConfig=" + routingConfig + ", "
        + "safetySettings=" + safetySettings + ", "
        + "tools=" + tools + ", "
        + "toolConfig=" + toolConfig + ", "
        + "labels=" + labels + ", "
        + "cachedContent=" + cachedContent + ", "
        + "responseModalities=" + responseModalities + ", "
        + "mediaResolution=" + mediaResolution + ", "
        + "speechConfig=" + speechConfig + ", "
        + "audioTimestamp=" + audioTimestamp + ", "
        + "thinkingConfig=" + thinkingConfig
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof GenerateContentConfig) {
      GenerateContentConfig that = (GenerateContentConfig) o;
      return this.systemInstruction.equals(that.systemInstruction())
          && this.temperature.equals(that.temperature())
          && this.topP.equals(that.topP())
          && this.topK.equals(that.topK())
          && this.candidateCount.equals(that.candidateCount())
          && this.maxOutputTokens.equals(that.maxOutputTokens())
          && this.stopSequences.equals(that.stopSequences())
          && this.responseLogprobs.equals(that.responseLogprobs())
          && this.logprobs.equals(that.logprobs())
          && this.presencePenalty.equals(that.presencePenalty())
          && this.frequencyPenalty.equals(that.frequencyPenalty())
          && this.seed.equals(that.seed())
          && this.responseMimeType.equals(that.responseMimeType())
          && this.responseSchema.equals(that.responseSchema())
          && this.routingConfig.equals(that.routingConfig())
          && this.safetySettings.equals(that.safetySettings())
          && this.tools.equals(that.tools())
          && this.toolConfig.equals(that.toolConfig())
          && this.labels.equals(that.labels())
          && this.cachedContent.equals(that.cachedContent())
          && this.responseModalities.equals(that.responseModalities())
          && this.mediaResolution.equals(that.mediaResolution())
          && this.speechConfig.equals(that.speechConfig())
          && this.audioTimestamp.equals(that.audioTimestamp())
          && this.thinkingConfig.equals(that.thinkingConfig());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= systemInstruction.hashCode();
    h$ *= 1000003;
    h$ ^= temperature.hashCode();
    h$ *= 1000003;
    h$ ^= topP.hashCode();
    h$ *= 1000003;
    h$ ^= topK.hashCode();
    h$ *= 1000003;
    h$ ^= candidateCount.hashCode();
    h$ *= 1000003;
    h$ ^= maxOutputTokens.hashCode();
    h$ *= 1000003;
    h$ ^= stopSequences.hashCode();
    h$ *= 1000003;
    h$ ^= responseLogprobs.hashCode();
    h$ *= 1000003;
    h$ ^= logprobs.hashCode();
    h$ *= 1000003;
    h$ ^= presencePenalty.hashCode();
    h$ *= 1000003;
    h$ ^= frequencyPenalty.hashCode();
    h$ *= 1000003;
    h$ ^= seed.hashCode();
    h$ *= 1000003;
    h$ ^= responseMimeType.hashCode();
    h$ *= 1000003;
    h$ ^= responseSchema.hashCode();
    h$ *= 1000003;
    h$ ^= routingConfig.hashCode();
    h$ *= 1000003;
    h$ ^= safetySettings.hashCode();
    h$ *= 1000003;
    h$ ^= tools.hashCode();
    h$ *= 1000003;
    h$ ^= toolConfig.hashCode();
    h$ *= 1000003;
    h$ ^= labels.hashCode();
    h$ *= 1000003;
    h$ ^= cachedContent.hashCode();
    h$ *= 1000003;
    h$ ^= responseModalities.hashCode();
    h$ *= 1000003;
    h$ ^= mediaResolution.hashCode();
    h$ *= 1000003;
    h$ ^= speechConfig.hashCode();
    h$ *= 1000003;
    h$ ^= audioTimestamp.hashCode();
    h$ *= 1000003;
    h$ ^= thinkingConfig.hashCode();
    return h$;
  }

  @Override
  public GenerateContentConfig.Builder toBuilder() {
    return new AutoValue_GenerateContentConfig.Builder(this);
  }

  static final class Builder extends GenerateContentConfig.Builder {
    private Optional<Content> systemInstruction = Optional.empty();
    private Optional<Float> temperature = Optional.empty();
    private Optional<Float> topP = Optional.empty();
    private Optional<Float> topK = Optional.empty();
    private Optional<Integer> candidateCount = Optional.empty();
    private Optional<Integer> maxOutputTokens = Optional.empty();
    private Optional<List<String>> stopSequences = Optional.empty();
    private Optional<Boolean> responseLogprobs = Optional.empty();
    private Optional<Integer> logprobs = Optional.empty();
    private Optional<Float> presencePenalty = Optional.empty();
    private Optional<Float> frequencyPenalty = Optional.empty();
    private Optional<Integer> seed = Optional.empty();
    private Optional<String> responseMimeType = Optional.empty();
    private Optional<Schema> responseSchema = Optional.empty();
    private Optional<GenerationConfigRoutingConfig> routingConfig = Optional.empty();
    private Optional<List<SafetySetting>> safetySettings = Optional.empty();
    private Optional<List<Tool>> tools = Optional.empty();
    private Optional<ToolConfig> toolConfig = Optional.empty();
    private Optional<Map<String, String>> labels = Optional.empty();
    private Optional<String> cachedContent = Optional.empty();
    private Optional<List<String>> responseModalities = Optional.empty();
    private Optional<String> mediaResolution = Optional.empty();
    private Optional<SpeechConfig> speechConfig = Optional.empty();
    private Optional<Boolean> audioTimestamp = Optional.empty();
    private Optional<ThinkingConfig> thinkingConfig = Optional.empty();
    Builder() {
    }
    Builder(GenerateContentConfig source) {
      this.systemInstruction = source.systemInstruction();
      this.temperature = source.temperature();
      this.topP = source.topP();
      this.topK = source.topK();
      this.candidateCount = source.candidateCount();
      this.maxOutputTokens = source.maxOutputTokens();
      this.stopSequences = source.stopSequences();
      this.responseLogprobs = source.responseLogprobs();
      this.logprobs = source.logprobs();
      this.presencePenalty = source.presencePenalty();
      this.frequencyPenalty = source.frequencyPenalty();
      this.seed = source.seed();
      this.responseMimeType = source.responseMimeType();
      this.responseSchema = source.responseSchema();
      this.routingConfig = source.routingConfig();
      this.safetySettings = source.safetySettings();
      this.tools = source.tools();
      this.toolConfig = source.toolConfig();
      this.labels = source.labels();
      this.cachedContent = source.cachedContent();
      this.responseModalities = source.responseModalities();
      this.mediaResolution = source.mediaResolution();
      this.speechConfig = source.speechConfig();
      this.audioTimestamp = source.audioTimestamp();
      this.thinkingConfig = source.thinkingConfig();
    }
    @Override
    public GenerateContentConfig.Builder systemInstruction(Content systemInstruction) {
      this.systemInstruction = Optional.of(systemInstruction);
      return this;
    }
    @Override
    public GenerateContentConfig.Builder temperature(Float temperature) {
      this.temperature = Optional.of(temperature);
      return this;
    }
    @Override
    public GenerateContentConfig.Builder topP(Float topP) {
      this.topP = Optional.of(topP);
      return this;
    }
    @Override
    public GenerateContentConfig.Builder topK(Float topK) {
      this.topK = Optional.of(topK);
      return this;
    }
    @Override
    public GenerateContentConfig.Builder candidateCount(Integer candidateCount) {
      this.candidateCount = Optional.of(candidateCount);
      return this;
    }
    @Override
    public GenerateContentConfig.Builder maxOutputTokens(Integer maxOutputTokens) {
      this.maxOutputTokens = Optional.of(maxOutputTokens);
      return this;
    }
    @Override
    public GenerateContentConfig.Builder stopSequences(List<String> stopSequences) {
      this.stopSequences = Optional.of(stopSequences);
      return this;
    }
    @Override
    public GenerateContentConfig.Builder responseLogprobs(boolean responseLogprobs) {
      this.responseLogprobs = Optional.of(responseLogprobs);
      return this;
    }
    @Override
    public GenerateContentConfig.Builder logprobs(Integer logprobs) {
      this.logprobs = Optional.of(logprobs);
      return this;
    }
    @Override
    public GenerateContentConfig.Builder presencePenalty(Float presencePenalty) {
      this.presencePenalty = Optional.of(presencePenalty);
      return this;
    }
    @Override
    public GenerateContentConfig.Builder frequencyPenalty(Float frequencyPenalty) {
      this.frequencyPenalty = Optional.of(frequencyPenalty);
      return this;
    }
    @Override
    public GenerateContentConfig.Builder seed(Integer seed) {
      this.seed = Optional.of(seed);
      return this;
    }
    @Override
    public GenerateContentConfig.Builder responseMimeType(String responseMimeType) {
      this.responseMimeType = Optional.of(responseMimeType);
      return this;
    }
    @Override
    public GenerateContentConfig.Builder responseSchema(Schema responseSchema) {
      this.responseSchema = Optional.of(responseSchema);
      return this;
    }
    @Override
    public GenerateContentConfig.Builder routingConfig(GenerationConfigRoutingConfig routingConfig) {
      this.routingConfig = Optional.of(routingConfig);
      return this;
    }
    @Override
    public GenerateContentConfig.Builder safetySettings(List<SafetySetting> safetySettings) {
      this.safetySettings = Optional.of(safetySettings);
      return this;
    }
    @Override
    public GenerateContentConfig.Builder tools(List<Tool> tools) {
      this.tools = Optional.of(tools);
      return this;
    }
    @Override
    public GenerateContentConfig.Builder toolConfig(ToolConfig toolConfig) {
      this.toolConfig = Optional.of(toolConfig);
      return this;
    }
    @Override
    public GenerateContentConfig.Builder labels(Map<String, String> labels) {
      this.labels = Optional.of(labels);
      return this;
    }
    @Override
    public GenerateContentConfig.Builder cachedContent(String cachedContent) {
      this.cachedContent = Optional.of(cachedContent);
      return this;
    }
    @Override
    public GenerateContentConfig.Builder responseModalities(List<String> responseModalities) {
      this.responseModalities = Optional.of(responseModalities);
      return this;
    }
    @Override
    public GenerateContentConfig.Builder mediaResolution(String mediaResolution) {
      this.mediaResolution = Optional.of(mediaResolution);
      return this;
    }
    @Override
    public GenerateContentConfig.Builder speechConfig(SpeechConfig speechConfig) {
      this.speechConfig = Optional.of(speechConfig);
      return this;
    }
    @Override
    public GenerateContentConfig.Builder audioTimestamp(boolean audioTimestamp) {
      this.audioTimestamp = Optional.of(audioTimestamp);
      return this;
    }
    @Override
    public GenerateContentConfig.Builder thinkingConfig(ThinkingConfig thinkingConfig) {
      this.thinkingConfig = Optional.of(thinkingConfig);
      return this;
    }
    @Override
    public GenerateContentConfig build() {
      return new AutoValue_GenerateContentConfig(
          this.systemInstruction,
          this.temperature,
          this.topP,
          this.topK,
          this.candidateCount,
          this.maxOutputTokens,
          this.stopSequences,
          this.responseLogprobs,
          this.logprobs,
          this.presencePenalty,
          this.frequencyPenalty,
          this.seed,
          this.responseMimeType,
          this.responseSchema,
          this.routingConfig,
          this.safetySettings,
          this.tools,
          this.toolConfig,
          this.labels,
          this.cachedContent,
          this.responseModalities,
          this.mediaResolution,
          this.speechConfig,
          this.audioTimestamp,
          this.thinkingConfig);
    }
  }

}
