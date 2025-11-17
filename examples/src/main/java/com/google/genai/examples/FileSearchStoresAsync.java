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
import com.google.genai.types.File;
import com.google.genai.types.FileSearchStore;
import com.google.genai.types.ListFileSearchStoresConfig;
import com.google.genai.types.Operation;
import com.google.genai.types.UploadFileConfig;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * An example of how to use the FileSearchStores module to upload, retrieve, and delete file search
 * stores.
 */
public final class FileSearchStoresAsync {

  private static <R, T extends Operation<R, T>> CompletableFuture<T> awaitOperationComplete(
      Client client, T operation) {
    if (operation.done().orElse(false)) {
      System.out.println("Operation " + operation.name().get() + " completed.");
      return CompletableFuture.completedFuture(operation);
    }

    System.out.println("Waiting for operation to complete...");
    return CompletableFuture.supplyAsync(
            () -> operation, CompletableFuture.delayedExecutor(5, TimeUnit.SECONDS))
        .thenApply(
            op -> {
              try {
                return client.async.operations.get(op, null).get();
              } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException("Failed to get operation status", e);
              }
            })
        .thenCompose(newOp -> awaitOperationComplete(client, (T) newOp));
  }

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    String filePath = args.length > 0 ? args[0] : Constants.UPLOAD_FILE_PATH;

    try (Client client = new Client()) {
      if (client.vertexAI()) {
        System.out.println("Vertex AI API is not supported for this example.");
        return;
      }
      System.out.println("Using Gemini Developer API");

      // Create store
      CompletableFuture<Void> finalFuture =
          client
              .async
              .fileSearchStores
              .create(null)
              .thenCompose(
                  store -> {
                    System.out.println("\n--- Created Store: " + store.name().get() + " ---");

                    // Get store
                    return client
                        .async
                        .fileSearchStores
                        .get(store.name().get(), null)
                        .thenAccept(
                            retrievedStore ->
                                System.out.println(
                                    "Get Store: Success (" + retrievedStore.name().get() + ")"))

                        // List stores.
                        .thenCompose(
                            v ->
                                client.async.fileSearchStores.list(
                                    ListFileSearchStoresConfig.builder().pageSize(10).build()))
                        .thenAccept(
                            pager -> {
                              System.out.println("List all stores names: ");
                              pager.forEach(
                                  item ->
                                      System.out.println("Store name: " + item.name().get()));
                            })
                        .thenApply(v -> store);
                  })
              .thenCompose(
                  store -> {
                    // Upload File
                    return client
                        .async
                        .files
                        .upload(filePath, UploadFileConfig.builder().mimeType("text/plain").build())
                        .thenApply(
                            file -> {
                              System.out.println(
                                  "Upload File (Files Service): " + file.name().get());
                              return new Object[] {store, file}; // Pass both
                            });
                  })
              .thenCompose(
                  objects -> {
                    FileSearchStore store = (FileSearchStore) objects[0];
                    File file = (File) objects[1];

                    // Import File
                    return client
                        .async
                        .fileSearchStores
                        .importFile(store.name().get(), file.name().get(), null)
                        .thenCompose(operation -> awaitOperationComplete(client, operation))
                        .thenApply(
                            completedOp -> {
                              System.out.println("Import File: LRO Completed.");
                              return store;
                            });
                  })

              // Direct upload file to the store
              .thenCompose(
                  store -> {
                    return client
                        .async
                        .fileSearchStores
                        .uploadToFileSearchStore(store.name().get(), filePath, null)
                        .thenCompose(operation -> awaitOperationComplete(client, operation))
                        .thenApply(
                            completedOp -> {
                              String docName = completedOp.response().get().documentName().get();
                              System.out.println("Direct Upload: Completed document " + docName);
                              return new Object[] {store, docName};
                            });
                  })
              .thenCompose(
                  objects -> {
                    FileSearchStore store = (FileSearchStore) objects[0];
                    String docName = (String) objects[1];
                    return client
                        .async
                        .fileSearchStores
                        .documents
                        .get(docName, null)
                        .thenAccept(
                            doc ->
                                System.out.println(
                                    "Get Document: Success (" + doc.name().get() + ")"))

                        // List documents
                        .thenCompose(
                            v ->
                                client.async.fileSearchStores.documents.list(
                                    store.name().get(), null))
                        .thenAccept(
                            pager -> {
                              System.out.println("List all document names: ");
                              pager.forEach(
                                  item ->
                                      System.out.println(
                                          "document name: " + item.name().get()));
                            })

                        // Delete document
                        .thenCompose(
                            v -> client.async.fileSearchStores.documents.delete(docName, null))
                        .thenRun(() -> System.out.println("Delete Document: Success."))

                        // Delete store
                        .thenCompose(
                            v -> client.async.fileSearchStores.delete(store.name().get(), null))
                        .thenAccept(v -> System.out.println("Delete Store: Success."));
                  });
      finalFuture.get();
    } catch (Exception e) {
      System.err.println("An error occurred during async execution: " + e.getMessage());
    }
  }

  private FileSearchStoresAsync() {}
}
