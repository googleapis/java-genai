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
 * <p>mvn exec:java -Dexec.mainClass="com.google.genai.examples.CachedContentOperations"
 * -Dexec.args="YOUR_MODEL_ID"
 */
package com.google.genai.examples;

import com.google.genai.Client;
import com.google.genai.types.CachedContent;
import com.google.genai.types.Content;
import com.google.genai.types.CreateCachedContentConfig;
import com.google.genai.types.DeleteCachedContentResponse;
import com.google.genai.types.ListCachedContentsConfig;
import com.google.genai.types.Part;
import com.google.genai.types.UpdateCachedContentConfig;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.time.Duration;
import java.time.Instant;
import org.jspecify.annotations.Nullable;

/** An example of using the Unified Gen AI Java SDK to do operations on cached content. */
public final class CachedContentOperations {

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

    // Create a cached content.
    Content content =
        Content.fromParts(
            fetchPdfPart(
                "https://storage.googleapis.com/cloud-samples-data/generative-ai/pdf/2403.05530.pdf"));

    CreateCachedContentConfig config =
        CreateCachedContentConfig.builder()
            .systemInstruction(Content.fromParts(Part.fromText("summarize the pdf")))
            .expireTime(Instant.now().plus(Duration.ofHours(1)))
            .contents(content)
            .build();

    CachedContent cachedContent1 = client.caches.create(modelId, config);
    System.out.println("Created cached content: " + cachedContent1);

    // Get the cached content by name.
    CachedContent cachedContent2 = client.caches.get(cachedContent1.name().get(), null);
    System.out.println("Get cached content: " + cachedContent2);

    CachedContent cachedContent3 =
        client.caches.update(
            cachedContent1.name().get(),
            UpdateCachedContentConfig.builder().ttl(Duration.ofMinutes(10)).build());
    System.out.println("Update cached content: " + cachedContent3);

    // List all cached contents.
    System.out.println("List cached contents resrouce names: ");
    for (CachedContent cachedContent :
        client.caches.list(ListCachedContentsConfig.builder().pageSize(5).build())) {
      System.out.println(cachedContent.name().get());
    }

    // Delete the cached content.
    DeleteCachedContentResponse unused = client.caches.delete(cachedContent1.name().get(), null);
    System.out.println("Deleted cached content: " + cachedContent1.name().get());
  }

  /** Downloads a PDF file from the URL and returns a {@link Part} object. */
  private static @Nullable Part fetchPdfPart(String pdfUrl) {
    String mimeType = "application/pdf";
    byte[] pdfData = null;

    System.out.println("Attempting to download PDF from: " + pdfUrl);

    try {
      URL url = new URL(pdfUrl);
      URLConnection connection = url.openConnection();
      connection.setConnectTimeout(10000);
      connection.setReadTimeout(30000);

      try (InputStream inputStream = connection.getInputStream()) {
        pdfData = inputStream.readAllBytes();
      }

      if (pdfData != null && pdfData.length > 0) {
        System.out.println("Successfully downloaded " + pdfData.length + " bytes.");

        // Create a Part object from the downloaded bytes and MIME type
        return Part.fromBytes(pdfData, mimeType);

      } else {
        System.err.println("Failed to download PDF data or the file was empty.");
        return null;
      }

    } catch (IOException e) {
      System.err.println("Error downloading or processing PDF: " + e.getMessage());
      e.printStackTrace();
    } catch (RuntimeException e) {
      System.err.println("An unexpected error occurred: " + e.getMessage());
      e.printStackTrace();
    }
    return null;
  }

  private CachedContentOperations() {}
}
