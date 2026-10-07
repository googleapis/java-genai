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

/**
 * Usage:
 *
 * <p>1a. If you are using Vertex AI, setup ADC to get credentials:
 * https://cloud.google.com/docs/authentication/provide-credentials-adc#google-idp
 *
 * <p>Then set Project, Location, and USE_VERTEXAI flag as environment variables:
 *
 * <p>export GOOGLE_CLOUD_PROJECT=YOUR_PROJECT
 *
 * <p>export GOOGLE_CLOUD_LOCATION=YOUR_LOCATION
 *
 * <p>export GOOGLE_GENAI_USE_VERTEXAI=true
 *
 * <p>1b. If you are using Gemini Developer API, set an API key environment variable. You can find a
 * list of available API keys here: https://aistudio.google.com/app/apikey
 *
 * <p>export GOOGLE_API_KEY=YOUR_API_KEY
 *
 * <p>2. Compile the java package and run the sample code.
 *
 * <p>mvn clean compile exec:java
 * -Dexec.mainClass="com.google.genai.examples.InteractionContinuation"
 */
package com.google.genai.examples;

import com.google.genai.Client;
import com.google.genai.gaos.models.interactions.Content;
import com.google.genai.gaos.models.interactions.CreateModelInteraction;
import com.google.genai.gaos.models.interactions.Interaction;
import com.google.genai.gaos.models.interactions.InteractionCompletedEvent;
import com.google.genai.gaos.models.interactions.InteractionSSEEvent;
import com.google.genai.gaos.models.interactions.InteractionSSEStreamEvent;
import com.google.genai.gaos.models.interactions.InteractionSseEventInteraction;
import com.google.genai.gaos.models.interactions.InteractionSseEventInteractionStatus;
import com.google.genai.gaos.models.interactions.InteractionStatus;
import com.google.genai.gaos.models.interactions.InteractionsInput;
import com.google.genai.gaos.models.interactions.Model;
import com.google.genai.gaos.models.interactions.ModelOutputStep;
import com.google.genai.gaos.models.interactions.Step;
import com.google.genai.gaos.models.interactions.StepDelta;
import com.google.genai.gaos.models.interactions.StepDeltaData;
import com.google.genai.gaos.models.interactions.TextContent;
import com.google.genai.gaos.models.interactions.TextDelta;
import com.google.genai.gaos.models.operations.CreateInteractionRequestBody;
import com.google.genai.gaos.models.operations.CreateInteractionResponse;
import com.google.genai.gaos.utils.EventStream;
import java.util.Collections;
import java.util.Objects;

/**
 * An example of using the Unified Gen AI Java SDK to continue a long-decoding interaction across
 * multiple requests using continuation tokens.
 */
public final class InteractionContinuation {
  public static void main(String[] args) {
    Client client = new Client();

    if (client.vertexAI()) {
      System.out.println("Interactions API is not yet supported on Vertex AI");
      return;
    }

    System.out.println("Using Gemini Developer API");

    String modelName = System.getenv("GEMINI_MODEL");
    if (modelName == null || modelName.isEmpty()) {
      modelName = Constants.GEMINI_MODEL_NAME;
    }

    System.out.println("--- Unary Continuation ---");
    runUnaryContinuation(client, modelName);

    System.out.println("\n--- Streaming Continuation ---");
    runStreamingContinuation(client, modelName);
  }

  private static void printInteractionText(Interaction interaction) {
    if (interaction.outputText().isPresent()) {
      System.out.print(interaction.outputText().get());
      return;
    }
    for (Step step : interaction.steps().orElse(Collections.emptyList())) {
      if (step instanceof ModelOutputStep) {
        ModelOutputStep modelOutputStep = (ModelOutputStep) step;
        for (Content content : modelOutputStep.content().orElse(Collections.emptyList())) {
          if (content instanceof TextContent) {
            TextContent textContent = (TextContent) content;
            textContent.text().ifPresent(System.out::print);
          }
        }
      }
    }
  }

  private static void runUnaryContinuation(Client client, String modelName) {
    CreateModelInteraction createRequest =
        CreateModelInteraction.builder()
            .model(Model.of(modelName))
            .input(InteractionsInput.of("Write a 500 word story about a robot."))
            .build();

    Interaction interaction =
        client
            .interactions
            .create(CreateInteractionRequestBody.of(createRequest))
            .interaction()
            .orElseThrow(() -> new IllegalStateException("No interaction returned"));

    printInteractionText(interaction);

    while (Objects.equals(interaction.status().orElse(null), InteractionStatus.INCOMPLETE)
        && interaction.continuationToken().isPresent()) {
      CreateModelInteraction continueRequest =
          CreateModelInteraction.builder()
              .model(Model.of(modelName))
              .previousInteractionId(interaction.id().orElse(null))
              .continuationToken(interaction.continuationToken().orElse(null))
              .build();

      interaction =
          client
              .interactions
              .create(CreateInteractionRequestBody.of(continueRequest))
              .interaction()
              .orElseThrow(
                  () -> new IllegalStateException("No interaction returned on continuation"));

      printInteractionText(interaction);
    }

    System.out.println("\nFinal status: " + interaction.status().orElse(null));
  }

  private static void runStreamingContinuation(Client client, String modelName) {
    String interactionId = null;
    String continuationToken = null;

    while (true) {
      CreateModelInteraction.Builder requestBuilder =
          CreateModelInteraction.builder()
              .model(Model.of(modelName))
              .previousInteractionId(interactionId)
              .continuationToken(continuationToken)
              .stream(true);
      if (continuationToken == null) {
        requestBuilder =
            requestBuilder.input(InteractionsInput.of("Write a 500 word story about a robot."));
      }

      InteractionSseEventInteractionStatus status = null;
      try {
        CreateInteractionResponse response =
            client.interactions.create(CreateInteractionRequestBody.of(requestBuilder.build()));
        try (EventStream<InteractionSSEStreamEvent> events = response.events()) {
          for (InteractionSSEStreamEvent streamEvent : events) {
            InteractionSSEEvent event = streamEvent.data().orElse(null);
            if (event instanceof StepDelta) {
              StepDelta stepDelta = (StepDelta) event;
              StepDeltaData data = stepDelta.delta().orElse(null);
              if (data instanceof TextDelta) {
                TextDelta textDelta = (TextDelta) data;
                textDelta.text().ifPresent(System.out::print);
                System.out.flush();
              }
            } else if (event instanceof InteractionCompletedEvent) {
              InteractionCompletedEvent interactionCompletedEvent =
                  (InteractionCompletedEvent) event;
              InteractionSseEventInteraction completedInteraction =
                  interactionCompletedEvent.interaction().orElse(null);
              if (completedInteraction != null) {
                interactionId = completedInteraction.id().orElse(null);
                status = completedInteraction.status().orElse(null);
                continuationToken = completedInteraction.continuationToken().orElse(null);
              }
            }
          }
        }
      } catch (Exception e) {
        throw new IllegalStateException("Error during streaming continuation", e);
      }

      if (!Objects.equals(status, InteractionSseEventInteractionStatus.INCOMPLETE)
          || continuationToken == null) {
        System.out.println("\nFinal stream status: " + status);
        break;
      }
    }
  }

  private InteractionContinuation() {}
}
