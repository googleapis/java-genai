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
import java.util.Map;
import java.util.Optional;

/** Specification for a computation based metric. */
@AutoValue
@JsonDeserialize(builder = ComputationBasedMetricSpec.Builder.class)
public abstract class ComputationBasedMetricSpec extends JsonSerializable {
  /** The type of the computation based metric. */
  @JsonProperty("type")
  public abstract Optional<ComputationBasedMetricType> type();

  /**
   * A map of parameters for the metric. ROUGE example: {"rouge_type": "rougeL", "split_summaries":
   * True, "use_stemmer": True}. BLEU example: {"use_effective_order": True}.
   */
  @JsonProperty("parameters")
  public abstract Optional<Map<String, Object>> parameters();

  /** Instantiates a builder for ComputationBasedMetricSpec. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ComputationBasedMetricSpec.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ComputationBasedMetricSpec. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `ComputationBasedMetricSpec.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ComputationBasedMetricSpec.Builder();
    }

    /**
     * Setter for type.
     *
     * <p>type: The type of the computation based metric.
     */
    @JsonProperty("type")
    public abstract Builder type(ComputationBasedMetricType type);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder type(Optional<ComputationBasedMetricType> type);

    /** Clears the value of type field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearType() {
      return type(Optional.empty());
    }

    /**
     * Setter for type given a known enum.
     *
     * <p>type: The type of the computation based metric.
     */
    @CanIgnoreReturnValue
    public Builder type(ComputationBasedMetricType.Known knownType) {
      return type(new ComputationBasedMetricType(knownType));
    }

    /**
     * Setter for type given a string.
     *
     * <p>type: The type of the computation based metric.
     */
    @CanIgnoreReturnValue
    public Builder type(String type) {
      return type(new ComputationBasedMetricType(type));
    }

    /**
     * Setter for parameters.
     *
     * <p>parameters: A map of parameters for the metric. ROUGE example: {"rouge_type": "rougeL",
     * "split_summaries": True, "use_stemmer": True}. BLEU example: {"use_effective_order": True}.
     */
    @JsonProperty("parameters")
    public abstract Builder parameters(Map<String, Object> parameters);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder parameters(Optional<Map<String, Object>> parameters);

    /** Clears the value of parameters field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearParameters() {
      return parameters(Optional.empty());
    }

    public abstract ComputationBasedMetricSpec build();
  }

  /** Deserializes a JSON string to a ComputationBasedMetricSpec object. */
  @ExcludeFromGeneratedCoverageReport
  public static ComputationBasedMetricSpec fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ComputationBasedMetricSpec.class);
  }
}
