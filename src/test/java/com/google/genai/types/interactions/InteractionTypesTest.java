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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.genai.types.interactions.content.FileSearchResultContent;
import com.google.genai.types.interactions.content.FunctionResultContent;
import com.google.genai.types.interactions.content.ImageContent;
import com.google.genai.types.interactions.content.McpServerToolCallContent;
import com.google.genai.types.interactions.content.McpServerToolResultContent;
import com.google.genai.types.interactions.content.TextContent;
import com.google.genai.types.interactions.content.ThoughtContent;
import com.google.genai.types.interactions.content.ThoughtSummaryContent;
import com.google.genai.types.interactions.tools.FileSearch;
import com.google.genai.types.interactions.tools.McpServer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

/**
 * Tests for Interactions API types that were updated to match OpenAPI spec.
 */
public class InteractionTypesTest {

  // ========== FileSearchResultContent Tests ==========

  @Test
  public void testFileSearchResultContentWithListResult() {
    // Arrange
    FileSearchResult result1 = FileSearchResult.builder()
        .text("First result text")
        .title("First Title")
        .fileSearchStore("store-1")
        .build();
    FileSearchResult result2 = FileSearchResult.builder()
        .text("Second result text")
        .title("Second Title")
        .fileSearchStore("store-2")
        .build();
    List<FileSearchResult> results = Arrays.asList(result1, result2);

    // Act
    FileSearchResultContent content = FileSearchResultContent.builder()
        .result(results)
        .build();

    // Assert
    assertTrue(content.result().isPresent());
    assertEquals(2, content.result().get().size());
    assertEquals("First result text", content.result().get().get(0).text().get());
    assertEquals("Second Title", content.result().get().get(1).title().get());
  }

  @Test
  public void testFileSearchResultContentJsonSerialization() {
    // Arrange
    FileSearchResult result = FileSearchResult.builder()
        .text("Test text")
        .title("Test Title")
        .fileSearchStore("my-store")
        .build();
    FileSearchResultContent content = FileSearchResultContent.builder()
        .result(Arrays.asList(result))
        .build();

    // Act
    String json = content.toJson();

    // Assert
    assertTrue(json.contains("\"type\":\"file_search_result\""));
    assertTrue(json.contains("\"result\":["));
    assertTrue(json.contains("\"text\":\"Test text\""));
    assertTrue(json.contains("\"title\":\"Test Title\""));
    assertTrue(json.contains("\"file_search_store\":\"my-store\""));
  }

  @Test
  public void testFileSearchResultContentJsonDeserialization() {
    // Arrange
    String json = "{\"type\":\"file_search_result\",\"result\":[{\"text\":\"Deserialized text\",\"title\":\"Deserialized Title\",\"file_search_store\":\"deserialized-store\"}]}";

    // Act
    FileSearchResultContent content = FileSearchResultContent.fromJson(json);

    // Assert
    assertTrue(content.result().isPresent());
    assertEquals(1, content.result().get().size());
    assertEquals("Deserialized text", content.result().get().get(0).text().get());
    assertEquals("Deserialized Title", content.result().get().get(0).title().get());
    assertEquals("deserialized-store", content.result().get().get(0).fileSearchStore().get());
  }

  // ========== McpServerToolCallContent Tests ==========

  @Test
  public void testMcpServerToolCallContentRequiredFields() {
    // Arrange
    Map<String, Object> arguments = new HashMap<>();
    arguments.put("param1", "value1");
    arguments.put("param2", 42);

    // Act
    McpServerToolCallContent content = McpServerToolCallContent.builder()
        .id("call-123")
        .name("my_tool")
        .serverName("my-server")
        .arguments(arguments)
        .build();

    // Assert - all fields are required (non-Optional)
    assertEquals("call-123", content.id());
    assertEquals("my_tool", content.name());
    assertEquals("my-server", content.serverName());
    assertEquals(2, content.arguments().size());
    assertEquals("value1", content.arguments().get("param1"));
    assertEquals(42, content.arguments().get("param2"));
  }

