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

package com.google.genai.types.interactions.content;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.genai.JsonSerializable;
import com.google.genai.types.interactions.AudioMimeType;
import com.google.genai.types.interactions.CodeExecutionCallArguments;
import com.google.genai.types.interactions.DocumentMimeType;
import com.google.genai.types.interactions.GoogleSearchCallArguments;
import com.google.genai.types.interactions.GoogleSearchResult;
import com.google.genai.types.interactions.ImageMimeType;
import com.google.genai.types.interactions.MediaResolution;
import com.google.genai.types.interactions.UrlContextCallArguments;
import com.google.genai.types.interactions.UrlContextResult;
import com.google.genai.types.interactions.VideoMimeType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

/**
 * Tests for content types in the Interactions API.
 *
 * <p>Tests the following content types:
 * <ul>
 *   <li>ImageContent - Image data with mime type and resolution</li>
 *   <li>AudioContent - Audio data with mime type</li>
 *   <li>VideoContent - Video data with mime type and resolution</li>
 *   <li>DocumentContent - Document data with mime type</li>
 *   <li>FunctionCallContent - Function call with id, name, arguments</li>
 *   <li>CodeExecutionCallContent - Code execution call with arguments</li>
 *   <li>CodeExecutionResultContent - Code execution result with error flag</li>
 *   <li>FileSearchCallContent - File search call with id</li>
 *   <li>GoogleSearchCallContent - Google search call with queries</li>
 *   <li>GoogleSearchResultContent - Google search result with results</li>
 *   <li>UrlContextCallContent - URL context call with URLs</li>
 *   <li>UrlContextResultContent - URL context result with results</li>
 *   <li>Content - Polymorphic deserialization</li>
 * </ul>
 *
 * <p>Note: Methods annotated with @ExcludeFromGeneratedCoverageReport are not directly tested.
 */
public class ContentTypesTest {

  // ========== ImageContent Tests ==========

  @Test
  public void testImageContentWithAllFields() {
    // Arrange & Act
    ImageContent image = ImageContent.builder()
        .data("base64data")
        .uri("https://example.com/image.png")
        .mimeType(new ImageMimeType(ImageMimeType.Known.IMAGE_PNG))
        .resolution(new MediaResolution(MediaResolution.Known.HIGH))
        .build();

    // Assert
    assertTrue(image.data().isPresent());
    assertEquals("base64data", image.data().get());
    assertTrue(image.uri().isPresent());
    assertEquals("https://example.com/image.png", image.uri().get());
    assertTrue(image.mimeType().isPresent());
    assertEquals("image/png", image.mimeType().get().toString());
    assertTrue(image.resolution().isPresent());
    assertEquals("high", image.resolution().get().toString());
  }

  @Test
  public void testImageContentJsonSerialization() {
    // Arrange
    ImageContent image = ImageContent.builder()
        .data("testdata")
        .mimeType(new ImageMimeType(ImageMimeType.Known.IMAGE_WEBP))
        .resolution(new MediaResolution(MediaResolution.Known.LOW))
        .build();

    // Act
    String json = image.toJson();

    // Assert
    assertTrue(json.contains("\"data\":\"testdata\""));
    assertTrue(json.contains("\"mime_type\":\"image/webp\""));
    assertTrue(json.contains("\"resolution\":\"low\""));
    assertTrue(json.contains("\"type\":\"image\""));
  }

  @Test
  public void testImageContentToBuilder() {
    // Arrange
    ImageContent original = ImageContent.builder()
        .data("original")
        .mimeType(new ImageMimeType(ImageMimeType.Known.IMAGE_PNG))
        .build();

    // Act
    ImageContent modified = original.toBuilder()
        .resolution(new MediaResolution(MediaResolution.Known.HIGH))
        .build();

    // Assert
    assertEquals("original", modified.data().get());
    assertEquals("high", modified.resolution().get().toString());
  }

  // ========== AudioContent Tests ==========

  @Test
  public void testAudioContentWithAllFields() {
    // Arrange & Act
    AudioContent audio = AudioContent.builder()
        .data("audiobase64")
        .uri("gs://bucket/audio.mp3")
        .mimeType(new AudioMimeType(AudioMimeType.Known.AUDIO_MP3))
        .build();

    // Assert
    assertTrue(audio.data().isPresent());
    assertEquals("audiobase64", audio.data().get());
    assertTrue(audio.uri().isPresent());
    assertEquals("gs://bucket/audio.mp3", audio.uri().get());
    assertTrue(audio.mimeType().isPresent());
    assertEquals("audio/mp3", audio.mimeType().get().toString());
  }

