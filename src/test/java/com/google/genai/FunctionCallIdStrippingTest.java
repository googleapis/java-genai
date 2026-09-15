/*
 * Copyright 2026 Google LLC
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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.ImmutableMap;
import com.google.genai.types.FunctionCall;
import com.google.genai.types.FunctionResponse;
import com.google.genai.types.Part;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests that the {@code id} field is stripped from {@code FunctionCall} and {@code
 * FunctionResponse} objects before they are sent to the Gemini API (Mldev format), while remaining
 * available for deserialization and client-side use.
 */
public class FunctionCallIdStrippingTest {

  private ApiClient apiClient;
  private Models models;
  private Batches batches;
  private LiveConverters liveConverters;
  private TokensConverters tokensConverters;
  private Caches caches;

  @BeforeEach
  public void setUp() {
    apiClient = mock(ApiClient.class);
    models = new Models(apiClient);
    batches = new Batches(apiClient);
    liveConverters = new LiveConverters(apiClient);
    tokensConverters = new TokensConverters(apiClient);
    caches = new Caches(apiClient);
  }

  // ---------------------------------------------------------------------------
  // functionCallToMldev — id stripping
  // ---------------------------------------------------------------------------

  @Test
  public void testFunctionCallToMldev_withId_idIsStripped_Models() {
    FunctionCall fc =
        FunctionCall.builder()
            .id("call_123")
            .name("getWeather")
            .args(ImmutableMap.<String, Object>of("city", "Seattle"))
            .build();
    JsonNode fromObject = JsonSerializable.toJsonNode(fc);
    ObjectNode parentObject = JsonSerializable.objectMapper().createObjectNode();
    JsonNode rootObject = JsonSerializable.objectMapper().createObjectNode();

    ObjectNode result = models.functionCallToMldev(fromObject, parentObject, rootObject);

    assertNull(result.get("id"), "id should be stripped from functionCall");
    assertEquals("getWeather", result.get("name").asText());
    assertNotNull(result.get("args"));
  }

  @Test
  public void testFunctionCallToMldev_withId_idIsStripped_Batches() {
    FunctionCall fc =
        FunctionCall.builder()
            .id("call_456")
            .name("search")
            .args(ImmutableMap.<String, Object>of("q", "test"))
            .build();
    JsonNode fromObject = JsonSerializable.toJsonNode(fc);
    ObjectNode parentObject = JsonSerializable.objectMapper().createObjectNode();

    ObjectNode result = batches.functionCallToMldev(fromObject, parentObject);

    assertNull(result.get("id"), "id should be stripped from functionCall");
    assertEquals("search", result.get("name").asText());
  }

  @Test
  public void testFunctionCallToMldev_withId_idIsStripped_LiveConverters() {
    FunctionCall fc =
        FunctionCall.builder()
            .id("call_live")
            .name("stream")
            .args(ImmutableMap.<String, Object>of("ch", "main"))
            .build();
    JsonNode fromObject = JsonSerializable.toJsonNode(fc);
    ObjectNode parentObject = JsonSerializable.objectMapper().createObjectNode();

    ObjectNode result = liveConverters.functionCallToMldev(fromObject, parentObject);

    assertNull(result.get("id"), "id should be stripped from functionCall");
    assertEquals("stream", result.get("name").asText());
  }

  @Test
  public void testFunctionCallToMldev_withId_idIsStripped_TokensConverters() {
    FunctionCall fc =
        FunctionCall.builder()
            .id("call_tok")
            .name("count")
            .args(ImmutableMap.<String, Object>of("t", "hi"))
            .build();
    JsonNode fromObject = JsonSerializable.toJsonNode(fc);
    ObjectNode parentObject = JsonSerializable.objectMapper().createObjectNode();

    ObjectNode result = tokensConverters.functionCallToMldev(fromObject, parentObject);

    assertNull(result.get("id"), "id should be stripped from functionCall");
    assertEquals("count", result.get("name").asText());
  }

