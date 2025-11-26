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
   * Optional. If enabled, audio timestamp will be included in the request to the model. This field
   * is not supported in Gemini API.
   */
  @JsonProperty("audioTimestamp")
  public abstract Optional<Boolean> audioTimestamp();

  /** Optional. Number of candidates to generate. */
  @JsonProperty("candidateCount")
  public abstract Optional<Integer> candidateCount();

  /**
   * Optional. If enabled, the model will detect emotions and adapt its responses accordingly. This
   * field is not supported in Gemini API.
   */
  @JsonProperty("enableAffectiveDialog")
  public abstract Optional<Boolean> enableAffectiveDialog();

  /** Optional. Frequency penalties. */
  @JsonProperty("frequencyPenalty")
  public abstract Optional<Float> frequencyPenalty();

  /** Optional. Logit probabilities. */
  @JsonProperty("logprobs")
  public abstract Optional<Integer> logprobs();

  /** Optional. The maximum number of output tokens to generate per message. */
  @JsonProperty("maxOutputTokens")
  public abstract Optional<Integer> maxOutputTokens();

  /** Optional. If specified, the media resolution specified will be used. */
  @JsonProperty("mediaResolution")
  public abstract Optional<MediaResolution> mediaResolution();

  /** Optional. Positive penalties. */
  @JsonProperty("presencePenalty")
  public abstract Optional<Float> presencePenalty();

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

  /** Optional. If true, export the logprobs results in response. */
  @JsonProperty("responseLogprobs")
  public abstract Optional<Boolean> responseLogprobs();

  /**
   * Optional. Output response mimetype of the generated candidate text. Supported mimetype: -
   * `text/plain`: (default) Text output. - `application/json`: JSON response in the candidates. The
   * model needs to be prompted to output the appropriate response type, otherwise the behavior is
   * undefined. This is a preview feature.
   */
  @JsonProperty("responseMimeType")
  public abstract Optional<String> responseMimeType();

  /** Optional. The modalities of the response. */
  @JsonProperty("responseModalities")
  public abstract Optional<List<Modality>> responseModalities();

  /**
   * Optional. The `Schema` object allows the definition of input and output data types. These types
   * can be objects, but also primitives and arrays. Represents a select subset of an [OpenAPI 3.0
   * schema object](https://spec.openapis.org/oas/v3.0.3#schema). If set, a compatible
   * response_mime_type must also be set. Compatible mimetypes: `application/json`: Schema for JSON
   * response.
   */
  @JsonProperty("responseSchema")
  public abstract Optional<Schema> responseSchema();

  /** Optional. Routing configuration. This field is not supported in Gemini API. */
  @JsonProperty("routingConfig")
  public abstract Optional<GenerationConfigRoutingConfig> routingConfig();

  /** Optional. Seed. */
  @JsonProperty("seed")
  public abstract Optional<Integer> seed();

  /** Optional. The speech generation config. */
  @JsonProperty("speechConfig")
  public abstract Optional<SpeechConfig> speechConfig();

  /** Optional. Stop sequences. */
  @JsonProperty("stopSequences")
  public abstract Optional<List<String>> stopSequences();

  /** Optional. Controls the randomness of predictions. */
  @JsonProperty("temperature")
  public abstract Optional<Float> temperature();

  /**
   * Optional. Config for thinking features. An error will be returned if this field is set for
   * models that don't support thinking.
   */
  @JsonProperty("thinkingConfig")
  public abstract Optional<ThinkingConfig> thinkingConfig();

  /** Optional. If specified, top-k sampling will be used. */
  @JsonProperty("topK")
  public abstract Optional<Float> topK();

  /** Optional. If specified, nucleus sampling will be used. */
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
     * Setter for audioTimestamp.
     *
     * <p>audioTimestamp: Optional. If enabled, audio timestamp will be included in the request to
     * the model. This field is not supported in Gemini API.
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
     * <p>candidateCount: Optional. Number of candidates to generate.
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
     * responses accordingly. This field is not supported in Gemini API.
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
     * <p>frequencyPenalty: Optional. Frequency penalties.
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
     * <p>logprobs: Optional. Logit probabilities.
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
     * <p>maxOutputTokens: Optional. The maximum number of output tokens to generate per message.
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
     * <p>mediaResolution: Optional. If specified, the media resolution specified will be used.
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
     * <p>mediaResolution: Optional. If specified, the media resolution specified will be used.
     */
    @CanIgnoreReturnValue
    public Builder mediaResolution(MediaResolution.Known knownType) {
      return mediaResolution(new MediaResolution(knownType));
    }

    /**
     * Setter for mediaResolution given a string.
     *
     * <p>mediaResolution: Optional. If specified, the media resolution specified will be used.
     */
    @CanIgnoreReturnValue
    public Builder mediaResolution(String mediaResolution) {
      return mediaResolution(new MediaResolution(mediaResolution));
    }

    /**
     * Setter for presencePenalty.
     *
     * <p>presencePenalty: Optional. Positive penalties.
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
     * Setter for responseLogprobs.
     *
     * <p>responseLogprobs: Optional. If true, export the logprobs results in response.
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
     * <p>responseMimeType: Optional. Output response mimetype of the generated candidate text.
     * Supported mimetype: - `text/plain`: (default) Text output. - `application/json`: JSON
     * response in the candidates. The model needs to be prompted to output the appropriate response
     * type, otherwise the behavior is undefined. This is a preview feature.
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
     * <p>responseModalities: Optional. The modalities of the response.
     */
    @JsonProperty("responseModalities")
    public abstract Builder responseModalities(List<Modality> responseModalities);

    /**
     * Setter for responseModalities.
     *
     * <p>responseModalities: Optional. The modalities of the response.
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
     * <p>responseModalities: Optional. The modalities of the response.
     */
    @CanIgnoreReturnValue
    public Builder responseModalities(String... responseModalities) {
      return responseModalitiesFromString(Arrays.asList(responseModalities));
    }

    /**
     * Setter for responseModalities given a varargs of known enums.
     *
     * <p>responseModalities: Optional. The modalities of the response.
     */
    @CanIgnoreReturnValue
    public Builder responseModalities(Modality.Known... knownTypes) {
      return responseModalitiesFromKnown(Arrays.asList(knownTypes));
    }

    /**
     * Setter for responseModalities given a list of known enums.
     *
     * <p>responseModalities: Optional. The modalities of the response.
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
     * <p>responseModalities: Optional. The modalities of the response.
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
     * <p>responseSchema: Optional. The `Schema` object allows the definition of input and output
     * data types. These types can be objects, but also primitives and arrays. Represents a select
     * subset of an [OpenAPI 3.0 schema object](https://spec.openapis.org/oas/v3.0.3#schema). If
     * set, a compatible response_mime_type must also be set. Compatible mimetypes:
     * `application/json`: Schema for JSON response.
     */
    @JsonProperty("responseSchema")
    public abstract Builder responseSchema(Schema responseSchema);

    /**
     * Setter for responseSchema builder.
     *
     * <p>responseSchema: Optional. The `Schema` object allows the definition of input and output
     * data types. These types can be objects, but also primitives and arrays. Represents a select
     * subset of an [OpenAPI 3.0 schema object](https://spec.openapis.org/oas/v3.0.3#schema). If
     * set, a compatible response_mime_type must also be set. Compatible mimetypes:
     * `application/json`: Schema for JSON response.
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
     * <p>seed: Optional. Seed.
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
     * <p>stopSequences: Optional. Stop sequences.
     */
    @JsonProperty("stopSequences")
    public abstract Builder stopSequences(List<String> stopSequences);

    /**
     * Setter for stopSequences.
     *
     * <p>stopSequences: Optional. Stop sequences.
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
     * <p>temperature: Optional. Controls the randomness of predictions.
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
     * <p>thinkingConfig: Optional. Config for thinking features. An error will be returned if this
     * field is set for models that don't support thinking.
     */
    @JsonProperty("thinkingConfig")
    public abstract Builder thinkingConfig(ThinkingConfig thinkingConfig);

    /**
     * Setter for thinkingConfig builder.
     *
     * <p>thinkingConfig: Optional. Config for thinking features. An error will be returned if this
     * field is set for models that don't support thinking.
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
     * <p>topK: Optional. If specified, top-k sampling will be used.
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
     * <p>topP: Optional. If specified, nucleus sampling will be used.
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
