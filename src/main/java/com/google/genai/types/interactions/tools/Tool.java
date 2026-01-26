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

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Base interface for tool types using a type discriminator.
 *
 * <p>This follows Jackson best practices for polymorphism:
 *
 * <ul>
 *   <li>{@code @JsonTypeInfo} on the base type with property "type"
 *   <li>Explicit {@code @JsonSubTypes} registration
 *   <li>Jackson handles type discrimination via annotations
 * </ul>
 *
 * <p>The Interactions API is available in both Vertex AI and Gemini API.
 *
 * <p>Note: The Interactions API is in beta and subject to change.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
  @JsonSubTypes.Type(value = Function.class, name = "function"),
  @JsonSubTypes.Type(value = GoogleSearch.class, name = "google_search"),
  @JsonSubTypes.Type(value = CodeExecution.class, name = "code_execution"),
  @JsonSubTypes.Type(value = UrlContext.class, name = "url_context"),
  @JsonSubTypes.Type(value = ComputerUse.class, name = "computer_use"),
  @JsonSubTypes.Type(value = McpServer.class, name = "mcp_server"),
  @JsonSubTypes.Type(value = FileSearch.class, name = "file_search"),
})
public interface Tool {}
