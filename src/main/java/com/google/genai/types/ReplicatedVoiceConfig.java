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

/**
 * The configuration for the replicated voice to use. This data type is not supported in Gemini API.
 */
@AutoValue
@JsonDeserialize(builder = ReplicatedVoiceConfig.Builder.class)
public abstract class ReplicatedVoiceConfig extends JsonSerializable {
  /**
   * Optional. The mimetype of the voice sample. Currently only mime_type=audio/pcm is supported,
   * which is raw mono 16-bit signed little-endian pcm data, with 24k sampling rate.
   */
  @JsonProperty("mimeType")
  public abstract Optional<String> mimeType();

  /** Optional. The sample of the custom voice. */
  @JsonProperty("voiceSampleAudio")
  public abstract Optional<byte[]> voiceSampleAudio();

  /** Instantiates a builder for ReplicatedVoiceConfig. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ReplicatedVoiceConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ReplicatedVoiceConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `ReplicatedVoiceConfig.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ReplicatedVoiceConfig.Builder();
    }

    /**
     * Setter for mimeType.
     *
     * <p>mimeType: Optional. The mimetype of the voice sample. Currently only mime_type=audio/pcm
     * is supported, which is raw mono 16-bit signed little-endian pcm data, with 24k sampling rate.
     */
    @JsonProperty("mimeType")
    public abstract Builder mimeType(String mimeType);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder mimeType(Optional<String> mimeType);

    /** Clears the value of mimeType field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearMimeType() {
      return mimeType(Optional.empty());
    }

    /**
     * Setter for voiceSampleAudio.
     *
     * <p>voiceSampleAudio: Optional. The sample of the custom voice.
     */
    @JsonProperty("voiceSampleAudio")
    public abstract Builder voiceSampleAudio(byte[] voiceSampleAudio);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder voiceSampleAudio(Optional<byte[]> voiceSampleAudio);

    /** Clears the value of voiceSampleAudio field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearVoiceSampleAudio() {
      return voiceSampleAudio(Optional.empty());
    }

    public abstract ReplicatedVoiceConfig build();
  }

  /** Deserializes a JSON string to a ReplicatedVoiceConfig object. */
  @ExcludeFromGeneratedCoverageReport
  public static ReplicatedVoiceConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ReplicatedVoiceConfig.class);
  }
}
