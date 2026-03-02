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

import static com.google.common.collect.ImmutableList.toImmutableList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/** Evaluation config for tuning. */
@AutoValue
@JsonDeserialize(builder = EvaluationConfig.Builder.class)
public abstract class EvaluationConfig extends JsonSerializable {
  /** The metrics used for evaluation. */
  @JsonProperty("metrics")
  public abstract Optional<List<UnifiedMetric>> metrics();

  /** Config for evaluation output. */
  @JsonProperty("outputConfig")
  public abstract Optional<OutputConfig> outputConfig();

  /** Autorater config for evaluation. */
  @JsonProperty("autoraterConfig")
  public abstract Optional<AutoraterConfig> autoraterConfig();

  /** Instantiates a builder for EvaluationConfig. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_EvaluationConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for EvaluationConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `EvaluationConfig.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_EvaluationConfig.Builder();
    }

    /**
     * Setter for metrics.
     *
     * <p>metrics: The metrics used for evaluation.
     */
    @JsonProperty("metrics")
    public abstract Builder metrics(List<UnifiedMetric> metrics);

    /**
     * Setter for metrics.
     *
     * <p>metrics: The metrics used for evaluation.
     */
    @CanIgnoreReturnValue
    public Builder metrics(UnifiedMetric... metrics) {
      return metrics(Arrays.asList(metrics));
    }

    /**
     * Setter for metrics builder.
     *
     * <p>metrics: The metrics used for evaluation.
     */
    @CanIgnoreReturnValue
    public Builder metrics(UnifiedMetric.Builder... metricsBuilders) {
      return metrics(
          Arrays.asList(metricsBuilders).stream()
              .map(UnifiedMetric.Builder::build)
              .collect(toImmutableList()));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder metrics(Optional<List<UnifiedMetric>> metrics);

    /** Clears the value of metrics field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearMetrics() {
      return metrics(Optional.empty());
    }

    /**
     * Setter for outputConfig.
     *
     * <p>outputConfig: Config for evaluation output.
     */
    @JsonProperty("outputConfig")
    public abstract Builder outputConfig(OutputConfig outputConfig);

    /**
     * Setter for outputConfig builder.
     *
     * <p>outputConfig: Config for evaluation output.
     */
    @CanIgnoreReturnValue
    public Builder outputConfig(OutputConfig.Builder outputConfigBuilder) {
      return outputConfig(outputConfigBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder outputConfig(Optional<OutputConfig> outputConfig);

    /** Clears the value of outputConfig field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearOutputConfig() {
      return outputConfig(Optional.empty());
    }

    /**
     * Setter for autoraterConfig.
     *
     * <p>autoraterConfig: Autorater config for evaluation.
     */
    @JsonProperty("autoraterConfig")
    public abstract Builder autoraterConfig(AutoraterConfig autoraterConfig);

    /**
     * Setter for autoraterConfig builder.
     *
     * <p>autoraterConfig: Autorater config for evaluation.
     */
    @CanIgnoreReturnValue
    public Builder autoraterConfig(AutoraterConfig.Builder autoraterConfigBuilder) {
      return autoraterConfig(autoraterConfigBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder autoraterConfig(Optional<AutoraterConfig> autoraterConfig);

    /** Clears the value of autoraterConfig field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearAutoraterConfig() {
      return autoraterConfig(Optional.empty());
    }

    public abstract EvaluationConfig build();
  }

  /** Deserializes a JSON string to a EvaluationConfig object. */
  @ExcludeFromGeneratedCoverageReport
  public static EvaluationConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, EvaluationConfig.class);
  }
}
