/*
 * Copyright 2026 Google LLC
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

package com.google.genai.examples;

import com.google.genai.Client;
import com.google.genai.gaos.models.interactions.CreateModelInteraction;
import com.google.genai.gaos.models.interactions.InteractionSSEEvent;
import com.google.genai.gaos.models.interactions.InteractionSSEStreamEvent;
import com.google.genai.gaos.models.interactions.InteractionsInput;
import com.google.genai.gaos.models.interactions.Model;
import com.google.genai.gaos.models.interactions.StepDelta;
import com.google.genai.gaos.models.interactions.StepDeltaData;
import com.google.genai.gaos.models.interactions.TextDelta;
import com.google.genai.gaos.models.operations.CreateInteractionRequestBody;
import com.google.genai.gaos.models.operations.CreateInteractionResponse;
import com.google.genai.gaos.utils.EventStream;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * An example of using the Unified Gen AI Java SDK to create a streaming interaction asynchronously.
 */
public final class InteractionCreateAsyncStreaming {
  public static void main(String[] args) {
    ExecutorService executor = Executors.newSingleThreadExecutor();
    try (Client client = new Client()) {
      if (client.vertexAI()) {
        System.out.println("Using Vertex AI");
      } else {
        System.out.println("Using Gemini Developer API");
      }

      CreateModelInteraction params =
          CreateModelInteraction.builder()
              .input(InteractionsInput.of("Tell me a story in 300 words."))
              .model(Model.of(Constants.GEMINI_MODEL_NAME))
              .stream(true)
              .build();

      System.out.println("Streaming response:");
      CompletableFuture<Void> future =
          CompletableFuture.runAsync(
              () -> {
                CreateInteractionResponse response =
                    client.interactions.create(CreateInteractionRequestBody.of(params));
                try (EventStream<InteractionSSEStreamEvent> events = response.events()) {
                  for (InteractionSSEStreamEvent streamEvent : events) {
                    InteractionSSEEvent event = streamEvent.data().orElse(null);
                    if (event instanceof StepDelta) {
                      StepDeltaData data = ((StepDelta) event).delta().orElse(null);
                      if (data instanceof TextDelta) {
                        ((TextDelta) data).text().ifPresent(t -> System.out.print(t));
                        System.out.flush();
                      }
                    }
                  }
                  System.out.println();
                } catch (Exception e) {
                  System.err.println("Error reading stream: " + e.getMessage());
                }
              },
              executor);

      future.join();
    } finally {
      executor.shutdown();
    }
  }

  private InteractionCreateAsyncStreaming() {}
}
