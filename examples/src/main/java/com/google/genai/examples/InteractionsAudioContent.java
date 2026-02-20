/*
 * Copyright 2025 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.genai.examples;

import com.google.genai.Client;
import com.google.genai.types.interactions.Input;
import com.google.genai.types.interactions.Interaction;
import com.google.genai.types.interactions.content.AudioContent;
import com.google.genai.types.interactions.content.Content;
import com.google.genai.types.interactions.content.TextContent;

/**
 * Example demonstrating audio transcription and analysis using AudioContent with the Interactions
 * API.
 *
 * <p>This example shows:
 * <ul>
 *   <li>Using AudioContent.fromUri() to analyze audio from a URL
 *   <li>Using AudioContent.fromData() to analyze base64-encoded audio
 * </ul>
 *
 * <p>To run this example:
 * <ol>
 *   <li>Set the GOOGLE_API_KEY environment variable: {@code export GOOGLE_API_KEY=YOUR_API_KEY}
 *   <li>Compile the examples: {@code mvn clean compile}
 *   <li>Run: {@code mvn exec:java -Dexec.mainClass="com.google.genai.examples.InteractionsAudioContent"}
 * </ol>
 *
 * <p><b>Note:</b> The Interactions API is currently in beta.
 */
public final class InteractionsAudioContent {

  public static void main(String[] args) {
    Client client = new Client();

    System.out.println("=== Interactions API: Audio Content Example ===\n");

    try {
      // ===== PART 1: Audio from URI =====
      System.out.println("--- PART 1: Audio from URI ---\n");

      String audioUri = "https://storage.googleapis.com/cloud-samples-data/speech/brooklyn_bridge.mp3";
      AudioContent audioContent = AudioContent.fromUri(audioUri, "audio/mp3");
      TextContent textPrompt = TextContent.of("Transcribe this audio.");

      Input input = Input.fromContents(textPrompt, audioContent);

      Interaction response = client.interactions.create("gemini-3-flash-preview", input);

      System.out.println("=== RESPONSE ===");
      System.out.println(response.toJson());
      System.out.println();

      printResults(response);

      // ===== PART 2: Audio from Inline Data (base64) =====
      System.out.println("\n--- PART 2: Audio from Inline Data (Base64) ---\n");

      // Small WAV file header encoded as base64
      String base64Data = "UklGRiQAAABXQVZFZm10IBAAAAABAAEAQB8AAAB9AAACABAAZGF0YQAAAAA=";
      AudioContent audioFromData = AudioContent.fromData(base64Data, "audio/wav");
      TextContent textPrompt2 = TextContent.of("What do you hear in this audio?");

      Input input2 = Input.fromContents(textPrompt2, audioFromData);

      Interaction response2 = client.interactions.create("gemini-3-flash-preview", input2);

      System.out.println("=== RESPONSE ===");
      System.out.println(response2.toJson());
      System.out.println();

      printResults(response2);

      System.out.println("\n=== Example completed ===");

    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
      e.printStackTrace();
    }
  }

  private static void printResults(Interaction interaction) {
    System.out.println("Results:");
    System.out.println("  Interaction ID: " + interaction.id());
    System.out.println("  Status: " + interaction.status());

    if (interaction.outputs().isPresent() && !interaction.outputs().get().isEmpty()) {
      for (Content output : interaction.outputs().get()) {
        if (output instanceof TextContent) {
          System.out.println("  Text: " + ((TextContent) output).text().orElse("(empty)"));
        } else {
          System.out.println("  " + output.getClass().getSimpleName());
        }
      }
    }
  }

  private InteractionsAudioContent() {}
}
