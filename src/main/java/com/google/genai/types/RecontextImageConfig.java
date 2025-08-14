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

/** Configuration for recontextualizing an image. */
@AutoValue
@JsonDeserialize(builder = RecontextImageConfig.Builder.class)
public abstract class RecontextImageConfig extends JsonSerializable {
  /** Used to override HTTP request options. */
  @JsonProperty("httpOptions")
  public abstract Optional<HttpOptions> httpOptions();

  /** Number of images to generate. */
  @JsonProperty("numberOfImages")
  public abstract Optional<Integer> numberOfImages();

  /**
   * The number of sampling steps. A higher value has better image quality, while a lower value has
   * better latency.
   */
  @JsonProperty("baseSteps")
  public abstract Optional<Integer> baseSteps();

  /** Cloud Storage URI used to store the generated images. */
  @JsonProperty("outputGcsUri")
  public abstract Optional<String> outputGcsUri();

  /** Random seed for image generation. */
  @JsonProperty("seed")
  public abstract Optional<Integer> seed();

  /** Filter level for safety filtering. */
  @JsonProperty("safetyFilterLevel")
  public abstract Optional<SafetyFilterLevel> safetyFilterLevel();

  /** Whether allow to generate person images, and restrict to specific ages. */
  @JsonProperty("personGeneration")
  public abstract Optional<PersonGeneration> personGeneration();

  /** Whether to add a SynthID watermark to the generated images. */
  @JsonProperty("addWatermark")
  public abstract Optional<Boolean> addWatermark();

  /** MIME type of the generated image. */
  @JsonProperty("outputMimeType")
  public abstract Optional<String> outputMimeType();

  /** Compression quality of the generated image (for ``image/jpeg`` only). */
  @JsonProperty("outputCompressionQuality")
  public abstract Optional<Integer> outputCompressionQuality();

  /** Whether to use the prompt rewriting logic. */
  @JsonProperty("enhancePrompt")
  public abstract Optional<Boolean> enhancePrompt();

  /** Instantiates a builder for RecontextImageConfig. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_RecontextImageConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for RecontextImageConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `RecontextImageConfig.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_RecontextImageConfig.Builder();
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
     * Setter for numberOfImages.
     *
     * <p>numberOfImages: Number of images to generate.
     */
    @JsonProperty("numberOfImages")
    public abstract Builder numberOfImages(Integer numberOfImages);

    /**
     * Setter for baseSteps.
     *
     * <p>baseSteps: The number of sampling steps. A higher value has better image quality, while a
     * lower value has better latency.
     */
    @JsonProperty("baseSteps")
    public abstract Builder baseSteps(Integer baseSteps);

    /**
     * Setter for outputGcsUri.
     *
     * <p>outputGcsUri: Cloud Storage URI used to store the generated images.
     */
    @JsonProperty("outputGcsUri")
    public abstract Builder outputGcsUri(String outputGcsUri);

    /**
     * Setter for seed.
     *
     * <p>seed: Random seed for image generation.
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
     * <p>personGeneration: Whether allow to generate person images, and restrict to specific ages.
     */
    @JsonProperty("personGeneration")
    public abstract Builder personGeneration(PersonGeneration personGeneration);

    /**
     * Setter for personGeneration given a known enum.
     *
     * <p>personGeneration: Whether allow to generate person images, and restrict to specific ages.
     */
    @CanIgnoreReturnValue
    public Builder personGeneration(PersonGeneration.Known knownType) {
      return personGeneration(new PersonGeneration(knownType));
    }

    /**
     * Setter for personGeneration given a string.
     *
     * <p>personGeneration: Whether allow to generate person images, and restrict to specific ages.
     */
    @CanIgnoreReturnValue
    public Builder personGeneration(String personGeneration) {
      return personGeneration(new PersonGeneration(personGeneration));
    }

    /**
     * Setter for addWatermark.
     *
     * <p>addWatermark: Whether to add a SynthID watermark to the generated images.
     */
    @JsonProperty("addWatermark")
    public abstract Builder addWatermark(boolean addWatermark);

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
     * Setter for enhancePrompt.
     *
     * <p>enhancePrompt: Whether to use the prompt rewriting logic.
     */
    @JsonProperty("enhancePrompt")
    public abstract Builder enhancePrompt(boolean enhancePrompt);

    public abstract RecontextImageConfig build();
  }

  /** Deserializes a JSON string to a RecontextImageConfig object. */
  @ExcludeFromGeneratedCoverageReport
  public static RecontextImageConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, RecontextImageConfig.class);
  }
}