  @Test
  public void testFunctionCallToMldev_withId_idIsStripped_Caches() {
    FunctionCall fc =
        FunctionCall.builder()
            .id("call_cache")
            .name("lookup")
            .args(ImmutableMap.<String, Object>of("key", "abc"))
            .build();
    JsonNode fromObject = JsonSerializable.toJsonNode(fc);
    ObjectNode parentObject = JsonSerializable.objectMapper().createObjectNode();

    ObjectNode result = caches.functionCallToMldev(fromObject, parentObject);

    assertNull(result.get("id"), "id should be stripped from functionCall");
    assertEquals("lookup", result.get("name").asText());
  }

  // ---------------------------------------------------------------------------
  // functionCallToMldev — regression: no id present
  // ---------------------------------------------------------------------------

  @Test
  public void testFunctionCallToMldev_withoutId_worksNormally() {
    FunctionCall fc =
        FunctionCall.builder()
            .name("getWeather")
            .args(ImmutableMap.<String, Object>of("city", "Portland"))
            .build();
    JsonNode fromObject = JsonSerializable.toJsonNode(fc);
    ObjectNode parentObject = JsonSerializable.objectMapper().createObjectNode();
    JsonNode rootObject = JsonSerializable.objectMapper().createObjectNode();

    ObjectNode result = models.functionCallToMldev(fromObject, parentObject, rootObject);

    assertNull(result.get("id"));
    assertEquals("getWeather", result.get("name").asText());
    assertNotNull(result.get("args"));
  }

  // ---------------------------------------------------------------------------
  // functionCallToMldev — name and args preserved
  // ---------------------------------------------------------------------------

  @Test
  public void testFunctionCallToMldev_nameOnly() {
    FunctionCall fc = FunctionCall.builder().name("noArgs").build();
    JsonNode fromObject = JsonSerializable.toJsonNode(fc);
    ObjectNode parentObject = JsonSerializable.objectMapper().createObjectNode();
    JsonNode rootObject = JsonSerializable.objectMapper().createObjectNode();

    ObjectNode result = models.functionCallToMldev(fromObject, parentObject, rootObject);

    assertNull(result.get("id"));
    assertEquals("noArgs", result.get("name").asText());
    assertNull(result.get("args"));
  }

  // ---------------------------------------------------------------------------
  // partToMldev — functionResponse id stripping
  // ---------------------------------------------------------------------------

  @Test
  public void testPartToMldev_functionResponseWithId_idIsStripped_Models() {
    FunctionResponse fr =
        FunctionResponse.builder()
            .id("call_123")
            .name("getWeather")
            .response(ImmutableMap.<String, Object>of("output", "sunny"))
            .build();
    Part part = Part.builder().functionResponse(fr).build();
    JsonNode fromObject = JsonSerializable.toJsonNode(part);
    ObjectNode parentObject = JsonSerializable.objectMapper().createObjectNode();
    JsonNode rootObject = JsonSerializable.objectMapper().createObjectNode();

    ObjectNode result = models.partToMldev(fromObject, parentObject, rootObject);

    JsonNode frNode = result.get("functionResponse");
    assertNotNull(frNode, "functionResponse should exist in output");
    assertNull(frNode.get("id"), "id should be stripped from functionResponse");
    assertEquals("getWeather", frNode.get("name").asText());
  }

  @Test
  public void testPartToMldev_functionResponseWithId_idIsStripped_Batches() {
    FunctionResponse fr =
        FunctionResponse.builder()
            .id("call_456")
            .name("search")
            .response(ImmutableMap.<String, Object>of("output", "results"))
            .build();
    Part part = Part.builder().functionResponse(fr).build();
    JsonNode fromObject = JsonSerializable.toJsonNode(part);
    ObjectNode parentObject = JsonSerializable.objectMapper().createObjectNode();

    ObjectNode result = batches.partToMldev(fromObject, parentObject);

    JsonNode frNode = result.get("functionResponse");
    assertNotNull(frNode);
    assertNull(frNode.get("id"), "id should be stripped from functionResponse");
    assertEquals("search", frNode.get("name").asText());
  }

