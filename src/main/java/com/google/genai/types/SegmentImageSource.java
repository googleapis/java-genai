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
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** A set of source input(s) for image segmentation. */
@AutoValue
@JsonDeserialize(builder = SegmentImageSource.Builder.class)
public abstract class SegmentImageSource extends JsonSerializable {
  /**
   * A text prompt for guiding the model during image segmentation. Required for prompt mode and
   * semantic mode, disallowed for other modes.
   */
  @JsonProperty("prompt")
  public abstract Optional<String> prompt();

  /** The image to be segmented. */
  @JsonProperty("image")
  public abstract Optional<Image> image();

  /**
   * The brush scribble to guide segmentation. Required for the interactive mode, disallowed for
   * other modes.
   */
  @JsonProperty("scribbleImage")
  public abstract Optional<ScribbleImage> scribbleImage();

  /** Instantiates a builder for SegmentImageSource. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_SegmentImageSource.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for SegmentImageSource. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `SegmentImageSource.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_SegmentImageSource.Builder();
    }

    /**
     * Setter for prompt.
     *
     * <p>prompt: A text prompt for guiding the model during image segmentation. Required for prompt
     * mode and semantic mode, disallowed for other modes.
     */
    @JsonProperty("prompt")
    public abstract Builder prompt(String prompt);

    /**
     * Setter for image.
     *
     * <p>image: The image to be segmented.
     */
    @JsonProperty("image")
    public abstract Builder image(Image image);

    /**
     * Setter for image builder.
     *
     * <p>image: The image to be segmented.
     */
    public Builder image(Image.Builder imageBuilder) {
      return image(imageBuilder.build());
    }

    /**
     * Setter for scribbleImage.
     *
     * <p>scribbleImage: The brush scribble to guide segmentation. Required for the interactive
     * mode, disallowed for other modes.
     */
    @JsonProperty("scribbleImage")
    public abstract Builder scribbleImage(ScribbleImage scribbleImage);

    /**
     * Setter for scribbleImage builder.
     *
     * <p>scribbleImage: The brush scribble to guide segmentation. Required for the interactive
     * mode, disallowed for other modes.
     */
    public Builder scribbleImage(ScribbleImage.Builder scribbleImageBuilder) {
      return scribbleImage(scribbleImageBuilder.build());
    }

    public abstract SegmentImageSource build();
  }

  /** Deserializes a JSON string to a SegmentImageSource object. */
  @ExcludeFromGeneratedCoverageReport
  public static SegmentImageSource fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, SegmentImageSource.class);
  }
}
