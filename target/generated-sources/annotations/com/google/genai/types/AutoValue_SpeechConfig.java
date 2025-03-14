package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_SpeechConfig extends SpeechConfig {

  private final Optional<VoiceConfig> voiceConfig;

  private AutoValue_SpeechConfig(
      Optional<VoiceConfig> voiceConfig) {
    this.voiceConfig = voiceConfig;
  }

  @JsonProperty("voiceConfig")
  @Override
  public Optional<VoiceConfig> voiceConfig() {
    return voiceConfig;
  }

  @Override
  public String toString() {
    return "SpeechConfig{"
        + "voiceConfig=" + voiceConfig
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof SpeechConfig) {
      SpeechConfig that = (SpeechConfig) o;
      return this.voiceConfig.equals(that.voiceConfig());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= voiceConfig.hashCode();
    return h$;
  }

  @Override
  public SpeechConfig.Builder toBuilder() {
    return new AutoValue_SpeechConfig.Builder(this);
  }

  static final class Builder extends SpeechConfig.Builder {
    private Optional<VoiceConfig> voiceConfig = Optional.empty();
    Builder() {
    }
    Builder(SpeechConfig source) {
      this.voiceConfig = source.voiceConfig();
    }
    @Override
    public SpeechConfig.Builder voiceConfig(VoiceConfig voiceConfig) {
      this.voiceConfig = Optional.of(voiceConfig);
      return this;
    }
    @Override
    public SpeechConfig build() {
      return new AutoValue_SpeechConfig(
          this.voiceConfig);
    }
  }

}
