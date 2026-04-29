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
 * -Dexec.mainClass="com.google.genai.examples.InteractionMultimodalResponseImageWithGenerateContent"
 */
package com.google.genai.examples;

import com.google.common.collect.ImmutableList;
import com.google.genai.Client;
import com.google.genai.JsonSerializable;
import com.google.genai.gaos.models.interactions.CreateModelInteraction;
import com.google.genai.gaos.models.interactions.Interaction;
import com.google.genai.gaos.models.interactions.InteractionsInput;
import com.google.genai.gaos.models.interactions.ResponseModality;
import com.google.genai.gaos.models.interactions.Step;
import com.google.genai.gaos.models.operations.CreateInteractionRequestBody;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;

/**
 * An example of using the Unified Gen AI Java SDK to create an interaction with image response and
 * generate content with image response.
 */
public final class InteractionMultimodalResponseImageWithGenerateContent {
  public static void main(String[] args) throws Exception {
    Client client = new Client();

    if (client.vertexAI()) {
      System.out.println("Interactions API is not yet supported on Vertex");
      return;
    }

    System.out.println("[Interactions] Start interactions multimodal response image");

    CreateModelInteraction params =
        CreateModelInteraction.builder()
            .model("gemini-2.5-flash-image")
            .responseModalities(ImmutableList.of(ResponseModality.IMAGE))
            .input(InteractionsInput.of("Generate an image of a futuristic cityscape at sunset."))
            .build();

      Interaction interaction =
          client.interactions.create(CreateInteractionRequestBody.of(params))
              .interaction()
              .orElseThrow(() -> new RuntimeException("Failed to create interaction"));

      System.out.println("Interaction ID: " + interaction.id().orElse(""));
      System.out.println("Status: " + interaction.status());

      interaction.steps().ifPresent(steps -> {
        int stepIndex = 1;
        for (Step step : steps) {
          System.out.println("Step " + stepIndex + ": " + step);
          stepIndex++;
        }
      });

    System.out.println("[Generate Content] Start generate content");
    GenerateContentConfig config =
        GenerateContentConfig.builder().responseModalities(ImmutableList.of("TEXT", "IMAGE")).build();

    GenerateContentResponse generateContentResponse =
        client.models.generateContent(
            "gemini-2.5-flash-image",
            "Generate an image of a futuristic cityscape at sunset.",
            config);

    System.out.println(
        "Generate Content response: " + JsonSerializable.toJsonString(generateContentResponse));
  }

  private InteractionMultimodalResponseImageWithGenerateContent() {}
}
