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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.genai.JsonSerializable;
import com.google.genai.types.Schema;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

/**
 * Tests for tool types in the Interactions API.
 *
 * <p>Tests the following tool types:
 * <ul>
 *   <li>Function - Function tool with name, description, parameters</li>
 *   <li>GoogleSearch - Google Search tool (empty builder)</li>
 *   <li>CodeExecution - Code execution tool (empty builder)</li>
 *   <li>UrlContext - URL context tool (empty builder)</li>
 *   <li>ComputerUse - Computer use tool with environment and excluded functions</li>
 *   <li>Tool - Polymorphic deserialization</li>
 * </ul>
 *
 * <p>Note: Methods annotated with @ExcludeFromGeneratedCoverageReport are not directly tested.
 */
public class ToolTypesTest {

  // ========== Function Tests ==========

  @Test
  public void testFunctionWithAllFields() {
    // Arrange
    Map<String, Schema> properties = new HashMap<>();
    properties.put("location", Schema.builder().type("string").build());

    Schema params = Schema.builder()
        .type("object")
        .properties(properties)
        .build();

    // Act
    Function function = Function.builder()
        .name("get_weather")
        .description("Get the current weather for a location")
        .parameters(params)
        .build();

    // Assert
    assertTrue(function.name().isPresent());
    assertEquals("get_weather", function.name().get());
    assertTrue(function.description().isPresent());
    assertEquals("Get the current weather for a location", function.description().get());
    assertTrue(function.parameters().isPresent());
  }

  @Test
  public void testFunctionJsonSerialization() {
    // Arrange
    Function function = Function.builder()
        .name("test_func")
        .description("A test function")
        .build();

    // Act
    String json = function.toJson();

    // Assert
    assertTrue(json.contains("\"name\":\"test_func\""));
    assertTrue(json.contains("\"description\":\"A test function\""));
    assertTrue(json.contains("\"type\":\"function\""));
  }

  @Test
  public void testFunctionToBuilder() {
    // Arrange
    Function original = Function.builder()
        .name("original_func")
        .description("Original description")
        .build();

    // Act
    Function modified = original.toBuilder()
        .description("Modified description")
        .build();

    // Assert
    assertEquals("original_func", modified.name().get());
    assertEquals("Modified description", modified.description().get());
  }

  @Test
  public void testFunctionWithSchemaBuilder() {
    // Arrange & Act
    Function function = Function.builder()
        .name("search")
        .parameters(Schema.builder()
            .type("object")
            .description("Search parameters"))
        .build();

    // Assert
    assertTrue(function.parameters().isPresent());
    assertEquals("object", function.parameters().get().type().get().toString());
  }

  // ========== GoogleSearch Tests ==========

  @Test
  public void testGoogleSearchEmptyBuilder() {
    // Arrange & Act
    GoogleSearch googleSearch = GoogleSearch.builder().build();

    // Assert
    assertNotNull(googleSearch);
  }

  @Test
  public void testGoogleSearchJsonSerialization() {
    // Arrange
    GoogleSearch googleSearch = GoogleSearch.builder().build();

    // Act
    String json = googleSearch.toJson();

    // Assert
    assertTrue(json.contains("\"type\":\"google_search\""));
  }

  @Test
  public void testGoogleSearchToBuilder() {
    // Arrange
    GoogleSearch original = GoogleSearch.builder().build();

    // Act
    GoogleSearch copy = original.toBuilder().build();

    // Assert
    assertNotNull(copy);
  }

  // ========== CodeExecution Tests ==========

  @Test
  public void testCodeExecutionEmptyBuilder() {
    // Arrange & Act
    CodeExecution codeExecution = CodeExecution.builder().build();

    // Assert
    assertNotNull(codeExecution);
  }

  @Test
  public void testCodeExecutionJsonSerialization() {
    // Arrange
    CodeExecution codeExecution = CodeExecution.builder().build();

    // Act
    String json = codeExecution.toJson();

    // Assert
    assertTrue(json.contains("\"type\":\"code_execution\""));
  }

  @Test
  public void testCodeExecutionToBuilder() {
    // Arrange
    CodeExecution original = CodeExecution.builder().build();

    // Act
    CodeExecution copy = original.toBuilder().build();

    // Assert
    assertNotNull(copy);
  }

  // ========== UrlContext Tests ==========

  @Test
  public void testUrlContextEmptyBuilder() {
    // Arrange & Act
    UrlContext urlContext = UrlContext.builder().build();

    // Assert
    assertNotNull(urlContext);
  }

  @Test
  public void testUrlContextJsonSerialization() {
    // Arrange
    UrlContext urlContext = UrlContext.builder().build();

    // Act
    String json = urlContext.toJson();

    // Assert
    assertTrue(json.contains("\"type\":\"url_context\""));
  }

  @Test
  public void testUrlContextToBuilder() {
    // Arrange
    UrlContext original = UrlContext.builder().build();

    // Act
    UrlContext copy = original.toBuilder().build();

    // Assert
    assertNotNull(copy);
  }

  // ========== ComputerUse Tests ==========

  @Test
  public void testComputerUseWithEnvironment() {
    // Arrange & Act
    ComputerUse computerUse = ComputerUse.builder()
        .environment("browser")
        .build();

    // Assert
    assertTrue(computerUse.environment().isPresent());
    assertEquals("browser", computerUse.environment().get());
  }

