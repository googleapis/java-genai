// Auto-generated code. Do not edit.

package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/**
 * Result of executing the [ExecutableCode]. Always follows a `part` containing the
 * [ExecutableCode].
 */
@AutoValue
@JsonDeserialize(builder = AutoValue_CodeExecutionResult.Builder.class)
public abstract class CodeExecutionResult extends JsonSerializable {
  /** Required. Outcome of the code execution. */
  public abstract Optional<String> getOutcome();

  /**
   * Optional. Contains stdout when code execution is successful, stderr or other description
   * otherwise.
   */
  public abstract Optional<String> getOutput();

  /** Instantiates a builder for CodeExecutionResult. */
  public static Builder builder() {
    return new AutoValue_CodeExecutionResult.Builder();
  }

  /** Builder for CodeExecutionResult. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("outcome")
    public abstract Builder setOutcome(String outcome);

    @JsonProperty("output")
    public abstract Builder setOutput(String output);

    public abstract CodeExecutionResult build();
  }

  /** Serializes the CodeExecutionResult object to a JSON string. */
  public String toJson() {
    return JsonSerializable.toJsonString(this);
  }

  /** Deserializes a JSON string to a CodeExecutionResult object. */
  public static CodeExecutionResult fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, CodeExecutionResult.class);
  }
}
