package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_GroundingSupport extends GroundingSupport {

  private final Optional<List<Float>> confidenceScores;

  private final Optional<List<Integer>> groundingChunkIndices;

  private final Optional<Segment> segment;

  private AutoValue_GroundingSupport(
      Optional<List<Float>> confidenceScores,
      Optional<List<Integer>> groundingChunkIndices,
      Optional<Segment> segment) {
    this.confidenceScores = confidenceScores;
    this.groundingChunkIndices = groundingChunkIndices;
    this.segment = segment;
  }

  @JsonProperty("confidenceScores")
  @Override
  public Optional<List<Float>> confidenceScores() {
    return confidenceScores;
  }

  @JsonProperty("groundingChunkIndices")
  @Override
  public Optional<List<Integer>> groundingChunkIndices() {
    return groundingChunkIndices;
  }

  @JsonProperty("segment")
  @Override
  public Optional<Segment> segment() {
    return segment;
  }

  @Override
  public String toString() {
    return "GroundingSupport{"
        + "confidenceScores=" + confidenceScores + ", "
        + "groundingChunkIndices=" + groundingChunkIndices + ", "
        + "segment=" + segment
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof GroundingSupport) {
      GroundingSupport that = (GroundingSupport) o;
      return this.confidenceScores.equals(that.confidenceScores())
          && this.groundingChunkIndices.equals(that.groundingChunkIndices())
          && this.segment.equals(that.segment());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= confidenceScores.hashCode();
    h$ *= 1000003;
    h$ ^= groundingChunkIndices.hashCode();
    h$ *= 1000003;
    h$ ^= segment.hashCode();
    return h$;
  }

  @Override
  public GroundingSupport.Builder toBuilder() {
    return new AutoValue_GroundingSupport.Builder(this);
  }

  static final class Builder extends GroundingSupport.Builder {
    private Optional<List<Float>> confidenceScores = Optional.empty();
    private Optional<List<Integer>> groundingChunkIndices = Optional.empty();
    private Optional<Segment> segment = Optional.empty();
    Builder() {
    }
    Builder(GroundingSupport source) {
      this.confidenceScores = source.confidenceScores();
      this.groundingChunkIndices = source.groundingChunkIndices();
      this.segment = source.segment();
    }
    @Override
    public GroundingSupport.Builder confidenceScores(List<Float> confidenceScores) {
      this.confidenceScores = Optional.of(confidenceScores);
      return this;
    }
    @Override
    public GroundingSupport.Builder groundingChunkIndices(List<Integer> groundingChunkIndices) {
      this.groundingChunkIndices = Optional.of(groundingChunkIndices);
      return this;
    }
    @Override
    public GroundingSupport.Builder segment(Segment segment) {
      this.segment = Optional.of(segment);
      return this;
    }
    @Override
    public GroundingSupport build() {
      return new AutoValue_GroundingSupport(
          this.confidenceScores,
          this.groundingChunkIndices,
          this.segment);
    }
  }

}
