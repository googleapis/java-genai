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
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.genai.types.interactions.content.ImageContent;
import com.google.genai.types.interactions.content.TextContent;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

/**
 * Tests for InputSerializer to ensure proper JSON serialization of Input union types.
 *
 * <p>InputSerializer handles three different input types:
 *
 * <ul>
 *   <li>String input - serialized as a JSON string
 *   <li>Single or list of Content objects - serialized with type discriminators
 *   <li>List of Turn objects - serialized with proper polymorphic handling
 * </ul>
 */
public class InputSerializerTest {

  // ========== String Input Tests ==========

  @Test
  public void testSerializeStringInput() {
    // Arrange
    Input input = Input.fromString("What is the capital of France?");

    // Act
    String json = input.toJson();

    // Assert - should serialize as a plain JSON string
    assertEquals("\"What is the capital of France?\"", json);
  }

  @Test
  public void testSerializeStringInputWithSpecialCharacters() {
    // Arrange
    Input input = Input.fromString("Hello \"world\" with\nnewlines and\ttabs");

    // Act
    String json = input.toJson();

    // Assert - should properly escape special characters
    assertTrue(json.contains("\\n"));
    assertTrue(json.contains("\\t"));
    assertTrue(json.contains("\\\""));
  }

  @Test
  public void testSerializeEmptyString() {
    // Arrange
    Input input = Input.fromString("");

    // Act
    String json = input.toJson();

    // Assert
    assertEquals("\"\"", json);
  }

  // ========== Single Content Input Tests ==========

  @Test
  public void testSerializeSingleTextContent() {
    // Arrange
    TextContent text = TextContent.of("This is a text message");
    Input input = Input.fromContent(text);

    // Act
    String json = input.toJson();

    // Assert - should include type discriminator
    assertTrue(json.contains("\"type\":\"text\""));
    assertTrue(json.contains("\"text\":\"This is a text message\""));
  }

  @Test
  public void testSerializeSingleImageContent() {
    // Arrange
    ImageContent image = ImageContent.fromUri("gs://bucket/image.png", "image/png");
    Input input = Input.fromContent(image);

    // Act
    String json = input.toJson();

    // Assert - should include type discriminator
    assertTrue(json.contains("\"type\":\"image\""));
    assertTrue(json.contains("\"uri\":\"gs://bucket/image.png\""));
    assertTrue(json.contains("\"mime_type\":\"image/png\""));
  }

  // ========== List of Content Input Tests ==========

  @Test
  public void testSerializeContentListFromList() {
    // Arrange
    TextContent text1 = TextContent.of("First message");
    TextContent text2 = TextContent.of("Second message");
    Input input = Input.fromContents(Arrays.asList(text1, text2));

    // Act
    String json = input.toJson();

    // Assert - should serialize as a JSON array with type discriminators
    assertTrue(json.startsWith("["));
    assertTrue(json.endsWith("]"));
    assertTrue(json.contains("\"type\":\"text\""));
    assertTrue(json.contains("\"text\":\"First message\""));
    assertTrue(json.contains("\"text\":\"Second message\""));
  }

  @Test
  public void testSerializeContentListFromVarargs() {
    // Arrange
    TextContent text = TextContent.of("Text message");
    ImageContent image = ImageContent.fromUri("gs://bucket/img.jpg", "image/jpeg");
    Input input = Input.fromContents(text, image);

    // Act
    String json = input.toJson();

    // Assert - should serialize mixed content types with their discriminators
    assertTrue(json.startsWith("["));
    assertTrue(json.contains("\"type\":\"text\""));
    assertTrue(json.contains("\"type\":\"image\""));
    assertTrue(json.contains("\"text\":\"Text message\""));
    assertTrue(json.contains("\"uri\":\"gs://bucket/img.jpg\""));
  }

  @Test
  public void testSerializeEmptyContentList() {
    // Arrange
    Input input = Input.fromContents(Arrays.asList());

    // Act
    String json = input.toJson();

    // Assert - should serialize as empty array
    assertEquals("[]", json);
  }

  // ========== Turn Input Tests ==========

  @Test
  public void testSerializeTurnListFromList() {
    // Arrange
    Turn userTurn = Turn.builder()
        .role("user")
        .content(TextContent.of("What is AI?"))
        .build();
    Turn modelTurn = Turn.builder()
        .role("model")
        .content(TextContent.of("AI stands for Artificial Intelligence."))
        .build();
    Input input = Input.fromTurns(Arrays.asList(userTurn, modelTurn));

    // Act
    String json = input.toJson();

    // Assert - should serialize as array with proper Turn structure
    assertTrue(json.startsWith("["));
    assertTrue(json.contains("\"role\":\"user\""));
    assertTrue(json.contains("\"role\":\"model\""));
    assertTrue(json.contains("\"What is AI?\""));
    assertTrue(json.contains("\"AI stands for Artificial Intelligence.\""));
  }

  @Test
  public void testSerializeTurnListFromVarargs() {
    // Arrange
    Turn turn1 = Turn.builder()
        .role("user")
        .content(TextContent.of("First turn"))
        .build();
    Turn turn2 = Turn.builder()
        .role("model")
        .content(TextContent.of("Second turn"))
        .build();
    Input input = Input.fromTurns(turn1, turn2);

    // Act
    String json = input.toJson();

    // Assert
    assertTrue(json.startsWith("["));
    assertTrue(json.contains("\"First turn\""));
    assertTrue(json.contains("\"Second turn\""));
  }

