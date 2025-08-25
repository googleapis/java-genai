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
 * <p>mvn exec:java -Dexec.mainClass="com.google.genai.examples.BatchManagement"
 * -Dexec.args="YOUR_MODEL_ID"
 */
package com.google.genai.examples;

import com.google.genai.Client;
import com.google.genai.types.BatchJob;
import com.google.genai.types.BatchJobDestination;
import com.google.genai.types.BatchJobSource;
import com.google.genai.types.Content;
import com.google.genai.types.CreateBatchJobConfig;
import com.google.genai.types.InlinedRequest;
import com.google.genai.types.ListBatchJobsConfig;
import com.google.genai.types.Part;

/** An example of using the Unified Gen AI Java SDK to do operations on batch jobs. */
public final class BatchManagement {

  public static void main(String[] args) {
    final String modelId;
    if (args.length != 0) {
      modelId = args[0];
    } else {
      modelId = Constants.GEMINI_MODEL_NAME;
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
      System.out.println("Using Vertex AI");
      // Create a batch job.
      BatchJobSource batchJobSource =
          BatchJobSource.builder()
              .gcsUri("gs://unified-genai-tests/batches/input/generate_content_requests.jsonl")
              .format("jsonl")
              .build();
      CreateBatchJobConfig config =
          CreateBatchJobConfig.builder()
              .displayName("summarize the pdf")
              .dest(
                  BatchJobDestination.builder()
                      .gcsUri("gs://unified-genai-tests/batches/output")
                      .format("jsonl"))
              .build();
      BatchJob batchJob1 = client.batches.create(modelId, batchJobSource, config);
      System.out.println("Created batch job: " + batchJob1);
      // Get the batch job by name.
      BatchJob batchJob2 = client.batches.get(batchJob1.name().get(), null);
      System.out.println("Get batch job: " + batchJob2);
      // Cancel the batch job.
      client.batches.cancel(batchJob1.name().get(), null);
      System.out.println("Cancelled batch job: " + batchJob1.name().get());
    } else {
      System.out.println("Using Gemini Developer API");
      // Create a batch job.
      BatchJobSource batchJobSource =
          BatchJobSource.builder()
              .inlinedRequests(
                  InlinedRequest.builder()
                      .contents(Content.builder().parts(Part.fromText("Hello!"))))
              .build();
      CreateBatchJobConfig config =
          CreateBatchJobConfig.builder().displayName("test-batch-job-java").build();
      BatchJob batchJob1 = client.batches.create(modelId, batchJobSource, config);
      System.out.println("Created batch job: " + batchJob1);
      // Get the batch job by name.
      BatchJob batchJob2 = client.batches.get(batchJob1.name().get(), null);
      System.out.println("Get batch job: " + batchJob2);
      // Cancel the batch job.
      client.batches.cancel(batchJob1.name().get(), null);
      System.out.println("Cancelled batch job: " + batchJob1.name().get());
    }

    // List all batch jobs.
    System.out.println("List batch jobs resource names: ");
    for (BatchJob b :
        client.batches.list(ListBatchJobsConfig.builder().pageSize(5).build()).page()) {
      System.out.println(b.name().get());
      System.out.println(b.state().get());
    }

  }

  private BatchManagement() {}
}
