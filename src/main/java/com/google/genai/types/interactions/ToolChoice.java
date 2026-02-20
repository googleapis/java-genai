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

package com.google.genai.types.interactions;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.genai.JsonSerializable;

/**
 * Union type for tool choice in the Interactions API.
 *
 * <p>This class represents the {@code oneOf} constraint in the OpenAPI specification where {@code
 * tool_choice} can be either:
 *
 * <ol>
 *   <li>A simple {@link ToolChoiceType} - controls overall tool usage behavior (auto, any, none,
 *       validated)
 *   <li>A {@link ToolChoiceConfig} object - provides fine-grained control over which specific tools
 *       are allowed
 * </ol>
 *
 * <p>This design pattern is used because {@code @AutoValue} cannot represent {@code oneOf} union
 * types from OpenAPI specifications. Instead, this class uses a custom serializer ({@link
 * ToolChoiceSerializer}) and deserializer ({@link ToolChoiceDeserializer}) to handle JSON
 * serialization and deserialization correctly.
 *
 * <p>Example usage:
 *
 * <pre>{@code
 * // Simple type-based control - let the model decide
 * ToolChoice toolChoice = ToolChoice.fromType(ToolChoiceType.Known.AUTO);
 *
 * // Simple type-based control - force tool usage
 * ToolChoice toolChoice = ToolChoice.fromType(ToolChoiceType.Known.ANY);
 *
 * // Fine-grained control - allow specific tools
 * ToolChoiceConfig config = ToolChoiceConfig.builder()
 *     .type(new ToolChoiceType(ToolChoiceType.Known.ANY))
 *     .allowedFunctionNames(Arrays.asList("get_weather", "search_web"))
 *     .build();
 * ToolChoice toolChoice = ToolChoice.fromConfig(config);
 *
 * // Use in generation config
 * GenerationConfig genConfig = GenerationConfig.builder()
 *     .toolChoice(ToolChoice.fromType(ToolChoiceType.Known.AUTO))
 *     .build();
 * }</pre>
 */
@JsonSerialize(using = ToolChoiceSerializer.class)
@JsonDeserialize(using = ToolChoiceDeserializer.class)
public final class ToolChoice extends JsonSerializable {
  private final Object value;

  private ToolChoice(Object value) {
    this.value = value;
  }

  /**
   * Creates a ToolChoice from a ToolChoiceType.
   *
   * @param type The tool choice type (auto, any, none, validated)
   * @return A ToolChoice instance wrapping the type
   */
  public static ToolChoice fromType(ToolChoiceType type) {
    return new ToolChoice(type);
  }

  /**
   * Creates a ToolChoice from a ToolChoiceType.Known enum.
   *
   * @param knownType The tool choice known type
   * @return A ToolChoice instance wrapping the type
   */
  public static ToolChoice fromType(ToolChoiceType.Known knownType) {
    return new ToolChoice(new ToolChoiceType(knownType));
  }

  /**
   * Creates a ToolChoice from a string value.
   *
   * @param typeString The tool choice type as a string
   * @return A ToolChoice instance wrapping the type
   */
  public static ToolChoice fromString(String typeString) {
    return new ToolChoice(new ToolChoiceType(typeString));
  }

  /**
   * Creates a ToolChoice from a ToolChoiceConfig.
   *
   * @param config The tool choice configuration
   * @return A ToolChoice instance wrapping the config
   */
  public static ToolChoice fromConfig(ToolChoiceConfig config) {
    return new ToolChoice(config);
  }

  /**
   * Creates a ToolChoice from a ToolChoiceConfig builder.
   *
   * @param configBuilder The tool choice configuration builder
   * @return A ToolChoice instance wrapping the config
   */
  public static ToolChoice fromConfig(ToolChoiceConfig.Builder configBuilder) {
    return new ToolChoice(configBuilder.build());
  }

  /**
   * Gets the underlying value.
   *
   * @return The wrapped value (ToolChoiceType or ToolChoiceConfig)
   */
  public Object getValue() {
    return value;
  }

  /**
   * Checks if this ToolChoice is a ToolChoiceType.
   *
   * @return true if the value is a ToolChoiceType
   */
  public boolean isType() {
    return value instanceof ToolChoiceType;
  }

  /**
   * Checks if this ToolChoice is a ToolChoiceConfig.
   *
   * @return true if the value is a ToolChoiceConfig
   */
  public boolean isConfig() {
    return value instanceof ToolChoiceConfig;
  }

  /**
   * Gets the value as a ToolChoiceType.
   *
   * @return The ToolChoiceType value
   * @throws IllegalStateException if the value is not a ToolChoiceType
   */
  public ToolChoiceType asType() {
    if (!isType()) {
      throw new IllegalStateException("ToolChoice is not a ToolChoiceType");
    }
    return (ToolChoiceType) value;
  }

  /**
   * Gets the value as a ToolChoiceConfig.
   *
   * @return The ToolChoiceConfig value
   * @throws IllegalStateException if the value is not a ToolChoiceConfig
   */
  public ToolChoiceConfig asConfig() {
    if (!isConfig()) {
      throw new IllegalStateException("ToolChoice is not a ToolChoiceConfig");
    }
    return (ToolChoiceConfig) value;
  }

  /** Deserializes a JSON string to a ToolChoice object. */
  public static ToolChoice fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ToolChoice.class);
  }
}
