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
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.genai.types.GenerateVideosConfig;
import com.google.genai.types.GenerateVideosOperation;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class OperationsTest {
  private Client createClient(boolean vertexAI, String replayId) {
    // Use the API mode until the replay mode is complete.
    DebugConfig debugConfig =
        new DebugConfig("api", "", System.getenv("GOOGLE_GENAI_REPLAYS_DIRECTORY"));
    Client client = Client.builder().debugConfig(debugConfig).vertexAI(vertexAI).build();
    if (client.clientMode().equals("replay") || client.clientMode().equals("auto")) {
      client.setReplayId(replayId);
    }
    return client;
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testGetVideosOperation(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        createClient(vertexAI, "tests/operations/test_get_videos_operation." + suffix + ".json");

    // Act
    GenerateVideosConfig generateVideosConfig =
        GenerateVideosConfig.builder().numberOfVideos(1).build();

    GenerateVideosOperation generateVideosOperation =
        client.models.generateVideos(
            "veo-2.0-generate-001",
            "A neon hologram of a cat driving at top speed",
            null,
            null,
            generateVideosConfig);

    // GenerateVideosOperation.done() is empty if the operation is not done.
    while (!generateVideosOperation.done().filter(Boolean::booleanValue).isPresent()) {
      try {
        Thread.sleep(10000); // Sleep for 10 seconds.
        generateVideosOperation =
            client.operations.getVideosOperation(generateVideosOperation, null);
        System.out.println("Waiting for operation to complete...");
      } catch (InterruptedException e) {
        System.out.println("Thread was interrupted while sleeping.");
        Thread.currentThread().interrupt();
      }
    }

    // Arrange
    assertTrue(generateVideosOperation.done().get());
    assertNotNull(
        generateVideosOperation.response().get().generatedVideos().get().get(0).video().get());
  }
}