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

package com.google.genai.types.interactions.streaming.delta;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.genai.types.interactions.AudioMimeType;
import com.google.genai.types.interactions.DocumentMimeType;
import com.google.genai.types.interactions.ImageMimeType;
import com.google.genai.types.interactions.MediaResolution;
import com.google.genai.types.interactions.VideoMimeType;
import com.google.genai.types.interactions.content.ImageContent;
import com.google.genai.types.interactions.content.TextContent;
import com.google.genai.types.interactions.content.ThoughtSummaryContent;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

/**
 * Tests for streaming delta types to verify correct type usage per OpenAPI spec.
 *
 * <p>Tests the following type corrections:
 * <ul>
 *   <li>ImageDelta: mimeType (ImageMimeType), resolution (MediaResolution)</li>
 *   <li>AudioDelta: mimeType (AudioMimeType)</li>
 *   <li>VideoDelta: mimeType (VideoMimeType), resolution (MediaResolution)</li>
 *   <li>DocumentDelta: mimeType (DocumentMimeType)</li>
 *   <li>FunctionResultDelta: result (Object)</li>
 *   <li>McpServerToolResultDelta: result (Object)</li>
 * </ul>
 */
public class DeltaTypesTest {

  // ========== ImageDelta Tests ==========

  @Test
  public void testImageDeltaWithMimeType() {
    // Arrange & Act
    ImageDelta delta = ImageDelta.builder()
        .mimeType(new ImageMimeType(ImageMimeType.Known.IMAGE_PNG))
        .build();

    // Assert
    assertTrue(delta.mimeType().isPresent());
    assertEquals("image/png", delta.mimeType().get().toString());
    assertEquals(ImageMimeType.Known.IMAGE_PNG, delta.mimeType().get().knownEnum());
  }

  @Test
  public void testImageDeltaWithResolution() {
    // Arrange & Act
    ImageDelta delta = ImageDelta.builder()
        .resolution(new MediaResolution(MediaResolution.Known.HIGH))
        .build();

    // Assert
    assertTrue(delta.resolution().isPresent());
    assertEquals("high", delta.resolution().get().toString());
    assertEquals(MediaResolution.Known.HIGH, delta.resolution().get().knownEnum());
  }

  @Test
  public void testImageDeltaWithAllFields() {
    // Arrange & Act
    ImageDelta delta = ImageDelta.builder()
        .data("base64encodeddata")
        .uri("gs://bucket/image.png")
        .mimeType(new ImageMimeType(ImageMimeType.Known.IMAGE_JPEG))
        .resolution(new MediaResolution(MediaResolution.Known.ULTRA_HIGH))
        .build();

    // Assert
    assertTrue(delta.data().isPresent());
    assertEquals("base64encodeddata", delta.data().get());
    assertTrue(delta.uri().isPresent());
    assertEquals("gs://bucket/image.png", delta.uri().get());
    assertTrue(delta.mimeType().isPresent());
    assertEquals("image/jpeg", delta.mimeType().get().toString());
    assertTrue(delta.resolution().isPresent());
    assertEquals("ultra_high", delta.resolution().get().toString());
  }

  @Test
  public void testImageDeltaJsonSerialization() {
    // Arrange
    ImageDelta delta = ImageDelta.builder()
        .data("abc123")
        .mimeType(new ImageMimeType(ImageMimeType.Known.IMAGE_WEBP))
        .resolution(new MediaResolution(MediaResolution.Known.LOW))
        .build();

    // Act
    String json = delta.toJson();

    // Assert
    assertTrue(json.contains("\"data\":\"abc123\""));
    assertTrue(json.contains("\"mime_type\":\"image/webp\""));
    assertTrue(json.contains("\"resolution\":\"low\""));
  }

  @Test
  public void testImageDeltaJsonDeserialization() {
    // Arrange
    String json = "{\"type\":\"image\",\"data\":\"xyz789\",\"mime_type\":\"image/png\",\"resolution\":\"high\"}";

    // Act
    ImageDelta delta = ImageDelta.fromJson(json);

    // Assert
    assertTrue(delta.data().isPresent());
    assertEquals("xyz789", delta.data().get());
    assertTrue(delta.mimeType().isPresent());
    assertEquals("image/png", delta.mimeType().get().toString());
    assertEquals(ImageMimeType.Known.IMAGE_PNG, delta.mimeType().get().knownEnum());
    assertTrue(delta.resolution().isPresent());
    assertEquals("high", delta.resolution().get().toString());
    assertEquals(MediaResolution.Known.HIGH, delta.resolution().get().knownEnum());
  }

