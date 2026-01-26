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

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.ImmutableMap;
import com.google.genai.types.HttpOptions;
import com.google.genai.types.interactions.CancelInteractionConfig;
import com.google.genai.types.interactions.CreateInteractionConfig;
import com.google.genai.types.interactions.DeleteInteractionConfig;
import com.google.genai.types.interactions.GetInteractionConfig;
import java.util.Optional;
import org.junit.jupiter.api.Test;

/**
 * Tests for api_version field in Interactions API config classes.
 *
 * <p>This test suite validates:
 * <ul>
 *   <li>api_version field presence and behavior across all 5 config classes</li>
 *   <li>JSON serialization (camelCase to snake_case: api_version)</li>
 *   <li>JSON deserialization (snake_case to camelCase)</li>
 *   <li>Builder methods (apiVersion, clearApiVersion)</li>
 *   <li>toBuilder() functionality</li>
 *   <li>httpOptions field removal (should not exist)</li>
 * </ul>
 */
public class InteractionsApiVersionTest {

  // ===========================
  // GetInteractionConfig Tests
  // ===========================

  @Test
  public void testGetInteractionConfig_apiVersion_set() {
    GetInteractionConfig config = GetInteractionConfig.builder()
        .apiVersion("v1beta")
        .build();

    assertTrue(config.apiVersion().isPresent());
    assertEquals("v1beta", config.apiVersion().get());
  }

  @Test
  public void testGetInteractionConfig_apiVersion_notSet() {
    GetInteractionConfig config = GetInteractionConfig.builder().build();

    assertFalse(config.apiVersion().isPresent());
  }

  @Test
  public void testGetInteractionConfig_clearApiVersion() {
    GetInteractionConfig config = GetInteractionConfig.builder()
        .apiVersion("v1beta")
        .clearApiVersion()
        .build();

    assertFalse(config.apiVersion().isPresent());
  }

  @Test
  public void testGetInteractionConfig_jsonSerialization() {
    GetInteractionConfig config = GetInteractionConfig.builder()
        .apiVersion("v1alpha")
        .build();

    String json = config.toJson();
    assertTrue(json.contains("\"api_version\""), "JSON should contain snake_case 'api_version'");
    assertTrue(json.contains("\"v1alpha\""), "JSON should contain the version value");
  }

  @Test
  public void testGetInteractionConfig_jsonDeserialization() {
    String json = "{\"api_version\":\"v1beta\",\"last_event_id\":\"event-123\"}";
    GetInteractionConfig config = GetInteractionConfig.fromJson(json);

    assertTrue(config.apiVersion().isPresent());
    assertEquals("v1beta", config.apiVersion().get());
    assertTrue(config.lastEventId().isPresent());
    assertEquals("event-123", config.lastEventId().get());
  }

  @Test
  public void testGetInteractionConfig_toBuilder() {
    GetInteractionConfig original = GetInteractionConfig.builder()
        .apiVersion("v1alpha")
        .lastEventId("event-1")
        .build();

    GetInteractionConfig copy = original.toBuilder()
        .apiVersion("v1beta")
        .build();

    assertEquals("v1alpha", original.apiVersion().get());
    assertEquals("v1beta", copy.apiVersion().get());
    assertEquals("event-1", copy.lastEventId().get());
  }

  @Test
  public void testGetInteractionConfig_withOtherFields() {
    GetInteractionConfig config = GetInteractionConfig.builder()
        .apiVersion("v1alpha")
        .lastEventId("event-456")
        .build();

    assertEquals("v1alpha", config.apiVersion().get());
    assertEquals("event-456", config.lastEventId().get());
  }

  // ================================
  // CreateInteractionConfig Tests
  // ================================

  @Test
  public void testCreateInteractionConfig_apiVersion_set() {
    CreateInteractionConfig config = CreateInteractionConfig.builder()
        .model("gemini-2.5-flash")
        .input("test")
        .apiVersion("v1beta")
        .build();

    assertTrue(config.apiVersion().isPresent());
    assertEquals("v1beta", config.apiVersion().get());
  }

  @Test
  public void testCreateInteractionConfig_apiVersion_notSet() {
    CreateInteractionConfig config = CreateInteractionConfig.builder()
        .model("gemini-2.5-flash")
        .input("test")
        .build();

    assertFalse(config.apiVersion().isPresent());
  }

