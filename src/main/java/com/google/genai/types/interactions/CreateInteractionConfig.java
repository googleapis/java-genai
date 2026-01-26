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

package com.google.genai.types.interactions;

import static com.google.common.collect.ImmutableList.toImmutableList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableList;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import com.google.genai.types.interactions.AgentConfig;
import com.google.genai.types.ExcludeFromGeneratedCoverageReport;
import com.google.genai.types.interactions.content.Content;
import com.google.genai.types.interactions.tools.Tool;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Configuration for creating an interaction.
 *
 * <p>The Interactions API is available in both Vertex AI and Gemini API.
 *
 * <p><b>When to use CreateInteractionConfig:</b>
 *
 * <p>For simple use cases, prefer the convenience methods:
 *
 * <ul>
 *   <li>{@code client.interactions.create(model, text)} - simple text prompt
 *   <li>{@code client.interactions.create(model, Input.fromContents(...))} - multimodal content
 * </ul>
 *
 * <p>Use CreateInteractionConfig when you need:
 *
 * <ul>
 *   <li>{@link #tools} - Google Search, Code Execution, Function Calling, etc.
 *   <li>{@link #systemInstruction} - custom system prompts
 *   <li>{@link #generationConfig} - thinking mode, temperature, etc.
 *   <li>{@link #previousInteractionId} - multi-turn conversation continuity
 *   <li>{@link #responseModalities} - request specific output types (text, image, audio)
 * </ul>
 *
 * <p>Note: The Interactions API is in beta and subject to change.
 *
 * @see com.google.genai.Interactions#create(CreateInteractionConfig)
 */
@AutoValue
@JsonDeserialize(builder = CreateInteractionConfig.Builder.class)
public abstract class CreateInteractionConfig extends JsonSerializable {
  /** Optional API version to use for this request. Overrides client-level api_version. */
  @JsonProperty("api_version")
  public abstract Optional<String> apiVersion();

  /** Required: The input for the interaction. */
  @JsonProperty("input")
  public abstract Input input();

  /** The model to use for the interaction. Either model or agent must be specified. */
  @JsonProperty("model")
  public abstract Optional<String> model();

  /** The agent to use for the interaction. Either model or agent must be specified. */
  @JsonProperty("agent")
  public abstract Optional<String> agent();

  /** Whether to run the interaction in the background. */
  @JsonProperty("background")
  public abstract Optional<Boolean> background();

  /** Whether to stream the interaction response. */
  @JsonProperty("stream")
  public abstract Optional<Boolean> stream();

  /** Configuration for generation (only used with model-based interactions). */
  @JsonProperty("generation_config")
  public abstract Optional<GenerationConfig> generationConfig();

  /** Configuration for the agent (only used with agent-based interactions). */
  @JsonProperty("agent_config")
  public abstract Optional<AgentConfig> agentConfig();

  /** The ID of the previous interaction for conversation continuity. */
  @JsonProperty("previous_interaction_id")
  public abstract Optional<String> previousInteractionId();

  /** The expected response format. */
  @JsonProperty("response_format")
  public abstract Optional<Object> responseFormat();

  /** The MIME type for the response. */
  @JsonProperty("response_mime_type")
  public abstract Optional<String> responseMimeType();

  /**
   * The requested modalities of the response.
   *
   * <p>Represents the set of modalities that the model can return.
   *
   * <p>Supported values:
   *
   * <ul>
   *   <li>{@link ResponseModality.Known#TEXT} - Text content
   *   <li>{@link ResponseModality.Known#IMAGE} - Image content
   *   <li>{@link ResponseModality.Known#AUDIO} - Audio content
   * </ul>
   */
  @JsonProperty("response_modalities")
  public abstract Optional<List<ResponseModality>> responseModalities();

  /** Whether to store the interaction history. */
  @JsonProperty("store")
  public abstract Optional<Boolean> store();

  /** Developer set system instruction. */
  @JsonProperty("system_instruction")
  public abstract Optional<String> systemInstruction();

  /**
   * A list of tools the model may use to generate the next response.
   *
   * <p>Use the dedicated Interactions tool types such as {@code Function}, {@code GoogleSearch},
   * {@code CodeExecution}, etc.
   */
  @JsonProperty("tools")
  public abstract Optional<List<Tool>> tools();

  /** Instantiates a builder for CreateInteractionConfig. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_CreateInteractionConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for CreateInteractionConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `CreateInteractionConfig.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_CreateInteractionConfig.Builder();
    }

    /**
     * Setter for api_version.
     *
     * <p>api_version: Optional API version to use for this request.
     */
    @JsonProperty("api_version")
    public abstract Builder apiVersion(String apiVersion);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder apiVersion(Optional<String> apiVersion);

    /** Clears the value of api_version field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearApiVersion() {
      return apiVersion(Optional.empty());
    }

    /**
     * Setter for input.
     *
     * <p>input: The input for the interaction.
     */
    @JsonProperty("input")
    public abstract Builder input(Input input);

    /**
     * Convenience setter for input from a string.
     *
     * <p>input: The input text for the interaction.
     */
    @CanIgnoreReturnValue
    public Builder input(String text) {
      return input(Input.fromString(text));
    }

    /**
     * Convenience setter for input from a list of Content.
     *
     * <p>input: The input content for the interaction.
     */
    @CanIgnoreReturnValue
    public Builder inputFromContents(List<Content> contents) {
      return input(Input.fromContents(contents));
    }

    /**
     * Convenience setter for input from Content objects (varargs).
     *
     * <p>input: The input content for the interaction.
     */
    @CanIgnoreReturnValue
    public Builder inputFromContents(Content... contents) {
      return input(Input.fromContents(contents));
    }

    /**
     * Convenience setter for input from a list of Turn.
     *
     * <p>input: The input turns for the interaction.
     */
    @CanIgnoreReturnValue
    public Builder inputFromTurns(List<Turn> turns) {
      return input(Input.fromTurns(turns));
    }

    /**
     * Convenience setter for input from Turn objects (varargs).
     *
     * <p>input: The input turns for the interaction.
     */
    @CanIgnoreReturnValue
    public Builder inputFromTurns(Turn... turns) {
      return input(Input.fromTurns(turns));
    }

    /**
     * Setter for model.
     *
     * <p>model: The model to use for the interaction.
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
     * Setter for agent.
     *
     * <p>agent: The agent to use for the interaction.
     */
    @JsonProperty("agent")
    public abstract Builder agent(String agent);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder agent(Optional<String> agent);

    /** Clears the value of agent field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearAgent() {
      return agent(Optional.empty());
    }

    /**
     * Setter for background.
     *
     * <p>background: Whether to run the interaction in the background.
     */
    @JsonProperty("background")
    public abstract Builder background(Boolean background);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder background(Optional<Boolean> background);

    /** Clears the value of background field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearBackground() {
      return background(Optional.empty());
    }

    /**
     * Setter for stream.
     *
     * <p>stream: Whether to stream the interaction response.
     */
    @JsonProperty("stream")
    public abstract Builder stream(Boolean stream);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder stream(Optional<Boolean> stream);

    /** Clears the value of stream field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearStream() {
      return stream(Optional.empty());
    }

    /**
     * Setter for generationConfig.
     *
     * <p>generationConfig: Configuration for generation.
     */
    @JsonProperty("generation_config")
    public abstract Builder generationConfig(GenerationConfig generationConfig);

    /**
     * Setter for generationConfig builder.
     *
     * <p>generationConfig: Configuration for generation.
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
     * Setter for agentConfig.
     *
     * <p>agentConfig: Configuration for the agent.
     */
    @JsonProperty("agent_config")
    public abstract Builder agentConfig(AgentConfig agentConfig);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder agentConfig(Optional<AgentConfig> agentConfig);

    /** Clears the value of agentConfig field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearAgentConfig() {
      return agentConfig(Optional.empty());
    }

    /**
     * Setter for previousInteractionId.
     *
     * <p>previousInteractionId: The ID of the previous interaction.
     */
    @JsonProperty("previous_interaction_id")
    public abstract Builder previousInteractionId(String previousInteractionId);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder previousInteractionId(Optional<String> previousInteractionId);

    /** Clears the value of previousInteractionId field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearPreviousInteractionId() {
      return previousInteractionId(Optional.empty());
    }

    /**
     * Setter for responseFormat.
     *
     * <p>responseFormat: The expected response format.
     */
    @JsonProperty("response_format")
    public abstract Builder responseFormat(Object responseFormat);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder responseFormat(Optional<Object> responseFormat);

    /** Clears the value of responseFormat field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearResponseFormat() {
      return responseFormat(Optional.empty());
    }

    /**
     * Setter for responseMimeType.
     *
     * <p>responseMimeType: The MIME type for the response.
     */
    @JsonProperty("response_mime_type")
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
     * <p>responseModalities: The modalities for the response.
     */
    @JsonProperty("response_modalities")
    public abstract Builder responseModalities(List<ResponseModality> responseModalities);

    /**
     * Setter for responseModalities.
     *
     * <p>responseModalities: The modalities for the response.
     */
    public Builder responseModalities(ResponseModality... responseModalities) {
      return responseModalities(Arrays.asList(responseModalities));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder responseModalities(Optional<List<ResponseModality>> responseModalities);

    /** Clears the value of responseModalities field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearResponseModalities() {
      return responseModalities(Optional.empty());
    }

    /**
     * Setter for responseModalities given a varargs of strings.
     *
     * <p>responseModalities: The modalities for the response.
     */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder responseModalities(String... responseModalities) {
      return responseModalitiesFromString(Arrays.asList(responseModalities));
    }

    /**
     * Setter for responseModalities given a varargs of known enums.
     *
     * <p>responseModalities: The modalities for the response.
     */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder responseModalities(ResponseModality.Known... knownTypes) {
      return responseModalitiesFromKnown(Arrays.asList(knownTypes));
    }

    /**
     * Setter for responseModalities given a list of known enums.
     *
     * <p>responseModalities: The modalities for the response.
     */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder responseModalitiesFromKnown(List<ResponseModality.Known> knownTypes) {
      ImmutableList<ResponseModality> listItems =
          knownTypes.stream().map(ResponseModality::new).collect(toImmutableList());
      return responseModalities(listItems);
    }

    /**
     * Setter for responseModalities given a list of strings.
     *
     * <p>responseModalities: The modalities for the response.
     */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder responseModalitiesFromString(List<String> responseModalities) {
      ImmutableList<ResponseModality> listItems =
          responseModalities.stream().map(ResponseModality::new).collect(toImmutableList());
      return responseModalities(listItems);
    }

    /**
     * Setter for store.
     *
     * <p>store: Whether to store the interaction history.
     */
    @JsonProperty("store")
    public abstract Builder store(Boolean store);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder store(Optional<Boolean> store);

    /** Clears the value of store field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearStore() {
      return store(Optional.empty());
    }

    /**
     * Setter for systemInstruction.
     *
     * <p>systemInstruction: Developer set system instruction.
     */
    @JsonProperty("system_instruction")
    public abstract Builder systemInstruction(String systemInstruction);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder systemInstruction(Optional<String> systemInstruction);

    /** Clears the value of systemInstruction field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearSystemInstruction() {
      return systemInstruction(Optional.empty());
    }

    /**
     * Setter for tools.
     *
     * <p>tools: A list of tools the model may use to generate the next response. Use the dedicated
     * Interactions tool types such as {@code FunctionTool}, {@code GoogleSearchTool}, etc.
     */
    @JsonProperty("tools")
    public abstract Builder tools(List<Tool> tools);

    /**
     * Setter for tools (varargs convenience method).
     *
     * <p>tools: A list of tools the model may use to generate the next response.
     */
    @CanIgnoreReturnValue
    public Builder tools(Tool... tools) {
      return tools(Arrays.asList(tools));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder tools(Optional<List<Tool>> tools);

    /** Clears the value of tools field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTools() {
      return tools(Optional.empty());
    }

    /** Builds the CreateInteractionConfig instance. */
    public abstract CreateInteractionConfig build();
  }

  /** Deserializes a CreateInteractionConfig from a JSON string. */
  @ExcludeFromGeneratedCoverageReport
  public static CreateInteractionConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, CreateInteractionConfig.class);
  }
}
