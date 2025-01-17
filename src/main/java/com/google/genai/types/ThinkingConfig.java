// Auto-generated code. Do not edit.

package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** The thinking features configuration. */
@AutoValue
@JsonDeserialize(builder = AutoValue_ThinkingConfig.Builder.class)
public abstract class ThinkingConfig extends JsonSerializable {
  /**
   * Indicates whether to include thoughts in the response. If true, thoughts are returned only if
   * the model supports thought and thoughts are available.
   */
  public abstract Optional<Boolean> getIncludeThoughts();

  /** Instantiates a builder for ThinkingConfig. */
  public static Builder builder() {
    return new AutoValue_ThinkingConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ThinkingConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("includeThoughts")
    public abstract Builder setIncludeThoughts(boolean includeThoughts);

    public abstract ThinkingConfig build();
  }

  /** Deserializes a JSON string to a ThinkingConfig object. */
  public static ThinkingConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ThinkingConfig.class);
  }
}