  @Test
  public void testImageDeltaClearMethods() {
    // Arrange
    ImageDelta.Builder builder = ImageDelta.builder()
        .mimeType(new ImageMimeType(ImageMimeType.Known.IMAGE_PNG))
        .resolution(new MediaResolution(MediaResolution.Known.HIGH));

    // Act
    ImageDelta delta = builder
        .clearMimeType()
        .clearResolution()
        .build();

    // Assert
    assertTrue(!delta.mimeType().isPresent());
    assertTrue(!delta.resolution().isPresent());
  }

  @Test
  public void testImageDeltaToBuilder() {
    // Arrange
    ImageDelta original = ImageDelta.builder()
        .mimeType(new ImageMimeType(ImageMimeType.Known.IMAGE_JPEG))
        .resolution(new MediaResolution(MediaResolution.Known.LOW))
        .build();

    // Act
    ImageDelta modified = original.toBuilder()
        .resolution(new MediaResolution(MediaResolution.Known.HIGH))
        .build();

    // Assert
    assertEquals("image/jpeg", modified.mimeType().get().toString());
    assertEquals("high", modified.resolution().get().toString());
  }

  // ========== AudioDelta Tests ==========

  @Test
  public void testAudioDeltaWithMimeType() {
    // Arrange & Act
    AudioDelta delta = AudioDelta.builder()
        .mimeType(new AudioMimeType(AudioMimeType.Known.AUDIO_MP3))
        .build();

    // Assert
    assertTrue(delta.mimeType().isPresent());
    assertEquals("audio/mp3", delta.mimeType().get().toString());
  }

  @Test
  public void testAudioDeltaWithAllFields() {
    // Arrange & Act
    AudioDelta delta = AudioDelta.builder()
        .data("audiobase64data")
        .uri("gs://bucket/audio.mp3")
        .mimeType(new AudioMimeType(AudioMimeType.Known.AUDIO_WAV))
        .build();

    // Assert
    assertTrue(delta.data().isPresent());
    assertEquals("audiobase64data", delta.data().get());
    assertTrue(delta.uri().isPresent());
    assertEquals("gs://bucket/audio.mp3", delta.uri().get());
    assertTrue(delta.mimeType().isPresent());
    assertEquals("audio/wav", delta.mimeType().get().toString());
  }

  @Test
  public void testAudioDeltaJsonSerialization() {
    // Arrange
    AudioDelta delta = AudioDelta.builder()
        .data("audiodata")
        .mimeType(new AudioMimeType(AudioMimeType.Known.AUDIO_FLAC))
        .build();

    // Act
    String json = delta.toJson();

    // Assert
    assertTrue(json.contains("\"data\":\"audiodata\""));
    assertTrue(json.contains("\"mime_type\":\"audio/flac\""));
  }

  @Test
  public void testAudioDeltaJsonDeserialization() {
    // Arrange
    String json = "{\"type\":\"audio\",\"data\":\"abcdef\",\"mime_type\":\"audio/ogg\"}";

    // Act
    AudioDelta delta = AudioDelta.fromJson(json);

    // Assert
    assertTrue(delta.data().isPresent());
    assertEquals("abcdef", delta.data().get());
    assertTrue(delta.mimeType().isPresent());
    assertEquals("audio/ogg", delta.mimeType().get().toString());
  }

  @Test
  public void testAudioDeltaClearMimeType() {
    // Arrange
    AudioDelta.Builder builder = AudioDelta.builder()
        .mimeType(new AudioMimeType(AudioMimeType.Known.AUDIO_AAC));

    // Act
    AudioDelta delta = builder.clearMimeType().build();

    // Assert
    assertTrue(!delta.mimeType().isPresent());
  }

  @Test
  public void testAudioDeltaToBuilder() {
    // Arrange
    AudioDelta original = AudioDelta.builder()
        .data("original")
        .mimeType(new AudioMimeType(AudioMimeType.Known.AUDIO_MP3))
        .build();

    // Act
    AudioDelta modified = original.toBuilder()
        .mimeType(new AudioMimeType(AudioMimeType.Known.AUDIO_WAV))
        .build();

    // Assert
    assertEquals("original", modified.data().get());
    assertEquals("audio/wav", modified.mimeType().get().toString());
  }

