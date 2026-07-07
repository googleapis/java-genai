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

/** Config for stream translation. */
@AutoValue
@JsonDeserialize(builder = TranslationConfig.Builder.class)
public abstract class TranslationConfig extends JsonSerializable {
  /**
   * If true, the model will generate audio when the target language is spoken, essentially it will
   * parrot the input. If false, we will not produce audio for the target language.
   */
  @JsonProperty("echoTargetLanguage")
  public abstract Optional<Boolean> echoTargetLanguage();

  /**
   * The target language for translation. Supported values are BCP-47 language codes (e.g. "en",
   * "es", "fr").
   */
  @JsonProperty("targetLanguageCode")
  public abstract Optional<String> targetLanguageCode();

  /** Instantiates a builder for TranslationConfig. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_TranslationConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for TranslationConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `TranslationConfig.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_TranslationConfig.Builder();
    }

    /**
     * Setter for echoTargetLanguage.
     *
     * <p>echoTargetLanguage: If true, the model will generate audio when the target language is
     * spoken, essentially it will parrot the input. If false, we will not produce audio for the
     * target language.
     */
    @JsonProperty("echoTargetLanguage")
    public abstract Builder echoTargetLanguage(boolean echoTargetLanguage);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder echoTargetLanguage(Optional<Boolean> echoTargetLanguage);

    /** Clears the value of echoTargetLanguage field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearEchoTargetLanguage() {
      return echoTargetLanguage(Optional.empty());
    }

    /**
     * Setter for targetLanguageCode.
     *
     * <p>targetLanguageCode: The target language for translation. Supported values are BCP-47
     * language codes (e.g. "en", "es", "fr").
     */
    @JsonProperty("targetLanguageCode")
    public abstract Builder targetLanguageCode(String targetLanguageCode);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder targetLanguageCode(Optional<String> targetLanguageCode);

    /** Clears the value of targetLanguageCode field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTargetLanguageCode() {
      return targetLanguageCode(Optional.empty());
    }

    public abstract TranslationConfig build();
  }

  /** Deserializes a JSON string to a TranslationConfig object. */
  @ExcludeFromGeneratedCoverageReport
  public static TranslationConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, TranslationConfig.class);
  }
}
