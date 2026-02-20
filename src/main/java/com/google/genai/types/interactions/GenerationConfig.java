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
 * Generation configuration specific to the Interactions API.
 *
 * <p>This configuration contains exactly the 10 fields defined in the Interactions API
 * specification for the generationConfig parameter. It differs from the general {@code
 * GenerationConfig} class which is shared across multiple APIs and has 25+ fields.
 *
 * <p>The Interactions API is available in both Vertex AI and Gemini API.
 *
 * <p>The 10 fields are:
 *
 * <ol>
 *   <li>temperature - Controls randomness of predictions
 *   <li>topP - Nucleus sampling parameter
 *   <li>seed - Random seed for reproducibility
 *   <li>stopSequences - List of sequences that stop generation
 *   <li>toolChoice - Controls tool usage behavior
 *   <li>thinkingLevel - Controls model thinking depth
 *   <li>thinkingSummaries - Controls inclusion of thinking summaries
 *   <li>maxOutputTokens - Maximum tokens to generate
 *   <li>speechConfig - Configuration for speech generation
 *   <li>imageConfig - Configuration for image generation
 * </ol>
 */
@AutoValue
@JsonDeserialize(builder = GenerationConfig.Builder.class)
public abstract class GenerationConfig extends JsonSerializable {

  /** Controls the randomness of predictions. Higher values increase randomness. */
  @JsonProperty("temperature")
  public abstract Optional<Float> temperature();

  /**
   * Nucleus sampling parameter. Only tokens with cumulative probability up to topP are considered.
   */
  @JsonProperty("top_p")
  public abstract Optional<Float> topP();

  /** Random seed for reproducible generation. */
  @JsonProperty("seed")
  public abstract Optional<Integer> seed();

  /** List of sequences that will stop generation when encountered. */
  @JsonProperty("stop_sequences")
  public abstract Optional<List<String>> stopSequences();

  /**
   * Controls tool usage behavior.
   *
   * <p>Can be a simple type (auto, any, none, validated) or a configuration object specifying
   * allowed tools.
   */
  @JsonProperty("tool_choice")
  public abstract Optional<ToolChoice> toolChoice();

  /**
   * Controls the amount of thinking the model performs.
   *
   * <p>Supported values: MINIMAL, LOW, MEDIUM, HIGH.
   */
  @JsonProperty("thinking_level")
  public abstract Optional<ThinkingLevel> thinkingLevel();

  /**
   * Controls whether thinking summaries are included in the response.
   *
   * <p>Supported values: AUTO, NONE.
   */
  @JsonProperty("thinking_summaries")
  public abstract Optional<ThinkingSummaries> thinkingSummaries();

  /** The maximum number of output tokens to generate. */
  @JsonProperty("max_output_tokens")
  public abstract Optional<Integer> maxOutputTokens();

  /** Configuration for speech generation (voice, language, speaker). */
  @JsonProperty("speech_config")
  public abstract Optional<SpeechConfig> speechConfig();

  /** Configuration for image generation (aspect ratio, image size). */
  @JsonProperty("image_config")
  public abstract Optional<ImageConfig> imageConfig();

  /** Instantiates a builder for GenerationConfig. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_GenerationConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GenerationConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use {@code GenerationConfig.builder()} for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_GenerationConfig.Builder();
    }

    /**
     * Setter for temperature.
     *
     * <p>temperature: Controls the randomness of predictions. Higher values increase randomness.
     */
    @JsonProperty("temperature")
    public abstract Builder temperature(Float temperature);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder temperature(Optional<Float> temperature);

    /** Clears the value of temperature field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTemperature() {
      return temperature(Optional.empty());
    }

    /**
     * Setter for topP.
     *
     * <p>topP: Nucleus sampling parameter. Only tokens with cumulative probability up to topP are
     * considered.
     */
    @JsonProperty("top_p")
    public abstract Builder topP(Float topP);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder topP(Optional<Float> topP);

    /** Clears the value of topP field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTopP() {
      return topP(Optional.empty());
    }

    /**
     * Setter for seed.
     *
     * <p>seed: Random seed for reproducible generation.
     */
    @JsonProperty("seed")
    public abstract Builder seed(Integer seed);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder seed(Optional<Integer> seed);

