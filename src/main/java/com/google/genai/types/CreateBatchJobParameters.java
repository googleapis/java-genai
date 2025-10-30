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

package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.api.core.InternalApi;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** Config for batches.create parameters. */
@AutoValue
@InternalApi
@JsonDeserialize(builder = CreateBatchJobParameters.Builder.class)
public abstract class CreateBatchJobParameters extends JsonSerializable {
  /** The name of the model to produces the predictions via the BatchJob. */
  @JsonProperty("model")
  public abstract Optional<String> model();

  /**
   * GCS URI(-s) or BigQuery URI to your input data to run batch job. Example:
   * "gs://path/to/input/data" or "bq://projectId.bqDatasetId.bqTableId".
   */
  @JsonProperty("src")
  public abstract Optional<BatchJobSource> src();

  /** Optional parameters for creating a BatchJob. */
  @JsonProperty("config")
  public abstract Optional<CreateBatchJobConfig> config();

  /** Instantiates a builder for CreateBatchJobParameters. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_CreateBatchJobParameters.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for CreateBatchJobParameters. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `CreateBatchJobParameters.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_CreateBatchJobParameters.Builder();
    }

    /**
     * Setter for model.
     *
     * <p>model: The name of the model to produces the predictions via the BatchJob.
     */
    @JsonProperty("model")
    public abstract Builder model(String model);

    /**
     * Setter for src.
     *
     * <p>src: GCS URI(-s) or BigQuery URI to your input data to run batch job. Example:
     * "gs://path/to/input/data" or "bq://projectId.bqDatasetId.bqTableId".
     */
    @JsonProperty("src")
    public abstract Builder src(BatchJobSource src);

    /**
     * Setter for src builder.
     *
     * <p>src: GCS URI(-s) or BigQuery URI to your input data to run batch job. Example:
     * "gs://path/to/input/data" or "bq://projectId.bqDatasetId.bqTableId".
     */
    public Builder src(BatchJobSource.Builder srcBuilder) {
      return src(srcBuilder.build());
    }

    /**
     * Setter for config.
     *
     * <p>config: Optional parameters for creating a BatchJob.
     */
    @JsonProperty("config")
    public abstract Builder config(CreateBatchJobConfig config);

    /**
     * Setter for config builder.
     *
     * <p>config: Optional parameters for creating a BatchJob.
     */
    public Builder config(CreateBatchJobConfig.Builder configBuilder) {
      return config(configBuilder.build());
    }

    public abstract CreateBatchJobParameters build();
  }

  /** Deserializes a JSON string to a CreateBatchJobParameters object. */
  @ExcludeFromGeneratedCoverageReport
  public static CreateBatchJobParameters fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, CreateBatchJobParameters.class);
  }
}
