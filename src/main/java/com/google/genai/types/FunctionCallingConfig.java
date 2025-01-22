// Auto-generated code. Do not edit.

package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.List;
import java.util.Optional;

/** Function calling config. */
@AutoValue
@JsonDeserialize(builder = AutoValue_FunctionCallingConfig.Builder.class)
public abstract class FunctionCallingConfig extends JsonSerializable {
  /** Optional. Function calling mode. */
  public abstract Optional<String> getMode();

  /**
   * Optional. Function names to call. Only set when the Mode is ANY. Function names should match
   * [FunctionDeclaration.name]. With mode set to ANY, model will predict a function call from the
   * set of function names provided.
   */
  public abstract Optional<List<String>> getAllowedFunctionNames();

  /** Instantiates a builder for FunctionCallingConfig. */
  public static Builder builder() {
    return new AutoValue_FunctionCallingConfig.Builder();
  }

  /** Builder for FunctionCallingConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("mode")
    public abstract Builder setMode(String mode);

    @JsonProperty("allowedFunctionNames")
    public abstract Builder setAllowedFunctionNames(List<String> allowedFunctionNames);

    public abstract FunctionCallingConfig build();
  }

  /** Serializes the FunctionCallingConfig object to a JSON string. */
  public String toJson() {
    return JsonSerializable.toJsonString(this);
  }

  /** Deserializes a JSON string to a FunctionCallingConfig object. */
  public static FunctionCallingConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, FunctionCallingConfig.class);
  }
}
