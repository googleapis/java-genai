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

/** The spec for a pre-defined metric. This data type is not supported in Gemini API. */
@AutoValue
@JsonDeserialize(builder = PredefinedMetricSpec.Builder.class)
public abstract class PredefinedMetricSpec extends JsonSerializable {
  /**
   * Required. The name of a pre-defined metric, such as "instruction_following_v1" or
   * "text_quality_v1".
   */
  @JsonProperty("metricSpecName")
  public abstract Optional<String> metricSpecName();

  /** Optional. The parameters needed to run the pre-defined metric. */
  @JsonProperty("metricSpecParameters")
  public abstract Optional<Map<String, Object>> metricSpecParameters();

  /** Instantiates a builder for PredefinedMetricSpec. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_PredefinedMetricSpec.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for PredefinedMetricSpec. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `PredefinedMetricSpec.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_PredefinedMetricSpec.Builder();
    }

    /**
     * Setter for metricSpecName.
     *
     * <p>metricSpecName: Required. The name of a pre-defined metric, such as
     * "instruction_following_v1" or "text_quality_v1".
     */
    @JsonProperty("metricSpecName")
    public abstract Builder metricSpecName(String metricSpecName);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder metricSpecName(Optional<String> metricSpecName);

    /** Clears the value of metricSpecName field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearMetricSpecName() {
      return metricSpecName(Optional.empty());
    }

    /**
     * Setter for metricSpecParameters.
     *
     * <p>metricSpecParameters: Optional. The parameters needed to run the pre-defined metric.
     */
    @JsonProperty("metricSpecParameters")
    public abstract Builder metricSpecParameters(Map<String, Object> metricSpecParameters);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder metricSpecParameters(Optional<Map<String, Object>> metricSpecParameters);

    /** Clears the value of metricSpecParameters field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearMetricSpecParameters() {
      return metricSpecParameters(Optional.empty());
    }

    public abstract PredefinedMetricSpec build();
  }

  /** Deserializes a JSON string to a PredefinedMetricSpec object. */
  @ExcludeFromGeneratedCoverageReport
  public static PredefinedMetricSpec fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, PredefinedMetricSpec.class);
  }
}
