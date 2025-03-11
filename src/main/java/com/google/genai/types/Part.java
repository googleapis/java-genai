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

import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import com.google.gson.annotations.SerializedName;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;
import java.util.Optional;

/**
 * A datatype containing media content.
 *
 * <p>Exactly one field within a Part should be set, representing the specific type of content being
 * conveyed. Using multiple fields within the same `Part` instance is considered invalid.
 */
@GenerateTypeAdapter
@AutoValue
public abstract class Part extends JsonSerializable {
  /** Metadata for a given video. */
  @SerializedName("videoMetadata")
  public abstract Optional<VideoMetadata> videoMetadata();

  /** Indicates if the part is thought from the model. */
  @SerializedName("thought")
  public abstract Optional<Boolean> thought();

  /** Optional. Result of executing the [ExecutableCode]. */
  @SerializedName("codeExecutionResult")
  public abstract Optional<CodeExecutionResult> codeExecutionResult();

  /** Optional. Code generated by the model that is meant to be executed. */
  @SerializedName("executableCode")
  public abstract Optional<ExecutableCode> executableCode();

  /** Optional. URI based data. */
  @SerializedName("fileData")
  public abstract Optional<FileData> fileData();

  /**
   * Optional. A predicted [FunctionCall] returned from the model that contains a string
   * representing the [FunctionDeclaration.name] with the parameters and their values.
   */
  @SerializedName("functionCall")
  public abstract Optional<FunctionCall> functionCall();

  /**
   * Optional. The result output of a [FunctionCall] that contains a string representing the
   * [FunctionDeclaration.name] and a structured JSON object containing any output from the function
   * call. It is used as context to the model.
   */
  @SerializedName("functionResponse")
  public abstract Optional<FunctionResponse> functionResponse();

  /** Optional. Inlined bytes data. */
  @SerializedName("inlineData")
  public abstract Optional<Blob> inlineData();

  /** Optional. Text part (can be code). */
  @SerializedName("text")
  public abstract Optional<String> text();

  /** Instantiates a builder for Part. */
  public static Builder builder() {
    return new AutoValue_Part.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for Part. */
  @AutoValue.Builder
  public abstract static class Builder {
    @SerializedName("videoMetadata")
    public abstract Builder videoMetadata(VideoMetadata videoMetadata);

    @SerializedName("videoMetadata")
    abstract Builder videoMetadata(Optional<VideoMetadata> videoMetadata);

    @SerializedName("thought")
    public abstract Builder thought(boolean thought);

    @SerializedName("thought")
    abstract Builder thought(Optional<Boolean> thought);

    @SerializedName("codeExecutionResult")
    public abstract Builder codeExecutionResult(CodeExecutionResult codeExecutionResult);

    @SerializedName("codeExecutionResult")
    abstract Builder codeExecutionResult(Optional<CodeExecutionResult> codeExecutionResult);

    @SerializedName("executableCode")
    public abstract Builder executableCode(ExecutableCode executableCode);

    @SerializedName("executableCode")
    abstract Builder executableCode(Optional<ExecutableCode> executableCode);

    @SerializedName("fileData")
    public abstract Builder fileData(FileData fileData);

    @SerializedName("fileData")
    abstract Builder fileData(Optional<FileData> fileData);

    @SerializedName("functionCall")
    public abstract Builder functionCall(FunctionCall functionCall);

    @SerializedName("functionCall")
    abstract Builder functionCall(Optional<FunctionCall> functionCall);

    @SerializedName("functionResponse")
    public abstract Builder functionResponse(FunctionResponse functionResponse);

    @SerializedName("functionResponse")
    abstract Builder functionResponse(Optional<FunctionResponse> functionResponse);

    @SerializedName("inlineData")
    public abstract Builder inlineData(Blob inlineData);

    @SerializedName("inlineData")
    abstract Builder inlineData(Optional<Blob> inlineData);

    @SerializedName("text")
    public abstract Builder text(String text);

    @SerializedName("text")
    abstract Builder text(Optional<String> text);

    public abstract Part build();
  }

  /** Deserializes a JSON string to a Part object. */
  public static Part fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, Part.class);
  }
}
