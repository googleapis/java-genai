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
import com.google.genai.types.interactions.CancelInteractionConfig;
import com.google.genai.types.interactions.CreateInteractionConfig;
import com.google.genai.types.interactions.GetInteractionConfig;
import com.google.genai.types.interactions.Interaction;
import com.google.genai.types.interactions.content.Content;
import com.google.genai.types.interactions.content.TextContent;

/**
 * Example: Cancel Interaction API
 *
 * <p>Demonstrates the interactions.cancel() API for background interactions.
 *
 * <p>Note: Only interactions created with background=true can be cancelled.
 *
 * <p>To run this example:
 * <ol>
 *   <li>Set the GOOGLE_API_KEY environment variable: {@code export GOOGLE_API_KEY=YOUR_API_KEY}
 *   <li>Compile the examples: {@code mvn clean compile}
 *   <li>Run: {@code mvn exec:java -Dexec.mainClass="com.google.genai.examples.InteractionsCancelExample"}
 * </ol>
 *
 * <p><b>Note:</b> The Interactions API is currently in beta.
 */
public final class InteractionsCancelExample {

  public static void main(String[] args) {
    Client client = new Client();

    System.out.println("=== Interactions API: Cancel Example ===\n");

    try {
      // ===== STEP 1: Create Background Interaction =====
      System.out.println("--- STEP 1: Create Background Interaction ---\n");

      CreateInteractionConfig createConfig =
          CreateInteractionConfig.builder()
              .agent("deep-research-pro-preview-12-2025")
              .input("Write an essay about space exploration.")
              .background(true)
              .build();

      System.out.println("=== REQUEST ===");
      System.out.println(createConfig.toJson());
      System.out.println();

      Interaction interaction = client.interactions.create(createConfig);

      System.out.println("=== RESPONSE ===");
      System.out.println(interaction.toJson());
      System.out.println();

      printResults(interaction);

      String interactionId = interaction.id();

      // ===== STEP 2: Cancel the Interaction =====
      System.out.println("\n--- STEP 2: Cancel the Interaction ---\n");

      try {
        Interaction cancelled = client.interactions.cancel(interactionId);

        System.out.println("=== RESPONSE ===");
        System.out.println(cancelled.toJson());
        System.out.println();

        printResults(cancelled);
      } catch (Exception e) {
        System.out.println("Results:");
        System.out.println("  Cancel failed: " + e.getMessage());
        System.out.println("  (May happen if interaction completed too quickly)");
      }

      // ===== STEP 3: Verify Final State =====
      System.out.println("\n--- STEP 3: Verify Final State ---\n");

      try {
        Interaction retrieved = client.interactions.get(interactionId);

        System.out.println("=== RESPONSE ===");
        System.out.println(retrieved.toJson());
        System.out.println();

        printResults(retrieved);
      } catch (Exception e) {
        System.out.println("Results:");
        System.out.println("  Get failed: " + e.getMessage());
      }

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

  private InteractionsCancelExample() {}
}
