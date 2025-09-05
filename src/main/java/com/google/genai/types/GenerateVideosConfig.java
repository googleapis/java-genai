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

import static com.google.common.collect.ImmutableList.toImmutableList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/** Configuration for generating videos. */
@AutoValue
@JsonDeserialize(builder = GenerateVideosConfig.Builder.class)
public abstract class GenerateVideosConfig extends JsonSerializable {
  /** Used to override HTTP request options. */
  @JsonProperty("httpOptions")
  public abstract Optional<HttpOptions> httpOptions();

  /** Number of output videos. */
  @JsonProperty("numberOfVideos")
  public abstract Optional<Integer> numberOfVideos();

  /** The gcs bucket where to save the generated videos. */
  @JsonProperty("outputGcsUri")
  public abstract Optional<String> outputGcsUri();

  /** Frames per second for video generation. */
  @JsonProperty("fps")
  public abstract Optional<Integer> fps();

  /** Duration of the clip for video generation in seconds. */
  @JsonProperty("durationSeconds")
  public abstract Optional<Integer> durationSeconds();

  /**
   * The RNG seed. If RNG seed is exactly same for each request with unchanged inputs, the
   * prediction results will be consistent. Otherwise, a random RNG seed will be used each time to
   * produce a different result.
   */
  @JsonProperty("seed")
  public abstract Optional<Integer> seed();

  /**
   * The aspect ratio for the generated video. 16:9 (landscape) and 9:16 (portrait) are supported.
   */
  @JsonProperty("aspectRatio")
  public abstract Optional<String> aspectRatio();

  /** The resolution for the generated video. 720p and 1080p are supported. */
  @JsonProperty("resolution")
  public abstract Optional<String> resolution();

  /**
   * Whether allow to generate person videos, and restrict to specific ages. Supported values are:
   * dont_allow, allow_adult.
   */
  @JsonProperty("personGeneration")
  public abstract Optional<String> personGeneration();

  /** The pubsub topic where to publish the video generation progress. */
  @JsonProperty("pubsubTopic")
  public abstract Optional<String> pubsubTopic();

  /** Explicitly state what should not be included in the generated videos. */
  @JsonProperty("negativePrompt")
  public abstract Optional<String> negativePrompt();

  /** Whether to use the prompt rewriting logic. */
  @JsonProperty("enhancePrompt")
  public abstract Optional<Boolean> enhancePrompt();

  /** Whether to generate audio along with the video. */
  @JsonProperty("generateAudio")
  public abstract Optional<Boolean> generateAudio();

  /**
   * Image to use as the last frame of generated videos. Only supported for image to video use
   * cases.
   */
  @JsonProperty("lastFrame")
  public abstract Optional<Image> lastFrame();

  /**
   * The images to use as the references to generate the videos. If this field is provided, the text
   * prompt field must also be provided. The image, video, or last_frame field are not supported.
   * Each image must be associated with a type. Veo 2 supports up to 3 asset images *or* 1 style
   * image.
   */
  @JsonProperty("referenceImages")
  public abstract Optional<List<VideoGenerationReferenceImage>> referenceImages();

  /** The mask to use for generating videos. */
  @JsonProperty("mask")
  public abstract Optional<VideoGenerationMask> mask();

  /** Compression quality of the generated videos. */
  @JsonProperty("compressionQuality")
  public abstract Optional<VideoCompressionQuality> compressionQuality();

  /** Instantiates a builder for GenerateVideosConfig. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_GenerateVideosConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GenerateVideosConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `GenerateVideosConfig.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_GenerateVideosConfig.Builder();
    }

    /**
     * Setter for httpOptions.
     *
     * <p>httpOptions: Used to override HTTP request options.
     */
    @JsonProperty("httpOptions")
    public abstract Builder httpOptions(HttpOptions httpOptions);

    /**
     * Setter for httpOptions builder.
     *
     * <p>httpOptions: Used to override HTTP request options.
     */
    public Builder httpOptions(HttpOptions.Builder httpOptionsBuilder) {
      return httpOptions(httpOptionsBuilder.build());
    }

    /**
     * Setter for numberOfVideos.
     *
     * <p>numberOfVideos: Number of output videos.
     */
    @JsonProperty("numberOfVideos")
    public abstract Builder numberOfVideos(Integer numberOfVideos);

    /**
     * Setter for outputGcsUri.
     *
     * <p>outputGcsUri: The gcs bucket where to save the generated videos.
     */
    @JsonProperty("outputGcsUri")
    public abstract Builder outputGcsUri(String outputGcsUri);

    /**
     * Setter for fps.
     *
     * <p>fps: Frames per second for video generation.
     */
    @JsonProperty("fps")
    public abstract Builder fps(Integer fps);

    /**
     * Setter for durationSeconds.
     *
     * <p>durationSeconds: Duration of the clip for video generation in seconds.
     */
    @JsonProperty("durationSeconds")
    public abstract Builder durationSeconds(Integer durationSeconds);

    /**
     * Setter for seed.
     *
     * <p>seed: The RNG seed. If RNG seed is exactly same for each request with unchanged inputs,
     * the prediction results will be consistent. Otherwise, a random RNG seed will be used each
     * time to produce a different result.
     */
    @JsonProperty("seed")
    public abstract Builder seed(Integer seed);

