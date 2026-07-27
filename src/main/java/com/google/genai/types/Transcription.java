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
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/** Audio transcription in Server Content. */
@AutoValue
@JsonDeserialize(builder = Transcription.Builder.class)
public abstract class Transcription extends JsonSerializable {
  /** Optional. Transcription text. */
  @JsonProperty("text")
  public abstract Optional<String> text();

  /** Optional. The bool indicates the end of the transcription. */
  @JsonProperty("finished")
  public abstract Optional<Boolean> finished();

  /** The BCP-47 language code of the transcription. */
  @JsonProperty("languageCode")
  public abstract Optional<String> languageCode();

  /** A label identifying the speaker of this audio segment (e.g. "spk_1", "spk_2"). */
  @JsonProperty("speakerLabel")
  public abstract Optional<String> speakerLabel();

  /** Detailed word-level transcriptions and timing details. */
  @JsonProperty("words")
  public abstract Optional<List<WordInfo>> words();

  /** Instantiates a builder for Transcription. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_Transcription.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for Transcription. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `Transcription.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_Transcription.Builder();
    }

    /**
     * Setter for text.
     *
     * <p>text: Optional. Transcription text.
     */
    @JsonProperty("text")
    public abstract Builder text(String text);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder text(Optional<String> text);

    /** Clears the value of text field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearText() {
      return text(Optional.empty());
    }

    /**
     * Setter for finished.
     *
     * <p>finished: Optional. The bool indicates the end of the transcription.
     */
    @JsonProperty("finished")
    public abstract Builder finished(boolean finished);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder finished(Optional<Boolean> finished);

    /** Clears the value of finished field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearFinished() {
      return finished(Optional.empty());
    }

    /**
     * Setter for languageCode.
     *
     * <p>languageCode: The BCP-47 language code of the transcription.
     */
    @JsonProperty("languageCode")
    public abstract Builder languageCode(String languageCode);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder languageCode(Optional<String> languageCode);

    /** Clears the value of languageCode field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearLanguageCode() {
      return languageCode(Optional.empty());
    }

    /**
     * Setter for speakerLabel.
     *
     * <p>speakerLabel: A label identifying the speaker of this audio segment (e.g. "spk_1",
     * "spk_2").
     */
    @JsonProperty("speakerLabel")
    public abstract Builder speakerLabel(String speakerLabel);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder speakerLabel(Optional<String> speakerLabel);

    /** Clears the value of speakerLabel field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearSpeakerLabel() {
      return speakerLabel(Optional.empty());
    }

    /**
     * Setter for words.
     *
     * <p>words: Detailed word-level transcriptions and timing details.
     */
    @JsonProperty("words")
    public abstract Builder words(List<WordInfo> words);

    /**
     * Setter for words.
     *
     * <p>words: Detailed word-level transcriptions and timing details.
     */
    @CanIgnoreReturnValue
    public Builder words(WordInfo... words) {
      return words(Arrays.asList(words));
    }

    /**
     * Setter for words builder.
     *
     * <p>words: Detailed word-level transcriptions and timing details.
     */
    @CanIgnoreReturnValue
    public Builder words(WordInfo.Builder... wordsBuilders) {
      return words(
          Arrays.asList(wordsBuilders).stream()
              .map(WordInfo.Builder::build)
              .collect(toImmutableList()));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder words(Optional<List<WordInfo>> words);

    /** Clears the value of words field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearWords() {
      return words(Optional.empty());
    }

    public abstract Transcription build();
  }

  /** Deserializes a JSON string to a Transcription object. */
  @ExcludeFromGeneratedCoverageReport
  public static Transcription fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, Transcription.class);
  }
}
