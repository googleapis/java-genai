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
 * <p>mvn exec:java -Dexec.mainClass="com.google.genai.examples.InteractionsStreamingImageGeneration"
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
import com.google.genai.types.interactions.streaming.delta.ImageDelta;
import com.google.genai.types.interactions.streaming.delta.TextDelta;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

/**
 * Example demonstrating streaming image generation using the Interactions API.
 *
 * <p>This is the reverse of image analysis - instead of sending an image and getting text,
 * we send a text prompt and receive a generated image.
 *
 * <p>This example shows:
 * <ul>
 *   <li>Using responseModalities to request IMAGE output
 *   <li>Handling ImageDelta for receiving generated image data
 *   <li>Saving the generated image to a file
 *   <li>Optionally receiving text description alongside the image
 * </ul>
 *
 * <p><b>Note:</b> The Interactions API is currently in beta.
 */
public final class InteractionsStreamingImageGeneration {

  public static void main(String[] args) {
    Client client = new Client();

    System.out.println("=== Interactions API: Streaming Image Generation Example ===\n");

    try {
      // ===== Image Generation with Streaming =====
      System.out.println("--- Generating Image from Text Prompt (Streaming) ---\n");

      String prompt = "Generate a beautiful painting of a sunset over the ocean with "
          + "vibrant orange and purple colors, with a small sailboat in the distance.";

      CreateInteractionConfig config =
          CreateInteractionConfig.builder()
              .model("gemini-3-pro-image-preview")  // Model that supports image generation
              .input(prompt)
              .responseModalities("TEXT", "IMAGE")  // Request both text and image output
              .build();

      System.out.println("Prompt: " + prompt);
      System.out.println();
      System.out.println("=== REQUEST ===");
      System.out.println(config.toJson());
      System.out.println();

      System.out.println("Streaming response:\n");

      int eventCount = 0;
      StringBuilder fullTextResponse = new StringBuilder();
      int imageDeltaCount = 0;
      StringBuilder imageDataBuilder = new StringBuilder();
      String imageMimeType = null;

      try (InteractionEventStream<InteractionSseEvent> stream =
          client.interactions.createStream(config)) {
        for (InteractionSseEvent event : stream) {
          eventCount++;

          // Log each event
          System.out.println("\n[EVENT #" + eventCount + "] " + event.getClass().getSimpleName());
          if (event instanceof JsonSerializable) {
            // For ImageDelta, don't print the full base64 data (too long)
            if (event instanceof ContentDelta) {
              ContentDelta deltaEvent = (ContentDelta) event;
              Delta delta = deltaEvent.delta().orElse(null);
              if (delta instanceof ImageDelta) {
                System.out.println("{\"event_type\":\"content.delta\",\"delta\":{\"type\":\"image\",...}}");
              } else {
                System.out.println(((JsonSerializable) event).toJson());
              }
            } else {
              System.out.println(((JsonSerializable) event).toJson());
            }
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
            contentStart.content().ifPresent(content ->
                System.out.println("  Content type: " + content));
          } else if (event instanceof ContentDelta) {
            ContentDelta deltaEvent = (ContentDelta) event;
            Delta delta = deltaEvent.delta().orElse(null);

            if (delta instanceof ImageDelta) {
              imageDeltaCount++;
              ImageDelta imageDelta = (ImageDelta) delta;
              System.out.println("[IMAGE DELTA #" + imageDeltaCount + "] Received image data");

              // Collect image data
              imageDelta.data().ifPresent(data -> {
                imageDataBuilder.append(data);
                System.out.println("  Data chunk size: " + data.length() + " characters");
              });

              // Get MIME type
              imageDelta.mimeType().ifPresent(mime -> {
                System.out.println("  MIME type: " + mime);
              });

              // Check for URI (some responses may include a URI instead of inline data)
              imageDelta.uri().ifPresent(uri ->
                  System.out.println("  URI: " + uri));

              // Check resolution if available
              imageDelta.resolution().ifPresent(resolution ->
                  System.out.println("  Resolution: " + resolution));

            } else if (delta instanceof TextDelta) {
              TextDelta textDelta = (TextDelta) delta;
              String text = textDelta.text().orElse("");
              fullTextResponse.append(text);
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

      System.out.println("\n\n=== SUMMARY ===");
      System.out.println("[TOTAL EVENTS: " + eventCount + "]");
      System.out.println("[IMAGE DELTAS: " + imageDeltaCount + "]");

      if (fullTextResponse.length() > 0) {
        System.out.println("[TEXT RESPONSE]: " + fullTextResponse.toString());
      }

      // Save the generated image if we received image data
      if (imageDataBuilder.length() > 0) {
        String imageData = imageDataBuilder.toString();
        System.out.println("\n[IMAGE DATA RECEIVED]");
        System.out.println("  Total base64 length: " + imageData.length() + " characters");

        // Determine file extension from MIME type
        String extension = "png";  // default
        if (imageMimeType != null) {
          if (imageMimeType.contains("jpeg") || imageMimeType.contains("jpg")) {
            extension = "jpg";
          } else if (imageMimeType.contains("gif")) {
            extension = "gif";
          } else if (imageMimeType.contains("webp")) {
            extension = "webp";
          }
        }

        // Create temp directory for output
        Path outputDir = Path.of(System.getProperty("java.io.tmpdir"), "genai-examples");
        Files.createDirectories(outputDir);
        Path outputPath = outputDir.resolve("generated_image_" + System.currentTimeMillis() + "." + extension);

        try {
          // Decode base64 and save to file
          byte[] imageBytes = Base64.getDecoder().decode(imageData);
          try (FileOutputStream fos = new FileOutputStream(outputPath.toFile())) {
            fos.write(imageBytes);
          }
          System.out.println("  Image saved to: " + outputPath.toAbsolutePath());
          System.out.println("  Image size: " + imageBytes.length + " bytes");
        } catch (IllegalArgumentException e) {
          System.out.println("  Warning: Could not decode base64 image data: " + e.getMessage());
        }
      } else {
        System.out.println("\n[NO IMAGE DATA RECEIVED]");
        System.out.println("Note: The model may not have generated an image for this prompt.");
        System.out.println("Try adjusting the prompt or ensure the model supports image generation.");
      }

      System.out.println("\n=== Example completed ===");

    } catch (IOException e) {
      System.err.println("IO Error: " + e.getMessage());
      e.printStackTrace();
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
      e.printStackTrace();
    }
  }

  private InteractionsStreamingImageGeneration() {}
}
