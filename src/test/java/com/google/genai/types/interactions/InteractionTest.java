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

import com.google.genai.types.interactions.GenerationConfig;
import com.google.genai.types.interactions.content.Content;
import com.google.genai.types.interactions.content.TextContent;
import com.google.genai.types.interactions.content.ThoughtContent;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

/** Tests for Interaction type and related classes. */
public class InteractionTest {

  @Test
  public void testInteractionBuilder() {
    // Arrange & Act
    Interaction interaction =
        Interaction.builder()
            .id("test-id")
            .status(InteractionStatus.COMPLETED)
            .model("gemini-2.5-flash")
            .role("user")
            .build();

    // Assert
    assertEquals("test-id", interaction.id());
    assertEquals(InteractionStatus.COMPLETED, interaction.status());
    assertTrue(interaction.model().isPresent());
    assertEquals("gemini-2.5-flash", interaction.model().get());
    assertTrue(interaction.role().isPresent());
    assertEquals("user", interaction.role().get());
  }

  @Test
  public void testInteractionWithOutputs() {
    // Arrange
    TextContent textContent = TextContent.builder().text("Paris").build();

    // Act
    Interaction interaction =
        Interaction.builder()
            .id("test-id")
            .status(InteractionStatus.COMPLETED)
            .outputs(textContent)
            .build();

    // Assert
    assertTrue(interaction.outputs().isPresent());
    assertEquals(1, interaction.outputs().get().size());
    assertTrue(interaction.outputs().get().get(0) instanceof TextContent);
  }

  @Test
  public void testInteractionWithTimestamps() {
    // Arrange
    Instant now = Instant.now();

    // Act
    Interaction interaction =
        Interaction.builder()
            .id("test-id")
            .status(InteractionStatus.COMPLETED)
            .created(now)
            .updated(now)
            .build();

    // Assert
    assertTrue(interaction.created().isPresent());
    assertEquals(now, interaction.created().get());
    assertTrue(interaction.updated().isPresent());
    assertEquals(now, interaction.updated().get());
  }

  @Test
  public void testInteractionWithUsage() {
    // Arrange
    Usage usage =
        Usage.builder()
            .totalInputTokens(10)
            .totalOutputTokens(20)
            .totalTokens(30)
            .build();

    // Act
    Interaction interaction =
        Interaction.builder()
            .id("test-id")
            .status(InteractionStatus.COMPLETED)
            .usage(usage)
            .build();

    // Assert
    assertTrue(interaction.usage().isPresent());
    assertEquals(10, interaction.usage().get().totalInputTokens().get());
    assertEquals(20, interaction.usage().get().totalOutputTokens().get());
    assertEquals(30, interaction.usage().get().totalTokens().get());
  }

  @Test
  public void testInteractionStatus() {
    // Test all status values
    assertEquals("COMPLETED", InteractionStatus.COMPLETED.toString());
    assertEquals("IN_PROGRESS", InteractionStatus.IN_PROGRESS.toString());
    assertEquals("FAILED", InteractionStatus.FAILED.toString());
  }

  @Test
  public void testTextContent() {
    // Act
    TextContent content = TextContent.builder().text("Hello, world!").build();

    // Assert
    assertTrue(content instanceof TextContent);
    assertTrue(content.text().isPresent());
    assertEquals("Hello, world!", content.text().get());
  }

  @Test
  public void testThoughtContent() {
    // Act
    ThoughtContent content = ThoughtContent.builder().signature("thought-sig-123").build();

    // Assert
    assertTrue(content instanceof ThoughtContent);
    assertTrue(content.signature().isPresent());
    assertEquals("thought-sig-123", content.signature().get());
  }

  @Test
  public void testCreateInteractionConfig() {
    // Act
    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model("gemini-2.5-flash")
            .input("What is the capital of France?")
            .build();

    // Assert
    assertTrue(config.model().isPresent());
    assertEquals("gemini-2.5-flash", config.model().get());
    assertNotNull(config.input());
  }

  @Test
  public void testCreateInteractionConfigWithAgent() {
    // Act
    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .agent("deep-research-pro-preview-12-2025")
            .input("Research quantum computing")
            .build();

    // Assert
    assertTrue(config.agent().isPresent());
    assertEquals("deep-research-pro-preview-12-2025", config.agent().get());
    assertFalse(config.model().isPresent());
    assertNotNull(config.input());
  }

