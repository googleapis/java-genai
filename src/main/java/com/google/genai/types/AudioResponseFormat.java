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

/** Configuration for audio-specific output formatting. */
@AutoValue
@JsonDeserialize(builder = AudioResponseFormat.Builder.class)
public abstract class AudioResponseFormat extends JsonSerializable {
  /**
   * Optional. Bit rate in bits per second (bps). Only applicable for compressed formats (MP3,
   * Opus).
   */
  @JsonProperty("bitRate")
  public abstract Optional<Integer> bitRate();

  /** Optional. Delivery mode for the generated content. */
  @JsonProperty("delivery")
  public abstract Optional<Delivery> delivery();

  /** Optional. The MIME type of the audio output. */
  @JsonProperty("mimeType")
  public abstract Optional<String> mimeType();

  /** Optional. Sample rate for the generated audio in Hertz. */
  @JsonProperty("sampleRate")
  public abstract Optional<Integer> sampleRate();

  /** Instantiates a builder for AudioResponseFormat. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_AudioResponseFormat.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for AudioResponseFormat. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `AudioResponseFormat.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_AudioResponseFormat.Builder();
    }

    /**
     * Setter for bitRate.
     *
     * <p>bitRate: Optional. Bit rate in bits per second (bps). Only applicable for compressed
     * formats (MP3, Opus).
     */
    @JsonProperty("bitRate")
    public abstract Builder bitRate(Integer bitRate);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder bitRate(Optional<Integer> bitRate);

    /** Clears the value of bitRate field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearBitRate() {
      return bitRate(Optional.empty());
    }

    /**
     * Setter for delivery.
     *
     * <p>delivery: Optional. Delivery mode for the generated content.
     */
    @JsonProperty("delivery")
    public abstract Builder delivery(Delivery delivery);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder delivery(Optional<Delivery> delivery);

    /** Clears the value of delivery field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearDelivery() {
      return delivery(Optional.empty());
    }

    /**
     * Setter for delivery given a known enum.
     *
     * <p>delivery: Optional. Delivery mode for the generated content.
     */
    @CanIgnoreReturnValue
    public Builder delivery(Delivery.Known knownType) {
      return delivery(new Delivery(knownType));
    }

    /**
     * Setter for delivery given a string.
     *
     * <p>delivery: Optional. Delivery mode for the generated content.
     */
    @CanIgnoreReturnValue
    public Builder delivery(String delivery) {
      return delivery(new Delivery(delivery));
    }

    /**
     * Setter for mimeType.
     *
     * <p>mimeType: Optional. The MIME type of the audio output.
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
     * Setter for sampleRate.
     *
     * <p>sampleRate: Optional. Sample rate for the generated audio in Hertz.
     */
    @JsonProperty("sampleRate")
    public abstract Builder sampleRate(Integer sampleRate);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder sampleRate(Optional<Integer> sampleRate);

    /** Clears the value of sampleRate field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearSampleRate() {
      return sampleRate(Optional.empty());
    }

    public abstract AudioResponseFormat build();
  }

  /** Deserializes a JSON string to a AudioResponseFormat object. */
  @ExcludeFromGeneratedCoverageReport
  public static AudioResponseFormat fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, AudioResponseFormat.class);
  }
}
