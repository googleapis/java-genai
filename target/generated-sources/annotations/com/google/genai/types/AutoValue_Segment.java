package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_Segment extends Segment {

  private final Optional<Integer> endIndex;

  private final Optional<Integer> partIndex;

  private final Optional<Integer> startIndex;

  private final Optional<String> text;

  private AutoValue_Segment(
      Optional<Integer> endIndex,
      Optional<Integer> partIndex,
      Optional<Integer> startIndex,
      Optional<String> text) {
    this.endIndex = endIndex;
    this.partIndex = partIndex;
    this.startIndex = startIndex;
    this.text = text;
  }

  @JsonProperty("endIndex")
  @Override
  public Optional<Integer> endIndex() {
    return endIndex;
  }

  @JsonProperty("partIndex")
  @Override
  public Optional<Integer> partIndex() {
    return partIndex;
  }

  @JsonProperty("startIndex")
  @Override
  public Optional<Integer> startIndex() {
    return startIndex;
  }

  @JsonProperty("text")
  @Override
  public Optional<String> text() {
    return text;
  }

  @Override
  public String toString() {
    return "Segment{"
        + "endIndex=" + endIndex + ", "
        + "partIndex=" + partIndex + ", "
        + "startIndex=" + startIndex + ", "
        + "text=" + text
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof Segment) {
      Segment that = (Segment) o;
      return this.endIndex.equals(that.endIndex())
          && this.partIndex.equals(that.partIndex())
          && this.startIndex.equals(that.startIndex())
          && this.text.equals(that.text());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= endIndex.hashCode();
    h$ *= 1000003;
    h$ ^= partIndex.hashCode();
    h$ *= 1000003;
    h$ ^= startIndex.hashCode();
    h$ *= 1000003;
    h$ ^= text.hashCode();
    return h$;
  }

  @Override
  public Segment.Builder toBuilder() {
    return new AutoValue_Segment.Builder(this);
  }

  static final class Builder extends Segment.Builder {
    private Optional<Integer> endIndex = Optional.empty();
    private Optional<Integer> partIndex = Optional.empty();
    private Optional<Integer> startIndex = Optional.empty();
    private Optional<String> text = Optional.empty();
    Builder() {
    }
    Builder(Segment source) {
      this.endIndex = source.endIndex();
      this.partIndex = source.partIndex();
      this.startIndex = source.startIndex();
      this.text = source.text();
    }
    @Override
    public Segment.Builder endIndex(Integer endIndex) {
      this.endIndex = Optional.of(endIndex);
      return this;
    }
    @Override
    public Segment.Builder partIndex(Integer partIndex) {
      this.partIndex = Optional.of(partIndex);
      return this;
    }
    @Override
    public Segment.Builder startIndex(Integer startIndex) {
      this.startIndex = Optional.of(startIndex);
      return this;
    }
    @Override
    public Segment.Builder text(String text) {
      this.text = Optional.of(text);
      return this;
    }
    @Override
    public Segment build() {
      return new AutoValue_Segment(
          this.endIndex,
          this.partIndex,
          this.startIndex,
          this.text);
    }
  }

}
