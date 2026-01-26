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
 * <p>mvn exec:java -Dexec.mainClass="com.google.genai.examples.InteractionsStreamingToolResults"
 */
package com.google.genai.examples;

import com.google.genai.Client;
import com.google.genai.JsonSerializable;
import com.google.genai.InteractionEventStream;
import com.google.genai.types.Schema;
import com.google.genai.types.interactions.CreateInteractionConfig;
import com.google.genai.types.interactions.streaming.ContentDelta;
import com.google.genai.types.interactions.streaming.InteractionSseEvent;
import com.google.genai.types.interactions.streaming.delta.Delta;
import com.google.genai.types.interactions.streaming.delta.FunctionResultDelta;
import com.google.genai.types.interactions.streaming.delta.GoogleSearchResultDelta;
import com.google.genai.types.interactions.streaming.delta.TextDelta;
import com.google.genai.types.interactions.streaming.delta.UrlContextResultDelta;
import com.google.genai.types.interactions.tools.Function;
import com.google.genai.types.interactions.tools.GoogleSearch;
import com.google.genai.types.interactions.tools.UrlContext;
import java.util.Map;

/**
 * Example: Streaming Tool Result Deltas
 *
 * <p>Demonstrates streaming for tool result delta types:
 *
 * <ul>
 *   <li>GoogleSearchResultDelta - Incremental search result updates
 *   <li>FunctionResultDelta - Incremental function result updates (bonus)
 *   <li>UrlContextResultDelta - Incremental URL context result updates (bonus)
 * </ul>
 *
 * <p>This example shows:
 *
 * <ul>
 *   <li>Part 1: Google Search with streaming results
 *   <li>Part 2: URL Context with streaming results
 *   <li>Part 3: Function results (if applicable)
 * </ul>
 *
 * <p>Note: The Interactions API is in beta and subject to change.
 */
public final class InteractionsStreamingToolResults {

