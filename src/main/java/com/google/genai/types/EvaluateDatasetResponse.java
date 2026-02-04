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

/**
 * Response in LRO for EvaluationService.EvaluateDataset. This data type is not supported in Gemini
 * API.
 */
@AutoValue
@JsonDeserialize(builder = EvaluateDatasetResponse.Builder.class)
public abstract class EvaluateDatasetResponse extends JsonSerializable {
  /**
   * Output only. Aggregation statistics derived from results of EvaluationService.EvaluateDataset.
   */
  @JsonProperty("aggregationOutput")
  public abstract Optional<AggregationOutput> aggregationOutput();

  /** Output only. Output info for EvaluationService.EvaluateDataset. */
  @JsonProperty("outputInfo")
  public abstract Optional<OutputInfo> outputInfo();

  /** Instantiates a builder for EvaluateDatasetResponse. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_EvaluateDatasetResponse.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for EvaluateDatasetResponse. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `EvaluateDatasetResponse.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_EvaluateDatasetResponse.Builder();
    }

    /**
     * Setter for aggregationOutput.
     *
     * <p>aggregationOutput: Output only. Aggregation statistics derived from results of
     * EvaluationService.EvaluateDataset.
     */
    @JsonProperty("aggregationOutput")
    public abstract Builder aggregationOutput(AggregationOutput aggregationOutput);

    /**
     * Setter for aggregationOutput builder.
     *
     * <p>aggregationOutput: Output only. Aggregation statistics derived from results of
     * EvaluationService.EvaluateDataset.
     */
    @CanIgnoreReturnValue
    public Builder aggregationOutput(AggregationOutput.Builder aggregationOutputBuilder) {
      return aggregationOutput(aggregationOutputBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder aggregationOutput(Optional<AggregationOutput> aggregationOutput);

    /** Clears the value of aggregationOutput field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearAggregationOutput() {
      return aggregationOutput(Optional.empty());
    }

    /**
     * Setter for outputInfo.
     *
     * <p>outputInfo: Output only. Output info for EvaluationService.EvaluateDataset.
     */
    @JsonProperty("outputInfo")
    public abstract Builder outputInfo(OutputInfo outputInfo);

    /**
     * Setter for outputInfo builder.
     *
     * <p>outputInfo: Output only. Output info for EvaluationService.EvaluateDataset.
     */
    @CanIgnoreReturnValue
    public Builder outputInfo(OutputInfo.Builder outputInfoBuilder) {
      return outputInfo(outputInfoBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder outputInfo(Optional<OutputInfo> outputInfo);

    /** Clears the value of outputInfo field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearOutputInfo() {
      return outputInfo(Optional.empty());
    }

    public abstract EvaluateDatasetResponse build();
  }

  /** Deserializes a JSON string to a EvaluateDatasetResponse object. */
  @ExcludeFromGeneratedCoverageReport
  public static EvaluateDatasetResponse fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, EvaluateDatasetResponse.class);
  }
}
