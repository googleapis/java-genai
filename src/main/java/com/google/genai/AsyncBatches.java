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

// Auto-generated code. Do not edit.

package com.google.genai;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.genai.errors.GenAiIOException;
import com.google.genai.types.BatchJob;
import com.google.genai.types.BatchJobSource;
import com.google.genai.types.CancelBatchJobConfig;
import com.google.genai.types.CreateBatchJobConfig;
import com.google.genai.types.DeleteBatchJobConfig;
import com.google.genai.types.DeleteResourceJob;
import com.google.genai.types.GetBatchJobConfig;
import com.google.genai.types.ListBatchJobsConfig;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/** Async module of {@link Batches} */
public final class AsyncBatches {
  Batches batches;

  public AsyncBatches(ApiClient apiClient) {
    this.batches = new Batches(apiClient);
  }

  /**
   * Asynchronously gets a batch job resource.
   *
   * @param name A fully-qualified BatchJob resource name or ID. Example:
   *     "projects/.../locations/.../batchPredictionJobs/456" or "456" when project and location are
   *     initialized in the Vertex AI client. Or "batches/abc" using the Gemini Developer AI client.
   * @param config A {@link GetBatchJobConfig} for configuring the get request.
   * @return A {@link BatchJob} object that contains the info of the batch job.
   */
  public CompletableFuture<BatchJob> get(String name, GetBatchJobConfig config) {
    return CompletableFuture.supplyAsync(() -> batches.get(name, config));
  }

  /**
   * Asynchronously cancels a batch job resource.
   *
   * @param name A fully-qualified BatchJob resource name or ID. Example:
   *     "projects/.../locations/.../batchPredictionJobs/456" or "456" when project and location are
   *     initialized in the Vertex AI client. Or "batches/abc" using the Gemini Developer AI client.
   * @param config A {@link CancelBatchJobConfig} for configuring the cancel request.
   */
  public CompletableFuture<Void> cancel(String name, CancelBatchJobConfig config) {
    return CompletableFuture.runAsync(() -> batches.cancel(name, config));
  }

  /**
   * Asynchronously deletes a batch job resource.
   *
   * @param name A fully-qualified BatchJob resource name or ID. Example:
   *     "projects/.../locations/.../batchPredictionJobs/456" or "456" when project and location are
   *     initialized in the Vertex AI client. Or "batches/abc" using the Gemini Developer AI client.
   * @param config A {@link DeleteBatchJobConfig} for configuring the delete request.
   */
  public CompletableFuture<DeleteResourceJob> delete(String name, DeleteBatchJobConfig config) {
    return CompletableFuture.supplyAsync(() -> batches.delete(name, config));
  }

  /**
   * Asynchronously creates a batch job.
   *
   * @param model the name of the GenAI model to use for batch generation.
   * @param src The {@link BatchJobSource} of the batch job.
   * @param config The configuration {@link CreateBatchJobConfig} for the batch job.
   * @return A future that resolves to the batch job.
   */
  public CompletableFuture<BatchJob> create(
      String model, BatchJobSource src, CreateBatchJobConfig config) {
    return CompletableFuture.supplyAsync(() -> batches.create(model, src, config));
  }

  /**
   * Asynchronously makes an API request to list the available batch jobs.
   *
   * @param config A {@link ListBatchJobsConfig} for configuring the list request.
   * @return A CompletableFuture that resolves to a {@link AsyncPager}. The AsyncPager has a
   *     `forEach` method that can be used to asynchronously process items in the page and
   *     automatically query the next page once the current page is exhausted.
   */
  @SuppressWarnings("PatternMatchingInstanceof")
  public CompletableFuture<AsyncPager<BatchJob>> list(ListBatchJobsConfig config) {
    Function<JsonSerializable, CompletableFuture<JsonNode>> request =
        requestConfig -> {
          if (!(requestConfig instanceof ListBatchJobsConfig)) {
            throw new GenAiIOException(
                "Internal error: Pager expected ListBatchJobsConfig but received "
                    + requestConfig.getClass().getName());
          }
          return CompletableFuture.supplyAsync(
              () ->
                  JsonSerializable.toJsonNode(
                      batches.privateList((ListBatchJobsConfig) requestConfig)));
        };
    return CompletableFuture.supplyAsync(
        () ->
            new AsyncPager<>(
                Pager.PagedItem.BATCH_JOBS,
                request,
                (ObjectNode) JsonSerializable.toJsonNode(config),
                request.apply(config)));
  }
}
