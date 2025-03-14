package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_LiveServerContent extends LiveServerContent {

  private final Optional<Content> modelTurn;

  private final Optional<Boolean> turnComplete;

  private final Optional<Boolean> interrupted;

  private AutoValue_LiveServerContent(
      Optional<Content> modelTurn,
      Optional<Boolean> turnComplete,
      Optional<Boolean> interrupted) {
    this.modelTurn = modelTurn;
    this.turnComplete = turnComplete;
    this.interrupted = interrupted;
  }

  @JsonProperty("modelTurn")
  @Override
  public Optional<Content> modelTurn() {
    return modelTurn;
  }

  @JsonProperty("turnComplete")
  @Override
  public Optional<Boolean> turnComplete() {
    return turnComplete;
  }

  @JsonProperty("interrupted")
  @Override
  public Optional<Boolean> interrupted() {
    return interrupted;
  }

  @Override
  public String toString() {
    return "LiveServerContent{"
        + "modelTurn=" + modelTurn + ", "
        + "turnComplete=" + turnComplete + ", "
        + "interrupted=" + interrupted
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof LiveServerContent) {
      LiveServerContent that = (LiveServerContent) o;
      return this.modelTurn.equals(that.modelTurn())
          && this.turnComplete.equals(that.turnComplete())
          && this.interrupted.equals(that.interrupted());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= modelTurn.hashCode();
    h$ *= 1000003;
    h$ ^= turnComplete.hashCode();
    h$ *= 1000003;
    h$ ^= interrupted.hashCode();
    return h$;
  }

  @Override
  public LiveServerContent.Builder toBuilder() {
    return new AutoValue_LiveServerContent.Builder(this);
  }

  static final class Builder extends LiveServerContent.Builder {
    private Optional<Content> modelTurn = Optional.empty();
    private Optional<Boolean> turnComplete = Optional.empty();
    private Optional<Boolean> interrupted = Optional.empty();
    Builder() {
    }
    Builder(LiveServerContent source) {
      this.modelTurn = source.modelTurn();
      this.turnComplete = source.turnComplete();
      this.interrupted = source.interrupted();
    }
    @Override
    public LiveServerContent.Builder modelTurn(Content modelTurn) {
      this.modelTurn = Optional.of(modelTurn);
      return this;
    }
    @Override
    public LiveServerContent.Builder turnComplete(boolean turnComplete) {
      this.turnComplete = Optional.of(turnComplete);
      return this;
    }
    @Override
    public LiveServerContent.Builder interrupted(boolean interrupted) {
      this.interrupted = Optional.of(interrupted);
      return this;
    }
    @Override
    public LiveServerContent build() {
      return new AutoValue_LiveServerContent(
          this.modelTurn,
          this.turnComplete,
          this.interrupted);
    }
  }

}
