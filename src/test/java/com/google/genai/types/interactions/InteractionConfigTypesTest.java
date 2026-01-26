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
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.genai.types.interactions.content.TextContent;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

/**
 * Tests for interaction configuration types in the Interactions API.
 *
 * <p>Tests the following types:
 * <ul>
 *   <li>GenerationConfig - Generation parameters</li>
 *   <li>Usage - Token usage statistics</li>
 *   <li>ModalityTokens - Token counts by modality</li>
 *   <li>Turn - Conversation turn with role and content</li>
 *   <li>ThinkingLevel - Thinking depth enum</li>
 *   <li>InteractionStatus - Interaction status enum</li>
 *   <li>GoogleSearchResult - Search result fields</li>
 *   <li>UrlContextResult - URL context result fields</li>
 *   <li>FileSearchResult - File search result fields</li>
 * </ul>
 *
 * <p>Note: Methods annotated with @ExcludeFromGeneratedCoverageReport are not directly tested.
 */
public class InteractionConfigTypesTest {

  // ========== GenerationConfig Tests ==========

  @Test
  public void testGenerationConfigWithAllFields() {
    // Arrange & Act
    GenerationConfig config = GenerationConfig.builder()
        .temperature(0.7f)
        .topP(0.9f)
        .seed(42)
        .maxOutputTokens(1024)
        .stopSequences("STOP", "END")
        .build();

    // Assert
    assertTrue(config.temperature().isPresent());
    assertEquals(0.7f, config.temperature().get(), 0.001f);
    assertTrue(config.topP().isPresent());
    assertEquals(0.9f, config.topP().get(), 0.001f);
    assertTrue(config.seed().isPresent());
    assertEquals(42, config.seed().get());
    assertTrue(config.maxOutputTokens().isPresent());
    assertEquals(1024, config.maxOutputTokens().get());
    assertTrue(config.stopSequences().isPresent());
    assertEquals(2, config.stopSequences().get().size());
  }

  @Test
  public void testGenerationConfigWithThinkingLevel() {
    // Arrange & Act
    GenerationConfig config = GenerationConfig.builder()
        .thinkingLevel(new ThinkingLevel(ThinkingLevel.Known.HIGH))
        .build();

    // Assert
    assertTrue(config.thinkingLevel().isPresent());
    assertEquals("high", config.thinkingLevel().get().toString());
  }

  @Test
  public void testGenerationConfigJsonSerialization() {
    // Arrange
    GenerationConfig config = GenerationConfig.builder()
        .temperature(0.5f)
        .topP(0.8f)
        .maxOutputTokens(512)
        .build();

    // Act
    String json = config.toJson();

    // Assert
    assertTrue(json.contains("\"temperature\":0.5"));
    assertTrue(json.contains("\"top_p\":0.8"));
    assertTrue(json.contains("\"max_output_tokens\":512"));
  }

  @Test
  public void testGenerationConfigToBuilder() {
    // Arrange
    GenerationConfig original = GenerationConfig.builder()
        .temperature(0.7f)
        .maxOutputTokens(1000)
        .build();

    // Act
    GenerationConfig modified = original.toBuilder()
        .maxOutputTokens(2000)
        .build();

    // Assert
    assertEquals(0.7f, modified.temperature().get(), 0.001f);
    assertEquals(2000, modified.maxOutputTokens().get());
  }

  @Test
  public void testGenerationConfigWithSpeechConfigBuilder() {
    // Arrange & Act
    GenerationConfig config = GenerationConfig.builder()
        .speechConfig(SpeechConfig.builder()
            .language("en-US")
            .voice("en-US-Wavenet-A"))
        .build();

    // Assert
    assertTrue(config.speechConfig().isPresent());
    assertEquals("en-US", config.speechConfig().get().language().get());
  }

  @Test
  public void testGenerationConfigWithImageConfigBuilder() {
    // Arrange & Act
    GenerationConfig config = GenerationConfig.builder()
        .imageConfig(ImageConfig.builder()
            .aspectRatio("16:9")
            .imageSize("2K"))
        .build();

    // Assert
    assertTrue(config.imageConfig().isPresent());
    assertEquals("16:9", config.imageConfig().get().aspectRatio().get());
  }

  // ========== Usage Tests ==========

