package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_GenerationConfig extends GenerationConfig {

  private final Optional<Boolean> audioTimestamp;

  private final Optional<Integer> candidateCount;

  private final Optional<Float> frequencyPenalty;

  private final Optional<Integer> logprobs;

  private final Optional<Integer> maxOutputTokens;

  private final Optional<Float> presencePenalty;

  private final Optional<Boolean> responseLogprobs;

  private final Optional<String> responseMimeType;

  private final Optional<Schema> responseSchema;

  private final Optional<GenerationConfigRoutingConfig> routingConfig;

  private final Optional<Integer> seed;

  private final Optional<List<String>> stopSequences;

  private final Optional<Float> temperature;

  private final Optional<Float> topK;

  private final Optional<Float> topP;

  private AutoValue_GenerationConfig(
      Optional<Boolean> audioTimestamp,
      Optional<Integer> candidateCount,
      Optional<Float> frequencyPenalty,
      Optional<Integer> logprobs,
      Optional<Integer> maxOutputTokens,
      Optional<Float> presencePenalty,
      Optional<Boolean> responseLogprobs,
      Optional<String> responseMimeType,
      Optional<Schema> responseSchema,
      Optional<GenerationConfigRoutingConfig> routingConfig,
      Optional<Integer> seed,
      Optional<List<String>> stopSequences,
      Optional<Float> temperature,
      Optional<Float> topK,
      Optional<Float> topP) {
    this.audioTimestamp = audioTimestamp;
    this.candidateCount = candidateCount;
    this.frequencyPenalty = frequencyPenalty;
    this.logprobs = logprobs;
    this.maxOutputTokens = maxOutputTokens;
    this.presencePenalty = presencePenalty;
    this.responseLogprobs = responseLogprobs;
    this.responseMimeType = responseMimeType;
    this.responseSchema = responseSchema;
    this.routingConfig = routingConfig;
    this.seed = seed;
    this.stopSequences = stopSequences;
    this.temperature = temperature;
    this.topK = topK;
    this.topP = topP;
  }

  @JsonProperty("audioTimestamp")
  @Override
  public Optional<Boolean> audioTimestamp() {
    return audioTimestamp;
  }

  @JsonProperty("candidateCount")
  @Override
  public Optional<Integer> candidateCount() {
    return candidateCount;
  }

  @JsonProperty("frequencyPenalty")
  @Override
  public Optional<Float> frequencyPenalty() {
    return frequencyPenalty;
  }

  @JsonProperty("logprobs")
  @Override
  public Optional<Integer> logprobs() {
    return logprobs;
  }

  @JsonProperty("maxOutputTokens")
  @Override
  public Optional<Integer> maxOutputTokens() {
    return maxOutputTokens;
  }

  @JsonProperty("presencePenalty")
  @Override
  public Optional<Float> presencePenalty() {
    return presencePenalty;
  }

  @JsonProperty("responseLogprobs")
  @Override
  public Optional<Boolean> responseLogprobs() {
    return responseLogprobs;
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

  @JsonProperty("seed")
  @Override
  public Optional<Integer> seed() {
    return seed;
  }

  @JsonProperty("stopSequences")
  @Override
  public Optional<List<String>> stopSequences() {
    return stopSequences;
  }

  @JsonProperty("temperature")
  @Override
  public Optional<Float> temperature() {
    return temperature;
  }

  @JsonProperty("topK")
  @Override
  public Optional<Float> topK() {
    return topK;
  }

  @JsonProperty("topP")
  @Override
  public Optional<Float> topP() {
    return topP;
  }

  @Override
  public String toString() {
    return "GenerationConfig{"
        + "audioTimestamp=" + audioTimestamp + ", "
        + "candidateCount=" + candidateCount + ", "
        + "frequencyPenalty=" + frequencyPenalty + ", "
        + "logprobs=" + logprobs + ", "
        + "maxOutputTokens=" + maxOutputTokens + ", "
        + "presencePenalty=" + presencePenalty + ", "
        + "responseLogprobs=" + responseLogprobs + ", "
        + "responseMimeType=" + responseMimeType + ", "
        + "responseSchema=" + responseSchema + ", "
        + "routingConfig=" + routingConfig + ", "
        + "seed=" + seed + ", "
        + "stopSequences=" + stopSequences + ", "
        + "temperature=" + temperature + ", "
        + "topK=" + topK + ", "
        + "topP=" + topP
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof GenerationConfig) {
      GenerationConfig that = (GenerationConfig) o;
      return this.audioTimestamp.equals(that.audioTimestamp())
          && this.candidateCount.equals(that.candidateCount())
          && this.frequencyPenalty.equals(that.frequencyPenalty())
          && this.logprobs.equals(that.logprobs())
          && this.maxOutputTokens.equals(that.maxOutputTokens())
          && this.presencePenalty.equals(that.presencePenalty())
          && this.responseLogprobs.equals(that.responseLogprobs())
          && this.responseMimeType.equals(that.responseMimeType())
          && this.responseSchema.equals(that.responseSchema())
          && this.routingConfig.equals(that.routingConfig())
          && this.seed.equals(that.seed())
          && this.stopSequences.equals(that.stopSequences())
          && this.temperature.equals(that.temperature())
          && this.topK.equals(that.topK())
          && this.topP.equals(that.topP());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= audioTimestamp.hashCode();
    h$ *= 1000003;
    h$ ^= candidateCount.hashCode();
    h$ *= 1000003;
    h$ ^= frequencyPenalty.hashCode();
    h$ *= 1000003;
    h$ ^= logprobs.hashCode();
    h$ *= 1000003;
    h$ ^= maxOutputTokens.hashCode();
    h$ *= 1000003;
    h$ ^= presencePenalty.hashCode();
    h$ *= 1000003;
    h$ ^= responseLogprobs.hashCode();
    h$ *= 1000003;
    h$ ^= responseMimeType.hashCode();
    h$ *= 1000003;
    h$ ^= responseSchema.hashCode();
    h$ *= 1000003;
    h$ ^= routingConfig.hashCode();
    h$ *= 1000003;
    h$ ^= seed.hashCode();
    h$ *= 1000003;
    h$ ^= stopSequences.hashCode();
    h$ *= 1000003;
    h$ ^= temperature.hashCode();
    h$ *= 1000003;
    h$ ^= topK.hashCode();
    h$ *= 1000003;
    h$ ^= topP.hashCode();
    return h$;
  }

  @Override
  public GenerationConfig.Builder toBuilder() {
    return new AutoValue_GenerationConfig.Builder(this);
  }

  static final class Builder extends GenerationConfig.Builder {
    private Optional<Boolean> audioTimestamp = Optional.empty();
    private Optional<Integer> candidateCount = Optional.empty();
    private Optional<Float> frequencyPenalty = Optional.empty();
    private Optional<Integer> logprobs = Optional.empty();
    private Optional<Integer> maxOutputTokens = Optional.empty();
    private Optional<Float> presencePenalty = Optional.empty();
    private Optional<Boolean> responseLogprobs = Optional.empty();
    private Optional<String> responseMimeType = Optional.empty();
    private Optional<Schema> responseSchema = Optional.empty();
    private Optional<GenerationConfigRoutingConfig> routingConfig = Optional.empty();
    private Optional<Integer> seed = Optional.empty();
    private Optional<List<String>> stopSequences = Optional.empty();
    private Optional<Float> temperature = Optional.empty();
    private Optional<Float> topK = Optional.empty();
    private Optional<Float> topP = Optional.empty();
    Builder() {
    }
    Builder(GenerationConfig source) {
      this.audioTimestamp = source.audioTimestamp();
      this.candidateCount = source.candidateCount();
      this.frequencyPenalty = source.frequencyPenalty();
      this.logprobs = source.logprobs();
      this.maxOutputTokens = source.maxOutputTokens();
      this.presencePenalty = source.presencePenalty();
      this.responseLogprobs = source.responseLogprobs();
      this.responseMimeType = source.responseMimeType();
      this.responseSchema = source.responseSchema();
      this.routingConfig = source.routingConfig();
      this.seed = source.seed();
      this.stopSequences = source.stopSequences();
      this.temperature = source.temperature();
      this.topK = source.topK();
      this.topP = source.topP();
    }
    @Override
    public GenerationConfig.Builder audioTimestamp(boolean audioTimestamp) {
      this.audioTimestamp = Optional.of(audioTimestamp);
      return this;
    }
    @Override
    public GenerationConfig.Builder candidateCount(Integer candidateCount) {
      this.candidateCount = Optional.of(candidateCount);
      return this;
    }
    @Override
    public GenerationConfig.Builder frequencyPenalty(Float frequencyPenalty) {
      this.frequencyPenalty = Optional.of(frequencyPenalty);
      return this;
    }
    @Override
    public GenerationConfig.Builder logprobs(Integer logprobs) {
      this.logprobs = Optional.of(logprobs);
      return this;
    }
    @Override
    public GenerationConfig.Builder maxOutputTokens(Integer maxOutputTokens) {
      this.maxOutputTokens = Optional.of(maxOutputTokens);
      return this;
    }
    @Override
    public GenerationConfig.Builder presencePenalty(Float presencePenalty) {
      this.presencePenalty = Optional.of(presencePenalty);
      return this;
    }
    @Override
    public GenerationConfig.Builder responseLogprobs(boolean responseLogprobs) {
      this.responseLogprobs = Optional.of(responseLogprobs);
      return this;
    }
    @Override
    public GenerationConfig.Builder responseMimeType(String responseMimeType) {
      this.responseMimeType = Optional.of(responseMimeType);
      return this;
    }
    @Override
    public GenerationConfig.Builder responseSchema(Schema responseSchema) {
      this.responseSchema = Optional.of(responseSchema);
      return this;
    }
    @Override
    public GenerationConfig.Builder routingConfig(GenerationConfigRoutingConfig routingConfig) {
      this.routingConfig = Optional.of(routingConfig);
      return this;
    }
    @Override
    public GenerationConfig.Builder seed(Integer seed) {
      this.seed = Optional.of(seed);
      return this;
    }
    @Override
    public GenerationConfig.Builder stopSequences(List<String> stopSequences) {
      this.stopSequences = Optional.of(stopSequences);
      return this;
    }
    @Override
    public GenerationConfig.Builder temperature(Float temperature) {
      this.temperature = Optional.of(temperature);
      return this;
    }
    @Override
    public GenerationConfig.Builder topK(Float topK) {
      this.topK = Optional.of(topK);
      return this;
    }
    @Override
    public GenerationConfig.Builder topP(Float topP) {
      this.topP = Optional.of(topP);
      return this;
    }
    @Override
    public GenerationConfig build() {
      return new AutoValue_GenerationConfig(
          this.audioTimestamp,
          this.candidateCount,
          this.frequencyPenalty,
          this.logprobs,
          this.maxOutputTokens,
          this.presencePenalty,
          this.responseLogprobs,
          this.responseMimeType,
          this.responseSchema,
          this.routingConfig,
          this.seed,
          this.stopSequences,
          this.temperature,
          this.topK,
          this.topP);
    }
  }

}
