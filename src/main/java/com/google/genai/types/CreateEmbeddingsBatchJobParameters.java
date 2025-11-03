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
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** Config for batches.create parameters. */
@AutoValue
@InternalApi
@JsonDeserialize(builder = CreateEmbeddingsBatchJobParameters.Builder.class)
public abstract class CreateEmbeddingsBatchJobParameters extends JsonSerializable {
  /** The name of the model to produces the predictions via the BatchJob. */
  @JsonProperty("model")
  public abstract Optional<String> model();

  /** input data to run batch job". */
  @JsonProperty("src")
  public abstract Optional<EmbeddingsBatchJobSource> src();

  /** Optional parameters for creating a BatchJob. */
  @JsonProperty("config")
  public abstract Optional<CreateEmbeddingsBatchJobConfig> config();

  /** Instantiates a builder for CreateEmbeddingsBatchJobParameters. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_CreateEmbeddingsBatchJobParameters.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for CreateEmbeddingsBatchJobParameters. */
  @AutoValue.Builder
  public abstract static class Builder {
    /**
     * For internal usage. Please use `CreateEmbeddingsBatchJobParameters.builder()` for
     * instantiation.
     */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_CreateEmbeddingsBatchJobParameters.Builder();
    }

    /**
     * Setter for model.
     *
     * <p>model: The name of the model to produces the predictions via the BatchJob.
     */
    @JsonProperty("model")
    public abstract Builder model(String model);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder model(Optional<String> model);

    /** Clears the value of model field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearModel() {
      return model(Optional.empty());
    }

    /**
     * Setter for src.
     *
     * <p>src: input data to run batch job".
     */
    @JsonProperty("src")
    public abstract Builder src(EmbeddingsBatchJobSource src);

    /**
     * Setter for src builder.
     *
     * <p>src: input data to run batch job".
     */
    @CanIgnoreReturnValue
    public Builder src(EmbeddingsBatchJobSource.Builder srcBuilder) {
      return src(srcBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder src(Optional<EmbeddingsBatchJobSource> src);

    /** Clears the value of src field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearSrc() {
      return src(Optional.empty());
    }

    /**
     * Setter for config.
     *
     * <p>config: Optional parameters for creating a BatchJob.
     */
    @JsonProperty("config")
    public abstract Builder config(CreateEmbeddingsBatchJobConfig config);

    /**
     * Setter for config builder.
     *
     * <p>config: Optional parameters for creating a BatchJob.
     */
    @CanIgnoreReturnValue
    public Builder config(CreateEmbeddingsBatchJobConfig.Builder configBuilder) {
      return config(configBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder config(Optional<CreateEmbeddingsBatchJobConfig> config);

    /** Clears the value of config field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearConfig() {
      return config(Optional.empty());
    }

    public abstract CreateEmbeddingsBatchJobParameters build();
  }

  /** Deserializes a JSON string to a CreateEmbeddingsBatchJobParameters object. */
  @ExcludeFromGeneratedCoverageReport
  public static CreateEmbeddingsBatchJobParameters fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, CreateEmbeddingsBatchJobParameters.class);
  }
}
