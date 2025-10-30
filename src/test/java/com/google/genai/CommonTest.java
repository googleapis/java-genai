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

package com.google.genai;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class CommonTest {

  @Test
  public void testSetValueByPath_simpleObject() {
    ObjectNode data = JsonSerializable.objectMapper.createObjectNode();
    Common.setValueByPath(data, new String[] {"a", "b"}, "v");

    // expected output: {a: {b: "v"}}
    assertEquals("v", data.get("a").get("b").asText());
  }

  @Test
  public void testSetValueByPath_arrayObject() {
    ObjectNode data = JsonSerializable.objectMapper.createObjectNode();
    Common.setValueByPath(data, new String[] {"a", "b[]", "c"}, Arrays.asList("v1", "v2"));

    // expected output: {a: {b: [{c: "v1"}, {c: "v2"}]}}
    assertEquals("v1", data.get("a").get("b").get(0).get("c").asText());
    assertEquals("v2", data.get("a").get("b").get(1).get("c").asText());
  }

  @Test
  public void testSetValueByPath_arrayObject_existing() {
    ObjectNode data = JsonSerializable.objectMapper.createObjectNode();
    List<String> values = Arrays.asList("v1", "v2");
    Common.setValueByPath(data, new String[] {"a", "b[]", "c"}, values);
    Common.setValueByPath(data, new String[] {"a", "b[]", "d"}, "v3");

    // expected output: {a: {b: [{c: "v1", d: "v3"}, {c: "v2", d: "v3"}]}}
    assertEquals("v1", data.get("a").get("b").get(0).get("c").asText());
    assertEquals("v2", data.get("a").get("b").get(1).get("c").asText());
    assertEquals("v3", data.get("a").get("b").get(0).get("d").asText());
    assertEquals("v3", data.get("a").get("b").get(1).get("d").asText());
  }

  @Test
  public void testSetValueByPath_firstArrayElement() {
    ObjectNode data = JsonSerializable.objectMapper.createObjectNode();
    Common.setValueByPath(data, new String[] {"a", "b[0]", "c"}, "v4");

    // expected output: {a: {b: [{c: "v4"}]}}
    assertEquals("v4", data.get("a").get("b").get(0).get("c").asText());
  }

  @Test
  public void testSetValueByPath_selfKey() {
    ObjectNode data = JsonSerializable.objectMapper.createObjectNode();
    data.putObject("a").putObject("b");
    ObjectNode value = JsonSerializable.objectMapper.createObjectNode();
    value.put("c", "v");
    Common.setValueByPath(data, new String[] {"_self"}, value);

    // expected output: {a: {b: {}}, c: "v"}
    assertEquals("", data.get("a").get("b").asText());
    assertEquals("v", data.get("c").asText());
  }

  @Test
  public void testGetValueByPath_simpleObject() {
    ObjectNode data = JsonSerializable.objectMapper.createObjectNode();
    data.putObject("a").put("b", "v");
    assertEquals("v", ((JsonNode) Common.getValueByPath(data, new String[] {"a", "b"})).asText());
  }

  @Test
  public void testGetValueByPath_arrayObject() {
    ObjectNode data = JsonSerializable.objectMapper.createObjectNode();
    ArrayNode array = data.putObject("a").putArray("b");
    array.addObject().put("c", "v1");
    array.addObject().put("c", "v2");
    ArrayNode result = (ArrayNode) Common.getValueByPath(data, new String[] {"a", "b[]", "c"});
    assertEquals("v1", result.get(0).asText());
    assertEquals("v2", result.get(1).asText());
  }

  @Test
  public void testGetValueByPath_firstArrayElement() {
    ObjectNode data = JsonSerializable.objectMapper.createObjectNode();
    ArrayNode array = data.putObject("a").putArray("b");
    array.addObject().put("c", "v4");
    assertEquals(
        "v4", ((JsonNode) Common.getValueByPath(data, new String[] {"a", "b[0]", "c"})).asText());
  }

  @Test
  public void testGetValueByPath_notFound() {
    ObjectNode data = JsonSerializable.objectMapper.createObjectNode();
    assertNull(Common.getValueByPath(data, new String[] {"a", "b"}));
  }

  @Test
  public void testGetValueByPath_nullObject() {
    assertNull(Common.getValueByPath(null, new String[] {"a", "b"}));
  }

  @Test
  public void testGetValueByPath_nullKeys() {
    ObjectNode data = JsonSerializable.objectMapper.createObjectNode();
    assertNull(Common.getValueByPath(data, null));
  }

  @Test
  public void testGetValueByPath_self() {
    ObjectNode data = JsonSerializable.objectMapper.createObjectNode();
    data.put("test", "value");
    assertEquals(data, Common.getValueByPath(data, new String[] {"_self"}));
  }

  @Test
  public void testFormatMap_simple() {
    ObjectNode data = JsonSerializable.objectMapper.createObjectNode();
    data.put("name", "Alice");
    data.put("age", "30");
    String template = "My name is {name} and I am {age} years old.";
    String expected = "My name is Alice and I am 30 years old.";
    assertEquals(expected, Common.formatMap(template, data));
  }

  @Test
  public void testFormatMap_missingKey() {
    ObjectNode data = JsonSerializable.objectMapper.createObjectNode();
    data.put("name", "Bob");
    String template = "My name is {name} and I live in {city}.";
    String expected = "My name is Bob and I live in {city}.";
    assertEquals(expected, Common.formatMap(template, data));
  }

  @Test
  public void testFormatMap_emptyData() {
    ObjectNode data = JsonSerializable.objectMapper.createObjectNode();
    String template = "This is a test.";
    String expected = "This is a test.";
    assertEquals(expected, Common.formatMap(template, data));
  }

  @Test
  public void testIsZero_null() {
    assertTrue(Common.isZero(null));
  }

  @Test
  public void testIsZero_number_zero() {
    assertTrue(Common.isZero(0));
    assertTrue(Common.isZero(0.0));
    assertTrue(Common.isZero(0L));
  }

  @Test
  public void testIsZero_number_nonzero() {
    assertFalse(Common.isZero(1));
    assertFalse(Common.isZero(0.1));
    assertFalse(Common.isZero(-1));
  }

  @Test
  public void testIsZero_character_zero() {
    assertTrue(Common.isZero('\0'));
  }

  @Test
  public void testIsZero_character_nonzero() {
    assertFalse(Common.isZero('a'));
  }

  @Test
  public void testIsZero_boolean_true() {
    assertFalse(Common.isZero(true));
  }

  @Test
  public void testIsZero_boolean_false() {
    assertTrue(Common.isZero(false));
  }

  @Test
  public void testIsZero_otherObject() {
    assertFalse(Common.isZero("test"));
    assertFalse(Common.isZero(new Object()));
  }

  @Test
  public void testMoveValueByPath_docstringExample() {
    // Test the exact example from the moveValueByPath docstring
    // Input: {'requests': [{'content': v1}, {'content': v2}]}
    // Path: {'requests[].*': 'requests[].request.*'}
    // Output: {'requests': [{'request': {'content': v1}}, {'request': {'content': v2}}]}

    ObjectNode data = JsonSerializable.objectMapper.createObjectNode();
    ArrayNode requests = data.putArray("requests");

    // Add first request with content
    ObjectNode request1 = requests.addObject();
    request1.put("content", "v1");

    // Add second request with content
    ObjectNode request2 = requests.addObject();
    request2.put("content", "v2");

    // Define the paths to move
    Map<String, String> paths = new HashMap<>();
    paths.put("requests[].*", "requests[].request.*");

    // Execute the move operation
    Common.moveValueByPath(data, paths);

    // Verify the result
    ArrayNode resultRequests = (ArrayNode) data.get("requests");

    // Check first request
    ObjectNode resultRequest1 = (ObjectNode) resultRequests.get(0);
    ObjectNode resultRequest1Inner = (ObjectNode) resultRequest1.get("request");
    assertEquals("v1", resultRequest1Inner.get("content").asText());
    assertNull(resultRequest1.get("content")); // Should be moved to request.content

    // Check second request
    ObjectNode resultRequest2 = (ObjectNode) resultRequests.get(1);
    ObjectNode resultRequest2Inner = (ObjectNode) resultRequest2.get("request");
    assertEquals("v2", resultRequest2Inner.get("content").asText());
    assertNull(resultRequest2.get("content")); // Should be moved to request.content
  }

  @Test
  public void testMoveValueByPath() {
    // Create test data structure
    ObjectNode data = JsonSerializable.objectMapper.createObjectNode();
    ArrayNode requests = data.putArray("requests");

    // First request
    ObjectNode request1 = requests.addObject();
    ObjectNode request1Inner = request1.putObject("request");
    ObjectNode content1 = request1Inner.putObject("content");
    ArrayNode parts1 = content1.putArray("parts");
    parts1.addObject().put("text", "1");
    request1.put("outputDimensionality", 64);

    // Second request
    ObjectNode request2 = requests.addObject();
    ObjectNode request2Inner = request2.putObject("request");
    ObjectNode content2 = request2Inner.putObject("content");
    ArrayNode parts2 = content2.putArray("parts");
    parts2.addObject().put("text", "2");
    request2.put("outputDimensionality", 64);

    // Third request
    ObjectNode request3 = requests.addObject();
    ObjectNode request3Inner = request3.putObject("request");
    ObjectNode content3 = request3Inner.putObject("content");
    ArrayNode parts3 = content3.putArray("parts");
    parts3.addObject().put("text", "3");
    request3.put("outputDimensionality", 64);

    // Define the paths to move
    Map<String, String> paths = new HashMap<>();
    paths.put("requests[].*", "requests[].request.*");

    // Execute the move operation
    Common.moveValueByPath(data, paths);

    // Verify the result
    ArrayNode resultRequests = (ArrayNode) data.get("requests");

    // Check first request
    ObjectNode resultRequest1 = (ObjectNode) resultRequests.get(0);
    ObjectNode resultRequest1Inner = (ObjectNode) resultRequest1.get("request");
    assertEquals(
        "1",
        resultRequest1Inner
            .get("content")
            .get("parts")
            .get(0)
            .get("text")
            .asText());
    assertEquals(64, resultRequest1Inner.get("outputDimensionality").asInt());
    assertNull(resultRequest1.get("outputDimensionality")); // Should be moved

    // Check second request
    ObjectNode resultRequest2 = (ObjectNode) resultRequests.get(1);
    ObjectNode resultRequest2Inner = (ObjectNode) resultRequest2.get("request");
    assertEquals(
        "2",
        resultRequest2Inner
            .get("content")
            .get("parts")
            .get(0)
            .get("text")
            .asText());
    assertEquals(64, resultRequest2Inner.get("outputDimensionality").asInt());
    assertNull(resultRequest2.get("outputDimensionality")); // Should be moved

    // Check third request
    ObjectNode resultRequest3 = (ObjectNode) resultRequests.get(2);
    ObjectNode resultRequest3Inner = (ObjectNode) resultRequest3.get("request");
    assertEquals(
        "3",
        resultRequest3Inner
            .get("content")
            .get("parts")
            .get(0)
            .get("text")
            .asText());
    assertEquals(64, resultRequest3Inner.get("outputDimensionality").asInt());
    assertNull(resultRequest3.get("outputDimensionality")); // Should be moved
  }
}
