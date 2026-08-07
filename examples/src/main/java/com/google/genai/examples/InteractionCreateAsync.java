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
import com.google.genai.gaos.models.interactions.Content;
import com.google.genai.gaos.models.interactions.CreateModelInteraction;
import com.google.genai.gaos.models.interactions.Interaction;
import com.google.genai.gaos.models.interactions.InteractionStatus;
import com.google.genai.gaos.models.interactions.InteractionsInput;
import com.google.genai.gaos.models.interactions.Model;
import com.google.genai.gaos.models.interactions.ModelOutputStep;
import com.google.genai.gaos.models.interactions.Step;
import com.google.genai.gaos.models.interactions.TextContent;
import com.google.genai.gaos.models.operations.CreateInteractionRequestBody;
import com.google.genai.gaos.models.operations.CreateInteractionResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** An example of using the Unified Gen AI Java SDK to create an interaction asynchronously. */
public final class InteractionCreateAsync {
  public static void main(String[] args) {
    ExecutorService executor = Executors.newSingleThreadExecutor();
    try (Client client = new Client()) {
      if (client.vertexAI()) {
        System.out.println("Using Vertex AI");
      } else {
        System.out.println("Using Gemini Developer API");
      }

      CreateModelInteraction createModelInteraction =
          CreateModelInteraction.builder()
              .input(InteractionsInput.of("What is your name?"))
              .model(Model.of(Constants.GEMINI_MODEL_NAME))
              .build();

      CompletableFuture<CreateInteractionResponse> futureResponse =
          CompletableFuture.supplyAsync(
              () -> client.interactions.create(CreateInteractionRequestBody.of(createModelInteraction)),
              executor);

      futureResponse
          .thenAccept(
              response -> {
                Interaction interaction =
                    response.interaction().orElseThrow(() -> new RuntimeException("No interaction returned"));
                System.out.println("Interaction ID: " + interaction.id().orElse("N/A"));
                System.out.println("Status: " + interaction.status().orElse(InteractionStatus.COMPLETED));

                if (interaction.steps().isPresent()) {
                  for (Step step : interaction.steps().get()) {
                    if (step instanceof ModelOutputStep) {
                      ModelOutputStep outputStep = (ModelOutputStep) step;
                      if (outputStep.content().isPresent()) {
                        for (Content content : outputStep.content().get()) {
                          if (content instanceof TextContent) {
                            ((TextContent) content).text().ifPresent(text -> System.out.println("Output: " + text));
                          }
                        }
                      }
                    }
                  }
                }
              })
          .join();
    } finally {
      executor.shutdown();
    }
  }

  private InteractionCreateAsync() {}
}
