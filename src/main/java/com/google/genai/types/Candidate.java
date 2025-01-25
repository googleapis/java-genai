// Auto-generated code. Do not edit.

package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.List;
import java.util.Optional;

/** A response candidate generated from the model. */
@AutoValue
@JsonDeserialize(builder = AutoValue_Candidate.Builder.class)
public abstract class Candidate extends JsonSerializable {
  /** Contains the multi-part content of the response. */
  public abstract Optional<Content> getContent();

  /** Source attribution of the generated content. */
  public abstract Optional<CitationMetadata> getCitationMetadata();

  /** Describes the reason the model stopped generating tokens. */
  public abstract Optional<String> getFinishMessage();

  /** Number of tokens for this candidate. */
  public abstract Optional<Integer> getTokenCount();

  /** Output only. Average log probability score of the candidate. */
  public abstract Optional<Double> getAvgLogprobs();

  /**
   * Output only. The reason why the model stopped generating tokens. If empty, the model has not
   * stopped generating the tokens.
   */
  public abstract Optional<String> getFinishReason();

  /** Output only. Metadata specifies sources used to ground generated content. */
  public abstract Optional<GroundingMetadata> getGroundingMetadata();

  /** Output only. Index of the candidate. */
  public abstract Optional<Integer> getIndex();

  /** Output only. Log-likelihood scores for the response tokens and top tokens */
  public abstract Optional<LogprobsResult> getLogprobsResult();

  /**
   * Output only. List of ratings for the safety of a response candidate. There is at most one
   * rating per category.
   */
  public abstract Optional<List<SafetyRating>> getSafetyRatings();

  /** Instantiates a builder for Candidate. */
  public static Builder builder() {
    return new AutoValue_Candidate.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for Candidate. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("content")
    public abstract Builder setContent(Content content);

    @JsonProperty("citationMetadata")
    public abstract Builder setCitationMetadata(CitationMetadata citationMetadata);

    @JsonProperty("finishMessage")
    public abstract Builder setFinishMessage(String finishMessage);

    @JsonProperty("tokenCount")
    public abstract Builder setTokenCount(Integer tokenCount);

    @JsonProperty("avgLogprobs")
    public abstract Builder setAvgLogprobs(Double avgLogprobs);

    @JsonProperty("finishReason")
    public abstract Builder setFinishReason(String finishReason);

    @JsonProperty("groundingMetadata")
    public abstract Builder setGroundingMetadata(GroundingMetadata groundingMetadata);

    @JsonProperty("index")
    public abstract Builder setIndex(Integer index);

    @JsonProperty("logprobsResult")
    public abstract Builder setLogprobsResult(LogprobsResult logprobsResult);

    @JsonProperty("safetyRatings")
    public abstract Builder setSafetyRatings(List<SafetyRating> safetyRatings);

    public abstract Candidate build();
  }

  /** Deserializes a JSON string to a Candidate object. */
  public static Candidate fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, Candidate.class);
  }
}
