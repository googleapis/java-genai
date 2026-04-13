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

/** Parameters for creating model interactions */
class CreateModelInteractionParams
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val input: JsonField<Input>,
    private val model: JsonField<Model>,
    private val id: JsonField<String>,
    private val background: JsonField<Boolean>,
    private val created: JsonField<OffsetDateTime>,
    private val generationConfig: JsonField<GenerationConfig>,
    private val outputs: JsonField<List<Content>>,
    private val previousInteractionId: JsonField<String>,
    private val responseFormat: JsonValue,
    private val responseMimeType: JsonField<String>,
    private val responseModalities: JsonField<List<ResponseModality>>,
    private val role: JsonField<String>,
    private val serviceTier: JsonField<ServiceTier>,
    private val status: JsonField<Status>,
    private val store: JsonField<Boolean>,
    private val stream: JsonField<Boolean>,
    private val systemInstruction: JsonField<String>,
    private val tools: JsonField<List<Tool>>,
    private val updated: JsonField<OffsetDateTime>,
    private val usage: JsonField<Usage>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("input") @ExcludeMissing input: JsonField<Input> = JsonMissing.of(),
        @JsonProperty("model") @ExcludeMissing model: JsonField<Model> = JsonMissing.of(),
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("background")
        @ExcludeMissing
        background: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("created")
        @ExcludeMissing
        created: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("generation_config")
        @ExcludeMissing
        generationConfig: JsonField<GenerationConfig> = JsonMissing.of(),
        @JsonProperty("outputs")
        @ExcludeMissing
        outputs: JsonField<List<Content>> = JsonMissing.of(),
        @JsonProperty("previous_interaction_id")
        @ExcludeMissing
        previousInteractionId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("response_format")
        @ExcludeMissing
        responseFormat: JsonValue = JsonMissing.of(),
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
    ) : this(
        input,
        model,
        id,
        background,
        created,
        generationConfig,
        outputs,
        previousInteractionId,
        responseFormat,
        responseMimeType,
        responseModalities,
        role,
        serviceTier,
        status,
        store,
        stream,
        systemInstruction,
        tools,
        updated,
        usage,
        mutableMapOf(),
    )

    /**
     * The input for the interaction.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun input(): Input = input.getRequired("input")

    /**
     * The model that will complete your prompt.\n\nSee
     * [models](https://ai.google.dev/gemini-api/docs/models) for additional details.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun model(): Model = model.getRequired("model")

    /**
     * Required. Output only. A unique identifier for the interaction completion.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun id(): Optional<String> = id.getOptional("id")

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
     * Input only. Configuration parameters for the model interaction.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun generationConfig(): Optional<GenerationConfig> =
        generationConfig.getOptional("generation_config")

    /**
     * Output only. Responses from the model.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun outputs(): Optional<List<Content>> = outputs.getOptional("outputs")

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
     * This arbitrary value can be deserialized into a custom type using the `convert` method:
     * ```java
     * MyClass myObject = createModelInteractionParams.responseFormat().convert(MyClass.class);
     * ```
     */
    @JsonProperty("response_format")
    @ExcludeMissing
    fun _responseFormat(): JsonValue = responseFormat

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
    fun role(): Optional<String> = role.getOptional("role")

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
     * Returns the raw JSON value of [input].
     *
     * Unlike [input], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("input") @ExcludeMissing fun _input(): JsonField<Input> = input

    /**
     * Returns the raw JSON value of [model].
     *
     * Unlike [model], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("model") @ExcludeMissing fun _model(): JsonField<Model> = model

    /**
     * Returns the raw JSON value of [id].
     *
     * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

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
     * Returns the raw JSON value of [generationConfig].
     *
     * Unlike [generationConfig], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("generation_config")
    @ExcludeMissing
    fun _generationConfig(): JsonField<GenerationConfig> = generationConfig

    /**
     * Returns the raw JSON value of [outputs].
     *
     * Unlike [outputs], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("outputs") @ExcludeMissing fun _outputs(): JsonField<List<Content>> = outputs

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
    @JsonProperty("role") @ExcludeMissing fun _role(): JsonField<String> = role

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
         * Returns a mutable builder for constructing an instance of [CreateModelInteractionParams].
         *
         * The following fields are required:
         * ```java
         * .input()
         * .model()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [CreateModelInteractionParams]. */
    class Builder internal constructor() {

        private var input: JsonField<Input>? = null
        private var model: JsonField<Model>? = null
        private var id: JsonField<String> = JsonMissing.of()
        private var background: JsonField<Boolean> = JsonMissing.of()
        private var created: JsonField<OffsetDateTime> = JsonMissing.of()
        private var generationConfig: JsonField<GenerationConfig> = JsonMissing.of()
        private var outputs: JsonField<MutableList<Content>>? = null
        private var previousInteractionId: JsonField<String> = JsonMissing.of()
        private var responseFormat: JsonValue = JsonMissing.of()
        private var responseMimeType: JsonField<String> = JsonMissing.of()
        private var responseModalities: JsonField<MutableList<ResponseModality>>? = null
        private var role: JsonField<String> = JsonMissing.of()
        private var serviceTier: JsonField<ServiceTier> = JsonMissing.of()
        private var status: JsonField<Status> = JsonMissing.of()
        private var store: JsonField<Boolean> = JsonMissing.of()
        private var stream: JsonField<Boolean> = JsonMissing.of()
        private var systemInstruction: JsonField<String> = JsonMissing.of()
        private var tools: JsonField<MutableList<Tool>>? = null
        private var updated: JsonField<OffsetDateTime> = JsonMissing.of()
        private var usage: JsonField<Usage> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(createModelInteractionParams: CreateModelInteractionParams) = apply {
            input = createModelInteractionParams.input
            model = createModelInteractionParams.model
            id = createModelInteractionParams.id
            background = createModelInteractionParams.background
            created = createModelInteractionParams.created
            generationConfig = createModelInteractionParams.generationConfig
            outputs = createModelInteractionParams.outputs.map { it.toMutableList() }
            previousInteractionId = createModelInteractionParams.previousInteractionId
            responseFormat = createModelInteractionParams.responseFormat
            responseMimeType = createModelInteractionParams.responseMimeType
            responseModalities =
                createModelInteractionParams.responseModalities.map { it.toMutableList() }
            role = createModelInteractionParams.role
            serviceTier = createModelInteractionParams.serviceTier
            status = createModelInteractionParams.status
            store = createModelInteractionParams.store
            stream = createModelInteractionParams.stream
            systemInstruction = createModelInteractionParams.systemInstruction
            tools = createModelInteractionParams.tools.map { it.toMutableList() }
            updated = createModelInteractionParams.updated
            usage = createModelInteractionParams.usage
            additionalProperties = createModelInteractionParams.additionalProperties.toMutableMap()
        }

        /** The input for the interaction. */
        fun input(input: Input) = input(JsonField.of(input))

        /**
         * Sets [Builder.input] to an arbitrary JSON value.
         *
         * You should usually call [Builder.input] with a well-typed [Input] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun input(input: JsonField<Input>) = apply { this.input = input }

        /** Alias for calling [input] with `Input.ofContentList(contentList)`. */
        fun inputOfContentList(contentList: List<Content>) = input(Input.ofContentList(contentList))

        /** Alias for calling [input] with `Input.ofString(string)`. */
        fun input(string: String) = input(Input.ofString(string))

        /** Alias for calling [input] with `Input.ofTurnList(turnList)`. */
        fun inputOfTurnList(turnList: List<Turn>) = input(Input.ofTurnList(turnList))

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

        /** Alias for calling [input] with `Input.ofThoughtContent(thoughtContent)`. */
        fun input(thoughtContent: ThoughtContent) = input(Input.ofThoughtContent(thoughtContent))

        /** Alias for calling [input] with `Input.ofFunctionCallContent(functionCallContent)`. */
        fun input(functionCallContent: FunctionCallContent) =
            input(Input.ofFunctionCallContent(functionCallContent))

        /**
         * Alias for calling [input] with
         * `Input.ofCodeExecutionCallContent(codeExecutionCallContent)`.
         */
        fun input(codeExecutionCallContent: CodeExecutionCallContent) =
            input(Input.ofCodeExecutionCallContent(codeExecutionCallContent))

        /**
         * Alias for calling [input] with `Input.ofUrlContextCallContent(urlContextCallContent)`.
         */
        fun input(urlContextCallContent: UrlContextCallContent) =
            input(Input.ofUrlContextCallContent(urlContextCallContent))

        /**
         * Alias for calling [input] with
         * `Input.ofMcpServerToolCallContent(mcpServerToolCallContent)`.
         */
        fun input(mcpServerToolCallContent: McpServerToolCallContent) =
            input(Input.ofMcpServerToolCallContent(mcpServerToolCallContent))

        /**
         * Alias for calling [input] with
         * `Input.ofGoogleSearchCallContent(googleSearchCallContent)`.
         */
        fun input(googleSearchCallContent: GoogleSearchCallContent) =
            input(Input.ofGoogleSearchCallContent(googleSearchCallContent))

        /**
         * Alias for calling [input] with `Input.ofFileSearchCallContent(fileSearchCallContent)`.
         */
        fun input(fileSearchCallContent: FileSearchCallContent) =
            input(Input.ofFileSearchCallContent(fileSearchCallContent))

        /**
         * Alias for calling [input] with `Input.ofGoogleMapsCallContent(googleMapsCallContent)`.
         */
        fun input(googleMapsCallContent: GoogleMapsCallContent) =
            input(Input.ofGoogleMapsCallContent(googleMapsCallContent))

        /**
         * Alias for calling [input] with `Input.ofFunctionResultContent(functionResultContent)`.
         */
        fun input(functionResultContent: FunctionResultContent) =
            input(Input.ofFunctionResultContent(functionResultContent))

        /**
         * Alias for calling [input] with
         * `Input.ofCodeExecutionResultContent(codeExecutionResultContent)`.
         */
        fun input(codeExecutionResultContent: CodeExecutionResultContent) =
            input(Input.ofCodeExecutionResultContent(codeExecutionResultContent))

        /**
         * Alias for calling [input] with
         * `Input.ofUrlContextResultContent(urlContextResultContent)`.
         */
        fun input(urlContextResultContent: UrlContextResultContent) =
            input(Input.ofUrlContextResultContent(urlContextResultContent))

        /**
         * Alias for calling [input] with
         * `Input.ofGoogleSearchResultContent(googleSearchResultContent)`.
         */
        fun input(googleSearchResultContent: GoogleSearchResultContent) =
            input(Input.ofGoogleSearchResultContent(googleSearchResultContent))

        /**
         * Alias for calling [input] with
         * `Input.ofMcpServerToolResultContent(mcpServerToolResultContent)`.
         */
        fun input(mcpServerToolResultContent: McpServerToolResultContent) =
            input(Input.ofMcpServerToolResultContent(mcpServerToolResultContent))

        /**
         * Alias for calling [input] with
         * `Input.ofFileSearchResultContent(fileSearchResultContent)`.
         */
        fun input(fileSearchResultContent: FileSearchResultContent) =
            input(Input.ofFileSearchResultContent(fileSearchResultContent))

        /**
         * Alias for calling [input] with
         * `Input.ofGoogleMapsResultContent(googleMapsResultContent)`.
         */
        fun input(googleMapsResultContent: GoogleMapsResultContent) =
            input(Input.ofGoogleMapsResultContent(googleMapsResultContent))

        /**
         * The model that will complete your prompt.\n\nSee
         * [models](https://ai.google.dev/gemini-api/docs/models) for additional details.
         */
        fun model(model: Model) = model(JsonField.of(model))

        /**
         * Sets [Builder.model] to an arbitrary JSON value.
         *
         * You should usually call [Builder.model] with a well-typed [Model] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun model(model: JsonField<Model>) = apply { this.model = model }

        /**
         * Sets [model] to an arbitrary [String].
         *
         * You should usually call [model] with a well-typed [Model] constant instead. This method
         * is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun model(value: String) = model(Model.of(value))

        /** Required. Output only. A unique identifier for the interaction completion. */
        fun id(id: String) = id(JsonField.of(id))

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

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

        /** Input only. Configuration parameters for the model interaction. */
        fun generationConfig(generationConfig: GenerationConfig) =
            generationConfig(JsonField.of(generationConfig))

        /**
         * Sets [Builder.generationConfig] to an arbitrary JSON value.
         *
         * You should usually call [Builder.generationConfig] with a well-typed [GenerationConfig]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun generationConfig(generationConfig: JsonField<GenerationConfig>) = apply {
            this.generationConfig = generationConfig
        }

        /** Output only. Responses from the model. */
        fun outputs(outputs: List<Content>) = outputs(JsonField.of(outputs))

        /**
         * Sets [Builder.outputs] to an arbitrary JSON value.
         *
         * You should usually call [Builder.outputs] with a well-typed `List<Content>` value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun outputs(outputs: JsonField<List<Content>>) = apply {
            this.outputs = outputs.map { it.toMutableList() }
        }

        /**
         * Adds a single [Content] to [outputs].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addOutput(output: Content) = apply {
            outputs =
                (outputs ?: JsonField.of(mutableListOf())).also {
                    checkKnown("outputs", it).add(output)
                }
        }

        /** Alias for calling [addOutput] with `Content.ofText(text)`. */
        fun addOutput(text: TextContent) = addOutput(Content.ofText(text))

        /**
         * Alias for calling [addOutput] with the following:
         * ```java
         * TextContent.builder()
         *     .text(text)
         *     .build()
         * ```
         */
        fun addTextOutput(text: String) = addOutput(TextContent.builder().text(text).build())

        /** Alias for calling [addOutput] with `Content.ofImage(image)`. */
        fun addOutput(image: ImageContent) = addOutput(Content.ofImage(image))

        /** Alias for calling [addOutput] with `Content.ofAudio(audio)`. */
        fun addOutput(audio: AudioContent) = addOutput(Content.ofAudio(audio))

        /** Alias for calling [addOutput] with `Content.ofDocument(document)`. */
        fun addOutput(document: DocumentContent) = addOutput(Content.ofDocument(document))

        /** Alias for calling [addOutput] with `Content.ofVideo(video)`. */
        fun addOutput(video: VideoContent) = addOutput(Content.ofVideo(video))

        /** Alias for calling [addOutput] with `Content.ofThought(thought)`. */
        fun addOutput(thought: ThoughtContent) = addOutput(Content.ofThought(thought))

        /** Alias for calling [addOutput] with `Content.ofFunctionCall(functionCall)`. */
        fun addOutput(functionCall: FunctionCallContent) =
            addOutput(Content.ofFunctionCall(functionCall))

        /** Alias for calling [addOutput] with `Content.ofCodeExecutionCall(codeExecutionCall)`. */
        fun addOutput(codeExecutionCall: CodeExecutionCallContent) =
            addOutput(Content.ofCodeExecutionCall(codeExecutionCall))

        /** Alias for calling [addOutput] with `Content.ofUrlContextCall(urlContextCall)`. */
        fun addOutput(urlContextCall: UrlContextCallContent) =
            addOutput(Content.ofUrlContextCall(urlContextCall))

        /** Alias for calling [addOutput] with `Content.ofMcpServerToolCall(mcpServerToolCall)`. */
        fun addOutput(mcpServerToolCall: McpServerToolCallContent) =
            addOutput(Content.ofMcpServerToolCall(mcpServerToolCall))

        /** Alias for calling [addOutput] with `Content.ofGoogleSearchCall(googleSearchCall)`. */
        fun addOutput(googleSearchCall: GoogleSearchCallContent) =
            addOutput(Content.ofGoogleSearchCall(googleSearchCall))

        /** Alias for calling [addOutput] with `Content.ofFileSearchCall(fileSearchCall)`. */
        fun addOutput(fileSearchCall: FileSearchCallContent) =
            addOutput(Content.ofFileSearchCall(fileSearchCall))

        /**
         * Alias for calling [addOutput] with the following:
         * ```java
         * FileSearchCallContent.builder()
         *     .id(id)
         *     .build()
         * ```
         */
        fun addFileSearchCallOutput(id: String) =
            addOutput(FileSearchCallContent.builder().id(id).build())

        /** Alias for calling [addOutput] with `Content.ofGoogleMapsCall(googleMapsCall)`. */
        fun addOutput(googleMapsCall: GoogleMapsCallContent) =
            addOutput(Content.ofGoogleMapsCall(googleMapsCall))

        /**
         * Alias for calling [addOutput] with the following:
         * ```java
         * GoogleMapsCallContent.builder()
         *     .id(id)
         *     .build()
         * ```
         */
        fun addGoogleMapsCallOutput(id: String) =
            addOutput(GoogleMapsCallContent.builder().id(id).build())

        /** Alias for calling [addOutput] with `Content.ofFunctionResult(functionResult)`. */
        fun addOutput(functionResult: FunctionResultContent) =
            addOutput(Content.ofFunctionResult(functionResult))

        /**
         * Alias for calling [addOutput] with `Content.ofCodeExecutionResult(codeExecutionResult)`.
         */
        fun addOutput(codeExecutionResult: CodeExecutionResultContent) =
            addOutput(Content.ofCodeExecutionResult(codeExecutionResult))

        /** Alias for calling [addOutput] with `Content.ofUrlContextResult(urlContextResult)`. */
        fun addOutput(urlContextResult: UrlContextResultContent) =
            addOutput(Content.ofUrlContextResult(urlContextResult))

        /**
         * Alias for calling [addOutput] with `Content.ofGoogleSearchResult(googleSearchResult)`.
         */
        fun addOutput(googleSearchResult: GoogleSearchResultContent) =
            addOutput(Content.ofGoogleSearchResult(googleSearchResult))

        /**
         * Alias for calling [addOutput] with `Content.ofMcpServerToolResult(mcpServerToolResult)`.
         */
        fun addOutput(mcpServerToolResult: McpServerToolResultContent) =
            addOutput(Content.ofMcpServerToolResult(mcpServerToolResult))

        /** Alias for calling [addOutput] with `Content.ofFileSearchResult(fileSearchResult)`. */
        fun addOutput(fileSearchResult: FileSearchResultContent) =
            addOutput(Content.ofFileSearchResult(fileSearchResult))

        /** Alias for calling [addOutput] with `Content.ofGoogleMapsResult(googleMapsResult)`. */
        fun addOutput(googleMapsResult: GoogleMapsResultContent) =
            addOutput(Content.ofGoogleMapsResult(googleMapsResult))

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
        fun responseFormat(responseFormat: JsonValue) = apply {
            this.responseFormat = responseFormat
        }

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
        fun role(role: String) = role(JsonField.of(role))

        /**
         * Sets [Builder.role] to an arbitrary JSON value.
         *
         * You should usually call [Builder.role] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun role(role: JsonField<String>) = apply { this.role = role }

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
         * Returns an immutable instance of [CreateModelInteractionParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .input()
         * .model()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): CreateModelInteractionParams =
            CreateModelInteractionParams(
                checkRequired("input", input),
                checkRequired("model", model),
                id,
                background,
                created,
                generationConfig,
                (outputs ?: JsonMissing.of()).map { it.toImmutable() },
                previousInteractionId,
                responseFormat,
                responseMimeType,
                (responseModalities ?: JsonMissing.of()).map { it.toImmutable() },
                role,
                serviceTier,
                status,
                store,
                stream,
                systemInstruction,
                (tools ?: JsonMissing.of()).map { it.toImmutable() },
                updated,
                usage,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): CreateModelInteractionParams = apply {
        if (validated) {
            return@apply
        }

        input().validate()
        model()
        id()
        background()
        created()
        generationConfig().ifPresent { it.validate() }
        outputs().ifPresent { it.forEach { it.validate() } }
        previousInteractionId()
        responseMimeType()
        responseModalities().ifPresent { it.forEach { it.validate() } }
        role()
        serviceTier().ifPresent { it.validate() }
        status().ifPresent { it.validate() }
        store()
        stream()
        systemInstruction()
        tools().ifPresent { it.forEach { it.validate() } }
        updated()
        usage().ifPresent { it.validate() }
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
        (input.asKnown().getOrNull()?.validity() ?: 0) +
            (if (model.asKnown().isPresent) 1 else 0) +
            (if (id.asKnown().isPresent) 1 else 0) +
            (if (background.asKnown().isPresent) 1 else 0) +
            (if (created.asKnown().isPresent) 1 else 0) +
            (generationConfig.asKnown().getOrNull()?.validity() ?: 0) +
            (outputs.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (if (previousInteractionId.asKnown().isPresent) 1 else 0) +
            (if (responseMimeType.asKnown().isPresent) 1 else 0) +
            (responseModalities.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (if (role.asKnown().isPresent) 1 else 0) +
            (serviceTier.asKnown().getOrNull()?.validity() ?: 0) +
            (status.asKnown().getOrNull()?.validity() ?: 0) +
            (if (store.asKnown().isPresent) 1 else 0) +
            (if (stream.asKnown().isPresent) 1 else 0) +
            (if (systemInstruction.asKnown().isPresent) 1 else 0) +
            (tools.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (if (updated.asKnown().isPresent) 1 else 0) +
            (usage.asKnown().getOrNull()?.validity() ?: 0)

    /** The input for the interaction. */
    @JsonDeserialize(using = Input.Deserializer::class)
    @JsonSerialize(using = Input.Serializer::class)
    class Input
    private constructor(
        private val contentList: List<Content>? = null,
        private val string: String? = null,
        private val turnList: List<Turn>? = null,
        private val textContent: TextContent? = null,
        private val imageContent: ImageContent? = null,
        private val audioContent: AudioContent? = null,
        private val documentContent: DocumentContent? = null,
        private val videoContent: VideoContent? = null,
        private val thoughtContent: ThoughtContent? = null,
        private val functionCallContent: FunctionCallContent? = null,
        private val codeExecutionCallContent: CodeExecutionCallContent? = null,
        private val urlContextCallContent: UrlContextCallContent? = null,
        private val mcpServerToolCallContent: McpServerToolCallContent? = null,
        private val googleSearchCallContent: GoogleSearchCallContent? = null,
        private val fileSearchCallContent: FileSearchCallContent? = null,
        private val googleMapsCallContent: GoogleMapsCallContent? = null,
        private val functionResultContent: FunctionResultContent? = null,
        private val codeExecutionResultContent: CodeExecutionResultContent? = null,
        private val urlContextResultContent: UrlContextResultContent? = null,
        private val googleSearchResultContent: GoogleSearchResultContent? = null,
        private val mcpServerToolResultContent: McpServerToolResultContent? = null,
        private val fileSearchResultContent: FileSearchResultContent? = null,
        private val googleMapsResultContent: GoogleMapsResultContent? = null,
        private val _json: JsonValue? = null,
    ) {

        fun contentList(): Optional<List<Content>> = Optional.ofNullable(contentList)

        fun string(): Optional<String> = Optional.ofNullable(string)

        fun turnList(): Optional<List<Turn>> = Optional.ofNullable(turnList)

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

        /** A thought content block. */
        fun thoughtContent(): Optional<ThoughtContent> = Optional.ofNullable(thoughtContent)

        /** A function tool call content block. */
        fun functionCallContent(): Optional<FunctionCallContent> =
            Optional.ofNullable(functionCallContent)

        /** Code execution content. */
        fun codeExecutionCallContent(): Optional<CodeExecutionCallContent> =
            Optional.ofNullable(codeExecutionCallContent)

        /** URL context content. */
        fun urlContextCallContent(): Optional<UrlContextCallContent> =
            Optional.ofNullable(urlContextCallContent)

        /** MCPServer tool call content. */
        fun mcpServerToolCallContent(): Optional<McpServerToolCallContent> =
            Optional.ofNullable(mcpServerToolCallContent)

        /** Google Search content. */
        fun googleSearchCallContent(): Optional<GoogleSearchCallContent> =
            Optional.ofNullable(googleSearchCallContent)

        /** File Search content. */
        fun fileSearchCallContent(): Optional<FileSearchCallContent> =
            Optional.ofNullable(fileSearchCallContent)

        /** Google Maps content. */
        fun googleMapsCallContent(): Optional<GoogleMapsCallContent> =
            Optional.ofNullable(googleMapsCallContent)

        /** A function tool result content block. */
        fun functionResultContent(): Optional<FunctionResultContent> =
            Optional.ofNullable(functionResultContent)

        /** Code execution result content. */
        fun codeExecutionResultContent(): Optional<CodeExecutionResultContent> =
            Optional.ofNullable(codeExecutionResultContent)

        /** URL context result content. */
        fun urlContextResultContent(): Optional<UrlContextResultContent> =
            Optional.ofNullable(urlContextResultContent)

        /** Google Search result content. */
        fun googleSearchResultContent(): Optional<GoogleSearchResultContent> =
            Optional.ofNullable(googleSearchResultContent)

        /** MCPServer tool result content. */
        fun mcpServerToolResultContent(): Optional<McpServerToolResultContent> =
            Optional.ofNullable(mcpServerToolResultContent)

        /** File Search result content. */
        fun fileSearchResultContent(): Optional<FileSearchResultContent> =
            Optional.ofNullable(fileSearchResultContent)

        /** Google Maps result content. */
        fun googleMapsResultContent(): Optional<GoogleMapsResultContent> =
            Optional.ofNullable(googleMapsResultContent)

        fun isContentList(): Boolean = contentList != null

        fun isString(): Boolean = string != null

        fun isTurnList(): Boolean = turnList != null

        fun isTextContent(): Boolean = textContent != null

        fun isImageContent(): Boolean = imageContent != null

        fun isAudioContent(): Boolean = audioContent != null

        fun isDocumentContent(): Boolean = documentContent != null

        fun isVideoContent(): Boolean = videoContent != null

        fun isThoughtContent(): Boolean = thoughtContent != null

        fun isFunctionCallContent(): Boolean = functionCallContent != null

        fun isCodeExecutionCallContent(): Boolean = codeExecutionCallContent != null

        fun isUrlContextCallContent(): Boolean = urlContextCallContent != null

        fun isMcpServerToolCallContent(): Boolean = mcpServerToolCallContent != null

        fun isGoogleSearchCallContent(): Boolean = googleSearchCallContent != null

        fun isFileSearchCallContent(): Boolean = fileSearchCallContent != null

        fun isGoogleMapsCallContent(): Boolean = googleMapsCallContent != null

        fun isFunctionResultContent(): Boolean = functionResultContent != null

        fun isCodeExecutionResultContent(): Boolean = codeExecutionResultContent != null

        fun isUrlContextResultContent(): Boolean = urlContextResultContent != null

        fun isGoogleSearchResultContent(): Boolean = googleSearchResultContent != null

        fun isMcpServerToolResultContent(): Boolean = mcpServerToolResultContent != null

        fun isFileSearchResultContent(): Boolean = fileSearchResultContent != null

        fun isGoogleMapsResultContent(): Boolean = googleMapsResultContent != null

        fun asContentList(): List<Content> = contentList.getOrThrow("contentList")

        fun asString(): String = string.getOrThrow("string")

        fun asTurnList(): List<Turn> = turnList.getOrThrow("turnList")

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

        /** A thought content block. */
        fun asThoughtContent(): ThoughtContent = thoughtContent.getOrThrow("thoughtContent")

        /** A function tool call content block. */
        fun asFunctionCallContent(): FunctionCallContent =
            functionCallContent.getOrThrow("functionCallContent")

        /** Code execution content. */
        fun asCodeExecutionCallContent(): CodeExecutionCallContent =
            codeExecutionCallContent.getOrThrow("codeExecutionCallContent")

        /** URL context content. */
        fun asUrlContextCallContent(): UrlContextCallContent =
            urlContextCallContent.getOrThrow("urlContextCallContent")

        /** MCPServer tool call content. */
        fun asMcpServerToolCallContent(): McpServerToolCallContent =
            mcpServerToolCallContent.getOrThrow("mcpServerToolCallContent")

        /** Google Search content. */
        fun asGoogleSearchCallContent(): GoogleSearchCallContent =
            googleSearchCallContent.getOrThrow("googleSearchCallContent")

        /** File Search content. */
        fun asFileSearchCallContent(): FileSearchCallContent =
            fileSearchCallContent.getOrThrow("fileSearchCallContent")

        /** Google Maps content. */
        fun asGoogleMapsCallContent(): GoogleMapsCallContent =
            googleMapsCallContent.getOrThrow("googleMapsCallContent")

        /** A function tool result content block. */
        fun asFunctionResultContent(): FunctionResultContent =
            functionResultContent.getOrThrow("functionResultContent")

        /** Code execution result content. */
        fun asCodeExecutionResultContent(): CodeExecutionResultContent =
            codeExecutionResultContent.getOrThrow("codeExecutionResultContent")

        /** URL context result content. */
        fun asUrlContextResultContent(): UrlContextResultContent =
            urlContextResultContent.getOrThrow("urlContextResultContent")

        /** Google Search result content. */
        fun asGoogleSearchResultContent(): GoogleSearchResultContent =
            googleSearchResultContent.getOrThrow("googleSearchResultContent")

        /** MCPServer tool result content. */
        fun asMcpServerToolResultContent(): McpServerToolResultContent =
            mcpServerToolResultContent.getOrThrow("mcpServerToolResultContent")

        /** File Search result content. */
        fun asFileSearchResultContent(): FileSearchResultContent =
            fileSearchResultContent.getOrThrow("fileSearchResultContent")

        /** Google Maps result content. */
        fun asGoogleMapsResultContent(): GoogleMapsResultContent =
            googleMapsResultContent.getOrThrow("googleMapsResultContent")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        fun <T> accept(visitor: Visitor<T>): T =
            when {
                contentList != null -> visitor.visitContentList(contentList)
                string != null -> visitor.visitString(string)
                turnList != null -> visitor.visitTurnList(turnList)
                textContent != null -> visitor.visitTextContent(textContent)
                imageContent != null -> visitor.visitImageContent(imageContent)
                audioContent != null -> visitor.visitAudioContent(audioContent)
                documentContent != null -> visitor.visitDocumentContent(documentContent)
                videoContent != null -> visitor.visitVideoContent(videoContent)
                thoughtContent != null -> visitor.visitThoughtContent(thoughtContent)
                functionCallContent != null -> visitor.visitFunctionCallContent(functionCallContent)
                codeExecutionCallContent != null ->
                    visitor.visitCodeExecutionCallContent(codeExecutionCallContent)
                urlContextCallContent != null ->
                    visitor.visitUrlContextCallContent(urlContextCallContent)
                mcpServerToolCallContent != null ->
                    visitor.visitMcpServerToolCallContent(mcpServerToolCallContent)
                googleSearchCallContent != null ->
                    visitor.visitGoogleSearchCallContent(googleSearchCallContent)
                fileSearchCallContent != null ->
                    visitor.visitFileSearchCallContent(fileSearchCallContent)
                googleMapsCallContent != null ->
                    visitor.visitGoogleMapsCallContent(googleMapsCallContent)
                functionResultContent != null ->
                    visitor.visitFunctionResultContent(functionResultContent)
                codeExecutionResultContent != null ->
                    visitor.visitCodeExecutionResultContent(codeExecutionResultContent)
                urlContextResultContent != null ->
                    visitor.visitUrlContextResultContent(urlContextResultContent)
                googleSearchResultContent != null ->
                    visitor.visitGoogleSearchResultContent(googleSearchResultContent)
                mcpServerToolResultContent != null ->
                    visitor.visitMcpServerToolResultContent(mcpServerToolResultContent)
                fileSearchResultContent != null ->
                    visitor.visitFileSearchResultContent(fileSearchResultContent)
                googleMapsResultContent != null ->
                    visitor.visitGoogleMapsResultContent(googleMapsResultContent)
                else -> visitor.unknown(_json)
            }

        private var validated: Boolean = false

        fun validate(): Input = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitContentList(contentList: List<Content>) {
                        contentList.forEach { it.validate() }
                    }

                    override fun visitString(string: String) {}

                    override fun visitTurnList(turnList: List<Turn>) {
                        turnList.forEach { it.validate() }
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

                    override fun visitThoughtContent(thoughtContent: ThoughtContent) {
                        thoughtContent.validate()
                    }

                    override fun visitFunctionCallContent(
                        functionCallContent: FunctionCallContent
                    ) {
                        functionCallContent.validate()
                    }

                    override fun visitCodeExecutionCallContent(
                        codeExecutionCallContent: CodeExecutionCallContent
                    ) {
                        codeExecutionCallContent.validate()
                    }

                    override fun visitUrlContextCallContent(
                        urlContextCallContent: UrlContextCallContent
                    ) {
                        urlContextCallContent.validate()
                    }

                    override fun visitMcpServerToolCallContent(
                        mcpServerToolCallContent: McpServerToolCallContent
                    ) {
                        mcpServerToolCallContent.validate()
                    }

                    override fun visitGoogleSearchCallContent(
                        googleSearchCallContent: GoogleSearchCallContent
                    ) {
                        googleSearchCallContent.validate()
                    }

                    override fun visitFileSearchCallContent(
                        fileSearchCallContent: FileSearchCallContent
                    ) {
                        fileSearchCallContent.validate()
                    }

                    override fun visitGoogleMapsCallContent(
                        googleMapsCallContent: GoogleMapsCallContent
                    ) {
                        googleMapsCallContent.validate()
                    }

                    override fun visitFunctionResultContent(
                        functionResultContent: FunctionResultContent
                    ) {
                        functionResultContent.validate()
                    }

                    override fun visitCodeExecutionResultContent(
                        codeExecutionResultContent: CodeExecutionResultContent
                    ) {
                        codeExecutionResultContent.validate()
                    }

                    override fun visitUrlContextResultContent(
                        urlContextResultContent: UrlContextResultContent
                    ) {
                        urlContextResultContent.validate()
                    }

                    override fun visitGoogleSearchResultContent(
                        googleSearchResultContent: GoogleSearchResultContent
                    ) {
                        googleSearchResultContent.validate()
                    }

                    override fun visitMcpServerToolResultContent(
                        mcpServerToolResultContent: McpServerToolResultContent
                    ) {
                        mcpServerToolResultContent.validate()
                    }

                    override fun visitFileSearchResultContent(
                        fileSearchResultContent: FileSearchResultContent
                    ) {
                        fileSearchResultContent.validate()
                    }

                    override fun visitGoogleMapsResultContent(
                        googleMapsResultContent: GoogleMapsResultContent
                    ) {
                        googleMapsResultContent.validate()
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
                    override fun visitContentList(contentList: List<Content>) =
                        contentList.sumOf { it.validity().toInt() }

                    override fun visitString(string: String) = 1

                    override fun visitTurnList(turnList: List<Turn>) =
                        turnList.sumOf { it.validity().toInt() }

                    override fun visitTextContent(textContent: TextContent) = textContent.validity()

                    override fun visitImageContent(imageContent: ImageContent) =
                        imageContent.validity()

                    override fun visitAudioContent(audioContent: AudioContent) =
                        audioContent.validity()

                    override fun visitDocumentContent(documentContent: DocumentContent) =
                        documentContent.validity()

                    override fun visitVideoContent(videoContent: VideoContent) =
                        videoContent.validity()

                    override fun visitThoughtContent(thoughtContent: ThoughtContent) =
                        thoughtContent.validity()

                    override fun visitFunctionCallContent(
                        functionCallContent: FunctionCallContent
                    ) = functionCallContent.validity()

                    override fun visitCodeExecutionCallContent(
                        codeExecutionCallContent: CodeExecutionCallContent
                    ) = codeExecutionCallContent.validity()

                    override fun visitUrlContextCallContent(
                        urlContextCallContent: UrlContextCallContent
                    ) = urlContextCallContent.validity()

                    override fun visitMcpServerToolCallContent(
                        mcpServerToolCallContent: McpServerToolCallContent
                    ) = mcpServerToolCallContent.validity()

                    override fun visitGoogleSearchCallContent(
                        googleSearchCallContent: GoogleSearchCallContent
                    ) = googleSearchCallContent.validity()

                    override fun visitFileSearchCallContent(
                        fileSearchCallContent: FileSearchCallContent
                    ) = fileSearchCallContent.validity()

                    override fun visitGoogleMapsCallContent(
                        googleMapsCallContent: GoogleMapsCallContent
                    ) = googleMapsCallContent.validity()

                    override fun visitFunctionResultContent(
                        functionResultContent: FunctionResultContent
                    ) = functionResultContent.validity()

                    override fun visitCodeExecutionResultContent(
                        codeExecutionResultContent: CodeExecutionResultContent
                    ) = codeExecutionResultContent.validity()

                    override fun visitUrlContextResultContent(
                        urlContextResultContent: UrlContextResultContent
                    ) = urlContextResultContent.validity()

                    override fun visitGoogleSearchResultContent(
                        googleSearchResultContent: GoogleSearchResultContent
                    ) = googleSearchResultContent.validity()

                    override fun visitMcpServerToolResultContent(
                        mcpServerToolResultContent: McpServerToolResultContent
                    ) = mcpServerToolResultContent.validity()

                    override fun visitFileSearchResultContent(
                        fileSearchResultContent: FileSearchResultContent
                    ) = fileSearchResultContent.validity()

                    override fun visitGoogleMapsResultContent(
                        googleMapsResultContent: GoogleMapsResultContent
                    ) = googleMapsResultContent.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Input &&
                contentList == other.contentList &&
                string == other.string &&
                turnList == other.turnList &&
                textContent == other.textContent &&
                imageContent == other.imageContent &&
                audioContent == other.audioContent &&
                documentContent == other.documentContent &&
                videoContent == other.videoContent &&
                thoughtContent == other.thoughtContent &&
                functionCallContent == other.functionCallContent &&
                codeExecutionCallContent == other.codeExecutionCallContent &&
                urlContextCallContent == other.urlContextCallContent &&
                mcpServerToolCallContent == other.mcpServerToolCallContent &&
                googleSearchCallContent == other.googleSearchCallContent &&
                fileSearchCallContent == other.fileSearchCallContent &&
                googleMapsCallContent == other.googleMapsCallContent &&
                functionResultContent == other.functionResultContent &&
                codeExecutionResultContent == other.codeExecutionResultContent &&
                urlContextResultContent == other.urlContextResultContent &&
                googleSearchResultContent == other.googleSearchResultContent &&
                mcpServerToolResultContent == other.mcpServerToolResultContent &&
                fileSearchResultContent == other.fileSearchResultContent &&
                googleMapsResultContent == other.googleMapsResultContent
        }

        override fun hashCode(): Int =
            Objects.hash(
                contentList,
                string,
                turnList,
                textContent,
                imageContent,
                audioContent,
                documentContent,
                videoContent,
                thoughtContent,
                functionCallContent,
                codeExecutionCallContent,
                urlContextCallContent,
                mcpServerToolCallContent,
                googleSearchCallContent,
                fileSearchCallContent,
                googleMapsCallContent,
                functionResultContent,
                codeExecutionResultContent,
                urlContextResultContent,
                googleSearchResultContent,
                mcpServerToolResultContent,
                fileSearchResultContent,
                googleMapsResultContent,
            )

        override fun toString(): String =
            when {
                contentList != null -> "Input{contentList=$contentList}"
                string != null -> "Input{string=$string}"
                turnList != null -> "Input{turnList=$turnList}"
                textContent != null -> "Input{textContent=$textContent}"
                imageContent != null -> "Input{imageContent=$imageContent}"
                audioContent != null -> "Input{audioContent=$audioContent}"
                documentContent != null -> "Input{documentContent=$documentContent}"
                videoContent != null -> "Input{videoContent=$videoContent}"
                thoughtContent != null -> "Input{thoughtContent=$thoughtContent}"
                functionCallContent != null -> "Input{functionCallContent=$functionCallContent}"
                codeExecutionCallContent != null ->
                    "Input{codeExecutionCallContent=$codeExecutionCallContent}"
                urlContextCallContent != null ->
                    "Input{urlContextCallContent=$urlContextCallContent}"
                mcpServerToolCallContent != null ->
                    "Input{mcpServerToolCallContent=$mcpServerToolCallContent}"
                googleSearchCallContent != null ->
                    "Input{googleSearchCallContent=$googleSearchCallContent}"
                fileSearchCallContent != null ->
                    "Input{fileSearchCallContent=$fileSearchCallContent}"
                googleMapsCallContent != null ->
                    "Input{googleMapsCallContent=$googleMapsCallContent}"
                functionResultContent != null ->
                    "Input{functionResultContent=$functionResultContent}"
                codeExecutionResultContent != null ->
                    "Input{codeExecutionResultContent=$codeExecutionResultContent}"
                urlContextResultContent != null ->
                    "Input{urlContextResultContent=$urlContextResultContent}"
                googleSearchResultContent != null ->
                    "Input{googleSearchResultContent=$googleSearchResultContent}"
                mcpServerToolResultContent != null ->
                    "Input{mcpServerToolResultContent=$mcpServerToolResultContent}"
                fileSearchResultContent != null ->
                    "Input{fileSearchResultContent=$fileSearchResultContent}"
                googleMapsResultContent != null ->
                    "Input{googleMapsResultContent=$googleMapsResultContent}"
                _json != null -> "Input{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Input")
            }

        companion object {

            @JvmStatic
            fun ofContentList(contentList: List<Content>) =
                Input(contentList = contentList.toImmutable())

            @JvmStatic fun ofString(string: String) = Input(string = string)

            @JvmStatic
            fun ofTurnList(turnList: List<Turn>) = Input(turnList = turnList.toImmutable())

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

            /** A thought content block. */
            @JvmStatic
            fun ofThoughtContent(thoughtContent: ThoughtContent) =
                Input(thoughtContent = thoughtContent)

            /** A function tool call content block. */
            @JvmStatic
            fun ofFunctionCallContent(functionCallContent: FunctionCallContent) =
                Input(functionCallContent = functionCallContent)

            /** Code execution content. */
            @JvmStatic
            fun ofCodeExecutionCallContent(codeExecutionCallContent: CodeExecutionCallContent) =
                Input(codeExecutionCallContent = codeExecutionCallContent)

            /** URL context content. */
            @JvmStatic
            fun ofUrlContextCallContent(urlContextCallContent: UrlContextCallContent) =
                Input(urlContextCallContent = urlContextCallContent)

            /** MCPServer tool call content. */
            @JvmStatic
            fun ofMcpServerToolCallContent(mcpServerToolCallContent: McpServerToolCallContent) =
                Input(mcpServerToolCallContent = mcpServerToolCallContent)

            /** Google Search content. */
            @JvmStatic
            fun ofGoogleSearchCallContent(googleSearchCallContent: GoogleSearchCallContent) =
                Input(googleSearchCallContent = googleSearchCallContent)

            /** File Search content. */
            @JvmStatic
            fun ofFileSearchCallContent(fileSearchCallContent: FileSearchCallContent) =
                Input(fileSearchCallContent = fileSearchCallContent)

            /** Google Maps content. */
            @JvmStatic
            fun ofGoogleMapsCallContent(googleMapsCallContent: GoogleMapsCallContent) =
                Input(googleMapsCallContent = googleMapsCallContent)

            /** A function tool result content block. */
            @JvmStatic
            fun ofFunctionResultContent(functionResultContent: FunctionResultContent) =
                Input(functionResultContent = functionResultContent)

            /** Code execution result content. */
            @JvmStatic
            fun ofCodeExecutionResultContent(
                codeExecutionResultContent: CodeExecutionResultContent
            ) = Input(codeExecutionResultContent = codeExecutionResultContent)

            /** URL context result content. */
            @JvmStatic
            fun ofUrlContextResultContent(urlContextResultContent: UrlContextResultContent) =
                Input(urlContextResultContent = urlContextResultContent)

            /** Google Search result content. */
            @JvmStatic
            fun ofGoogleSearchResultContent(googleSearchResultContent: GoogleSearchResultContent) =
                Input(googleSearchResultContent = googleSearchResultContent)

            /** MCPServer tool result content. */
            @JvmStatic
            fun ofMcpServerToolResultContent(
                mcpServerToolResultContent: McpServerToolResultContent
            ) = Input(mcpServerToolResultContent = mcpServerToolResultContent)

            /** File Search result content. */
            @JvmStatic
            fun ofFileSearchResultContent(fileSearchResultContent: FileSearchResultContent) =
                Input(fileSearchResultContent = fileSearchResultContent)

            /** Google Maps result content. */
            @JvmStatic
            fun ofGoogleMapsResultContent(googleMapsResultContent: GoogleMapsResultContent) =
                Input(googleMapsResultContent = googleMapsResultContent)
        }

        /** An interface that defines how to map each variant of [Input] to a value of type [T]. */
        interface Visitor<out T> {

            fun visitContentList(contentList: List<Content>): T

            fun visitString(string: String): T

            fun visitTurnList(turnList: List<Turn>): T

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

            /** A thought content block. */
            fun visitThoughtContent(thoughtContent: ThoughtContent): T

            /** A function tool call content block. */
            fun visitFunctionCallContent(functionCallContent: FunctionCallContent): T

            /** Code execution content. */
            fun visitCodeExecutionCallContent(codeExecutionCallContent: CodeExecutionCallContent): T

            /** URL context content. */
            fun visitUrlContextCallContent(urlContextCallContent: UrlContextCallContent): T

            /** MCPServer tool call content. */
            fun visitMcpServerToolCallContent(mcpServerToolCallContent: McpServerToolCallContent): T

            /** Google Search content. */
            fun visitGoogleSearchCallContent(googleSearchCallContent: GoogleSearchCallContent): T

            /** File Search content. */
            fun visitFileSearchCallContent(fileSearchCallContent: FileSearchCallContent): T

            /** Google Maps content. */
            fun visitGoogleMapsCallContent(googleMapsCallContent: GoogleMapsCallContent): T

            /** A function tool result content block. */
            fun visitFunctionResultContent(functionResultContent: FunctionResultContent): T

            /** Code execution result content. */
            fun visitCodeExecutionResultContent(
                codeExecutionResultContent: CodeExecutionResultContent
            ): T

            /** URL context result content. */
            fun visitUrlContextResultContent(urlContextResultContent: UrlContextResultContent): T

            /** Google Search result content. */
            fun visitGoogleSearchResultContent(
                googleSearchResultContent: GoogleSearchResultContent
            ): T

            /** MCPServer tool result content. */
            fun visitMcpServerToolResultContent(
                mcpServerToolResultContent: McpServerToolResultContent
            ): T

            /** File Search result content. */
            fun visitFileSearchResultContent(fileSearchResultContent: FileSearchResultContent): T

            /** Google Maps result content. */
            fun visitGoogleMapsResultContent(googleMapsResultContent: GoogleMapsResultContent): T

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
                            tryDeserialize(node, jacksonTypeRef<ThoughtContent>())?.let {
                                Input(thoughtContent = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<FunctionCallContent>())?.let {
                                Input(functionCallContent = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<CodeExecutionCallContent>())?.let {
                                Input(codeExecutionCallContent = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<UrlContextCallContent>())?.let {
                                Input(urlContextCallContent = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<McpServerToolCallContent>())?.let {
                                Input(mcpServerToolCallContent = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<GoogleSearchCallContent>())?.let {
                                Input(googleSearchCallContent = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<FileSearchCallContent>())?.let {
                                Input(fileSearchCallContent = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<GoogleMapsCallContent>())?.let {
                                Input(googleMapsCallContent = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<FunctionResultContent>())?.let {
                                Input(functionResultContent = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<CodeExecutionResultContent>())
                                ?.let { Input(codeExecutionResultContent = it, _json = json) },
                            tryDeserialize(node, jacksonTypeRef<UrlContextResultContent>())?.let {
                                Input(urlContextResultContent = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<GoogleSearchResultContent>())?.let {
                                Input(googleSearchResultContent = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<McpServerToolResultContent>())
                                ?.let { Input(mcpServerToolResultContent = it, _json = json) },
                            tryDeserialize(node, jacksonTypeRef<FileSearchResultContent>())?.let {
                                Input(fileSearchResultContent = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<GoogleMapsResultContent>())?.let {
                                Input(googleMapsResultContent = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<String>())?.let {
                                Input(string = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<List<Content>>())?.let {
                                Input(contentList = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<List<Turn>>())?.let {
                                Input(turnList = it, _json = json)
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
                    value.contentList != null -> generator.writeObject(value.contentList)
                    value.string != null -> generator.writeObject(value.string)
                    value.turnList != null -> generator.writeObject(value.turnList)
                    value.textContent != null -> generator.writeObject(value.textContent)
                    value.imageContent != null -> generator.writeObject(value.imageContent)
                    value.audioContent != null -> generator.writeObject(value.audioContent)
                    value.documentContent != null -> generator.writeObject(value.documentContent)
                    value.videoContent != null -> generator.writeObject(value.videoContent)
                    value.thoughtContent != null -> generator.writeObject(value.thoughtContent)
                    value.functionCallContent != null ->
                        generator.writeObject(value.functionCallContent)
                    value.codeExecutionCallContent != null ->
                        generator.writeObject(value.codeExecutionCallContent)
                    value.urlContextCallContent != null ->
                        generator.writeObject(value.urlContextCallContent)
                    value.mcpServerToolCallContent != null ->
                        generator.writeObject(value.mcpServerToolCallContent)
                    value.googleSearchCallContent != null ->
                        generator.writeObject(value.googleSearchCallContent)
                    value.fileSearchCallContent != null ->
                        generator.writeObject(value.fileSearchCallContent)
                    value.googleMapsCallContent != null ->
                        generator.writeObject(value.googleMapsCallContent)
                    value.functionResultContent != null ->
                        generator.writeObject(value.functionResultContent)
                    value.codeExecutionResultContent != null ->
                        generator.writeObject(value.codeExecutionResultContent)
                    value.urlContextResultContent != null ->
                        generator.writeObject(value.urlContextResultContent)
                    value.googleSearchResultContent != null ->
                        generator.writeObject(value.googleSearchResultContent)
                    value.mcpServerToolResultContent != null ->
                        generator.writeObject(value.mcpServerToolResultContent)
                    value.fileSearchResultContent != null ->
                        generator.writeObject(value.fileSearchResultContent)
                    value.googleMapsResultContent != null ->
                        generator.writeObject(value.googleMapsResultContent)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Input")
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

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is CreateModelInteractionParams &&
            input == other.input &&
            model == other.model &&
            id == other.id &&
            background == other.background &&
            created == other.created &&
            generationConfig == other.generationConfig &&
            outputs == other.outputs &&
            previousInteractionId == other.previousInteractionId &&
            responseFormat == other.responseFormat &&
            responseMimeType == other.responseMimeType &&
            responseModalities == other.responseModalities &&
            role == other.role &&
            serviceTier == other.serviceTier &&
            status == other.status &&
            store == other.store &&
            stream == other.stream &&
            systemInstruction == other.systemInstruction &&
            tools == other.tools &&
            updated == other.updated &&
            usage == other.usage &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            input,
            model,
            id,
            background,
            created,
            generationConfig,
            outputs,
            previousInteractionId,
            responseFormat,
            responseMimeType,
            responseModalities,
            role,
            serviceTier,
            status,
            store,
            stream,
            systemInstruction,
            tools,
            updated,
            usage,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "CreateModelInteractionParams{input=$input, model=$model, id=$id, background=$background, created=$created, generationConfig=$generationConfig, outputs=$outputs, previousInteractionId=$previousInteractionId, responseFormat=$responseFormat, responseMimeType=$responseMimeType, responseModalities=$responseModalities, role=$role, serviceTier=$serviceTier, status=$status, store=$store, stream=$stream, systemInstruction=$systemInstruction, tools=$tools, updated=$updated, usage=$usage, additionalProperties=$additionalProperties}"
}