  @Test
  public void testPartToMldev_functionResponseWithId_idIsStripped_LiveConverters() {
    FunctionResponse fr =
        FunctionResponse.builder()
            .id("call_live")
            .name("stream")
            .response(ImmutableMap.<String, Object>of("output", "data"))
            .build();
    Part part = Part.builder().functionResponse(fr).build();
    JsonNode fromObject = JsonSerializable.toJsonNode(part);
    ObjectNode parentObject = JsonSerializable.objectMapper().createObjectNode();

    ObjectNode result = liveConverters.partToMldev(fromObject, parentObject);

    JsonNode frNode = result.get("functionResponse");
    assertNotNull(frNode);
    assertNull(frNode.get("id"), "id should be stripped from functionResponse");
    assertEquals("stream", frNode.get("name").asText());
  }

  @Test
  public void testPartToMldev_functionResponseWithId_idIsStripped_TokensConverters() {
    FunctionResponse fr =
        FunctionResponse.builder()
            .id("call_tok")
            .name("count")
            .response(ImmutableMap.<String, Object>of("output", "42"))
            .build();
    Part part = Part.builder().functionResponse(fr).build();
    JsonNode fromObject = JsonSerializable.toJsonNode(part);
    ObjectNode parentObject = JsonSerializable.objectMapper().createObjectNode();

    ObjectNode result = tokensConverters.partToMldev(fromObject, parentObject);

    JsonNode frNode = result.get("functionResponse");
    assertNotNull(frNode);
    assertNull(frNode.get("id"), "id should be stripped from functionResponse");
    assertEquals("count", frNode.get("name").asText());
  }

  @Test
  public void testPartToMldev_functionResponseWithId_idIsStripped_Caches() {
    FunctionResponse fr =
        FunctionResponse.builder()
            .id("call_cache")
            .name("lookup")
            .response(ImmutableMap.<String, Object>of("output", "hit"))
            .build();
    Part part = Part.builder().functionResponse(fr).build();
    JsonNode fromObject = JsonSerializable.toJsonNode(part);
    ObjectNode parentObject = JsonSerializable.objectMapper().createObjectNode();

    ObjectNode result = caches.partToMldev(fromObject, parentObject);

    JsonNode frNode = result.get("functionResponse");
    assertNotNull(frNode);
    assertNull(frNode.get("id"), "id should be stripped from functionResponse");
    assertEquals("lookup", frNode.get("name").asText());
  }

  // ---------------------------------------------------------------------------
  // partToMldev — regression: functionResponse without id
  // ---------------------------------------------------------------------------

  @Test
  public void testPartToMldev_functionResponseWithoutId_worksNormally() {
    FunctionResponse fr =
        FunctionResponse.builder()
            .name("getWeather")
            .response(ImmutableMap.<String, Object>of("output", "rainy"))
            .build();
    Part part = Part.builder().functionResponse(fr).build();
    JsonNode fromObject = JsonSerializable.toJsonNode(part);
    ObjectNode parentObject = JsonSerializable.objectMapper().createObjectNode();
    JsonNode rootObject = JsonSerializable.objectMapper().createObjectNode();

    ObjectNode result = models.partToMldev(fromObject, parentObject, rootObject);

    JsonNode frNode = result.get("functionResponse");
    assertNotNull(frNode);
    assertNull(frNode.get("id"));
    assertEquals("getWeather", frNode.get("name").asText());
  }

  // ---------------------------------------------------------------------------
  // partToMldev — all fields except id are preserved in functionResponse
  // ---------------------------------------------------------------------------

