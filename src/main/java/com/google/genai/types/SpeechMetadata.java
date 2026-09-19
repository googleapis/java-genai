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

/** Extra metadata associated with the part for speech synthesis. */
@AutoValue
@JsonDeserialize(builder = SpeechMetadata.Builder.class)
public abstract class SpeechMetadata extends JsonSerializable {
  /**
   * The speaker for this part, which must match a `speaker` name in
   * `MultiSpeakerVoiceConfig.speaker_voice_configs`.
   */
  @JsonProperty("speaker")
  public abstract Optional<String> speaker();

  /** The style instruction for how the voice should be synthesized (e.g. "excited, fast-paced"). */
  @JsonProperty("style")
  public abstract Optional<String> style();

  /** Instantiates a builder for SpeechMetadata. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_SpeechMetadata.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for SpeechMetadata. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `SpeechMetadata.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_SpeechMetadata.Builder();
    }

    /**
     * Setter for speaker.
     *
     * <p>speaker: The speaker for this part, which must match a `speaker` name in
     * `MultiSpeakerVoiceConfig.speaker_voice_configs`.
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
     * Setter for style.
     *
     * <p>style: The style instruction for how the voice should be synthesized (e.g. "excited,
     * fast-paced").
     */
    @JsonProperty("style")
    public abstract Builder style(String style);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder style(Optional<String> style);

    /** Clears the value of style field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearStyle() {
      return style(Optional.empty());
    }

    public abstract SpeechMetadata build();
  }

  /** Deserializes a JSON string to a SpeechMetadata object. */
  @ExcludeFromGeneratedCoverageReport
  public static SpeechMetadata fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, SpeechMetadata.class);
  }
}
