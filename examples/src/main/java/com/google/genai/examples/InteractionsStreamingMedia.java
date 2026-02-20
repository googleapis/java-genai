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
 * <p>mvn exec:java -Dexec.mainClass="com.google.genai.examples.InteractionsStreamingMedia"
 */
package com.google.genai.examples;

import com.google.genai.Client;
import com.google.genai.InteractionEventStream;
import com.google.genai.JsonSerializable;
import com.google.genai.types.interactions.Input;
import com.google.genai.types.interactions.content.ImageContent;
import com.google.genai.types.interactions.content.TextContent;
import com.google.genai.types.interactions.content.VideoContent;
import com.google.genai.types.interactions.streaming.ContentDelta;
import com.google.genai.types.interactions.streaming.InteractionSseEvent;
import com.google.genai.types.interactions.streaming.delta.Delta;
import com.google.genai.types.interactions.streaming.delta.ImageDelta;
import com.google.genai.types.interactions.streaming.delta.TextDelta;
import com.google.genai.types.interactions.streaming.delta.VideoDelta;

/**
 * Example: Streaming Media Content Deltas
 *
 * <p>Demonstrates streaming for media-related delta types:
 *
 * <ul>
 *   <li>ImageDelta - Incremental image processing updates
 *   <li>VideoDelta - Incremental video analysis updates
 * </ul>
 *
 * <p>This example shows:
 *
 * <ul>
 *   <li>Part 1: Image analysis with streaming deltas
 *   <li>Part 2: Video description with streaming deltas
 * </ul>
 *
 * <p>Note: The Interactions API is in beta and subject to change.
 */
public final class InteractionsStreamingMedia {

  public static void main(String[] args) {
    Client client = new Client();

    System.out.println("=== Interactions API: Streaming Media Deltas ===\n");

    try {
      // ===== PART 1: Image Analysis Streaming =====
      System.out.println("--- PART 1: Image Analysis Streaming (ImageDelta) ---\n");

      ImageContent imageContent =
          ImageContent.fromUri(
              "https://storage.googleapis.com/generativeai-downloads/images/cake.jpg",
              "image/jpeg");
      TextContent imagePrompt = TextContent.of("Describe this image in detail. What do you see?");

      // Using simplified streaming API: createStream(model, input)
      Input imageInput = Input.fromContents(imagePrompt, imageContent);

      System.out.println("Streaming image analysis:\n");

      int eventCount1 = 0;
      StringBuilder textBuffer1 = new StringBuilder();
      int imageDeltaCount = 0;

      try (InteractionEventStream<InteractionSseEvent> stream =
          client.interactions.createStream("gemini-3-flash-preview", imageInput)) {
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

            if (delta instanceof ImageDelta) {
              imageDeltaCount++;
              ImageDelta imageDelta = (ImageDelta) delta;
              System.out.println("[IMAGE DELTA #" + imageDeltaCount + "] Image processing update");
              imageDelta.data().ifPresent(data -> System.out.println("  Data present: " + (data.length() > 0)));
              imageDelta.mimeType().ifPresent(mime -> System.out.println("  MIME type: " + mime));
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
      System.out.println("[IMAGE DELTAS: " + imageDeltaCount + "]");
      System.out.println("[FULL DESCRIPTION]: " + textBuffer1.toString());
      System.out.println();

      // ===== PART 2: Video Analysis Streaming =====
      System.out.println("--- PART 2: Video Analysis Streaming (VideoDelta) ---\n");

      VideoContent videoContent =
          VideoContent.fromUri(
              "https://storage.googleapis.com/cloud-samples-data/generative-ai/video/pixel8.mp4",
              "video/mp4");
      TextContent videoPrompt = TextContent.of("Describe what happens in this video. What do you observe?");

      // Using simplified streaming API: createStream(model, input)
      Input videoInput = Input.fromContents(videoPrompt, videoContent);

      System.out.println("Streaming video analysis:\n");

      int eventCount2 = 0;
      StringBuilder textBuffer2 = new StringBuilder();
      int videoDeltaCount = 0;

      try (InteractionEventStream<InteractionSseEvent> stream =
          client.interactions.createStream("gemini-3-flash-preview", videoInput)) {
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

            if (delta instanceof VideoDelta) {
              videoDeltaCount++;
              VideoDelta videoDelta = (VideoDelta) delta;
              System.out.println("[VIDEO DELTA #" + videoDeltaCount + "] Video processing update");
              videoDelta.data().ifPresent(data -> System.out.println("  Data present: " + (data.length() > 0)));
              videoDelta.mimeType().ifPresent(mime -> System.out.println("  MIME type: " + mime));
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
      System.out.println("[VIDEO DELTAS: " + videoDeltaCount + "]");
      System.out.println("[FULL DESCRIPTION]: " + textBuffer2.toString());
      System.out.println();

      System.out.println("=== Example completed ===");

    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
      e.printStackTrace();
    }
  }

  private InteractionsStreamingMedia() {}
}
