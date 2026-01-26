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
 * <p>mvn exec:java -Dexec.mainClass="com.google.genai.examples.InteractionsStreamingTextAndThought"
 */
package com.google.genai.examples;

import com.google.genai.Client;
import com.google.genai.JsonSerializable;
import com.google.genai.InteractionEventStream;
import com.google.genai.types.interactions.content.ImageContent;
import com.google.genai.types.interactions.content.TextContent;
import com.google.genai.types.interactions.content.ThoughtSummaryContent;
import com.google.genai.types.interactions.streaming.ContentDelta;
import com.google.genai.types.interactions.streaming.InteractionSseEvent;
import com.google.genai.types.interactions.streaming.delta.Delta;
import com.google.genai.types.interactions.streaming.delta.TextDelta;
import com.google.genai.types.interactions.streaming.delta.ThoughtSignatureDelta;
import com.google.genai.types.interactions.streaming.delta.ThoughtSummaryDelta;

/**
 * Example: Streaming Text and Thought Deltas
 *
 * <p>Demonstrates streaming for text and thought-related delta types:
 *
 * <ul>
 *   <li>TextDelta - Incremental text generation
 *   <li>ThoughtSignatureDelta - Thought process metadata
 *   <li>ThoughtSummaryDelta - Summary of reasoning
 * </ul>
 *
 * <p>This example shows:
 *
 * <ul>
 *   <li>Part 1: Basic text streaming (counting)
 *   <li>Part 2: Story generation with incremental text
 *   <li>Part 3: Complex reasoning with thought deltas
 * </ul>
 *
 * <p>Note: The Interactions API is in beta and subject to change.
 */
public final class InteractionsStreamingTextAndThought {

