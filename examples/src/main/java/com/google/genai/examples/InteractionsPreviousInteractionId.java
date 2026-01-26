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

package com.google.genai.examples;

import com.google.genai.Client;
import com.google.genai.types.interactions.CreateInteractionConfig;
import com.google.genai.types.interactions.Interaction;
import com.google.genai.types.interactions.Usage;
import com.google.genai.types.interactions.content.Content;
import com.google.genai.types.interactions.content.TextContent;

/**
 * Example: Conversation Continuity with previousInteractionId
 *
 * <p>Demonstrates how to link interactions using previousInteractionId to maintain conversation
 * context across separate API calls. This example verifies context is preserved by asking for
 * 10 random numbers, then asking the model to perform various math operations on them:
 * addition, multiplication, and calculating mean/median/mode.
 *
 * <p>To run this example:
 *
 * <ol>
 *   <li>Set the GOOGLE_API_KEY environment variable: {@code export GOOGLE_API_KEY=YOUR_API_KEY}
 *   <li>Compile the examples: {@code mvn clean compile}
 *   <li>Run: {@code mvn exec:java
 *       -Dexec.mainClass="com.google.genai.examples.InteractionsPreviousInteractionId"}
 * </ol>
 *
 * <p><b>Note:</b> The Interactions API is currently in beta.
 */
public final class InteractionsPreviousInteractionId {

  public static void main(String[] args) {
    Client client = new Client();

    System.out.println("=== Interactions API: Previous Interaction ID Example ===\n");

    try {
      // ===== First Interaction =====
      System.out.println("--- First Interaction ---\n");

      CreateInteractionConfig config1 =
          CreateInteractionConfig.builder()
              .model("gemini-2.5-flash")
              .input("Give me 10 random numbers less than 50.")
              .build();

      System.out.println("=== REQUEST ===");
      System.out.println(config1.toJson());
      System.out.println();

      Interaction response1 = client.interactions.create(config1);

      System.out.println("=== RESPONSE ===");
      System.out.println(response1.toJson());
      System.out.println();

      printResults(response1);

      // ===== Second Interaction (linked to first) =====
      System.out.println("\n--- Second Interaction (linked) ---\n");

      CreateInteractionConfig config2 =
          CreateInteractionConfig.builder()
              .model("gemini-2.5-flash")
              .input("Add all the numbers you just gave me.")
              .previousInteractionId(response1.id())
              .build();

      System.out.println("=== REQUEST ===");
      System.out.println(config2.toJson());
      System.out.println();

      Interaction response2 = client.interactions.create(config2);

      System.out.println("=== RESPONSE ===");
      System.out.println(response2.toJson());
      System.out.println();

      printResults(response2);

      // ===== Third Interaction (linked to second) =====
      System.out.println("\n--- Third Interaction (linked) ---\n");

      CreateInteractionConfig config3 =
          CreateInteractionConfig.builder()
              .model("gemini-2.5-flash")
              .input("Now multiply all those original numbers together.")
              .previousInteractionId(response2.id())
              .build();

      System.out.println("=== REQUEST ===");
      System.out.println(config3.toJson());
      System.out.println();

      Interaction response3 = client.interactions.create(config3);

      System.out.println("=== RESPONSE ===");
      System.out.println(response3.toJson());
      System.out.println();

      printResults(response3);

      // ===== Fourth Interaction (linked to third) =====
      System.out.println("\n--- Fourth Interaction (linked) ---\n");

      CreateInteractionConfig config4 =
          CreateInteractionConfig.builder()
              .model("gemini-2.5-flash")
              .input("which number is the greatest ? .")
              .previousInteractionId(response3.id())
              .build();

      System.out.println("=== REQUEST ===");
      System.out.println(config4.toJson());
      System.out.println();

      Interaction response4 = client.interactions.create(config4);

      System.out.println("=== RESPONSE ===");
      System.out.println(response4.toJson());
      System.out.println();

      printResults(response4);

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
    if (interaction.previousInteractionId().isPresent()) {
      System.out.println(
          "  Previous Interaction ID: " + interaction.previousInteractionId().get());
    }

    if (interaction.outputs().isPresent() && !interaction.outputs().get().isEmpty()) {
      for (Content output : interaction.outputs().get()) {
        if (output instanceof TextContent) {
          System.out.println("  Text: " + ((TextContent) output).text().orElse("(empty)"));
        } else {
          System.out.println("  " + output.getClass().getSimpleName());
        }
      }
    }

    if (interaction.usage().isPresent()) {
      Usage usage = interaction.usage().get();
      System.out.println("  Usage:");
      System.out.println("    Input tokens: " + usage.totalInputTokens().orElse(0));
      System.out.println("    Output tokens: " + usage.totalOutputTokens().orElse(0));
      System.out.println("    Total tokens: " + usage.totalTokens().orElse(0));
    }
  }

  private InteractionsPreviousInteractionId() {}
}