  // ========== VideoDelta Tests ==========

  @Test
  public void testVideoDeltaWithMimeType() {
    // Arrange & Act
    VideoDelta delta = VideoDelta.builder()
        .mimeType(new VideoMimeType(VideoMimeType.Known.VIDEO_MP4))
        .build();

    // Assert
    assertTrue(delta.mimeType().isPresent());
    assertEquals("video/mp4", delta.mimeType().get().toString());
  }

  @Test
  public void testVideoDeltaWithResolution() {
    // Arrange & Act
    VideoDelta delta = VideoDelta.builder()
        .resolution(new MediaResolution(MediaResolution.Known.ULTRA_HIGH))
        .build();

    // Assert
    assertTrue(delta.resolution().isPresent());
    assertEquals("ultra_high", delta.resolution().get().toString());
  }

  @Test
  public void testVideoDeltaWithAllFields() {
    // Arrange & Act
    VideoDelta delta = VideoDelta.builder()
        .data("videobase64data")
        .uri("gs://bucket/video.mp4")
        .mimeType(new VideoMimeType(VideoMimeType.Known.VIDEO_WEBM))
        .resolution(new MediaResolution(MediaResolution.Known.HIGH))
        .build();

    // Assert
    assertTrue(delta.data().isPresent());
    assertEquals("videobase64data", delta.data().get());
    assertTrue(delta.uri().isPresent());
    assertEquals("gs://bucket/video.mp4", delta.uri().get());
    assertTrue(delta.mimeType().isPresent());
    assertEquals("video/webm", delta.mimeType().get().toString());
    assertTrue(delta.resolution().isPresent());
    assertEquals("high", delta.resolution().get().toString());
  }

  @Test
  public void testVideoDeltaJsonSerialization() {
    // Arrange
    VideoDelta delta = VideoDelta.builder()
        .data("viddata")
        .mimeType(new VideoMimeType(VideoMimeType.Known.VIDEO_MOV))
        .resolution(new MediaResolution(MediaResolution.Known.LOW))
        .build();

    // Act
    String json = delta.toJson();

    // Assert
    assertTrue(json.contains("\"data\":\"viddata\""));
    assertTrue(json.contains("\"mime_type\":\"video/mov\""));
    assertTrue(json.contains("\"resolution\":\"low\""));
  }

  @Test
  public void testVideoDeltaJsonDeserialization() {
    // Arrange
    String json = "{\"type\":\"video\",\"data\":\"videodata\",\"mime_type\":\"video/mpeg\",\"resolution\":\"ultra_high\"}";

    // Act
    VideoDelta delta = VideoDelta.fromJson(json);

    // Assert
    assertTrue(delta.data().isPresent());
    assertEquals("videodata", delta.data().get());
    assertTrue(delta.mimeType().isPresent());
    assertEquals("video/mpeg", delta.mimeType().get().toString());
    assertTrue(delta.resolution().isPresent());
    assertEquals("ultra_high", delta.resolution().get().toString());
    assertEquals(MediaResolution.Known.ULTRA_HIGH, delta.resolution().get().knownEnum());
  }

  @Test
  public void testVideoDeltaClearMethods() {
    // Arrange
    VideoDelta.Builder builder = VideoDelta.builder()
        .mimeType(new VideoMimeType(VideoMimeType.Known.VIDEO_AVI))
        .resolution(new MediaResolution(MediaResolution.Known.HIGH));

    // Act
    VideoDelta delta = builder
        .clearMimeType()
        .clearResolution()
        .build();

    // Assert
    assertTrue(!delta.mimeType().isPresent());
    assertTrue(!delta.resolution().isPresent());
  }

  @Test
  public void testVideoDeltaToBuilder() {
    // Arrange
    VideoDelta original = VideoDelta.builder()
        .mimeType(new VideoMimeType(VideoMimeType.Known.VIDEO_MP4))
        .resolution(new MediaResolution(MediaResolution.Known.LOW))
        .build();

    // Act
    VideoDelta modified = original.toBuilder()
        .resolution(new MediaResolution(MediaResolution.Known.ULTRA_HIGH))
        .build();

    // Assert
    assertEquals("video/mp4", modified.mimeType().get().toString());
    assertEquals("ultra_high", modified.resolution().get().toString());
  }

