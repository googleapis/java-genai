/*
 * Copyright 2025 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.genai.examples;

import com.google.genai.Client;
import com.google.genai.types.interactions.Input;
import com.google.genai.types.interactions.Interaction;
import com.google.genai.types.interactions.content.Content;
import com.google.genai.types.interactions.content.DocumentContent;
import com.google.genai.types.interactions.content.TextContent;

/**
 * Example demonstrating document analysis using DocumentContent with the Interactions API.
 *
 * <p>This example shows:
 * <ul>
 *   <li>Using DocumentContent.fromUri() to analyze a document from a URL
 *   <li>Using DocumentContent.fromData() to analyze base64-encoded documents
 * </ul>
 *
 * <p>To run this example:
 * <ol>
 *   <li>Set the GOOGLE_API_KEY environment variable: {@code export GOOGLE_API_KEY=YOUR_API_KEY}
 *   <li>Compile the examples: {@code mvn clean compile}
 *   <li>Run: {@code mvn exec:java -Dexec.mainClass="com.google.genai.examples.InteractionsDocumentContent"}
 * </ol>
 *
 * <p><b>Note:</b> The Interactions API is currently in beta.
 */
public final class InteractionsDocumentContent {

  public static void main(String[] args) {
    Client client = new Client();

    System.out.println("=== Interactions API: Document Content Example ===\n");

    try {
      // ===== PART 1: Document from URI =====
      System.out.println("--- PART 1: Document from URI ---\n");

      String documentUri = "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf";
      DocumentContent documentContent = DocumentContent.fromUri(documentUri, "application/pdf");
      TextContent textPrompt = TextContent.of("Summarize this document.");

      Input input = Input.fromContents(textPrompt, documentContent);

      Interaction response = client.interactions.create("gemini-3-flash-preview", input);

      System.out.println("=== RESPONSE ===");
      System.out.println(response.toJson());
      System.out.println();

      printResults(response);

      // ===== PART 2: Document from Inline Data (base64) =====
      System.out.println("\n--- PART 2: Document from Inline Data (Base64) ---\n");

      // Minimal valid PDF document (1 page with "Hello World")
      String base64Data =
          "JVBERi0xLjQKJeLjz9MKMSAwIG9iago8PAovVHlwZSAvQ2F0YWxvZwovUGFnZXMgMiAwIFIKPj4KZW5kb2JqCjIgMCBvYmoKPDwKL1R5cGUgL1BhZ2VzCi9LaWRzIFszIDAgUl0KL0NvdW50IDEKL01lZGlhQm94IFswIDAgNjEyIDc5Ml0KPj4KZW5kb2JqCjMgMCBvYmoKPDwKL1R5cGUgL1BhZ2UKL1BhcmVudCAyIDAgUgovUmVzb3VyY2VzIDw8Ci9Gb250IDw8Ci9GMSA0IDAgUgo+Pgo+PgovQ29udGVudHMgNSAwIFIKPj4KZW5kb2JqCjQgMCBvYmoKPDwKL1R5cGUgL0ZvbnQKL1N1YnR5cGUgL1R5cGUxCi9CYXNlRm9udCAvSGVsdmV0aWNhCj4+CmVuZG9iago1IDAgb2JqCjw8Ci9MZW5ndGggNDQKPj4Kc3RyZWFtCkJUCi9GMSA0OCBUZgoxMCA3MDAgVGQKKEhlbGxvIFdvcmxkKSBUagpFVAplbmRzdHJlYW0KZW5kb2JqCnhyZWYKMCA2CjAwMDAwMDAwMDAgNjU1MzUgZgogCjAwMDAwMDAwMTUgMDAwMDAgbiAKMDAwMDAwMDA2NCAwMDAwMCBuIAowMDAwMDAwMTUxIDAwMDAwIG4gCjAwMDAwMDAyNjIgMDAwMDAgbiAKMDAwMDAwMDM0OSAwMDAwMCBuIAp0cmFpbGVyCjw8Ci9TaXplIDYKL1Jvb3QgMSAwIFIKPj4Kc3RhcnR4cmVmCjQ0MgolJUVPRgo=";
      DocumentContent documentFromData = DocumentContent.fromData(base64Data, "application/pdf");
      TextContent textPrompt2 = TextContent.of("What is the content of this document?");

      Input input2 = Input.fromContents(textPrompt2, documentFromData);

      Interaction response2 = client.interactions.create("gemini-3-flash-preview", input2);

      System.out.println("=== RESPONSE ===");
      System.out.println(response2.toJson());
      System.out.println();

      printResults(response2);

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

  private InteractionsDocumentContent() {}
}
