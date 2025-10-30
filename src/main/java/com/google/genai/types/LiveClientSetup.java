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
import com.google.genai.JsonSerializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/** Message contains configuration that will apply for the duration of the streaming session. */
@AutoValue
@JsonDeserialize(builder = LiveClientSetup.Builder.class)
public abstract class LiveClientSetup extends JsonSerializable {
  /** The fully qualified name of the publisher model or tuned model endpoint to use. */
  @JsonProperty("model")
  public abstract Optional<String> model();

  /** The generation configuration for the session. Note: only a subset of fields are supported. */
  @JsonProperty("generationConfig")
  public abstract Optional<GenerationConfig> generationConfig();

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

  /** Configures the realtime input behavior in BidiGenerateContent. */
  @JsonProperty("realtimeInputConfig")
  public abstract Optional<RealtimeInputConfig> realtimeInputConfig();

  /**
   * Configures session resumption mechanism.
   *
   * <p>If included server will send SessionResumptionUpdate messages.
   */
  @JsonProperty("sessionResumption")
  public abstract Optional<SessionResumptionConfig> sessionResumption();

  /**
   * Configures context window compression mechanism.
   *
   * <p>If included, server will compress context window to fit into given length.
   */
  @JsonProperty("contextWindowCompression")
  public abstract Optional<ContextWindowCompressionConfig> contextWindowCompression();

  /** The transcription of the input aligns with the input audio language. */
  @JsonProperty("inputAudioTranscription")
  public abstract Optional<AudioTranscriptionConfig> inputAudioTranscription();

  /**
   * The transcription of the output aligns with the language code specified for the output audio.
   */
  @JsonProperty("outputAudioTranscription")
  public abstract Optional<AudioTranscriptionConfig> outputAudioTranscription();

  /**
   * Configures the proactivity of the model. This allows the model to respond proactively to the
   * input and to ignore irrelevant input.
   */
  @JsonProperty("proactivity")
  public abstract Optional<ProactivityConfig> proactivity();

  /** Instantiates a builder for LiveClientSetup. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_LiveClientSetup.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for LiveClientSetup. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `LiveClientSetup.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_LiveClientSetup.Builder();
    }

    /**
     * Setter for model.
     *
     * <p>model: The fully qualified name of the publisher model or tuned model endpoint to use.
     */
    @JsonProperty("model")
    public abstract Builder model(String model);

    /**
     * Setter for generationConfig.
     *
     * <p>generationConfig: The generation configuration for the session. Note: only a subset of
     * fields are supported.
     */
    @JsonProperty("generationConfig")
    public abstract Builder generationConfig(GenerationConfig generationConfig);

    /**
     * Setter for generationConfig builder.
     *
     * <p>generationConfig: The generation configuration for the session. Note: only a subset of
     * fields are supported.
     */
    public Builder generationConfig(GenerationConfig.Builder generationConfigBuilder) {
      return generationConfig(generationConfigBuilder.build());
    }

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
     * Setter for sessionResumption.
     *
     * <p>sessionResumption: Configures session resumption mechanism.
     *
     * <p>If included server will send SessionResumptionUpdate messages.
     */
    @JsonProperty("sessionResumption")
    public abstract Builder sessionResumption(SessionResumptionConfig sessionResumption);

    /**
     * Setter for sessionResumption builder.
     *
     * <p>sessionResumption: Configures session resumption mechanism.
     *
     * <p>If included server will send SessionResumptionUpdate messages.
     */
    public Builder sessionResumption(SessionResumptionConfig.Builder sessionResumptionBuilder) {
      return sessionResumption(sessionResumptionBuilder.build());
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

    public abstract LiveClientSetup build();
  }

  /** Deserializes a JSON string to a LiveClientSetup object. */
  @ExcludeFromGeneratedCoverageReport
  public static LiveClientSetup fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, LiveClientSetup.class);
  }
}
