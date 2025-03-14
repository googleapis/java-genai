package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_LogprobsResult extends LogprobsResult {

  private final Optional<List<LogprobsResultCandidate>> chosenCandidates;

  private final Optional<List<LogprobsResultTopCandidates>> topCandidates;

  private AutoValue_LogprobsResult(
      Optional<List<LogprobsResultCandidate>> chosenCandidates,
      Optional<List<LogprobsResultTopCandidates>> topCandidates) {
    this.chosenCandidates = chosenCandidates;
    this.topCandidates = topCandidates;
  }

  @JsonProperty("chosenCandidates")
  @Override
  public Optional<List<LogprobsResultCandidate>> chosenCandidates() {
    return chosenCandidates;
  }

  @JsonProperty("topCandidates")
  @Override
  public Optional<List<LogprobsResultTopCandidates>> topCandidates() {
    return topCandidates;
  }

  @Override
  public String toString() {
    return "LogprobsResult{"
        + "chosenCandidates=" + chosenCandidates + ", "
        + "topCandidates=" + topCandidates
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof LogprobsResult) {
      LogprobsResult that = (LogprobsResult) o;
      return this.chosenCandidates.equals(that.chosenCandidates())
          && this.topCandidates.equals(that.topCandidates());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= chosenCandidates.hashCode();
    h$ *= 1000003;
    h$ ^= topCandidates.hashCode();
    return h$;
  }

  @Override
  public LogprobsResult.Builder toBuilder() {
    return new AutoValue_LogprobsResult.Builder(this);
  }

  static final class Builder extends LogprobsResult.Builder {
    private Optional<List<LogprobsResultCandidate>> chosenCandidates = Optional.empty();
    private Optional<List<LogprobsResultTopCandidates>> topCandidates = Optional.empty();
    Builder() {
    }
    Builder(LogprobsResult source) {
      this.chosenCandidates = source.chosenCandidates();
      this.topCandidates = source.topCandidates();
    }
    @Override
    public LogprobsResult.Builder chosenCandidates(List<LogprobsResultCandidate> chosenCandidates) {
      this.chosenCandidates = Optional.of(chosenCandidates);
      return this;
    }
    @Override
    public LogprobsResult.Builder topCandidates(List<LogprobsResultTopCandidates> topCandidates) {
      this.topCandidates = Optional.of(topCandidates);
      return this;
    }
    @Override
    public LogprobsResult build() {
      return new AutoValue_LogprobsResult(
          this.chosenCandidates,
          this.topCandidates);
    }
  }

}
