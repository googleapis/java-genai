package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_VideoMetadata extends VideoMetadata {

  private final Optional<String> endOffset;

  private final Optional<String> startOffset;

  private AutoValue_VideoMetadata(
      Optional<String> endOffset,
      Optional<String> startOffset) {
    this.endOffset = endOffset;
    this.startOffset = startOffset;
  }

  @JsonProperty("endOffset")
  @Override
  public Optional<String> endOffset() {
    return endOffset;
  }

  @JsonProperty("startOffset")
  @Override
  public Optional<String> startOffset() {
    return startOffset;
  }

  @Override
  public String toString() {
    return "VideoMetadata{"
        + "endOffset=" + endOffset + ", "
        + "startOffset=" + startOffset
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof VideoMetadata) {
      VideoMetadata that = (VideoMetadata) o;
      return this.endOffset.equals(that.endOffset())
          && this.startOffset.equals(that.startOffset());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= endOffset.hashCode();
    h$ *= 1000003;
    h$ ^= startOffset.hashCode();
    return h$;
  }

  @Override
  public VideoMetadata.Builder toBuilder() {
    return new AutoValue_VideoMetadata.Builder(this);
  }

  static final class Builder extends VideoMetadata.Builder {
    private Optional<String> endOffset = Optional.empty();
    private Optional<String> startOffset = Optional.empty();
    Builder() {
    }
    Builder(VideoMetadata source) {
      this.endOffset = source.endOffset();
      this.startOffset = source.startOffset();
    }
    @Override
    public VideoMetadata.Builder endOffset(String endOffset) {
      this.endOffset = Optional.of(endOffset);
      return this;
    }
    @Override
    public VideoMetadata.Builder startOffset(String startOffset) {
      this.startOffset = Optional.of(startOffset);
      return this;
    }
    @Override
    public VideoMetadata build() {
      return new AutoValue_VideoMetadata(
          this.endOffset,
          this.startOffset);
    }
  }

}
