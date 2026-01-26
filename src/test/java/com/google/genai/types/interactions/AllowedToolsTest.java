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
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Comprehensive tests for AllowedTools configuration class.
 *
 * <p>AllowedTools specifies which tools are permitted in tool choice configurations, with a mode
 * (auto, any, none, validated) and a list of tool names.
 */
public class AllowedToolsTest {

  // ========== Builder Tests ==========

  @Test
  public void testAllowedToolsBuilderWithModeAndTools() {
    // Arrange & Act
    AllowedTools allowedTools = AllowedTools.builder()
        .mode("auto")
        .tools("get_weather", "get_forecast", "search_web")
        .build();

    // Assert
    assertTrue(allowedTools.mode().isPresent());
    assertEquals("auto", allowedTools.mode().get());
    assertTrue(allowedTools.tools().isPresent());
    assertEquals(3, allowedTools.tools().get().size());
    assertEquals("get_weather", allowedTools.tools().get().get(0));
    assertEquals("get_forecast", allowedTools.tools().get().get(1));
    assertEquals("search_web", allowedTools.tools().get().get(2));
  }

  @Test
  public void testAllowedToolsBuilderWithModeOnly() {
    // Arrange & Act
    AllowedTools allowedTools = AllowedTools.builder()
        .mode("any")
        .build();

    // Assert
    assertTrue(allowedTools.mode().isPresent());
    assertEquals("any", allowedTools.mode().get());
    assertFalse(allowedTools.tools().isPresent());
  }

  @Test
  public void testAllowedToolsBuilderWithToolsOnly() {
    // Arrange & Act
    AllowedTools allowedTools = AllowedTools.builder()
        .tools("tool1", "tool2")
        .build();

    // Assert
    assertFalse(allowedTools.mode().isPresent());
    assertTrue(allowedTools.tools().isPresent());
    assertEquals(2, allowedTools.tools().get().size());
  }

  @Test
  public void testAllowedToolsBuilderEmpty() {
    // Arrange & Act
    AllowedTools allowedTools = AllowedTools.builder().build();

    // Assert
    assertFalse(allowedTools.mode().isPresent());
    assertFalse(allowedTools.tools().isPresent());
  }

  @Test
  public void testAllowedToolsBuilderWithList() {
    // Arrange
    List<String> toolsList = Arrays.asList("calculator", "converter", "translator");

    // Act
    AllowedTools allowedTools = AllowedTools.builder()
        .mode("validated")
        .tools(toolsList)
        .build();

    // Assert
    assertTrue(allowedTools.mode().isPresent());
    assertEquals("validated", allowedTools.mode().get());
    assertTrue(allowedTools.tools().isPresent());
    assertEquals(3, allowedTools.tools().get().size());
    assertEquals(toolsList, allowedTools.tools().get());
  }

  // ========== Factory Method Tests ==========

  @Test
  public void testAllowedToolsOfWithVarargs() {
    // Arrange & Act
    AllowedTools allowedTools = AllowedTools.of("auto", "weather_tool", "news_tool");

    // Assert
    assertTrue(allowedTools.mode().isPresent());
    assertEquals("auto", allowedTools.mode().get());
    assertTrue(allowedTools.tools().isPresent());
    assertEquals(2, allowedTools.tools().get().size());
    assertEquals("weather_tool", allowedTools.tools().get().get(0));
    assertEquals("news_tool", allowedTools.tools().get().get(1));
  }

  @Test
  public void testAllowedToolsOfWithList() {
    // Arrange
    List<String> tools = Arrays.asList("search", "browse", "analyze");

    // Act
    AllowedTools allowedTools = AllowedTools.of("any", tools);

    // Assert
    assertEquals("any", allowedTools.mode().get());
    assertEquals(3, allowedTools.tools().get().size());
    assertEquals(tools, allowedTools.tools().get());
  }

  @Test
  public void testAllowedToolsOfWithSingleTool() {
    // Arrange & Act
    AllowedTools allowedTools = AllowedTools.of("validated", "single_tool");

    // Assert
    assertEquals("validated", allowedTools.mode().get());
    assertEquals(1, allowedTools.tools().get().size());
    assertEquals("single_tool", allowedTools.tools().get().get(0));
  }

  // ========== Mode Value Tests ==========

  @Test
  public void testAllowedToolsModeAuto() {
    // Arrange & Act
    AllowedTools allowedTools = AllowedTools.builder().mode("auto").build();

    // Assert
    assertEquals("auto", allowedTools.mode().get());
  }

  @Test
  public void testAllowedToolsModeAny() {
    // Arrange & Act
    AllowedTools allowedTools = AllowedTools.builder().mode("any").build();

    // Assert
    assertEquals("any", allowedTools.mode().get());
  }

  @Test
  public void testAllowedToolsModeNone() {
    // Arrange & Act
    AllowedTools allowedTools = AllowedTools.builder().mode("none").build();

    // Assert
    assertEquals("none", allowedTools.mode().get());
  }

