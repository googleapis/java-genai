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

/** Specifies a metric that is computed by running user-defined Python functions remotely. */
@AutoValue
@JsonDeserialize(builder = CustomCodeExecutionSpec.Builder.class)
public abstract class CustomCodeExecutionSpec extends JsonSerializable {
  /**
   * A string representing a user-defined function for evaluation. Expected user to define the
   * following function, e.g.: def evaluate(instance: dict[str, Any]) -&gt; float: Please include
   * this function signature in the code snippet. Instance is the evaluation instance, any fields
   * populated in the instance are available to the function as instance[field_name].
   */
  @JsonProperty("evaluationFunction")
  public abstract Optional<String> evaluationFunction();

  /**
   * Optional. The region to use for code execution. If set, the Code Execution Sandbox will be
   * invoked in the specified region regardless of the request's originating region. Must be a
   * region where the Code Execution Sandbox is available. Supported regions: us-central1, us-east1,
   * us-east4, us-west1, us-west4, southamerica-east1, europe-west2, europe-west3, asia-east1,
   * asia-south1, asia-southeast1. If unset, the request's originating region is used; requests from
   * regions where the sandbox is unavailable will fail with UNIMPLEMENTED.
   */
  @JsonProperty("codeExecutionRegion")
  public abstract Optional<String> codeExecutionRegion();

  /** Instantiates a builder for CustomCodeExecutionSpec. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_CustomCodeExecutionSpec.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for CustomCodeExecutionSpec. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `CustomCodeExecutionSpec.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_CustomCodeExecutionSpec.Builder();
    }

    /**
     * Setter for evaluationFunction.
     *
     * <p>evaluationFunction: A string representing a user-defined function for evaluation. Expected
     * user to define the following function, e.g.: def evaluate(instance: dict[str, Any]) -&gt;
     * float: Please include this function signature in the code snippet. Instance is the evaluation
     * instance, any fields populated in the instance are available to the function as
     * instance[field_name].
     */
    @JsonProperty("evaluationFunction")
    public abstract Builder evaluationFunction(String evaluationFunction);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder evaluationFunction(Optional<String> evaluationFunction);

    /** Clears the value of evaluationFunction field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearEvaluationFunction() {
      return evaluationFunction(Optional.empty());
    }

    /**
     * Setter for codeExecutionRegion.
     *
     * <p>codeExecutionRegion: Optional. The region to use for code execution. If set, the Code
     * Execution Sandbox will be invoked in the specified region regardless of the request's
     * originating region. Must be a region where the Code Execution Sandbox is available. Supported
     * regions: us-central1, us-east1, us-east4, us-west1, us-west4, southamerica-east1,
     * europe-west2, europe-west3, asia-east1, asia-south1, asia-southeast1. If unset, the request's
     * originating region is used; requests from regions where the sandbox is unavailable will fail
     * with UNIMPLEMENTED.
     */
    @JsonProperty("codeExecutionRegion")
    public abstract Builder codeExecutionRegion(String codeExecutionRegion);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder codeExecutionRegion(Optional<String> codeExecutionRegion);

    /** Clears the value of codeExecutionRegion field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearCodeExecutionRegion() {
      return codeExecutionRegion(Optional.empty());
    }

    public abstract CustomCodeExecutionSpec build();
  }

  /** Deserializes a JSON string to a CustomCodeExecutionSpec object. */
  @ExcludeFromGeneratedCoverageReport
  public static CustomCodeExecutionSpec fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, CustomCodeExecutionSpec.class);
  }
}
