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
 * -Dexec.mainClass="com.google.genai.examples.InteractionToolCallWithFunctions"
 */
package com.google.genai.examples;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.genai.Client;
import com.google.genai.interactions.core.JsonValue;
import com.google.genai.interactions.models.interactions.Content;
import com.google.genai.interactions.models.interactions.CreateModelInteractionParams;
import com.google.genai.interactions.models.interactions.Function;
import com.google.genai.interactions.models.interactions.Interaction;
import com.google.genai.interactions.models.interactions.Model;
import com.google.genai.interactions.models.interactions.Tool;

/** An example of using the Unified Gen AI Java SDK to perform tool calling with functions. */
public final class InteractionToolCallWithFunctions {
  public static void main(String[] args) {
    Client client = new Client();

    if (client.vertexAI()) {
      System.out.println("Interactions API is not yet supported on Vertex AI");
      return;
    }

    System.out.println("Using Gemini Developer API");

    ImmutableMap<String, Object> attendeesSchema =
        ImmutableMap.of(
            "type",
            "array",
            "items",
            ImmutableMap.of("type", "string"),
            "description",
            "List of people attending the meeting.");
    ImmutableMap<String, Object> dateSchema =
        ImmutableMap.of("type", "string", "description", "Date of the meeting (e.g., 2024-07-29)");
    ImmutableMap<String, Object> timeSchema =
        ImmutableMap.of("type", "string", "description", "Time of the meeting (e.g., 15:00)");
    ImmutableMap<String, Object> topicSchema =
        ImmutableMap.of("type", "string", "description", "The subject or topic of the meeting.");

    ImmutableMap<String, Object> properties =
        ImmutableMap.of(
            "attendees",
            attendeesSchema,
            "date",
            dateSchema,
            "time",
            timeSchema,
            "topic",
            topicSchema);

    ImmutableMap<String, Object> parametersSchema =
        ImmutableMap.of(
            "type",
            "object",
            "properties",
            properties,
            "required",
            ImmutableList.of("attendees", "date", "time", "topic"));

    Function function =
        Function.builder()
            .name("schedule_meeting")
            .description("Schedules a meeting with specified attendees at a given time and date.")
            .parameters(JsonValue.from(parametersSchema))
            .build();

    Tool tool = Tool.ofFunction(function);

    CreateModelInteractionParams params =
        CreateModelInteractionParams.builder()
            .input(
                "Schedule a meeting for 10/06/2028 at 10 am with Peter and Amir about the Next Gen"
                    + " API")
            .model(Model.GEMINI_2_5_FLASH)
            .tools(ImmutableList.of(tool))
            .build();

    Interaction interaction = client.interactions.create(params);

    System.out.println("Interaction ID: " + interaction.id());
    System.out.println("Status: " + interaction.status());

    interaction
        .outputs()
        .ifPresent(
            outputs -> {
              for (Content output : outputs) {
                output.text().ifPresent(text -> System.out.println("Output Text: " + text.text()));
                output
                    .functionCall()
                    .ifPresent(
                        fc -> {
                          System.out.println("Function Call: " + fc.name());
                          System.out.println(
                              "Arguments: " + fc.arguments()._additionalProperties());
                        });
              }
            });
  }

  private InteractionToolCallWithFunctions() {}
}
