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
 * -Dexec.mainClass="com.google.genai.examples.EditImageStyleTransfer"
 */
package com.google.genai.examples;

import com.google.genai.Client;
import com.google.genai.types.EditImageConfig;
import com.google.genai.types.EditImageResponse;
import com.google.genai.types.GenerateImagesConfig;
import com.google.genai.types.GenerateImagesResponse;
import com.google.genai.types.Image;
import com.google.genai.types.ReferenceImage;
import com.google.genai.types.StyleReferenceConfig;
import com.google.genai.types.StyleReferenceImage;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.http.HttpException;

/** An example of using the Unified Gen AI Java SDK to edit an image (Style transfer). */
public class EditImageStyleTransfer {
  public static void main(String[] args) throws IOException, HttpException {
    // Instantiates the client using Vertex AI, and sets the project and location in the builder.
    Client client =
        Client.builder()
            .vertexAI(true)
            .project(System.getenv("GOOGLE_CLOUD_PROJECT"))
            .location(System.getenv("GOOGLE_CLOUD_LOCATION"))
            .build();

    GenerateImagesConfig generateImagesConfig =
        GenerateImagesConfig.builder().numberOfImages(1).outputMimeType("image/jpeg").build();

    GenerateImagesResponse generatedImagesResponse =
        client.models.generateImages(
            "imagen-3.0-generate-001",
            "A starry night sky painted with watercolors",
            generateImagesConfig);

    Image generatedImage = generatedImagesResponse.generatedImages().get().get(0).image().get();

    // Style transfer.
    EditImageConfig editImageConfig =
        EditImageConfig.builder().numberOfImages(1).outputMimeType("image/jpeg").build();

    ArrayList<ReferenceImage> referenceImages = new ArrayList<>();
    StyleReferenceImage styleReferenceImage =
        StyleReferenceImage.builder()
            .referenceImage(generatedImage)
            .referenceId(1)
            .config(StyleReferenceConfig.builder().styleDescription("Watercolor").build())
            .build();
    referenceImages.add(styleReferenceImage);

    EditImageResponse editImageResponse =
        client.models.editImage(
            "imagen-3.0-capability-001",
            "Generate an image in the style of [1] based on the following caption: A church in the"
                + " mountain.",
            referenceImages,
            editImageConfig);

    Image editedImage = editImageResponse.generatedImages().get().get(0).image().get();
    // Do something with editedImage.
  }
}
