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
import com.google.auto.value.AutoValue;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** The aggregation result for a single metric. This data type is not supported in Gemini API. */
@AutoValue
@JsonDeserialize(builder = AggregationResult.Builder.class)
public abstract class AggregationResult extends JsonSerializable {
  /** Aggregation metric. */
  @JsonProperty("aggregationMetric")
  public abstract Optional<AggregationMetric> aggregationMetric();

  /** Results for bleu metric. */
  @JsonProperty("bleuMetricValue")
  public abstract Optional<BleuMetricValue> bleuMetricValue();

  /** Result for code execution metric. */
  @JsonProperty("customCodeExecutionResult")
  public abstract Optional<CustomCodeExecutionResult> customCodeExecutionResult();

  /** Results for exact match metric. */
  @JsonProperty("exactMatchMetricValue")
  public abstract Optional<ExactMatchMetricValue> exactMatchMetricValue();

  /** Result for pairwise metric. */
  @JsonProperty("pairwiseMetricResult")
  public abstract Optional<PairwiseMetricResult> pairwiseMetricResult();

  /** Result for pointwise metric. */
  @JsonProperty("pointwiseMetricResult")
  public abstract Optional<PointwiseMetricResult> pointwiseMetricResult();

  /** Results for rouge metric. */
  @JsonProperty("rougeMetricValue")
  public abstract Optional<RougeMetricValue> rougeMetricValue();

  /** Instantiates a builder for AggregationResult. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_AggregationResult.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for AggregationResult. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `AggregationResult.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_AggregationResult.Builder();
    }

    /**
     * Setter for aggregationMetric.
     *
     * <p>aggregationMetric: Aggregation metric.
     */
    @JsonProperty("aggregationMetric")
    public abstract Builder aggregationMetric(AggregationMetric aggregationMetric);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder aggregationMetric(Optional<AggregationMetric> aggregationMetric);

    /** Clears the value of aggregationMetric field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearAggregationMetric() {
      return aggregationMetric(Optional.empty());
    }

    /**
     * Setter for aggregationMetric given a known enum.
     *
     * <p>aggregationMetric: Aggregation metric.
     */
    @CanIgnoreReturnValue
    public Builder aggregationMetric(AggregationMetric.Known knownType) {
      return aggregationMetric(new AggregationMetric(knownType));
    }

    /**
     * Setter for aggregationMetric given a string.
     *
     * <p>aggregationMetric: Aggregation metric.
     */
    @CanIgnoreReturnValue
    public Builder aggregationMetric(String aggregationMetric) {
      return aggregationMetric(new AggregationMetric(aggregationMetric));
    }

    /**
     * Setter for bleuMetricValue.
     *
     * <p>bleuMetricValue: Results for bleu metric.
     */
    @JsonProperty("bleuMetricValue")
    public abstract Builder bleuMetricValue(BleuMetricValue bleuMetricValue);