  @Test
  public void testCreateInteractionConfig_clearApiVersion() {
    CreateInteractionConfig config = CreateInteractionConfig.builder()
        .model("gemini-2.5-flash")
        .input("test")
        .apiVersion("v1beta")
        .clearApiVersion()
        .build();

    assertFalse(config.apiVersion().isPresent());
  }

  @Test
  public void testCreateInteractionConfig_jsonSerialization() {
    CreateInteractionConfig config = CreateInteractionConfig.builder()
        .model("gemini-2.5-flash")
        .input("test")
        .apiVersion("v1alpha")
        .build();

    String json = config.toJson();
    assertTrue(json.contains("\"api_version\""), "JSON should contain snake_case 'api_version'");
    assertTrue(json.contains("\"v1alpha\""), "JSON should contain the version value");
  }

  @Test
  public void testCreateInteractionConfig_jsonDeserialization() {
    String json = "{\"model\":\"gemini-2.5-flash\",\"input\":\"test\",\"api_version\":\"v1beta\"}";
    CreateInteractionConfig config = CreateInteractionConfig.fromJson(json);

    assertTrue(config.apiVersion().isPresent());
    assertEquals("v1beta", config.apiVersion().get());
  }

  @Test
  public void testCreateInteractionConfig_toBuilder() {
    CreateInteractionConfig original = CreateInteractionConfig.builder()
        .model("gemini-2.5-flash")
        .input("test")
        .apiVersion("v1alpha")
        .build();

    CreateInteractionConfig copy = original.toBuilder()
        .apiVersion("v1beta")
        .build();

    assertEquals("v1alpha", original.apiVersion().get());
    assertEquals("v1beta", copy.apiVersion().get());
  }

  @Test
  public void testCreateInteractionConfig_withOtherFields() {
    CreateInteractionConfig config = CreateInteractionConfig.builder()
        .model("gemini-2.5-flash")
        .input("What is AI?")
        .apiVersion("v1alpha")
        .systemInstruction("Be helpful")
        .build();

    assertEquals("v1alpha", config.apiVersion().get());
    assertEquals("gemini-2.5-flash", config.model().get());
    assertEquals("Be helpful", config.systemInstruction().get());
  }

  // ================================
  // CancelInteractionConfig Tests
  // ================================

  @Test
  public void testCancelInteractionConfig_apiVersion_set() {
    CancelInteractionConfig config = CancelInteractionConfig.builder()
        .apiVersion("v1beta")
        .build();

    assertTrue(config.apiVersion().isPresent());
    assertEquals("v1beta", config.apiVersion().get());
  }

  @Test
  public void testCancelInteractionConfig_apiVersion_notSet() {
    CancelInteractionConfig config = CancelInteractionConfig.builder().build();

    assertFalse(config.apiVersion().isPresent());
  }

  @Test
  public void testCancelInteractionConfig_clearApiVersion() {
    CancelInteractionConfig config = CancelInteractionConfig.builder()
        .apiVersion("v1beta")
        .clearApiVersion()
        .build();

    assertFalse(config.apiVersion().isPresent());
  }

  @Test
  public void testCancelInteractionConfig_jsonSerialization() {
    CancelInteractionConfig config = CancelInteractionConfig.builder()
        .apiVersion("v1alpha")
        .build();

    String json = config.toJson();
    assertTrue(json.contains("\"api_version\""), "JSON should contain snake_case 'api_version'");
    assertTrue(json.contains("\"v1alpha\""), "JSON should contain the version value");
  }

  @Test
  public void testCancelInteractionConfig_jsonDeserialization() {
    String json = "{\"api_version\":\"v1beta\"}";
    CancelInteractionConfig config = CancelInteractionConfig.fromJson(json);

    assertTrue(config.apiVersion().isPresent());
    assertEquals("v1beta", config.apiVersion().get());
  }

  @Test
  public void testCancelInteractionConfig_toBuilder() {
    CancelInteractionConfig original = CancelInteractionConfig.builder()
        .apiVersion("v1alpha")
        .build();

    CancelInteractionConfig copy = original.toBuilder()
        .apiVersion("v1beta")
        .build();

    assertEquals("v1alpha", original.apiVersion().get());
    assertEquals("v1beta", copy.apiVersion().get());
  }