  @Test
  public void testMcpServerToolCallContentMissingRequiredFieldThrows() {
    // Arrange
    Map<String, Object> arguments = new HashMap<>();
    arguments.put("param1", "value1");

    // Act & Assert - missing id should throw
    assertThrows(IllegalStateException.class, () -> {
      McpServerToolCallContent.builder()
          .name("my_tool")
          .serverName("my-server")
          .arguments(arguments)
          .build();
    });
  }

  @Test
  public void testMcpServerToolCallContentJsonSerialization() {
    // Arrange
    Map<String, Object> arguments = new HashMap<>();
    arguments.put("query", "test query");
    McpServerToolCallContent content = McpServerToolCallContent.builder()
        .id("call-456")
        .name("search_tool")
        .serverName("search-server")
        .arguments(arguments)
        .build();

    // Act
    String json = content.toJson();

    // Assert
    assertTrue(json.contains("\"type\":\"mcp_server_tool_call\""));
    assertTrue(json.contains("\"id\":\"call-456\""));
    assertTrue(json.contains("\"name\":\"search_tool\""));
    assertTrue(json.contains("\"server_name\":\"search-server\""));
    assertTrue(json.contains("\"arguments\":{"));
    assertTrue(json.contains("\"query\":\"test query\""));
  }

  @Test
  public void testMcpServerToolCallContentJsonDeserialization() {
    // Arrange
    String json = "{\"type\":\"mcp_server_tool_call\",\"id\":\"call-789\",\"name\":\"calc_tool\",\"server_name\":\"calc-server\",\"arguments\":{\"x\":10,\"y\":20}}";

    // Act
    McpServerToolCallContent content = McpServerToolCallContent.fromJson(json);

    // Assert
    assertEquals("call-789", content.id());
    assertEquals("calc_tool", content.name());
    assertEquals("calc-server", content.serverName());
    assertEquals(10, content.arguments().get("x"));
    assertEquals(20, content.arguments().get("y"));
  }

  @Test
  public void testMcpServerToolCallContentOfFactory() {
    // Arrange
    Map<String, Object> arguments = new HashMap<>();
    arguments.put("key", "value");

    // Act
    McpServerToolCallContent content = McpServerToolCallContent.of(
        "id-001", "tool_name", "server_name", arguments);

    // Assert
    assertEquals("id-001", content.id());
    assertEquals("tool_name", content.name());
    assertEquals("server_name", content.serverName());
    assertEquals("value", content.arguments().get("key"));
  }

  // ========== McpServerToolResultContent Tests ==========

  @Test
  public void testMcpServerToolResultContentRequiredFields() {
    // Arrange
    Map<String, Object> result = new HashMap<>();
    result.put("output", "success");

    // Act
    McpServerToolResultContent content = McpServerToolResultContent.builder()
        .callId("call-123")
        .result(result)
        .build();

    // Assert - callId and result are required (non-Optional)
    assertEquals("call-123", content.callId());
    assertNotNull(content.result());
  }

  @Test
  public void testMcpServerToolResultContentMissingCallIdThrows() {
    // Arrange
    Map<String, Object> result = new HashMap<>();
    result.put("output", "success");

    // Act & Assert - missing callId should throw
    assertThrows(IllegalStateException.class, () -> {
      McpServerToolResultContent.builder()
          .result(result)
          .build();
    });
  }

  @Test
  public void testMcpServerToolResultContentMissingResultThrows() {
    // Act & Assert - missing result should throw
    assertThrows(IllegalStateException.class, () -> {
      McpServerToolResultContent.builder()
          .callId("call-123")
          .build();
    });
  }

  @Test
  public void testMcpServerToolResultContentWithOptionalFields() {
    // Arrange
    String result = "tool output";

    // Act
    McpServerToolResultContent content = McpServerToolResultContent.builder()
        .callId("call-456")
        .result(result)
        .name("my_tool")
        .serverName("my-server")
        .build();

    // Assert
    assertEquals("call-456", content.callId());
    assertEquals("tool output", content.result());
    assertTrue(content.name().isPresent());
    assertEquals("my_tool", content.name().get());
    assertTrue(content.serverName().isPresent());
    assertEquals("my-server", content.serverName().get());
  }