  // ========== DocumentDelta Tests ==========

  @Test
  public void testDocumentDeltaWithMimeType() {
    // Arrange & Act
    DocumentDelta delta = DocumentDelta.builder()
        .mimeType(new DocumentMimeType(DocumentMimeType.Known.APPLICATION_PDF))
        .build();

    // Assert
    assertTrue(delta.mimeType().isPresent());
    assertEquals("application/pdf", delta.mimeType().get().toString());
  }

  @Test
  public void testDocumentDeltaWithAllFields() {
    // Arrange & Act
    DocumentDelta delta = DocumentDelta.builder()
        .data("pdfbase64data")
        .uri("gs://bucket/document.pdf")
        .mimeType(new DocumentMimeType(DocumentMimeType.Known.APPLICATION_PDF))
        .build();

    // Assert
    assertTrue(delta.data().isPresent());
    assertEquals("pdfbase64data", delta.data().get());
    assertTrue(delta.uri().isPresent());
    assertEquals("gs://bucket/document.pdf", delta.uri().get());
    assertTrue(delta.mimeType().isPresent());
    assertEquals("application/pdf", delta.mimeType().get().toString());
  }

  @Test
  public void testDocumentDeltaJsonSerialization() {
    // Arrange
    DocumentDelta delta = DocumentDelta.builder()
        .data("docdata")
        .mimeType(new DocumentMimeType(DocumentMimeType.Known.APPLICATION_PDF))
        .build();

    // Act
    String json = delta.toJson();

    // Assert
    assertTrue(json.contains("\"data\":\"docdata\""));
    assertTrue(json.contains("\"mime_type\":\"application/pdf\""));
  }

  @Test
  public void testDocumentDeltaJsonDeserialization() {
    // Arrange
    String json = "{\"type\":\"document\",\"data\":\"docbase64\",\"mime_type\":\"application/pdf\"}";

    // Act
    DocumentDelta delta = DocumentDelta.fromJson(json);

    // Assert
    assertTrue(delta.data().isPresent());
    assertEquals("docbase64", delta.data().get());
    assertTrue(delta.mimeType().isPresent());
    assertEquals("application/pdf", delta.mimeType().get().toString());
  }

  @Test
  public void testDocumentDeltaClearMimeType() {
    // Arrange
    DocumentDelta.Builder builder = DocumentDelta.builder()
        .mimeType(new DocumentMimeType(DocumentMimeType.Known.APPLICATION_PDF));

    // Act
    DocumentDelta delta = builder.clearMimeType().build();

    // Assert
    assertTrue(!delta.mimeType().isPresent());
  }

  @Test
  public void testDocumentDeltaToBuilder() {
    // Arrange
    DocumentDelta original = DocumentDelta.builder()
        .data("original")
        .mimeType(new DocumentMimeType(DocumentMimeType.Known.APPLICATION_PDF))
        .build();

    // Act
    DocumentDelta modified = original.toBuilder()
        .uri("gs://bucket/new.pdf")
        .build();

    // Assert
    assertEquals("original", modified.data().get());
    assertEquals("gs://bucket/new.pdf", modified.uri().get());
    assertEquals("application/pdf", modified.mimeType().get().toString());
  }

  @Test
  public void testDocumentDeltaWithCustomMimeType() {
    // Arrange & Act - test with custom/unknown MIME type
    DocumentDelta delta = DocumentDelta.builder()
        .mimeType(new DocumentMimeType("text/plain"))
        .build();

    // Assert
    assertTrue(delta.mimeType().isPresent());
    assertEquals("text/plain", delta.mimeType().get().toString());
  }

  // ========== FunctionResultDelta Tests ==========

  @Test
  public void testFunctionResultDeltaWithStringResult() {
    // Arrange & Act
    FunctionResultDelta delta = FunctionResultDelta.builder()
        .callId("call-123")
        .name("my_function")
        .result("success")
        .build();

    // Assert
    assertTrue(delta.callId().isPresent());
    assertEquals("call-123", delta.callId().get());
    assertTrue(delta.name().isPresent());
    assertEquals("my_function", delta.name().get());
    assertTrue(delta.result().isPresent());
    assertEquals("success", delta.result().get());
  }