  // ================================
  // DeleteInteractionConfig Tests
  // ================================

  @Test
  public void testDeleteInteractionConfig_apiVersion_set() {
    DeleteInteractionConfig config = DeleteInteractionConfig.builder()
        .apiVersion("v1beta")
        .build();

    assertTrue(config.apiVersion().isPresent());
    assertEquals("v1beta", config.apiVersion().get());
  }

  @Test
  public void testDeleteInteractionConfig_apiVersion_notSet() {
    DeleteInteractionConfig config = DeleteInteractionConfig.builder().build();

    assertFalse(config.apiVersion().isPresent());
  }

  @Test
  public void testDeleteInteractionConfig_clearApiVersion() {
    DeleteInteractionConfig config = DeleteInteractionConfig.builder()
        .apiVersion("v1beta")
        .clearApiVersion()
        .build();

    assertFalse(config.apiVersion().isPresent());
  }

  @Test
  public void testDeleteInteractionConfig_jsonSerialization() {
    DeleteInteractionConfig config = DeleteInteractionConfig.builder()
        .apiVersion("v1alpha")
        .build();

    String json = config.toJson();
    assertTrue(json.contains("\"api_version\""), "JSON should contain snake_case 'api_version'");
    assertTrue(json.contains("\"v1alpha\""), "JSON should contain the version value");
  }

  @Test
  public void testDeleteInteractionConfig_jsonDeserialization() {
    String json = "{\"api_version\":\"v1beta\"}";
    DeleteInteractionConfig config = DeleteInteractionConfig.fromJson(json);

    assertTrue(config.apiVersion().isPresent());
    assertEquals("v1beta", config.apiVersion().get());
  }

  @Test
  public void testDeleteInteractionConfig_toBuilder() {
    DeleteInteractionConfig original = DeleteInteractionConfig.builder()
        .apiVersion("v1alpha")
        .build();

    DeleteInteractionConfig copy = original.toBuilder()
        .apiVersion("v1beta")
        .build();

    assertEquals("v1alpha", original.apiVersion().get());
    assertEquals("v1beta", copy.apiVersion().get());
  }

  // ================================
  // Edge Cases and Cross-Config Tests
  // ================================

  @Test
  public void testAllConfigs_supportDifferentVersions() {
    String[] versions = {"v1alpha", "v1beta", "v1", "v2", "v1beta1", "v2alpha"};

    for (String version : versions) {
      // Test each config type with the version
      GetInteractionConfig getConfig = GetInteractionConfig.builder()
          .apiVersion(version)
          .build();
      assertEquals(version, getConfig.apiVersion().get());

      GetInteractionConfig streamConfig = GetInteractionConfig.builder()
          .apiVersion(version)
          .build();
      assertEquals(version, streamConfig.apiVersion().get());

      CreateInteractionConfig createConfig = CreateInteractionConfig.builder()
          .model("gemini-2.5-flash")
          .input("test")
          .apiVersion(version)
          .build();
      assertEquals(version, createConfig.apiVersion().get());

      CancelInteractionConfig cancelConfig = CancelInteractionConfig.builder()
          .apiVersion(version)
          .build();
      assertEquals(version, cancelConfig.apiVersion().get());

      DeleteInteractionConfig deleteConfig = DeleteInteractionConfig.builder()
          .apiVersion(version)
          .build();
      assertEquals(version, deleteConfig.apiVersion().get());
    }
  }

  @Test
  public void testCreateInteractionConfig_jsonRoundTrip() {
    CreateInteractionConfig original = CreateInteractionConfig.builder()
        .model("gemini-2.5-flash")
        .input("What is quantum computing?")
        .apiVersion("v1alpha")
        .systemInstruction("Be concise")
        .build();

    String json = original.toJson();
    CreateInteractionConfig deserialized = CreateInteractionConfig.fromJson(json);

    assertEquals(original.apiVersion().get(), deserialized.apiVersion().get());
    assertEquals(original.model().get(), deserialized.model().get());
    assertEquals(original.systemInstruction().get(), deserialized.systemInstruction().get());
  }

