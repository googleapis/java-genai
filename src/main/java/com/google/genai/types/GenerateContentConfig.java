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
import java.util.Map;
import java.util.Optional;

/**
 * Optional model configuration parameters.
 *
 * <p>For more information, see `Content generation parameters
 * <https://cloud.google.com/vertex-ai/generative-ai/docs/multimodal/content-generation-parameters>`_.
 */
@AutoValue
@JsonDeserialize(builder = GenerateContentConfig.Builder.class)
public abstract class GenerateContentConfig extends JsonSerializable {
  /** Used to override HTTP request options. */
  @JsonProperty("httpOptions")
  public abstract Optional<HttpOptions> httpOptions();

  /** If true, the raw HTTP response will be returned in the 'sdk_http_response' field. */
  @JsonProperty("shouldReturnHttpResponse")
  public abstract Optional<Boolean> shouldReturnHttpResponse();

  /**
   * Instructions for the model to steer it toward better performance. For example, "Answer as
   * concisely as possible" or "Don't use technical terms in your response".
   */
  @JsonProperty("systemInstruction")
  public abstract Optional<Content> systemInstruction();

  /**
   * Value that controls the degree of randomness in token selection. Lower temperatures are good
   * for prompts that require a less open-ended or creative response, while higher temperatures can
   * lead to more diverse or creative results.
   */
  @JsonProperty("temperature")
  public abstract Optional<Float> temperature();

  /**
   * Tokens are selected from the most to least probable until the sum of their probabilities equals
   * this value. Use a lower value for less random responses and a higher value for more random
   * responses.
   */
  @JsonProperty("topP")
  public abstract Optional<Float> topP();

  /**
   * For each token selection step, the ``top_k`` tokens with the highest probabilities are sampled.
   * Then tokens are further filtered based on ``top_p`` with the final token selected using
   * temperature sampling. Use a lower number for less random responses and a higher number for more
   * random responses.
   */
  @JsonProperty("topK")
  public abstract Optional<Float> topK();

  /** Number of response variations to return. */
  @JsonProperty("candidateCount")
  public abstract Optional<Integer> candidateCount();

  /** Maximum number of tokens that can be generated in the response. */
  @JsonProperty("maxOutputTokens")
  public abstract Optional<Integer> maxOutputTokens();

  /**
   * List of strings that tells the model to stop generating text if one of the strings is
   * encountered in the response.
   */
  @JsonProperty("stopSequences")
  public abstract Optional<List<String>> stopSequences();

  /**
   * Whether to return the log probabilities of the tokens that were chosen by the model at each
   * step.
   */
  @JsonProperty("responseLogprobs")
  public abstract Optional<Boolean> responseLogprobs();

  /** Number of top candidate tokens to return the log probabilities for at each generation step. */
  @JsonProperty("logprobs")
  public abstract Optional<Integer> logprobs();

  /**
   * Positive values penalize tokens that already appear in the generated text, increasing the
   * probability of generating more diverse content.
   */
  @JsonProperty("presencePenalty")
  public abstract Optional<Float> presencePenalty();

  /**
   * Positive values penalize tokens that repeatedly appear in the generated text, increasing the
   * probability of generating more diverse content.
   */
  @JsonProperty("frequencyPenalty")
  public abstract Optional<Float> frequencyPenalty();

  /**
   * When ``seed`` is fixed to a specific number, the model makes a best effort to provide the same
   * response for repeated requests. By default, a random number is used.
   */
  @JsonProperty("seed")
  public abstract Optional<Integer> seed();

  /**
   * Output response mimetype of the generated candidate text. Supported mimetype: - `text/plain`:
   * (default) Text output. - `application/json`: JSON response in the candidates. The model needs
   * to be prompted to output the appropriate response type, otherwise the behavior is undefined.
   * This is a preview feature.
   */
  @JsonProperty("responseMimeType")
  public abstract Optional<String> responseMimeType();

  /**
   * The `Schema` object allows the definition of input and output data types. These types can be
   * objects, but also primitives and arrays. Represents a select subset of an [OpenAPI 3.0 schema
   * object](https://spec.openapis.org/oas/v3.0.3#schema). If set, a compatible response_mime_type
   * must also be set. Compatible mimetypes: `application/json`: Schema for JSON response.
   */
  @JsonProperty("responseSchema")
  public abstract Optional<Schema> responseSchema();

