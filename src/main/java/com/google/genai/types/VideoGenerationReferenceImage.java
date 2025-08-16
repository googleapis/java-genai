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

/** A reference image for video generation. */
@AutoValue
@JsonDeserialize(builder = VideoGenerationReferenceImage.Builder.class)
public abstract class VideoGenerationReferenceImage extends JsonSerializable {
  /** The reference image. */
  @JsonProperty("image")
  public abstract Optional<Image> image();

  /**
   * The type of the reference image, which defines how the reference image will be used to generate
   * the video. Supported values are 'asset' or 'style'.
   */
  @JsonProperty("referenceType")
  public abstract Optional<String> referenceType();

  /** Instantiates a builder for VideoGenerationReferenceImage. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_VideoGenerationReferenceImage.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for VideoGenerationReferenceImage. */
  @AutoValue.Builder
  public abstract static class Builder {
    /**
     * For internal usage. Please use `VideoGenerationReferenceImage.builder()` for instantiation.
     */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_VideoGenerationReferenceImage.Builder();
    }

    /**
     * Setter for image.
     *
     * <p>image: The reference image.
     */
    @JsonProperty("image")
    public abstract Builder image(Image image);

    /**
     * Setter for image builder.
     *
     * <p>image: The reference image.
     */
    public Builder image(Image.Builder imageBuilder) {
      return image(imageBuilder.build());
    }

    /**
     * Setter for referenceType.
     *
     * <p>referenceType: The type of the reference image, which defines how the reference image will
     * be used to generate the video. Supported values are 'asset' or 'style'.
     */
    @JsonProperty("referenceType")
    public abstract Builder referenceType(String referenceType);

    public abstract VideoGenerationReferenceImage build();
  }

  /** Deserializes a JSON string to a VideoGenerationReferenceImage object. */
  @ExcludeFromGeneratedCoverageReport
  public static VideoGenerationReferenceImage fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, VideoGenerationReferenceImage.class);
  }
}