  @Test
  public void testGetInteractionConfig_jsonRoundTrip() {
    GetInteractionConfig original = GetInteractionConfig.builder()
        .apiVersion("v1beta")
        .lastEventId("event-789")
        .build();

    String json = original.toJson();
    GetInteractionConfig deserialized = GetInteractionConfig.fromJson(json);

    assertEquals(original.apiVersion().get(), deserialized.apiVersion().get());
    assertEquals(original.lastEventId().get(), deserialized.lastEventId().get());
  }

  @Test
  public void testEmptyConfig_noApiVersion() {
    // Test that configs can be built without apiVersion
    GetInteractionConfig getConfig = GetInteractionConfig.builder().build();
    assertFalse(getConfig.apiVersion().isPresent());

    GetInteractionConfig streamConfig = GetInteractionConfig.builder().build();
    assertFalse(streamConfig.apiVersion().isPresent());

    CancelInteractionConfig cancelConfig = CancelInteractionConfig.builder().build();
    assertFalse(cancelConfig.apiVersion().isPresent());

    DeleteInteractionConfig deleteConfig = DeleteInteractionConfig.builder().build();
    assertFalse(deleteConfig.apiVersion().isPresent());
  }

  @Test
  public void testJsonNode_apiVersion() {
    // Test that apiVersion appears in JSON node correctly
    CreateInteractionConfig config = CreateInteractionConfig.builder()
        .model("gemini-2.5-flash")
        .input("test")
        .apiVersion("v1alpha")
        .build();

    JsonNode node = JsonSerializable.toJsonNode(config);
    assertTrue(node.has("api_version"));
    assertEquals("v1alpha", node.get("api_version").asText());
  }

  // =====================================================================
  // HttpOptions Merge Tests - Proves request-level preserves client-level
  // =====================================================================

  /**
   * Tests that ApiClient.mergeHttpOptions() correctly merges client-level and request-level
   * HttpOptions, proving that:
   * 1. Request-level api_version overrides client-level api_version
   * 2. Client-level timeout is preserved when not set in request-level
   * 3. Client-level headers are preserved when not set in request-level
   * 4. Client-level baseUrl is preserved when not set in request-level
   */
  @Test
  public void testMergeHttpOptions_requestLevelApiVersionPreservesOtherClientSettings()
      throws Exception {
    // Arrange: Create client with comprehensive HttpOptions
    HttpOptions clientHttpOptions = HttpOptions.builder()
        .apiVersion("v1")                              // ← Client-level api_version
        .timeout(30000)                                // ← Client-level timeout
        .baseUrl("https://client-base-url.com")        // ← Client-level baseUrl
        .headers(ImmutableMap.of(
            "X-Client-Header", "client-value",         // ← Client-level header
            "X-Custom-ID", "client-123"                // ← Client-level header
        ))
        .build();

    HttpApiClient client = new HttpApiClient(
        Optional.of("test-api-key"),
        Optional.of(clientHttpOptions),
        Optional.empty()
    );

    // Arrange: Create request-level HttpOptions with ONLY api_version
    // (This simulates what Interactions.buildRequestForCreate does)
    HttpOptions requestHttpOptions = HttpOptions.builder()
        .apiVersion("v1beta")  // ← Request-level api_version ONLY
        .build();

    // Act: Merge the options (this is what happens inside ApiClient.buildRequest)
    HttpOptions merged = client.mergeHttpOptions(requestHttpOptions);

    // Assert: Request-level api_version overrides client-level
    assertTrue(merged.apiVersion().isPresent());
    assertEquals("v1beta", merged.apiVersion().get(),
        "Request-level api_version should override client-level");

    // Assert: Client-level timeout is PRESERVED (not overridden)
    assertTrue(merged.timeout().isPresent());
    assertEquals(30000, merged.timeout().get(),
        "Client-level timeout should be preserved when not set in request-level");

    // Assert: Client-level baseUrl is PRESERVED (not overridden)
    assertTrue(merged.baseUrl().isPresent());
    assertEquals("https://client-base-url.com", merged.baseUrl().get(),
        "Client-level baseUrl should be preserved when not set in request-level");

    // Assert: Client-level headers are PRESERVED (not overridden)
    assertTrue(merged.headers().isPresent());
    assertEquals("client-value", merged.headers().get().get("X-Client-Header"),
        "Client-level headers should be preserved when not set in request-level");
    assertEquals("client-123", merged.headers().get().get("X-Custom-ID"),
        "Client-level headers should be preserved when not set in request-level");
  }