  @Test
  public void testMcpServerToolResultContentJsonSerialization() {
    // Arrange
    McpServerToolResultContent content = McpServerToolResultContent.builder()
        .callId("call-789")
        .result("result data")
        .name("tool_name")
        .serverName("server_name")
        .build();

    // Act
    String json = content.toJson();

    // Assert
    assertTrue(json.contains("\"type\":\"mcp_server_tool_result\""));
    assertTrue(json.contains("\"call_id\":\"call-789\""));
    assertTrue(json.contains("\"result\":\"result data\""));
    assertTrue(json.contains("\"name\":\"tool_name\""));
    assertTrue(json.contains("\"server_name\":\"server_name\""));
  }

  @Test
  public void testMcpServerToolResultContentJsonDeserialization() {
    // Arrange
    String json = "{\"type\":\"mcp_server_tool_result\",\"call_id\":\"call-abc\",\"result\":{\"value\":42},\"name\":\"test_tool\",\"server_name\":\"test_server\"}";

    // Act
    McpServerToolResultContent content = McpServerToolResultContent.fromJson(json);

    // Assert
    assertEquals("call-abc", content.callId());
    assertNotNull(content.result());
    assertTrue(content.name().isPresent());
    assertEquals("test_tool", content.name().get());
    assertTrue(content.serverName().isPresent());
    assertEquals("test_server", content.serverName().get());
  }

  @Test
  public void testMcpServerToolResultContentOfFactory() {
    // Act
    McpServerToolResultContent content = McpServerToolResultContent.of("call-id", "result-value");

    // Assert
    assertEquals("call-id", content.callId());
    assertEquals("result-value", content.result());
  }

  @Test
  public void testMcpServerToolResultContentOfFactoryWithAllFields() {
    // Act
    McpServerToolResultContent content = McpServerToolResultContent.of(
        "call-id", "result-value", "tool-name", "server-name");

    // Assert
    assertEquals("call-id", content.callId());
    assertEquals("result-value", content.result());
    assertEquals("tool-name", content.name().get());
    assertEquals("server-name", content.serverName().get());
  }

  // ========== FileSearch Tests ==========

  @Test
  public void testFileSearchMetadataFilterIsString() {
    // Act
    FileSearch tool = FileSearch.builder()
        .fileSearchStoreNames("store-1", "store-2")
        .topK(10)
        .metadataFilter("category = 'documents' AND year > 2020")
        .build();

    // Assert
    assertTrue(tool.fileSearchStoreNames().isPresent());
    assertEquals(2, tool.fileSearchStoreNames().get().size());
    assertTrue(tool.topK().isPresent());
    assertEquals(10, tool.topK().get());
    assertTrue(tool.metadataFilter().isPresent());
    assertEquals("category = 'documents' AND year > 2020", tool.metadataFilter().get());
  }

  @Test
  public void testFileSearchJsonSerializationSnakeCase() {
    // Arrange
    FileSearch tool = FileSearch.builder()
        .fileSearchStoreNames("my-store")
        .topK(5)
        .metadataFilter("status = 'active'")
        .build();

    // Act
    String json = tool.toJson();

    // Assert - verify snake_case JSON property names
    assertTrue(json.contains("\"type\":\"file_search\""));
    assertTrue(json.contains("\"file_search_store_names\":[\"my-store\"]"));
    assertTrue(json.contains("\"top_k\":5"));
    assertTrue(json.contains("\"metadata_filter\":\"status = 'active'\""));
    // Should NOT contain camelCase
    assertTrue(!json.contains("\"fileSearchStoreNames\""));
    assertTrue(!json.contains("\"topK\""));
    assertTrue(!json.contains("\"metadataFilter\""));
  }

  @Test
  public void testFileSearchJsonDeserialization() {
    // Arrange - JSON with snake_case property names
    String json = "{\"type\":\"file_search\",\"file_search_store_names\":[\"store-a\",\"store-b\"],\"top_k\":15,\"metadata_filter\":\"type = 'pdf'\"}";

    // Act
    FileSearch tool = FileSearch.fromJson(json);

    // Assert
    assertTrue(tool.fileSearchStoreNames().isPresent());
    assertEquals(2, tool.fileSearchStoreNames().get().size());
    assertEquals("store-a", tool.fileSearchStoreNames().get().get(0));
    assertEquals("store-b", tool.fileSearchStoreNames().get().get(1));
    assertTrue(tool.topK().isPresent());
    assertEquals(15, tool.topK().get());
    assertTrue(tool.metadataFilter().isPresent());
    assertEquals("type = 'pdf'", tool.metadataFilter().get());
  }

