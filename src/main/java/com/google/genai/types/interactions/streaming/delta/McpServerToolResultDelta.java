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
import java.util.Optional;

/**
 * Represents an incremental MCP server tool result update in a streaming response.
 *
 * <p>Note: The Interactions API is in beta and subject to change.
 */
@AutoValue
@JsonDeserialize(builder = McpServerToolResultDelta.Builder.class)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonTypeName("mcp_server_tool_result")
public abstract class McpServerToolResultDelta extends JsonSerializable implements Delta {

  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_McpServerToolResultDelta.Builder();
  }

  public abstract Builder toBuilder();

  /** Returns the call ID this result corresponds to. */
  @JsonProperty("call_id")
  public abstract Optional<String> callId();

  /** Returns the name of the tool. */
  @JsonProperty("name")
  public abstract Optional<String> name();

  /** Returns the name of the MCP server. */
  @JsonProperty("server_name")
  public abstract Optional<String> serverName();

  /** Returns the result of the MCP tool call. */
  @JsonProperty("result")
  public abstract Optional<Object> result();

  @AutoValue.Builder
  public abstract static class Builder {
    @JsonCreator
    private static Builder create() {
      return new AutoValue_McpServerToolResultDelta.Builder();
    }

    @JsonProperty("call_id")
    public abstract Builder callId(String callId);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder callId(Optional<String> callId);

    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearCallId() {
      return callId(Optional.empty());
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

    @JsonProperty("result")
    public abstract Builder result(Object result);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder result(Optional<Object> result);

    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearResult() {
      return result(Optional.empty());
    }

    public abstract McpServerToolResultDelta build();
  }

  @ExcludeFromGeneratedCoverageReport
  public static McpServerToolResultDelta fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, McpServerToolResultDelta.class);
  }
}
