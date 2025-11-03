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

/** Completion and its preference score. This data type is not supported in Gemini API. */
@AutoValue
@JsonDeserialize(builder = GeminiPreferenceExampleCompletion.Builder.class)
public abstract class GeminiPreferenceExampleCompletion extends JsonSerializable {
  /** Single turn completion for the given prompt. */
  @JsonProperty("completion")
  public abstract Optional<Content> completion();

  /** The score for the given completion. */
  @JsonProperty("score")
  public abstract Optional<Float> score();

  /** Instantiates a builder for GeminiPreferenceExampleCompletion. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_GeminiPreferenceExampleCompletion.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GeminiPreferenceExampleCompletion. */
  @AutoValue.Builder
  public abstract static class Builder {
    /**
     * For internal usage. Please use `GeminiPreferenceExampleCompletion.builder()` for
     * instantiation.
     */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_GeminiPreferenceExampleCompletion.Builder();
    }

    /**
     * Setter for completion.
     *
     * <p>completion: Single turn completion for the given prompt.
     */
    @JsonProperty("completion")
    public abstract Builder completion(Content completion);

    /**
     * Setter for completion builder.
     *
     * <p>completion: Single turn completion for the given prompt.
     */
    @CanIgnoreReturnValue
    public Builder completion(Content.Builder completionBuilder) {
      return completion(completionBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder completion(Optional<Content> completion);

    /** Clears the value of completion field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearCompletion() {
      return completion(Optional.empty());
    }

    /**
     * Setter for score.
     *
     * <p>score: The score for the given completion.
     */
    @JsonProperty("score")
    public abstract Builder score(Float score);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder score(Optional<Float> score);

    /** Clears the value of score field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearScore() {
      return score(Optional.empty());
    }

    public abstract GeminiPreferenceExampleCompletion build();
  }

  /** Deserializes a JSON string to a GeminiPreferenceExampleCompletion object. */
  @ExcludeFromGeneratedCoverageReport
  public static GeminiPreferenceExampleCompletion fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GeminiPreferenceExampleCompletion.class);
  }
}
