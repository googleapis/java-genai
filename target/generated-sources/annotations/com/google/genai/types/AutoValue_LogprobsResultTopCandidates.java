package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_LogprobsResultTopCandidates extends LogprobsResultTopCandidates {

  private final Optional<List<LogprobsResultCandidate>> candidates;

  private AutoValue_LogprobsResultTopCandidates(
      Optional<List<LogprobsResultCandidate>> candidates) {
    this.candidates = candidates;
  }

  @JsonProperty("candidates")
  @Override
  public Optional<List<LogprobsResultCandidate>> candidates() {
    return candidates;
  }

  @Override
  public String toString() {
    return "LogprobsResultTopCandidates{"
        + "candidates=" + candidates
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof LogprobsResultTopCandidates) {
      LogprobsResultTopCandidates that = (LogprobsResultTopCandidates) o;
      return this.candidates.equals(that.candidates());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= candidates.hashCode();
    return h$;
  }

  @Override
  public LogprobsResultTopCandidates.Builder toBuilder() {
    return new AutoValue_LogprobsResultTopCandidates.Builder(this);
  }

  static final class Builder extends LogprobsResultTopCandidates.Builder {
    private Optional<List<LogprobsResultCandidate>> candidates = Optional.empty();
    Builder() {
    }
    Builder(LogprobsResultTopCandidates source) {
      this.candidates = source.candidates();
    }
    @Override
    public LogprobsResultTopCandidates.Builder candidates(List<LogprobsResultCandidate> candidates) {
      this.candidates = Optional.of(candidates);
      return this;
    }
    @Override
    public LogprobsResultTopCandidates build() {
      return new AutoValue_LogprobsResultTopCandidates(
          this.candidates);
    }
  }

}
