package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_VertexAISearch extends VertexAISearch {

  private final Optional<String> datastore;

  private AutoValue_VertexAISearch(
      Optional<String> datastore) {
    this.datastore = datastore;
  }

  @JsonProperty("datastore")
  @Override
  public Optional<String> datastore() {
    return datastore;
  }

  @Override
  public String toString() {
    return "VertexAISearch{"
        + "datastore=" + datastore
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof VertexAISearch) {
      VertexAISearch that = (VertexAISearch) o;
      return this.datastore.equals(that.datastore());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= datastore.hashCode();
    return h$;
  }

  @Override
  public VertexAISearch.Builder toBuilder() {
    return new AutoValue_VertexAISearch.Builder(this);
  }

  static final class Builder extends VertexAISearch.Builder {
    private Optional<String> datastore = Optional.empty();
    Builder() {
    }
    Builder(VertexAISearch source) {
      this.datastore = source.datastore();
    }
    @Override
    public VertexAISearch.Builder datastore(String datastore) {
      this.datastore = Optional.of(datastore);
      return this;
    }
    @Override
    public VertexAISearch build() {
      return new AutoValue_VertexAISearch(
          this.datastore);
    }
  }

}
