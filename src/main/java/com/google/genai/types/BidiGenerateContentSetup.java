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
import java.util.Optional;

/**
 * Message to be sent in the first (and only in the first) `BidiGenerateContentClientMessage`.
 * Contains configuration that will apply for the duration of the streaming RPC. Clients should wait
 * for a `BidiGenerateContentSetupComplete` message before sending any additional messages. This
 * data type is not supported in Vertex AI.
 */
@AutoValue
@JsonDeserialize(builder = BidiGenerateContentSetup.Builder.class)
public abstract class BidiGenerateContentSetup extends JsonSerializable {
  /**
   * Optional. Configures a context window compression mechanism. If included, the server will
   * automatically reduce the size of the context when it exceeds the configured length.
   */
  @JsonProperty("contextWindowCompression")
  public abstract Optional<ContextWindowCompressionConfig> contextWindowCompression();

  /**
   * Optional. Generation config. The following fields are not supported: - `response_logprobs` -
   * `response_mime_type` - `logprobs` - `response_schema` - `response_json_schema` -
   * `stop_sequence` - `skip_response_cache` - `routing_config` - `audio_timestamp`
   */
  @JsonProperty("generationConfig")
  public abstract Optional<GenerationConfig> generationConfig();

  /** Optional. Configures the exchange of history between the client and the server. */
  @JsonProperty("historyConfig")
  public abstract Optional<HistoryConfig> historyConfig();

  /**
   * Optional. If set, enables transcription of voice input. The transcription aligns with the input
   * audio language, if configured.
   */
  @JsonProperty("inputAudioTranscription")
  public abstract Optional<AudioTranscriptionConfig> inputAudioTranscription();

  /**
   * Required. The model's resource name. This serves as an ID for the Model to use. Format:
   * `models/{model}`
   */
  @JsonProperty("model")
  public abstract Optional<String> model();

  /**
   * Optional. If set, enables transcription of the model's audio output. The transcription aligns
   * with the language code specified for the output audio, if configured.
   */
  @JsonProperty("outputAudioTranscription")
  public abstract Optional<AudioTranscriptionConfig> outputAudioTranscription();

  /** Optional. Configures the handling of realtime input. */
  @JsonProperty("realtimeInputConfig")
  public abstract Optional<RealtimeInputConfig> realtimeInputConfig();

  /**
   * Optional. Configures session resumption mechanism. If included, the server will send
   * `SessionResumptionUpdate` messages.
   */
  @JsonProperty("sessionResumption")
  public abstract Optional<SessionResumptionConfig> sessionResumption();

  /**
   * Optional. The user provided system instructions for the model. Note: Only text should be used
   * in parts and content in each part will be in a separate paragraph.
   */
  @JsonProperty("systemInstruction")
  public abstract Optional<Content> systemInstruction();

  /**
   * Optional. A list of `Tools` the model may use to generate the next response. A `Tool` is a
   * piece of code that enables the system to interact with external systems to perform an action,
   * or set of actions, outside of knowledge and scope of the model.
   */
  @JsonProperty("tools")
  public abstract Optional<List<Tool>> tools();

  /** Instantiates a builder for BidiGenerateContentSetup. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_BidiGenerateContentSetup.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for BidiGenerateContentSetup. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `BidiGenerateContentSetup.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_BidiGenerateContentSetup.Builder();
    }

    /**
     * Setter for contextWindowCompression.
     *
     * <p>contextWindowCompression: Optional. Configures a context window compression mechanism. If
     * included, the server will automatically reduce the size of the context when it exceeds the
     * configured length.
     */
    @JsonProperty("contextWindowCompression")
    public abstract Builder contextWindowCompression(
        ContextWindowCompressionConfig contextWindowCompression);