  /**
   * Optional. Output schema of the generated response. This is an alternative to `response_schema`
   * that accepts [JSON Schema](https://json-schema.org/). If set, `response_schema` must be
   * omitted, but `response_mime_type` is required. While the full JSON Schema may be sent, not all
   * features are supported. Specifically, only the following properties are supported: - `$id` -
   * `$defs` - `$ref` - `$anchor` - `type` - `format` - `title` - `description` - `enum` (for
   * strings and numbers) - `items` - `prefixItems` - `minItems` - `maxItems` - `minimum` -
   * `maximum` - `anyOf` - `oneOf` (interpreted the same as `anyOf`) - `properties` -
   * `additionalProperties` - `required` The non-standard `propertyOrdering` property may also be
   * set. Cyclic references are unrolled to a limited degree and, as such, may only be used within
   * non-required properties. (Nullable properties are not sufficient.) If `$ref` is set on a
   * sub-schema, no other properties, except for than those starting as a `$`, may be set.
   */
  @JsonProperty("responseJsonSchema")
  public abstract Optional<Object> responseJsonSchema();

  /** Configuration for model router requests. */
  @JsonProperty("routingConfig")
  public abstract Optional<GenerationConfigRoutingConfig> routingConfig();

  /** Configuration for model selection. */
  @JsonProperty("modelSelectionConfig")
  public abstract Optional<ModelSelectionConfig> modelSelectionConfig();

  /** Safety settings in the request to block unsafe content in the response. */
  @JsonProperty("safetySettings")
  public abstract Optional<List<SafetySetting>> safetySettings();

  /**
   * Code that enables the system to interact with external systems to perform an action outside of
   * the knowledge and scope of the model.
   */
  @JsonProperty("tools")
  public abstract Optional<List<Tool>> tools();

  /** Associates model output to a specific function call. */
  @JsonProperty("toolConfig")
  public abstract Optional<ToolConfig> toolConfig();

  /** Labels with user-defined metadata to break down billed charges. */
  @JsonProperty("labels")
  public abstract Optional<Map<String, String>> labels();

  /** Resource name of a context cache that can be used in subsequent requests. */
  @JsonProperty("cachedContent")
  public abstract Optional<String> cachedContent();

  /**
   * The requested modalities of the response. Represents the set of modalities that the model can
   * return.
   */
  @JsonProperty("responseModalities")
  public abstract Optional<List<String>> responseModalities();

  /** If specified, the media resolution specified will be used. */
  @JsonProperty("mediaResolution")
  public abstract Optional<MediaResolution> mediaResolution();

  /** The speech generation configuration. */
  @JsonProperty("speechConfig")
  public abstract Optional<SpeechConfig> speechConfig();

  /** If enabled, audio timestamp will be included in the request to the model. */
  @JsonProperty("audioTimestamp")
  public abstract Optional<Boolean> audioTimestamp();

  /** The configuration for automatic function calling. */
  @JsonProperty("automaticFunctionCalling")
  public abstract Optional<AutomaticFunctionCallingConfig> automaticFunctionCalling();

  /** The thinking features configuration. */
  @JsonProperty("thinkingConfig")
  public abstract Optional<ThinkingConfig> thinkingConfig();

  /** The image generation configuration. */
  @JsonProperty("imageConfig")
  public abstract Optional<ImageConfig> imageConfig();

  /** Instantiates a builder for GenerateContentConfig. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_GenerateContentConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GenerateContentConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `GenerateContentConfig.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_GenerateContentConfig.Builder();
    }

    /**
     * Setter for httpOptions.
     *
     * <p>httpOptions: Used to override HTTP request options.
     */
    @JsonProperty("httpOptions")
    public abstract Builder httpOptions(HttpOptions httpOptions);

