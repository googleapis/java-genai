package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_LiveClientToolResponse extends LiveClientToolResponse {

  private final Optional<List<FunctionResponse>> functionResponses;

  private AutoValue_LiveClientToolResponse(
      Optional<List<FunctionResponse>> functionResponses) {
    this.functionResponses = functionResponses;
  }

  @JsonProperty("functionResponses")
  @Override
  public Optional<List<FunctionResponse>> functionResponses() {
    return functionResponses;
  }

  @Override
  public String toString() {
    return "LiveClientToolResponse{"
        + "functionResponses=" + functionResponses
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof LiveClientToolResponse) {
      LiveClientToolResponse that = (LiveClientToolResponse) o;
      return this.functionResponses.equals(that.functionResponses());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= functionResponses.hashCode();
    return h$;
  }

  @Override
  public LiveClientToolResponse.Builder toBuilder() {
    return new AutoValue_LiveClientToolResponse.Builder(this);
  }

  static final class Builder extends LiveClientToolResponse.Builder {
    private Optional<List<FunctionResponse>> functionResponses = Optional.empty();
    Builder() {
    }
    Builder(LiveClientToolResponse source) {
      this.functionResponses = source.functionResponses();
    }
    @Override
    public LiveClientToolResponse.Builder functionResponses(List<FunctionResponse> functionResponses) {
      this.functionResponses = Optional.of(functionResponses);
      return this;
    }
    @Override
    public LiveClientToolResponse build() {
      return new AutoValue_LiveClientToolResponse(
          this.functionResponses);
    }
  }

}