  @Test
  public void testAudioContentJsonSerialization() {
    // Arrange
    AudioContent audio = AudioContent.builder()
        .data("audiodata")
        .mimeType(new AudioMimeType(AudioMimeType.Known.AUDIO_FLAC))
        .build();

    // Act
    String json = audio.toJson();

    // Assert
    assertTrue(json.contains("\"data\":\"audiodata\""));
    assertTrue(json.contains("\"mime_type\":\"audio/flac\""));
    assertTrue(json.contains("\"type\":\"audio\""));
  }

  @Test
  public void testAudioContentToBuilder() {
    // Arrange
    AudioContent original = AudioContent.builder()
        .data("original")
        .mimeType(new AudioMimeType(AudioMimeType.Known.AUDIO_MP3))
        .build();

    // Act
    AudioContent modified = original.toBuilder()
        .uri("gs://new/path")
        .build();

    // Assert
    assertEquals("original", modified.data().get());
    assertEquals("gs://new/path", modified.uri().get());
  }

  // ========== VideoContent Tests ==========

  @Test
  public void testVideoContentWithAllFields() {
    // Arrange & Act
    VideoContent video = VideoContent.builder()
        .data("videobase64")
        .uri("gs://bucket/video.mp4")
        .mimeType(new VideoMimeType(VideoMimeType.Known.VIDEO_MP4))
        .resolution(new MediaResolution(MediaResolution.Known.ULTRA_HIGH))
        .build();

    // Assert
    assertTrue(video.data().isPresent());
    assertTrue(video.uri().isPresent());
    assertTrue(video.mimeType().isPresent());
    assertEquals("video/mp4", video.mimeType().get().toString());
    assertTrue(video.resolution().isPresent());
    assertEquals("ultra_high", video.resolution().get().toString());
  }

  @Test
  public void testVideoContentJsonSerialization() {
    // Arrange
    VideoContent video = VideoContent.builder()
        .uri("https://example.com/video.mov")
        .mimeType(new VideoMimeType(VideoMimeType.Known.VIDEO_MOV))
        .resolution(new MediaResolution(MediaResolution.Known.MEDIUM))
        .build();

    // Act
    String json = video.toJson();

    // Assert
    assertTrue(json.contains("\"uri\":\"https://example.com/video.mov\""));
    assertTrue(json.contains("\"mime_type\":\"video/mov\""));
    assertTrue(json.contains("\"resolution\":\"medium\""));
    assertTrue(json.contains("\"type\":\"video\""));
  }

  @Test
  public void testVideoContentToBuilder() {
    // Arrange
    VideoContent original = VideoContent.builder()
        .data("original")
        .mimeType(new VideoMimeType(VideoMimeType.Known.VIDEO_MP4))
        .build();

    // Act
    VideoContent modified = original.toBuilder()
        .resolution(new MediaResolution(MediaResolution.Known.HIGH))
        .build();

    // Assert
    assertEquals("original", modified.data().get());
    assertEquals("high", modified.resolution().get().toString());
  }

  // ========== DocumentContent Tests ==========

  @Test
  public void testDocumentContentWithAllFields() {
    // Arrange & Act
    DocumentContent doc = DocumentContent.builder()
        .data("pdfbase64")
        .uri("gs://bucket/doc.pdf")
        .mimeType(new DocumentMimeType(DocumentMimeType.Known.APPLICATION_PDF))
        .build();

    // Assert
    assertTrue(doc.data().isPresent());
    assertEquals("pdfbase64", doc.data().get());
    assertTrue(doc.uri().isPresent());
    assertTrue(doc.mimeType().isPresent());
    assertEquals("application/pdf", doc.mimeType().get().toString());
  }

  @Test
  public void testDocumentContentJsonSerialization() {
    // Arrange
    DocumentContent doc = DocumentContent.builder()
        .data("docdata")
        .mimeType(new DocumentMimeType(DocumentMimeType.Known.APPLICATION_PDF))
        .build();

    // Act
    String json = doc.toJson();

    // Assert
    assertTrue(json.contains("\"data\":\"docdata\""));
    assertTrue(json.contains("\"mime_type\":\"application/pdf\""));
    assertTrue(json.contains("\"type\":\"document\""));
  }

