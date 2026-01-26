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
import com.google.genai.types.interactions.GoogleSearchResult;
import com.google.genai.types.interactions.Interaction;
import com.google.genai.types.interactions.content.Content;
import com.google.genai.types.interactions.content.GoogleSearchCallContent;
import com.google.genai.types.interactions.content.GoogleSearchResultContent;
import com.google.genai.types.interactions.content.TextContent;
import com.google.genai.types.interactions.tools.GoogleSearch;
import java.util.List;

/**
 * Example: Google Search Tool with the Interactions API
 *
 * <p>Demonstrates how to use the GoogleSearch tool to enable the model to search the web.
 *
 * <p>To run this example:
 * <ol>
 *   <li>Set the GOOGLE_API_KEY environment variable: {@code export GOOGLE_API_KEY=YOUR_API_KEY}
 *   <li>Compile the examples: {@code mvn clean compile}
 *   <li>Run: {@code mvn exec:java -Dexec.mainClass="com.google.genai.examples.InteractionsGoogleSearch"}
 * </ol>
 *
 * <p><b>Note:</b> The Interactions API is currently in beta.
 */
public final class InteractionsGoogleSearch {

  public static void main(String[] args) {
    Client client = new Client();

    System.out.println("=== Interactions API: Google Search Tool Example ===\n");

    try {
      GoogleSearch searchTool = GoogleSearch.builder().build();

      CreateInteractionConfig config =
          CreateInteractionConfig.builder()
              .model("gemini-3-flash-preview")
              .input("What are the latest developments in quantum computing in 2025?")
              .tools(searchTool)
              .build();

      System.out.println("=== REQUEST ===");
      System.out.println(config.toJson());
      System.out.println();

      Interaction response = client.interactions.create(config);

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

    if (!interaction.outputs().isPresent() || interaction.outputs().get().isEmpty()) {
      System.out.println("  Outputs: (none)");
      return;
    }

    for (Content output : interaction.outputs().get()) {
      if (output instanceof TextContent) {
        System.out.println("  Text: " + ((TextContent) output).text().orElse("(empty)"));
      } else if (output instanceof GoogleSearchCallContent) {
        GoogleSearchCallContent searchCall = (GoogleSearchCallContent) output;
        System.out.println("  GoogleSearchCall: id=" + searchCall.id());
        if (searchCall.arguments().isPresent()) {
          System.out.println("    queries: " + searchCall.arguments().get().queries().orElse(List.of()));
        }
      } else if (output instanceof GoogleSearchResultContent) {
        GoogleSearchResultContent searchResult = (GoogleSearchResultContent) output;
        System.out.println("  GoogleSearchResult: callId=" + searchResult.callId().orElse("N/A"));
        if (searchResult.result().isPresent()) {
          List<GoogleSearchResult> results = searchResult.result().get();
          System.out.println("    results count: " + results.size());
          for (int i = 0; i < Math.min(3, results.size()); i++) {
            GoogleSearchResult result = results.get(i);
            System.out.println("    [" + (i + 1) + "] " + result.title().orElse("N/A"));
          }
        }
      } else {
        System.out.println("  " + output.getClass().getSimpleName());
      }
    }
  }

  private InteractionsGoogleSearch() {}
}
