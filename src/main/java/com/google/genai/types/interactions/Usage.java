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

package com.google.genai.types.interactions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import com.google.genai.types.ExcludeFromGeneratedCoverageReport;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Token usage metadata for the Interactions API.
 *
 * <p>This class represents token usage statistics returned by the Interactions API. Unlike the
 * standard {@code UsageMetadata} class used by GenerateContent, the Interactions API returns
 * usage data with snake_case field names (e.g., {@code total_input_tokens} instead of
 * {@code promptTokenCount}).
 *
 * <p>Example usage:
 *
 * <pre>{@code
 * Interaction interaction = client.interactions.create(config);
 * if (interaction.usage().isPresent()) {
 *   Usage usage = interaction.usage().get();
 *   System.out.println("Input tokens: " + usage.totalInputTokens().orElse(0));
 *   System.out.println("Output tokens: " + usage.totalOutputTokens().orElse(0));
 *   System.out.println("Total tokens: " + usage.totalTokens().orElse(0));
 * }
 * }</pre>
 *
 * <p>The Interactions API is available in both Vertex AI and Gemini API.
 *
 * <p>Note: The Interactions API is in beta and subject to change.
 */
@AutoValue
@JsonDeserialize(builder = Usage.Builder.class)
public abstract class Usage extends JsonSerializable {

  /** Number of tokens in the prompt (context). */
  @JsonProperty("total_input_tokens")
  public abstract Optional<Integer> totalInputTokens();

  /** A breakdown of input token usage by modality. */
  @JsonProperty("input_tokens_by_modality")
  public abstract Optional<List<ModalityTokens>> inputTokensByModality();

  /** Number of tokens in the cached part of the prompt (the cached content). */
  @JsonProperty("total_cached_tokens")
  public abstract Optional<Integer> totalCachedTokens();

  /** A breakdown of cached token usage by modality. */
  @JsonProperty("cached_tokens_by_modality")
  public abstract Optional<List<ModalityTokens>> cachedTokensByModality();

  /** Total number of tokens across all the generated responses. */
  @JsonProperty("total_output_tokens")
  public abstract Optional<Integer> totalOutputTokens();

  /** A breakdown of output token usage by modality. */
  @JsonProperty("output_tokens_by_modality")
  public abstract Optional<List<ModalityTokens>> outputTokensByModality();

  /** Number of tokens present in tool-use prompt(s). */
  @JsonProperty("total_tool_use_tokens")
  public abstract Optional<Integer> totalToolUseTokens();

  /** A breakdown of tool-use token usage by modality. */
  @JsonProperty("tool_use_tokens_by_modality")
  public abstract Optional<List<ModalityTokens>> toolUseTokensByModality();

  /** Number of tokens of thoughts for thinking models. */
  @JsonProperty("total_thought_tokens")
  public abstract Optional<Integer> totalThoughtTokens();

  /** Total token count for the interaction request (prompt + responses + other internal tokens). */
  @JsonProperty("total_tokens")
  public abstract Optional<Integer> totalTokens();

  /** Instantiates a builder for Usage. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_Usage.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for Usage. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use {@code Usage.builder()} for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_Usage.Builder();
    }

    /**
     * Setter for totalInputTokens.
     *
     * <p>totalInputTokens: Number of tokens in the prompt (context).
     */
    @JsonProperty("total_input_tokens")
    public abstract Builder totalInputTokens(Integer totalInputTokens);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder totalInputTokens(Optional<Integer> totalInputTokens);

    /** Clears the value of totalInputTokens field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTotalInputTokens() {
      return totalInputTokens(Optional.empty());
    }

    /**
     * Setter for inputTokensByModality.
     *
     * <p>inputTokensByModality: A breakdown of input token usage by modality.
     */
    @JsonProperty("input_tokens_by_modality")
    public abstract Builder inputTokensByModality(List<ModalityTokens> inputTokensByModality);

