// Auto-generated code. Do not edit.

package com.google.genai.datatypes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** Candidate for the logprobs token and score. */
@AutoValue
@JsonDeserialize(builder = AutoValue_LogprobsResultCandidate.Builder.class)
public abstract class LogprobsResultCandidate extends JsonSerializable {
  /** The candidate's log probability. */
  public abstract Optional<Float> getLogProbability();

  /** The candidate's token string value. */
  public abstract Optional<String> getToken();

  /** The candidate's token id value. */
  public abstract Optional<Integer> getTokenId();

  /** Instantiates a builder for LogprobsResultCandidate. */
  public static Builder builder() {
    return new AutoValue_LogprobsResultCandidate.Builder();
  }

  /** Builder for LogprobsResultCandidate. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("logProbability")
    public abstract Builder setLogProbability(Float logProbability);

    @JsonProperty("token")
    public abstract Builder setToken(String token);

    @JsonProperty("tokenId")
    public abstract Builder setTokenId(Integer tokenId);

    public abstract LogprobsResultCandidate build();
  }

  /** Serializes the LogprobsResultCandidate object to a JSON string. */
  public String toJson() {
    return JsonSerializable.toJsonString(this);
  }

  /** Deserializes a JSON string to a LogprobsResultCandidate object. */
  public static LogprobsResultCandidate fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, LogprobsResultCandidate.class);
  }
}