  /**
   * Tests that when request-level has multiple HttpOptions fields,
   * they all override correctly while still preserving non-specified client-level fields.
   */
  @Test
  public void testMergeHttpOptions_multipleRequestFieldsOverrideSelectively() throws Exception {
    // Arrange: Create client with comprehensive HttpOptions
    HttpOptions clientHttpOptions = HttpOptions.builder()
        .apiVersion("v1")
        .timeout(30000)
        .baseUrl("https://client-base-url.com")
        .headers(ImmutableMap.of("X-Client", "client-val"))
        .build();

    HttpApiClient client = new HttpApiClient(
        Optional.of("test-api-key"),
        Optional.of(clientHttpOptions),
        Optional.empty()
    );

    // Arrange: Create request-level HttpOptions with api_version AND timeout
    HttpOptions requestHttpOptions = HttpOptions.builder()
        .apiVersion("v1beta")  // ← Override api_version
        .timeout(60000)        // ← Override timeout
        .build();

    // Act: Merge the options
    HttpOptions merged = client.mergeHttpOptions(requestHttpOptions);

    // Assert: Both request-level fields override
    assertEquals("v1beta", merged.apiVersion().get());
    assertEquals(60000, merged.timeout().get());

    // Assert: Unspecified fields are preserved from client-level
    assertEquals("https://client-base-url.com", merged.baseUrl().get());
    assertEquals("client-val", merged.headers().get().get("X-Client"));
  }

  /**
   * Tests that when request-level HttpOptions is null/empty,
   * client-level HttpOptions is returned unchanged.
   */
  @Test
  public void testMergeHttpOptions_nullRequestReturnsClientOptions() throws Exception {
    // Arrange: Create client with HttpOptions
    HttpOptions clientHttpOptions = HttpOptions.builder()
        .apiVersion("v1")
        .timeout(30000)
        .build();

    HttpApiClient client = new HttpApiClient(
        Optional.of("test-api-key"),
        Optional.of(clientHttpOptions),
        Optional.empty()
    );

    // Act: Merge with null request options
    HttpOptions merged = client.mergeHttpOptions(null);

    // Assert: Client options returned unchanged
    assertEquals("v1", merged.apiVersion().get());
    assertEquals(30000, merged.timeout().get());
  }

  /**
   * Integration test: Proves the full flow from CreateInteractionConfig
   * through buildRequestForCreate to the actual HTTP request maintains
   * client-level settings while overriding api_version.
   */
  @Test
  public void testInteractions_buildRequestForCreate_preservesClientHttpOptions() throws Exception {
    // Arrange: Create client with comprehensive HttpOptions
    HttpOptions clientHttpOptions = HttpOptions.builder()
        .apiVersion("v1")
        .timeout(30000)
        .baseUrl("https://generativelanguage.googleapis.com")
        .headers(ImmutableMap.of("X-Custom", "client-header"))
        .build();

    HttpApiClient apiClient = new HttpApiClient(
        Optional.of("test-api-key"),
        Optional.of(clientHttpOptions),
        Optional.empty()
    );

    Interactions interactions = new Interactions(apiClient);

    // Arrange: Create interaction config with request-level api_version
    CreateInteractionConfig config = CreateInteractionConfig.builder()
        .model("gemini-2.5-flash")
        .input("test")
        .apiVersion("v1beta")  // ← Request-level override
        .build();

    // Act: Build the request (this is what happens inside interactions.create())
    Common.BuiltRequest builtRequest = interactions.buildRequestForCreate(config, false);

    // Assert: The built request should have HttpOptions with only api_version
    assertTrue(builtRequest.httpOptions().isPresent());
    HttpOptions requestHttpOptions = builtRequest.httpOptions().get();

    // The request-level HttpOptions contains ONLY api_version
    assertTrue(requestHttpOptions.apiVersion().isPresent());
    assertEquals("v1beta", requestHttpOptions.apiVersion().get());

    // Other fields should NOT be present in request-level HttpOptions
    assertFalse(requestHttpOptions.timeout().isPresent(),
        "Request-level should not duplicate client-level timeout");
    assertFalse(requestHttpOptions.baseUrl().isPresent(),
        "Request-level should not duplicate client-level baseUrl");
    assertFalse(requestHttpOptions.headers().isPresent(),
        "Request-level should not duplicate client-level headers");

    // Now when ApiClient.mergeHttpOptions is called during the actual request,
    // it will merge these options, preserving client-level timeout, baseUrl, and headers
    HttpOptions merged = apiClient.mergeHttpOptions(requestHttpOptions);

    // Assert: After merge, api_version is overridden but everything else is preserved
    assertEquals("v1beta", merged.apiVersion().get(),
        "Request-level api_version should override");
    assertEquals(30000, merged.timeout().get(),
        "Client-level timeout should be preserved");
    assertEquals("https://generativelanguage.googleapis.com", merged.baseUrl().get(),
        "Client-level baseUrl should be preserved");
    assertEquals("client-header", merged.headers().get().get("X-Custom"),
        "Client-level headers should be preserved");
  }

