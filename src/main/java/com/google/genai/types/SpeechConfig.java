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

/** The speech generation config. */
@AutoValue
@JsonDeserialize(builder = SpeechConfig.Builder.class)
public abstract class SpeechConfig extends JsonSerializable {
  /** Optional. Language code (ISO 639. e.g. en-US) for the speech synthesization. */
  @JsonProperty("languageCode")
  public abstract Optional<String> languageCode();

  /** The configuration for the speaker to use. */
  @JsonProperty("voiceConfig")
  public abstract Optional<VoiceConfig> voiceConfig();

  /**
   * Optional. The configuration for the multi-speaker setup. It is mutually exclusive with the
   * voice_config field. This field is not supported in Vertex AI.
   */
  @JsonProperty("multiSpeakerVoiceConfig")
  public abstract Optional<MultiSpeakerVoiceConfig> multiSpeakerVoiceConfig();

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
    /** For internal usage. Please use `SpeechConfig.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_SpeechConfig.Builder();
    }

    /**
     * Setter for languageCode.
     *
     * <p>languageCode: Optional. Language code (ISO 639. e.g. en-US) for the speech synthesization.
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
     * Setter for voiceConfig.
     *
     * <p>voiceConfig: The configuration for the speaker to use.
     */
    @JsonProperty("voiceConfig")
    public abstract Builder voiceConfig(VoiceConfig voiceConfig);

    /**
     * Setter for voiceConfig builder.
     *
     * <p>voiceConfig: The configuration for the speaker to use.
     */
    @CanIgnoreReturnValue
    public Builder voiceConfig(VoiceConfig.Builder voiceConfigBuilder) {
      return voiceConfig(voiceConfigBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder voiceConfig(Optional<VoiceConfig> voiceConfig);

    /** Clears the value of voiceConfig field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearVoiceConfig() {
      return voiceConfig(Optional.empty());
    }

    /**
     * Setter for multiSpeakerVoiceConfig.
     *
     * <p>multiSpeakerVoiceConfig: Optional. The configuration for the multi-speaker setup. It is
     * mutually exclusive with the voice_config field. This field is not supported in Vertex AI.
     */
    @JsonProperty("multiSpeakerVoiceConfig")
    public abstract Builder multiSpeakerVoiceConfig(
        MultiSpeakerVoiceConfig multiSpeakerVoiceConfig);

    /**
     * Setter for multiSpeakerVoiceConfig builder.
     *
     * <p>multiSpeakerVoiceConfig: Optional. The configuration for the multi-speaker setup. It is
     * mutually exclusive with the voice_config field. This field is not supported in Vertex AI.
     */
    @CanIgnoreReturnValue
    public Builder multiSpeakerVoiceConfig(
        MultiSpeakerVoiceConfig.Builder multiSpeakerVoiceConfigBuilder) {
      return multiSpeakerVoiceConfig(multiSpeakerVoiceConfigBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder multiSpeakerVoiceConfig(
        Optional<MultiSpeakerVoiceConfig> multiSpeakerVoiceConfig);

    /** Clears the value of multiSpeakerVoiceConfig field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearMultiSpeakerVoiceConfig() {
      return multiSpeakerVoiceConfig(Optional.empty());
    }

    public abstract SpeechConfig build();
  }

  /** Deserializes a JSON string to a SpeechConfig object. */
  @ExcludeFromGeneratedCoverageReport
  public static SpeechConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, SpeechConfig.class);
  }
}