  @Test
  public void testFunctionResultDeltaWithMapResult() {
    // Arrange
    Map<String, Object> resultMap = new HashMap<>();
    resultMap.put("status", "ok");
    resultMap.put("count", 42);

    // Act
    FunctionResultDelta delta = FunctionResultDelta.builder()
        .callId("call-456")
        .name("process_data")
        .result(resultMap)
        .build();

    // Assert
    assertTrue(delta.result().isPresent());
    assertTrue(delta.result().get() instanceof Map);
    @SuppressWarnings("unchecked")
    Map<String, Object> result = (Map<String, Object>) delta.result().get();
    assertEquals("ok", result.get("status"));
    assertEquals(42, result.get("count"));
  }

  @Test
  public void testFunctionResultDeltaWithIsError() {
    // Arrange & Act
    FunctionResultDelta delta = FunctionResultDelta.builder()
        .callId("call-789")
        .name("failing_function")
        .result("Error: something went wrong")
        .isError(true)
        .build();

    // Assert
    assertTrue(delta.isError().isPresent());
    assertEquals(true, delta.isError().get());
    assertTrue(delta.result().isPresent());
    assertEquals("Error: something went wrong", delta.result().get());
  }

  @Test
  public void testFunctionResultDeltaJsonSerializationWithString() {
    // Arrange
    FunctionResultDelta delta = FunctionResultDelta.builder()
        .callId("call-001")
        .name("test_func")
        .result("string result")
        .build();

    // Act
    String json = delta.toJson();

    // Assert
    assertTrue(json.contains("\"call_id\":\"call-001\""));
    assertTrue(json.contains("\"name\":\"test_func\""));
    assertTrue(json.contains("\"result\":\"string result\""));
  }

  @Test
  public void testFunctionResultDeltaJsonSerializationWithMap() {
    // Arrange
    Map<String, Object> resultMap = new HashMap<>();
    resultMap.put("value", 100);

    FunctionResultDelta delta = FunctionResultDelta.builder()
        .callId("call-002")
        .result(resultMap)
        .build();

    // Act
    String json = delta.toJson();

    // Assert
    assertTrue(json.contains("\"call_id\":\"call-002\""));
    assertTrue(json.contains("\"result\":{"));
    assertTrue(json.contains("\"value\":100"));
  }

  @Test
  public void testFunctionResultDeltaJsonDeserializationWithString() {
    // Arrange
    String json = "{\"type\":\"function_result\",\"call_id\":\"call-abc\",\"name\":\"func\",\"result\":\"text result\"}";

    // Act
    FunctionResultDelta delta = FunctionResultDelta.fromJson(json);

    // Assert
    assertTrue(delta.callId().isPresent());
    assertEquals("call-abc", delta.callId().get());
    assertTrue(delta.name().isPresent());
    assertEquals("func", delta.name().get());
    assertTrue(delta.result().isPresent());
    assertEquals("text result", delta.result().get());
  }

  @Test
  public void testFunctionResultDeltaJsonDeserializationWithObject() {
    // Arrange
    String json = "{\"type\":\"function_result\",\"call_id\":\"call-def\",\"result\":{\"key\":\"value\",\"num\":42}}";

    // Act
    FunctionResultDelta delta = FunctionResultDelta.fromJson(json);

    // Assert
    assertTrue(delta.result().isPresent());
    assertNotNull(delta.result().get());
  }

  @Test
  public void testFunctionResultDeltaClearMethods() {
    // Arrange
    FunctionResultDelta.Builder builder = FunctionResultDelta.builder()
        .callId("call-123")
        .name("func")
        .result("result")
        .isError(false);

    // Act
    FunctionResultDelta delta = builder
        .clearCallId()
        .clearName()
        .clearResult()
        .clearIsError()
        .build();

    // Assert
    assertTrue(!delta.callId().isPresent());
    assertTrue(!delta.name().isPresent());
    assertTrue(!delta.result().isPresent());
    assertTrue(!delta.isError().isPresent());
  }

