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

/**
 * Usage metadata about the content generation request and response. This message provides a
 * detailed breakdown of token usage and other relevant metrics. This data type is not supported in
 * Gemini API.
 */
@AutoValue
@JsonDeserialize(builder = GenerateContentResponseUsageMetadata.Builder.class)
public abstract class GenerateContentResponseUsageMetadata extends JsonSerializable {
  /**
   * Output only. A detailed breakdown of the token count for each modality in the cached content.
   */
  @JsonProperty("cacheTokensDetails")
  public abstract Optional<List<ModalityTokenCount>> cacheTokensDetails();

  /** Output only. The number of tokens in the cached content that was used for this request. */
  @JsonProperty("cachedContentTokenCount")
  public abstract Optional<Integer> cachedContentTokenCount();

  /** The total number of tokens in the generated candidates. */
  @JsonProperty("candidatesTokenCount")
  public abstract Optional<Integer> candidatesTokenCount();

  /**
   * Output only. A detailed breakdown of the token count for each modality in the generated
   * candidates.
   */
  @JsonProperty("candidatesTokensDetails")
  public abstract Optional<List<ModalityTokenCount>> candidatesTokensDetails();

  /**
   * The total number of tokens in the prompt. This includes any text, images, or other media
   * provided in the request. When `cached_content` is set, this also includes the number of tokens
   * in the cached content.
   */
  @JsonProperty("promptTokenCount")
  public abstract Optional<Integer> promptTokenCount();

  /** Output only. A detailed breakdown of the token count for each modality in the prompt. */
  @JsonProperty("promptTokensDetails")
  public abstract Optional<List<ModalityTokenCount>> promptTokensDetails();

  /**
   * Output only. The number of tokens that were part of the model's generated "thoughts" output, if
   * applicable.
   */
  @JsonProperty("thoughtsTokenCount")
  public abstract Optional<Integer> thoughtsTokenCount();

  /**
   * Output only. The number of tokens in the results from tool executions, which are provided back
   * to the model as input, if applicable.
   */
  @JsonProperty("toolUsePromptTokenCount")
  public abstract Optional<Integer> toolUsePromptTokenCount();

  /**
   * Output only. A detailed breakdown by modality of the token counts from the results of tool
   * executions, which are provided back to the model as input.
   */
  @JsonProperty("toolUsePromptTokensDetails")
  public abstract Optional<List<ModalityTokenCount>> toolUsePromptTokensDetails();

  /**
   * The total number of tokens for the entire request. This is the sum of `prompt_token_count`,
   * `candidates_token_count`, `tool_use_prompt_token_count`, and `thoughts_token_count`.
   */
  @JsonProperty("totalTokenCount")
  public abstract Optional<Integer> totalTokenCount();

  /** Output only. The traffic type for this request. */
  @JsonProperty("trafficType")
  public abstract Optional<TrafficType> trafficType();

  /** Instantiates a builder for GenerateContentResponseUsageMetadata. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_GenerateContentResponseUsageMetadata.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GenerateContentResponseUsageMetadata. */
  @AutoValue.Builder
  public abstract static class Builder {
    /**
     * For internal usage. Please use `GenerateContentResponseUsageMetadata.builder()` for
     * instantiation.
     */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_GenerateContentResponseUsageMetadata.Builder();
    }

    /**
     * Setter for cacheTokensDetails.
     *
     * <p>cacheTokensDetails: Output only. A detailed breakdown of the token count for each modality
     * in the cached content.
     */
    @JsonProperty("cacheTokensDetails")
    public abstract Builder cacheTokensDetails(List<ModalityTokenCount> cacheTokensDetails);

    /**
     * Setter for cacheTokensDetails.
     *
     * <p>cacheTokensDetails: Output only. A detailed breakdown of the token count for each modality
     * in the cached content.
     */
    @CanIgnoreReturnValue
    public Builder cacheTokensDetails(ModalityTokenCount... cacheTokensDetails) {
      return cacheTokensDetails(Arrays.asList(cacheTokensDetails));
    }

