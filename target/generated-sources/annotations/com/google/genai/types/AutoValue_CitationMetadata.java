package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_CitationMetadata extends CitationMetadata {

  private final Optional<List<Citation>> citations;

  private AutoValue_CitationMetadata(
      Optional<List<Citation>> citations) {
    this.citations = citations;
  }

  @JsonProperty("citations")
  @Override
  public Optional<List<Citation>> citations() {
    return citations;
  }

  @Override
  public String toString() {
    return "CitationMetadata{"
        + "citations=" + citations
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof CitationMetadata) {
      CitationMetadata that = (CitationMetadata) o;
      return this.citations.equals(that.citations());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= citations.hashCode();
    return h$;
  }

  @Override
  public CitationMetadata.Builder toBuilder() {
    return new AutoValue_CitationMetadata.Builder(this);
  }

  static final class Builder extends CitationMetadata.Builder {
    private Optional<List<Citation>> citations = Optional.empty();
    Builder() {
    }
    Builder(CitationMetadata source) {
      this.citations = source.citations();
    }
    @Override
    public CitationMetadata.Builder citations(List<Citation> citations) {
      this.citations = Optional.of(citations);
      return this;
    }
    @Override
    public CitationMetadata build() {
      return new AutoValue_CitationMetadata(
          this.citations);
    }
  }

}
