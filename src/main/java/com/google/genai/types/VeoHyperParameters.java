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

  /** Optional. The tuning task. Either I2V or T2V. */
  @JsonProperty("tuningTask")
  public abstract Optional<TuningTask> tuningTask();

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

    /**
     * Setter for learningRateMultiplier.
     *
     * <p>learningRateMultiplier: Optional. Multiplier for adjusting the default learning rate.
     */
    @JsonProperty("learningRateMultiplier")
    public abstract Builder learningRateMultiplier(Double learningRateMultiplier);

    /**
     * Setter for tuningTask.
     *
     * <p>tuningTask: Optional. The tuning task. Either I2V or T2V.
     */
    @JsonProperty("tuningTask")
    public abstract Builder tuningTask(TuningTask tuningTask);

    /**
     * Setter for tuningTask given a known enum.
     *
     * <p>tuningTask: Optional. The tuning task. Either I2V or T2V.
     */
    @CanIgnoreReturnValue
    public Builder tuningTask(TuningTask.Known knownType) {
      return tuningTask(new TuningTask(knownType));
    }

    /**
     * Setter for tuningTask given a string.
     *
     * <p>tuningTask: Optional. The tuning task. Either I2V or T2V.
     */
    @CanIgnoreReturnValue
    public Builder tuningTask(String tuningTask) {
      return tuningTask(new TuningTask(tuningTask));
    }

    public abstract VeoHyperParameters build();
  }

  /** Deserializes a JSON string to a VeoHyperParameters object. */
  @ExcludeFromGeneratedCoverageReport
  public static VeoHyperParameters fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, VeoHyperParameters.class);
  }
}
