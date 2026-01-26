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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Tests for ToolChoiceSerializer to ensure proper JSON serialization of ToolChoice union types.
 *
 * <p>ToolChoiceSerializer handles two different types:
 *
 * <ul>
 *   <li>ToolChoiceType - serialized as a simple string (e.g., "auto", "any", "none", "validated")
 *   <li>ToolChoiceConfig - serialized as an object with allowed_tools field
 * </ul>
 */
public class ToolChoiceSerializerTest {

  // ========== ToolChoiceType Serialization Tests ==========

  @Test
  public void testSerializeToolChoiceTypeAuto() {
    // Arrange
    ToolChoice toolChoice = ToolChoice.fromType(ToolChoiceType.Known.AUTO);

    // Act
    String json = toolChoice.toJson();

    // Assert - should serialize as lowercase string (matching API format)
    assertEquals("\"auto\"", json);
  }

  @Test
  public void testSerializeToolChoiceTypeAny() {
    // Arrange
    ToolChoice toolChoice = ToolChoice.fromType(ToolChoiceType.Known.ANY);

    // Act
    String json = toolChoice.toJson();

    // Assert - should serialize as lowercase string (matching API format)
    assertEquals("\"any\"", json);
  }

  @Test
  public void testSerializeToolChoiceTypeNone() {
    // Arrange
    ToolChoice toolChoice = ToolChoice.fromType(ToolChoiceType.Known.NONE);

    // Act
    String json = toolChoice.toJson();

    // Assert - should serialize as lowercase string (matching API format)
    assertEquals("\"none\"", json);
  }

  @Test
  public void testSerializeToolChoiceTypeValidated() {
    // Arrange
    ToolChoice toolChoice = ToolChoice.fromType(ToolChoiceType.Known.VALIDATED);

    // Act
    String json = toolChoice.toJson();

    // Assert - should serialize as lowercase string (matching API format)
    assertEquals("\"validated\"", json);
  }

  @Test
  public void testSerializeToolChoiceTypeFromString() {
    // Arrange
    ToolChoice toolChoice = ToolChoice.fromString("auto");

    // Act
    String json = toolChoice.toJson();

    // Assert
    assertEquals("\"auto\"", json);
  }

  @Test
  public void testSerializeToolChoiceTypeCustomValue() {
    // Arrange - test with a custom/unknown type value
    ToolChoice toolChoice = ToolChoice.fromString("custom_mode");

    // Act
    String json = toolChoice.toJson();

    // Assert
    assertEquals("\"custom_mode\"", json);
  }

  @Test
  public void testToolChoiceTypeIsType() {
    // Arrange
    ToolChoice toolChoice = ToolChoice.fromType(ToolChoiceType.Known.AUTO);

    // Act & Assert
    assertTrue(toolChoice.isType());
    assertFalse(toolChoice.isConfig());
  }

  @Test
  public void testToolChoiceTypeAsType() {
    // Arrange
    ToolChoice toolChoice = ToolChoice.fromType(ToolChoiceType.Known.ANY);

    // Act
    ToolChoiceType type = toolChoice.asType();

    // Assert - toString() returns the lowercase API value
    assertEquals("any", type.toString());
  }

  @Test
  public void testToolChoiceTypeAsConfigThrows() {
    // Arrange
    ToolChoice toolChoice = ToolChoice.fromType(ToolChoiceType.Known.AUTO);

    // Act & Assert
    assertThrows(IllegalStateException.class, () -> {
      toolChoice.asConfig();
    });
  }

  // ========== ToolChoiceConfig Serialization Tests ==========

  @Test
  public void testSerializeToolChoiceConfigWithAllowedTools() {
    // Arrange
    AllowedTools allowedTools = AllowedTools.builder()
        .mode("any")
        .tools("get_weather", "search_web")
        .build();
    ToolChoiceConfig config = ToolChoiceConfig.builder()
        .allowedTools(allowedTools)
        .build();
    ToolChoice toolChoice = ToolChoice.fromConfig(config);

    // Act
    String json = toolChoice.toJson();

    // Assert - should serialize as an object with allowed_tools field
    assertTrue(json.contains("\"allowed_tools\""));
    assertTrue(json.contains("\"mode\":\"any\""));
    assertTrue(json.contains("\"tools\":["));
    assertTrue(json.contains("\"get_weather\""));
    assertTrue(json.contains("\"search_web\""));
  }

  @Test
  public void testSerializeToolChoiceConfigEmpty() {
    // Arrange
    ToolChoiceConfig config = ToolChoiceConfig.builder().build();
    ToolChoice toolChoice = ToolChoice.fromConfig(config);

    // Act
    String json = toolChoice.toJson();

    // Assert - should serialize as empty object
    assertEquals("{}", json);
  }

  @Test
  public void testSerializeToolChoiceConfigFromBuilder() {
    // Arrange - using builder directly
    ToolChoice toolChoice = ToolChoice.fromConfig(
        ToolChoiceConfig.builder()
            .allowedTools(AllowedTools.of("validated", "tool1", "tool2"))
    );

    // Act
    String json = toolChoice.toJson();

    // Assert
    assertTrue(json.contains("\"allowed_tools\""));
    assertTrue(json.contains("\"mode\":\"validated\""));
    assertTrue(json.contains("\"tool1\""));
    assertTrue(json.contains("\"tool2\""));
  }

  @Test
  public void testToolChoiceConfigIsConfig() {
    // Arrange
    ToolChoiceConfig config = ToolChoiceConfig.builder().build();
    ToolChoice toolChoice = ToolChoice.fromConfig(config);

    // Act & Assert
    assertTrue(toolChoice.isConfig());
    assertFalse(toolChoice.isType());
  }

