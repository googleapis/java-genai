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
 * <p>1. Compile the java package and run the sample code.
 *
 * <p>mvn clean compile exec:java -Dexec.mainClass="com.google.genai.examples.LocalCountTokens"
 */
package com.google.genai.examples;

import com.google.genai.LocalTokenizer;

/** An example of using the Unified Gen AI Java SDK to count tokens locally. */
public class LocalCountTokens {
  public static void main(String[] args) {
    LocalTokenizer tokenizer = new LocalTokenizer(Constants.GEMINI_MODEL_NAME);
    System.out.println(
        "Count for 'Hello world': " + tokenizer.countTokens("Hello world").totalTokens());
  }
}
