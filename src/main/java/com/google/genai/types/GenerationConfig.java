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
import com.google.common.collect.ImmutableList;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/** Generation config. */
@AutoValue
@JsonDeserialize(builder = GenerationConfig.Builder.class)
public abstract class GenerationConfig extends JsonSerializable {
  /** Optional. Config for model selection. */
  @JsonProperty("modelSelectionConfig")
  public abstract Optional<ModelSelectionConfig> modelSelectionConfig();

  /**
   * Output schema of the generated response. This is an alternative to `response_schema` that
   * accepts [JSON Schema](https://json-schema.org/).
   */
  @JsonProperty("responseJsonSchema")
  public abstract Optional<Object> responseJsonSchema();

  /**
   * Optional. If enabled, audio timestamps will be included in the request to the model. This can
   * be useful for synchronizing audio with other modalities in the response. This field is not
   * supported in Gemini API.
   */
  @JsonProperty("audioTimestamp")
  public abstract Optional<Boolean> audioTimestamp();

  /**
   * Optional. The number of candidate responses to generate. A higher `candidate_count` can provide
   * more options to choose from, but it also consumes more resources. This can be useful for
   * generating a variety of responses and selecting the best one.
   */
  @JsonProperty("candidateCount")
  public abstract Optional<Integer> candidateCount();

  /**
   * Optional. If enabled, the model will detect emotions and adapt its responses accordingly. For
   * example, if the model detects that the user is frustrated, it may provide a more empathetic
   * response. This field is not supported in Gemini API.
   */
  @JsonProperty("enableAffectiveDialog")
  public abstract Optional<Boolean> enableAffectiveDialog();

  /**
   * Optional. Penalizes tokens based on their frequency in the generated text. A positive value
   * helps to reduce the repetition of words and phrases. Valid values can range from [-2.0, 2.0].
   */
  @JsonProperty("frequencyPenalty")
  public abstract Optional<Float> frequencyPenalty();

  /**
   * Optional. The number of top log probabilities to return for each token. This can be used to see
   * which other tokens were considered likely candidates for a given position. A higher value will
   * return more options, but it will also increase the size of the response.
   */
  @JsonProperty("logprobs")
  public abstract Optional<Integer> logprobs();

  /**
   * Optional. The maximum number of tokens to generate in the response. A token is approximately
   * four characters. The default value varies by model. This parameter can be used to control the
   * length of the generated text and prevent overly long responses.
   */
  @JsonProperty("maxOutputTokens")
  public abstract Optional<Integer> maxOutputTokens();

  /**
   * Optional. The token resolution at which input media content is sampled. This is used to control
   * the trade-off between the quality of the response and the number of tokens used to represent
   * the media. A higher resolution allows the model to perceive more detail, which can lead to a
   * more nuanced response, but it will also use more tokens. This does not affect the image
   * dimensions sent to the model.
   */
  @JsonProperty("mediaResolution")
  public abstract Optional<MediaResolution> mediaResolution();

  /**
   * Optional. Penalizes tokens that have already appeared in the generated text. A positive value
   * encourages the model to generate more diverse and less repetitive text. Valid values can range
   * from [-2.0, 2.0].
   */
  @JsonProperty("presencePenalty")
  public abstract Optional<Float> presencePenalty();

  /**
   * Optional. If set to true, the log probabilities of the output tokens are returned. Log
   * probabilities are the logarithm of the probability of a token appearing in the output. A higher
   * log probability means the token is more likely to be generated. This can be useful for
   * analyzing the model's confidence in its own output and for debugging.
   */
  @JsonProperty("responseLogprobs")
  public abstract Optional<Boolean> responseLogprobs();

  /**
   * Optional. The IANA standard MIME type of the response. The model will generate output that
   * conforms to this MIME type. Supported values include 'text/plain' (default) and
   * 'application/json'. The model needs to be prompted to output the appropriate response type,
   * otherwise the behavior is undefined. This is a preview feature.
   */
  @JsonProperty("responseMimeType")
  public abstract Optional<String> responseMimeType();

  /**
   * Optional. The modalities of the response. The model will generate a response that includes all
   * the specified modalities. For example, if this is set to `[TEXT, IMAGE]`, the response will
   * include both text and an image.
   */
  @JsonProperty("responseModalities")
  public abstract Optional<List<Modality>> responseModalities();

