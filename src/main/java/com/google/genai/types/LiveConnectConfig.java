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

/** Session config for the API connection. */
@AutoValue
@JsonDeserialize(builder = LiveConnectConfig.Builder.class)
public abstract class LiveConnectConfig extends JsonSerializable {
  /** Used to override HTTP request options. */
  @JsonProperty("httpOptions")
  public abstract Optional<HttpOptions> httpOptions();

  /**
   * The requested modalities of the response. Represents the set of modalities that the model can
   * return. Defaults to AUDIO if not specified.
   */
  @JsonProperty("responseModalities")
  public abstract Optional<List<Modality>> responseModalities();

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

  /** Maximum number of tokens that can be generated in the response. */
  @JsonProperty("maxOutputTokens")
  public abstract Optional<Integer> maxOutputTokens();

  /** If specified, the media resolution specified will be used. */
  @JsonProperty("mediaResolution")
  public abstract Optional<MediaResolution> mediaResolution();

  /**
   * When ``seed`` is fixed to a specific number, the model makes a best effort to provide the same
   * response for repeated requests. By default, a random number is used.
   */
  @JsonProperty("seed")
  public abstract Optional<Integer> seed();

  /** The speech generation configuration. */
  @JsonProperty("speechConfig")
  public abstract Optional<SpeechConfig> speechConfig();

  /**
   * Config for thinking features. An error will be returned if this field is set for models that
   * don't support thinking.
   */
  @JsonProperty("thinkingConfig")
  public abstract Optional<ThinkingConfig> thinkingConfig();

  /** If enabled, the model will detect emotions and adapt its responses accordingly. */
  @JsonProperty("enableAffectiveDialog")
  public abstract Optional<Boolean> enableAffectiveDialog();

  /**
   * The user provided system instructions for the model. Note: only text should be used in parts
   * and content in each part will be in a separate paragraph.
   */
  @JsonProperty("systemInstruction")
  public abstract Optional<Content> systemInstruction();

  /**
   * A list of `Tools` the model may use to generate the next response.
   *
   * <p>A `Tool` is a piece of code that enables the system to interact with external systems to
   * perform an action, or set of actions, outside of knowledge and scope of the model.
   */
  @JsonProperty("tools")
  public abstract Optional<List<Tool>> tools();

  /**
   * Configures session resumption mechanism.
   *
   * <p>If included the server will send SessionResumptionUpdate messages.
   */
  @JsonProperty("sessionResumption")
  public abstract Optional<SessionResumptionConfig> sessionResumption();

  /** The transcription of the input aligns with the input audio language. */
  @JsonProperty("inputAudioTranscription")
  public abstract Optional<AudioTranscriptionConfig> inputAudioTranscription();

  /**
   * The transcription of the output aligns with the language code specified for the output audio.
   */
  @JsonProperty("outputAudioTranscription")
  public abstract Optional<AudioTranscriptionConfig> outputAudioTranscription();

  /** Configures the realtime input behavior in BidiGenerateContent. */
  @JsonProperty("realtimeInputConfig")
  public abstract Optional<RealtimeInputConfig> realtimeInputConfig();

  /**
   * Configures context window compression mechanism.
   *
   * <p>If included, server will compress context window to fit into given length.
   */
  @JsonProperty("contextWindowCompression")
  public abstract Optional<ContextWindowCompressionConfig> contextWindowCompression();

  /**
   * Configures the proactivity of the model. This allows the model to respond proactively to the
   * input and to ignore irrelevant input.
   */
  @JsonProperty("proactivity")
  public abstract Optional<ProactivityConfig> proactivity();

  /** Instantiates a builder for LiveConnectConfig. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_LiveConnectConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for LiveConnectConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `LiveConnectConfig.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_LiveConnectConfig.Builder();
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
    public Builder httpOptions(HttpOptions.Builder httpOptionsBuilder) {
      return httpOptions(httpOptionsBuilder.build());
    }

    /**
     * Setter for responseModalities.
     *
     * <p>responseModalities: The requested modalities of the response. Represents the set of
     * modalities that the model can return. Defaults to AUDIO if not specified.
     */
    @JsonProperty("responseModalities")
    public abstract Builder responseModalities(List<Modality> responseModalities);

    /**
     * Setter for responseModalities.
     *
     * <p>responseModalities: The requested modalities of the response. Represents the set of
     * modalities that the model can return. Defaults to AUDIO if not specified.
     */
    public Builder responseModalities(Modality... responseModalities) {
      return responseModalities(Arrays.asList(responseModalities));
    }

    /**
     * Setter for responseModalities given a varargs of strings.
     *
     * <p>responseModalities: The requested modalities of the response. Represents the set of
     * modalities that the model can return. Defaults to AUDIO if not specified.
     */
    @CanIgnoreReturnValue
    public Builder responseModalities(String... responseModalities) {
      return responseModalitiesFromString(Arrays.asList(responseModalities));
    }

