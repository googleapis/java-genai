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
 * The output from a server-side `ToolCall` execution.
 *
 * <p>This message contains the results of a tool invocation that was initiated by a `ToolCall` from
 * the model. The client should pass this `ToolResponse` back to the API in a subsequent turn within
 * a `Content` message, along with the corresponding `ToolCall`.
 */
@AutoValue
@JsonDeserialize(builder = ToolResponse.Builder.class)
public abstract class ToolResponse extends JsonSerializable {
  /** The identifier of the tool call this response is for. */
  @JsonProperty("id")
  public abstract Optional<String> id();

  /** The type of tool that was called, matching the tool_type in the corresponding ToolCall. */
  @JsonProperty("toolType")
  public abstract Optional<ToolType> toolType();

  /** The tool response. */
  @JsonProperty("response")
  public abstract Optional<Map<String, Object>> response();

  /** Instantiates a builder for ToolResponse. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ToolResponse.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ToolResponse. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `ToolResponse.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ToolResponse.Builder();
    }

    /**
     * Setter for id.
     *
     * <p>id: The identifier of the tool call this response is for.
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
     * <p>toolType: The type of tool that was called, matching the tool_type in the corresponding
     * ToolCall.
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
     * <p>toolType: The type of tool that was called, matching the tool_type in the corresponding
     * ToolCall.
     */
    @CanIgnoreReturnValue
    public Builder toolType(ToolType.Known knownType) {
      return toolType(new ToolType(knownType));
    }

    /**
     * Setter for toolType given a string.
     *
     * <p>toolType: The type of tool that was called, matching the tool_type in the corresponding
     * ToolCall.
     */
    @CanIgnoreReturnValue
    public Builder toolType(String toolType) {
      return toolType(new ToolType(toolType));
    }

    /**
     * Setter for response.
     *
     * <p>response: The tool response.
     */
    @JsonProperty("response")
    public abstract Builder response(Map<String, Object> response);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder response(Optional<Map<String, Object>> response);

    /** Clears the value of response field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearResponse() {
      return response(Optional.empty());
    }

    public abstract ToolResponse build();
  }

  /** Deserializes a JSON string to a ToolResponse object. */
  @ExcludeFromGeneratedCoverageReport
  public static ToolResponse fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ToolResponse.class);
  }
}
