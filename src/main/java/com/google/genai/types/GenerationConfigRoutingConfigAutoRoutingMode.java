// Auto-generated code. Do not edit.

package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/**
 * When automated routing is specified, the routing will be determined by the pretrained routing
 * model and customer provided model routing preference.
 */
@AutoValue
@JsonDeserialize(builder = AutoValue_GenerationConfigRoutingConfigAutoRoutingMode.Builder.class)
public abstract class GenerationConfigRoutingConfigAutoRoutingMode extends JsonSerializable {
  /** The model routing preference. */
  public abstract Optional<String> getModelRoutingPreference();

  /** Instantiates a builder for GenerationConfigRoutingConfigAutoRoutingMode. */
  public static Builder builder() {
    return new AutoValue_GenerationConfigRoutingConfigAutoRoutingMode.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GenerationConfigRoutingConfigAutoRoutingMode. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("modelRoutingPreference")
    public abstract Builder setModelRoutingPreference(String modelRoutingPreference);

    public abstract GenerationConfigRoutingConfigAutoRoutingMode build();
  }

  /** Deserializes a JSON string to a GenerationConfigRoutingConfigAutoRoutingMode object. */
  public static GenerationConfigRoutingConfigAutoRoutingMode fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(
        jsonString, GenerationConfigRoutingConfigAutoRoutingMode.class);
  }
}
