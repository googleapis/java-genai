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

/** Fine-tuning job creation parameters - optional fields. */
@AutoValue
@InternalApi
@JsonDeserialize(builder = CreateTuningJobParametersPrivate.Builder.class)
public abstract class CreateTuningJobParametersPrivate extends JsonSerializable {
  /** The base model that is being tuned, e.g., "gemini-2.5-flash". */
  @JsonProperty("baseModel")
  public abstract Optional<String> baseModel();

  /** The PreTunedModel that is being tuned. */
  @JsonProperty("preTunedModel")
  public abstract Optional<PreTunedModel> preTunedModel();

  /**
   * Cloud Storage path to file containing training dataset for tuning. The dataset must be
   * formatted as a JSONL file.
   */
  @JsonProperty("trainingDataset")
  public abstract Optional<TuningDataset> trainingDataset();

  /** Configuration for the tuning job. */
  @JsonProperty("config")
  public abstract Optional<CreateTuningJobConfig> config();

  /** Instantiates a builder for CreateTuningJobParametersPrivate. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_CreateTuningJobParametersPrivate.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for CreateTuningJobParametersPrivate. */
  @AutoValue.Builder
  public abstract static class Builder {
    /**
     * For internal usage. Please use `CreateTuningJobParametersPrivate.builder()` for
     * instantiation.
     */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_CreateTuningJobParametersPrivate.Builder();
    }

    /**
     * Setter for baseModel.
     *
     * <p>baseModel: The base model that is being tuned, e.g., "gemini-2.5-flash".
     */
    @JsonProperty("baseModel")
    public abstract Builder baseModel(String baseModel);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder baseModel(Optional<String> baseModel);

    /** Clears the value of baseModel field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearBaseModel() {
      return baseModel(Optional.empty());
    }

    /**
     * Setter for preTunedModel.
     *
     * <p>preTunedModel: The PreTunedModel that is being tuned.
     */
    @JsonProperty("preTunedModel")
    public abstract Builder preTunedModel(PreTunedModel preTunedModel);

    /**
     * Setter for preTunedModel builder.
     *
     * <p>preTunedModel: The PreTunedModel that is being tuned.
     */
    @CanIgnoreReturnValue
    public Builder preTunedModel(PreTunedModel.Builder preTunedModelBuilder) {
      return preTunedModel(preTunedModelBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder preTunedModel(Optional<PreTunedModel> preTunedModel);

    /** Clears the value of preTunedModel field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearPreTunedModel() {
      return preTunedModel(Optional.empty());
    }

    /**
     * Setter for trainingDataset.
     *
     * <p>trainingDataset: Cloud Storage path to file containing training dataset for tuning. The
     * dataset must be formatted as a JSONL file.
     */
    @JsonProperty("trainingDataset")
    public abstract Builder trainingDataset(TuningDataset trainingDataset);

    /**
     * Setter for trainingDataset builder.
     *
     * <p>trainingDataset: Cloud Storage path to file containing training dataset for tuning. The
     * dataset must be formatted as a JSONL file.
     */
    @CanIgnoreReturnValue
    public Builder trainingDataset(TuningDataset.Builder trainingDatasetBuilder) {
      return trainingDataset(trainingDatasetBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder trainingDataset(Optional<TuningDataset> trainingDataset);

    /** Clears the value of trainingDataset field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTrainingDataset() {
      return trainingDataset(Optional.empty());
    }

    /**
     * Setter for config.
     *
     * <p>config: Configuration for the tuning job.
     */
    @JsonProperty("config")
    public abstract Builder config(CreateTuningJobConfig config);

    /**
     * Setter for config builder.
     *
     * <p>config: Configuration for the tuning job.
     */
    @CanIgnoreReturnValue
    public Builder config(CreateTuningJobConfig.Builder configBuilder) {
      return config(configBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder config(Optional<CreateTuningJobConfig> config);

    /** Clears the value of config field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearConfig() {
      return config(Optional.empty());
    }

    public abstract CreateTuningJobParametersPrivate build();
  }

  /** Deserializes a JSON string to a CreateTuningJobParametersPrivate object. */
  @ExcludeFromGeneratedCoverageReport
  public static CreateTuningJobParametersPrivate fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, CreateTuningJobParametersPrivate.class);
  }
}
