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

package com.google.genai.types.interactions.streaming.delta;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import com.google.genai.types.ExcludeFromGeneratedCoverageReport;
import java.util.Map;
import java.util.Optional;

/**
 * Represents an incremental MCP server tool call update in a streaming response.
 *
 * <p>Note: The Interactions API is in beta and subject to change.
 */
@AutoValue
@JsonDeserialize(builder = McpServerToolCallDelta.Builder.class)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonTypeName("mcp_server_tool_call")
public abstract class McpServerToolCallDelta extends JsonSerializable implements Delta {

  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_McpServerToolCallDelta.Builder();
  }

  public abstract Builder toBuilder();

  /** Returns the unique identifier for this MCP tool call. */
  @JsonProperty("id")
  public abstract Optional<String> id();

  /** Returns the name of the tool being called. */
  @JsonProperty("name")
  public abstract Optional<String> name();

  /** Returns the name of the MCP server. */
  @JsonProperty("server_name")
  public abstract Optional<String> serverName();

  /** Returns the arguments for the MCP tool call as a map of key-value pairs. */
  @JsonProperty("arguments")
  public abstract Optional<Map<String, Object>> arguments();

  @AutoValue.Builder
  public abstract static class Builder {
    @JsonCreator
    private static Builder create() {
      return new AutoValue_McpServerToolCallDelta.Builder();
    }

    @JsonProperty("id")
    public abstract Builder id(String id);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder id(Optional<String> id);

    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearId() {
      return id(Optional.empty());
    }

    @JsonProperty("name")
    public abstract Builder name(String name);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder name(Optional<String> name);

    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearName() {
      return name(Optional.empty());
    }

    @JsonProperty("server_name")
    public abstract Builder serverName(String serverName);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder serverName(Optional<String> serverName);

    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearServerName() {
      return serverName(Optional.empty());
    }

    @JsonProperty("arguments")
    public abstract Builder arguments(Map<String, Object> arguments);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder arguments(Optional<Map<String, Object>> arguments);

    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearArguments() {
      return arguments(Optional.empty());
    }

    public abstract McpServerToolCallDelta build();
  }

  @ExcludeFromGeneratedCoverageReport
  public static McpServerToolCallDelta fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, McpServerToolCallDelta.class);
  }
}
