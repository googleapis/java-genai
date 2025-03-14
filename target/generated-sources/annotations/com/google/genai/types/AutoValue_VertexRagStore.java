package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_VertexRagStore extends VertexRagStore {

  private final Optional<List<String>> ragCorpora;

  private final Optional<List<VertexRagStoreRagResource>> ragResources;

  private final Optional<Integer> similarityTopK;

  private final Optional<Double> vectorDistanceThreshold;

  private AutoValue_VertexRagStore(
      Optional<List<String>> ragCorpora,
      Optional<List<VertexRagStoreRagResource>> ragResources,
      Optional<Integer> similarityTopK,
      Optional<Double> vectorDistanceThreshold) {
    this.ragCorpora = ragCorpora;
    this.ragResources = ragResources;
    this.similarityTopK = similarityTopK;
    this.vectorDistanceThreshold = vectorDistanceThreshold;
  }

  @JsonProperty("ragCorpora")
  @Override
  public Optional<List<String>> ragCorpora() {
    return ragCorpora;
  }

  @JsonProperty("ragResources")
  @Override
  public Optional<List<VertexRagStoreRagResource>> ragResources() {
    return ragResources;
  }

  @JsonProperty("similarityTopK")
  @Override
  public Optional<Integer> similarityTopK() {
    return similarityTopK;
  }

  @JsonProperty("vectorDistanceThreshold")
  @Override
  public Optional<Double> vectorDistanceThreshold() {
    return vectorDistanceThreshold;
  }

  @Override
  public String toString() {
    return "VertexRagStore{"
        + "ragCorpora=" + ragCorpora + ", "
        + "ragResources=" + ragResources + ", "
        + "similarityTopK=" + similarityTopK + ", "
        + "vectorDistanceThreshold=" + vectorDistanceThreshold
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof VertexRagStore) {
      VertexRagStore that = (VertexRagStore) o;
      return this.ragCorpora.equals(that.ragCorpora())
          && this.ragResources.equals(that.ragResources())
          && this.similarityTopK.equals(that.similarityTopK())
          && this.vectorDistanceThreshold.equals(that.vectorDistanceThreshold());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= ragCorpora.hashCode();
    h$ *= 1000003;
    h$ ^= ragResources.hashCode();
    h$ *= 1000003;
    h$ ^= similarityTopK.hashCode();
    h$ *= 1000003;
    h$ ^= vectorDistanceThreshold.hashCode();
    return h$;
  }

  @Override
  public VertexRagStore.Builder toBuilder() {
    return new AutoValue_VertexRagStore.Builder(this);
  }

  static final class Builder extends VertexRagStore.Builder {
    private Optional<List<String>> ragCorpora = Optional.empty();
    private Optional<List<VertexRagStoreRagResource>> ragResources = Optional.empty();
    private Optional<Integer> similarityTopK = Optional.empty();
    private Optional<Double> vectorDistanceThreshold = Optional.empty();
    Builder() {
    }
    Builder(VertexRagStore source) {
      this.ragCorpora = source.ragCorpora();
      this.ragResources = source.ragResources();
      this.similarityTopK = source.similarityTopK();
      this.vectorDistanceThreshold = source.vectorDistanceThreshold();
    }
    @Override
    public VertexRagStore.Builder ragCorpora(List<String> ragCorpora) {
      this.ragCorpora = Optional.of(ragCorpora);
      return this;
    }
    @Override
    public VertexRagStore.Builder ragResources(List<VertexRagStoreRagResource> ragResources) {
      this.ragResources = Optional.of(ragResources);
      return this;
    }
    @Override
    public VertexRagStore.Builder similarityTopK(Integer similarityTopK) {
      this.similarityTopK = Optional.of(similarityTopK);
      return this;
    }
    @Override
    public VertexRagStore.Builder vectorDistanceThreshold(Double vectorDistanceThreshold) {
      this.vectorDistanceThreshold = Optional.of(vectorDistanceThreshold);
      return this;
    }
    @Override
    public VertexRagStore build() {
      return new AutoValue_VertexRagStore(
          this.ragCorpora,
          this.ragResources,
          this.similarityTopK,
          this.vectorDistanceThreshold);
    }
  }

}
