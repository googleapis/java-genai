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
 * <p>mvn exec:java -Dexec.mainClass="com.google.genai.examples.InteractionsUrlContext"
 */
package com.google.genai.examples;

import com.google.genai.Client;
import com.google.genai.types.interactions.CreateInteractionConfig;
import com.google.genai.types.interactions.Interaction;
import com.google.genai.types.interactions.UrlContextResult;
import com.google.genai.types.interactions.content.Content;
import com.google.genai.types.interactions.content.TextContent;
import com.google.genai.types.interactions.content.UrlContextCallContent;
import com.google.genai.types.interactions.content.UrlContextResultContent;
import com.google.genai.types.interactions.tools.UrlContext;
import java.util.List;

/**
 * Example: URL Context Tool with the Interactions API
 *
 * <p>Demonstrates how to use the UrlContext to enable the model to retrieve and use context
 * from URLs. The model can fetch web pages and use their content to answer questions.
 *
 * <p>Note: The Interactions API is in beta and subject to change.
 */
public final class InteractionsUrlContext {

  public static void main(String[] args) {
    // Instantiate the client. The client gets the API key from the environment variable
    // `GOOGLE_API_KEY`.
    //
    Client client = new Client();

    System.out.println("=== Interactions API: URL Context Tool Example ===\n");

    // ===== STEP 1: Create UrlContext =====
    System.out.println("STEP 1: Create UrlContext\n");

    UrlContext urlTool = UrlContext.builder().build();

    System.out.println("UrlContext created successfully\n");

    // ===== STEP 2: Create interaction with URL Context enabled =====
    System.out.println("---\n");
    System.out.println("STEP 2: Create interaction with URL Context enabled\n");

    String userQuestion =
        "Please fetch and summarize the content from https://www.wikipedia.org/wiki/Artificial_intelligence";
    System.out.println("User: " + userQuestion + "\n");

    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model("gemini-3-flash-preview")
            .input(userQuestion)
            .tools(urlTool)
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
        } else if (content instanceof UrlContextCallContent) {
          UrlContextCallContent urlCall = (UrlContextCallContent) content;
          System.out.println("URL Context Call:");
          System.out.println("  ID: " + urlCall.id());
          if (urlCall.arguments().isPresent()) {
            System.out.println("  URLs: " + urlCall.arguments().get().urls().orElse(java.util.List.of()));
          }
          System.out.println();
        } else if (content instanceof UrlContextResultContent) {
          UrlContextResultContent urlResult = (UrlContextResultContent) content;
          System.out.println("URL Context Result:");
          System.out.println("  Call ID: " + urlResult.callId().orElse("N/A"));
          System.out.println("  Is Error: " + urlResult.isError().orElse(false));

          if (urlResult.result().isPresent()) {
            List<UrlContextResult> results = urlResult.result().get();
            System.out.println("  Results: " + results.size() + " found");
            for (int i = 0; i < results.size(); i++) {
              UrlContextResult result = results.get(i);
              System.out.println("  [" + (i + 1) + "] URL: " + result.url().orElse("N/A"));
              System.out.println("      Status: " + result.status().map(Object::toString).orElse("N/A"));
            }
          }
          System.out.println();
        }
      }
    }

    System.out.println("\n=== Example completed ===");
  }

  private InteractionsUrlContext() {}
}
