package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_LiveClientContent extends LiveClientContent {

  private final Optional<List<Content>> turns;

  private final Optional<Boolean> turnComplete;

  private AutoValue_LiveClientContent(
      Optional<List<Content>> turns,
      Optional<Boolean> turnComplete) {
    this.turns = turns;
    this.turnComplete = turnComplete;
  }

  @JsonProperty("turns")
  @Override
  public Optional<List<Content>> turns() {
    return turns;
  }

  @JsonProperty("turnComplete")
  @Override
  public Optional<Boolean> turnComplete() {
    return turnComplete;
  }

  @Override
  public String toString() {
    return "LiveClientContent{"
        + "turns=" + turns + ", "
        + "turnComplete=" + turnComplete
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof LiveClientContent) {
      LiveClientContent that = (LiveClientContent) o;
      return this.turns.equals(that.turns())
          && this.turnComplete.equals(that.turnComplete());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= turns.hashCode();
    h$ *= 1000003;
    h$ ^= turnComplete.hashCode();
    return h$;
  }

  @Override
  public LiveClientContent.Builder toBuilder() {
    return new AutoValue_LiveClientContent.Builder(this);
  }

  static final class Builder extends LiveClientContent.Builder {
    private Optional<List<Content>> turns = Optional.empty();
    private Optional<Boolean> turnComplete = Optional.empty();
    Builder() {
    }
    Builder(LiveClientContent source) {
      this.turns = source.turns();
      this.turnComplete = source.turnComplete();
    }
    @Override
    public LiveClientContent.Builder turns(List<Content> turns) {
      this.turns = Optional.of(turns);
      return this;
    }
    @Override
    public LiveClientContent.Builder turnComplete(boolean turnComplete) {
      this.turnComplete = Optional.of(turnComplete);
      return this;
    }
    @Override
    public LiveClientContent build() {
      return new AutoValue_LiveClientContent(
          this.turns,
          this.turnComplete);
    }
  }

}