  @Test
  public void testFileSearchWithoutOptionalFields() {
    // Act
    FileSearch tool = FileSearch.builder().build();

    // Assert - all fields are optional
    assertTrue(!tool.fileSearchStoreNames().isPresent());
    assertTrue(!tool.topK().isPresent());
    assertTrue(!tool.metadataFilter().isPresent());
  }

  @Test
  public void testFileSearchClearMethods() {
    // Arrange
    FileSearch.Builder builder = FileSearch.builder()
        .fileSearchStoreNames("store-1")
        .topK(10)
        .metadataFilter("filter");

    // Act
    FileSearch tool = builder
        .clearFileSearchStoreNames()
        .clearTopK()
        .clearMetadataFilter()
        .build();

    // Assert
    assertTrue(!tool.fileSearchStoreNames().isPresent());
    assertTrue(!tool.topK().isPresent());
    assertTrue(!tool.metadataFilter().isPresent());
  }

  // ========== ResultItems Tests ==========

  @Test
  public void testResultItemsBuilder() {
    // Arrange
    List<Object> items = Arrays.asList("text item", 42, new HashMap<String, Object>());

    // Act
    ResultItems resultItems = ResultItems.builder()
        .items(items)
        .build();

    // Assert
    assertTrue(resultItems.items().isPresent());
    assertEquals(3, resultItems.items().get().size());
    assertEquals("text item", resultItems.items().get().get(0));
    assertEquals(42, resultItems.items().get().get(1));
  }

  @Test
  public void testResultItemsVarargs() {
    // Act
    ResultItems resultItems = ResultItems.of("item1", "item2", "item3");

    // Assert
    assertTrue(resultItems.items().isPresent());
    assertEquals(3, resultItems.items().get().size());
  }

  @Test
  public void testResultItemsJsonSerialization() {
    // Arrange
    ResultItems resultItems = ResultItems.of("hello", "world");

    // Act
    String json = resultItems.toJson();

    // Assert
    assertTrue(json.contains("\"items\":[\"hello\",\"world\"]"));
  }

  @Test
  public void testResultItemsJsonDeserialization() {
    // Arrange
    String json = "{\"items\":[\"a\",\"b\",\"c\"]}";

    // Act
    ResultItems resultItems = ResultItems.fromJson(json);

    // Assert
    assertTrue(resultItems.items().isPresent());
    assertEquals(3, resultItems.items().get().size());
    assertEquals("a", resultItems.items().get().get(0));
    assertEquals("b", resultItems.items().get().get(1));
    assertEquals("c", resultItems.items().get().get(2));
  }

  // ========== FunctionResultContent Tests ==========

  @Test
  public void testFunctionResultContentWithStringResult() {
    // Act
    FunctionResultContent content = FunctionResultContent.of("call-1", "my_function", "success");

    // Assert
    assertEquals("call-1", content.id());
    assertEquals("my_function", content.name().get());
    assertEquals("success", content.result());
    assertTrue(content.resultAsString().isPresent());
    assertEquals("success", content.resultAsString().get());
    assertTrue(!content.resultAsMap().isPresent());
  }

  @Test
  public void testFunctionResultContentWithMapResult() {
    // Arrange
    Map<String, Object> result = new HashMap<>();
    result.put("key", "value");
    result.put("count", 42);

    // Act
    FunctionResultContent content = FunctionResultContent.of("call-2", "my_function", result);

    // Assert
    assertEquals("call-2", content.id());
    assertTrue(content.resultAsMap().isPresent());
    assertEquals("value", content.resultAsMap().get().get("key"));
    assertEquals(42, content.resultAsMap().get().get("count"));
    assertTrue(!content.resultAsString().isPresent());
  }

