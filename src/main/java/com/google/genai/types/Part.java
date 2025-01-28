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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/**
 * A datatype containing media content.
 *
 * <p>Exactly one field within a Part should be set, representing the specific type of content being
 * conveyed. Using multiple fields within the same `Part` instance is considered invalid.
 */
@AutoValue
@JsonDeserialize(builder = AutoValue_Part.Builder.class)
public abstract class Part extends JsonSerializable {
  /** Metadata for a given video. */
  public abstract Optional<VideoMetadata> getVideoMetadata();

  /** Indicates if the part is thought from the model. */
  public abstract Optional<Boolean> getThought();

  /** Optional. Result of executing the [ExecutableCode]. */
  public abstract Optional<CodeExecutionResult> getCodeExecutionResult();

  /** Optional. Code generated by the model that is meant to be executed. */
  public abstract Optional<ExecutableCode> getExecutableCode();

  /** Optional. URI based data. */
  public abstract Optional<FileData> getFileData();

  /**
   * Optional. A predicted [FunctionCall] returned from the model that contains a string
   * representing the [FunctionDeclaration.name] with the parameters and their values.
   */
  public abstract Optional<FunctionCall> getFunctionCall();

  /**
   * Optional. The result output of a [FunctionCall] that contains a string representing the
   * [FunctionDeclaration.name] and a structured JSON object containing any output from the function
   * call. It is used as context to the model.
   */
  public abstract Optional<FunctionResponse> getFunctionResponse();

  /** Optional. Inlined bytes data. */
  public abstract Optional<Blob> getInlineData();

  /** Optional. Text part (can be code). */
  public abstract Optional<String> getText();

  /** Instantiates a builder for Part. */
  public static Builder builder() {
    return new AutoValue_Part.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for Part. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("videoMetadata")
    public abstract Builder setVideoMetadata(VideoMetadata videoMetadata);

    @JsonProperty("thought")
    public abstract Builder setThought(boolean thought);

    @JsonProperty("codeExecutionResult")
    public abstract Builder setCodeExecutionResult(CodeExecutionResult codeExecutionResult);

    @JsonProperty("executableCode")
    public abstract Builder setExecutableCode(ExecutableCode executableCode);

    @JsonProperty("fileData")
    public abstract Builder setFileData(FileData fileData);

    @JsonProperty("functionCall")
    public abstract Builder setFunctionCall(FunctionCall functionCall);

    @JsonProperty("functionResponse")
    public abstract Builder setFunctionResponse(FunctionResponse functionResponse);

    @JsonProperty("inlineData")
    public abstract Builder setInlineData(Blob inlineData);

    @JsonProperty("text")
    public abstract Builder setText(String text);

    public abstract Part build();
  }

  /** Deserializes a JSON string to a Part object. */
  public static Part fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, Part.class);
  }
}
