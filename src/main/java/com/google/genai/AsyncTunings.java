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
import com.google.genai.types.CancelTuningJobConfig;
import com.google.genai.types.CancelTuningJobParameters;
import com.google.genai.types.CancelTuningJobResponse;
import com.google.genai.types.CreateTuningJobConfig;
import com.google.genai.types.CreateTuningJobParametersPrivate;
import com.google.genai.types.GetTuningJobConfig;
import com.google.genai.types.GetTuningJobParameters;
import com.google.genai.types.JobState;
import com.google.genai.types.ListTuningJobsConfig;
import com.google.genai.types.ListTuningJobsParameters;
import com.google.genai.types.ListTuningJobsResponse;
import com.google.genai.types.PreTunedModel;
import com.google.genai.types.TuningDataset;
import com.google.genai.types.TuningJob;
import com.google.genai.types.TuningOperation;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/** Async module of {@link Tunings} */
public final class AsyncTunings {

  Tunings tunings;
  ApiClient apiClient;

  public AsyncTunings(ApiClient apiClient) {
    this.apiClient = apiClient;
    this.tunings = new Tunings(apiClient);
  }

  CompletableFuture<TuningJob> privateGet(String name, GetTuningJobConfig config) {
    GetTuningJobParameters.Builder parameterBuilder = GetTuningJobParameters.builder();

    if (!Common.isZero(name)) {
      parameterBuilder.name(name);
    }
    if (!Common.isZero(config)) {
      parameterBuilder.config(config);
    }
    JsonNode parameterNode = JsonSerializable.toJsonNode(parameterBuilder.build());
    BuiltRequest builtRequest = tunings.buildRequestForPrivateGet(name, config);
    return this.apiClient
        .asyncRequest("get", builtRequest.path(), builtRequest.body(), builtRequest.httpOptions())
        .thenApplyAsync(
            response -> {
              try (ApiResponse res = response) {
                return tunings.processResponseForPrivateGet(res, config, parameterNode);
              }
            });
  }

  CompletableFuture<ListTuningJobsResponse> privateList(ListTuningJobsConfig config) {
    ListTuningJobsParameters.Builder parameterBuilder = ListTuningJobsParameters.builder();

    if (!Common.isZero(config)) {
      parameterBuilder.config(config);
    }
    JsonNode parameterNode = JsonSerializable.toJsonNode(parameterBuilder.build());
    BuiltRequest builtRequest = tunings.buildRequestForPrivateList(config);
    return this.apiClient
        .asyncRequest("get", builtRequest.path(), builtRequest.body(), builtRequest.httpOptions())
        .thenApplyAsync(
            response -> {
              try (ApiResponse res = response) {
                return tunings.processResponseForPrivateList(res, config, parameterNode);
              }
            });
  }

  /**
   * Asynchronously cancels a tuning job resource.
   *
   * @param name The resource name of the tuning job. For Vertex, this is the full resource name.
   *     For Gemini API, this is `tunedModels/{id}`.
   * @param config A {@link CancelTuningJobConfig} for configuring the cancel request.
   */
  public CompletableFuture<CancelTuningJobResponse> cancel(
      String name, CancelTuningJobConfig config) {
    CancelTuningJobParameters.Builder parameterBuilder = CancelTuningJobParameters.builder();

    if (!Common.isZero(name)) {
      parameterBuilder.name(name);
    }
    if (!Common.isZero(config)) {
      parameterBuilder.config(config);
    }
    JsonNode parameterNode = JsonSerializable.toJsonNode(parameterBuilder.build());
    BuiltRequest builtRequest = tunings.buildRequestForCancel(name, config);
    return this.apiClient
        .asyncRequest("post", builtRequest.path(), builtRequest.body(), builtRequest.httpOptions())
        .thenApplyAsync(
            response -> {
              try (ApiResponse res = response) {
                return tunings.processResponseForCancel(res, config, parameterNode);
              }
            });
  }