  public static void main(String[] args) {
    Client client = new Client();

    System.out.println("=== Interactions API: Streaming Text and Thought Deltas ===\n");

    try {
      // ===== PART 1: Basic Text Streaming =====
      System.out.println("--- PART 1: Basic Text Streaming (TextDelta) ---\n");

      // Using the convenience overload: createStream(model, text)
      String model = "gemini-3-flash-preview";
      String prompt1 = "Count from 1 to 10, saying each number on a new line.";

      System.out.println("[REQUEST]");
      System.out.println("  Model: " + model);
      System.out.println("  Input: " + prompt1);
      System.out.println();

      System.out.println("Streaming response:\n");

      int eventCount1 = 0;
      StringBuilder textBuffer1 = new StringBuilder();

      try (InteractionEventStream<InteractionSseEvent> stream =
          client.interactions.createStream(model, prompt1)) {
        for (InteractionSseEvent event : stream) {
          eventCount1++;

          // Log raw event
          System.out.println(
              "\n[EVENT #" + eventCount1 + "] " + event.getClass().getSimpleName());
          if (event instanceof JsonSerializable) {
            System.out.println(((JsonSerializable) event).toJson());
          }

          // Handle TextDelta
          if (event instanceof ContentDelta) {
            ContentDelta deltaEvent = (ContentDelta) event;
            Delta delta = deltaEvent.delta().orElse(null);

            if (delta instanceof TextDelta) {
              TextDelta textDelta = (TextDelta) delta;
              String text = textDelta.text().orElse("");
              textBuffer1.append(text);
              System.out.print("[TEXT DELTA] " + text);
            }
          }
        }
      }

      System.out.println("\n\n[TOTAL EVENTS: " + eventCount1 + "]");
      System.out.println("[FULL TEXT]: " + textBuffer1.toString());
      System.out.println();

      // ===== PART 2: Story Generation with Text Streaming =====
      System.out.println("--- PART 2: Story Generation (TextDelta) ---\n");

      String prompt2 = "Write a very short story (2-3 sentences) about a robot learning to paint.";

      System.out.println("[REQUEST]");
      System.out.println("  Model: " + model);
      System.out.println("  Input: " + prompt2);
      System.out.println();

      System.out.println("Streaming story:\n");

      int eventCount2 = 0;
      StringBuilder textBuffer2 = new StringBuilder();

      try (InteractionEventStream<InteractionSseEvent> stream =
          client.interactions.createStream(model, prompt2)) {
        for (InteractionSseEvent event : stream) {
          eventCount2++;

          // Log raw event
          System.out.println(
              "\n[EVENT #" + eventCount2 + "] " + event.getClass().getSimpleName());
          if (event instanceof JsonSerializable) {
            System.out.println(((JsonSerializable) event).toJson());
          }

          // Handle TextDelta
          if (event instanceof ContentDelta) {
            ContentDelta deltaEvent = (ContentDelta) event;
            Delta delta = deltaEvent.delta().orElse(null);

            if (delta instanceof TextDelta) {
              TextDelta textDelta = (TextDelta) delta;
              String text = textDelta.text().orElse("");
              textBuffer2.append(text);
              System.out.print("[TEXT DELTA] " + text);
            }
          }
        }
      }

      System.out.println("\n\n[TOTAL EVENTS: " + eventCount2 + "]");
      System.out.println("[FULL STORY]: " + textBuffer2.toString());
      System.out.println();

      // ===== PART 3: Complex Reasoning with Thought Deltas =====
      System.out.println("--- PART 3: Complex Reasoning (ThoughtSummaryDelta, ThoughtSignatureDelta) ---\n");

      String prompt3 = "Think step by step: Solve the equation 3x + 7 = 22. Show your reasoning.";

      System.out.println("[REQUEST]");
      System.out.println("  Model: " + model);
      System.out.println("  Input: " + prompt3);
      System.out.println();

      System.out.println("Streaming reasoning:\n");

      int eventCount3 = 0;
      StringBuilder textBuffer3 = new StringBuilder();
      StringBuilder thoughtBuffer = new StringBuilder();
      int thoughtCount = 0;

      try (InteractionEventStream<InteractionSseEvent> stream =
          client.interactions.createStream(model, prompt3)) {
        for (InteractionSseEvent event : stream) {
          eventCount3++;

          // Log raw event
          System.out.println(
              "\n[EVENT #" + eventCount3 + "] " + event.getClass().getSimpleName());
          if (event instanceof JsonSerializable) {
            System.out.println(((JsonSerializable) event).toJson());
          }

          // Handle different delta types
          if (event instanceof ContentDelta) {
            ContentDelta deltaEvent = (ContentDelta) event;
            Delta delta = deltaEvent.delta().orElse(null);

            if (delta instanceof TextDelta) {
              TextDelta textDelta = (TextDelta) delta;
              String text = textDelta.text().orElse("");
              textBuffer3.append(text);
              System.out.print("[TEXT DELTA] " + text);
            } else if (delta instanceof ThoughtSignatureDelta) {
              ThoughtSignatureDelta thoughtSig = (ThoughtSignatureDelta) delta;
              thoughtCount++;
              System.out.println("[THOUGHT SIGNATURE DELTA] Received thought signature metadata");
              thoughtSig.signature().ifPresent(sig -> System.out.println("  Signature: " + sig));
            } else if (delta instanceof ThoughtSummaryDelta) {
              ThoughtSummaryDelta thoughtSummary = (ThoughtSummaryDelta) delta;
              ThoughtSummaryContent content = thoughtSummary.content().orElse(null);
              if (content instanceof TextContent) {
                TextContent textContent = (TextContent) content;
                String text = textContent.text().orElse("");
                thoughtBuffer.append(text);
                System.out.println("[THOUGHT SUMMARY DELTA - TEXT] " + text);
              } else if (content instanceof ImageContent) {
                ImageContent imageContent = (ImageContent) content;
                String mimeType = imageContent.mimeType().map(Object::toString).orElse("unknown");
                System.out.println("[THOUGHT SUMMARY DELTA - IMAGE] MIME: " + mimeType);
                imageContent.data().ifPresent(data ->
                    System.out.println("  Data length: " + data.length() + " chars"));
                imageContent.uri().ifPresent(uri ->
                    System.out.println("  URI: " + uri));
              } else if (content != null) {
                System.out.println("[THOUGHT SUMMARY DELTA] Unknown content type: "
                    + content.getClass().getSimpleName());
              }
            }
          }
        }
      }

      System.out.println("\n\n[TOTAL EVENTS: " + eventCount3 + "]");
      System.out.println("[THOUGHT COUNT: " + thoughtCount + "]");
      if (thoughtBuffer.length() > 0) {
        System.out.println("[THOUGHT SUMMARIES]: " + thoughtBuffer.toString());
      }
      System.out.println("[FULL TEXT]: " + textBuffer3.toString());
      System.out.println();

      System.out.println("=== Example completed ===");

    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
      e.printStackTrace();
    }
  }

  private InteractionsStreamingTextAndThought() {}
}
