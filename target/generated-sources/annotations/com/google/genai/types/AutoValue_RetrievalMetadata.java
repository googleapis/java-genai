package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_RetrievalMetadata extends RetrievalMetadata {

  private final Optional<Float> googleSearchDynamicRetrievalScore;

  private AutoValue_RetrievalMetadata(
      Optional<Float> googleSearchDynamicRetrievalScore) {
    this.googleSearchDynamicRetrievalScore = googleSearchDynamicRetrievalScore;
  }

  @JsonProperty("googleSearchDynamicRetrievalScore")
  @Override
  public Optional<Float> googleSearchDynamicRetrievalScore() {
    return googleSearchDynamicRetrievalScore;
  }

  @Override
  public String toString() {
    return "RetrievalMetadata{"
        + "googleSearchDynamicRetrievalScore=" + googleSearchDynamicRetrievalScore
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof RetrievalMetadata) {
      RetrievalMetadata that = (RetrievalMetadata) o;
      return this.googleSearchDynamicRetrievalScore.equals(that.googleSearchDynamicRetrievalScore());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= googleSearchDynamicRetrievalScore.hashCode();
    return h$;
  }

  @Override
  public RetrievalMetadata.Builder toBuilder() {
    return new AutoValue_RetrievalMetadata.Builder(this);
  }

  static final class Builder extends RetrievalMetadata.Builder {
    private Optional<Float> googleSearchDynamicRetrievalScore = Optional.empty();
    Builder() {
    }
    Builder(RetrievalMetadata source) {
      this.googleSearchDynamicRetrievalScore = source.googleSearchDynamicRetrievalScore();
    }
    @Override
    public RetrievalMetadata.Builder googleSearchDynamicRetrievalScore(Float googleSearchDynamicRetrievalScore) {
      this.googleSearchDynamicRetrievalScore = Optional.of(googleSearchDynamicRetrievalScore);
      return this;
    }
    @Override
    public RetrievalMetadata build() {
      return new AutoValue_RetrievalMetadata(
          this.googleSearchDynamicRetrievalScore);
    }
  }

}
