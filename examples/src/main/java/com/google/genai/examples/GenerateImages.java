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
 * <p>mvn clean compile exec:java -Dexec.mainClass="com.google.genai.examples.GenerateImages"
 */
package com.google.genai.examples;

import com.google.genai.Client;
import com.google.genai.types.GenerateImagesConfig;
import com.google.genai.types.GenerateImagesResponse;
import com.google.genai.types.Image;

/** An example of using the Unified Gen AI Java SDK to generate images. */
public class GenerateImages {
  public static void main(String[] args) {
    // Instantiate the client. The client by default uses the Gemini Developer API. It gets the API
    // key from the environment variable `GOOGLE_API_KEY`.
    Client client = new Client();

    if (client.vertexAI()) {
      System.out.println("Using Vertex AI");
    } else {
      System.out.println("Using Gemini Developer API");
    }

    GenerateImagesConfig generateImagesConfig =
        GenerateImagesConfig.builder()
            .numberOfImages(1)
            .outputMimeType("image/jpeg")
            .includeSafetyAttributes(true)
            .build();

    GenerateImagesResponse generatedImagesResponse =
        client.models.generateImages(
            "imagen-3.0-generate-001", "Robot holding a red skateboard", generateImagesConfig);

    System.out.println(
        "Generated " + generatedImagesResponse.generatedImages().get().size() + " images.");

    Image generatedImage = generatedImagesResponse.generatedImages().get().get(0).image().get();
    // Do something with the image.

    System.out.println(
        "Prompt Safety Attributes:\n"
            + generatedImagesResponse.positivePromptSafetyAttributes().get());
  }
}
