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
 * Describes the info for output of EvaluationService.EvaluateDataset. This data type is not
 * supported in Gemini API.
 */
@AutoValue
@JsonDeserialize(builder = OutputInfo.Builder.class)
public abstract class OutputInfo extends JsonSerializable {
  /**
   * Output only. The full path of the Cloud Storage directory created, into which the evaluation
   * results and aggregation results are written.
   */
  @JsonProperty("gcsOutputDirectory")
  public abstract Optional<String> gcsOutputDirectory();

  /** Instantiates a builder for OutputInfo. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_OutputInfo.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for OutputInfo. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `OutputInfo.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_OutputInfo.Builder();
    }

    /**
     * Setter for gcsOutputDirectory.
     *
     * <p>gcsOutputDirectory: Output only. The full path of the Cloud Storage directory created,
     * into which the evaluation results and aggregation results are written.
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

    public abstract OutputInfo build();
  }

  /** Deserializes a JSON string to a OutputInfo object. */
  @ExcludeFromGeneratedCoverageReport
  public static OutputInfo fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, OutputInfo.class);
  }
}
