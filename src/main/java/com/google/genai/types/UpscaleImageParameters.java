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
import com.google.api.core.InternalApi;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** User-facing config UpscaleImageParameters. */
@AutoValue
@InternalApi
@JsonDeserialize(builder = UpscaleImageParameters.Builder.class)
public abstract class UpscaleImageParameters extends JsonSerializable {
  /** The model to use. */
  @JsonProperty("model")
  public abstract Optional<String> model();

  /** The input image to upscale. */
  @JsonProperty("image")
  public abstract Optional<Image> image();

  /** The factor to upscale the image (x2 or x4). */
  @JsonProperty("upscaleFactor")
  public abstract Optional<String> upscaleFactor();

  /** Configuration for upscaling. */
  @JsonProperty("config")
  public abstract Optional<UpscaleImageConfig> config();

  /** Instantiates a builder for UpscaleImageParameters. */
  public static Builder builder() {
    return new AutoValue_UpscaleImageParameters.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for UpscaleImageParameters. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `UpscaleImageParameters.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_UpscaleImageParameters.Builder();
    }

    /**
     * Setter for model.
     *
     * <p>model: The model to use.
     */
    @JsonProperty("model")
    public abstract Builder model(String model);

    /**
     * Setter for image.
     *
     * <p>image: The input image to upscale.
     */
    @JsonProperty("image")
    public abstract Builder image(Image image);

    /**
     * Setter for image builder.
     *
     * <p>image: The input image to upscale.
     */
    public Builder image(Image.Builder imageBuilder) {
      return image(imageBuilder.build());
    }

    /**
     * Setter for upscaleFactor.
     *
     * <p>upscaleFactor: The factor to upscale the image (x2 or x4).
     */
    @JsonProperty("upscaleFactor")
    public abstract Builder upscaleFactor(String upscaleFactor);

    /**
     * Setter for config.
     *
     * <p>config: Configuration for upscaling.
     */
    @JsonProperty("config")
    public abstract Builder config(UpscaleImageConfig config);

    /**
     * Setter for config builder.
     *
     * <p>config: Configuration for upscaling.
     */
    public Builder config(UpscaleImageConfig.Builder configBuilder) {
      return config(configBuilder.build());
    }

    public abstract UpscaleImageParameters build();
  }

  /** Deserializes a JSON string to a UpscaleImageParameters object. */
  public static UpscaleImageParameters fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, UpscaleImageParameters.class);
  }
}
