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
import com.google.genai.gaos.models.interactions.AgentOption;
import com.google.genai.gaos.models.interactions.CreateAgentInteraction;
import com.google.genai.gaos.models.interactions.InteractionCreatedEvent;
import com.google.genai.gaos.models.interactions.InteractionSSEEvent;
import com.google.genai.gaos.models.interactions.InteractionSSEStreamEvent;
import com.google.genai.gaos.models.interactions.InteractionsInput;
import com.google.genai.gaos.models.operations.CreateInteractionRequestBody;
import com.google.genai.gaos.models.operations.CreateInteractionResponse;
import com.google.genai.gaos.models.operations.GetInteractionByIdRequest;
import com.google.genai.gaos.models.operations.GetInteractionByIdResponse;
import com.google.genai.gaos.utils.EventStream;
import java.util.concurrent.atomic.AtomicReference;

/**
 * An example of using the Unified Gen AI Java SDK to run a Deep Research interaction on Vertex AI.
 */
public final class InteractionDeepResearch {
  public static void main(String[] args) {
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
            .agent(AgentOption.of("deep-research-pro-preview-12-2025"))
            .input(InteractionsInput.of("I want to learn more about the history of Hadrian's Wall"))
            .background(true)
            .stream(true)
            .build();

    AtomicReference<String> interactionId = new AtomicReference<>();

    System.out.println("--- Starting Deep Research Interaction ---");
    try {
      CreateInteractionResponse response =
          client.interactions.create(CreateInteractionRequestBody.of(params));
      try (EventStream<InteractionSSEStreamEvent> events = response.events()) {
        for (InteractionSSEStreamEvent streamEvent : events) {
          InteractionSSEEvent event = streamEvent.data().orElse(null);
          if (event instanceof InteractionCreatedEvent) {
            String id = ((InteractionCreatedEvent) event).interaction().flatMap(i -> i.id()).orElse(null);
            interactionId.set(id);
            System.out.println("Started Interaction ID: " + id);
            break;
          }
        }
      }
    } catch (Exception e) {
      System.err.println("Error starting interaction: " + e.getMessage());
    }

    String id = interactionId.get();
    if (id == null) {
      System.err.println("Failed to capture interaction ID.");
      return;
    }

    System.out.println("\n--- Resuming Interaction: " + id + " ---");
    try {
      GetInteractionByIdRequest getRequest = new GetInteractionByIdRequest(id);
      GetInteractionByIdResponse getResponse = client.interactions.get(getRequest);
      getResponse.interaction().ifPresent(interaction -> {
        System.out.println("Interaction status: " + interaction.status().orElse(null));
      });
    } catch (Exception e) {
      System.err.println("Error getting interaction: " + e.getMessage());
    }
  }

  private InteractionDeepResearch() {}
}
