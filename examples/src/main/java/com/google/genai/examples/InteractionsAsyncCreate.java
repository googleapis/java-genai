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
import com.google.genai.types.interactions.content.Content;
import com.google.genai.types.interactions.content.TextContent;
import java.util.concurrent.CompletableFuture;

/**
 * Example: Async Create with the Interactions API
 *
 * <p>Demonstrates async operations using CompletableFuture for non-blocking requests.
 *
 * <p>To run this example:
 * <ol>
 *   <li>Set the GOOGLE_API_KEY environment variable: {@code export GOOGLE_API_KEY=YOUR_API_KEY}
 *   <li>Compile the examples: {@code mvn clean compile}
 *   <li>Run: {@code mvn exec:java -Dexec.mainClass="com.google.genai.examples.InteractionsAsyncCreate"}
 * </ol>
 *
 * <p><b>Note:</b> The Interactions API is currently in beta.
 */
public final class InteractionsAsyncCreate {

  public static void main(String[] args) {
    Client client = new Client();

    System.out.println("=== Interactions API: Async Create Example ===\n");

    try {
      // ===== Basic Async Create =====
      System.out.println("--- Async Create with Callback ---\n");

      // Using the convenience overload: create(model, text)
      String model = "gemini-3-flash-preview";
      String prompt = "What is the capital of France?";

      System.out.println("=== REQUEST ===");
      System.out.println("Model: " + model);
      System.out.println("Input: " + prompt);
      System.out.println();

      System.out.println("Sending async request...\n");

      CompletableFuture<Interaction> future = client.async.interactions.create(model, prompt);

      // Wait for completion and print response
      Interaction interaction = future.join();

      System.out.println("=== RESPONSE ===");
      System.out.println(interaction.toJson());
      System.out.println();

      printResults(interaction);

      // ===== Multiple Parallel Creates =====
      System.out.println("\n--- Multiple Parallel Creates ---\n");

      // Using convenience overload for parallel requests
      CompletableFuture<Interaction> p1 =
          client.async.interactions.create(model, "What is machine learning?");

      CompletableFuture<Interaction> p2 =
          client.async.interactions.create(model, "What is quantum computing?");

      p1.thenAccept(i -> System.out.println("Request 1 completed: " + i.id()));
      p2.thenAccept(i -> System.out.println("Request 2 completed: " + i.id()));

      CompletableFuture.allOf(p1, p2)
          .thenRun(() -> System.out.println("\nAll parallel requests completed!"));

      CompletableFuture.allOf(p1, p2).join();

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

  private InteractionsAsyncCreate() {}
}