    /**
     * Setter for aspectRatio.
     *
     * <p>aspectRatio: The aspect ratio for the generated video. 16:9 (landscape) and 9:16
     * (portrait) are supported.
     */
    @JsonProperty("aspectRatio")
    public abstract Builder aspectRatio(String aspectRatio);

    /**
     * Setter for resolution.
     *
     * <p>resolution: The resolution for the generated video. 720p and 1080p are supported.
     */
    @JsonProperty("resolution")
    public abstract Builder resolution(String resolution);

    /**
     * Setter for personGeneration.
     *
     * <p>personGeneration: Whether allow to generate person videos, and restrict to specific ages.
     * Supported values are: dont_allow, allow_adult.
     */
    @JsonProperty("personGeneration")
    public abstract Builder personGeneration(String personGeneration);

    /**
     * Setter for pubsubTopic.
     *
     * <p>pubsubTopic: The pubsub topic where to publish the video generation progress.
     */
    @JsonProperty("pubsubTopic")
    public abstract Builder pubsubTopic(String pubsubTopic);

    /**
     * Setter for negativePrompt.
     *
     * <p>negativePrompt: Explicitly state what should not be included in the generated videos.
     */
    @JsonProperty("negativePrompt")
    public abstract Builder negativePrompt(String negativePrompt);

    /**
     * Setter for enhancePrompt.
     *
     * <p>enhancePrompt: Whether to use the prompt rewriting logic.
     */
    @JsonProperty("enhancePrompt")
    public abstract Builder enhancePrompt(boolean enhancePrompt);

    /**
     * Setter for generateAudio.
     *
     * <p>generateAudio: Whether to generate audio along with the video.
     */
    @JsonProperty("generateAudio")
    public abstract Builder generateAudio(boolean generateAudio);

    /**
     * Setter for lastFrame.
     *
     * <p>lastFrame: Image to use as the last frame of generated videos. Only supported for image to
     * video use cases.
     */
    @JsonProperty("lastFrame")
    public abstract Builder lastFrame(Image lastFrame);

    /**
     * Setter for lastFrame builder.
     *
     * <p>lastFrame: Image to use as the last frame of generated videos. Only supported for image to
     * video use cases.
     */
    public Builder lastFrame(Image.Builder lastFrameBuilder) {
      return lastFrame(lastFrameBuilder.build());
    }

    /**
     * Setter for referenceImages.
     *
     * <p>referenceImages: The images to use as the references to generate the videos. If this field
     * is provided, the text prompt field must also be provided. The image, video, or last_frame
     * field are not supported. Each image must be associated with a type. Veo 2 supports up to 3
     * asset images *or* 1 style image.
     */
    @JsonProperty("referenceImages")
    public abstract Builder referenceImages(List<VideoGenerationReferenceImage> referenceImages);

    /**
     * Setter for referenceImages.
     *
     * <p>referenceImages: The images to use as the references to generate the videos. If this field
     * is provided, the text prompt field must also be provided. The image, video, or last_frame
     * field are not supported. Each image must be associated with a type. Veo 2 supports up to 3
     * asset images *or* 1 style image.
     */
    public Builder referenceImages(VideoGenerationReferenceImage... referenceImages) {
      return referenceImages(Arrays.asList(referenceImages));
    }

    /**
     * Setter for referenceImages builder.
     *
     * <p>referenceImages: The images to use as the references to generate the videos. If this field
     * is provided, the text prompt field must also be provided. The image, video, or last_frame
     * field are not supported. Each image must be associated with a type. Veo 2 supports up to 3
     * asset images *or* 1 style image.
     */
    public Builder referenceImages(
        VideoGenerationReferenceImage.Builder... referenceImagesBuilders) {
      return referenceImages(
          Arrays.asList(referenceImagesBuilders).stream()
              .map(VideoGenerationReferenceImage.Builder::build)
              .collect(toImmutableList()));
    }

    /**
     * Setter for mask.
     *
     * <p>mask: The mask to use for generating videos.
     */
    @JsonProperty("mask")
    public abstract Builder mask(VideoGenerationMask mask);

    /**
     * Setter for mask builder.
     *
     * <p>mask: The mask to use for generating videos.
     */
    public Builder mask(VideoGenerationMask.Builder maskBuilder) {
      return mask(maskBuilder.build());
    }

    /**
     * Setter for compressionQuality.
     *
     * <p>compressionQuality: Compression quality of the generated videos.
     */
    @JsonProperty("compressionQuality")
    public abstract Builder compressionQuality(VideoCompressionQuality compressionQuality);

    /**
     * Setter for compressionQuality given a known enum.
     *
     * <p>compressionQuality: Compression quality of the generated videos.
     */
    @CanIgnoreReturnValue
    public Builder compressionQuality(VideoCompressionQuality.Known knownType) {
      return compressionQuality(new VideoCompressionQuality(knownType));
    }

    /**
     * Setter for compressionQuality given a string.
     *
     * <p>compressionQuality: Compression quality of the generated videos.
     */
    @CanIgnoreReturnValue
    public Builder compressionQuality(String compressionQuality) {
      return compressionQuality(new VideoCompressionQuality(compressionQuality));
    }

    public abstract GenerateVideosConfig build();
  }

  /** Deserializes a JSON string to a GenerateVideosConfig object. */
  @ExcludeFromGeneratedCoverageReport
  public static GenerateVideosConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GenerateVideosConfig.class);
  }
}
