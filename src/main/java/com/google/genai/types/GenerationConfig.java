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
    public Builder modelSelectionConfig(ModelSelectionConfig.Builder modelSelectionConfigBuilder) {
      return modelSelectionConfig(modelSelectionConfigBuilder.build());
    }

    /**
     * Setter for audioTimestamp.
     *
     * <p>audioTimestamp: Optional. If enabled, audio timestamp will be included in the request to
     * the model. This field is not supported in Gemini API.
     */
    @JsonProperty("audioTimestamp")
    public abstract Builder audioTimestamp(boolean audioTimestamp);

    /**
     * Setter for candidateCount.
     *
     * <p>candidateCount: Optional. Number of candidates to generate.
     */
    @JsonProperty("candidateCount")
    public abstract Builder candidateCount(Integer candidateCount);

    /**
     * Setter for enableAffectiveDialog.
     *
     * <p>enableAffectiveDialog: Optional. If enabled, the model will detect emotions and adapt its
     * responses accordingly. This field is not supported in Gemini API.
     */
    @JsonProperty("enableAffectiveDialog")
    public abstract Builder enableAffectiveDialog(boolean enableAffectiveDialog);

    /**
     * Setter for frequencyPenalty.
     *
     * <p>frequencyPenalty: Optional. Frequency penalties.
     */
    @JsonProperty("frequencyPenalty")
    public abstract Builder frequencyPenalty(Float frequencyPenalty);

    /**
     * Setter for logprobs.
     *
     * <p>logprobs: Optional. Logit probabilities.
     */
    @JsonProperty("logprobs")
    public abstract Builder logprobs(Integer logprobs);

    /**
     * Setter for maxOutputTokens.
     *
     * <p>maxOutputTokens: Optional. The maximum number of output tokens to generate per message.
     */
    @JsonProperty("maxOutputTokens")
    public abstract Builder maxOutputTokens(Integer maxOutputTokens);

    /**
     * Setter for mediaResolution.
     *
     * <p>mediaResolution: Optional. If specified, the media resolution specified will be used.
     */
    @JsonProperty("mediaResolution")
    public abstract Builder mediaResolution(MediaResolution mediaResolution);

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

    /**
     * Setter for responseLogprobs.
     *
     * <p>responseLogprobs: Optional. If true, export the logprobs results in response.
     */
    @JsonProperty("responseLogprobs")
    public abstract Builder responseLogprobs(boolean responseLogprobs);

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
    public Builder responseModalities(Modality... responseModalities) {
      return responseModalities(Arrays.asList(responseModalities));
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
    public Builder responseSchema(Schema.Builder responseSchemaBuilder) {
      return responseSchema(responseSchemaBuilder.build());
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
    public Builder routingConfig(GenerationConfigRoutingConfig.Builder routingConfigBuilder) {
      return routingConfig(routingConfigBuilder.build());
    }

    /**
     * Setter for seed.
     *
     * <p>seed: Optional. Seed.
     */
    @JsonProperty("seed")
    public abstract Builder seed(Integer seed);

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
    public Builder speechConfig(SpeechConfig.Builder speechConfigBuilder) {
      return speechConfig(speechConfigBuilder.build());
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
    public Builder stopSequences(String... stopSequences) {
      return stopSequences(Arrays.asList(stopSequences));
    }

    /**
     * Setter for temperature.
     *
     * <p>temperature: Optional. Controls the randomness of predictions.
     */
    @JsonProperty("temperature")
    public abstract Builder temperature(Float temperature);

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
    public Builder thinkingConfig(ThinkingConfig.Builder thinkingConfigBuilder) {
      return thinkingConfig(thinkingConfigBuilder.build());
    }

    /**
     * Setter for topK.
     *
     * <p>topK: Optional. If specified, top-k sampling will be used.
     */
    @JsonProperty("topK")
    public abstract Builder topK(Float topK);

    /**
     * Setter for topP.
     *
     * <p>topP: Optional. If specified, nucleus sampling will be used.
     */
    @JsonProperty("topP")
    public abstract Builder topP(Float topP);

    /**
     * Setter for enableEnhancedCivicAnswers.
     *
     * <p>enableEnhancedCivicAnswers: Optional. Enables enhanced civic answers. It may not be
     * available for all models. This field is not supported in Vertex AI.
     */
    @JsonProperty("enableEnhancedCivicAnswers")
    public abstract Builder enableEnhancedCivicAnswers(boolean enableEnhancedCivicAnswers);

    public abstract GenerationConfig build();
  }

  /** Deserializes a JSON string to a GenerationConfig object. */
  @ExcludeFromGeneratedCoverageReport
  public static GenerationConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GenerationConfig.class);
  }
}
