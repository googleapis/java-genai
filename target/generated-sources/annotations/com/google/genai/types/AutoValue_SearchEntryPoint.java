package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_SearchEntryPoint extends SearchEntryPoint {

  private final Optional<String> renderedContent;

  private final Optional<byte[]> sdkBlob;

  private AutoValue_SearchEntryPoint(
      Optional<String> renderedContent,
      Optional<byte[]> sdkBlob) {
    this.renderedContent = renderedContent;
    this.sdkBlob = sdkBlob;
  }

  @JsonProperty("renderedContent")
  @Override
  public Optional<String> renderedContent() {
    return renderedContent;
  }

  @JsonProperty("sdkBlob")
  @Override
  public Optional<byte[]> sdkBlob() {
    return sdkBlob;
  }

  @Override
  public String toString() {
    return "SearchEntryPoint{"
        + "renderedContent=" + renderedContent + ", "
        + "sdkBlob=" + sdkBlob
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof SearchEntryPoint) {
      SearchEntryPoint that = (SearchEntryPoint) o;
      return this.renderedContent.equals(that.renderedContent())
          && this.sdkBlob.equals(that.sdkBlob());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= renderedContent.hashCode();
    h$ *= 1000003;
    h$ ^= sdkBlob.hashCode();
    return h$;
  }

  @Override
  public SearchEntryPoint.Builder toBuilder() {
    return new AutoValue_SearchEntryPoint.Builder(this);
  }

  static final class Builder extends SearchEntryPoint.Builder {
    private Optional<String> renderedContent = Optional.empty();
    private Optional<byte[]> sdkBlob = Optional.empty();
    Builder() {
    }
    Builder(SearchEntryPoint source) {
      this.renderedContent = source.renderedContent();
      this.sdkBlob = source.sdkBlob();
    }
    @Override
    public SearchEntryPoint.Builder renderedContent(String renderedContent) {
      this.renderedContent = Optional.of(renderedContent);
      return this;
    }
    @Override
    public SearchEntryPoint.Builder sdkBlob(byte[] sdkBlob) {
      this.sdkBlob = Optional.of(sdkBlob);
      return this;
    }
    @Override
    public SearchEntryPoint build() {
      return new AutoValue_SearchEntryPoint(
          this.renderedContent,
          this.sdkBlob);
    }
  }

}