  public static void main(String[] args) {
    Client client = new Client();

    System.out.println("=== Interactions API: Streaming Tool Result Deltas ===\n");

    try {
      // ===== PART 1: Google Search Results Streaming =====
      System.out.println("--- PART 1: Google Search Results Streaming (GoogleSearchResultDelta) ---\n");

      GoogleSearch searchTool = GoogleSearch.builder().build();

      CreateInteractionConfig searchConfig =
          CreateInteractionConfig.builder()
              .model("gemini-3-flash-preview")
              .input("Search for the top 5 AI breakthroughs in 2025")
              .tools(searchTool)
              .build();

      System.out.println("[REQUEST] CreateInteractionConfig:");
      System.out.println(searchConfig.toJson());
      System.out.println();

      System.out.println("Streaming Google Search results:\n");

      int eventCount1 = 0;
      StringBuilder textBuffer1 = new StringBuilder();
      int searchResultDeltaCount = 0;

      try (InteractionEventStream<InteractionSseEvent> stream =
          client.interactions.createStream(searchConfig)) {
        for (InteractionSseEvent event : stream) {
          eventCount1++;

          // Log raw event
          System.out.println(
              "\n[EVENT #" + eventCount1 + "] " + event.getClass().getSimpleName());
          if (event instanceof JsonSerializable) {
            System.out.println(((JsonSerializable) event).toJson());
          }

          // Handle deltas
          if (event instanceof ContentDelta) {
            ContentDelta deltaEvent = (ContentDelta) event;
            Delta delta = deltaEvent.delta().orElse(null);

            if (delta instanceof GoogleSearchResultDelta) {
              searchResultDeltaCount++;
              GoogleSearchResultDelta searchResultDelta = (GoogleSearchResultDelta) delta;
              System.out.println("[GOOGLE SEARCH RESULT DELTA #" + searchResultDeltaCount + "]");
              searchResultDelta.callId().ifPresent(id -> System.out.println("  Call ID: " + id));
              searchResultDelta.result().ifPresent(results -> {
                System.out.println("  Results count: " + results.size());
                results.forEach(r -> System.out.println("    - " + r.toJson()));
              });
              searchResultDelta.signature().ifPresent(sig -> System.out.println("  Signature: " + sig));
              searchResultDelta.isError().ifPresent(isError ->
                  System.out.println("  Is Error: " + isError));
            } else if (delta instanceof TextDelta) {
              TextDelta textDelta = (TextDelta) delta;
              String text = textDelta.text().orElse("");
              textBuffer1.append(text);
              System.out.print("[TEXT DELTA] " + text);
            }
          }
        }
      }

      System.out.println("\n\n[TOTAL EVENTS: " + eventCount1 + "]");
      System.out.println("[GOOGLE SEARCH RESULT DELTAS: " + searchResultDeltaCount + "]");
      if (textBuffer1.length() > 0) {
        System.out.println("[FULL TEXT]: " + textBuffer1.toString());
      }
      System.out.println();

      // ===== PART 2: URL Context Results Streaming =====
      System.out.println("--- PART 2: URL Context Results Streaming (UrlContextResultDelta) ---\n");

      UrlContext urlTool = UrlContext.builder().build();

      CreateInteractionConfig urlConfig =
          CreateInteractionConfig.builder()
              .model("gemini-3-flash-preview")
              .input("Fetch content from https://www.wikipedia.org/wiki/Machine_learning and summarize it")
              .tools(urlTool)
              .build();

      System.out.println("[REQUEST] CreateInteractionConfig:");
      System.out.println(urlConfig.toJson());
      System.out.println();

      System.out.println("Streaming URL context results:\n");

      int eventCount2 = 0;
      StringBuilder textBuffer2 = new StringBuilder();
      int urlResultDeltaCount = 0;

      try (InteractionEventStream<InteractionSseEvent> stream =
          client.interactions.createStream(urlConfig)) {
        for (InteractionSseEvent event : stream) {
          eventCount2++;

          // Log raw event
          System.out.println(
              "\n[EVENT #" + eventCount2 + "] " + event.getClass().getSimpleName());
          if (event instanceof JsonSerializable) {
            System.out.println(((JsonSerializable) event).toJson());
          }

          // Handle deltas
          if (event instanceof ContentDelta) {
            ContentDelta deltaEvent = (ContentDelta) event;
            Delta delta = deltaEvent.delta().orElse(null);

            if (delta instanceof UrlContextResultDelta) {
              urlResultDeltaCount++;
              UrlContextResultDelta urlResultDelta = (UrlContextResultDelta) delta;
              System.out.println("[URL CONTEXT RESULT DELTA #" + urlResultDeltaCount + "]");
              urlResultDelta.callId().ifPresent(id -> System.out.println("  Call ID: " + id));
              urlResultDelta.result().ifPresent(results -> {
                System.out.println("  Results count: " + results.size());
                results.forEach(r -> System.out.println("    - " + r.toJson()));
              });
              urlResultDelta.signature().ifPresent(sig -> System.out.println("  Signature: " + sig));
              urlResultDelta.isError().ifPresent(isError ->
                  System.out.println("  Is Error: " + isError));
            } else if (delta instanceof TextDelta) {
              TextDelta textDelta = (TextDelta) delta;
              String text = textDelta.text().orElse("");
              textBuffer2.append(text);
              System.out.print("[TEXT DELTA] " + text);
            }
          }
        }
      }

      System.out.println("\n\n[TOTAL EVENTS: " + eventCount2 + "]");
      System.out.println("[URL CONTEXT RESULT DELTAS: " + urlResultDeltaCount + "]");
      if (textBuffer2.length() > 0) {
        System.out.println("[FULL TEXT]: " + textBuffer2.toString());
      }
      System.out.println();

      // ===== PART 3: Function Results Streaming (Bonus) =====
      System.out.println("--- PART 3: Function Results Streaming (FunctionResultDelta - Bonus) ---\n");

      Function calculatorTool =
          Function.builder()
              .name("calculate")
              .description("Perform a calculation")
              .parameters(
                  Schema.builder()
                      .type("object")
                      .properties(
                          Map.of(
                              "expression",
                              Schema.builder()
                                  .type("string")
                                  .description("The mathematical expression")
                                  .build()))
                      .required("expression")
                      .build())
              .build();

      CreateInteractionConfig functionConfig =
          CreateInteractionConfig.builder()
              .model("gemini-3-flash-preview")
              .input("Calculate 25 * 4 + 12")
              .tools(calculatorTool)
              .build();

      System.out.println("[REQUEST] CreateInteractionConfig:");
      System.out.println(functionConfig.toJson());
      System.out.println();

      System.out.println("Streaming function results:\n");

      int eventCount3 = 0;
      StringBuilder textBuffer3 = new StringBuilder();
      int functionResultDeltaCount = 0;

      try (InteractionEventStream<InteractionSseEvent> stream =
          client.interactions.createStream(functionConfig)) {
        for (InteractionSseEvent event : stream) {
          eventCount3++;

          // Log raw event
          System.out.println(
              "\n[EVENT #" + eventCount3 + "] " + event.getClass().getSimpleName());
          if (event instanceof JsonSerializable) {
            System.out.println(((JsonSerializable) event).toJson());
          }

          // Handle deltas
          if (event instanceof ContentDelta) {
            ContentDelta deltaEvent = (ContentDelta) event;
            Delta delta = deltaEvent.delta().orElse(null);

            if (delta instanceof FunctionResultDelta) {
              functionResultDeltaCount++;
              FunctionResultDelta funcResultDelta = (FunctionResultDelta) delta;
              System.out.println("[FUNCTION RESULT DELTA #" + functionResultDeltaCount + "]");
              funcResultDelta.callId().ifPresent(id -> System.out.println("  Call ID: " + id));
              funcResultDelta.name().ifPresent(name -> System.out.println("  Name: " + name));
              funcResultDelta.result().ifPresent(result -> System.out.println("  Result: " + result));
            } else if (delta instanceof TextDelta) {
              TextDelta textDelta = (TextDelta) delta;
              String text = textDelta.text().orElse("");
              textBuffer3.append(text);
              System.out.print("[TEXT DELTA] " + text);
            }
          }
        }
      }

      System.out.println("\n\n[TOTAL EVENTS: " + eventCount3 + "]");
      System.out.println("[FUNCTION RESULT DELTAS: " + functionResultDeltaCount + "]");
      if (textBuffer3.length() > 0) {
        System.out.println("[FULL TEXT]: " + textBuffer3.toString());
      }
      System.out.println();

      System.out.println("=== Example completed ===");

    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
      e.printStackTrace();
    }
  }

  private InteractionsStreamingToolResults() {}
}
