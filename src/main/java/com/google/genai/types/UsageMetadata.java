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

/** Usage metadata about response(s). */
@AutoValue
@JsonDeserialize(builder = UsageMetadata.Builder.class)
public abstract class UsageMetadata extends JsonSerializable {
  /**
   * Number of tokens in the prompt. When `cached_content` is set, this is still the total effective
   * prompt size meaning this includes the number of tokens in the cached content.
   */
  @JsonProperty("promptTokenCount")
  public abstract Optional<Integer> promptTokenCount();

  /** Number of tokens in the cached part of the prompt (the cached content). */
  @JsonProperty("cachedContentTokenCount")
  public abstract Optional<Integer> cachedContentTokenCount();

  /** Total number of tokens across all the generated response candidates. */
  @JsonProperty("responseTokenCount")
  public abstract Optional<Integer> responseTokenCount();

  /** Number of tokens present in tool-use prompt(s). */
  @JsonProperty("toolUsePromptTokenCount")
  public abstract Optional<Integer> toolUsePromptTokenCount();

  /** Number of tokens of thoughts for thinking models. */
  @JsonProperty("thoughtsTokenCount")
  public abstract Optional<Integer> thoughtsTokenCount();

  /** Total token count for prompt, response candidates, and tool-use prompts(if present). */
  @JsonProperty("totalTokenCount")
  public abstract Optional<Integer> totalTokenCount();

  /** List of modalities that were processed in the request input. */
  @JsonProperty("promptTokensDetails")
  public abstract Optional<List<ModalityTokenCount>> promptTokensDetails();

  /** List of modalities that were processed in the cache input. */
  @JsonProperty("cacheTokensDetails")
  public abstract Optional<List<ModalityTokenCount>> cacheTokensDetails();

  /** List of modalities that were returned in the response. */
  @JsonProperty("responseTokensDetails")
  public abstract Optional<List<ModalityTokenCount>> responseTokensDetails();

  /** List of modalities that were processed in the tool-use prompt. */
  @JsonProperty("toolUsePromptTokensDetails")
  public abstract Optional<List<ModalityTokenCount>> toolUsePromptTokensDetails();

  /**
   * Traffic type. This shows whether a request consumes Pay-As-You-Go or Provisioned Throughput
   * quota.
   */
  @JsonProperty("trafficType")
  public abstract Optional<TrafficType> trafficType();

  /** Instantiates a builder for UsageMetadata. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_UsageMetadata.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for UsageMetadata. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `UsageMetadata.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_UsageMetadata.Builder();
    }

    /**
     * Setter for promptTokenCount.
     *
     * <p>promptTokenCount: Number of tokens in the prompt. When `cached_content` is set, this is
     * still the total effective prompt size meaning this includes the number of tokens in the
     * cached content.
     */
    @JsonProperty("promptTokenCount")
    public abstract Builder promptTokenCount(Integer promptTokenCount);

    /**
     * Setter for cachedContentTokenCount.
     *
     * <p>cachedContentTokenCount: Number of tokens in the cached part of the prompt (the cached
     * content).
     */
    @JsonProperty("cachedContentTokenCount")
    public abstract Builder cachedContentTokenCount(Integer cachedContentTokenCount);

    /**
     * Setter for responseTokenCount.
     *
     * <p>responseTokenCount: Total number of tokens across all the generated response candidates.
     */
    @JsonProperty("responseTokenCount")
    public abstract Builder responseTokenCount(Integer responseTokenCount);

    /**
     * Setter for toolUsePromptTokenCount.
     *
     * <p>toolUsePromptTokenCount: Number of tokens present in tool-use prompt(s).
     */
    @JsonProperty("toolUsePromptTokenCount")
    public abstract Builder toolUsePromptTokenCount(Integer toolUsePromptTokenCount);

    /**
     * Setter for thoughtsTokenCount.
     *
     * <p>thoughtsTokenCount: Number of tokens of thoughts for thinking models.
     */
    @JsonProperty("thoughtsTokenCount")
    public abstract Builder thoughtsTokenCount(Integer thoughtsTokenCount);

    /**
     * Setter for totalTokenCount.
     *
     * <p>totalTokenCount: Total token count for prompt, response candidates, and tool-use
     * prompts(if present).
     */
    @JsonProperty("totalTokenCount")
    public abstract Builder totalTokenCount(Integer totalTokenCount);

    /**
     * Setter for promptTokensDetails.
     *
     * <p>promptTokensDetails: List of modalities that were processed in the request input.
     */
    @JsonProperty("promptTokensDetails")
    public abstract Builder promptTokensDetails(List<ModalityTokenCount> promptTokensDetails);

    /**
     * Setter for promptTokensDetails.
     *
     * <p>promptTokensDetails: List of modalities that were processed in the request input.
     */
    public Builder promptTokensDetails(ModalityTokenCount... promptTokensDetails) {
      return promptTokensDetails(Arrays.asList(promptTokensDetails));
    }

