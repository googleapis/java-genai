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

/** Configuration for image-specific output formatting. */
@AutoValue
@JsonDeserialize(builder = ImageResponseFormat.Builder.class)
public abstract class ImageResponseFormat extends JsonSerializable {
  /** Optional. The aspect ratio for the image output. */
  @JsonProperty("aspectRatio")
  public abstract Optional<AspectRatio> aspectRatio();

  /** Optional. Delivery mode for the generated content. */
  @JsonProperty("delivery")
  public abstract Optional<Delivery> delivery();

  /** Optional. The size of the image output. */
  @JsonProperty("imageSize")
  public abstract Optional<ImageSize> imageSize();

  /** Optional. The MIME type of the image output. */
  @JsonProperty("mimeType")
  public abstract Optional<String> mimeType();

  /** Instantiates a builder for ImageResponseFormat. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ImageResponseFormat.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ImageResponseFormat. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `ImageResponseFormat.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ImageResponseFormat.Builder();
    }

    /**
     * Setter for aspectRatio.
     *
     * <p>aspectRatio: Optional. The aspect ratio for the image output.
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
     * <p>aspectRatio: Optional. The aspect ratio for the image output.
     */
    @CanIgnoreReturnValue
    public Builder aspectRatio(AspectRatio.Known knownType) {
      return aspectRatio(new AspectRatio(knownType));
    }

    /**
     * Setter for aspectRatio given a string.
     *
     * <p>aspectRatio: Optional. The aspect ratio for the image output.
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
     * Setter for imageSize.
     *
     * <p>imageSize: Optional. The size of the image output.
     */
    @JsonProperty("imageSize")
    public abstract Builder imageSize(ImageSize imageSize);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder imageSize(Optional<ImageSize> imageSize);

    /** Clears the value of imageSize field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearImageSize() {
      return imageSize(Optional.empty());
    }

    /**
     * Setter for imageSize given a known enum.
     *
     * <p>imageSize: Optional. The size of the image output.
     */
    @CanIgnoreReturnValue
    public Builder imageSize(ImageSize.Known knownType) {
      return imageSize(new ImageSize(knownType));
    }

    /**
     * Setter for imageSize given a string.
     *
     * <p>imageSize: Optional. The size of the image output.
     */
    @CanIgnoreReturnValue
    public Builder imageSize(String imageSize) {
      return imageSize(new ImageSize(imageSize));
    }

    /**
     * Setter for mimeType.
     *
     * <p>mimeType: Optional. The MIME type of the image output.
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

    public abstract ImageResponseFormat build();
  }

  /** Deserializes a JSON string to a ImageResponseFormat object. */
  @ExcludeFromGeneratedCoverageReport
  public static ImageResponseFormat fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ImageResponseFormat.class);
  }
}