    /**
     * Setter for contextWindowCompression builder.
     *
     * <p>contextWindowCompression: Optional. Configures a context window compression mechanism. If
     * included, the server will automatically reduce the size of the context when it exceeds the
     * configured length.
     */
    @CanIgnoreReturnValue
    public Builder contextWindowCompression(
        ContextWindowCompressionConfig.Builder contextWindowCompressionBuilder) {
      return contextWindowCompression(contextWindowCompressionBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder contextWindowCompression(
        Optional<ContextWindowCompressionConfig> contextWindowCompression);

    /** Clears the value of contextWindowCompression field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearContextWindowCompression() {
      return contextWindowCompression(Optional.empty());
    }

    /**
     * Setter for generationConfig.
     *
     * <p>generationConfig: Optional. Generation config. The following fields are not supported: -
     * `response_logprobs` - `response_mime_type` - `logprobs` - `response_schema` -
     * `response_json_schema` - `stop_sequence` - `skip_response_cache` - `routing_config` -
     * `audio_timestamp`
     */
    @JsonProperty("generationConfig")
    public abstract Builder generationConfig(GenerationConfig generationConfig);

    /**
     * Setter for generationConfig builder.
     *
     * <p>generationConfig: Optional. Generation config. The following fields are not supported: -
     * `response_logprobs` - `response_mime_type` - `logprobs` - `response_schema` -
     * `response_json_schema` - `stop_sequence` - `skip_response_cache` - `routing_config` -
     * `audio_timestamp`
     */
    @CanIgnoreReturnValue
    public Builder generationConfig(GenerationConfig.Builder generationConfigBuilder) {
      return generationConfig(generationConfigBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder generationConfig(Optional<GenerationConfig> generationConfig);

    /** Clears the value of generationConfig field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearGenerationConfig() {
      return generationConfig(Optional.empty());
    }

    /**
     * Setter for historyConfig.
     *
     * <p>historyConfig: Optional. Configures the exchange of history between the client and the
     * server.
     */
    @JsonProperty("historyConfig")
    public abstract Builder historyConfig(HistoryConfig historyConfig);

    /**
     * Setter for historyConfig builder.
     *
     * <p>historyConfig: Optional. Configures the exchange of history between the client and the
     * server.
     */
    @CanIgnoreReturnValue
    public Builder historyConfig(HistoryConfig.Builder historyConfigBuilder) {
      return historyConfig(historyConfigBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder historyConfig(Optional<HistoryConfig> historyConfig);

    /** Clears the value of historyConfig field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearHistoryConfig() {
      return historyConfig(Optional.empty());
    }

    /**
     * Setter for inputAudioTranscription.
     *
     * <p>inputAudioTranscription: Optional. If set, enables transcription of voice input. The
     * transcription aligns with the input audio language, if configured.
     */
    @JsonProperty("inputAudioTranscription")
    public abstract Builder inputAudioTranscription(
        AudioTranscriptionConfig inputAudioTranscription);

    /**
     * Setter for inputAudioTranscription builder.
     *
     * <p>inputAudioTranscription: Optional. If set, enables transcription of voice input. The
     * transcription aligns with the input audio language, if configured.
     */
    @CanIgnoreReturnValue
    public Builder inputAudioTranscription(
        AudioTranscriptionConfig.Builder inputAudioTranscriptionBuilder) {
      return inputAudioTranscription(inputAudioTranscriptionBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder inputAudioTranscription(
        Optional<AudioTranscriptionConfig> inputAudioTranscription);

    /** Clears the value of inputAudioTranscription field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearInputAudioTranscription() {
      return inputAudioTranscription(Optional.empty());
    }

    /**
     * Setter for model.
     *
     * <p>model: Required. The model's resource name. This serves as an ID for the Model to use.
     * Format: `models/{model}`
     */
    @JsonProperty("model")
    public abstract Builder model(String model);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder model(Optional<String> model);

    /** Clears the value of model field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearModel() {
      return model(Optional.empty());
    }

    /**
     * Setter for outputAudioTranscription.
     *
     * <p>outputAudioTranscription: Optional. If set, enables transcription of the model's audio
     * output. The transcription aligns with the language code specified for the output audio, if
     * configured.
     */
    @JsonProperty("outputAudioTranscription")
    public abstract Builder outputAudioTranscription(
        AudioTranscriptionConfig outputAudioTranscription);

    /**
     * Setter for outputAudioTranscription builder.
     *
     * <p>outputAudioTranscription: Optional. If set, enables transcription of the model's audio
     * output. The transcription aligns with the language code specified for the output audio, if
     * configured.
     */
    @CanIgnoreReturnValue
    public Builder outputAudioTranscription(
        AudioTranscriptionConfig.Builder outputAudioTranscriptionBuilder) {
      return outputAudioTranscription(outputAudioTranscriptionBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder outputAudioTranscription(
        Optional<AudioTranscriptionConfig> outputAudioTranscription);

    /** Clears the value of outputAudioTranscription field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearOutputAudioTranscription() {
      return outputAudioTranscription(Optional.empty());
    }

    /**
     * Setter for realtimeInputConfig.
     *
     * <p>realtimeInputConfig: Optional. Configures the handling of realtime input.
     */
    @JsonProperty("realtimeInputConfig")
    public abstract Builder realtimeInputConfig(RealtimeInputConfig realtimeInputConfig);

    /**
     * Setter for realtimeInputConfig builder.
     *
     * <p>realtimeInputConfig: Optional. Configures the handling of realtime input.
     */
    @CanIgnoreReturnValue
    public Builder realtimeInputConfig(RealtimeInputConfig.Builder realtimeInputConfigBuilder) {
      return realtimeInputConfig(realtimeInputConfigBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder realtimeInputConfig(Optional<RealtimeInputConfig> realtimeInputConfig);

    /** Clears the value of realtimeInputConfig field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearRealtimeInputConfig() {
      return realtimeInputConfig(Optional.empty());
    }

    /**
     * Setter for sessionResumption.
     *
     * <p>sessionResumption: Optional. Configures session resumption mechanism. If included, the
     * server will send `SessionResumptionUpdate` messages.
     */
    @JsonProperty("sessionResumption")
    public abstract Builder sessionResumption(SessionResumptionConfig sessionResumption);

    /**
     * Setter for sessionResumption builder.
     *
     * <p>sessionResumption: Optional. Configures session resumption mechanism. If included, the
     * server will send `SessionResumptionUpdate` messages.
     */
    @CanIgnoreReturnValue
    public Builder sessionResumption(SessionResumptionConfig.Builder sessionResumptionBuilder) {
      return sessionResumption(sessionResumptionBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder sessionResumption(Optional<SessionResumptionConfig> sessionResumption);

    /** Clears the value of sessionResumption field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearSessionResumption() {
      return sessionResumption(Optional.empty());
    }

    /**
     * Setter for systemInstruction.
     *
     * <p>systemInstruction: Optional. The user provided system instructions for the model. Note:
     * Only text should be used in parts and content in each part will be in a separate paragraph.
     */
    @JsonProperty("systemInstruction")
    public abstract Builder systemInstruction(Content systemInstruction);

    /**
     * Setter for systemInstruction builder.
     *
     * <p>systemInstruction: Optional. The user provided system instructions for the model. Note:
     * Only text should be used in parts and content in each part will be in a separate paragraph.
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
     * Setter for tools.
     *
     * <p>tools: Optional. A list of `Tools` the model may use to generate the next response. A
     * `Tool` is a piece of code that enables the system to interact with external systems to
     * perform an action, or set of actions, outside of knowledge and scope of the model.
     */
    @JsonProperty("tools")
    public abstract Builder tools(List<Tool> tools);

    /**
     * Setter for tools.
     *
     * <p>tools: Optional. A list of `Tools` the model may use to generate the next response. A
     * `Tool` is a piece of code that enables the system to interact with external systems to
     * perform an action, or set of actions, outside of knowledge and scope of the model.
     */
    @CanIgnoreReturnValue
    public Builder tools(Tool... tools) {
      return tools(Arrays.asList(tools));
    }

    /**
     * Setter for tools builder.
     *
     * <p>tools: Optional. A list of `Tools` the model may use to generate the next response. A
     * `Tool` is a piece of code that enables the system to interact with external systems to
     * perform an action, or set of actions, outside of knowledge and scope of the model.
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

    public abstract BidiGenerateContentSetup build();
  }

  /** Deserializes a JSON string to a BidiGenerateContentSetup object. */
  @ExcludeFromGeneratedCoverageReport
  public static BidiGenerateContentSetup fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, BidiGenerateContentSetup.class);
  }
}
