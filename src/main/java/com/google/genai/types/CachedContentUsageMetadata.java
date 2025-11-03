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

/** Metadata on the usage of the cached content. */
@AutoValue
@JsonDeserialize(builder = CachedContentUsageMetadata.Builder.class)
public abstract class CachedContentUsageMetadata extends JsonSerializable {
  /** Duration of audio in seconds. This field is not supported in Gemini API. */
  @JsonProperty("audioDurationSeconds")
  public abstract Optional<Integer> audioDurationSeconds();

  /** Number of images. This field is not supported in Gemini API. */
  @JsonProperty("imageCount")
  public abstract Optional<Integer> imageCount();

  /** Number of text characters. This field is not supported in Gemini API. */
  @JsonProperty("textCount")
  public abstract Optional<Integer> textCount();

  /** Total number of tokens that the cached content consumes. */
  @JsonProperty("totalTokenCount")
  public abstract Optional<Integer> totalTokenCount();

  /** Duration of video in seconds. This field is not supported in Gemini API. */
  @JsonProperty("videoDurationSeconds")
  public abstract Optional<Integer> videoDurationSeconds();

  /** Instantiates a builder for CachedContentUsageMetadata. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_CachedContentUsageMetadata.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for CachedContentUsageMetadata. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `CachedContentUsageMetadata.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_CachedContentUsageMetadata.Builder();
    }

    /**
     * Setter for audioDurationSeconds.
     *
     * <p>audioDurationSeconds: Duration of audio in seconds. This field is not supported in Gemini
     * API.
     */
    @JsonProperty("audioDurationSeconds")
    public abstract Builder audioDurationSeconds(Integer audioDurationSeconds);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder audioDurationSeconds(Optional<Integer> audioDurationSeconds);

    /** Clears the value of audioDurationSeconds field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearAudioDurationSeconds() {
      return audioDurationSeconds(Optional.empty());
    }

    /**
     * Setter for imageCount.
     *
     * <p>imageCount: Number of images. This field is not supported in Gemini API.
     */
    @JsonProperty("imageCount")
    public abstract Builder imageCount(Integer imageCount);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder imageCount(Optional<Integer> imageCount);

    /** Clears the value of imageCount field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearImageCount() {
      return imageCount(Optional.empty());
    }

    /**
     * Setter for textCount.
     *
     * <p>textCount: Number of text characters. This field is not supported in Gemini API.
     */
    @JsonProperty("textCount")
    public abstract Builder textCount(Integer textCount);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder textCount(Optional<Integer> textCount);

    /** Clears the value of textCount field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTextCount() {
      return textCount(Optional.empty());
    }

    /**
     * Setter for totalTokenCount.
     *
     * <p>totalTokenCount: Total number of tokens that the cached content consumes.
     */
    @JsonProperty("totalTokenCount")
    public abstract Builder totalTokenCount(Integer totalTokenCount);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder totalTokenCount(Optional<Integer> totalTokenCount);

    /** Clears the value of totalTokenCount field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTotalTokenCount() {
      return totalTokenCount(Optional.empty());
    }

    /**
     * Setter for videoDurationSeconds.
     *
     * <p>videoDurationSeconds: Duration of video in seconds. This field is not supported in Gemini
     * API.
     */
    @JsonProperty("videoDurationSeconds")
    public abstract Builder videoDurationSeconds(Integer videoDurationSeconds);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder videoDurationSeconds(Optional<Integer> videoDurationSeconds);

    /** Clears the value of videoDurationSeconds field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearVideoDurationSeconds() {
      return videoDurationSeconds(Optional.empty());
    }

    public abstract CachedContentUsageMetadata build();
  }

  /** Deserializes a JSON string to a CachedContentUsageMetadata object. */
  @ExcludeFromGeneratedCoverageReport
  public static CachedContentUsageMetadata fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, CachedContentUsageMetadata.class);
  }
}