  @Test
  public void testComputerUseWithExcludedFunctionsList() {
    // Arrange & Act
    ComputerUse computerUse = ComputerUse.builder()
        .environment("desktop")
        .excludedPredefinedFunctions(Arrays.asList("screenshot", "click"))
        .build();

    // Assert
    assertTrue(computerUse.excludedPredefinedFunctions().isPresent());
    assertEquals(2, computerUse.excludedPredefinedFunctions().get().size());
    assertTrue(computerUse.excludedPredefinedFunctions().get().contains("screenshot"));
    assertTrue(computerUse.excludedPredefinedFunctions().get().contains("click"));
  }

  @Test
  public void testComputerUseWithExcludedFunctionsVarargs() {
    // Arrange & Act
    ComputerUse computerUse = ComputerUse.builder()
        .environment("browser")
        .excludedPredefinedFunctions("scroll", "type", "navigate")
        .build();

    // Assert
    assertTrue(computerUse.excludedPredefinedFunctions().isPresent());
    assertEquals(3, computerUse.excludedPredefinedFunctions().get().size());
  }

  @Test
  public void testComputerUseJsonSerialization() {
    // Arrange
    ComputerUse computerUse = ComputerUse.builder()
        .environment("browser")
        .excludedPredefinedFunctions("func1", "func2")
        .build();

    // Act
    String json = computerUse.toJson();

    // Assert
    assertTrue(json.contains("\"type\":\"computer_use\""));
    assertTrue(json.contains("\"environment\":\"browser\""));
    assertTrue(json.contains("\"excludedPredefinedFunctions\""));
  }

  @Test
  public void testComputerUseToBuilder() {
    // Arrange
    ComputerUse original = ComputerUse.builder()
        .environment("browser")
        .build();

    // Act
    ComputerUse modified = original.toBuilder()
        .environment("desktop")
        .build();

    // Assert
    assertEquals("desktop", modified.environment().get());
  }

  // ========== Tool Polymorphic Deserialization Tests ==========

  @Test
  public void testPolymorphicDeserializationFunction() {
    // Arrange
    String json = "{\"type\":\"function\",\"name\":\"test_func\",\"description\":\"Test\"}";
    JsonNode jsonNode = JsonSerializable.stringToJsonNode(json);

    // Act
    Tool tool = JsonSerializable.fromJsonNodePolymorphic(jsonNode, Tool.class);

    // Assert
    assertNotNull(tool);
    assertTrue(tool instanceof Function);
    Function function = (Function) tool;
    assertEquals("test_func", function.name().get());
  }

  @Test
  public void testPolymorphicDeserializationGoogleSearch() {
    // Arrange
    String json = "{\"type\":\"google_search\"}";
    JsonNode jsonNode = JsonSerializable.stringToJsonNode(json);

    // Act
    Tool tool = JsonSerializable.fromJsonNodePolymorphic(jsonNode, Tool.class);

    // Assert
    assertNotNull(tool);
    assertTrue(tool instanceof GoogleSearch);
  }

  @Test
  public void testPolymorphicDeserializationCodeExecution() {
    // Arrange
    String json = "{\"type\":\"code_execution\"}";
    JsonNode jsonNode = JsonSerializable.stringToJsonNode(json);

    // Act
    Tool tool = JsonSerializable.fromJsonNodePolymorphic(jsonNode, Tool.class);

    // Assert
    assertNotNull(tool);
    assertTrue(tool instanceof CodeExecution);
  }

  @Test
  public void testPolymorphicDeserializationUrlContext() {
    // Arrange
    String json = "{\"type\":\"url_context\"}";
    JsonNode jsonNode = JsonSerializable.stringToJsonNode(json);

    // Act
    Tool tool = JsonSerializable.fromJsonNodePolymorphic(jsonNode, Tool.class);

    // Assert
    assertNotNull(tool);
    assertTrue(tool instanceof UrlContext);
  }

  @Test
  public void testPolymorphicDeserializationComputerUse() {
    // Arrange
    String json = "{\"type\":\"computer_use\",\"environment\":\"browser\"}";
    JsonNode jsonNode = JsonSerializable.stringToJsonNode(json);

    // Act
    Tool tool = JsonSerializable.fromJsonNodePolymorphic(jsonNode, Tool.class);

    // Assert
    assertNotNull(tool);
    assertTrue(tool instanceof ComputerUse);
    ComputerUse computerUse = (ComputerUse) tool;
    assertEquals("browser", computerUse.environment().get());
  }

  // ========== All Tool Types Implement Interface ==========

  @Test
  public void testAllToolTypesImplementToolInterface() {
    // Verify all tool types implement Tool interface
    Tool function = Function.builder().name("test").build();
    Tool googleSearch = GoogleSearch.builder().build();
    Tool codeExecution = CodeExecution.builder().build();
    Tool urlContext = UrlContext.builder().build();
    Tool computerUse = ComputerUse.builder().environment("browser").build();

    assertNotNull(function);
    assertNotNull(googleSearch);
    assertNotNull(codeExecution);
    assertNotNull(urlContext);
    assertNotNull(computerUse);
  }
}
