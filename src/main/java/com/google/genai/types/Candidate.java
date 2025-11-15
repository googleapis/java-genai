/*
 * Copyright 2025 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// Auto-generated code. Do not edit.

package com.google.genai.types;

import static com.google.common.collect.ImmutableList.toImmutableList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/** A response candidate generated from the model. */
@AutoValue
@JsonDeserialize(builder = Candidate.Builder.class)
public abstract class Candidate extends JsonSerializable {
  /** Contains the multi-part content of the response. */
  @JsonProperty("content")
  public abstract Optional<Content> content();

  /** Source attribution of the generated content. */
  @JsonProperty("citationMetadata")
  public abstract Optional<CitationMetadata> citationMetadata();

  /** Describes the reason the model stopped generating tokens. */
  @JsonProperty("finishMessage")
  public abstract Optional<String> finishMessage();

  /** Number of tokens for this candidate. */
  @JsonProperty("tokenCount")
  public abstract Optional<Integer> tokenCount();

  /**
   * The reason why the model stopped generating tokens. If empty, the model has not stopped
   * generating the tokens.
   */
  @JsonProperty("finishReason")
  public abstract Optional<FinishReason> finishReason();

  /**
   * Output only. The average log probability of the tokens in this candidate. This is a
   * length-normalized score that can be used to compare the quality of candidates of different
   * lengths. A higher average log probability suggests a more confident and coherent response.
   */
  @JsonProperty("avgLogprobs")
  public abstract Optional<Double> avgLogprobs();

  /**
   * Output only. Metadata returned when grounding is enabled. It contains the sources used to
   * ground the generated content.
   */
  @JsonProperty("groundingMetadata")
  public abstract Optional<GroundingMetadata> groundingMetadata();

  /**
   * Output only. The 0-based index of this candidate in the list of generated responses. This is
   * useful for distinguishing between multiple candidates when `candidate_count` > 1.
   */
  @JsonProperty("index")
  public abstract Optional<Integer> index();

  /**
   * Output only. The detailed log probability information for the tokens in this candidate. This is
   * useful for debugging, understanding model uncertainty, and identifying potential
   * "hallucinations".
   */
  @JsonProperty("logprobsResult")
  public abstract Optional<LogprobsResult> logprobsResult();

  /**
   * Output only. A list of ratings for the safety of a response candidate. There is at most one
   * rating per category.
   */
  @JsonProperty("safetyRatings")
  public abstract Optional<List<SafetyRating>> safetyRatings();

  /**
   * Output only. Metadata returned when the model uses the `url_context` tool to get information
   * from a user-provided URL.
   */
  @JsonProperty("urlContextMetadata")
  public abstract Optional<UrlContextMetadata> urlContextMetadata();

  /** Instantiates a builder for Candidate. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_Candidate.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for Candidate. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `Candidate.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_Candidate.Builder();
    }

    /**
     * Setter for content.
     *
     * <p>content: Contains the multi-part content of the response.
     */
    @JsonProperty("content")
    public abstract Builder content(Content content);