  @Test
  public void testFunctionResultContentWithResultItems() {
    // Arrange
    ResultItems resultItems = ResultItems.of("item1", "item2");

    // Act
    FunctionResultContent content = FunctionResultContent.of("call-3", "my_function", resultItems);

    // Assert
    assertEquals("call-3", content.id());
    assertEquals(resultItems, content.result());
    assertTrue(content.resultAsResultItems().isPresent());
    assertEquals(2, content.resultAsResultItems().get().items().get().size());
  }

  @Test
  public void testFunctionResultContentResultAsResultItemsFromMap() {
    // Test that resultAsResultItems() works when Jackson deserializes to Map with "items" key
    // Arrange
    Map<String, Object> mapWithItems = new HashMap<>();
    mapWithItems.put("items", Arrays.asList("a", "b", "c"));

    FunctionResultContent content = FunctionResultContent.builder()
        .id("call-4")
        .result(mapWithItems)
        .build();

    // Act
    assertTrue(content.resultAsResultItems().isPresent());
    ResultItems items = content.resultAsResultItems().get();

    // Assert
    assertEquals(3, items.items().get().size());
    assertEquals("a", items.items().get().get(0));
  }

  @Test
  public void testFunctionResultContentJsonSerializationWithString() {
    // Arrange
    FunctionResultContent content = FunctionResultContent.of("call-5", "test_func", "string result");

    // Act
    String json = content.toJson();

    // Assert
    assertTrue(json.contains("\"type\":\"function_result\""));
    assertTrue(json.contains("\"call_id\":\"call-5\""));
    assertTrue(json.contains("\"name\":\"test_func\""));
    assertTrue(json.contains("\"result\":\"string result\""));
  }

  @Test
  public void testFunctionResultContentJsonDeserializationWithString() {
    // Arrange
    String json = "{\"type\":\"function_result\",\"call_id\":\"call-6\",\"name\":\"func\",\"result\":\"string value\"}";

    // Act
    FunctionResultContent content = FunctionResultContent.fromJson(json);

    // Assert
    assertEquals("call-6", content.id());
    assertEquals("func", content.name().get());
    assertTrue(content.resultAsString().isPresent());
    assertEquals("string value", content.resultAsString().get());
  }

  @Test
  public void testFunctionResultContentJsonDeserializationWithResultItems() {
    // Arrange
    String json = "{\"type\":\"function_result\",\"call_id\":\"call-7\",\"name\":\"func\",\"result\":{\"items\":[\"x\",\"y\",\"z\"]}}";

    // Act
    FunctionResultContent content = FunctionResultContent.fromJson(json);

    // Assert
    assertEquals("call-7", content.id());
    assertTrue(content.resultAsResultItems().isPresent());
    assertEquals(3, content.resultAsResultItems().get().items().get().size());
  }

  @Test
  public void testFunctionResultContentSuccessFactory() {
    // Act
    FunctionResultContent content = FunctionResultContent.ofSuccess("call-8", "func", "ok");

    // Assert
    assertEquals("call-8", content.id());
    assertTrue(content.isError().isPresent());
    assertEquals(false, content.isError().get());
  }

  @Test
  public void testFunctionResultContentErrorFactory() {
    // Act
    FunctionResultContent content = FunctionResultContent.ofError("call-9", "func", "error message");

    // Assert
    assertEquals("call-9", content.id());
    assertTrue(content.isError().isPresent());
    assertEquals(true, content.isError().get());
  }

  // ========== McpServerToolResultContent Result Type Tests ==========

  @Test
  public void testMcpServerToolResultContentWithStringResultTypeSafe() {
    // Act
    McpServerToolResultContent content = McpServerToolResultContent.of("call-1", "string result");

    // Assert
    assertTrue(content.resultAsString().isPresent());
    assertEquals("string result", content.resultAsString().get());
    assertTrue(!content.resultAsMap().isPresent());
  }

  @Test
  public void testMcpServerToolResultContentWithMapResultTypeSafe() {
    // Arrange
    Map<String, Object> result = new HashMap<>();
    result.put("status", "ok");

    // Act
    McpServerToolResultContent content = McpServerToolResultContent.of("call-2", result);

    // Assert
    assertTrue(content.resultAsMap().isPresent());
    assertEquals("ok", content.resultAsMap().get().get("status"));
    assertTrue(!content.resultAsString().isPresent());
  }

