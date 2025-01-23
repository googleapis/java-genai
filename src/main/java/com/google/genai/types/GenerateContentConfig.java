// Auto-generated code. Do not edit.

package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.List;
import java.util.Optional;

/**
 * Optional model configuration parameters.
 *
 * <p>For more information, see `Content generation parameters
 * <https://cloud.google.com/vertex-ai/generative-ai/docs/multimodal/content-generation-parameters>`_.
 */
@AutoValue
@JsonDeserialize(builder = AutoValue_GenerateContentConfig.Builder.class)
public abstract class GenerateContentConfig extends JsonSerializable {
  /**
   * Instructions for the model to steer it toward better performance. For example, "Answer as
   * concisely as possible" or "Don't use technical terms in your response".
   */
  public abstract Optional<Content> getSystemInstruction();

  /**
   * Value that controls the degree of randomness in token selection. Lower temperatures are good
   * for prompts that require a less open-ended or creative response, while higher temperatures can
   * lead to more diverse or creative results.
   */
  public abstract Optional<Float> getTemperature();

  /**
   * Tokens are selected from the most to least probable until the sum of their probabilities equals
   * this value. Use a lower value for less random responses and a higher value for more random
   * responses.
   */
  public abstract Optional<Float> getTopP();

  /**
   * For each token selection step, the ``top_k`` tokens with the highest probabilities are sampled.
   * Then tokens are further filtered based on ``top_p`` with the final token selected using
   * temperature sampling. Use a lower number for less random responses and a higher number for more
   * random responses.
   */
  public abstract Optional<Float> getTopK();

  /** Number of response variations to return. */
  public abstract Optional<Integer> getCandidateCount();

  /** Maximum number of tokens that can be generated in the response. */
  public abstract Optional<Integer> getMaxOutputTokens();

  /**
   * List of strings that tells the model to stop generating text if one of the strings is
   * encountered in the response.
   */
  public abstract Optional<List<String>> getStopSequences();

  /**
   * Whether to return the log probabilities of the tokens that were chosen by the model at each
   * step.
   */
  public abstract Optional<Boolean> getResponseLogprobs();

  /** Number of top candidate tokens to return the log probabilities for at each generation step. */
  public abstract Optional<Integer> getLogprobs();

  /**
   * Positive values penalize tokens that already appear in the generated text, increasing the
   * probability of generating more diverse content.
   */
  public abstract Optional<Float> getPresencePenalty();

  /**
   * Positive values penalize tokens that repeatedly appear in the generated text, increasing the
   * probability of generating more diverse content.
   */
  public abstract Optional<Float> getFrequencyPenalty();

  /**
   * When ``seed`` is fixed to a specific number, the model makes a best effort to provide the same
   * response for repeated requests. By default, a random number is used.
   */
  public abstract Optional<Integer> getSeed();

  /** Output response media type of the generated candidate text. */
  public abstract Optional<String> getResponseMimeType();

  /** Schema that the generated candidate text must adhere to. */
  public abstract Optional<Schema> getResponseSchema();

  /** Configuration for model router requests. */
  public abstract Optional<GenerationConfigRoutingConfig> getRoutingConfig();

  /** Safety settings in the request to block unsafe content in the response. */
  public abstract Optional<List<SafetySetting>> getSafetySettings();

  /**
   * Code that enables the system to interact with external systems to perform an action outside of
   * the knowledge and scope of the model.
   */
  public abstract Optional<List<Tool>> getTools();

  /** Associates model output to a specific function call. */
  public abstract Optional<ToolConfig> getToolConfig();

  /** Resource name of a context cache that can be used in subsequent requests. */
  public abstract Optional<String> getCachedContent();

  /**
   * The requested modalities of the response. Represents the set of modalities that the model can
   * return.
   */
  public abstract Optional<List<String>> getResponseModalities();

  /** If specified, the media resolution specified will be used. */
  public abstract Optional<String> getMediaResolution();

  /** The speech generation configuration. */
  public abstract Optional<SpeechConfig> getSpeechConfig();

  /** If enabled, audio timestamp will be included in the request to the model. */
  public abstract Optional<Boolean> getAudioTimestamp();

  /** The thinking features configuration. */
  public abstract Optional<ThinkingConfig> getThinkingConfig();

  /** Instantiates a builder for GenerateContentConfig. */
  public static Builder builder() {
    return new AutoValue_GenerateContentConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GenerateContentConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("systemInstruction")
    public abstract Builder setSystemInstruction(Content systemInstruction);

    @JsonProperty("temperature")
    public abstract Builder setTemperature(Float temperature);

    @JsonProperty("topP")
    public abstract Builder setTopP(Float topP);

    @JsonProperty("topK")
    public abstract Builder setTopK(Float topK);

    @JsonProperty("candidateCount")
    public abstract Builder setCandidateCount(Integer candidateCount);

    @JsonProperty("maxOutputTokens")
    public abstract Builder setMaxOutputTokens(Integer maxOutputTokens);

    @JsonProperty("stopSequences")
    public abstract Builder setStopSequences(List<String> stopSequences);

    @JsonProperty("responseLogprobs")
    public abstract Builder setResponseLogprobs(boolean responseLogprobs);

    @JsonProperty("logprobs")
    public abstract Builder setLogprobs(Integer logprobs);

    @JsonProperty("presencePenalty")
    public abstract Builder setPresencePenalty(Float presencePenalty);

    @JsonProperty("frequencyPenalty")
    public abstract Builder setFrequencyPenalty(Float frequencyPenalty);

    @JsonProperty("seed")
    public abstract Builder setSeed(Integer seed);

    @JsonProperty("responseMimeType")
    public abstract Builder setResponseMimeType(String responseMimeType);

    @JsonProperty("responseSchema")
    public abstract Builder setResponseSchema(Schema responseSchema);

    @JsonProperty("routingConfig")
    public abstract Builder setRoutingConfig(GenerationConfigRoutingConfig routingConfig);

    @JsonProperty("safetySettings")
    public abstract Builder setSafetySettings(List<SafetySetting> safetySettings);

    @JsonProperty("tools")
    public abstract Builder setTools(List<Tool> tools);

    @JsonProperty("toolConfig")
    public abstract Builder setToolConfig(ToolConfig toolConfig);

    @JsonProperty("cachedContent")
    public abstract Builder setCachedContent(String cachedContent);

    @JsonProperty("responseModalities")
    public abstract Builder setResponseModalities(List<String> responseModalities);

    @JsonProperty("mediaResolution")
    public abstract Builder setMediaResolution(String mediaResolution);

    @JsonProperty("speechConfig")
    public abstract Builder setSpeechConfig(SpeechConfig speechConfig);

    @JsonProperty("audioTimestamp")
    public abstract Builder setAudioTimestamp(boolean audioTimestamp);

    @JsonProperty("thinkingConfig")
    public abstract Builder setThinkingConfig(ThinkingConfig thinkingConfig);

    public abstract GenerateContentConfig build();
  }

  /** Deserializes a JSON string to a GenerateContentConfig object. */
  public static GenerateContentConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GenerateContentConfig.class);
  }
}
