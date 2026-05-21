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

/** Hyperparameters for Veo. This data type is not supported in Gemini API. */
@AutoValue
@InternalApi
@JsonDeserialize(builder = VeoHyperParameters.Builder.class)
public abstract class VeoHyperParameters extends JsonSerializable {
  /**
   * Optional. Number of complete passes the model makes over the entire training dataset during
   * training.
   */
  @JsonProperty("epochCount")
  public abstract Optional<Long> epochCount();

  /** Optional. Multiplier for adjusting the default learning rate. */
  @JsonProperty("learningRateMultiplier")
  public abstract Optional<Double> learningRateMultiplier();

  /** The tuning task for Veo. */
  @JsonProperty("tuningTask")
  public abstract Optional<TuningTask> tuningTask();

  /**
   * Optional. The ratio of Google internal dataset to use in the training mixture, in range of `[0,
   * 1)`. If `0.2`, it means 20% of Google internal dataset and 80% of user dataset will be used for
   * training. If not set, the default value is 0.1.
   */
  @JsonProperty("veoDataMixtureRatio")
  public abstract Optional<Double> veoDataMixtureRatio();

  /** Optional. The adapter size for LoRA tuning. */
  @JsonProperty("adapterSize")
  public abstract Optional<AdapterSize> adapterSize();

  /** The speed of the tuning job. Only supported for Veo 3.0 models. */
  @JsonProperty("tuningSpeed")
  public abstract Optional<TuningSpeed> tuningSpeed();

  /** Instantiates a builder for VeoHyperParameters. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_VeoHyperParameters.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for VeoHyperParameters. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `VeoHyperParameters.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_VeoHyperParameters.Builder();
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
     * <p>tuningTask: The tuning task for Veo.
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
     * <p>tuningTask: The tuning task for Veo.
     */
    @CanIgnoreReturnValue
    public Builder tuningTask(TuningTask.Known knownType) {
      return tuningTask(new TuningTask(knownType));
    }

    /**
     * Setter for tuningTask given a string.
     *
     * <p>tuningTask: The tuning task for Veo.
     */
    @CanIgnoreReturnValue
    public Builder tuningTask(String tuningTask) {
      return tuningTask(new TuningTask(tuningTask));
    }

    /**
     * Setter for veoDataMixtureRatio.
     *
     * <p>veoDataMixtureRatio: Optional. The ratio of Google internal dataset to use in the training
     * mixture, in range of `[0, 1)`. If `0.2`, it means 20% of Google internal dataset and 80% of
     * user dataset will be used for training. If not set, the default value is 0.1.
     */
    @JsonProperty("veoDataMixtureRatio")
    public abstract Builder veoDataMixtureRatio(Double veoDataMixtureRatio);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder veoDataMixtureRatio(Optional<Double> veoDataMixtureRatio);

    /** Clears the value of veoDataMixtureRatio field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearVeoDataMixtureRatio() {
      return veoDataMixtureRatio(Optional.empty());
    }

    /**
     * Setter for adapterSize.
     *
     * <p>adapterSize: Optional. The adapter size for LoRA tuning.
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
     * <p>adapterSize: Optional. The adapter size for LoRA tuning.
     */
    @CanIgnoreReturnValue
    public Builder adapterSize(AdapterSize.Known knownType) {
      return adapterSize(new AdapterSize(knownType));
    }

    /**
     * Setter for adapterSize given a string.
     *
     * <p>adapterSize: Optional. The adapter size for LoRA tuning.
     */
    @CanIgnoreReturnValue
    public Builder adapterSize(String adapterSize) {
      return adapterSize(new AdapterSize(adapterSize));
    }

    /**
     * Setter for tuningSpeed.
     *
     * <p>tuningSpeed: The speed of the tuning job. Only supported for Veo 3.0 models.
     */
    @JsonProperty("tuningSpeed")
    public abstract Builder tuningSpeed(TuningSpeed tuningSpeed);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder tuningSpeed(Optional<TuningSpeed> tuningSpeed);

    /** Clears the value of tuningSpeed field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTuningSpeed() {
      return tuningSpeed(Optional.empty());
    }

    /**
     * Setter for tuningSpeed given a known enum.
     *
     * <p>tuningSpeed: The speed of the tuning job. Only supported for Veo 3.0 models.
     */
    @CanIgnoreReturnValue
    public Builder tuningSpeed(TuningSpeed.Known knownType) {
      return tuningSpeed(new TuningSpeed(knownType));
    }

    /**
     * Setter for tuningSpeed given a string.
     *
     * <p>tuningSpeed: The speed of the tuning job. Only supported for Veo 3.0 models.
     */
    @CanIgnoreReturnValue
    public Builder tuningSpeed(String tuningSpeed) {
      return tuningSpeed(new TuningSpeed(tuningSpeed));
    }

    public abstract VeoHyperParameters build();
  }

  /** Deserializes a JSON string to a VeoHyperParameters object. */
  @ExcludeFromGeneratedCoverageReport
  public static VeoHyperParameters fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, VeoHyperParameters.class);
  }
}
