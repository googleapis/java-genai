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
 * <p>mvn clean compile exec:java -Dexec.mainClass="com.google.genai.examples.CreateCachedContent"
 */
package com.google.genai.examples;

import com.google.common.collect.ImmutableList;
import com.google.genai.Client;
import com.google.genai.types.CachedContent;
import com.google.genai.types.Content;
import com.google.genai.types.CreateCachedContentConfig;
import com.google.genai.types.Part;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/** An example of using the Unified Gen AI Java SDK to compute tokens for simple text input. */
public class CreateCachedContent {
   
  public static void main(String[] args) {
    // Instantiate the client. The client by default uses the Gemini Developer API. It gets the API
    // key from the environment variable `GOOGLE_API_KEY`.
    Client client = new Client();

    Content content = Content.fromParts(
        fetchPdfPart(
            "https://storage.googleapis.com/cloud-samples-data/generative-ai/pdf/2403.05530.pdf"),
        fetchPdfPart(
            "https://storage.googleapis.com/cloud-samples-data/generative-ai/pdf/2312.11805v3.pdf"));

    CreateCachedContentConfig config =
        CreateCachedContentConfig.builder()
            .systemInstruction(Content.fromParts(Part.fromText("summarize the two pdfs")))
            .contents(ImmutableList.of(content))
            .build();

    CachedContent response =
        client.caches.create("gemini-2.0-flash-001", config);
    // Gets the text string from the response by the quick accessor method `text()`.
    System.out.println("Unary response: " + response);
  }

  private static Part fetchPdfPart(String pdfUrl) {
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
    } catch (Exception e) {
      System.err.println("An unexpected error occurred: " + e.getMessage());
      e.printStackTrace();
    }
    return null;
  }
}
