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
import com.google.genai.types.interactions.MediaResolution;
import com.google.genai.types.interactions.content.Content;
import com.google.genai.types.interactions.content.ImageContent;
import com.google.genai.types.interactions.content.TextContent;

/**
 * Example demonstrating MediaResolution with ImageContent in the Interactions API.
 *
 * <p>This example shows how to specify image resolution when analyzing images.
 *
 * <p>To run this example:
 * <ol>
 *   <li>Set the GOOGLE_API_KEY environment variable: {@code export GOOGLE_API_KEY=YOUR_API_KEY}
 *   <li>Compile the examples: {@code mvn clean compile}
 *   <li>Run: {@code mvn exec:java -Dexec.mainClass="com.google.genai.examples.InteractionsMediaResolution"}
 * </ol>
 *
 * <p><b>Note:</b> The Interactions API is currently in beta.
 */
public final class InteractionsMediaResolution {

  public static void main(String[] args) {
    Client client = new Client();

    System.out.println("=== Interactions API: Media Resolution Example ===\n");

    try {
      String imageUri = "https://storage.googleapis.com/generativeai-downloads/images/cake.jpg";

      // Create ImageContent with HIGH resolution
      MediaResolution resolution = new MediaResolution(MediaResolution.Known.HIGH);
      ImageContent imageContent = ImageContent.builder()
          .uri(imageUri)
          .mimeType(new com.google.genai.types.interactions.ImageMimeType("image/jpeg"))
          .resolution(resolution)
          .build();

      TextContent textPrompt = TextContent.of("Describe this image in detail.");

      Input input = Input.fromContents(textPrompt, imageContent);

      Interaction response = client.interactions.create("gemini-3-flash-preview", input);

      System.out.println("=== RESPONSE ===");
      System.out.println(response.toJson());
      System.out.println();

      printResults(response);

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

  private InteractionsMediaResolution() {}
}
