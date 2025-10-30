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
 * <p>mvn exec:java -Dexec.mainClass="com.google.genai.examples.BatchInlinedRequests"
 * -Dexec.args="YOUR_MODEL_ID"
 */
package com.google.genai.examples;

import com.google.common.collect.ImmutableList;
import com.google.genai.Client;
import com.google.genai.types.BatchJob;
import com.google.genai.types.BatchJobSource;
import com.google.genai.types.Content;
import com.google.genai.types.CreateBatchJobConfig;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.InlinedRequest;
import com.google.genai.types.Part;

/** An example of creating a batch job with inlined requests. */
public final class BatchInlinedRequests {

  public static void main(String[] args) {
    // Instantiate the client. The client by default uses the Gemini Developer API. It gets the API
    // key from the environment variable `GOOGLE_API_KEY`. Vertex AI API can be used by setting the
    // environment variables `GOOGLE_CLOUD_LOCATION` and `GOOGLE_CLOUD_PROJECT`, as well as setting
    // `GOOGLE_GENAI_USE_VERTEXAI` to "true".
    Client client = new Client();

    if (client.vertexAI()) {
      System.out.println("Inlined requests are not supported for Vertex AI backend.");
      return;
    } else {
      System.out.println("Calling GeminiAPI Backend...");
    }

    InlinedRequest request1 =
        InlinedRequest.builder()
            .contents(Content.builder().parts(Part.fromText("Tell me a one-sentence joke.")))
            .config(
                GenerateContentConfig.builder()
                    .systemInstruction(
                        Content.builder()
                            .parts(
                                Part.fromText(
                                    "You are a funny comedian. Always respond with humor and"
                                        + " wit.")))
                    .temperature(0.5f))
            .build();

    InlinedRequest request2 =
        InlinedRequest.builder()
            .contents(Content.builder().parts(Part.fromText("Why is the sky blue?")))
            .config(
                GenerateContentConfig.builder()
                    .systemInstruction(
                        Content.builder()
                            .parts(
                                Part.fromText(
                                    "You are a helpful science teacher. Explain complex concepts in"
                                        + " simple terms.")))
                    .temperature(0.5f))
            .build();

    BatchJobSource batchJobSource =
        BatchJobSource.builder().inlinedRequests(ImmutableList.of(request1, request2)).build();

    CreateBatchJobConfig config =
        CreateBatchJobConfig.builder().displayName("inlined-requests-job-1").build();

    BatchJob batchJob =
        client.batches.create(Constants.GEMINI_MODEL_NAME, batchJobSource, config);

    System.out.println("Created batch job: " + batchJob.name().get());
  }
}
