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

/** The dataset used for evaluation. This data type is not supported in Gemini API. */
@AutoValue
@JsonDeserialize(builder = EvaluationDataset.Builder.class)
public abstract class EvaluationDataset extends JsonSerializable {
  /** BigQuery source holds the dataset. */
  @JsonProperty("bigquerySource")
  public abstract Optional<BigQuerySource> bigquerySource();

  /**
   * Cloud storage source holds the dataset. Currently only one Cloud Storage file path is
   * supported.
   */
  @JsonProperty("gcsSource")
  public abstract Optional<GcsSource> gcsSource();

  /** Instantiates a builder for EvaluationDataset. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_EvaluationDataset.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for EvaluationDataset. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `EvaluationDataset.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_EvaluationDataset.Builder();
    }

    /**
     * Setter for bigquerySource.
     *
     * <p>bigquerySource: BigQuery source holds the dataset.
     */
    @JsonProperty("bigquerySource")
    public abstract Builder bigquerySource(BigQuerySource bigquerySource);

    /**
     * Setter for bigquerySource builder.
     *
     * <p>bigquerySource: BigQuery source holds the dataset.
     */
    @CanIgnoreReturnValue
    public Builder bigquerySource(BigQuerySource.Builder bigquerySourceBuilder) {
      return bigquerySource(bigquerySourceBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder bigquerySource(Optional<BigQuerySource> bigquerySource);

    /** Clears the value of bigquerySource field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearBigquerySource() {
      return bigquerySource(Optional.empty());
    }

    /**
     * Setter for gcsSource.
     *
     * <p>gcsSource: Cloud storage source holds the dataset. Currently only one Cloud Storage file
     * path is supported.
     */
    @JsonProperty("gcsSource")
    public abstract Builder gcsSource(GcsSource gcsSource);

    /**
     * Setter for gcsSource builder.
     *
     * <p>gcsSource: Cloud storage source holds the dataset. Currently only one Cloud Storage file
     * path is supported.
     */
    @CanIgnoreReturnValue
    public Builder gcsSource(GcsSource.Builder gcsSourceBuilder) {
      return gcsSource(gcsSourceBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder gcsSource(Optional<GcsSource> gcsSource);

    /** Clears the value of gcsSource field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearGcsSource() {
      return gcsSource(Optional.empty());
    }

    public abstract EvaluationDataset build();
  }

  /** Deserializes a JSON string to a EvaluationDataset object. */
  @ExcludeFromGeneratedCoverageReport
  public static EvaluationDataset fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, EvaluationDataset.class);
  }
}
