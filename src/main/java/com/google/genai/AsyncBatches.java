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
import com.google.genai.Common.BuiltRequest;
import com.google.genai.errors.GenAiIOException;
import com.google.genai.types.BatchJob;
import com.google.genai.types.BatchJobSource;
import com.google.genai.types.CancelBatchJobConfig;
import com.google.genai.types.CreateBatchJobConfig;
import com.google.genai.types.CreateEmbeddingsBatchJobConfig;
import com.google.genai.types.DeleteBatchJobConfig;
import com.google.genai.types.DeleteResourceJob;
import com.google.genai.types.EmbeddingsBatchJobSource;
import com.google.genai.types.GetBatchJobConfig;
import com.google.genai.types.ListBatchJobsConfig;
import com.google.genai.types.ListBatchJobsResponse;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/** Async module of {@link Batches} */
public final class AsyncBatches {

  Batches batches;
  ApiClient apiClient;

  public AsyncBatches(ApiClient apiClient) {
    this.apiClient = apiClient;
    this.batches = new Batches(apiClient);
  }

  CompletableFuture<BatchJob> privateCreate(
      String model, BatchJobSource src, CreateBatchJobConfig config) {
    BuiltRequest builtRequest = batches.buildRequestForPrivateCreate(model, src, config);
    return this.apiClient
        .asyncRequest("post", builtRequest.path, builtRequest.body, builtRequest.httpOptions)
        .thenApplyAsync(
            response -> {
              try (ApiResponse res = response) {
                return batches.processResponseForPrivateCreate(res, config);
              }
            });
  }

  CompletableFuture<BatchJob> privateCreateEmbeddings(
      String model, EmbeddingsBatchJobSource src, CreateEmbeddingsBatchJobConfig config) {
    BuiltRequest builtRequest = batches.buildRequestForPrivateCreateEmbeddings(model, src, config);
    return this.apiClient
        .asyncRequest("post", builtRequest.path, builtRequest.body, builtRequest.httpOptions)
        .thenApplyAsync(
            response -> {
              try (ApiResponse res = response) {
                return batches.processResponseForPrivateCreateEmbeddings(res, config);
              }
            });
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
    BuiltRequest builtRequest = batches.buildRequestForGet(name, config);
    return this.apiClient
        .asyncRequest("get", builtRequest.path, builtRequest.body, builtRequest.httpOptions)
        .thenApplyAsync(
            response -> {
              try (ApiResponse res = response) {
                return batches.processResponseForGet(res, config);
              }
            });
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
    BuiltRequest builtRequest = batches.buildRequestForCancel(name, config);
    return this.apiClient
        .asyncRequest("post", builtRequest.path, builtRequest.body, builtRequest.httpOptions)
        .thenAccept(
            response -> {
              try (ApiResponse res = response) {}
            });
  }

  CompletableFuture<ListBatchJobsResponse> privateList(ListBatchJobsConfig config) {
    BuiltRequest builtRequest = batches.buildRequestForPrivateList(config);
    return this.apiClient
        .asyncRequest("get", builtRequest.path, builtRequest.body, builtRequest.httpOptions)
        .thenApplyAsync(
            response -> {
              try (ApiResponse res = response) {
                return batches.processResponseForPrivateList(res, config);
              }
            });
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
    BuiltRequest builtRequest = batches.buildRequestForDelete(name, config);
    return this.apiClient
        .asyncRequest("delete", builtRequest.path, builtRequest.body, builtRequest.httpOptions)
        .thenApplyAsync(
            response -> {
              try (ApiResponse res = response) {
                return batches.processResponseForDelete(res, config);
              }
            });
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
    if (config == null) {
      config = ListBatchJobsConfig.builder().build();
    }
    ListBatchJobsConfig finalConfig = config;
    Function<JsonSerializable, CompletableFuture<JsonNode>> request =
        requestConfig -> {
          if (!(requestConfig instanceof ListBatchJobsConfig)) {
            throw new GenAiIOException(
                "Internal error: Pager expected ListBatchJobsConfig but received "
                    + requestConfig.getClass().getName());
          }
          return this.privateList((ListBatchJobsConfig) requestConfig)
              .thenApply(JsonSerializable::toJsonNode);
        };
    return CompletableFuture.supplyAsync(
        () ->
            new AsyncPager<BatchJob>(
                Pager.PagedItem.BATCH_JOBS,
                request,
                (ObjectNode) JsonSerializable.toJsonNode(finalConfig),
                request.apply(finalConfig)));
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
    if (this.apiClient.vertexAI()) {
      if (src.inlinedRequests().isPresent()) {
        throw new GenAiIOException("inlinedRequests is not supported for Vertex AI.");
      }
      if (src.fileName().isPresent()) {
        throw new GenAiIOException("fileName is not supported for Vertex AI.");
      }
      if (src.gcsUri().isPresent() && src.bigqueryUri().isPresent()) {
        throw new GenAiIOException("Only one of gcsUri and bigqueryUri can be set.");
      }
      if (!src.gcsUri().isPresent() && !src.bigqueryUri().isPresent()) {
        throw new GenAiIOException("One of gcsUri and bigqueryUri must be set.");
      }
    } else {
      if (src.fileName().isPresent() && src.inlinedRequests().isPresent()) {
        throw new GenAiIOException("Only one of fileName and InlinedRequests can be set.");
      }
      if (!src.fileName().isPresent() && !src.inlinedRequests().isPresent()) {
        throw new GenAiIOException("one of fileName and InlinedRequests must be set.");
      }
    }
    return this.privateCreate(model, src, config);
  }

  /**
   * Makes an API request to create the batch embeddings job.
   *
   * <p>This method is experimental.
   *
   * @param model the name of the GenAI model to use for generation
   * @param src The {@link EmbeddingsBatchJobSource} of the batch job.
   * @param config The configuration {@link CreateEmbeddingsBatchJobConfig} for the batch job.
   * @return A BatchJob.
   */
  public CompletableFuture<BatchJob> createEmbeddings(
      String model, EmbeddingsBatchJobSource src, CreateEmbeddingsBatchJobConfig config) {
    if (this.apiClient.vertexAI()) {
      throw new UnsupportedOperationException(
          "Vertex AI does not support batches.createEmbeddings.");
    }
    return this.privateCreateEmbeddings(model, src, config);
  }
}
