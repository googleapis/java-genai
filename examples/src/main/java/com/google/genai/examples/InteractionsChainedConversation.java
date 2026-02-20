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
 * <p>Demonstrates how to chain multiple API calls into a continuous conversation using
 * {@code previousInteractionId}. This is useful when:
 *
 * <ul>
 *   <li>You want server-side conversation history (no need to resend previous messages)
 *   <li>Building chatbots or assistants with persistent context
 *   <li>Breaking up long conversations across multiple requests
 * </ul>
 *
 * <p><b>How it works:</b> Each interaction returns an ID. Pass this ID as
 * {@code previousInteractionId} in the next request to link them together.
 *
 * <p>To run this example:
 *
 * <ol>
 *   <li>Set the GOOGLE_API_KEY environment variable: {@code export GOOGLE_API_KEY=YOUR_API_KEY}
 *   <li>Compile: {@code mvn clean compile}
 *   <li>Run: {@code mvn exec:java
 *       -Dexec.mainClass="com.google.genai.examples.InteractionsChainedConversation"}
 * </ol>
 *
 * <p><b>Note:</b> The Interactions API is currently in beta.
 */
public final class InteractionsChainedConversation {

  private static final String SEPARATOR = "─".repeat(60);
  private static final String MODEL = "gemini-3-flash-preview";
  private static final String SYSTEM_INSTRUCTION =
      "You are a helpful cooking assistant. Keep responses brief (2-3 sentences max).";

  public static void main(String[] args) {
    Client client = new Client();

    printHeader("Chained Conversation Example");
    System.out.println("This example shows how to chain interactions for conversation continuity.");
    System.out.println("Each response ID becomes the 'previousInteractionId' for the next request.");
    System.out.println();

    try {
      // ═══════════════════════════════════════════════════════════════
      // TURN 1: Start the conversation
      // ═══════════════════════════════════════════════════════════════
      printTurnHeader(1, "Starting conversation", null);

      String prompt1 = "What ingredients do I need for pasta aglio e olio?";
      printRequest(prompt1, null);

      CreateInteractionConfig config1 =
          CreateInteractionConfig.builder()
              .model(MODEL)
              .systemInstruction(SYSTEM_INSTRUCTION)
              .input(prompt1)
              .build();

      Interaction response1 = client.interactions.create(config1);
      printResponse(response1);

      // ═══════════════════════════════════════════════════════════════
      // TURN 2: Follow-up question (linked to turn 1)
      // ═══════════════════════════════════════════════════════════════
      printTurnHeader(2, "Follow-up question", response1.id());

      String prompt2 = "How long should I cook the garlic?";
      printRequest(prompt2, response1.id());

      CreateInteractionConfig config2 =
          CreateInteractionConfig.builder()
              .model(MODEL)
              .systemInstruction(SYSTEM_INSTRUCTION)
              .input(prompt2)
              .previousInteractionId(response1.id())  // Link to previous
              .build();

      Interaction response2 = client.interactions.create(config2);
      printResponse(response2);

      // ═══════════════════════════════════════════════════════════════
      // TURN 3: Another follow-up (linked to turn 2)
      // ═══════════════════════════════════════════════════════════════
      printTurnHeader(3, "Asking about variations", response2.id());

      String prompt3 = "What protein can I add?";
      printRequest(prompt3, response2.id());

      CreateInteractionConfig config3 =
          CreateInteractionConfig.builder()
              .model(MODEL)
              .systemInstruction(SYSTEM_INSTRUCTION)
              .input(prompt3)
              .previousInteractionId(response2.id())  // Link to previous
              .build();

      Interaction response3 = client.interactions.create(config3);
      printResponse(response3);

      // ═══════════════════════════════════════════════════════════════
      // TURN 4: Test context retention (linked to turn 3)
      // ═══════════════════════════════════════════════════════════════
      printTurnHeader(4, "Testing context retention", response3.id());

      String prompt4 = "Summarize our recipe discussion in one sentence.";
      printRequest(prompt4, response3.id());

      CreateInteractionConfig config4 =
          CreateInteractionConfig.builder()
              .model(MODEL)
              .systemInstruction(SYSTEM_INSTRUCTION)
              .input(prompt4)
              .previousInteractionId(response3.id())  // Link to previous
              .build();

      Interaction response4 = client.interactions.create(config4);
      printResponse(response4);

      // ═══════════════════════════════════════════════════════════════
      // Summary
      // ═══════════════════════════════════════════════════════════════
      printSection("Conversation Chain Summary");
      System.out.println("  Turn 1: " + response1.id());
      System.out.println("     ↓");
      System.out.println("  Turn 2: " + response2.id());
      System.out.println("     ↓");
      System.out.println("  Turn 3: " + response3.id());
      System.out.println("     ↓");
      System.out.println("  Turn 4: " + response4.id());
      System.out.println();
      System.out.println("  ✓ All turns linked via previousInteractionId");
      System.out.println("  ✓ Context preserved across 4 separate API calls");

      printFooter("Example completed successfully");

    } catch (Exception e) {
      printFooter("Error: " + e.getMessage());
      e.printStackTrace();
    }
  }

