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

/** The image generation configuration to be used in GenerateContentConfig. */
@AutoValue
@JsonDeserialize(builder = ImageConfig.Builder.class)
public abstract class ImageConfig extends JsonSerializable {
  /**
   * Aspect ratio of the generated images. Supported values are "1:1", "2:3", "3:2", "3:4", "4:3",
   * "9:16", "16:9", and "21:9".
   */
  @JsonProperty("aspectRatio")
  public abstract Optional<String> aspectRatio();

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
    /** For internal usage. Please use `ImageConfig.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ImageConfig.Builder();
    }

    /**
     * Setter for aspectRatio.
     *
     * <p>aspectRatio: Aspect ratio of the generated images. Supported values are "1:1", "2:3",
     * "3:2", "3:4", "4:3", "9:16", "16:9", and "21:9".
     */
    @JsonProperty("aspectRatio")
    public abstract Builder aspectRatio(String aspectRatio);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder aspectRatio(Optional<String> aspectRatio);

    /** Clears the value of aspectRatio field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearAspectRatio() {
      return aspectRatio(Optional.empty());
    }

    public abstract ImageConfig build();
  }

  /** Deserializes a JSON string to a ImageConfig object. */
  @ExcludeFromGeneratedCoverageReport
  public static ImageConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ImageConfig.class);
  }
}
