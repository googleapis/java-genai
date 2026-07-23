/*
 * Copyright 2026 Google LLC
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
 * <p>mvn clean compile exec:java
 * -Dexec.mainClass="com.google.genai.examples.InteractionCreateAsyncStreaming"
 */
package com.google.genai.examples;
 
import com.google.genai.Client;
import com.google.genai.gaos.models.interactions.CreateModelInteraction;
import com.google.genai.gaos.models.interactions.InteractionSSEEvent;
import com.google.genai.gaos.models.interactions.InteractionSSEStreamEvent;
import com.google.genai.gaos.models.interactions.InteractionsInput;
import com.google.genai.gaos.models.interactions.StepDelta;
import com.google.genai.gaos.models.interactions.StepDeltaData;
import com.google.genai.gaos.models.interactions.TextDelta;
import com.google.genai.gaos.models.operations.CreateInteractionRequestBody;
import java.util.concurrent.CompletableFuture;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * An example of using the Unified Gen AI Java SDK to create a streaming interaction asynchronously.
 */
public final class InteractionCreateAsyncStreaming {
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

    CreateModelInteraction params =
        CreateModelInteraction.builder()
            .input(InteractionsInput.of("Tell me a story in 300 words."))
            .model(Constants.GEMINI_MODEL_NAME)
            .stream(true)
            .build();

    System.out.println("Streaming response:");
    CompletableFuture<Void> onCompleteFuture = new CompletableFuture<>();
    client
        .async
        .interactions
        .create(CreateInteractionRequestBody.of(params))
        .subscribe(
            new Subscriber<InteractionSSEStreamEvent>() {
              @Override
              public void onSubscribe(Subscription s) {
                s.request(Long.MAX_VALUE);
              }

              @Override
              public void onNext(InteractionSSEStreamEvent streamEvent) {
                InteractionSSEEvent event = streamEvent.data();
                if (event.value() instanceof StepDelta) {
                  StepDelta stepDelta = (StepDelta) event.value();
                  StepDeltaData data = stepDelta.delta();
                  if (data.value() instanceof TextDelta) {
                    TextDelta textDelta = (TextDelta) data.value();
                    System.out.print(textDelta.text());
                    System.out.flush();
                  }
                }
              }

              @Override
              public void onError(Throwable t) {
                onCompleteFuture.completeExceptionally(t);
              }

              @Override
              public void onComplete() {
                onCompleteFuture.complete(null);
              }
            });

    // Wait for the stream to complete.
    onCompleteFuture.join();
    System.out.println();
 
    client.close();
  }
 
  private InteractionCreateAsyncStreaming() {}
}
