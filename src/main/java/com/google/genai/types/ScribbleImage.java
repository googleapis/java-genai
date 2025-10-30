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

/** An image mask representing a brush scribble. */
@AutoValue
@JsonDeserialize(builder = ScribbleImage.Builder.class)
public abstract class ScribbleImage extends JsonSerializable {
  /** The brush scribble to guide segmentation. Valid for the interactive mode. */
  @JsonProperty("image")
  public abstract Optional<Image> image();

  /** Instantiates a builder for ScribbleImage. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ScribbleImage.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ScribbleImage. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `ScribbleImage.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ScribbleImage.Builder();
    }

    /**
     * Setter for image.
     *
     * <p>image: The brush scribble to guide segmentation. Valid for the interactive mode.
     */
    @JsonProperty("image")
    public abstract Builder image(Image image);

    /**
     * Setter for image builder.
     *
     * <p>image: The brush scribble to guide segmentation. Valid for the interactive mode.
     */
    public Builder image(Image.Builder imageBuilder) {
      return image(imageBuilder.build());
    }

    public abstract ScribbleImage build();
  }

  /** Deserializes a JSON string to a ScribbleImage object. */
  @ExcludeFromGeneratedCoverageReport
  public static ScribbleImage fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ScribbleImage.class);
  }
}