  @Test
  public void testDocumentContentToBuilder() {
    // Arrange
    DocumentContent original = DocumentContent.builder()
        .data("original")
        .mimeType(new DocumentMimeType(DocumentMimeType.Known.APPLICATION_PDF))
        .build();

    // Act
    DocumentContent modified = original.toBuilder()
        .uri("gs://new/doc.pdf")
        .build();

    // Assert
    assertEquals("original", modified.data().get());
    assertEquals("gs://new/doc.pdf", modified.uri().get());
  }

  // ========== FunctionCallContent Tests ==========

  @Test
  public void testFunctionCallContentWithAllFields() {
    // Arrange
    Map<String, Object> args = new HashMap<>();
    args.put("location", "San Francisco");
    args.put("units", "celsius");

    // Act
    FunctionCallContent call = FunctionCallContent.builder()
        .id("call-123")
        .name("get_weather")
        .arguments(args)
        .build();

    // Assert
    assertEquals("call-123", call.id());
    assertTrue(call.name().isPresent());
    assertEquals("get_weather", call.name().get());
    assertTrue(call.arguments().isPresent());
    assertEquals("San Francisco", call.arguments().get().get("location"));
  }

  @Test
  public void testFunctionCallContentJsonSerialization() {
    // Arrange
    Map<String, Object> args = new HashMap<>();
    args.put("city", "NYC");

    FunctionCallContent call = FunctionCallContent.builder()
        .id("fc-001")
        .name("lookup_city")
        .arguments(args)
        .build();

    // Act
    String json = call.toJson();

    // Assert
    assertTrue(json.contains("\"id\":\"fc-001\""));
    assertTrue(json.contains("\"name\":\"lookup_city\""));
    assertTrue(json.contains("\"type\":\"function_call\""));
  }

  @Test
  public void testFunctionCallContentToBuilder() {
    // Arrange
    FunctionCallContent original = FunctionCallContent.builder()
        .id("original-id")
        .name("original_func")
        .build();

    // Act
    FunctionCallContent modified = original.toBuilder()
        .name("modified_func")
        .build();

    // Assert
    assertEquals("original-id", modified.id());
    assertEquals("modified_func", modified.name().get());
  }

  // ========== CodeExecutionCallContent Tests ==========

  @Test
  public void testCodeExecutionCallContentWithAllFields() {
    // Arrange & Act
    CodeExecutionCallContent call = CodeExecutionCallContent.builder()
        .id("code-123")
        .arguments(CodeExecutionCallArguments.builder()
            .language("python")
            .code("print('hello')")
            .build())
        .build();

    // Assert
    assertTrue(call.id().isPresent());
    assertEquals("code-123", call.id().get());
    assertTrue(call.arguments().isPresent());
    assertEquals("python", call.arguments().get().language().get());
    assertEquals("print('hello')", call.arguments().get().code().get());
  }

  @Test
  public void testCodeExecutionCallContentJsonSerialization() {
    // Arrange
    CodeExecutionCallContent call = CodeExecutionCallContent.builder()
        .id("exec-001")
        .arguments(CodeExecutionCallArguments.builder()
            .language("python")
            .code("x = 1 + 1")
            .build())
        .build();

    // Act
    String json = call.toJson();

    // Assert
    assertTrue(json.contains("\"id\":\"exec-001\""));
    assertTrue(json.contains("\"type\":\"code_execution_call\""));
    assertTrue(json.contains("\"language\":\"python\""));
  }

  @Test
  public void testCodeExecutionCallContentToBuilder() {
    // Arrange
    CodeExecutionCallContent original = CodeExecutionCallContent.builder()
        .id("original-id")
        .build();

    // Act
    CodeExecutionCallContent modified = original.toBuilder()
        .arguments(CodeExecutionCallArguments.builder()
            .language("ruby")
            .code("puts 'hi'")
            .build())
        .build();

    // Assert
    assertEquals("original-id", modified.id().get());
    assertEquals("ruby", modified.arguments().get().language().get());
  }

  // ========== CodeExecutionResultContent Tests ==========

