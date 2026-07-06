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
 * Configuration for video-specific output formatting. This data type is not supported in Gemini
 * API.
 */
@AutoValue
@JsonDeserialize(builder = VideoResponseFormat.Builder.class)
public abstract class VideoResponseFormat extends JsonSerializable {
  /** The aspect ratio for the video output. */
  @JsonProperty("aspectRatio")
  public abstract Optional<AspectRatio> aspectRatio();

  /** Optional. Delivery mode for the generated content. */
  @JsonProperty("delivery")
  public abstract Optional<Delivery> delivery();

  /** Optional. The duration for the video output. */
  @JsonProperty("duration")
  public abstract Optional<Duration> duration();

  /**
   * Optional. The Google Cloud Storage URI to store the video output. Required for Vertex if
   * delivery is URI.
   */
  @JsonProperty("gcsUri")
  public abstract Optional<String> gcsUri();

  /** Instantiates a builder for VideoResponseFormat. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_VideoResponseFormat.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for VideoResponseFormat. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `VideoResponseFormat.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_VideoResponseFormat.Builder();
    }

    /**
     * Setter for aspectRatio.
     *
     * <p>aspectRatio: The aspect ratio for the video output.
     */
    @JsonProperty("aspectRatio")
    public abstract Builder aspectRatio(AspectRatio aspectRatio);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder aspectRatio(Optional<AspectRatio> aspectRatio);

    /** Clears the value of aspectRatio field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearAspectRatio() {
      return aspectRatio(Optional.empty());
    }

    /**
     * Setter for aspectRatio given a known enum.
     *
     * <p>aspectRatio: The aspect ratio for the video output.
     */
    @CanIgnoreReturnValue
    public Builder aspectRatio(AspectRatio.Known knownType) {
      return aspectRatio(new AspectRatio(knownType));
    }

    /**
     * Setter for aspectRatio given a string.
     *
     * <p>aspectRatio: The aspect ratio for the video output.
     */
    @CanIgnoreReturnValue
    public Builder aspectRatio(String aspectRatio) {
      return aspectRatio(new AspectRatio(aspectRatio));
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
     * Setter for duration.
     *
     * <p>duration: Optional. The duration for the video output.
     */
    @JsonProperty("duration")
    public abstract Builder duration(Duration duration);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder duration(Optional<Duration> duration);

    /** Clears the value of duration field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearDuration() {
      return duration(Optional.empty());
    }

    /**
     * Setter for gcsUri.
     *
     * <p>gcsUri: Optional. The Google Cloud Storage URI to store the video output. Required for
     * Vertex if delivery is URI.
     */
    @JsonProperty("gcsUri")
    public abstract Builder gcsUri(String gcsUri);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder gcsUri(Optional<String> gcsUri);

    /** Clears the value of gcsUri field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearGcsUri() {
      return gcsUri(Optional.empty());
    }

    public abstract VideoResponseFormat build();
  }

  /** Deserializes a JSON string to a VideoResponseFormat object. */
  @ExcludeFromGeneratedCoverageReport
  public static VideoResponseFormat fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, VideoResponseFormat.class);
  }
}