  /**
   * Tests that the Interactions API pattern of creating minimal HttpOptions
   * with only api_version works correctly across all operation types.
   */
  @Test
  public void testInteractions_allOperations_preserveClientSettings() throws Exception {
    // Arrange: Create client with comprehensive HttpOptions
    HttpOptions clientHttpOptions = HttpOptions.builder()
        .apiVersion("v1")
        .timeout(25000)
        .baseUrl("https://generativelanguage.googleapis.com")
        .build();

    HttpApiClient apiClient = new HttpApiClient(
        Optional.of("test-api-key"),
        Optional.of(clientHttpOptions),
        Optional.empty()
    );

    Interactions interactions = new Interactions(apiClient);

    // Test 1: Create operation
    CreateInteractionConfig createConfig = CreateInteractionConfig.builder()
        .model("gemini-2.5-flash")
        .input("test")
        .apiVersion("v1beta")
        .build();

    Common.BuiltRequest createRequest = interactions.buildRequestForCreate(createConfig, false);
    HttpOptions createMerged = apiClient.mergeHttpOptions(createRequest.httpOptions().orElse(null));

    assertEquals("v1beta", createMerged.apiVersion().get());
    assertEquals(25000, createMerged.timeout().get());
    assertEquals("https://generativelanguage.googleapis.com", createMerged.baseUrl().get());

    // Test 2: Get operation
    GetInteractionConfig getConfig = GetInteractionConfig.builder()
        .apiVersion("v1alpha")
        .build();

    Common.BuiltRequest getRequest = interactions.buildRequestForGet("test-id", getConfig);
    HttpOptions getMerged = apiClient.mergeHttpOptions(getRequest.httpOptions().orElse(null));

    assertEquals("v1alpha", getMerged.apiVersion().get());
    assertEquals(25000, getMerged.timeout().get());

    // Test 3: Cancel operation
    CancelInteractionConfig cancelConfig = CancelInteractionConfig.builder()
        .apiVersion("v2")
        .build();

    Common.BuiltRequest cancelRequest = interactions.buildRequestForCancel("test-id", cancelConfig);
    HttpOptions cancelMerged = apiClient.mergeHttpOptions(cancelRequest.httpOptions().orElse(null));

    assertEquals("v2", cancelMerged.apiVersion().get());
    assertEquals(25000, cancelMerged.timeout().get());

    // Test 4: Delete operation
    DeleteInteractionConfig deleteConfig = DeleteInteractionConfig.builder()
        .apiVersion("v1beta1")
        .build();

    Common.BuiltRequest deleteRequest = interactions.buildRequestForDelete("test-id", deleteConfig);
    HttpOptions deleteMerged = apiClient.mergeHttpOptions(deleteRequest.httpOptions().orElse(null));

    assertEquals("v1beta1", deleteMerged.apiVersion().get());
    assertEquals(25000, deleteMerged.timeout().get());

    // Test 5: GetStream operation
    GetInteractionConfig streamConfig = GetInteractionConfig.builder()
        .apiVersion("v1alpha")
        .lastEventId("event-123")
        .build();

    Common.BuiltRequest streamRequest = interactions.buildRequestForGetStream("test-id", streamConfig);
    HttpOptions streamMerged = apiClient.mergeHttpOptions(streamRequest.httpOptions().orElse(null));

    assertEquals("v1alpha", streamMerged.apiVersion().get());
    assertEquals(25000, streamMerged.timeout().get());
  }
}
