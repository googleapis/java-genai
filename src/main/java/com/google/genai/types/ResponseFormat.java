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
 * Configuration for the model to configure output formatting and delivery. This data type is not
 * supported in Gemini API.
 */
@AutoValue
@JsonDeserialize(builder = ResponseFormat.Builder.class)
public abstract class ResponseFormat extends JsonSerializable {
  /** Audio output format. */
  @JsonProperty("audio")
  public abstract Optional<AudioResponseFormat> audio();

  /** Image output format. */
  @JsonProperty("image")
  public abstract Optional<ImageResponseFormat> image();

  /** Text output format. */
  @JsonProperty("text")
  public abstract Optional<TextResponseFormat> text();

  /** Video output format. */
  @JsonProperty("video")
  public abstract Optional<VideoResponseFormat> video();

  /** Instantiates a builder for ResponseFormat. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ResponseFormat.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ResponseFormat. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `ResponseFormat.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ResponseFormat.Builder();
    }

    /**
     * Setter for audio.
     *
     * <p>audio: Audio output format.
     */
    @JsonProperty("audio")
    public abstract Builder audio(AudioResponseFormat audio);

    /**
     * Setter for audio builder.
     *
     * <p>audio: Audio output format.
     */
    @CanIgnoreReturnValue
    public Builder audio(AudioResponseFormat.Builder audioBuilder) {
      return audio(audioBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder audio(Optional<AudioResponseFormat> audio);

    /** Clears the value of audio field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearAudio() {
      return audio(Optional.empty());
    }

    /**
     * Setter for image.
     *
     * <p>image: Image output format.
     */
    @JsonProperty("image")
    public abstract Builder image(ImageResponseFormat image);

    /**
     * Setter for image builder.
     *
     * <p>image: Image output format.
     */
    @CanIgnoreReturnValue
    public Builder image(ImageResponseFormat.Builder imageBuilder) {
      return image(imageBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder image(Optional<ImageResponseFormat> image);

    /** Clears the value of image field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearImage() {
      return image(Optional.empty());
    }

    /**
     * Setter for text.
     *
     * <p>text: Text output format.
     */
    @JsonProperty("text")
    public abstract Builder text(TextResponseFormat text);

    /**
     * Setter for text builder.
     *
     * <p>text: Text output format.
     */
    @CanIgnoreReturnValue
    public Builder text(TextResponseFormat.Builder textBuilder) {
      return text(textBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder text(Optional<TextResponseFormat> text);

    /** Clears the value of text field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearText() {
      return text(Optional.empty());
    }

    /**
     * Setter for video.
     *
     * <p>video: Video output format.
     */
    @JsonProperty("video")
    public abstract Builder video(VideoResponseFormat video);

    /**
     * Setter for video builder.
     *
     * <p>video: Video output format.
     */
    @CanIgnoreReturnValue
    public Builder video(VideoResponseFormat.Builder videoBuilder) {
      return video(videoBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder video(Optional<VideoResponseFormat> video);

    /** Clears the value of video field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearVideo() {
      return video(Optional.empty());
    }

    public abstract ResponseFormat build();
  }

  /** Deserializes a JSON string to a ResponseFormat object. */
  @ExcludeFromGeneratedCoverageReport
  public static ResponseFormat fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ResponseFormat.class);
  }
}