  @Test
  public void testUsageWithAllFields() {
    // Arrange & Act
    Usage usage = Usage.builder()
        .totalInputTokens(100)
        .totalOutputTokens(200)
        .totalCachedTokens(50)
        .totalToolUseTokens(25)
        .totalThoughtTokens(75)
        .totalTokens(450)
        .build();

    // Assert
    assertEquals(100, usage.totalInputTokens().get());
    assertEquals(200, usage.totalOutputTokens().get());
    assertEquals(50, usage.totalCachedTokens().get());
    assertEquals(25, usage.totalToolUseTokens().get());
    assertEquals(75, usage.totalThoughtTokens().get());
    assertEquals(450, usage.totalTokens().get());
  }

  @Test
  public void testUsageWithModalityTokens() {
    // Arrange
    ModalityTokens textTokens = ModalityTokens.builder()
        .modality(new ResponseModality(ResponseModality.Known.TEXT))
        .tokens(100)
        .build();

    ModalityTokens imageTokens = ModalityTokens.builder()
        .modality(new ResponseModality(ResponseModality.Known.IMAGE))
        .tokens(50)
        .build();

    // Act
    Usage usage = Usage.builder()
        .totalInputTokens(150)
        .inputTokensByModality(textTokens, imageTokens)
        .build();

    // Assert
    assertTrue(usage.inputTokensByModality().isPresent());
    assertEquals(2, usage.inputTokensByModality().get().size());
  }

  @Test
  public void testUsageJsonSerialization() {
    // Arrange
    Usage usage = Usage.builder()
        .totalInputTokens(100)
        .totalOutputTokens(200)
        .totalTokens(300)
        .build();

    // Act
    String json = usage.toJson();

    // Assert
    assertTrue(json.contains("\"total_input_tokens\":100"));
    assertTrue(json.contains("\"total_output_tokens\":200"));
    assertTrue(json.contains("\"total_tokens\":300"));
  }

  @Test
  public void testUsageToBuilder() {
    // Arrange
    Usage original = Usage.builder()
        .totalInputTokens(100)
        .totalOutputTokens(200)
        .build();

    // Act
    Usage modified = original.toBuilder()
        .totalOutputTokens(300)
        .build();

    // Assert
    assertEquals(100, modified.totalInputTokens().get());
    assertEquals(300, modified.totalOutputTokens().get());
  }

  // ========== ModalityTokens Tests ==========

  @Test
  public void testModalityTokensWithAllFields() {
    // Arrange & Act
    ModalityTokens tokens = ModalityTokens.builder()
        .modality(new ResponseModality(ResponseModality.Known.TEXT))
        .tokens(500)
        .build();

    // Assert
    assertTrue(tokens.modality().isPresent());
    assertEquals("text", tokens.modality().get().toString());
    assertTrue(tokens.tokens().isPresent());
    assertEquals(500, tokens.tokens().get());
  }

  @Test
  public void testModalityTokensJsonSerialization() {
    // Arrange
    ModalityTokens tokens = ModalityTokens.builder()
        .modality(new ResponseModality(ResponseModality.Known.AUDIO))
        .tokens(250)
        .build();

    // Act
    String json = tokens.toJson();

    // Assert
    assertTrue(json.contains("\"modality\":\"audio\""));
    assertTrue(json.contains("\"tokens\":250"));
  }

  @Test
  public void testModalityTokensToBuilder() {
    // Arrange
    ModalityTokens original = ModalityTokens.builder()
        .modality(new ResponseModality(ResponseModality.Known.TEXT))
        .tokens(100)
        .build();

    // Act
    ModalityTokens modified = original.toBuilder()
        .tokens(200)
        .build();

    // Assert
    assertEquals("text", modified.modality().get().toString());
    assertEquals(200, modified.tokens().get());
  }

  // ========== Turn Tests ==========

  @Test
  public void testTurnWithRoleAndContent() {
    // Arrange & Act
    Turn turn = Turn.builder()
        .role("user")
        .content(TextContent.builder().text("Hello").build())
        .build();

    // Assert
    assertTrue(turn.role().isPresent());
    assertEquals("user", turn.role().get());
    assertTrue(turn.content().isPresent());
    assertEquals(1, turn.content().get().size());
  }

  @Test
  public void testTurnWithMultipleContents() {
    // Arrange & Act
    Turn turn = Turn.builder()
        .role("model")
        .content(Arrays.asList(
            TextContent.builder().text("Part 1").build(),
            TextContent.builder().text("Part 2").build()
        ))
        .build();

    // Assert
    assertEquals("model", turn.role().get());
    assertEquals(2, turn.content().get().size());
  }

