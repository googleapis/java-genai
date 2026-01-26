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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Tests for ToolChoiceDeserializer to ensure proper JSON deserialization of ToolChoice union types.
 *
 * <p>ToolChoiceDeserializer handles two different types:
 *
 * <ul>
 *   <li>ToolChoiceType - deserialized from a simple string (e.g., "auto", "any", "none",
 *       "validated")
 *   <li>ToolChoiceConfig - deserialized from an object with allowed_tools field
 * </ul>
 *
 * <p>This test class mirrors the structure of ToolChoiceSerializerTest for consistency.
 */
public class ToolChoiceDeserializerTest {

  // ========== ToolChoiceType Deserialization Tests ==========

  @Test
  public void testDeserializeToolChoiceTypeAuto() {
    // Arrange - JSON string
    String json = "\"auto\"";

    // Act - deserialize
    ToolChoice toolChoice = ToolChoice.fromJson(json);

    // Assert
    assertNotNull(toolChoice);
    assertTrue(toolChoice.isType());
    assertFalse(toolChoice.isConfig());
    assertEquals("auto", toolChoice.asType().toString());
  }

  @Test
  public void testDeserializeToolChoiceTypeAny() {
    // Arrange - JSON string
    String json = "\"any\"";

    // Act - deserialize
    ToolChoice toolChoice = ToolChoice.fromJson(json);

    // Assert
    assertNotNull(toolChoice);
    assertTrue(toolChoice.isType());
    assertFalse(toolChoice.isConfig());
    assertEquals("any", toolChoice.asType().toString());
  }

  @Test
  public void testDeserializeToolChoiceTypeNone() {
    // Arrange - JSON string
    String json = "\"none\"";

    // Act - deserialize
    ToolChoice toolChoice = ToolChoice.fromJson(json);

    // Assert
    assertNotNull(toolChoice);
    assertTrue(toolChoice.isType());
    assertEquals("none", toolChoice.asType().toString());
  }

  @Test
  public void testDeserializeToolChoiceTypeValidated() {
    // Arrange - JSON string
    String json = "\"validated\"";

    // Act - deserialize
    ToolChoice toolChoice = ToolChoice.fromJson(json);

    // Assert
    assertNotNull(toolChoice);
    assertTrue(toolChoice.isType());
    assertEquals("validated", toolChoice.asType().toString());
  }

  @Test
  public void testDeserializeToolChoiceTypeUnspecified() {
    // Arrange - JSON string
    String json = "\"unspecified\"";

    // Act - deserialize
    ToolChoice toolChoice = ToolChoice.fromJson(json);

    // Assert
    assertNotNull(toolChoice);
    assertTrue(toolChoice.isType());
    assertEquals("unspecified", toolChoice.asType().toString());
  }

  @Test
  public void testDeserializeToolChoiceTypeFromString() {
    // Arrange - custom string value
    String json = "\"custom_mode\"";

    // Act - deserialize
    ToolChoice toolChoice = ToolChoice.fromJson(json);

    // Assert
    assertNotNull(toolChoice);
    assertTrue(toolChoice.isType());
    assertEquals("custom_mode", toolChoice.asType().toString());
  }

  @Test
  public void testDeserializeToolChoiceTypeVerifyIsType() {
    // Arrange
    String json = "\"auto\"";

    // Act
    ToolChoice toolChoice = ToolChoice.fromJson(json);

    // Assert
    assertTrue(toolChoice.isType());
    assertFalse(toolChoice.isConfig());
  }

  // ========== ToolChoiceConfig Deserialization Tests ==========

  @Test
  public void testDeserializeToolChoiceConfigWithAllowedTools() {
    // Arrange - JSON object
    String json =
        "{\"allowed_tools\":{\"mode\":\"any\",\"tools\":[\"get_weather\",\"search_web\"]}}";

    // Act - deserialize
    ToolChoice toolChoice = ToolChoice.fromJson(json);

    // Assert
    assertNotNull(toolChoice);
    assertTrue(toolChoice.isConfig());
    assertFalse(toolChoice.isType());
    ToolChoiceConfig config = toolChoice.asConfig();
    assertTrue(config.allowedTools().isPresent());
    assertEquals("any", config.allowedTools().get().mode().get());
    assertTrue(config.allowedTools().get().tools().isPresent());
    assertEquals(2, config.allowedTools().get().tools().get().size());
    assertTrue(config.allowedTools().get().tools().get().contains("get_weather"));
    assertTrue(config.allowedTools().get().tools().get().contains("search_web"));
  }

