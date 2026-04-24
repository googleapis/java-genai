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

/** Represents the `output_info` field in batch jobs. */
@AutoValue
@JsonDeserialize(builder = BatchJobOutputInfo.Builder.class)
public abstract class BatchJobOutputInfo extends JsonSerializable {
  /**
   * This field is experimental and may change in future versions. The Vertex AI dataset name
   * containing the output data.
   */
  @JsonProperty("vertexMultimodalDatasetName")
  public abstract Optional<String> vertexMultimodalDatasetName();

  /**
   * The full path of the Cloud Storage directory created, into which the prediction output is
   * written.
   */
  @JsonProperty("gcsOutputDirectory")
  public abstract Optional<String> gcsOutputDirectory();

  /**
   * The name of the BigQuery table created, in `predictions_<timestamp>` format, into which the
   * prediction output is written.
   */
  @JsonProperty("bigqueryOutputTable")
  public abstract Optional<String> bigqueryOutputTable();

  /** Instantiates a builder for BatchJobOutputInfo. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_BatchJobOutputInfo.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for BatchJobOutputInfo. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `BatchJobOutputInfo.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_BatchJobOutputInfo.Builder();
    }

    /**
     * Setter for vertexMultimodalDatasetName.
     *
     * <p>vertexMultimodalDatasetName: This field is experimental and may change in future versions.
     * The Vertex AI dataset name containing the output data.
     */
    @JsonProperty("vertexMultimodalDatasetName")
    public abstract Builder vertexMultimodalDatasetName(String vertexMultimodalDatasetName);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder vertexMultimodalDatasetName(Optional<String> vertexMultimodalDatasetName);

    /** Clears the value of vertexMultimodalDatasetName field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearVertexMultimodalDatasetName() {
      return vertexMultimodalDatasetName(Optional.empty());
    }

    /**
     * Setter for gcsOutputDirectory.
     *
     * <p>gcsOutputDirectory: The full path of the Cloud Storage directory created, into which the
     * prediction output is written.
     */
    @JsonProperty("gcsOutputDirectory")
    public abstract Builder gcsOutputDirectory(String gcsOutputDirectory);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder gcsOutputDirectory(Optional<String> gcsOutputDirectory);

    /** Clears the value of gcsOutputDirectory field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearGcsOutputDirectory() {
      return gcsOutputDirectory(Optional.empty());
    }

    /**
     * Setter for bigqueryOutputTable.
     *
     * <p>bigqueryOutputTable: The name of the BigQuery table created, in `predictions_<timestamp>`
     * format, into which the prediction output is written.
     */
    @JsonProperty("bigqueryOutputTable")
    public abstract Builder bigqueryOutputTable(String bigqueryOutputTable);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder bigqueryOutputTable(Optional<String> bigqueryOutputTable);

    /** Clears the value of bigqueryOutputTable field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearBigqueryOutputTable() {
      return bigqueryOutputTable(Optional.empty());
    }

    public abstract BatchJobOutputInfo build();
  }

  /** Deserializes a JSON string to a BatchJobOutputInfo object. */
  @ExcludeFromGeneratedCoverageReport
  public static BatchJobOutputInfo fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, BatchJobOutputInfo.class);
  }
}
