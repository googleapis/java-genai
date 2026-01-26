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

import com.google.genai.types.interactions.content.FileSearchCallContent;

/**
 * Example demonstrating the usage of FileSearchCallContent.
 *
 * <p>FileSearchCallContent represents a file search operation call in the Interactions API.
 * Unlike other tool calls, FileSearchCallContent contains only the type and call ID.
 * All search configuration (stores, top_k, filters) is defined in the FileSearch.
 */
public class FileSearchCallContentExample {

  public static void main(String[] args) {
    // Example 1: Create FileSearchCallContent using builder
    FileSearchCallContent content1 =
        FileSearchCallContent.builder()
            .id("file-search-call-123")
            .build();

    System.out.println("FileSearchCallContent (using builder) JSON:");
    System.out.println(content1.toJson());
    System.out.println();

    // Example 2: Create FileSearchCallContent using convenience method
    FileSearchCallContent content2 =
        FileSearchCallContent.of("call-456");

    System.out.println("FileSearchCallContent (using of() method) JSON:");
    System.out.println(content2.toJson());
    System.out.println();

    // Example 3: Deserialize from JSON
    String json = content2.toJson();
    FileSearchCallContent deserialized = FileSearchCallContent.fromJson(json);

    System.out.println("Deserialized FileSearchCallContent:");
    System.out.println("  ID: " + deserialized.id());
    System.out.println();

    // Example 4: Create FileSearchCallContent without ID (optional)
    FileSearchCallContent content3 =
        FileSearchCallContent.builder()
            .build();

    System.out.println("FileSearchCallContent (no ID) JSON:");
    System.out.println(content3.toJson());
  }
}
