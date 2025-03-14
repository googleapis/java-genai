package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_UploadFileConfig extends UploadFileConfig {

  private final Optional<String> name;

  private final Optional<String> mimeType;

  private final Optional<String> displayName;

  private AutoValue_UploadFileConfig(
      Optional<String> name,
      Optional<String> mimeType,
      Optional<String> displayName) {
    this.name = name;
    this.mimeType = mimeType;
    this.displayName = displayName;
  }

  @JsonProperty("name")
  @Override
  public Optional<String> name() {
    return name;
  }

  @JsonProperty("mimeType")
  @Override
  public Optional<String> mimeType() {
    return mimeType;
  }

  @JsonProperty("displayName")
  @Override
  public Optional<String> displayName() {
    return displayName;
  }

  @Override
  public String toString() {
    return "UploadFileConfig{"
        + "name=" + name + ", "
        + "mimeType=" + mimeType + ", "
        + "displayName=" + displayName
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof UploadFileConfig) {
      UploadFileConfig that = (UploadFileConfig) o;
      return this.name.equals(that.name())
          && this.mimeType.equals(that.mimeType())
          && this.displayName.equals(that.displayName());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= name.hashCode();
    h$ *= 1000003;
    h$ ^= mimeType.hashCode();
    h$ *= 1000003;
    h$ ^= displayName.hashCode();
    return h$;
  }

  @Override
  public UploadFileConfig.Builder toBuilder() {
    return new AutoValue_UploadFileConfig.Builder(this);
  }

  static final class Builder extends UploadFileConfig.Builder {
    private Optional<String> name = Optional.empty();
    private Optional<String> mimeType = Optional.empty();
    private Optional<String> displayName = Optional.empty();
    Builder() {
    }
    Builder(UploadFileConfig source) {
      this.name = source.name();
      this.mimeType = source.mimeType();
      this.displayName = source.displayName();
    }
    @Override
    public UploadFileConfig.Builder name(String name) {
      this.name = Optional.of(name);
      return this;
    }
    @Override
    public UploadFileConfig.Builder mimeType(String mimeType) {
      this.mimeType = Optional.of(mimeType);
      return this;
    }
    @Override
    public UploadFileConfig.Builder displayName(String displayName) {
      this.displayName = Optional.of(displayName);
      return this;
    }
    @Override
    public UploadFileConfig build() {
      return new AutoValue_UploadFileConfig(
          this.name,
          this.mimeType,
          this.displayName);
    }
  }

}
