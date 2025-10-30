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
 * <p>export GOOGLE_GENAI_USE_VERTEXAI=true
 *
 * <p>1b. If you are using Gemini Developer API, set an API key environment variable. You can find a
 * list of available API keys here: https://aistudio.google.com/app/apikey
 *
 * <p>export GOOGLE_API_KEY=YOUR_API_KEY
 *
 * <p>2. Compile the java package and run the sample code.
 *
 * <p>mvn clean compile
 *
 * <p>mvn exec:java -Dexec.mainClass="com.google.genai.examples.ChatWithHistoryStreaming"
 * -Dexec.args="YOUR_MODEL_ID"
 */
package com.google.genai.examples;

import com.google.genai.Chat;
import com.google.genai.Client;
import com.google.genai.ResponseStream;
import com.google.genai.types.GenerateContentResponse;

/**
 * An example of using the Unified Gen AI Java SDK to create a chat session and stream the response.
 */
public final class ChatWithHistoryStreaming {
  public static void main(String[] args) {
    final String modelId;
    if (args.length != 0) {
      modelId = args[0];
    } else {
      modelId = Constants.GEMINI_MODEL_NAME;
    }

    // Instantiate the client. The client by default uses the Gemini Developer API. It gets the API
    // key from the environment variable `GOOGLE_API_KEY`. Vertex AI API can be used by setting the
    // environment variables `GOOGLE_CLOUD_LOCATION` and `GOOGLE_CLOUD_PROJECT`, as well as setting
    // `GOOGLE_GENAI_USE_VERTEXAI` to "true".
    //
    // Note: Some services are only available in a specific API backend (Gemini or Vertex), you will
    // get a `UnsupportedOperationException` if you try to use a service that is not available in
    // the backend you are using.
    Client client = new Client();

    if (client.vertexAI()) {
      System.out.println("Using Vertex AI");
    } else {
      System.out.println("Using Gemini Developer API");
    }

    // Create a chat session.
    Chat chatSession = client.chats.create(modelId);

    ResponseStream<GenerateContentResponse> responseStream =
        chatSession.sendMessageStream("Can you tell me a story about cheese in 100 words?", null);

    System.out.println("Streaming response:");
    for (GenerateContentResponse response : responseStream) {
      // Iterate over the stream and print each response as it arrives.
      System.out.print(response.text());
    }

    ResponseStream<GenerateContentResponse> responseStream2 =
        chatSession.sendMessageStream(
            "Can you modify the story to be written for a 5 year old?", null);

    System.out.println("Streaming response 2:");
    for (GenerateContentResponse response : responseStream2) {
      // Iterate over the stream and print each response as it arrives.
      System.out.print(response.text());
    }

    // Get the history of the chat session.
    // History is added after the stream is consumed and includes the aggregated response from the
    // stream, so chatSession.getHistory(false) here returns 4 items (2 user-model message pairs)
    System.out.println("History: " + chatSession.getHistory(false));
  }

  private ChatWithHistoryStreaming() {}
}