  @Test
  public void testFunctionResultDeltaToBuilder() {
    // Arrange
    FunctionResultDelta original = FunctionResultDelta.builder()
        .callId("original-call")
        .name("original-func")
        .result("original-result")
        .build();

    // Act
    FunctionResultDelta modified = original.toBuilder()
        .result("modified-result")
        .build();

    // Assert
    assertEquals("original-call", modified.callId().get());
    assertEquals("original-func", modified.name().get());
    assertEquals("modified-result", modified.result().get());
  }

  // ========== McpServerToolResultDelta Tests ==========

  @Test
  public void testMcpServerToolResultDeltaWithStringResult() {
    // Arrange & Act
    McpServerToolResultDelta delta = McpServerToolResultDelta.builder()
        .callId("mcp-call-123")
        .name("mcp_tool")
        .serverName("my-server")
        .result("tool output")
        .build();

    // Assert
    assertTrue(delta.callId().isPresent());
    assertEquals("mcp-call-123", delta.callId().get());
    assertTrue(delta.name().isPresent());
    assertEquals("mcp_tool", delta.name().get());
    assertTrue(delta.serverName().isPresent());
    assertEquals("my-server", delta.serverName().get());
    assertTrue(delta.result().isPresent());
    assertEquals("tool output", delta.result().get());
  }

  @Test
  public void testMcpServerToolResultDeltaWithMapResult() {
    // Arrange
    Map<String, Object> resultMap = new HashMap<>();
    resultMap.put("status", "completed");
    resultMap.put("items", 5);

    // Act
    McpServerToolResultDelta delta = McpServerToolResultDelta.builder()
        .callId("mcp-call-456")
        .serverName("data-server")
        .result(resultMap)
        .build();

    // Assert
    assertTrue(delta.result().isPresent());
    assertTrue(delta.result().get() instanceof Map);
    @SuppressWarnings("unchecked")
    Map<String, Object> result = (Map<String, Object>) delta.result().get();
    assertEquals("completed", result.get("status"));
    assertEquals(5, result.get("items"));
  }

  @Test
  public void testMcpServerToolResultDeltaJsonSerializationWithString() {
    // Arrange
    McpServerToolResultDelta delta = McpServerToolResultDelta.builder()
        .callId("mcp-001")
        .name("search_tool")
        .serverName("search-server")
        .result("found 10 results")
        .build();

    // Act
    String json = delta.toJson();

    // Assert
    assertTrue(json.contains("\"call_id\":\"mcp-001\""));
    assertTrue(json.contains("\"name\":\"search_tool\""));
    assertTrue(json.contains("\"server_name\":\"search-server\""));
    assertTrue(json.contains("\"result\":\"found 10 results\""));
  }

  @Test
  public void testMcpServerToolResultDeltaJsonSerializationWithMap() {
    // Arrange
    Map<String, Object> resultMap = new HashMap<>();
    resultMap.put("data", "test");

    McpServerToolResultDelta delta = McpServerToolResultDelta.builder()
        .callId("mcp-002")
        .result(resultMap)
        .build();

    // Act
    String json = delta.toJson();

    // Assert
    assertTrue(json.contains("\"call_id\":\"mcp-002\""));
    assertTrue(json.contains("\"result\":{"));
    assertTrue(json.contains("\"data\":\"test\""));
  }

  @Test
  public void testMcpServerToolResultDeltaJsonDeserializationWithString() {
    // Arrange
    String json = "{\"type\":\"mcp_server_tool_result\",\"call_id\":\"mcp-abc\",\"name\":\"tool\",\"server_name\":\"srv\",\"result\":\"output\"}";

    // Act
    McpServerToolResultDelta delta = McpServerToolResultDelta.fromJson(json);

    // Assert
    assertTrue(delta.callId().isPresent());
    assertEquals("mcp-abc", delta.callId().get());
    assertTrue(delta.name().isPresent());
    assertEquals("tool", delta.name().get());
    assertTrue(delta.serverName().isPresent());
    assertEquals("srv", delta.serverName().get());
    assertTrue(delta.result().isPresent());
    assertEquals("output", delta.result().get());
  }

  @Test
  public void testMcpServerToolResultDeltaJsonDeserializationWithObject() {
    // Arrange
    String json = "{\"type\":\"mcp_server_tool_result\",\"call_id\":\"mcp-def\",\"result\":{\"message\":\"hello\",\"code\":200}}";

    // Act
    McpServerToolResultDelta delta = McpServerToolResultDelta.fromJson(json);

    // Assert
    assertTrue(delta.result().isPresent());
    assertNotNull(delta.result().get());
  }

