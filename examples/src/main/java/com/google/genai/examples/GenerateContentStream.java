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
 * <p>1. Install maven: https://maven.apache.org/
 *
 * <p>2a. If you are using Vertex AI, setup ADC to get credentials:
 * https://cloud.google.com/docs/authentication/provide-credentials-adc#google-idp
 *
 * <p>Then set Project and Location flag as environment variables:
 *
 * <p>export GOOGLE_CLOUD_PROJECT=YOUR_PROJECT
 *
 * <p>export GOOGLE_CLOUD_LOCATION=YOUR_LOCATION
 *
 * <p>2b. If you are using Google AI, set an API key environment variable. You can find a list of
 * available API keys here: https://aistudio.google.com/app/apikey
 *
 * <p>export GOOGLE_API_KEY=YOUR_API_KEY
 *
 * <p>3. Compile the java package, then run the GenerateContentStream code.
 *
 * <p>mvn clean compile
 *
 * <p>mvn exec:java -Dexec.mainClass="com.google.genai.examples.GenerateContentStream"
 */
package com.google.genai.examples;

import com.google.genai.Client;
import com.google.genai.ResponseStream;
import com.google.genai.types.GenerateContentResponse;
import java.io.IOException;
import org.apache.http.HttpException;

/** GenerateContentStream class to prototype GenAI SDK functionalities. */
public final class GenerateContentStream {
  public static void main(String[] args) throws IOException, HttpException {
    String apiKey = System.getenv("GOOGLE_API_KEY");
    String project = System.getenv("GOOGLE_CLOUD_PROJECT");
    String location = System.getenv("GOOGLE_CLOUD_LOCATION");

    // Instantiate a client that will use Google AI APIs.
    Client mldevClient = Client.builder().setApiKey(apiKey).build();

    // Instantiate a client that will use Vertex AI APIs.
    Client vertexClient =
        Client.builder().setProject(project).setLocation(location).setVertexAI(true).build();

    // Streaming generateContent call.
    ResponseStream<GenerateContentResponse> responseStream =
        mldevClient.models.generateContentStream(
            "gemini-2.0-flash-exp", "Tell me a story in 300 words.", null);
    System.out.println("Streaming response:");
    for (GenerateContentResponse res : responseStream) {
      System.out.print(res.text());
    }
  }
}
