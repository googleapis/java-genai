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
 * <p>mvn exec:java -Dexec.mainClass="com.google.genai.examples.TuningJobsAsync"
 * -Dexec.args="YOUR_MODEL_ID"
 */
package com.google.genai.examples;

import com.google.genai.AsyncPager;
import com.google.genai.Client;
import com.google.genai.types.ListTuningJobsConfig;
import com.google.genai.types.TuningDataset;
import com.google.genai.types.TuningJob;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/** An example of using the Unified Gen AI Java SDK to do async operations on tuning jobs. */
public final class TuningJobsAsync {

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
    ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    try {
      if (client.vertexAI()) {
        System.out.println("Using Vertex AI");
        // Create a tuning job.
        TuningDataset tuningDataset =
            TuningDataset.builder()
                .gcsUri(
                    "gs://cloud-samples-data/ai-platform/generative_ai/gemini-1_5/text/sft_train_data.jsonl")
                .build();
        CompletableFuture<TuningJob> tuningJob1Future =
            client.async.tunings.tune(modelId, tuningDataset, null);
        tuningJob1Future
            .thenCompose(
                tuningJob -> {
                  System.out.println("Created tuning job: " + tuningJob.name().get());
                  return pollUntilComplete(tuningJob.name().get(), client, scheduler);
                })
            .thenAccept(
                finalJob -> {
                  String tunedModel = finalJob.tunedModel().get().model().get();
                  System.out.println("Tuned model: " + tunedModel);
                })
            .join();
        System.out.println();
      } else {
        System.out.println("Using Gemini Developer API");
      }

      // List tuning jobs.
      CompletableFuture<AsyncPager<TuningJob>> asyncPagerFuture =
          client.async.tunings.list(ListTuningJobsConfig.builder().pageSize(5).build());
      asyncPagerFuture
          .thenCompose(
              asyncPager -> {
                System.out.println("List tuning jobs resource names: ");
                return asyncPager.page();
              })
          .thenAccept(
              page -> {
                page.forEach(
                    job -> System.out.println(job.name().get() + "\n" + job.state().get()));
              })
          .join();
    } finally {
      scheduler.shutdown();
    }
  }

  @SuppressWarnings("FutureReturnValueIgnored")
  // Polls the tuning job status asynchronously until it is complete.
  private static CompletableFuture<TuningJob> pollUntilComplete(
      String jobName, Client client, ScheduledExecutorService scheduler) {

    return client
        .async
        .tunings
        .get(jobName, null)
        .thenCompose(
            job -> {
              // Check if the model is ready.
              if (job.tunedModel().isPresent() && job.tunedModel().get().model().isPresent()) {
                return CompletableFuture.completedFuture(job);
              } else {
                // The job is not done. Schedule the next poll.
                System.out.println(
                    "Waiting for tuned model to be available... Current state: "
                        + job.state().get());
                CompletableFuture<TuningJob> result = new CompletableFuture<>();
                // Schedule the next call to this same method after a 10-second delay.
                scheduler.schedule(
                    () ->
                        pollUntilComplete(jobName, client, scheduler)
                            .thenAccept(result::complete)
                            .exceptionally(
                                ex -> {
                                  result.completeExceptionally(ex);
                                  return null;
                                }),
                    10,
                    TimeUnit.SECONDS);
                return result;
              }
            });
  }

  private TuningJobsAsync() {}
}
