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
 * <p>1a. If you are using Vertex AI, setup ADC to get credentials:
 * https://cloud.google.com/docs/authentication/provide-credentials-adc#google-idp
 *
 * <p>Then set Project, Location, and USE_VERTEXAI flag as environment variables:
 *
 * <p>export GOOGLE_CLOUD_PROJECT=YOUR_PROJECT
 *
 * <p>export GOOGLE_CLOUD_LOCATION=YOUR_LOCATION
 *
 * <p>1b. If you are using Gemini Developer AI, set an API key environment variable. You can find a
 * list of available API keys here: https://aistudio.google.com/app/apikey
 *
 * <p>export GOOGLE_API_KEY=YOUR_API_KEY
 *
 * <p>2. Compile the java package and run the sample code.
 *
 * <p>mvn clean compile
 *
 * <p>mvn exec:java -Dexec.mainClass="com.google.genai.examples.GenerateContentWithFunctionCall"
 */
package com.google.genai.examples;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.genai.Client;
import com.google.genai.types.FunctionDeclaration;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Schema;

/** An example of using the Unified Gen AI Java SDK to generate content with function calling. */
public class GenerateContentWithFunctionCall {
  public static void main(String[] args) {
    // Instantiate the client using Gemini Developer API.
    Client client = new Client();

    FunctionDeclaration functionDeclaration =
        FunctionDeclaration.builder()
            .name("get_current_weather")
            .parameters(
                Schema.builder()
                    .type("object")
                    .properties(
                        ImmutableMap.of(
                            "location",
                            Schema.builder()
                                .type("string")
                                .description("The location to get the weather for.")
                                .build(),
                            "unit",
                            Schema.builder()
                                .type("string")
                                .description("The unit to return the weather in, e.g. 'celsius'.")
                                .build()))
                    .required(ImmutableList.of("location", "unit"))
                    .build())
            .build();

    GenerateContentConfig config =
        GenerateContentConfig.fromJson(
            String.format(
                "{\"tools\":[{\"functionDeclarations\":[%s]}]}", functionDeclaration.toJson()));

    GenerateContentResponse response =
        client.models.generateContent(
            "gemini-2.0-flash-001", "What is the weather in Vancouver?", config);

    // Gets the function calls from the response by the quick accessor method `functionCalls()`.
    System.out.println("Response: " + response.functionCalls());
  }
}
