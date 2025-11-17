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
 * <p>mvn clean compile exec:java
 * -Dexec.mainClass="com.google.genai.examples.GenerateVideosExtension" -Dexec.args="YOUR_MODEL_ID"
 */
package com.google.genai.examples;

import com.google.genai.Client;
import com.google.genai.JsonSerializable;
import com.google.genai.errors.GenAiIOException;
import com.google.genai.types.GenerateVideosConfig;
import com.google.genai.types.GenerateVideosOperation;
import com.google.genai.types.GenerateVideosSource;
import com.google.genai.types.Video;

/** An example of using the Unified Gen AI Java SDK to extend a video. */
public final class GenerateVideosExtension {
  public static void main(String[] args) {
    final String modelId;
    if (args.length != 0) {
      modelId = args[0];
    } else {
      modelId = Constants.VEO_MODEL_NAME;
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
      System.out.println("Sample is only available for Gemini Developer API.");
      return;
    } else {
      System.out.println("Using Gemini Developer API");
    }

    // Optional: If the default 20MB limit is not enough for the generated video response, you can
    // increase the limit via system property `genai.json.maxReadLength` or via this static method
    // `JsonSerializable.setMaxReadLength`.
    JsonSerializable.setMaxReadLength(100_000_000);

    // Generate first video.
    GenerateVideosConfig generateVideosConfig =
        GenerateVideosConfig.builder().numberOfVideos(1).build();
    GenerateVideosSource generateVideosSource =
        GenerateVideosSource.builder()
            .prompt("A neon hologram of a cat driving at top speed")
            .build();

    GenerateVideosOperation generateVideosOperation1 =
        client.models.generateVideos(modelId, generateVideosSource, generateVideosConfig);

    // GenerateVideosOperation.done() is empty if the operation is not done.
    while (!generateVideosOperation1.done().filter(Boolean::booleanValue).isPresent()) {
      try {
        Thread.sleep(10000); // Sleep for 10 seconds.
        generateVideosOperation1 =
            client.operations.getVideosOperation(generateVideosOperation1, null);
        System.out.println("Waiting for operation to complete...");
      } catch (InterruptedException e) {
        System.out.println("Thread was interrupted while sleeping.");
        Thread.currentThread().interrupt();
      }
    }
    System.out.println(
        "Generated "
            + generateVideosOperation1.response().get().generatedVideos().get().size()
            + " video(s).");

    Video generatedVideo1 =
        generateVideosOperation1.response().get().generatedVideos().get().get(0).video().get();

    if (!client.vertexAI()) {
      try {
        client.files.download(generatedVideo1, "video.mp4", null);
        System.out.println("Downloaded video to video.mp4");
      } catch (GenAiIOException e) {
        System.out.println("An error occurred while downloading the video: " + e.getMessage());
      }
    }

    // Extend the generated video.
    GenerateVideosConfig generateVideosConfig2 =
        GenerateVideosConfig.builder().numberOfVideos(1).build();
    GenerateVideosSource generateVideosSource2 =
        GenerateVideosSource.builder().prompt("Rain").video(generatedVideo1).build();

    GenerateVideosOperation generateVideosOperation2 =
        client.models.generateVideos(modelId, generateVideosSource2, generateVideosConfig2);

    // GenerateVideosOperation.done() is empty if the operation is not done.
    while (!generateVideosOperation2.done().filter(Boolean::booleanValue).isPresent()) {
      try {
        Thread.sleep(10000); // Sleep for 10 seconds.
        generateVideosOperation2 =
            client.operations.getVideosOperation(generateVideosOperation2, null);
        System.out.println("Waiting for operation to complete...");
      } catch (InterruptedException e) {
        System.out.println("Thread was interrupted while sleeping.");
        Thread.currentThread().interrupt();
      }
    }
    System.out.println(
        "Generated "
            + generateVideosOperation2.response().get().generatedVideos().get().size()
            + " video(s).");

    Video generatedVideo2 =
        generateVideosOperation2.response().get().generatedVideos().get().get(0).video().get();

    if (!client.vertexAI()) {
      try {
        client.files.download(generatedVideo2, "video.mp4", null);
        System.out.println("Downloaded extended video to video.mp4");
      } catch (GenAiIOException e) {
        System.out.println("An error occurred while downloading the video: " + e.getMessage());
      }
    }
  }

  private GenerateVideosExtension() {}
}