  @Test
  public void testMcpServerToolResultContentWithResultItems() {
    // Arrange
    ResultItems resultItems = ResultItems.of("a", "b", "c");

    // Act
    McpServerToolResultContent content = McpServerToolResultContent.of("call-3", resultItems);

    // Assert
    assertEquals(resultItems, content.result());
    assertTrue(content.resultAsResultItems().isPresent());
    assertEquals(3, content.resultAsResultItems().get().items().get().size());
  }

  @Test
  public void testMcpServerToolResultContentResultAsResultItemsFromMap() {
    // Arrange - simulates Jackson deserializing {"items": [...]} as Map
    Map<String, Object> mapWithItems = new HashMap<>();
    mapWithItems.put("items", Arrays.asList("x", "y"));

    McpServerToolResultContent content = McpServerToolResultContent.builder()
        .callId("call-4")
        .result(mapWithItems)
        .build();

    // Act
    assertTrue(content.resultAsResultItems().isPresent());
    ResultItems items = content.resultAsResultItems().get();

    // Assert
    assertEquals(2, items.items().get().size());
  }

  @Test
  public void testMcpServerToolResultContentJsonDeserializationWithResultItems() {
    // Arrange
    String json = "{\"type\":\"mcp_server_tool_result\",\"call_id\":\"call-5\",\"result\":{\"items\":[\"1\",\"2\",\"3\"]}}";

    // Act
    McpServerToolResultContent content = McpServerToolResultContent.fromJson(json);

    // Assert
    assertTrue(content.resultAsResultItems().isPresent());
    assertEquals(3, content.resultAsResultItems().get().items().get().size());
  }

  // ========== AllowedTools Tests ==========

  @Test
  public void testAllowedToolsBuilder() {
    // Act
    AllowedTools allowedTools = AllowedTools.builder()
        .mode("auto")
        .tools("tool1", "tool2", "tool3")
        .build();

    // Assert
    assertTrue(allowedTools.mode().isPresent());
    assertEquals("auto", allowedTools.mode().get());
    assertTrue(allowedTools.tools().isPresent());
    assertEquals(3, allowedTools.tools().get().size());
    assertEquals("tool1", allowedTools.tools().get().get(0));
  }

  @Test
  public void testAllowedToolsOfFactory() {
    // Act
    AllowedTools allowedTools = AllowedTools.of("any", "get_weather", "get_forecast");

    // Assert
    assertEquals("any", allowedTools.mode().get());
    assertEquals(2, allowedTools.tools().get().size());
  }

  @Test
  public void testAllowedToolsJsonSerialization() {
    // Arrange
    AllowedTools allowedTools = AllowedTools.of("validated", "tool_a", "tool_b");

    // Act
    String json = allowedTools.toJson();

    // Assert
    assertTrue(json.contains("\"mode\":\"validated\""));
    assertTrue(json.contains("\"tools\":[\"tool_a\",\"tool_b\"]"));
  }

  @Test
  public void testAllowedToolsJsonDeserialization() {
    // Arrange
    String json = "{\"mode\":\"none\",\"tools\":[\"x\",\"y\",\"z\"]}";

    // Act
    AllowedTools allowedTools = AllowedTools.fromJson(json);

    // Assert
    assertEquals("none", allowedTools.mode().get());
    assertEquals(3, allowedTools.tools().get().size());
  }

  // ========== McpServer Tests ==========

  @Test
  public void testMcpServerWithAllowedToolsList() {
    // Arrange
    AllowedTools allowed1 = AllowedTools.of("auto", "get_weather", "get_forecast");
    AllowedTools allowed2 = AllowedTools.of("any", "search", "query");

    // Act
    McpServer tool = McpServer.builder()
        .name("my-mcp-server")
        .url("https://mcp.example.com")
        .allowedTools(allowed1, allowed2)
        .build();

    // Assert
    assertTrue(tool.name().isPresent());
    assertEquals("my-mcp-server", tool.name().get());
    assertTrue(tool.allowedTools().isPresent());
    assertEquals(2, tool.allowedTools().get().size());
    assertEquals("auto", tool.allowedTools().get().get(0).mode().get());
    assertEquals(2, tool.allowedTools().get().get(0).tools().get().size());
  }

