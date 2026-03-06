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
 * <p>mvn exec:java -Dexec.mainClass="com.google.genai.examples.InteractionsStreamingExample"
 */
package com.google.genai.examples;

import com.google.genai.Client;
import com.google.genai.JsonSerializable;
import com.google.genai.InteractionEventStream;
import com.google.genai.types.interactions.streaming.ContentDelta;
import com.google.genai.types.interactions.streaming.ContentStart;
import com.google.genai.types.interactions.streaming.ContentStop;
import com.google.genai.types.interactions.streaming.ErrorEvent;
import com.google.genai.types.interactions.streaming.InteractionEvent;
import com.google.genai.types.interactions.streaming.InteractionStatusUpdate;
import com.google.genai.types.interactions.streaming.InteractionSseEvent;
import com.google.genai.types.interactions.streaming.delta.Delta;
import com.google.genai.types.interactions.streaming.delta.FunctionCallDelta;
import com.google.genai.types.interactions.streaming.delta.TextDelta;

/**
 * Example: Streaming Interactions
 *
 * <p>Demonstrates the streaming API for interactions, which allows receiving real-time incremental
 * updates as the model generates its response.
 *
 * <p>Streaming is useful for:
 *
 * <ul>
 *   <li>Displaying responses incrementally to users
 *   <li>Processing large outputs without waiting for completion
 *   <li>Providing responsive user experiences
 * </ul>
 *
 * <p>Note: The Interactions API is in beta and subject to change.
 */
public final class InteractionsStreamingExample {