    /**
     * Setter for inputTokensByModality (varargs convenience method).
     *
     * <p>inputTokensByModality: A breakdown of input token usage by modality.
     */
    @CanIgnoreReturnValue
    public Builder inputTokensByModality(ModalityTokens... inputTokensByModality) {
      return inputTokensByModality(Arrays.asList(inputTokensByModality));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder inputTokensByModality(Optional<List<ModalityTokens>> inputTokensByModality);

    /** Clears the value of inputTokensByModality field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearInputTokensByModality() {
      return inputTokensByModality(Optional.empty());
    }

    /**
     * Setter for totalCachedTokens.
     *
     * <p>totalCachedTokens: Number of tokens in the cached part of the prompt.
     */
    @JsonProperty("total_cached_tokens")
    public abstract Builder totalCachedTokens(Integer totalCachedTokens);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder totalCachedTokens(Optional<Integer> totalCachedTokens);

    /** Clears the value of totalCachedTokens field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTotalCachedTokens() {
      return totalCachedTokens(Optional.empty());
    }

    /**
     * Setter for cachedTokensByModality.
     *
     * <p>cachedTokensByModality: A breakdown of cached token usage by modality.
     */
    @JsonProperty("cached_tokens_by_modality")
    public abstract Builder cachedTokensByModality(List<ModalityTokens> cachedTokensByModality);

    /**
     * Setter for cachedTokensByModality (varargs convenience method).
     *
     * <p>cachedTokensByModality: A breakdown of cached token usage by modality.
     */
    @CanIgnoreReturnValue
    public Builder cachedTokensByModality(ModalityTokens... cachedTokensByModality) {
      return cachedTokensByModality(Arrays.asList(cachedTokensByModality));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder cachedTokensByModality(Optional<List<ModalityTokens>> cachedTokensByModality);

    /** Clears the value of cachedTokensByModality field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearCachedTokensByModality() {
      return cachedTokensByModality(Optional.empty());
    }

    /**
     * Setter for totalOutputTokens.
     *
     * <p>totalOutputTokens: Total number of tokens across all the generated responses.
     */
    @JsonProperty("total_output_tokens")
    public abstract Builder totalOutputTokens(Integer totalOutputTokens);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder totalOutputTokens(Optional<Integer> totalOutputTokens);

    /** Clears the value of totalOutputTokens field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTotalOutputTokens() {
      return totalOutputTokens(Optional.empty());
    }

    /**
     * Setter for outputTokensByModality.
     *
     * <p>outputTokensByModality: A breakdown of output token usage by modality.
     */
    @JsonProperty("output_tokens_by_modality")
    public abstract Builder outputTokensByModality(List<ModalityTokens> outputTokensByModality);

    /**
     * Setter for outputTokensByModality (varargs convenience method).
     *
     * <p>outputTokensByModality: A breakdown of output token usage by modality.
     */
    @CanIgnoreReturnValue
    public Builder outputTokensByModality(ModalityTokens... outputTokensByModality) {
      return outputTokensByModality(Arrays.asList(outputTokensByModality));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder outputTokensByModality(Optional<List<ModalityTokens>> outputTokensByModality);

    /** Clears the value of outputTokensByModality field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearOutputTokensByModality() {
      return outputTokensByModality(Optional.empty());
    }

    /**
     * Setter for totalToolUseTokens.
     *
     * <p>totalToolUseTokens: Number of tokens present in tool-use prompt(s).
     */
    @JsonProperty("total_tool_use_tokens")
    public abstract Builder totalToolUseTokens(Integer totalToolUseTokens);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder totalToolUseTokens(Optional<Integer> totalToolUseTokens);

    /** Clears the value of totalToolUseTokens field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTotalToolUseTokens() {
      return totalToolUseTokens(Optional.empty());
    }

    /**
     * Setter for toolUseTokensByModality.
     *
     * <p>toolUseTokensByModality: A breakdown of tool-use token usage by modality.
     */
    @JsonProperty("tool_use_tokens_by_modality")
    public abstract Builder toolUseTokensByModality(List<ModalityTokens> toolUseTokensByModality);

    /**
     * Setter for toolUseTokensByModality (varargs convenience method).
     *
     * <p>toolUseTokensByModality: A breakdown of tool-use token usage by modality.
     */
    @CanIgnoreReturnValue
    public Builder toolUseTokensByModality(ModalityTokens... toolUseTokensByModality) {
      return toolUseTokensByModality(Arrays.asList(toolUseTokensByModality));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder toolUseTokensByModality(Optional<List<ModalityTokens>> toolUseTokensByModality);

    /** Clears the value of toolUseTokensByModality field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearToolUseTokensByModality() {
      return toolUseTokensByModality(Optional.empty());
    }

    /**
     * Setter for totalThoughtTokens.
     *
     * <p>totalThoughtTokens: Number of tokens of thoughts for thinking models.
     */
    @JsonProperty("total_thought_tokens")
    public abstract Builder totalThoughtTokens(Integer totalThoughtTokens);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder totalThoughtTokens(Optional<Integer> totalThoughtTokens);

    /** Clears the value of totalThoughtTokens field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTotalThoughtTokens() {
      return totalThoughtTokens(Optional.empty());
    }

    /**
     * Setter for totalTokens.
     *
     * <p>totalTokens: Total token count for the interaction request.
     */
    @JsonProperty("total_tokens")
    public abstract Builder totalTokens(Integer totalTokens);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder totalTokens(Optional<Integer> totalTokens);

    /** Clears the value of totalTokens field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTotalTokens() {
      return totalTokens(Optional.empty());
    }

    public abstract Usage build();
  }

  /** Deserializes a JSON string to a Usage object. */
  @ExcludeFromGeneratedCoverageReport
  public static Usage fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, Usage.class);
  }
}