  @Test
  public void testMcpServerJsonSerializationSnakeCase() {
    // Arrange
    AllowedTools allowed = AllowedTools.of("auto", "my_tool");
    McpServer tool = McpServer.builder()
        .name("test-server")
        .url("https://example.com")
        .allowedTools(allowed)
        .build();

    // Act
    String json = tool.toJson();

    // Assert - verify snake_case JSON property name
    assertTrue(json.contains("\"type\":\"mcp_server\""));
    assertTrue(json.contains("\"allowed_tools\":["));
    assertTrue(json.contains("\"mode\":\"auto\""));
    assertTrue(json.contains("\"tools\":[\"my_tool\"]"));
    // Should NOT contain camelCase
    assertTrue(!json.contains("\"allowedTools\""));
  }

  @Test
  public void testMcpServerJsonDeserialization() {
    // Arrange - JSON with snake_case property names
    String json = "{\"type\":\"mcp_server\",\"name\":\"deserialized-server\",\"url\":\"https://test.com\",\"allowed_tools\":[{\"mode\":\"any\",\"tools\":[\"a\",\"b\"]}]}";

    // Act
    McpServer tool = McpServer.fromJson(json);

    // Assert
    assertTrue(tool.name().isPresent());
    assertEquals("deserialized-server", tool.name().get());
    assertTrue(tool.url().isPresent());
    assertEquals("https://test.com", tool.url().get());
    assertTrue(tool.allowedTools().isPresent());
    assertEquals(1, tool.allowedTools().get().size());
    assertEquals("any", tool.allowedTools().get().get(0).mode().get());
    assertEquals(2, tool.allowedTools().get().get(0).tools().get().size());
  }

  // ========== ThoughtContent Summary Type Restriction Tests ==========

  @Test
  public void testThoughtContentSummaryWithTextContent() {
    // Arrange
    TextContent text1 = TextContent.builder().text("Summary point 1").build();
    TextContent text2 = TextContent.builder().text("Summary point 2").build();

    // Act
    ThoughtContent thought = ThoughtContent.builder()
        .signature("sig123")
        .summary(text1, text2)
        .build();

    // Assert
    assertTrue(thought.summary().isPresent());
    assertEquals(2, thought.summary().get().size());
    assertTrue(thought.summary().get().get(0) instanceof TextContent);
    assertEquals("Summary point 1", ((TextContent) thought.summary().get().get(0)).text().get());
  }

  @Test
  public void testThoughtContentSummaryWithImageContent() {
    // Arrange
    ImageContent image = ImageContent.fromUri("gs://bucket/image.png", "image/png");

    // Act
    ThoughtContent thought = ThoughtContent.builder()
        .summary(image)
        .build();

    // Assert
    assertTrue(thought.summary().isPresent());
    assertEquals(1, thought.summary().get().size());
    assertTrue(thought.summary().get().get(0) instanceof ImageContent);
  }

  @Test
  public void testThoughtContentSummaryWithMixedContent() {
    // Arrange - mixing TextContent and ImageContent is allowed
    TextContent text = TextContent.builder().text("Description of image").build();
    ImageContent image = ImageContent.fromUri("gs://bucket/diagram.png", "image/png");

    // Act
    ThoughtContent thought = ThoughtContent.of(text, image);

    // Assert
    assertTrue(thought.summary().isPresent());
    assertEquals(2, thought.summary().get().size());
    assertTrue(thought.summary().get().get(0) instanceof TextContent);
    assertTrue(thought.summary().get().get(1) instanceof ImageContent);
  }

  @Test
  public void testThoughtContentSummaryWithList() {
    // Arrange
    List<ThoughtSummaryContent> summaryList = Arrays.asList(
        TextContent.of("First item"),
        TextContent.of("Second item"),
        ImageContent.fromData("base64data", "image/jpeg")
    );

    // Act
    ThoughtContent thought = ThoughtContent.of(summaryList);

    // Assert
    assertTrue(thought.summary().isPresent());
    assertEquals(3, thought.summary().get().size());
  }

