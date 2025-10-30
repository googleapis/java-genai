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
import java.util.Map;
import java.util.Optional;

/** The config for generating an images. */
@AutoValue
@JsonDeserialize(builder = GenerateImagesConfig.Builder.class)
public abstract class GenerateImagesConfig extends JsonSerializable {
  /** Used to override HTTP request options. */
  @JsonProperty("httpOptions")
  public abstract Optional<HttpOptions> httpOptions();

  /** Cloud Storage URI used to store the generated images. */
  @JsonProperty("outputGcsUri")
  public abstract Optional<String> outputGcsUri();

  /** Description of what to discourage in the generated images. */
  @JsonProperty("negativePrompt")
  public abstract Optional<String> negativePrompt();

  /** Number of images to generate. */
  @JsonProperty("numberOfImages")
  public abstract Optional<Integer> numberOfImages();

  /**
   * Aspect ratio of the generated images. Supported values are "1:1", "3:4", "4:3", "9:16", and
   * "16:9".
   */
  @JsonProperty("aspectRatio")
  public abstract Optional<String> aspectRatio();

  /**
   * Controls how much the model adheres to the text prompt. Large values increase output and prompt
   * alignment, but may compromise image quality.
   */
  @JsonProperty("guidanceScale")
  public abstract Optional<Float> guidanceScale();

  /**
   * Random seed for image generation. This is not available when ``add_watermark`` is set to true.
   */
  @JsonProperty("seed")
  public abstract Optional<Integer> seed();

  /** Filter level for safety filtering. */
  @JsonProperty("safetyFilterLevel")
  public abstract Optional<SafetyFilterLevel> safetyFilterLevel();

  /** Allows generation of people by the model. */
  @JsonProperty("personGeneration")
  public abstract Optional<PersonGeneration> personGeneration();

  /**
   * Whether to report the safety scores of each generated image and the positive prompt in the
   * response.
   */
  @JsonProperty("includeSafetyAttributes")
  public abstract Optional<Boolean> includeSafetyAttributes();

  /**
   * Whether to include the Responsible AI filter reason if the image is filtered out of the
   * response.
   */
  @JsonProperty("includeRaiReason")
  public abstract Optional<Boolean> includeRaiReason();

  /** Language of the text in the prompt. */
  @JsonProperty("language")
  public abstract Optional<ImagePromptLanguage> language();

  /** MIME type of the generated image. */
  @JsonProperty("outputMimeType")
  public abstract Optional<String> outputMimeType();

  /** Compression quality of the generated image (for ``image/jpeg`` only). */
  @JsonProperty("outputCompressionQuality")
  public abstract Optional<Integer> outputCompressionQuality();

  /** Whether to add a watermark to the generated images. */
  @JsonProperty("addWatermark")
  public abstract Optional<Boolean> addWatermark();

  /** User specified labels to track billing usage. */
  @JsonProperty("labels")
  public abstract Optional<Map<String, String>> labels();

  /**
   * The size of the largest dimension of the generated image. Supported sizes are 1K and 2K (not
   * supported for Imagen 3 models).
   */
  @JsonProperty("imageSize")
  public abstract Optional<String> imageSize();

  /** Whether to use the prompt rewriting logic. */
  @JsonProperty("enhancePrompt")
  public abstract Optional<Boolean> enhancePrompt();

  /** Instantiates a builder for GenerateImagesConfig. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_GenerateImagesConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GenerateImagesConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `GenerateImagesConfig.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_GenerateImagesConfig.Builder();
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
     * Setter for outputGcsUri.
     *
     * <p>outputGcsUri: Cloud Storage URI used to store the generated images.
     */
    @JsonProperty("outputGcsUri")
    public abstract Builder outputGcsUri(String outputGcsUri);

    /**
     * Setter for negativePrompt.
     *
     * <p>negativePrompt: Description of what to discourage in the generated images.
     */
    @JsonProperty("negativePrompt")
    public abstract Builder negativePrompt(String negativePrompt);

    /**
     * Setter for numberOfImages.
     *
     * <p>numberOfImages: Number of images to generate.
     */
    @JsonProperty("numberOfImages")
    public abstract Builder numberOfImages(Integer numberOfImages);

    /**
     * Setter for aspectRatio.
     *
     * <p>aspectRatio: Aspect ratio of the generated images. Supported values are "1:1", "3:4",
     * "4:3", "9:16", and "16:9".
     */
    @JsonProperty("aspectRatio")
    public abstract Builder aspectRatio(String aspectRatio);

    /**
     * Setter for guidanceScale.
     *
     * <p>guidanceScale: Controls how much the model adheres to the text prompt. Large values
     * increase output and prompt alignment, but may compromise image quality.
     */
    @JsonProperty("guidanceScale")
    public abstract Builder guidanceScale(Float guidanceScale);

    /**
     * Setter for seed.
     *
     * <p>seed: Random seed for image generation. This is not available when ``add_watermark`` is
     * set to true.
     */
    @JsonProperty("seed")
    public abstract Builder seed(Integer seed);

