package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_GenerateImagesConfig extends GenerateImagesConfig {

  private final Optional<String> outputGcsUri;

  private final Optional<String> negativePrompt;

  private final Optional<Integer> numberOfImages;

  private final Optional<String> aspectRatio;

  private final Optional<Float> guidanceScale;

  private final Optional<Integer> seed;

  private final Optional<String> safetyFilterLevel;

  private final Optional<String> personGeneration;

  private final Optional<Boolean> includeSafetyAttributes;

  private final Optional<Boolean> includeRaiReason;

  private final Optional<String> language;

  private final Optional<String> outputMimeType;

  private final Optional<Integer> outputCompressionQuality;

  private final Optional<Boolean> addWatermark;

  private final Optional<Boolean> enhancePrompt;

  private AutoValue_GenerateImagesConfig(
      Optional<String> outputGcsUri,
      Optional<String> negativePrompt,
      Optional<Integer> numberOfImages,
      Optional<String> aspectRatio,
      Optional<Float> guidanceScale,
      Optional<Integer> seed,
      Optional<String> safetyFilterLevel,
      Optional<String> personGeneration,
      Optional<Boolean> includeSafetyAttributes,
      Optional<Boolean> includeRaiReason,
      Optional<String> language,
      Optional<String> outputMimeType,
      Optional<Integer> outputCompressionQuality,
      Optional<Boolean> addWatermark,
      Optional<Boolean> enhancePrompt) {
    this.outputGcsUri = outputGcsUri;
    this.negativePrompt = negativePrompt;
    this.numberOfImages = numberOfImages;
    this.aspectRatio = aspectRatio;
    this.guidanceScale = guidanceScale;
    this.seed = seed;
    this.safetyFilterLevel = safetyFilterLevel;
    this.personGeneration = personGeneration;
    this.includeSafetyAttributes = includeSafetyAttributes;
    this.includeRaiReason = includeRaiReason;
    this.language = language;
    this.outputMimeType = outputMimeType;
    this.outputCompressionQuality = outputCompressionQuality;
    this.addWatermark = addWatermark;
    this.enhancePrompt = enhancePrompt;
  }

  @JsonProperty("outputGcsUri")
  @Override
  public Optional<String> outputGcsUri() {
    return outputGcsUri;
  }

  @JsonProperty("negativePrompt")
  @Override
  public Optional<String> negativePrompt() {
    return negativePrompt;
  }

  @JsonProperty("numberOfImages")
  @Override
  public Optional<Integer> numberOfImages() {
    return numberOfImages;
  }

  @JsonProperty("aspectRatio")
  @Override
  public Optional<String> aspectRatio() {
    return aspectRatio;
  }

  @JsonProperty("guidanceScale")
  @Override
  public Optional<Float> guidanceScale() {
    return guidanceScale;
  }

  @JsonProperty("seed")
  @Override
  public Optional<Integer> seed() {
    return seed;
  }

  @JsonProperty("safetyFilterLevel")
  @Override
  public Optional<String> safetyFilterLevel() {
    return safetyFilterLevel;
  }

  @JsonProperty("personGeneration")
  @Override
  public Optional<String> personGeneration() {
    return personGeneration;
  }

  @JsonProperty("includeSafetyAttributes")
  @Override
  public Optional<Boolean> includeSafetyAttributes() {
    return includeSafetyAttributes;
  }

  @JsonProperty("includeRaiReason")
  @Override
  public Optional<Boolean> includeRaiReason() {
    return includeRaiReason;
  }

  @JsonProperty("language")
  @Override
  public Optional<String> language() {
    return language;
  }

  @JsonProperty("outputMimeType")
  @Override
  public Optional<String> outputMimeType() {
    return outputMimeType;
  }

  @JsonProperty("outputCompressionQuality")
  @Override
  public Optional<Integer> outputCompressionQuality() {
    return outputCompressionQuality;
  }

  @JsonProperty("addWatermark")
  @Override
  public Optional<Boolean> addWatermark() {
    return addWatermark;
  }

  @JsonProperty("enhancePrompt")
  @Override
  public Optional<Boolean> enhancePrompt() {
    return enhancePrompt;
  }

  @Override
  public String toString() {
    return "GenerateImagesConfig{"
        + "outputGcsUri=" + outputGcsUri + ", "
        + "negativePrompt=" + negativePrompt + ", "
        + "numberOfImages=" + numberOfImages + ", "
        + "aspectRatio=" + aspectRatio + ", "
        + "guidanceScale=" + guidanceScale + ", "
        + "seed=" + seed + ", "
        + "safetyFilterLevel=" + safetyFilterLevel + ", "
        + "personGeneration=" + personGeneration + ", "
        + "includeSafetyAttributes=" + includeSafetyAttributes + ", "
        + "includeRaiReason=" + includeRaiReason + ", "
        + "language=" + language + ", "
        + "outputMimeType=" + outputMimeType + ", "
        + "outputCompressionQuality=" + outputCompressionQuality + ", "
        + "addWatermark=" + addWatermark + ", "
        + "enhancePrompt=" + enhancePrompt
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof GenerateImagesConfig) {
      GenerateImagesConfig that = (GenerateImagesConfig) o;
      return this.outputGcsUri.equals(that.outputGcsUri())
          && this.negativePrompt.equals(that.negativePrompt())
          && this.numberOfImages.equals(that.numberOfImages())
          && this.aspectRatio.equals(that.aspectRatio())
          && this.guidanceScale.equals(that.guidanceScale())
          && this.seed.equals(that.seed())
          && this.safetyFilterLevel.equals(that.safetyFilterLevel())
          && this.personGeneration.equals(that.personGeneration())
          && this.includeSafetyAttributes.equals(that.includeSafetyAttributes())
          && this.includeRaiReason.equals(that.includeRaiReason())
          && this.language.equals(that.language())
          && this.outputMimeType.equals(that.outputMimeType())
          && this.outputCompressionQuality.equals(that.outputCompressionQuality())
          && this.addWatermark.equals(that.addWatermark())
          && this.enhancePrompt.equals(that.enhancePrompt());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= outputGcsUri.hashCode();
    h$ *= 1000003;
    h$ ^= negativePrompt.hashCode();
    h$ *= 1000003;
    h$ ^= numberOfImages.hashCode();
    h$ *= 1000003;
    h$ ^= aspectRatio.hashCode();
    h$ *= 1000003;
    h$ ^= guidanceScale.hashCode();
    h$ *= 1000003;
    h$ ^= seed.hashCode();
    h$ *= 1000003;
    h$ ^= safetyFilterLevel.hashCode();
    h$ *= 1000003;
    h$ ^= personGeneration.hashCode();
    h$ *= 1000003;
    h$ ^= includeSafetyAttributes.hashCode();
    h$ *= 1000003;
    h$ ^= includeRaiReason.hashCode();
    h$ *= 1000003;
    h$ ^= language.hashCode();
    h$ *= 1000003;
    h$ ^= outputMimeType.hashCode();
    h$ *= 1000003;
    h$ ^= outputCompressionQuality.hashCode();
    h$ *= 1000003;
    h$ ^= addWatermark.hashCode();
    h$ *= 1000003;
    h$ ^= enhancePrompt.hashCode();
    return h$;
  }

  @Override
  public GenerateImagesConfig.Builder toBuilder() {
    return new AutoValue_GenerateImagesConfig.Builder(this);
  }

  static final class Builder extends GenerateImagesConfig.Builder {
    private Optional<String> outputGcsUri = Optional.empty();
    private Optional<String> negativePrompt = Optional.empty();
    private Optional<Integer> numberOfImages = Optional.empty();
    private Optional<String> aspectRatio = Optional.empty();
    private Optional<Float> guidanceScale = Optional.empty();
    private Optional<Integer> seed = Optional.empty();
    private Optional<String> safetyFilterLevel = Optional.empty();
    private Optional<String> personGeneration = Optional.empty();
    private Optional<Boolean> includeSafetyAttributes = Optional.empty();
    private Optional<Boolean> includeRaiReason = Optional.empty();
    private Optional<String> language = Optional.empty();
    private Optional<String> outputMimeType = Optional.empty();
    private Optional<Integer> outputCompressionQuality = Optional.empty();
    private Optional<Boolean> addWatermark = Optional.empty();
    private Optional<Boolean> enhancePrompt = Optional.empty();
    Builder() {
    }
    Builder(GenerateImagesConfig source) {
      this.outputGcsUri = source.outputGcsUri();
      this.negativePrompt = source.negativePrompt();
      this.numberOfImages = source.numberOfImages();
      this.aspectRatio = source.aspectRatio();
      this.guidanceScale = source.guidanceScale();
      this.seed = source.seed();
      this.safetyFilterLevel = source.safetyFilterLevel();
      this.personGeneration = source.personGeneration();
      this.includeSafetyAttributes = source.includeSafetyAttributes();
      this.includeRaiReason = source.includeRaiReason();
      this.language = source.language();
      this.outputMimeType = source.outputMimeType();
      this.outputCompressionQuality = source.outputCompressionQuality();
      this.addWatermark = source.addWatermark();
      this.enhancePrompt = source.enhancePrompt();
    }
    @Override
    public GenerateImagesConfig.Builder outputGcsUri(String outputGcsUri) {
      this.outputGcsUri = Optional.of(outputGcsUri);
      return this;
    }
    @Override
    public GenerateImagesConfig.Builder negativePrompt(String negativePrompt) {
      this.negativePrompt = Optional.of(negativePrompt);
      return this;
    }
    @Override
    public GenerateImagesConfig.Builder numberOfImages(Integer numberOfImages) {
      this.numberOfImages = Optional.of(numberOfImages);
      return this;
    }
    @Override
    public GenerateImagesConfig.Builder aspectRatio(String aspectRatio) {
      this.aspectRatio = Optional.of(aspectRatio);
      return this;
    }
    @Override
    public GenerateImagesConfig.Builder guidanceScale(Float guidanceScale) {
      this.guidanceScale = Optional.of(guidanceScale);
      return this;
    }
    @Override
    public GenerateImagesConfig.Builder seed(Integer seed) {
      this.seed = Optional.of(seed);
      return this;
    }
    @Override
    public GenerateImagesConfig.Builder safetyFilterLevel(String safetyFilterLevel) {
      this.safetyFilterLevel = Optional.of(safetyFilterLevel);
      return this;
    }
    @Override
    public GenerateImagesConfig.Builder personGeneration(String personGeneration) {
      this.personGeneration = Optional.of(personGeneration);
      return this;
    }
    @Override
    public GenerateImagesConfig.Builder includeSafetyAttributes(boolean includeSafetyAttributes) {
      this.includeSafetyAttributes = Optional.of(includeSafetyAttributes);
      return this;
    }
    @Override
    public GenerateImagesConfig.Builder includeRaiReason(boolean includeRaiReason) {
      this.includeRaiReason = Optional.of(includeRaiReason);
      return this;
    }
    @Override
    public GenerateImagesConfig.Builder language(String language) {
      this.language = Optional.of(language);
      return this;
    }
    @Override
    public GenerateImagesConfig.Builder outputMimeType(String outputMimeType) {
      this.outputMimeType = Optional.of(outputMimeType);
      return this;
    }
    @Override
    public GenerateImagesConfig.Builder outputCompressionQuality(Integer outputCompressionQuality) {
      this.outputCompressionQuality = Optional.of(outputCompressionQuality);
      return this;
    }
    @Override
    public GenerateImagesConfig.Builder addWatermark(boolean addWatermark) {
      this.addWatermark = Optional.of(addWatermark);
      return this;
    }
    @Override
    public GenerateImagesConfig.Builder enhancePrompt(boolean enhancePrompt) {
      this.enhancePrompt = Optional.of(enhancePrompt);
      return this;
    }
    @Override
    public GenerateImagesConfig build() {
      return new AutoValue_GenerateImagesConfig(
          this.outputGcsUri,
          this.negativePrompt,
          this.numberOfImages,
          this.aspectRatio,
          this.guidanceScale,
          this.seed,
          this.safetyFilterLevel,
          this.personGeneration,
          this.includeSafetyAttributes,
          this.includeRaiReason,
          this.language,
          this.outputMimeType,
          this.outputCompressionQuality,
          this.addWatermark,
          this.enhancePrompt);
    }
  }

}
