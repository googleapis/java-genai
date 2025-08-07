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
import com.google.genai.types.CreateTuningJobConfig;
import com.google.genai.types.GetTuningJobConfig;
import com.google.genai.types.JobState;
import com.google.genai.types.ListTuningJobsConfig;
import com.google.genai.types.PreTunedModel;
import com.google.genai.types.TuningDataset;
import com.google.genai.types.TuningJob;
import com.google.genai.types.TuningOperation;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/** Async module of {@link Tunings} */
public final class AsyncTunings {
  Tunings tunings;

  public AsyncTunings(ApiClient apiClient) {
    this.tunings = new Tunings(apiClient);
  }

  /**
   * Asynchronously makes an API request to get a tuning job.
   *
   * @param name The resource name of the tuning job.
   * @param config A {@link GetTuningJobConfig} for configuring the get request.
   * @return A CompletableFuture that resolves to a {@link TuningJob} object.
   */
  public CompletableFuture<TuningJob> get(String name, GetTuningJobConfig config) {
    return CompletableFuture.supplyAsync(() -> tunings.privateGet(name, config));
  }

  /**
   * Asynchronously makes an API request to list the available tuning jobs.
   *
   * @param config A {@link ListTuningJobsConfig} for configuring the list request.
   * @return A CompletableFuture that resolves to a {@link AsyncPager}. The AsyncPager has a
   *     `forEach` method that can be used to asynchronously process items in the page and
   *     automatically query the next page once the current page is exhausted.
   */
  @SuppressWarnings("PatternMatchingInstanceof")
  public CompletableFuture<AsyncPager<TuningJob>> list(ListTuningJobsConfig config) {
    Function<JsonSerializable, CompletableFuture<JsonNode>> request =
        requestConfig -> {
          if (!(requestConfig instanceof ListTuningJobsConfig)) {
            throw new GenAiIOException(
                "Internal error: Pager expected ListTuningJobsConfig but received "
                    + requestConfig.getClass().getName());
          }
          return CompletableFuture.supplyAsync(
              () ->
                  JsonSerializable.toJsonNode(
                      tunings.privateList((ListTuningJobsConfig) requestConfig)));
        };
    return CompletableFuture.supplyAsync(
        () ->
            new AsyncPager<>(
                Pager.PagedItem.TUNING_JOBS,
                request,
                (ObjectNode) JsonSerializable.toJsonNode(config),
                request.apply(config)));
  }

  /**
   * Asynchronously makes an API request to create a supervised fine-tuning job.
   *
   * <p>This method is experimental.
   *
   * @param baseModel The base model to tune.
   * @param trainingDataset The training dataset to use for tuning.
   * @param config A {@link CreateTuningJobConfig} for configuring the create request.
   * @return A CompletableFuture that resolves to a {@link TuningJob} object.
   */
  public CompletableFuture<TuningJob> tune(
      String baseModel, TuningDataset trainingDataset, CreateTuningJobConfig config) {
    return CompletableFuture.supplyAsync(
        () -> {
          if (tunings.apiClient.vertexAI()) {
            if (baseModel.startsWith("projects/")) {
              PreTunedModel preTunedModel =
                  PreTunedModel.builder().tunedModelName(baseModel).build();
              return tunings.privateTune(null, preTunedModel, trainingDataset, config);
            } else {
              return tunings.privateTune(baseModel, null, trainingDataset, config);
            }
          } else {
            TuningOperation operation =
                tunings.privateTuneMldev(baseModel, null, trainingDataset, config);
            String tunedModelName = "";
            if (operation.metadata().isPresent()
                && operation.metadata().get().containsKey("tunedModel")) {
              tunedModelName = (String) operation.metadata().get().get("tunedModel");
            } else {
              if (!operation.name().isPresent()) {
                throw new IllegalArgumentException("Operation name is required.");
              }
              tunedModelName = operation.name().get().split("/operations/")[0];
            }
            return TuningJob.builder()
                .name(tunedModelName)
                .state(JobState.Known.JOB_STATE_QUEUED)
                .build();
          }
        });
  }
}
