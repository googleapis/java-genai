// Auto-generated code. Do not edit.

package com.google.genai.types;

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

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for PrebuiltVoiceConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("voiceName")
    public abstract Builder setVoiceName(String voiceName);

    public abstract PrebuiltVoiceConfig build();
  }

  /** Deserializes a JSON string to a PrebuiltVoiceConfig object. */
  public static PrebuiltVoiceConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, PrebuiltVoiceConfig.class);
  }
}
