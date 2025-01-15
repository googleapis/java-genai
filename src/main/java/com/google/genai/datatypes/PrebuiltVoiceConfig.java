// Auto-generated code. Do not edit.

package com.google.genai.datatypes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** The configuration for the prebuilt speaker to use. */
@AutoValue
@JsonDeserialize(builder = AutoValue_PrebuiltVoiceConfig.Builder.class)
public abstract class PrebuiltVoiceConfig extends JsonSerializable {
  /** The name of the prebuilt voice to use. */
  public abstract Optional<String> getVoiceName();

  /** Instantiates a builder for PrebuiltVoiceConfig. */
  public static Builder builder() {
    return new AutoValue_PrebuiltVoiceConfig.Builder();
  }

  /** Builder for PrebuiltVoiceConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("voiceName")
    public abstract Builder setVoiceName(String voiceName);

    public abstract PrebuiltVoiceConfig build();
  }

  /** Serializes the PrebuiltVoiceConfig object to a JSON string. */
  public String toJson() {
    return JsonSerializable.toJsonString(this);
  }

  /** Deserializes a JSON string to a PrebuiltVoiceConfig object. */
  public static PrebuiltVoiceConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, PrebuiltVoiceConfig.class);
  }
}
