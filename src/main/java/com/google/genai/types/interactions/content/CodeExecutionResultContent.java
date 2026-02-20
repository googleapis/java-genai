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

package com.google.genai.types.interactions.content;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import com.google.genai.types.ExcludeFromGeneratedCoverageReport;
import java.util.Optional;

/**
 * Code execution result content for the Interactions API.
 *
 * <p>Represents the result of executing code requested by the model. This content type appears when
 * providing the output from a code execution call back to the model.
 *
 * <p>Example usage:
 *
 * <pre>{@code
 * CodeExecutionResultContent result = CodeExecutionResultContent.builder()
 *     .callId("call_123")
 *     .result("Hello, World!")
 *     .build();
 * }</pre>
 *
 * <p>The Interactions API is available in both Vertex AI and Gemini API.
 *
 * <p>Note: The Interactions API is in beta and subject to change.
 */
@AutoValue
@JsonDeserialize(builder = CodeExecutionResultContent.Builder.class)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonTypeName("code_execution_result")
public abstract class CodeExecutionResultContent extends JsonSerializable implements Content {

  @JsonProperty("result")
  public abstract Optional<String> result();

  @JsonProperty("is_error")
  public abstract Optional<Boolean> isError();

  @JsonProperty("signature")
  public abstract Optional<String> signature();

  @JsonProperty("call_id")
  public abstract Optional<String> callId();

  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_CodeExecutionResultContent.Builder();
  }

  public abstract Builder toBuilder();

  @AutoValue.Builder
  public abstract static class Builder {
    @JsonCreator
    private static Builder create() {
      return new AutoValue_CodeExecutionResultContent.Builder();
    }

    @JsonProperty("result")
    public abstract Builder result(String result);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder result(Optional<String> result);

    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearResult() {
      return result(Optional.empty());
    }

    @JsonProperty("is_error")
    public abstract Builder isError(Boolean isError);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder isError(Optional<Boolean> isError);

    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearIsError() {
      return isError(Optional.empty());
    }

    @JsonProperty("signature")
    public abstract Builder signature(String signature);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder signature(Optional<String> signature);

    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearSignature() {
      return signature(Optional.empty());
    }

    @JsonProperty("call_id")
    public abstract Builder callId(String callId);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder callId(Optional<String> callId);

    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearCallId() {
      return callId(Optional.empty());
    }

    public abstract CodeExecutionResultContent build();
  }

  @ExcludeFromGeneratedCoverageReport
  public static CodeExecutionResultContent fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, CodeExecutionResultContent.class);
  }

  @ExcludeFromGeneratedCoverageReport
  public static CodeExecutionResultContent of(String result, String callId) {
    return builder().result(result).callId(callId).isError(false).build();
  }

  @ExcludeFromGeneratedCoverageReport
  public static CodeExecutionResultContent ofError(String result, String callId) {
    return builder().result(result).callId(callId).isError(true).build();
  }
}
