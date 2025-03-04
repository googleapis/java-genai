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
 * <p>mvn clean compile exec:java
 * -Dexec.mainClass="com.google.genai.examples.LiveTextToTextGenerationAsync"
 */
package com.google.genai.examples;

import com.google.common.collect.ImmutableList;
import com.google.genai.Client;
import com.google.genai.types.Content;
import com.google.genai.types.HttpOptions;
import com.google.genai.types.LiveClientContent;
import com.google.genai.types.LiveClientMessage;
import com.google.genai.types.LiveConnectConfig;
import com.google.genai.types.LiveServerContent;
import com.google.genai.types.LiveServerMessage;
import com.google.genai.types.Part;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import org.apache.http.HttpException;

/** Example of using the live module to send and receive messages asynchronously. */
public class LiveTextToTextGenerationAsync {

  public static void main(String[] args) throws IOException, HttpException {
    // Instantiates the client.
    Client client =
        Client.builder().httpOptions(HttpOptions.builder().apiVersion("v1alpha").build()).build();

    LiveConnectConfig config =
        LiveConnectConfig.builder().responseModalities(ImmutableList.of("TEXT")).build();

    CompletableFuture<Void> allDone = new CompletableFuture<>();

    client
        .async
        .live
        .connect("gemini-2.0-flash-exp", config)
        .thenCompose(
            session -> {
              String inputText = "Write a short poem about a cat.";
              System.out.println("\n**Input**\n" + inputText);

              LiveClientMessage input = liveClientMessageFromText(inputText);

              return session
                  // Send the input message.
                  .send(input)
                  .thenCompose(
                      unused -> {
                        System.out.print("\n**Response**\n");
                        // Receive messages from the live session.
                        return session.receive(message -> printLiveServerMessage(message, allDone));
                      })
                  .thenCompose(unused -> allDone)
                  .thenCompose(unused -> session.close());
            });
  }

  public static LiveClientMessage liveClientMessageFromText(String text) {
    return LiveClientMessage.builder()
        .clientContent(
            LiveClientContent.builder()
                .turnComplete(true)
                .turns(
                    ImmutableList.of(
                        Content.builder()
                            .parts(ImmutableList.of(Part.builder().text(text).build()))
                            .build()))
                .build())
        .build();
  }

  public static void printLiveServerMessage(
      LiveServerMessage message, CompletableFuture<Void> allDone) {
    if (message.serverContent().isPresent()) {
      LiveServerContent content = message.serverContent().get();
      if (content.modelTurn().isPresent()) {
        Content modelTurn = content.modelTurn().get();
        for (Part part : modelTurn.parts().orElse(ImmutableList.of())) {
          if (part.text().isPresent()) {
            System.out.print(part.text().get());
          }
        }
      }
      if (content.turnComplete().orElse(false)) {
        System.out.println();
        allDone.complete(null);
      }
    }
  }
}
