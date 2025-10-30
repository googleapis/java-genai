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
import com.google.genai.JsonSerializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/** Dataset distribution for Supervised Tuning. This data type is not supported in Gemini API. */
@AutoValue
@JsonDeserialize(builder = SupervisedTuningDatasetDistribution.Builder.class)
public abstract class SupervisedTuningDatasetDistribution extends JsonSerializable {
  /** Output only. Sum of a given population of values that are billable. */
  @JsonProperty("billableSum")
  public abstract Optional<Long> billableSum();

  /** Output only. Defines the histogram bucket. */
  @JsonProperty("buckets")
  public abstract Optional<List<SupervisedTuningDatasetDistributionDatasetBucket>> buckets();

  /** Output only. The maximum of the population values. */
  @JsonProperty("max")
  public abstract Optional<Double> max();

  /** Output only. The arithmetic mean of the values in the population. */
  @JsonProperty("mean")
  public abstract Optional<Double> mean();

  /** Output only. The median of the values in the population. */
  @JsonProperty("median")
  public abstract Optional<Double> median();

  /** Output only. The minimum of the population values. */
  @JsonProperty("min")
  public abstract Optional<Double> min();

  /** Output only. The 5th percentile of the values in the population. */
  @JsonProperty("p5")
  public abstract Optional<Double> p5();

  /** Output only. The 95th percentile of the values in the population. */
  @JsonProperty("p95")
  public abstract Optional<Double> p95();

  /** Output only. Sum of a given population of values. */
  @JsonProperty("sum")
  public abstract Optional<Long> sum();

  /** Instantiates a builder for SupervisedTuningDatasetDistribution. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_SupervisedTuningDatasetDistribution.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for SupervisedTuningDatasetDistribution. */
  @AutoValue.Builder
  public abstract static class Builder {
    /**
     * For internal usage. Please use `SupervisedTuningDatasetDistribution.builder()` for
     * instantiation.
     */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_SupervisedTuningDatasetDistribution.Builder();
    }

    /**
     * Setter for billableSum.
     *
     * <p>billableSum: Output only. Sum of a given population of values that are billable.
     */
    @JsonProperty("billableSum")
    public abstract Builder billableSum(Long billableSum);

    /**
     * Setter for buckets.
     *
     * <p>buckets: Output only. Defines the histogram bucket.
     */
    @JsonProperty("buckets")
    public abstract Builder buckets(List<SupervisedTuningDatasetDistributionDatasetBucket> buckets);

    /**
     * Setter for buckets.
     *
     * <p>buckets: Output only. Defines the histogram bucket.
     */
    public Builder buckets(SupervisedTuningDatasetDistributionDatasetBucket... buckets) {
      return buckets(Arrays.asList(buckets));
    }

    /**
     * Setter for buckets builder.
     *
     * <p>buckets: Output only. Defines the histogram bucket.
     */
    public Builder buckets(
        SupervisedTuningDatasetDistributionDatasetBucket.Builder... bucketsBuilders) {
      return buckets(
          Arrays.asList(bucketsBuilders).stream()
              .map(SupervisedTuningDatasetDistributionDatasetBucket.Builder::build)
              .collect(toImmutableList()));
    }

    /**
     * Setter for max.
     *
     * <p>max: Output only. The maximum of the population values.
     */
    @JsonProperty("max")
    public abstract Builder max(Double max);

    /**
     * Setter for mean.
     *
     * <p>mean: Output only. The arithmetic mean of the values in the population.
     */
    @JsonProperty("mean")
    public abstract Builder mean(Double mean);

    /**
     * Setter for median.
     *
     * <p>median: Output only. The median of the values in the population.
     */
    @JsonProperty("median")
    public abstract Builder median(Double median);

    /**
     * Setter for min.
     *
     * <p>min: Output only. The minimum of the population values.
     */
    @JsonProperty("min")
    public abstract Builder min(Double min);

    /**
     * Setter for p5.
     *
     * <p>p5: Output only. The 5th percentile of the values in the population.
     */
    @JsonProperty("p5")
    public abstract Builder p5(Double p5);

    /**
     * Setter for p95.
     *
     * <p>p95: Output only. The 95th percentile of the values in the population.
     */
    @JsonProperty("p95")
    public abstract Builder p95(Double p95);

    /**
     * Setter for sum.
     *
     * <p>sum: Output only. Sum of a given population of values.
     */
    @JsonProperty("sum")
    public abstract Builder sum(Long sum);

    public abstract SupervisedTuningDatasetDistribution build();
  }

  /** Deserializes a JSON string to a SupervisedTuningDatasetDistribution object. */
  @ExcludeFromGeneratedCoverageReport
  public static SupervisedTuningDatasetDistribution fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, SupervisedTuningDatasetDistribution.class);
  }
}
