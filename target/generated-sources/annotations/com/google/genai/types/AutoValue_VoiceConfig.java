package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_VoiceConfig extends VoiceConfig {

  private final Optional<PrebuiltVoiceConfig> prebuiltVoiceConfig;

  private AutoValue_VoiceConfig(
      Optional<PrebuiltVoiceConfig> prebuiltVoiceConfig) {
    this.prebuiltVoiceConfig = prebuiltVoiceConfig;
  }

  @JsonProperty("prebuiltVoiceConfig")
  @Override
  public Optional<PrebuiltVoiceConfig> prebuiltVoiceConfig() {
    return prebuiltVoiceConfig;
  }

  @Override
  public String toString() {
    return "VoiceConfig{"
        + "prebuiltVoiceConfig=" + prebuiltVoiceConfig
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof VoiceConfig) {
      VoiceConfig that = (VoiceConfig) o;
      return this.prebuiltVoiceConfig.equals(that.prebuiltVoiceConfig());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= prebuiltVoiceConfig.hashCode();
    return h$;
  }

  @Override
  public VoiceConfig.Builder toBuilder() {
    return new AutoValue_VoiceConfig.Builder(this);
  }

  static final class Builder extends VoiceConfig.Builder {
    private Optional<PrebuiltVoiceConfig> prebuiltVoiceConfig = Optional.empty();
    Builder() {
    }
    Builder(VoiceConfig source) {
      this.prebuiltVoiceConfig = source.prebuiltVoiceConfig();
    }
    @Override
    public VoiceConfig.Builder prebuiltVoiceConfig(PrebuiltVoiceConfig prebuiltVoiceConfig) {
      this.prebuiltVoiceConfig = Optional.of(prebuiltVoiceConfig);
      return this;
    }
    @Override
    public VoiceConfig build() {
      return new AutoValue_VoiceConfig(
          this.prebuiltVoiceConfig);
    }
  }

}
