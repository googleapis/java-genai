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
 * <p>Note: The Interactions API is currently only available via the Gemini Developer API (not
 * Vertex AI).
 *
 * <p>IMPORTANT: This example requires file search stores to be created beforehand.
 *
 * <p>Setup Instructions:
 *
 * <p>1. Create a file search store using the Google AI API:
 *
 * <p>Using gcloud CLI or API:
 *
 * <p>curl -X POST https://generativelanguage.googleapis.com/v1beta/fileSearchStores \
 *
 * <p>-H "Authorization: Bearer $(gcloud auth print-access-token)" \
 *
 * <p>-H "Content-Type: application/json" \
 *
 * <p>-d '{"displayName": "my-document-store"}'
 *
 * <p>2. Upload files to the store:
 *
 * <p>curl -X POST
 * https://generativelanguage.googleapis.com/v1beta/fileSearchStores/STORE_NAME/documents \
 *
 * <p>-H "Authorization: Bearer $(gcloud auth print-access-token)" \
 *
 * <p>-F "file=@/path/to/document.pdf"
 *
 * <p>3. Set an API key environment variable:
 *
 * <p>export GOOGLE_API_KEY=YOUR_API_KEY
 *
 * <p>4. Update the store name in this example to match your created store.
 *
 * <p>5. Compile and run:
 *
 * <p>mvn clean compile
 *
 * <p>mvn exec:java -Dexec.mainClass="com.google.genai.examples.InteractionsFileSearch"
 */
package com.google.genai.examples;

import com.google.genai.Client;
import com.google.genai.types.interactions.CreateInteractionConfig;
import com.google.genai.types.interactions.Interaction;
import com.google.genai.types.interactions.FileSearchResult;
import com.google.genai.types.interactions.content.FileSearchResultContent;
import com.google.genai.types.interactions.content.Content;
import com.google.genai.types.interactions.content.TextContent;
import com.google.genai.types.interactions.tools.FileSearch;

/**
 * Example: File Search Tool with the Interactions API
 *
 * <p>Demonstrates how to use the FileSearch to enable the model to search through file stores.
 * This is useful for RAG (Retrieval-Augmented Generation) use cases where you want the model to
 * answer questions based on your documents.
 *
 * <p>IMPORTANT: Requires file search stores to be created beforehand. See usage instructions
 * above.
 *
 * <p>Note: The Interactions API is in beta and subject to change.
 */
public final class InteractionsFileSearch {

  public static void main(String[] args) {
    // Instantiate the client. The client gets the API key from the environment variable
    // `GOOGLE_API_KEY`.
    //
    Client client = new Client();

    System.out.println("=== Interactions API: File Search Tool Example ===\n");

    System.out.println("IMPORTANT: This example requires file search stores to be created.\n");
    System.out.println("See the file header comments for setup instructions.\n");

    // ===== STEP 1: Create FileSearch =====
    System.out.println("STEP 1: Create FileSearch\n");

    // Configure the file search tool
    // Update the store names to match your created file search stores
    FileSearch fileSearchTool =
        FileSearch.builder()
            .fileSearchStoreNames(
                "my-document-store-1",
                "my-document-store-2" // You can search across multiple stores
                )
            .topK(10) // Maximum number of results to return
            // Optional: Add metadata filter
            // .metadataFilter(ImmutableMap.of("category", "technical", "year", 2025))
            .build();

    System.out.println("FileSearch created successfully");
    System.out.println(
        "Store Names: " + fileSearchTool.fileSearchStoreNames().orElse(java.util.List.of()));
    System.out.println("Top K: " + fileSearchTool.topK().orElse(10));
    System.out.println();

    // ===== STEP 2: Create interaction with File Search enabled =====
    System.out.println("---\n");
    System.out.println("STEP 2: Create interaction with File Search enabled\n");

    String userQuestion =
        "What are the main features described in the documentation? "
            + "Please search through the uploaded files.";
    System.out.println("User: " + userQuestion + "\n");

    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model("gemini-3-flash-preview")
            .input(userQuestion)
            .tools(fileSearchTool)
            .build();

    // Print the request JSON
    System.out.println("=== REQUEST ===");
    System.out.println(config.toJson());
    System.out.println();

    try {
      Interaction response = client.interactions.create(config);

      // Print the response JSON
      System.out.println("=== RESPONSE ===");
      System.out.println(response.toJson());
      System.out.println();

      // ===== STEP 3: Extract and display the results =====
      System.out.println("---\n");
      System.out.println("STEP 3: Extract and display the results\n");

      System.out.println("Response received. Interaction ID: " + response.id());
      System.out.println();

      if (response.outputs().isPresent()) {
        for (Content content : response.outputs().get()) {
          if (content instanceof TextContent) {
            System.out.println("Text: " + ((TextContent) content).text().orElse("(empty)"));
            System.out.println();
          } else if (content instanceof FileSearchResultContent) {
            FileSearchResultContent searchResult = (FileSearchResultContent) content;
            System.out.println("File Search Result:");

            if (searchResult.result().isPresent()) {
              for (FileSearchResult result : searchResult.result().get()) {
                System.out.println("  Title: " + result.title().orElse("N/A"));
                System.out.println("  File Search Store: " + result.fileSearchStore().orElse("N/A"));

                if (result.text().isPresent()) {
                  String text = result.text().get();
                  System.out.println("  Text (first 500 chars):");
                  System.out.println("  ---");
                  System.out.println(text.length() > 500 ? text.substring(0, 500) + "..." : text);
                  System.out.println("  ---");
                  System.out.println("  Total text length: " + text.length() + " chars");
                }
              }
            }
            System.out.println();
          }
        }
      }

      System.out.println("\n=== Example completed successfully ===");

    } catch (Exception e) {
      System.err.println("\n=== Error occurred ===");
      System.err.println("Error: " + e.getMessage());
      System.err.println("\nNote: This example requires file search stores to be created.");
      System.err.println("Please check:");
      System.err.println("  1. File search stores exist with the names specified");
      System.err.println("  2. Files have been uploaded to the stores");
      System.err.println("  3. Your API key has access to the stores");
      System.err.println("  4. The store names in this example match your actual store names");
      e.printStackTrace();
    }
  }

  private InteractionsFileSearch() {}
}