  public static void main(String[] args) {
    Client client = new Client();

    System.out.println("=== Interactions API: Streaming Example ===\n");

    try {
      // ===== PART 1: Basic Streaming =====
      System.out.println("--- PART 1: Basic Streaming ---\n");

      // Using the convenience overload: createStream(model, text)
      String model = "gemini-3-flash-preview";
      String prompt1 = "Count from 1 to 10, saying each number on a new line.";

      // Log request
      System.out.println("[REQUEST]");
      System.out.println("  Model: " + model);
      System.out.println("  Input: " + prompt1);
      System.out.println();

      System.out.println("Streaming response:\n");

      int eventCount = 0;
      try (InteractionEventStream<InteractionSseEvent> stream =
          client.interactions.createStream(model, prompt1)) {
        for (InteractionSseEvent event : stream) {
          eventCount++;
          // Log each raw event as JSON
          System.out.println("\n[RESPONSE EVENT #" + eventCount + "] " + event.getClass().getSimpleName() + ":");
          if (event instanceof JsonSerializable) {
            System.out.println(((JsonSerializable) event).toJson());
          }

          if (event instanceof ContentDelta) {
            ContentDelta deltaEvent = (ContentDelta) event;
            Delta delta = deltaEvent.delta().orElse(null);
            if (delta instanceof TextDelta) {
              TextDelta textDelta = (TextDelta) delta;
              // Print text incrementally without newline
              System.out.print("[TEXT] " + textDelta.text().orElse(""));
            }
          }
        }
      }
      System.out.println("\n[TOTAL EVENTS RECEIVED: " + eventCount + "]\n");

      // ===== PART 2: Processing All Event Types =====
      System.out.println("--- PART 2: Processing All Event Types ---\n");

      // Using the convenience overload: createStream(model, text)
      String prompt2 = "Tell me a very short story (2-3 sentences).";

      // Log request
      System.out.println("[REQUEST]");
      System.out.println("  Model: " + model);
      System.out.println("  Input: " + prompt2);
      System.out.println();

      System.out.println("Processing all event types:\n");

      int eventCount2 = 0;
      try (InteractionEventStream<InteractionSseEvent> stream =
          client.interactions.createStream(model, prompt2)) {
        for (InteractionSseEvent event : stream) {
          eventCount2++;
          // Log each raw event as JSON
          System.out.println("\n[RESPONSE EVENT #" + eventCount2 + "] " + event.getClass().getSimpleName() + ":");
          if (event instanceof JsonSerializable) {
            System.out.println(((JsonSerializable) event).toJson());
          }

          // Handle each event type
          if (event instanceof InteractionEvent) {
            InteractionEvent interactionEvent = (InteractionEvent) event;
            if (interactionEvent.isStart()) {
              System.out.println("[START] Interaction started");
              interactionEvent
                  .interaction()
                  .ifPresent(
                      interaction -> {
                        System.out.println("  ID: " + interaction.id());
                        System.out.println("  Status: " + interaction.status());
                      });
            } else if (interactionEvent.isComplete()) {
              System.out.println("[COMPLETE] Interaction completed");
              interactionEvent
                  .interaction()
                  .ifPresent(
                      interaction -> {
                        System.out.println("  Final status: " + interaction.status());
                        interaction
                            .usage()
                            .ifPresent(
                                usage -> {
                                  usage
                                      .totalTokens()
                                      .ifPresent(
                                          count -> System.out.println("  Total tokens: " + count));
                                });
                      });
            }
          } else if (event instanceof ContentStart) {
            ContentStart contentStart = (ContentStart) event;
            System.out.println(
                "[CONTENT_START] Index: " + contentStart.index().orElse(-1));
          } else if (event instanceof ContentDelta) {
            ContentDelta deltaEvent = (ContentDelta) event;
            Delta delta = deltaEvent.delta().orElse(null);
            if (delta instanceof TextDelta) {
              TextDelta textDelta = (TextDelta) delta;
              System.out.print("[DELTA] " + textDelta.text().orElse("") + "\n");
            } else if (delta instanceof FunctionCallDelta) {
              FunctionCallDelta fcDelta = (FunctionCallDelta) delta;
              System.out.println(
                  "[DELTA] FunctionCall: "
                      + fcDelta.name().orElse("unknown")
                      + " args: "
                      + fcDelta.arguments().map(Object::toString).orElse(""));
            } else if (delta != null) {
              System.out.println("[DELTA] " + delta.getClass().getSimpleName());
            }
          } else if (event instanceof ContentStop) {
            ContentStop contentStop = (ContentStop) event;
            System.out.println(
                "[CONTENT_STOP] Index: " + contentStop.index().orElse(-1));
          } else if (event instanceof InteractionStatusUpdate) {
            InteractionStatusUpdate statusEvent = (InteractionStatusUpdate) event;
            System.out.println(
                "[STATUS_UPDATE] Status: " + statusEvent.status().orElse(null));
          } else if (event instanceof ErrorEvent) {
            ErrorEvent errorEvent = (ErrorEvent) event;
            System.out.println("[ERROR] Streaming error occurred");
            errorEvent
                .error()
                .ifPresent(
                    error -> {
                      System.out.println("  Code: " + error.code().orElse("unknown"));
                      System.out.println("  Message: " + error.message().orElse("unknown"));
                    });
          }
        }
      }
      System.out.println("\n[TOTAL EVENTS RECEIVED: " + eventCount2 + "]\n");

      // ===== PART 3: Collecting Full Response =====
      System.out.println("--- PART 3: Collecting Full Response ---\n");

      // Using the convenience overload: createStream(model, text)
      String prompt3 = "What are the first 5 prime numbers?";

      // Log request
      System.out.println("[REQUEST]");
      System.out.println("  Model: " + model);
      System.out.println("  Input: " + prompt3);
      System.out.println();

      StringBuilder fullResponse = new StringBuilder();
      String interactionId = null;

      int eventCount3 = 0;
      try (InteractionEventStream<InteractionSseEvent> stream =
          client.interactions.createStream(model, prompt3)) {
        for (InteractionSseEvent event : stream) {
          eventCount3++;
          // Log each raw event as JSON
          System.out.println("\n[RESPONSE EVENT #" + eventCount3 + "] " + event.getClass().getSimpleName() + ":");
          if (event instanceof JsonSerializable) {
            System.out.println(((JsonSerializable) event).toJson());
          }

          if (event instanceof InteractionEvent) {
            InteractionEvent interactionEvent = (InteractionEvent) event;
            if (interactionEvent.isStart()) {
              interactionId = interactionEvent.interaction().map(i -> i.id()).orElse(null);
            }
          } else if (event instanceof ContentDelta) {
            ContentDelta deltaEvent = (ContentDelta) event;
            Delta delta = deltaEvent.delta().orElse(null);
            if (delta instanceof TextDelta) {
              TextDelta textDelta = (TextDelta) delta;
              fullResponse.append(textDelta.text().orElse(""));
            }
          }
        }
      }

      System.out.println("\n[TOTAL EVENTS RECEIVED: " + eventCount3 + "]");
      System.out.println("Interaction ID: " + interactionId);
      System.out.println("Full response: " + fullResponse.toString());
      System.out.println();

      System.out.println("=== Example completed ===");

    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
      e.printStackTrace();
    }
  }

  private InteractionsStreamingExample() {}
}