  @Test
  public void testCodeExecutionResultContentWithAllFields() {
    // Arrange & Act
    CodeExecutionResultContent result = CodeExecutionResultContent.builder()
        .callId("call-123")
        .result("Hello, World!")
        .isError(false)
        .signature("sig-abc")
        .build();

    // Assert
    assertTrue(result.result().isPresent());
    assertEquals("Hello, World!", result.result().get());
    assertTrue(result.callId().isPresent());
    assertEquals("call-123", result.callId().get());
    assertTrue(result.isError().isPresent());
    assertFalse(result.isError().get());
    assertEquals("sig-abc", result.signature().get());
  }

  @Test
  public void testCodeExecutionResultContentJsonSerialization() {
    // Arrange
    CodeExecutionResultContent result = CodeExecutionResultContent.builder()
        .callId("res-001")
        .result("output")
        .isError(false)
        .build();

    // Act
    String json = result.toJson();

    // Assert
    assertTrue(json.contains("\"result\":\"output\""));
    assertTrue(json.contains("\"call_id\":\"res-001\""));
    assertTrue(json.contains("\"is_error\":false"));
    assertTrue(json.contains("\"type\":\"code_execution_result\""));
  }

  @Test
  public void testCodeExecutionResultContentToBuilder() {
    // Arrange
    CodeExecutionResultContent original = CodeExecutionResultContent.builder()
        .callId("original-call")
        .result("original result")
        .build();

    // Act
    CodeExecutionResultContent modified = original.toBuilder()
        .isError(true)
        .build();

    // Assert
    assertEquals("original-call", modified.callId().get());
    assertEquals("original result", modified.result().get());
    assertTrue(modified.isError().get());
  }

  // ========== FileSearchCallContent Tests ==========

  @Test
  public void testFileSearchCallContentWithId() {
    // Arrange & Act
    FileSearchCallContent call = FileSearchCallContent.builder()
        .id("search-123")
        .build();

    // Assert
    assertTrue(call.id().isPresent());
    assertEquals("search-123", call.id().get());
  }

  @Test
  public void testFileSearchCallContentJsonSerialization() {
    // Arrange
    FileSearchCallContent call = FileSearchCallContent.builder()
        .id("fs-001")
        .build();

    // Act
    String json = call.toJson();

    // Assert
    assertTrue(json.contains("\"id\":\"fs-001\""));
    assertTrue(json.contains("\"type\":\"file_search_call\""));
  }

  @Test
  public void testFileSearchCallContentToBuilder() {
    // Arrange
    FileSearchCallContent original = FileSearchCallContent.builder()
        .id("original-id")
        .build();

    // Act
    FileSearchCallContent modified = original.toBuilder()
        .id("modified-id")
        .build();

    // Assert
    assertEquals("modified-id", modified.id().get());
  }

  // ========== GoogleSearchCallContent Tests ==========

  @Test
  public void testGoogleSearchCallContentWithAllFields() {
    // Arrange & Act
    GoogleSearchCallContent call = GoogleSearchCallContent.builder()
        .id("gs-123")
        .arguments(GoogleSearchCallArguments.builder()
            .queries(Arrays.asList("AI news", "machine learning"))
            .build())
        .build();

    // Assert
    assertTrue(call.id().isPresent());
    assertEquals("gs-123", call.id().get());
    assertTrue(call.arguments().isPresent());
    assertEquals(2, call.arguments().get().queries().get().size());
  }

  @Test
  public void testGoogleSearchCallContentJsonSerialization() {
    // Arrange
    GoogleSearchCallContent call = GoogleSearchCallContent.builder()
        .id("gs-ser-001")
        .arguments(GoogleSearchCallArguments.builder()
            .queries(Arrays.asList("test query"))
            .build())
        .build();

    // Act
    String json = call.toJson();

    // Assert
    assertTrue(json.contains("\"id\":\"gs-ser-001\""));
    assertTrue(json.contains("\"type\":\"google_search_call\""));
    assertTrue(json.contains("\"queries\""));
  }

  @Test
  public void testGoogleSearchCallContentToBuilder() {
    // Arrange
    GoogleSearchCallContent original = GoogleSearchCallContent.builder()
        .id("original-id")
        .build();

    // Act
    GoogleSearchCallContent modified = original.toBuilder()
        .arguments(GoogleSearchCallArguments.builder()
            .queries(Arrays.asList("new query"))
            .build())
        .build();

    // Assert
    assertEquals("original-id", modified.id().get());
    assertTrue(modified.arguments().isPresent());
  }

