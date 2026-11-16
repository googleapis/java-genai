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
 * Provides metadata for a video, including the start and end offsets for clipping and the frame
 * rate.
 */
@AutoValue
@JsonDeserialize(builder = VideoMetadata.Builder.class)
public abstract class VideoMetadata extends JsonSerializable {
  /** Optional. The end offset of the video. */
  @JsonProperty("endOffset")
  public abstract Optional<Duration> endOffset();

  /**
   * Optional. The frame rate of the video sent to the model. If not specified, the default value is
   * 1.0. The valid range is (0.0, 24.0].
   */
  @JsonProperty("fps")
  public abstract Optional<Double> fps();

  /** Optional. The start offset of the video. */
  @JsonProperty("startOffset")
  public abstract Optional<Duration> startOffset();

  /** Instantiates a builder for VideoMetadata. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_VideoMetadata.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for VideoMetadata. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `VideoMetadata.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_VideoMetadata.Builder();
    }

    /**
     * Setter for endOffset.
     *
     * <p>endOffset: Optional. The end offset of the video.
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

    /**
     * Setter for fps.
     *
     * <p>fps: Optional. The frame rate of the video sent to the model. If not specified, the
     * default value is 1.0. The valid range is (0.0, 24.0].
     */
    @JsonProperty("fps")
    public abstract Builder fps(Double fps);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder fps(Optional<Double> fps);

    /** Clears the value of fps field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearFps() {
      return fps(Optional.empty());
    }

    /**
     * Setter for startOffset.
     *
     * <p>startOffset: Optional. The start offset of the video.
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

    public abstract VideoMetadata build();
  }

  /** Deserializes a JSON string to a VideoMetadata object. */
  @ExcludeFromGeneratedCoverageReport
  public static VideoMetadata fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, VideoMetadata.class);
  }
}
