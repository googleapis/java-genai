// Auto-generated code. Do not edit.

package com.google.genai.types;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;

/**
 * Tool that executes code generated by the model, and automatically returns the result to the
 * model. See also [ExecutableCode]and [CodeExecutionResult] which are input and output to this
 * tool.
 */
@AutoValue
@JsonDeserialize(builder = AutoValue_ToolCodeExecution.Builder.class)
public abstract class ToolCodeExecution extends JsonSerializable {
  /** Instantiates a builder for ToolCodeExecution. */
  public static Builder builder() {
    return new AutoValue_ToolCodeExecution.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ToolCodeExecution. */
  @AutoValue.Builder
  public abstract static class Builder {
    public abstract ToolCodeExecution build();
  }

  /** Deserializes a JSON string to a ToolCodeExecution object. */
  public static ToolCodeExecution fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ToolCodeExecution.class);
  }
}
