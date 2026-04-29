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
 * <p>mvn clean compile exec:java -Dexec.mainClass="com.google.genai.examples.InteractionStateless"
 */
package com.google.genai.examples;

import com.google.genai.Client;
import com.google.genai.gaos.models.interactions.Content;
import com.google.genai.gaos.models.interactions.CreateModelInteraction;
import com.google.genai.gaos.models.interactions.Interaction;
import com.google.genai.gaos.models.interactions.InteractionsInput;
import com.google.genai.gaos.models.interactions.ModelOutputStep;
import com.google.genai.gaos.models.interactions.Step;
import com.google.genai.gaos.models.interactions.TextContent;
import com.google.genai.gaos.models.interactions.UserInputStep;
import com.google.genai.gaos.models.operations.CreateInteractionRequestBody;
import java.util.ArrayList;
import java.util.List;

/**
 * An example of using the Unified Gen AI Java SDK to create a stateless interaction (multi-turn
 * chat).
 */
public final class InteractionStateless {
  public static void main(String[] args) {
    Client client = new Client();

    if (client.vertexAI()) {
      System.out.println("Interactions API is not yet supported on Vertex");
      return;
    } else {
      System.out.println("Using Gemini Developer API");
    }

    List<Step> conversationHistory = new ArrayList<>();
    conversationHistory.add(
        Step.of(
            UserInputStep.builder()
                .content(
                    List.of(
                        Content.of(
                            TextContent.builder()
                                .text("What are the three largest cities in Spain?")
                                .build())))
                .build()));

    System.out.println("User: What are the three largest cities in Spain?");

    CreateModelInteraction params1 =
        CreateModelInteraction.builder()
            .input(InteractionsInput.ofStep(conversationHistory))
            .model(Constants.GEMINI_MODEL_NAME)
            .store(false)
            .build();

    Interaction response1 =
        client.interactions.create(CreateInteractionRequestBody.of(params1))
            .interaction()
            .orElseThrow(() -> new RuntimeException("Failed to create interaction"));

    System.out.println("Model: ");
    // Add model response to history
    response1.steps().ifPresent(steps -> {
      for (Step step : steps) {
        if (step.value() instanceof ModelOutputStep) {
          ModelOutputStep modelOutput = (ModelOutputStep) step.value();
          modelOutput.content().ifPresent(contents -> {
            for (Content output : contents) {
              if (output.value() instanceof TextContent) {
                TextContent text = (TextContent) output.value();
                System.out.println(text.text());
              }
            }
          });
        }
        conversationHistory.add(step);
      }
    });

    // Add next user message
    conversationHistory.add(
        Step.of(
            UserInputStep.builder()
                .content(
                    List.of(
                        Content.of(
                            TextContent.builder()
                                .text("What is the most famous landmark in the second one?")
                                .build())))
                .build()));

    System.out.println("\nUser: What is the most famous landmark in the second one?");

    CreateModelInteraction params2 =
        CreateModelInteraction.builder()
            .input(InteractionsInput.ofStep(conversationHistory))
            .model(Constants.GEMINI_MODEL_NAME)
            .store(false)
            .build();

    Interaction response2 =
        client.interactions.create(CreateInteractionRequestBody.of(params2))
            .interaction()
            .orElseThrow(() -> new RuntimeException("Failed to create interaction"));

    System.out.println("Model: ");
    response2.steps().ifPresent(steps -> {
      for (Step step : steps) {
        if (step.value() instanceof ModelOutputStep) {
          ModelOutputStep modelOutput = (ModelOutputStep) step.value();
          modelOutput.content().ifPresent(contents -> {
            for (Content output : contents) {
              if (output.value() instanceof TextContent) {
                TextContent text = (TextContent) output.value();
                System.out.println(text.text());
              }
            }
          });
        }
      }
    });
  }

  private InteractionStateless() {}
}
