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
 * -Dexec.mainClass="com.google.genai.examples.InteractionStructuredOutputJson"
 */
package com.google.genai.examples;

import com.google.genai.Client;
import com.google.genai.gaos.models.interactions.CreateModelInteraction;
import com.google.genai.gaos.models.interactions.CreateModelInteractionResponseFormat;
import com.google.genai.gaos.models.interactions.Interaction;
import com.google.genai.gaos.models.interactions.InteractionsInput;
import com.google.genai.gaos.models.interactions.ResponseFormat;
import com.google.genai.gaos.models.interactions.TextResponseFormat;
import com.google.genai.gaos.models.interactions.TextResponseFormatMimeType;
import com.google.genai.gaos.models.operations.CreateInteractionRequestBody;
import java.util.HashMap;
import java.util.Map;

/** Example of requesting structured JSON output using the Interactions API. */
public class InteractionStructuredOutputJson {

  private static void createInteractions(Client client) {

    Map<String, Object> format = new HashMap<>();
    format.put("type", "array");
    format.put("description", "A list of colors");

    TextResponseFormat textResponseFormat = TextResponseFormat.builder()
        .mimeType(TextResponseFormatMimeType.APPLICATION_JSON)
        .schema(format)
        .build();

    CreateModelInteraction params =
        CreateModelInteraction.builder()
            .model(Constants.GEMINI_MODEL_NAME)
            .input(InteractionsInput.of("Which are the colors of a rainbow"))
            .responseFormat(CreateModelInteractionResponseFormat.of(ResponseFormat.of(textResponseFormat)))
            .build();

    Interaction interaction =
        client.interactions.create(CreateInteractionRequestBody.of(params))
            .interaction()
            .orElseThrow(() -> new RuntimeException("Failed to create interaction"));

    System.out.println(interaction);
  }

  public static void main(String[] args) throws Exception {
    Client client = new Client();
    if (client.vertexAI()) {
      System.out.println("Interactions API is not yet supported on Vertex");
      return;
    }
      createInteractions(client);
  }

  private InteractionStructuredOutputJson() {}
}
