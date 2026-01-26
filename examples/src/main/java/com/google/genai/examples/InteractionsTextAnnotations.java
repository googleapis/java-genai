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
 * <p>Note: The Interactions API is currently only available via the Gemini Developer API (not
 * Vertex AI).
 *
 * <p>1. Set an API key environment variable:
 *
 * <p>export GOOGLE_API_KEY=YOUR_API_KEY
 *
 * <p>2. Compile and run:
 *
 * <p>mvn clean compile
 *
 * <p>mvn exec:java -Dexec.mainClass="com.google.genai.examples.InteractionsTextAnnotations"
 */
package com.google.genai.examples;

import com.google.genai.Client;
import com.google.genai.types.interactions.Interaction;
import com.google.genai.types.interactions.Annotation;
import com.google.genai.types.interactions.content.Content;
import com.google.genai.types.interactions.content.TextContent;
import java.util.List;

/**
 * TextContent Annotations Testing Example
 *
 * <p>This example demonstrates testing for the annotations field in TextContent responses
 * from the Interactions API. The annotations field provides citation information for
 * model-generated content.
 *
 * <p>Structure: TextContent contains:
 * - type: "text"
 * - text: The actual text content
 * - annotations: Optional array of Annotation objects for citations
 *
 * <p>Each Annotation contains:
 * - start_index: Start byte position of cited segment
 * - end_index: End byte position of cited segment (exclusive)
 * - source: Source reference (URL, title, etc.)
 *
 * <p>This example tests various prompts to see if the API returns annotations.
 *
 * <p>Note: The Interactions API is in beta and subject to change.
 */
public final class InteractionsTextAnnotations {

  public static void main(String[] args) {
    Client client = new Client();

    System.out.println("=== TextContent Annotations Testing Example ===\n");
    System.out.println("Testing if the Interactions API returns annotations in TextContent\n");

    // Test Case 1: Simple factual question
    testAnnotations(
        client,
        "Test Case 1: Simple Factual Question",
        "What is the capital of France?");

    // Test Case 2: Question that might trigger citations
    testAnnotations(
        client,
        "Test Case 2: Request with Explicit Citation Request",
        "Tell me about climate change and cite your sources.");

    // Test Case 3: Research-oriented question
    testAnnotations(
        client,
        "Test Case 3: Research Question",
        "What are the latest advancements in quantum computing? Please provide citations.");

    // Test Case 4: Grounding/search request
    testAnnotations(
        client,
        "Test Case 4: Grounding Request",
        "Search for information about the Paris Agreement and summarize it with citations.");

    System.out.println("\n=== All test cases completed ===");
  }

  private static final String MODEL = "gemini-3-flash-preview";

  /**
   * Test helper that makes an API call and checks for annotations in the response.
   */
  private static void testAnnotations(Client client, String testName, String prompt) {
    System.out.println("\n--- " + testName + " ---\n");

    try {
      // Using the convenience overload: create(model, text)
      System.out.println("=== REQUEST ===");
      System.out.println("Model: " + MODEL);
      System.out.println("Input: " + prompt);
      System.out.println();

      Interaction response = client.interactions.create(MODEL, prompt);

      System.out.println("=== RESPONSE ===");
      System.out.println(response.toJson());
      System.out.println();

      analyzeAnnotations(response);

    } catch (Exception e) {
      System.err.println("ERROR in " + testName + ":");
      System.err.println("  Exception: " + e.getClass().getName());
      System.err.println("  Message: " + e.getMessage());
      e.printStackTrace();
    }
  }

  /**
   * Analyzes interaction outputs to find and display annotations.
   */
  private static void analyzeAnnotations(Interaction interaction) {
    System.out.println("Results:");

    if (!interaction.outputs().isPresent() || interaction.outputs().get().isEmpty()) {
      System.out.println("  ❌ No outputs found in response");
      return;
    }

    boolean foundAnnotations = false;
    int textContentCount = 0;
    int totalAnnotations = 0;

    for (Content content : interaction.outputs().get()) {
      if (content instanceof TextContent) {
        textContentCount++;
        TextContent textContent = (TextContent) content;

        System.out.println("\n  TextContent #" + textContentCount + ":");
        System.out.println("    Text: " + textContent.text().orElse("(empty)"));

        if (textContent.annotations().isPresent() && !textContent.annotations().get().isEmpty()) {
          foundAnnotations = true;
          List<Annotation> annotations = textContent.annotations().get();
          totalAnnotations += annotations.size();

          System.out.println("    ✅ ANNOTATIONS FOUND: " + annotations.size() + " annotation(s)");

          for (int i = 0; i < annotations.size(); i++) {
            Annotation ann = annotations.get(i);
            System.out.println("\n      Annotation " + (i + 1) + ":");
            System.out.println("        Start Index: " + ann.startIndex().orElse(null));
            System.out.println("        End Index: " + ann.endIndex().orElse(null));
            System.out.println("        Source: " + ann.source().orElse("(none)"));

            // Show the cited text segment if indices are present
            if (ann.startIndex().isPresent()
                && ann.endIndex().isPresent()
                && textContent.text().isPresent()) {
              String text = textContent.text().get();
              int start = ann.startIndex().get();
              int end = ann.endIndex().get();
              if (start >= 0 && end <= text.length() && start < end) {
                String citedText = text.substring(start, end);
                System.out.println("        Cited Text: \"" + citedText + "\"");
              }
            }
          }
        } else {
          System.out.println("    ❌ No annotations found in this TextContent");
        }
      }
    }

    System.out.println("\n  SUMMARY:");
    System.out.println("    Total TextContent blocks: " + textContentCount);
    System.out.println("    Total annotations found: " + totalAnnotations);

    if (foundAnnotations) {
      System.out.println("    ✅ SUCCESS: The API returned annotations!");
    } else {
      System.out.println("    ⚠️  The API did not return annotations for this request.");
      System.out.println("       This could mean:");
      System.out.println("       - The model didn't use external sources");
      System.out.println("       - The annotations feature may not be enabled yet");
      System.out.println("       - This specific prompt didn't trigger citations");
    }
  }

  private InteractionsTextAnnotations() {}
}
