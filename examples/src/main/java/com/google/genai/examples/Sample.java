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
 * <p>3. Compile the java package, then run the sample code.
 *
 * <p>mvn clean compile
 *
 * <p>mvn exec:java -Dexec.mainClass="com.google.genai.examples.Sample"
 */
package com.google.genai.examples;

import com.google.common.collect.ImmutableList;
import com.google.genai.Client;
import com.google.genai.ResponseStream;
import com.google.genai.types.Content;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.GenerateImagesConfig;
import com.google.genai.types.GenerateImagesResponse;
import com.google.genai.types.Part;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.apache.http.HttpException;

/** Sample class to prototype GenAI SDK functionalities. */
public final class Sample {
  public static void main(String[] args) throws IOException, HttpException {
    String apiKey = System.getenv("GOOGLE_API_KEY");
    String project = System.getenv("GOOGLE_CLOUD_PROJECT");
    String location = System.getenv("GOOGLE_CLOUD_LOCATION");

    // Instantiate a client that will use Google AI APIs.
    Client mldevClient = Client.builder().setApiKey(apiKey).build();

    // Instantiate a client that will use Vertex AI APIs.
    Client vertexClient =
        Client.builder().setProject(project).setLocation(location).setVertexAI(true).build();

    List<Content> contents =
        ImmutableList.of(
            Content.builder()
                .setRole("user")
                .setParts(ImmutableList.of(Part.builder().setText("What is your name?").build()))
                .build());

    // Async generateContent call.
    CompletableFuture<GenerateContentResponse> responseFuture =
        mldevClient.async.models.generateContent("gemini-2.0-flash-exp", contents, null);

    System.out.println("I've called the async method.");

    responseFuture.thenAccept(
        response -> {
          System.out.println("Async response: " + response.toJson());
        });

    System.out.println("I've scheduled to print the async response.");

    // Unary generateContent call.
    GenerateContentResponse response =
        vertexClient.models.generateContent("gemini-2.0-flash-exp", "What is your name?", null);
    System.out.println("Unary response:\n" + response.toJson() + "\n");

    // Streaming generateContent call.
    ResponseStream<GenerateContentResponse> responseStream =
        mldevClient.models.generateContentStream(
            "gemini-2.0-flash-exp", "What is your name?", null);
    System.out.println("Streaming response:");
    for (GenerateContentResponse res : responseStream) {
      System.out.println(res.toJson() + "\n");
    }
    // To save resources and avoid connection leaks, it is recommended to close the response
    // stream after consumption (or using try block to get the response stream).
    responseStream.close();

    // This will force the `main` thread to wait for the async operation to complete before exiting.
    // So the async response will be printed after the unary and streaming responses.
    responseFuture.join();

    // Test error message.
    try {
      vertexClient.models.generateContent("gemini-1.6-pro", "What is your name?", null);
    } catch (HttpException e) {
      System.out.println("Example of generateContent error message: " + e.getMessage());
    }

    // Generate image
    GenerateImagesConfig generateImagesConfig =
        GenerateImagesConfig.builder().setNumberOfImages(1).setOutputMimeType("image/jpeg").build();
    GenerateImagesResponse generatedImagesResponse =
        vertexClient.models.generateImages(
            "imagen-3.0-generate-001", "Robot holding a red skateboard", generateImagesConfig);
    System.out.println(
        "Image:\n"
            + generatedImagesResponse
                .getGeneratedImages()
                .get()
                .get(0)
                .getImage()
                .get()
                .getImageBytes()
                .get());
  }
}
