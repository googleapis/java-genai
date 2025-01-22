// Auto-generated code. Do not edit.

package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.List;
import java.util.Optional;

/** Logprobs Result */
@AutoValue
@JsonDeserialize(builder = AutoValue_LogprobsResult.Builder.class)
public abstract class LogprobsResult extends JsonSerializable {
  /**
   * Length = total number of decoding steps. The chosen candidates may or may not be in
   * top_candidates.
   */
  public abstract Optional<List<LogprobsResultCandidate>> getChosenCandidates();

  /** Length = total number of decoding steps. */
  public abstract Optional<List<LogprobsResultTopCandidates>> getTopCandidates();

  /** Instantiates a builder for LogprobsResult. */
  public static Builder builder() {
    return new AutoValue_LogprobsResult.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for LogprobsResult. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("chosenCandidates")
    public abstract Builder setChosenCandidates(List<LogprobsResultCandidate> chosenCandidates);

    @JsonProperty("topCandidates")
    public abstract Builder setTopCandidates(List<LogprobsResultTopCandidates> topCandidates);

    public abstract LogprobsResult build();
  }

  /** Deserializes a JSON string to a LogprobsResult object. */
  public static LogprobsResult fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, LogprobsResult.class);
  }
}
