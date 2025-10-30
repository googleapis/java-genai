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

/** Config for batches.delete parameters. */
@AutoValue
@InternalApi
@JsonDeserialize(builder = DeleteBatchJobParameters.Builder.class)
public abstract class DeleteBatchJobParameters extends JsonSerializable {
  /**
   * A fully-qualified BatchJob resource name or ID. Example:
   * "projects/.../locations/.../batchPredictionJobs/456" or "456" when project and location are
   * initialized in the client.
   */
  @JsonProperty("name")
  public abstract Optional<String> name();

  /** Optional parameters for the request. */
  @JsonProperty("config")
  public abstract Optional<DeleteBatchJobConfig> config();

  /** Instantiates a builder for DeleteBatchJobParameters. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_DeleteBatchJobParameters.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for DeleteBatchJobParameters. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `DeleteBatchJobParameters.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_DeleteBatchJobParameters.Builder();
    }

    /**
     * Setter for name.
     *
     * <p>name: A fully-qualified BatchJob resource name or ID. Example:
     * "projects/.../locations/.../batchPredictionJobs/456" or "456" when project and location are
     * initialized in the client.
     */
    @JsonProperty("name")
    public abstract Builder name(String name);

    /**
     * Setter for config.
     *
     * <p>config: Optional parameters for the request.
     */
    @JsonProperty("config")
    public abstract Builder config(DeleteBatchJobConfig config);

    /**
     * Setter for config builder.
     *
     * <p>config: Optional parameters for the request.
     */
    public Builder config(DeleteBatchJobConfig.Builder configBuilder) {
      return config(configBuilder.build());
    }

    public abstract DeleteBatchJobParameters build();
  }

  /** Deserializes a JSON string to a DeleteBatchJobParameters object. */
  @ExcludeFromGeneratedCoverageReport
  public static DeleteBatchJobParameters fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, DeleteBatchJobParameters.class);
  }
}