    /**
     * Setter for safetyFilterLevel.
     *
     * <p>safetyFilterLevel: Filter level for safety filtering.
     */
    @JsonProperty("safetyFilterLevel")
    public abstract Builder safetyFilterLevel(SafetyFilterLevel safetyFilterLevel);

    /**
     * Setter for safetyFilterLevel given a known enum.
     *
     * <p>safetyFilterLevel: Filter level for safety filtering.
     */
    @CanIgnoreReturnValue
    public Builder safetyFilterLevel(SafetyFilterLevel.Known knownType) {
      return safetyFilterLevel(new SafetyFilterLevel(knownType));
    }

    /**
     * Setter for safetyFilterLevel given a string.
     *
     * <p>safetyFilterLevel: Filter level for safety filtering.
     */
    @CanIgnoreReturnValue
    public Builder safetyFilterLevel(String safetyFilterLevel) {
      return safetyFilterLevel(new SafetyFilterLevel(safetyFilterLevel));
    }

    /**
     * Setter for personGeneration.
     *
     * <p>personGeneration: Allows generation of people by the model.
     */
    @JsonProperty("personGeneration")
    public abstract Builder personGeneration(PersonGeneration personGeneration);

    /**
     * Setter for personGeneration given a known enum.
     *
     * <p>personGeneration: Allows generation of people by the model.
     */
    @CanIgnoreReturnValue
    public Builder personGeneration(PersonGeneration.Known knownType) {
      return personGeneration(new PersonGeneration(knownType));
    }

    /**
     * Setter for personGeneration given a string.
     *
     * <p>personGeneration: Allows generation of people by the model.
     */
    @CanIgnoreReturnValue
    public Builder personGeneration(String personGeneration) {
      return personGeneration(new PersonGeneration(personGeneration));
    }

    /**
     * Setter for includeSafetyAttributes.
     *
     * <p>includeSafetyAttributes: Whether to report the safety scores of each generated image and
     * the positive prompt in the response.
     */
    @JsonProperty("includeSafetyAttributes")
    public abstract Builder includeSafetyAttributes(boolean includeSafetyAttributes);

    /**
     * Setter for includeRaiReason.
     *
     * <p>includeRaiReason: Whether to include the Responsible AI filter reason if the image is
     * filtered out of the response.
     */
    @JsonProperty("includeRaiReason")
    public abstract Builder includeRaiReason(boolean includeRaiReason);

    /**
     * Setter for language.
     *
     * <p>language: Language of the text in the prompt.
     */
    @JsonProperty("language")
    public abstract Builder language(ImagePromptLanguage language);

    /**
     * Setter for language given a known enum.
     *
     * <p>language: Language of the text in the prompt.
     */
    @CanIgnoreReturnValue
    public Builder language(ImagePromptLanguage.Known knownType) {
      return language(new ImagePromptLanguage(knownType));
    }

    /**
     * Setter for language given a string.
     *
     * <p>language: Language of the text in the prompt.
     */
    @CanIgnoreReturnValue
    public Builder language(String language) {
      return language(new ImagePromptLanguage(language));
    }

    /**
     * Setter for outputMimeType.
     *
     * <p>outputMimeType: MIME type of the generated image.
     */
    @JsonProperty("outputMimeType")
    public abstract Builder outputMimeType(String outputMimeType);

    /**
     * Setter for outputCompressionQuality.
     *
     * <p>outputCompressionQuality: Compression quality of the generated image (for ``image/jpeg``
     * only).
     */
    @JsonProperty("outputCompressionQuality")
    public abstract Builder outputCompressionQuality(Integer outputCompressionQuality);

    /**
     * Setter for addWatermark.
     *
     * <p>addWatermark: Whether to add a watermark to the generated images.
     */
    @JsonProperty("addWatermark")
    public abstract Builder addWatermark(boolean addWatermark);

    /**
     * Setter for labels.
     *
     * <p>labels: User specified labels to track billing usage.
     */
    @JsonProperty("labels")
    public abstract Builder labels(Map<String, String> labels);

    /**
     * Setter for imageSize.
     *
     * <p>imageSize: The size of the largest dimension of the generated image. Supported sizes are
     * 1K and 2K (not supported for Imagen 3 models).
     */
    @JsonProperty("imageSize")
    public abstract Builder imageSize(String imageSize);

    /**
     * Setter for enhancePrompt.
     *
     * <p>enhancePrompt: Whether to use the prompt rewriting logic.
     */
    @JsonProperty("enhancePrompt")
    public abstract Builder enhancePrompt(boolean enhancePrompt);

    public abstract GenerateImagesConfig build();
  }

  /** Deserializes a JSON string to a GenerateImagesConfig object. */
  @ExcludeFromGeneratedCoverageReport
  public static GenerateImagesConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GenerateImagesConfig.class);
  }
}
