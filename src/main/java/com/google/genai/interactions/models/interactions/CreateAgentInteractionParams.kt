/*
* Copyright 2025 Google LLC
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

// File generated from our OpenAPI spec by Stainless.

package com.google.genai.interactions.models.interactions

import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.google.genai.interactions.core.BaseDeserializer
import com.google.genai.interactions.core.BaseSerializer
import com.google.genai.interactions.core.Enum
import com.google.genai.interactions.core.ExcludeMissing
import com.google.genai.interactions.core.JsonField
import com.google.genai.interactions.core.JsonMissing
import com.google.genai.interactions.core.JsonValue
import com.google.genai.interactions.core.allMaxBy
import com.google.genai.interactions.core.checkKnown
import com.google.genai.interactions.core.checkRequired
import com.google.genai.interactions.core.getOrThrow
import com.google.genai.interactions.core.toImmutable
import com.google.genai.interactions.errors.GeminiNextGenApiInvalidDataException
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** Parameters for creating agent interactions */
class CreateAgentInteractionParams
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val agent: JsonField<Agent>,
    private val input: JsonField<Input>,
    private val id: JsonField<String>,
    private val agentConfig: JsonField<AgentConfig>,
    private val background: JsonField<Boolean>,
    private val created: JsonField<OffsetDateTime>,
    private val previousInteractionId: JsonField<String>,
    private val responseFormat: JsonField<ResponseFormat>,
    private val responseMimeType: JsonField<String>,
    private val responseModalities: JsonField<List<ResponseModality>>,
    private val role: JsonField<String>,
    private val serviceTier: JsonField<ServiceTier>,
    private val status: JsonField<Status>,
    private val steps: JsonField<List<Step>>,
    private val store: JsonField<Boolean>,
    private val stream: JsonField<Boolean>,
    private val systemInstruction: JsonField<String>,
    private val tools: JsonField<List<Tool>>,
    private val updated: JsonField<OffsetDateTime>,
    private val usage: JsonField<Usage>,
    private val webhookConfig: JsonField<WebhookConfig>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("agent") @ExcludeMissing agent: JsonField<Agent> = JsonMissing.of(),
        @JsonProperty("input") @ExcludeMissing input: JsonField<Input> = JsonMissing.of(),
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("agent_config")
        @ExcludeMissing
        agentConfig: JsonField<AgentConfig> = JsonMissing.of(),
        @JsonProperty("background")
        @ExcludeMissing
        background: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("created")
        @ExcludeMissing
        created: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("previous_interaction_id")
        @ExcludeMissing
        previousInteractionId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("response_format")
        @ExcludeMissing
        responseFormat: JsonField<ResponseFormat> = JsonMissing.of(),
        @JsonProperty("response_mime_type")
        @ExcludeMissing
        responseMimeType: JsonField<String> = JsonMissing.of(),
        @JsonProperty("response_modalities")
        @ExcludeMissing
        responseModalities: JsonField<List<ResponseModality>> = JsonMissing.of(),
        @JsonProperty("role") @ExcludeMissing role: JsonField<String> = JsonMissing.of(),
        @JsonProperty("service_tier")
        @ExcludeMissing
        serviceTier: JsonField<ServiceTier> = JsonMissing.of(),
        @JsonProperty("status") @ExcludeMissing status: JsonField<Status> = JsonMissing.of(),
        @JsonProperty("steps") @ExcludeMissing steps: JsonField<List<Step>> = JsonMissing.of(),
        @JsonProperty("store") @ExcludeMissing store: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("stream") @ExcludeMissing stream: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("system_instruction")
        @ExcludeMissing
        systemInstruction: JsonField<String> = JsonMissing.of(),
        @JsonProperty("tools") @ExcludeMissing tools: JsonField<List<Tool>> = JsonMissing.of(),
        @JsonProperty("updated")
        @ExcludeMissing
        updated: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("usage") @ExcludeMissing usage: JsonField<Usage> = JsonMissing.of(),
        @JsonProperty("webhook_config")
        @ExcludeMissing
        webhookConfig: JsonField<WebhookConfig> = JsonMissing.of(),
    ) : this(
        agent,
        input,
        id,
        agentConfig,
        background,
        created,
        previousInteractionId,
        responseFormat,
        responseMimeType,
        responseModalities,
        role,
        serviceTier,
        status,
        steps,
        store,
        stream,
        systemInstruction,
        tools,
        updated,
        usage,
        webhookConfig,
        mutableMapOf(),
    )

    /**
     * The name of the `Agent` used for generating the interaction.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun agent(): Agent = agent.getRequired("agent")

    /**
     * The input for the interaction.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun input(): Input = input.getRequired("input")

    /**
     * Required. Output only. A unique identifier for the interaction completion.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun id(): Optional<String> = id.getOptional("id")

    /**
     * Configuration parameters for the agent interaction.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun agentConfig(): Optional<AgentConfig> = agentConfig.getOptional("agent_config")

    /**
     * Input only. Whether to run the model interaction in the background.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun background(): Optional<Boolean> = background.getOptional("background")

    /**
     * Required. Output only. The time at which the response was created in ISO 8601 format
     * (YYYY-MM-DDThh:mm:ssZ).
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun created(): Optional<OffsetDateTime> = created.getOptional("created")

    /**
     * The ID of the previous interaction, if any.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun previousInteractionId(): Optional<String> =
        previousInteractionId.getOptional("previous_interaction_id")

    /**
     * Enforces that the generated response is a JSON object that complies with the JSON schema
     * specified in this field.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun responseFormat(): Optional<ResponseFormat> = responseFormat.getOptional("response_format")

    /**
     * The mime type of the response. This is required if response_format is set.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun responseMimeType(): Optional<String> = responseMimeType.getOptional("response_mime_type")

    /**
     * The requested modalities of the response (TEXT, IMAGE, AUDIO).
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun responseModalities(): Optional<List<ResponseModality>> =
        responseModalities.getOptional("response_modalities")

    /**
     * Output only. The role of the interaction.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    @Deprecated("deprecated") fun role(): Optional<String> = role.getOptional("role")

    /**
     * The service tier for the interaction.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun serviceTier(): Optional<ServiceTier> = serviceTier.getOptional("service_tier")

    /**
     * Required. Output only. The status of the interaction.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun status(): Optional<Status> = status.getOptional("status")

    /**
     * Output only. The steps that make up the interaction.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun steps(): Optional<List<Step>> = steps.getOptional("steps")

    /**
     * Input only. Whether to store the response and request for later retrieval.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun store(): Optional<Boolean> = store.getOptional("store")

    /**
     * Input only. Whether the interaction will be streamed.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun stream(): Optional<Boolean> = stream.getOptional("stream")

    /**
     * System instruction for the interaction.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun systemInstruction(): Optional<String> = systemInstruction.getOptional("system_instruction")

    /**
     * A list of tool declarations the model may call during interaction.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun tools(): Optional<List<Tool>> = tools.getOptional("tools")

    /**
     * Required. Output only. The time at which the response was last updated in ISO 8601 format
     * (YYYY-MM-DDThh:mm:ssZ).
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun updated(): Optional<OffsetDateTime> = updated.getOptional("updated")

    /**
     * Output only. Statistics on the interaction request's token usage.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun usage(): Optional<Usage> = usage.getOptional("usage")

    /**
     * Optional. Webhook configuration for receiving notifications when the interaction completes.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun webhookConfig(): Optional<WebhookConfig> = webhookConfig.getOptional("webhook_config")

    /**
     * Returns the raw JSON value of [agent].
     *
     * Unlike [agent], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("agent") @ExcludeMissing fun _agent(): JsonField<Agent> = agent

    /**
     * Returns the raw JSON value of [input].
     *
     * Unlike [input], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("input") @ExcludeMissing fun _input(): JsonField<Input> = input

    /**
     * Returns the raw JSON value of [id].
     *
     * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    /**
     * Returns the raw JSON value of [agentConfig].
     *
     * Unlike [agentConfig], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("agent_config")
    @ExcludeMissing
    fun _agentConfig(): JsonField<AgentConfig> = agentConfig

    /**
     * Returns the raw JSON value of [background].
     *
     * Unlike [background], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("background") @ExcludeMissing fun _background(): JsonField<Boolean> = background

    /**
     * Returns the raw JSON value of [created].
     *
     * Unlike [created], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("created") @ExcludeMissing fun _created(): JsonField<OffsetDateTime> = created

    /**
     * Returns the raw JSON value of [previousInteractionId].
     *
     * Unlike [previousInteractionId], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("previous_interaction_id")
    @ExcludeMissing
    fun _previousInteractionId(): JsonField<String> = previousInteractionId

    /**
     * Returns the raw JSON value of [responseFormat].
     *
     * Unlike [responseFormat], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("response_format")
    @ExcludeMissing
    fun _responseFormat(): JsonField<ResponseFormat> = responseFormat

    /**
     * Returns the raw JSON value of [responseMimeType].
     *
     * Unlike [responseMimeType], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("response_mime_type")
    @ExcludeMissing
    fun _responseMimeType(): JsonField<String> = responseMimeType

    /**
     * Returns the raw JSON value of [responseModalities].
     *
     * Unlike [responseModalities], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("response_modalities")
    @ExcludeMissing
    fun _responseModalities(): JsonField<List<ResponseModality>> = responseModalities

    /**
     * Returns the raw JSON value of [role].
     *
     * Unlike [role], this method doesn't throw if the JSON field has an unexpected type.
     */
    @Deprecated("deprecated")
    @JsonProperty("role")
    @ExcludeMissing
    fun _role(): JsonField<String> = role

    /**
     * Returns the raw JSON value of [serviceTier].
     *
     * Unlike [serviceTier], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("service_tier")
    @ExcludeMissing
    fun _serviceTier(): JsonField<ServiceTier> = serviceTier

    /**
     * Returns the raw JSON value of [status].
     *
     * Unlike [status], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("status") @ExcludeMissing fun _status(): JsonField<Status> = status

    /**
     * Returns the raw JSON value of [steps].
     *
     * Unlike [steps], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("steps") @ExcludeMissing fun _steps(): JsonField<List<Step>> = steps

    /**
     * Returns the raw JSON value of [store].
     *
     * Unlike [store], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("store") @ExcludeMissing fun _store(): JsonField<Boolean> = store

    /**
     * Returns the raw JSON value of [stream].
     *
     * Unlike [stream], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("stream") @ExcludeMissing fun _stream(): JsonField<Boolean> = stream

    /**
     * Returns the raw JSON value of [systemInstruction].
     *
     * Unlike [systemInstruction], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("system_instruction")
    @ExcludeMissing
    fun _systemInstruction(): JsonField<String> = systemInstruction

    /**
     * Returns the raw JSON value of [tools].
     *
     * Unlike [tools], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("tools") @ExcludeMissing fun _tools(): JsonField<List<Tool>> = tools

    /**
     * Returns the raw JSON value of [updated].
     *
     * Unlike [updated], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("updated") @ExcludeMissing fun _updated(): JsonField<OffsetDateTime> = updated

    /**
     * Returns the raw JSON value of [usage].
     *
     * Unlike [usage], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("usage") @ExcludeMissing fun _usage(): JsonField<Usage> = usage

    /**
     * Returns the raw JSON value of [webhookConfig].
     *
     * Unlike [webhookConfig], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("webhook_config")
    @ExcludeMissing
    fun _webhookConfig(): JsonField<WebhookConfig> = webhookConfig

    @JsonAnySetter
    private fun putAdditionalProperty(key: String, value: JsonValue) {
        additionalProperties.put(key, value)
    }

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> =
        Collections.unmodifiableMap(additionalProperties)

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [CreateAgentInteractionParams].
         *
         * The following fields are required:
         * ```java
         * .agent()
         * .input()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [CreateAgentInteractionParams]. */
    class Builder internal constructor() {

        private var agent: JsonField<Agent>? = null
        private var input: JsonField<Input>? = null
        private var id: JsonField<String> = JsonMissing.of()
        private var agentConfig: JsonField<AgentConfig> = JsonMissing.of()
        private var background: JsonField<Boolean> = JsonMissing.of()
        private var created: JsonField<OffsetDateTime> = JsonMissing.of()
        private var previousInteractionId: JsonField<String> = JsonMissing.of()
        private var responseFormat: JsonField<ResponseFormat> = JsonMissing.of()
        private var responseMimeType: JsonField<String> = JsonMissing.of()
        private var responseModalities: JsonField<MutableList<ResponseModality>>? = null
        private var role: JsonField<String> = JsonMissing.of()
        private var serviceTier: JsonField<ServiceTier> = JsonMissing.of()
        private var status: JsonField<Status> = JsonMissing.of()
        private var steps: JsonField<MutableList<Step>>? = null
        private var store: JsonField<Boolean> = JsonMissing.of()
        private var stream: JsonField<Boolean> = JsonMissing.of()
        private var systemInstruction: JsonField<String> = JsonMissing.of()
        private var tools: JsonField<MutableList<Tool>>? = null
        private var updated: JsonField<OffsetDateTime> = JsonMissing.of()
        private var usage: JsonField<Usage> = JsonMissing.of()
        private var webhookConfig: JsonField<WebhookConfig> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(createAgentInteractionParams: CreateAgentInteractionParams) = apply {
            agent = createAgentInteractionParams.agent
            input = createAgentInteractionParams.input
            id = createAgentInteractionParams.id
            agentConfig = createAgentInteractionParams.agentConfig
            background = createAgentInteractionParams.background
            created = createAgentInteractionParams.created
            previousInteractionId = createAgentInteractionParams.previousInteractionId
            responseFormat = createAgentInteractionParams.responseFormat
            responseMimeType = createAgentInteractionParams.responseMimeType
            responseModalities =
                createAgentInteractionParams.responseModalities.map { it.toMutableList() }
            role = createAgentInteractionParams.role
            serviceTier = createAgentInteractionParams.serviceTier
            status = createAgentInteractionParams.status
            steps = createAgentInteractionParams.steps.map { it.toMutableList() }
            store = createAgentInteractionParams.store
            stream = createAgentInteractionParams.stream
            systemInstruction = createAgentInteractionParams.systemInstruction
            tools = createAgentInteractionParams.tools.map { it.toMutableList() }
            updated = createAgentInteractionParams.updated
            usage = createAgentInteractionParams.usage
            webhookConfig = createAgentInteractionParams.webhookConfig
            additionalProperties = createAgentInteractionParams.additionalProperties.toMutableMap()
        }

        /** The name of the `Agent` used for generating the interaction. */
        fun agent(agent: Agent) = agent(JsonField.of(agent))

        /**
         * Sets [Builder.agent] to an arbitrary JSON value.
         *
         * You should usually call [Builder.agent] with a well-typed [Agent] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun agent(agent: JsonField<Agent>) = apply { this.agent = agent }

        /**
         * Sets [agent] to an arbitrary [String].
         *
         * You should usually call [agent] with a well-typed [Agent] constant instead. This method
         * is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun agent(value: String) = agent(Agent.of(value))

        /** The input for the interaction. */
        fun input(input: Input) = input(JsonField.of(input))

        /**
         * Sets [Builder.input] to an arbitrary JSON value.
         *
         * You should usually call [Builder.input] with a well-typed [Input] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun input(input: JsonField<Input>) = apply { this.input = input }

        /** Alias for calling [input] with `Input.ofString(string)`. */
        fun input(string: String) = input(Input.ofString(string))

        /** Alias for calling [input] with `Input.ofStepList(stepList)`. */
        fun inputOfStepList(stepList: List<Step>) = input(Input.ofStepList(stepList))

        /** Alias for calling [input] with `Input.ofContentList(contentList)`. */
        fun inputOfContentList(contentList: List<Content>) = input(Input.ofContentList(contentList))

        /** Alias for calling [input] with `Input.ofTextContent(textContent)`. */
        fun input(textContent: TextContent) = input(Input.ofTextContent(textContent))

        /** Alias for calling [input] with `Input.ofImageContent(imageContent)`. */
        fun input(imageContent: ImageContent) = input(Input.ofImageContent(imageContent))

        /** Alias for calling [input] with `Input.ofAudioContent(audioContent)`. */
        fun input(audioContent: AudioContent) = input(Input.ofAudioContent(audioContent))

        /** Alias for calling [input] with `Input.ofDocumentContent(documentContent)`. */
        fun input(documentContent: DocumentContent) =
            input(Input.ofDocumentContent(documentContent))

        /** Alias for calling [input] with `Input.ofVideoContent(videoContent)`. */
        fun input(videoContent: VideoContent) = input(Input.ofVideoContent(videoContent))

        /** Required. Output only. A unique identifier for the interaction completion. */
        fun id(id: String) = id(JsonField.of(id))

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

        /** Configuration parameters for the agent interaction. */
        fun agentConfig(agentConfig: AgentConfig) = agentConfig(JsonField.of(agentConfig))

        /**
         * Sets [Builder.agentConfig] to an arbitrary JSON value.
         *
         * You should usually call [Builder.agentConfig] with a well-typed [AgentConfig] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun agentConfig(agentConfig: JsonField<AgentConfig>) = apply {
            this.agentConfig = agentConfig
        }

        /** Alias for calling [agentConfig] with `AgentConfig.ofDynamic(dynamic)`. */
        fun agentConfig(dynamic: DynamicAgentConfig) = agentConfig(AgentConfig.ofDynamic(dynamic))

        /** Alias for calling [agentConfig] with `AgentConfig.ofDeepResearch(deepResearch)`. */
        fun agentConfig(deepResearch: DeepResearchAgentConfig) =
            agentConfig(AgentConfig.ofDeepResearch(deepResearch))

        /** Input only. Whether to run the model interaction in the background. */
        fun background(background: Boolean) = background(JsonField.of(background))

        /**
         * Sets [Builder.background] to an arbitrary JSON value.
         *
         * You should usually call [Builder.background] with a well-typed [Boolean] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun background(background: JsonField<Boolean>) = apply { this.background = background }

        /**
         * Required. Output only. The time at which the response was created in ISO 8601 format
         * (YYYY-MM-DDThh:mm:ssZ).
         */
        fun created(created: OffsetDateTime) = created(JsonField.of(created))

        /**
         * Sets [Builder.created] to an arbitrary JSON value.
         *
         * You should usually call [Builder.created] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun created(created: JsonField<OffsetDateTime>) = apply { this.created = created }

        /** The ID of the previous interaction, if any. */
        fun previousInteractionId(previousInteractionId: String) =
            previousInteractionId(JsonField.of(previousInteractionId))

        /**
         * Sets [Builder.previousInteractionId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.previousInteractionId] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun previousInteractionId(previousInteractionId: JsonField<String>) = apply {
            this.previousInteractionId = previousInteractionId
        }

        /**
         * Enforces that the generated response is a JSON object that complies with the JSON schema
         * specified in this field.
         */
        fun responseFormat(responseFormat: ResponseFormat) =
            responseFormat(JsonField.of(responseFormat))

        /**
         * Sets [Builder.responseFormat] to an arbitrary JSON value.
         *
         * You should usually call [Builder.responseFormat] with a well-typed [ResponseFormat] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun responseFormat(responseFormat: JsonField<ResponseFormat>) = apply {
            this.responseFormat = responseFormat
        }

        /** Alias for calling [responseFormat] with `ResponseFormat.ofList(list)`. */
        fun responseFormatOfList(list: List<ResponseFormat.InnerResponseFormat>) =
            responseFormat(ResponseFormat.ofList(list))

        /** Alias for calling [responseFormat] with `ResponseFormat.ofAudio(audio)`. */
        fun responseFormat(audio: AudioResponseFormat) =
            responseFormat(ResponseFormat.ofAudio(audio))

        /** Alias for calling [responseFormat] with `ResponseFormat.ofText(text)`. */
        fun responseFormat(text: TextResponseFormat) = responseFormat(ResponseFormat.ofText(text))

        /** Alias for calling [responseFormat] with `ResponseFormat.ofImage(image)`. */
        fun responseFormat(image: ImageResponseFormat) =
            responseFormat(ResponseFormat.ofImage(image))

        /** Alias for calling [responseFormat] with `ResponseFormat.ofVideo(video)`. */
        fun responseFormat(video: VideoResponseFormat) =
            responseFormat(ResponseFormat.ofVideo(video))

        /** Alias for calling [responseFormat] with `ResponseFormat.ofJsonValue(jsonValue)`. */
        fun responseFormat(jsonValue: JsonValue) =
            responseFormat(ResponseFormat.ofJsonValue(jsonValue))

        /** The mime type of the response. This is required if response_format is set. */
        fun responseMimeType(responseMimeType: String) =
            responseMimeType(JsonField.of(responseMimeType))

        /**
         * Sets [Builder.responseMimeType] to an arbitrary JSON value.
         *
         * You should usually call [Builder.responseMimeType] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun responseMimeType(responseMimeType: JsonField<String>) = apply {
            this.responseMimeType = responseMimeType
        }

        /** The requested modalities of the response (TEXT, IMAGE, AUDIO). */
        fun responseModalities(responseModalities: List<ResponseModality>) =
            responseModalities(JsonField.of(responseModalities))

        /**
         * Sets [Builder.responseModalities] to an arbitrary JSON value.
         *
         * You should usually call [Builder.responseModalities] with a well-typed
         * `List<ResponseModality>` value instead. This method is primarily for setting the field to
         * an undocumented or not yet supported value.
         */
        fun responseModalities(responseModalities: JsonField<List<ResponseModality>>) = apply {
            this.responseModalities = responseModalities.map { it.toMutableList() }
        }

        /**
         * Adds a single [ResponseModality] to [responseModalities].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addResponseModality(responseModality: ResponseModality) = apply {
            responseModalities =
                (responseModalities ?: JsonField.of(mutableListOf())).also {
                    checkKnown("responseModalities", it).add(responseModality)
                }
        }

        /** Output only. The role of the interaction. */
        @Deprecated("deprecated") fun role(role: String) = role(JsonField.of(role))

        /**
         * Sets [Builder.role] to an arbitrary JSON value.
         *
         * You should usually call [Builder.role] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        @Deprecated("deprecated") fun role(role: JsonField<String>) = apply { this.role = role }

        /** The service tier for the interaction. */
        fun serviceTier(serviceTier: ServiceTier) = serviceTier(JsonField.of(serviceTier))

        /**
         * Sets [Builder.serviceTier] to an arbitrary JSON value.
         *
         * You should usually call [Builder.serviceTier] with a well-typed [ServiceTier] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun serviceTier(serviceTier: JsonField<ServiceTier>) = apply {
            this.serviceTier = serviceTier
        }

        /** Required. Output only. The status of the interaction. */
        fun status(status: Status) = status(JsonField.of(status))

        /**
         * Sets [Builder.status] to an arbitrary JSON value.
         *
         * You should usually call [Builder.status] with a well-typed [Status] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun status(status: JsonField<Status>) = apply { this.status = status }

        /** Output only. The steps that make up the interaction. */
        fun steps(steps: List<Step>) = steps(JsonField.of(steps))

        /**
         * Sets [Builder.steps] to an arbitrary JSON value.
         *
         * You should usually call [Builder.steps] with a well-typed `List<Step>` value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun steps(steps: JsonField<List<Step>>) = apply {
            this.steps = steps.map { it.toMutableList() }
        }

        /**
         * Adds a single [Step] to [steps].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addStep(step: Step) = apply {
            steps =
                (steps ?: JsonField.of(mutableListOf())).also { checkKnown("steps", it).add(step) }
        }

        /** Alias for calling [addStep] with `Step.ofUserInput(userInput)`. */
        fun addStep(userInput: UserInputStep) = addStep(Step.ofUserInput(userInput))

        /** Alias for calling [addStep] with `Step.ofModelOutput(modelOutput)`. */
        fun addStep(modelOutput: ModelOutputStep) = addStep(Step.ofModelOutput(modelOutput))

        /** Alias for calling [addStep] with `Step.ofThought(thought)`. */
        fun addStep(thought: ThoughtStep) = addStep(Step.ofThought(thought))

        /** Alias for calling [addStep] with `Step.ofFunctionCall(functionCall)`. */
        fun addStep(functionCall: FunctionCallStep) = addStep(Step.ofFunctionCall(functionCall))

        /** Alias for calling [addStep] with `Step.ofCodeExecutionCall(codeExecutionCall)`. */
        fun addStep(codeExecutionCall: CodeExecutionCallStep) =
            addStep(Step.ofCodeExecutionCall(codeExecutionCall))

        /** Alias for calling [addStep] with `Step.ofUrlContextCall(urlContextCall)`. */
        fun addStep(urlContextCall: UrlContextCallStep) =
            addStep(Step.ofUrlContextCall(urlContextCall))

        /** Alias for calling [addStep] with `Step.ofMcpServerToolCall(mcpServerToolCall)`. */
        fun addStep(mcpServerToolCall: McpServerToolCallStep) =
            addStep(Step.ofMcpServerToolCall(mcpServerToolCall))

        /** Alias for calling [addStep] with `Step.ofGoogleSearchCall(googleSearchCall)`. */
        fun addStep(googleSearchCall: GoogleSearchCallStep) =
            addStep(Step.ofGoogleSearchCall(googleSearchCall))

        /** Alias for calling [addStep] with `Step.ofFileSearchCall(fileSearchCall)`. */
        fun addStep(fileSearchCall: FileSearchCallStep) =
            addStep(Step.ofFileSearchCall(fileSearchCall))

        /**
         * Alias for calling [addStep] with the following:
         * ```java
         * FileSearchCallStep.builder()
         *     .id(id)
         *     .build()
         * ```
         */
        fun addFileSearchCallStep(id: String) = addStep(FileSearchCallStep.builder().id(id).build())

        /** Alias for calling [addStep] with `Step.ofGoogleMapsCall(googleMapsCall)`. */
        fun addStep(googleMapsCall: GoogleMapsCallStep) =
            addStep(Step.ofGoogleMapsCall(googleMapsCall))

        /**
         * Alias for calling [addStep] with the following:
         * ```java
         * GoogleMapsCallStep.builder()
         *     .id(id)
         *     .build()
         * ```
         */
        fun addGoogleMapsCallStep(id: String) = addStep(GoogleMapsCallStep.builder().id(id).build())

        /** Alias for calling [addStep] with `Step.ofFunctionResult(functionResult)`. */
        fun addStep(functionResult: FunctionResultStep) =
            addStep(Step.ofFunctionResult(functionResult))

        /** Alias for calling [addStep] with `Step.ofCodeExecutionResult(codeExecutionResult)`. */
        fun addStep(codeExecutionResult: CodeExecutionResultStep) =
            addStep(Step.ofCodeExecutionResult(codeExecutionResult))

        /** Alias for calling [addStep] with `Step.ofUrlContextResult(urlContextResult)`. */
        fun addStep(urlContextResult: UrlContextResultStep) =
            addStep(Step.ofUrlContextResult(urlContextResult))

        /** Alias for calling [addStep] with `Step.ofGoogleSearchResult(googleSearchResult)`. */
        fun addStep(googleSearchResult: GoogleSearchResultStep) =
            addStep(Step.ofGoogleSearchResult(googleSearchResult))

        /** Alias for calling [addStep] with `Step.ofMcpServerToolResult(mcpServerToolResult)`. */
        fun addStep(mcpServerToolResult: McpServerToolResultStep) =
            addStep(Step.ofMcpServerToolResult(mcpServerToolResult))

        /** Alias for calling [addStep] with `Step.ofFileSearchResult(fileSearchResult)`. */
        fun addStep(fileSearchResult: FileSearchResultStep) =
            addStep(Step.ofFileSearchResult(fileSearchResult))

        /**
         * Alias for calling [addStep] with the following:
         * ```java
         * FileSearchResultStep.builder()
         *     .callId(callId)
         *     .build()
         * ```
         */
        fun addFileSearchResultStep(callId: String) =
            addStep(FileSearchResultStep.builder().callId(callId).build())

        /** Alias for calling [addStep] with `Step.ofGoogleMapsResult(googleMapsResult)`. */
        fun addStep(googleMapsResult: GoogleMapsResultStep) =
            addStep(Step.ofGoogleMapsResult(googleMapsResult))

        /** Input only. Whether to store the response and request for later retrieval. */
        fun store(store: Boolean) = store(JsonField.of(store))

        /**
         * Sets [Builder.store] to an arbitrary JSON value.
         *
         * You should usually call [Builder.store] with a well-typed [Boolean] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun store(store: JsonField<Boolean>) = apply { this.store = store }

        /** Input only. Whether the interaction will be streamed. */
        fun stream(stream: Boolean) = stream(JsonField.of(stream))

        /**
         * Sets [Builder.stream] to an arbitrary JSON value.
         *
         * You should usually call [Builder.stream] with a well-typed [Boolean] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun stream(stream: JsonField<Boolean>) = apply { this.stream = stream }

        /** System instruction for the interaction. */
        fun systemInstruction(systemInstruction: String) =
            systemInstruction(JsonField.of(systemInstruction))

        /**
         * Sets [Builder.systemInstruction] to an arbitrary JSON value.
         *
         * You should usually call [Builder.systemInstruction] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun systemInstruction(systemInstruction: JsonField<String>) = apply {
            this.systemInstruction = systemInstruction
        }

        /** A list of tool declarations the model may call during interaction. */
        fun tools(tools: List<Tool>) = tools(JsonField.of(tools))

        /**
         * Sets [Builder.tools] to an arbitrary JSON value.
         *
         * You should usually call [Builder.tools] with a well-typed `List<Tool>` value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun tools(tools: JsonField<List<Tool>>) = apply {
            this.tools = tools.map { it.toMutableList() }
        }

        /**
         * Adds a single [Tool] to [tools].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addTool(tool: Tool) = apply {
            tools =
                (tools ?: JsonField.of(mutableListOf())).also { checkKnown("tools", it).add(tool) }
        }

        /** Alias for calling [addTool] with `Tool.ofFunction(function)`. */
        fun addTool(function: Function) = addTool(Tool.ofFunction(function))

        /** Alias for calling [addTool] with `Tool.ofCodeExecution()`. */
        fun addToolCodeExecution() = addTool(Tool.ofCodeExecution())

        /** Alias for calling [addTool] with `Tool.ofUrlContext()`. */
        fun addToolUrlContext() = addTool(Tool.ofUrlContext())

        /** Alias for calling [addTool] with `Tool.ofComputerUse(computerUse)`. */
        fun addTool(computerUse: Tool.ComputerUse) = addTool(Tool.ofComputerUse(computerUse))

        /** Alias for calling [addTool] with `Tool.ofMcpServer(mcpServer)`. */
        fun addTool(mcpServer: Tool.McpServer) = addTool(Tool.ofMcpServer(mcpServer))

        /** Alias for calling [addTool] with `Tool.ofGoogleSearch(googleSearch)`. */
        fun addTool(googleSearch: Tool.GoogleSearch) = addTool(Tool.ofGoogleSearch(googleSearch))

        /** Alias for calling [addTool] with `Tool.ofFileSearch(fileSearch)`. */
        fun addTool(fileSearch: Tool.FileSearch) = addTool(Tool.ofFileSearch(fileSearch))

        /** Alias for calling [addTool] with `Tool.ofGoogleMaps(googleMaps)`. */
        fun addTool(googleMaps: Tool.GoogleMaps) = addTool(Tool.ofGoogleMaps(googleMaps))

        /** Alias for calling [addTool] with `Tool.ofRetrieval(retrieval)`. */
        fun addTool(retrieval: Tool.Retrieval) = addTool(Tool.ofRetrieval(retrieval))

        /**
         * Required. Output only. The time at which the response was last updated in ISO 8601 format
         * (YYYY-MM-DDThh:mm:ssZ).
         */
        fun updated(updated: OffsetDateTime) = updated(JsonField.of(updated))

        /**
         * Sets [Builder.updated] to an arbitrary JSON value.
         *
         * You should usually call [Builder.updated] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun updated(updated: JsonField<OffsetDateTime>) = apply { this.updated = updated }

        /** Output only. Statistics on the interaction request's token usage. */
        fun usage(usage: Usage) = usage(JsonField.of(usage))

        /**
         * Sets [Builder.usage] to an arbitrary JSON value.
         *
         * You should usually call [Builder.usage] with a well-typed [Usage] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun usage(usage: JsonField<Usage>) = apply { this.usage = usage }

        /**
         * Optional. Webhook configuration for receiving notifications when the interaction
         * completes.
         */
        fun webhookConfig(webhookConfig: WebhookConfig) = webhookConfig(JsonField.of(webhookConfig))

        /**
         * Sets [Builder.webhookConfig] to an arbitrary JSON value.
         *
         * You should usually call [Builder.webhookConfig] with a well-typed [WebhookConfig] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun webhookConfig(webhookConfig: JsonField<WebhookConfig>) = apply {
            this.webhookConfig = webhookConfig
        }

        fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.clear()
            putAllAdditionalProperties(additionalProperties)
        }

        fun putAdditionalProperty(key: String, value: JsonValue) = apply {
            additionalProperties.put(key, value)
        }

        fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.putAll(additionalProperties)
        }

        fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

        fun removeAllAdditionalProperties(keys: Set<String>) = apply {
            keys.forEach(::removeAdditionalProperty)
        }

        /**
         * Returns an immutable instance of [CreateAgentInteractionParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .agent()
         * .input()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): CreateAgentInteractionParams =
            CreateAgentInteractionParams(
                checkRequired("agent", agent),
                checkRequired("input", input),
                id,
                agentConfig,
                background,
                created,
                previousInteractionId,
                responseFormat,
                responseMimeType,
                (responseModalities ?: JsonMissing.of()).map { it.toImmutable() },
                role,
                serviceTier,
                status,
                (steps ?: JsonMissing.of()).map { it.toImmutable() },
                store,
                stream,
                systemInstruction,
                (tools ?: JsonMissing.of()).map { it.toImmutable() },
                updated,
                usage,
                webhookConfig,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): CreateAgentInteractionParams = apply {
        if (validated) {
            return@apply
        }

        agent()
        input().validate()
        id()
        agentConfig().ifPresent { it.validate() }
        background()
        created()
        previousInteractionId()
        responseFormat().ifPresent { it.validate() }
        responseMimeType()
        responseModalities().ifPresent { it.forEach { it.validate() } }
        role()
        serviceTier().ifPresent { it.validate() }
        status().ifPresent { it.validate() }
        steps().ifPresent { it.forEach { it.validate() } }
        store()
        stream()
        systemInstruction()
        tools().ifPresent { it.forEach { it.validate() } }
        updated()
        usage().ifPresent { it.validate() }
        webhookConfig().ifPresent { it.validate() }
        validated = true
    }

    fun isValid(): Boolean =
        try {
            validate()
            true
        } catch (e: GeminiNextGenApiInvalidDataException) {
            false
        }

    /**
     * Returns a score indicating how many valid values are contained in this object recursively.
     *
     * Used for best match union deserialization.
     */
    @JvmSynthetic
    internal fun validity(): Int =
        (if (agent.asKnown().isPresent) 1 else 0) +
            (input.asKnown().getOrNull()?.validity() ?: 0) +
            (if (id.asKnown().isPresent) 1 else 0) +
            (agentConfig.asKnown().getOrNull()?.validity() ?: 0) +
            (if (background.asKnown().isPresent) 1 else 0) +
            (if (created.asKnown().isPresent) 1 else 0) +
            (if (previousInteractionId.asKnown().isPresent) 1 else 0) +
            (responseFormat.asKnown().getOrNull()?.validity() ?: 0) +
            (if (responseMimeType.asKnown().isPresent) 1 else 0) +
            (responseModalities.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (if (role.asKnown().isPresent) 1 else 0) +
            (serviceTier.asKnown().getOrNull()?.validity() ?: 0) +
            (status.asKnown().getOrNull()?.validity() ?: 0) +
            (steps.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (if (store.asKnown().isPresent) 1 else 0) +
            (if (stream.asKnown().isPresent) 1 else 0) +
            (if (systemInstruction.asKnown().isPresent) 1 else 0) +
            (tools.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (if (updated.asKnown().isPresent) 1 else 0) +
            (usage.asKnown().getOrNull()?.validity() ?: 0) +
            (webhookConfig.asKnown().getOrNull()?.validity() ?: 0)

    /** The name of the `Agent` used for generating the interaction. */
    class Agent @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

        /**
         * Returns this class instance's raw value.
         *
         * This is usually only useful if this instance was deserialized from data that doesn't
         * match any known member, and you want to know that value. For example, if the SDK is on an
         * older version than the API, then the API may respond with new members that the SDK is
         * unaware of.
         */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            /** Gemini Deep Research Agent */
            @JvmField
            val DEEP_RESEARCH_PRO_PREVIEW_12_2025 = of("deep-research-pro-preview-12-2025")

            /** Gemini Deep Research Agent */
            @JvmField val DEEP_RESEARCH_PREVIEW_04_2026 = of("deep-research-preview-04-2026")

            /** Gemini Deep Research Max Agent */
            @JvmField
            val DEEP_RESEARCH_MAX_PREVIEW_04_2026 = of("deep-research-max-preview-04-2026")

            @JvmStatic fun of(value: String) = Agent(JsonField.of(value))
        }

        /** An enum containing [Agent]'s known values. */
        enum class Known {
            /** Gemini Deep Research Agent */
            DEEP_RESEARCH_PRO_PREVIEW_12_2025,
            /** Gemini Deep Research Agent */
            DEEP_RESEARCH_PREVIEW_04_2026,
            /** Gemini Deep Research Max Agent */
            DEEP_RESEARCH_MAX_PREVIEW_04_2026,
        }

        /**
         * An enum containing [Agent]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Agent] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            /** Gemini Deep Research Agent */
            DEEP_RESEARCH_PRO_PREVIEW_12_2025,
            /** Gemini Deep Research Agent */
            DEEP_RESEARCH_PREVIEW_04_2026,
            /** Gemini Deep Research Max Agent */
            DEEP_RESEARCH_MAX_PREVIEW_04_2026,
            /** An enum member indicating that [Agent] was instantiated with an unknown value. */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         *
         * Use the [known] method instead if you're certain the value is always known or if you want
         * to throw for the unknown case.
         */
        fun value(): Value =
            when (this) {
                DEEP_RESEARCH_PRO_PREVIEW_12_2025 -> Value.DEEP_RESEARCH_PRO_PREVIEW_12_2025
                DEEP_RESEARCH_PREVIEW_04_2026 -> Value.DEEP_RESEARCH_PREVIEW_04_2026
                DEEP_RESEARCH_MAX_PREVIEW_04_2026 -> Value.DEEP_RESEARCH_MAX_PREVIEW_04_2026
                else -> Value._UNKNOWN
            }

        /**
         * Returns an enum member corresponding to this class instance's value.
         *
         * Use the [value] method instead if you're uncertain the value is always known and don't
         * want to throw for the unknown case.
         *
         * @throws GeminiNextGenApiInvalidDataException if this class instance's value is a not a
         *   known member.
         */
        fun known(): Known =
            when (this) {
                DEEP_RESEARCH_PRO_PREVIEW_12_2025 -> Known.DEEP_RESEARCH_PRO_PREVIEW_12_2025
                DEEP_RESEARCH_PREVIEW_04_2026 -> Known.DEEP_RESEARCH_PREVIEW_04_2026
                DEEP_RESEARCH_MAX_PREVIEW_04_2026 -> Known.DEEP_RESEARCH_MAX_PREVIEW_04_2026
                else -> throw GeminiNextGenApiInvalidDataException("Unknown Agent: $value")
            }

        /**
         * Returns this class instance's primitive wire representation.
         *
         * This differs from the [toString] method because that method is primarily for debugging
         * and generally doesn't throw.
         *
         * @throws GeminiNextGenApiInvalidDataException if this class instance's value does not have
         *   the expected primitive type.
         */
        fun asString(): String =
            _value().asString().orElseThrow {
                GeminiNextGenApiInvalidDataException("Value is not a String")
            }

        private var validated: Boolean = false

        fun validate(): Agent = apply {
            if (validated) {
                return@apply
            }

            known()
            validated = true
        }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: GeminiNextGenApiInvalidDataException) {
                false
            }

        /**
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Agent && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    /** The input for the interaction. */
    @JsonDeserialize(using = Input.Deserializer::class)
    @JsonSerialize(using = Input.Serializer::class)
    class Input
    private constructor(
        private val string: String? = null,
        private val stepList: List<Step>? = null,
        private val contentList: List<Content>? = null,
        private val textContent: TextContent? = null,
        private val imageContent: ImageContent? = null,
        private val audioContent: AudioContent? = null,
        private val documentContent: DocumentContent? = null,
        private val videoContent: VideoContent? = null,
        private val _json: JsonValue? = null,
    ) {

        fun string(): Optional<String> = Optional.ofNullable(string)

        fun stepList(): Optional<List<Step>> = Optional.ofNullable(stepList)

        fun contentList(): Optional<List<Content>> = Optional.ofNullable(contentList)

        /** A text content block. */
        fun textContent(): Optional<TextContent> = Optional.ofNullable(textContent)

        /** An image content block. */
        fun imageContent(): Optional<ImageContent> = Optional.ofNullable(imageContent)

        /** An audio content block. */
        fun audioContent(): Optional<AudioContent> = Optional.ofNullable(audioContent)

        /** A document content block. */
        fun documentContent(): Optional<DocumentContent> = Optional.ofNullable(documentContent)

        /** A video content block. */
        fun videoContent(): Optional<VideoContent> = Optional.ofNullable(videoContent)

        fun isString(): Boolean = string != null

        fun isStepList(): Boolean = stepList != null

        fun isContentList(): Boolean = contentList != null

        fun isTextContent(): Boolean = textContent != null

        fun isImageContent(): Boolean = imageContent != null

        fun isAudioContent(): Boolean = audioContent != null

        fun isDocumentContent(): Boolean = documentContent != null

        fun isVideoContent(): Boolean = videoContent != null

        fun asString(): String = string.getOrThrow("string")

        fun asStepList(): List<Step> = stepList.getOrThrow("stepList")

        fun asContentList(): List<Content> = contentList.getOrThrow("contentList")

        /** A text content block. */
        fun asTextContent(): TextContent = textContent.getOrThrow("textContent")

        /** An image content block. */
        fun asImageContent(): ImageContent = imageContent.getOrThrow("imageContent")

        /** An audio content block. */
        fun asAudioContent(): AudioContent = audioContent.getOrThrow("audioContent")

        /** A document content block. */
        fun asDocumentContent(): DocumentContent = documentContent.getOrThrow("documentContent")

        /** A video content block. */
        fun asVideoContent(): VideoContent = videoContent.getOrThrow("videoContent")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        fun <T> accept(visitor: Visitor<T>): T =
            when {
                string != null -> visitor.visitString(string)
                stepList != null -> visitor.visitStepList(stepList)
                contentList != null -> visitor.visitContentList(contentList)
                textContent != null -> visitor.visitTextContent(textContent)
                imageContent != null -> visitor.visitImageContent(imageContent)
                audioContent != null -> visitor.visitAudioContent(audioContent)
                documentContent != null -> visitor.visitDocumentContent(documentContent)
                videoContent != null -> visitor.visitVideoContent(videoContent)
                else -> visitor.unknown(_json)
            }

        private var validated: Boolean = false

        fun validate(): Input = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitString(string: String) {}

                    override fun visitStepList(stepList: List<Step>) {
                        stepList.forEach { it.validate() }
                    }

                    override fun visitContentList(contentList: List<Content>) {
                        contentList.forEach { it.validate() }
                    }

                    override fun visitTextContent(textContent: TextContent) {
                        textContent.validate()
                    }

                    override fun visitImageContent(imageContent: ImageContent) {
                        imageContent.validate()
                    }

                    override fun visitAudioContent(audioContent: AudioContent) {
                        audioContent.validate()
                    }

                    override fun visitDocumentContent(documentContent: DocumentContent) {
                        documentContent.validate()
                    }

                    override fun visitVideoContent(videoContent: VideoContent) {
                        videoContent.validate()
                    }
                }
            )
            validated = true
        }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: GeminiNextGenApiInvalidDataException) {
                false
            }

        /**
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic
        internal fun validity(): Int =
            accept(
                object : Visitor<Int> {
                    override fun visitString(string: String) = 1

                    override fun visitStepList(stepList: List<Step>) =
                        stepList.sumOf { it.validity().toInt() }

                    override fun visitContentList(contentList: List<Content>) =
                        contentList.sumOf { it.validity().toInt() }

                    override fun visitTextContent(textContent: TextContent) = textContent.validity()

                    override fun visitImageContent(imageContent: ImageContent) =
                        imageContent.validity()

                    override fun visitAudioContent(audioContent: AudioContent) =
                        audioContent.validity()

                    override fun visitDocumentContent(documentContent: DocumentContent) =
                        documentContent.validity()

                    override fun visitVideoContent(videoContent: VideoContent) =
                        videoContent.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Input &&
                string == other.string &&
                stepList == other.stepList &&
                contentList == other.contentList &&
                textContent == other.textContent &&
                imageContent == other.imageContent &&
                audioContent == other.audioContent &&
                documentContent == other.documentContent &&
                videoContent == other.videoContent
        }

        override fun hashCode(): Int =
            Objects.hash(
                string,
                stepList,
                contentList,
                textContent,
                imageContent,
                audioContent,
                documentContent,
                videoContent,
            )

        override fun toString(): String =
            when {
                string != null -> "Input{string=$string}"
                stepList != null -> "Input{stepList=$stepList}"
                contentList != null -> "Input{contentList=$contentList}"
                textContent != null -> "Input{textContent=$textContent}"
                imageContent != null -> "Input{imageContent=$imageContent}"
                audioContent != null -> "Input{audioContent=$audioContent}"
                documentContent != null -> "Input{documentContent=$documentContent}"
                videoContent != null -> "Input{videoContent=$videoContent}"
                _json != null -> "Input{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Input")
            }

        companion object {

            @JvmStatic fun ofString(string: String) = Input(string = string)

            @JvmStatic
            fun ofStepList(stepList: List<Step>) = Input(stepList = stepList.toImmutable())

            @JvmStatic
            fun ofContentList(contentList: List<Content>) =
                Input(contentList = contentList.toImmutable())

            /** A text content block. */
            @JvmStatic
            fun ofTextContent(textContent: TextContent) = Input(textContent = textContent)

            /** An image content block. */
            @JvmStatic
            fun ofImageContent(imageContent: ImageContent) = Input(imageContent = imageContent)

            /** An audio content block. */
            @JvmStatic
            fun ofAudioContent(audioContent: AudioContent) = Input(audioContent = audioContent)

            /** A document content block. */
            @JvmStatic
            fun ofDocumentContent(documentContent: DocumentContent) =
                Input(documentContent = documentContent)

            /** A video content block. */
            @JvmStatic
            fun ofVideoContent(videoContent: VideoContent) = Input(videoContent = videoContent)
        }

        /** An interface that defines how to map each variant of [Input] to a value of type [T]. */
        interface Visitor<out T> {

            fun visitString(string: String): T

            fun visitStepList(stepList: List<Step>): T

            fun visitContentList(contentList: List<Content>): T

            /** A text content block. */
            fun visitTextContent(textContent: TextContent): T

            /** An image content block. */
            fun visitImageContent(imageContent: ImageContent): T

            /** An audio content block. */
            fun visitAudioContent(audioContent: AudioContent): T

            /** A document content block. */
            fun visitDocumentContent(documentContent: DocumentContent): T

            /** A video content block. */
            fun visitVideoContent(videoContent: VideoContent): T

            /**
             * Maps an unknown variant of [Input] to a value of type [T].
             *
             * An instance of [Input] can contain an unknown variant if it was deserialized from
             * data that doesn't match any known variant. For example, if the SDK is on an older
             * version than the API, then the API may respond with new variants that the SDK is
             * unaware of.
             *
             * @throws GeminiNextGenApiInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw GeminiNextGenApiInvalidDataException("Unknown Input: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<Input>(Input::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Input {
                val json = JsonValue.fromJsonNode(node)

                val bestMatches =
                    sequenceOf(
                            tryDeserialize(node, jacksonTypeRef<TextContent>())?.let {
                                Input(textContent = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<ImageContent>())?.let {
                                Input(imageContent = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<AudioContent>())?.let {
                                Input(audioContent = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<DocumentContent>())?.let {
                                Input(documentContent = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<VideoContent>())?.let {
                                Input(videoContent = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<String>())?.let {
                                Input(string = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<List<Step>>())?.let {
                                Input(stepList = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<List<Content>>())?.let {
                                Input(contentList = it, _json = json)
                            },
                        )
                        .filterNotNull()
                        .allMaxBy { it.validity() }
                        .toList()
                return when (bestMatches.size) {
                    // This can happen if what we're deserializing is completely incompatible with
                    // all the possible variants (e.g. deserializing from boolean).
                    0 -> Input(_json = json)
                    1 -> bestMatches.single()
                    // If there's more than one match with the highest validity, then use the first
                    // completely valid match, or simply the first match if none are completely
                    // valid.
                    else -> bestMatches.firstOrNull { it.isValid() } ?: bestMatches.first()
                }
            }
        }

        internal class Serializer : BaseSerializer<Input>(Input::class) {

            override fun serialize(
                value: Input,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.string != null -> generator.writeObject(value.string)
                    value.stepList != null -> generator.writeObject(value.stepList)
                    value.contentList != null -> generator.writeObject(value.contentList)
                    value.textContent != null -> generator.writeObject(value.textContent)
                    value.imageContent != null -> generator.writeObject(value.imageContent)
                    value.audioContent != null -> generator.writeObject(value.audioContent)
                    value.documentContent != null -> generator.writeObject(value.documentContent)
                    value.videoContent != null -> generator.writeObject(value.videoContent)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Input")
                }
            }
        }
    }

    /** Configuration parameters for the agent interaction. */
    @JsonDeserialize(using = AgentConfig.Deserializer::class)
    @JsonSerialize(using = AgentConfig.Serializer::class)
    class AgentConfig
    private constructor(
        private val dynamic: DynamicAgentConfig? = null,
        private val deepResearch: DeepResearchAgentConfig? = null,
        private val _json: JsonValue? = null,
    ) {

        /** Configuration for dynamic agents. */
        fun dynamic(): Optional<DynamicAgentConfig> = Optional.ofNullable(dynamic)

        /** Configuration for the Deep Research agent. */
        fun deepResearch(): Optional<DeepResearchAgentConfig> = Optional.ofNullable(deepResearch)

        fun isDynamic(): Boolean = dynamic != null

        fun isDeepResearch(): Boolean = deepResearch != null

        /** Configuration for dynamic agents. */
        fun asDynamic(): DynamicAgentConfig = dynamic.getOrThrow("dynamic")

        /** Configuration for the Deep Research agent. */
        fun asDeepResearch(): DeepResearchAgentConfig = deepResearch.getOrThrow("deepResearch")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        fun <T> accept(visitor: Visitor<T>): T =
            when {
                dynamic != null -> visitor.visitDynamic(dynamic)
                deepResearch != null -> visitor.visitDeepResearch(deepResearch)
                else -> visitor.unknown(_json)
            }

        private var validated: Boolean = false

        fun validate(): AgentConfig = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitDynamic(dynamic: DynamicAgentConfig) {
                        dynamic.validate()
                    }

                    override fun visitDeepResearch(deepResearch: DeepResearchAgentConfig) {
                        deepResearch.validate()
                    }
                }
            )
            validated = true
        }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: GeminiNextGenApiInvalidDataException) {
                false
            }

        /**
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic
        internal fun validity(): Int =
            accept(
                object : Visitor<Int> {
                    override fun visitDynamic(dynamic: DynamicAgentConfig) = dynamic.validity()

                    override fun visitDeepResearch(deepResearch: DeepResearchAgentConfig) =
                        deepResearch.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is AgentConfig &&
                dynamic == other.dynamic &&
                deepResearch == other.deepResearch
        }

        override fun hashCode(): Int = Objects.hash(dynamic, deepResearch)

        override fun toString(): String =
            when {
                dynamic != null -> "AgentConfig{dynamic=$dynamic}"
                deepResearch != null -> "AgentConfig{deepResearch=$deepResearch}"
                _json != null -> "AgentConfig{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid AgentConfig")
            }

        companion object {

            /** Configuration for dynamic agents. */
            @JvmStatic fun ofDynamic(dynamic: DynamicAgentConfig) = AgentConfig(dynamic = dynamic)

            /** Configuration for the Deep Research agent. */
            @JvmStatic
            fun ofDeepResearch(deepResearch: DeepResearchAgentConfig) =
                AgentConfig(deepResearch = deepResearch)
        }

        /**
         * An interface that defines how to map each variant of [AgentConfig] to a value of type
         * [T].
         */
        interface Visitor<out T> {

            /** Configuration for dynamic agents. */
            fun visitDynamic(dynamic: DynamicAgentConfig): T

            /** Configuration for the Deep Research agent. */
            fun visitDeepResearch(deepResearch: DeepResearchAgentConfig): T

            /**
             * Maps an unknown variant of [AgentConfig] to a value of type [T].
             *
             * An instance of [AgentConfig] can contain an unknown variant if it was deserialized
             * from data that doesn't match any known variant. For example, if the SDK is on an
             * older version than the API, then the API may respond with new variants that the SDK
             * is unaware of.
             *
             * @throws GeminiNextGenApiInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw GeminiNextGenApiInvalidDataException("Unknown AgentConfig: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<AgentConfig>(AgentConfig::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): AgentConfig {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "dynamic" -> {
                        return tryDeserialize(node, jacksonTypeRef<DynamicAgentConfig>())?.let {
                            AgentConfig(dynamic = it, _json = json)
                        } ?: AgentConfig(_json = json)
                    }
                    "deep-research" -> {
                        return tryDeserialize(node, jacksonTypeRef<DeepResearchAgentConfig>())
                            ?.let { AgentConfig(deepResearch = it, _json = json) }
                            ?: AgentConfig(_json = json)
                    }
                }

                return AgentConfig(_json = json)
            }
        }

        internal class Serializer : BaseSerializer<AgentConfig>(AgentConfig::class) {

            override fun serialize(
                value: AgentConfig,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.dynamic != null -> generator.writeObject(value.dynamic)
                    value.deepResearch != null -> generator.writeObject(value.deepResearch)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid AgentConfig")
                }
            }
        }
    }

    /**
     * Enforces that the generated response is a JSON object that complies with the JSON schema
     * specified in this field.
     */
    @JsonDeserialize(using = ResponseFormat.Deserializer::class)
    @JsonSerialize(using = ResponseFormat.Serializer::class)
    class ResponseFormat
    private constructor(
        private val list: List<InnerResponseFormat>? = null,
        private val audio: AudioResponseFormat? = null,
        private val text: TextResponseFormat? = null,
        private val image: ImageResponseFormat? = null,
        private val video: VideoResponseFormat? = null,
        private val jsonValue: JsonValue? = null,
        private val _json: JsonValue? = null,
    ) {

        fun list(): Optional<List<InnerResponseFormat>> = Optional.ofNullable(list)

        /** Configuration for audio output format. */
        fun audio(): Optional<AudioResponseFormat> = Optional.ofNullable(audio)

        /** Configuration for text output format. */
        fun text(): Optional<TextResponseFormat> = Optional.ofNullable(text)

        /** Configuration for image output format. */
        fun image(): Optional<ImageResponseFormat> = Optional.ofNullable(image)

        /** Configuration for video output format. */
        fun video(): Optional<VideoResponseFormat> = Optional.ofNullable(video)

        fun jsonValue(): Optional<JsonValue> = Optional.ofNullable(jsonValue)

        fun isList(): Boolean = list != null

        fun isAudio(): Boolean = audio != null

        fun isText(): Boolean = text != null

        fun isImage(): Boolean = image != null

        fun isVideo(): Boolean = video != null

        fun isJsonValue(): Boolean = jsonValue != null

        fun asList(): List<InnerResponseFormat> = list.getOrThrow("list")

        /** Configuration for audio output format. */
        fun asAudio(): AudioResponseFormat = audio.getOrThrow("audio")

        /** Configuration for text output format. */
        fun asText(): TextResponseFormat = text.getOrThrow("text")

        /** Configuration for image output format. */
        fun asImage(): ImageResponseFormat = image.getOrThrow("image")

        /** Configuration for video output format. */
        fun asVideo(): VideoResponseFormat = video.getOrThrow("video")

        fun asJsonValue(): JsonValue = jsonValue.getOrThrow("jsonValue")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        fun <T> accept(visitor: Visitor<T>): T =
            when {
                list != null -> visitor.visitList(list)
                audio != null -> visitor.visitAudio(audio)
                text != null -> visitor.visitText(text)
                image != null -> visitor.visitImage(image)
                video != null -> visitor.visitVideo(video)
                jsonValue != null -> visitor.visitJsonValue(jsonValue)
                else -> visitor.unknown(_json)
            }

        private var validated: Boolean = false

        fun validate(): ResponseFormat = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitList(list: List<InnerResponseFormat>) {
                        list.forEach { it.validate() }
                    }

                    override fun visitAudio(audio: AudioResponseFormat) {
                        audio.validate()
                    }

                    override fun visitText(text: TextResponseFormat) {
                        text.validate()
                    }

                    override fun visitImage(image: ImageResponseFormat) {
                        image.validate()
                    }

                    override fun visitVideo(video: VideoResponseFormat) {
                        video.validate()
                    }

                    override fun visitJsonValue(jsonValue: JsonValue) {}
                }
            )
            validated = true
        }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: GeminiNextGenApiInvalidDataException) {
                false
            }

        /**
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic
        internal fun validity(): Int =
            accept(
                object : Visitor<Int> {
                    override fun visitList(list: List<InnerResponseFormat>) =
                        list.sumOf { it.validity().toInt() }

                    override fun visitAudio(audio: AudioResponseFormat) = audio.validity()

                    override fun visitText(text: TextResponseFormat) = text.validity()

                    override fun visitImage(image: ImageResponseFormat) = image.validity()

                    override fun visitVideo(video: VideoResponseFormat) = video.validity()

                    override fun visitJsonValue(jsonValue: JsonValue) = 1

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is ResponseFormat &&
                list == other.list &&
                audio == other.audio &&
                text == other.text &&
                image == other.image &&
                video == other.video &&
                jsonValue == other.jsonValue
        }

        override fun hashCode(): Int = Objects.hash(list, audio, text, image, video, jsonValue)

        override fun toString(): String =
            when {
                list != null -> "ResponseFormat{list=$list}"
                audio != null -> "ResponseFormat{audio=$audio}"
                text != null -> "ResponseFormat{text=$text}"
                image != null -> "ResponseFormat{image=$image}"
                video != null -> "ResponseFormat{video=$video}"
                jsonValue != null -> "ResponseFormat{jsonValue=$jsonValue}"
                _json != null -> "ResponseFormat{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid ResponseFormat")
            }

        companion object {

            @JvmStatic
            fun ofList(list: List<InnerResponseFormat>) = ResponseFormat(list = list.toImmutable())

            /** Configuration for audio output format. */
            @JvmStatic fun ofAudio(audio: AudioResponseFormat) = ResponseFormat(audio = audio)

            /** Configuration for text output format. */
            @JvmStatic fun ofText(text: TextResponseFormat) = ResponseFormat(text = text)

            /** Configuration for image output format. */
            @JvmStatic fun ofImage(image: ImageResponseFormat) = ResponseFormat(image = image)

            /** Configuration for video output format. */
            @JvmStatic fun ofVideo(video: VideoResponseFormat) = ResponseFormat(video = video)

            @JvmStatic fun ofJsonValue(jsonValue: JsonValue) = ResponseFormat(jsonValue = jsonValue)
        }

        /**
         * An interface that defines how to map each variant of [ResponseFormat] to a value of type
         * [T].
         */
        interface Visitor<out T> {

            fun visitList(list: List<InnerResponseFormat>): T

            /** Configuration for audio output format. */
            fun visitAudio(audio: AudioResponseFormat): T

            /** Configuration for text output format. */
            fun visitText(text: TextResponseFormat): T

            /** Configuration for image output format. */
            fun visitImage(image: ImageResponseFormat): T

            /** Configuration for video output format. */
            fun visitVideo(video: VideoResponseFormat): T

            fun visitJsonValue(jsonValue: JsonValue): T

            /**
             * Maps an unknown variant of [ResponseFormat] to a value of type [T].
             *
             * An instance of [ResponseFormat] can contain an unknown variant if it was deserialized
             * from data that doesn't match any known variant. For example, if the SDK is on an
             * older version than the API, then the API may respond with new variants that the SDK
             * is unaware of.
             *
             * @throws GeminiNextGenApiInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw GeminiNextGenApiInvalidDataException("Unknown ResponseFormat: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<ResponseFormat>(ResponseFormat::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): ResponseFormat {
                val json = JsonValue.fromJsonNode(node)

                val bestMatches =
                    sequenceOf(
                            tryDeserialize(node, jacksonTypeRef<AudioResponseFormat>())?.let {
                                ResponseFormat(audio = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<TextResponseFormat>())?.let {
                                ResponseFormat(text = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<ImageResponseFormat>())?.let {
                                ResponseFormat(image = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<VideoResponseFormat>())?.let {
                                ResponseFormat(video = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<List<InnerResponseFormat>>())?.let {
                                ResponseFormat(list = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<JsonValue>())?.let {
                                ResponseFormat(jsonValue = it, _json = json)
                            },
                        )
                        .filterNotNull()
                        .allMaxBy { it.validity() }
                        .toList()
                return when (bestMatches.size) {
                    // This can happen if what we're deserializing is completely incompatible with
                    // all the possible variants.
                    0 -> ResponseFormat(_json = json)
                    1 -> bestMatches.single()
                    // If there's more than one match with the highest validity, then use the first
                    // completely valid match, or simply the first match if none are completely
                    // valid.
                    else -> bestMatches.firstOrNull { it.isValid() } ?: bestMatches.first()
                }
            }
        }

        internal class Serializer : BaseSerializer<ResponseFormat>(ResponseFormat::class) {

            override fun serialize(
                value: ResponseFormat,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.list != null -> generator.writeObject(value.list)
                    value.audio != null -> generator.writeObject(value.audio)
                    value.text != null -> generator.writeObject(value.text)
                    value.image != null -> generator.writeObject(value.image)
                    value.video != null -> generator.writeObject(value.video)
                    value.jsonValue != null -> generator.writeObject(value.jsonValue)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid ResponseFormat")
                }
            }
        }

        /** Configuration for audio output format. */
        @JsonDeserialize(using = InnerResponseFormat.Deserializer::class)
        @JsonSerialize(using = InnerResponseFormat.Serializer::class)
        class InnerResponseFormat
        private constructor(
            private val audio: AudioResponseFormat? = null,
            private val text: TextResponseFormat? = null,
            private val image: ImageResponseFormat? = null,
            private val video: VideoResponseFormat? = null,
            private val jsonValue: JsonValue? = null,
            private val _json: JsonValue? = null,
        ) {

            /** Configuration for audio output format. */
            fun audio(): Optional<AudioResponseFormat> = Optional.ofNullable(audio)

            /** Configuration for text output format. */
            fun text(): Optional<TextResponseFormat> = Optional.ofNullable(text)

            /** Configuration for image output format. */
            fun image(): Optional<ImageResponseFormat> = Optional.ofNullable(image)

            /** Configuration for video output format. */
            fun video(): Optional<VideoResponseFormat> = Optional.ofNullable(video)

            fun jsonValue(): Optional<JsonValue> = Optional.ofNullable(jsonValue)

            fun isAudio(): Boolean = audio != null

            fun isText(): Boolean = text != null

            fun isImage(): Boolean = image != null

            fun isVideo(): Boolean = video != null

            fun isJsonValue(): Boolean = jsonValue != null

            /** Configuration for audio output format. */
            fun asAudio(): AudioResponseFormat = audio.getOrThrow("audio")

            /** Configuration for text output format. */
            fun asText(): TextResponseFormat = text.getOrThrow("text")

            /** Configuration for image output format. */
            fun asImage(): ImageResponseFormat = image.getOrThrow("image")

            /** Configuration for video output format. */
            fun asVideo(): VideoResponseFormat = video.getOrThrow("video")

            fun asJsonValue(): JsonValue = jsonValue.getOrThrow("jsonValue")

            fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

            fun <T> accept(visitor: Visitor<T>): T =
                when {
                    audio != null -> visitor.visitAudio(audio)
                    text != null -> visitor.visitText(text)
                    image != null -> visitor.visitImage(image)
                    video != null -> visitor.visitVideo(video)
                    jsonValue != null -> visitor.visitJsonValue(jsonValue)
                    else -> visitor.unknown(_json)
                }

            private var validated: Boolean = false

            fun validate(): InnerResponseFormat = apply {
                if (validated) {
                    return@apply
                }

                accept(
                    object : Visitor<Unit> {
                        override fun visitAudio(audio: AudioResponseFormat) {
                            audio.validate()
                        }

                        override fun visitText(text: TextResponseFormat) {
                            text.validate()
                        }

                        override fun visitImage(image: ImageResponseFormat) {
                            image.validate()
                        }

                        override fun visitVideo(video: VideoResponseFormat) {
                            video.validate()
                        }

                        override fun visitJsonValue(jsonValue: JsonValue) {}
                    }
                )
                validated = true
            }

            fun isValid(): Boolean =
                try {
                    validate()
                    true
                } catch (e: GeminiNextGenApiInvalidDataException) {
                    false
                }

            /**
             * Returns a score indicating how many valid values are contained in this object
             * recursively.
             *
             * Used for best match union deserialization.
             */
            @JvmSynthetic
            internal fun validity(): Int =
                accept(
                    object : Visitor<Int> {
                        override fun visitAudio(audio: AudioResponseFormat) = audio.validity()

                        override fun visitText(text: TextResponseFormat) = text.validity()

                        override fun visitImage(image: ImageResponseFormat) = image.validity()

                        override fun visitVideo(video: VideoResponseFormat) = video.validity()

                        override fun visitJsonValue(jsonValue: JsonValue) = 1

                        override fun unknown(json: JsonValue?) = 0
                    }
                )

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is InnerResponseFormat &&
                    audio == other.audio &&
                    text == other.text &&
                    image == other.image &&
                    video == other.video &&
                    jsonValue == other.jsonValue
            }

            override fun hashCode(): Int = Objects.hash(audio, text, image, video, jsonValue)

            override fun toString(): String =
                when {
                    audio != null -> "InnerResponseFormat{audio=$audio}"
                    text != null -> "InnerResponseFormat{text=$text}"
                    image != null -> "InnerResponseFormat{image=$image}"
                    video != null -> "InnerResponseFormat{video=$video}"
                    jsonValue != null -> "InnerResponseFormat{jsonValue=$jsonValue}"
                    _json != null -> "InnerResponseFormat{_unknown=$_json}"
                    else -> throw IllegalStateException("Invalid InnerResponseFormat")
                }

            companion object {

                /** Configuration for audio output format. */
                @JvmStatic
                fun ofAudio(audio: AudioResponseFormat) = InnerResponseFormat(audio = audio)

                /** Configuration for text output format. */
                @JvmStatic fun ofText(text: TextResponseFormat) = InnerResponseFormat(text = text)

                /** Configuration for image output format. */
                @JvmStatic
                fun ofImage(image: ImageResponseFormat) = InnerResponseFormat(image = image)

                /** Configuration for video output format. */
                @JvmStatic
                fun ofVideo(video: VideoResponseFormat) = InnerResponseFormat(video = video)

                @JvmStatic
                fun ofJsonValue(jsonValue: JsonValue) = InnerResponseFormat(jsonValue = jsonValue)
            }

            /**
             * An interface that defines how to map each variant of [InnerResponseFormat] to a value
             * of type [T].
             */
            interface Visitor<out T> {

                /** Configuration for audio output format. */
                fun visitAudio(audio: AudioResponseFormat): T

                /** Configuration for text output format. */
                fun visitText(text: TextResponseFormat): T

                /** Configuration for image output format. */
                fun visitImage(image: ImageResponseFormat): T

                /** Configuration for video output format. */
                fun visitVideo(video: VideoResponseFormat): T

                fun visitJsonValue(jsonValue: JsonValue): T

                /**
                 * Maps an unknown variant of [InnerResponseFormat] to a value of type [T].
                 *
                 * An instance of [InnerResponseFormat] can contain an unknown variant if it was
                 * deserialized from data that doesn't match any known variant. For example, if the
                 * SDK is on an older version than the API, then the API may respond with new
                 * variants that the SDK is unaware of.
                 *
                 * @throws GeminiNextGenApiInvalidDataException in the default implementation.
                 */
                fun unknown(json: JsonValue?): T {
                    throw GeminiNextGenApiInvalidDataException("Unknown InnerResponseFormat: $json")
                }
            }

            internal class Deserializer :
                BaseDeserializer<InnerResponseFormat>(InnerResponseFormat::class) {

                override fun ObjectCodec.deserialize(node: JsonNode): InnerResponseFormat {
                    val json = JsonValue.fromJsonNode(node)

                    val bestMatches =
                        sequenceOf(
                                tryDeserialize(node, jacksonTypeRef<AudioResponseFormat>())?.let {
                                    InnerResponseFormat(audio = it, _json = json)
                                },
                                tryDeserialize(node, jacksonTypeRef<TextResponseFormat>())?.let {
                                    InnerResponseFormat(text = it, _json = json)
                                },
                                tryDeserialize(node, jacksonTypeRef<ImageResponseFormat>())?.let {
                                    InnerResponseFormat(image = it, _json = json)
                                },
                                tryDeserialize(node, jacksonTypeRef<VideoResponseFormat>())?.let {
                                    InnerResponseFormat(video = it, _json = json)
                                },
                                tryDeserialize(node, jacksonTypeRef<JsonValue>())?.let {
                                    InnerResponseFormat(jsonValue = it, _json = json)
                                },
                            )
                            .filterNotNull()
                            .allMaxBy { it.validity() }
                            .toList()
                    return when (bestMatches.size) {
                        // This can happen if what we're deserializing is completely incompatible
                        // with all the possible variants.
                        0 -> InnerResponseFormat(_json = json)
                        1 -> bestMatches.single()
                        // If there's more than one match with the highest validity, then use the
                        // first completely valid match, or simply the first match if none are
                        // completely valid.
                        else -> bestMatches.firstOrNull { it.isValid() } ?: bestMatches.first()
                    }
                }
            }

            internal class Serializer :
                BaseSerializer<InnerResponseFormat>(InnerResponseFormat::class) {

                override fun serialize(
                    value: InnerResponseFormat,
                    generator: JsonGenerator,
                    provider: SerializerProvider,
                ) {
                    when {
                        value.audio != null -> generator.writeObject(value.audio)
                        value.text != null -> generator.writeObject(value.text)
                        value.image != null -> generator.writeObject(value.image)
                        value.video != null -> generator.writeObject(value.video)
                        value.jsonValue != null -> generator.writeObject(value.jsonValue)
                        value._json != null -> generator.writeObject(value._json)
                        else -> throw IllegalStateException("Invalid InnerResponseFormat")
                    }
                }
            }
        }
    }

    class ResponseModality @JsonCreator private constructor(private val value: JsonField<String>) :
        Enum {

        /**
         * Returns this class instance's raw value.
         *
         * This is usually only useful if this instance was deserialized from data that doesn't
         * match any known member, and you want to know that value. For example, if the SDK is on an
         * older version than the API, then the API may respond with new members that the SDK is
         * unaware of.
         */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val TEXT = of("text")

            @JvmField val IMAGE = of("image")

            @JvmField val AUDIO = of("audio")

            @JvmField val VIDEO = of("video")

            @JvmField val DOCUMENT = of("document")

            @JvmStatic fun of(value: String) = ResponseModality(JsonField.of(value))
        }

        /** An enum containing [ResponseModality]'s known values. */
        enum class Known {
            TEXT,
            IMAGE,
            AUDIO,
            VIDEO,
            DOCUMENT,
        }

        /**
         * An enum containing [ResponseModality]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [ResponseModality] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            TEXT,
            IMAGE,
            AUDIO,
            VIDEO,
            DOCUMENT,
            /**
             * An enum member indicating that [ResponseModality] was instantiated with an unknown
             * value.
             */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         *
         * Use the [known] method instead if you're certain the value is always known or if you want
         * to throw for the unknown case.
         */
        fun value(): Value =
            when (this) {
                TEXT -> Value.TEXT
                IMAGE -> Value.IMAGE
                AUDIO -> Value.AUDIO
                VIDEO -> Value.VIDEO
                DOCUMENT -> Value.DOCUMENT
                else -> Value._UNKNOWN
            }

        /**
         * Returns an enum member corresponding to this class instance's value.
         *
         * Use the [value] method instead if you're uncertain the value is always known and don't
         * want to throw for the unknown case.
         *
         * @throws GeminiNextGenApiInvalidDataException if this class instance's value is a not a
         *   known member.
         */
        fun known(): Known =
            when (this) {
                TEXT -> Known.TEXT
                IMAGE -> Known.IMAGE
                AUDIO -> Known.AUDIO
                VIDEO -> Known.VIDEO
                DOCUMENT -> Known.DOCUMENT
                else ->
                    throw GeminiNextGenApiInvalidDataException("Unknown ResponseModality: $value")
            }

        /**
         * Returns this class instance's primitive wire representation.
         *
         * This differs from the [toString] method because that method is primarily for debugging
         * and generally doesn't throw.
         *
         * @throws GeminiNextGenApiInvalidDataException if this class instance's value does not have
         *   the expected primitive type.
         */
        fun asString(): String =
            _value().asString().orElseThrow {
                GeminiNextGenApiInvalidDataException("Value is not a String")
            }

        private var validated: Boolean = false

        fun validate(): ResponseModality = apply {
            if (validated) {
                return@apply
            }

            known()
            validated = true
        }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: GeminiNextGenApiInvalidDataException) {
                false
            }

        /**
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is ResponseModality && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    /** The service tier for the interaction. */
    class ServiceTier @JsonCreator private constructor(private val value: JsonField<String>) :
        Enum {

        /**
         * Returns this class instance's raw value.
         *
         * This is usually only useful if this instance was deserialized from data that doesn't
         * match any known member, and you want to know that value. For example, if the SDK is on an
         * older version than the API, then the API may respond with new members that the SDK is
         * unaware of.
         */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val FLEX = of("flex")

            @JvmField val STANDARD = of("standard")

            @JvmField val PRIORITY = of("priority")

            @JvmStatic fun of(value: String) = ServiceTier(JsonField.of(value))
        }

        /** An enum containing [ServiceTier]'s known values. */
        enum class Known {
            FLEX,
            STANDARD,
            PRIORITY,
        }

        /**
         * An enum containing [ServiceTier]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [ServiceTier] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            FLEX,
            STANDARD,
            PRIORITY,
            /**
             * An enum member indicating that [ServiceTier] was instantiated with an unknown value.
             */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         *
         * Use the [known] method instead if you're certain the value is always known or if you want
         * to throw for the unknown case.
         */
        fun value(): Value =
            when (this) {
                FLEX -> Value.FLEX
                STANDARD -> Value.STANDARD
                PRIORITY -> Value.PRIORITY
                else -> Value._UNKNOWN
            }

        /**
         * Returns an enum member corresponding to this class instance's value.
         *
         * Use the [value] method instead if you're uncertain the value is always known and don't
         * want to throw for the unknown case.
         *
         * @throws GeminiNextGenApiInvalidDataException if this class instance's value is a not a
         *   known member.
         */
        fun known(): Known =
            when (this) {
                FLEX -> Known.FLEX
                STANDARD -> Known.STANDARD
                PRIORITY -> Known.PRIORITY
                else -> throw GeminiNextGenApiInvalidDataException("Unknown ServiceTier: $value")
            }

        /**
         * Returns this class instance's primitive wire representation.
         *
         * This differs from the [toString] method because that method is primarily for debugging
         * and generally doesn't throw.
         *
         * @throws GeminiNextGenApiInvalidDataException if this class instance's value does not have
         *   the expected primitive type.
         */
        fun asString(): String =
            _value().asString().orElseThrow {
                GeminiNextGenApiInvalidDataException("Value is not a String")
            }

        private var validated: Boolean = false

        fun validate(): ServiceTier = apply {
            if (validated) {
                return@apply
            }

            known()
            validated = true
        }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: GeminiNextGenApiInvalidDataException) {
                false
            }

        /**
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is ServiceTier && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    /** Required. Output only. The status of the interaction. */
    class Status @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

        /**
         * Returns this class instance's raw value.
         *
         * This is usually only useful if this instance was deserialized from data that doesn't
         * match any known member, and you want to know that value. For example, if the SDK is on an
         * older version than the API, then the API may respond with new members that the SDK is
         * unaware of.
         */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val IN_PROGRESS = of("in_progress")

            @JvmField val REQUIRES_ACTION = of("requires_action")

            @JvmField val COMPLETED = of("completed")

            @JvmField val FAILED = of("failed")

            @JvmField val CANCELLED = of("cancelled")

            @JvmField val INCOMPLETE = of("incomplete")

            @JvmStatic fun of(value: String) = Status(JsonField.of(value))
        }

        /** An enum containing [Status]'s known values. */
        enum class Known {
            IN_PROGRESS,
            REQUIRES_ACTION,
            COMPLETED,
            FAILED,
            CANCELLED,
            INCOMPLETE,
        }

        /**
         * An enum containing [Status]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Status] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            IN_PROGRESS,
            REQUIRES_ACTION,
            COMPLETED,
            FAILED,
            CANCELLED,
            INCOMPLETE,
            /** An enum member indicating that [Status] was instantiated with an unknown value. */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         *
         * Use the [known] method instead if you're certain the value is always known or if you want
         * to throw for the unknown case.
         */
        fun value(): Value =
            when (this) {
                IN_PROGRESS -> Value.IN_PROGRESS
                REQUIRES_ACTION -> Value.REQUIRES_ACTION
                COMPLETED -> Value.COMPLETED
                FAILED -> Value.FAILED
                CANCELLED -> Value.CANCELLED
                INCOMPLETE -> Value.INCOMPLETE
                else -> Value._UNKNOWN
            }

        /**
         * Returns an enum member corresponding to this class instance's value.
         *
         * Use the [value] method instead if you're uncertain the value is always known and don't
         * want to throw for the unknown case.
         *
         * @throws GeminiNextGenApiInvalidDataException if this class instance's value is a not a
         *   known member.
         */
        fun known(): Known =
            when (this) {
                IN_PROGRESS -> Known.IN_PROGRESS
                REQUIRES_ACTION -> Known.REQUIRES_ACTION
                COMPLETED -> Known.COMPLETED
                FAILED -> Known.FAILED
                CANCELLED -> Known.CANCELLED
                INCOMPLETE -> Known.INCOMPLETE
                else -> throw GeminiNextGenApiInvalidDataException("Unknown Status: $value")
            }

        /**
         * Returns this class instance's primitive wire representation.
         *
         * This differs from the [toString] method because that method is primarily for debugging
         * and generally doesn't throw.
         *
         * @throws GeminiNextGenApiInvalidDataException if this class instance's value does not have
         *   the expected primitive type.
         */
        fun asString(): String =
            _value().asString().orElseThrow {
                GeminiNextGenApiInvalidDataException("Value is not a String")
            }

        private var validated: Boolean = false

        fun validate(): Status = apply {
            if (validated) {
                return@apply
            }

            known()
            validated = true
        }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: GeminiNextGenApiInvalidDataException) {
                false
            }

        /**
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Status && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    /** Message for configuring webhook events for a request. */
    class WebhookConfig
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val uris: JsonField<List<String>>,
        private val userMetadata: JsonField<UserMetadata>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("uris") @ExcludeMissing uris: JsonField<List<String>> = JsonMissing.of(),
            @JsonProperty("user_metadata")
            @ExcludeMissing
            userMetadata: JsonField<UserMetadata> = JsonMissing.of(),
        ) : this(uris, userMetadata, mutableMapOf())

        /**
         * Optional. If set, these webhook URIs will be used for webhook events instead of the
         * registered webhooks.
         *
         * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
         *   (e.g. if the server responded with an unexpected value).
         */
        fun uris(): Optional<List<String>> = uris.getOptional("uris")

        /**
         * Optional. The user metadata that will be returned on each event emission to the webhooks.
         *
         * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
         *   (e.g. if the server responded with an unexpected value).
         */
        fun userMetadata(): Optional<UserMetadata> = userMetadata.getOptional("user_metadata")

        /**
         * Returns the raw JSON value of [uris].
         *
         * Unlike [uris], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("uris") @ExcludeMissing fun _uris(): JsonField<List<String>> = uris

        /**
         * Returns the raw JSON value of [userMetadata].
         *
         * Unlike [userMetadata], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("user_metadata")
        @ExcludeMissing
        fun _userMetadata(): JsonField<UserMetadata> = userMetadata

        @JsonAnySetter
        private fun putAdditionalProperty(key: String, value: JsonValue) {
            additionalProperties.put(key, value)
        }

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> =
            Collections.unmodifiableMap(additionalProperties)

        fun toBuilder() = Builder().from(this)

        companion object {

            /** Returns a mutable builder for constructing an instance of [WebhookConfig]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [WebhookConfig]. */
        class Builder internal constructor() {

            private var uris: JsonField<MutableList<String>>? = null
            private var userMetadata: JsonField<UserMetadata> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(webhookConfig: WebhookConfig) = apply {
                uris = webhookConfig.uris.map { it.toMutableList() }
                userMetadata = webhookConfig.userMetadata
                additionalProperties = webhookConfig.additionalProperties.toMutableMap()
            }

            /**
             * Optional. If set, these webhook URIs will be used for webhook events instead of the
             * registered webhooks.
             */
            fun uris(uris: List<String>) = uris(JsonField.of(uris))

            /**
             * Sets [Builder.uris] to an arbitrary JSON value.
             *
             * You should usually call [Builder.uris] with a well-typed `List<String>` value
             * instead. This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun uris(uris: JsonField<List<String>>) = apply {
                this.uris = uris.map { it.toMutableList() }
            }

            /**
             * Adds a single [String] to [Builder.uris].
             *
             * @throws IllegalStateException if the field was previously set to a non-list.
             */
            fun addUris(uris: String) = apply {
                this.uris =
                    (this.uris ?: JsonField.of(mutableListOf())).also {
                        checkKnown("uris", it).add(uris)
                    }
            }

            /**
             * Optional. The user metadata that will be returned on each event emission to the
             * webhooks.
             */
            fun userMetadata(userMetadata: UserMetadata) = userMetadata(JsonField.of(userMetadata))

            /**
             * Sets [Builder.userMetadata] to an arbitrary JSON value.
             *
             * You should usually call [Builder.userMetadata] with a well-typed [UserMetadata] value
             * instead. This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun userMetadata(userMetadata: JsonField<UserMetadata>) = apply {
                this.userMetadata = userMetadata
            }

            fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.clear()
                putAllAdditionalProperties(additionalProperties)
            }

            fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                additionalProperties.put(key, value)
            }

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            /**
             * Returns an immutable instance of [WebhookConfig].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): WebhookConfig =
                WebhookConfig(
                    (uris ?: JsonMissing.of()).map { it.toImmutable() },
                    userMetadata,
                    additionalProperties.toMutableMap(),
                )
        }

        private var validated: Boolean = false

        fun validate(): WebhookConfig = apply {
            if (validated) {
                return@apply
            }

            uris()
            userMetadata().ifPresent { it.validate() }
            validated = true
        }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: GeminiNextGenApiInvalidDataException) {
                false
            }

        /**
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic
        internal fun validity(): Int =
            (uris.asKnown().getOrNull()?.size ?: 0) +
                (userMetadata.asKnown().getOrNull()?.validity() ?: 0)

        /**
         * Optional. The user metadata that will be returned on each event emission to the webhooks.
         */
        class UserMetadata
        @JsonCreator
        private constructor(
            @com.fasterxml.jackson.annotation.JsonValue
            private val additionalProperties: Map<String, JsonValue>
        ) {

            @JsonAnyGetter
            @ExcludeMissing
            fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

            fun toBuilder() = Builder().from(this)

            companion object {

                /** Returns a mutable builder for constructing an instance of [UserMetadata]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [UserMetadata]. */
            class Builder internal constructor() {

                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(userMetadata: UserMetadata) = apply {
                    additionalProperties = userMetadata.additionalProperties.toMutableMap()
                }

                fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                    this.additionalProperties.clear()
                    putAllAdditionalProperties(additionalProperties)
                }

                fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                    additionalProperties.put(key, value)
                }

                fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) =
                    apply {
                        this.additionalProperties.putAll(additionalProperties)
                    }

                fun removeAdditionalProperty(key: String) = apply {
                    additionalProperties.remove(key)
                }

                fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                    keys.forEach(::removeAdditionalProperty)
                }

                /**
                 * Returns an immutable instance of [UserMetadata].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 */
                fun build(): UserMetadata = UserMetadata(additionalProperties.toImmutable())
            }

            private var validated: Boolean = false

            fun validate(): UserMetadata = apply {
                if (validated) {
                    return@apply
                }

                validated = true
            }

            fun isValid(): Boolean =
                try {
                    validate()
                    true
                } catch (e: GeminiNextGenApiInvalidDataException) {
                    false
                }

            /**
             * Returns a score indicating how many valid values are contained in this object
             * recursively.
             *
             * Used for best match union deserialization.
             */
            @JvmSynthetic
            internal fun validity(): Int =
                additionalProperties.count { (_, value) -> !value.isNull() && !value.isMissing() }

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is UserMetadata && additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy { Objects.hash(additionalProperties) }

            override fun hashCode(): Int = hashCode

            override fun toString() = "UserMetadata{additionalProperties=$additionalProperties}"
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is WebhookConfig &&
                uris == other.uris &&
                userMetadata == other.userMetadata &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(uris, userMetadata, additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "WebhookConfig{uris=$uris, userMetadata=$userMetadata, additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is CreateAgentInteractionParams &&
            agent == other.agent &&
            input == other.input &&
            id == other.id &&
            agentConfig == other.agentConfig &&
            background == other.background &&
            created == other.created &&
            previousInteractionId == other.previousInteractionId &&
            responseFormat == other.responseFormat &&
            responseMimeType == other.responseMimeType &&
            responseModalities == other.responseModalities &&
            role == other.role &&
            serviceTier == other.serviceTier &&
            status == other.status &&
            steps == other.steps &&
            store == other.store &&
            stream == other.stream &&
            systemInstruction == other.systemInstruction &&
            tools == other.tools &&
            updated == other.updated &&
            usage == other.usage &&
            webhookConfig == other.webhookConfig &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            agent,
            input,
            id,
            agentConfig,
            background,
            created,
            previousInteractionId,
            responseFormat,
            responseMimeType,
            responseModalities,
            role,
            serviceTier,
            status,
            steps,
            store,
            stream,
            systemInstruction,
            tools,
            updated,
            usage,
            webhookConfig,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "CreateAgentInteractionParams{agent=$agent, input=$input, id=$id, agentConfig=$agentConfig, background=$background, created=$created, previousInteractionId=$previousInteractionId, responseFormat=$responseFormat, responseMimeType=$responseMimeType, responseModalities=$responseModalities, role=$role, serviceTier=$serviceTier, status=$status, steps=$steps, store=$store, stream=$stream, systemInstruction=$systemInstruction, tools=$tools, updated=$updated, usage=$usage, webhookConfig=$webhookConfig, additionalProperties=$additionalProperties}"
}
