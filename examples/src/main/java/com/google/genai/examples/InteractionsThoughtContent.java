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
 * <p>1. Set an API key environment variable. You can find a list of available API keys here:
 * https://aistudio.google.com/app/apikey
 *
 * <p>export GOOGLE_API_KEY=YOUR_API_KEY
 *
 * <p>2. Compile the java package and run the sample code.
 *
 * <p>mvn clean compile
 *
 * <p>mvn exec:java
 * -Dexec.mainClass="com.google.genai.examples.InteractionsThoughtContent"
 */
package com.google.genai.examples;

import com.google.genai.Client;
import com.google.genai.types.interactions.Interaction;
import com.google.genai.types.interactions.content.ImageContent;
import com.google.genai.types.interactions.content.Content;
import com.google.genai.types.interactions.content.TextContent;
import com.google.genai.types.interactions.content.ThoughtContent;
import com.google.genai.types.interactions.content.ThoughtSummaryContent;
import java.util.List;

/**
 * ThoughtContent Testing Example
 *
 * <p>This example tests the ThoughtContent functionality to understand how the model's internal
 * reasoning is exposed through the Interactions API.
 *
 * <p>Structure: ThoughtContent contains:
 * - signature: A cryptographic signature for the thought
 * - summary: Optional list of Content (TextContent, ImageContent, etc.)
 *
 * <p>The summary field matches Python's structure: Optional[List[Union[TextContent, ImageContent]]]
 * allowing the model to provide reasoning summaries with both text and visual elements.
 *
 * <p>Test Cases: 1. Simple Math Reasoning - Basic arithmetic with step-by-step thinking 2. Complex
 * Logic Puzzle - Multi-step reasoning task 3. Reasoning with extended thinking mode
 *
 * <p>Note: The Interactions API is in beta and subject to change.
 */
public final class InteractionsThoughtContent {

  private static final String MODEL = "gemini-3-flash-preview";

  public static void main(String[] args) {
    Client client = new Client();

    System.out.println("=== ThoughtContent Testing Example ===\n");
    System.out.println("Testing model's internal reasoning (ThoughtContent)\n");

    // Test Case 1: Simple Math Reasoning
    testSimpleMathReasoning(client);

    // Test Case 2: Complex Logic Puzzle
    testComplexLogicPuzzle(client);

    // Test Case 3: Extended Thinking Mode
    testExtendedThinking(client);

    System.out.println("\n=== All tests completed ===");
  }

  /**
   * Test Case 1: Simple Math Reasoning
   *
   * <p>Tests if ThoughtContent appears for basic arithmetic reasoning.
   */
  private static void testSimpleMathReasoning(Client client) {
    System.out.println("\n--- Test Case 1: Simple Math Reasoning ---\n");

    // Using the convenience overload: create(model, text)
    String prompt = "Think step by step: What is 15 * 24?";

    System.out.println("=== REQUEST ===");
    System.out.println("Model: " + MODEL);
    System.out.println("Input: " + prompt);
    System.out.println();

    try {
      Interaction response = client.interactions.create(MODEL, prompt);

      System.out.println("=== RESPONSE ===");
      System.out.println(response.toJson());
      System.out.println();

      printResults(response);
      analyzeContent(response);

    } catch (Exception e) {
      System.err.println("ERROR in Test Case 1:");
      System.err.println("  Exception: " + e.getClass().getName());
      System.err.println("  Message: " + e.getMessage());
      e.printStackTrace();
    }
  }

  /**
   * Test Case 2: Complex Logic Puzzle
   *
   * <p>Tests ThoughtContent with a more complex reasoning task that requires multiple steps.
   */
  private static void testComplexLogicPuzzle(Client client) {
    System.out.println("\n--- Test Case 2: Complex Logic Puzzle ---\n");

    String prompt =
        "Solve this logic puzzle step by step:\n"
            + "Three friends - Alice, Bob, and Carol - each have a different favorite color (red,"
            + " blue, or green).\n"
            + "- Alice doesn't like blue\n"
            + "- Bob's favorite color is not red\n"
            + "- Carol likes green\n"
            + "What is each person's favorite color? Show your reasoning.";

    System.out.println("=== REQUEST ===");
    System.out.println("Model: " + MODEL);
    System.out.println("Input: " + prompt);
    System.out.println();

    try {
      Interaction response = client.interactions.create(MODEL, prompt);

      System.out.println("=== RESPONSE ===");
      System.out.println(response.toJson());
      System.out.println();

      printResults(response);
      analyzeContent(response);

    } catch (Exception e) {
      System.err.println("ERROR in Test Case 2:");
      System.err.println("  Exception: " + e.getClass().getName());
      System.err.println("  Message: " + e.getMessage());
      e.printStackTrace();
    }
  }

