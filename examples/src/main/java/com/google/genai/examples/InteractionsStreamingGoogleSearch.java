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
 * <p>mvn exec:java -Dexec.mainClass="com.google.genai.examples.InteractionsStreamingGoogleSearch"
 */
package com.google.genai.examples;

import com.google.genai.Client;
import com.google.genai.InteractionEventStream;
import com.google.genai.JsonSerializable;
import com.google.genai.types.interactions.CreateInteractionConfig;
import com.google.genai.types.interactions.streaming.ContentDelta;
import com.google.genai.types.interactions.streaming.ContentStart;
import com.google.genai.types.interactions.streaming.ContentStop;
import com.google.genai.types.interactions.streaming.ErrorEvent;
import com.google.genai.types.interactions.streaming.InteractionEvent;
import com.google.genai.types.interactions.streaming.InteractionSseEvent;
import com.google.genai.types.interactions.streaming.delta.Delta;
import com.google.genai.types.interactions.streaming.delta.GoogleSearchCallDelta;
import com.google.genai.types.interactions.streaming.delta.GoogleSearchResultDelta;
import com.google.genai.types.interactions.streaming.delta.TextDelta;
import com.google.genai.types.interactions.tools.GoogleSearch;

/**
 * Example: Streaming Google Search Tool with the Interactions API
 *
 * <p>Demonstrates how to use the GoogleSearch tool with streaming to enable the model
 * to search the web and receive results incrementally.
 *
 * <p>This example shows:
 * <ul>
 *   <li>Enabling Google Search tool with streaming
 *   <li>Handling GoogleSearchCallDelta for search query updates
 *   <li>Handling GoogleSearchResultDelta for search result updates
 *   <li>Receiving text responses incrementally
 * </ul>
 *
 * <p><b>Note:</b> The Interactions API is currently in beta.
 */
public final class InteractionsStreamingGoogleSearch {

  public static void main(String[] args) {
    Client client = new Client();

    System.out.println("=== Interactions API: Streaming Google Search Tool Example ===\n");

    try {
      GoogleSearch searchTool = GoogleSearch.builder().build();

      CreateInteractionConfig config =
          CreateInteractionConfig.builder()
              .model("gemini-3-flash-preview")
              .input("What are the latest developments in quantum computing in 2025?")
              .tools(searchTool)
              .build();

      System.out.println("=== REQUEST ===");
      System.out.println(config.toJson());
      System.out.println();

      System.out.println("Streaming response:\n");

      int eventCount = 0;
      StringBuilder fullResponse = new StringBuilder();
      int searchCallDeltaCount = 0;
      int searchResultDeltaCount = 0;

      try (InteractionEventStream<InteractionSseEvent> stream =
          client.interactions.createStream(config)) {
        for (InteractionSseEvent event : stream) {
          eventCount++;

          // Log each event
          System.out.println("\n[EVENT #" + eventCount + "] " + event.getClass().getSimpleName());
          if (event instanceof JsonSerializable) {
            System.out.println(((JsonSerializable) event).toJson());
          }

          // Handle event types
          if (event instanceof InteractionEvent) {
            InteractionEvent interactionEvent = (InteractionEvent) event;
            if (interactionEvent.isStart()) {
              System.out.println("[START] Interaction started");
              interactionEvent.interaction().ifPresent(
                  interaction -> System.out.println("  ID: " + interaction.id()));
            } else if (interactionEvent.isComplete()) {
              System.out.println("[COMPLETE] Interaction completed");
              interactionEvent.interaction().ifPresent(interaction -> {
                System.out.println("  Final status: " + interaction.status());
                interaction.usage().ifPresent(usage -> {
                  usage.totalTokens().ifPresent(
                      count -> System.out.println("  Total tokens: " + count));
                });
              });
            }
          } else if (event instanceof ContentStart) {
            ContentStart contentStart = (ContentStart) event;
            System.out.println("[CONTENT_START] Index: " + contentStart.index().orElse(-1));
          } else if (event instanceof ContentDelta) {
            ContentDelta deltaEvent = (ContentDelta) event;
            Delta delta = deltaEvent.delta().orElse(null);

            if (delta instanceof GoogleSearchCallDelta) {
              searchCallDeltaCount++;
              GoogleSearchCallDelta searchDelta = (GoogleSearchCallDelta) delta;
              System.out.println("[GOOGLE SEARCH CALL DELTA #" + searchCallDeltaCount + "]");
              searchDelta.id().ifPresent(id -> System.out.println("  ID: " + id));
              searchDelta.arguments().ifPresent(arguments -> {
                System.out.println("  Arguments:");
                arguments.queries().ifPresent(queries ->
                    System.out.println("    Queries: " + queries));
              });
            } else if (delta instanceof GoogleSearchResultDelta) {
              searchResultDeltaCount++;
              GoogleSearchResultDelta resultDelta = (GoogleSearchResultDelta) delta;
              System.out.println("[GOOGLE SEARCH RESULT DELTA #" + searchResultDeltaCount + "]");
              resultDelta.callId().ifPresent(callId -> System.out.println("  Call ID: " + callId));
              resultDelta.isError().ifPresent(isError -> System.out.println("  Is Error: " + isError));
              resultDelta.result().ifPresent(results -> {
                System.out.println("  Results count: " + results.size());
                for (int i = 0; i < results.size(); i++) {
                  System.out.println("  Result[" + i + "]: " + results.get(i).toJson());
                }
              });
            } else if (delta instanceof TextDelta) {
              TextDelta textDelta = (TextDelta) delta;
              String text = textDelta.text().orElse("");
              fullResponse.append(text);
              System.out.print("[TEXT] " + text);
            } else if (delta != null) {
              System.out.println("[DELTA] " + delta.getClass().getSimpleName());
            }
          } else if (event instanceof ContentStop) {
            ContentStop contentStop = (ContentStop) event;
            System.out.println("\n[CONTENT_STOP] Index: " + contentStop.index().orElse(-1));
          } else if (event instanceof ErrorEvent) {
            ErrorEvent errorEvent = (ErrorEvent) event;
            System.out.println("[ERROR] Streaming error occurred");
            errorEvent.error().ifPresent(error -> {
              System.out.println("  Code: " + error.code().orElse("unknown"));
              System.out.println("  Message: " + error.message().orElse("unknown"));
            });
          }
        }
      }

      System.out.println("\n\n[TOTAL EVENTS: " + eventCount + "]");
      System.out.println("[GOOGLE SEARCH CALL DELTAS: " + searchCallDeltaCount + "]");
      System.out.println("[GOOGLE SEARCH RESULT DELTAS: " + searchResultDeltaCount + "]");
      if (fullResponse.length() > 0) {
        System.out.println("[FULL TEXT RESPONSE]: " + fullResponse.toString());
      }

      System.out.println("\n=== Example completed ===");

    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
      e.printStackTrace();
    }
  }

  private InteractionsStreamingGoogleSearch() {}
}
