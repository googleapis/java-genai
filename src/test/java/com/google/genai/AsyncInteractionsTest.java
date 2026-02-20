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

import com.google.genai.types.interactions.CreateInteractionConfig;
import com.google.genai.types.interactions.GenerationConfig;
import com.google.genai.types.interactions.Input;
import com.google.genai.types.interactions.Turn;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Test;

/** Tests for the AsyncInteractions resource. */
public class AsyncInteractionsTest {

  @Test
  public void testAsyncValidationBothModelAndAgent() throws ExecutionException, InterruptedException {
    // Arrange
    Client client = Client.builder().apiKey("test-key").build();
    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model("gemini-2.5-flash")
            .agent("deep-research-pro-preview-12-2025")
            .input("Test input")
            .build();

    // Act & Assert
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class, () -> client.async.interactions.create(config).get());

    assertNotNull(exception.getMessage());
    assertTrue(exception.getMessage().contains("Cannot specify both 'model' and 'agent'"));
  }

  @Test
  public void testAsyncValidationNeitherModelNorAgent()
      throws ExecutionException, InterruptedException {
    // Arrange
    Client client = Client.builder().apiKey("test-key").build();
    CreateInteractionConfig config =
        CreateInteractionConfig.builder().input("Test input").build();

    // Act & Assert
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class, () -> client.async.interactions.create(config).get());

    assertNotNull(exception.getMessage());
    assertTrue(exception.getMessage().contains("Must specify either 'model' or 'agent'"));
  }

  @Test
  public void testAsyncValidationAgentWithGenerationConfig()
      throws ExecutionException, InterruptedException {
    // Arrange
    Client client = Client.builder().apiKey("test-key").build();
    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .agent("deep-research-pro-preview-12-2025")
            .input("Test input")
            .generationConfig(
                GenerationConfig.builder().temperature(0.5f).build())
            .build();

    // Act & Assert
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class, () -> client.async.interactions.create(config).get());

    assertNotNull(exception.getMessage());
    assertTrue(exception.getMessage().contains("Cannot use 'generationConfig' with agent-based"));
  }

  @Test
  public void testAsyncValidationModelWithAgentConfig()
      throws ExecutionException, InterruptedException {
    // Arrange
    Client client = Client.builder().apiKey("test-key").build();
    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model("gemini-2.5-flash")
            .input("Test input")
            .agentConfig(com.google.genai.types.interactions.DynamicAgentConfig.create())
            .build();

    // Act & Assert
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class, () -> client.async.interactions.create(config).get());

    assertNotNull(exception.getMessage());
    assertTrue(exception.getMessage().contains("Cannot use 'agentConfig' with model-based"));
  }

  // Note: Vertex AI support was added in PR3.
  // The testAsyncVertexAIUnsupported test has been removed since Vertex AI is now supported.
  // Integration tests for Vertex AI are in examples/VertexAIInteractionTest.java

  // ===== Convenience Overload Tests =====

  @Test
  public void testCreateWithModelAndTextOverload() {
    // Arrange
    Client client = Client.builder().apiKey("test-key").build();

    // Act - Verify the overload method exists and returns a CompletableFuture
    CompletableFuture<?> future = client.async.interactions.create("gemini-2.5-flash", "Hello");

    // Assert - The future should be created (will fail on actual API call, but method exists)
    assertNotNull(future);
  }

  @Test
  public void testCreateWithModelAndInputOverload() {
    // Arrange
    Client client = Client.builder().apiKey("test-key").build();
    Input input = Input.fromTurns(
        Turn.user("Hello"),
        Turn.model("Hi there!"),
        Turn.user("How are you?"));

    // Act - Verify the overload method exists and returns a CompletableFuture
    CompletableFuture<?> future = client.async.interactions.create("gemini-2.5-flash", input);

    // Assert - The future should be created
    assertNotNull(future);
  }

  @Test
  public void testCreateStreamWithModelAndTextOverload() {
    // Arrange
    Client client = Client.builder().apiKey("test-key").build();

    // Act - Verify the overload method exists and returns a CompletableFuture
    CompletableFuture<?> future =
        client.async.interactions.createStream("gemini-2.5-flash", "Tell me a story");

    // Assert - The future should be created
    assertNotNull(future);
  }

  @Test
  public void testCreateStreamWithModelAndInputOverload() {
    // Arrange
    Client client = Client.builder().apiKey("test-key").build();
    Input input = Input.fromTurns(
        Turn.user("Start a story"),
        Turn.model("Once upon a time..."),
        Turn.user("Continue the story"));

    // Act - Verify the overload method exists and returns a CompletableFuture
    CompletableFuture<?> future =
        client.async.interactions.createStream("gemini-2.5-flash", input);

    // Assert - The future should be created
    assertNotNull(future);
  }

  @Test
  public void testCreateOverloadDelegatesToConfigMethod() {
    // Arrange
    Client client = Client.builder().apiKey("test-key").build();
    String model = "gemini-2.5-flash";
    String text = "Test prompt";

    // Act - Call the overload
    CompletableFuture<?> overloadFuture = client.async.interactions.create(model, text);

    // Act - Call the config-based method with equivalent config
    CreateInteractionConfig config =
        CreateInteractionConfig.builder().model(model).input(text).build();
    CompletableFuture<?> configFuture = client.async.interactions.create(config);

    // Assert - Both should return non-null futures (actual behavior is the same internally)
    assertNotNull(overloadFuture);
    assertNotNull(configFuture);
  }

  @Test
  public void testCreateStreamOverloadDelegatesToConfigMethod() {
    // Arrange
    Client client = Client.builder().apiKey("test-key").build();
    String model = "gemini-2.5-flash";
    String text = "Stream test";

    // Act - Call the overload
    CompletableFuture<?> overloadFuture = client.async.interactions.createStream(model, text);

    // Act - Call the config-based method with equivalent config
    CreateInteractionConfig config =
        CreateInteractionConfig.builder().model(model).input(text).build();
    CompletableFuture<?> configFuture = client.async.interactions.createStream(config);

    // Assert - Both should return non-null futures
    assertNotNull(overloadFuture);
    assertNotNull(configFuture);
  }

  @Test
  public void testAsyncGetConvenienceOverload() {
    // Arrange
    Client client = Client.builder().apiKey("test-key").build();
    String interactionId = "test-interaction-id";

    // Act - Call the convenience overload
    CompletableFuture<?> future = client.async.interactions.get(interactionId);

    // Assert - Future should be non-null
    assertNotNull(future);
  }

  @Test
  public void testAsyncCancelConvenienceOverload() {
    // Arrange
    Client client = Client.builder().apiKey("test-key").build();
    String interactionId = "test-interaction-id";

    // Act - Call the convenience overload
    CompletableFuture<?> future = client.async.interactions.cancel(interactionId);

    // Assert - Future should be non-null
    assertNotNull(future);
  }

  @Test
  public void testAsyncDeleteConvenienceOverload() {
    // Arrange
    Client client = Client.builder().apiKey("test-key").build();
    String interactionId = "test-interaction-id";

    // Act - Call the convenience overload
    CompletableFuture<?> future = client.async.interactions.delete(interactionId);

    // Assert - Future should be non-null
    assertNotNull(future);
  }
}
