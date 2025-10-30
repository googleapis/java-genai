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
 * <p>mvn exec:java -Dexec.mainClass="com.google.genai.examples.LiveTextToAudioTranscriptionAsync"
 * -Dexec.args="YOUR_MODEL_ID"
 */
package com.google.genai.examples;

import com.google.genai.AsyncSession;
import com.google.genai.Client;
import com.google.genai.types.AudioTranscriptionConfig;
import com.google.genai.types.Content;
import com.google.genai.types.GoogleSearch;
import com.google.genai.types.LiveConnectConfig;
import com.google.genai.types.LiveSendClientContentParameters;
import com.google.genai.types.LiveServerContent;
import com.google.genai.types.LiveServerMessage;
import com.google.genai.types.Modality;
import com.google.genai.types.Part;
import com.google.genai.types.SpeechConfig;
import com.google.genai.types.Tool;
import com.google.genai.types.Transcription;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/** Example of using the live module to transcribe text to audio. */
public final class LiveTextToAudioTranscriptionAsync {

  public static void main(String[] args) {
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

    final String modelId;
    if (args.length != 0) {
      modelId = args[0];
    } else if (client.vertexAI()) {
      modelId = Constants.GEMINI_LIVE_MODEL_NAME;
    } else {
      modelId = Constants.GEMINI_LIVE_MODEL_NAME_PREVIEW;
    }

    // Sets the system instruction in the config.
    Content systemInstruction = Content.fromParts(Part.fromText("Answer in Japanese."));
    // Sets the Google Search tool in the config.
    Tool googleSearchTool = Tool.builder().googleSearch(GoogleSearch.builder()).build();

    // Configures live session and audio transcription.
    LiveConnectConfig config =
        LiveConnectConfig.builder()
            .responseModalities(Modality.Known.AUDIO)
            .outputAudioTranscription(AudioTranscriptionConfig.builder())
            .systemInstruction(systemInstruction)
            .speechConfig(SpeechConfig.builder().languageCode("ja-JP"))
            .tools(googleSearchTool)
            .build();

    CompletableFuture<Void> allDone = new CompletableFuture<>();

    CompletableFuture<AsyncSession> futureSession = client.async.live.connect(modelId, config);

    futureSession
        .thenCompose(
            session -> {
              String inputText = "What is the weather like in Tokyo now?";
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
    if (message.serverContent().isPresent()) {
      LiveServerContent content = message.serverContent().get();
      processModelTurn(content);
      processGroundingMetadata(content);

      // Print audio transcription.
      if (content.outputTranscription().isPresent()) {
        Transcription transcription = content.outputTranscription().get();
        if (transcription.text().isPresent()) {
          System.out.print(transcription.text().get());
        }
      }

      // Check if the server's turn is complete and signal the allDone future if so.
      if (content.turnComplete().orElse(false)) {
        System.out.println();
        allDone.complete(null);
      }
    }
  }

  private static void processModelTurn(LiveServerContent content) {
    content.modelTurn().flatMap(Content::parts).stream()
        .flatMap(Collection::stream)
        .map(Part::inlineData)
        .filter(Optional::isPresent)
        // Print some text to indicate that audio is returned.
        .forEach(inlineData -> System.out.print("Some audio bytes in inline_data..."));
  }

  private static void processGroundingMetadata(LiveServerContent content) {
    // Print grounding metadata if present.
    content.groundingMetadata().stream()
        .flatMap(metadata -> metadata.groundingChunks().stream())
        .flatMap(Collection::stream)
        .map(chunk -> chunk.web())
        .filter(Optional::isPresent)
        .map(web -> web.get().uri())
        .filter(Optional::isPresent)
        .forEach(uri -> System.out.println("\n\nGrounding URI: " + uri.get()));
  }

  private LiveTextToAudioTranscriptionAsync() {}
}
