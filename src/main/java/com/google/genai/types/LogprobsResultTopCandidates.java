// Auto-generated code. Do not edit.

package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.List;
import java.util.Optional;

/** Candidates with top log probabilities at each decoding step. */
@AutoValue
@JsonDeserialize(builder = AutoValue_LogprobsResultTopCandidates.Builder.class)
public abstract class LogprobsResultTopCandidates extends JsonSerializable {
  /** Sorted by log probability in descending order. */
  public abstract Optional<List<LogprobsResultCandidate>> getCandidates();

  /** Instantiates a builder for LogprobsResultTopCandidates. */
  public static Builder builder() {
    return new AutoValue_LogprobsResultTopCandidates.Builder();
  }

  /** Builder for LogprobsResultTopCandidates. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("candidates")
    public abstract Builder setCandidates(List<LogprobsResultCandidate> candidates);

    public abstract LogprobsResultTopCandidates build();
  }

  /** Serializes the LogprobsResultTopCandidates object to a JSON string. */
  public String toJson() {
    return JsonSerializable.toJsonString(this);
  }

  /** Deserializes a JSON string to a LogprobsResultTopCandidates object. */
  public static LogprobsResultTopCandidates fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, LogprobsResultTopCandidates.class);
  }
}
