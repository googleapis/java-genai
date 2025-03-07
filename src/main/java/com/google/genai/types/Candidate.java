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

import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import com.google.gson.annotations.SerializedName;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;
import java.util.List;
import java.util.Optional;

/** A response candidate generated from the model. */
@GenerateTypeAdapter
@AutoValue
public abstract class Candidate extends JsonSerializable {
  /** Contains the multi-part content of the response. */
  @SerializedName("content")
  public abstract Optional<Content> content();

  /** Source attribution of the generated content. */
  @SerializedName("citationMetadata")
  public abstract Optional<CitationMetadata> citationMetadata();

  /** Describes the reason the model stopped generating tokens. */
  @SerializedName("finishMessage")
  public abstract Optional<String> finishMessage();

  /** Number of tokens for this candidate. */
  @SerializedName("tokenCount")
  public abstract Optional<Integer> tokenCount();

  /** Output only. Average log probability score of the candidate. */
  @SerializedName("avgLogprobs")
  public abstract Optional<Double> avgLogprobs();

  /**
   * Output only. The reason why the model stopped generating tokens. If empty, the model has not
   * stopped generating the tokens.
   */
  @SerializedName("finishReason")
  public abstract Optional<String> finishReason();

  /** Output only. Metadata specifies sources used to ground generated content. */
  @SerializedName("groundingMetadata")
  public abstract Optional<GroundingMetadata> groundingMetadata();

  /** Output only. Index of the candidate. */
  @SerializedName("index")
  public abstract Optional<Integer> index();

  /** Output only. Log-likelihood scores for the response tokens and top tokens */
  @SerializedName("logprobsResult")
  public abstract Optional<LogprobsResult> logprobsResult();

  /**
   * Output only. List of ratings for the safety of a response candidate. There is at most one
   * rating per category.
   */
  @SerializedName("safetyRatings")
  public abstract Optional<List<SafetyRating>> safetyRatings();

  /** Instantiates a builder for Candidate. */
  public static Builder builder() {
    return new AutoValue_Candidate.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for Candidate. */
  @AutoValue.Builder
  public abstract static class Builder {
    @SerializedName("content")
    public abstract Builder content(Content content);

    @SerializedName("content")
    abstract Builder content(Optional<Content> content);

    @SerializedName("citationMetadata")
    public abstract Builder citationMetadata(CitationMetadata citationMetadata);

    @SerializedName("citationMetadata")
    abstract Builder citationMetadata(Optional<CitationMetadata> citationMetadata);

    @SerializedName("finishMessage")
    public abstract Builder finishMessage(String finishMessage);

    @SerializedName("finishMessage")
    abstract Builder finishMessage(Optional<String> finishMessage);

    @SerializedName("tokenCount")
    public abstract Builder tokenCount(Integer tokenCount);

    @SerializedName("tokenCount")
    abstract Builder tokenCount(Optional<Integer> tokenCount);

    @SerializedName("avgLogprobs")
    public abstract Builder avgLogprobs(Double avgLogprobs);

    @SerializedName("avgLogprobs")
    abstract Builder avgLogprobs(Optional<Double> avgLogprobs);

    @SerializedName("finishReason")
    public abstract Builder finishReason(String finishReason);

    @SerializedName("finishReason")
    abstract Builder finishReason(Optional<String> finishReason);

    @SerializedName("groundingMetadata")
    public abstract Builder groundingMetadata(GroundingMetadata groundingMetadata);

    @SerializedName("groundingMetadata")
    abstract Builder groundingMetadata(Optional<GroundingMetadata> groundingMetadata);

    @SerializedName("index")
    public abstract Builder index(Integer index);

    @SerializedName("index")
    abstract Builder index(Optional<Integer> index);

    @SerializedName("logprobsResult")
    public abstract Builder logprobsResult(LogprobsResult logprobsResult);

    @SerializedName("logprobsResult")
    abstract Builder logprobsResult(Optional<LogprobsResult> logprobsResult);

    @SerializedName("safetyRatings")
    public abstract Builder safetyRatings(List<SafetyRating> safetyRatings);

    @SerializedName("safetyRatings")
    abstract Builder safetyRatings(Optional<List<SafetyRating>> safetyRatings);

    public abstract Candidate build();
  }

  /** Deserializes a JSON string to a Candidate object. */
  public static Candidate fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, Candidate.class);
  }
}