  @Test
  public void testTurnJsonSerialization() {
    // Arrange
    Turn turn = Turn.builder()
        .role("user")
        .content(TextContent.builder().text("Test message").build())
        .build();

    // Act
    String json = turn.toJson();

    // Assert
    assertTrue(json.contains("\"role\":\"user\""));
    assertTrue(json.contains("\"content\""));
  }

  @Test
  public void testTurnToBuilder() {
    // Arrange
    Turn original = Turn.builder()
        .role("user")
        .content(TextContent.builder().text("Original").build())
        .build();

    // Act
    Turn modified = original.toBuilder()
        .role("model")
        .build();

    // Assert
    assertEquals("model", modified.role().get());
    assertTrue(modified.content().isPresent());
  }

  // ========== ThinkingLevel Tests ==========

  @Test
  public void testThinkingLevelKnownValues() {
    // Test all known values
    ThinkingLevel minimal = new ThinkingLevel(ThinkingLevel.Known.MINIMAL);
    ThinkingLevel low = new ThinkingLevel(ThinkingLevel.Known.LOW);
    ThinkingLevel medium = new ThinkingLevel(ThinkingLevel.Known.MEDIUM);
    ThinkingLevel high = new ThinkingLevel(ThinkingLevel.Known.HIGH);

    assertEquals("minimal", minimal.toString());
    assertEquals("low", low.toString());
    assertEquals("medium", medium.toString());
    assertEquals("high", high.toString());

    assertEquals(ThinkingLevel.Known.MINIMAL, minimal.knownEnum());
    assertEquals(ThinkingLevel.Known.LOW, low.knownEnum());
    assertEquals(ThinkingLevel.Known.MEDIUM, medium.knownEnum());
    assertEquals(ThinkingLevel.Known.HIGH, high.knownEnum());
  }

  @Test
  public void testThinkingLevelFromString() {
    // Test case-insensitive parsing
    ThinkingLevel fromLower = new ThinkingLevel("low");
    ThinkingLevel fromUpper = new ThinkingLevel("LOW");
    ThinkingLevel fromMixed = new ThinkingLevel("Low");

    assertEquals(ThinkingLevel.Known.LOW, fromLower.knownEnum());
    assertEquals(ThinkingLevel.Known.LOW, fromUpper.knownEnum());
    assertEquals(ThinkingLevel.Known.LOW, fromMixed.knownEnum());
  }

  @Test
  public void testThinkingLevelUnknownValue() {
    // Test fallback for unknown values
    ThinkingLevel unknown = new ThinkingLevel("super_high");

    assertEquals(ThinkingLevel.Known.THINKING_LEVEL_UNSPECIFIED, unknown.knownEnum());
    assertEquals("super_high", unknown.toString());
  }

  @Test
  public void testThinkingLevelEquality() {
    // Test equality for known values
    ThinkingLevel high1 = new ThinkingLevel(ThinkingLevel.Known.HIGH);
    ThinkingLevel high2 = new ThinkingLevel("high");
    ThinkingLevel medium = new ThinkingLevel(ThinkingLevel.Known.MEDIUM);

    assertEquals(high1, high2);
    assertNotEquals(high1, medium);
  }

  @Test
  public void testThinkingLevelHashCode() {
    // Test hashCode consistency
    ThinkingLevel level1 = new ThinkingLevel(ThinkingLevel.Known.MEDIUM);
    ThinkingLevel level2 = new ThinkingLevel("medium");

    assertEquals(level1.hashCode(), level2.hashCode());
  }

  // ========== InteractionStatus Tests ==========

  @Test
  public void testInteractionStatusKnownValues() {
    // Test all known values
    InteractionStatus inProgress = new InteractionStatus(InteractionStatus.Known.IN_PROGRESS);
    InteractionStatus requiresAction = new InteractionStatus(InteractionStatus.Known.REQUIRES_ACTION);
    InteractionStatus completed = new InteractionStatus(InteractionStatus.Known.COMPLETED);
    InteractionStatus failed = new InteractionStatus(InteractionStatus.Known.FAILED);
    InteractionStatus cancelled = new InteractionStatus(InteractionStatus.Known.CANCELLED);

    assertEquals("in_progress", inProgress.toString());
    assertEquals("requires_action", requiresAction.toString());
    assertEquals("completed", completed.toString());
    assertEquals("failed", failed.toString());
    assertEquals("cancelled", cancelled.toString());

    assertEquals(InteractionStatus.Known.IN_PROGRESS, inProgress.knownEnum());
    assertEquals(InteractionStatus.Known.REQUIRES_ACTION, requiresAction.knownEnum());
    assertEquals(InteractionStatus.Known.COMPLETED, completed.knownEnum());
    assertEquals(InteractionStatus.Known.FAILED, failed.knownEnum());
    assertEquals(InteractionStatus.Known.CANCELLED, cancelled.knownEnum());
  }

