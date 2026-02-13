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

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Base interface for streaming delta types using a type discriminator.
 *
 * <p>Delta types represent incremental content updates in a streaming response. Each delta type
 * corresponds to a specific content type and contains the incremental data for that content.
 *
 * <p>This follows Jackson best practices for polymorphism:
 *
 * <ul>
 *   <li>{@code @JsonTypeInfo} on the base type with property "type"
 *   <li>Explicit {@code @JsonSubTypes} registration
 *   <li>Jackson handles type discrimination via annotations
 * </ul>
 *
 * <p>Note: The Interactions API is in beta and subject to change.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
  @JsonSubTypes.Type(value = TextDelta.class, name = "text"),
  @JsonSubTypes.Type(value = ImageDelta.class, name = "image"),
  @JsonSubTypes.Type(value = AudioDelta.class, name = "audio"),
  @JsonSubTypes.Type(value = DocumentDelta.class, name = "document"),
  @JsonSubTypes.Type(value = VideoDelta.class, name = "video"),
  @JsonSubTypes.Type(value = ThoughtSummaryDelta.class, name = "thought_summary"),
  @JsonSubTypes.Type(value = ThoughtSignatureDelta.class, name = "thought_signature"),
  @JsonSubTypes.Type(value = FunctionCallDelta.class, name = "function_call"),
  @JsonSubTypes.Type(value = FunctionResultDelta.class, name = "function_result"),
  @JsonSubTypes.Type(value = CodeExecutionCallDelta.class, name = "code_execution_call"),
  @JsonSubTypes.Type(value = CodeExecutionResultDelta.class, name = "code_execution_result"),
  @JsonSubTypes.Type(value = UrlContextCallDelta.class, name = "url_context_call"),
  @JsonSubTypes.Type(value = UrlContextResultDelta.class, name = "url_context_result"),
  @JsonSubTypes.Type(value = GoogleSearchCallDelta.class, name = "google_search_call"),
  @JsonSubTypes.Type(value = GoogleSearchResultDelta.class, name = "google_search_result"),
  @JsonSubTypes.Type(value = McpServerToolCallDelta.class, name = "mcp_server_tool_call"),
  @JsonSubTypes.Type(value = McpServerToolResultDelta.class, name = "mcp_server_tool_result"),
  @JsonSubTypes.Type(value = FileSearchCallDelta.class, name = "file_search_call"),
  @JsonSubTypes.Type(value = FileSearchResultDelta.class, name = "file_search_result")
})
public interface Delta {
  // Marker interface - Jackson handles type discrimination via annotations
}