  /**
   * Optional. Lets you to specify a schema for the model's response, ensuring that the output
   * conforms to a particular structure. This is useful for generating structured data such as JSON.
   * The schema is a subset of the [OpenAPI 3.0 schema
   * object](https://spec.openapis.org/oas/v3.0.3#schema) object. When this field is set, you must
   * also set the `response_mime_type` to `application/json`.
   */
  @JsonProperty("responseSchema")
  public abstract Optional<Schema> responseSchema();

  /** Optional. Routing configuration. This field is not supported in Gemini API. */
  @JsonProperty("routingConfig")
  public abstract Optional<GenerationConfigRoutingConfig> routingConfig();

  /**
   * Optional. A seed for the random number generator. By setting a seed, you can make the model's
   * output mostly deterministic. For a given prompt and parameters (like temperature, top_p, etc.),
   * the model will produce the same response every time. However, it's not a guaranteed absolute
   * deterministic behavior. This is different from parameters like `temperature`, which control the
   * *level* of randomness. `seed` ensures that the "random" choices the model makes are the same on
   * every run, making it essential for testing and ensuring reproducible results.
   */
  @JsonProperty("seed")
  public abstract Optional<Integer> seed();

  /** Optional. The speech generation config. */
  @JsonProperty("speechConfig")
  public abstract Optional<SpeechConfig> speechConfig();

  /**
   * Optional. A list of character sequences that will stop the model from generating further
   * tokens. If a stop sequence is generated, the output will end at that point. This is useful for
   * controlling the length and structure of the output. For example, you can use ["\n", "###"] to
   * stop generation at a new line or a specific marker.
   */
  @JsonProperty("stopSequences")
  public abstract Optional<List<String>> stopSequences();

  /**
   * Optional. Controls the randomness of the output. A higher temperature results in more creative
   * and diverse responses, while a lower temperature makes the output more predictable and focused.
   * The valid range is (0.0, 2.0].
   */
  @JsonProperty("temperature")
  public abstract Optional<Float> temperature();

  /**
   * Optional. Configuration for thinking features. An error will be returned if this field is set
   * for models that don't support thinking.
   */
  @JsonProperty("thinkingConfig")
  public abstract Optional<ThinkingConfig> thinkingConfig();

  /**
   * Optional. Specifies the top-k sampling threshold. The model considers only the top k most
   * probable tokens for the next token. This can be useful for generating more coherent and less
   * random text. For example, a `top_k` of 40 means the model will choose the next word from the 40
   * most likely words.
   */
  @JsonProperty("topK")
  public abstract Optional<Float> topK();

  /**
   * Optional. Specifies the nucleus sampling threshold. The model considers only the smallest set
   * of tokens whose cumulative probability is at least `top_p`. This helps generate more diverse
   * and less repetitive responses. For example, a `top_p` of 0.9 means the model considers tokens
   * until the cumulative probability of the tokens to select from reaches 0.9. It's recommended to
   * adjust either temperature or `top_p`, but not both.
   */
  @JsonProperty("topP")
  public abstract Optional<Float> topP();

  /**
   * Optional. Enables enhanced civic answers. It may not be available for all models. This field is
   * not supported in Vertex AI.
   */
  @JsonProperty("enableEnhancedCivicAnswers")
  public abstract Optional<Boolean> enableEnhancedCivicAnswers();

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
    /** For internal usage. Please use `GenerationConfig.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_GenerationConfig.Builder();
    }

    /**
     * Setter for modelSelectionConfig.
     *
     * <p>modelSelectionConfig: Optional. Config for model selection.
     */
    @JsonProperty("modelSelectionConfig")
    public abstract Builder modelSelectionConfig(ModelSelectionConfig modelSelectionConfig);