  @Test
  public void testInteractionStatusFromString() {
    // Test case-insensitive parsing
    InteractionStatus fromLower = new InteractionStatus("in_progress");
    InteractionStatus fromUpper = new InteractionStatus("IN_PROGRESS");
    InteractionStatus fromMixed = new InteractionStatus("In_Progress");

    assertEquals(InteractionStatus.Known.IN_PROGRESS, fromLower.knownEnum());
    assertEquals(InteractionStatus.Known.IN_PROGRESS, fromUpper.knownEnum());
    assertEquals(InteractionStatus.Known.IN_PROGRESS, fromMixed.knownEnum());
  }

  @Test
  public void testInteractionStatusUnknownValue() {
    // Test fallback for unknown values
    InteractionStatus unknown = new InteractionStatus("super_status");

    assertEquals(InteractionStatus.Known.INTERACTION_STATUS_UNSPECIFIED, unknown.knownEnum());
    assertEquals("super_status", unknown.toString());
  }

  @Test
  public void testInteractionStatusInvalidValuesMapToUnspecified() {
    // Test various invalid values all map to UNSPECIFIED
    String[] invalidValues = {"INVALID", "random_value", "123", "", "in-progress"};

    for (String invalidValue : invalidValues) {
      InteractionStatus status = new InteractionStatus(invalidValue);
      assertEquals(InteractionStatus.Known.INTERACTION_STATUS_UNSPECIFIED, status.knownEnum(),
          "Value '" + invalidValue + "' should map to UNSPECIFIED");
      assertEquals(invalidValue, status.toString(),
          "toString() should preserve original value");
    }
  }

  @Test
  public void testInteractionStatusEquality() {
    // Test equality for known values
    InteractionStatus completed1 = new InteractionStatus(InteractionStatus.Known.COMPLETED);
    InteractionStatus completed2 = new InteractionStatus("COMPLETED");
    InteractionStatus failed = new InteractionStatus(InteractionStatus.Known.FAILED);

    assertEquals(completed1, completed2);
    assertNotEquals(completed1, failed);
  }

  @Test
  public void testInteractionStatusHashCode() {
    // Test hashCode consistency
    InteractionStatus status1 = new InteractionStatus(InteractionStatus.Known.IN_PROGRESS);
    InteractionStatus status2 = new InteractionStatus("in_progress");

    assertEquals(status1.hashCode(), status2.hashCode());
  }

  @Test
  public void testInteractionStatusEnumCount() {
    // Verify we have exactly 6 statuses (including UNSPECIFIED)
    assertEquals(6, InteractionStatus.Known.values().length);
  }

  // ========== GoogleSearchResult Tests ==========

  @Test
  public void testGoogleSearchResultWithAllFields() {
    // Arrange & Act
    GoogleSearchResult result = GoogleSearchResult.builder()
        .title("Example Title")
        .url("https://example.com")
        .renderedContent("This is the rendered content of the search result")
        .build();

    // Assert
    assertTrue(result.title().isPresent());
    assertEquals("Example Title", result.title().get());
    assertTrue(result.url().isPresent());
    assertEquals("https://example.com", result.url().get());
    assertTrue(result.renderedContent().isPresent());
    assertEquals("This is the rendered content of the search result", result.renderedContent().get());
  }

  @Test
  public void testGoogleSearchResultJsonSerialization() {
    // Arrange
    GoogleSearchResult result = GoogleSearchResult.builder()
        .title("Test Title")
        .url("https://test.com")
        .renderedContent("Test rendered content")
        .build();

    // Act
    String json = result.toJson();

    // Assert
    assertTrue(json.contains("\"title\":\"Test Title\""));
    assertTrue(json.contains("\"url\":\"https://test.com\""));
    assertTrue(json.contains("\"rendered_content\":\"Test rendered content\""));
  }

