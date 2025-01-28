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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.List;
import java.util.Optional;

/** Generation config. */
@AutoValue
@JsonDeserialize(builder = AutoValue_GenerationConfig.Builder.class)
public abstract class GenerationConfig extends JsonSerializable {
  /** Optional. If enabled, audio timestamp will be included in the request to the model. */
  public abstract Optional<Boolean> getAudioTimestamp();

  /** Optional. Number of candidates to generate. */
  public abstract Optional<Integer> getCandidateCount();

  /** Optional. Frequency penalties. */
  public abstract Optional<Float> getFrequencyPenalty();

  /** Optional. Logit probabilities. */
  public abstract Optional<Integer> getLogprobs();

  /** Optional. The maximum number of output tokens to generate per message. */
  public abstract Optional<Integer> getMaxOutputTokens();

  /** Optional. Positive penalties. */
  public abstract Optional<Float> getPresencePenalty();

  /** Optional. If true, export the logprobs results in response. */
  public abstract Optional<Boolean> getResponseLogprobs();

  /**
   * Optional. Output response mimetype of the generated candidate text. Supported mimetype: -
   * `text/plain`: (default) Text output. - `application/json`: JSON response in the candidates. The
   * model needs to be prompted to output the appropriate response type, otherwise the behavior is
   * undefined. This is a preview feature.
   */
  public abstract Optional<String> getResponseMimeType();

  /**
   * Optional. The `Schema` object allows the definition of input and output data types. These types
   * can be objects, but also primitives and arrays. Represents a select subset of an [OpenAPI 3.0
   * schema object](https://spec.openapis.org/oas/v3.0.3#schema). If set, a compatible
   * response_mime_type must also be set. Compatible mimetypes: `application/json`: Schema for JSON
   * response.
   */
  public abstract Optional<Schema> getResponseSchema();

  /** Optional. Routing configuration. */
  public abstract Optional<GenerationConfigRoutingConfig> getRoutingConfig();

  /** Optional. Seed. */
  public abstract Optional<Integer> getSeed();

  /** Optional. Stop sequences. */
  public abstract Optional<List<String>> getStopSequences();

  /** Optional. Controls the randomness of predictions. */
  public abstract Optional<Float> getTemperature();

  /** Optional. If specified, top-k sampling will be used. */
  public abstract Optional<Float> getTopK();

  /** Optional. If specified, nucleus sampling will be used. */
  public abstract Optional<Float> getTopP();

  /** Instantiates a builder for GenerationConfig. */
  public static Builder builder() {
    return new AutoValue_GenerationConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GenerationConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("audioTimestamp")
    public abstract Builder setAudioTimestamp(boolean audioTimestamp);

    @JsonProperty("candidateCount")
    public abstract Builder setCandidateCount(Integer candidateCount);

    @JsonProperty("frequencyPenalty")
    public abstract Builder setFrequencyPenalty(Float frequencyPenalty);

    @JsonProperty("logprobs")
    public abstract Builder setLogprobs(Integer logprobs);

    @JsonProperty("maxOutputTokens")
    public abstract Builder setMaxOutputTokens(Integer maxOutputTokens);

    @JsonProperty("presencePenalty")
    public abstract Builder setPresencePenalty(Float presencePenalty);

    @JsonProperty("responseLogprobs")
    public abstract Builder setResponseLogprobs(boolean responseLogprobs);

    @JsonProperty("responseMimeType")
    public abstract Builder setResponseMimeType(String responseMimeType);

    @JsonProperty("responseSchema")
    public abstract Builder setResponseSchema(Schema responseSchema);

    @JsonProperty("routingConfig")
    public abstract Builder setRoutingConfig(GenerationConfigRoutingConfig routingConfig);

    @JsonProperty("seed")
    public abstract Builder setSeed(Integer seed);

    @JsonProperty("stopSequences")
    public abstract Builder setStopSequences(List<String> stopSequences);

    @JsonProperty("temperature")
    public abstract Builder setTemperature(Float temperature);

    @JsonProperty("topK")
    public abstract Builder setTopK(Float topK);

    @JsonProperty("topP")
    public abstract Builder setTopP(Float topP);

    public abstract GenerationConfig build();
  }

  /** Deserializes a JSON string to a GenerationConfig object. */
  public static GenerationConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GenerationConfig.class);
  }
}
