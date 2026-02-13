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
 * <p>mvn exec:java -Dexec.mainClass="com.google.genai.examples.InteractionsStreamingThoughtSummary"
 */
package com.google.genai.examples;

import com.google.genai.Client;
import com.google.genai.InteractionEventStream;
import com.google.genai.JsonSerializable;
import com.google.genai.types.interactions.CreateInteractionConfig;
import com.google.genai.types.interactions.GenerationConfig;
import com.google.genai.types.interactions.ThinkingLevel;
import com.google.genai.types.interactions.ThinkingSummaries;
import com.google.genai.types.interactions.content.ImageContent;
import com.google.genai.types.interactions.content.TextContent;
import com.google.genai.types.interactions.content.ThoughtSummaryContent;
import com.google.genai.types.interactions.streaming.ContentDelta;
import com.google.genai.types.interactions.streaming.ContentStart;
import com.google.genai.types.interactions.streaming.ContentStop;
import com.google.genai.types.interactions.streaming.ErrorEvent;
import com.google.genai.types.interactions.streaming.InteractionEvent;
import com.google.genai.types.interactions.streaming.InteractionSseEvent;
import com.google.genai.types.interactions.streaming.delta.Delta;
import com.google.genai.types.interactions.streaming.delta.TextDelta;
import com.google.genai.types.interactions.streaming.delta.ThoughtSignatureDelta;
import com.google.genai.types.interactions.streaming.delta.ThoughtSummaryDelta;

/**
 * Example demonstrating streaming ThoughtSummaryDelta with the Interactions API.
 *
 * <p>This example shows how to:
 * <ul>
 *   <li>Configure thinking mode with thinkingLevel and thinkingSummaries
 *   <li>Stream responses and handle ThoughtSummaryDelta events
 *   <li>Process polymorphic ThoughtSummaryContent (TextContent or ImageContent)
 * </ul>
 *
 * <p>ThoughtSummaryDelta.content is a polymorphic type that can be either:
 * <ul>
 *   <li>TextContent - Text-based thought summary
 *   <li>ImageContent - Image-based thought summary (for visual reasoning)
 * </ul>
 *
 * <p>To trigger thought summaries, you must configure:
 * <ul>
 *   <li>thinkingLevel: Controls depth of reasoning (MINIMAL, LOW, MEDIUM, HIGH)
 *   <li>thinkingSummaries: Controls whether summaries are included (AUTO, NONE)
 * </ul>
 *
 * <p>Note: The Interactions API is in beta and subject to change.
 */
public final class InteractionsStreamingThoughtSummary {

