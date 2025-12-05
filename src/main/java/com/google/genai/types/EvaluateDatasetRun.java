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

/** Evaluate Dataset Run Result for Tuning Job. This data type is not supported in Gemini API. */
@AutoValue
@JsonDeserialize(builder = EvaluateDatasetRun.Builder.class)
public abstract class EvaluateDatasetRun extends JsonSerializable {
  /**
   * Output only. The checkpoint id used in the evaluation run. Only populated when evaluating
   * checkpoints.
   */
  @JsonProperty("checkpointId")
  public abstract Optional<String> checkpointId();

  /** Output only. The error of the evaluation run if any. */
  @JsonProperty("error")
  public abstract Optional<GoogleRpcStatus> error();

  /** Output only. Results for EvaluationService.EvaluateDataset. */
  @JsonProperty("evaluateDatasetResponse")
  public abstract Optional<EvaluateDatasetResponse> evaluateDatasetResponse();

  /**
   * Output only. The operation ID of the evaluation run. Format:
   * `projects/{project}/locations/{location}/operations/{operation_id}`.
   */
  @JsonProperty("operationName")
  public abstract Optional<String> operationName();

  /** Instantiates a builder for EvaluateDatasetRun. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_EvaluateDatasetRun.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for EvaluateDatasetRun. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `EvaluateDatasetRun.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_EvaluateDatasetRun.Builder();
    }

    /**
     * Setter for checkpointId.
     *
     * <p>checkpointId: Output only. The checkpoint id used in the evaluation run. Only populated
     * when evaluating checkpoints.
     */
    @JsonProperty("checkpointId")
    public abstract Builder checkpointId(String checkpointId);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder checkpointId(Optional<String> checkpointId);

    /** Clears the value of checkpointId field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearCheckpointId() {
      return checkpointId(Optional.empty());
    }

    /**
     * Setter for error.
     *
     * <p>error: Output only. The error of the evaluation run if any.
     */
    @JsonProperty("error")
    public abstract Builder error(GoogleRpcStatus error);

    /**
     * Setter for error builder.
     *
     * <p>error: Output only. The error of the evaluation run if any.
     */
    @CanIgnoreReturnValue
    public Builder error(GoogleRpcStatus.Builder errorBuilder) {
      return error(errorBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder error(Optional<GoogleRpcStatus> error);

    /** Clears the value of error field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearError() {
      return error(Optional.empty());
    }

    /**
     * Setter for evaluateDatasetResponse.
     *
     * <p>evaluateDatasetResponse: Output only. Results for EvaluationService.EvaluateDataset.
     */
    @JsonProperty("evaluateDatasetResponse")
    public abstract Builder evaluateDatasetResponse(
        EvaluateDatasetResponse evaluateDatasetResponse);

    /**
     * Setter for evaluateDatasetResponse builder.
     *
     * <p>evaluateDatasetResponse: Output only. Results for EvaluationService.EvaluateDataset.
     */
    @CanIgnoreReturnValue
    public Builder evaluateDatasetResponse(
        EvaluateDatasetResponse.Builder evaluateDatasetResponseBuilder) {
      return evaluateDatasetResponse(evaluateDatasetResponseBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder evaluateDatasetResponse(
        Optional<EvaluateDatasetResponse> evaluateDatasetResponse);

    /** Clears the value of evaluateDatasetResponse field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearEvaluateDatasetResponse() {
      return evaluateDatasetResponse(Optional.empty());
    }

    /**
     * Setter for operationName.
     *
     * <p>operationName: Output only. The operation ID of the evaluation run. Format:
     * `projects/{project}/locations/{location}/operations/{operation_id}`.
     */
    @JsonProperty("operationName")
    public abstract Builder operationName(String operationName);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder operationName(Optional<String> operationName);

    /** Clears the value of operationName field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearOperationName() {
      return operationName(Optional.empty());
    }

    public abstract EvaluateDatasetRun build();
  }

  /** Deserializes a JSON string to a EvaluateDatasetRun object. */
  @ExcludeFromGeneratedCoverageReport
  public static EvaluateDatasetRun fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, EvaluateDatasetRun.class);
  }
}
