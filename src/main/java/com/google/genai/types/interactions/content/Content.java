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

package com.google.genai.types.interactions.content;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Base interface for content types using a type discriminator.
 *
 * <p>This follows Jackson best practices for polymorphism:
 *
 * <ul>
 *   <li>{@code @JsonTypeInfo} on the base type with property "type"
 *   <li>Explicit {@code @JsonSubTypes} registration
 *   <li>Jackson handles type discrimination via annotations
 * </ul>
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
  @JsonSubTypes.Type(value = TextContent.class, name = "text"),
  @JsonSubTypes.Type(value = FunctionCallContent.class, name = "function_call"),
  @JsonSubTypes.Type(value = FunctionResultContent.class, name = "function_result"),
  @JsonSubTypes.Type(value = ImageContent.class, name = "image"),
  @JsonSubTypes.Type(value = AudioContent.class, name = "audio"),
  @JsonSubTypes.Type(value = VideoContent.class, name = "video"),
  @JsonSubTypes.Type(value = DocumentContent.class, name = "document"),
  @JsonSubTypes.Type(value = CodeExecutionCallContent.class, name = "code_execution_call"),
  @JsonSubTypes.Type(value = CodeExecutionResultContent.class, name = "code_execution_result"),
  @JsonSubTypes.Type(value = UrlContextCallContent.class, name = "url_context_call"),
  @JsonSubTypes.Type(value = UrlContextResultContent.class, name = "url_context_result"),
  @JsonSubTypes.Type(value = GoogleSearchCallContent.class, name = "google_search_call"),
  @JsonSubTypes.Type(value = GoogleSearchResultContent.class, name = "google_search_result"),
  @JsonSubTypes.Type(value = McpServerToolCallContent.class, name = "mcp_server_tool_call"),
  @JsonSubTypes.Type(value = McpServerToolResultContent.class, name = "mcp_server_tool_result"),
  @JsonSubTypes.Type(value = ThoughtContent.class, name = "thought"),
  @JsonSubTypes.Type(value = FileSearchCallContent.class, name = "file_search_call"),
  @JsonSubTypes.Type(value = FileSearchResultContent.class, name = "file_search_result")
})
public interface Content {}
