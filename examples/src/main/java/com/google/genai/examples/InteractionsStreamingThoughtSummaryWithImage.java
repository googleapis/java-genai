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
 * <p>mvn exec:java
 * -Dexec.mainClass="com.google.genai.examples.InteractionsStreamingThoughtSummaryWithImage"
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
 * Example attempting to trigger ImageContent in ThoughtSummaryDelta.
 *
 * <p>This example explores whether the API returns ImageContent in thought summaries when:
 * <ul>
 *   <li>Providing image input and asking for visual reasoning
 *   <li>Asking the model to visualize or diagram its thinking process
 *   <li>Using complex visual analysis tasks
 * </ul>
 *
 * <p>According to the OpenAPI spec, ThoughtSummaryDelta.content can be either:
 * <ul>
 *   <li>TextContent - Text-based thought summary
 *   <li>ImageContent - Image-based thought summary (for visual reasoning)
 * </ul>
 *
 * <p>Note: The Interactions API is in beta and ImageContent in thought summaries may not
 * be fully available yet.
 */
public final class InteractionsStreamingThoughtSummaryWithImage {

  public static void main(String[] args) {
    Client client = new Client();

    System.out.println("=== Interactions API: ThoughtSummary with Image Input ===\n");
    System.out.println("Testing whether ImageContent appears in ThoughtSummaryDelta\n");

    try {
      // ===== Simple VIBGYOR Colors Request =====
      System.out.println("--- Requesting VIBGYOR Rainbow Colors as Images ---\n");

      GenerationConfig generationConfig = GenerationConfig.builder()
          .thinkingLevel(new ThinkingLevel(ThinkingLevel.Known.HIGH))
          .thinkingSummaries(new ThinkingSummaries(ThinkingSummaries.Known.AUTO))
          .build();

      // Note: Commented alternative prompt for generating VIBGYOR rainbow colors
      // TextContent prompt = TextContent.of("Generate images showing the 7 VIBGYOR rainbow colors...");
      TextContent prompt = TextContent.of("Generate simple image showing RED color");

      CreateInteractionConfig config = CreateInteractionConfig.builder()
          .model("gemini-3-pro-image-preview")
          .generationConfig(generationConfig)
          .inputFromContents(prompt)
          .build();

      System.out.println("=== REQUEST ===");
      System.out.println(config.toJson());
      System.out.println();

      runStreamingAnalysis(client, config, "VIBGYOR Colors");

      System.out.println("\n=== All tests completed ===");

    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
      e.printStackTrace();
    }
  }

  private static void runStreamingAnalysis(Client client, CreateInteractionConfig config,
      String testName) {
    System.out.println("Streaming response for: " + testName + "\n");

    int eventCount = 0;
    StringBuilder textBuffer = new StringBuilder();
    int thoughtSummaryCount = 0;
    int textContentCount = 0;
    int imageContentCount = 0;

    try (InteractionEventStream<InteractionSseEvent> stream =
        client.interactions.createStream(config)) {
      for (InteractionSseEvent event : stream) {
        eventCount++;

        // Handle event types
        if (event instanceof InteractionEvent) {
          InteractionEvent interactionEvent = (InteractionEvent) event;
          if (interactionEvent.isStart()) {
            System.out.println("[INTERACTION_START]");
            interactionEvent.interaction().ifPresent(
                interaction -> System.out.println("  ID: " + interaction.id()));
          } else if (interactionEvent.isComplete()) {
            System.out.println("[INTERACTION_COMPLETE]");
          }
        } else if (event instanceof ContentStart) {
          ContentStart contentStart = (ContentStart) event;
          System.out.println("[CONTENT_START] index=" + contentStart.index().orElse(-1));
        } else if (event instanceof ContentDelta) {
          ContentDelta deltaEvent = (ContentDelta) event;
          Delta delta = deltaEvent.delta().orElse(null);

          if (delta instanceof TextDelta) {
            TextDelta textDelta = (TextDelta) delta;
            String text = textDelta.text().orElse("");
            textBuffer.append(text);
            // Don't print every text delta to reduce noise

          } else if (delta instanceof ThoughtSignatureDelta) {
            System.out.println("[THOUGHT_SIGNATURE_DELTA] Received");

          } else if (delta instanceof ThoughtSummaryDelta) {
            thoughtSummaryCount++;
            ThoughtSummaryDelta summaryDelta = (ThoughtSummaryDelta) delta;
            ThoughtSummaryContent content = summaryDelta.content().orElse(null);

            if (content instanceof TextContent) {
              textContentCount++;
              TextContent textContent = (TextContent) content;
              String text = textContent.text().orElse("");
              String preview = text.length() > 100 ? text.substring(0, 100) + "..." : text;
              System.out.println("[THOUGHT_SUMMARY - TextContent] " + preview);

            } else if (content instanceof ImageContent) {
              imageContentCount++;
              ImageContent imageContent = (ImageContent) content;
              System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
              System.out.println("[THOUGHT_SUMMARY - ImageContent] *** FOUND IMAGE IN THOUGHT! ***");
              System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

              // Log all details of the image
              System.out.println("  Full JSON: " + ((JsonSerializable) imageContent).toJson());
              imageContent.mimeType().ifPresent(mimeType ->
                  System.out.println("  MIME Type: " + mimeType));
              imageContent.data().ifPresent(data ->
                  System.out.println("  Data: " + data.length() + " chars (base64)"));
              imageContent.uri().ifPresent(uri ->
                  System.out.println("  URI: " + uri));
              imageContent.resolution().ifPresent(resolution ->
                  System.out.println("  Resolution: " + resolution));

            } else if (content != null) {
              System.out.println("[THOUGHT_SUMMARY - Unknown: " + content.getClass().getSimpleName()
                  + "]");
            } else {
              System.out.println("[THOUGHT_SUMMARY - null content]");
            }
          }

        } else if (event instanceof ContentStop) {
          System.out.println("[CONTENT_STOP]");
        } else if (event instanceof ErrorEvent) {
          ErrorEvent errorEvent = (ErrorEvent) event;
          System.out.println("[ERROR]");
          errorEvent.error().ifPresent(error -> {
            System.out.println("  Code: " + error.code().orElse("unknown"));
            System.out.println("  Message: " + error.message().orElse("unknown"));
          });
        }
      }
    } catch (Exception e) {
      System.err.println("Stream error: " + e.getMessage());
    }

    // Summary
    System.out.println("\n--- " + testName + " Summary ---");
    System.out.println("Total events: " + eventCount);
    System.out.println("ThoughtSummaryDelta count: " + thoughtSummaryCount);
    System.out.println("  - TextContent: " + textContentCount);
    System.out.println("  - ImageContent: " + imageContentCount);

    if (imageContentCount > 0) {
      System.out.println("\n*** SUCCESS: ImageContent found in ThoughtSummaryDelta! ***");
    } else {
      System.out.println("\n(No ImageContent in thought summaries for this test)");
    }

    System.out.println("\nFinal response preview: "
        + (textBuffer.length() > 200 ? textBuffer.substring(0, 200) + "..." : textBuffer));
  }

  private InteractionsStreamingThoughtSummaryWithImage() {}
}
