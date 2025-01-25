// Auto-generated code. Do not edit.

package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** Class that represents the config for generating images. */
@AutoValue
@JsonDeserialize(builder = AutoValue_GenerateImagesConfig.Builder.class)
public abstract class GenerateImagesConfig extends JsonSerializable {
  /** Cloud Storage URI used to store the generated images. */
  public abstract Optional<String> getOutputGcsUri();

  /** Description of what to discourage in the generated images. */
  public abstract Optional<String> getNegativePrompt();

  /** Number of images to generate. */
  public abstract Optional<Integer> getNumberOfImages();

  /**
   * Controls how much the model adheres to the text prompt. Large values increase output and prompt
   * alignment, but may compromise image quality.
   */
  public abstract Optional<Float> getGuidanceScale();

  /**
   * Random seed for image generation. This is not available when ``add_watermark`` is set to true.
   */
  public abstract Optional<Integer> getSeed();

  /** Filter level for safety filtering. */
  public abstract Optional<String> getSafetyFilterLevel();

  /** Allows generation of people by the model. */
  public abstract Optional<String> getPersonGeneration();

  /** Whether to report the safety scores of each image in the response. */
  public abstract Optional<Boolean> getIncludeSafetyAttributes();

  /**
   * Whether to include the Responsible AI filter reason if the image is filtered out of the
   * response.
   */
  public abstract Optional<Boolean> getIncludeRaiReason();

  /** Language of the text in the prompt. */
  public abstract Optional<String> getLanguage();

  /** MIME type of the generated image. */
  public abstract Optional<String> getOutputMimeType();

  /** Compression quality of the generated image (for ``image/jpeg`` only). */
  public abstract Optional<Integer> getOutputCompressionQuality();

  /** Whether to add a watermark to the generated images. */
  public abstract Optional<Boolean> getAddWatermark();

  /** Aspect ratio of the generated images. */
  public abstract Optional<String> getAspectRatio();

  /** Whether to use the prompt rewriting logic. */
  public abstract Optional<Boolean> getEnhancePrompt();

  /** Instantiates a builder for GenerateImagesConfig. */
  public static Builder builder() {
    return new AutoValue_GenerateImagesConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GenerateImagesConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("outputGcsUri")
    public abstract Builder setOutputGcsUri(String outputGcsUri);

    @JsonProperty("negativePrompt")
    public abstract Builder setNegativePrompt(String negativePrompt);

    @JsonProperty("numberOfImages")
    public abstract Builder setNumberOfImages(Integer numberOfImages);

    @JsonProperty("guidanceScale")
    public abstract Builder setGuidanceScale(Float guidanceScale);

    @JsonProperty("seed")
    public abstract Builder setSeed(Integer seed);

    @JsonProperty("safetyFilterLevel")
    public abstract Builder setSafetyFilterLevel(String safetyFilterLevel);

    @JsonProperty("personGeneration")
    public abstract Builder setPersonGeneration(String personGeneration);

    @JsonProperty("includeSafetyAttributes")
    public abstract Builder setIncludeSafetyAttributes(boolean includeSafetyAttributes);

    @JsonProperty("includeRaiReason")
    public abstract Builder setIncludeRaiReason(boolean includeRaiReason);

    @JsonProperty("language")
    public abstract Builder setLanguage(String language);

    @JsonProperty("outputMimeType")
    public abstract Builder setOutputMimeType(String outputMimeType);

    @JsonProperty("outputCompressionQuality")
    public abstract Builder setOutputCompressionQuality(Integer outputCompressionQuality);

    @JsonProperty("addWatermark")
    public abstract Builder setAddWatermark(boolean addWatermark);

    @JsonProperty("aspectRatio")
    public abstract Builder setAspectRatio(String aspectRatio);

    @JsonProperty("enhancePrompt")
    public abstract Builder setEnhancePrompt(boolean enhancePrompt);

    public abstract GenerateImagesConfig build();
  }

  /** Deserializes a JSON string to a GenerateImagesConfig object. */
  public static GenerateImagesConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GenerateImagesConfig.class);
  }
}
