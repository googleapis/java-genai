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
 * <p>Deep Research is currently only available on Vertex AI.
 *
 * <p>1. Setup ADC to get credentials:
 * https://cloud.google.com/docs/authentication/provide-credentials-adc#google-idp
 *
 * <p>2. Set Project, Location, and USE_VERTEXAI flag as environment variables:
 *
 * <p>export GOOGLE_CLOUD_PROJECT=YOUR_PROJECT
 *
 * <p>export GOOGLE_CLOUD_LOCATION=global
 *
 * <p>export GOOGLE_GENAI_USE_VERTEXAI=true
 *
 * <p>3. Compile the java package and run the sample code.
 *
 * <p>mvn clean compile exec:java
 * -Dexec.mainClass="com.google.genai.examples.InteractionDeepResearch"
 */
package com.google.genai.examples;

import com.google.genai.Client;
import com.google.genai.gaos.models.interactions.CreateAgentInteraction;
import com.google.genai.gaos.models.interactions.ErrorEvent;
import com.google.genai.gaos.models.interactions.InteractionCompletedEvent;
import com.google.genai.gaos.models.interactions.InteractionCreatedEvent;
import com.google.genai.gaos.models.interactions.InteractionSSEEvent;
import com.google.genai.gaos.models.interactions.InteractionSSEStreamEvent;
import com.google.genai.gaos.models.interactions.InteractionStatusUpdate;
import com.google.genai.gaos.models.interactions.InteractionsInput;
import com.google.genai.gaos.models.interactions.StepDelta;
import com.google.genai.gaos.models.interactions.StepDeltaData;
import com.google.genai.gaos.models.interactions.StepStart;
import com.google.genai.gaos.models.interactions.StepStop;
import com.google.genai.gaos.models.interactions.TextDelta;
import com.google.genai.gaos.models.operations.CreateInteractionRequestBody;
import com.google.genai.gaos.models.operations.CreateInteractionResponse;
import com.google.genai.gaos.models.operations.GetInteractionByIdResponse;
import com.google.genai.gaos.utils.EventStream;
import java.util.concurrent.atomic.AtomicReference;

/**
 * An example of using the Unified Gen AI Java SDK to run a Deep Research interaction on Vertex AI.
 */
public final class InteractionDeepResearch {
  public static void main(String[] args) throws Exception {
    // Instantiate the client. Deep Research requires Vertex AI.
    // Ensure GOOGLE_CLOUD_PROJECT, GOOGLE_CLOUD_LOCATION=global, and
    // GOOGLE_GENAI_USE_VERTEXAI=true are set in your environment.
    Client client = new Client();

    if (!client.vertexAI()) {
      System.err.println("Deep Research is only supported on Vertex AI. "
          + "Please set GOOGLE_GENAI_USE_VERTEXAI=true.");
      return;
    }

    System.out.println(
        "Using Vertex AI Project: " + (client.project() != null ? client.project() : "unknown"));

    CreateAgentInteraction params =
        CreateAgentInteraction.builder()
            .agent("deep-research-pro-preview-12-2025")
            .input(InteractionsInput.of("I want to learn more about the history of Hadrian's Wall"))
            .background(true)
            .stream(true)
            .build();

    AtomicReference<String> interactionId = new AtomicReference<>();

    System.out.println("--- Starting Deep Research Interaction ---");

    CreateInteractionResponse createResponse =
        client.interactions.create()
            .apiVersion("v1beta1")
            .body(CreateInteractionRequestBody.of(params))
            .call();

    try (EventStream<InteractionSSEStreamEvent> eventStream = createResponse.events()) {
      // We consume the first few events to capture the Interaction ID and see progress.
      for (InteractionSSEStreamEvent streamEvent : eventStream) {
        InteractionSSEEvent event = streamEvent.data();
        if (event.value() instanceof InteractionCreatedEvent) {
          InteractionCreatedEvent createdEvent = (InteractionCreatedEvent) event.value();
          String id = createdEvent.interaction().id();
          interactionId.set(id);
          System.out.println("Started Interaction ID: " + id);
        }
        String eventType = "unknown";
        if (event.value() instanceof InteractionCreatedEvent) {
          eventType = "interaction.created";
        } else if (event.value() instanceof InteractionCompletedEvent) {
          eventType = "interaction.completed";
        } else if (event.value() instanceof InteractionStatusUpdate) {
          eventType = "interaction.status_update";
        } else if (event.value() instanceof StepStart) {
          eventType = "step.start";
        } else if (event.value() instanceof StepDelta) {
          eventType = "step.delta";
        } else if (event.value() instanceof StepStop) {
          eventType = "step.stop";
        } else if (event.value() instanceof ErrorEvent) {
          eventType = "error";
        }

        System.out.println("Event type: " + eventType);

        // Stop consuming after capturing the ID or if the stream is complete
        if (interactionId.get() != null) {
          break;
        }
      }
    }

    String id = interactionId.get();
    if (id == null) {
      System.err.println("Failed to capture interaction ID.");
      return;
    }

    // Deep research can take a long time. In a real application, you might poll or resume later.
    // Here we resume the stream to wait for more output.
    System.out.println("\n--- Resuming Interaction: " + id + " ---");
    GetInteractionByIdResponse getResponse =
        client.interactions.get(
            com.google.genai.gaos.models.operations.GetInteractionByIdRequest.builder()
                .apiVersion("v1beta1")
                .id(id)
                .stream(true)
                .build());

    try (EventStream<InteractionSSEStreamEvent> eventStream = getResponse.events()) {
      for (InteractionSSEStreamEvent streamEvent : eventStream) {
        InteractionSSEEvent event = streamEvent.data();
        if (event.value() instanceof InteractionStatusUpdate) {
          InteractionStatusUpdate statusUpdate = (InteractionStatusUpdate) event.value();
          System.out.println(
              "\n[Status update: " + statusUpdate.status().value() + "]");
        } else if (event.value() instanceof StepDelta) {
          StepDelta stepDelta = (StepDelta) event.value();
          StepDeltaData data = stepDelta.delta();
          if (data.value() instanceof TextDelta) {
            TextDelta textDelta = (TextDelta) data.value();
            System.out.print(textDelta.text());
          } else {
            // If it's a content delta but doesn't have text (e.g. tool call
            // or thought), print the type.
            System.out.println("\n[Agent Activity]: " + data.value().getClass().getSimpleName());
          }
        }
      }
      System.out.println();
    }
  }

  private InteractionDeepResearch() {}
}
