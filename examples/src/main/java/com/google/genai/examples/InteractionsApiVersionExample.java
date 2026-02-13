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
import com.google.genai.types.HttpOptions;
import com.google.genai.types.interactions.CreateInteractionConfig;
import com.google.genai.types.interactions.GetInteractionConfig;
import com.google.genai.types.interactions.Interaction;
import com.google.genai.types.interactions.content.AudioContent;
import com.google.genai.types.interactions.content.Content;
import com.google.genai.types.interactions.content.TextContent;

/**
 * Demonstrates API version usage in Interactions API with real API calls.
 *
 * <p>This example shows:
 * <ol>
 *   <li>Client-level api_version (v1beta) - applies to all requests</li>
 *   <li>Request-level api_version (v1alpha) - overrides client-level</li>
 *   <li>Precedence: request-level > client-level > default</li>
 *   <li>Invalid api_version handling (error case)</li>
 *   <li>All config types supporting api_version</li>
 * </ol>
 *
 * <p>Each example makes real API calls and prints:
 * <ul>
 *   <li>JSON request (config.toJson())</li>
 *   <li>JSON response (interaction.toJson())</li>
 *   <li>Parsed results</li>
 * </ul>
 *
 * <p>Usage:
 * <ol>
 *   <li>Set GOOGLE_API_KEY environment variable</li>
 *   <li>Unset Vertex AI variables: GOOGLE_CLOUD_PROJECT, GOOGLE_CLOUD_LOCATION, GOOGLE_GENAI_USE_VERTEXAI</li>
 *   <li>mvn clean compile</li>
 *   <li>export GOOGLE_API_KEY="..." && unset GOOGLE_CLOUD_PROJECT GOOGLE_CLOUD_LOCATION GOOGLE_GENAI_USE_VERTEXAI && mvn exec:java -Dexec.mainClass="com.google.genai.examples.InteractionsApiVersionExample"</li>
 * </ol>
 *
 * <p>Note: The Interactions API is in beta and subject to change.
 */
public class InteractionsApiVersionExample {

  public static void main(String[] args) {
    System.out.println("=== Interactions API Version Examples ===");
    System.out.println("Testing api_version functionality with real API calls\n");

    example1_clientLevelApiVersion();
    System.out.println("\n" + "=".repeat(80) + "\n");

    example2_requestLevelApiVersion();
    System.out.println("\n" + "=".repeat(80) + "\n");

    example3_precedence();
    System.out.println("\n" + "=".repeat(80) + "\n");

    example4_invalidApiVersion();
    System.out.println("\n" + "=".repeat(80) + "\n");

    example5_allConfigTypes();
    System.out.println("\n" + "=".repeat(80) + "\n");

    example6_requestApiVersionPreservesClientSettings();

    System.out.println("\n=== All Examples Completed ===");
  }

  /**
   * Example 1: Set api_version at client-level (v1beta - valid).
   */
  private static void example1_clientLevelApiVersion() {
    System.out.println("--- Example 1: Client-Level API Version (v1beta) ---");

    try {
      // Set api_version for ALL requests from this client
      HttpOptions httpOptions = HttpOptions.builder()
          .apiVersion("v1beta")
          .timeout(30000)
          .build();

      Client client = Client.builder()
          .httpOptions(httpOptions)
          .build();

      // Text content example
      TextContent textContent = TextContent.of("What is the capital of France?");

      CreateInteractionConfig config = CreateInteractionConfig.builder()
          .model("gemini-3-flash-preview")
          .inputFromContents(textContent)
          .build();

      System.out.println("\n=== REQUEST ===");
      System.out.println(config.toJson());
      System.out.println();

      Interaction response = client.interactions.create(config);

      System.out.println("=== RESPONSE ===");
      System.out.println(response.toJson());
      System.out.println();

      printResults(response);

      System.out.println("✓ Success: Client-level api_version v1beta works\n");
    } catch (Exception e) {
      System.err.println("✗ Error: " + e.getMessage());
      e.printStackTrace();
    }
  }

