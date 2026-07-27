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

/** Information about a single recognized word. */
@AutoValue
@JsonDeserialize(builder = WordInfo.Builder.class)
public abstract class WordInfo extends JsonSerializable {
  /** Transcript of the word. */
  @JsonProperty("word")
  public abstract Optional<String> word();

  /** Start offset in time of the word relative to the start of the audio. */
  @JsonProperty("startOffset")
  public abstract Optional<String> startOffset();

  /** End offset in time of the word relative to the start of the audio. */
  @JsonProperty("endOffset")
  public abstract Optional<String> endOffset();

  /** Instantiates a builder for WordInfo. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_WordInfo.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for WordInfo. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `WordInfo.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_WordInfo.Builder();
    }

    /**
     * Setter for word.
     *
     * <p>word: Transcript of the word.
     */
    @JsonProperty("word")
    public abstract Builder word(String word);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder word(Optional<String> word);

    /** Clears the value of word field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearWord() {
      return word(Optional.empty());
    }

    /**
     * Setter for startOffset.
     *
     * <p>startOffset: Start offset in time of the word relative to the start of the audio.
     */
    @JsonProperty("startOffset")
    public abstract Builder startOffset(String startOffset);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder startOffset(Optional<String> startOffset);

    /** Clears the value of startOffset field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearStartOffset() {
      return startOffset(Optional.empty());
    }

    /**
     * Setter for endOffset.
     *
     * <p>endOffset: End offset in time of the word relative to the start of the audio.
     */
    @JsonProperty("endOffset")
    public abstract Builder endOffset(String endOffset);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder endOffset(Optional<String> endOffset);

    /** Clears the value of endOffset field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearEndOffset() {
      return endOffset(Optional.empty());
    }

    public abstract WordInfo build();
  }

  /** Deserializes a JSON string to a WordInfo object. */
  @ExcludeFromGeneratedCoverageReport
  public static WordInfo fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, WordInfo.class);
  }
}
