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
import com.google.genai.JsonSerializable;
import java.util.Optional;

/**
 * Dataset bucket used to create a histogram for the distribution given a population of values. This
 * data type is not supported in Gemini API.
 */
@AutoValue
@JsonDeserialize(builder = DatasetDistributionDistributionBucket.Builder.class)
public abstract class DatasetDistributionDistributionBucket extends JsonSerializable {
  /** Output only. Number of values in the bucket. */
  @JsonProperty("count")
  public abstract Optional<Long> count();

  /** Output only. Left bound of the bucket. */
  @JsonProperty("left")
  public abstract Optional<Double> left();

  /** Output only. Right bound of the bucket. */
  @JsonProperty("right")
  public abstract Optional<Double> right();

  /** Instantiates a builder for DatasetDistributionDistributionBucket. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_DatasetDistributionDistributionBucket.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for DatasetDistributionDistributionBucket. */
  @AutoValue.Builder
  public abstract static class Builder {
    /**
     * For internal usage. Please use `DatasetDistributionDistributionBucket.builder()` for
     * instantiation.
     */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_DatasetDistributionDistributionBucket.Builder();
    }

    /**
     * Setter for count.
     *
     * <p>count: Output only. Number of values in the bucket.
     */
    @JsonProperty("count")
    public abstract Builder count(Long count);

    /**
     * Setter for left.
     *
     * <p>left: Output only. Left bound of the bucket.
     */
    @JsonProperty("left")
    public abstract Builder left(Double left);

    /**
     * Setter for right.
     *
     * <p>right: Output only. Right bound of the bucket.
     */
    @JsonProperty("right")
    public abstract Builder right(Double right);

    public abstract DatasetDistributionDistributionBucket build();
  }

  /** Deserializes a JSON string to a DatasetDistributionDistributionBucket object. */
  @ExcludeFromGeneratedCoverageReport
  public static DatasetDistributionDistributionBucket fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, DatasetDistributionDistributionBucket.class);
  }
}
