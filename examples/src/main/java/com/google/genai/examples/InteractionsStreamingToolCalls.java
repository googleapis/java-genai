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
 * <p>mvn exec:java -Dexec.mainClass="com.google.genai.examples.InteractionsStreamingToolCalls"
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
import com.google.genai.types.interactions.streaming.delta.FunctionCallDelta;
import com.google.genai.types.interactions.streaming.delta.GoogleSearchCallDelta;
import com.google.genai.types.interactions.streaming.delta.TextDelta;
import com.google.genai.types.interactions.streaming.delta.UrlContextCallDelta;
import com.google.genai.types.interactions.tools.Function;
import com.google.genai.types.interactions.tools.GoogleSearch;
import com.google.genai.types.interactions.tools.UrlContext;
import java.util.Map;

/**
 * Example: Streaming Tool Call Deltas
 *
 * <p>Demonstrates streaming for tool invocation delta types:
 *
 * <ul>
 *   <li>FunctionCallDelta - Incremental function call updates
 *   <li>UrlContextCallDelta - Incremental URL context fetch updates
 *   <li>GoogleSearchCallDelta - Incremental Google Search query updates
 * </ul>
 *
 * <p>This example shows:
 *
 * <ul>
 *   <li>Part 1: Function calling streaming (weather example)
 *   <li>Part 2: URL context fetching streaming
 *   <li>Part 3: Google Search streaming
 * </ul>
 *
 * <p>Note: The Interactions API is in beta and subject to change.
 */
public final class InteractionsStreamingToolCalls {

  public static void main(String[] args) {
    Client client = new Client();

    System.out.println("=== Interactions API: Streaming Tool Call Deltas ===\n");

    try {
      // ===== PART 1: Function Calling Streaming =====
      System.out.println("--- PART 1: Function Calling Streaming (FunctionCallDelta) ---\n");

      Function weatherTool =
          Function.builder()
              .name("get_weather")
              .description("Get the current weather for a location")
              .parameters(
                  Schema.builder()
                      .type("object")
                      .properties(
                          Map.of(
                              "location",
                              Schema.builder()
                                  .type("string")
                                  .description("The city name")
                                  .build()))
                      .required("location")
                      .build())
              .build();

      CreateInteractionConfig functionConfig =
          CreateInteractionConfig.builder()
              .model("gemini-3-flash-preview")
              .input("What's the weather in Paris and London?")
              .tools(weatherTool)
              .build();

      System.out.println("[REQUEST] CreateInteractionConfig:");
      System.out.println(functionConfig.toJson());
      System.out.println();

      System.out.println("Streaming function calls:\n");

      int eventCount1 = 0;
      StringBuilder textBuffer1 = new StringBuilder();
      int functionCallDeltaCount = 0;

      try (InteractionEventStream<InteractionSseEvent> stream =
          client.interactions.createStream(functionConfig)) {
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

            if (delta instanceof FunctionCallDelta) {
              functionCallDeltaCount++;
              FunctionCallDelta fcDelta = (FunctionCallDelta) delta;
              System.out.println("[FUNCTION CALL DELTA #" + functionCallDeltaCount + "]");
              fcDelta.id().ifPresent(id -> System.out.println("  ID: " + id));
              fcDelta.name().ifPresent(name -> System.out.println("  Name: " + name));
              fcDelta.arguments().ifPresent(arguments -> System.out.println("  Arguments: " + arguments));
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
      System.out.println("[FUNCTION CALL DELTAS: " + functionCallDeltaCount + "]");
      if (textBuffer1.length() > 0) {
        System.out.println("[FULL TEXT]: " + textBuffer1.toString());
      }
      System.out.println();

      // ===== PART 2: URL Context Streaming =====
      System.out.println("--- PART 2: URL Context Streaming (UrlContextCallDelta) ---\n");

      UrlContext urlTool = UrlContext.builder().build();

      CreateInteractionConfig urlConfig =
          CreateInteractionConfig.builder()
              .model("gemini-3-flash-preview")
              .input("Fetch and summarize https://www.wikipedia.org/wiki/Artificial_intelligence")
              .tools(urlTool)
              .build();

      System.out.println("[REQUEST] CreateInteractionConfig:");
      System.out.println(urlConfig.toJson());
      System.out.println();

      System.out.println("Streaming URL context calls:\n");

      int eventCount2 = 0;
      StringBuilder textBuffer2 = new StringBuilder();
      int urlCallDeltaCount = 0;

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

            if (delta instanceof UrlContextCallDelta) {
              urlCallDeltaCount++;
              UrlContextCallDelta urlDelta = (UrlContextCallDelta) delta;
              System.out.println("[URL CONTEXT CALL DELTA #" + urlCallDeltaCount + "]");
              urlDelta.id().ifPresent(id -> System.out.println("  ID: " + id));
              urlDelta.arguments().ifPresent(arguments -> System.out.println("  Arguments: " + arguments));
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
      System.out.println("[URL CONTEXT CALL DELTAS: " + urlCallDeltaCount + "]");
      if (textBuffer2.length() > 0) {
        System.out.println("[FULL TEXT]: " + textBuffer2.toString());
      }
      System.out.println();

      // ===== PART 3: Google Search Streaming =====
      System.out.println("--- PART 3: Google Search Streaming (GoogleSearchCallDelta) ---\n");

      GoogleSearch searchTool = GoogleSearch.builder().build();

      CreateInteractionConfig searchConfig =
          CreateInteractionConfig.builder()
              .model("gemini-3-flash-preview")
              .input("What are the latest developments in quantum computing in 2025?")
              .tools(searchTool)
              .build();

      System.out.println("[REQUEST] CreateInteractionConfig:");
      System.out.println(searchConfig.toJson());
      System.out.println();

      System.out.println("Streaming Google Search calls:\n");

      int eventCount3 = 0;
      StringBuilder textBuffer3 = new StringBuilder();
      int searchCallDeltaCount = 0;

      try (InteractionEventStream<InteractionSseEvent> stream =
          client.interactions.createStream(searchConfig)) {
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

            if (delta instanceof GoogleSearchCallDelta) {
              searchCallDeltaCount++;
              GoogleSearchCallDelta searchDelta = (GoogleSearchCallDelta) delta;
              System.out.println("[GOOGLE SEARCH CALL DELTA #" + searchCallDeltaCount + "]");
              searchDelta.id().ifPresent(id -> System.out.println("  ID: " + id));
              searchDelta.arguments().ifPresent(arguments -> System.out.println("  Arguments: " + arguments));
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
      System.out.println("[GOOGLE SEARCH CALL DELTAS: " + searchCallDeltaCount + "]");
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

  private InteractionsStreamingToolCalls() {}
}