  @Test
  public void testThoughtContentJsonSerializationWithSummary() {
    // Arrange
    ThoughtContent thought = ThoughtContent.builder()
        .signature("test-signature")
        .summary(TextContent.of("Summary text"))
        .build();

    // Act
    String json = thought.toJson();

    // Assert
    assertTrue(json.contains("\"type\":\"thought\""));
    assertTrue(json.contains("\"signature\":\"test-signature\""));
    assertTrue(json.contains("\"summary\":["));
    assertTrue(json.contains("\"text\":\"Summary text\""));
  }

  @Test
  public void testThoughtContentJsonDeserializationWithTextSummary() {
    // Arrange
    String json = "{\"type\":\"thought\",\"signature\":\"sig-abc\",\"summary\":[{\"type\":\"text\",\"text\":\"Deserialized summary\"}]}";

    // Act
    ThoughtContent thought = ThoughtContent.fromJson(json);

    // Assert
    assertTrue(thought.signature().isPresent());
    assertEquals("sig-abc", thought.signature().get());
    assertTrue(thought.summary().isPresent());
    assertEquals(1, thought.summary().get().size());
    assertTrue(thought.summary().get().get(0) instanceof TextContent);
    assertEquals("Deserialized summary", ((TextContent) thought.summary().get().get(0)).text().get());
  }

  @Test
  public void testThoughtContentJsonDeserializationWithImageSummary() {
    // Arrange
    String json = "{\"type\":\"thought\",\"summary\":[{\"type\":\"image\",\"uri\":\"gs://bucket/img.png\",\"mime_type\":\"image/png\"}]}";

    // Act
    ThoughtContent thought = ThoughtContent.fromJson(json);

    // Assert
    assertTrue(thought.summary().isPresent());
    assertEquals(1, thought.summary().get().size());
    assertTrue(thought.summary().get().get(0) instanceof ImageContent);
    ImageContent image = (ImageContent) thought.summary().get().get(0);
    assertEquals("gs://bucket/img.png", image.uri().get());
    assertEquals("image/png", image.mimeType().get().toString());
  }

  @Test
  public void testThoughtContentJsonDeserializationWithMixedSummary() {
    // Arrange
    String json = "{\"type\":\"thought\",\"summary\":[{\"type\":\"text\",\"text\":\"Text part\"},{\"type\":\"image\",\"uri\":\"gs://bucket/photo.jpg\",\"mime_type\":\"image/jpeg\"}]}";

    // Act
    ThoughtContent thought = ThoughtContent.fromJson(json);

    // Assert
    assertTrue(thought.summary().isPresent());
    assertEquals(2, thought.summary().get().size());
    assertTrue(thought.summary().get().get(0) instanceof TextContent);
    assertTrue(thought.summary().get().get(1) instanceof ImageContent);
  }

  @Test
  public void testThoughtContentToBuilderPreservesSummary() {
    // Arrange
    ThoughtContent original = ThoughtContent.builder()
        .signature("original-sig")
        .summary(TextContent.of("Original summary"))
        .build();

    // Act
    ThoughtContent modified = original.toBuilder()
        .signature("modified-sig")
        .build();

    // Assert
    assertEquals("modified-sig", modified.signature().get());
    assertTrue(modified.summary().isPresent());
    assertEquals(1, modified.summary().get().size());
    assertEquals("Original summary", ((TextContent) modified.summary().get().get(0)).text().get());
  }

  @Test
  public void testThoughtContentClearSummary() {
    // Arrange
    ThoughtContent.Builder builder = ThoughtContent.builder()
        .signature("sig")
        .summary(TextContent.of("Will be cleared"));

    // Act
    ThoughtContent thought = builder.clearSummary().build();

    // Assert
    assertTrue(!thought.summary().isPresent());
  }

  @Test
  public void testTextContentImplementsThoughtSummaryContent() {
    // Verify compile-time type safety
    ThoughtSummaryContent content = TextContent.of("test");
    assertTrue(content instanceof TextContent);
    assertTrue(content instanceof ThoughtSummaryContent);
  }

  @Test
  public void testImageContentImplementsThoughtSummaryContent() {
    // Verify compile-time type safety
    ThoughtSummaryContent content = ImageContent.fromUri("gs://bucket/test.png", "image/png");
    assertTrue(content instanceof ImageContent);
    assertTrue(content instanceof ThoughtSummaryContent);
  }
}
