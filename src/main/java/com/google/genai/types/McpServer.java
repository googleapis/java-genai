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
import java.util.Optional;

/**
 * A MCPServer is a server that can be called by the model to perform actions. It is a server that
 * implements the MCP protocol. Next ID: 5. This data type is not supported in Vertex AI.
 */
@AutoValue
@JsonDeserialize(builder = McpServer.Builder.class)
public abstract class McpServer extends JsonSerializable {
  /** The name of the MCPServer. */
  @JsonProperty("name")
  public abstract Optional<String> name();

  /** A transport that can stream HTTP requests and responses. */
  @JsonProperty("streamableHttpTransport")
  public abstract Optional<StreamableHttpTransport> streamableHttpTransport();

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
    /** For internal usage. Please use `McpServer.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_McpServer.Builder();
    }

    /**
     * Setter for name.
     *
     * <p>name: The name of the MCPServer.
     */
    @JsonProperty("name")
    public abstract Builder name(String name);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder name(Optional<String> name);

    /** Clears the value of name field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearName() {
      return name(Optional.empty());
    }

    /**
     * Setter for streamableHttpTransport.
     *
     * <p>streamableHttpTransport: A transport that can stream HTTP requests and responses.
     */
    @JsonProperty("streamableHttpTransport")
    public abstract Builder streamableHttpTransport(
        StreamableHttpTransport streamableHttpTransport);

    /**
     * Setter for streamableHttpTransport builder.
     *
     * <p>streamableHttpTransport: A transport that can stream HTTP requests and responses.
     */
    @CanIgnoreReturnValue
    public Builder streamableHttpTransport(
        StreamableHttpTransport.Builder streamableHttpTransportBuilder) {
      return streamableHttpTransport(streamableHttpTransportBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder streamableHttpTransport(
        Optional<StreamableHttpTransport> streamableHttpTransport);

    /** Clears the value of streamableHttpTransport field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearStreamableHttpTransport() {
      return streamableHttpTransport(Optional.empty());
    }

    public abstract McpServer build();
  }

  /** Deserializes a JSON string to a McpServer object. */
  @ExcludeFromGeneratedCoverageReport
  public static McpServer fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, McpServer.class);
  }
}
