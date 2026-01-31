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

package com.google.genai.types.interactions.tools;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import com.google.genai.types.ExcludeFromGeneratedCoverageReport;
import com.google.genai.types.interactions.AllowedTools;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * MCP (Model Context Protocol) server tool for the Interactions API.
 *
 * <p>Enables the model to interact with an MCP server.
 *
 * <p>Example usage:
 *
 * <pre>{@code
 * McpServer mcpTool = McpServer.builder()
 *     .name("my-mcp-server")
 *     .url("https://mcp.example.com/endpoint")
 *     .build();
 * }</pre>
 *
 * <p>The Interactions API is available in both Vertex AI and Gemini API.
 *
 * <p>Note: The Interactions API is in beta and subject to change.
 */
@AutoValue
@JsonDeserialize(builder = McpServer.Builder.class)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonTypeName("mcp_server")
public abstract class McpServer extends JsonSerializable implements Tool {

  /** The name of the MCP server. */
  @JsonProperty("name")
  public abstract Optional<String> name();

  /** The URL of the MCP server endpoint. */
  @JsonProperty("url")
  public abstract Optional<String> url();

  /** Optional headers to include in requests to the MCP server. */
  @JsonProperty("headers")
  public abstract Optional<Map<String, String>> headers();

  /** List of allowed tools configuration from the MCP server. */
  @JsonProperty("allowed_tools")
  public abstract Optional<List<AllowedTools>> allowedTools();

  /** Instantiates a builder for McpServer. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_McpServer.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for McpServer. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use {@code McpServer.builder()} for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_McpServer.Builder();
    }

    /**
     * Setter for name.
     *
     * <p>name: The name of the MCP server.
     */
    @JsonProperty("name")
    public abstract Builder name(String name);

    abstract Builder name(Optional<String> name);

    /** Clears the value of name field. */
    @CanIgnoreReturnValue
    public Builder clearName() {
      return name(Optional.empty());
    }

    /**
     * Setter for url.
     *
     * <p>url: The URL of the MCP server endpoint.
     */
    @JsonProperty("url")
    public abstract Builder url(String url);

    abstract Builder url(Optional<String> url);

    /** Clears the value of url field. */
    @CanIgnoreReturnValue
    public Builder clearUrl() {
      return url(Optional.empty());
    }

    /**
     * Setter for headers.
     *
     * <p>headers: Optional headers to include in requests.
     */
    @JsonProperty("headers")
    public abstract Builder headers(Map<String, String> headers);

    abstract Builder headers(Optional<Map<String, String>> headers);

    /** Clears the value of headers field. */
    @CanIgnoreReturnValue
    public Builder clearHeaders() {
      return headers(Optional.empty());
    }

    /**
     * Setter for allowedTools.
     *
     * <p>allowedTools: List of allowed tools configuration from the MCP server.
     */
    @JsonProperty("allowed_tools")
    public abstract Builder allowedTools(List<AllowedTools> allowedTools);

    /**
     * Setter for allowedTools (varargs convenience method).
     *
     * <p>allowedTools: List of allowed tools configuration from the MCP server.
     */
    @CanIgnoreReturnValue
    public Builder allowedTools(AllowedTools... allowedTools) {
      return allowedTools(Arrays.asList(allowedTools));
    }

    abstract Builder allowedTools(Optional<List<AllowedTools>> allowedTools);

    /** Clears the value of allowedTools field. */
    @CanIgnoreReturnValue
    public Builder clearAllowedTools() {
      return allowedTools(Optional.empty());
    }

    public abstract McpServer build();
  }

  /** Deserializes a JSON string to a McpServer object. */
  @ExcludeFromGeneratedCoverageReport
  public static McpServer fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, McpServer.class);
  }
}
