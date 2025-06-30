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
import java.util.List;
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
  public void testFormatMap_nonValueNode() {
    ObjectNode data = JsonSerializable.objectMapper.createObjectNode();
    data.putObject("person").put("name", "John");
    String template = "This is a {person}.";
    String expected = "This is a {person}.";
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
}