  @Test
  public void testSerializeTurnWithMultipleContent() {
    // Arrange
    Turn turn = Turn.builder()
        .role("user")
        .content(
            TextContent.of("Check this image"),
            ImageContent.fromUri("gs://bucket/photo.png", "image/png"))
        .build();
    Input input = Input.fromTurns(turn);

    // Act
    String json = input.toJson();

    // Assert - should serialize turn with multiple content items
    assertTrue(json.contains("\"type\":\"text\""));
    assertTrue(json.contains("\"type\":\"image\""));
    assertTrue(json.contains("\"Check this image\""));
    assertTrue(json.contains("\"gs://bucket/photo.png\""));
  }

  @Test
  public void testSerializeEmptyTurnList() {
    // Arrange
    Input input = Input.fromTurns(Arrays.asList());

    // Act
    String json = input.toJson();

    // Assert - should serialize as empty array
    assertEquals("[]", json);
  }

  // ========== Turn Factory Methods Tests ==========

  @Test
  public void testSerializeTurnUsingFactoryMethods() {
    // Arrange - Use Turn.user() and Turn.model() factory methods
    Input input = Input.fromTurns(
        Turn.user("Hello!"),
        Turn.model("Hi there! How can I help?"),
        Turn.user("What is AI?")
    );

    // Act
    String json = input.toJson();

    // Assert - should serialize as array with proper Turn structure
    assertTrue(json.startsWith("["));
    assertTrue(json.contains("\"role\":\"user\""));
    assertTrue(json.contains("\"role\":\"model\""));
    assertTrue(json.contains("\"Hello!\""));
    assertTrue(json.contains("\"Hi there! How can I help?\""));
    assertTrue(json.contains("\"What is AI?\""));
  }

  @Test
  public void testSerializeTurnUserFactoryMethod() {
    // Arrange
    Turn turn = Turn.user("User message");
    Input input = Input.fromTurns(turn);

    // Act
    String json = input.toJson();

    // Assert
    assertTrue(json.contains("\"role\":\"user\""));
    assertTrue(json.contains("\"type\":\"text\""));
    assertTrue(json.contains("\"User message\""));
  }

  @Test
  public void testSerializeTurnModelFactoryMethod() {
    // Arrange
    Turn turn = Turn.model("Model response");
    Input input = Input.fromTurns(turn);

    // Act
    String json = input.toJson();

    // Assert
    assertTrue(json.contains("\"role\":\"model\""));
    assertTrue(json.contains("\"type\":\"text\""));
    assertTrue(json.contains("\"Model response\""));
  }

  // ========== Round-trip Serialization Tests ==========

  @Test
  public void testRoundTripStringInput() {
    // Arrange
    String originalText = "Hello, world!";
    Input input = Input.fromString(originalText);

    // Act
    String json = input.toJson();

    // Assert - verify it's a valid JSON string
    assertTrue(json.startsWith("\""));
    assertTrue(json.endsWith("\""));
    assertTrue(json.contains("Hello, world!"));
  }

  @Test
  public void testRoundTripContentListInput() {
    // Arrange
    TextContent text1 = TextContent.of("Message 1");
    TextContent text2 = TextContent.of("Message 2");
    Input input = Input.fromContents(text1, text2);

    // Act
    String json = input.toJson();

    // Assert - verify array structure with type info
    assertTrue(json.startsWith("["));
    assertTrue(json.endsWith("]"));
    // Count occurrences of "type":"text" (should be 2)
    int typeCount = json.split("\"type\":\"text\"", -1).length - 1;
    assertEquals(2, typeCount);
  }

  @Test
  public void testRoundTripTurnListInput() {
    // Arrange
    Turn turn = Turn.builder()
        .role("user")
        .content(TextContent.of("Test message"))
        .build();
    Input input = Input.fromTurns(turn);

    // Act
    String json = input.toJson();

    // Assert - verify turn serialization preserves structure
    assertTrue(json.contains("\"role\""));
    assertTrue(json.contains("\"content\""));
    assertTrue(json.contains("\"type\":\"text\""));
  }

  // ========== Edge Cases ==========

  @Test
  public void testSerializeSingleContentInList() {
    // Arrange - single content in a list
    TextContent text = TextContent.of("Single item");
    Input input = Input.fromContents(Arrays.asList(text));

    // Act
    String json = input.toJson();

    // Assert - should still serialize as array (not unwrapped)
    assertTrue(json.startsWith("["));
    assertTrue(json.endsWith("]"));
    assertTrue(json.contains("\"type\":\"text\""));
  }

  @Test
  public void testSerializeComplexContent() {
    // Arrange - content with annotations
    Annotation annotation = Annotation.of(0, 10, "https://example.com");
    TextContent text = TextContent.builder()
        .text("Source text")
        .annotations(annotation)
        .build();
    Input input = Input.fromContent(text);

    // Act
    String json = input.toJson();

    // Assert - should include all nested fields
    assertTrue(json.contains("\"type\":\"text\""));
    assertTrue(json.contains("\"text\":\"Source text\""));
    assertTrue(json.contains("\"annotations\""));
    assertTrue(json.contains("\"source\":\"https://example.com\""));
  }

  @Test
  public void testSerializeMixedContentTypes() {
    // Arrange - various content types in one list
    TextContent text = TextContent.of("Description");
    ImageContent image = ImageContent.fromData("base64data", "image/png");
    Input input = Input.fromContents(text, image);

    // Act
    String json = input.toJson();

    // Assert - each content type should have correct discriminator
    assertTrue(json.contains("\"type\":\"text\""));
    assertTrue(json.contains("\"type\":\"image\""));
    assertTrue(json.contains("\"data\":\"base64data\""));
  }
}
