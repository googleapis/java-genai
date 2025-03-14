package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_Image extends Image {

  private final Optional<String> gcsUri;

  private final Optional<byte[]> imageBytes;

  private final Optional<String> mimeType;

  private AutoValue_Image(
      Optional<String> gcsUri,
      Optional<byte[]> imageBytes,
      Optional<String> mimeType) {
    this.gcsUri = gcsUri;
    this.imageBytes = imageBytes;
    this.mimeType = mimeType;
  }

  @JsonProperty("gcsUri")
  @Override
  public Optional<String> gcsUri() {
    return gcsUri;
  }

  @JsonProperty("imageBytes")
  @Override
  public Optional<byte[]> imageBytes() {
    return imageBytes;
  }

  @JsonProperty("mimeType")
  @Override
  public Optional<String> mimeType() {
    return mimeType;
  }

  @Override
  public String toString() {
    return "Image{"
        + "gcsUri=" + gcsUri + ", "
        + "imageBytes=" + imageBytes + ", "
        + "mimeType=" + mimeType
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof Image) {
      Image that = (Image) o;
      return this.gcsUri.equals(that.gcsUri())
          && this.imageBytes.equals(that.imageBytes())
          && this.mimeType.equals(that.mimeType());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= gcsUri.hashCode();
    h$ *= 1000003;
    h$ ^= imageBytes.hashCode();
    h$ *= 1000003;
    h$ ^= mimeType.hashCode();
    return h$;
  }

  @Override
  public Image.Builder toBuilder() {
    return new AutoValue_Image.Builder(this);
  }

  static final class Builder extends Image.Builder {
    private Optional<String> gcsUri = Optional.empty();
    private Optional<byte[]> imageBytes = Optional.empty();
    private Optional<String> mimeType = Optional.empty();
    Builder() {
    }
    Builder(Image source) {
      this.gcsUri = source.gcsUri();
      this.imageBytes = source.imageBytes();
      this.mimeType = source.mimeType();
    }
    @Override
    public Image.Builder gcsUri(String gcsUri) {
      this.gcsUri = Optional.of(gcsUri);
      return this;
    }
    @Override
    public Image.Builder imageBytes(byte[] imageBytes) {
      this.imageBytes = Optional.of(imageBytes);
      return this;
    }
    @Override
    public Image.Builder mimeType(String mimeType) {
      this.mimeType = Optional.of(mimeType);
      return this;
    }
    @Override
    public Image build() {
      return new AutoValue_Image(
          this.gcsUri,
          this.imageBytes,
          this.mimeType);
    }
  }

}
