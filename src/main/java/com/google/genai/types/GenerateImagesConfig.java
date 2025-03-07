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

import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import com.google.gson.annotations.SerializedName;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;
import java.util.Optional;

/** The config for generating an images. */
@GenerateTypeAdapter
@AutoValue
public abstract class GenerateImagesConfig extends JsonSerializable {
  /** Cloud Storage URI used to store the generated images. */
  @SerializedName("outputGcsUri")
  public abstract Optional<String> outputGcsUri();

  /** Description of what to discourage in the generated images. */
  @SerializedName("negativePrompt")
  public abstract Optional<String> negativePrompt();

  /** Number of images to generate. */
  @SerializedName("numberOfImages")
  public abstract Optional<Integer> numberOfImages();

  /** Aspect ratio of the generated images. */
  @SerializedName("aspectRatio")
  public abstract Optional<String> aspectRatio();

  /**
   * Controls how much the model adheres to the text prompt. Large values increase output and prompt
   * alignment, but may compromise image quality.
   */
  @SerializedName("guidanceScale")
  public abstract Optional<Float> guidanceScale();

  /**
   * Random seed for image generation. This is not available when ``add_watermark`` is set to true.
   */
  @SerializedName("seed")
  public abstract Optional<Integer> seed();

  /** Filter level for safety filtering. */
  @SerializedName("safetyFilterLevel")
  public abstract Optional<String> safetyFilterLevel();

  /** Allows generation of people by the model. */
  @SerializedName("personGeneration")
  public abstract Optional<String> personGeneration();

  /** Whether to report the safety scores of each image in the response. */
  @SerializedName("includeSafetyAttributes")
  public abstract Optional<Boolean> includeSafetyAttributes();

  /**
   * Whether to include the Responsible AI filter reason if the image is filtered out of the
   * response.
   */
  @SerializedName("includeRaiReason")
  public abstract Optional<Boolean> includeRaiReason();

  /** Language of the text in the prompt. */
  @SerializedName("language")
  public abstract Optional<String> language();

  /** MIME type of the generated image. */
  @SerializedName("outputMimeType")
  public abstract Optional<String> outputMimeType();

  /** Compression quality of the generated image (for ``image/jpeg`` only). */
  @SerializedName("outputCompressionQuality")
  public abstract Optional<Integer> outputCompressionQuality();

  /** Whether to add a watermark to the generated images. */
  @SerializedName("addWatermark")
  public abstract Optional<Boolean> addWatermark();

  /** Whether to use the prompt rewriting logic. */
  @SerializedName("enhancePrompt")
  public abstract Optional<Boolean> enhancePrompt();

  /** Instantiates a builder for GenerateImagesConfig. */
  public static Builder builder() {
    return new AutoValue_GenerateImagesConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GenerateImagesConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    @SerializedName("outputGcsUri")
    public abstract Builder outputGcsUri(String outputGcsUri);

    @SerializedName("outputGcsUri")
    abstract Builder outputGcsUri(Optional<String> outputGcsUri);

    @SerializedName("negativePrompt")
    public abstract Builder negativePrompt(String negativePrompt);

    @SerializedName("negativePrompt")
    abstract Builder negativePrompt(Optional<String> negativePrompt);

    @SerializedName("numberOfImages")
    public abstract Builder numberOfImages(Integer numberOfImages);

    @SerializedName("numberOfImages")
    abstract Builder numberOfImages(Optional<Integer> numberOfImages);

    @SerializedName("aspectRatio")
    public abstract Builder aspectRatio(String aspectRatio);

    @SerializedName("aspectRatio")
    abstract Builder aspectRatio(Optional<String> aspectRatio);

    @SerializedName("guidanceScale")
    public abstract Builder guidanceScale(Float guidanceScale);

    @SerializedName("guidanceScale")
    abstract Builder guidanceScale(Optional<Float> guidanceScale);

    @SerializedName("seed")
    public abstract Builder seed(Integer seed);

    @SerializedName("seed")
    abstract Builder seed(Optional<Integer> seed);

    @SerializedName("safetyFilterLevel")
    public abstract Builder safetyFilterLevel(String safetyFilterLevel);

    @SerializedName("safetyFilterLevel")
    abstract Builder safetyFilterLevel(Optional<String> safetyFilterLevel);

    @SerializedName("personGeneration")
    public abstract Builder personGeneration(String personGeneration);

    @SerializedName("personGeneration")
    abstract Builder personGeneration(Optional<String> personGeneration);

    @SerializedName("includeSafetyAttributes")
    public abstract Builder includeSafetyAttributes(boolean includeSafetyAttributes);

    @SerializedName("includeSafetyAttributes")
    abstract Builder includeSafetyAttributes(Optional<Boolean> includeSafetyAttributes);

    @SerializedName("includeRaiReason")
    public abstract Builder includeRaiReason(boolean includeRaiReason);

    @SerializedName("includeRaiReason")
    abstract Builder includeRaiReason(Optional<Boolean> includeRaiReason);

    @SerializedName("language")
    public abstract Builder language(String language);

    @SerializedName("language")
    abstract Builder language(Optional<String> language);

    @SerializedName("outputMimeType")
    public abstract Builder outputMimeType(String outputMimeType);

    @SerializedName("outputMimeType")
    abstract Builder outputMimeType(Optional<String> outputMimeType);

    @SerializedName("outputCompressionQuality")
    public abstract Builder outputCompressionQuality(Integer outputCompressionQuality);

    @SerializedName("outputCompressionQuality")
    abstract Builder outputCompressionQuality(Optional<Integer> outputCompressionQuality);

    @SerializedName("addWatermark")
    public abstract Builder addWatermark(boolean addWatermark);

    @SerializedName("addWatermark")
    abstract Builder addWatermark(Optional<Boolean> addWatermark);

    @SerializedName("enhancePrompt")
    public abstract Builder enhancePrompt(boolean enhancePrompt);

    @SerializedName("enhancePrompt")
    abstract Builder enhancePrompt(Optional<Boolean> enhancePrompt);

    public abstract GenerateImagesConfig build();
  }

  /** Deserializes a JSON string to a GenerateImagesConfig object. */
  public static GenerateImagesConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GenerateImagesConfig.class);
  }
}
