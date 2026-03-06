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
import com.google.genai.types.interactions.GetInteractionConfig;
import com.google.genai.types.interactions.Interaction;
import com.google.genai.types.interactions.content.Content;
import com.google.genai.types.interactions.content.TextContent;

/**
 * Example: Get Interaction API
 *
 * <p>Demonstrates the interactions.get() API to retrieve an interaction by ID.
 *
 * <p>To run this example:
 * <ol>
 *   <li>Set the GOOGLE_API_KEY environment variable: {@code export GOOGLE_API_KEY=YOUR_API_KEY}
 *   <li>Compile the examples: {@code mvn clean compile}
 *   <li>Run: {@code mvn exec:java -Dexec.mainClass="com.google.genai.examples.InteractionsGetExample"}
 * </ol>
 *
 * <p><b>Note:</b> The Interactions API is currently in beta.
 */
public final class InteractionsGetExample {

  public static void main(String[] args) {
    Client client = new Client();

    System.out.println("=== Interactions API: Get Example ===\n");

    try {
      // ===== STEP 1: Create an Interaction =====
      System.out.println("--- STEP 1: Create an Interaction ---\n");

      // Using the convenience overload: create(model, text)
      String model = "gemini-3-flash-preview";
      String prompt = "Explain quantum computing in 2-3 sentences.";

      System.out.println("=== REQUEST ===");
      System.out.println("Model: " + model);
      System.out.println("Input: " + prompt);
      System.out.println();

      Interaction interaction = client.interactions.create(model, prompt);

      System.out.println("=== RESPONSE ===");
      System.out.println(interaction.toJson());
      System.out.println();

      printResults(interaction);

      // ===== STEP 2: Get Interaction by ID =====
      System.out.println("\n--- STEP 2: Get Interaction by ID ---\n");

      String interactionId = interaction.id();
      Interaction retrieved = client.interactions.get(interactionId);

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

  private InteractionsGetExample() {}
}
