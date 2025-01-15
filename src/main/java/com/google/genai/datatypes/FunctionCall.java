// Auto-generated code. Do not edit.

package com.google.genai.datatypes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Map;
import java.util.Optional;

/** A function call. */
@AutoValue
@JsonDeserialize(builder = AutoValue_FunctionCall.Builder.class)
public abstract class FunctionCall extends JsonSerializable {
  /**
   * The unique id of the function call. If populated, the client to execute the `function_call` and
   * return the response with the matching `id`.
   */
  public abstract Optional<String> getId();

  /**
   * Optional. Required. The function parameters and values in JSON object format. See
   * [FunctionDeclaration.parameters] for parameter details.
   */
  public abstract Optional<Map<String, Object>> getArgs();

  /** Required. The name of the function to call. Matches [FunctionDeclaration.name]. */
  public abstract Optional<String> getName();

  /** Instantiates a builder for FunctionCall. */
  public static Builder builder() {
    return new AutoValue_FunctionCall.Builder();
  }

  /** Builder for FunctionCall. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("id")
    public abstract Builder setId(String id);

    @JsonProperty("args")
    public abstract Builder setArgs(Map<String, Object> args);

    @JsonProperty("name")
    public abstract Builder setName(String name);

    public abstract FunctionCall build();
  }

  /** Serializes the FunctionCall object to a JSON string. */
  public String toJson() {
    return JsonSerializable.toJsonString(this);
  }

  /** Deserializes a JSON string to a FunctionCall object. */
  public static FunctionCall fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, FunctionCall.class);
  }
}