  @Test
  public void testMcpServerToolResultDeltaClearMethods() {
    // Arrange
    McpServerToolResultDelta.Builder builder = McpServerToolResultDelta.builder()
        .callId("call")
        .name("tool")
        .serverName("server")
        .result("result");

    // Act
    McpServerToolResultDelta delta = builder
        .clearCallId()
        .clearName()
        .clearServerName()
        .clearResult()
        .build();

    // Assert
    assertTrue(!delta.callId().isPresent());
    assertTrue(!delta.name().isPresent());
    assertTrue(!delta.serverName().isPresent());
    assertTrue(!delta.result().isPresent());
  }

  @Test
  public void testMcpServerToolResultDeltaToBuilder() {
    // Arrange
    McpServerToolResultDelta original = McpServerToolResultDelta.builder()
        .callId("original-call")
        .name("original-tool")
        .serverName("original-server")
        .result("original-result")
        .build();

    // Act
    McpServerToolResultDelta modified = original.toBuilder()
        .result("modified-result")
        .build();

    // Assert
    assertEquals("original-call", modified.callId().get());
    assertEquals("original-tool", modified.name().get());
    assertEquals("original-server", modified.serverName().get());
    assertEquals("modified-result", modified.result().get());
  }

  // ========== Cross-Type Validation Tests ==========

  @Test
  public void testAllDeltasImplementDeltaInterface() {
    // Verify all delta types implement the Delta interface
    Delta imageDelta = ImageDelta.builder().build();
    Delta audioDelta = AudioDelta.builder().build();
    Delta videoDelta = VideoDelta.builder().build();
    Delta documentDelta = DocumentDelta.builder().build();
    Delta functionResultDelta = FunctionResultDelta.builder().build();
    Delta mcpServerToolResultDelta = McpServerToolResultDelta.builder().build();

    assertNotNull(imageDelta);
    assertNotNull(audioDelta);
    assertNotNull(videoDelta);
    assertNotNull(documentDelta);
    assertNotNull(functionResultDelta);
    assertNotNull(mcpServerToolResultDelta);
  }

  @Test
  public void testMediaResolutionValuesAcrossDeltaTypes() {
    // Test that MediaResolution works consistently across ImageDelta and VideoDelta
    MediaResolution lowRes = new MediaResolution(MediaResolution.Known.LOW);
    MediaResolution highRes = new MediaResolution(MediaResolution.Known.HIGH);
    MediaResolution ultraHighRes = new MediaResolution(MediaResolution.Known.ULTRA_HIGH);

    ImageDelta imageDelta = ImageDelta.builder().resolution(lowRes).build();
    VideoDelta videoDelta = VideoDelta.builder().resolution(ultraHighRes).build();

    assertEquals("low", imageDelta.resolution().get().toString());
    assertEquals("ultra_high", videoDelta.resolution().get().toString());
    assertEquals(MediaResolution.Known.LOW, imageDelta.resolution().get().knownEnum());
    assertEquals(MediaResolution.Known.ULTRA_HIGH, videoDelta.resolution().get().knownEnum());
  }

  @Test
  public void testObjectResultTypeFlexibility() {
    // Test that Object result type accepts various types
    String stringResult = "text";
    Integer intResult = 42;
    Map<String, Object> mapResult = new HashMap<>();
    mapResult.put("key", "value");

    FunctionResultDelta stringDelta = FunctionResultDelta.builder().result(stringResult).build();
    FunctionResultDelta intDelta = FunctionResultDelta.builder().result(intResult).build();
    FunctionResultDelta mapDelta = FunctionResultDelta.builder().result(mapResult).build();

    assertEquals("text", stringDelta.result().get());
    assertEquals(42, intDelta.result().get());
    assertTrue(mapDelta.result().get() instanceof Map);
  }

  // ========== ThoughtSummaryDelta Tests ==========

  @Test
  public void testThoughtSummaryDeltaWithTextContent() {
    // Arrange
    TextContent textContent = TextContent.builder()
        .text("This is a thought summary")
        .build();

    // Act
    ThoughtSummaryDelta delta = ThoughtSummaryDelta.builder()
        .content(textContent)
        .build();

    // Assert
    assertTrue(delta.content().isPresent());
    assertTrue(delta.content().get() instanceof TextContent);
    TextContent content = (TextContent) delta.content().get();
    assertEquals("This is a thought summary", content.text().get());
  }