  @Test
  public void testPartToMldev_functionResponse_preservesAllFieldsExceptId() {
    FunctionResponse fr =
        FunctionResponse.builder()
            .id("strip_me")
            .name("importantFunc")
            .response(ImmutableMap.<String, Object>of("result", "success", "data", "payload"))
            .build();
    Part part = Part.builder().functionResponse(fr).build();
    JsonNode fromObject = JsonSerializable.toJsonNode(part);
    ObjectNode parentObject = JsonSerializable.objectMapper().createObjectNode();
    JsonNode rootObject = JsonSerializable.objectMapper().createObjectNode();

    ObjectNode result = models.partToMldev(fromObject, parentObject, rootObject);

    JsonNode frNode = result.get("functionResponse");
    assertNull(frNode.get("id"), "id should be stripped");
    assertEquals("importantFunc", frNode.get("name").asText());
    assertEquals("success", frNode.get("response").get("result").asText());
    assertEquals("payload", frNode.get("response").get("data").asText());
  }

  // ---------------------------------------------------------------------------
  // partToMldev — functionCall inside Part has id stripped
  // ---------------------------------------------------------------------------

  @Test
  public void testPartToMldev_functionCallWithId_idIsStripped() {
    FunctionCall fc =
        FunctionCall.builder()
            .id("fc_e2e")
            .name("lookupUser")
            .args(ImmutableMap.<String, Object>of("userId", "u123"))
            .build();
    Part part = Part.builder().functionCall(fc).build();
    JsonNode fromObject = JsonSerializable.toJsonNode(part);
    ObjectNode parentObject = JsonSerializable.objectMapper().createObjectNode();
    JsonNode rootObject = JsonSerializable.objectMapper().createObjectNode();

    ObjectNode result = models.partToMldev(fromObject, parentObject, rootObject);

    JsonNode fcNode = result.get("functionCall");
    assertNotNull(fcNode, "functionCall should exist in output");
    assertNull(fcNode.get("id"), "id should be stripped from functionCall");
    assertEquals("lookupUser", fcNode.get("name").asText());
  }

  // ---------------------------------------------------------------------------
  // Deserialization: id field is still readable from API responses
  // ---------------------------------------------------------------------------

  @Test
  public void testFunctionCall_idStillDeserializable() {
    String json = "{\"id\":\"call_999\",\"name\":\"myFunc\",\"args\":{\"key\":\"value\"}}";
    FunctionCall fc = JsonSerializable.fromJsonString(json, FunctionCall.class);

    assertTrue(fc.id().isPresent());
    assertEquals("call_999", fc.id().get());
    assertEquals("myFunc", fc.name().get());
  }

  @Test
  public void testFunctionResponse_idStillDeserializable() {
    String json = "{\"id\":\"call_999\",\"name\":\"myFunc\",\"response\":{\"out\":\"done\"}}";
    FunctionResponse fr = JsonSerializable.fromJsonString(json, FunctionResponse.class);

    assertTrue(fr.id().isPresent());
    assertEquals("call_999", fr.id().get());
    assertEquals("myFunc", fr.name().get());
  }

  // ---------------------------------------------------------------------------
  // Client-side serialization: id still appears in toJson() output
  // ---------------------------------------------------------------------------

  @Test
  public void testFunctionCall_clientSideSerializationStillIncludesId() {
    FunctionCall fc = FunctionCall.builder().id("serialize_me").name("testFunc").build();
    String json = fc.toJson();

    assertTrue(json.contains("\"id\":\"serialize_me\""),
        "id should still appear in client-side toJson() output");
  }

  @Test
  public void testFunctionResponse_clientSideSerializationStillIncludesId() {
    FunctionResponse fr = FunctionResponse.builder().id("serialize_me").name("testFunc").build();
    String json = fr.toJson();

    assertTrue(json.contains("\"id\":\"serialize_me\""),
        "id should still appear in client-side toJson() output");
  }
}
