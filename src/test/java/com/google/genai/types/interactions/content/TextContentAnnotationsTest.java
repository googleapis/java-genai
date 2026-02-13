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

import com.google.genai.types.interactions.Annotation;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

/** Tests for TextContent with annotations field. */
public class TextContentAnnotationsTest {

  @Test
  public void testTextContentWithoutAnnotations() {
    // Arrange & Act
    TextContent textContent = TextContent.of("This is a simple text without citations.");

    // Assert
    assertTrue(textContent.text().isPresent());
    assertEquals("This is a simple text without citations.", textContent.text().get());
    assertFalse(textContent.annotations().isPresent());
  }

  @Test
  public void testTextContentWithAnnotationsList() {
    // Arrange
    Annotation annotation1 =
        Annotation.builder()
            .startIndex(0)
            .endIndex(25)
            .source("https://example.com/source1")
            .build();

    Annotation annotation2 =
        Annotation.builder()
            .startIndex(26)
            .endIndex(50)
            .source("Wikipedia: AI Article")
            .build();

    List<Annotation> annotations = Arrays.asList(annotation1, annotation2);

    // Act
    TextContent textContent =
        TextContent.builder()
            .text("This text has citations from multiple sources for attribution.")
            .annotations(annotations)
            .build();

    // Assert
    assertTrue(textContent.text().isPresent());
    assertTrue(textContent.annotations().isPresent());
    assertEquals(2, textContent.annotations().get().size());
    assertEquals("https://example.com/source1", textContent.annotations().get().get(0).source().get());
    assertEquals("Wikipedia: AI Article", textContent.annotations().get().get(1).source().get());
  }

  @Test
  public void testTextContentWithAnnotationsVarargs() {
    // Arrange
    Annotation annotation1 = Annotation.of(0, 15, "https://source1.com");
    Annotation annotation2 = Annotation.of(16, 30, "https://source2.com");

    // Act
    TextContent textContent =
        TextContent.builder()
            .text("Annotated content using varargs syntax.")
            .annotations(annotation1, annotation2)
            .build();

    // Assert
    assertTrue(textContent.annotations().isPresent());
    assertEquals(2, textContent.annotations().get().size());
    assertEquals("https://source1.com", textContent.annotations().get().get(0).source().get());
    assertEquals("https://source2.com", textContent.annotations().get().get(1).source().get());
  }

  @Test
  public void testTextContentClearAnnotations() {
    // Arrange
    TextContent.Builder builder =
        TextContent.builder()
            .text("Text with annotations")
            .annotations(Annotation.of(0, 5, "https://example.com"));

    // Act
    builder.clearAnnotations();
    TextContent textContent = builder.build();

    // Assert
    assertFalse(textContent.annotations().isPresent());
  }

  @Test
  public void testTextContentJsonSerializationWithoutAnnotations() {
    // Arrange
    TextContent textContent = TextContent.of("This is a simple text without citations.");

    // Act
    String json = textContent.toJson();

    // Assert
    assertNotNull(json);
    assertTrue(json.contains("\"type\":\"text\""));
    assertTrue(json.contains("\"text\":\"This is a simple text without citations.\""));
    assertFalse(json.contains("\"annotations\""));
  }

  @Test
  public void testTextContentJsonSerializationWithAnnotations() {
    // Arrange
    Annotation annotation1 = Annotation.of(0, 25, "https://example.com/source1");
    Annotation annotation2 = Annotation.of(26, 50, "Wikipedia: AI Article");

    TextContent textContent =
        TextContent.builder()
            .text("This text has citations from multiple sources for attribution.")
            .annotations(Arrays.asList(annotation1, annotation2))
            .build();

    // Act
    String json = textContent.toJson();

    // Assert
    assertNotNull(json);
    assertTrue(json.contains("\"type\":\"text\""));
    assertTrue(json.contains("\"text\":\"This text has citations"));
    assertTrue(json.contains("\"annotations\""));
    assertTrue(json.contains("\"start_index\":0"));
    assertTrue(json.contains("\"end_index\":25"));
    assertTrue(json.contains("\"source\":\"https://example.com/source1\""));
    assertTrue(json.contains("\"source\":\"Wikipedia: AI Article\""));
  }

