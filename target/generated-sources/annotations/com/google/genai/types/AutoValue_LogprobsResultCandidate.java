package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_LogprobsResultCandidate extends LogprobsResultCandidate {

  private final Optional<Float> logProbability;

  private final Optional<String> token;

  private final Optional<Integer> tokenId;

  private AutoValue_LogprobsResultCandidate(
      Optional<Float> logProbability,
      Optional<String> token,
      Optional<Integer> tokenId) {
    this.logProbability = logProbability;
    this.token = token;
    this.tokenId = tokenId;
  }

  @JsonProperty("logProbability")
  @Override
  public Optional<Float> logProbability() {
    return logProbability;
  }

  @JsonProperty("token")
  @Override
  public Optional<String> token() {
    return token;
  }

  @JsonProperty("tokenId")
  @Override
  public Optional<Integer> tokenId() {
    return tokenId;
  }

  @Override
  public String toString() {
    return "LogprobsResultCandidate{"
        + "logProbability=" + logProbability + ", "
        + "token=" + token + ", "
        + "tokenId=" + tokenId
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof LogprobsResultCandidate) {
      LogprobsResultCandidate that = (LogprobsResultCandidate) o;
      return this.logProbability.equals(that.logProbability())
          && this.token.equals(that.token())
          && this.tokenId.equals(that.tokenId());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= logProbability.hashCode();
    h$ *= 1000003;
    h$ ^= token.hashCode();
    h$ *= 1000003;
    h$ ^= tokenId.hashCode();
    return h$;
  }

  @Override
  public LogprobsResultCandidate.Builder toBuilder() {
    return new AutoValue_LogprobsResultCandidate.Builder(this);
  }

  static final class Builder extends LogprobsResultCandidate.Builder {
    private Optional<Float> logProbability = Optional.empty();
    private Optional<String> token = Optional.empty();
    private Optional<Integer> tokenId = Optional.empty();
    Builder() {
    }
    Builder(LogprobsResultCandidate source) {
      this.logProbability = source.logProbability();
      this.token = source.token();
      this.tokenId = source.tokenId();
    }
    @Override
    public LogprobsResultCandidate.Builder logProbability(Float logProbability) {
      this.logProbability = Optional.of(logProbability);
      return this;
    }
    @Override
    public LogprobsResultCandidate.Builder token(String token) {
      this.token = Optional.of(token);
      return this;
    }
    @Override
    public LogprobsResultCandidate.Builder tokenId(Integer tokenId) {
      this.tokenId = Optional.of(tokenId);
      return this;
    }
    @Override
    public LogprobsResultCandidate build() {
      return new AutoValue_LogprobsResultCandidate(
          this.logProbability,
          this.token,
          this.tokenId);
    }
  }

}
