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

package com.google.genai.types.interactions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import com.google.genai.types.ExcludeFromGeneratedCoverageReport;
import java.util.Optional;

/**
 * Configuration for speech generation in the Interactions API.
 *
 * <p>This class controls how the model generates speech output, including voice selection, language
 * preferences, and speaker identification for multi-speaker scenarios. It is specific to the
 * Interactions API and has a simpler structure than speech configuration classes used in other
 * APIs.
 *
 * <p>The Interactions API is available in both Vertex AI and Gemini API.
 *
 * <p>Example usage:
 *
 * <pre>{@code
 * // Configure speech with all options
 * SpeechConfig speechConfig = SpeechConfig.builder()
 *     .voice("en-US-Studio-O")
 *     .language("en-US")
 *     .speaker("speaker1")
 *     .build();
 *
 * // Use in generation config
 * GenerationConfig config = GenerationConfig.builder()
 *     .speechConfig(speechConfig)
 *     .build();
 *
 * // Or use builder convenience method
 * GenerationConfig config2 = GenerationConfig.builder()
 *     .speechConfig(SpeechConfig.builder().voice("en-GB-Studio-B"))
 *     .build();
 * }</pre>
 */
@AutoValue
@JsonDeserialize(builder = SpeechConfig.Builder.class)
public abstract class SpeechConfig extends JsonSerializable {

  /** The voice to use for speech generation. */
  @JsonProperty("voice")
  public abstract Optional<String> voice();

  /** The language code for speech generation (e.g., "en-US"). */
  @JsonProperty("language")
  public abstract Optional<String> language();

  /** The speaker identifier for multi-speaker scenarios. */
  @JsonProperty("speaker")
  public abstract Optional<String> speaker();

  /** Instantiates a builder for SpeechConfig. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_SpeechConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for SpeechConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use {@code SpeechConfig.builder()} for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_SpeechConfig.Builder();
    }

    /**
     * Setter for voice.
     *
     * <p>voice: The voice to use for speech generation.
     */
    @JsonProperty("voice")
    public abstract Builder voice(String voice);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder voice(Optional<String> voice);

    /** Clears the value of voice field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearVoice() {
      return voice(Optional.empty());
    }

    /**
     * Setter for language.
     *
     * <p>language: The language code for speech generation (e.g., "en-US").
     */
    @JsonProperty("language")
    public abstract Builder language(String language);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder language(Optional<String> language);

    /** Clears the value of language field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearLanguage() {
      return language(Optional.empty());
    }

    /**
     * Setter for speaker.
     *
     * <p>speaker: The speaker identifier for multi-speaker scenarios.
     */
    @JsonProperty("speaker")
    public abstract Builder speaker(String speaker);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder speaker(Optional<String> speaker);

    /** Clears the value of speaker field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearSpeaker() {
      return speaker(Optional.empty());
    }

    public abstract SpeechConfig build();
  }

  /** Deserializes a JSON string to an SpeechConfig object. */
  @ExcludeFromGeneratedCoverageReport
  public static SpeechConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, SpeechConfig.class);
  }
}
