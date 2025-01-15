// Auto-generated code. Do not edit.

package com.google.genai.datatypes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** The configuration for routing the request to a specific model. */
@AutoValue
@JsonDeserialize(builder = AutoValue_GenerationConfigRoutingConfig.Builder.class)
public abstract class GenerationConfigRoutingConfig extends JsonSerializable {
  /** Automated routing. */
  public abstract Optional<GenerationConfigRoutingConfigAutoRoutingMode> getAutoMode();

  /** Manual routing. */
  public abstract Optional<GenerationConfigRoutingConfigManualRoutingMode> getManualMode();

  /** Instantiates a builder for GenerationConfigRoutingConfig. */
  public static Builder builder() {
    return new AutoValue_GenerationConfigRoutingConfig.Builder();
  }

  /** Builder for GenerationConfigRoutingConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("autoMode")
    public abstract Builder setAutoMode(GenerationConfigRoutingConfigAutoRoutingMode autoMode);

    @JsonProperty("manualMode")
    public abstract Builder setManualMode(
        GenerationConfigRoutingConfigManualRoutingMode manualMode);

    public abstract GenerationConfigRoutingConfig build();
  }

  /** Serializes the GenerationConfigRoutingConfig object to a JSON string. */
  public String toJson() {
    return JsonSerializable.toJsonString(this);
  }

  /** Deserializes a JSON string to a GenerationConfigRoutingConfig object. */
  public static GenerationConfigRoutingConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GenerationConfigRoutingConfig.class);
  }
}
