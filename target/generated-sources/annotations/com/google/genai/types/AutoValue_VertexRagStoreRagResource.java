package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_VertexRagStoreRagResource extends VertexRagStoreRagResource {

  private final Optional<String> ragCorpus;

  private final Optional<List<String>> ragFileIds;

  private AutoValue_VertexRagStoreRagResource(
      Optional<String> ragCorpus,
      Optional<List<String>> ragFileIds) {
    this.ragCorpus = ragCorpus;
    this.ragFileIds = ragFileIds;
  }

  @JsonProperty("ragCorpus")
  @Override
  public Optional<String> ragCorpus() {
    return ragCorpus;
  }

  @JsonProperty("ragFileIds")
  @Override
  public Optional<List<String>> ragFileIds() {
    return ragFileIds;
  }

  @Override
  public String toString() {
    return "VertexRagStoreRagResource{"
        + "ragCorpus=" + ragCorpus + ", "
        + "ragFileIds=" + ragFileIds
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof VertexRagStoreRagResource) {
      VertexRagStoreRagResource that = (VertexRagStoreRagResource) o;
      return this.ragCorpus.equals(that.ragCorpus())
          && this.ragFileIds.equals(that.ragFileIds());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= ragCorpus.hashCode();
    h$ *= 1000003;
    h$ ^= ragFileIds.hashCode();
    return h$;
  }

  @Override
  public VertexRagStoreRagResource.Builder toBuilder() {
    return new AutoValue_VertexRagStoreRagResource.Builder(this);
  }

  static final class Builder extends VertexRagStoreRagResource.Builder {
    private Optional<String> ragCorpus = Optional.empty();
    private Optional<List<String>> ragFileIds = Optional.empty();
    Builder() {
    }
    Builder(VertexRagStoreRagResource source) {
      this.ragCorpus = source.ragCorpus();
      this.ragFileIds = source.ragFileIds();
    }
    @Override
    public VertexRagStoreRagResource.Builder ragCorpus(String ragCorpus) {
      this.ragCorpus = Optional.of(ragCorpus);
      return this;
    }
    @Override
    public VertexRagStoreRagResource.Builder ragFileIds(List<String> ragFileIds) {
      this.ragFileIds = Optional.of(ragFileIds);
      return this;
    }
    @Override
    public VertexRagStoreRagResource build() {
      return new AutoValue_VertexRagStoreRagResource(
          this.ragCorpus,
          this.ragFileIds);
    }
  }

}
