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
 * <p>mvn clean compile exec:java -Dexec.mainClass="com.google.genai.examples.EnvironmentFiles"
 */
package com.google.genai.examples;

import com.google.genai.Client;
import com.google.genai.gaos.models.environments.CreateEnvironmentRequest;
import com.google.genai.gaos.models.environments.Environment;
import com.google.genai.gaos.models.environments.EnvironmentFile;
import com.google.genai.gaos.models.environments.GetEnvironmentFilesResponse;
import com.google.genai.gaos.models.interactions.Source;
import com.google.genai.gaos.models.interactions.SourceType;
import com.google.genai.gaos.models.operations.CreateEnvironmentResponse;
import com.google.genai.gaos.models.operations.GetEnvironmentFilesRequest;
import java.util.Arrays;
import java.util.List;

/** An example of using the Unified Gen AI Java SDK to create environments and query environment files. */
public final class EnvironmentFiles {
  public static void main(String[] args) {
    Client client = new Client();

    if (client.vertexAI()) {
      System.out.println("Environment Files API is currently supported on Gemini API (MLDev). Skipping on Vertex.");
      return;
    }

    System.out.println("Using Gemini Developer API");

    System.out.println("\n--- 1. Creating Environment with Workspace Files ---");
    CreateEnvironmentRequest createReq =
        CreateEnvironmentRequest.builder()
            .sources(
                Arrays.asList(
                    Source.builder()
                        .type(SourceType.INLINE)
                        .target("main.py")
                        .content("print(\"Hello from Java Environment Files demo!\")\n")
                        .build(),
                    Source.builder()
                        .type(SourceType.INLINE)
                        .target("config.json")
                        .content("{\"version\": \"1.0\", \"debug\": true}\n")
                        .build(),
                    Source.builder()
                        .type(SourceType.INLINE)
                        .target("src/utils.py")
                        .content("def greet(name: str) -> str:\n  return f\"Hello, {name}!\"\n")
                        .build()))
            .build();

    CreateEnvironmentResponse createResponse = client.environments.createEnvironment(createReq);
    Environment env =
        createResponse
            .environment()
            .orElseThrow(() -> new RuntimeException("No environment returned"));

    String envId = env.id().orElseThrow(() -> new RuntimeException("No environment ID returned"));
    System.out.println("Environment created successfully! ID: " + envId);

    try {
      System.out.println("\n--- 2. Listing Files at Root Directory (path=\".\") ---");
      com.google.genai.gaos.models.operations.GetEnvironmentFilesResponse rootFilesResponse =
          client.environments.files().list(
              GetEnvironmentFilesRequest.builder()
                  .environment(envId)
                  .path(".")
                  .build());

      GetEnvironmentFilesResponse rootFiles =
          rootFilesResponse
              .getEnvironmentFilesResponse()
              .orElseThrow(() -> new RuntimeException("No file list returned"));

      List<EnvironmentFile> files = rootFiles.files().orElse(Arrays.asList());
      for (EnvironmentFile file : files) {
        System.out.printf(
            " - %s (type=%s, size=%s bytes)\n",
            file.name().orElse("unknown"),
            file.type().map(Object::toString).orElse("unknown"),
            file.sizeBytes().orElse("unknown"));
      }

      System.out.println("\n--- 3. Querying Subdirectory (path=\"src\", recursive=true) ---");
      com.google.genai.gaos.models.operations.GetEnvironmentFilesResponse srcFilesResponse =
          client.environments.files().list(
              GetEnvironmentFilesRequest.builder()
                  .environment(envId)
                  .path("src")
                  .recursive(true)
                  .build());

      srcFilesResponse
          .getEnvironmentFilesResponse()
          .ifPresent(
              res -> {
                for (EnvironmentFile file : res.files().orElse(Arrays.asList())) {
                  System.out.printf(
                      " - %s (path=%s)\n",
                      file.name().orElse("unknown"),
                      file.path().orElse("unknown"));
                }
              });

      System.out.println("\n--- 4. Querying Specific File Path (path=\"main.py\") ---");
      com.google.genai.gaos.models.operations.GetEnvironmentFilesResponse mainFileResponse =
          client.environments.files().list(
              GetEnvironmentFilesRequest.builder()
                  .environment(envId)
                  .path("main.py")
                  .build());

      mainFileResponse
          .getEnvironmentFilesResponse()
          .ifPresent(
              res -> {
                for (EnvironmentFile file : res.files().orElse(Arrays.asList())) {
                  System.out.println("main.py file size: " + file.sizeBytes().orElse("0"));
                }
              });
    } finally {
      System.out.println("\n--- 5. Cleaning up Environment ID: " + envId + " ---");
      var deleteRes = client.environments.deleteEnvironment(envId);
      System.out.println("Environment deleted successfully: " + deleteRes.statusCode());
    }
  }

  private EnvironmentFiles() {}
}
