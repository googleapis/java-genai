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

/**
 * Usage:
 *
 * <p>Note: The Interactions API is currently only available via the Gemini Developer API (not
 * Vertex AI).
 *
 * <p>1. Set an API key environment variable. You can find a list of available API keys here:
 * https://aistudio.google.com/app/apikey
 *
 * <p>export GOOGLE_API_KEY=YOUR_API_KEY
 *
 * <p>2. Compile the java package and run the sample code.
 *
 * <p>mvn clean compile
 *
 * <p>mvn exec:java -Dexec.mainClass="com.google.genai.examples.InteractionsStreamResumptionExample"
 */
package com.google.genai.examples;

import com.google.genai.Client;
import com.google.genai.InteractionEventStream;
import com.google.genai.types.interactions.GetInteractionConfig;
import com.google.genai.types.interactions.streaming.ContentDelta;
import com.google.genai.types.interactions.streaming.InteractionEvent;
import com.google.genai.types.interactions.streaming.InteractionSseEvent;
import com.google.genai.types.interactions.streaming.delta.Delta;
import com.google.genai.types.interactions.streaming.delta.TextDelta;

/**
 * Example: Stream Resumption for Interactions
 *
 * <p>Demonstrates how to resume a streaming interaction after a connection interruption. This is
 * useful for:
 *
 * <ul>
 *   <li>Recovering from network failures
 *   <li>Handling connection timeouts
 *   <li>Building resilient streaming applications
 * </ul>
 *
 * <p>The key concept is tracking the {@code eventId} from each event and using it with
 * {@code lastEventId} in GetInteractionConfig to resume from where you left off.
 *
 * <p>Note: The Interactions API is in beta and subject to change.
 */
public final class InteractionsStreamResumptionExample {

  public static void main(String[] args) {
    Client client = new Client();

    System.out.println("=== Interactions API: Stream Resumption Example ===\n");

    try {
      // Track the last event ID and interaction ID for resumption
      String lastEventId = null;
      String interactionId = null;
      StringBuilder collectedText = new StringBuilder();
      int eventCount = 0;
      int eventsBeforeSimulatedFailure = 3; // Simulate failure after 3 events

      // Using the convenience overload: createStream(model, text)
      String model = "gemini-3-flash-preview";
      String prompt = "Write a haiku about Java programming.";

      System.out.println("--- Starting initial stream (will simulate failure) ---\n");

      // First attempt - simulating a connection failure partway through
      try (InteractionEventStream<InteractionSseEvent> stream =
          client.interactions.createStream(model, prompt)) {
        for (InteractionSseEvent event : stream) {
          // Track the event ID for potential resumption
          lastEventId = event.eventId().orElse(lastEventId);

          // Capture the interaction ID from the start event
          if (event instanceof InteractionEvent) {
            InteractionEvent interactionEvent = (InteractionEvent) event;
            if (interactionEvent.isStart()) {
              interactionId = interactionEvent.interaction().map(i -> i.id()).orElse(null);
              System.out.println("Interaction started: " + interactionId);
            } else if (interactionEvent.isComplete()) {
              System.out.println("\n\nInteraction completed successfully (no resumption needed)");
            }
          } else if (event instanceof ContentDelta) {
            ContentDelta deltaEvent = (ContentDelta) event;
            Delta delta = deltaEvent.delta().orElse(null);
            if (delta instanceof TextDelta) {
              TextDelta textDelta = (TextDelta) delta;
              String text = textDelta.text().orElse("");
              collectedText.append(text);
              System.out.print(text);
            }
          }

          eventCount++;

          // Simulate a connection failure after a few events
          boolean isComplete = (event instanceof InteractionEvent) && ((InteractionEvent) event).isComplete();
          if (eventCount >= eventsBeforeSimulatedFailure && !isComplete) {
            System.out.println("\n\n[SIMULATED] Connection interrupted after " + eventCount + " events!");
            System.out.println("[SIMULATED] Last event ID: " + lastEventId);
            throw new RuntimeException("Simulated connection failure");
          }
        }
      } catch (RuntimeException e) {
        if (!e.getMessage().contains("Simulated")) {
          throw e;
        }
        // Expected - continue to resumption
      }

      // Only attempt resumption if we have the necessary information
      if (interactionId != null && lastEventId != null) {
        System.out.println("\n--- Resuming stream from last event ---\n");

        // Resume the stream from where we left off
        GetInteractionConfig resumeConfig =
            GetInteractionConfig.builder()
                .lastEventId(lastEventId)
                .build();

        try (InteractionEventStream<InteractionSseEvent> resumedStream =
            client.interactions.getStream(interactionId, resumeConfig)) {
          System.out.println("Resumed content:\n");
          for (InteractionSseEvent event : resumedStream) {
            if (event instanceof ContentDelta) {
              ContentDelta deltaEvent = (ContentDelta) event;
              Delta delta = deltaEvent.delta().orElse(null);
              if (delta instanceof TextDelta) {
                TextDelta textDelta = (TextDelta) delta;
                String text = textDelta.text().orElse("");
                collectedText.append(text);
                System.out.print(text);
              }
            } else if (event instanceof InteractionEvent) {
              InteractionEvent interactionEvent = (InteractionEvent) event;
              if (interactionEvent.isComplete()) {
                System.out.println("\n\nResumed stream completed successfully!");
              }
            }
          }
        }

        System.out.println("\n--- Full collected response ---\n");
        System.out.println(collectedText.toString());
      } else {
        System.out.println("Could not resume: missing interaction ID or event ID");
      }

      System.out.println("\n=== Example completed ===");

    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
      e.printStackTrace();
    }
  }

  private InteractionsStreamResumptionExample() {}
}
