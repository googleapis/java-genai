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

/** Hyperparameters for Omni LoRA tuning. This data type is not supported in Gemini API. */
@AutoValue
@InternalApi
@JsonDeserialize(builder = OmniHyperParameters.Builder.class)
public abstract class OmniHyperParameters extends JsonSerializable {
  /** Optional. Adapter size for Omni LoRA tuning. */
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

  /** Optional. The tuning task for Omni LoRA tuning. */
  @JsonProperty("tuningTask")
  public abstract Optional<TuningTask> tuningTask();

  /** Instantiates a builder for OmniHyperParameters. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_OmniHyperParameters.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for OmniHyperParameters. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `OmniHyperParameters.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_OmniHyperParameters.Builder();
    }

    /**
     * Setter for adapterSize.
     *
     * <p>adapterSize: Optional. Adapter size for Omni LoRA tuning.
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
     * <p>adapterSize: Optional. Adapter size for Omni LoRA tuning.
     */
    @CanIgnoreReturnValue
    public Builder adapterSize(AdapterSize.Known knownType) {
      return adapterSize(new AdapterSize(knownType));
    }

    /**
     * Setter for adapterSize given a string.
     *
     * <p>adapterSize: Optional. Adapter size for Omni LoRA tuning.
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
     * <p>learningRateMultiplier: Optional. Multiplier for adjusting the default learning rate.
     */
    @JsonProperty("learningRateMultiplier")
    public abstract Builder learningRateMultiplier(Double learningRateMultiplier);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder learningRateMultiplier(Optional<Double> learningRateMultiplier);

    /** Clears the value of learningRateMultiplier field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearLearningRateMultiplier() {
      return learningRateMultiplier(Optional.empty());
    }

    /**
     * Setter for tuningTask.
     *
     * <p>tuningTask: Optional. The tuning task for Omni LoRA tuning.
     */
    @JsonProperty("tuningTask")
    public abstract Builder tuningTask(TuningTask tuningTask);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder tuningTask(Optional<TuningTask> tuningTask);

    /** Clears the value of tuningTask field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTuningTask() {
      return tuningTask(Optional.empty());
    }

    /**
     * Setter for tuningTask given a known enum.
     *
     * <p>tuningTask: Optional. The tuning task for Omni LoRA tuning.
     */
    @CanIgnoreReturnValue
    public Builder tuningTask(TuningTask.Known knownType) {
      return tuningTask(new TuningTask(knownType));
    }

    /**
     * Setter for tuningTask given a string.
     *
     * <p>tuningTask: Optional. The tuning task for Omni LoRA tuning.
     */
    @CanIgnoreReturnValue
    public Builder tuningTask(String tuningTask) {
      return tuningTask(new TuningTask(tuningTask));
    }

    public abstract OmniHyperParameters build();
  }

  /** Deserializes a JSON string to a OmniHyperParameters object. */
  @ExcludeFromGeneratedCoverageReport
  public static OmniHyperParameters fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, OmniHyperParameters.class);
  }
}
