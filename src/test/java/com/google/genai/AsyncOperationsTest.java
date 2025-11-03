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
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@EnabledIfEnvironmentVariable(
    named = "GOOGLE_GENAI_REPLAYS_DIRECTORY",
    matches = ".*genai/replays.*")
@ExtendWith(EnvironmentVariablesMockingExtension.class)
public class AsyncOperationsTest {

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testAsyncGetVideosOperation(boolean vertexAI)
      throws ExecutionException, InterruptedException {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI,
            "tests/models/generate_videos/test_create_operation_to_poll." + suffix + ".json");

    String operationName =
        vertexAI
            ? "projects/<project>/locations/<location>/publishers/google/models/veo-3.1-generate-preview/operations/6a55ba83-558a-4b29-9999-325b1eb218cf"
            : "models/veo-3.1-generate-preview/operations/9jwcbmi5tkvw";

    // Act
    GenerateVideosOperation generateVideosOperation =
        client
            .async
            .operations
            .getVideosOperation(GenerateVideosOperation.builder().name(operationName).build(), null)
            .get();

    // GenerateVideosOperation.done() is empty if the operation is not done.
    while (!generateVideosOperation.done().filter(Boolean::booleanValue).isPresent()) {
      try {
        if (!client.clientMode().equals("replay")) {
          Thread.sleep(10000); // Sleep for 10 seconds.
          System.out.println("Waiting for operation to complete...");
        }
        generateVideosOperation =
            client.async.operations.getVideosOperation(generateVideosOperation, null).get();
      } catch (InterruptedException e) {
        System.out.println("Thread was interrupted while sleeping.");
        Thread.currentThread().interrupt();
      }
    }

    // Assert
    assertTrue(generateVideosOperation.done().get());
    assertNotNull(
        generateVideosOperation.response().get().generatedVideos().get().get(0).video().get());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testTextToVideoPollAsync(boolean vertexAI)
      throws ExecutionException, InterruptedException {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI,
            "tests/models/generate_videos/test_text_to_video_poll_async." + suffix + ".json");

    String model = "veo-3.1-generate-preview";
    String prompt = "A neon hologram of a cat driving at top speed";
    GenerateVideosConfig.Builder configBuilder = GenerateVideosConfig.builder();
    if (vertexAI) {
      configBuilder.outputGcsUri("gs://genai-sdk-tests/temp/videos/");
    }
    GenerateVideosConfig config = configBuilder.build();

    // Act
    GenerateVideosOperation generateVideosOperation =
        client.async.models.generateVideos(model, prompt, null, null, config).get();

    // GenerateVideosOperation.done() is empty if the operation is not done.
    while (!generateVideosOperation.done().filter(Boolean::booleanValue).isPresent()) {
      try {
        if (!client.clientMode().equals("replay")) {
          Thread.sleep(10000); // Sleep for 10 seconds.
          System.out.println("Waiting for operation to complete...");
        }
        generateVideosOperation =
            client.async.operations.getVideosOperation(generateVideosOperation, null).get();
      } catch (InterruptedException e) {
        System.out.println("Thread was interrupted while sleeping.");
        Thread.currentThread().interrupt();
      }
    }

    // Assert
    assertTrue(generateVideosOperation.done().get());
    assertNotNull(
        generateVideosOperation.response().get().generatedVideos().get().get(0).video().get());
    assertTrue(
        generateVideosOperation
            .response()
            .get()
            .generatedVideos()
            .get()
            .get(0)
            .video()
            .get()
            .uri()
            .isPresent());
  }
}
