// Auto-generated code. Do not edit.

package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** The configuration for the voice to use. */
@AutoValue
@JsonDeserialize(builder = AutoValue_VoiceConfig.Builder.class)
public abstract class VoiceConfig extends JsonSerializable {
  /** The configuration for the speaker to use. */
  public abstract Optional<PrebuiltVoiceConfig> getPrebuiltVoiceConfig();

  /** Instantiates a builder for VoiceConfig. */
  public static Builder builder() {
    return new AutoValue_VoiceConfig.Builder();
  }

  /** Builder for VoiceConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("prebuiltVoiceConfig")
    public abstract Builder setPrebuiltVoiceConfig(PrebuiltVoiceConfig prebuiltVoiceConfig);

    public abstract VoiceConfig build();
  }

  /** Serializes the VoiceConfig object to a JSON string. */
  public String toJson() {
    return JsonSerializable.toJsonString(this);
  }

  /** Deserializes a JSON string to a VoiceConfig object. */
  public static VoiceConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, VoiceConfig.class);
  }
}