  @Test
  public void testThoughtSummaryDeltaWithImageContent() {
    // Arrange
    ImageContent imageContent = ImageContent.builder()
        .data("base64data")
        .mimeType(new ImageMimeType(ImageMimeType.Known.IMAGE_PNG))
        .build();

    // Act
    ThoughtSummaryDelta delta = ThoughtSummaryDelta.builder()
        .content(imageContent)
        .build();

    // Assert
    assertTrue(delta.content().isPresent());
    assertTrue(delta.content().get() instanceof ImageContent);
    ImageContent content = (ImageContent) delta.content().get();
    assertEquals("base64data", content.data().get());
    assertEquals("image/png", content.mimeType().get().toString());
  }

  @Test
  public void testThoughtSummaryDeltaJsonSerializationWithTextContent() {
    // Arrange
    TextContent textContent = TextContent.builder()
        .text("Serialized thought")
        .build();
    ThoughtSummaryDelta delta = ThoughtSummaryDelta.builder()
        .content(textContent)
        .build();

    // Act
    String json = delta.toJson();

    // Assert
    assertTrue(json.contains("\"type\":\"thought_summary\""));
    assertTrue(json.contains("\"content\":{"));
    assertTrue(json.contains("\"text\":\"Serialized thought\""));
  }

  @Test
  public void testThoughtSummaryDeltaJsonDeserializationWithTextContent() {
    // Arrange
    String json = "{\"type\":\"thought_summary\",\"content\":{\"type\":\"text\",\"text\":\"Deserialized thought\"}}";

    // Act
    ThoughtSummaryDelta delta = ThoughtSummaryDelta.fromJson(json);

    // Assert
    assertTrue(delta.content().isPresent());
    assertTrue(delta.content().get() instanceof TextContent);
    TextContent content = (TextContent) delta.content().get();
    assertEquals("Deserialized thought", content.text().get());
  }

  @Test
  public void testThoughtSummaryDeltaJsonDeserializationWithImageContent() {
    // Arrange
    String json = "{\"type\":\"thought_summary\",\"content\":{\"type\":\"image\",\"data\":\"imgdata\",\"mime_type\":\"image/jpeg\"}}";

    // Act
    ThoughtSummaryDelta delta = ThoughtSummaryDelta.fromJson(json);

    // Assert
    assertTrue(delta.content().isPresent());
    assertTrue(delta.content().get() instanceof ImageContent);
    ImageContent content = (ImageContent) delta.content().get();
    assertEquals("imgdata", content.data().get());
    assertEquals("image/jpeg", content.mimeType().get().toString());
  }

  @Test
  public void testThoughtSummaryDeltaClearContent() {
    // Arrange
    TextContent textContent = TextContent.builder()
        .text("To be cleared")
        .build();
    ThoughtSummaryDelta.Builder builder = ThoughtSummaryDelta.builder()
        .content(textContent);

    // Act
    ThoughtSummaryDelta delta = builder.clearContent().build();

    // Assert
    assertTrue(!delta.content().isPresent());
  }

  @Test
  public void testThoughtSummaryDeltaToBuilder() {
    // Arrange
    TextContent originalContent = TextContent.builder()
        .text("Original thought")
        .build();
    ThoughtSummaryDelta original = ThoughtSummaryDelta.builder()
        .content(originalContent)
        .build();

    // Act
    TextContent newContent = TextContent.builder()
        .text("Modified thought")
        .build();
    ThoughtSummaryDelta modified = original.toBuilder()
        .content(newContent)
        .build();

    // Assert
    assertTrue(modified.content().isPresent());
    assertTrue(modified.content().get() instanceof TextContent);
    TextContent content = (TextContent) modified.content().get();
    assertEquals("Modified thought", content.text().get());
  }

  @Test
  public void testThoughtSummaryDeltaImplementsDeltaInterface() {
    // Verify ThoughtSummaryDelta implements the Delta interface
    Delta thoughtSummaryDelta = ThoughtSummaryDelta.builder().build();
    assertNotNull(thoughtSummaryDelta);
  }
}
