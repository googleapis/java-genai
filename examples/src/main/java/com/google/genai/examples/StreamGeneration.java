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

import com.google.genai.Client;
import com.google.genai.ResponseStream;
import com.google.genai.types.GenerateContentResponse;
import java.io.IOException;
import org.apache.http.HttpException;

/**
 * An example of using the Unified Gen AI Java SDK to generate stream of content.
 *
 * <p>mvn clean compile exec:java -Dexec.mainClass="com.google.genai.examples.StreamGeneration"
 */
public class StreamGeneration {
  public static void main(String[] args) throws IOException, HttpException {
    Client client = Client.builder().setVertexAI(true).build();

    ResponseStream<GenerateContentResponse> response =
        client.models.generateContentStream(
            "gemini-2.0-flash-exp", "Tell me a story in 300 words.", null);
    for (GenerateContentResponse res : response) {
      System.out.print(res.text());
    }
  }
}
