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

package com.google.genai.examples;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.genai.Client;
import com.google.genai.types.Content;
import com.google.genai.types.File;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Part;
import com.google.genai.types.RegisterFilesResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * An example of how to use the registerFiles method to register GCS files with the Gemini Developer
 * API.
 */
public final class RegisterFiles {
  public static void main(String[] args) throws IOException {
    // Instantiate the client. The client by default uses the Gemini Developer API.
    Client client = new Client();

    if (client.vertexAI()) {
      System.out.println("registerFiles is only supported in the Gemini Developer client.");
      System.exit(0);
    }

    // GoogleCredentials.getApplicationDefault() will use application default credentials.
    // Note: registerFiles is only supported by the Gemini Developer API (MLDev), not Vertex AI.
    GoogleCredentials credentials =
        GoogleCredentials.getApplicationDefault()
            .createScoped(
                Arrays.asList(
                    "https://www.googleapis.com/auth/cloud-platform",
                    "https://www.googleapis.com/auth/devstorage.read_only"));

    List<String> uris = Arrays.asList("gs://tensorflow_docs/image.jpg");

    RegisterFilesResponse response = client.files.registerFiles(credentials, uris, null);

    List<File> files =
        response.files().orElseThrow(() -> new RuntimeException("No files returned"));
    File file = files.get(0);

    System.out.println("Registered file: " + file.uri().get());

    // Use the registered file in a generateContent call.
    Content content =
        Content.fromParts(
            Part.fromText("can you summarize this file?"),
            Part.fromUri(file.uri().get(), file.mimeType().get()));

    GenerateContentResponse genResponse =
        client.models.generateContent("gemini-2.5-flash", content, null);

    System.out.println("Response: " + genResponse.text());
  }

  private RegisterFiles() {}
}