  CompletableFuture<TuningJob> privateTune(
      String baseModel,
      PreTunedModel preTunedModel,
      TuningDataset trainingDataset,
      CreateTuningJobConfig config) {
    CreateTuningJobParametersPrivate.Builder parameterBuilder =
        CreateTuningJobParametersPrivate.builder();

    if (!Common.isZero(baseModel)) {
      parameterBuilder.baseModel(baseModel);
    }
    if (!Common.isZero(preTunedModel)) {
      parameterBuilder.preTunedModel(preTunedModel);
    }
    if (!Common.isZero(trainingDataset)) {
      parameterBuilder.trainingDataset(trainingDataset);
    }
    if (!Common.isZero(config)) {
      parameterBuilder.config(config);
    }
    JsonNode parameterNode = JsonSerializable.toJsonNode(parameterBuilder.build());
    BuiltRequest builtRequest =
        tunings.buildRequestForPrivateTune(baseModel, preTunedModel, trainingDataset, config);
    return this.apiClient
        .asyncRequest("post", builtRequest.path(), builtRequest.body(), builtRequest.httpOptions())
        .thenApplyAsync(
            response -> {
              try (ApiResponse res = response) {
                return tunings.processResponseForPrivateTune(res, config, parameterNode);
              }
            });
  }

  CompletableFuture<TuningOperation> privateTuneMldev(
      String baseModel,
      PreTunedModel preTunedModel,
      TuningDataset trainingDataset,
      CreateTuningJobConfig config) {
    CreateTuningJobParametersPrivate.Builder parameterBuilder =
        CreateTuningJobParametersPrivate.builder();

    if (!Common.isZero(baseModel)) {
      parameterBuilder.baseModel(baseModel);
    }
    if (!Common.isZero(preTunedModel)) {
      parameterBuilder.preTunedModel(preTunedModel);
    }
    if (!Common.isZero(trainingDataset)) {
      parameterBuilder.trainingDataset(trainingDataset);
    }
    if (!Common.isZero(config)) {
      parameterBuilder.config(config);
    }
    JsonNode parameterNode = JsonSerializable.toJsonNode(parameterBuilder.build());
    BuiltRequest builtRequest =
        tunings.buildRequestForPrivateTuneMldev(baseModel, preTunedModel, trainingDataset, config);
    return this.apiClient
        .asyncRequest("post", builtRequest.path(), builtRequest.body(), builtRequest.httpOptions())
        .thenApplyAsync(
            response -> {
              try (ApiResponse res = response) {
                return tunings.processResponseForPrivateTuneMldev(res, config, parameterNode);
              }
            });
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
    if (config == null) {
      config = ListTuningJobsConfig.builder().build();
    }
    ListTuningJobsConfig finalConfig = config;
    Function<JsonSerializable, CompletableFuture<JsonNode>> request =
        requestConfig -> {
          if (!(requestConfig instanceof ListTuningJobsConfig)) {
            throw new GenAiIOException(
                "Internal error: Pager expected ListTuningJobsConfig but received "
                    + requestConfig.getClass().getName());
          }
          return this.privateList((ListTuningJobsConfig) requestConfig)
              .thenApply(JsonSerializable::toJsonNode);
        };
    return CompletableFuture.supplyAsync(
        () ->
            new AsyncPager<TuningJob>(
                Pager.PagedItem.TUNING_JOBS,
                request,
                (ObjectNode) JsonSerializable.toJsonNode(finalConfig),
                request.apply(finalConfig)));
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

    if (tunings.apiClient.vertexAI()) {
      CompletableFuture<TuningJob> tuningJobFuture;
      if (baseModel.startsWith("projects/")) {
        PreTunedModel.Builder preTunedModelBuilder =
            PreTunedModel.builder().tunedModelName(baseModel);
        if (config != null && config.preTunedModelCheckpointId().isPresent()) {
          preTunedModelBuilder.checkpointId(config.preTunedModelCheckpointId().get());
        }
        tuningJobFuture =
            this.privateTune(null, preTunedModelBuilder.build(), trainingDataset, config);
      } else {
        tuningJobFuture = this.privateTune(baseModel, null, trainingDataset, config);
      }
      if (config != null && config.evaluationConfig().isPresent()) {
        return tuningJobFuture.thenApply(
            tuningJob ->
                tuningJob.toBuilder().evaluationConfig(config.evaluationConfig().get()).build());
      }
      return tuningJobFuture;
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
      return CompletableFuture.completedFuture(
          TuningJob.builder().name(tunedModelName).state(JobState.Known.JOB_STATE_QUEUED).build());
    }
  }
}
