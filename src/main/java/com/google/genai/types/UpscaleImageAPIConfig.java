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
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import java.util.Map;
import java.util.Optional;

/**
 * Internal API config for UpscaleImage.
 *
 * <p>These fields require default values sent to the API which are not intended to be modifiable or
 * exposed to users in the SDK method.
 */
@AutoValue
@InternalApi
@JsonDeserialize(builder = UpscaleImageAPIConfig.Builder.class)
public abstract class UpscaleImageAPIConfig extends JsonSerializable {
  /** Used to override HTTP request options. */
  @JsonProperty("httpOptions")
  public abstract Optional<HttpOptions> httpOptions();

  /** Cloud Storage URI used to store the generated images. */
  @JsonProperty("outputGcsUri")
  public abstract Optional<String> outputGcsUri();

  /** Filter level for safety filtering. */
  @JsonProperty("safetyFilterLevel")
  public abstract Optional<SafetyFilterLevel> safetyFilterLevel();

  /** Allows generation of people by the model. */
  @JsonProperty("personGeneration")
  public abstract Optional<PersonGeneration> personGeneration();

  /** Whether to include a reason for filtered-out images in the response. */
  @JsonProperty("includeRaiReason")
  public abstract Optional<Boolean> includeRaiReason();

  /** The image format that the output should be saved as. */
  @JsonProperty("outputMimeType")
  public abstract Optional<String> outputMimeType();

  /** The level of compression. Only applicable if the ``output_mime_type`` is ``image/jpeg``. */
  @JsonProperty("outputCompressionQuality")
  public abstract Optional<Integer> outputCompressionQuality();

  /**
   * Whether to add an image enhancing step before upscaling. It is expected to suppress the noise
   * and JPEG compression artifacts from the input image.
   */
  @JsonProperty("enhanceInputImage")
  public abstract Optional<Boolean> enhanceInputImage();

  /**
   * With a higher image preservation factor, the original image pixels are more respected. With a
   * lower image preservation factor, the output image will have be more different from the input
   * image, but with finer details and less noise.
   */
  @JsonProperty("imagePreservationFactor")
  public abstract Optional<Float> imagePreservationFactor();

  /** User specified labels to track billing usage. */
  @JsonProperty("labels")
  public abstract Optional<Map<String, String>> labels();

  /** */
  @JsonProperty("numberOfImages")
  public abstract Optional<Integer> numberOfImages();

  /** */
  @JsonProperty("mode")
  public abstract Optional<String> mode();

  /** Instantiates a builder for UpscaleImageAPIConfig. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_UpscaleImageAPIConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for UpscaleImageAPIConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `UpscaleImageAPIConfig.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_UpscaleImageAPIConfig.Builder();
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
    @CanIgnoreReturnValue
    public Builder httpOptions(HttpOptions.Builder httpOptionsBuilder) {
      return httpOptions(httpOptionsBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder httpOptions(Optional<HttpOptions> httpOptions);

    /** Clears the value of httpOptions field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearHttpOptions() {
      return httpOptions(Optional.empty());
    }

    /**
     * Setter for outputGcsUri.
     *
     * <p>outputGcsUri: Cloud Storage URI used to store the generated images.
     */
    @JsonProperty("outputGcsUri")
    public abstract Builder outputGcsUri(String outputGcsUri);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder outputGcsUri(Optional<String> outputGcsUri);

    /** Clears the value of outputGcsUri field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearOutputGcsUri() {
      return outputGcsUri(Optional.empty());
    }

    /**
     * Setter for safetyFilterLevel.
     *
     * <p>safetyFilterLevel: Filter level for safety filtering.
     */
    @JsonProperty("safetyFilterLevel")
    public abstract Builder safetyFilterLevel(SafetyFilterLevel safetyFilterLevel);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder safetyFilterLevel(Optional<SafetyFilterLevel> safetyFilterLevel);