  @Test
  public void testToolChoiceConfigAsConfig() {
    // Arrange
    AllowedTools allowedTools = AllowedTools.of("auto", "tool_a");
    ToolChoiceConfig originalConfig = ToolChoiceConfig.builder()
        .allowedTools(allowedTools)
        .build();
    ToolChoice toolChoice = ToolChoice.fromConfig(originalConfig);

    // Act
    ToolChoiceConfig retrievedConfig = toolChoice.asConfig();

    // Assert
    assertTrue(retrievedConfig.allowedTools().isPresent());
    assertEquals("auto", retrievedConfig.allowedTools().get().mode().get());
  }

  @Test
  public void testToolChoiceConfigAsTypeThrows() {
    // Arrange
    ToolChoiceConfig config = ToolChoiceConfig.builder().build();
    ToolChoice toolChoice = ToolChoice.fromConfig(config);

    // Act & Assert
    assertThrows(IllegalStateException.class, () -> {
      toolChoice.asType();
    });
  }

  // ========== Round-trip Serialization Tests ==========

  @Test
  public void testRoundTripToolChoiceTypeAuto() {
    // Arrange
    ToolChoice original = ToolChoice.fromType(ToolChoiceType.Known.AUTO);

    // Act
    String json = original.toJson();

    // Assert - verify JSON structure (lowercase for API)
    assertEquals("\"auto\"", json);
    assertTrue(original.isType());
    assertEquals("auto", original.asType().toString());
  }

  @Test
  public void testRoundTripToolChoiceConfig() {
    // Arrange
    AllowedTools allowedTools = AllowedTools.of("any", "func1", "func2", "func3");
    ToolChoiceConfig config = ToolChoiceConfig.builder()
        .allowedTools(allowedTools)
        .build();
    ToolChoice original = ToolChoice.fromConfig(config);

    // Act
    String json = original.toJson();

    // Assert - verify JSON structure
    assertTrue(json.contains("\"allowed_tools\""));
    assertTrue(json.contains("\"mode\":\"any\""));
    assertTrue(json.contains("\"tools\":["));
    assertTrue(original.isConfig());
  }

  // ========== Integration with GenerationConfig Tests ==========

  @Test
  public void testToolChoiceTypeInGenerationConfig() {
    // Arrange
    GenerationConfig genConfig = GenerationConfig.builder()
        .toolChoice(ToolChoice.fromType(ToolChoiceType.Known.AUTO))
        .build();

    // Act
    String json = genConfig.toJson();

    // Assert - should include tool_choice as a lowercase string (matching API format)
    assertTrue(json.contains("\"tool_choice\":\"auto\""));
  }

  @Test
  public void testToolChoiceConfigInGenerationConfig() {
    // Arrange
    AllowedTools allowedTools = AllowedTools.of("validated", "weather_tool");
    ToolChoiceConfig config = ToolChoiceConfig.builder()
        .allowedTools(allowedTools)
        .build();
    GenerationConfig genConfig = GenerationConfig.builder()
        .toolChoice(ToolChoice.fromConfig(config))
        .build();

    // Act
    String json = genConfig.toJson();

    // Assert - should include tool_choice as an object
    assertTrue(json.contains("\"tool_choice\":{"));
    assertTrue(json.contains("\"allowed_tools\""));
    assertTrue(json.contains("\"mode\":\"validated\""));
    assertTrue(json.contains("\"weather_tool\""));
  }

  // ========== Edge Cases ==========

  @Test
  public void testSerializeToolChoiceWithUnspecifiedType() {
    // Arrange
    ToolChoice toolChoice = ToolChoice.fromType(ToolChoiceType.Known.TOOL_CHOICE_TYPE_UNSPECIFIED);

    // Act
    String json = toolChoice.toJson();

    // Assert - should serialize as lowercase string (matching API format)
    assertEquals("\"unspecified\"", json);
  }

  @Test
  public void testToolChoiceGetValue() {
    // Arrange
    ToolChoice typeChoice = ToolChoice.fromType(ToolChoiceType.Known.AUTO);
    ToolChoice configChoice = ToolChoice.fromConfig(ToolChoiceConfig.builder().build());

    // Act & Assert
    assertTrue(typeChoice.getValue() instanceof ToolChoiceType);
    assertTrue(configChoice.getValue() instanceof ToolChoiceConfig);
  }

  @Test
  public void testSerializeComplexToolChoiceConfig() {
    // Arrange - config with multiple tools
    AllowedTools allowedTools = AllowedTools.builder()
        .mode("any")
        .tools("get_weather", "get_news", "search_web", "calculator")
        .build();
    ToolChoiceConfig config = ToolChoiceConfig.builder()
        .allowedTools(allowedTools)
        .build();
    ToolChoice toolChoice = ToolChoice.fromConfig(config);

    // Act
    String json = toolChoice.toJson();

    // Assert - verify all tools are present
    assertTrue(json.contains("\"get_weather\""));
    assertTrue(json.contains("\"get_news\""));
    assertTrue(json.contains("\"search_web\""));
    assertTrue(json.contains("\"calculator\""));
  }

  @Test
  public void testSerializeToolChoiceConfigWithAutoMode() {
    // Arrange
    AllowedTools allowedTools = AllowedTools.of("auto", "tool1");
    ToolChoiceConfig config = ToolChoiceConfig.builder()
        .allowedTools(allowedTools)
        .build();
    ToolChoice toolChoice = ToolChoice.fromConfig(config);

    // Act
    String json = toolChoice.toJson();

    // Assert
    assertTrue(json.contains("\"mode\":\"auto\""));
    assertTrue(json.contains("\"tool1\""));
  }
}
