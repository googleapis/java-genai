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
 * <p>mvn exec:java -Dexec.mainClass="com.google.genai.examples.InteractionsStreamingVideoContent"
 */
package com.google.genai.examples;

import com.google.genai.Client;
import com.google.genai.InteractionEventStream;
import com.google.genai.JsonSerializable;
import com.google.genai.types.interactions.Input;
import com.google.genai.types.interactions.content.TextContent;
import com.google.genai.types.interactions.content.VideoContent;
import com.google.genai.types.interactions.streaming.ContentDelta;
import com.google.genai.types.interactions.streaming.ContentStart;
import com.google.genai.types.interactions.streaming.ContentStop;
import com.google.genai.types.interactions.streaming.ErrorEvent;
import com.google.genai.types.interactions.streaming.InteractionEvent;
import com.google.genai.types.interactions.streaming.InteractionSseEvent;
import com.google.genai.types.interactions.streaming.delta.Delta;
import com.google.genai.types.interactions.streaming.delta.TextDelta;

/**
 * Example demonstrating streaming video analysis using VideoContent with the Interactions API.
 *
 * <p>This example shows:
 * <ul>
 *   <li>Using VideoContent.fromUri() to analyze a video from a URL with streaming
 *   <li>Receiving video analysis results incrementally as they are generated
 * </ul>
 *
 * <p><b>Note:</b> Inline base64-encoded video is not shown because video files are typically
 * large and not suitable for inline embedding.
 *
 * <p><b>Note:</b> The Interactions API is currently in beta.
 */
public final class InteractionsStreamingVideoContent {

  public static void main(String[] args) {
    Client client = new Client();

    System.out.println("=== Interactions API: Streaming Video Content Example ===\n");

    try {
      // ===== Video from URI (Streaming) =====
      System.out.println("--- Video from URI (Streaming) ---\n");

      String videoUri = "https://storage.googleapis.com/cloud-samples-data/generative-ai/video/pixel8.mp4";
      VideoContent videoContent = VideoContent.fromUri(videoUri, "video/mp4");
      TextContent textPrompt = TextContent.of("Describe what happens in this video.");

      // Using simplified streaming API: createStream(model, input)
      Input input = Input.fromContents(textPrompt, videoContent);

      System.out.println("Streaming response:\n");

      int eventCount = 0;
      StringBuilder fullResponse = new StringBuilder();

      try (InteractionEventStream<InteractionSseEvent> stream =
          client.interactions.createStream("gemini-3-flash-preview", input)) {
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
            }
          } else if (event instanceof ContentStart) {
            System.out.println("[CONTENT_START]");
          } else if (event instanceof ContentDelta) {
            ContentDelta deltaEvent = (ContentDelta) event;
            Delta delta = deltaEvent.delta().orElse(null);
            if (delta instanceof TextDelta) {
              TextDelta textDelta = (TextDelta) delta;
              String text = textDelta.text().orElse("");
              fullResponse.append(text);
              System.out.print("[TEXT] " + text);
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

      System.out.println("\n\n[TOTAL EVENTS: " + eventCount + "]");
      System.out.println("[FULL RESPONSE]: " + fullResponse.toString());

      System.out.println("\n=== Example completed ===");

    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
      e.printStackTrace();
    }
  }

  private InteractionsStreamingVideoContent() {}
}