  @Test
  public void testAllowedToolsModeValidated() {
    // Arrange & Act
    AllowedTools allowedTools = AllowedTools.builder().mode("validated").build();

    // Assert
    assertEquals("validated", allowedTools.mode().get());
  }

  @Test
  public void testAllowedToolsCustomMode() {
    // Arrange & Act - test with custom/unknown mode value
    AllowedTools allowedTools = AllowedTools.builder().mode("custom_mode").build();

    // Assert
    assertEquals("custom_mode", allowedTools.mode().get());
  }

  // ========== Clear Methods Tests ==========

  @Test
  public void testAllowedToolsClearMode() {
    // Arrange
    AllowedTools.Builder builder = AllowedTools.builder()
        .mode("auto")
        .tools("tool1");

    // Act
    AllowedTools allowedTools = builder.clearMode().build();

    // Assert
    assertFalse(allowedTools.mode().isPresent());
    assertTrue(allowedTools.tools().isPresent());
  }

  @Test
  public void testAllowedToolsClearTools() {
    // Arrange
    AllowedTools.Builder builder = AllowedTools.builder()
        .mode("any")
        .tools("tool1", "tool2");

    // Act
    AllowedTools allowedTools = builder.clearTools().build();

    // Assert
    assertTrue(allowedTools.mode().isPresent());
    assertFalse(allowedTools.tools().isPresent());
  }

  @Test
  public void testAllowedToolsClearBoth() {
    // Arrange
    AllowedTools.Builder builder = AllowedTools.builder()
        .mode("validated")
        .tools("tool1");

    // Act
    AllowedTools allowedTools = builder.clearMode().clearTools().build();

    // Assert
    assertFalse(allowedTools.mode().isPresent());
    assertFalse(allowedTools.tools().isPresent());
  }

  // ========== ToBuilder Tests ==========

  @Test
  public void testAllowedToolsToBuilder() {
    // Arrange
    AllowedTools original = AllowedTools.of("auto", "tool_a", "tool_b");

    // Act
    AllowedTools modified = original.toBuilder()
        .mode("any")
        .build();

    // Assert
    assertEquals("any", modified.mode().get());
    assertEquals(2, modified.tools().get().size());
    assertEquals("tool_a", modified.tools().get().get(0));
  }

  @Test
  public void testAllowedToolsToBuilderPreservesValues() {
    // Arrange
    AllowedTools original = AllowedTools.of("validated", "x", "y", "z");

    // Act
    AllowedTools copy = original.toBuilder().build();

    // Assert
    assertEquals(original.mode().get(), copy.mode().get());
    assertEquals(original.tools().get().size(), copy.tools().get().size());
    assertEquals(original.tools().get(), copy.tools().get());
  }

  // ========== JSON Serialization Tests ==========

  @Test
  public void testAllowedToolsJsonSerialization() {
    // Arrange
    AllowedTools allowedTools = AllowedTools.of("auto", "get_weather", "search_web");

    // Act
    String json = allowedTools.toJson();

    // Assert
    assertTrue(json.contains("\"mode\":\"auto\""));
    assertTrue(json.contains("\"tools\":["));
    assertTrue(json.contains("\"get_weather\""));
    assertTrue(json.contains("\"search_web\""));
  }

  @Test
  public void testAllowedToolsJsonSerializationModeOnly() {
    // Arrange
    AllowedTools allowedTools = AllowedTools.builder().mode("none").build();

    // Act
    String json = allowedTools.toJson();

    // Assert
    assertTrue(json.contains("\"mode\":\"none\""));
    assertFalse(json.contains("\"tools\""));
  }

  @Test
  public void testAllowedToolsJsonSerializationToolsOnly() {
    // Arrange
    AllowedTools allowedTools = AllowedTools.builder()
        .tools("tool1", "tool2")
        .build();

    // Act
    String json = allowedTools.toJson();

    // Assert
    assertTrue(json.contains("\"tools\":[\"tool1\",\"tool2\"]"));
    assertFalse(json.contains("\"mode\""));
  }

  @Test
  public void testAllowedToolsJsonSerializationEmpty() {
    // Arrange
    AllowedTools allowedTools = AllowedTools.builder().build();

    // Act
    String json = allowedTools.toJson();

    // Assert
    assertEquals("{}", json);
  }

  // ========== JSON Deserialization Tests ==========

  @Test
  public void testAllowedToolsJsonDeserialization() {
    // Arrange
    String json = "{\"mode\":\"validated\",\"tools\":[\"analyzer\",\"parser\"]}";

    // Act
    AllowedTools allowedTools = AllowedTools.fromJson(json);

    // Assert
    assertTrue(allowedTools.mode().isPresent());
    assertEquals("validated", allowedTools.mode().get());
    assertTrue(allowedTools.tools().isPresent());
    assertEquals(2, allowedTools.tools().get().size());
    assertEquals("analyzer", allowedTools.tools().get().get(0));
    assertEquals("parser", allowedTools.tools().get().get(1));
  }

