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
 * <p>export GOOGLE_GENAI_USE_VERTEXAI=true
 *
 * <p>1b. If you are using Gemini Developer API, set an API key environment variable. You can find a
 * list of available API keys here: https://aistudio.google.com/app/apikey
 *
 * <p>export GOOGLE_API_KEY=YOUR_API_KEY
 *
 * <p>2. Compile the java package and run the sample code.
 *
 * <p>mvn clean compile
 *
 * <p>mvn exec:java -Dexec.mainClass="com.google.genai.examples.GenerateContentWithFunctionCallJson"
 * -Dexec.args="YOUR_MODEL_ID"
 */
package com.google.genai.examples;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.genai.Client;
import com.google.genai.types.FunctionDeclaration;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Tool;

/** An example of using the Unified Gen AI Java SDK to generate content with function calling. */
public final class GenerateContentWithFunctionCallJson {
  /** A callable function to get the weather. */
  public static void main(String[] args) {
    final String modelId;
    if (args.length != 0) {
      modelId = args[0];
    } else {
      modelId = Constants.GEMINI_MODEL_NAME;
    }

    // Instantiate the client. The client by default uses the Gemini Developer API. It gets the API
    // key from the environment variable `GOOGLE_API_KEY`. Vertex AI API can be used by setting the
    // environment variables `GOOGLE_CLOUD_LOCATION` and `GOOGLE_CLOUD_PROJECT`, as well as setting
    // `GOOGLE_GENAI_USE_VERTEXAI` to "true".
    //
    // Note: Some services are only available in a specific API backend (Gemini or Vertex), you will
    // get a `UnsupportedOperationException` if you try to use a service that is not available in
    // the backend you are using.
    Client client = new Client();

    if (client.vertexAI()) {
      System.out.println("Using Vertex AI");
    } else {
      System.out.println("Using Gemini Developer API");
    }

    // Define the schema for the function declaration, in Json format.
    ImmutableMap<String, Object> parametersSchema =
        ImmutableMap.of(
            "type", "object",
            "properties", ImmutableMap.of("location", ImmutableMap.of("type", "string")),
            "required", ImmutableList.of("location"));

    ImmutableMap<String, Object> responseSchema =
        ImmutableMap.of(
            "type", "object",
            "properties", ImmutableMap.of("weather", ImmutableMap.of("type", "string")),
            "required", ImmutableList.of("weather"));

    // Define the tool with the function declaration.
    Tool toolWithFunctionDeclarations =
        Tool.builder()
            .functionDeclarations(
                FunctionDeclaration.builder()
                    .name("get_weather")
                    .description("Returns the weather in a given location.")
                    .parametersJsonSchema(parametersSchema)
                    .responseJsonSchema(responseSchema)
                    .build())
            .build();

    // Add the tool to the GenerateContentConfig.
    GenerateContentConfig config =
        GenerateContentConfig.builder().tools(toolWithFunctionDeclarations).build();

    GenerateContentResponse response =
        client.models.generateContent(modelId, "What is the weather in Vancouver?", config);

    System.out.println("The response is: " + response.functionCalls());
  }

  private GenerateContentWithFunctionCallJson() {}
}
