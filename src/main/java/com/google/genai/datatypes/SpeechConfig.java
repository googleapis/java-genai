// Auto-generated code. Do not edit.

package com.google.genai.datatypes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** The speech generation configuration. */
@AutoValue
@JsonDeserialize(builder = AutoValue_SpeechConfig.Builder.class)
public abstract class SpeechConfig extends JsonSerializable {
  /** The configuration for the speaker to use. */
  public abstract Optional<VoiceConfig> getVoiceConfig();

  /** Instantiates a builder for SpeechConfig. */
  public static Builder builder() {
    return new AutoValue_SpeechConfig.Builder();
  }

  /** Builder for SpeechConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("voiceConfig")
    public abstract Builder setVoiceConfig(VoiceConfig voiceConfig);

    public abstract SpeechConfig build();
  }

  /** Serializes the SpeechConfig object to a JSON string. */
  public String toJson() {
    return JsonSerializable.toJsonString(this);
  }

  /** Deserializes a JSON string to a SpeechConfig object. */
  public static SpeechConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, SpeechConfig.class);
  }
}
