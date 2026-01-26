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
 * <p>IMPORTANT: Computer Use tool may require special API access. This example demonstrates the
 * configuration but may not work without proper authorization.
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
 * <p>mvn exec:java -Dexec.mainClass="com.google.genai.examples.InteractionsComputerUse"
 */
package com.google.genai.examples;

import com.google.genai.Client;
import com.google.genai.types.interactions.CreateInteractionConfig;
import com.google.genai.types.interactions.Interaction;
import com.google.genai.types.interactions.content.Content;
import com.google.genai.types.interactions.content.TextContent;
import com.google.genai.types.interactions.tools.ComputerUse;

/**
 * Example: Computer Use Tool with the Interactions API
 *
 * <p>Demonstrates how to use the ComputerUse to enable the model to interact with a computer
 * environment. This tool allows the model to control applications, browse web pages, and perform
 * other computer-based tasks.
 *
 * <p>IMPORTANT NOTES:
 * <ul>
 *   <li>This feature may require special API access and authorization.</li>
 *   <li>ComputerUse is a <b>configuration-only</b> tool - it enables a capability but does not
 *       have explicit call/result content types in the SDK.</li>
 *   <li>Results from computer use operations are returned as standard content types like
 *       TextContent or ImageContent (e.g., screenshots).</li>
 *   <li>Unlike FunctionCallContent or CodeExecutionCallContent, there are no
 *       ComputerUseCallContent or ComputerUseResultContent types.</li>
 * </ul>
 *
 * <p>Note: The Interactions API is in beta and subject to change.
 */
public final class InteractionsComputerUse {

  public static void main(String[] args) {
    // Instantiate the client. The client gets the API key from the environment variable
    // `GOOGLE_API_KEY`.
    //
    Client client = new Client();

    System.out.println("=== Interactions API: Computer Use Tool Example ===\n");

    System.out.println(
        "IMPORTANT: Computer Use tool may require special API access and authorization.\n");

    // ===== STEP 1: Create ComputerUse =====
    System.out.println("STEP 1: Create ComputerUse\n");

    // Configure the computer use environment
    // Common environments: "browser" for web browsing, "desktop" for full desktop access
    //
    // IMPORTANT: ComputerUse is a configuration that enables computer use capabilities.
    // Unlike FunctionCall or CodeExecution, it does NOT have corresponding
    // ComputerUseCallContent or ComputerUseResultContent types.
    // Results are returned as standard content types (TextContent, ImageContent, etc.)
    ComputerUse computerTool =
        ComputerUse.builder()
            .environment("browser")
            // Optionally exclude predefined functions
            // .excludedPredefinedFunctions("function1", "function2")
            .build();

    System.out.println("ComputerUse created successfully");
    System.out.println("Environment: " + computerTool.environment().orElse("default"));
    System.out.println();

    // ===== STEP 2: Create interaction with Computer Use enabled =====
    System.out.println("---\n");
    System.out.println("STEP 2: Create interaction with Computer Use enabled\n");

    String userQuestion = "Navigate to https://www.example.com and tell me what you see.";
    System.out.println("User: " + userQuestion + "\n");

    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model("gemini-3-flash-preview")
            .input(userQuestion)
            .tools(computerTool)
            .build();

    // Print the request JSON
    System.out.println("=== REQUEST ===");
    System.out.println(config.toJson());
    System.out.println();

    try {
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
          System.out.println("Content Type: " + content.getClass().getSimpleName());

          if (content instanceof TextContent) {
            System.out.println("Text: " + ((TextContent) content).text().orElse("(empty)"));
            System.out.println();
          }

          // IMPORTANT: ComputerUse does NOT have dedicated content types
          // (no ComputerUseCallContent or ComputerUseResultContent).
          //
          // Instead, computer use operations return results as:
          // - TextContent: For textual responses and descriptions
          // - ImageContent: For screenshots of computer actions
          // - Other standard content types as needed
          //
          // This is different from tools like FunctionCall, CodeExecution, etc.,
          // which have explicit call/result content type pairs.
        }
      }

      System.out.println("\n=== Example completed successfully ===");

    } catch (Exception e) {
      System.err.println("\n=== Error occurred ===");
      System.err.println("Error: " + e.getMessage());
      System.err.println(
          "\nNote: Computer Use tool may require special API access or authorization.");
      System.err.println("Please check:");
      System.err.println("  1. Your API key has Computer Use permissions");
      System.err.println("  2. The feature is enabled for your account");
      System.err.println("  3. You're using a model that supports Computer Use");
      e.printStackTrace();
    }
  }

  private InteractionsComputerUse() {}
}
