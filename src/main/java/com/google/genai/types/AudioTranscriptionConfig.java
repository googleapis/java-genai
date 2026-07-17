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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/** The audio transcription configuration in Setup. */
@AutoValue
@JsonDeserialize(builder = AudioTranscriptionConfig.Builder.class)
public abstract class AudioTranscriptionConfig extends JsonSerializable {
  /** Deprecated: use LanguageAuto or LanguageHints instead. */
  @JsonProperty("languageCodes")
  public abstract Optional<List<String>> languageCodes();

  /** The model will detect the language automatically. Do not use together with LanguageHints. */
  @JsonProperty("languageAuto")
  public abstract Optional<LanguageAuto> languageAuto();

  /** Specifies one or more languages in the audio. Do not use together with LanguageAuto. */
  @JsonProperty("languageHints")
  public abstract Optional<LanguageHints> languageHints();

  /**
   * A list of custom vocabulary phrases, which biases the ASR model to improve recognition of these
   * specific terms.
   */
  @JsonProperty("customVocabulary")
  public abstract Optional<List<String>> customVocabulary();

  /**
   * Deprecated. A list of phrases used for speech adaptation, which biases the ASR model to improve
   * recognition of these specific terms.
   */
  @JsonProperty("adaptationPhrases")
  public abstract Optional<List<String>> adaptationPhrases();

  /** Instantiates a builder for AudioTranscriptionConfig. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_AudioTranscriptionConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for AudioTranscriptionConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `AudioTranscriptionConfig.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_AudioTranscriptionConfig.Builder();
    }

    /**
     * Setter for languageCodes.
     *
     * <p>languageCodes: Deprecated: use LanguageAuto or LanguageHints instead.
     */
    @JsonProperty("languageCodes")
    public abstract Builder languageCodes(List<String> languageCodes);

    /**
     * Setter for languageCodes.
     *
     * <p>languageCodes: Deprecated: use LanguageAuto or LanguageHints instead.
     */
    @CanIgnoreReturnValue
    public Builder languageCodes(String... languageCodes) {
      return languageCodes(Arrays.asList(languageCodes));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder languageCodes(Optional<List<String>> languageCodes);

    /** Clears the value of languageCodes field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearLanguageCodes() {
      return languageCodes(Optional.empty());
    }

    /**
     * Setter for languageAuto.
     *
     * <p>languageAuto: The model will detect the language automatically. Do not use together with
     * LanguageHints.
     */
    @JsonProperty("languageAuto")
    public abstract Builder languageAuto(LanguageAuto languageAuto);

    /**
     * Setter for languageAuto builder.
     *
     * <p>languageAuto: The model will detect the language automatically. Do not use together with
     * LanguageHints.
     */
    @CanIgnoreReturnValue
    public Builder languageAuto(LanguageAuto.Builder languageAutoBuilder) {
      return languageAuto(languageAutoBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder languageAuto(Optional<LanguageAuto> languageAuto);

    /** Clears the value of languageAuto field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearLanguageAuto() {
      return languageAuto(Optional.empty());
    }

    /**
     * Setter for languageHints.
     *
     * <p>languageHints: Specifies one or more languages in the audio. Do not use together with
     * LanguageAuto.
     */
    @JsonProperty("languageHints")
    public abstract Builder languageHints(LanguageHints languageHints);

    /**
     * Setter for languageHints builder.
     *
     * <p>languageHints: Specifies one or more languages in the audio. Do not use together with
     * LanguageAuto.
     */
    @CanIgnoreReturnValue
    public Builder languageHints(LanguageHints.Builder languageHintsBuilder) {
      return languageHints(languageHintsBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder languageHints(Optional<LanguageHints> languageHints);

    /** Clears the value of languageHints field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearLanguageHints() {
      return languageHints(Optional.empty());
    }

    /**
     * Setter for customVocabulary.
     *
     * <p>customVocabulary: A list of custom vocabulary phrases, which biases the ASR model to
     * improve recognition of these specific terms.
     */
    @JsonProperty("customVocabulary")
    public abstract Builder customVocabulary(List<String> customVocabulary);

    /**
     * Setter for customVocabulary.
     *
     * <p>customVocabulary: A list of custom vocabulary phrases, which biases the ASR model to
     * improve recognition of these specific terms.
     */
    @CanIgnoreReturnValue
    public Builder customVocabulary(String... customVocabulary) {
      return customVocabulary(Arrays.asList(customVocabulary));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder customVocabulary(Optional<List<String>> customVocabulary);

    /** Clears the value of customVocabulary field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearCustomVocabulary() {
      return customVocabulary(Optional.empty());
    }

    /**
     * Setter for adaptationPhrases.
     *
     * <p>adaptationPhrases: Deprecated. A list of phrases used for speech adaptation, which biases
     * the ASR model to improve recognition of these specific terms.
     */
    @JsonProperty("adaptationPhrases")
    public abstract Builder adaptationPhrases(List<String> adaptationPhrases);

    /**
     * Setter for adaptationPhrases.
     *
     * <p>adaptationPhrases: Deprecated. A list of phrases used for speech adaptation, which biases
     * the ASR model to improve recognition of these specific terms.
     */
    @CanIgnoreReturnValue
    public Builder adaptationPhrases(String... adaptationPhrases) {
      return adaptationPhrases(Arrays.asList(adaptationPhrases));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder adaptationPhrases(Optional<List<String>> adaptationPhrases);

    /** Clears the value of adaptationPhrases field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearAdaptationPhrases() {
      return adaptationPhrases(Optional.empty());
    }

    public abstract AudioTranscriptionConfig build();
  }

  /** Deserializes a JSON string to a AudioTranscriptionConfig object. */
  @ExcludeFromGeneratedCoverageReport
  public static AudioTranscriptionConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, AudioTranscriptionConfig.class);
  }
}