  @Test
  public void testCreateInteractionConfigWithGenerationConfig() {
    // Arrange
    GenerationConfig genConfig = GenerationConfig.builder().temperature(0.7f).topP(0.9f).build();

    // Act
    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model("gemini-2.5-flash")
            .input("Test")
            .generationConfig(genConfig)
            .build();

    // Assert
    assertTrue(config.generationConfig().isPresent());
    assertEquals(0.7f, config.generationConfig().get().temperature().get());
    assertEquals(0.9f, config.generationConfig().get().topP().get());
  }

  @Test
  public void testCreateInteractionConfig_InputIsRequired() {
    // Arrange & Act
    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model("gemini-2.5-flash")
            .input("What is AI?")
            .build();

    // Assert - input() returns Input directly, not Optional<Input>
    Input input = config.input(); // No Optional unwrapping needed
    assertNotNull(input);
    assertEquals("What is AI?", input.getValue());
  }

  @Test
  public void testCreateInteractionConfig_InputFromString() {
    // Arrange & Act
    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model("gemini-2.5-flash")
            .input("Hello, world!")
            .build();

    // Assert
    assertNotNull(config.input());
    assertEquals("Hello, world!", config.input().getValue());
  }

  @Test
  public void testCreateInteractionConfig_InputFromContentsList() {
    // Arrange
    List<Content> contents =
        Arrays.asList(
            TextContent.builder().text("First part").build(),
            TextContent.builder().text("Second part").build());

    // Act
    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model("gemini-2.5-flash")
            .inputFromContents(contents)
            .build();

    // Assert
    assertNotNull(config.input());
    assertTrue(config.input().getValue() instanceof List);
    @SuppressWarnings("unchecked")
    List<Content> resultContents = (List<Content>) config.input().getValue();
    assertEquals(2, resultContents.size());
  }

  @Test
  public void testCreateInteractionConfig_InputFromContentsVarargs() {
    // Arrange
    TextContent content1 = TextContent.builder().text("Part 1").build();
    TextContent content2 = TextContent.builder().text("Part 2").build();

    // Act
    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model("gemini-2.5-flash")
            .inputFromContents(content1, content2)
            .build();

    // Assert
    assertNotNull(config.input());
    assertTrue(config.input().getValue() instanceof List);
    @SuppressWarnings("unchecked")
    List<Content> resultContents = (List<Content>) config.input().getValue();
    assertEquals(2, resultContents.size());
  }

  @Test
  public void testCreateInteractionConfig_InputFromTurnsList() {
    // Arrange
    List<Turn> turns =
        Arrays.asList(
            Turn.builder()
                .role("user")
                .content(TextContent.builder().text("Hello").build())
                .build(),
            Turn.builder()
                .role("model")
                .content(TextContent.builder().text("Hi there").build())
                .build());

    // Act
    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model("gemini-2.5-flash")
            .inputFromTurns(turns)
            .build();

    // Assert
    assertNotNull(config.input());
    assertTrue(config.input().getValue() instanceof List);
    @SuppressWarnings("unchecked")
    List<Turn> resultTurns = (List<Turn>) config.input().getValue();
    assertEquals(2, resultTurns.size());
  }

  @Test
  public void testCreateInteractionConfig_InputFromTurnsVarargs() {
    // Arrange
    Turn turn1 =
        Turn.builder()
            .role("user")
            .content(TextContent.builder().text("Question").build())
            .build();
    Turn turn2 =
        Turn.builder()
            .role("model")
            .content(TextContent.builder().text("Answer").build())
            .build();

    // Act
    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model("gemini-2.5-flash")
            .inputFromTurns(turn1, turn2)
            .build();

    // Assert
    assertNotNull(config.input());
    assertTrue(config.input().getValue() instanceof List);
    @SuppressWarnings("unchecked")
    List<Turn> resultTurns = (List<Turn>) config.input().getValue();
    assertEquals(2, resultTurns.size());
  }

  @Test
  public void testCreateInteractionConfig_InputDirectAccess() {
    // Arrange & Act
    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model("gemini-2.5-flash")
            .input("Direct access test")
            .build();

    // Assert - No Optional unwrapping needed
    // OLD way (when it was Optional<Input>): config.input().get().getValue()
    // NEW way (required Input): config.input().getValue()
    String inputValue = (String) config.input().getValue();
    assertEquals("Direct access test", inputValue);
  }

