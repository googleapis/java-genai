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

/** Hyperparameters for Distillation. */
@AutoValue
@InternalApi
@JsonDeserialize(builder = DistillationHyperParameters.Builder.class)
public abstract class DistillationHyperParameters extends JsonSerializable {
  /** Optional. Adapter size for distillation. */
  @JsonProperty("adapterSize")
  public abstract Optional<AdapterSize> adapterSize();

  /**
   * Optional. Number of complete passes the model makes over the entire training dataset during
   * training.
   */
  @JsonProperty("epochCount")
  public abstract Optional<Long> epochCount();

  /** Optional. Multiplier for adjusting the default learning rate. */
  @JsonProperty("learningRateMultiplier")
  public abstract Optional<Double> learningRateMultiplier();

  /** Instantiates a builder for DistillationHyperParameters. */
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
     * Setter for adapterSize.
     *
     * <p>adapterSize: Optional. Adapter size for distillation.
     */
    @JsonProperty("adapterSize")
    public abstract Builder adapterSize(AdapterSize adapterSize);

    /**
     * Setter for adapterSize given a known enum.
     *
     * <p>adapterSize: Optional. Adapter size for distillation.
     */
    @CanIgnoreReturnValue
    public Builder adapterSize(AdapterSize.Known knownType) {
      return adapterSize(new AdapterSize(knownType));
    }

    /**
     * Setter for adapterSize given a string.
     *
     * <p>adapterSize: Optional. Adapter size for distillation.
     */
    @CanIgnoreReturnValue
    public Builder adapterSize(String adapterSize) {
      return adapterSize(new AdapterSize(adapterSize));
    }

    /**
     * Setter for epochCount.
     *
     * <p>epochCount: Optional. Number of complete passes the model makes over the entire training
     * dataset during training.
     */
    @JsonProperty("epochCount")
    public abstract Builder epochCount(Long epochCount);

    /**
     * Setter for learningRateMultiplier.
     *
     * <p>learningRateMultiplier: Optional. Multiplier for adjusting the default learning rate.
     */
    @JsonProperty("learningRateMultiplier")
    public abstract Builder learningRateMultiplier(Double learningRateMultiplier);

    public abstract DistillationHyperParameters build();
  }

  /** Deserializes a JSON string to a DistillationHyperParameters object. */
  public static DistillationHyperParameters fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, DistillationHyperParameters.class);
  }
}
