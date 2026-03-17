/*
 * Copyright 2025 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// Auto-generated code. Do not edit.

package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import java.util.Map;
import java.util.Optional;

/**
 * A predicted server-side `ToolCall` returned from the model.
 *
 * <p>This message contains information about a tool that the model wants to invoke. The client is
 * NOT expected to execute this `ToolCall`. Instead, the client should pass this `ToolCall` back to
 * the API in a subsequent turn within a `Content` message, along with the corresponding
 * `ToolResponse`.
 */
@AutoValue
@JsonDeserialize(builder = ToolCall.Builder.class)
public abstract class ToolCall extends JsonSerializable {
  /**
   * Unique identifier of the tool call. The server returns the tool response with the matching
   * `id`.
   */
  @JsonProperty("id")
  public abstract Optional<String> id();

  /** The type of tool that was called. */
  @JsonProperty("toolType")
  public abstract Optional<ToolType> toolType();

  /** The tool call arguments. Example: {"arg1": "value1", "arg2": "value2"}. */
  @JsonProperty("args")
  public abstract Optional<Map<String, Object>> args();

  /** Instantiates a builder for ToolCall. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ToolCall.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ToolCall. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `ToolCall.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ToolCall.Builder();
    }

    /**
     * Setter for id.
     *
     * <p>id: Unique identifier of the tool call. The server returns the tool response with the
     * matching `id`.
     */
    @JsonProperty("id")
    public abstract Builder id(String id);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder id(Optional<String> id);

    /** Clears the value of id field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearId() {
      return id(Optional.empty());
    }

    /**
     * Setter for toolType.
     *
     * <p>toolType: The type of tool that was called.
     */
    @JsonProperty("toolType")
    public abstract Builder toolType(ToolType toolType);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder toolType(Optional<ToolType> toolType);

    /** Clears the value of toolType field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearToolType() {
      return toolType(Optional.empty());
    }

    /**
     * Setter for toolType given a known enum.
     *
     * <p>toolType: The type of tool that was called.
     */
    @CanIgnoreReturnValue
    public Builder toolType(ToolType.Known knownType) {
      return toolType(new ToolType(knownType));
    }

    /**
     * Setter for toolType given a string.
     *
     * <p>toolType: The type of tool that was called.
     */
    @CanIgnoreReturnValue
    public Builder toolType(String toolType) {
      return toolType(new ToolType(toolType));
    }

    /**
     * Setter for args.
     *
     * <p>args: The tool call arguments. Example: {"arg1": "value1", "arg2": "value2"}.
     */
    @JsonProperty("args")
    public abstract Builder args(Map<String, Object> args);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder args(Optional<Map<String, Object>> args);

    /** Clears the value of args field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearArgs() {
      return args(Optional.empty());
    }

    public abstract ToolCall build();
  }

  /** Deserializes a JSON string to a ToolCall object. */
  @ExcludeFromGeneratedCoverageReport
  public static ToolCall fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ToolCall.class);
  }
}
