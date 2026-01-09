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

/**
 * The aggregation result for the entire dataset and all metrics. This data type is not supported in
 * Gemini API.
 */
@AutoValue
@JsonDeserialize(builder = AggregationOutput.Builder.class)
public abstract class AggregationOutput extends JsonSerializable {
  /** One AggregationResult per metric. */
  @JsonProperty("aggregationResults")
  public abstract Optional<List<AggregationResult>> aggregationResults();

  /** The dataset used for evaluation & aggregation. */
  @JsonProperty("dataset")
  public abstract Optional<EvaluationDataset> dataset();

  /** Instantiates a builder for AggregationOutput. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_AggregationOutput.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for AggregationOutput. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `AggregationOutput.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_AggregationOutput.Builder();
    }

    /**
     * Setter for aggregationResults.
     *
     * <p>aggregationResults: One AggregationResult per metric.
     */
    @JsonProperty("aggregationResults")
    public abstract Builder aggregationResults(List<AggregationResult> aggregationResults);

    /**
     * Setter for aggregationResults.
     *
     * <p>aggregationResults: One AggregationResult per metric.
     */
    @CanIgnoreReturnValue
    public Builder aggregationResults(AggregationResult... aggregationResults) {
      return aggregationResults(Arrays.asList(aggregationResults));
    }

    /**
     * Setter for aggregationResults builder.
     *
     * <p>aggregationResults: One AggregationResult per metric.
     */
    @CanIgnoreReturnValue
    public Builder aggregationResults(AggregationResult.Builder... aggregationResultsBuilders) {
      return aggregationResults(
          Arrays.asList(aggregationResultsBuilders).stream()
              .map(AggregationResult.Builder::build)
              .collect(toImmutableList()));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder aggregationResults(Optional<List<AggregationResult>> aggregationResults);

    /** Clears the value of aggregationResults field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearAggregationResults() {
      return aggregationResults(Optional.empty());
    }

    /**
     * Setter for dataset.
     *
     * <p>dataset: The dataset used for evaluation & aggregation.
     */
    @JsonProperty("dataset")
    public abstract Builder dataset(EvaluationDataset dataset);

    /**
     * Setter for dataset builder.
     *
     * <p>dataset: The dataset used for evaluation & aggregation.
     */
    @CanIgnoreReturnValue
    public Builder dataset(EvaluationDataset.Builder datasetBuilder) {
      return dataset(datasetBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder dataset(Optional<EvaluationDataset> dataset);

    /** Clears the value of dataset field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearDataset() {
      return dataset(Optional.empty());
    }

    public abstract AggregationOutput build();
  }

  /** Deserializes a JSON string to a AggregationOutput object. */
  @ExcludeFromGeneratedCoverageReport
  public static AggregationOutput fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, AggregationOutput.class);
  }
}
