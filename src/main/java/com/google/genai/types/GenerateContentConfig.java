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
import java.util.Map;
import java.util.Optional;

/**
 * Optional model configuration parameters.
 *
 * <p>For more information, see `Content generation parameters
 * <https://cloud.google.com/vertex-ai/generative-ai/docs/multimodal/content-generation-parameters>`_.
 */
@GenerateTypeAdapter
@AutoValue
public abstract class GenerateContentConfig extends JsonSerializable {
  /**
   * Instructions for the model to steer it toward better performance. For example, "Answer as
   * concisely as possible" or "Don't use technical terms in your response".
   */
  @SerializedName("systemInstruction")
  public abstract Optional<Content> systemInstruction();

  /**
   * Value that controls the degree of randomness in token selection. Lower temperatures are good
   * for prompts that require a less open-ended or creative response, while higher temperatures can
   * lead to more diverse or creative results.
   */
  @SerializedName("temperature")
  public abstract Optional<Float> temperature();

  /**
   * Tokens are selected from the most to least probable until the sum of their probabilities equals
   * this value. Use a lower value for less random responses and a higher value for more random
   * responses.
   */
  @SerializedName("topP")
  public abstract Optional<Float> topP();

  /**
   * For each token selection step, the ``top_k`` tokens with the highest probabilities are sampled.
   * Then tokens are further filtered based on ``top_p`` with the final token selected using
   * temperature sampling. Use a lower number for less random responses and a higher number for more
   * random responses.
   */
  @SerializedName("topK")
  public abstract Optional<Float> topK();

  /** Number of response variations to return. */
  @SerializedName("candidateCount")
  public abstract Optional<Integer> candidateCount();

  /** Maximum number of tokens that can be generated in the response. */
  @SerializedName("maxOutputTokens")
  public abstract Optional<Integer> maxOutputTokens();

  /**
   * List of strings that tells the model to stop generating text if one of the strings is
   * encountered in the response.
   */
  @SerializedName("stopSequences")
  public abstract Optional<List<String>> stopSequences();

  /**
   * Whether to return the log probabilities of the tokens that were chosen by the model at each
   * step.
   */
  @SerializedName("responseLogprobs")
  public abstract Optional<Boolean> responseLogprobs();

  /** Number of top candidate tokens to return the log probabilities for at each generation step. */
  @SerializedName("logprobs")
  public abstract Optional<Integer> logprobs();

  /**
   * Positive values penalize tokens that already appear in the generated text, increasing the
   * probability of generating more diverse content.
   */
  @SerializedName("presencePenalty")
  public abstract Optional<Float> presencePenalty();

  /**
   * Positive values penalize tokens that repeatedly appear in the generated text, increasing the
   * probability of generating more diverse content.
   */
  @SerializedName("frequencyPenalty")
  public abstract Optional<Float> frequencyPenalty();

  /**
   * When ``seed`` is fixed to a specific number, the model makes a best effort to provide the same
   * response for repeated requests. By default, a random number is used.
   */
  @SerializedName("seed")
  public abstract Optional<Integer> seed();

  /** Output response media type of the generated candidate text. */
  @SerializedName("responseMimeType")
  public abstract Optional<String> responseMimeType();

  /** Schema that the generated candidate text must adhere to. */
  @SerializedName("responseSchema")
  public abstract Optional<Schema> responseSchema();

  /** Configuration for model router requests. */
  @SerializedName("routingConfig")
  public abstract Optional<GenerationConfigRoutingConfig> routingConfig();

  /** Safety settings in the request to block unsafe content in the response. */
  @SerializedName("safetySettings")
  public abstract Optional<List<SafetySetting>> safetySettings();

  /**
   * Code that enables the system to interact with external systems to perform an action outside of
   * the knowledge and scope of the model.
   */
  @SerializedName("tools")
  public abstract Optional<List<Tool>> tools();

  /** Associates model output to a specific function call. */
  @SerializedName("toolConfig")
  public abstract Optional<ToolConfig> toolConfig();

  /** Labels with user-defined metadata to break down billed charges. */
  @SerializedName("labels")
  public abstract Optional<Map<String, String>> labels();

  /** Resource name of a context cache that can be used in subsequent requests. */
  @SerializedName("cachedContent")
  public abstract Optional<String> cachedContent();

  /**
   * The requested modalities of the response. Represents the set of modalities that the model can
   * return.
   */
  @SerializedName("responseModalities")
  public abstract Optional<List<String>> responseModalities();

  /** If specified, the media resolution specified will be used. */
  @SerializedName("mediaResolution")
  public abstract Optional<String> mediaResolution();

  /** The speech generation configuration. */
  @SerializedName("speechConfig")
  public abstract Optional<SpeechConfig> speechConfig();

  /** If enabled, audio timestamp will be included in the request to the model. */
  @SerializedName("audioTimestamp")
  public abstract Optional<Boolean> audioTimestamp();

  /** The thinking features configuration. */
  @SerializedName("thinkingConfig")
  public abstract Optional<ThinkingConfig> thinkingConfig();

