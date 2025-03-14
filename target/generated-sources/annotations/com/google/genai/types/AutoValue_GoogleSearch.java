package com.google.genai.types;

import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_GoogleSearch extends GoogleSearch {

  private AutoValue_GoogleSearch() {
  }

  @Override
  public String toString() {
    return "GoogleSearch{"
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof GoogleSearch) {
      return true;
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    return h$;
  }

  @Override
  public GoogleSearch.Builder toBuilder() {
    return new AutoValue_GoogleSearch.Builder(this);
  }

  static final class Builder extends GoogleSearch.Builder {
    Builder() {
    }
    Builder(GoogleSearch source) {
    }
    @Override
    public GoogleSearch build() {
      return new AutoValue_GoogleSearch();
    }
  }

}
