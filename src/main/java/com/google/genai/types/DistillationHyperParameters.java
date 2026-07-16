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

/** Distillation hyperparameters for tuning. */
@AutoValue
@InternalApi
@JsonDeserialize(builder = DistillationHyperParameters.Builder.class)
public abstract class DistillationHyperParameters extends JsonSerializable {
  /** Number of complete passes the model makes over the entire training dataset during training. */
  @JsonProperty("epochCount")
  public abstract Optional<Long> epochCount();

  /** Multiplier for adjusting the default learning rate. */
  @JsonProperty("learningRateMultiplier")
  public abstract Optional<Float> learningRateMultiplier();

  /** The size of the adapter. Can be 'small', 'medium', or 'large'. */
  @JsonProperty("adapterSize")
  public abstract Optional<AdapterSize> adapterSize();

  /** Batch size for tuning. This feature is only available for open source models. */
  @JsonProperty("batchSize")
  public abstract Optional<Integer> batchSize();

  /** The learning rate for distillation tuning. */
  @JsonProperty("learningRate")
  public abstract Optional<Float> learningRate();

  /**
   * Generation config for Distillation teacher model sampling. Only the following fields are
   * supported for distillation teacher samplings: - temperature - top_p - top_k - candidate_count -
   * thinking_config
   */
  @JsonProperty("generationConfig")
  public abstract Optional<GenerationConfig> generationConfig();

  /** Instantiates a builder for DistillationHyperParameters. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_DistillationHyperParameters.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for DistillationHyperParameters. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `DistillationHyperParameters.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_DistillationHyperParameters.Builder();
    }

    /**
     * Setter for epochCount.
     *
     * <p>epochCount: Number of complete passes the model makes over the entire training dataset
     * during training.
     */
    @JsonProperty("epochCount")
    public abstract Builder epochCount(Long epochCount);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder epochCount(Optional<Long> epochCount);

    /** Clears the value of epochCount field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearEpochCount() {
      return epochCount(Optional.empty());
    }

    /**
     * Setter for learningRateMultiplier.
     *
     * <p>learningRateMultiplier: Multiplier for adjusting the default learning rate.
     */
    @JsonProperty("learningRateMultiplier")
    public abstract Builder learningRateMultiplier(Float learningRateMultiplier);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder learningRateMultiplier(Optional<Float> learningRateMultiplier);

    /** Clears the value of learningRateMultiplier field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearLearningRateMultiplier() {
      return learningRateMultiplier(Optional.empty());
    }

    /**
     * Setter for adapterSize.
     *
     * <p>adapterSize: The size of the adapter. Can be 'small', 'medium', or 'large'.
     */
    @JsonProperty("adapterSize")
    public abstract Builder adapterSize(AdapterSize adapterSize);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder adapterSize(Optional<AdapterSize> adapterSize);

    /** Clears the value of adapterSize field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearAdapterSize() {
      return adapterSize(Optional.empty());
    }

    /**
     * Setter for adapterSize given a known enum.
     *
     * <p>adapterSize: The size of the adapter. Can be 'small', 'medium', or 'large'.
     */
    @CanIgnoreReturnValue
    public Builder adapterSize(AdapterSize.Known knownType) {
      return adapterSize(new AdapterSize(knownType));
    }

    /**
     * Setter for adapterSize given a string.
     *
     * <p>adapterSize: The size of the adapter. Can be 'small', 'medium', or 'large'.
     */
    @CanIgnoreReturnValue
    public Builder adapterSize(String adapterSize) {
      return adapterSize(new AdapterSize(adapterSize));
    }

    /**
     * Setter for batchSize.
     *
     * <p>batchSize: Batch size for tuning. This feature is only available for open source models.
     */
    @JsonProperty("batchSize")
    public abstract Builder batchSize(Integer batchSize);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder batchSize(Optional<Integer> batchSize);

    /** Clears the value of batchSize field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearBatchSize() {
      return batchSize(Optional.empty());
    }

    /**
     * Setter for learningRate.
     *
     * <p>learningRate: The learning rate for distillation tuning.
     */
    @JsonProperty("learningRate")
    public abstract Builder learningRate(Float learningRate);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder learningRate(Optional<Float> learningRate);

    /** Clears the value of learningRate field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearLearningRate() {
      return learningRate(Optional.empty());
    }

    /**
     * Setter for generationConfig.
     *
     * <p>generationConfig: Generation config for Distillation teacher model sampling. Only the
     * following fields are supported for distillation teacher samplings: - temperature - top_p -
     * top_k - candidate_count - thinking_config
     */
    @JsonProperty("generationConfig")
    public abstract Builder generationConfig(GenerationConfig generationConfig);

    /**
     * Setter for generationConfig builder.
     *
     * <p>generationConfig: Generation config for Distillation teacher model sampling. Only the
     * following fields are supported for distillation teacher samplings: - temperature - top_p -
     * top_k - candidate_count - thinking_config
     */
    @CanIgnoreReturnValue
    public Builder generationConfig(GenerationConfig.Builder generationConfigBuilder) {
      return generationConfig(generationConfigBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder generationConfig(Optional<GenerationConfig> generationConfig);

    /** Clears the value of generationConfig field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearGenerationConfig() {
      return generationConfig(Optional.empty());
    }

    public abstract DistillationHyperParameters build();
  }

  /** Deserializes a JSON string to a DistillationHyperParameters object. */
  @ExcludeFromGeneratedCoverageReport
  public static DistillationHyperParameters fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, DistillationHyperParameters.class);
  }
}