  // ========== GoogleSearchResultContent Tests ==========

  @Test
  public void testGoogleSearchResultContentWithAllFields() {
    // Arrange
    List<GoogleSearchResult> results = Arrays.asList(
        GoogleSearchResult.builder()
            .title("Test Result")
            .url("https://example.com")
            .renderedContent("A test rendered content")
            .build()
    );

    // Act
    GoogleSearchResultContent content = GoogleSearchResultContent.builder()
        .callId("gsr-123")
        .result(results)
        .signature("sig-xyz")
        .isError(false)
        .build();

    // Assert
    assertTrue(content.result().isPresent());
    assertEquals(1, content.result().get().size());
    assertEquals("gsr-123", content.callId().get());
    assertEquals("sig-xyz", content.signature().get());
    assertFalse(content.isError().get());
  }

  @Test
  public void testGoogleSearchResultContentJsonSerialization() {
    // Arrange
    GoogleSearchResultContent content = GoogleSearchResultContent.builder()
        .callId("gsr-ser-001")
        .result(Arrays.asList(GoogleSearchResult.builder().title("Title").build()))
        .isError(false)
        .build();

    // Act
    String json = content.toJson();

    // Assert
    assertTrue(json.contains("\"call_id\":\"gsr-ser-001\""));
    assertTrue(json.contains("\"type\":\"google_search_result\""));
  }

  @Test
  public void testGoogleSearchResultContentToBuilder() {
    // Arrange
    GoogleSearchResultContent original = GoogleSearchResultContent.builder()
        .callId("original-call")
        .isError(false)
        .build();

    // Act
    GoogleSearchResultContent modified = original.toBuilder()
        .signature("new-sig")
        .build();

    // Assert
    assertEquals("original-call", modified.callId().get());
    assertEquals("new-sig", modified.signature().get());
  }

  // ========== UrlContextCallContent Tests ==========

  @Test
  public void testUrlContextCallContentWithAllFields() {
    // Arrange & Act
    UrlContextCallContent call = UrlContextCallContent.builder()
        .id("url-123")
        .arguments(UrlContextCallArguments.builder()
            .urls(Arrays.asList("https://example.com", "https://test.com"))
            .build())
        .build();

    // Assert
    assertEquals("url-123", call.id().get());
    assertTrue(call.arguments().isPresent());
    assertEquals(2, call.arguments().get().urls().get().size());
  }

  @Test
  public void testUrlContextCallContentJsonSerialization() {
    // Arrange
    UrlContextCallContent call = UrlContextCallContent.builder()
        .id("url-ser-001")
        .arguments(UrlContextCallArguments.builder()
            .urls(Arrays.asList("https://example.com"))
            .build())
        .build();

    // Act
    String json = call.toJson();

    // Assert
    assertTrue(json.contains("\"id\":\"url-ser-001\""));
    assertTrue(json.contains("\"type\":\"url_context_call\""));
    assertTrue(json.contains("\"urls\""));
  }

  @Test
  public void testUrlContextCallContentToBuilder() {
    // Arrange
    UrlContextCallContent original = UrlContextCallContent.builder()
        .id("original-id")
        .build();

    // Act
    UrlContextCallContent modified = original.toBuilder()
        .arguments(UrlContextCallArguments.builder()
            .urls(Arrays.asList("https://new.com"))
            .build())
        .build();

    // Assert
    assertEquals("original-id", modified.id().get());
    assertTrue(modified.arguments().isPresent());
  }

  // ========== UrlContextResultContent Tests ==========

  @Test
  public void testUrlContextResultContentWithAllFields() {
    // Arrange
    List<UrlContextResult> results = Arrays.asList(
        UrlContextResult.builder()
            .url("https://example.com")
            .build()
    );

    // Act
    UrlContextResultContent content = UrlContextResultContent.builder()
        .callId("ucr-123")
        .result(results)
        .signature("sig-ucr")
        .isError(false)
        .build();

    // Assert
    assertTrue(content.result().isPresent());
    assertEquals(1, content.result().get().size());
    assertEquals("ucr-123", content.callId().get());
    assertEquals("sig-ucr", content.signature().get());
    assertFalse(content.isError().get());
  }