  public static void main(String[] args) {
    Client client = new Client();

    System.out.println("=== Interactions API: Streaming ThoughtSummaryDelta Example ===\n");
    System.out.println("This example demonstrates the polymorphic ThoughtSummaryContent type,");
    System.out.println("which can be either TextContent or ImageContent.\n");

    try {
      // ===== PART 1: Complex Reasoning with Thinking Summaries =====
      System.out.println("--- PART 1: Complex Reasoning with ThoughtSummaryDelta ---\n");

      // Configure generation with thinking enabled
      GenerationConfig generationConfig = GenerationConfig.builder()
          .thinkingLevel(new ThinkingLevel(ThinkingLevel.Known.HIGH))
          .thinkingSummaries(new ThinkingSummaries(ThinkingSummaries.Known.AUTO))
          .build();

      CreateInteractionConfig config = CreateInteractionConfig.builder()
          .model("gemini-3-flash-preview")
          .generationConfig(generationConfig)
          .input("Solve this step by step: A train leaves Station A at 9:00 AM traveling at "
              + "60 mph. Another train leaves Station B (120 miles away) at 9:30 AM traveling "
              + "toward Station A at 80 mph. At what time do they meet?")
          .build();

      System.out.println("=== REQUEST ===");
      System.out.println(config.toJson());
      System.out.println();

      System.out.println("Streaming response:\n");

      int eventCount = 0;
      StringBuilder textBuffer = new StringBuilder();
      StringBuilder thoughtSummaryBuffer = new StringBuilder();
      int thoughtSummaryCount = 0;
      int textContentCount = 0;
      int imageContentCount = 0;

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
              System.out.println("[INTERACTION_START] Interaction started");
              interactionEvent.interaction().ifPresent(
                  interaction -> System.out.println("  ID: " + interaction.id()));
            } else if (interactionEvent.isComplete()) {
              System.out.println("[INTERACTION_COMPLETE] Interaction completed");
            }
          } else if (event instanceof ContentStart) {
            System.out.println("[CONTENT_START]");
          } else if (event instanceof ContentDelta) {
            ContentDelta deltaEvent = (ContentDelta) event;
            Delta delta = deltaEvent.delta().orElse(null);

            if (delta instanceof TextDelta) {
              // Regular text output
              TextDelta textDelta = (TextDelta) delta;
              String text = textDelta.text().orElse("");
              textBuffer.append(text);
              System.out.print("[TEXT_DELTA] " + text);

            } else if (delta instanceof ThoughtSignatureDelta) {
              // Thought signature (cryptographic proof of thinking)
              ThoughtSignatureDelta sigDelta = (ThoughtSignatureDelta) delta;
              System.out.println("[THOUGHT_SIGNATURE_DELTA]");
              sigDelta.signature().ifPresent(sig ->
                  System.out.println("  Signature: " + sig.substring(0, Math.min(50, sig.length()))
                      + (sig.length() > 50 ? "..." : "")));

            } else if (delta instanceof ThoughtSummaryDelta) {
              // Thought summary - THIS IS THE KEY PART demonstrating the polymorphic type
              thoughtSummaryCount++;
              ThoughtSummaryDelta summaryDelta = (ThoughtSummaryDelta) delta;

              // content() returns Optional<ThoughtSummaryContent>
              // ThoughtSummaryContent is a polymorphic interface that can be:
              // - TextContent: text-based thought summary
              // - ImageContent: image-based thought summary (for visual reasoning)
              ThoughtSummaryContent content = summaryDelta.content().orElse(null);

              if (content instanceof TextContent) {
                textContentCount++;
                TextContent textContent = (TextContent) content;
                String text = textContent.text().orElse("");
                thoughtSummaryBuffer.append(text);
                System.out.println("[THOUGHT_SUMMARY_DELTA - TextContent]");
                System.out.println("  Text: " + text);

                // TextContent may also have annotations
                textContent.annotations().ifPresent(annotations -> {
                  System.out.println("  Annotations: " + annotations.size() + " annotation(s)");
                });

              } else if (content instanceof ImageContent) {
                imageContentCount++;
                ImageContent imageContent = (ImageContent) content;
                System.out.println("[THOUGHT_SUMMARY_DELTA - ImageContent]");

                // ImageContent can have data (base64) or uri
                imageContent.mimeType().ifPresent(mimeType ->
                    System.out.println("  MIME Type: " + mimeType));
                imageContent.data().ifPresent(data ->
                    System.out.println("  Data: " + data.length() + " chars (base64)"));
                imageContent.uri().ifPresent(uri ->
                    System.out.println("  URI: " + uri));
                imageContent.resolution().ifPresent(resolution ->
                    System.out.println("  Resolution: " + resolution));

              } else if (content != null) {
                // Handle any future content types gracefully
                System.out.println("[THOUGHT_SUMMARY_DELTA - " + content.getClass().getSimpleName()
                    + "]");
                System.out.println("  (Unknown content type)");
              } else {
                System.out.println("[THOUGHT_SUMMARY_DELTA - null content]");
              }
            }

          } else if (event instanceof ContentStop) {
            System.out.println("\n[CONTENT_STOP]");
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

      // Summary
      System.out.println("\n\n========== SUMMARY ==========");
      System.out.println("Total events received: " + eventCount);
      System.out.println();
      System.out.println("ThoughtSummaryDelta count: " + thoughtSummaryCount);
      System.out.println("  - TextContent instances: " + textContentCount);
      System.out.println("  - ImageContent instances: " + imageContentCount);
      System.out.println();

      if (thoughtSummaryBuffer.length() > 0) {
        System.out.println("Thought Summary Text:");
        System.out.println(thoughtSummaryBuffer.toString());
        System.out.println();
      }

      System.out.println("Final Text Response:");
      System.out.println(textBuffer.toString());
      System.out.println();

      // ===== PART 2: Logic Puzzle with Thinking =====
      System.out.println("\n--- PART 2: Logic Puzzle with ThoughtSummaryDelta ---\n");

      GenerationConfig generationConfig2 = GenerationConfig.builder()
          .thinkingLevel(new ThinkingLevel(ThinkingLevel.Known.HIGH))
          .thinkingSummaries(new ThinkingSummaries(ThinkingSummaries.Known.AUTO))
          .build();

      CreateInteractionConfig config2 = CreateInteractionConfig.builder()
          .model("gemini-3-flash-preview")
          .generationConfig(generationConfig2)
          .input("Solve this logic puzzle: "
              + "Five houses in a row are painted different colors. "
              + "The green house is immediately to the left of the white house. "
              + "The owner of the red house owns a dog. "
              + "The owner of the yellow house owns a bird. "
              + "The green house owner drinks coffee. "
              + "What color house does the bird owner live in?")
          .build();

      System.out.println("=== REQUEST ===");
      System.out.println(config2.toJson());
      System.out.println();

      System.out.println("Streaming response:\n");

      int eventCount2 = 0;
      StringBuilder textBuffer2 = new StringBuilder();
      int thoughtSummaryCount2 = 0;

      try (InteractionEventStream<InteractionSseEvent> stream =
          client.interactions.createStream(config2)) {
        for (InteractionSseEvent event : stream) {
          eventCount2++;

          if (event instanceof ContentDelta) {
            ContentDelta deltaEvent = (ContentDelta) event;
            Delta delta = deltaEvent.delta().orElse(null);

            if (delta instanceof TextDelta) {
              TextDelta textDelta = (TextDelta) delta;
              String text = textDelta.text().orElse("");
              textBuffer2.append(text);
              System.out.print(text);

            } else if (delta instanceof ThoughtSummaryDelta) {
              thoughtSummaryCount2++;
              ThoughtSummaryDelta summaryDelta = (ThoughtSummaryDelta) delta;
              ThoughtSummaryContent content = summaryDelta.content().orElse(null);

              if (content instanceof TextContent) {
                TextContent textContent = (TextContent) content;
                System.out.println("\n[THOUGHT] " + textContent.text().orElse(""));
              } else if (content instanceof ImageContent) {
                System.out.println("\n[THOUGHT - IMAGE] Visual reasoning step received");
              }
            }
          }
        }
      }

      System.out.println("\n\n========== PART 2 SUMMARY ==========");
      System.out.println("Total events: " + eventCount2);
      System.out.println("ThoughtSummaryDelta count: " + thoughtSummaryCount2);
      System.out.println();

      System.out.println("=== Example completed ===");

    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
      e.printStackTrace();
    }
  }

  private InteractionsStreamingThoughtSummary() {}
}
