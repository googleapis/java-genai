// Auto-generated code. Do not edit.

package com.google.genai.datatypes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** When manual routing is set, the specified model will be used directly. */
@AutoValue
@JsonDeserialize(builder = AutoValue_GenerationConfigRoutingConfigManualRoutingMode.Builder.class)
public abstract class GenerationConfigRoutingConfigManualRoutingMode extends JsonSerializable {
  /** The model name to use. Only the public LLM models are accepted. e.g. 'gemini-1.5-pro-001'. */
  public abstract Optional<String> getModelName();

  /** Instantiates a builder for GenerationConfigRoutingConfigManualRoutingMode. */
  public static Builder builder() {
    return new AutoValue_GenerationConfigRoutingConfigManualRoutingMode.Builder();
  }

  /** Builder for GenerationConfigRoutingConfigManualRoutingMode. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("modelName")
    public abstract Builder setModelName(String modelName);

    public abstract GenerationConfigRoutingConfigManualRoutingMode build();
  }

  /** Serializes the GenerationConfigRoutingConfigManualRoutingMode object to a JSON string. */
  public String toJson() {
    return JsonSerializable.toJsonString(this);
  }

  /** Deserializes a JSON string to a GenerationConfigRoutingConfigManualRoutingMode object. */
  public static GenerationConfigRoutingConfigManualRoutingMode fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(
        jsonString, GenerationConfigRoutingConfigManualRoutingMode.class);
  }
}
