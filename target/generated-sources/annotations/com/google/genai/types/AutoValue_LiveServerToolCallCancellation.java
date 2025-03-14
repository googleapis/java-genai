package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_LiveServerToolCallCancellation extends LiveServerToolCallCancellation {

  private final Optional<List<String>> ids;

  private AutoValue_LiveServerToolCallCancellation(
      Optional<List<String>> ids) {
    this.ids = ids;
  }

  @JsonProperty("ids")
  @Override
  public Optional<List<String>> ids() {
    return ids;
  }

  @Override
  public String toString() {
    return "LiveServerToolCallCancellation{"
        + "ids=" + ids
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof LiveServerToolCallCancellation) {
      LiveServerToolCallCancellation that = (LiveServerToolCallCancellation) o;
      return this.ids.equals(that.ids());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= ids.hashCode();
    return h$;
  }

  @Override
  public LiveServerToolCallCancellation.Builder toBuilder() {
    return new AutoValue_LiveServerToolCallCancellation.Builder(this);
  }

  static final class Builder extends LiveServerToolCallCancellation.Builder {
    private Optional<List<String>> ids = Optional.empty();
    Builder() {
    }
    Builder(LiveServerToolCallCancellation source) {
      this.ids = source.ids();
    }
    @Override
    public LiveServerToolCallCancellation.Builder ids(List<String> ids) {
      this.ids = Optional.of(ids);
      return this;
    }
    @Override
    public LiveServerToolCallCancellation build() {
      return new AutoValue_LiveServerToolCallCancellation(
          this.ids);
    }
  }

}
