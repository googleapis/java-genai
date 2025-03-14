package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_LiveServerToolCall extends LiveServerToolCall {

  private final Optional<List<FunctionCall>> functionCalls;

  private AutoValue_LiveServerToolCall(
      Optional<List<FunctionCall>> functionCalls) {
    this.functionCalls = functionCalls;
  }

  @JsonProperty("functionCalls")
  @Override
  public Optional<List<FunctionCall>> functionCalls() {
    return functionCalls;
  }

  @Override
  public String toString() {
    return "LiveServerToolCall{"
        + "functionCalls=" + functionCalls
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof LiveServerToolCall) {
      LiveServerToolCall that = (LiveServerToolCall) o;
      return this.functionCalls.equals(that.functionCalls());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= functionCalls.hashCode();
    return h$;
  }

  @Override
  public LiveServerToolCall.Builder toBuilder() {
    return new AutoValue_LiveServerToolCall.Builder(this);
  }

  static final class Builder extends LiveServerToolCall.Builder {
    private Optional<List<FunctionCall>> functionCalls = Optional.empty();
    Builder() {
    }
    Builder(LiveServerToolCall source) {
      this.functionCalls = source.functionCalls();
    }
    @Override
    public LiveServerToolCall.Builder functionCalls(List<FunctionCall> functionCalls) {
      this.functionCalls = Optional.of(functionCalls);
      return this;
    }
    @Override
    public LiveServerToolCall build() {
      return new AutoValue_LiveServerToolCall(
          this.functionCalls);
    }
  }

}