  @Test
  public void testDeserializeToolChoiceConfigEmpty() {
    // Arrange - empty object {}
    String json = "{}";

    // Act - deserialize
    ToolChoice toolChoice = ToolChoice.fromJson(json);

    // Assert
    assertNotNull(toolChoice);
    assertTrue(toolChoice.isConfig());
    ToolChoiceConfig config = toolChoice.asConfig();
    assertFalse(config.allowedTools().isPresent());
  }

  @Test
  public void testDeserializeToolChoiceConfigComplexTools() {
    // Arrange - multiple tools
    String json =
        "{\"allowed_tools\":{\"mode\":\"validated\",\"tools\":[\"tool1\",\"tool2\",\"tool3\",\"tool4\"]}}";

    // Act - deserialize
    ToolChoice toolChoice = ToolChoice.fromJson(json);

    // Assert
    assertNotNull(toolChoice);
    assertTrue(toolChoice.isConfig());
    ToolChoiceConfig config = toolChoice.asConfig();
    assertTrue(config.allowedTools().isPresent());
    assertEquals("validated", config.allowedTools().get().mode().get());
    assertEquals(4, config.allowedTools().get().tools().get().size());
  }

  @Test
  public void testDeserializeToolChoiceConfigWithAutoMode() {
    // Arrange - mode: "auto"
    String json = "{\"allowed_tools\":{\"mode\":\"auto\",\"tools\":[\"weather_tool\"]}}";

    // Act - deserialize
    ToolChoice toolChoice = ToolChoice.fromJson(json);

    // Assert
    assertNotNull(toolChoice);
    assertTrue(toolChoice.isConfig());
    ToolChoiceConfig config = toolChoice.asConfig();
    assertTrue(config.allowedTools().isPresent());
    assertEquals("auto", config.allowedTools().get().mode().get());
    assertTrue(config.allowedTools().get().tools().get().contains("weather_tool"));
  }

  @Test
  public void testDeserializeToolChoiceConfigVerifyIsConfig() {
    // Arrange
    String json = "{\"allowed_tools\":{\"mode\":\"any\",\"tools\":[\"tool1\"]}}";

    // Act
    ToolChoice toolChoice = ToolChoice.fromJson(json);

    // Assert
    assertTrue(toolChoice.isConfig());
    assertFalse(toolChoice.isType());
  }

  // ========== Round-trip Tests ==========

  @Test
  public void testRoundTripToolChoiceTypeAuto() {
    // Arrange
    ToolChoice original = ToolChoice.fromType(ToolChoiceType.Known.AUTO);

    // Act - serialize then deserialize
    String json = original.toJson();
    ToolChoice deserialized = ToolChoice.fromJson(json);

    // Assert
    assertNotNull(deserialized);
    assertTrue(deserialized.isType());
    assertEquals("auto", deserialized.asType().toString());
    assertEquals(original.getValue().toString(), deserialized.getValue().toString());
  }

  @Test
  public void testRoundTripToolChoiceTypeValidated() {
    // Arrange
    ToolChoice original = ToolChoice.fromType(ToolChoiceType.Known.VALIDATED);

    // Act - serialize then deserialize
    String json = original.toJson();
    ToolChoice deserialized = ToolChoice.fromJson(json);

    // Assert
    assertNotNull(deserialized);
    assertTrue(deserialized.isType());
    assertEquals("validated", deserialized.asType().toString());
    assertEquals(original.getValue().toString(), deserialized.getValue().toString());
  }

  @Test
  public void testRoundTripToolChoiceConfig() {
    // Arrange
    AllowedTools allowedTools = AllowedTools.of("any", "func1", "func2", "func3");
    ToolChoiceConfig config = ToolChoiceConfig.builder().allowedTools(allowedTools).build();
    ToolChoice original = ToolChoice.fromConfig(config);

    // Act - serialize then deserialize
    String json = original.toJson();
    ToolChoice deserialized = ToolChoice.fromJson(json);

    // Assert
    assertNotNull(deserialized);
    assertTrue(deserialized.isConfig());
    ToolChoiceConfig deserializedConfig = deserialized.asConfig();
    assertTrue(deserializedConfig.allowedTools().isPresent());
    assertEquals("any", deserializedConfig.allowedTools().get().mode().get());
    assertEquals(3, deserializedConfig.allowedTools().get().tools().get().size());
    assertTrue(deserializedConfig.allowedTools().get().tools().get().contains("func1"));
    assertTrue(deserializedConfig.allowedTools().get().tools().get().contains("func2"));
    assertTrue(deserializedConfig.allowedTools().get().tools().get().contains("func3"));
  }

