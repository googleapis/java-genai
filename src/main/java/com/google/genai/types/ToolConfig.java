// Auto-generated code. Do not edit.

package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/**
 * Tool config.
 *
 * <p>This config is shared for all tools provided in the request.
 */
@AutoValue
@JsonDeserialize(builder = AutoValue_ToolConfig.Builder.class)
public abstract class ToolConfig extends JsonSerializable {
  /** Optional. Function calling config. */
  public abstract Optional<FunctionCallingConfig> getFunctionCallingConfig();

  /** Instantiates a builder for ToolConfig. */
  public static Builder builder() {
    return new AutoValue_ToolConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ToolConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("functionCallingConfig")
    public abstract Builder setFunctionCallingConfig(FunctionCallingConfig functionCallingConfig);

    public abstract ToolConfig build();
  }

  /** Deserializes a JSON string to a ToolConfig object. */
  public static ToolConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ToolConfig.class);
  }
}
