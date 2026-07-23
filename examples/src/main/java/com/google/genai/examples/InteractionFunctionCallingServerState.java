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
 * -Dexec.mainClass="com.google.genai.examples.InteractionFunctionCallingServerState"
 */
package com.google.genai.examples;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.genai.Client;
import com.google.genai.gaos.models.interactions.Content;
import com.google.genai.gaos.models.interactions.CreateModelInteraction;
import com.google.genai.gaos.models.interactions.Function;
import com.google.genai.gaos.models.interactions.FunctionCallStep;
import com.google.genai.gaos.models.interactions.FunctionResultStep;
import com.google.genai.gaos.models.interactions.FunctionResultStepResultUnion;
import com.google.genai.gaos.models.interactions.Interaction;
import com.google.genai.gaos.models.interactions.InteractionsInput;
import com.google.genai.gaos.models.interactions.ModelOutputStep;
import com.google.genai.gaos.models.interactions.Step;
import com.google.genai.gaos.models.interactions.TextContent;
import com.google.genai.gaos.models.interactions.Tool;
import com.google.genai.gaos.models.operations.CreateInteractionRequestBody;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * An example of using the Interactions API for multi-turn function calling where the state is
 * managed by the server.
 */
public final class InteractionFunctionCallingServerState {
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
            .parameters(parametersSchema)
            .build();

    // 2. First turn: Model decides to call the function
    CreateModelInteraction params =
        CreateModelInteraction.builder()
            .input(
                InteractionsInput.of(
                    "Schedule a meeting for 2025-11-01 at 10 am with Peter and Amir about the Next"
                        + " Gen API"))
            .model(Constants.GEMINI_MODEL_NAME)
            .tools(ImmutableList.of(Tool.of(function)))
            .build();

    Interaction response =
        client
            .interactions
            .create(CreateInteractionRequestBody.of(params))
            .interaction()
            .orElseThrow(() -> new RuntimeException("Failed to create interaction"));

    String functionCallId = null;
    String functionName = null;

    if (response.steps().isPresent()) {
      List<Step> steps = response.steps().get();
      for (Step step : steps) {
        if (step.value() instanceof FunctionCallStep) {
          FunctionCallStep functionCall = (FunctionCallStep) step.value();
          functionCallId = functionCall.id();
          functionName = functionCall.name();
          System.out.println("Model requested function call: " + functionName);
          System.out.println("Arguments: " + functionCall.arguments());
        } else if (step.value() instanceof ModelOutputStep) {
          ModelOutputStep modelOutput = (ModelOutputStep) step.value();
          modelOutput
              .content()
              .ifPresent(
                  contents -> {
                    for (Content output : contents) {
                      if (output.value() instanceof TextContent) {
                        TextContent text = (TextContent) output.value();
                        System.out.println("Output Text: " + text.text());
                      }
                    }
                  });
        }
      }
    }

    // 3. Second turn: Send the function result back to the model
    if (functionCallId != null) {
      System.out.println("Sending function result back...");

      FunctionResultStep functionResult =
          FunctionResultStep.builder()
              .callId(functionCallId)
              .name(functionName)
              .result(FunctionResultStepResultUnion.of("Meeting scheduled successfully."))
              .build();

      CreateModelInteraction followUpParams =
          CreateModelInteraction.builder()
              .model(Constants.GEMINI_MODEL_NAME)
              .previousInteractionId(response.id().orElse(""))
              .input(InteractionsInput.ofStep(ImmutableList.of(Step.of(functionResult))))
              .build();

      Interaction followUpResponse =
          client
              .interactions
              .create(CreateInteractionRequestBody.of(followUpParams))
              .interaction()
              .orElseThrow(() -> new RuntimeException("Failed to create follow up interaction"));

      System.out.println("Final response status: " + followUpResponse.status());
      followUpResponse
          .steps()
          .ifPresent(
              followUpSteps -> {
                for (Step step : followUpSteps) {
                  if (step.value() instanceof ModelOutputStep) {
                    ModelOutputStep modelOutput = (ModelOutputStep) step.value();
                    modelOutput
                        .content()
                        .ifPresent(
                            contents -> {
                              for (Content output : contents) {
                                if (output.value() instanceof TextContent) {
                                  TextContent text = (TextContent) output.value();
                                  System.out.println("Final Output: " + text.text());
                                }
                              }
                            });
                  }
                }
              });
    } else {
      System.out.println("No function call requested by the model.");
    }
  }

  private InteractionFunctionCallingServerState() {}
}
