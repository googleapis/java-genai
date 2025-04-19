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
 * <p>mvn clean compile exec:java -Dexec.mainClass="com.google.genai.examples.GenerateContent"
 */
package com.google.genai.examples;

import com.google.genai.Chat;
import com.google.genai.Client;
import com.google.genai.ResponseStream;
import com.google.genai.types.GenerateContentResponse;

/** An example of using the Unified Gen AI Java SDK to generate content. */
public class StreamingChatWithHistory {
  public static void main(String[] args) {
    // Instantiate the client. The client by default uses the Gemini Developer API. It gets the API
    // key from the environment variable `GOOGLE_API_KEY`.
    Client client = new Client();

    // Create a chat session.
    Chat chatSession = client.chats.create("gemini-2.0-flash-001");

    ResponseStream<GenerateContentResponse> responseStream =
        chatSession.sendMessageStream("Can you tell me a story about cheese in 100 words?", null);

    while (responseStream.iterator().hasNext()) {
      GenerateContentResponse response = responseStream.iterator().next();
      System.out.println("Streaming response 1: " + response.text());
    }

    ResponseStream<GenerateContentResponse> responseStream2 =
        chatSession.sendMessageStream(
            "Can you modify the story to be written for a 5 year old?", null);

    while (responseStream2.iterator().hasNext()) {
      GenerateContentResponse response = responseStream2.iterator().next();
      System.out.println("Streaming response 2: " + response.text());
    }

    // Get the history of the chat session.
    // History is added on subsequent calls to sendMessageStream or sendMessage so this will only
    // include the first user message and aggregated model response.
    System.out.println("History: " + chatSession.getHistory(false));
  }
}