    /** Clears the value of safetyFilterLevel field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearSafetyFilterLevel() {
      return safetyFilterLevel(Optional.empty());
    }

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

    @ExcludeFromGeneratedCoverageReport
    abstract Builder personGeneration(Optional<PersonGeneration> personGeneration);

    /** Clears the value of personGeneration field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearPersonGeneration() {
      return personGeneration(Optional.empty());
    }

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
     * Setter for includeRaiReason.
     *
     * <p>includeRaiReason: Whether to include a reason for filtered-out images in the response.
     */
    @JsonProperty("includeRaiReason")
    public abstract Builder includeRaiReason(boolean includeRaiReason);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder includeRaiReason(Optional<Boolean> includeRaiReason);

    /** Clears the value of includeRaiReason field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearIncludeRaiReason() {
      return includeRaiReason(Optional.empty());
    }

    /**
     * Setter for outputMimeType.
     *
     * <p>outputMimeType: The image format that the output should be saved as.
     */
    @JsonProperty("outputMimeType")
    public abstract Builder outputMimeType(String outputMimeType);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder outputMimeType(Optional<String> outputMimeType);

    /** Clears the value of outputMimeType field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearOutputMimeType() {
      return outputMimeType(Optional.empty());
    }

    /**
     * Setter for outputCompressionQuality.
     *
     * <p>outputCompressionQuality: The level of compression. Only applicable if the
     * ``output_mime_type`` is ``image/jpeg``.
     */
    @JsonProperty("outputCompressionQuality")
    public abstract Builder outputCompressionQuality(Integer outputCompressionQuality);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder outputCompressionQuality(Optional<Integer> outputCompressionQuality);

    /** Clears the value of outputCompressionQuality field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearOutputCompressionQuality() {
      return outputCompressionQuality(Optional.empty());
    }

    /**
     * Setter for enhanceInputImage.
     *
     * <p>enhanceInputImage: Whether to add an image enhancing step before upscaling. It is expected
     * to suppress the noise and JPEG compression artifacts from the input image.
     */
    @JsonProperty("enhanceInputImage")
    public abstract Builder enhanceInputImage(boolean enhanceInputImage);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder enhanceInputImage(Optional<Boolean> enhanceInputImage);

    /** Clears the value of enhanceInputImage field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearEnhanceInputImage() {
      return enhanceInputImage(Optional.empty());
    }

    /**
     * Setter for imagePreservationFactor.
     *
     * <p>imagePreservationFactor: With a higher image preservation factor, the original image
     * pixels are more respected. With a lower image preservation factor, the output image will have
     * be more different from the input image, but with finer details and less noise.
     */
    @JsonProperty("imagePreservationFactor")
    public abstract Builder imagePreservationFactor(Float imagePreservationFactor);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder imagePreservationFactor(Optional<Float> imagePreservationFactor);

    /** Clears the value of imagePreservationFactor field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearImagePreservationFactor() {
      return imagePreservationFactor(Optional.empty());
    }

    /**
     * Setter for labels.
     *
     * <p>labels: User specified labels to track billing usage.
     */
    @JsonProperty("labels")
    public abstract Builder labels(Map<String, String> labels);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder labels(Optional<Map<String, String>> labels);

    /** Clears the value of labels field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearLabels() {
      return labels(Optional.empty());
    }

    /**
     * Setter for numberOfImages.
     *
     * <p>numberOfImages:
     */
    @JsonProperty("numberOfImages")
    public abstract Builder numberOfImages(Integer numberOfImages);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder numberOfImages(Optional<Integer> numberOfImages);

    /** Clears the value of numberOfImages field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearNumberOfImages() {
      return numberOfImages(Optional.empty());
    }

    /**
     * Setter for mode.
     *
     * <p>mode:
     */
    @JsonProperty("mode")
    public abstract Builder mode(String mode);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder mode(Optional<String> mode);

    /** Clears the value of mode field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearMode() {
      return mode(Optional.empty());
    }

    public abstract UpscaleImageAPIConfig build();
  }

  /** Deserializes a JSON string to a UpscaleImageAPIConfig object. */
  @ExcludeFromGeneratedCoverageReport
  public static UpscaleImageAPIConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, UpscaleImageAPIConfig.class);
  }
}
