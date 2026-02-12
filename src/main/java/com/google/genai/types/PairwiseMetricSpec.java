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

/** Spec for pairwise metric. */
@AutoValue
@JsonDeserialize(builder = PairwiseMetricSpec.Builder.class)
public abstract class PairwiseMetricSpec extends JsonSerializable {
  /** Required. Metric prompt template for pairwise metric. */
  @JsonProperty("metricPromptTemplate")
  public abstract Optional<String> metricPromptTemplate();

  /** Optional. The field name of the baseline response. */
  @JsonProperty("baselineResponseFieldName")
  public abstract Optional<String> baselineResponseFieldName();

  /** Optional. The field name of the candidate response. */
  @JsonProperty("candidateResponseFieldName")
  public abstract Optional<String> candidateResponseFieldName();

  /**
   * Optional. CustomOutputFormatConfig allows customization of metric output. When this config is
   * set, the default output is replaced with the raw output string. If a custom format is chosen,
   * the `pairwise_choice` and `explanation` fields in the corresponding metric result will be
   * empty.
   */
  @JsonProperty("customOutputFormatConfig")
  public abstract Optional<CustomOutputFormatConfig> customOutputFormatConfig();

  /** Optional. System instructions for pairwise metric. */
  @JsonProperty("systemInstruction")
  public abstract Optional<String> systemInstruction();

  /** Instantiates a builder for PairwiseMetricSpec. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_PairwiseMetricSpec.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for PairwiseMetricSpec. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `PairwiseMetricSpec.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_PairwiseMetricSpec.Builder();
    }

    /**
     * Setter for metricPromptTemplate.
     *
     * <p>metricPromptTemplate: Required. Metric prompt template for pairwise metric.
     */
    @JsonProperty("metricPromptTemplate")
    public abstract Builder metricPromptTemplate(String metricPromptTemplate);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder metricPromptTemplate(Optional<String> metricPromptTemplate);

    /** Clears the value of metricPromptTemplate field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearMetricPromptTemplate() {
      return metricPromptTemplate(Optional.empty());
    }

    /**
     * Setter for baselineResponseFieldName.
     *
     * <p>baselineResponseFieldName: Optional. The field name of the baseline response.
     */
    @JsonProperty("baselineResponseFieldName")
    public abstract Builder baselineResponseFieldName(String baselineResponseFieldName);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder baselineResponseFieldName(Optional<String> baselineResponseFieldName);

    /** Clears the value of baselineResponseFieldName field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearBaselineResponseFieldName() {
      return baselineResponseFieldName(Optional.empty());
    }

    /**
     * Setter for candidateResponseFieldName.
     *
     * <p>candidateResponseFieldName: Optional. The field name of the candidate response.
     */
    @JsonProperty("candidateResponseFieldName")
    public abstract Builder candidateResponseFieldName(String candidateResponseFieldName);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder candidateResponseFieldName(Optional<String> candidateResponseFieldName);

    /** Clears the value of candidateResponseFieldName field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearCandidateResponseFieldName() {
      return candidateResponseFieldName(Optional.empty());
    }

    /**
     * Setter for customOutputFormatConfig.
     *
     * <p>customOutputFormatConfig: Optional. CustomOutputFormatConfig allows customization of
     * metric output. When this config is set, the default output is replaced with the raw output
     * string. If a custom format is chosen, the `pairwise_choice` and `explanation` fields in the
     * corresponding metric result will be empty.
     */
    @JsonProperty("customOutputFormatConfig")
    public abstract Builder customOutputFormatConfig(
        CustomOutputFormatConfig customOutputFormatConfig);

    /**
     * Setter for customOutputFormatConfig builder.
     *
     * <p>customOutputFormatConfig: Optional. CustomOutputFormatConfig allows customization of
     * metric output. When this config is set, the default output is replaced with the raw output
     * string. If a custom format is chosen, the `pairwise_choice` and `explanation` fields in the
     * corresponding metric result will be empty.
     */
    @CanIgnoreReturnValue
    public Builder customOutputFormatConfig(
        CustomOutputFormatConfig.Builder customOutputFormatConfigBuilder) {
      return customOutputFormatConfig(customOutputFormatConfigBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder customOutputFormatConfig(
        Optional<CustomOutputFormatConfig> customOutputFormatConfig);

    /** Clears the value of customOutputFormatConfig field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearCustomOutputFormatConfig() {
      return customOutputFormatConfig(Optional.empty());
    }

    /**
     * Setter for systemInstruction.
     *
     * <p>systemInstruction: Optional. System instructions for pairwise metric.
     */
    @JsonProperty("systemInstruction")
    public abstract Builder systemInstruction(String systemInstruction);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder systemInstruction(Optional<String> systemInstruction);

    /** Clears the value of systemInstruction field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearSystemInstruction() {
      return systemInstruction(Optional.empty());
    }

    public abstract PairwiseMetricSpec build();
  }

  /** Deserializes a JSON string to a PairwiseMetricSpec object. */
  @ExcludeFromGeneratedCoverageReport
  public static PairwiseMetricSpec fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, PairwiseMetricSpec.class);
  }
}