  /**
   * Test Case 3: Extended Thinking Mode
   *
   * <p>Tests ThoughtContent with a reasoning-heavy task.
   */
  private static void testExtendedThinking(Client client) {
    System.out.println("\n--- Test Case 3: Extended Thinking Test ---\n");

    String prompt =
        "Calculate the following and explain your reasoning:\n"
            + "If a train travels at 60 mph for 2.5 hours, then speeds up to 80 mph for another 1.5"
            + " hours, how far does it travel in total?";

    System.out.println("=== REQUEST ===");
    System.out.println("Model: " + MODEL);
    System.out.println("Input: " + prompt);
    System.out.println();

    try {
      Interaction response = client.interactions.create(MODEL, prompt);

      System.out.println("=== RESPONSE ===");
      System.out.println(response.toJson());
      System.out.println();

      printResults(response);
      analyzeContent(response);

    } catch (Exception e) {
      System.err.println("ERROR in Test Case 3:");
      System.err.println("  Exception: " + e.getClass().getName());
      System.err.println("  Message: " + e.getMessage());
      e.printStackTrace();
    }
  }

  private static void printResults(Interaction interaction) {
    System.out.println("Results:");
    System.out.println("  Interaction ID: " + interaction.id());
    System.out.println("  Status: " + interaction.status());
  }

  /**
   * Analyzes interaction content to identify and display ThoughtContent.
   *
   * <p>This method: - Iterates through all outputs in the interaction - Identifies ThoughtContent
   * instances - Displays thought signatures and summaries - Compares actual structure with expected
   * structure
   */
  private static void analyzeContent(Interaction interaction) {
    System.out.println("CONTENT ANALYSIS:");

    if (!interaction.outputs().isPresent() || interaction.outputs().get().isEmpty()) {
      System.out.println("  No outputs found in response");
      return;
    }

    int thoughtCount = 0;
    int textCount = 0;
    int otherCount = 0;

    System.out.println("\n  Analyzing " + interaction.outputs().get().size() + " output(s):");

    for (Content content : interaction.outputs().get()) {
      System.out.println("\n    Content Type: " + content.getClass().getSimpleName());

      if (content instanceof ThoughtContent) {
        thoughtCount++;
        ThoughtContent thought = (ThoughtContent) content;

        System.out.println("    >>> THOUGHT CONTENT FOUND <<<");
        System.out.println("      Signature: " + thought.signature().orElse("(none)"));

        if (thought.summary().isPresent()) {
          List<ThoughtSummaryContent> summaryContents = thought.summary().get();
          System.out.println("      Summary: " + summaryContents.size() + " item(s)");

          for (int i = 0; i < summaryContents.size(); i++) {
            ThoughtSummaryContent summaryItem = summaryContents.get(i);
            System.out.println("        Item " + (i + 1) + ": " + summaryItem.getClass().getSimpleName());

            if (summaryItem instanceof TextContent) {
              String text = ((TextContent) summaryItem).text().orElse("(empty)");
              String preview = text.length() > 100 ? text.substring(0, 100) + "..." : text;
              System.out.println("          Text: " + preview);
            } else if (summaryItem instanceof ImageContent) {
              System.out.println("          Image: " + ((ImageContent) summaryItem).uri().orElse("(no uri)"));
            }
          }
        } else {
          System.out.println("      Summary: (none)");
        }

        System.out.println("      Full ThoughtContent JSON:");
        System.out.println("      " + thought.toJson());

      } else if (content instanceof TextContent) {
        textCount++;
        TextContent text = (TextContent) content;
        String textValue = text.text().orElse("(empty)");
        String preview = textValue.length() > 100 ? textValue.substring(0, 100) + "..." : textValue;
        System.out.println("      Text Preview: " + preview);
      } else {
        otherCount++;
        System.out.println("      Content Class: " + content.getClass().getName());
      }
    }

    System.out.println("\n  SUMMARY:");
    System.out.println("    ThoughtContent instances: " + thoughtCount);
    System.out.println("    TextContent instances: " + textCount);
    System.out.println("    Other content instances: " + otherCount);

    if (thoughtCount == 0) {
      System.out.println(
          "\n  NOTE: No ThoughtContent found. This may be expected if the model didn't use"
              + " internal reasoning for this query.");
    }

    // Display final text output
    if (interaction.outputs().isPresent() && !interaction.outputs().get().isEmpty()) {
      System.out.println("\n  FINAL TEXT OUTPUT:");
      for (Content output : interaction.outputs().get()) {
        if (output instanceof TextContent) {
          System.out.println("    " + ((TextContent) output).text().orElse("(empty)"));
        }
      }
    }
  }
}
