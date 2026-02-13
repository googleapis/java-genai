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
import com.google.genai.InteractionEventStream;
import com.google.genai.types.interactions.CreateInteractionConfig;
import com.google.genai.types.interactions.Interaction;
import com.google.genai.types.interactions.streaming.ContentDelta;
import com.google.genai.types.interactions.streaming.InteractionEvent;
import com.google.genai.types.interactions.streaming.InteractionSseEvent;
import com.google.genai.types.interactions.streaming.delta.TextDelta;

/**
 * Demonstrates streaming a background agent interaction.
 *
 * <p>This example shows how to:
 * <ul>
 *   <li>Create a background agent with {@code background=true}</li>
 *   <li>Stream the agent's progress using {@code getStream()}</li>
 *   <li>Extract and display text output from streaming events</li>
 * </ul>
 *
 * <p><b>Key Concepts:</b>
 * <ul>
 *   <li><b>background=true:</b> Returns immediately with an interaction ID; agent runs asynchronously</li>
 *   <li><b>getStream():</b> Monitors progress of an existing interaction via Server-Sent Events (SSE)</li>
 *   <li><b>TextDelta:</b> Incremental text chunks as the agent generates output</li>
 * </ul>
 *
 * <p><b>Usage:</b>
 * <pre>{@code
 * export GOOGLE_API_KEY="YOUR_API_KEY"
 * unset GOOGLE_CLOUD_PROJECT GOOGLE_CLOUD_LOCATION GOOGLE_GENAI_USE_VERTEXAI
 * mvn exec:java -Dexec.mainClass="com.google.genai.examples.InteractionsStreamingAgent"
 * }</pre>
 */
public class InteractionsStreamingAgent {

  public static void main(String[] args) {
    try {
      // Initialize the client
      Client client = new Client.Builder().build();

      System.out.println("Creating background agent interaction...");

      // Create a background agent interaction
      CreateInteractionConfig config =
          CreateInteractionConfig.builder()
              .agent("deep-research-pro-preview-12-2025")
              .input("Write a brief summary of quantum computing.")
              .background(true) // Returns immediately; agent runs in background
              .build();

      Interaction interaction = client.interactions.create(config);

      System.out.println("Agent started. ID: " + interaction.id());
      System.out.println("Status: " + interaction.status());
      System.out.println("\nStreaming output:\n");

      StringBuilder output = new StringBuilder();

      // Stream the agent's progress
      try (InteractionEventStream<InteractionSseEvent> stream =
          client.interactions.getStream(interaction.id())) {

        for (InteractionSseEvent event : stream) {
          // Extract text deltas
          if (event instanceof ContentDelta) {
            ContentDelta delta = (ContentDelta) event;
            delta.delta().ifPresent(d -> {
              if (d instanceof TextDelta) {
                String text = ((TextDelta) d).text().orElse("");
                output.append(text);
                System.out.print(text); // Stream to console in real-time
                System.out.flush();
              }
            });
          }

          // Check for completion
          if (event instanceof InteractionEvent) {
            InteractionEvent ie = (InteractionEvent) event;
            if (ie.isComplete()) {
              System.out.println("\n\nAgent completed successfully!");

              // Display usage information if available
              ie.interaction().ifPresent(i -> {
                i.usage().ifPresent(usage -> {
                  usage.totalTokens().ifPresent(tokens ->
                      System.out.println("Total tokens used: " + tokens));
                });
              });
              break;
            }
          }
        }
      }

      // Display the full output
      if (output.length() > 0) {
        System.out.println("\n--- Full Output ---");
        System.out.println(output.toString());
      }

    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
      e.printStackTrace();
      System.exit(1);
    }
  }
}
