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
 * -Dexec.mainClass="com.google.genai.examples.InteractionToolCallWithUrlContext"
 */
package com.google.genai.examples;

import com.google.genai.Client;
import com.google.genai.interactions.models.interactions.Content;
import com.google.genai.interactions.models.interactions.CreateModelInteractionParams;
import com.google.genai.interactions.models.interactions.Interaction;
import com.google.genai.interactions.models.interactions.Model;
import com.google.genai.interactions.models.interactions.Step;
import com.google.genai.interactions.models.interactions.Tool;

/** An example of using the Interactions API with URL context tool. */
public final class InteractionToolCallWithUrlContext {
  public static void main(String[] args) {
    Client client = new Client();

    if (client.vertexAI()) {
      System.out.println("Interactions API is not yet supported on Vertex");
      return;
    }

    System.out.println("Using Gemini Developer API");

    CreateModelInteractionParams params =
        CreateModelInteractionParams.builder()
            .input(
                "Compare the ingredients and cooking times from the recipes at"
                    + " https://www.foodnetwork.com/recipes/ina-garten/perfect-roast-chicken-recipe-1940592"
                    + " and https://www.allrecipes.com/recipe/21151/simple-whole-roast-chicken/")
            .model(Model.GEMINI_2_5_FLASH)
            .addTool(Tool.ofUrlContext())
            .build();

    try {
      Interaction interaction = client.interactions.create(params);

      System.out.println("Interaction ID: " + interaction.id());
      System.out.println("Status: " + interaction.status());

      // Print the text outputs from the interaction.
      interaction
          .steps()
          .ifPresent(
              steps -> {
                for (Step step : steps) {
                  if (step.isModelOutput()) {
                    step.asModelOutput()
                        .content()
                        .ifPresent(
                            contents -> {
                              for (Content content : contents) {
                                content
                                    .text()
                                    .ifPresent(
                                        text -> System.out.println("Output: " + text.text()));
                              }
                            });
                  }
                }
              });
    } catch (RuntimeException e) {
      System.err.println("Error creating interaction: " + e.getMessage());
      e.printStackTrace();
    }
  }

  private InteractionToolCallWithUrlContext() {}
}
