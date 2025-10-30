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
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** A single example for tuning. This data type is not supported in Vertex AI. */
@AutoValue
@JsonDeserialize(builder = TuningExample.Builder.class)
public abstract class TuningExample extends JsonSerializable {
  /** Required. The expected model output. */
  @JsonProperty("output")
  public abstract Optional<String> output();

  /** Optional. Text model input. */
  @JsonProperty("textInput")
  public abstract Optional<String> textInput();

  /** Instantiates a builder for TuningExample. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_TuningExample.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for TuningExample. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `TuningExample.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_TuningExample.Builder();
    }

    /**
     * Setter for output.
     *
     * <p>output: Required. The expected model output.
     */
    @JsonProperty("output")
    public abstract Builder output(String output);

    /**
     * Setter for textInput.
     *
     * <p>textInput: Optional. Text model input.
     */
    @JsonProperty("textInput")
    public abstract Builder textInput(String textInput);

    public abstract TuningExample build();
  }

  /** Deserializes a JSON string to a TuningExample object. */
  @ExcludeFromGeneratedCoverageReport
  public static TuningExample fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, TuningExample.class);
  }
}
