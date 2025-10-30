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
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** Config for optional parameters. */
@AutoValue
@JsonDeserialize(builder = CreateBatchJobConfig.Builder.class)
public abstract class CreateBatchJobConfig extends JsonSerializable {
  /** Used to override HTTP request options. */
  @JsonProperty("httpOptions")
  public abstract Optional<HttpOptions> httpOptions();

  /** The user-defined name of this BatchJob. */
  @JsonProperty("displayName")
  public abstract Optional<String> displayName();

  /**
   * GCS or BigQuery URI prefix for the output predictions. Example: "gs://path/to/output/data" or
   * "bq://projectId.bqDatasetId.bqTableId".
   */
  @JsonProperty("dest")
  public abstract Optional<BatchJobDestination> dest();

  /** Instantiates a builder for CreateBatchJobConfig. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_CreateBatchJobConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for CreateBatchJobConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `CreateBatchJobConfig.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_CreateBatchJobConfig.Builder();
    }

    /**
     * Setter for httpOptions.
     *
     * <p>httpOptions: Used to override HTTP request options.
     */
    @JsonProperty("httpOptions")
    public abstract Builder httpOptions(HttpOptions httpOptions);

    /**
     * Setter for httpOptions builder.
     *
     * <p>httpOptions: Used to override HTTP request options.
     */
    public Builder httpOptions(HttpOptions.Builder httpOptionsBuilder) {
      return httpOptions(httpOptionsBuilder.build());
    }

    /**
     * Setter for displayName.
     *
     * <p>displayName: The user-defined name of this BatchJob.
     */
    @JsonProperty("displayName")
    public abstract Builder displayName(String displayName);

    /**
     * Setter for dest.
     *
     * <p>dest: GCS or BigQuery URI prefix for the output predictions. Example:
     * "gs://path/to/output/data" or "bq://projectId.bqDatasetId.bqTableId".
     */
    @JsonProperty("dest")
    public abstract Builder dest(BatchJobDestination dest);

    /**
     * Setter for dest builder.
     *
     * <p>dest: GCS or BigQuery URI prefix for the output predictions. Example:
     * "gs://path/to/output/data" or "bq://projectId.bqDatasetId.bqTableId".
     */
    public Builder dest(BatchJobDestination.Builder destBuilder) {
      return dest(destBuilder.build());
    }

    public abstract CreateBatchJobConfig build();
  }

  /** Deserializes a JSON string to a CreateBatchJobConfig object. */
  @ExcludeFromGeneratedCoverageReport
  public static CreateBatchJobConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, CreateBatchJobConfig.class);
  }
}
