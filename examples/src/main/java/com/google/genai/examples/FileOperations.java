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
 * <p>mvn clean compile exec:java -Dexec.mainClass="com.google.genai.examples.FileOperations"
 * -Dexec.args="your_file_path.txt"
 */
package com.google.genai.examples;

import com.google.genai.Client;
import com.google.genai.types.DeleteFileConfig;
import com.google.genai.types.DeleteFileResponse;
import com.google.genai.types.File;
import com.google.genai.types.GetFileConfig;
import com.google.genai.types.UploadFileConfig;
import java.io.IOException;

/** An example of how to use the Files module to upload, retrieve, and delete files. */
public class FileOperations {
  public static void main(String[] args) {

    if (args.length == 0) {
      System.out.println("Please provide a file path on the -Dexec.args argument.");
      return;
    }

    String filePath = args[0];

    // Instantiate the client. The client by default uses the Gemini Developer API. It gets the API
    // key from the environment variable `GOOGLE_API_KEY`.
    Client client = new Client();

    // Upload a file to the API.
    try {
      File file =
          client.files.upload(filePath, UploadFileConfig.builder().mimeType("text/plain").build());
      System.out.println("Uploaded file: " + file);

      // Get the uploaded file.
      File retrievedFile = client.files.get(file.name().get(), null);
      System.out.println("Retrieved file: " + retrievedFile);

      // Delete the uploaded file.
      DeleteFileResponse deleteFileResponse = client.files.delete(file.name().get(), null);
      System.out.println("Deleted file");

    } catch (IOException e) {
      System.out.println("An error occurred while uploading the file: " + e.getMessage());
    }
  }
}
