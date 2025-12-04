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
 * <p>mvn exec:java -Dexec.mainClass="com.google.genai.examples.LiveEphemeralTokenAsync"
 * -Dexec.args="YOUR_MODEL_ID"
 */
package com.google.genai.examples;

import com.google.common.collect.ImmutableList;
import com.google.genai.AsyncSession;
import com.google.genai.Client;
import com.google.genai.types.AuthToken;
import com.google.genai.types.Content;
import com.google.genai.types.CreateAuthTokenConfig;
import com.google.genai.types.HttpOptions;
import com.google.genai.types.LiveConnectConfig;
import com.google.genai.types.LiveConnectConstraints;
import com.google.genai.types.LiveSendClientContentParameters;
import com.google.genai.types.LiveServerContent;
import com.google.genai.types.LiveServerMessage;
import com.google.genai.types.Modality;
import com.google.genai.types.Part;
import java.util.concurrent.CompletableFuture;

/** Example of using the live module to send and receive text messages asynchronously. */
public final class LiveEphemeralTokenAsync {

  public static void main(String[] args) {
    // Instantiate the client. The client by default uses the Gemini Developer API. It gets the API
    // key from the environment variable `GOOGLE_API_KEY`. Vertex AI API can be used by setting the
    // environment variables `GOOGLE_CLOUD_LOCATION` and `GOOGLE_CLOUD_PROJECT`, as well as setting
    // `GOOGLE_GENAI_USE_VERTEXAI` to "true".
    //
    // Note: Some services are only available in a specific API backend (Gemini or Vertex), you will
    // get a `UnsupportedOperationException` if you try to use a service that is not available in
    // the backend you are using.
    Client client =
        Client.builder().httpOptions(HttpOptions.builder().apiVersion("v1alpha").build()).build();

    if (client.vertexAI()) {
      System.out.println("Vertex AI API is not supported for this example.");
      System.exit(0);
    } else {
      System.out.println("Using Gemini Developer API");
    }
    System.out.println("Creating auth token...");

    // Create an auth token for the live session.
    AuthToken authToken =
        client.authTokens.create(
            CreateAuthTokenConfig.builder()
                .uses(2)
                .liveConnectConstraints(
                    LiveConnectConstraints.builder()
                        .model(Constants.GEMINI_LIVE_MODEL_NAME_PREVIEW)
                        .config(
                            LiveConnectConfig.builder()
                                .systemInstruction(
                                    Content.fromParts(
                                        Part.fromText(
                                            "Answer questions like C-3PO from Star Wars would.")))
                                .responseModalities(Modality.Known.AUDIO)
                                .build())
                        .build())
                .lockAdditionalFields(ImmutableList.of("topP"))
                .build());
    System.out.println("Created auth token: " + authToken.name());

    final String modelId;
    if (args.length != 0) {
      modelId = args[0];
    } else {
      modelId = Constants.GEMINI_LIVE_MODEL_NAME_PREVIEW;
    }

    // Create a client using the ephemeral auth token.
    if (authToken == null || authToken.name() == null) {
      System.out.println("No auth token created.");
      System.exit(0);
    }
    Client clientWithAuthToken =
        Client.builder()
            .apiKey(authToken.name().orElse(null))
            .httpOptions(HttpOptions.builder().apiVersion("v1alpha").build())
            .build();

    // Note that the system instruction here is ignored by the server. The system instruction was
    // set and locked in the LiveConnectConstraints of the CreateAuthTokenConfig. Here we are just
    // demonstrating that here. Other unlocked fields (like temperature) can be configured here.
    LiveConnectConfig config =
        LiveConnectConfig.builder()
            .systemInstruction(
                Content.fromParts(
                    Part.fromText("You are a pirate. Answer all questions like a pirate would.")))
            .build();

    CompletableFuture<Void> allDone = new CompletableFuture<>();

    CompletableFuture<AsyncSession> futureSession =
        clientWithAuthToken.async.live.connect(modelId, config);

    futureSession
        .thenCompose(
            session -> {
              String inputText = "What would you say if you are surprised?";
              System.out.println("Connecting to live session...");
              System.out.println(session.sessionId());
              System.out.println("\n**Input**\n" + inputText);

              return session
                  // Send the input message.
                  .sendClientContent(clientContentFromText(inputText))
                  .thenCompose(
                      unused -> {
                        System.out.print("\n**Response**\n");
                        // Receive messages from the live session.
                        return session.receive(message -> printLiveServerMessage(message, allDone));
                      })
                  // Wait for the allDone future to complete, which is signaled in
                  // printLiveServerMessage when the server is done sending messages.
                  .thenCompose(unused -> allDone)
                  // Close the session.
                  .thenCompose(unused -> session.close());
            })
        .join();
  }

  /** Wraps client message text. */
  public static LiveSendClientContentParameters clientContentFromText(String text) {
    return LiveSendClientContentParameters.builder()
        .turnComplete(true)
        .turns(Content.fromParts(Part.fromText(text)))
        .build();
  }

  public static void printLiveServerMessage(
      LiveServerMessage message, CompletableFuture<Void> allDone) {
    // Extract and print text from the model.
    message
        .serverContent()
        .flatMap(LiveServerContent::modelTurn)
        .flatMap(Content::parts)
        .ifPresent(parts -> parts.forEach(part -> part.text().ifPresent(System.out::print)));

    // Check if the server's turn is complete and signal the allDone future if so.
    if (message.serverContent().flatMap(LiveServerContent::turnComplete).orElse(false)) {
      System.out.println("\n**End of turn, full message: **\n");
      System.out.println(message);
      System.out.println();
      allDone.complete(null);
    }
  }

  private LiveEphemeralTokenAsync() {}
}
