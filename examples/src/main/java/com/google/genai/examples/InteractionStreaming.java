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
 * <p>mvn clean compile exec:java -Dexec.mainClass="com.google.genai.examples.InteractionStreaming"
 */
package com.google.genai.examples;

import com.google.genai.Client;
import com.google.genai.gaos.models.interactions.CreateModelInteraction;
import com.google.genai.gaos.models.interactions.InteractionSSEEvent;
import com.google.genai.gaos.models.interactions.InteractionSSEStreamEvent;
import com.google.genai.gaos.models.interactions.InteractionsInput;
import com.google.genai.gaos.models.interactions.StepDelta;
import com.google.genai.gaos.models.interactions.StepDeltaData;
import com.google.genai.gaos.models.interactions.TextDelta;
import com.google.genai.gaos.models.operations.CreateInteractionRequestBody;
import com.google.genai.gaos.models.operations.CreateInteractionResponse;
import com.google.genai.gaos.utils.EventStream;

/** An example of using the Unified Gen AI Java SDK to stream an interaction. */
public final class InteractionStreaming {
  public static void main(String[] args) throws Exception {
    Client client = new Client();

    if (client.vertexAI()) {
      System.out.println("Interactions API is not yet supported on Vertex AI");
      return;
    }

    System.out.println("Using Gemini Developer API");

    CreateModelInteraction params =
        CreateModelInteraction.builder()
            .input(InteractionsInput.of("Tell me a story"))
            .model(Constants.GEMINI_MODEL_NAME)
            .stream(true)
            .build();

    CreateInteractionResponse response =
        client.interactions.create(CreateInteractionRequestBody.of(params));

    try (EventStream<InteractionSSEStreamEvent> eventStream = response.events()) {
      for (InteractionSSEStreamEvent streamEvent : eventStream) {
        InteractionSSEEvent event = streamEvent.data();
        if (event.value() instanceof StepDelta) {
          StepDelta stepDelta = (StepDelta) event.value();
          StepDeltaData data = stepDelta.delta();
          if (data.value() instanceof TextDelta) {
            TextDelta textDelta = (TextDelta) data.value();
            System.out.print(textDelta.text());
            System.out.flush();
          }
        }
      }
      System.out.println();
    }
  }

  private InteractionStreaming() {}
}