  @Test
  public void testTextContentJsonDeserializationWithAnnotations() {
    // Arrange
    String json =
        "{\"type\":\"text\",\"text\":\"Sample text with citations.\","
            + "\"annotations\":["
            + "{\"start_index\":0,\"end_index\":15,\"source\":\"https://source1.com\"},"
            + "{\"start_index\":16,\"end_index\":30,\"source\":\"https://source2.com\"}"
            + "]}";

    // Act
    TextContent textContent = TextContent.fromJson(json);

    // Assert
    assertTrue(textContent.text().isPresent());
    assertEquals("Sample text with citations.", textContent.text().get());
    assertTrue(textContent.annotations().isPresent());
    assertEquals(2, textContent.annotations().get().size());

    Annotation firstAnnotation = textContent.annotations().get().get(0);
    assertEquals(0, firstAnnotation.startIndex().get());
    assertEquals(15, firstAnnotation.endIndex().get());
    assertEquals("https://source1.com", firstAnnotation.source().get());

    Annotation secondAnnotation = textContent.annotations().get().get(1);
    assertEquals(16, secondAnnotation.startIndex().get());
    assertEquals(30, secondAnnotation.endIndex().get());
    assertEquals("https://source2.com", secondAnnotation.source().get());
  }

  @Test
  public void testTextContentRoundTripSerializationWithAnnotations() {
    // Arrange
    Annotation annotation1 = Annotation.of(0, 20, "https://example.org");
    Annotation annotation2 = Annotation.of(21, 40, "Research Paper: AI");

    TextContent original =
        TextContent.builder()
            .text("Text with multiple citation sources.")
            .annotations(annotation1, annotation2)
            .build();

    // Act - Serialize and deserialize
    String json = original.toJson();
    TextContent deserialized = TextContent.fromJson(json);

    // Assert - Fields match
    assertEquals(original.text(), deserialized.text());
    assertTrue(deserialized.annotations().isPresent());
    assertEquals(2, deserialized.annotations().get().size());

    // Verify annotations content
    List<Annotation> originalAnnotations = original.annotations().get();
    List<Annotation> deserializedAnnotations = deserialized.annotations().get();

    for (int i = 0; i < originalAnnotations.size(); i++) {
      assertEquals(
          originalAnnotations.get(i).startIndex(), deserializedAnnotations.get(i).startIndex());
      assertEquals(
          originalAnnotations.get(i).endIndex(), deserializedAnnotations.get(i).endIndex());
      assertEquals(originalAnnotations.get(i).source(), deserializedAnnotations.get(i).source());
    }
  }

  @Test
  public void testTextContentToBuilder() {
    // Arrange
    TextContent original =
        TextContent.builder()
            .text("Original text")
            .annotations(Annotation.of(0, 10, "https://source.com"))
            .build();

    // Act - Modify via toBuilder
    TextContent modified =
        original.toBuilder()
            .text("Modified text")
            .annotations(Annotation.of(0, 15, "https://new-source.com"))
            .build();

    // Assert - Original unchanged
    assertEquals("Original text", original.text().get());
    assertEquals("https://source.com", original.annotations().get().get(0).source().get());

    // Modified has new values
    assertEquals("Modified text", modified.text().get());
    assertEquals("https://new-source.com", modified.annotations().get().get(0).source().get());
  }

  @Test
  public void testTextContentWithEmptyAnnotationsList() {
    // Arrange & Act
    TextContent textContent =
        TextContent.builder()
            .text("Text with empty annotations list")
            .annotations(Arrays.asList())
            .build();

    // Assert
    assertTrue(textContent.annotations().isPresent());
    assertTrue(textContent.annotations().get().isEmpty());
  }

  @Test
  public void testTextContentAnnotationsMatchOpenApiSpec() {
    // This test verifies the structure matches the OpenAPI specification
    // OpenAPI spec defines: TextContent { text: string (optional), annotations: array of Annotation (optional) }

    // Arrange
    Annotation annotation = Annotation.of(0, 10, "https://example.com");

    // Act
    TextContent textContent =
        TextContent.builder()
            .text("Sample text")
            .annotations(annotation)
            .build();

    String json = textContent.toJson();

    // Assert - Verify JSON structure matches OpenAPI spec
    assertTrue(json.contains("\"type\":\"text\""));
    assertTrue(json.contains("\"text\":"));
    assertTrue(json.contains("\"annotations\":"));
    assertTrue(json.contains("\"start_index\":"));
    assertTrue(json.contains("\"end_index\":"));
    assertTrue(json.contains("\"source\":"));
  }
}