  @Test
  public void testRoundTripToolChoiceConfigComplex() {
    // Arrange - complex config round-trip
    AllowedTools allowedTools =
        AllowedTools.builder()
            .mode("validated")
            .tools("get_weather", "get_news", "search_web", "calculator")
            .build();
    ToolChoiceConfig config = ToolChoiceConfig.builder().allowedTools(allowedTools).build();
    ToolChoice original = ToolChoice.fromConfig(config);

    // Act - serialize then deserialize
    String json = original.toJson();
    ToolChoice deserialized = ToolChoice.fromJson(json);

    // Assert
    assertNotNull(deserialized);
    assertTrue(deserialized.isConfig());
    ToolChoiceConfig deserializedConfig = deserialized.asConfig();
    assertTrue(deserializedConfig.allowedTools().isPresent());
    assertEquals("validated", deserializedConfig.allowedTools().get().mode().get());
    assertEquals(4, deserializedConfig.allowedTools().get().tools().get().size());
  }

  // ========== Integration with GenerationConfig Tests ==========

  @Test
  public void testDeserializeToolChoiceTypeFromGenerationConfig() {
    // Arrange - full GenerationConfig JSON
    String json = "{\"tool_choice\":\"auto\",\"temperature\":0.7}";

    // Act
    GenerationConfig config = GenerationConfig.fromJson(json);

    // Assert
    assertNotNull(config);
    assertTrue(config.toolChoice().isPresent());
    ToolChoice toolChoice = config.toolChoice().get();
    assertTrue(toolChoice.isType());
    assertEquals("auto", toolChoice.asType().toString());
  }

  @Test
  public void testDeserializeToolChoiceConfigFromGenerationConfig() {
    // Arrange - full GenerationConfig JSON with ToolChoiceConfig
    String json =
        "{\"tool_choice\":{\"allowed_tools\":{\"mode\":\"validated\",\"tools\":[\"weather_tool\"]}},\"temperature\":0.5}";

    // Act
    GenerationConfig config = GenerationConfig.fromJson(json);

    // Assert
    assertNotNull(config);
    assertTrue(config.toolChoice().isPresent());
    ToolChoice toolChoice = config.toolChoice().get();
    assertTrue(toolChoice.isConfig());
    ToolChoiceConfig toolChoiceConfig = toolChoice.asConfig();
    assertTrue(toolChoiceConfig.allowedTools().isPresent());
    assertEquals("validated", toolChoiceConfig.allowedTools().get().mode().get());
    assertTrue(toolChoiceConfig.allowedTools().get().tools().get().contains("weather_tool"));
  }

  // ========== Edge Cases ==========

  @Test
  public void testDeserializeNull() {
    // Arrange
    String json = "null";

    // Act
    ToolChoice toolChoice = ToolChoice.fromJson(json);

    // Assert
    assertNull(toolChoice);
  }

  @Test
  public void testDeserializeInvalidTokenThrows() {
    // Arrange - number instead of string/object
    String json = "123";

    // Act & Assert
    assertThrows(
        Exception.class,
        () -> {
          ToolChoice.fromJson(json);
        });
  }

  @Test
  public void testDeserializeBooleanThrows() {
    // Arrange - boolean instead of string/object
    String json = "true";

    // Act & Assert
    assertThrows(
        Exception.class,
        () -> {
          ToolChoice.fromJson(json);
        });
  }

  @Test
  public void testDeserializeMalformedJsonThrows() {
    // Arrange - malformed JSON
    String json = "{\"allowed_tools\":invalid}";

    // Act & Assert
    assertThrows(
        Exception.class,
        () -> {
          ToolChoice.fromJson(json);
        });
  }

  @Test
  public void testDeserializeToolChoiceGetValue() {
    // Arrange
    String typeJson = "\"auto\"";
    String configJson = "{\"allowed_tools\":{\"mode\":\"any\",\"tools\":[\"tool1\"]}}";

    // Act
    ToolChoice typeChoice = ToolChoice.fromJson(typeJson);
    ToolChoice configChoice = ToolChoice.fromJson(configJson);

    // Assert - getValue() returns correct type
    assertTrue(typeChoice.getValue() instanceof ToolChoiceType);
    assertTrue(configChoice.getValue() instanceof ToolChoiceConfig);
  }

  @Test
  public void testDeserializeToolChoiceAsType() {
    // Arrange
    String json = "\"validated\"";

    // Act
    ToolChoice toolChoice = ToolChoice.fromJson(json);

    // Assert - asType() works after deserialization
    assertNotNull(toolChoice);
    ToolChoiceType type = toolChoice.asType();
    assertEquals("validated", type.toString());
  }

