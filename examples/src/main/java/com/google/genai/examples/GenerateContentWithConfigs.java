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
 * <p>mvn exec:java -Dexec.mainClass="com.google.genai.examples.GenerateContentWithConfigs"
 */
package com.google.genai.examples;

import com.google.common.collect.ImmutableList;
import com.google.genai.Client;
import com.google.genai.types.Content;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.GoogleSearch;
import com.google.genai.types.Part;
import com.google.genai.types.SafetySetting;
import com.google.genai.types.Tool;
import java.io.IOException;
import org.apache.http.HttpException;

/** An example of using the Unified Gen AI Java SDK to generate content with extra configs. */
public class GenerateContentWithConfigs {
  public static void main(String[] args) throws IOException, HttpException {
    // Instantiate the client using Gemini Developer API.
    Client client = new Client();

    // Sets the safety settings in the config.
    ImmutableList<SafetySetting> safetySettings =
        ImmutableList.of(
            SafetySetting.builder()
                .category("HARM_CATEGORY_HATE_SPEECH")
                .threshold("BLOCK_ONLY_HIGH")
                .build(),
            SafetySetting.builder()
                .category("HARM_CATEGORY_DANGEROUS_CONTENT")
                .threshold("BLOCK_LOW_AND_ABOVE")
                .build());

    // Sets the system instruction in the config.
    Content systemInstruction = Content.fromParts(Part.fromText("You are a history teacher."));

    // Sets the Google Search tool in the config.
    Tool googleSearchTool = Tool.builder().googleSearch(GoogleSearch.builder().build()).build();

    GenerateContentConfig config =
        GenerateContentConfig.builder()
            .candidateCount(1)
            .maxOutputTokens(1024)
            .safetySettings(safetySettings)
            .systemInstruction(systemInstruction)
            .tools(ImmutableList.of(googleSearchTool))
            .build();

    GenerateContentResponse response =
        client.models.generateContent("gemini-2.0-flash-001", "Tell me the history of LLM", config);

    System.out.println("Response: " + response.text());
  }
}
