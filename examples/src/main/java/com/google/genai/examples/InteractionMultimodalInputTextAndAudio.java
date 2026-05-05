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
 * -Dexec.mainClass="com.google.genai.examples.InteractionMultimodalInputTextAndAudio"
 */
package com.google.genai.examples;

import com.google.genai.Client;
import com.google.genai.interactions.models.interactions.AudioContent;
import com.google.genai.interactions.models.interactions.Content;
import com.google.genai.interactions.models.interactions.CreateModelInteractionParams;
import com.google.genai.interactions.models.interactions.Interaction;
import com.google.genai.interactions.models.interactions.Step;
import com.google.genai.interactions.models.interactions.TextContent;
import java.util.Arrays;
import java.util.List;

/** Example of using the Interactions API with multimodal input (text and audio). */
public class InteractionMultimodalInputTextAndAudio {

  private InteractionMultimodalInputTextAndAudio() {}

  private static void createInteractions(Client client) {
    Content textContent =
        Content.ofText(TextContent.builder().text("Summarize this audio clip.").build());
    Content audioContent =
        Content.ofAudio(
            AudioContent.builder()
                .uri("gs://cloud-samples-data/generative-ai/audio/pixel.mp3")
                .mimeType(AudioContent.MimeType.AUDIO_MP3)
                .build());

    List<Content> contents = Arrays.asList(textContent, audioContent);

    CreateModelInteractionParams params =
        CreateModelInteractionParams.builder()
            .model("gemini-2.5-flash")
            .inputOfContentList(contents)
            .build();

    Interaction interaction = client.interactions.create(params);

    interaction
        .steps()
        .ifPresent(
            steps -> {
              for (int i = 0; i < steps.size(); i++) {
                System.out.println("Step " + (i + 1) + ": " + steps.get(i));
              }
            });
  }

  public static void main(String[] args) {
    Client client = new Client();
    if (client.vertexAI()) {
      System.out.println("Interactions API is not yet supported on Vertex");
      return;
    }
    try {
      createInteractions(client);
    } catch (RuntimeException e) {
      System.err.println("Got error: " + e.getMessage());
      e.printStackTrace();
    }
  }
}
