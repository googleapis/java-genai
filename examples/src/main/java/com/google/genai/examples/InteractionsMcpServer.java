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
 * <p>IMPORTANT: This example requires a running MCP (Model Context Protocol) server.
 *
 * <p>Setup Instructions:
 *
 * <p>1. Set up an MCP server. You can use one of the reference implementations:
 *
 * <p>- MCP Server SDK: https://github.com/modelcontextprotocol/servers
 *
 * <p>- Example: Weather MCP Server (Python)
 *
 * <p>pip install mcp
 *
 * <p>python -m mcp.server.weather --port 8080
 *
 * <p>2. Set an API key environment variable:
 *
 * <p>export GOOGLE_API_KEY=YOUR_API_KEY
 *
 * <p>3. Update the MCP server URL in this example to point to your running MCP server.
 *
 * <p>4. Compile and run:
 *
 * <p>mvn clean compile
 *
 * <p>mvn exec:java -Dexec.mainClass="com.google.genai.examples.InteractionsMcpServer"
 */
package com.google.genai.examples;

import com.google.common.collect.ImmutableMap;
import com.google.genai.Client;
import com.google.genai.types.interactions.CreateInteractionConfig;
import com.google.genai.types.interactions.Interaction;
import com.google.genai.types.interactions.content.Content;
import com.google.genai.types.interactions.content.McpServerToolCallContent;
import com.google.genai.types.interactions.content.McpServerToolResultContent;
import com.google.genai.types.interactions.content.TextContent;
import com.google.genai.types.interactions.tools.McpServer;

/**
 * Example: MCP Server Tool with the Interactions API
 *
 * <p>Demonstrates how to use the McpServer to enable the model to interact with an MCP (Model
 * Context Protocol) server. This allows integration with external tools and services that implement
 * the MCP protocol.
 *
 * <p>IMPORTANT: Requires a running MCP server. See usage instructions above.
 *
 * <p>Note: The Interactions API is in beta and subject to change.
 */
public final class InteractionsMcpServer {

  public static void main(String[] args) {
    // Instantiate the client. The client gets the API key from the environment variable
    // `GOOGLE_API_KEY`.
    //
    Client client = new Client();

    System.out.println("=== Interactions API: MCP Server Tool Example ===\n");

    System.out.println("IMPORTANT: This example requires a running MCP server.\n");
    System.out.println("See the file header comments for setup instructions.\n");

    // ===== STEP 1: Create McpServer =====
    System.out.println("STEP 1: Create McpServer\n");

    // Configure the MCP server connection
    // Update these values to match your MCP server setup
    McpServer mcpTool =
        McpServer.builder()
            .name("my-mcp-server")
            .url("http://localhost:8080/mcp") // Update this URL to your MCP server
            // Optional: Add custom headers if your MCP server requires authentication
            .headers(
                ImmutableMap.of(
                    "Content-Type", "application/json"
                    // "Authorization", "Bearer YOUR_TOKEN"
                    ))
            // Optional: Restrict to specific tools from the MCP server
            // .allowedTools("weather", "calculator")
            .build();

    System.out.println("McpServer created successfully");
    System.out.println("Name: " + mcpTool.name().orElse("N/A"));
    System.out.println("URL: " + mcpTool.url().orElse("N/A"));
    System.out.println();

    // ===== STEP 2: Create interaction with MCP Server enabled =====
    System.out.println("---\n");
    System.out.println("STEP 2: Create interaction with MCP Server enabled\n");

    String userQuestion =
        "What's the weather like in San Francisco? "
            + "(This uses the MCP server's weather tool)";
    System.out.println("User: " + userQuestion + "\n");

    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model("gemini-3-flash-preview")
            .input(userQuestion)
            .tools(mcpTool)
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
          if (content instanceof TextContent) {
            System.out.println("Text: " + ((TextContent) content).text().orElse("(empty)"));
            System.out.println();
          } else if (content instanceof McpServerToolCallContent) {
            McpServerToolCallContent mcpCall = (McpServerToolCallContent) content;
            System.out.println("MCP Server Tool Call:");
            System.out.println("  ID: " + mcpCall.id());
            System.out.println("  Tool Name: " + mcpCall.name());
            System.out.println("  Server Name: " + mcpCall.serverName());
            System.out.println("  Arguments: " + mcpCall.arguments());
            System.out.println();
          } else if (content instanceof McpServerToolResultContent) {
            McpServerToolResultContent mcpResult = (McpServerToolResultContent) content;
            System.out.println("MCP Server Tool Result:");
            System.out.println("  Call ID: " + mcpResult.callId());
            System.out.println("  Tool Name: " + mcpResult.name().orElse("N/A"));
            System.out.println("  Server Name: " + mcpResult.serverName().orElse("N/A"));
            System.out.println("  Result: " + mcpResult.result());
            System.out.println();
          }
        }
      }

      System.out.println("\n=== Example completed successfully ===");

    } catch (Exception e) {
      System.err.println("\n=== Error occurred ===");
      System.err.println("Error: " + e.getMessage());
      System.err.println("\nNote: This example requires a running MCP server.");
      System.err.println("Please check:");
      System.err.println("  1. Your MCP server is running and accessible");
      System.err.println("  2. The URL in this example matches your MCP server endpoint");
      System.err.println("  3. Any required authentication headers are configured");
      System.err.println("  4. Your firewall allows connections to the MCP server");
      e.printStackTrace();
    }
  }

  private InteractionsMcpServer() {}
}
