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
import com.google.genai.interactions.core.ClientOptions;
import com.google.genai.interactions.core.RequestOptions;
import com.google.genai.interactions.core.http.StreamResponse;
import com.google.genai.interactions.models.interactions.CreateAgentInteractionParams;
import com.google.genai.interactions.models.interactions.InteractionSseEvent;
import com.google.genai.interactions.services.blocking.InteractionService;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicReference;

/**
 * An example of using the Unified Gen AI Java SDK to run a Deep Research interaction on Vertex AI.
 */
public final class InteractionDeepResearch {
  public static void main(String[] args) {
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

    CreateAgentInteractionParams params =
        CreateAgentInteractionParams.builder()
            .agent("deep-research-pro-preview-12-2025")
            .input("I want to learn more about the history of Hadrian's Wall")
            .background(true)
            .stream(true)
            .build();

    AtomicReference<String> interactionId = new AtomicReference<>();
    RequestOptions options = RequestOptions.builder().timeout(Duration.ofHours(1)).build();

    System.out.println("--- Starting Deep Research Interaction ---");
    InteractionService interactions =
        client.interactions.withOptions(
            b -> {
              ClientOptions.Builder unused =
                  b.apiVersion("v1beta1");
            });

    try (StreamResponse<InteractionSseEvent> streamResponse =
        interactions.createStreaming(params, options)) {
      // We consume the first few events to capture the Interaction ID and see progress.
      streamResponse.stream()
          .limit(1)
          .forEach(
              event -> {
                if (event.isStart()) {
                  String id = event.asStart().interaction().id();
                  interactionId.set(id);
                  System.out.println("Started Interaction ID: " + id);
                }
                String eventType = "unknown";
                if (event.isStart()) {
                  eventType = "interaction.start";
                } else if (event.isComplete()) {
                  eventType = "interaction.complete";
                } else if (event.isStatusUpdate()) {
                  eventType = "interaction.status_update";
                } else if (event.isStepStart()) {
                  eventType = "step.start";
                } else if (event.isStepDelta()) {
                  eventType = "step.delta";
                } else if (event.isStepStop()) {
                  eventType = "step.stop";
                } else if (event.isError()) {
                  eventType = "error";
                }

                System.out.println("Event type: " + eventType);
              });
    }

    String id = interactionId.get();
    if (id == null) {
      System.err.println("Failed to capture interaction ID.");
      return;
    }

    // Deep research can take a long time. In a real application, you might poll or resume later.
    // Here we resume the stream to wait for more output.
    System.out.println("\n--- Resuming Interaction: " + id + " ---");
    try (StreamResponse<InteractionSseEvent> getStream =
        interactions.getStreaming(id, options)) {
      getStream.stream()
          .forEach(
              event -> {
                if (event.isStatusUpdate()) {
                  System.out.println(
                      "\n[Status update: " + event.asStatusUpdate().status().known() + "]");
                }

                event
                    .stepDelta()
                    .ifPresent(
                        delta -> {
                          delta
                              .delta()
                              .text()
                              .ifPresentOrElse(
                                  text -> System.out.print(text.text()),
                                  () -> {
                                    // If it's a content delta but doesn't have text (e.g. tool call
                                    // or thought), print it.
                                    System.out.println(
                                        "\n[Agent Activity]: "
                                            + delta
                                                .delta()
                                                ._json()
                                                .map(Object::toString)
                                                .orElse("Unknown activity"));
                                  });
                        });
              });
      System.out.println();
    }
  }

  private InteractionDeepResearch() {}
}
