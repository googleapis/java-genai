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

/** Configuration for a single speaker in a multi-speaker setup. */
@AutoValue
@JsonDeserialize(builder = SpeakerVoiceConfig.Builder.class)
public abstract class SpeakerVoiceConfig extends JsonSerializable {
  /**
   * Required. The name of the speaker. This should be the same as the speaker name used in the
   * prompt.
   */
  @JsonProperty("speaker")
  public abstract Optional<String> speaker();

  /** Required. The configuration for the voice of this speaker. */
  @JsonProperty("voiceConfig")
  public abstract Optional<VoiceConfig> voiceConfig();

  /** Instantiates a builder for SpeakerVoiceConfig. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_SpeakerVoiceConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for SpeakerVoiceConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `SpeakerVoiceConfig.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_SpeakerVoiceConfig.Builder();
    }

    /**
     * Setter for speaker.
     *
     * <p>speaker: Required. The name of the speaker. This should be the same as the speaker name
     * used in the prompt.
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

    /**
     * Setter for voiceConfig.
     *
     * <p>voiceConfig: Required. The configuration for the voice of this speaker.
     */
    @JsonProperty("voiceConfig")
    public abstract Builder voiceConfig(VoiceConfig voiceConfig);

    /**
     * Setter for voiceConfig builder.
     *
     * <p>voiceConfig: Required. The configuration for the voice of this speaker.
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

    public abstract SpeakerVoiceConfig build();
  }

  /** Deserializes a JSON string to a SpeakerVoiceConfig object. */
  @ExcludeFromGeneratedCoverageReport
  public static SpeakerVoiceConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, SpeakerVoiceConfig.class);
  }
}