  @Test
  public void testGoogleSearchResultToBuilder() {
    // Arrange
    GoogleSearchResult original = GoogleSearchResult.builder()
        .title("Original")
        .url("https://original.com")
        .build();

    // Act
    GoogleSearchResult modified = original.toBuilder()
        .title("Modified")
        .build();

    // Assert
    assertEquals("Modified", modified.title().get());
    assertEquals("https://original.com", modified.url().get());
  }

  // ========== UrlContextResult Tests ==========

  @Test
  public void testUrlContextResultWithAllFields() {
    // Arrange & Act
    UrlContextResult result = UrlContextResult.builder()
        .url("https://example.com/page")
        .status(new UrlContextResultStatus(UrlContextResultStatus.Known.SUCCESS))
        .build();

    // Assert
    assertTrue(result.url().isPresent());
    assertEquals("https://example.com/page", result.url().get());
    assertTrue(result.status().isPresent());
    assertEquals(UrlContextResultStatus.Known.SUCCESS, result.status().get().knownEnum());
  }

  @Test
  public void testUrlContextResultJsonSerialization() {
    // Arrange
    UrlContextResult result = UrlContextResult.builder()
        .url("https://test.com")
        .status(new UrlContextResultStatus(UrlContextResultStatus.Known.ERROR))
        .build();

    // Act
    String json = result.toJson();

    // Assert
    assertTrue(json.contains("\"url\":\"https://test.com\""));
    assertTrue(json.contains("\"status\":\"error\""));
  }

  @Test
  public void testUrlContextResultToBuilder() {
    // Arrange
    UrlContextResult original = UrlContextResult.builder()
        .url("https://original.com")
        .status(new UrlContextResultStatus(UrlContextResultStatus.Known.SUCCESS))
        .build();

    // Act
    UrlContextResult modified = original.toBuilder()
        .status(new UrlContextResultStatus(UrlContextResultStatus.Known.ERROR))
        .build();

    // Assert
    assertEquals("https://original.com", modified.url().get());
    assertEquals(UrlContextResultStatus.Known.ERROR, modified.status().get().knownEnum());
  }

  // ========== FileSearchResult Tests ==========

  @Test
  public void testFileSearchResultWithAllFields() {
    // Arrange & Act
    FileSearchResult result = FileSearchResult.builder()
        .text("Matching text from file")
        .title("Document Title")
        .fileSearchStore("store-123")
        .build();

    // Assert
    assertTrue(result.text().isPresent());
    assertEquals("Matching text from file", result.text().get());
    assertTrue(result.title().isPresent());
    assertEquals("Document Title", result.title().get());
    assertTrue(result.fileSearchStore().isPresent());
    assertEquals("store-123", result.fileSearchStore().get());
  }

  @Test
  public void testFileSearchResultJsonSerialization() {
    // Arrange
    FileSearchResult result = FileSearchResult.builder()
        .text("File text")
        .title("File Title")
        .fileSearchStore("store-abc")
        .build();

    // Act
    String json = result.toJson();

    // Assert
    assertTrue(json.contains("\"text\":\"File text\""));
    assertTrue(json.contains("\"title\":\"File Title\""));
    assertTrue(json.contains("\"file_search_store\":\"store-abc\""));
  }

  @Test
  public void testFileSearchResultToBuilder() {
    // Arrange
    FileSearchResult original = FileSearchResult.builder()
        .text("Original text")
        .title("Original title")
        .build();

    // Act
    FileSearchResult modified = original.toBuilder()
        .title("Modified title")
        .build();

    // Assert
    assertEquals("Original text", modified.text().get());
    assertEquals("Modified title", modified.title().get());
  }

  // ========== SpeechConfig Tests ==========

  @Test
  public void testSpeechConfigWithAllFields() {
    // Arrange & Act
    SpeechConfig config = SpeechConfig.builder()
        .language("en-US")
        .voice("en-US-Wavenet-A")
        .speaker("speaker1")
        .build();

    // Assert
    assertTrue(config.language().isPresent());
    assertEquals("en-US", config.language().get());
    assertTrue(config.voice().isPresent());
    assertEquals("en-US-Wavenet-A", config.voice().get());
    assertTrue(config.speaker().isPresent());
    assertEquals("speaker1", config.speaker().get());
  }

