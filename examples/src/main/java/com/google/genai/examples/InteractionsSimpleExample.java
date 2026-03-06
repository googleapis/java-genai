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
 * <p>1. Set an API key environment variable. You can find a list of available API keys here:
 * https://aistudio.google.com/app/apikey
 *
 * <p>export GOOGLE_API_KEY=YOUR_API_KEY
 *
 * <p>2. Compile the java package and run the sample code.
 *
 * <p>mvn clean compile
 *
 * <p>mvn exec:java -Dexec.mainClass="com.google.genai.examples.InteractionsSimpleExample"
 */
package com.google.genai.examples;

import com.google.genai.Client;
import com.google.genai.types.interactions.Interaction;
import com.google.genai.types.interactions.content.Content;
import com.google.genai.types.interactions.content.TextContent;

/**
 * Example: Simple Text Interaction
 *
 * <p>Demonstrates the simplest way to create an interaction using the convenience overload:
 * {@code create(model, text)}
 *
 * <p>This is the most concise way to interact with the Interactions API when you just need to send
 * a simple text message and get a response.
 *
 * <p>Note: The Interactions API is in beta and subject to change.
 */
public final class InteractionsSimpleExample {

  public static void main(String[] args) {
    // Instantiate the client. The client gets the API key from the environment variable
    // `GOOGLE_API_KEY`.
    Client client = new Client();

    System.out.println("=== Simple Text Interaction ===\n");

    try {
      // Simplest possible interaction - just model and text!
      Interaction response = client.interactions.create(
          "gemini-3-flash-preview",
          "What is the capital of France?"
      );

      System.out.println("Status: " + response.status());
      System.out.println("Response: " + getTextOutput(response));

      System.out.println("\n=== Example completed ===");

    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
      e.printStackTrace();
    }
  }

  /** Extracts the first text output from an interaction response. */
  private static String getTextOutput(Interaction interaction) {
    if (!interaction.outputs().isPresent() || interaction.outputs().get().isEmpty()) {
      return "(no output)";
    }

    for (Content output : interaction.outputs().get()) {
      if (output instanceof TextContent) {
        TextContent textContent = (TextContent) output;
        return textContent.text().orElse("(empty)");
      }
    }
    return "(no text output)";
  }

  private InteractionsSimpleExample() {}
}