  /** Instantiates a builder for GenerateContentConfig. */
  public static Builder builder() {
    return new AutoValue_GenerateContentConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GenerateContentConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    @SerializedName("systemInstruction")
    public abstract Builder systemInstruction(Content systemInstruction);

    @SerializedName("systemInstruction")
    abstract Builder systemInstruction(Optional<Content> systemInstruction);

    @SerializedName("temperature")
    public abstract Builder temperature(Float temperature);

    @SerializedName("temperature")
    abstract Builder temperature(Optional<Float> temperature);

    @SerializedName("topP")
    public abstract Builder topP(Float topP);

    @SerializedName("topP")
    abstract Builder topP(Optional<Float> topP);

    @SerializedName("topK")
    public abstract Builder topK(Float topK);

    @SerializedName("topK")
    abstract Builder topK(Optional<Float> topK);

    @SerializedName("candidateCount")
    public abstract Builder candidateCount(Integer candidateCount);

    @SerializedName("candidateCount")
    abstract Builder candidateCount(Optional<Integer> candidateCount);

    @SerializedName("maxOutputTokens")
    public abstract Builder maxOutputTokens(Integer maxOutputTokens);

    @SerializedName("maxOutputTokens")
    abstract Builder maxOutputTokens(Optional<Integer> maxOutputTokens);

    @SerializedName("stopSequences")
    public abstract Builder stopSequences(List<String> stopSequences);

    @SerializedName("stopSequences")
    abstract Builder stopSequences(Optional<List<String>> stopSequences);

    @SerializedName("responseLogprobs")
    public abstract Builder responseLogprobs(boolean responseLogprobs);

    @SerializedName("responseLogprobs")
    abstract Builder responseLogprobs(Optional<Boolean> responseLogprobs);

    @SerializedName("logprobs")
    public abstract Builder logprobs(Integer logprobs);

    @SerializedName("logprobs")
    abstract Builder logprobs(Optional<Integer> logprobs);

    @SerializedName("presencePenalty")
    public abstract Builder presencePenalty(Float presencePenalty);

    @SerializedName("presencePenalty")
    abstract Builder presencePenalty(Optional<Float> presencePenalty);

    @SerializedName("frequencyPenalty")
    public abstract Builder frequencyPenalty(Float frequencyPenalty);

    @SerializedName("frequencyPenalty")
    abstract Builder frequencyPenalty(Optional<Float> frequencyPenalty);

    @SerializedName("seed")
    public abstract Builder seed(Integer seed);

    @SerializedName("seed")
    abstract Builder seed(Optional<Integer> seed);

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

    @SerializedName("safetySettings")
    public abstract Builder safetySettings(List<SafetySetting> safetySettings);

    @SerializedName("safetySettings")
    abstract Builder safetySettings(Optional<List<SafetySetting>> safetySettings);

    @SerializedName("tools")
    public abstract Builder tools(List<Tool> tools);

    @SerializedName("tools")
    abstract Builder tools(Optional<List<Tool>> tools);

    @SerializedName("toolConfig")
    public abstract Builder toolConfig(ToolConfig toolConfig);

    @SerializedName("toolConfig")
    abstract Builder toolConfig(Optional<ToolConfig> toolConfig);

    @SerializedName("labels")
    public abstract Builder labels(Map<String, String> labels);

    @SerializedName("labels")
    abstract Builder labels(Optional<Map<String, String>> labels);

    @SerializedName("cachedContent")
    public abstract Builder cachedContent(String cachedContent);

    @SerializedName("cachedContent")
    abstract Builder cachedContent(Optional<String> cachedContent);

    @SerializedName("responseModalities")
    public abstract Builder responseModalities(List<String> responseModalities);

    @SerializedName("responseModalities")
    abstract Builder responseModalities(Optional<List<String>> responseModalities);

    @SerializedName("mediaResolution")
    public abstract Builder mediaResolution(String mediaResolution);

    @SerializedName("mediaResolution")
    abstract Builder mediaResolution(Optional<String> mediaResolution);

    @SerializedName("speechConfig")
    public abstract Builder speechConfig(SpeechConfig speechConfig);

    @SerializedName("speechConfig")
    abstract Builder speechConfig(Optional<SpeechConfig> speechConfig);

    @SerializedName("audioTimestamp")
    public abstract Builder audioTimestamp(boolean audioTimestamp);

    @SerializedName("audioTimestamp")
    abstract Builder audioTimestamp(Optional<Boolean> audioTimestamp);

    @SerializedName("thinkingConfig")
    public abstract Builder thinkingConfig(ThinkingConfig thinkingConfig);

    @SerializedName("thinkingConfig")
    abstract Builder thinkingConfig(Optional<ThinkingConfig> thinkingConfig);

    public abstract GenerateContentConfig build();
  }

  /** Deserializes a JSON string to a GenerateContentConfig object. */
  public static GenerateContentConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GenerateContentConfig.class);
  }
}