  @Test
  public void testAllowedToolsJsonDeserializationModeOnly() {
    // Arrange
    String json = "{\"mode\":\"auto\"}";

    // Act
    AllowedTools allowedTools = AllowedTools.fromJson(json);

    // Assert
    assertEquals("auto", allowedTools.mode().get());
    assertFalse(allowedTools.tools().isPresent());
  }

  @Test
  public void testAllowedToolsJsonDeserializationToolsOnly() {
    // Arrange
    String json = "{\"tools\":[\"a\",\"b\",\"c\"]}";

    // Act
    AllowedTools allowedTools = AllowedTools.fromJson(json);

    // Assert
    assertFalse(allowedTools.mode().isPresent());
    assertEquals(3, allowedTools.tools().get().size());
  }

  @Test
  public void testAllowedToolsJsonDeserializationEmpty() {
    // Arrange
    String json = "{}";

    // Act
    AllowedTools allowedTools = AllowedTools.fromJson(json);

    // Assert
    assertFalse(allowedTools.mode().isPresent());
    assertFalse(allowedTools.tools().isPresent());
  }

  // ========== Round-trip Serialization Tests ==========

  @Test
  public void testAllowedToolsRoundTrip() {
    // Arrange
    AllowedTools original = AllowedTools.of("any", "func1", "func2", "func3");

    // Act
    String json = original.toJson();
    AllowedTools deserialized = AllowedTools.fromJson(json);

    // Assert
    assertEquals(original.mode().get(), deserialized.mode().get());
    assertEquals(original.tools().get().size(), deserialized.tools().get().size());
    assertEquals(original.tools().get(), deserialized.tools().get());
  }

  @Test
  public void testAllowedToolsRoundTripModeOnly() {
    // Arrange
    AllowedTools original = AllowedTools.builder().mode("validated").build();

    // Act
    String json = original.toJson();
    AllowedTools deserialized = AllowedTools.fromJson(json);

    // Assert
    assertEquals(original.mode().get(), deserialized.mode().get());
    assertFalse(deserialized.tools().isPresent());
  }

  // ========== Edge Cases ==========

  @Test
  public void testAllowedToolsWithEmptyToolsList() {
    // Arrange
    AllowedTools allowedTools = AllowedTools.builder()
        .mode("auto")
        .tools(Arrays.asList())
        .build();

    // Assert - empty list is still present
    assertTrue(allowedTools.tools().isPresent());
    assertEquals(0, allowedTools.tools().get().size());
  }

  @Test
  public void testAllowedToolsWithManyTools() {
    // Arrange - test with many tools
    AllowedTools allowedTools = AllowedTools.of(
        "any",
        "tool1", "tool2", "tool3", "tool4", "tool5",
        "tool6", "tool7", "tool8", "tool9", "tool10"
    );

    // Assert
    assertEquals(10, allowedTools.tools().get().size());
    assertEquals("tool1", allowedTools.tools().get().get(0));
    assertEquals("tool10", allowedTools.tools().get().get(9));
  }

  @Test
  public void testAllowedToolsWithSpecialCharactersInToolNames() {
    // Arrange
    AllowedTools allowedTools = AllowedTools.of(
        "auto",
        "get_weather-v2",
        "search::web",
        "tool.name.with.dots"
    );

    // Assert
    assertEquals(3, allowedTools.tools().get().size());
    assertTrue(allowedTools.tools().get().contains("get_weather-v2"));
    assertTrue(allowedTools.tools().get().contains("search::web"));
    assertTrue(allowedTools.tools().get().contains("tool.name.with.dots"));
  }

  @Test
  public void testAllowedToolsNotNull() {
    // Arrange
    AllowedTools allowedTools = AllowedTools.builder().build();

    // Assert
    assertNotNull(allowedTools);
    assertNotNull(allowedTools.mode());
    assertNotNull(allowedTools.tools());
  }

  // ========== Integration Tests ==========

  @Test
  public void testAllowedToolsInToolChoiceConfig() {
    // Arrange
    AllowedTools allowedTools = AllowedTools.of("validated", "weather", "news");
    ToolChoiceConfig config = ToolChoiceConfig.builder()
        .allowedTools(allowedTools)
        .build();

    // Act
    String json = config.toJson();

    // Assert
    assertTrue(json.contains("\"allowed_tools\""));
    assertTrue(json.contains("\"mode\":\"validated\""));
    assertTrue(json.contains("\"weather\""));
    assertTrue(json.contains("\"news\""));
  }

  @Test
  public void testAllowedToolsInToolChoice() {
    // Arrange
    AllowedTools allowedTools = AllowedTools.of("any", "search", "calculate");
    ToolChoiceConfig config = ToolChoiceConfig.builder()
        .allowedTools(allowedTools)
        .build();
    ToolChoice toolChoice = ToolChoice.fromConfig(config);

    // Act
    String json = toolChoice.toJson();

    // Assert
    assertTrue(json.contains("\"allowed_tools\""));
    assertTrue(json.contains("\"mode\":\"any\""));
    assertTrue(json.contains("\"search\""));
    assertTrue(json.contains("\"calculate\""));
  }
}