    /**
     * Setter for cacheTokensDetails builder.
     *
     * <p>cacheTokensDetails: Output only. A detailed breakdown of the token count for each modality
     * in the cached content.
     */
    @CanIgnoreReturnValue
    public Builder cacheTokensDetails(ModalityTokenCount.Builder... cacheTokensDetailsBuilders) {
      return cacheTokensDetails(
          Arrays.asList(cacheTokensDetailsBuilders).stream()
              .map(ModalityTokenCount.Builder::build)
              .collect(toImmutableList()));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder cacheTokensDetails(Optional<List<ModalityTokenCount>> cacheTokensDetails);

    /** Clears the value of cacheTokensDetails field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearCacheTokensDetails() {
      return cacheTokensDetails(Optional.empty());
    }

    /**
     * Setter for cachedContentTokenCount.
     *
     * <p>cachedContentTokenCount: Output only. The number of tokens in the cached content that was
     * used for this request.
     */
    @JsonProperty("cachedContentTokenCount")
    public abstract Builder cachedContentTokenCount(Integer cachedContentTokenCount);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder cachedContentTokenCount(Optional<Integer> cachedContentTokenCount);

    /** Clears the value of cachedContentTokenCount field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearCachedContentTokenCount() {
      return cachedContentTokenCount(Optional.empty());
    }

    /**
     * Setter for candidatesTokenCount.
     *
     * <p>candidatesTokenCount: The total number of tokens in the generated candidates.
     */
    @JsonProperty("candidatesTokenCount")
    public abstract Builder candidatesTokenCount(Integer candidatesTokenCount);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder candidatesTokenCount(Optional<Integer> candidatesTokenCount);

    /** Clears the value of candidatesTokenCount field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearCandidatesTokenCount() {
      return candidatesTokenCount(Optional.empty());
    }

    /**
     * Setter for candidatesTokensDetails.
     *
     * <p>candidatesTokensDetails: Output only. A detailed breakdown of the token count for each
     * modality in the generated candidates.
     */
    @JsonProperty("candidatesTokensDetails")
    public abstract Builder candidatesTokensDetails(
        List<ModalityTokenCount> candidatesTokensDetails);

    /**
     * Setter for candidatesTokensDetails.
     *
     * <p>candidatesTokensDetails: Output only. A detailed breakdown of the token count for each
     * modality in the generated candidates.
     */
    @CanIgnoreReturnValue
    public Builder candidatesTokensDetails(ModalityTokenCount... candidatesTokensDetails) {
      return candidatesTokensDetails(Arrays.asList(candidatesTokensDetails));
    }

    /**
     * Setter for candidatesTokensDetails builder.
     *
     * <p>candidatesTokensDetails: Output only. A detailed breakdown of the token count for each
     * modality in the generated candidates.
     */
    @CanIgnoreReturnValue
    public Builder candidatesTokensDetails(
        ModalityTokenCount.Builder... candidatesTokensDetailsBuilders) {
      return candidatesTokensDetails(
          Arrays.asList(candidatesTokensDetailsBuilders).stream()
              .map(ModalityTokenCount.Builder::build)
              .collect(toImmutableList()));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder candidatesTokensDetails(
        Optional<List<ModalityTokenCount>> candidatesTokensDetails);

    /** Clears the value of candidatesTokensDetails field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearCandidatesTokensDetails() {
      return candidatesTokensDetails(Optional.empty());
    }

    /**
     * Setter for promptTokenCount.
     *
     * <p>promptTokenCount: The total number of tokens in the prompt. This includes any text,
     * images, or other media provided in the request. When `cached_content` is set, this also
     * includes the number of tokens in the cached content.
     */
    @JsonProperty("promptTokenCount")
    public abstract Builder promptTokenCount(Integer promptTokenCount);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder promptTokenCount(Optional<Integer> promptTokenCount);

    /** Clears the value of promptTokenCount field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearPromptTokenCount() {
      return promptTokenCount(Optional.empty());
    }

    /**
     * Setter for promptTokensDetails.
     *
     * <p>promptTokensDetails: Output only. A detailed breakdown of the token count for each
     * modality in the prompt.
     */
    @JsonProperty("promptTokensDetails")
    public abstract Builder promptTokensDetails(List<ModalityTokenCount> promptTokensDetails);

    /**
     * Setter for promptTokensDetails.
     *
     * <p>promptTokensDetails: Output only. A detailed breakdown of the token count for each
     * modality in the prompt.
     */
    @CanIgnoreReturnValue
    public Builder promptTokensDetails(ModalityTokenCount... promptTokensDetails) {
      return promptTokensDetails(Arrays.asList(promptTokensDetails));
    }

    /**
     * Setter for promptTokensDetails builder.
     *
     * <p>promptTokensDetails: Output only. A detailed breakdown of the token count for each
     * modality in the prompt.
     */
    @CanIgnoreReturnValue
    public Builder promptTokensDetails(ModalityTokenCount.Builder... promptTokensDetailsBuilders) {
      return promptTokensDetails(
          Arrays.asList(promptTokensDetailsBuilders).stream()
              .map(ModalityTokenCount.Builder::build)
              .collect(toImmutableList()));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder promptTokensDetails(Optional<List<ModalityTokenCount>> promptTokensDetails);

    /** Clears the value of promptTokensDetails field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearPromptTokensDetails() {
      return promptTokensDetails(Optional.empty());
    }

    /**
     * Setter for thoughtsTokenCount.
     *
     * <p>thoughtsTokenCount: Output only. The number of tokens that were part of the model's
     * generated "thoughts" output, if applicable.
     */
    @JsonProperty("thoughtsTokenCount")
    public abstract Builder thoughtsTokenCount(Integer thoughtsTokenCount);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder thoughtsTokenCount(Optional<Integer> thoughtsTokenCount);

    /** Clears the value of thoughtsTokenCount field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearThoughtsTokenCount() {
      return thoughtsTokenCount(Optional.empty());
    }

    /**
     * Setter for toolUsePromptTokenCount.
     *
     * <p>toolUsePromptTokenCount: Output only. The number of tokens in the results from tool
     * executions, which are provided back to the model as input, if applicable.
     */
    @JsonProperty("toolUsePromptTokenCount")
    public abstract Builder toolUsePromptTokenCount(Integer toolUsePromptTokenCount);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder toolUsePromptTokenCount(Optional<Integer> toolUsePromptTokenCount);

    /** Clears the value of toolUsePromptTokenCount field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearToolUsePromptTokenCount() {
      return toolUsePromptTokenCount(Optional.empty());
    }

    /**
     * Setter for toolUsePromptTokensDetails.
     *
     * <p>toolUsePromptTokensDetails: Output only. A detailed breakdown by modality of the token
     * counts from the results of tool executions, which are provided back to the model as input.
     */
    @JsonProperty("toolUsePromptTokensDetails")
    public abstract Builder toolUsePromptTokensDetails(
        List<ModalityTokenCount> toolUsePromptTokensDetails);

    /**
     * Setter for toolUsePromptTokensDetails.
     *
     * <p>toolUsePromptTokensDetails: Output only. A detailed breakdown by modality of the token
     * counts from the results of tool executions, which are provided back to the model as input.
     */
    @CanIgnoreReturnValue
    public Builder toolUsePromptTokensDetails(ModalityTokenCount... toolUsePromptTokensDetails) {
      return toolUsePromptTokensDetails(Arrays.asList(toolUsePromptTokensDetails));
    }

    /**
     * Setter for toolUsePromptTokensDetails builder.
     *
     * <p>toolUsePromptTokensDetails: Output only. A detailed breakdown by modality of the token
     * counts from the results of tool executions, which are provided back to the model as input.
     */
    @CanIgnoreReturnValue
    public Builder toolUsePromptTokensDetails(
        ModalityTokenCount.Builder... toolUsePromptTokensDetailsBuilders) {
      return toolUsePromptTokensDetails(
          Arrays.asList(toolUsePromptTokensDetailsBuilders).stream()
              .map(ModalityTokenCount.Builder::build)
              .collect(toImmutableList()));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder toolUsePromptTokensDetails(
        Optional<List<ModalityTokenCount>> toolUsePromptTokensDetails);

    /** Clears the value of toolUsePromptTokensDetails field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearToolUsePromptTokensDetails() {
      return toolUsePromptTokensDetails(Optional.empty());
    }

    /**
     * Setter for totalTokenCount.
     *
     * <p>totalTokenCount: The total number of tokens for the entire request. This is the sum of
     * `prompt_token_count`, `candidates_token_count`, `tool_use_prompt_token_count`, and
     * `thoughts_token_count`.
     */
    @JsonProperty("totalTokenCount")
    public abstract Builder totalTokenCount(Integer totalTokenCount);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder totalTokenCount(Optional<Integer> totalTokenCount);

    /** Clears the value of totalTokenCount field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTotalTokenCount() {
      return totalTokenCount(Optional.empty());
    }

    /**
     * Setter for trafficType.
     *
     * <p>trafficType: Output only. The traffic type for this request.
     */
    @JsonProperty("trafficType")
    public abstract Builder trafficType(TrafficType trafficType);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder trafficType(Optional<TrafficType> trafficType);

    /** Clears the value of trafficType field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTrafficType() {
      return trafficType(Optional.empty());
    }

    /**
     * Setter for trafficType given a known enum.
     *
     * <p>trafficType: Output only. The traffic type for this request.
     */
    @CanIgnoreReturnValue
    public Builder trafficType(TrafficType.Known knownType) {
      return trafficType(new TrafficType(knownType));
    }

    /**
     * Setter for trafficType given a string.
     *
     * <p>trafficType: Output only. The traffic type for this request.
     */
    @CanIgnoreReturnValue
    public Builder trafficType(String trafficType) {
      return trafficType(new TrafficType(trafficType));
    }

    public abstract GenerateContentResponseUsageMetadata build();
  }

  /** Deserializes a JSON string to a GenerateContentResponseUsageMetadata object. */
  @ExcludeFromGeneratedCoverageReport
  public static GenerateContentResponseUsageMetadata fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GenerateContentResponseUsageMetadata.class);
  }
}
