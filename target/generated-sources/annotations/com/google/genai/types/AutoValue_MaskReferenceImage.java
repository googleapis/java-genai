package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_MaskReferenceImage extends MaskReferenceImage {

  private final Optional<Image> referenceImage;

  private final Optional<Integer> referenceId;

  private final Optional<String> referenceType;

  private final Optional<MaskReferenceConfig> config;

  private AutoValue_MaskReferenceImage(
      Optional<Image> referenceImage,
      Optional<Integer> referenceId,
      Optional<String> referenceType,
      Optional<MaskReferenceConfig> config) {
    this.referenceImage = referenceImage;
    this.referenceId = referenceId;
    this.referenceType = referenceType;
    this.config = config;
  }

  @JsonProperty("referenceImage")
  @Override
  public Optional<Image> referenceImage() {
    return referenceImage;
  }

  @JsonProperty("referenceId")
  @Override
  public Optional<Integer> referenceId() {
    return referenceId;
  }

  @JsonProperty("referenceType")
  @Override
  public Optional<String> referenceType() {
    return referenceType;
  }

  @JsonProperty("config")
  @Override
  public Optional<MaskReferenceConfig> config() {
    return config;
  }

  @Override
  public String toString() {
    return "MaskReferenceImage{"
        + "referenceImage=" + referenceImage + ", "
        + "referenceId=" + referenceId + ", "
        + "referenceType=" + referenceType + ", "
        + "config=" + config
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof MaskReferenceImage) {
      MaskReferenceImage that = (MaskReferenceImage) o;
      return this.referenceImage.equals(that.referenceImage())
          && this.referenceId.equals(that.referenceId())
          && this.referenceType.equals(that.referenceType())
          && this.config.equals(that.config());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= referenceImage.hashCode();
    h$ *= 1000003;
    h$ ^= referenceId.hashCode();
    h$ *= 1000003;
    h$ ^= referenceType.hashCode();
    h$ *= 1000003;
    h$ ^= config.hashCode();
    return h$;
  }

  @Override
  public MaskReferenceImage.Builder toBuilder() {
    return new AutoValue_MaskReferenceImage.Builder(this);
  }

  static final class Builder extends MaskReferenceImage.Builder {
    private Optional<Image> referenceImage = Optional.empty();
    private Optional<Integer> referenceId = Optional.empty();
    private Optional<String> referenceType = Optional.empty();
    private Optional<MaskReferenceConfig> config = Optional.empty();
    Builder() {
    }
    Builder(MaskReferenceImage source) {
      this.referenceImage = source.referenceImage();
      this.referenceId = source.referenceId();
      this.referenceType = source.referenceType();
      this.config = source.config();
    }
    @Override
    public MaskReferenceImage.Builder referenceImage(Image referenceImage) {
      this.referenceImage = Optional.of(referenceImage);
      return this;
    }
    @Override
    public MaskReferenceImage.Builder referenceId(Integer referenceId) {
      this.referenceId = Optional.of(referenceId);
      return this;
    }
    @Override
    public MaskReferenceImage.Builder referenceType(String referenceType) {
      this.referenceType = Optional.of(referenceType);
      return this;
    }
    @Override
    public MaskReferenceImage.Builder config(MaskReferenceConfig config) {
      this.config = Optional.of(config);
      return this;
    }
    @Override
    public MaskReferenceImage build() {
      return new AutoValue_MaskReferenceImage(
          this.referenceImage,
          this.referenceId,
          this.referenceType,
          this.config);
    }
  }

}