  @Test
  public void testCreateInteractionConfig_InputWithAgent() {
    // Arrange & Act
    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .agent("deep-research-pro-preview-12-2025")
            .input("Research quantum computing")
            .build();

    // Assert
    assertNotNull(config.input());
    assertEquals("Research quantum computing", config.input().getValue());
    assertTrue(config.agent().isPresent());
    assertFalse(config.model().isPresent());
  }

  @Test
  public void testGetInteractionConfig() {
    // Act
    GetInteractionConfig config = GetInteractionConfig.builder().build();

    // Assert
    assertNotNull(config);
  }

  @Test
  public void testCancelInteractionConfig() {
    // Act
    CancelInteractionConfig config = CancelInteractionConfig.builder().build();

    // Assert
    assertNotNull(config);
  }

  @Test
  public void testDeleteInteractionConfig() {
    // Act
    DeleteInteractionConfig config = DeleteInteractionConfig.builder().build();

    // Assert
    assertNotNull(config);
  }

  @Test
  public void testDeleteInteraction() {
    // Act
    DeleteInteractionResponse response = DeleteInteractionResponse.builder().build();

    // Assert
    assertNotNull(response);
  }

  @Test
  public void testInteractionToBuilder() {
    // Arrange
    Interaction original =
        Interaction.builder()
            .id("test-id")
            .status(InteractionStatus.COMPLETED)
            .model("gemini-2.5-flash")
            .build();

    // Act
    Interaction modified = original.toBuilder().role("assistant").build();

    // Assert
    assertEquals("test-id", modified.id());
    assertEquals(InteractionStatus.COMPLETED, modified.status());
    assertEquals("gemini-2.5-flash", modified.model().get());
    assertTrue(modified.role().isPresent());
    assertEquals("assistant", modified.role().get());
  }

  @Test
  public void testInteractionClearMethods() {
    // Arrange
    Interaction.Builder builder =
        Interaction.builder().id("test-id").status(InteractionStatus.COMPLETED).model("test-model");

    // Act - Note: id() and status() are now required fields, so clearId() and clearStatus() no longer exist
    Interaction interaction = builder.clearModel().build();

    // Assert
    assertEquals("test-id", interaction.id());
    assertEquals(InteractionStatus.COMPLETED, interaction.status());
    assertFalse(interaction.model().isPresent());
  }

  @Test
  public void testInteractionInputSerializationWithMultipleContents() {
    // Arrange - create multiple TextContent objects
    TextContent content1 = TextContent.builder().text("Hello").build();
    TextContent content2 = TextContent.builder().text("World").build();
    Input input = Input.fromContents(content1, content2);

    // Act - serialize to JSON
    String json = input.toJson();

    // Assert - verify the "type" property is included
    assertTrue(json.contains("\"type\":\"text\""), "JSON should contain type discriminator");
    assertTrue(json.contains("\"text\":\"Hello\""), "JSON should contain first text");
    assertTrue(json.contains("\"text\":\"World\""), "JSON should contain second text");
    // Expected format: [{"type":"text","text":"Hello"},{"type":"text","text":"World"}]
    assertTrue(json.startsWith("["), "JSON should be an array");
    assertTrue(json.endsWith("]"), "JSON should end with array bracket");
  }

  @Test
  public void testInteractionInputSerializationWithSingleContent() {
    // Arrange - create a single TextContent object
    TextContent content = TextContent.builder().text("Hello").build();
    Input input = Input.fromContent(content);

    // Act - serialize to JSON
    String json = input.toJson();

    // Assert - verify the "type" property is included
    assertTrue(json.contains("\"type\":\"text\""), "JSON should contain type discriminator");
    assertTrue(json.contains("\"text\":\"Hello\""), "JSON should contain text");
    // Expected format: {"type":"text","text":"Hello"}
    assertTrue(json.startsWith("{"), "JSON should be an object");
    assertTrue(json.endsWith("}"), "JSON should end with object bracket");
  }

  @Test
  public void testInteractionInputSerializationWithString() {
    // Arrange
    Input input = Input.fromString("Hello, world!");

    // Act - serialize to JSON
    String json = input.toJson();

    // Assert
    assertEquals("\"Hello, world!\"", json);
  }
}
