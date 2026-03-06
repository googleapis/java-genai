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
 * <p>mvn exec:java -Dexec.mainClass="com.google.genai.examples.InteractionsCreateExample"
 */
package com.google.genai.examples;

import com.google.genai.Client;
import com.google.genai.types.interactions.CreateInteractionConfig;
import com.google.genai.types.interactions.Interaction;
import com.google.genai.types.interactions.content.AudioContent;
import com.google.genai.types.interactions.content.Content;
import com.google.genai.types.interactions.content.DocumentContent;
import com.google.genai.types.interactions.content.ImageContent;
import com.google.genai.types.interactions.content.TextContent;
import com.google.genai.types.interactions.content.ThoughtContent;
import com.google.genai.types.interactions.content.VideoContent;

/**
 * Example: Create Interaction with All Content Types
 *
 * <p>Demonstrates interactions.create() with all available INPUT content types:
 *
 * <ul>
 *   <li>TextContent - Plain text input
 *   <li>ImageContent - Image via URL
 *   <li>AudioContent - Audio via URL
 *   <li>VideoContent - Video via URL
 *   <li>DocumentContent - PDF document via URL
 * </ul>
 *
 * <p>Also shows all possible OUTPUT content types that may be returned.
 *
 * <p>Note: The Interactions API is in beta and subject to change.
 */
public final class InteractionsCreateExample {