    /**
     * Setter for content builder.
     *
     * <p>content: Contains the multi-part content of the response.
     */
    @CanIgnoreReturnValue
    public Builder content(Content.Builder contentBuilder) {
      return content(contentBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder content(Optional<Content> content);

    /** Clears the value of content field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearContent() {
      return content(Optional.empty());
    }

    /**
     * Setter for citationMetadata.
     *
     * <p>citationMetadata: Source attribution of the generated content.
     */
    @JsonProperty("citationMetadata")
    public abstract Builder citationMetadata(CitationMetadata citationMetadata);

    /**
     * Setter for citationMetadata builder.
     *
     * <p>citationMetadata: Source attribution of the generated content.
     */
    @CanIgnoreReturnValue
    public Builder citationMetadata(CitationMetadata.Builder citationMetadataBuilder) {
      return citationMetadata(citationMetadataBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder citationMetadata(Optional<CitationMetadata> citationMetadata);

    /** Clears the value of citationMetadata field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearCitationMetadata() {
      return citationMetadata(Optional.empty());
    }

    /**
     * Setter for finishMessage.
     *
     * <p>finishMessage: Describes the reason the model stopped generating tokens.
     */
    @JsonProperty("finishMessage")
    public abstract Builder finishMessage(String finishMessage);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder finishMessage(Optional<String> finishMessage);

    /** Clears the value of finishMessage field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearFinishMessage() {
      return finishMessage(Optional.empty());
    }

    /**
     * Setter for tokenCount.
     *
     * <p>tokenCount: Number of tokens for this candidate.
     */
    @JsonProperty("tokenCount")
    public abstract Builder tokenCount(Integer tokenCount);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder tokenCount(Optional<Integer> tokenCount);

    /** Clears the value of tokenCount field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTokenCount() {
      return tokenCount(Optional.empty());
    }

    /**
     * Setter for finishReason.
     *
     * <p>finishReason: The reason why the model stopped generating tokens. If empty, the model has
     * not stopped generating the tokens.
     */
    @JsonProperty("finishReason")
    public abstract Builder finishReason(FinishReason finishReason);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder finishReason(Optional<FinishReason> finishReason);

    /** Clears the value of finishReason field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearFinishReason() {
      return finishReason(Optional.empty());
    }

    /**
     * Setter for finishReason given a known enum.
     *
     * <p>finishReason: The reason why the model stopped generating tokens. If empty, the model has
     * not stopped generating the tokens.
     */
    @CanIgnoreReturnValue
    public Builder finishReason(FinishReason.Known knownType) {
      return finishReason(new FinishReason(knownType));
    }

    /**
     * Setter for finishReason given a string.
     *
     * <p>finishReason: The reason why the model stopped generating tokens. If empty, the model has
     * not stopped generating the tokens.
     */
    @CanIgnoreReturnValue
    public Builder finishReason(String finishReason) {
      return finishReason(new FinishReason(finishReason));
    }

    /**
     * Setter for avgLogprobs.
     *
     * <p>avgLogprobs: Output only. The average log probability of the tokens in this candidate.
     * This is a length-normalized score that can be used to compare the quality of candidates of
     * different lengths. A higher average log probability suggests a more confident and coherent
     * response.
     */
    @JsonProperty("avgLogprobs")
    public abstract Builder avgLogprobs(Double avgLogprobs);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder avgLogprobs(Optional<Double> avgLogprobs);

    /** Clears the value of avgLogprobs field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearAvgLogprobs() {
      return avgLogprobs(Optional.empty());
    }

    /**
     * Setter for groundingMetadata.
     *
     * <p>groundingMetadata: Output only. Metadata returned when grounding is enabled. It contains
     * the sources used to ground the generated content.
     */
    @JsonProperty("groundingMetadata")
    public abstract Builder groundingMetadata(GroundingMetadata groundingMetadata);

    /**
     * Setter for groundingMetadata builder.
     *
     * <p>groundingMetadata: Output only. Metadata returned when grounding is enabled. It contains
     * the sources used to ground the generated content.
     */
    @CanIgnoreReturnValue
    public Builder groundingMetadata(GroundingMetadata.Builder groundingMetadataBuilder) {
      return groundingMetadata(groundingMetadataBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder groundingMetadata(Optional<GroundingMetadata> groundingMetadata);

    /** Clears the value of groundingMetadata field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearGroundingMetadata() {
      return groundingMetadata(Optional.empty());
    }

    /**
     * Setter for index.
     *
     * <p>index: Output only. The 0-based index of this candidate in the list of generated
     * responses. This is useful for distinguishing between multiple candidates when
     * `candidate_count` > 1.
     */
    @JsonProperty("index")
    public abstract Builder index(Integer index);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder index(Optional<Integer> index);

    /** Clears the value of index field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearIndex() {
      return index(Optional.empty());
    }

    /**
     * Setter for logprobsResult.
     *
     * <p>logprobsResult: Output only. The detailed log probability information for the tokens in
     * this candidate. This is useful for debugging, understanding model uncertainty, and
     * identifying potential "hallucinations".
     */
    @JsonProperty("logprobsResult")
    public abstract Builder logprobsResult(LogprobsResult logprobsResult);

    /**
     * Setter for logprobsResult builder.
     *
     * <p>logprobsResult: Output only. The detailed log probability information for the tokens in
     * this candidate. This is useful for debugging, understanding model uncertainty, and
     * identifying potential "hallucinations".
     */
    @CanIgnoreReturnValue
    public Builder logprobsResult(LogprobsResult.Builder logprobsResultBuilder) {
      return logprobsResult(logprobsResultBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder logprobsResult(Optional<LogprobsResult> logprobsResult);

    /** Clears the value of logprobsResult field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearLogprobsResult() {
      return logprobsResult(Optional.empty());
    }

    /**
     * Setter for safetyRatings.
     *
     * <p>safetyRatings: Output only. A list of ratings for the safety of a response candidate.
     * There is at most one rating per category.
     */
    @JsonProperty("safetyRatings")
    public abstract Builder safetyRatings(List<SafetyRating> safetyRatings);

    /**
     * Setter for safetyRatings.
     *
     * <p>safetyRatings: Output only. A list of ratings for the safety of a response candidate.
     * There is at most one rating per category.
     */
    @CanIgnoreReturnValue
    public Builder safetyRatings(SafetyRating... safetyRatings) {
      return safetyRatings(Arrays.asList(safetyRatings));
    }

    /**
     * Setter for safetyRatings builder.
     *
     * <p>safetyRatings: Output only. A list of ratings for the safety of a response candidate.
     * There is at most one rating per category.
     */
    @CanIgnoreReturnValue
    public Builder safetyRatings(SafetyRating.Builder... safetyRatingsBuilders) {
      return safetyRatings(
          Arrays.asList(safetyRatingsBuilders).stream()
              .map(SafetyRating.Builder::build)
              .collect(toImmutableList()));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder safetyRatings(Optional<List<SafetyRating>> safetyRatings);

    /** Clears the value of safetyRatings field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearSafetyRatings() {
      return safetyRatings(Optional.empty());
    }

    /**
     * Setter for urlContextMetadata.
     *
     * <p>urlContextMetadata: Output only. Metadata returned when the model uses the `url_context`
     * tool to get information from a user-provided URL.
     */
    @JsonProperty("urlContextMetadata")
    public abstract Builder urlContextMetadata(UrlContextMetadata urlContextMetadata);

    /**
     * Setter for urlContextMetadata builder.
     *
     * <p>urlContextMetadata: Output only. Metadata returned when the model uses the `url_context`
     * tool to get information from a user-provided URL.
     */
    @CanIgnoreReturnValue
    public Builder urlContextMetadata(UrlContextMetadata.Builder urlContextMetadataBuilder) {
      return urlContextMetadata(urlContextMetadataBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder urlContextMetadata(Optional<UrlContextMetadata> urlContextMetadata);

    /** Clears the value of urlContextMetadata field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearUrlContextMetadata() {
      return urlContextMetadata(Optional.empty());
    }

    public abstract Candidate build();
  }

  /** Deserializes a JSON string to a Candidate object. */
  @ExcludeFromGeneratedCoverageReport
  public static Candidate fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, Candidate.class);
  }
}
