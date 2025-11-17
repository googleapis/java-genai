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
 * <p>mvn exec:java -Dexec.mainClass="com.google.genai.examples.FileSearchStores"
 * -Dexec.args="path/to/file"
 */
package com.google.genai.examples;

import com.google.genai.Client;
import com.google.genai.errors.GenAiIOException;
import com.google.genai.types.Document;
import com.google.genai.types.File;
import com.google.genai.types.FileSearchStore;
import com.google.genai.types.ImportFileOperation;
import com.google.genai.types.ListFileSearchStoresConfig;
import com.google.genai.types.UploadFileConfig;
import com.google.genai.types.UploadToFileSearchStoreOperation;

/**
 * An example of how to use the FileSearchStores module to upload, retrieve, and delete file search
 * stores.
 */
public final class FileSearchStores {
  public static void main(String[] args) {
    final String filePath;
    if (args.length != 0) {
      filePath = args[0];
    } else {
      filePath = Constants.UPLOAD_FILE_PATH;
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
      System.out.println("Vertex AI API is not supported for this example.");
      System.exit(0);
    } else {
      System.out.println("Using Gemini Developer API");
    }

    try {
      FileSearchStore fileSearchStore = client.fileSearchStores.create(null);
      System.out.println("Created file store: " + fileSearchStore.name().get());

      // Get the uploaded file search store.
      FileSearchStore retrievedFileStore =
          client.fileSearchStores.get(fileSearchStore.name().get(), null);
      System.out.println("Retrieved file store: " + retrievedFileStore.name().get());

      // List all file stores.
      System.out.println("List file stores: ");
      for (FileSearchStore f :
          client.fileSearchStores.list(ListFileSearchStoresConfig.builder().pageSize(10).build())) {
        System.out.println("  File store name: " + f.name().get());
      }

      // Upload a file to the Files Service.
      File file =
          client.files.upload(filePath, UploadFileConfig.builder().mimeType("text/plain").build());
      System.out.println("Uploaded file: " + file.name().get());

      // Import the uploaded file to the file search store.
      ImportFileOperation importOperation =
          client.fileSearchStores.importFile(fileSearchStore.name().get(), file.name().get(), null);
      System.out.println("Import file operation: " + importOperation.name().get());
      while (importOperation.done().filter(Boolean::booleanValue).isEmpty()) {
        try {
          Thread.sleep(5000); // Sleep for 5 seconds.
          importOperation = client.operations.get(importOperation, null);
          System.out.println("Waiting for import operation to complete...");
        } catch (InterruptedException e) {
          System.out.println("Thread was interrupted while sleeping.");
          Thread.currentThread().interrupt();
        }
      }

      // Upload a file to the file search store.
      UploadToFileSearchStoreOperation uploadOperation =
          client.fileSearchStores.uploadToFileSearchStore(
              fileSearchStore.name().get(), filePath, null);
      System.out.println("Upload to file search store operation: " + uploadOperation.name().get());
      while (uploadOperation.done().filter(Boolean::booleanValue).isEmpty()) {
        try {
          Thread.sleep(5000); // Sleep for 5 seconds.
          uploadOperation = client.operations.get(uploadOperation, null);
          System.out.println("Waiting for upload operation to complete...");
        } catch (InterruptedException e) {
          System.out.println("Thread was interrupted while sleeping.");
          Thread.currentThread().interrupt();
        }
      }

      String documentName = uploadOperation.response().get().documentName().get();
      System.out.println("Uploaded document: " + documentName);

      // Get document
      Document retrievedDocument = client.fileSearchStores.documents.get(documentName, null);
      System.out.println("Retrieved document: " + retrievedDocument.name().get());

      // List documents
      System.out.println("List documents: ");
      for (Document d :
          client.fileSearchStores.documents.list(fileSearchStore.name().get(), null)) {
        System.out.println("  Document name: " + d.name().get());
      }

      // Delete the imported document
      client.fileSearchStores.documents.delete(documentName, null);
      System.out.println("Deleted document: " + documentName);

      // Delete the file search store
      client.fileSearchStores.delete(fileSearchStore.name().get(), null);
      System.out.println("Deleted file: " + fileSearchStore.name().get());
    } catch (GenAiIOException e) {
      System.out.println("An error occurred while uploading the file: " + e.getMessage());
    }
  }

  private FileSearchStores() {}
}
