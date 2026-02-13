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

import com.google.common.collect.ImmutableMap;
import com.google.genai.Client;
import com.google.genai.types.Schema;
import com.google.genai.types.interactions.CreateInteractionConfig;
import com.google.genai.types.interactions.Interaction;
import com.google.genai.types.interactions.content.Content;
import com.google.genai.types.interactions.content.FunctionCallContent;
import com.google.genai.types.interactions.content.FunctionResultContent;
import com.google.genai.types.interactions.content.TextContent;
import com.google.genai.types.interactions.tools.Function;
import java.util.Map;

/**
 * Example: Function Calling with the Interactions API
 *
 * <p>Demonstrates manual function calling where you define functions, extract calls from responses,
 * execute them, and send results back.
 *
 * <p>To run this example:
 * <ol>
 *   <li>Set the GOOGLE_API_KEY environment variable: {@code export GOOGLE_API_KEY=YOUR_API_KEY}
 *   <li>Compile the examples: {@code mvn clean compile}
 *   <li>Run: {@code mvn exec:java -Dexec.mainClass="com.google.genai.examples.InteractionsFunctionCalling"}
 * </ol>
 *
 * <p><b>Note:</b> The Interactions API is currently in beta.
 */
public final class InteractionsFunctionCalling {

  public static void main(String[] args) {
    // [START interactions_client_init]
    Client client = new Client();
    // [END interactions_client_init]

    System.out.println("=== Interactions API: Function Calling Example ===\n");

    try {
      // ===== STEP 1: Define the Function =====
      System.out.println("--- STEP 1: Define the Function ---\n");

      // [START interactions_function_calling]
      // [START interactions_function_declaration]
      Function weatherTool =
          Function.builder()
              .name("get_weather")
              .description("Get the current weather for a location")
              .parameters(
                  Schema.builder()
                      .type("object")
                      .properties(
                          Map.of(
                              "location",
                              Schema.builder()
                                  .type("string")
                                  .description("The city name")
                                  .build()))
                      .required("location")
                      .build())
              .build();
      // [END interactions_function_declaration]

      System.out.println("Function defined: get_weather\n");

      // ===== STEP 2: First Request - Ask about weather =====
      System.out.println("--- STEP 2: First Request (triggers function call) ---\n");

      CreateInteractionConfig config1 =
          CreateInteractionConfig.builder()
              .model("gemini-3-flash-preview")
              .input("What's the weather like in Paris?")
              .tools(weatherTool)
              .build();

      System.out.println("=== REQUEST ===");
      System.out.println(config1.toJson());
      System.out.println();

      Interaction response1 = client.interactions.create(config1);

      System.out.println("=== RESPONSE ===");
      System.out.println(response1.toJson());
      System.out.println();

      printResults(response1);

      // Extract function call
      FunctionCallContent functionCall = extractFunctionCall(response1);
      if (functionCall == null) {
        System.out.println("ERROR: Expected a function call but didn't receive one.");
        return;
      }

      System.out.println("  Function Call ID: " + functionCall.id());
      System.out.println("  Function Name: " + functionCall.name());
      System.out.println("  Arguments: " + functionCall.arguments());

      // ===== STEP 3: Execute Function and Send Result =====
      System.out.println("\n--- STEP 3: Execute Function and Send Result ---\n");

      Map<String, Object> weatherResult = executeGetWeather(
          functionCall.arguments().orElseThrow(
              () -> new IllegalStateException("Function call missing arguments")));
      System.out.println("Function executed. Result: " + weatherResult + "\n");

      // [START interactions_function_response]
      FunctionResultContent functionResult =
          FunctionResultContent.builder()
              .id(functionCall.id())
              .name(functionCall.name().orElseThrow(
                  () -> new IllegalStateException("Function call missing name")))
              .result(weatherResult)
              .build();

      CreateInteractionConfig config2 =
          CreateInteractionConfig.builder()
              .model("gemini-3-flash-preview")
              .inputFromContents(functionResult)
              .previousInteractionId(response1.id())
              .tools(weatherTool)
              .build();

      System.out.println("=== REQUEST ===");
      System.out.println(config2.toJson());
      System.out.println();

      Interaction response2 = client.interactions.create(config2);
      // [END interactions_function_response]
      // [END interactions_function_calling]

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

  private static Map<String, Object> executeGetWeather(Map<String, Object> arguments) {
    String location = (String) arguments.getOrDefault("location", "Unknown");
    return ImmutableMap.of(
        "location", location,
        "temperature", "22",
        "unit", "celsius",
        "condition", "sunny with a few clouds");
  }

  private static FunctionCallContent extractFunctionCall(Interaction interaction) {
    if (interaction.outputs().isPresent()) {
      for (Content output : interaction.outputs().get()) {
        if (output instanceof FunctionCallContent) {
          return (FunctionCallContent) output;
        }
      }
    }
    return null;
  }

  private static void printResults(Interaction interaction) {
    System.out.println("Results:");
    System.out.println("  Interaction ID: " + interaction.id());
    System.out.println("  Status: " + interaction.status());

    if (interaction.outputs().isPresent() && !interaction.outputs().get().isEmpty()) {
      for (Content output : interaction.outputs().get()) {
        if (output instanceof TextContent) {
          System.out.println("  Text: " + ((TextContent) output).text().orElse("(empty)"));
        } else if (output instanceof FunctionCallContent) {
          System.out.println("  FunctionCallContent:");
        } else {
          System.out.println("  " + output.getClass().getSimpleName());
        }
      }
    }
  }

  private InteractionsFunctionCalling() {}
}
