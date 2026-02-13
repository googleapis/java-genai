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
import com.google.genai.types.interactions.Interaction;
import com.google.genai.types.interactions.content.Content;
import com.google.genai.types.interactions.content.TextContent;

/**
 * Example: Delete Interaction API
 *
 * <p>Demonstrates the interactions.delete() API to delete an interaction.
 *
 * <p>To run this example:
 * <ol>
 *   <li>Set the GOOGLE_API_KEY environment variable: {@code export GOOGLE_API_KEY=YOUR_API_KEY}
 *   <li>Compile the examples: {@code mvn clean compile}
 *   <li>Run: {@code mvn exec:java -Dexec.mainClass="com.google.genai.examples.InteractionsDeleteExample"}
 * </ol>
 *
 * <p><b>Note:</b> The Interactions API is currently in beta.
 */
public final class InteractionsDeleteExample {

  public static void main(String[] args) {
    Client client = new Client();

    System.out.println("=== Interactions API: Delete Example ===\n");

    try {
      // ===== STEP 1: Create an Interaction =====
      System.out.println("--- STEP 1: Create an Interaction ---\n");

      // Using the convenience overload: create(model, text)
      String model = "gemini-3-flash-preview";
      String prompt = "What is the capital of France?";

      System.out.println("=== REQUEST ===");
      System.out.println("Model: " + model);
      System.out.println("Input: " + prompt);
      System.out.println();

      Interaction interaction = client.interactions.create(model, prompt);

      System.out.println("=== RESPONSE ===");
      System.out.println(interaction.toJson());
      System.out.println();

      printResults(interaction);

      String interactionId = interaction.id();

      // ===== STEP 2: Delete the Interaction =====
      System.out.println("\n--- STEP 2: Delete the Interaction ---\n");

      client.interactions.delete(interactionId);

      System.out.println("Results:");
      System.out.println("  Interaction deleted successfully: " + interactionId);

      // ===== STEP 3: Verify Deletion =====
      System.out.println("\n--- STEP 3: Verify Deletion (Get should fail) ---\n");

      try {
        client.interactions.get(interactionId);
        System.out.println("ERROR: Should have thrown an exception!");
      } catch (Exception e) {
        System.out.println("Results:");
        System.out.println("  Error: " + e.getMessage());
        System.out.println("  (Expected - interaction was deleted)");
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

  private InteractionsDeleteExample() {}
}
