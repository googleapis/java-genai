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

/** Class that represents the parameters for generating videos. */
@AutoValue
@InternalApi
@JsonDeserialize(builder = GenerateVideosParameters.Builder.class)
public abstract class GenerateVideosParameters extends JsonSerializable {
  /**
   * ID of the model to use. For a list of models, see `Google models
   * <https://cloud.google.com/vertex-ai/generative-ai/docs/learn/models>`_.
   */
  @JsonProperty("model")
  public abstract Optional<String> model();

  /** The text prompt for generating the videos. Optional if image or video is provided. */
  @JsonProperty("prompt")
  public abstract Optional<String> prompt();

  /**
   * The input image for generating the videos. Optional if prompt is provided. Not allowed if video
   * is provided.
   */
  @JsonProperty("image")
  public abstract Optional<Image> image();

  /**
   * The input video for video extension use cases. Optional if prompt is provided. Not allowed if
   * image is provided.
   */
  @JsonProperty("video")
  public abstract Optional<Video> video();

  /** A set of source input(s) for video generation. */
  @JsonProperty("source")
  public abstract Optional<GenerateVideosSource> source();

  /** Configuration for generating videos. */
  @JsonProperty("config")
  public abstract Optional<GenerateVideosConfig> config();

  /** Instantiates a builder for GenerateVideosParameters. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_GenerateVideosParameters.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GenerateVideosParameters. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `GenerateVideosParameters.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_GenerateVideosParameters.Builder();
    }

    /**
     * Setter for model.
     *
     * <p>model: ID of the model to use. For a list of models, see `Google models
     * <https://cloud.google.com/vertex-ai/generative-ai/docs/learn/models>`_.
     */
    @JsonProperty("model")
    public abstract Builder model(String model);

    /**
     * Setter for prompt.
     *
     * <p>prompt: The text prompt for generating the videos. Optional if image or video is provided.
     */
    @JsonProperty("prompt")
    public abstract Builder prompt(String prompt);

    /**
     * Setter for image.
     *
     * <p>image: The input image for generating the videos. Optional if prompt is provided. Not
     * allowed if video is provided.
     */
    @JsonProperty("image")
    public abstract Builder image(Image image);

    /**
     * Setter for image builder.
     *
     * <p>image: The input image for generating the videos. Optional if prompt is provided. Not
     * allowed if video is provided.
     */
    public Builder image(Image.Builder imageBuilder) {
      return image(imageBuilder.build());
    }

    /**
     * Setter for video.
     *
     * <p>video: The input video for video extension use cases. Optional if prompt is provided. Not
     * allowed if image is provided.
     */
    @JsonProperty("video")
    public abstract Builder video(Video video);

    /**
     * Setter for video builder.
     *
     * <p>video: The input video for video extension use cases. Optional if prompt is provided. Not
     * allowed if image is provided.
     */
    public Builder video(Video.Builder videoBuilder) {
      return video(videoBuilder.build());
    }

    /**
     * Setter for source.
     *
     * <p>source: A set of source input(s) for video generation.
     */
    @JsonProperty("source")
    public abstract Builder source(GenerateVideosSource source);

    /**
     * Setter for source builder.
     *
     * <p>source: A set of source input(s) for video generation.
     */
    public Builder source(GenerateVideosSource.Builder sourceBuilder) {
      return source(sourceBuilder.build());
    }

    /**
     * Setter for config.
     *
     * <p>config: Configuration for generating videos.
     */
    @JsonProperty("config")
    public abstract Builder config(GenerateVideosConfig config);

    /**
     * Setter for config builder.
     *
     * <p>config: Configuration for generating videos.
     */
    public Builder config(GenerateVideosConfig.Builder configBuilder) {
      return config(configBuilder.build());
    }

    public abstract GenerateVideosParameters build();
  }

  /** Deserializes a JSON string to a GenerateVideosParameters object. */
  @ExcludeFromGeneratedCoverageReport
  public static GenerateVideosParameters fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GenerateVideosParameters.class);
  }
}