    /** Clears the value of seed field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearSeed() {
      return seed(Optional.empty());
    }

    /**
     * Setter for stopSequences.
     *
     * <p>stopSequences: List of sequences that will stop generation when encountered.
     */
    @JsonProperty("stop_sequences")
    public abstract Builder stopSequences(List<String> stopSequences);

    /**
     * Setter for stopSequences (varargs convenience method).
     *
     * <p>stopSequences: List of sequences that will stop generation when encountered.
     */
    @CanIgnoreReturnValue
    public Builder stopSequences(String... stopSequences) {
      return stopSequences(Arrays.asList(stopSequences));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder stopSequences(Optional<List<String>> stopSequences);

    /** Clears the value of stopSequences field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearStopSequences() {
      return stopSequences(Optional.empty());
    }

    /**
     * Setter for toolChoice.
     *
     * <p>toolChoice: Controls tool usage behavior. Can be a simple type (auto, any, none,
     * validated) or a configuration object specifying allowed tools.
     */
    @JsonProperty("tool_choice")
    public abstract Builder toolChoice(ToolChoice toolChoice);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder toolChoice(Optional<ToolChoice> toolChoice);

    /** Clears the value of toolChoice field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearToolChoice() {
      return toolChoice(Optional.empty());
    }

    /**
     * Setter for thinkingLevel.
     *
     * <p>thinkingLevel: Controls the amount of thinking the model performs. Supported values:
     * MINIMAL, LOW, MEDIUM, HIGH.
     */
    @JsonProperty("thinking_level")
    public abstract Builder thinkingLevel(ThinkingLevel thinkingLevel);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder thinkingLevel(Optional<ThinkingLevel> thinkingLevel);

    /** Clears the value of thinkingLevel field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearThinkingLevel() {
      return thinkingLevel(Optional.empty());
    }

    /**
     * Setter for thinkingSummaries.
     *
     * <p>thinkingSummaries: Controls whether thinking summaries are included in the response.
     * Supported values: AUTO, NONE.
     */
    @JsonProperty("thinking_summaries")
    public abstract Builder thinkingSummaries(ThinkingSummaries thinkingSummaries);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder thinkingSummaries(Optional<ThinkingSummaries> thinkingSummaries);

    /** Clears the value of thinkingSummaries field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearThinkingSummaries() {
      return thinkingSummaries(Optional.empty());
    }

    /**
     * Setter for maxOutputTokens.
     *
     * <p>maxOutputTokens: The maximum number of output tokens to generate.
     */
    @JsonProperty("max_output_tokens")
    public abstract Builder maxOutputTokens(Integer maxOutputTokens);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder maxOutputTokens(Optional<Integer> maxOutputTokens);

    /** Clears the value of maxOutputTokens field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearMaxOutputTokens() {
      return maxOutputTokens(Optional.empty());
    }

    /**
     * Setter for speechConfig.
     *
     * <p>speechConfig: Configuration for speech generation (voice, language, speaker).
     */
    @JsonProperty("speech_config")
    public abstract Builder speechConfig(SpeechConfig speechConfig);

    /**
     * Convenience setter for speechConfig using a builder.
     *
     * <p>speechConfig: Configuration for speech generation (voice, language, speaker).
     */
    @CanIgnoreReturnValue
    public Builder speechConfig(SpeechConfig.Builder speechConfigBuilder) {
      return speechConfig(speechConfigBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder speechConfig(Optional<SpeechConfig> speechConfig);

    /** Clears the value of speechConfig field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearSpeechConfig() {
      return speechConfig(Optional.empty());
    }

    /**
     * Setter for imageConfig.
     *
     * <p>imageConfig: Configuration for image generation (aspect ratio, image size).
     */
    @JsonProperty("image_config")
    public abstract Builder imageConfig(ImageConfig imageConfig);

    /**
     * Convenience setter for imageConfig using a builder.
     *
     * <p>imageConfig: Configuration for image generation (aspect ratio, image size).
     */
    @CanIgnoreReturnValue
    public Builder imageConfig(ImageConfig.Builder imageConfigBuilder) {
      return imageConfig(imageConfigBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder imageConfig(Optional<ImageConfig> imageConfig);

    /** Clears the value of imageConfig field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearImageConfig() {
      return imageConfig(Optional.empty());
    }

    public abstract GenerationConfig build();
  }

  /** Deserializes a JSON string to an GenerationConfig object. */
  @ExcludeFromGeneratedCoverageReport
  public static GenerationConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GenerationConfig.class);
  }
}
