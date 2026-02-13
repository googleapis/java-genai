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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.genai.types.interactions.CancelInteractionConfig;
import com.google.genai.types.interactions.CreateInteractionConfig;
import com.google.genai.types.interactions.DeleteInteractionConfig;
import com.google.genai.types.interactions.GetInteractionConfig;
import com.google.genai.types.interactions.Interaction;
import com.google.genai.types.interactions.InteractionStatus;
import org.junit.jupiter.api.Test;

/**
 * API-level tests for Interactions resource.
 *
 * <p>These tests cover the request building and parameter validation logic without making real API
 * calls.
 */
public class InteractionsApiTest {

  @Test
  public void testCreateWithNullConfig() {
    // Arrange
    Client client = Client.builder().apiKey("test-key").build();

    // Act & Assert
    assertThrows(NullPointerException.class, () -> client.interactions.create(null));
  }

  @Test
  public void testGetWithNullId() {
    // Arrange
    Client client = Client.builder().apiKey("test-key").build();

    // Act & Assert
    assertThrows(
        IllegalArgumentException.class, () -> client.interactions.get(null, GetInteractionConfig.builder().build()));
  }

  @Test
  public void testGetWithEmptyId() {
    // Arrange
    Client client = Client.builder().apiKey("test-key").build();

    // Act & Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> client.interactions.get("", GetInteractionConfig.builder().build()));
  }

  @Test
  public void testCancelWithNullId() {
    // Arrange
    Client client = Client.builder().apiKey("test-key").build();

    // Act & Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> client.interactions.cancel(null, CancelInteractionConfig.builder().build()));
  }

  @Test
  public void testCancelWithEmptyId() {
    // Arrange
    Client client = Client.builder().apiKey("test-key").build();

    // Act & Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> client.interactions.cancel("", CancelInteractionConfig.builder().build()));
  }

  @Test
  public void testDeleteWithNullId() {
    // Arrange
    Client client = Client.builder().apiKey("test-key").build();

    // Act & Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> client.interactions.delete(null, DeleteInteractionConfig.builder().build()));
  }

  @Test
  public void testDeleteWithEmptyId() {
    // Arrange
    Client client = Client.builder().apiKey("test-key").build();

    // Act & Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> client.interactions.delete("", DeleteInteractionConfig.builder().build()));
  }

  @Test
  public void testCreateConfigBuilderDefaults() {
    // Act
    CreateInteractionConfig config =
        CreateInteractionConfig.builder().model("gemini-2.5-flash").input("test").build();

    // Assert
    assertTrue(config.model().isPresent());
    assertNotNull(config.input());
  }

  @Test
  public void testGetConfigBuilder() {
    // Act
    GetInteractionConfig config = GetInteractionConfig.builder().build();

    // Assert
    assertNotNull(config);
  }

  @Test
  public void testCancelConfigBuilder() {
    // Act
    CancelInteractionConfig config = CancelInteractionConfig.builder().build();

    // Assert
    assertNotNull(config);
  }

  @Test
  public void testDeleteConfigBuilder() {
    // Act
    DeleteInteractionConfig config = DeleteInteractionConfig.builder().build();

    // Assert
    assertNotNull(config);
  }

  // Note: JSON deserialization tests are complex due to Jackson configuration
  // and are better tested through integration tests

  @Test
  public void testInteractionSerialization() {
    // Arrange
    Interaction interaction =
        Interaction.builder()
            .id("test-id")
            .status(new InteractionStatus(InteractionStatus.Known.IN_PROGRESS))
            .model("gemini-2.5-flash")
            .build();

    // Act
    String json = interaction.toJson();

    // Assert - Just verify JSON is generated
    assertNotNull(json);
    assertTrue(json.length() > 0);
  }

  @Test
  public void testCreateConfigSerialization() {
    // Arrange
    CreateInteractionConfig config =
        CreateInteractionConfig.builder().model("gemini-2.5-flash").input("What is AI?").build();

    // Act
    String json = config.toJson();

    // Assert - Just verify JSON is generated
    assertNotNull(json);
    assertTrue(json.length() > 0);
  }

  @Test
  public void testInteractionWithPreviousId() {
    // Act
    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model("gemini-2.5-flash")
            .input("Follow up question")
            .previousInteractionId("previous-id-123")
            .build();

    // Assert
    assertTrue(config.previousInteractionId().isPresent());
    assertEquals("previous-id-123", config.previousInteractionId().get());
  }

  @Test
  public void testInteractionClearPreviousId() {
    // Arrange
    CreateInteractionConfig.Builder builder =
        CreateInteractionConfig.builder()
            .model("gemini-2.5-flash")
            .input("test")
            .previousInteractionId("id-123");

    // Act
    CreateInteractionConfig config = builder.clearPreviousInteractionId().build();

    // Assert
    assertTrue(!config.previousInteractionId().isPresent());
  }

  @Test
  public void testInteractionStatusValues() {
    // Test that all status values are accessible
    assertNotNull(new InteractionStatus(InteractionStatus.Known.COMPLETED));
    assertNotNull(new InteractionStatus(InteractionStatus.Known.IN_PROGRESS));
    assertNotNull(new InteractionStatus(InteractionStatus.Known.FAILED));
  }
}