  @Test
  public void testSpeechConfigJsonSerialization() {
    // Arrange
    SpeechConfig config = SpeechConfig.builder()
        .language("fr-FR")
        .voice("fr-FR-Wavenet-B")
        .build();

    // Act
    String json = config.toJson();

    // Assert
    assertTrue(json.contains("\"language\":\"fr-FR\""));
    assertTrue(json.contains("\"voice\":\"fr-FR-Wavenet-B\""));
  }

  @Test
  public void testSpeechConfigToBuilder() {
    // Arrange
    SpeechConfig original = SpeechConfig.builder()
        .language("en-US")
        .voice("voice-1")
        .build();

    // Act
    SpeechConfig modified = original.toBuilder()
        .voice("voice-2")
        .build();

    // Assert
    assertEquals("en-US", modified.language().get());
    assertEquals("voice-2", modified.voice().get());
  }

  // ========== ImageConfig Tests ==========

  @Test
  public void testImageConfigWithAllFields() {
    // Arrange & Act
    ImageConfig config = ImageConfig.builder()
        .aspectRatio("16:9")
        .imageSize("2K")
        .build();

    // Assert
    assertTrue(config.aspectRatio().isPresent());
    assertEquals("16:9", config.aspectRatio().get());
    assertTrue(config.imageSize().isPresent());
    assertEquals("2K", config.imageSize().get());
  }

  @Test
  public void testImageConfigJsonSerialization() {
    // Arrange
    ImageConfig config = ImageConfig.builder()
        .aspectRatio("1:1")
        .imageSize("4K")
        .build();

    // Act
    String json = config.toJson();

    // Assert
    assertTrue(json.contains("\"aspect_ratio\":\"1:1\""));
    assertTrue(json.contains("\"image_size\":\"4K\""));
  }

  @Test
  public void testImageConfigToBuilder() {
    // Arrange
    ImageConfig original = ImageConfig.builder()
        .aspectRatio("4:3")
        .imageSize("1K")
        .build();

    // Act
    ImageConfig modified = original.toBuilder()
        .aspectRatio("16:9")
        .build();

    // Assert
    assertEquals("16:9", modified.aspectRatio().get());
    assertEquals("1K", modified.imageSize().get());
  }

  // ========== CodeExecutionCallArguments Tests ==========

  @Test
  public void testCodeExecutionCallArgumentsWithAllFields() {
    // Arrange & Act
    CodeExecutionCallArguments args = CodeExecutionCallArguments.builder()
        .language("python")
        .code("print('Hello')")
        .build();

    // Assert
    assertTrue(args.language().isPresent());
    assertEquals("python", args.language().get());
    assertTrue(args.code().isPresent());
    assertEquals("print('Hello')", args.code().get());
  }

  @Test
  public void testCodeExecutionCallArgumentsJsonSerialization() {
    // Arrange
    CodeExecutionCallArguments args = CodeExecutionCallArguments.builder()
        .language("javascript")
        .code("console.log('test')")
        .build();

    // Act
    String json = args.toJson();

    // Assert
    assertTrue(json.contains("\"language\":\"javascript\""));
    assertTrue(json.contains("\"code\":\"console.log('test')\""));
  }

  // ========== GoogleSearchCallArguments Tests ==========

  @Test
  public void testGoogleSearchCallArgumentsWithQueries() {
    // Arrange & Act
    GoogleSearchCallArguments args = GoogleSearchCallArguments.builder()
        .queries(Arrays.asList("AI news", "machine learning"))
        .build();

    // Assert
    assertTrue(args.queries().isPresent());
    assertEquals(2, args.queries().get().size());
    assertEquals("AI news", args.queries().get().get(0));
  }

  @Test
  public void testGoogleSearchCallArgumentsJsonSerialization() {
    // Arrange
    GoogleSearchCallArguments args = GoogleSearchCallArguments.builder()
        .queries(Arrays.asList("test query"))
        .build();

    // Act
    String json = args.toJson();

    // Assert
    assertTrue(json.contains("\"queries\""));
    assertTrue(json.contains("\"test query\""));
  }

  // ========== UrlContextCallArguments Tests ==========

  @Test
  public void testUrlContextCallArgumentsWithUrls() {
    // Arrange & Act
    UrlContextCallArguments args = UrlContextCallArguments.builder()
        .urls(Arrays.asList("https://example.com", "https://test.com"))
        .build();

    // Assert
    assertTrue(args.urls().isPresent());
    assertEquals(2, args.urls().get().size());
  }