  @Test
  public void testDeserializeToolChoiceAsConfig() {
    // Arrange
    String json = "{\"allowed_tools\":{\"mode\":\"auto\",\"tools\":[\"tool1\",\"tool2\"]}}";

    // Act
    ToolChoice toolChoice = ToolChoice.fromJson(json);

    // Assert - asConfig() works after deserialization
    assertNotNull(toolChoice);
    ToolChoiceConfig config = toolChoice.asConfig();
    assertTrue(config.allowedTools().isPresent());
    assertEquals("auto", config.allowedTools().get().mode().get());
  }

  @Test
  public void testDeserializeToolChoiceAsTypeThrows() {
    // Arrange - deserialize a config
    String json = "{\"allowed_tools\":{\"mode\":\"any\",\"tools\":[\"tool1\"]}}";

    // Act
    ToolChoice toolChoice = ToolChoice.fromJson(json);

    // Assert - asType() throws when it's a config
    assertThrows(
        IllegalStateException.class,
        () -> {
          toolChoice.asType();
        });
  }

  @Test
  public void testDeserializeToolChoiceAsConfigThrows() {
    // Arrange - deserialize a type
    String json = "\"auto\"";

    // Act
    ToolChoice toolChoice = ToolChoice.fromJson(json);

    // Assert - asConfig() throws when it's a type
    assertThrows(
        IllegalStateException.class,
        () -> {
          toolChoice.asConfig();
        });
  }

  @Test
  public void testDeserializeArrayThrows() {
    // Arrange - array instead of string/object
    String json = "[\"auto\",\"any\"]";

    // Act & Assert
    assertThrows(
        Exception.class,
        () -> {
          ToolChoice.fromJson(json);
        });
  }

  @Test
  public void testDeserializeEmptyStringThrows() {
    // Arrange - empty string (not a valid ToolChoiceType)
    String json = "\"\"";

    // Act - deserialize (should work, but results in empty type string)
    ToolChoice toolChoice = ToolChoice.fromJson(json);

    // Assert - should deserialize as a type with empty string value
    assertNotNull(toolChoice);
    assertTrue(toolChoice.isType());
    assertEquals("", toolChoice.asType().toString());
  }

  @Test
  public void testDeserializeToolChoiceConfigWithMissingMode() {
    // Arrange - allowed_tools without mode field
    String json = "{\"allowed_tools\":{\"tools\":[\"tool1\",\"tool2\"]}}";

    // Act
    ToolChoice toolChoice = ToolChoice.fromJson(json);

    // Assert - should deserialize successfully (mode is optional)
    assertNotNull(toolChoice);
    assertTrue(toolChoice.isConfig());
    ToolChoiceConfig config = toolChoice.asConfig();
    assertTrue(config.allowedTools().isPresent());
    assertFalse(config.allowedTools().get().mode().isPresent());
    assertTrue(config.allowedTools().get().tools().isPresent());
    assertEquals(2, config.allowedTools().get().tools().get().size());
  }

  @Test
  public void testDeserializeToolChoiceConfigWithEmptyTools() {
    // Arrange - allowed_tools with empty tools array
    String json = "{\"allowed_tools\":{\"mode\":\"any\",\"tools\":[]}}";

    // Act
    ToolChoice toolChoice = ToolChoice.fromJson(json);

    // Assert
    assertNotNull(toolChoice);
    assertTrue(toolChoice.isConfig());
    ToolChoiceConfig config = toolChoice.asConfig();
    assertTrue(config.allowedTools().isPresent());
    assertEquals("any", config.allowedTools().get().mode().get());
    assertTrue(config.allowedTools().get().tools().isPresent());
    assertEquals(0, config.allowedTools().get().tools().get().size());
  }

  @Test
  public void testDeserializeMultipleToolChoiceTypes() {
    // Arrange - test multiple different type values
    String[] typeValues = {"auto", "any", "none", "validated", "unspecified"};

    for (String typeValue : typeValues) {
      // Arrange
      String json = "\"" + typeValue + "\"";

      // Act
      ToolChoice toolChoice = ToolChoice.fromJson(json);

      // Assert
      assertNotNull(toolChoice, "Failed for type: " + typeValue);
      assertTrue(toolChoice.isType(), "Failed for type: " + typeValue);
      assertEquals(typeValue, toolChoice.asType().toString(), "Failed for type: " + typeValue);
    }
  }
}
