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

/** A mask for video generation. */
@AutoValue
@JsonDeserialize(builder = VideoGenerationMask.Builder.class)
public abstract class VideoGenerationMask extends JsonSerializable {
  /** The image mask to use for generating videos. */
  @JsonProperty("image")
  public abstract Optional<Image> image();

  /**
   * Describes how the mask will be used. Inpainting masks must match the aspect ratio of the input
   * video. Outpainting masks can be either 9:16 or 16:9.
   */
  @JsonProperty("maskMode")
  public abstract Optional<String> maskMode();

  /** Instantiates a builder for VideoGenerationMask. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_VideoGenerationMask.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for VideoGenerationMask. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `VideoGenerationMask.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_VideoGenerationMask.Builder();
    }

    /**
     * Setter for image.
     *
     * <p>image: The image mask to use for generating videos.
     */
    @JsonProperty("image")
    public abstract Builder image(Image image);

    /**
     * Setter for image builder.
     *
     * <p>image: The image mask to use for generating videos.
     */
    public Builder image(Image.Builder imageBuilder) {
      return image(imageBuilder.build());
    }

    /**
     * Setter for maskMode.
     *
     * <p>maskMode: Describes how the mask will be used. Inpainting masks must match the aspect
     * ratio of the input video. Outpainting masks can be either 9:16 or 16:9.
     */
    @JsonProperty("maskMode")
    public abstract Builder maskMode(String maskMode);

    public abstract VideoGenerationMask build();
  }

  /** Deserializes a JSON string to a VideoGenerationMask object. */
  @ExcludeFromGeneratedCoverageReport
  public static VideoGenerationMask fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, VideoGenerationMask.class);
  }
}
