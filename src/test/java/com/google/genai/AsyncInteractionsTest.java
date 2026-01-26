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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.genai.types.interactions.CreateInteractionConfig;
import com.google.genai.types.interactions.GenerationConfig;
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
}