    /**
     * Setter for bleuMetricValue builder.
     *
     * <p>bleuMetricValue: Results for bleu metric.
     */
    @CanIgnoreReturnValue
    public Builder bleuMetricValue(BleuMetricValue.Builder bleuMetricValueBuilder) {
      return bleuMetricValue(bleuMetricValueBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder bleuMetricValue(Optional<BleuMetricValue> bleuMetricValue);

    /** Clears the value of bleuMetricValue field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearBleuMetricValue() {
      return bleuMetricValue(Optional.empty());
    }

    /**
     * Setter for customCodeExecutionResult.
     *
     * <p>customCodeExecutionResult: Result for code execution metric.
     */
    @JsonProperty("customCodeExecutionResult")
    public abstract Builder customCodeExecutionResult(
        CustomCodeExecutionResult customCodeExecutionResult);

    /**
     * Setter for customCodeExecutionResult builder.
     *
     * <p>customCodeExecutionResult: Result for code execution metric.
     */
    @CanIgnoreReturnValue
    public Builder customCodeExecutionResult(
        CustomCodeExecutionResult.Builder customCodeExecutionResultBuilder) {
      return customCodeExecutionResult(customCodeExecutionResultBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder customCodeExecutionResult(
        Optional<CustomCodeExecutionResult> customCodeExecutionResult);

    /** Clears the value of customCodeExecutionResult field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearCustomCodeExecutionResult() {
      return customCodeExecutionResult(Optional.empty());
    }

    /**
     * Setter for exactMatchMetricValue.
     *
     * <p>exactMatchMetricValue: Results for exact match metric.
     */
    @JsonProperty("exactMatchMetricValue")
    public abstract Builder exactMatchMetricValue(ExactMatchMetricValue exactMatchMetricValue);

    /**
     * Setter for exactMatchMetricValue builder.
     *
     * <p>exactMatchMetricValue: Results for exact match metric.
     */
    @CanIgnoreReturnValue
    public Builder exactMatchMetricValue(
        ExactMatchMetricValue.Builder exactMatchMetricValueBuilder) {
      return exactMatchMetricValue(exactMatchMetricValueBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder exactMatchMetricValue(Optional<ExactMatchMetricValue> exactMatchMetricValue);

    /** Clears the value of exactMatchMetricValue field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearExactMatchMetricValue() {
      return exactMatchMetricValue(Optional.empty());
    }

    /**
     * Setter for pairwiseMetricResult.
     *
     * <p>pairwiseMetricResult: Result for pairwise metric.
     */
    @JsonProperty("pairwiseMetricResult")
    public abstract Builder pairwiseMetricResult(PairwiseMetricResult pairwiseMetricResult);

    /**
     * Setter for pairwiseMetricResult builder.
     *
     * <p>pairwiseMetricResult: Result for pairwise metric.
     */
    @CanIgnoreReturnValue
    public Builder pairwiseMetricResult(PairwiseMetricResult.Builder pairwiseMetricResultBuilder) {
      return pairwiseMetricResult(pairwiseMetricResultBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder pairwiseMetricResult(Optional<PairwiseMetricResult> pairwiseMetricResult);

    /** Clears the value of pairwiseMetricResult field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearPairwiseMetricResult() {
      return pairwiseMetricResult(Optional.empty());
    }

    /**
     * Setter for pointwiseMetricResult.
     *
     * <p>pointwiseMetricResult: Result for pointwise metric.
     */
    @JsonProperty("pointwiseMetricResult")
    public abstract Builder pointwiseMetricResult(PointwiseMetricResult pointwiseMetricResult);

    /**
     * Setter for pointwiseMetricResult builder.
     *
     * <p>pointwiseMetricResult: Result for pointwise metric.
     */
    @CanIgnoreReturnValue
    public Builder pointwiseMetricResult(
        PointwiseMetricResult.Builder pointwiseMetricResultBuilder) {
      return pointwiseMetricResult(pointwiseMetricResultBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder pointwiseMetricResult(Optional<PointwiseMetricResult> pointwiseMetricResult);

    /** Clears the value of pointwiseMetricResult field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearPointwiseMetricResult() {
      return pointwiseMetricResult(Optional.empty());
    }

    /**
     * Setter for rougeMetricValue.
     *
     * <p>rougeMetricValue: Results for rouge metric.
     */
    @JsonProperty("rougeMetricValue")
    public abstract Builder rougeMetricValue(RougeMetricValue rougeMetricValue);

    /**
     * Setter for rougeMetricValue builder.
     *
     * <p>rougeMetricValue: Results for rouge metric.
     */
    @CanIgnoreReturnValue
    public Builder rougeMetricValue(RougeMetricValue.Builder rougeMetricValueBuilder) {
      return rougeMetricValue(rougeMetricValueBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder rougeMetricValue(Optional<RougeMetricValue> rougeMetricValue);

    /** Clears the value of rougeMetricValue field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearRougeMetricValue() {
      return rougeMetricValue(Optional.empty());
    }

    public abstract AggregationResult build();
  }

  /** Deserializes a JSON string to a AggregationResult object. */
  @ExcludeFromGeneratedCoverageReport
  public static AggregationResult fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, AggregationResult.class);
  }
}
