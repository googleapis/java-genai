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

import com.google.common.collect.ImmutableList;
import com.google.genai.Client;
import com.google.genai.gaos.models.interactions.AudioContent;
import com.google.genai.gaos.models.interactions.AudioContentMimeType;
import com.google.genai.gaos.models.interactions.Content;
import com.google.genai.gaos.models.interactions.CreateModelInteraction;
import com.google.genai.gaos.models.interactions.Interaction;
import com.google.genai.gaos.models.interactions.InteractionsInput;
import com.google.genai.gaos.models.interactions.TextContent;
import com.google.genai.gaos.models.operations.CreateInteractionRequestBody;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

/** Example of using the Interactions API with multimodal input (text and audio). */
public final class InteractionMultimodalInputTextAndAudio {

  private InteractionMultimodalInputTextAndAudio() {}

  private static void createInteractions(Client client) {
    String base64Audio;
    try {
      base64Audio = Base64.getEncoder().encodeToString(
          Files.readAllBytes(Paths.get("./resources/sample_audio.mp3"))
      );
    } catch (IOException e) {
      throw new RuntimeException("Failed to read audio file", e);
    }

    Content textContent =
        Content.of(TextContent.builder().text("Summarize this audio clip.").build());
    Content audioContent =
        Content.of(
            AudioContent.builder()
                .data(base64Audio)
                .mimeType(AudioContentMimeType.AUDIO_MP3)
                .build());

    List<Content> contents = ImmutableList.of(textContent, audioContent);

    CreateModelInteraction params =
        CreateModelInteraction.builder()
            .model(Constants.GEMINI_3_5_FLASH_MODEL_NAME)
            .input(InteractionsInput.ofContent(contents))
            .build();

    Interaction interaction =
        client.interactions.create(CreateInteractionRequestBody.of(params))
            .interaction()
            .orElseThrow(() -> new RuntimeException("Failed to create interaction"));

    System.out.println("Interaction ID: " + interaction.id().orElse(""));
    System.out.println("Status: " + interaction.status());

    interaction.steps().ifPresent(steps -> {
      for (int i = 0; i < steps.size(); i++) {
        System.out.println("Step " + (i + 1) + ": " + steps.get(i));
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
}
