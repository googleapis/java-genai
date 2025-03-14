package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_Retrieval extends Retrieval {

  private final Optional<Boolean> disableAttribution;

  private final Optional<VertexAISearch> vertexAiSearch;

  private final Optional<VertexRagStore> vertexRagStore;

  private AutoValue_Retrieval(
      Optional<Boolean> disableAttribution,
      Optional<VertexAISearch> vertexAiSearch,
      Optional<VertexRagStore> vertexRagStore) {
    this.disableAttribution = disableAttribution;
    this.vertexAiSearch = vertexAiSearch;
    this.vertexRagStore = vertexRagStore;
  }

  @JsonProperty("disableAttribution")
  @Override
  public Optional<Boolean> disableAttribution() {
    return disableAttribution;
  }

  @JsonProperty("vertexAiSearch")
  @Override
  public Optional<VertexAISearch> vertexAiSearch() {
    return vertexAiSearch;
  }

  @JsonProperty("vertexRagStore")
  @Override
  public Optional<VertexRagStore> vertexRagStore() {
    return vertexRagStore;
  }

  @Override
  public String toString() {
    return "Retrieval{"
        + "disableAttribution=" + disableAttribution + ", "
        + "vertexAiSearch=" + vertexAiSearch + ", "
        + "vertexRagStore=" + vertexRagStore
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof Retrieval) {
      Retrieval that = (Retrieval) o;
      return this.disableAttribution.equals(that.disableAttribution())
          && this.vertexAiSearch.equals(that.vertexAiSearch())
          && this.vertexRagStore.equals(that.vertexRagStore());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= disableAttribution.hashCode();
    h$ *= 1000003;
    h$ ^= vertexAiSearch.hashCode();
    h$ *= 1000003;
    h$ ^= vertexRagStore.hashCode();
    return h$;
  }

  @Override
  public Retrieval.Builder toBuilder() {
    return new AutoValue_Retrieval.Builder(this);
  }

  static final class Builder extends Retrieval.Builder {
    private Optional<Boolean> disableAttribution = Optional.empty();
    private Optional<VertexAISearch> vertexAiSearch = Optional.empty();
    private Optional<VertexRagStore> vertexRagStore = Optional.empty();
    Builder() {
    }
    Builder(Retrieval source) {
      this.disableAttribution = source.disableAttribution();
      this.vertexAiSearch = source.vertexAiSearch();
      this.vertexRagStore = source.vertexRagStore();
    }
    @Override
    public Retrieval.Builder disableAttribution(boolean disableAttribution) {
      this.disableAttribution = Optional.of(disableAttribution);
      return this;
    }
    @Override
    public Retrieval.Builder vertexAiSearch(VertexAISearch vertexAiSearch) {
      this.vertexAiSearch = Optional.of(vertexAiSearch);
      return this;
    }
    @Override
    public Retrieval.Builder vertexRagStore(VertexRagStore vertexRagStore) {
      this.vertexRagStore = Optional.of(vertexRagStore);
      return this;
    }
    @Override
    public Retrieval build() {
      return new AutoValue_Retrieval(
          this.disableAttribution,
          this.vertexAiSearch,
          this.vertexRagStore);
    }
  }

}
