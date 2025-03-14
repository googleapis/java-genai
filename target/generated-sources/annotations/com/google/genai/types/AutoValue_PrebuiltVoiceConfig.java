package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_PrebuiltVoiceConfig extends PrebuiltVoiceConfig {

  private final Optional<String> voiceName;

  private AutoValue_PrebuiltVoiceConfig(
      Optional<String> voiceName) {
    this.voiceName = voiceName;
  }

  @JsonProperty("voiceName")
  @Override
  public Optional<String> voiceName() {
    return voiceName;
  }

  @Override
  public String toString() {
    return "PrebuiltVoiceConfig{"
        + "voiceName=" + voiceName
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof PrebuiltVoiceConfig) {
      PrebuiltVoiceConfig that = (PrebuiltVoiceConfig) o;
      return this.voiceName.equals(that.voiceName());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= voiceName.hashCode();
    return h$;
  }

  @Override
  public PrebuiltVoiceConfig.Builder toBuilder() {
    return new AutoValue_PrebuiltVoiceConfig.Builder(this);
  }

  static final class Builder extends PrebuiltVoiceConfig.Builder {
    private Optional<String> voiceName = Optional.empty();
    Builder() {
    }
    Builder(PrebuiltVoiceConfig source) {
      this.voiceName = source.voiceName();
    }
    @Override
    public PrebuiltVoiceConfig.Builder voiceName(String voiceName) {
      this.voiceName = Optional.of(voiceName);
      return this;
    }
    @Override
    public PrebuiltVoiceConfig build() {
      return new AutoValue_PrebuiltVoiceConfig(
          this.voiceName);
    }
  }

}
