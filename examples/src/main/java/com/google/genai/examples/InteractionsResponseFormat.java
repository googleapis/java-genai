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
 * <p>mvn exec:java -Dexec.mainClass="com.google.genai.examples.InteractionsResponseFormat"
 */
package com.google.genai.examples;

import com.google.common.collect.ImmutableMap;
import com.google.genai.Client;
import com.google.genai.types.Schema;
import com.google.genai.types.Type;
import com.google.genai.types.interactions.CreateInteractionConfig;
import com.google.genai.types.interactions.Interaction;
import com.google.genai.types.interactions.content.Content;
import com.google.genai.types.interactions.content.TextContent;

/**
 * Example: Interactions API with Response Format (Structured JSON Output)
 *
 * <p>Demonstrates how to use the responseFormat field in CreateInteractionConfig to get structured
 * JSON responses from the Interactions API. This is useful when you need the model to return data
 * in a specific schema that can be parsed programmatically.
 *
 * <p>The example shows:
 *
 * <ul>
 *   <li>Creating a Schema object defining the expected response structure
 *   <li>Using responseFormat(schema) in CreateInteractionConfig
 *   <li>Parsing the structured JSON response
 * </ul>
 *
 * <p>Note: The Interactions API is in beta and subject to change.
 */
public final class InteractionsResponseFormat {

  public static void main(String[] args) {
    // Instantiate the client. The client gets the API key from the environment variable
    // `GOOGLE_API_KEY`.
    //
    Client client = new Client();

    System.out.println("=== Interactions API: Response Format Example ===\n");

    try {
      // Define the schema for the expected response structure.
      // This schema requests a recipe with name, ingredients list, and prep time.
      Schema responseSchema =
          Schema.builder()
              .type(new Type(Type.Known.OBJECT))
              .properties(
                  ImmutableMap.of(
                      "recipe_name",
                      Schema.builder().type(new Type(Type.Known.STRING)).build(),
                      "ingredients",
                      Schema.builder()
                          .type(new Type(Type.Known.ARRAY))
                          .items(Schema.builder().type(new Type(Type.Known.STRING)))
                          .build(),
                      "prep_time_minutes",
                      Schema.builder().type(new Type(Type.Known.INTEGER)).build()))
              .required("recipe_name", "ingredients")
              .build();

      // Create the interaction config with the response format schema.
      CreateInteractionConfig config =
          CreateInteractionConfig.builder()
              .model("gemini-3-flash-preview")
              .input("Give me a recipe for chocolate chip cookies.")
              .responseFormat(responseSchema)
              .build();

      System.out.println("=== REQUEST ===");
      System.out.println(config.toJson());
      System.out.println();

      // Execute the interaction.
      Interaction response = client.interactions.create(config);

      System.out.println("=== RESPONSE ===");
      System.out.println(response.toJson());
      System.out.println();

      // Print extracted results.
      printResults(response);

      System.out.println("\n=== Example completed ===");

    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
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

    System.out.println("  Outputs:");
    for (Content output : interaction.outputs().get()) {
      if (output instanceof TextContent) {
        TextContent t = (TextContent) output;
        String text = t.text().orElse("(empty)");
        System.out.println("  Structured JSON Response:");
        System.out.println("  " + text);
      } else {
        System.out.println("  " + output.getClass().getSimpleName());
      }
    }
  }

  private InteractionsResponseFormat() {}
}