  @Test
  public void testUrlContextCallArgumentsJsonSerialization() {
    // Arrange
    UrlContextCallArguments args = UrlContextCallArguments.builder()
        .urls(Arrays.asList("https://example.com"))
        .build();

    // Act
    String json = args.toJson();

    // Assert
    assertTrue(json.contains("\"urls\""));
    assertTrue(json.contains("\"https://example.com\""));
  }

  // ========== UrlContextResultStatus Tests ==========

  @Test
  public void testUrlContextResultStatusKnownValues() {
    // Test all known values
    UrlContextResultStatus success = new UrlContextResultStatus(UrlContextResultStatus.Known.SUCCESS);
    UrlContextResultStatus error = new UrlContextResultStatus(UrlContextResultStatus.Known.ERROR);
    UrlContextResultStatus paywall = new UrlContextResultStatus(UrlContextResultStatus.Known.PAYWALL);
    UrlContextResultStatus unsafe = new UrlContextResultStatus(UrlContextResultStatus.Known.UNSAFE);

    assertEquals("success", success.toString());
    assertEquals("error", error.toString());
    assertEquals("paywall", paywall.toString());
    assertEquals("unsafe", unsafe.toString());

    assertEquals(UrlContextResultStatus.Known.SUCCESS, success.knownEnum());
    assertEquals(UrlContextResultStatus.Known.ERROR, error.knownEnum());
    assertEquals(UrlContextResultStatus.Known.PAYWALL, paywall.knownEnum());
    assertEquals(UrlContextResultStatus.Known.UNSAFE, unsafe.knownEnum());
  }

  @Test
  public void testUrlContextResultStatusFromString() {
    // Test case-insensitive parsing
    UrlContextResultStatus fromLower = new UrlContextResultStatus("success");
    UrlContextResultStatus fromUpper = new UrlContextResultStatus("SUCCESS");
    UrlContextResultStatus fromMixed = new UrlContextResultStatus("Success");

    assertEquals(UrlContextResultStatus.Known.SUCCESS, fromLower.knownEnum());
    assertEquals(UrlContextResultStatus.Known.SUCCESS, fromUpper.knownEnum());
    assertEquals(UrlContextResultStatus.Known.SUCCESS, fromMixed.knownEnum());
  }

  @Test
  public void testUrlContextResultStatusUnknownValue() {
    // Test fallback for unknown values
    UrlContextResultStatus unknown = new UrlContextResultStatus("unknown_status");

    assertEquals(UrlContextResultStatus.Known.URL_CONTEXT_RESULT_STATUS_UNSPECIFIED, unknown.knownEnum());
    assertEquals("unknown_status", unknown.toString());
  }

  @Test
  public void testUrlContextResultStatusInvalidValuesMapToUnspecified() {
    // Test various invalid values all map to UNSPECIFIED
    String[] invalidValues = {"INVALID", "random_value", "123", ""};

    for (String invalidValue : invalidValues) {
      UrlContextResultStatus status = new UrlContextResultStatus(invalidValue);
      assertEquals(UrlContextResultStatus.Known.URL_CONTEXT_RESULT_STATUS_UNSPECIFIED, status.knownEnum(),
          "Value '" + invalidValue + "' should map to UNSPECIFIED");
      assertEquals(invalidValue, status.toString(),
          "toString() should preserve original value");
    }
  }

  @Test
  public void testUrlContextResultStatusEquality() {
    // Test equality for known values
    UrlContextResultStatus success1 = new UrlContextResultStatus(UrlContextResultStatus.Known.SUCCESS);
    UrlContextResultStatus success2 = new UrlContextResultStatus("SUCCESS");
    UrlContextResultStatus error = new UrlContextResultStatus(UrlContextResultStatus.Known.ERROR);

    assertEquals(success1, success2);
    assertNotEquals(success1, error);
  }

  @Test
  public void testUrlContextResultStatusHashCode() {
    // Test hashCode consistency
    UrlContextResultStatus status1 = new UrlContextResultStatus(UrlContextResultStatus.Known.ERROR);
    UrlContextResultStatus status2 = new UrlContextResultStatus("error");

    assertEquals(status1.hashCode(), status2.hashCode());
  }

  @Test
  public void testUrlContextResultStatusEnumCount() {
    // Verify we have exactly 5 statuses (including UNSPECIFIED)
    assertEquals(5, UrlContextResultStatus.Known.values().length);
  }
}