  @Test
  public void testUrlContextResultContentJsonSerialization() {
    // Arrange
    UrlContextResultContent content = UrlContextResultContent.builder()
        .callId("ucr-ser-001")
        .result(Arrays.asList(UrlContextResult.builder().url("https://example.com").build()))
        .isError(false)
        .build();

    // Act
    String json = content.toJson();

    // Assert
    assertTrue(json.contains("\"call_id\":\"ucr-ser-001\""));
    assertTrue(json.contains("\"type\":\"url_context_result\""));
  }

  @Test
  public void testUrlContextResultContentToBuilder() {
    // Arrange
    UrlContextResultContent original = UrlContextResultContent.builder()
        .callId("original-call")
        .isError(false)
        .build();

    // Act
    UrlContextResultContent modified = original.toBuilder()
        .signature("new-sig")
        .build();

    // Assert
    assertEquals("original-call", modified.callId().get());
    assertEquals("new-sig", modified.signature().get());
  }

  // ========== Content Polymorphic Deserialization Tests ==========

  @Test
  public void testPolymorphicDeserializationText() {
    // Arrange
    String json = "{\"type\":\"text\",\"text\":\"Hello world\"}";
    JsonNode jsonNode = JsonSerializable.stringToJsonNode(json);

    // Act
    Content content = JsonSerializable.fromJsonNodePolymorphic(jsonNode, Content.class);

    // Assert
    assertNotNull(content);
    assertTrue(content instanceof TextContent);
    assertEquals("Hello world", ((TextContent) content).text().get());
  }

  @Test
  public void testPolymorphicDeserializationImage() {
    // Arrange
    String json = "{\"type\":\"image\",\"data\":\"base64data\",\"mime_type\":\"image/png\"}";
    JsonNode jsonNode = JsonSerializable.stringToJsonNode(json);

    // Act
    Content content = JsonSerializable.fromJsonNodePolymorphic(jsonNode, Content.class);

    // Assert
    assertNotNull(content);
    assertTrue(content instanceof ImageContent);
  }

  @Test
  public void testPolymorphicDeserializationAudio() {
    // Arrange
    String json = "{\"type\":\"audio\",\"data\":\"audiodata\",\"mime_type\":\"audio/mp3\"}";
    JsonNode jsonNode = JsonSerializable.stringToJsonNode(json);

    // Act
    Content content = JsonSerializable.fromJsonNodePolymorphic(jsonNode, Content.class);

    // Assert
    assertNotNull(content);
    assertTrue(content instanceof AudioContent);
  }

  @Test
  public void testPolymorphicDeserializationVideo() {
    // Arrange
    String json = "{\"type\":\"video\",\"data\":\"videodata\",\"mime_type\":\"video/mp4\"}";
    JsonNode jsonNode = JsonSerializable.stringToJsonNode(json);

    // Act
    Content content = JsonSerializable.fromJsonNodePolymorphic(jsonNode, Content.class);

    // Assert
    assertNotNull(content);
    assertTrue(content instanceof VideoContent);
  }

  @Test
  public void testPolymorphicDeserializationDocument() {
    // Arrange
    String json = "{\"type\":\"document\",\"data\":\"pdfdata\",\"mime_type\":\"application/pdf\"}";
    JsonNode jsonNode = JsonSerializable.stringToJsonNode(json);

    // Act
    Content content = JsonSerializable.fromJsonNodePolymorphic(jsonNode, Content.class);

    // Assert
    assertNotNull(content);
    assertTrue(content instanceof DocumentContent);
  }

  @Test
  public void testPolymorphicDeserializationFunctionCall() {
    // Arrange
    String json = "{\"type\":\"function_call\",\"id\":\"fc-poly\",\"name\":\"test\"}";
    JsonNode jsonNode = JsonSerializable.stringToJsonNode(json);

    // Act
    Content content = JsonSerializable.fromJsonNodePolymorphic(jsonNode, Content.class);

    // Assert
    assertNotNull(content);
    assertTrue(content instanceof FunctionCallContent);
  }

  @Test
  public void testPolymorphicDeserializationCodeExecutionCall() {
    // Arrange
    String json = "{\"type\":\"code_execution_call\",\"id\":\"code-poly\"}";
    JsonNode jsonNode = JsonSerializable.stringToJsonNode(json);

    // Act
    Content content = JsonSerializable.fromJsonNodePolymorphic(jsonNode, Content.class);

    // Assert
    assertNotNull(content);
    assertTrue(content instanceof CodeExecutionCallContent);
  }