  public static void main(String[] args) {
    // Instantiate the client. The client gets the API key from the environment variable
    // `GOOGLE_API_KEY`.
    //
    Client client = new Client();

    System.out.println("=== Interactions API: All Content Types Example ===\n");

    try {
      // ===== PART 1: TextContent =====
      System.out.println("--- PART 1: TextContent ---\n");

      TextContent textContent = TextContent.of("What is the meaning of life?");

      CreateInteractionConfig textConfig =
          CreateInteractionConfig.builder()
              .model("gemini-3-flash-preview")
              .inputFromContents(textContent)
              .build();

      System.out.println("=== REQUEST ===");
      System.out.println(textConfig.toJson());
      System.out.println();

      Interaction textResponse = client.interactions.create(textConfig);

      System.out.println("=== RESPONSE ===");
      System.out.println(textResponse.toJson());
      System.out.println();

      printResults(textResponse);

      // ===== PART 2: ImageContent =====
      System.out.println("\n--- PART 2: ImageContent ---\n");

      String imageUri = "https://storage.googleapis.com/generativeai-downloads/images/cake.jpg";
      ImageContent imageContent = ImageContent.fromUri(imageUri, "image/jpeg");
      TextContent imagePrompt = TextContent.of("Describe this image in detail.");

      CreateInteractionConfig imageConfig =
          CreateInteractionConfig.builder()
              .model("gemini-3-flash-preview")
              .inputFromContents(imagePrompt, imageContent)
              .build();

      System.out.println("=== REQUEST ===");
      System.out.println(imageConfig.toJson());
      System.out.println();

      Interaction imageResponse = client.interactions.create(imageConfig);

      System.out.println("=== RESPONSE ===");
      System.out.println(imageResponse.toJson());
      System.out.println();

      printResults(imageResponse);

      // ===== PART 3: AudioContent =====
      System.out.println("\n--- PART 3: AudioContent ---\n");

      String audioUri = "https://storage.googleapis.com/cloud-samples-data/speech/brooklyn_bridge.mp3";
      AudioContent audioContent = AudioContent.fromUri(audioUri, "audio/mp3");
      TextContent audioPrompt = TextContent.of("Transcribe this audio.");

      CreateInteractionConfig audioConfig =
          CreateInteractionConfig.builder()
              .model("gemini-3-flash-preview")
              .inputFromContents(audioPrompt, audioContent)
              .build();

      System.out.println("=== REQUEST ===");
      System.out.println(audioConfig.toJson());
      System.out.println();

      Interaction audioResponse = client.interactions.create(audioConfig);

      System.out.println("=== RESPONSE ===");
      System.out.println(audioResponse.toJson());
      System.out.println();

      printResults(audioResponse);

      // ===== PART 4: VideoContent =====
      System.out.println("\n--- PART 4: VideoContent ---\n");

      String videoUri = "https://storage.googleapis.com/cloud-samples-data/generative-ai/video/pixel8.mp4";
      VideoContent videoContent = VideoContent.fromUri(videoUri, "video/mp4");
      TextContent videoPrompt = TextContent.of("Describe what happens in this video.");

      CreateInteractionConfig videoConfig =
          CreateInteractionConfig.builder()
              .model("gemini-3-flash-preview")
              .inputFromContents(videoPrompt, videoContent)
              .build();

      System.out.println("=== REQUEST ===");
      System.out.println(videoConfig.toJson());
      System.out.println();

      Interaction videoResponse = client.interactions.create(videoConfig);

      System.out.println("=== RESPONSE ===");
      System.out.println(videoResponse.toJson());
      System.out.println();

      printResults(videoResponse);

      // ===== PART 5: DocumentContent (PDF) =====
      System.out.println("\n--- PART 5: DocumentContent ---\n");

      String pdfUri = "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf";
      DocumentContent documentContent = DocumentContent.fromUri(pdfUri, "application/pdf");
      TextContent docPrompt = TextContent.of("Summarize the main points of this document.");

      CreateInteractionConfig docConfig =
          CreateInteractionConfig.builder()
              .model("gemini-3-flash-preview")
              .inputFromContents(docPrompt, documentContent)
              .build();

      System.out.println("=== REQUEST ===");
      System.out.println(docConfig.toJson());
      System.out.println();

      Interaction docResponse = client.interactions.create(docConfig);

      System.out.println("=== RESPONSE ===");
      System.out.println(docResponse.toJson());
      System.out.println();

      printResults(docResponse);

      // ===== PART 6: Multiple Content Types Combined =====
      System.out.println("\n--- PART 6: Multiple Content Types Combined ---\n");

      // Note: Using gemini-3-flash-preview for multi-modal (image + video) combined requests
      ImageContent cakeImage = ImageContent.fromUri(
          "https://storage.googleapis.com/generativeai-downloads/images/cake.jpg", "image/jpeg");
      VideoContent pixelVideo = VideoContent.fromUri(
          "https://storage.googleapis.com/cloud-samples-data/generative-ai/video/pixel8.mp4", "video/mp4");
      TextContent multiPrompt = TextContent.of("Compare the cake image with what you see in the video. Are they related?");

      CreateInteractionConfig multiConfig =
          CreateInteractionConfig.builder()
              .model("gemini-3-flash-preview")
              .inputFromContents(multiPrompt, cakeImage, pixelVideo)
              .build();

      System.out.println("=== REQUEST ===");
      System.out.println(multiConfig.toJson());
      System.out.println();

      Interaction multiResponse = client.interactions.create(multiConfig);

      System.out.println("=== RESPONSE ===");
      System.out.println(multiResponse.toJson());
      System.out.println();

      printResults(multiResponse);

      // ===== PART 7: Verify with Get API =====
      System.out.println("\n--- PART 7: Verify Last Interaction (Get API) ---\n");

      Interaction retrieved = client.interactions.get(multiResponse.id());

      System.out.println("=== RESPONSE ===");
      System.out.println(retrieved.toJson());
      System.out.println();

      printResults(retrieved);

      System.out.println("\n=== Example completed ===");

    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
      e.printStackTrace();
    }
  }

  /** Prints extracted results from the interaction response. */
  private static void printResults(Interaction interaction) {
    System.out.println("Results:");
    System.out.println("  Interaction ID: " + interaction.id());
    System.out.println("  Status: " + interaction.status());

    if (!interaction.outputs().isPresent() || interaction.outputs().get().isEmpty()) {
      System.out.println("  Outputs: (none)");
      return;
    }

    for (Content output : interaction.outputs().get()) {
      if (output instanceof TextContent) {
        TextContent t = (TextContent) output;
        String text = t.text().orElse("(empty)");
        System.out.println("  Text: " + text);
      } else if (output instanceof ThoughtContent) {
        ThoughtContent tc = (ThoughtContent) output;
        System.out.println("  ThoughtContent:");
        System.out.println("    signature: " + tc.signature().orElse("(none)"));
        if (tc.summary().isPresent()) {
          System.out.println("    summaries: " + tc.summary().get().size());
        }
      } else {
        System.out.println("  " + output.getClass().getSimpleName());
      }
    }
  }

  private InteractionsCreateExample() {}
}
