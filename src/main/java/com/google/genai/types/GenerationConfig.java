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

/** Generation config. */
@GenerateTypeAdapter
@AutoValue
public abstract class GenerationConfig extends JsonSerializable {
  /** Optional. If enabled, audio timestamp will be included in the request to the model. */
  @SerializedName("audioTimestamp")
  public abstract Optional<Boolean> audioTimestamp();

  /** Optional. Number of candidates to generate. */
  @SerializedName("candidateCount")
  public abstract Optional<Integer> candidateCount();

  /** Optional. Frequency penalties. */
  @SerializedName("frequencyPenalty")
  public abstract Optional<Float> frequencyPenalty();

  /** Optional. Logit probabilities. */
  @SerializedName("logprobs")
  public abstract Optional<Integer> logprobs();

  /** Optional. The maximum number of output tokens to generate per message. */
  @SerializedName("maxOutputTokens")
  public abstract Optional<Integer> maxOutputTokens();

  /** Optional. Positive penalties. */
  @SerializedName("presencePenalty")
  public abstract Optional<Float> presencePenalty();

  /** Optional. If true, export the logprobs results in response. */
  @SerializedName("responseLogprobs")
  public abstract Optional<Boolean> responseLogprobs();

  /**
   * Optional. Output response mimetype of the generated candidate text. Supported mimetype: -
   * `text/plain`: (default) Text output. - `application/json`: JSON response in the candidates. The
   * model needs to be prompted to output the appropriate response type, otherwise the behavior is
   * undefined. This is a preview feature.
   */
  @SerializedName("responseMimeType")
  public abstract Optional<String> responseMimeType();

  /**
   * Optional. The `Schema` object allows the definition of input and output data types. These types
   * can be objects, but also primitives and arrays. Represents a select subset of an [OpenAPI 3.0
   * schema object](https://spec.openapis.org/oas/v3.0.3#schema). If set, a compatible
   * response_mime_type must also be set. Compatible mimetypes: `application/json`: Schema for JSON
   * response.
   */
  @SerializedName("responseSchema")
  public abstract Optional<Schema> responseSchema();

  /** Optional. Routing configuration. */
  @SerializedName("routingConfig")
  public abstract Optional<GenerationConfigRoutingConfig> routingConfig();

  /** Optional. Seed. */
  @SerializedName("seed")
  public abstract Optional<Integer> seed();

  /** Optional. Stop sequences. */
  @SerializedName("stopSequences")
  public abstract Optional<List<String>> stopSequences();

  /** Optional. Controls the randomness of predictions. */
  @SerializedName("temperature")
  public abstract Optional<Float> temperature();

  /** Optional. If specified, top-k sampling will be used. */
  @SerializedName("topK")
  public abstract Optional<Float> topK();

  /** Optional. If specified, nucleus sampling will be used. */
  @SerializedName("topP")
  public abstract Optional<Float> topP();

  /** Instantiates a builder for GenerationConfig. */
  public static Builder builder() {
    return new AutoValue_GenerationConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GenerationConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    @SerializedName("audioTimestamp")
    public abstract Builder audioTimestamp(boolean audioTimestamp);

    @SerializedName("audioTimestamp")
    abstract Builder audioTimestamp(Optional<Boolean> audioTimestamp);

    @SerializedName("candidateCount")
    public abstract Builder candidateCount(Integer candidateCount);

    @SerializedName("candidateCount")
    abstract Builder candidateCount(Optional<Integer> candidateCount);

    @SerializedName("frequencyPenalty")
    public abstract Builder frequencyPenalty(Float frequencyPenalty);

    @SerializedName("frequencyPenalty")
    abstract Builder frequencyPenalty(Optional<Float> frequencyPenalty);

    @SerializedName("logprobs")
    public abstract Builder logprobs(Integer logprobs);

    @SerializedName("logprobs")
    abstract Builder logprobs(Optional<Integer> logprobs);

    @SerializedName("maxOutputTokens")
    public abstract Builder maxOutputTokens(Integer maxOutputTokens);

    @SerializedName("maxOutputTokens")
    abstract Builder maxOutputTokens(Optional<Integer> maxOutputTokens);

    @SerializedName("presencePenalty")
    public abstract Builder presencePenalty(Float presencePenalty);

    @SerializedName("presencePenalty")
    abstract Builder presencePenalty(Optional<Float> presencePenalty);

    @SerializedName("responseLogprobs")
    public abstract Builder responseLogprobs(boolean responseLogprobs);

    @SerializedName("responseLogprobs")
    abstract Builder responseLogprobs(Optional<Boolean> responseLogprobs);

    @SerializedName("responseMimeType")
    public abstract Builder responseMimeType(String responseMimeType);

    @SerializedName("responseMimeType")
    abstract Builder responseMimeType(Optional<String> responseMimeType);

    @SerializedName("responseSchema")
    public abstract Builder responseSchema(Schema responseSchema);

    @SerializedName("responseSchema")
    abstract Builder responseSchema(Optional<Schema> responseSchema);

    @SerializedName("routingConfig")
    public abstract Builder routingConfig(GenerationConfigRoutingConfig routingConfig);

    @SerializedName("routingConfig")
    abstract Builder routingConfig(Optional<GenerationConfigRoutingConfig> routingConfig);

    @SerializedName("seed")
    public abstract Builder seed(Integer seed);

    @SerializedName("seed")
    abstract Builder seed(Optional<Integer> seed);

    @SerializedName("stopSequences")
    public abstract Builder stopSequences(List<String> stopSequences);

    @SerializedName("stopSequences")
    abstract Builder stopSequences(Optional<List<String>> stopSequences);

    @SerializedName("temperature")
    public abstract Builder temperature(Float temperature);

    @SerializedName("temperature")
    abstract Builder temperature(Optional<Float> temperature);

    @SerializedName("topK")
    public abstract Builder topK(Float topK);

    @SerializedName("topK")
    abstract Builder topK(Optional<Float> topK);

    @SerializedName("topP")
    public abstract Builder topP(Float topP);

    @SerializedName("topP")
    abstract Builder topP(Optional<Float> topP);

    public abstract GenerationConfig build();
  }

  /** Deserializes a JSON string to a GenerationConfig object. */
  public static GenerationConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GenerationConfig.class);
  }
}