  @Test
  public void testPolymorphicDeserializationCodeExecutionResult() {
    // Arrange
    String json = "{\"type\":\"code_execution_result\",\"result\":\"output\",\"is_error\":false}";
    JsonNode jsonNode = JsonSerializable.stringToJsonNode(json);

    // Act
    Content content = JsonSerializable.fromJsonNodePolymorphic(jsonNode, Content.class);

    // Assert
    assertNotNull(content);
    assertTrue(content instanceof CodeExecutionResultContent);
  }

  @Test
  public void testPolymorphicDeserializationGoogleSearchCall() {
    // Arrange
    String json = "{\"type\":\"google_search_call\",\"id\":\"gs-poly\"}";
    JsonNode jsonNode = JsonSerializable.stringToJsonNode(json);

    // Act
    Content content = JsonSerializable.fromJsonNodePolymorphic(jsonNode, Content.class);

    // Assert
    assertNotNull(content);
    assertTrue(content instanceof GoogleSearchCallContent);
  }

  @Test
  public void testPolymorphicDeserializationGoogleSearchResult() {
    // Arrange
    String json = "{\"type\":\"google_search_result\",\"call_id\":\"gsr-poly\"}";
    JsonNode jsonNode = JsonSerializable.stringToJsonNode(json);

    // Act
    Content content = JsonSerializable.fromJsonNodePolymorphic(jsonNode, Content.class);

    // Assert
    assertNotNull(content);
    assertTrue(content instanceof GoogleSearchResultContent);
  }

  @Test
  public void testPolymorphicDeserializationUrlContextCall() {
    // Arrange
    String json = "{\"type\":\"url_context_call\",\"id\":\"uc-poly\"}";
    JsonNode jsonNode = JsonSerializable.stringToJsonNode(json);

    // Act
    Content content = JsonSerializable.fromJsonNodePolymorphic(jsonNode, Content.class);

    // Assert
    assertNotNull(content);
    assertTrue(content instanceof UrlContextCallContent);
  }

  @Test
  public void testPolymorphicDeserializationUrlContextResult() {
    // Arrange
    String json = "{\"type\":\"url_context_result\",\"call_id\":\"ucr-poly\"}";
    JsonNode jsonNode = JsonSerializable.stringToJsonNode(json);

    // Act
    Content content = JsonSerializable.fromJsonNodePolymorphic(jsonNode, Content.class);

    // Assert
    assertNotNull(content);
    assertTrue(content instanceof UrlContextResultContent);
  }

  @Test
  public void testPolymorphicDeserializationFileSearchCall() {
    // Arrange
    String json = "{\"type\":\"file_search_call\",\"id\":\"fs-poly\"}";
    JsonNode jsonNode = JsonSerializable.stringToJsonNode(json);

    // Act
    Content content = JsonSerializable.fromJsonNodePolymorphic(jsonNode, Content.class);

    // Assert
    assertNotNull(content);
    assertTrue(content instanceof FileSearchCallContent);
  }

  // ========== All Content Types Implement Interface ==========

  @Test
  public void testAllContentTypesImplementContentInterface() {
    // Verify all content types implement Content interface
    Content image = ImageContent.builder().data("data").build();
    Content audio = AudioContent.builder().data("data").build();
    Content video = VideoContent.builder().data("data").build();
    Content document = DocumentContent.builder().data("data").build();
    Content functionCall = FunctionCallContent.builder().id("id").build();
    Content codeCall = CodeExecutionCallContent.builder().build();
    Content codeResult = CodeExecutionResultContent.builder().build();
    Content fileSearch = FileSearchCallContent.builder().build();
    Content googleSearchCall = GoogleSearchCallContent.builder().build();
    Content googleSearchResult = GoogleSearchResultContent.builder().build();
    Content urlContextCall = UrlContextCallContent.builder().build();
    Content urlContextResult = UrlContextResultContent.builder().build();
    Content textContent = TextContent.builder().text("test").build();

    assertNotNull(image);
    assertNotNull(audio);
    assertNotNull(video);
    assertNotNull(document);
    assertNotNull(functionCall);
    assertNotNull(codeCall);
    assertNotNull(codeResult);
    assertNotNull(fileSearch);
    assertNotNull(googleSearchCall);
    assertNotNull(googleSearchResult);
    assertNotNull(urlContextCall);
    assertNotNull(urlContextResult);
    assertNotNull(textContent);
  }
}