    /**
     * Setter for responseModalities given a varargs of known enums.
     *
     * <p>responseModalities: The requested modalities of the response. Represents the set of
     * modalities that the model can return. Defaults to AUDIO if not specified.
     */
    @CanIgnoreReturnValue
    public Builder responseModalities(Modality.Known... knownTypes) {
      return responseModalitiesFromKnown(Arrays.asList(knownTypes));
    }

    /**
     * Setter for responseModalities given a list of known enums.
     *
     * <p>responseModalities: The requested modalities of the response. Represents the set of
     * modalities that the model can return. Defaults to AUDIO if not specified.
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
     * <p>responseModalities: The requested modalities of the response. Represents the set of
     * modalities that the model can return. Defaults to AUDIO if not specified.
     */
    @CanIgnoreReturnValue
    public Builder responseModalitiesFromString(List<String> responseModalities) {
      ImmutableList<Modality> listItems =
          responseModalities.stream().map(Modality::new).collect(toImmutableList());
      return responseModalities(listItems);
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

    /**
     * Setter for topP.
     *
     * <p>topP: Tokens are selected from the most to least probable until the sum of their
     * probabilities equals this value. Use a lower value for less random responses and a higher
     * value for more random responses.
     */
    @JsonProperty("topP")
    public abstract Builder topP(Float topP);

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

    /**
     * Setter for maxOutputTokens.
     *
     * <p>maxOutputTokens: Maximum number of tokens that can be generated in the response.
     */
    @JsonProperty("maxOutputTokens")
    public abstract Builder maxOutputTokens(Integer maxOutputTokens);

    /**
     * Setter for mediaResolution.
     *
     * <p>mediaResolution: If specified, the media resolution specified will be used.
     */
    @JsonProperty("mediaResolution")
    public abstract Builder mediaResolution(MediaResolution mediaResolution);

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
     * Setter for seed.
     *
     * <p>seed: When ``seed`` is fixed to a specific number, the model makes a best effort to
     * provide the same response for repeated requests. By default, a random number is used.
     */
    @JsonProperty("seed")
    public abstract Builder seed(Integer seed);

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
    public Builder speechConfig(SpeechConfig.Builder speechConfigBuilder) {
      return speechConfig(speechConfigBuilder.build());
    }

    /**
     * Setter for thinkingConfig.
     *
     * <p>thinkingConfig: Config for thinking features. An error will be returned if this field is
     * set for models that don't support thinking.
     */
    @JsonProperty("thinkingConfig")
    public abstract Builder thinkingConfig(ThinkingConfig thinkingConfig);

    /**
     * Setter for thinkingConfig builder.
     *
     * <p>thinkingConfig: Config for thinking features. An error will be returned if this field is
     * set for models that don't support thinking.
     */
    public Builder thinkingConfig(ThinkingConfig.Builder thinkingConfigBuilder) {
      return thinkingConfig(thinkingConfigBuilder.build());
    }

    /**
     * Setter for enableAffectiveDialog.
     *
     * <p>enableAffectiveDialog: If enabled, the model will detect emotions and adapt its responses
     * accordingly.
     */
    @JsonProperty("enableAffectiveDialog")
    public abstract Builder enableAffectiveDialog(boolean enableAffectiveDialog);

    /**
     * Setter for systemInstruction.
     *
     * <p>systemInstruction: The user provided system instructions for the model. Note: only text
     * should be used in parts and content in each part will be in a separate paragraph.
     */
    @JsonProperty("systemInstruction")
    public abstract Builder systemInstruction(Content systemInstruction);

    /**
     * Setter for systemInstruction builder.
     *
     * <p>systemInstruction: The user provided system instructions for the model. Note: only text
     * should be used in parts and content in each part will be in a separate paragraph.
     */
    public Builder systemInstruction(Content.Builder systemInstructionBuilder) {
      return systemInstruction(systemInstructionBuilder.build());
    }

    /**
     * Setter for tools.
     *
     * <p>tools: A list of `Tools` the model may use to generate the next response.
     *
     * <p>A `Tool` is a piece of code that enables the system to interact with external systems to
     * perform an action, or set of actions, outside of knowledge and scope of the model.
     */
    @JsonProperty("tools")
    public abstract Builder tools(List<Tool> tools);

    /**
     * Setter for tools.
     *
     * <p>tools: A list of `Tools` the model may use to generate the next response.
     *
     * <p>A `Tool` is a piece of code that enables the system to interact with external systems to
     * perform an action, or set of actions, outside of knowledge and scope of the model.
     */
    public Builder tools(Tool... tools) {
      return tools(Arrays.asList(tools));
    }

    /**
     * Setter for tools builder.
     *
     * <p>tools: A list of `Tools` the model may use to generate the next response.
     *
     * <p>A `Tool` is a piece of code that enables the system to interact with external systems to
     * perform an action, or set of actions, outside of knowledge and scope of the model.
     */
    public Builder tools(Tool.Builder... toolsBuilders) {
      return tools(
          Arrays.asList(toolsBuilders).stream()
              .map(Tool.Builder::build)
              .collect(toImmutableList()));
    }

    /**
     * Setter for sessionResumption.
     *
     * <p>sessionResumption: Configures session resumption mechanism.
     *
     * <p>If included the server will send SessionResumptionUpdate messages.
     */
    @JsonProperty("sessionResumption")
    public abstract Builder sessionResumption(SessionResumptionConfig sessionResumption);

    /**
     * Setter for sessionResumption builder.
     *
     * <p>sessionResumption: Configures session resumption mechanism.
     *
     * <p>If included the server will send SessionResumptionUpdate messages.
     */
    public Builder sessionResumption(SessionResumptionConfig.Builder sessionResumptionBuilder) {
      return sessionResumption(sessionResumptionBuilder.build());
    }

    /**
     * Setter for inputAudioTranscription.
     *
     * <p>inputAudioTranscription: The transcription of the input aligns with the input audio
     * language.
     */
    @JsonProperty("inputAudioTranscription")
    public abstract Builder inputAudioTranscription(
        AudioTranscriptionConfig inputAudioTranscription);

    /**
     * Setter for inputAudioTranscription builder.
     *
     * <p>inputAudioTranscription: The transcription of the input aligns with the input audio
     * language.
     */
    public Builder inputAudioTranscription(
        AudioTranscriptionConfig.Builder inputAudioTranscriptionBuilder) {
      return inputAudioTranscription(inputAudioTranscriptionBuilder.build());
    }

    /**
     * Setter for outputAudioTranscription.
     *
     * <p>outputAudioTranscription: The transcription of the output aligns with the language code
     * specified for the output audio.
     */
    @JsonProperty("outputAudioTranscription")
    public abstract Builder outputAudioTranscription(
        AudioTranscriptionConfig outputAudioTranscription);

    /**
     * Setter for outputAudioTranscription builder.
     *
     * <p>outputAudioTranscription: The transcription of the output aligns with the language code
     * specified for the output audio.
     */
    public Builder outputAudioTranscription(
        AudioTranscriptionConfig.Builder outputAudioTranscriptionBuilder) {
      return outputAudioTranscription(outputAudioTranscriptionBuilder.build());
    }

    /**
     * Setter for realtimeInputConfig.
     *
     * <p>realtimeInputConfig: Configures the realtime input behavior in BidiGenerateContent.
     */
    @JsonProperty("realtimeInputConfig")
    public abstract Builder realtimeInputConfig(RealtimeInputConfig realtimeInputConfig);

    /**
     * Setter for realtimeInputConfig builder.
     *
     * <p>realtimeInputConfig: Configures the realtime input behavior in BidiGenerateContent.
     */
    public Builder realtimeInputConfig(RealtimeInputConfig.Builder realtimeInputConfigBuilder) {
      return realtimeInputConfig(realtimeInputConfigBuilder.build());
    }

    /**
     * Setter for contextWindowCompression.
     *
     * <p>contextWindowCompression: Configures context window compression mechanism.
     *
     * <p>If included, server will compress context window to fit into given length.
     */
    @JsonProperty("contextWindowCompression")
    public abstract Builder contextWindowCompression(
        ContextWindowCompressionConfig contextWindowCompression);

    /**
     * Setter for contextWindowCompression builder.
     *
     * <p>contextWindowCompression: Configures context window compression mechanism.
     *
     * <p>If included, server will compress context window to fit into given length.
     */
    public Builder contextWindowCompression(
        ContextWindowCompressionConfig.Builder contextWindowCompressionBuilder) {
      return contextWindowCompression(contextWindowCompressionBuilder.build());
    }

    /**
     * Setter for proactivity.
     *
     * <p>proactivity: Configures the proactivity of the model. This allows the model to respond
     * proactively to the input and to ignore irrelevant input.
     */
    @JsonProperty("proactivity")
    public abstract Builder proactivity(ProactivityConfig proactivity);

    /**
     * Setter for proactivity builder.
     *
     * <p>proactivity: Configures the proactivity of the model. This allows the model to respond
     * proactively to the input and to ignore irrelevant input.
     */
    public Builder proactivity(ProactivityConfig.Builder proactivityBuilder) {
      return proactivity(proactivityBuilder.build());
    }

    public abstract LiveConnectConfig build();
  }

  /** Deserializes a JSON string to a LiveConnectConfig object. */
  @ExcludeFromGeneratedCoverageReport
  public static LiveConnectConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, LiveConnectConfig.class);
  }
}
