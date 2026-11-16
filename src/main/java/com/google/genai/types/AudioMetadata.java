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
import java.time.Duration;
import java.util.Optional;

/**
 * Metadata describes the input audio content.
 *
 * <p>Set this in `Part.audio_metadata` or `FunctionResponsePart.audio_metadata` when `inline_data`
 * or `file_data` contains audio to configure static audio preprocessing.
 */
@AutoValue
@JsonDeserialize(builder = AudioMetadata.Builder.class)
public abstract class AudioMetadata extends JsonSerializable {
  /** The start offset of the audio, relative to the start of the audio stream. */
  @JsonProperty("startOffset")
  public abstract Optional<Duration> startOffset();

  /** The end offset of the audio, relative to the start of the audio stream. */
  @JsonProperty("endOffset")
  public abstract Optional<Duration> endOffset();

  /** Instantiates a builder for AudioMetadata. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_AudioMetadata.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for AudioMetadata. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `AudioMetadata.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_AudioMetadata.Builder();
    }

    /**
     * Setter for startOffset.
     *
     * <p>startOffset: The start offset of the audio, relative to the start of the audio stream.
     */
    @JsonProperty("startOffset")
    public abstract Builder startOffset(Duration startOffset);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder startOffset(Optional<Duration> startOffset);

    /** Clears the value of startOffset field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearStartOffset() {
      return startOffset(Optional.empty());
    }

    /**
     * Setter for endOffset.
     *
     * <p>endOffset: The end offset of the audio, relative to the start of the audio stream.
     */
    @JsonProperty("endOffset")
    public abstract Builder endOffset(Duration endOffset);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder endOffset(Optional<Duration> endOffset);

    /** Clears the value of endOffset field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearEndOffset() {
      return endOffset(Optional.empty());
    }

    public abstract AudioMetadata build();
  }

  /** Deserializes a JSON string to a AudioMetadata object. */
  @ExcludeFromGeneratedCoverageReport
  public static AudioMetadata fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, AudioMetadata.class);
  }
}