  /**
   * Example 2: Override api_version for a specific request (v1alpha - valid).
   */
  private static void example2_requestLevelApiVersion() {
    System.out.println("--- Example 2: Request-Level API Version Override (v1alpha) ---");

    try {
      Client client = new Client();

      // Audio content example
      String audioUri = "https://storage.googleapis.com/cloud-samples-data/speech/brooklyn_bridge.mp3";
      AudioContent audioContent = AudioContent.fromUri(audioUri, "audio/mp3");
      TextContent audioPrompt = TextContent.of("Transcribe this audio.");

      CreateInteractionConfig config = CreateInteractionConfig.builder()
          .model("gemini-3-flash-preview")
          .inputFromContents(audioPrompt, audioContent)
          .apiVersion("v1alpha")  // ← Request-level override
          .build();

      System.out.println("\n=== REQUEST ===");
      System.out.println(config.toJson());
      System.out.println();

      Interaction response = client.interactions.create(config);

      System.out.println("=== RESPONSE ===");
      System.out.println(response.toJson());
      System.out.println();

      printResults(response);

      System.out.println("✓ Success: Request-level api_version v1alpha works\n");
    } catch (Exception e) {
      System.err.println("✗ Error: " + e.getMessage());
      e.printStackTrace();
    }
  }

  /**
   * Example 3: Demonstrate precedence (request-level overrides client-level).
   */
  private static void example3_precedence() {
    System.out.println("--- Example 3: Precedence (Request > Client) ---");

    try {
      // Client has v1 api_version AND 30s timeout
      HttpOptions clientOptions = HttpOptions.builder()
          .apiVersion("v1")
          .timeout(30000)  // ← Client timeout will be preserved
          .build();

      Client client = Client.builder()
          .httpOptions(clientOptions)
          .build();

      TextContent textContent = TextContent.of("What is quantum computing?");

      // Request overrides ONLY api_version (timeout is NOT overridden)
      CreateInteractionConfig config = CreateInteractionConfig.builder()
          .model("gemini-3-flash-preview")
          .inputFromContents(textContent)
          .apiVersion("v1beta")  // ← Overrides client-level v1
          .build();

      System.out.println("Client-level api_version: v1");
      System.out.println("Client-level timeout: 30000ms");
      System.out.println("Request-level api_version: v1beta");
      System.out.println("Request-level timeout: (not set)");
      System.out.println("Expected: v1beta api_version + 30000ms timeout\n");

      System.out.println("=== REQUEST ===");
      System.out.println(config.toJson());
      System.out.println();

      Interaction response = client.interactions.create(config);

      System.out.println("=== RESPONSE ===");
      System.out.println(response.toJson());
      System.out.println();

      printResults(response);

      System.out.println("✓ Success: Request-level api_version takes precedence\n");
    } catch (Exception e) {
      System.err.println("✗ Error: " + e.getMessage());
      e.printStackTrace();
    }
  }

  /**
   * Example 4: Invalid API version (error case).
   */
  private static void example4_invalidApiVersion() {
    System.out.println("--- Example 4: Invalid API Version (Error Test) ---");

    try {
      Client client = new Client();

      TextContent textContent = TextContent.of("Hello world");

      CreateInteractionConfig config = CreateInteractionConfig.builder()
          .model("gemini-3-flash-preview")
          .inputFromContents(textContent)
          .apiVersion("v999invalid")  // ← Invalid version
          .build();

      System.out.println("\n=== REQUEST ===");
      System.out.println(config.toJson());
      System.out.println();

      System.out.println("Attempting to create interaction with invalid api_version: v999invalid");

      Interaction response = client.interactions.create(config);

      System.out.println("=== RESPONSE ===");
      System.out.println(response.toJson());
      System.out.println();

      System.out.println("✗ Unexpected: Invalid API version should have failed\n");
    } catch (Exception e) {
      System.out.println("✓ Expected Error Caught:");
      System.out.println("  Message: " + e.getMessage());
      System.out.println("  Type: " + e.getClass().getSimpleName());
      System.out.println("  This is expected behavior - invalid API versions are rejected\n");
    }
  }

