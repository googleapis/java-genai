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
 * <p>mvn exec:java -Dexec.mainClass="com.google.genai.examples.InteractionsCodeExecution"
 */
package com.google.genai.examples;

import com.google.genai.Client;
import com.google.genai.types.interactions.CreateInteractionConfig;
import com.google.genai.types.interactions.Interaction;
import com.google.genai.types.interactions.content.CodeExecutionCallContent;
import com.google.genai.types.interactions.content.CodeExecutionResultContent;
import com.google.genai.types.interactions.content.Content;
import com.google.genai.types.interactions.content.TextContent;
import com.google.genai.types.interactions.tools.CodeExecution;

/**
 * Example: Code Execution Tool with the Interactions API
 *
 * <p>Demonstrates how to use the CodeExecution to enable the model to execute code as part of
 * generation. The model can write and run code to solve problems.
 *
 * <p>Note: The Interactions API is in beta and subject to change.
 */
public final class InteractionsCodeExecution {

  public static void main(String[] args) {
    // Instantiate the client. The client gets the API key from the environment variable
    // `GOOGLE_API_KEY`.
    //
    Client client = new Client();

    System.out.println("=== Interactions API: Code Execution Tool Example ===\n");

    // ===== STEP 1: Create CodeExecution =====
    System.out.println("STEP 1: Create CodeExecution\n");

    CodeExecution codeTool = CodeExecution.builder().build();

    System.out.println("CodeExecution created successfully\n");

    // ===== STEP 2: Create interaction with Code Execution enabled =====
    System.out.println("---\n");
    System.out.println("STEP 2: Create interaction with Code Execution enabled\n");

    String userQuestion =
        "Calculate the first 20 Fibonacci numbers and find their sum. "
            + "Show me the code and the result.";
    System.out.println("User: " + userQuestion + "\n");

    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model("gemini-3-flash-preview")
            .input(userQuestion)
            .tools(codeTool)
            .build();

    // Print the request JSON
    System.out.println("=== REQUEST ===");
    System.out.println(config.toJson());
    System.out.println();

    Interaction response = client.interactions.create(config);

    // Print the response JSON
    System.out.println("=== RESPONSE ===");
    System.out.println(response.toJson());
    System.out.println();

    // ===== STEP 3: Extract and display the results =====
    System.out.println("---\n");
    System.out.println("STEP 3: Extract and display the results\n");

    System.out.println("Response received. Interaction ID: " + response.id());
    System.out.println();

    if (response.outputs().isPresent()) {
      for (Content content : response.outputs().get()) {
        if (content instanceof TextContent) {
          System.out.println("Text: " + ((TextContent) content).text().orElse("(empty)"));
          System.out.println();
        } else if (content instanceof CodeExecutionCallContent) {
          CodeExecutionCallContent codeCall = (CodeExecutionCallContent) content;
          System.out.println("Code Execution Call:");
          System.out.println("  ID: " + codeCall.id());
          if (codeCall.arguments().isPresent()) {
            System.out.println("  Language: " + codeCall.arguments().get().language().orElse("N/A"));
            System.out.println("  Code:");
            System.out.println("  ---");
            System.out.println(codeCall.arguments().get().code().orElse("(empty)"));
            System.out.println("  ---");
          }
          System.out.println();
        } else if (content instanceof CodeExecutionResultContent) {
          CodeExecutionResultContent codeResult = (CodeExecutionResultContent) content;
          System.out.println("Code Execution Result:");
          System.out.println("  Call ID: " + codeResult.callId().orElse("N/A"));
          System.out.println("  Is Error: " + codeResult.isError().orElse(false));
          System.out.println("  Result:");
          System.out.println("  ---");
          System.out.println(codeResult.result().orElse("(empty)"));
          System.out.println("  ---");
          System.out.println();
        }
      }
    }

    System.out.println("\n=== Example completed ===");
  }

  private InteractionsCodeExecution() {}
}
