// Auto-generated code. Do not edit.

package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Map;
import java.util.Optional;

/** A function response. */
@AutoValue
@JsonDeserialize(builder = AutoValue_FunctionResponse.Builder.class)
public abstract class FunctionResponse extends JsonSerializable {
  /**
   * The id of the function call this response is for. Populated by the client to match the
   * corresponding function call `id`.
   */
  public abstract Optional<String> getId();

  /**
   * Required. The name of the function to call. Matches [FunctionDeclaration.name] and
   * [FunctionCall.name].
   */
  public abstract Optional<String> getName();

  /**
   * Required. The function response in JSON object format. Use "output" key to specify function
   * output and "error" key to specify error details (if any). If "output" and "error" keys are not
   * specified, then whole "response" is treated as function output.
   */
  public abstract Optional<Map<String, Object>> getResponse();

  /** Instantiates a builder for FunctionResponse. */
  public static Builder builder() {
    return new AutoValue_FunctionResponse.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for FunctionResponse. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("id")
    public abstract Builder setId(String id);

    @JsonProperty("name")
    public abstract Builder setName(String name);

    @JsonProperty("response")
    public abstract Builder setResponse(Map<String, Object> response);

    public abstract FunctionResponse build();
  }

  /** Deserializes a JSON string to a FunctionResponse object. */
  public static FunctionResponse fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, FunctionResponse.class);
  }
}
