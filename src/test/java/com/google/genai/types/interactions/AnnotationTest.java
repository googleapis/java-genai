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

import org.junit.jupiter.api.Test;

/** Tests for Annotation type. */
public class AnnotationTest {

  @Test
  public void testAnnotationBuilder() {
    // Arrange & Act
    Annotation annotation =
        Annotation.builder()
            .startIndex(0)
            .endIndex(25)
            .source("https://example.com/source1")
            .build();

    // Assert
    assertTrue(annotation.startIndex().isPresent());
    assertEquals(0, annotation.startIndex().get());
    assertTrue(annotation.endIndex().isPresent());
    assertEquals(25, annotation.endIndex().get());
    assertTrue(annotation.source().isPresent());
    assertEquals("https://example.com/source1", annotation.source().get());
  }

  @Test
  public void testAnnotationOfFactoryMethod() {
    // Arrange & Act
    Annotation annotation = Annotation.of(10, 50, "Wikipedia: AI Article");

    // Assert
    assertTrue(annotation.startIndex().isPresent());
    assertEquals(10, annotation.startIndex().get());
    assertTrue(annotation.endIndex().isPresent());
    assertEquals(50, annotation.endIndex().get());
    assertTrue(annotation.source().isPresent());
    assertEquals("Wikipedia: AI Article", annotation.source().get());
  }

  @Test
  public void testAnnotationWithOptionalFields() {
    // Arrange & Act - Build annotation with only source
    Annotation annotation = Annotation.builder().source("https://source.com").build();

    // Assert
    assertFalse(annotation.startIndex().isPresent());
    assertFalse(annotation.endIndex().isPresent());
    assertTrue(annotation.source().isPresent());
    assertEquals("https://source.com", annotation.source().get());
  }

  @Test
  public void testAnnotationClearMethods() {
    // Arrange
    Annotation.Builder builder =
        Annotation.builder()
            .startIndex(0)
            .endIndex(10)
            .source("https://example.com");

    // Act - Clear fields
    builder.clearStartIndex();
    builder.clearEndIndex();
    builder.clearSource();
    Annotation annotation = builder.build();

    // Assert
    assertFalse(annotation.startIndex().isPresent());
    assertFalse(annotation.endIndex().isPresent());
    assertFalse(annotation.source().isPresent());
  }

  @Test
  public void testAnnotationJsonSerialization() {
    // Arrange
    Annotation annotation = Annotation.of(0, 25, "https://example.com/source1");

    // Act
    String json = annotation.toJson();

    // Assert
    assertNotNull(json);
    assertTrue(json.contains("\"start_index\":0"));
    assertTrue(json.contains("\"end_index\":25"));
    assertTrue(json.contains("\"source\":\"https://example.com/source1\""));
  }

  @Test
  public void testAnnotationJsonDeserialization() {
    // Arrange
    String json =
        "{\"start_index\":15,\"end_index\":30,\"source\":\"https://example.com/citation\"}";

    // Act
    Annotation annotation = Annotation.fromJson(json);

    // Assert
    assertTrue(annotation.startIndex().isPresent());
    assertEquals(15, annotation.startIndex().get());
    assertTrue(annotation.endIndex().isPresent());
    assertEquals(30, annotation.endIndex().get());
    assertTrue(annotation.source().isPresent());
    assertEquals("https://example.com/citation", annotation.source().get());
  }

  @Test
  public void testAnnotationRoundTripSerialization() {
    // Arrange
    Annotation original = Annotation.of(5, 20, "https://source.org");

    // Act - Serialize and deserialize
    String json = original.toJson();
    Annotation deserialized = Annotation.fromJson(json);

    // Assert - Fields match
    assertEquals(original.startIndex(), deserialized.startIndex());
    assertEquals(original.endIndex(), deserialized.endIndex());
    assertEquals(original.source(), deserialized.source());
  }

  @Test
  public void testAnnotationToBuilder() {
    // Arrange
    Annotation original = Annotation.of(10, 50, "https://example.com");

    // Act - Modify via toBuilder
    Annotation modified = original.toBuilder().source("https://new-source.com").build();

    // Assert - Original unchanged, modified has new source
    assertEquals("https://example.com", original.source().get());
    assertEquals("https://new-source.com", modified.source().get());
    assertEquals(original.startIndex(), modified.startIndex());
    assertEquals(original.endIndex(), modified.endIndex());
  }
}
