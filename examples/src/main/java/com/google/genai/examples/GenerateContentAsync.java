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
 * <p>mvn clean compile exec:java -Dexec.mainClass="com.google.genai.examples.GenerateContentAsync"
 */
package com.google.genai.examples;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import org.apache.http.HttpException;

/** An example of using the Unified Gen AI Java SDK to generate content asynchronously. */
public class GenerateContentAsync {
  public static void main(String[] args) throws IOException, HttpException {
    // Instantiates the client using Gemini Developer API, and sets the API key in the builder.
    Client client = Client.builder().apiKey(System.getenv("GOOGLE_API_KEY")).build();

    CompletableFuture<GenerateContentResponse> responseFuture =
        client.async.models.generateContent(
            "gemini-2.0-flash-exp", "Introduce Google AI Studio.", null);

    responseFuture
        .thenAccept(
            response -> {
              System.out.println("Async response: " + response.text());
            })
        .join();
  }
}
