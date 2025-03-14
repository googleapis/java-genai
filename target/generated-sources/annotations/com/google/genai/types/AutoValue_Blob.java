package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_Blob extends Blob {

  private final Optional<byte[]> data;

  private final Optional<String> mimeType;

  private AutoValue_Blob(
      Optional<byte[]> data,
      Optional<String> mimeType) {
    this.data = data;
    this.mimeType = mimeType;
  }

  @JsonProperty("data")
  @Override
  public Optional<byte[]> data() {
    return data;
  }

  @JsonProperty("mimeType")
  @Override
  public Optional<String> mimeType() {
    return mimeType;
  }

  @Override
  public String toString() {
    return "Blob{"
        + "data=" + data + ", "
        + "mimeType=" + mimeType
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof Blob) {
      Blob that = (Blob) o;
      return this.data.equals(that.data())
          && this.mimeType.equals(that.mimeType());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= data.hashCode();
    h$ *= 1000003;
    h$ ^= mimeType.hashCode();
    return h$;
  }

  @Override
  public Blob.Builder toBuilder() {
    return new AutoValue_Blob.Builder(this);
  }

  static final class Builder extends Blob.Builder {
    private Optional<byte[]> data = Optional.empty();
    private Optional<String> mimeType = Optional.empty();
    Builder() {
    }
    Builder(Blob source) {
      this.data = source.data();
      this.mimeType = source.mimeType();
    }
    @Override
    public Blob.Builder data(byte[] data) {
      this.data = Optional.of(data);
      return this;
    }
    @Override
    public Blob.Builder mimeType(String mimeType) {
      this.mimeType = Optional.of(mimeType);
      return this;
    }
    @Override
    public Blob build() {
      return new AutoValue_Blob(
          this.data,
          this.mimeType);
    }
  }

}
