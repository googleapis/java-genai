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

import com.google.genai.types.GenerateVideosOperation;
import java.util.Optional;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@EnabledIfEnvironmentVariable(
    named = "GOOGLE_GENAI_REPLAYS_DIRECTORY",
    matches = ".*genai/replays.*")
public class OperationsTest {
  private Client createClient(boolean vertexAI, String replayId) {
    String clientMode = System.getenv("GOOGLE_GENAI_CLIENT_MODE");
    DebugConfig debugConfig =
        new DebugConfig(
            clientMode == null ? "replay" : clientMode,
            "",
            System.getenv("GOOGLE_GENAI_REPLAYS_DIRECTORY"));
    String apiKey = Optional.ofNullable(ApiClient.getApiKeyFromEnv()).orElse("api-key");
    String project = Optional.ofNullable(System.getenv("GOOGLE_GENAI_PROJECT")).orElse("project");
    String location =
        Optional.ofNullable(System.getenv("GOOGLE_GENAI_LOCATION")).orElse("location");

    Client.Builder clientBuilder = Client.builder().vertexAI(vertexAI).debugConfig(debugConfig);

    if (vertexAI) {
      clientBuilder.project(project).location(location);
    } else {
      clientBuilder.apiKey(apiKey);
    }
    Client client = clientBuilder.build();

    if (client.clientMode().equals("replay")) {
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
        createClient(
            vertexAI,
            "tests/models/generate_videos/test_create_operation_to_poll." + suffix + ".json");

    String operationName =
        vertexAI
            ? "projects/<project>/locations/<location>/publishers/google/models/veo-2.0-generate-001/operations/ddb46542-07ed-4000-958d-655fbffb05a4"
            : "models/veo-2.0-generate-001/operations/ren0ubieaocs";

    // Act
    GenerateVideosOperation generateVideosOperation =
        client.operations.getVideosOperation(
            GenerateVideosOperation.builder().name(operationName).build(), null);

    // GenerateVideosOperation.done() is empty if the operation is not done.
    while (!generateVideosOperation.done().filter(Boolean::booleanValue).isPresent()) {
      try {
        if (!client.clientMode().equals("replay")) {
          Thread.sleep(10000); // Sleep for 10 seconds.
          System.out.println("Waiting for operation to complete...");
        }
        generateVideosOperation =
            client.operations.getVideosOperation(generateVideosOperation, null);
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