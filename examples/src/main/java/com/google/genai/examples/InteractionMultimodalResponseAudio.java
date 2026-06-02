/*
 * Copyright 2026 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
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
 * -Dexec.mainClass="com.google.genai.examples.InteractionMultimodalResponseAudio"
 */
package com.google.genai.examples;

import com.google.common.collect.ImmutableList;
import com.google.genai.Client;
import com.google.genai.gaos.models.interactions.CreateModelInteraction;
import com.google.genai.gaos.models.interactions.GenerationConfig;
import com.google.genai.gaos.models.interactions.Interaction;
import com.google.genai.gaos.models.interactions.InteractionsInput;
import com.google.genai.gaos.models.interactions.ResponseModality;
import com.google.genai.gaos.models.interactions.SpeechConfig;
import com.google.genai.gaos.models.interactions.Step;
import com.google.genai.gaos.models.operations.CreateInteractionRequestBody;
import java.util.Arrays;

/** Example of generating audio using the Interactions API. */
public final class InteractionMultimodalResponseAudio {

  private static void createInteractions(Client client) {

    SpeechConfig speechConfig = SpeechConfig.builder().voice("achernar").language("en-US").build();

    GenerationConfig generationConfig =
        GenerationConfig.builder().speechConfig(ImmutableList.of(speechConfig)).build();

    CreateModelInteraction params =
        CreateModelInteraction.builder()
            .model("gemini-2.5-flash-preview-tts")
            .responseModalities(ImmutableList.of(ResponseModality.AUDIO))
            .generationConfig(generationConfig)
            .input(InteractionsInput.of("Say cheerfully: Have a wonderful day!"))
            .build();

    Interaction interaction =
        client.interactions.create(CreateInteractionRequestBody.of(params))
            .interaction()
            .orElseThrow(() -> new RuntimeException("Failed to create interaction"));

    interaction.steps().ifPresent(steps -> {
      int i = 1;
      for (Step step : steps) {
        System.out.println("Output " + i + ": " + step);
        i++;
      }
    });
  }

  public static void main(String[] args) throws Exception {
    Client client = new Client();
    if (client.vertexAI()) {
      System.out.println("Interactions API is not yet supported on Vertex");
      return;
    }
      createInteractions(client);
  }

  private InteractionMultimodalResponseAudio() {}
}
