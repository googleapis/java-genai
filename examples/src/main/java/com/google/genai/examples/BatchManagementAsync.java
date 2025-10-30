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
 * <p>mvn exec:java -Dexec.mainClass="com.google.genai.examples.BatchManagementAsync"
 * -Dexec.args="YOUR_MODEL_ID"
 */
package com.google.genai.examples;

import com.google.genai.AsyncPager;
import com.google.genai.Client;
import com.google.genai.types.BatchJob;
import com.google.genai.types.BatchJobDestination;
import com.google.genai.types.BatchJobSource;
import com.google.genai.types.Content;
import com.google.genai.types.CreateBatchJobConfig;
import com.google.genai.types.InlinedRequest;
import com.google.genai.types.ListBatchJobsConfig;
import com.google.genai.types.Part;
import java.util.concurrent.CompletableFuture;

/** An example of using the Unified Gen AI Java SDK to do async operations on batch jobs. */
public final class BatchManagementAsync {

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
              .bigqueryUri(
                  "bq://vertex-sdk-dev.unified_genai_tests_batches.generate_content_requests")
              .format("bigquery")
              .build();
      CreateBatchJobConfig config =
          CreateBatchJobConfig.builder()
              .displayName("test batch")
              .dest(
                  BatchJobDestination.builder()
                      .bigqueryUri(
                          "bq://vertex-sdk-dev.unified_genai_tests_batches.generate_content_output")
                      .format("bigquery"))
              .build();
      CompletableFuture<Void> finalFuture =
          client
              .async
              .batches
              .create(modelId, batchJobSource, config)
              .thenCompose(
                  batchJob1 -> {
                    System.out.println("Created batch job: " + batchJob1.name());
                    CompletableFuture<BatchJob> batchJob2Future =
                        client.async.batches.get(batchJob1.name().get(), null);

                    return batchJob2Future.thenCompose(
                        batchJob2 -> {
                          System.out.println("Get batch job: " + batchJob2.name());
                          // Cancel the batch job. This is now nested, so it happens after get
                          // completes.
                          return client
                              .async
                              .batches
                              .cancel(batchJob1.name().get(), null)
                              .thenAccept(
                                  cancelResponse -> {
                                    System.out.println(
                                        "Successfully initiated cancellation for batch job: "
                                            + batchJob1.name());
                                  });
                        });
                  });
      finalFuture.join();
      System.out.println("All batch job operations completed.");
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
      CompletableFuture<Void> finalFuture =
          client
              .async
              .batches
              .create(modelId, batchJobSource, config)
              .thenCompose(
                  batchJob1 -> {
                    System.out.println("Created batch job: " + batchJob1.name());
                    CompletableFuture<BatchJob> batchJob2Future =
                        client.async.batches.get(batchJob1.name().get(), null);

                    return batchJob2Future.thenCompose(
                        batchJob2 -> {
                          System.out.println("Get batch job: " + batchJob2.name());
                          // Cancel the batch job. This is now nested, so it happens after get
                          // completes.
                          return client
                              .async
                              .batches
                              .cancel(batchJob1.name().get(), null)
                              .thenAccept(
                                  cancelResponse -> {
                                    System.out.println(
                                        "Successfully initiated cancellation for batch job: "
                                            + batchJob1.name());
                                  });
                        });
                  });
      finalFuture.join();
      System.out.println("All batch job operations completed.");
    }
    // List all batch jobs.
    CompletableFuture<AsyncPager<BatchJob>> asyncPagerFuture =
        client.async.batches.list(ListBatchJobsConfig.builder().pageSize(10).build());
    asyncPagerFuture
        .thenCompose(
            asyncPager -> {
              System.out.println("List all batch job names: ");
              return asyncPager.forEach(
                  item -> System.out.println("Batch job name: " + item.name().get()));
            })
        .join();
  }

  private BatchManagementAsync() {}
}
