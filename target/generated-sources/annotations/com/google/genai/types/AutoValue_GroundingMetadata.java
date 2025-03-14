package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_GroundingMetadata extends GroundingMetadata {

  private final Optional<List<GroundingChunk>> groundingChunks;

  private final Optional<List<GroundingSupport>> groundingSupports;

  private final Optional<RetrievalMetadata> retrievalMetadata;

  private final Optional<List<String>> retrievalQueries;

  private final Optional<SearchEntryPoint> searchEntryPoint;

  private final Optional<List<String>> webSearchQueries;

  private AutoValue_GroundingMetadata(
      Optional<List<GroundingChunk>> groundingChunks,
      Optional<List<GroundingSupport>> groundingSupports,
      Optional<RetrievalMetadata> retrievalMetadata,
      Optional<List<String>> retrievalQueries,
      Optional<SearchEntryPoint> searchEntryPoint,
      Optional<List<String>> webSearchQueries) {
    this.groundingChunks = groundingChunks;
    this.groundingSupports = groundingSupports;
    this.retrievalMetadata = retrievalMetadata;
    this.retrievalQueries = retrievalQueries;
    this.searchEntryPoint = searchEntryPoint;
    this.webSearchQueries = webSearchQueries;
  }

  @JsonProperty("groundingChunks")
  @Override
  public Optional<List<GroundingChunk>> groundingChunks() {
    return groundingChunks;
  }

  @JsonProperty("groundingSupports")
  @Override
  public Optional<List<GroundingSupport>> groundingSupports() {
    return groundingSupports;
  }

  @JsonProperty("retrievalMetadata")
  @Override
  public Optional<RetrievalMetadata> retrievalMetadata() {
    return retrievalMetadata;
  }

  @JsonProperty("retrievalQueries")
  @Override
  public Optional<List<String>> retrievalQueries() {
    return retrievalQueries;
  }

  @JsonProperty("searchEntryPoint")
  @Override
  public Optional<SearchEntryPoint> searchEntryPoint() {
    return searchEntryPoint;
  }

  @JsonProperty("webSearchQueries")
  @Override
  public Optional<List<String>> webSearchQueries() {
    return webSearchQueries;
  }

  @Override
  public String toString() {
    return "GroundingMetadata{"
        + "groundingChunks=" + groundingChunks + ", "
        + "groundingSupports=" + groundingSupports + ", "
        + "retrievalMetadata=" + retrievalMetadata + ", "
        + "retrievalQueries=" + retrievalQueries + ", "
        + "searchEntryPoint=" + searchEntryPoint + ", "
        + "webSearchQueries=" + webSearchQueries
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof GroundingMetadata) {
      GroundingMetadata that = (GroundingMetadata) o;
      return this.groundingChunks.equals(that.groundingChunks())
          && this.groundingSupports.equals(that.groundingSupports())
          && this.retrievalMetadata.equals(that.retrievalMetadata())
          && this.retrievalQueries.equals(that.retrievalQueries())
          && this.searchEntryPoint.equals(that.searchEntryPoint())
          && this.webSearchQueries.equals(that.webSearchQueries());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= groundingChunks.hashCode();
    h$ *= 1000003;
    h$ ^= groundingSupports.hashCode();
    h$ *= 1000003;
    h$ ^= retrievalMetadata.hashCode();
    h$ *= 1000003;
    h$ ^= retrievalQueries.hashCode();
    h$ *= 1000003;
    h$ ^= searchEntryPoint.hashCode();
    h$ *= 1000003;
    h$ ^= webSearchQueries.hashCode();
    return h$;
  }

  @Override
  public GroundingMetadata.Builder toBuilder() {
    return new AutoValue_GroundingMetadata.Builder(this);
  }

  static final class Builder extends GroundingMetadata.Builder {
    private Optional<List<GroundingChunk>> groundingChunks = Optional.empty();
    private Optional<List<GroundingSupport>> groundingSupports = Optional.empty();
    private Optional<RetrievalMetadata> retrievalMetadata = Optional.empty();
    private Optional<List<String>> retrievalQueries = Optional.empty();
    private Optional<SearchEntryPoint> searchEntryPoint = Optional.empty();
    private Optional<List<String>> webSearchQueries = Optional.empty();
    Builder() {
    }
    Builder(GroundingMetadata source) {
      this.groundingChunks = source.groundingChunks();
      this.groundingSupports = source.groundingSupports();
      this.retrievalMetadata = source.retrievalMetadata();
      this.retrievalQueries = source.retrievalQueries();
      this.searchEntryPoint = source.searchEntryPoint();
      this.webSearchQueries = source.webSearchQueries();
    }
    @Override
    public GroundingMetadata.Builder groundingChunks(List<GroundingChunk> groundingChunks) {
      this.groundingChunks = Optional.of(groundingChunks);
      return this;
    }
    @Override
    public GroundingMetadata.Builder groundingSupports(List<GroundingSupport> groundingSupports) {
      this.groundingSupports = Optional.of(groundingSupports);
      return this;
    }
    @Override
    public GroundingMetadata.Builder retrievalMetadata(RetrievalMetadata retrievalMetadata) {
      this.retrievalMetadata = Optional.of(retrievalMetadata);
      return this;
    }
    @Override
    public GroundingMetadata.Builder retrievalQueries(List<String> retrievalQueries) {
      this.retrievalQueries = Optional.of(retrievalQueries);
      return this;
    }
    @Override
    public GroundingMetadata.Builder searchEntryPoint(SearchEntryPoint searchEntryPoint) {
      this.searchEntryPoint = Optional.of(searchEntryPoint);
      return this;
    }
    @Override
    public GroundingMetadata.Builder webSearchQueries(List<String> webSearchQueries) {
      this.webSearchQueries = Optional.of(webSearchQueries);
      return this;
    }
    @Override
    public GroundingMetadata build() {
      return new AutoValue_GroundingMetadata(
          this.groundingChunks,
          this.groundingSupports,
          this.retrievalMetadata,
          this.retrievalQueries,
          this.searchEntryPoint,
          this.webSearchQueries);
    }
  }

}
