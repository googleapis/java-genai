/*
 * Copyright 2026 Google LLC
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
 * <p>1a. If you are using Vertex AI, setup ADC to get credentials:
 * https://cloud.google.com/docs/authentication/provide-credentials-adc#google-idp
 *
 * <p>Then set Project, Location, and USE_VERTEXAI flag as environment variables:
 *
 * <p>export GOOGLE_CLOUD_PROJECT=YOUR_PROJECT
 *
 * <p>export GOOGLE_CLOUD_LOCATION=YOUR_LOCATION
 *
 * <p>export GOOGLE_GENAI_USE_VERTEXAI=true
 *
 * <p>1b. If you are using Gemini Developer API, set an API key environment variable. You can find a
 * list of available API keys here: https://aistudio.google.com/app/apikey
 *
 * <p>export GOOGLE_API_KEY=YOUR_API_KEY
 *
 * <p>2. Compile the java package and run the sample code.
 *
 * <p>mvn clean compile exec:java
 * -Dexec.mainClass="com.google.genai.examples.InteractionFunctionCallingClientState"
 */
package com.google.genai.examples;

import com.google.common.collect.ImmutableMap;
import com.google.genai.Client;
import com.google.genai.interactions.core.JsonValue;
import com.google.genai.interactions.models.interactions.Content;
import com.google.genai.interactions.models.interactions.CreateModelInteractionParams;
import com.google.genai.interactions.models.interactions.Function;
import com.google.genai.interactions.models.interactions.FunctionCallContent;
import com.google.genai.interactions.models.interactions.FunctionResultContent;
import com.google.genai.interactions.models.interactions.Interaction;
import com.google.genai.interactions.models.interactions.Model;
import com.google.genai.interactions.models.interactions.Tool;
import com.google.genai.interactions.models.interactions.Turn;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * An example of using the Interactions API for multi-turn function calling where the state is
 * managed by the client.
 */
public final class InteractionFunctionCallingClientState {
  public static void main(String[] args) {
    Client client = new Client();

    if (client.vertexAI()) {
      System.out.println("Interactions API is not yet supported on Vertex");
      return;
    }

    System.out.println("Using Gemini Developer API");

    // 1. Define the function (tool)
    Map<String, Object> attendeesSchema = new HashMap<>();
    attendeesSchema.put("type", "array");
    attendeesSchema.put("items", ImmutableMap.of("type", "string"));
    attendeesSchema.put("description", "List of people attending the meeting.");

    Map<String, Object> dateSchema = new HashMap<>();
    dateSchema.put("type", "string");
    dateSchema.put("description", "Date of the meeting (e.g., 2024-07-29)");

    Map<String, Object> timeSchema = new HashMap<>();
    timeSchema.put("type", "string");
    timeSchema.put("description", "Time of the meeting (e.g., 15:00)");

    Map<String, Object> topicSchema = new HashMap<>();
    topicSchema.put("type", "string");
    topicSchema.put("description", "The subject or topic of the meeting.");

    Map<String, Object> properties = new HashMap<>();
    properties.put("attendees", attendeesSchema);
    properties.put("date", dateSchema);
    properties.put("time", timeSchema);
    properties.put("topic", topicSchema);

    Map<String, Object> parametersSchema = new HashMap<>();
    parametersSchema.put("type", "object");
    parametersSchema.put("properties", properties);
    parametersSchema.put("required", Arrays.asList("attendees", "date", "time", "topic"));

    Function function =
        Function.builder()
            .name("schedule_meeting")
            .description("Schedules a meeting with specified attendees at a given time and date.")
            .parameters(JsonValue.from(parametersSchema))
            .build();

    // 2. Initialize conversation history
    List<Turn> conversationHistory = new ArrayList<>();
    conversationHistory.add(
        Turn.builder()
            .role("user")
            .content(
                "Schedule a meeting for 2025-11-01 at 10 am with Peter and Amir about the Next Gen"
                    + " API")
            .build());

    // 3. First turn: Model decides to call the function
    CreateModelInteractionParams params =
        CreateModelInteractionParams.builder()
            .model(Model.GEMINI_2_5_FLASH)
            .inputOfTurnList(conversationHistory)
            .tools(Arrays.asList(Tool.ofFunction(function)))
            .build();

    Interaction response = client.interactions.create(params);

    String functionCallId = null;
    String functionName = null;

    List<Content> outputs = response.outputs().orElse(null);
    if (outputs != null) {
      for (Content output : outputs) {
        if (output.isFunctionCall()) {
          FunctionCallContent functionCall = output.asFunctionCall();
          functionCallId = functionCall.id();
          functionName = functionCall.name();
          System.out.println("Model requested function call: " + functionName);
          System.out.println("Arguments: " + functionCall.arguments());
        } else if (output.text().isPresent()) {
          System.out.println("Output Text: " + output.text().get().text());
        }
      }

      // Add model response back to history
      conversationHistory.add(Turn.builder().role("model").contentOfContentList(outputs).build());
    }

    // 4. Second turn: Send the function result back to the model
    if (functionCallId != null) {
      System.out.println("Sending function result back...");

      FunctionResultContent functionResult =
          FunctionResultContent.builder()
              .callId(functionCallId)
              .name(functionName)
              .result("Meeting scheduled successfully.")
              .build();

      // Create a turn with function result
      conversationHistory.add(
          Turn.builder()
              .role("user")
              .contentOfContentList(Arrays.asList(Content.ofFunctionResult(functionResult)))
              .build());

      CreateModelInteractionParams followUpParams =
          CreateModelInteractionParams.builder()
              .model(Model.GEMINI_2_5_FLASH)
              .inputOfTurnList(conversationHistory)
              .build();

      Interaction followUpResponse = client.interactions.create(followUpParams);

      System.out.println("Final response status: " + followUpResponse.status());
      followUpResponse
          .outputs()
          .ifPresent(
              finalOutputs -> {
                for (Content output : finalOutputs) {
                  output
                      .text()
                      .ifPresent(text -> System.out.println("Final Output: " + text.text()));
                }
              });
    } else {
      System.out.println("No function call requested by the model.");
    }
  }

  private InteractionFunctionCallingClientState() {}
}
