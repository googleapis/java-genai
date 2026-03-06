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

package com.google.genai.types.interactions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import com.google.genai.types.ExcludeFromGeneratedCoverageReport;
import java.util.Optional;

/**
 * Configuration for image generation in the Interactions API.
 *
 * <p>This class controls the visual properties of generated images, including aspect ratio and
 * resolution. It is specific to the Interactions API and provides a simplified interface compared
 * to image configuration in other APIs.
 *
 * <p>The Interactions API is available in both Vertex AI and Gemini API.
 *
 * <p>Supported aspect ratios include:
 *
 * <ul>
 *   <li>"1:1" - Square format
 *   <li>"2:3", "3:2" - Standard photo formats
 *   <li>"3:4", "4:3" - Traditional screen formats
 *   <li>"9:16", "16:9" - Mobile and widescreen formats
 *   <li>"21:9" - Ultra-widescreen format
 * </ul>
 *
 * <p>Supported image sizes: "1K", "2K", "4K"
 *
 * <p>Example usage:
 *
 * <pre>{@code
 * // Configure square images at 2K resolution
 * ImageConfig imageConfig = ImageConfig.builder()
 *     .aspectRatio("1:1")
 *     .imageSize("2K")
 *     .build();
 *
 * // Configure widescreen 4K images
 * ImageConfig widescreenConfig = ImageConfig.builder()
 *     .aspectRatio("16:9")
 *     .imageSize("4K")
 *     .build();
 *
 * // Use in generation config
 * GenerationConfig config = GenerationConfig.builder()
 *     .imageConfig(imageConfig)
 *     .build();
 * }</pre>
 */
@AutoValue
@JsonDeserialize(builder = ImageConfig.Builder.class)
public abstract class ImageConfig extends JsonSerializable {

  /**
   * Aspect ratio of the generated images.
   *
   * <p>Supported values:
   *
   * <ul>
   *   <li>{@code "1:1"} - Square (1:1)
   *   <li>{@code "2:3"} - Portrait (2:3)
   *   <li>{@code "3:2"} - Landscape (3:2)
   *   <li>{@code "3:4"} - Portrait (3:4)
   *   <li>{@code "4:3"} - Landscape (4:3)
   *   <li>{@code "4:5"} - Portrait (4:5)
   *   <li>{@code "5:4"} - Landscape (5:4)
   *   <li>{@code "9:16"} - Vertical video (9:16)
   *   <li>{@code "16:9"} - Horizontal video (16:9)
   *   <li>{@code "21:9"} - Ultra-wide (21:9)
   * </ul>
   *
   * <p>This field accepts any string value to support future aspect ratios.
   */
  @JsonProperty("aspect_ratio")
  public abstract Optional<String> aspectRatio();

  /**
   * Specifies the size of generated images.
   *
   * <p>Supported values:
   *
   * <ul>
   *   <li>{@code "1K"} - 1024x1024 pixels (default)
   *   <li>{@code "2K"} - 2048x2048 pixels
   *   <li>{@code "4K"} - 4096x4096 pixels
   * </ul>
   *
   * <p>If not specified, the model will use default value {@code "1K"}.
   *
   * <p>This field accepts any string value to support future image sizes.
   */
  @JsonProperty("image_size")
  public abstract Optional<String> imageSize();

  /** Instantiates a builder for ImageConfig. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ImageConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ImageConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use {@code ImageConfig.builder()} for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ImageConfig.Builder();
    }

    /**
     * Setter for aspectRatio.
     *
     * <p>aspectRatio: Aspect ratio of the generated images.
     *
     * <p>Supported values: "1:1", "2:3", "3:2", "3:4", "4:3", "4:5", "5:4", "9:16", "16:9", "21:9".
     */
    @JsonProperty("aspect_ratio")
    public abstract Builder aspectRatio(String aspectRatio);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder aspectRatio(Optional<String> aspectRatio);

    /** Clears the value of aspectRatio field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearAspectRatio() {
      return aspectRatio(Optional.empty());
    }

    /**
     * Setter for imageSize.
     *
     * <p>imageSize: Size of the generated images. Supported values are "1K", "2K", and "4K".
     */
    @JsonProperty("image_size")
    public abstract Builder imageSize(String imageSize);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder imageSize(Optional<String> imageSize);

    /** Clears the value of imageSize field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearImageSize() {
      return imageSize(Optional.empty());
    }

    public abstract ImageConfig build();
  }

  /** Deserializes a JSON string to an ImageConfig object. */
  @ExcludeFromGeneratedCoverageReport
  public static ImageConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ImageConfig.class);
  }
}
