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

import static com.google.common.collect.ImmutableList.toImmutableList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/** Input example for preference optimization. This data type is not supported in Gemini API. */
@AutoValue
@JsonDeserialize(builder = GeminiPreferenceExample.Builder.class)
public abstract class GeminiPreferenceExample extends JsonSerializable {
  /** List of completions for a given prompt. */
  @JsonProperty("completions")
  public abstract Optional<List<GeminiPreferenceExampleCompletion>> completions();

  /** Multi-turn contents that represents the Prompt. */
  @JsonProperty("contents")
  public abstract Optional<List<Content>> contents();

  /** Instantiates a builder for GeminiPreferenceExample. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_GeminiPreferenceExample.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GeminiPreferenceExample. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `GeminiPreferenceExample.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_GeminiPreferenceExample.Builder();
    }

    /**
     * Setter for completions.
     *
     * <p>completions: List of completions for a given prompt.
     */
    @JsonProperty("completions")
    public abstract Builder completions(List<GeminiPreferenceExampleCompletion> completions);

    /**
     * Setter for completions.
     *
     * <p>completions: List of completions for a given prompt.
     */
    public Builder completions(GeminiPreferenceExampleCompletion... completions) {
      return completions(Arrays.asList(completions));
    }

    /**
     * Setter for completions builder.
     *
     * <p>completions: List of completions for a given prompt.
     */
    public Builder completions(GeminiPreferenceExampleCompletion.Builder... completionsBuilders) {
      return completions(
          Arrays.asList(completionsBuilders).stream()
              .map(GeminiPreferenceExampleCompletion.Builder::build)
              .collect(toImmutableList()));
    }

    /**
     * Setter for contents.
     *
     * <p>contents: Multi-turn contents that represents the Prompt.
     */
    @JsonProperty("contents")
    public abstract Builder contents(List<Content> contents);

    /**
     * Setter for contents.
     *
     * <p>contents: Multi-turn contents that represents the Prompt.
     */
    public Builder contents(Content... contents) {
      return contents(Arrays.asList(contents));
    }

    /**
     * Setter for contents builder.
     *
     * <p>contents: Multi-turn contents that represents the Prompt.
     */
    public Builder contents(Content.Builder... contentsBuilders) {
      return contents(
          Arrays.asList(contentsBuilders).stream()
              .map(Content.Builder::build)
              .collect(toImmutableList()));
    }

    public abstract GeminiPreferenceExample build();
  }

  /** Deserializes a JSON string to a GeminiPreferenceExample object. */
  @ExcludeFromGeneratedCoverageReport
  public static GeminiPreferenceExample fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GeminiPreferenceExample.class);
  }
}
