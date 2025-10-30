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

/** Hyperparameters for SFT. This data type is not supported in Gemini API. */
@AutoValue
@InternalApi
@JsonDeserialize(builder = SupervisedHyperParameters.Builder.class)
public abstract class SupervisedHyperParameters extends JsonSerializable {
  /** Optional. Adapter size for tuning. */
  @JsonProperty("adapterSize")
  public abstract Optional<AdapterSize> adapterSize();

  /** Optional. Batch size for tuning. This feature is only available for open source models. */
  @JsonProperty("batchSize")
  public abstract Optional<Long> batchSize();

  /**
   * Optional. Number of complete passes the model makes over the entire training dataset during
   * training.
   */
  @JsonProperty("epochCount")
  public abstract Optional<Long> epochCount();

  /**
   * Optional. Learning rate for tuning. Mutually exclusive with `learning_rate_multiplier`. This
   * feature is only available for open source models.
   */
  @JsonProperty("learningRate")
  public abstract Optional<Double> learningRate();

  /**
   * Optional. Multiplier for adjusting the default learning rate. Mutually exclusive with
   * `learning_rate`. This feature is only available for 1P models.
   */
  @JsonProperty("learningRateMultiplier")
  public abstract Optional<Double> learningRateMultiplier();

  /** Instantiates a builder for SupervisedHyperParameters. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_SupervisedHyperParameters.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for SupervisedHyperParameters. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `SupervisedHyperParameters.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_SupervisedHyperParameters.Builder();
    }

    /**
     * Setter for adapterSize.
     *
     * <p>adapterSize: Optional. Adapter size for tuning.
     */
    @JsonProperty("adapterSize")
    public abstract Builder adapterSize(AdapterSize adapterSize);

    /**
     * Setter for adapterSize given a known enum.
     *
     * <p>adapterSize: Optional. Adapter size for tuning.
     */
    @CanIgnoreReturnValue
    public Builder adapterSize(AdapterSize.Known knownType) {
      return adapterSize(new AdapterSize(knownType));
    }

    /**
     * Setter for adapterSize given a string.
     *
     * <p>adapterSize: Optional. Adapter size for tuning.
     */
    @CanIgnoreReturnValue
    public Builder adapterSize(String adapterSize) {
      return adapterSize(new AdapterSize(adapterSize));
    }

    /**
     * Setter for batchSize.
     *
     * <p>batchSize: Optional. Batch size for tuning. This feature is only available for open source
     * models.
     */
    @JsonProperty("batchSize")
    public abstract Builder batchSize(Long batchSize);

    /**
     * Setter for epochCount.
     *
     * <p>epochCount: Optional. Number of complete passes the model makes over the entire training
     * dataset during training.
     */
    @JsonProperty("epochCount")
    public abstract Builder epochCount(Long epochCount);

    /**
     * Setter for learningRate.
     *
     * <p>learningRate: Optional. Learning rate for tuning. Mutually exclusive with
     * `learning_rate_multiplier`. This feature is only available for open source models.
     */
    @JsonProperty("learningRate")
    public abstract Builder learningRate(Double learningRate);

    /**
     * Setter for learningRateMultiplier.
     *
     * <p>learningRateMultiplier: Optional. Multiplier for adjusting the default learning rate.
     * Mutually exclusive with `learning_rate`. This feature is only available for 1P models.
     */
    @JsonProperty("learningRateMultiplier")
    public abstract Builder learningRateMultiplier(Double learningRateMultiplier);

    public abstract SupervisedHyperParameters build();
  }

  /** Deserializes a JSON string to a SupervisedHyperParameters object. */
  @ExcludeFromGeneratedCoverageReport
  public static SupervisedHyperParameters fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, SupervisedHyperParameters.class);
  }
}
