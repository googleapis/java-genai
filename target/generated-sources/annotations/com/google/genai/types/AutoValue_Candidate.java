package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_Candidate extends Candidate {

  private final Optional<Content> content;

  private final Optional<CitationMetadata> citationMetadata;

  private final Optional<String> finishMessage;

  private final Optional<Integer> tokenCount;

  private final Optional<Double> avgLogprobs;

  private final Optional<String> finishReason;

  private final Optional<GroundingMetadata> groundingMetadata;

  private final Optional<Integer> index;

  private final Optional<LogprobsResult> logprobsResult;

  private final Optional<List<SafetyRating>> safetyRatings;

  private AutoValue_Candidate(
      Optional<Content> content,
      Optional<CitationMetadata> citationMetadata,
      Optional<String> finishMessage,
      Optional<Integer> tokenCount,
      Optional<Double> avgLogprobs,
      Optional<String> finishReason,
      Optional<GroundingMetadata> groundingMetadata,
      Optional<Integer> index,
      Optional<LogprobsResult> logprobsResult,
      Optional<List<SafetyRating>> safetyRatings) {
    this.content = content;
    this.citationMetadata = citationMetadata;
    this.finishMessage = finishMessage;
    this.tokenCount = tokenCount;
    this.avgLogprobs = avgLogprobs;
    this.finishReason = finishReason;
    this.groundingMetadata = groundingMetadata;
    this.index = index;
    this.logprobsResult = logprobsResult;
    this.safetyRatings = safetyRatings;
  }

  @JsonProperty("content")
  @Override
  public Optional<Content> content() {
    return content;
  }

  @JsonProperty("citationMetadata")
  @Override
  public Optional<CitationMetadata> citationMetadata() {
    return citationMetadata;
  }

  @JsonProperty("finishMessage")
  @Override
  public Optional<String> finishMessage() {
    return finishMessage;
  }

  @JsonProperty("tokenCount")
  @Override
  public Optional<Integer> tokenCount() {
    return tokenCount;
  }

  @JsonProperty("avgLogprobs")
  @Override
  public Optional<Double> avgLogprobs() {
    return avgLogprobs;
  }

  @JsonProperty("finishReason")
  @Override
  public Optional<String> finishReason() {
    return finishReason;
  }

  @JsonProperty("groundingMetadata")
  @Override
  public Optional<GroundingMetadata> groundingMetadata() {
    return groundingMetadata;
  }

  @JsonProperty("index")
  @Override
  public Optional<Integer> index() {
    return index;
  }

  @JsonProperty("logprobsResult")
  @Override
  public Optional<LogprobsResult> logprobsResult() {
    return logprobsResult;
  }

  @JsonProperty("safetyRatings")
  @Override
  public Optional<List<SafetyRating>> safetyRatings() {
    return safetyRatings;
  }

  @Override
  public String toString() {
    return "Candidate{"
        + "content=" + content + ", "
        + "citationMetadata=" + citationMetadata + ", "
        + "finishMessage=" + finishMessage + ", "
        + "tokenCount=" + tokenCount + ", "
        + "avgLogprobs=" + avgLogprobs + ", "
        + "finishReason=" + finishReason + ", "
        + "groundingMetadata=" + groundingMetadata + ", "
        + "index=" + index + ", "
        + "logprobsResult=" + logprobsResult + ", "
        + "safetyRatings=" + safetyRatings
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof Candidate) {
      Candidate that = (Candidate) o;
      return this.content.equals(that.content())
          && this.citationMetadata.equals(that.citationMetadata())
          && this.finishMessage.equals(that.finishMessage())
          && this.tokenCount.equals(that.tokenCount())
          && this.avgLogprobs.equals(that.avgLogprobs())
          && this.finishReason.equals(that.finishReason())
          && this.groundingMetadata.equals(that.groundingMetadata())
          && this.index.equals(that.index())
          && this.logprobsResult.equals(that.logprobsResult())
          && this.safetyRatings.equals(that.safetyRatings());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= content.hashCode();
    h$ *= 1000003;
    h$ ^= citationMetadata.hashCode();
    h$ *= 1000003;
    h$ ^= finishMessage.hashCode();
    h$ *= 1000003;
    h$ ^= tokenCount.hashCode();
    h$ *= 1000003;
    h$ ^= avgLogprobs.hashCode();
    h$ *= 1000003;
    h$ ^= finishReason.hashCode();
    h$ *= 1000003;
    h$ ^= groundingMetadata.hashCode();
    h$ *= 1000003;
    h$ ^= index.hashCode();
    h$ *= 1000003;
    h$ ^= logprobsResult.hashCode();
    h$ *= 1000003;
    h$ ^= safetyRatings.hashCode();
    return h$;
  }

  @Override
  public Candidate.Builder toBuilder() {
    return new AutoValue_Candidate.Builder(this);
  }

  static final class Builder extends Candidate.Builder {
    private Optional<Content> content = Optional.empty();
    private Optional<CitationMetadata> citationMetadata = Optional.empty();
    private Optional<String> finishMessage = Optional.empty();
    private Optional<Integer> tokenCount = Optional.empty();
    private Optional<Double> avgLogprobs = Optional.empty();
    private Optional<String> finishReason = Optional.empty();
    private Optional<GroundingMetadata> groundingMetadata = Optional.empty();
    private Optional<Integer> index = Optional.empty();
    private Optional<LogprobsResult> logprobsResult = Optional.empty();
    private Optional<List<SafetyRating>> safetyRatings = Optional.empty();
    Builder() {
    }
    Builder(Candidate source) {
      this.content = source.content();
      this.citationMetadata = source.citationMetadata();
      this.finishMessage = source.finishMessage();
      this.tokenCount = source.tokenCount();
      this.avgLogprobs = source.avgLogprobs();
      this.finishReason = source.finishReason();
      this.groundingMetadata = source.groundingMetadata();
      this.index = source.index();
      this.logprobsResult = source.logprobsResult();
      this.safetyRatings = source.safetyRatings();
    }
    @Override
    public Candidate.Builder content(Content content) {
      this.content = Optional.of(content);
      return this;
    }
    @Override
    public Candidate.Builder citationMetadata(CitationMetadata citationMetadata) {
      this.citationMetadata = Optional.of(citationMetadata);
      return this;
    }
    @Override
    public Candidate.Builder finishMessage(String finishMessage) {
      this.finishMessage = Optional.of(finishMessage);
      return this;
    }
    @Override
    public Candidate.Builder tokenCount(Integer tokenCount) {
      this.tokenCount = Optional.of(tokenCount);
      return this;
    }
    @Override
    public Candidate.Builder avgLogprobs(Double avgLogprobs) {
      this.avgLogprobs = Optional.of(avgLogprobs);
      return this;
    }
    @Override
    public Candidate.Builder finishReason(String finishReason) {
      this.finishReason = Optional.of(finishReason);
      return this;
    }
    @Override
    public Candidate.Builder groundingMetadata(GroundingMetadata groundingMetadata) {
      this.groundingMetadata = Optional.of(groundingMetadata);
      return this;
    }
    @Override
    public Candidate.Builder index(Integer index) {
      this.index = Optional.of(index);
      return this;
    }
    @Override
    public Candidate.Builder logprobsResult(LogprobsResult logprobsResult) {
      this.logprobsResult = Optional.of(logprobsResult);
      return this;
    }
    @Override
    public Candidate.Builder safetyRatings(List<SafetyRating> safetyRatings) {
      this.safetyRatings = Optional.of(safetyRatings);
      return this;
    }
    @Override
    public Candidate build() {
      return new AutoValue_Candidate(
          this.content,
          this.citationMetadata,
          this.finishMessage,
          this.tokenCount,
          this.avgLogprobs,
          this.finishReason,
          this.groundingMetadata,
          this.index,
          this.logprobsResult,
          this.safetyRatings);
    }
  }

}