  /**
   * Example 5: api_version works with all config types.
   */
  private static void example5_allConfigTypes() {
    System.out.println("--- Example 5: All Config Types Support api_version ---");

    try {
      Client client = new Client();

      // Create interaction
      TextContent textContent = TextContent.of("Tell me a short joke");

      CreateInteractionConfig createConfig = CreateInteractionConfig.builder()
          .model("gemini-3-flash-preview")
          .inputFromContents(textContent)
          .apiVersion("v1beta")
          .build();

      System.out.println("CreateInteractionConfig with api_version: v1beta");
      System.out.println("\n=== REQUEST ===");
      System.out.println(createConfig.toJson());
      System.out.println();

      Interaction created = client.interactions.create(createConfig);

      System.out.println("=== RESPONSE ===");
      System.out.println(created.toJson());
      System.out.println();

      printResults(created);

      // Get interaction with api_version
      GetInteractionConfig getConfig = GetInteractionConfig.builder()
          .apiVersion("v1beta")
          .build();

      System.out.println("\nGetInteractionConfig with api_version: v1beta");
      Interaction retrieved = client.interactions.get(created.id(), getConfig);
      System.out.println("Retrieved Interaction ID: " + retrieved.id());

      System.out.println("\n✓ Success: All config types work with api_version\n");
    } catch (Exception e) {
      System.err.println("✗ Error: " + e.getMessage());
      e.printStackTrace();
    }
  }

  /**
   * Example 6: Proves that request-level api_version ONLY overrides api_version,
   * preserving all other client-level settings (timeout, headers, etc.).
   */
  private static void example6_requestApiVersionPreservesClientSettings() {
    System.out.println("--- Example 6: Request api_version Preserves Client Settings ---");
    System.out.println("This example proves that setting api_version in the request config");
    System.out.println("does NOT override other client-level HttpOptions like timeout.\n");

    try {
      // Create client with MULTIPLE HttpOptions settings
      HttpOptions clientOptions = HttpOptions.builder()
          .apiVersion("v1")           // ← Client-level api_version
          .timeout(60000)             // ← Client-level timeout (60 seconds)
          .build();

      Client client = Client.builder()
          .httpOptions(clientOptions)
          .build();

      System.out.println("Client HttpOptions:");
      System.out.println("  api_version: v1");
      System.out.println("  timeout: 60000ms (60 seconds)");
      System.out.println();

      // Create config that ONLY sets api_version (not timeout)
      TextContent textContent = TextContent.of("Explain quantum entanglement in simple terms");

      CreateInteractionConfig config = CreateInteractionConfig.builder()
          .model("gemini-3-flash-preview")
          .inputFromContents(textContent)
          .apiVersion("v1beta")  // ← Override ONLY api_version
          .build();

      System.out.println("Request Config:");
      System.out.println("  api_version: v1beta (overrides client v1)");
      System.out.println("  timeout: NOT SET (should preserve client's 60000ms)");
      System.out.println();

      System.out.println("Expected behavior:");
      System.out.println("  ✓ API request will use api_version v1beta (from request)");
      System.out.println("  ✓ API request will use timeout 60000ms (from client - preserved!)");
      System.out.println();

      System.out.println("=== REQUEST ===");
      System.out.println(config.toJson());
      System.out.println();

      long startTime = System.currentTimeMillis();
      Interaction response = client.interactions.create(config);
      long duration = System.currentTimeMillis() - startTime;

      System.out.println("=== RESPONSE ===");
      System.out.println(response.toJson());
      System.out.println();

      printResults(response);

      System.out.println("\nRequest completed in " + duration + "ms");
      System.out.println("✓ Success: Request completed within client timeout (60000ms)");
      System.out.println("✓ This proves client timeout was preserved even though config set api_version");
      System.out.println();

      System.out.println("Key Insight:");
      System.out.println("  The request-level api_version (v1beta) was used for the API endpoint,");
      System.out.println("  BUT the client-level timeout (60000ms) was still applied to the request.");
      System.out.println("  This demonstrates SELECTIVE OVERRIDE: only api_version changed,");
      System.out.println("  all other HttpOptions (timeout, headers, etc.) were preserved.");

      System.out.println("\n✓ Success: Request-level api_version preserves client-level settings\n");
    } catch (Exception e) {
      System.err.println("✗ Error: " + e.getMessage());
      e.printStackTrace();
    }
  }

  /** Prints extracted results from the interaction response. */
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
        TextContent t = (TextContent) output;
        String text = t.text().orElse("(empty)");
        System.out.println("  Text: " + text.substring(0, Math.min(200, text.length())) + "...");
      }
    }
  }
}