    /**
     * Setter for httpOptions builder.
     *
     * <p>httpOptions: Used to override HTTP request options.
     */
    @CanIgnoreReturnValue
    public Builder httpOptions(HttpOptions.Builder httpOptionsBuilder) {
      return httpOptions(httpOptionsBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder httpOptions(Optional<HttpOptions> httpOptions);

    /** Clears the value of httpOptions field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearHttpOptions() {
      return httpOptions(Optional.empty());
    }

    /**
     * Setter for shouldReturnHttpResponse.
     *
     * <p>shouldReturnHttpResponse: If true, the raw HTTP response will be returned in the
     * 'sdk_http_response' field.
     */
    @JsonProperty("shouldReturnHttpResponse")
    public abstract Builder shouldReturnHttpResponse(boolean shouldReturnHttpResponse);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder shouldReturnHttpResponse(Optional<Boolean> shouldReturnHttpResponse);

    /** Clears the value of shouldReturnHttpResponse field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearShouldReturnHttpResponse() {
      return shouldReturnHttpResponse(Optional.empty());
    }

    /**
     * Setter for systemInstruction.
     *
     * <p>systemInstruction: Instructions for the model to steer it toward better performance. For
     * example, "Answer as concisely as possible" or "Don't use technical terms in your response".
     */
    @JsonProperty("systemInstruction")
    public abstract Builder systemInstruction(Content systemInstruction);

    /**
     * Setter for systemInstruction builder.
     *
     * <p>systemInstruction: Instructions for the model to steer it toward better performance. For
     * example, "Answer as concisely as possible" or "Don't use technical terms in your response".
     */
    @CanIgnoreReturnValue
    public Builder systemInstruction(Content.Builder systemInstructionBuilder) {
      return systemInstruction(systemInstructionBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder systemInstruction(Optional<Content> systemInstruction);

    /** Clears the value of systemInstruction field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearSystemInstruction() {
      return systemInstruction(Optional.empty());
    }

    /**
     * Setter for temperature.
     *
     * <p>temperature: Value that controls the degree of randomness in token selection. Lower
     * temperatures are good for prompts that require a less open-ended or creative response, while
     * higher temperatures can lead to more diverse or creative results.
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
     * <p>topP: Tokens are selected from the most to least probable until the sum of their
     * probabilities equals this value. Use a lower value for less random responses and a higher
     * value for more random responses.
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
     * Setter for topK.
     *
     * <p>topK: For each token selection step, the ``top_k`` tokens with the highest probabilities
     * are sampled. Then tokens are further filtered based on ``top_p`` with the final token
     * selected using temperature sampling. Use a lower number for less random responses and a
     * higher number for more random responses.
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
     * Setter for candidateCount.
     *
     * <p>candidateCount: Number of response variations to return.
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
     * Setter for maxOutputTokens.
     *
     * <p>maxOutputTokens: Maximum number of tokens that can be generated in the response.
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
     * Setter for stopSequences.
     *
     * <p>stopSequences: List of strings that tells the model to stop generating text if one of the
     * strings is encountered in the response.
     */
    @JsonProperty("stopSequences")
    public abstract Builder stopSequences(List<String> stopSequences);

    /**
     * Setter for stopSequences.
     *
     * <p>stopSequences: List of strings that tells the model to stop generating text if one of the
     * strings is encountered in the response.
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
     * Setter for responseLogprobs.
     *
     * <p>responseLogprobs: Whether to return the log probabilities of the tokens that were chosen
     * by the model at each step.
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
     * Setter for logprobs.
     *
     * <p>logprobs: Number of top candidate tokens to return the log probabilities for at each
     * generation step.
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
     * Setter for presencePenalty.
     *
     * <p>presencePenalty: Positive values penalize tokens that already appear in the generated
     * text, increasing the probability of generating more diverse content.
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
     * Setter for frequencyPenalty.
     *
     * <p>frequencyPenalty: Positive values penalize tokens that repeatedly appear in the generated
     * text, increasing the probability of generating more diverse content.
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
     * Setter for seed.
     *
     * <p>seed: When ``seed`` is fixed to a specific number, the model makes a best effort to
     * provide the same response for repeated requests. By default, a random number is used.
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
     * Setter for responseMimeType.
     *
     * <p>responseMimeType: Output response mimetype of the generated candidate text. Supported
     * mimetype: - `text/plain`: (default) Text output. - `application/json`: JSON response in the
     * candidates. The model needs to be prompted to output the appropriate response type, otherwise
     * the behavior is undefined. This is a preview feature.
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
     * Setter for responseSchema.
     *
     * <p>responseSchema: The `Schema` object allows the definition of input and output data types.
     * These types can be objects, but also primitives and arrays. Represents a select subset of an
     * [OpenAPI 3.0 schema object](https://spec.openapis.org/oas/v3.0.3#schema). If set, a
     * compatible response_mime_type must also be set. Compatible mimetypes: `application/json`:
     * Schema for JSON response.
     */
    @JsonProperty("responseSchema")
    public abstract Builder responseSchema(Schema responseSchema);

    /**
     * Setter for responseSchema builder.
     *
     * <p>responseSchema: The `Schema` object allows the definition of input and output data types.
     * These types can be objects, but also primitives and arrays. Represents a select subset of an
     * [OpenAPI 3.0 schema object](https://spec.openapis.org/oas/v3.0.3#schema). If set, a
     * compatible response_mime_type must also be set. Compatible mimetypes: `application/json`:
     * Schema for JSON response.
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
     * Setter for responseJsonSchema.
     *
     * <p>responseJsonSchema: Optional. Output schema of the generated response. This is an
     * alternative to `response_schema` that accepts [JSON Schema](https://json-schema.org/). If
     * set, `response_schema` must be omitted, but `response_mime_type` is required. While the full
     * JSON Schema may be sent, not all features are supported. Specifically, only the following
     * properties are supported: - `$id` - `$defs` - `$ref` - `$anchor` - `type` - `format` -
     * `title` - `description` - `enum` (for strings and numbers) - `items` - `prefixItems` -
     * `minItems` - `maxItems` - `minimum` - `maximum` - `anyOf` - `oneOf` (interpreted the same as
     * `anyOf`) - `properties` - `additionalProperties` - `required` The non-standard
     * `propertyOrdering` property may also be set. Cyclic references are unrolled to a limited
     * degree and, as such, may only be used within non-required properties. (Nullable properties
     * are not sufficient.) If `$ref` is set on a sub-schema, no other properties, except for than
     * those starting as a `$`, may be set.
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
     * Setter for routingConfig.
     *
     * <p>routingConfig: Configuration for model router requests.
     */
    @JsonProperty("routingConfig")
    public abstract Builder routingConfig(GenerationConfigRoutingConfig routingConfig);

    /**
     * Setter for routingConfig builder.
     *
     * <p>routingConfig: Configuration for model router requests.
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
     * Setter for modelSelectionConfig.
     *
     * <p>modelSelectionConfig: Configuration for model selection.
     */
    @JsonProperty("modelSelectionConfig")
    public abstract Builder modelSelectionConfig(ModelSelectionConfig modelSelectionConfig);

    /**
     * Setter for modelSelectionConfig builder.
     *
     * <p>modelSelectionConfig: Configuration for model selection.
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
     * Setter for safetySettings.
     *
     * <p>safetySettings: Safety settings in the request to block unsafe content in the response.
     */
    @JsonProperty("safetySettings")
    public abstract Builder safetySettings(List<SafetySetting> safetySettings);

    /**
     * Setter for safetySettings.
     *
     * <p>safetySettings: Safety settings in the request to block unsafe content in the response.
     */
    @CanIgnoreReturnValue
    public Builder safetySettings(SafetySetting... safetySettings) {
      return safetySettings(Arrays.asList(safetySettings));
    }

    /**
     * Setter for safetySettings builder.
     *
     * <p>safetySettings: Safety settings in the request to block unsafe content in the response.
     */
    @CanIgnoreReturnValue
    public Builder safetySettings(SafetySetting.Builder... safetySettingsBuilders) {
      return safetySettings(
          Arrays.asList(safetySettingsBuilders).stream()
              .map(SafetySetting.Builder::build)
              .collect(toImmutableList()));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder safetySettings(Optional<List<SafetySetting>> safetySettings);

    /** Clears the value of safetySettings field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearSafetySettings() {
      return safetySettings(Optional.empty());
    }

    /**
     * Setter for tools.
     *
     * <p>tools: Code that enables the system to interact with external systems to perform an action
     * outside of the knowledge and scope of the model.
     */
    @JsonProperty("tools")
    public abstract Builder tools(List<Tool> tools);

    /**
     * Setter for tools.
     *
     * <p>tools: Code that enables the system to interact with external systems to perform an action
     * outside of the knowledge and scope of the model.
     */
    @CanIgnoreReturnValue
    public Builder tools(Tool... tools) {
      return tools(Arrays.asList(tools));
    }

    /**
     * Setter for tools builder.
     *
     * <p>tools: Code that enables the system to interact with external systems to perform an action
     * outside of the knowledge and scope of the model.
     */
    @CanIgnoreReturnValue
    public Builder tools(Tool.Builder... toolsBuilders) {
      return tools(
          Arrays.asList(toolsBuilders).stream()
              .map(Tool.Builder::build)
              .collect(toImmutableList()));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder tools(Optional<List<Tool>> tools);

    /** Clears the value of tools field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTools() {
      return tools(Optional.empty());
    }

    /**
     * Setter for toolConfig.
     *
     * <p>toolConfig: Associates model output to a specific function call.
     */
    @JsonProperty("toolConfig")
    public abstract Builder toolConfig(ToolConfig toolConfig);

    /**
     * Setter for toolConfig builder.
     *
     * <p>toolConfig: Associates model output to a specific function call.
     */
    @CanIgnoreReturnValue
    public Builder toolConfig(ToolConfig.Builder toolConfigBuilder) {
      return toolConfig(toolConfigBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder toolConfig(Optional<ToolConfig> toolConfig);

    /** Clears the value of toolConfig field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearToolConfig() {
      return toolConfig(Optional.empty());
    }

    /**
     * Setter for labels.
     *
     * <p>labels: Labels with user-defined metadata to break down billed charges.
     */
    @JsonProperty("labels")
    public abstract Builder labels(Map<String, String> labels);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder labels(Optional<Map<String, String>> labels);

    /** Clears the value of labels field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearLabels() {
      return labels(Optional.empty());
    }

    /**
     * Setter for cachedContent.
     *
     * <p>cachedContent: Resource name of a context cache that can be used in subsequent requests.
     */
    @JsonProperty("cachedContent")
    public abstract Builder cachedContent(String cachedContent);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder cachedContent(Optional<String> cachedContent);

    /** Clears the value of cachedContent field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearCachedContent() {
      return cachedContent(Optional.empty());
    }

    /**
     * Setter for responseModalities.
     *
     * <p>responseModalities: The requested modalities of the response. Represents the set of
     * modalities that the model can return.
     */
    @JsonProperty("responseModalities")
    public abstract Builder responseModalities(List<String> responseModalities);

    /**
     * Setter for responseModalities.
     *
     * <p>responseModalities: The requested modalities of the response. Represents the set of
     * modalities that the model can return.
     */
    @CanIgnoreReturnValue
    public Builder responseModalities(String... responseModalities) {
      return responseModalities(Arrays.asList(responseModalities));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder responseModalities(Optional<List<String>> responseModalities);

    /** Clears the value of responseModalities field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearResponseModalities() {
      return responseModalities(Optional.empty());
    }

    /**
     * Setter for mediaResolution.
     *
     * <p>mediaResolution: If specified, the media resolution specified will be used.
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
     * <p>mediaResolution: If specified, the media resolution specified will be used.
     */
    @CanIgnoreReturnValue
    public Builder mediaResolution(MediaResolution.Known knownType) {
      return mediaResolution(new MediaResolution(knownType));
    }

    /**
     * Setter for mediaResolution given a string.
     *
     * <p>mediaResolution: If specified, the media resolution specified will be used.
     */
    @CanIgnoreReturnValue
    public Builder mediaResolution(String mediaResolution) {
      return mediaResolution(new MediaResolution(mediaResolution));
    }

    /**
     * Setter for speechConfig.
     *
     * <p>speechConfig: The speech generation configuration.
     */
    @JsonProperty("speechConfig")
    public abstract Builder speechConfig(SpeechConfig speechConfig);

    /**
     * Setter for speechConfig builder.
     *
     * <p>speechConfig: The speech generation configuration.
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
     * Setter for audioTimestamp.
     *
     * <p>audioTimestamp: If enabled, audio timestamp will be included in the request to the model.
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
     * Setter for automaticFunctionCalling.
     *
     * <p>automaticFunctionCalling: The configuration for automatic function calling.
     */
    @JsonProperty("automaticFunctionCalling")
    public abstract Builder automaticFunctionCalling(
        AutomaticFunctionCallingConfig automaticFunctionCalling);

    /**
     * Setter for automaticFunctionCalling builder.
     *
     * <p>automaticFunctionCalling: The configuration for automatic function calling.
     */
    @CanIgnoreReturnValue
    public Builder automaticFunctionCalling(
        AutomaticFunctionCallingConfig.Builder automaticFunctionCallingBuilder) {
      return automaticFunctionCalling(automaticFunctionCallingBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder automaticFunctionCalling(
        Optional<AutomaticFunctionCallingConfig> automaticFunctionCalling);

    /** Clears the value of automaticFunctionCalling field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearAutomaticFunctionCalling() {
      return automaticFunctionCalling(Optional.empty());
    }

    /**
     * Setter for thinkingConfig.
     *
     * <p>thinkingConfig: The thinking features configuration.
     */
    @JsonProperty("thinkingConfig")
    public abstract Builder thinkingConfig(ThinkingConfig thinkingConfig);

    /**
     * Setter for thinkingConfig builder.
     *
     * <p>thinkingConfig: The thinking features configuration.
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
     * Setter for imageConfig.
     *
     * <p>imageConfig: The image generation configuration.
     */
    @JsonProperty("imageConfig")
    public abstract Builder imageConfig(ImageConfig imageConfig);

    /**
     * Setter for imageConfig builder.
     *
     * <p>imageConfig: The image generation configuration.
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

    public abstract GenerateContentConfig build();
  }

  /** Deserializes a JSON string to a GenerateContentConfig object. */
  @ExcludeFromGeneratedCoverageReport
  public static GenerateContentConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GenerateContentConfig.class);
  }
}