  // ─────────────────────────────────────────────────────────────
  // Output Formatting Helpers
  // ─────────────────────────────────────────────────────────────

  private static void printHeader(String title) {
    System.out.println();
    System.out.println("╔" + "═".repeat(58) + "╗");
    System.out.println("║  " + centerText(title, 54) + "  ║");
    System.out.println("╚" + "═".repeat(58) + "╝");
    System.out.println();
  }

  private static void printTurnHeader(int turnNumber, String description, String previousId) {
    System.out.println();
    System.out.println("┌" + "─".repeat(58) + "┐");
    System.out.println("│  TURN " + turnNumber + ": " + description
        + " ".repeat(Math.max(0, 47 - description.length())) + "│");
    System.out.println("└" + "─".repeat(58) + "┘");
    if (previousId != null) {
      System.out.println("  → Linking to: " + previousId);
    } else {
      System.out.println("  → Starting fresh (no previous interaction)");
    }
    System.out.println();
  }

  private static void printSection(String title) {
    System.out.println();
    System.out.println(SEPARATOR);
    System.out.println("  " + title);
    System.out.println(SEPARATOR);
  }

  private static void printFooter(String message) {
    System.out.println();
    System.out.println(SEPARATOR);
    System.out.println("  " + message);
    System.out.println(SEPARATOR);
    System.out.println();
  }

  private static String centerText(String text, int width) {
    if (text.length() >= width) {
      return text;
    }
    int padding = (width - text.length()) / 2;
    return " ".repeat(padding) + text + " ".repeat(width - text.length() - padding);
  }

  private static void printRequest(String prompt, String previousId) {
    System.out.println("  REQUEST:");
    System.out.println("    Model: " + MODEL);
    System.out.println("    Input: \"" + prompt + "\"");
    if (previousId != null) {
      System.out.println("    previousInteractionId: " + previousId);
    }
    System.out.println();
  }

  private static void printResponse(Interaction interaction) {
    // Metadata
    System.out.println("  Interaction ID: " + interaction.id());
    System.out.println("  Status: " + interaction.status());
    interaction.previousInteractionId()
        .ifPresent(id -> System.out.println("  Previous ID: " + id));

    // Response text
    System.out.println();
    System.out.println("  Response:");
    String text = getTextOutput(interaction);
    // Indent each line of the response
    for (String line : text.split("\n")) {
      System.out.println("    " + line);
    }

    // Token usage
    if (interaction.usage().isPresent()) {
      Usage usage = interaction.usage().get();
      System.out.println();
      System.out.println("  Tokens: " + usage.totalInputTokens().orElse(0) + " in / "
          + usage.totalOutputTokens().orElse(0) + " out");
    }
  }

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

  private InteractionsChainedConversation() {}
}