    /**
     * Setter for promptTokensDetails builder.
     *
     * <p>promptTokensDetails: List of modalities that were processed in the request input.
     */
    public Builder promptTokensDetails(ModalityTokenCount.Builder... promptTokensDetailsBuilders) {
      return promptTokensDetails(
          Arrays.asList(promptTokensDetailsBuilders).stream()
              .map(ModalityTokenCount.Builder::build)
              .collect(toImmutableList()));
    }

    /**
     * Setter for cacheTokensDetails.
     *
     * <p>cacheTokensDetails: List of modalities that were processed in the cache input.
     */
    @JsonProperty("cacheTokensDetails")
    public abstract Builder cacheTokensDetails(List<ModalityTokenCount> cacheTokensDetails);

    /**
     * Setter for cacheTokensDetails.
     *
     * <p>cacheTokensDetails: List of modalities that were processed in the cache input.
     */
    public Builder cacheTokensDetails(ModalityTokenCount... cacheTokensDetails) {
      return cacheTokensDetails(Arrays.asList(cacheTokensDetails));
    }

    /**
     * Setter for cacheTokensDetails builder.
     *
     * <p>cacheTokensDetails: List of modalities that were processed in the cache input.
     */
    public Builder cacheTokensDetails(ModalityTokenCount.Builder... cacheTokensDetailsBuilders) {
      return cacheTokensDetails(
          Arrays.asList(cacheTokensDetailsBuilders).stream()
              .map(ModalityTokenCount.Builder::build)
              .collect(toImmutableList()));
    }

    /**
     * Setter for responseTokensDetails.
     *
     * <p>responseTokensDetails: List of modalities that were returned in the response.
     */
    @JsonProperty("responseTokensDetails")
    public abstract Builder responseTokensDetails(List<ModalityTokenCount> responseTokensDetails);

    /**
     * Setter for responseTokensDetails.
     *
     * <p>responseTokensDetails: List of modalities that were returned in the response.
     */
    public Builder responseTokensDetails(ModalityTokenCount... responseTokensDetails) {
      return responseTokensDetails(Arrays.asList(responseTokensDetails));
    }

    /**
     * Setter for responseTokensDetails builder.
     *
     * <p>responseTokensDetails: List of modalities that were returned in the response.
     */
    public Builder responseTokensDetails(
        ModalityTokenCount.Builder... responseTokensDetailsBuilders) {
      return responseTokensDetails(
          Arrays.asList(responseTokensDetailsBuilders).stream()
              .map(ModalityTokenCount.Builder::build)
              .collect(toImmutableList()));
    }

    /**
     * Setter for toolUsePromptTokensDetails.
     *
     * <p>toolUsePromptTokensDetails: List of modalities that were processed in the tool-use prompt.
     */
    @JsonProperty("toolUsePromptTokensDetails")
    public abstract Builder toolUsePromptTokensDetails(
        List<ModalityTokenCount> toolUsePromptTokensDetails);

    /**
     * Setter for toolUsePromptTokensDetails.
     *
     * <p>toolUsePromptTokensDetails: List of modalities that were processed in the tool-use prompt.
     */
    public Builder toolUsePromptTokensDetails(ModalityTokenCount... toolUsePromptTokensDetails) {
      return toolUsePromptTokensDetails(Arrays.asList(toolUsePromptTokensDetails));
    }

    /**
     * Setter for toolUsePromptTokensDetails builder.
     *
     * <p>toolUsePromptTokensDetails: List of modalities that were processed in the tool-use prompt.
     */
    public Builder toolUsePromptTokensDetails(
        ModalityTokenCount.Builder... toolUsePromptTokensDetailsBuilders) {
      return toolUsePromptTokensDetails(
          Arrays.asList(toolUsePromptTokensDetailsBuilders).stream()
              .map(ModalityTokenCount.Builder::build)
              .collect(toImmutableList()));
    }

    /**
     * Setter for trafficType.
     *
     * <p>trafficType: Traffic type. This shows whether a request consumes Pay-As-You-Go or
     * Provisioned Throughput quota.
     */
    @JsonProperty("trafficType")
    public abstract Builder trafficType(TrafficType trafficType);

    /**
     * Setter for trafficType given a known enum.
     *
     * <p>trafficType: Traffic type. This shows whether a request consumes Pay-As-You-Go or
     * Provisioned Throughput quota.
     */
    @CanIgnoreReturnValue
    public Builder trafficType(TrafficType.Known knownType) {
      return trafficType(new TrafficType(knownType));
    }

    /**
     * Setter for trafficType given a string.
     *
     * <p>trafficType: Traffic type. This shows whether a request consumes Pay-As-You-Go or
     * Provisioned Throughput quota.
     */
    @CanIgnoreReturnValue
    public Builder trafficType(String trafficType) {
      return trafficType(new TrafficType(trafficType));
    }

    public abstract UsageMetadata build();
  }

  /** Deserializes a JSON string to a UsageMetadata object. */
  @ExcludeFromGeneratedCoverageReport
  public static UsageMetadata fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, UsageMetadata.class);
  }
}