    /**
     * Setter for modelSelectionConfig builder.
     *
     * <p>modelSelectionConfig: Optional. Config for model selection.
     */
    @CanIgnoreReturnValue
    public Builder modelSelectionConfig(ModelSelectionConfig.Builder modelSelectionConfigBuilder) {
      return modelSelectionConfig(modelSelectionConfigBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder modelSelectionConfig(Optional<ModelSelectionConfig> modelSelectionConfig);

    /** Clears the value of modelSelectionConfig field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearModelSelectionConfig() {
      return modelSelectionConfig(Optional.empty());
    }

    /**
     * Setter for responseJsonSchema.
     *
     * <p>responseJsonSchema: Output schema of the generated response. This is an alternative to
     * `response_schema` that accepts [JSON Schema](https://json-schema.org/).
     */
    @JsonProperty("responseJsonSchema")
    public abstract Builder responseJsonSchema(Object responseJsonSchema);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder responseJsonSchema(Optional<Object> responseJsonSchema);

    /** Clears the value of responseJsonSchema field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearResponseJsonSchema() {
      return responseJsonSchema(Optional.empty());
    }

    /**
     * Setter for audioTimestamp.
     *
     * <p>audioTimestamp: Optional. If enabled, audio timestamps will be included in the request to
     * the model. This can be useful for synchronizing audio with other modalities in the response.
     * This field is not supported in Gemini API.
     */
    @JsonProperty("audioTimestamp")
    public abstract Builder audioTimestamp(boolean audioTimestamp);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder audioTimestamp(Optional<Boolean> audioTimestamp);

    /** Clears the value of audioTimestamp field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearAudioTimestamp() {
      return audioTimestamp(Optional.empty());
    }

    /**
     * Setter for candidateCount.
     *
     * <p>candidateCount: Optional. The number of candidate responses to generate. A higher
     * `candidate_count` can provide more options to choose from, but it also consumes more
     * resources. This can be useful for generating a variety of responses and selecting the best
     * one.
     */
    @JsonProperty("candidateCount")
    public abstract Builder candidateCount(Integer candidateCount);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder candidateCount(Optional<Integer> candidateCount);

    /** Clears the value of candidateCount field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearCandidateCount() {
      return candidateCount(Optional.empty());
    }

    /**
     * Setter for enableAffectiveDialog.
     *
     * <p>enableAffectiveDialog: Optional. If enabled, the model will detect emotions and adapt its
     * responses accordingly. For example, if the model detects that the user is frustrated, it may
     * provide a more empathetic response. This field is not supported in Gemini API.
     */
    @JsonProperty("enableAffectiveDialog")
    public abstract Builder enableAffectiveDialog(boolean enableAffectiveDialog);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder enableAffectiveDialog(Optional<Boolean> enableAffectiveDialog);

    /** Clears the value of enableAffectiveDialog field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearEnableAffectiveDialog() {
      return enableAffectiveDialog(Optional.empty());
    }

    /**
     * Setter for frequencyPenalty.
     *
     * <p>frequencyPenalty: Optional. Penalizes tokens based on their frequency in the generated
     * text. A positive value helps to reduce the repetition of words and phrases. Valid values can
     * range from [-2.0, 2.0].
     */
    @JsonProperty("frequencyPenalty")
    public abstract Builder frequencyPenalty(Float frequencyPenalty);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder frequencyPenalty(Optional<Float> frequencyPenalty);

    /** Clears the value of frequencyPenalty field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearFrequencyPenalty() {
      return frequencyPenalty(Optional.empty());
    }

    /**
     * Setter for logprobs.
     *
     * <p>logprobs: Optional. The number of top log probabilities to return for each token. This can
     * be used to see which other tokens were considered likely candidates for a given position. A
     * higher value will return more options, but it will also increase the size of the response.
     */
    @JsonProperty("logprobs")
    public abstract Builder logprobs(Integer logprobs);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder logprobs(Optional<Integer> logprobs);

    /** Clears the value of logprobs field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearLogprobs() {
      return logprobs(Optional.empty());
    }

    /**
     * Setter for maxOutputTokens.
     *
     * <p>maxOutputTokens: Optional. The maximum number of tokens to generate in the response. A
     * token is approximately four characters. The default value varies by model. This parameter can
     * be used to control the length of the generated text and prevent overly long responses.
     */
    @JsonProperty("maxOutputTokens")
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
     * Setter for mediaResolution.
     *
     * <p>mediaResolution: Optional. The token resolution at which input media content is sampled.
     * This is used to control the trade-off between the quality of the response and the number of
     * tokens used to represent the media. A higher resolution allows the model to perceive more
     * detail, which can lead to a more nuanced response, but it will also use more tokens. This
     * does not affect the image dimensions sent to the model.
     */
    @JsonProperty("mediaResolution")
    public abstract Builder mediaResolution(MediaResolution mediaResolution);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder mediaResolution(Optional<MediaResolution> mediaResolution);

    /** Clears the value of mediaResolution field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearMediaResolution() {
      return mediaResolution(Optional.empty());
    }

    /**
     * Setter for mediaResolution given a known enum.
     *
     * <p>mediaResolution: Optional. The token resolution at which input media content is sampled.
     * This is used to control the trade-off between the quality of the response and the number of
     * tokens used to represent the media. A higher resolution allows the model to perceive more
     * detail, which can lead to a more nuanced response, but it will also use more tokens. This
     * does not affect the image dimensions sent to the model.
     */
    @CanIgnoreReturnValue
    public Builder mediaResolution(MediaResolution.Known knownType) {
      return mediaResolution(new MediaResolution(knownType));
    }

    /**
     * Setter for mediaResolution given a string.
     *
     * <p>mediaResolution: Optional. The token resolution at which input media content is sampled.
     * This is used to control the trade-off between the quality of the response and the number of
     * tokens used to represent the media. A higher resolution allows the model to perceive more
     * detail, which can lead to a more nuanced response, but it will also use more tokens. This
     * does not affect the image dimensions sent to the model.
     */
    @CanIgnoreReturnValue
    public Builder mediaResolution(String mediaResolution) {
      return mediaResolution(new MediaResolution(mediaResolution));
    }

    /**
     * Setter for presencePenalty.
     *
     * <p>presencePenalty: Optional. Penalizes tokens that have already appeared in the generated
     * text. A positive value encourages the model to generate more diverse and less repetitive
     * text. Valid values can range from [-2.0, 2.0].
     */
    @JsonProperty("presencePenalty")
    public abstract Builder presencePenalty(Float presencePenalty);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder presencePenalty(Optional<Float> presencePenalty);

    /** Clears the value of presencePenalty field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearPresencePenalty() {
      return presencePenalty(Optional.empty());
    }

    /**
     * Setter for responseLogprobs.
     *
     * <p>responseLogprobs: Optional. If set to true, the log probabilities of the output tokens are
     * returned. Log probabilities are the logarithm of the probability of a token appearing in the
     * output. A higher log probability means the token is more likely to be generated. This can be
     * useful for analyzing the model's confidence in its own output and for debugging.
     */
    @JsonProperty("responseLogprobs")
    public abstract Builder responseLogprobs(boolean responseLogprobs);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder responseLogprobs(Optional<Boolean> responseLogprobs);

    /** Clears the value of responseLogprobs field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearResponseLogprobs() {
      return responseLogprobs(Optional.empty());
    }

    /**
     * Setter for responseMimeType.
     *
     * <p>responseMimeType: Optional. The IANA standard MIME type of the response. The model will
     * generate output that conforms to this MIME type. Supported values include 'text/plain'
     * (default) and 'application/json'. The model needs to be prompted to output the appropriate
     * response type, otherwise the behavior is undefined. This is a preview feature.
     */
    @JsonProperty("responseMimeType")
    public abstract Builder responseMimeType(String responseMimeType);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder responseMimeType(Optional<String> responseMimeType);

    /** Clears the value of responseMimeType field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearResponseMimeType() {
      return responseMimeType(Optional.empty());
    }

    /**
     * Setter for responseModalities.
     *
     * <p>responseModalities: Optional. The modalities of the response. The model will generate a
     * response that includes all the specified modalities. For example, if this is set to `[TEXT,
     * IMAGE]`, the response will include both text and an image.
     */
    @JsonProperty("responseModalities")
    public abstract Builder responseModalities(List<Modality> responseModalities);

    /**
     * Setter for responseModalities.
     *
     * <p>responseModalities: Optional. The modalities of the response. The model will generate a
     * response that includes all the specified modalities. For example, if this is set to `[TEXT,
     * IMAGE]`, the response will include both text and an image.
     */
    @CanIgnoreReturnValue
    public Builder responseModalities(Modality... responseModalities) {
      return responseModalities(Arrays.asList(responseModalities));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder responseModalities(Optional<List<Modality>> responseModalities);

    /** Clears the value of responseModalities field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearResponseModalities() {
      return responseModalities(Optional.empty());
    }

    /**
     * Setter for responseModalities given a varargs of strings.
     *
     * <p>responseModalities: Optional. The modalities of the response. The model will generate a
     * response that includes all the specified modalities. For example, if this is set to `[TEXT,
     * IMAGE]`, the response will include both text and an image.
     */
    @CanIgnoreReturnValue
    public Builder responseModalities(String... responseModalities) {
      return responseModalitiesFromString(Arrays.asList(responseModalities));
    }

    /**
     * Setter for responseModalities given a varargs of known enums.
     *
     * <p>responseModalities: Optional. The modalities of the response. The model will generate a
     * response that includes all the specified modalities. For example, if this is set to `[TEXT,
     * IMAGE]`, the response will include both text and an image.
     */
    @CanIgnoreReturnValue
    public Builder responseModalities(Modality.Known... knownTypes) {
      return responseModalitiesFromKnown(Arrays.asList(knownTypes));
    }

    /**
     * Setter for responseModalities given a list of known enums.
     *
     * <p>responseModalities: Optional. The modalities of the response. The model will generate a
     * response that includes all the specified modalities. For example, if this is set to `[TEXT,
     * IMAGE]`, the response will include both text and an image.
     */
    @CanIgnoreReturnValue
    public Builder responseModalitiesFromKnown(List<Modality.Known> knownTypes) {
      ImmutableList<Modality> listItems =
          knownTypes.stream().map(Modality::new).collect(toImmutableList());
      return responseModalities(listItems);
    }

    /**
     * Setter for responseModalities given a list of strings.
     *
     * <p>responseModalities: Optional. The modalities of the response. The model will generate a
     * response that includes all the specified modalities. For example, if this is set to `[TEXT,
     * IMAGE]`, the response will include both text and an image.
     */
    @CanIgnoreReturnValue
    public Builder responseModalitiesFromString(List<String> responseModalities) {
      ImmutableList<Modality> listItems =
          responseModalities.stream().map(Modality::new).collect(toImmutableList());
      return responseModalities(listItems);
    }

    /**
     * Setter for responseSchema.
     *
     * <p>responseSchema: Optional. Lets you to specify a schema for the model's response, ensuring
     * that the output conforms to a particular structure. This is useful for generating structured
     * data such as JSON. The schema is a subset of the [OpenAPI 3.0 schema
     * object](https://spec.openapis.org/oas/v3.0.3#schema) object. When this field is set, you must
     * also set the `response_mime_type` to `application/json`.
     */
    @JsonProperty("responseSchema")
    public abstract Builder responseSchema(Schema responseSchema);

    /**
     * Setter for responseSchema builder.
     *
     * <p>responseSchema: Optional. Lets you to specify a schema for the model's response, ensuring
     * that the output conforms to a particular structure. This is useful for generating structured
     * data such as JSON. The schema is a subset of the [OpenAPI 3.0 schema
     * object](https://spec.openapis.org/oas/v3.0.3#schema) object. When this field is set, you must
     * also set the `response_mime_type` to `application/json`.
     */
    @CanIgnoreReturnValue
    public Builder responseSchema(Schema.Builder responseSchemaBuilder) {
      return responseSchema(responseSchemaBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder responseSchema(Optional<Schema> responseSchema);

    /** Clears the value of responseSchema field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearResponseSchema() {
      return responseSchema(Optional.empty());
    }

    /**
     * Setter for routingConfig.
     *
     * <p>routingConfig: Optional. Routing configuration. This field is not supported in Gemini API.
     */
    @JsonProperty("routingConfig")
    public abstract Builder routingConfig(GenerationConfigRoutingConfig routingConfig);

    /**
     * Setter for routingConfig builder.
     *
     * <p>routingConfig: Optional. Routing configuration. This field is not supported in Gemini API.
     */
    @CanIgnoreReturnValue
    public Builder routingConfig(GenerationConfigRoutingConfig.Builder routingConfigBuilder) {
      return routingConfig(routingConfigBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder routingConfig(Optional<GenerationConfigRoutingConfig> routingConfig);

    /** Clears the value of routingConfig field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearRoutingConfig() {
      return routingConfig(Optional.empty());
    }

    /**
     * Setter for seed.
     *
     * <p>seed: Optional. A seed for the random number generator. By setting a seed, you can make
     * the model's output mostly deterministic. For a given prompt and parameters (like temperature,
     * top_p, etc.), the model will produce the same response every time. However, it's not a
     * guaranteed absolute deterministic behavior. This is different from parameters like
     * `temperature`, which control the *level* of randomness. `seed` ensures that the "random"
     * choices the model makes are the same on every run, making it essential for testing and
     * ensuring reproducible results.
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
     * Setter for speechConfig.
     *
     * <p>speechConfig: Optional. The speech generation config.
     */
    @JsonProperty("speechConfig")
    public abstract Builder speechConfig(SpeechConfig speechConfig);

    /**
     * Setter for speechConfig builder.
     *
     * <p>speechConfig: Optional. The speech generation config.
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
     * Setter for stopSequences.
     *
     * <p>stopSequences: Optional. A list of character sequences that will stop the model from
     * generating further tokens. If a stop sequence is generated, the output will end at that
     * point. This is useful for controlling the length and structure of the output. For example,
     * you can use ["\n", "###"] to stop generation at a new line or a specific marker.
     */
    @JsonProperty("stopSequences")
    public abstract Builder stopSequences(List<String> stopSequences);

    /**
     * Setter for stopSequences.
     *
     * <p>stopSequences: Optional. A list of character sequences that will stop the model from
     * generating further tokens. If a stop sequence is generated, the output will end at that
     * point. This is useful for controlling the length and structure of the output. For example,
     * you can use ["\n", "###"] to stop generation at a new line or a specific marker.
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
     * Setter for temperature.
     *
     * <p>temperature: Optional. Controls the randomness of the output. A higher temperature results
     * in more creative and diverse responses, while a lower temperature makes the output more
     * predictable and focused. The valid range is (0.0, 2.0].
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
     * Setter for thinkingConfig.
     *
     * <p>thinkingConfig: Optional. Configuration for thinking features. An error will be returned
     * if this field is set for models that don't support thinking.
     */
    @JsonProperty("thinkingConfig")
    public abstract Builder thinkingConfig(ThinkingConfig thinkingConfig);

    /**
     * Setter for thinkingConfig builder.
     *
     * <p>thinkingConfig: Optional. Configuration for thinking features. An error will be returned
     * if this field is set for models that don't support thinking.
     */
    @CanIgnoreReturnValue
    public Builder thinkingConfig(ThinkingConfig.Builder thinkingConfigBuilder) {
      return thinkingConfig(thinkingConfigBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder thinkingConfig(Optional<ThinkingConfig> thinkingConfig);

    /** Clears the value of thinkingConfig field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearThinkingConfig() {
      return thinkingConfig(Optional.empty());
    }

    /**
     * Setter for topK.
     *
     * <p>topK: Optional. Specifies the top-k sampling threshold. The model considers only the top k
     * most probable tokens for the next token. This can be useful for generating more coherent and
     * less random text. For example, a `top_k` of 40 means the model will choose the next word from
     * the 40 most likely words.
     */
    @JsonProperty("topK")
    public abstract Builder topK(Float topK);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder topK(Optional<Float> topK);

    /** Clears the value of topK field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTopK() {
      return topK(Optional.empty());
    }

    /**
     * Setter for topP.
     *
     * <p>topP: Optional. Specifies the nucleus sampling threshold. The model considers only the
     * smallest set of tokens whose cumulative probability is at least `top_p`. This helps generate
     * more diverse and less repetitive responses. For example, a `top_p` of 0.9 means the model
     * considers tokens until the cumulative probability of the tokens to select from reaches 0.9.
     * It's recommended to adjust either temperature or `top_p`, but not both.
     */
    @JsonProperty("topP")
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
     * Setter for enableEnhancedCivicAnswers.
     *
     * <p>enableEnhancedCivicAnswers: Optional. Enables enhanced civic answers. It may not be
     * available for all models. This field is not supported in Vertex AI.
     */
    @JsonProperty("enableEnhancedCivicAnswers")
    public abstract Builder enableEnhancedCivicAnswers(boolean enableEnhancedCivicAnswers);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder enableEnhancedCivicAnswers(Optional<Boolean> enableEnhancedCivicAnswers);

    /** Clears the value of enableEnhancedCivicAnswers field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearEnableEnhancedCivicAnswers() {
      return enableEnhancedCivicAnswers(Optional.empty());
    }

    public abstract GenerationConfig build();
  }

  /** Deserializes a JSON string to a GenerationConfig object. */
  @ExcludeFromGeneratedCoverageReport
  public static GenerationConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GenerationConfig.class);
  }
}
