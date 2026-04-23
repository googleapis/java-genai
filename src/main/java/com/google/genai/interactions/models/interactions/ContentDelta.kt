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
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

class ContentDelta
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val delta: JsonField<Delta>,
    private val eventType: JsonValue,
    private val index: JsonField<Int>,
    private val eventId: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("delta") @ExcludeMissing delta: JsonField<Delta> = JsonMissing.of(),
        @JsonProperty("event_type") @ExcludeMissing eventType: JsonValue = JsonMissing.of(),
        @JsonProperty("index") @ExcludeMissing index: JsonField<Int> = JsonMissing.of(),
        @JsonProperty("event_id") @ExcludeMissing eventId: JsonField<String> = JsonMissing.of(),
    ) : this(delta, eventType, index, eventId, mutableMapOf())

    /**
     * The delta content data for a content block.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun delta(): Delta = delta.getRequired("delta")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("content.delta")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("event_type") @ExcludeMissing fun _eventType(): JsonValue = eventType

    /**
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun index(): Int = index.getRequired("index")

    /**
     * The event_id token to be used to resume the interaction stream, from this event.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun eventId(): Optional<String> = eventId.getOptional("event_id")

    /**
     * Returns the raw JSON value of [delta].
     *
     * Unlike [delta], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("delta") @ExcludeMissing fun _delta(): JsonField<Delta> = delta

    /**
     * Returns the raw JSON value of [index].
     *
     * Unlike [index], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("index") @ExcludeMissing fun _index(): JsonField<Int> = index

    /**
     * Returns the raw JSON value of [eventId].
     *
     * Unlike [eventId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("event_id") @ExcludeMissing fun _eventId(): JsonField<String> = eventId

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
         * Returns a mutable builder for constructing an instance of [ContentDelta].
         *
         * The following fields are required:
         * ```java
         * .delta()
         * .index()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [ContentDelta]. */
    class Builder internal constructor() {

        private var delta: JsonField<Delta>? = null
        private var eventType: JsonValue = JsonValue.from("content.delta")
        private var index: JsonField<Int>? = null
        private var eventId: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(contentDelta: ContentDelta) = apply {
            delta = contentDelta.delta
            eventType = contentDelta.eventType
            index = contentDelta.index
            eventId = contentDelta.eventId
            additionalProperties = contentDelta.additionalProperties.toMutableMap()
        }

        /** The delta content data for a content block. */
        fun delta(delta: Delta) = delta(JsonField.of(delta))

        /**
         * Sets [Builder.delta] to an arbitrary JSON value.
         *
         * You should usually call [Builder.delta] with a well-typed [Delta] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun delta(delta: JsonField<Delta>) = apply { this.delta = delta }

        /** Alias for calling [delta] with `Delta.ofText(text)`. */
        fun delta(text: Delta.Text) = delta(Delta.ofText(text))

        /**
         * Alias for calling [delta] with the following:
         * ```java
         * Delta.Text.builder()
         *     .text(text)
         *     .build()
         * ```
         */
        fun textDelta(text: String) = delta(Delta.Text.builder().text(text).build())

        /** Alias for calling [delta] with `Delta.ofImage(image)`. */
        fun delta(image: Delta.Image) = delta(Delta.ofImage(image))

        /** Alias for calling [delta] with `Delta.ofAudio(audio)`. */
        fun delta(audio: Delta.Audio) = delta(Delta.ofAudio(audio))

        /** Alias for calling [delta] with `Delta.ofDocument(document)`. */
        fun delta(document: Delta.Document) = delta(Delta.ofDocument(document))

        /** Alias for calling [delta] with `Delta.ofVideo(video)`. */
        fun delta(video: Delta.Video) = delta(Delta.ofVideo(video))

        /** Alias for calling [delta] with `Delta.ofThoughtSummary(thoughtSummary)`. */
        fun delta(thoughtSummary: Delta.ThoughtSummary) =
            delta(Delta.ofThoughtSummary(thoughtSummary))

        /** Alias for calling [delta] with `Delta.ofThoughtSignature(thoughtSignature)`. */
        fun delta(thoughtSignature: Delta.ThoughtSignature) =
            delta(Delta.ofThoughtSignature(thoughtSignature))

        /** Alias for calling [delta] with `Delta.ofFunctionCall(functionCall)`. */
        fun delta(functionCall: Delta.FunctionCall) = delta(Delta.ofFunctionCall(functionCall))

        /** Alias for calling [delta] with `Delta.ofCodeExecutionCall(codeExecutionCall)`. */
        fun delta(codeExecutionCall: Delta.CodeExecutionCall) =
            delta(Delta.ofCodeExecutionCall(codeExecutionCall))

        /** Alias for calling [delta] with `Delta.ofUrlContextCall(urlContextCall)`. */
        fun delta(urlContextCall: Delta.UrlContextCall) =
            delta(Delta.ofUrlContextCall(urlContextCall))

        /** Alias for calling [delta] with `Delta.ofGoogleSearchCall(googleSearchCall)`. */
        fun delta(googleSearchCall: Delta.GoogleSearchCall) =
            delta(Delta.ofGoogleSearchCall(googleSearchCall))

        /** Alias for calling [delta] with `Delta.ofMcpServerToolCall(mcpServerToolCall)`. */
        fun delta(mcpServerToolCall: Delta.McpServerToolCall) =
            delta(Delta.ofMcpServerToolCall(mcpServerToolCall))

        /** Alias for calling [delta] with `Delta.ofFileSearchCall(fileSearchCall)`. */
        fun delta(fileSearchCall: Delta.FileSearchCall) =
            delta(Delta.ofFileSearchCall(fileSearchCall))

        /**
         * Alias for calling [delta] with the following:
         * ```java
         * Delta.FileSearchCall.builder()
         *     .id(id)
         *     .build()
         * ```
         */
        fun fileSearchCallDelta(id: String) = delta(Delta.FileSearchCall.builder().id(id).build())

        /** Alias for calling [delta] with `Delta.ofGoogleMapsCall(googleMapsCall)`. */
        fun delta(googleMapsCall: Delta.GoogleMapsCall) =
            delta(Delta.ofGoogleMapsCall(googleMapsCall))

        /**
         * Alias for calling [delta] with the following:
         * ```java
         * Delta.GoogleMapsCall.builder()
         *     .id(id)
         *     .build()
         * ```
         */
        fun googleMapsCallDelta(id: String) = delta(Delta.GoogleMapsCall.builder().id(id).build())

        /** Alias for calling [delta] with `Delta.ofFunctionResult(functionResult)`. */
        fun delta(functionResult: Delta.FunctionResult) =
            delta(Delta.ofFunctionResult(functionResult))

        /** Alias for calling [delta] with `Delta.ofCodeExecutionResult(codeExecutionResult)`. */
        fun delta(codeExecutionResult: Delta.CodeExecutionResult) =
            delta(Delta.ofCodeExecutionResult(codeExecutionResult))

        /** Alias for calling [delta] with `Delta.ofUrlContextResult(urlContextResult)`. */
        fun delta(urlContextResult: Delta.UrlContextResult) =
            delta(Delta.ofUrlContextResult(urlContextResult))

        /** Alias for calling [delta] with `Delta.ofGoogleSearchResult(googleSearchResult)`. */
        fun delta(googleSearchResult: Delta.GoogleSearchResult) =
            delta(Delta.ofGoogleSearchResult(googleSearchResult))

        /** Alias for calling [delta] with `Delta.ofMcpServerToolResult(mcpServerToolResult)`. */
        fun delta(mcpServerToolResult: Delta.McpServerToolResult) =
            delta(Delta.ofMcpServerToolResult(mcpServerToolResult))

        /** Alias for calling [delta] with `Delta.ofFileSearchResult(fileSearchResult)`. */
        fun delta(fileSearchResult: Delta.FileSearchResult) =
            delta(Delta.ofFileSearchResult(fileSearchResult))

        /** Alias for calling [delta] with `Delta.ofGoogleMapsResult(googleMapsResult)`. */
        fun delta(googleMapsResult: Delta.GoogleMapsResult) =
            delta(Delta.ofGoogleMapsResult(googleMapsResult))

        /**
         * Alias for calling [delta] with the following:
         * ```java
         * Delta.GoogleMapsResult.builder()
         *     .callId(callId)
         *     .build()
         * ```
         */
        fun googleMapsResultDelta(callId: String) =
            delta(Delta.GoogleMapsResult.builder().callId(callId).build())

        /** Alias for calling [delta] with `Delta.ofTextAnnotation(textAnnotation)`. */
        fun delta(textAnnotation: Delta.TextAnnotation) =
            delta(Delta.ofTextAnnotation(textAnnotation))

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("content.delta")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun eventType(eventType: JsonValue) = apply { this.eventType = eventType }

        fun index(index: Int) = index(JsonField.of(index))

        /**
         * Sets [Builder.index] to an arbitrary JSON value.
         *
         * You should usually call [Builder.index] with a well-typed [Int] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun index(index: JsonField<Int>) = apply { this.index = index }

        /** The event_id token to be used to resume the interaction stream, from this event. */
        fun eventId(eventId: String) = eventId(JsonField.of(eventId))

        /**
         * Sets [Builder.eventId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.eventId] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun eventId(eventId: JsonField<String>) = apply { this.eventId = eventId }

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
         * Returns an immutable instance of [ContentDelta].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .delta()
         * .index()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): ContentDelta =
            ContentDelta(
                checkRequired("delta", delta),
                eventType,
                checkRequired("index", index),
                eventId,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): ContentDelta = apply {
        if (validated) {
            return@apply
        }

        delta().validate()
        _eventType().let {
            if (it != JsonValue.from("content.delta")) {
                throw GeminiNextGenApiInvalidDataException("'eventType' is invalid, received $it")
            }
        }
        index()
        eventId()
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
        (delta.asKnown().getOrNull()?.validity() ?: 0) +
            eventType.let { if (it == JsonValue.from("content.delta")) 1 else 0 } +
            (if (index.asKnown().isPresent) 1 else 0) +
            (if (eventId.asKnown().isPresent) 1 else 0)

    /** The delta content data for a content block. */
    @JsonDeserialize(using = Delta.Deserializer::class)
    @JsonSerialize(using = Delta.Serializer::class)
    class Delta
    private constructor(
        private val text: Text? = null,
        private val image: Image? = null,
        private val audio: Audio? = null,
        private val document: Document? = null,
        private val video: Video? = null,
        private val thoughtSummary: ThoughtSummary? = null,
        private val thoughtSignature: ThoughtSignature? = null,
        private val functionCall: FunctionCall? = null,
        private val codeExecutionCall: CodeExecutionCall? = null,
        private val urlContextCall: UrlContextCall? = null,
        private val googleSearchCall: GoogleSearchCall? = null,
        private val mcpServerToolCall: McpServerToolCall? = null,
        private val fileSearchCall: FileSearchCall? = null,
        private val googleMapsCall: GoogleMapsCall? = null,
        private val functionResult: FunctionResult? = null,
        private val codeExecutionResult: CodeExecutionResult? = null,
        private val urlContextResult: UrlContextResult? = null,
        private val googleSearchResult: GoogleSearchResult? = null,
        private val mcpServerToolResult: McpServerToolResult? = null,
        private val fileSearchResult: FileSearchResult? = null,
        private val googleMapsResult: GoogleMapsResult? = null,
        private val textAnnotation: TextAnnotation? = null,
        private val _json: JsonValue? = null,
    ) {

        fun text(): Optional<Text> = Optional.ofNullable(text)

        fun image(): Optional<Image> = Optional.ofNullable(image)

        fun audio(): Optional<Audio> = Optional.ofNullable(audio)

        fun document(): Optional<Document> = Optional.ofNullable(document)

        fun video(): Optional<Video> = Optional.ofNullable(video)

        fun thoughtSummary(): Optional<ThoughtSummary> = Optional.ofNullable(thoughtSummary)

        fun thoughtSignature(): Optional<ThoughtSignature> = Optional.ofNullable(thoughtSignature)

        fun functionCall(): Optional<FunctionCall> = Optional.ofNullable(functionCall)

        fun codeExecutionCall(): Optional<CodeExecutionCall> =
            Optional.ofNullable(codeExecutionCall)

        fun urlContextCall(): Optional<UrlContextCall> = Optional.ofNullable(urlContextCall)

        fun googleSearchCall(): Optional<GoogleSearchCall> = Optional.ofNullable(googleSearchCall)

        fun mcpServerToolCall(): Optional<McpServerToolCall> =
            Optional.ofNullable(mcpServerToolCall)

        fun fileSearchCall(): Optional<FileSearchCall> = Optional.ofNullable(fileSearchCall)

        fun googleMapsCall(): Optional<GoogleMapsCall> = Optional.ofNullable(googleMapsCall)

        fun functionResult(): Optional<FunctionResult> = Optional.ofNullable(functionResult)

        fun codeExecutionResult(): Optional<CodeExecutionResult> =
            Optional.ofNullable(codeExecutionResult)

        fun urlContextResult(): Optional<UrlContextResult> = Optional.ofNullable(urlContextResult)

        fun googleSearchResult(): Optional<GoogleSearchResult> =
            Optional.ofNullable(googleSearchResult)

        fun mcpServerToolResult(): Optional<McpServerToolResult> =
            Optional.ofNullable(mcpServerToolResult)

        fun fileSearchResult(): Optional<FileSearchResult> = Optional.ofNullable(fileSearchResult)

        fun googleMapsResult(): Optional<GoogleMapsResult> = Optional.ofNullable(googleMapsResult)

        fun textAnnotation(): Optional<TextAnnotation> = Optional.ofNullable(textAnnotation)

        fun isText(): Boolean = text != null

        fun isImage(): Boolean = image != null

        fun isAudio(): Boolean = audio != null

        fun isDocument(): Boolean = document != null

        fun isVideo(): Boolean = video != null

        fun isThoughtSummary(): Boolean = thoughtSummary != null

        fun isThoughtSignature(): Boolean = thoughtSignature != null

        fun isFunctionCall(): Boolean = functionCall != null

        fun isCodeExecutionCall(): Boolean = codeExecutionCall != null

        fun isUrlContextCall(): Boolean = urlContextCall != null

        fun isGoogleSearchCall(): Boolean = googleSearchCall != null

        fun isMcpServerToolCall(): Boolean = mcpServerToolCall != null

        fun isFileSearchCall(): Boolean = fileSearchCall != null

        fun isGoogleMapsCall(): Boolean = googleMapsCall != null

        fun isFunctionResult(): Boolean = functionResult != null

        fun isCodeExecutionResult(): Boolean = codeExecutionResult != null

        fun isUrlContextResult(): Boolean = urlContextResult != null

        fun isGoogleSearchResult(): Boolean = googleSearchResult != null

        fun isMcpServerToolResult(): Boolean = mcpServerToolResult != null

        fun isFileSearchResult(): Boolean = fileSearchResult != null

        fun isGoogleMapsResult(): Boolean = googleMapsResult != null

        fun isTextAnnotation(): Boolean = textAnnotation != null

        fun asText(): Text = text.getOrThrow("text")

        fun asImage(): Image = image.getOrThrow("image")

        fun asAudio(): Audio = audio.getOrThrow("audio")

        fun asDocument(): Document = document.getOrThrow("document")

        fun asVideo(): Video = video.getOrThrow("video")

        fun asThoughtSummary(): ThoughtSummary = thoughtSummary.getOrThrow("thoughtSummary")

        fun asThoughtSignature(): ThoughtSignature = thoughtSignature.getOrThrow("thoughtSignature")

        fun asFunctionCall(): FunctionCall = functionCall.getOrThrow("functionCall")

        fun asCodeExecutionCall(): CodeExecutionCall =
            codeExecutionCall.getOrThrow("codeExecutionCall")

        fun asUrlContextCall(): UrlContextCall = urlContextCall.getOrThrow("urlContextCall")

        fun asGoogleSearchCall(): GoogleSearchCall = googleSearchCall.getOrThrow("googleSearchCall")

        fun asMcpServerToolCall(): McpServerToolCall =
            mcpServerToolCall.getOrThrow("mcpServerToolCall")

        fun asFileSearchCall(): FileSearchCall = fileSearchCall.getOrThrow("fileSearchCall")

        fun asGoogleMapsCall(): GoogleMapsCall = googleMapsCall.getOrThrow("googleMapsCall")

        fun asFunctionResult(): FunctionResult = functionResult.getOrThrow("functionResult")

        fun asCodeExecutionResult(): CodeExecutionResult =
            codeExecutionResult.getOrThrow("codeExecutionResult")

        fun asUrlContextResult(): UrlContextResult = urlContextResult.getOrThrow("urlContextResult")

        fun asGoogleSearchResult(): GoogleSearchResult =
            googleSearchResult.getOrThrow("googleSearchResult")

        fun asMcpServerToolResult(): McpServerToolResult =
            mcpServerToolResult.getOrThrow("mcpServerToolResult")

        fun asFileSearchResult(): FileSearchResult = fileSearchResult.getOrThrow("fileSearchResult")

        fun asGoogleMapsResult(): GoogleMapsResult = googleMapsResult.getOrThrow("googleMapsResult")

        fun asTextAnnotation(): TextAnnotation = textAnnotation.getOrThrow("textAnnotation")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        fun <T> accept(visitor: Visitor<T>): T =
            when {
                text != null -> visitor.visitText(text)
                image != null -> visitor.visitImage(image)
                audio != null -> visitor.visitAudio(audio)
                document != null -> visitor.visitDocument(document)
                video != null -> visitor.visitVideo(video)
                thoughtSummary != null -> visitor.visitThoughtSummary(thoughtSummary)
                thoughtSignature != null -> visitor.visitThoughtSignature(thoughtSignature)
                functionCall != null -> visitor.visitFunctionCall(functionCall)
                codeExecutionCall != null -> visitor.visitCodeExecutionCall(codeExecutionCall)
                urlContextCall != null -> visitor.visitUrlContextCall(urlContextCall)
                googleSearchCall != null -> visitor.visitGoogleSearchCall(googleSearchCall)
                mcpServerToolCall != null -> visitor.visitMcpServerToolCall(mcpServerToolCall)
                fileSearchCall != null -> visitor.visitFileSearchCall(fileSearchCall)
                googleMapsCall != null -> visitor.visitGoogleMapsCall(googleMapsCall)
                functionResult != null -> visitor.visitFunctionResult(functionResult)
                codeExecutionResult != null -> visitor.visitCodeExecutionResult(codeExecutionResult)
                urlContextResult != null -> visitor.visitUrlContextResult(urlContextResult)
                googleSearchResult != null -> visitor.visitGoogleSearchResult(googleSearchResult)
                mcpServerToolResult != null -> visitor.visitMcpServerToolResult(mcpServerToolResult)
                fileSearchResult != null -> visitor.visitFileSearchResult(fileSearchResult)
                googleMapsResult != null -> visitor.visitGoogleMapsResult(googleMapsResult)
                textAnnotation != null -> visitor.visitTextAnnotation(textAnnotation)
                else -> visitor.unknown(_json)
            }

        private var validated: Boolean = false

        fun validate(): Delta = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitText(text: Text) {
                        text.validate()
                    }

                    override fun visitImage(image: Image) {
                        image.validate()
                    }

                    override fun visitAudio(audio: Audio) {
                        audio.validate()
                    }

                    override fun visitDocument(document: Document) {
                        document.validate()
                    }

                    override fun visitVideo(video: Video) {
                        video.validate()
                    }

                    override fun visitThoughtSummary(thoughtSummary: ThoughtSummary) {
                        thoughtSummary.validate()
                    }

                    override fun visitThoughtSignature(thoughtSignature: ThoughtSignature) {
                        thoughtSignature.validate()
                    }

                    override fun visitFunctionCall(functionCall: FunctionCall) {
                        functionCall.validate()
                    }

                    override fun visitCodeExecutionCall(codeExecutionCall: CodeExecutionCall) {
                        codeExecutionCall.validate()
                    }

                    override fun visitUrlContextCall(urlContextCall: UrlContextCall) {
                        urlContextCall.validate()
                    }

                    override fun visitGoogleSearchCall(googleSearchCall: GoogleSearchCall) {
                        googleSearchCall.validate()
                    }

                    override fun visitMcpServerToolCall(mcpServerToolCall: McpServerToolCall) {
                        mcpServerToolCall.validate()
                    }

                    override fun visitFileSearchCall(fileSearchCall: FileSearchCall) {
                        fileSearchCall.validate()
                    }

                    override fun visitGoogleMapsCall(googleMapsCall: GoogleMapsCall) {
                        googleMapsCall.validate()
                    }

                    override fun visitFunctionResult(functionResult: FunctionResult) {
                        functionResult.validate()
                    }

                    override fun visitCodeExecutionResult(
                        codeExecutionResult: CodeExecutionResult
                    ) {
                        codeExecutionResult.validate()
                    }

                    override fun visitUrlContextResult(urlContextResult: UrlContextResult) {
                        urlContextResult.validate()
                    }

                    override fun visitGoogleSearchResult(googleSearchResult: GoogleSearchResult) {
                        googleSearchResult.validate()
                    }

                    override fun visitMcpServerToolResult(
                        mcpServerToolResult: McpServerToolResult
                    ) {
                        mcpServerToolResult.validate()
                    }

                    override fun visitFileSearchResult(fileSearchResult: FileSearchResult) {
                        fileSearchResult.validate()
                    }

                    override fun visitGoogleMapsResult(googleMapsResult: GoogleMapsResult) {
                        googleMapsResult.validate()
                    }

                    override fun visitTextAnnotation(textAnnotation: TextAnnotation) {
                        textAnnotation.validate()
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
                    override fun visitText(text: Text) = text.validity()

                    override fun visitImage(image: Image) = image.validity()

                    override fun visitAudio(audio: Audio) = audio.validity()

                    override fun visitDocument(document: Document) = document.validity()

                    override fun visitVideo(video: Video) = video.validity()

                    override fun visitThoughtSummary(thoughtSummary: ThoughtSummary) =
                        thoughtSummary.validity()

                    override fun visitThoughtSignature(thoughtSignature: ThoughtSignature) =
                        thoughtSignature.validity()

                    override fun visitFunctionCall(functionCall: FunctionCall) =
                        functionCall.validity()

                    override fun visitCodeExecutionCall(codeExecutionCall: CodeExecutionCall) =
                        codeExecutionCall.validity()

                    override fun visitUrlContextCall(urlContextCall: UrlContextCall) =
                        urlContextCall.validity()

                    override fun visitGoogleSearchCall(googleSearchCall: GoogleSearchCall) =
                        googleSearchCall.validity()

                    override fun visitMcpServerToolCall(mcpServerToolCall: McpServerToolCall) =
                        mcpServerToolCall.validity()

                    override fun visitFileSearchCall(fileSearchCall: FileSearchCall) =
                        fileSearchCall.validity()

                    override fun visitGoogleMapsCall(googleMapsCall: GoogleMapsCall) =
                        googleMapsCall.validity()

                    override fun visitFunctionResult(functionResult: FunctionResult) =
                        functionResult.validity()

                    override fun visitCodeExecutionResult(
                        codeExecutionResult: CodeExecutionResult
                    ) = codeExecutionResult.validity()

                    override fun visitUrlContextResult(urlContextResult: UrlContextResult) =
                        urlContextResult.validity()

                    override fun visitGoogleSearchResult(googleSearchResult: GoogleSearchResult) =
                        googleSearchResult.validity()

                    override fun visitMcpServerToolResult(
                        mcpServerToolResult: McpServerToolResult
                    ) = mcpServerToolResult.validity()

                    override fun visitFileSearchResult(fileSearchResult: FileSearchResult) =
                        fileSearchResult.validity()

                    override fun visitGoogleMapsResult(googleMapsResult: GoogleMapsResult) =
                        googleMapsResult.validity()

                    override fun visitTextAnnotation(textAnnotation: TextAnnotation) =
                        textAnnotation.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Delta &&
                text == other.text &&
                image == other.image &&
                audio == other.audio &&
                document == other.document &&
                video == other.video &&
                thoughtSummary == other.thoughtSummary &&
                thoughtSignature == other.thoughtSignature &&
                functionCall == other.functionCall &&
                codeExecutionCall == other.codeExecutionCall &&
                urlContextCall == other.urlContextCall &&
                googleSearchCall == other.googleSearchCall &&
                mcpServerToolCall == other.mcpServerToolCall &&
                fileSearchCall == other.fileSearchCall &&
                googleMapsCall == other.googleMapsCall &&
                functionResult == other.functionResult &&
                codeExecutionResult == other.codeExecutionResult &&
                urlContextResult == other.urlContextResult &&
                googleSearchResult == other.googleSearchResult &&
                mcpServerToolResult == other.mcpServerToolResult &&
                fileSearchResult == other.fileSearchResult &&
                googleMapsResult == other.googleMapsResult &&
                textAnnotation == other.textAnnotation
        }

        override fun hashCode(): Int =
            Objects.hash(
                text,
                image,
                audio,
                document,
                video,
                thoughtSummary,
                thoughtSignature,
                functionCall,
                codeExecutionCall,
                urlContextCall,
                googleSearchCall,
                mcpServerToolCall,
                fileSearchCall,
                googleMapsCall,
                functionResult,
                codeExecutionResult,
                urlContextResult,
                googleSearchResult,
                mcpServerToolResult,
                fileSearchResult,
                googleMapsResult,
                textAnnotation,
            )

        override fun toString(): String =
            when {
                text != null -> "Delta{text=$text}"
                image != null -> "Delta{image=$image}"
                audio != null -> "Delta{audio=$audio}"
                document != null -> "Delta{document=$document}"
                video != null -> "Delta{video=$video}"
                thoughtSummary != null -> "Delta{thoughtSummary=$thoughtSummary}"
                thoughtSignature != null -> "Delta{thoughtSignature=$thoughtSignature}"
                functionCall != null -> "Delta{functionCall=$functionCall}"
                codeExecutionCall != null -> "Delta{codeExecutionCall=$codeExecutionCall}"
                urlContextCall != null -> "Delta{urlContextCall=$urlContextCall}"
                googleSearchCall != null -> "Delta{googleSearchCall=$googleSearchCall}"
                mcpServerToolCall != null -> "Delta{mcpServerToolCall=$mcpServerToolCall}"
                fileSearchCall != null -> "Delta{fileSearchCall=$fileSearchCall}"
                googleMapsCall != null -> "Delta{googleMapsCall=$googleMapsCall}"
                functionResult != null -> "Delta{functionResult=$functionResult}"
                codeExecutionResult != null -> "Delta{codeExecutionResult=$codeExecutionResult}"
                urlContextResult != null -> "Delta{urlContextResult=$urlContextResult}"
                googleSearchResult != null -> "Delta{googleSearchResult=$googleSearchResult}"
                mcpServerToolResult != null -> "Delta{mcpServerToolResult=$mcpServerToolResult}"
                fileSearchResult != null -> "Delta{fileSearchResult=$fileSearchResult}"
                googleMapsResult != null -> "Delta{googleMapsResult=$googleMapsResult}"
                textAnnotation != null -> "Delta{textAnnotation=$textAnnotation}"
                _json != null -> "Delta{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Delta")
            }

        companion object {

            @JvmStatic fun ofText(text: Text) = Delta(text = text)

            @JvmStatic fun ofImage(image: Image) = Delta(image = image)

            @JvmStatic fun ofAudio(audio: Audio) = Delta(audio = audio)

            @JvmStatic fun ofDocument(document: Document) = Delta(document = document)

            @JvmStatic fun ofVideo(video: Video) = Delta(video = video)

            @JvmStatic
            fun ofThoughtSummary(thoughtSummary: ThoughtSummary) =
                Delta(thoughtSummary = thoughtSummary)

            @JvmStatic
            fun ofThoughtSignature(thoughtSignature: ThoughtSignature) =
                Delta(thoughtSignature = thoughtSignature)

            @JvmStatic
            fun ofFunctionCall(functionCall: FunctionCall) = Delta(functionCall = functionCall)

            @JvmStatic
            fun ofCodeExecutionCall(codeExecutionCall: CodeExecutionCall) =
                Delta(codeExecutionCall = codeExecutionCall)

            @JvmStatic
            fun ofUrlContextCall(urlContextCall: UrlContextCall) =
                Delta(urlContextCall = urlContextCall)

            @JvmStatic
            fun ofGoogleSearchCall(googleSearchCall: GoogleSearchCall) =
                Delta(googleSearchCall = googleSearchCall)

            @JvmStatic
            fun ofMcpServerToolCall(mcpServerToolCall: McpServerToolCall) =
                Delta(mcpServerToolCall = mcpServerToolCall)

            @JvmStatic
            fun ofFileSearchCall(fileSearchCall: FileSearchCall) =
                Delta(fileSearchCall = fileSearchCall)

            @JvmStatic
            fun ofGoogleMapsCall(googleMapsCall: GoogleMapsCall) =
                Delta(googleMapsCall = googleMapsCall)

            @JvmStatic
            fun ofFunctionResult(functionResult: FunctionResult) =
                Delta(functionResult = functionResult)

            @JvmStatic
            fun ofCodeExecutionResult(codeExecutionResult: CodeExecutionResult) =
                Delta(codeExecutionResult = codeExecutionResult)

            @JvmStatic
            fun ofUrlContextResult(urlContextResult: UrlContextResult) =
                Delta(urlContextResult = urlContextResult)

            @JvmStatic
            fun ofGoogleSearchResult(googleSearchResult: GoogleSearchResult) =
                Delta(googleSearchResult = googleSearchResult)

            @JvmStatic
            fun ofMcpServerToolResult(mcpServerToolResult: McpServerToolResult) =
                Delta(mcpServerToolResult = mcpServerToolResult)

            @JvmStatic
            fun ofFileSearchResult(fileSearchResult: FileSearchResult) =
                Delta(fileSearchResult = fileSearchResult)

            @JvmStatic
            fun ofGoogleMapsResult(googleMapsResult: GoogleMapsResult) =
                Delta(googleMapsResult = googleMapsResult)

            @JvmStatic
            fun ofTextAnnotation(textAnnotation: TextAnnotation) =
                Delta(textAnnotation = textAnnotation)
        }

        /** An interface that defines how to map each variant of [Delta] to a value of type [T]. */
        interface Visitor<out T> {

            fun visitText(text: Text): T

            fun visitImage(image: Image): T

            fun visitAudio(audio: Audio): T

            fun visitDocument(document: Document): T

            fun visitVideo(video: Video): T

            fun visitThoughtSummary(thoughtSummary: ThoughtSummary): T

            fun visitThoughtSignature(thoughtSignature: ThoughtSignature): T

            fun visitFunctionCall(functionCall: FunctionCall): T

            fun visitCodeExecutionCall(codeExecutionCall: CodeExecutionCall): T

            fun visitUrlContextCall(urlContextCall: UrlContextCall): T

            fun visitGoogleSearchCall(googleSearchCall: GoogleSearchCall): T

            fun visitMcpServerToolCall(mcpServerToolCall: McpServerToolCall): T

            fun visitFileSearchCall(fileSearchCall: FileSearchCall): T

            fun visitGoogleMapsCall(googleMapsCall: GoogleMapsCall): T

            fun visitFunctionResult(functionResult: FunctionResult): T

            fun visitCodeExecutionResult(codeExecutionResult: CodeExecutionResult): T

            fun visitUrlContextResult(urlContextResult: UrlContextResult): T

            fun visitGoogleSearchResult(googleSearchResult: GoogleSearchResult): T

            fun visitMcpServerToolResult(mcpServerToolResult: McpServerToolResult): T

            fun visitFileSearchResult(fileSearchResult: FileSearchResult): T

            fun visitGoogleMapsResult(googleMapsResult: GoogleMapsResult): T

            fun visitTextAnnotation(textAnnotation: TextAnnotation): T

            /**
             * Maps an unknown variant of [Delta] to a value of type [T].
             *
             * An instance of [Delta] can contain an unknown variant if it was deserialized from
             * data that doesn't match any known variant. For example, if the SDK is on an older
             * version than the API, then the API may respond with new variants that the SDK is
             * unaware of.
             *
             * @throws GeminiNextGenApiInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw GeminiNextGenApiInvalidDataException("Unknown Delta: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<Delta>(Delta::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Delta {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "text" -> {
                        return tryDeserialize(node, jacksonTypeRef<Text>())?.let {
                            Delta(text = it, _json = json)
                        } ?: Delta(_json = json)
                    }
                    "image" -> {
                        return tryDeserialize(node, jacksonTypeRef<Image>())?.let {
                            Delta(image = it, _json = json)
                        } ?: Delta(_json = json)
                    }
                    "audio" -> {
                        return tryDeserialize(node, jacksonTypeRef<Audio>())?.let {
                            Delta(audio = it, _json = json)
                        } ?: Delta(_json = json)
                    }
                    "document" -> {
                        return tryDeserialize(node, jacksonTypeRef<Document>())?.let {
                            Delta(document = it, _json = json)
                        } ?: Delta(_json = json)
                    }
                    "video" -> {
                        return tryDeserialize(node, jacksonTypeRef<Video>())?.let {
                            Delta(video = it, _json = json)
                        } ?: Delta(_json = json)
                    }
                    "thought_summary" -> {
                        return tryDeserialize(node, jacksonTypeRef<ThoughtSummary>())?.let {
                            Delta(thoughtSummary = it, _json = json)
                        } ?: Delta(_json = json)
                    }
                    "thought_signature" -> {
                        return tryDeserialize(node, jacksonTypeRef<ThoughtSignature>())?.let {
                            Delta(thoughtSignature = it, _json = json)
                        } ?: Delta(_json = json)
                    }
                    "function_call" -> {
                        return tryDeserialize(node, jacksonTypeRef<FunctionCall>())?.let {
                            Delta(functionCall = it, _json = json)
                        } ?: Delta(_json = json)
                    }
                    "code_execution_call" -> {
                        return tryDeserialize(node, jacksonTypeRef<CodeExecutionCall>())?.let {
                            Delta(codeExecutionCall = it, _json = json)
                        } ?: Delta(_json = json)
                    }
                    "url_context_call" -> {
                        return tryDeserialize(node, jacksonTypeRef<UrlContextCall>())?.let {
                            Delta(urlContextCall = it, _json = json)
                        } ?: Delta(_json = json)
                    }
                    "google_search_call" -> {
                        return tryDeserialize(node, jacksonTypeRef<GoogleSearchCall>())?.let {
                            Delta(googleSearchCall = it, _json = json)
                        } ?: Delta(_json = json)
                    }
                    "mcp_server_tool_call" -> {
                        return tryDeserialize(node, jacksonTypeRef<McpServerToolCall>())?.let {
                            Delta(mcpServerToolCall = it, _json = json)
                        } ?: Delta(_json = json)
                    }
                    "file_search_call" -> {
                        return tryDeserialize(node, jacksonTypeRef<FileSearchCall>())?.let {
                            Delta(fileSearchCall = it, _json = json)
                        } ?: Delta(_json = json)
                    }
                    "google_maps_call" -> {
                        return tryDeserialize(node, jacksonTypeRef<GoogleMapsCall>())?.let {
                            Delta(googleMapsCall = it, _json = json)
                        } ?: Delta(_json = json)
                    }
                    "function_result" -> {
                        return tryDeserialize(node, jacksonTypeRef<FunctionResult>())?.let {
                            Delta(functionResult = it, _json = json)
                        } ?: Delta(_json = json)
                    }
                    "code_execution_result" -> {
                        return tryDeserialize(node, jacksonTypeRef<CodeExecutionResult>())?.let {
                            Delta(codeExecutionResult = it, _json = json)
                        } ?: Delta(_json = json)
                    }
                    "url_context_result" -> {
                        return tryDeserialize(node, jacksonTypeRef<UrlContextResult>())?.let {
                            Delta(urlContextResult = it, _json = json)
                        } ?: Delta(_json = json)
                    }
                    "google_search_result" -> {
                        return tryDeserialize(node, jacksonTypeRef<GoogleSearchResult>())?.let {
                            Delta(googleSearchResult = it, _json = json)
                        } ?: Delta(_json = json)
                    }
                    "mcp_server_tool_result" -> {
                        return tryDeserialize(node, jacksonTypeRef<McpServerToolResult>())?.let {
                            Delta(mcpServerToolResult = it, _json = json)
                        } ?: Delta(_json = json)
                    }
                    "file_search_result" -> {
                        return tryDeserialize(node, jacksonTypeRef<FileSearchResult>())?.let {
                            Delta(fileSearchResult = it, _json = json)
                        } ?: Delta(_json = json)
                    }
                    "google_maps_result" -> {
                        return tryDeserialize(node, jacksonTypeRef<GoogleMapsResult>())?.let {
                            Delta(googleMapsResult = it, _json = json)
                        } ?: Delta(_json = json)
                    }
                    "text_annotation" -> {
                        return tryDeserialize(node, jacksonTypeRef<TextAnnotation>())?.let {
                            Delta(textAnnotation = it, _json = json)
                        } ?: Delta(_json = json)
                    }
                }

                return Delta(_json = json)
            }
        }

        internal class Serializer : BaseSerializer<Delta>(Delta::class) {

            override fun serialize(
                value: Delta,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.text != null -> generator.writeObject(value.text)
                    value.image != null -> generator.writeObject(value.image)
                    value.audio != null -> generator.writeObject(value.audio)
                    value.document != null -> generator.writeObject(value.document)
                    value.video != null -> generator.writeObject(value.video)
                    value.thoughtSummary != null -> generator.writeObject(value.thoughtSummary)
                    value.thoughtSignature != null -> generator.writeObject(value.thoughtSignature)
                    value.functionCall != null -> generator.writeObject(value.functionCall)
                    value.codeExecutionCall != null ->
                        generator.writeObject(value.codeExecutionCall)
                    value.urlContextCall != null -> generator.writeObject(value.urlContextCall)
                    value.googleSearchCall != null -> generator.writeObject(value.googleSearchCall)
                    value.mcpServerToolCall != null ->
                        generator.writeObject(value.mcpServerToolCall)
                    value.fileSearchCall != null -> generator.writeObject(value.fileSearchCall)
                    value.googleMapsCall != null -> generator.writeObject(value.googleMapsCall)
                    value.functionResult != null -> generator.writeObject(value.functionResult)
                    value.codeExecutionResult != null ->
                        generator.writeObject(value.codeExecutionResult)
                    value.urlContextResult != null -> generator.writeObject(value.urlContextResult)
                    value.googleSearchResult != null ->
                        generator.writeObject(value.googleSearchResult)
                    value.mcpServerToolResult != null ->
                        generator.writeObject(value.mcpServerToolResult)
                    value.fileSearchResult != null -> generator.writeObject(value.fileSearchResult)
                    value.googleMapsResult != null -> generator.writeObject(value.googleMapsResult)
                    value.textAnnotation != null -> generator.writeObject(value.textAnnotation)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Delta")
                }
            }
        }

        class Text
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val text: JsonField<String>,
            private val type: JsonValue,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("text") @ExcludeMissing text: JsonField<String> = JsonMissing.of(),
                @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
            ) : this(text, type, mutableMapOf())

            /**
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   or is unexpectedly missing or null (e.g. if the server responded with an unexpected
             *   value).
             */
            fun text(): String = text.getRequired("text")

            /**
             * Expected to always return the following:
             * ```java
             * JsonValue.from("text")
             * ```
             *
             * However, this method can be useful for debugging and logging (e.g. if the server
             * responded with an unexpected value).
             */
            @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

            /**
             * Returns the raw JSON value of [text].
             *
             * Unlike [text], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("text") @ExcludeMissing fun _text(): JsonField<String> = text

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
                 * Returns a mutable builder for constructing an instance of [Text].
                 *
                 * The following fields are required:
                 * ```java
                 * .text()
                 * ```
                 */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [Text]. */
            class Builder internal constructor() {

                private var text: JsonField<String>? = null
                private var type: JsonValue = JsonValue.from("text")
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(text: Text) = apply {
                    this.text = text.text
                    type = text.type
                    additionalProperties = text.additionalProperties.toMutableMap()
                }

                fun text(text: String) = text(JsonField.of(text))

                /**
                 * Sets [Builder.text] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.text] with a well-typed [String] value instead.
                 * This method is primarily for setting the field to an undocumented or not yet
                 * supported value.
                 */
                fun text(text: JsonField<String>) = apply { this.text = text }

                /**
                 * Sets the field to an arbitrary JSON value.
                 *
                 * It is usually unnecessary to call this method because the field defaults to the
                 * following:
                 * ```java
                 * JsonValue.from("text")
                 * ```
                 *
                 * This method is primarily for setting the field to an undocumented or not yet
                 * supported value.
                 */
                fun type(type: JsonValue) = apply { this.type = type }

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
                 * Returns an immutable instance of [Text].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 *
                 * The following fields are required:
                 * ```java
                 * .text()
                 * ```
                 *
                 * @throws IllegalStateException if any required field is unset.
                 */
                fun build(): Text =
                    Text(checkRequired("text", text), type, additionalProperties.toMutableMap())
            }

            private var validated: Boolean = false

            fun validate(): Text = apply {
                if (validated) {
                    return@apply
                }

                text()
                _type().let {
                    if (it != JsonValue.from("text")) {
                        throw GeminiNextGenApiInvalidDataException(
                            "'type' is invalid, received $it"
                        )
                    }
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
                (if (text.asKnown().isPresent) 1 else 0) +
                    type.let { if (it == JsonValue.from("text")) 1 else 0 }

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is Text &&
                    text == other.text &&
                    type == other.type &&
                    additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy { Objects.hash(text, type, additionalProperties) }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "Text{text=$text, type=$type, additionalProperties=$additionalProperties}"
        }

        class Image
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val type: JsonValue,
            private val data: JsonField<String>,
            private val mimeType: JsonField<MimeType>,
            private val resolution: JsonField<Resolution>,
            private val uri: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
                @JsonProperty("data") @ExcludeMissing data: JsonField<String> = JsonMissing.of(),
                @JsonProperty("mime_type")
                @ExcludeMissing
                mimeType: JsonField<MimeType> = JsonMissing.of(),
                @JsonProperty("resolution")
                @ExcludeMissing
                resolution: JsonField<Resolution> = JsonMissing.of(),
                @JsonProperty("uri") @ExcludeMissing uri: JsonField<String> = JsonMissing.of(),
            ) : this(type, data, mimeType, resolution, uri, mutableMapOf())

            /**
             * Expected to always return the following:
             * ```java
             * JsonValue.from("image")
             * ```
             *
             * However, this method can be useful for debugging and logging (e.g. if the server
             * responded with an unexpected value).
             */
            @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

            /**
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   (e.g. if the server responded with an unexpected value).
             */
            fun data(): Optional<String> = data.getOptional("data")

            /**
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   (e.g. if the server responded with an unexpected value).
             */
            fun mimeType(): Optional<MimeType> = mimeType.getOptional("mime_type")

            /**
             * The resolution of the media.
             *
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   (e.g. if the server responded with an unexpected value).
             */
            fun resolution(): Optional<Resolution> = resolution.getOptional("resolution")

            /**
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   (e.g. if the server responded with an unexpected value).
             */
            fun uri(): Optional<String> = uri.getOptional("uri")

            /**
             * Returns the raw JSON value of [data].
             *
             * Unlike [data], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("data") @ExcludeMissing fun _data(): JsonField<String> = data

            /**
             * Returns the raw JSON value of [mimeType].
             *
             * Unlike [mimeType], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("mime_type")
            @ExcludeMissing
            fun _mimeType(): JsonField<MimeType> = mimeType

            /**
             * Returns the raw JSON value of [resolution].
             *
             * Unlike [resolution], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("resolution")
            @ExcludeMissing
            fun _resolution(): JsonField<Resolution> = resolution

            /**
             * Returns the raw JSON value of [uri].
             *
             * Unlike [uri], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("uri") @ExcludeMissing fun _uri(): JsonField<String> = uri

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

                /** Returns a mutable builder for constructing an instance of [Image]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [Image]. */
            class Builder internal constructor() {

                private var type: JsonValue = JsonValue.from("image")
                private var data: JsonField<String> = JsonMissing.of()
                private var mimeType: JsonField<MimeType> = JsonMissing.of()
                private var resolution: JsonField<Resolution> = JsonMissing.of()
                private var uri: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(image: Image) = apply {
                    type = image.type
                    data = image.data
                    mimeType = image.mimeType
                    resolution = image.resolution
                    uri = image.uri
                    additionalProperties = image.additionalProperties.toMutableMap()
                }

                /**
                 * Sets the field to an arbitrary JSON value.
                 *
                 * It is usually unnecessary to call this method because the field defaults to the
                 * following:
                 * ```java
                 * JsonValue.from("image")
                 * ```
                 *
                 * This method is primarily for setting the field to an undocumented or not yet
                 * supported value.
                 */
                fun type(type: JsonValue) = apply { this.type = type }

                fun data(data: String) = data(JsonField.of(data))

                /**
                 * Sets [Builder.data] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.data] with a well-typed [String] value instead.
                 * This method is primarily for setting the field to an undocumented or not yet
                 * supported value.
                 */
                fun data(data: JsonField<String>) = apply { this.data = data }

                fun mimeType(mimeType: MimeType) = mimeType(JsonField.of(mimeType))

                /**
                 * Sets [Builder.mimeType] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.mimeType] with a well-typed [MimeType] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun mimeType(mimeType: JsonField<MimeType>) = apply { this.mimeType = mimeType }

                /** The resolution of the media. */
                fun resolution(resolution: Resolution) = resolution(JsonField.of(resolution))

                /**
                 * Sets [Builder.resolution] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.resolution] with a well-typed [Resolution] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun resolution(resolution: JsonField<Resolution>) = apply {
                    this.resolution = resolution
                }

                fun uri(uri: String) = uri(JsonField.of(uri))

                /**
                 * Sets [Builder.uri] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.uri] with a well-typed [String] value instead.
                 * This method is primarily for setting the field to an undocumented or not yet
                 * supported value.
                 */
                fun uri(uri: JsonField<String>) = apply { this.uri = uri }

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
                 * Returns an immutable instance of [Image].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 */
                fun build(): Image =
                    Image(
                        type,
                        data,
                        mimeType,
                        resolution,
                        uri,
                        additionalProperties.toMutableMap(),
                    )
            }

            private var validated: Boolean = false

            fun validate(): Image = apply {
                if (validated) {
                    return@apply
                }

                _type().let {
                    if (it != JsonValue.from("image")) {
                        throw GeminiNextGenApiInvalidDataException(
                            "'type' is invalid, received $it"
                        )
                    }
                }
                data()
                mimeType().ifPresent { it.validate() }
                resolution().ifPresent { it.validate() }
                uri()
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
                type.let { if (it == JsonValue.from("image")) 1 else 0 } +
                    (if (data.asKnown().isPresent) 1 else 0) +
                    (mimeType.asKnown().getOrNull()?.validity() ?: 0) +
                    (resolution.asKnown().getOrNull()?.validity() ?: 0) +
                    (if (uri.asKnown().isPresent) 1 else 0)

            class MimeType @JsonCreator private constructor(private val value: JsonField<String>) :
                Enum {

                /**
                 * Returns this class instance's raw value.
                 *
                 * This is usually only useful if this instance was deserialized from data that
                 * doesn't match any known member, and you want to know that value. For example, if
                 * the SDK is on an older version than the API, then the API may respond with new
                 * members that the SDK is unaware of.
                 */
                @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

                companion object {

                    @JvmField val IMAGE_PNG = of("image/png")

                    @JvmField val IMAGE_JPEG = of("image/jpeg")

                    @JvmField val IMAGE_WEBP = of("image/webp")

                    @JvmField val IMAGE_HEIC = of("image/heic")

                    @JvmField val IMAGE_HEIF = of("image/heif")

                    @JvmField val IMAGE_GIF = of("image/gif")

                    @JvmField val IMAGE_BMP = of("image/bmp")

                    @JvmField val IMAGE_TIFF = of("image/tiff")

                    @JvmStatic fun of(value: String) = MimeType(JsonField.of(value))
                }

                /** An enum containing [MimeType]'s known values. */
                enum class Known {
                    IMAGE_PNG,
                    IMAGE_JPEG,
                    IMAGE_WEBP,
                    IMAGE_HEIC,
                    IMAGE_HEIF,
                    IMAGE_GIF,
                    IMAGE_BMP,
                    IMAGE_TIFF,
                }

                /**
                 * An enum containing [MimeType]'s known values, as well as an [_UNKNOWN] member.
                 *
                 * An instance of [MimeType] can contain an unknown value in a couple of cases:
                 * - It was deserialized from data that doesn't match any known member. For example,
                 *   if the SDK is on an older version than the API, then the API may respond with
                 *   new members that the SDK is unaware of.
                 * - It was constructed with an arbitrary value using the [of] method.
                 */
                enum class Value {
                    IMAGE_PNG,
                    IMAGE_JPEG,
                    IMAGE_WEBP,
                    IMAGE_HEIC,
                    IMAGE_HEIF,
                    IMAGE_GIF,
                    IMAGE_BMP,
                    IMAGE_TIFF,
                    /**
                     * An enum member indicating that [MimeType] was instantiated with an unknown
                     * value.
                     */
                    _UNKNOWN,
                }

                /**
                 * Returns an enum member corresponding to this class instance's value, or
                 * [Value._UNKNOWN] if the class was instantiated with an unknown value.
                 *
                 * Use the [known] method instead if you're certain the value is always known or if
                 * you want to throw for the unknown case.
                 */
                fun value(): Value =
                    when (this) {
                        IMAGE_PNG -> Value.IMAGE_PNG
                        IMAGE_JPEG -> Value.IMAGE_JPEG
                        IMAGE_WEBP -> Value.IMAGE_WEBP
                        IMAGE_HEIC -> Value.IMAGE_HEIC
                        IMAGE_HEIF -> Value.IMAGE_HEIF
                        IMAGE_GIF -> Value.IMAGE_GIF
                        IMAGE_BMP -> Value.IMAGE_BMP
                        IMAGE_TIFF -> Value.IMAGE_TIFF
                        else -> Value._UNKNOWN
                    }

                /**
                 * Returns an enum member corresponding to this class instance's value.
                 *
                 * Use the [value] method instead if you're uncertain the value is always known and
                 * don't want to throw for the unknown case.
                 *
                 * @throws GeminiNextGenApiInvalidDataException if this class instance's value is a
                 *   not a known member.
                 */
                fun known(): Known =
                    when (this) {
                        IMAGE_PNG -> Known.IMAGE_PNG
                        IMAGE_JPEG -> Known.IMAGE_JPEG
                        IMAGE_WEBP -> Known.IMAGE_WEBP
                        IMAGE_HEIC -> Known.IMAGE_HEIC
                        IMAGE_HEIF -> Known.IMAGE_HEIF
                        IMAGE_GIF -> Known.IMAGE_GIF
                        IMAGE_BMP -> Known.IMAGE_BMP
                        IMAGE_TIFF -> Known.IMAGE_TIFF
                        else ->
                            throw GeminiNextGenApiInvalidDataException("Unknown MimeType: $value")
                    }

                /**
                 * Returns this class instance's primitive wire representation.
                 *
                 * This differs from the [toString] method because that method is primarily for
                 * debugging and generally doesn't throw.
                 *
                 * @throws GeminiNextGenApiInvalidDataException if this class instance's value does
                 *   not have the expected primitive type.
                 */
                fun asString(): String =
                    _value().asString().orElseThrow {
                        GeminiNextGenApiInvalidDataException("Value is not a String")
                    }

                private var validated: Boolean = false

                fun validate(): MimeType = apply {
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

                    return other is MimeType && value == other.value
                }

                override fun hashCode() = value.hashCode()

                override fun toString() = value.toString()
            }

            /** The resolution of the media. */
            class Resolution
            @JsonCreator
            private constructor(private val value: JsonField<String>) : Enum {

                /**
                 * Returns this class instance's raw value.
                 *
                 * This is usually only useful if this instance was deserialized from data that
                 * doesn't match any known member, and you want to know that value. For example, if
                 * the SDK is on an older version than the API, then the API may respond with new
                 * members that the SDK is unaware of.
                 */
                @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

                companion object {

                    @JvmField val LOW = of("low")

                    @JvmField val MEDIUM = of("medium")

                    @JvmField val HIGH = of("high")

                    @JvmField val ULTRA_HIGH = of("ultra_high")

                    @JvmStatic fun of(value: String) = Resolution(JsonField.of(value))
                }

                /** An enum containing [Resolution]'s known values. */
                enum class Known {
                    LOW,
                    MEDIUM,
                    HIGH,
                    ULTRA_HIGH,
                }

                /**
                 * An enum containing [Resolution]'s known values, as well as an [_UNKNOWN] member.
                 *
                 * An instance of [Resolution] can contain an unknown value in a couple of cases:
                 * - It was deserialized from data that doesn't match any known member. For example,
                 *   if the SDK is on an older version than the API, then the API may respond with
                 *   new members that the SDK is unaware of.
                 * - It was constructed with an arbitrary value using the [of] method.
                 */
                enum class Value {
                    LOW,
                    MEDIUM,
                    HIGH,
                    ULTRA_HIGH,
                    /**
                     * An enum member indicating that [Resolution] was instantiated with an unknown
                     * value.
                     */
                    _UNKNOWN,
                }

                /**
                 * Returns an enum member corresponding to this class instance's value, or
                 * [Value._UNKNOWN] if the class was instantiated with an unknown value.
                 *
                 * Use the [known] method instead if you're certain the value is always known or if
                 * you want to throw for the unknown case.
                 */
                fun value(): Value =
                    when (this) {
                        LOW -> Value.LOW
                        MEDIUM -> Value.MEDIUM
                        HIGH -> Value.HIGH
                        ULTRA_HIGH -> Value.ULTRA_HIGH
                        else -> Value._UNKNOWN
                    }

                /**
                 * Returns an enum member corresponding to this class instance's value.
                 *
                 * Use the [value] method instead if you're uncertain the value is always known and
                 * don't want to throw for the unknown case.
                 *
                 * @throws GeminiNextGenApiInvalidDataException if this class instance's value is a
                 *   not a known member.
                 */
                fun known(): Known =
                    when (this) {
                        LOW -> Known.LOW
                        MEDIUM -> Known.MEDIUM
                        HIGH -> Known.HIGH
                        ULTRA_HIGH -> Known.ULTRA_HIGH
                        else ->
                            throw GeminiNextGenApiInvalidDataException("Unknown Resolution: $value")
                    }

                /**
                 * Returns this class instance's primitive wire representation.
                 *
                 * This differs from the [toString] method because that method is primarily for
                 * debugging and generally doesn't throw.
                 *
                 * @throws GeminiNextGenApiInvalidDataException if this class instance's value does
                 *   not have the expected primitive type.
                 */
                fun asString(): String =
                    _value().asString().orElseThrow {
                        GeminiNextGenApiInvalidDataException("Value is not a String")
                    }

                private var validated: Boolean = false

                fun validate(): Resolution = apply {
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

                    return other is Resolution && value == other.value
                }

                override fun hashCode() = value.hashCode()

                override fun toString() = value.toString()
            }

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is Image &&
                    type == other.type &&
                    data == other.data &&
                    mimeType == other.mimeType &&
                    resolution == other.resolution &&
                    uri == other.uri &&
                    additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(type, data, mimeType, resolution, uri, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "Image{type=$type, data=$data, mimeType=$mimeType, resolution=$resolution, uri=$uri, additionalProperties=$additionalProperties}"
        }

        class Audio
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val type: JsonValue,
            private val channels: JsonField<Int>,
            private val data: JsonField<String>,
            private val mimeType: JsonField<MimeType>,
            private val rate: JsonField<Int>,
            private val uri: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
                @JsonProperty("channels")
                @ExcludeMissing
                channels: JsonField<Int> = JsonMissing.of(),
                @JsonProperty("data") @ExcludeMissing data: JsonField<String> = JsonMissing.of(),
                @JsonProperty("mime_type")
                @ExcludeMissing
                mimeType: JsonField<MimeType> = JsonMissing.of(),
                @JsonProperty("rate") @ExcludeMissing rate: JsonField<Int> = JsonMissing.of(),
                @JsonProperty("uri") @ExcludeMissing uri: JsonField<String> = JsonMissing.of(),
            ) : this(type, channels, data, mimeType, rate, uri, mutableMapOf())

            /**
             * Expected to always return the following:
             * ```java
             * JsonValue.from("audio")
             * ```
             *
             * However, this method can be useful for debugging and logging (e.g. if the server
             * responded with an unexpected value).
             */
            @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

            /**
             * The number of audio channels.
             *
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   (e.g. if the server responded with an unexpected value).
             */
            fun channels(): Optional<Int> = channels.getOptional("channels")

            /**
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   (e.g. if the server responded with an unexpected value).
             */
            fun data(): Optional<String> = data.getOptional("data")

            /**
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   (e.g. if the server responded with an unexpected value).
             */
            fun mimeType(): Optional<MimeType> = mimeType.getOptional("mime_type")

            /**
             * The sample rate of the audio.
             *
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   (e.g. if the server responded with an unexpected value).
             */
            fun rate(): Optional<Int> = rate.getOptional("rate")

            /**
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   (e.g. if the server responded with an unexpected value).
             */
            fun uri(): Optional<String> = uri.getOptional("uri")

            /**
             * Returns the raw JSON value of [channels].
             *
             * Unlike [channels], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("channels") @ExcludeMissing fun _channels(): JsonField<Int> = channels

            /**
             * Returns the raw JSON value of [data].
             *
             * Unlike [data], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("data") @ExcludeMissing fun _data(): JsonField<String> = data

            /**
             * Returns the raw JSON value of [mimeType].
             *
             * Unlike [mimeType], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("mime_type")
            @ExcludeMissing
            fun _mimeType(): JsonField<MimeType> = mimeType

            /**
             * Returns the raw JSON value of [rate].
             *
             * Unlike [rate], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("rate") @ExcludeMissing fun _rate(): JsonField<Int> = rate

            /**
             * Returns the raw JSON value of [uri].
             *
             * Unlike [uri], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("uri") @ExcludeMissing fun _uri(): JsonField<String> = uri

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

                /** Returns a mutable builder for constructing an instance of [Audio]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [Audio]. */
            class Builder internal constructor() {

                private var type: JsonValue = JsonValue.from("audio")
                private var channels: JsonField<Int> = JsonMissing.of()
                private var data: JsonField<String> = JsonMissing.of()
                private var mimeType: JsonField<MimeType> = JsonMissing.of()
                private var rate: JsonField<Int> = JsonMissing.of()
                private var uri: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(audio: Audio) = apply {
                    type = audio.type
                    channels = audio.channels
                    data = audio.data
                    mimeType = audio.mimeType
                    rate = audio.rate
                    uri = audio.uri
                    additionalProperties = audio.additionalProperties.toMutableMap()
                }

                /**
                 * Sets the field to an arbitrary JSON value.
                 *
                 * It is usually unnecessary to call this method because the field defaults to the
                 * following:
                 * ```java
                 * JsonValue.from("audio")
                 * ```
                 *
                 * This method is primarily for setting the field to an undocumented or not yet
                 * supported value.
                 */
                fun type(type: JsonValue) = apply { this.type = type }

                /** The number of audio channels. */
                fun channels(channels: Int) = channels(JsonField.of(channels))

                /**
                 * Sets [Builder.channels] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.channels] with a well-typed [Int] value instead.
                 * This method is primarily for setting the field to an undocumented or not yet
                 * supported value.
                 */
                fun channels(channels: JsonField<Int>) = apply { this.channels = channels }

                fun data(data: String) = data(JsonField.of(data))

                /**
                 * Sets [Builder.data] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.data] with a well-typed [String] value instead.
                 * This method is primarily for setting the field to an undocumented or not yet
                 * supported value.
                 */
                fun data(data: JsonField<String>) = apply { this.data = data }

                fun mimeType(mimeType: MimeType) = mimeType(JsonField.of(mimeType))

                /**
                 * Sets [Builder.mimeType] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.mimeType] with a well-typed [MimeType] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun mimeType(mimeType: JsonField<MimeType>) = apply { this.mimeType = mimeType }

                /** The sample rate of the audio. */
                fun rate(rate: Int) = rate(JsonField.of(rate))

                /**
                 * Sets [Builder.rate] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.rate] with a well-typed [Int] value instead.
                 * This method is primarily for setting the field to an undocumented or not yet
                 * supported value.
                 */
                fun rate(rate: JsonField<Int>) = apply { this.rate = rate }

                fun uri(uri: String) = uri(JsonField.of(uri))

                /**
                 * Sets [Builder.uri] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.uri] with a well-typed [String] value instead.
                 * This method is primarily for setting the field to an undocumented or not yet
                 * supported value.
                 */
                fun uri(uri: JsonField<String>) = apply { this.uri = uri }

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
                 * Returns an immutable instance of [Audio].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 */
                fun build(): Audio =
                    Audio(
                        type,
                        channels,
                        data,
                        mimeType,
                        rate,
                        uri,
                        additionalProperties.toMutableMap(),
                    )
            }

            private var validated: Boolean = false

            fun validate(): Audio = apply {
                if (validated) {
                    return@apply
                }

                _type().let {
                    if (it != JsonValue.from("audio")) {
                        throw GeminiNextGenApiInvalidDataException(
                            "'type' is invalid, received $it"
                        )
                    }
                }
                channels()
                data()
                mimeType().ifPresent { it.validate() }
                rate()
                uri()
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
                type.let { if (it == JsonValue.from("audio")) 1 else 0 } +
                    (if (channels.asKnown().isPresent) 1 else 0) +
                    (if (data.asKnown().isPresent) 1 else 0) +
                    (mimeType.asKnown().getOrNull()?.validity() ?: 0) +
                    (if (rate.asKnown().isPresent) 1 else 0) +
                    (if (uri.asKnown().isPresent) 1 else 0)

            class MimeType @JsonCreator private constructor(private val value: JsonField<String>) :
                Enum {

                /**
                 * Returns this class instance's raw value.
                 *
                 * This is usually only useful if this instance was deserialized from data that
                 * doesn't match any known member, and you want to know that value. For example, if
                 * the SDK is on an older version than the API, then the API may respond with new
                 * members that the SDK is unaware of.
                 */
                @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

                companion object {

                    @JvmField val AUDIO_WAV = of("audio/wav")

                    @JvmField val AUDIO_MP3 = of("audio/mp3")

                    @JvmField val AUDIO_AIFF = of("audio/aiff")

                    @JvmField val AUDIO_AAC = of("audio/aac")

                    @JvmField val AUDIO_OGG = of("audio/ogg")

                    @JvmField val AUDIO_FLAC = of("audio/flac")

                    @JvmField val AUDIO_MPEG = of("audio/mpeg")

                    @JvmField val AUDIO_M4A = of("audio/m4a")

                    @JvmField val AUDIO_L16 = of("audio/l16")

                    @JvmField val AUDIO_OPUS = of("audio/opus")

                    @JvmField val AUDIO_ALAW = of("audio/alaw")

                    @JvmField val AUDIO_MULAW = of("audio/mulaw")

                    @JvmStatic fun of(value: String) = MimeType(JsonField.of(value))
                }

                /** An enum containing [MimeType]'s known values. */
                enum class Known {
                    AUDIO_WAV,
                    AUDIO_MP3,
                    AUDIO_AIFF,
                    AUDIO_AAC,
                    AUDIO_OGG,
                    AUDIO_FLAC,
                    AUDIO_MPEG,
                    AUDIO_M4A,
                    AUDIO_L16,
                    AUDIO_OPUS,
                    AUDIO_ALAW,
                    AUDIO_MULAW,
                }

                /**
                 * An enum containing [MimeType]'s known values, as well as an [_UNKNOWN] member.
                 *
                 * An instance of [MimeType] can contain an unknown value in a couple of cases:
                 * - It was deserialized from data that doesn't match any known member. For example,
                 *   if the SDK is on an older version than the API, then the API may respond with
                 *   new members that the SDK is unaware of.
                 * - It was constructed with an arbitrary value using the [of] method.
                 */
                enum class Value {
                    AUDIO_WAV,
                    AUDIO_MP3,
                    AUDIO_AIFF,
                    AUDIO_AAC,
                    AUDIO_OGG,
                    AUDIO_FLAC,
                    AUDIO_MPEG,
                    AUDIO_M4A,
                    AUDIO_L16,
                    AUDIO_OPUS,
                    AUDIO_ALAW,
                    AUDIO_MULAW,
                    /**
                     * An enum member indicating that [MimeType] was instantiated with an unknown
                     * value.
                     */
                    _UNKNOWN,
                }

                /**
                 * Returns an enum member corresponding to this class instance's value, or
                 * [Value._UNKNOWN] if the class was instantiated with an unknown value.
                 *
                 * Use the [known] method instead if you're certain the value is always known or if
                 * you want to throw for the unknown case.
                 */
                fun value(): Value =
                    when (this) {
                        AUDIO_WAV -> Value.AUDIO_WAV
                        AUDIO_MP3 -> Value.AUDIO_MP3
                        AUDIO_AIFF -> Value.AUDIO_AIFF
                        AUDIO_AAC -> Value.AUDIO_AAC
                        AUDIO_OGG -> Value.AUDIO_OGG
                        AUDIO_FLAC -> Value.AUDIO_FLAC
                        AUDIO_MPEG -> Value.AUDIO_MPEG
                        AUDIO_M4A -> Value.AUDIO_M4A
                        AUDIO_L16 -> Value.AUDIO_L16
                        AUDIO_OPUS -> Value.AUDIO_OPUS
                        AUDIO_ALAW -> Value.AUDIO_ALAW
                        AUDIO_MULAW -> Value.AUDIO_MULAW
                        else -> Value._UNKNOWN
                    }

                /**
                 * Returns an enum member corresponding to this class instance's value.
                 *
                 * Use the [value] method instead if you're uncertain the value is always known and
                 * don't want to throw for the unknown case.
                 *
                 * @throws GeminiNextGenApiInvalidDataException if this class instance's value is a
                 *   not a known member.
                 */
                fun known(): Known =
                    when (this) {
                        AUDIO_WAV -> Known.AUDIO_WAV
                        AUDIO_MP3 -> Known.AUDIO_MP3
                        AUDIO_AIFF -> Known.AUDIO_AIFF
                        AUDIO_AAC -> Known.AUDIO_AAC
                        AUDIO_OGG -> Known.AUDIO_OGG
                        AUDIO_FLAC -> Known.AUDIO_FLAC
                        AUDIO_MPEG -> Known.AUDIO_MPEG
                        AUDIO_M4A -> Known.AUDIO_M4A
                        AUDIO_L16 -> Known.AUDIO_L16
                        AUDIO_OPUS -> Known.AUDIO_OPUS
                        AUDIO_ALAW -> Known.AUDIO_ALAW
                        AUDIO_MULAW -> Known.AUDIO_MULAW
                        else ->
                            throw GeminiNextGenApiInvalidDataException("Unknown MimeType: $value")
                    }

                /**
                 * Returns this class instance's primitive wire representation.
                 *
                 * This differs from the [toString] method because that method is primarily for
                 * debugging and generally doesn't throw.
                 *
                 * @throws GeminiNextGenApiInvalidDataException if this class instance's value does
                 *   not have the expected primitive type.
                 */
                fun asString(): String =
                    _value().asString().orElseThrow {
                        GeminiNextGenApiInvalidDataException("Value is not a String")
                    }

                private var validated: Boolean = false

                fun validate(): MimeType = apply {
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

                    return other is MimeType && value == other.value
                }

                override fun hashCode() = value.hashCode()

                override fun toString() = value.toString()
            }

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is Audio &&
                    type == other.type &&
                    channels == other.channels &&
                    data == other.data &&
                    mimeType == other.mimeType &&
                    rate == other.rate &&
                    uri == other.uri &&
                    additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(type, channels, data, mimeType, rate, uri, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "Audio{type=$type, channels=$channels, data=$data, mimeType=$mimeType, rate=$rate, uri=$uri, additionalProperties=$additionalProperties}"
        }

        class Document
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val type: JsonValue,
            private val data: JsonField<String>,
            private val mimeType: JsonField<MimeType>,
            private val uri: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
                @JsonProperty("data") @ExcludeMissing data: JsonField<String> = JsonMissing.of(),
                @JsonProperty("mime_type")
                @ExcludeMissing
                mimeType: JsonField<MimeType> = JsonMissing.of(),
                @JsonProperty("uri") @ExcludeMissing uri: JsonField<String> = JsonMissing.of(),
            ) : this(type, data, mimeType, uri, mutableMapOf())

            /**
             * Expected to always return the following:
             * ```java
             * JsonValue.from("document")
             * ```
             *
             * However, this method can be useful for debugging and logging (e.g. if the server
             * responded with an unexpected value).
             */
            @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

            /**
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   (e.g. if the server responded with an unexpected value).
             */
            fun data(): Optional<String> = data.getOptional("data")

            /**
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   (e.g. if the server responded with an unexpected value).
             */
            fun mimeType(): Optional<MimeType> = mimeType.getOptional("mime_type")

            /**
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   (e.g. if the server responded with an unexpected value).
             */
            fun uri(): Optional<String> = uri.getOptional("uri")

            /**
             * Returns the raw JSON value of [data].
             *
             * Unlike [data], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("data") @ExcludeMissing fun _data(): JsonField<String> = data

            /**
             * Returns the raw JSON value of [mimeType].
             *
             * Unlike [mimeType], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("mime_type")
            @ExcludeMissing
            fun _mimeType(): JsonField<MimeType> = mimeType

            /**
             * Returns the raw JSON value of [uri].
             *
             * Unlike [uri], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("uri") @ExcludeMissing fun _uri(): JsonField<String> = uri

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

                /** Returns a mutable builder for constructing an instance of [Document]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [Document]. */
            class Builder internal constructor() {

                private var type: JsonValue = JsonValue.from("document")
                private var data: JsonField<String> = JsonMissing.of()
                private var mimeType: JsonField<MimeType> = JsonMissing.of()
                private var uri: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(document: Document) = apply {
                    type = document.type
                    data = document.data
                    mimeType = document.mimeType
                    uri = document.uri
                    additionalProperties = document.additionalProperties.toMutableMap()
                }

                /**
                 * Sets the field to an arbitrary JSON value.
                 *
                 * It is usually unnecessary to call this method because the field defaults to the
                 * following:
                 * ```java
                 * JsonValue.from("document")
                 * ```
                 *
                 * This method is primarily for setting the field to an undocumented or not yet
                 * supported value.
                 */
                fun type(type: JsonValue) = apply { this.type = type }

                fun data(data: String) = data(JsonField.of(data))

                /**
                 * Sets [Builder.data] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.data] with a well-typed [String] value instead.
                 * This method is primarily for setting the field to an undocumented or not yet
                 * supported value.
                 */
                fun data(data: JsonField<String>) = apply { this.data = data }

                fun mimeType(mimeType: MimeType) = mimeType(JsonField.of(mimeType))

                /**
                 * Sets [Builder.mimeType] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.mimeType] with a well-typed [MimeType] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun mimeType(mimeType: JsonField<MimeType>) = apply { this.mimeType = mimeType }

                fun uri(uri: String) = uri(JsonField.of(uri))

                /**
                 * Sets [Builder.uri] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.uri] with a well-typed [String] value instead.
                 * This method is primarily for setting the field to an undocumented or not yet
                 * supported value.
                 */
                fun uri(uri: JsonField<String>) = apply { this.uri = uri }

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
                 * Returns an immutable instance of [Document].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 */
                fun build(): Document =
                    Document(type, data, mimeType, uri, additionalProperties.toMutableMap())
            }

            private var validated: Boolean = false

            fun validate(): Document = apply {
                if (validated) {
                    return@apply
                }

                _type().let {
                    if (it != JsonValue.from("document")) {
                        throw GeminiNextGenApiInvalidDataException(
                            "'type' is invalid, received $it"
                        )
                    }
                }
                data()
                mimeType().ifPresent { it.validate() }
                uri()
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
                type.let { if (it == JsonValue.from("document")) 1 else 0 } +
                    (if (data.asKnown().isPresent) 1 else 0) +
                    (mimeType.asKnown().getOrNull()?.validity() ?: 0) +
                    (if (uri.asKnown().isPresent) 1 else 0)

            class MimeType @JsonCreator private constructor(private val value: JsonField<String>) :
                Enum {

                /**
                 * Returns this class instance's raw value.
                 *
                 * This is usually only useful if this instance was deserialized from data that
                 * doesn't match any known member, and you want to know that value. For example, if
                 * the SDK is on an older version than the API, then the API may respond with new
                 * members that the SDK is unaware of.
                 */
                @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

                companion object {

                    @JvmField val APPLICATION_PDF = of("application/pdf")

                    @JvmStatic fun of(value: String) = MimeType(JsonField.of(value))
                }

                /** An enum containing [MimeType]'s known values. */
                enum class Known {
                    APPLICATION_PDF
                }

                /**
                 * An enum containing [MimeType]'s known values, as well as an [_UNKNOWN] member.
                 *
                 * An instance of [MimeType] can contain an unknown value in a couple of cases:
                 * - It was deserialized from data that doesn't match any known member. For example,
                 *   if the SDK is on an older version than the API, then the API may respond with
                 *   new members that the SDK is unaware of.
                 * - It was constructed with an arbitrary value using the [of] method.
                 */
                enum class Value {
                    APPLICATION_PDF,
                    /**
                     * An enum member indicating that [MimeType] was instantiated with an unknown
                     * value.
                     */
                    _UNKNOWN,
                }

                /**
                 * Returns an enum member corresponding to this class instance's value, or
                 * [Value._UNKNOWN] if the class was instantiated with an unknown value.
                 *
                 * Use the [known] method instead if you're certain the value is always known or if
                 * you want to throw for the unknown case.
                 */
                fun value(): Value =
                    when (this) {
                        APPLICATION_PDF -> Value.APPLICATION_PDF
                        else -> Value._UNKNOWN
                    }

                /**
                 * Returns an enum member corresponding to this class instance's value.
                 *
                 * Use the [value] method instead if you're uncertain the value is always known and
                 * don't want to throw for the unknown case.
                 *
                 * @throws GeminiNextGenApiInvalidDataException if this class instance's value is a
                 *   not a known member.
                 */
                fun known(): Known =
                    when (this) {
                        APPLICATION_PDF -> Known.APPLICATION_PDF
                        else ->
                            throw GeminiNextGenApiInvalidDataException("Unknown MimeType: $value")
                    }

                /**
                 * Returns this class instance's primitive wire representation.
                 *
                 * This differs from the [toString] method because that method is primarily for
                 * debugging and generally doesn't throw.
                 *
                 * @throws GeminiNextGenApiInvalidDataException if this class instance's value does
                 *   not have the expected primitive type.
                 */
                fun asString(): String =
                    _value().asString().orElseThrow {
                        GeminiNextGenApiInvalidDataException("Value is not a String")
                    }

                private var validated: Boolean = false

                fun validate(): MimeType = apply {
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

                    return other is MimeType && value == other.value
                }

                override fun hashCode() = value.hashCode()

                override fun toString() = value.toString()
            }

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is Document &&
                    type == other.type &&
                    data == other.data &&
                    mimeType == other.mimeType &&
                    uri == other.uri &&
                    additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(type, data, mimeType, uri, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "Document{type=$type, data=$data, mimeType=$mimeType, uri=$uri, additionalProperties=$additionalProperties}"
        }

        class Video
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val type: JsonValue,
            private val data: JsonField<String>,
            private val mimeType: JsonField<MimeType>,
            private val resolution: JsonField<Resolution>,
            private val uri: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
                @JsonProperty("data") @ExcludeMissing data: JsonField<String> = JsonMissing.of(),
                @JsonProperty("mime_type")
                @ExcludeMissing
                mimeType: JsonField<MimeType> = JsonMissing.of(),
                @JsonProperty("resolution")
                @ExcludeMissing
                resolution: JsonField<Resolution> = JsonMissing.of(),
                @JsonProperty("uri") @ExcludeMissing uri: JsonField<String> = JsonMissing.of(),
            ) : this(type, data, mimeType, resolution, uri, mutableMapOf())

            /**
             * Expected to always return the following:
             * ```java
             * JsonValue.from("video")
             * ```
             *
             * However, this method can be useful for debugging and logging (e.g. if the server
             * responded with an unexpected value).
             */
            @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

            /**
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   (e.g. if the server responded with an unexpected value).
             */
            fun data(): Optional<String> = data.getOptional("data")

            /**
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   (e.g. if the server responded with an unexpected value).
             */
            fun mimeType(): Optional<MimeType> = mimeType.getOptional("mime_type")

            /**
             * The resolution of the media.
             *
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   (e.g. if the server responded with an unexpected value).
             */
            fun resolution(): Optional<Resolution> = resolution.getOptional("resolution")

            /**
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   (e.g. if the server responded with an unexpected value).
             */
            fun uri(): Optional<String> = uri.getOptional("uri")

            /**
             * Returns the raw JSON value of [data].
             *
             * Unlike [data], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("data") @ExcludeMissing fun _data(): JsonField<String> = data

            /**
             * Returns the raw JSON value of [mimeType].
             *
             * Unlike [mimeType], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("mime_type")
            @ExcludeMissing
            fun _mimeType(): JsonField<MimeType> = mimeType

            /**
             * Returns the raw JSON value of [resolution].
             *
             * Unlike [resolution], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("resolution")
            @ExcludeMissing
            fun _resolution(): JsonField<Resolution> = resolution

            /**
             * Returns the raw JSON value of [uri].
             *
             * Unlike [uri], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("uri") @ExcludeMissing fun _uri(): JsonField<String> = uri

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

                /** Returns a mutable builder for constructing an instance of [Video]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [Video]. */
            class Builder internal constructor() {

                private var type: JsonValue = JsonValue.from("video")
                private var data: JsonField<String> = JsonMissing.of()
                private var mimeType: JsonField<MimeType> = JsonMissing.of()
                private var resolution: JsonField<Resolution> = JsonMissing.of()
                private var uri: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(video: Video) = apply {
                    type = video.type
                    data = video.data
                    mimeType = video.mimeType
                    resolution = video.resolution
                    uri = video.uri
                    additionalProperties = video.additionalProperties.toMutableMap()
                }

                /**
                 * Sets the field to an arbitrary JSON value.
                 *
                 * It is usually unnecessary to call this method because the field defaults to the
                 * following:
                 * ```java
                 * JsonValue.from("video")
                 * ```
                 *
                 * This method is primarily for setting the field to an undocumented or not yet
                 * supported value.
                 */
                fun type(type: JsonValue) = apply { this.type = type }

                fun data(data: String) = data(JsonField.of(data))

                /**
                 * Sets [Builder.data] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.data] with a well-typed [String] value instead.
                 * This method is primarily for setting the field to an undocumented or not yet
                 * supported value.
                 */
                fun data(data: JsonField<String>) = apply { this.data = data }

                fun mimeType(mimeType: MimeType) = mimeType(JsonField.of(mimeType))

                /**
                 * Sets [Builder.mimeType] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.mimeType] with a well-typed [MimeType] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun mimeType(mimeType: JsonField<MimeType>) = apply { this.mimeType = mimeType }

                /** The resolution of the media. */
                fun resolution(resolution: Resolution) = resolution(JsonField.of(resolution))

                /**
                 * Sets [Builder.resolution] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.resolution] with a well-typed [Resolution] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun resolution(resolution: JsonField<Resolution>) = apply {
                    this.resolution = resolution
                }

                fun uri(uri: String) = uri(JsonField.of(uri))

                /**
                 * Sets [Builder.uri] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.uri] with a well-typed [String] value instead.
                 * This method is primarily for setting the field to an undocumented or not yet
                 * supported value.
                 */
                fun uri(uri: JsonField<String>) = apply { this.uri = uri }

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
                 * Returns an immutable instance of [Video].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 */
                fun build(): Video =
                    Video(
                        type,
                        data,
                        mimeType,
                        resolution,
                        uri,
                        additionalProperties.toMutableMap(),
                    )
            }

            private var validated: Boolean = false

            fun validate(): Video = apply {
                if (validated) {
                    return@apply
                }

                _type().let {
                    if (it != JsonValue.from("video")) {
                        throw GeminiNextGenApiInvalidDataException(
                            "'type' is invalid, received $it"
                        )
                    }
                }
                data()
                mimeType().ifPresent { it.validate() }
                resolution().ifPresent { it.validate() }
                uri()
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
                type.let { if (it == JsonValue.from("video")) 1 else 0 } +
                    (if (data.asKnown().isPresent) 1 else 0) +
                    (mimeType.asKnown().getOrNull()?.validity() ?: 0) +
                    (resolution.asKnown().getOrNull()?.validity() ?: 0) +
                    (if (uri.asKnown().isPresent) 1 else 0)

            class MimeType @JsonCreator private constructor(private val value: JsonField<String>) :
                Enum {

                /**
                 * Returns this class instance's raw value.
                 *
                 * This is usually only useful if this instance was deserialized from data that
                 * doesn't match any known member, and you want to know that value. For example, if
                 * the SDK is on an older version than the API, then the API may respond with new
                 * members that the SDK is unaware of.
                 */
                @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

                companion object {

                    @JvmField val VIDEO_MP4 = of("video/mp4")

                    @JvmField val VIDEO_MPEG = of("video/mpeg")

                    @JvmField val VIDEO_MPG = of("video/mpg")

                    @JvmField val VIDEO_MOV = of("video/mov")

                    @JvmField val VIDEO_AVI = of("video/avi")

                    @JvmField val VIDEO_X_FLV = of("video/x-flv")

                    @JvmField val VIDEO_WEBM = of("video/webm")

                    @JvmField val VIDEO_WMV = of("video/wmv")

                    @JvmField val VIDEO_3GPP = of("video/3gpp")

                    @JvmStatic fun of(value: String) = MimeType(JsonField.of(value))
                }

                /** An enum containing [MimeType]'s known values. */
                enum class Known {
                    VIDEO_MP4,
                    VIDEO_MPEG,
                    VIDEO_MPG,
                    VIDEO_MOV,
                    VIDEO_AVI,
                    VIDEO_X_FLV,
                    VIDEO_WEBM,
                    VIDEO_WMV,
                    VIDEO_3GPP,
                }

                /**
                 * An enum containing [MimeType]'s known values, as well as an [_UNKNOWN] member.
                 *
                 * An instance of [MimeType] can contain an unknown value in a couple of cases:
                 * - It was deserialized from data that doesn't match any known member. For example,
                 *   if the SDK is on an older version than the API, then the API may respond with
                 *   new members that the SDK is unaware of.
                 * - It was constructed with an arbitrary value using the [of] method.
                 */
                enum class Value {
                    VIDEO_MP4,
                    VIDEO_MPEG,
                    VIDEO_MPG,
                    VIDEO_MOV,
                    VIDEO_AVI,
                    VIDEO_X_FLV,
                    VIDEO_WEBM,
                    VIDEO_WMV,
                    VIDEO_3GPP,
                    /**
                     * An enum member indicating that [MimeType] was instantiated with an unknown
                     * value.
                     */
                    _UNKNOWN,
                }

                /**
                 * Returns an enum member corresponding to this class instance's value, or
                 * [Value._UNKNOWN] if the class was instantiated with an unknown value.
                 *
                 * Use the [known] method instead if you're certain the value is always known or if
                 * you want to throw for the unknown case.
                 */
                fun value(): Value =
                    when (this) {
                        VIDEO_MP4 -> Value.VIDEO_MP4
                        VIDEO_MPEG -> Value.VIDEO_MPEG
                        VIDEO_MPG -> Value.VIDEO_MPG
                        VIDEO_MOV -> Value.VIDEO_MOV
                        VIDEO_AVI -> Value.VIDEO_AVI
                        VIDEO_X_FLV -> Value.VIDEO_X_FLV
                        VIDEO_WEBM -> Value.VIDEO_WEBM
                        VIDEO_WMV -> Value.VIDEO_WMV
                        VIDEO_3GPP -> Value.VIDEO_3GPP
                        else -> Value._UNKNOWN
                    }

                /**
                 * Returns an enum member corresponding to this class instance's value.
                 *
                 * Use the [value] method instead if you're uncertain the value is always known and
                 * don't want to throw for the unknown case.
                 *
                 * @throws GeminiNextGenApiInvalidDataException if this class instance's value is a
                 *   not a known member.
                 */
                fun known(): Known =
                    when (this) {
                        VIDEO_MP4 -> Known.VIDEO_MP4
                        VIDEO_MPEG -> Known.VIDEO_MPEG
                        VIDEO_MPG -> Known.VIDEO_MPG
                        VIDEO_MOV -> Known.VIDEO_MOV
                        VIDEO_AVI -> Known.VIDEO_AVI
                        VIDEO_X_FLV -> Known.VIDEO_X_FLV
                        VIDEO_WEBM -> Known.VIDEO_WEBM
                        VIDEO_WMV -> Known.VIDEO_WMV
                        VIDEO_3GPP -> Known.VIDEO_3GPP
                        else ->
                            throw GeminiNextGenApiInvalidDataException("Unknown MimeType: $value")
                    }

                /**
                 * Returns this class instance's primitive wire representation.
                 *
                 * This differs from the [toString] method because that method is primarily for
                 * debugging and generally doesn't throw.
                 *
                 * @throws GeminiNextGenApiInvalidDataException if this class instance's value does
                 *   not have the expected primitive type.
                 */
                fun asString(): String =
                    _value().asString().orElseThrow {
                        GeminiNextGenApiInvalidDataException("Value is not a String")
                    }

                private var validated: Boolean = false

                fun validate(): MimeType = apply {
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

                    return other is MimeType && value == other.value
                }

                override fun hashCode() = value.hashCode()

                override fun toString() = value.toString()
            }

            /** The resolution of the media. */
            class Resolution
            @JsonCreator
            private constructor(private val value: JsonField<String>) : Enum {

                /**
                 * Returns this class instance's raw value.
                 *
                 * This is usually only useful if this instance was deserialized from data that
                 * doesn't match any known member, and you want to know that value. For example, if
                 * the SDK is on an older version than the API, then the API may respond with new
                 * members that the SDK is unaware of.
                 */
                @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

                companion object {

                    @JvmField val LOW = of("low")

                    @JvmField val MEDIUM = of("medium")

                    @JvmField val HIGH = of("high")

                    @JvmField val ULTRA_HIGH = of("ultra_high")

                    @JvmStatic fun of(value: String) = Resolution(JsonField.of(value))
                }

                /** An enum containing [Resolution]'s known values. */
                enum class Known {
                    LOW,
                    MEDIUM,
                    HIGH,
                    ULTRA_HIGH,
                }

                /**
                 * An enum containing [Resolution]'s known values, as well as an [_UNKNOWN] member.
                 *
                 * An instance of [Resolution] can contain an unknown value in a couple of cases:
                 * - It was deserialized from data that doesn't match any known member. For example,
                 *   if the SDK is on an older version than the API, then the API may respond with
                 *   new members that the SDK is unaware of.
                 * - It was constructed with an arbitrary value using the [of] method.
                 */
                enum class Value {
                    LOW,
                    MEDIUM,
                    HIGH,
                    ULTRA_HIGH,
                    /**
                     * An enum member indicating that [Resolution] was instantiated with an unknown
                     * value.
                     */
                    _UNKNOWN,
                }

                /**
                 * Returns an enum member corresponding to this class instance's value, or
                 * [Value._UNKNOWN] if the class was instantiated with an unknown value.
                 *
                 * Use the [known] method instead if you're certain the value is always known or if
                 * you want to throw for the unknown case.
                 */
                fun value(): Value =
                    when (this) {
                        LOW -> Value.LOW
                        MEDIUM -> Value.MEDIUM
                        HIGH -> Value.HIGH
                        ULTRA_HIGH -> Value.ULTRA_HIGH
                        else -> Value._UNKNOWN
                    }

                /**
                 * Returns an enum member corresponding to this class instance's value.
                 *
                 * Use the [value] method instead if you're uncertain the value is always known and
                 * don't want to throw for the unknown case.
                 *
                 * @throws GeminiNextGenApiInvalidDataException if this class instance's value is a
                 *   not a known member.
                 */
                fun known(): Known =
                    when (this) {
                        LOW -> Known.LOW
                        MEDIUM -> Known.MEDIUM
                        HIGH -> Known.HIGH
                        ULTRA_HIGH -> Known.ULTRA_HIGH
                        else ->
                            throw GeminiNextGenApiInvalidDataException("Unknown Resolution: $value")
                    }

                /**
                 * Returns this class instance's primitive wire representation.
                 *
                 * This differs from the [toString] method because that method is primarily for
                 * debugging and generally doesn't throw.
                 *
                 * @throws GeminiNextGenApiInvalidDataException if this class instance's value does
                 *   not have the expected primitive type.
                 */
                fun asString(): String =
                    _value().asString().orElseThrow {
                        GeminiNextGenApiInvalidDataException("Value is not a String")
                    }

                private var validated: Boolean = false

                fun validate(): Resolution = apply {
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

                    return other is Resolution && value == other.value
                }

                override fun hashCode() = value.hashCode()

                override fun toString() = value.toString()
            }

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is Video &&
                    type == other.type &&
                    data == other.data &&
                    mimeType == other.mimeType &&
                    resolution == other.resolution &&
                    uri == other.uri &&
                    additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(type, data, mimeType, resolution, uri, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "Video{type=$type, data=$data, mimeType=$mimeType, resolution=$resolution, uri=$uri, additionalProperties=$additionalProperties}"
        }

        class ThoughtSummary
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val type: JsonValue,
            private val content: JsonField<Content>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
                @JsonProperty("content")
                @ExcludeMissing
                content: JsonField<Content> = JsonMissing.of(),
            ) : this(type, content, mutableMapOf())

            /**
             * Expected to always return the following:
             * ```java
             * JsonValue.from("thought_summary")
             * ```
             *
             * However, this method can be useful for debugging and logging (e.g. if the server
             * responded with an unexpected value).
             */
            @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

            /**
             * A new summary item to be added to the thought.
             *
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   (e.g. if the server responded with an unexpected value).
             */
            fun content(): Optional<Content> = content.getOptional("content")

            /**
             * Returns the raw JSON value of [content].
             *
             * Unlike [content], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("content") @ExcludeMissing fun _content(): JsonField<Content> = content

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

                /** Returns a mutable builder for constructing an instance of [ThoughtSummary]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [ThoughtSummary]. */
            class Builder internal constructor() {

                private var type: JsonValue = JsonValue.from("thought_summary")
                private var content: JsonField<Content> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(thoughtSummary: ThoughtSummary) = apply {
                    type = thoughtSummary.type
                    content = thoughtSummary.content
                    additionalProperties = thoughtSummary.additionalProperties.toMutableMap()
                }

                /**
                 * Sets the field to an arbitrary JSON value.
                 *
                 * It is usually unnecessary to call this method because the field defaults to the
                 * following:
                 * ```java
                 * JsonValue.from("thought_summary")
                 * ```
                 *
                 * This method is primarily for setting the field to an undocumented or not yet
                 * supported value.
                 */
                fun type(type: JsonValue) = apply { this.type = type }

                /** A new summary item to be added to the thought. */
                fun content(content: Content) = content(JsonField.of(content))

                /**
                 * Sets [Builder.content] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.content] with a well-typed [Content] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun content(content: JsonField<Content>) = apply { this.content = content }

                /** Alias for calling [content] with `Content.ofText(text)`. */
                fun content(text: TextContent) = content(Content.ofText(text))

                /**
                 * Alias for calling [content] with the following:
                 * ```java
                 * TextContent.builder()
                 *     .text(text)
                 *     .build()
                 * ```
                 */
                fun textContent(text: String) = content(TextContent.builder().text(text).build())

                /** Alias for calling [content] with `Content.ofImage(image)`. */
                fun content(image: ImageContent) = content(Content.ofImage(image))

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
                 * Returns an immutable instance of [ThoughtSummary].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 */
                fun build(): ThoughtSummary =
                    ThoughtSummary(type, content, additionalProperties.toMutableMap())
            }

            private var validated: Boolean = false

            fun validate(): ThoughtSummary = apply {
                if (validated) {
                    return@apply
                }

                _type().let {
                    if (it != JsonValue.from("thought_summary")) {
                        throw GeminiNextGenApiInvalidDataException(
                            "'type' is invalid, received $it"
                        )
                    }
                }
                content().ifPresent { it.validate() }
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
                type.let { if (it == JsonValue.from("thought_summary")) 1 else 0 } +
                    (content.asKnown().getOrNull()?.validity() ?: 0)

            /** A text content block. */
            @JsonDeserialize(using = Content.Deserializer::class)
            @JsonSerialize(using = Content.Serializer::class)
            class Content
            private constructor(
                private val text: TextContent? = null,
                private val image: ImageContent? = null,
                private val _json: JsonValue? = null,
            ) {

                /** A text content block. */
                fun text(): Optional<TextContent> = Optional.ofNullable(text)

                /** An image content block. */
                fun image(): Optional<ImageContent> = Optional.ofNullable(image)

                fun isText(): Boolean = text != null

                fun isImage(): Boolean = image != null

                /** A text content block. */
                fun asText(): TextContent = text.getOrThrow("text")

                /** An image content block. */
                fun asImage(): ImageContent = image.getOrThrow("image")

                fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

                fun <T> accept(visitor: Visitor<T>): T =
                    when {
                        text != null -> visitor.visitText(text)
                        image != null -> visitor.visitImage(image)
                        else -> visitor.unknown(_json)
                    }

                private var validated: Boolean = false

                fun validate(): Content = apply {
                    if (validated) {
                        return@apply
                    }

                    accept(
                        object : Visitor<Unit> {
                            override fun visitText(text: TextContent) {
                                text.validate()
                            }

                            override fun visitImage(image: ImageContent) {
                                image.validate()
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
                            override fun visitText(text: TextContent) = text.validity()

                            override fun visitImage(image: ImageContent) = image.validity()

                            override fun unknown(json: JsonValue?) = 0
                        }
                    )

                override fun equals(other: Any?): Boolean {
                    if (this === other) {
                        return true
                    }

                    return other is Content && text == other.text && image == other.image
                }

                override fun hashCode(): Int = Objects.hash(text, image)

                override fun toString(): String =
                    when {
                        text != null -> "Content{text=$text}"
                        image != null -> "Content{image=$image}"
                        _json != null -> "Content{_unknown=$_json}"
                        else -> throw IllegalStateException("Invalid Content")
                    }

                companion object {

                    /** A text content block. */
                    @JvmStatic fun ofText(text: TextContent) = Content(text = text)

                    /** An image content block. */
                    @JvmStatic fun ofImage(image: ImageContent) = Content(image = image)
                }

                /**
                 * An interface that defines how to map each variant of [Content] to a value of type
                 * [T].
                 */
                interface Visitor<out T> {

                    /** A text content block. */
                    fun visitText(text: TextContent): T

                    /** An image content block. */
                    fun visitImage(image: ImageContent): T

                    /**
                     * Maps an unknown variant of [Content] to a value of type [T].
                     *
                     * An instance of [Content] can contain an unknown variant if it was
                     * deserialized from data that doesn't match any known variant. For example, if
                     * the SDK is on an older version than the API, then the API may respond with
                     * new variants that the SDK is unaware of.
                     *
                     * @throws GeminiNextGenApiInvalidDataException in the default implementation.
                     */
                    fun unknown(json: JsonValue?): T {
                        throw GeminiNextGenApiInvalidDataException("Unknown Content: $json")
                    }
                }

                internal class Deserializer : BaseDeserializer<Content>(Content::class) {

                    override fun ObjectCodec.deserialize(node: JsonNode): Content {
                        val json = JsonValue.fromJsonNode(node)
                        val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                        when (type) {
                            "text" -> {
                                return tryDeserialize(node, jacksonTypeRef<TextContent>())?.let {
                                    Content(text = it, _json = json)
                                } ?: Content(_json = json)
                            }
                            "image" -> {
                                return tryDeserialize(node, jacksonTypeRef<ImageContent>())?.let {
                                    Content(image = it, _json = json)
                                } ?: Content(_json = json)
                            }
                        }

                        return Content(_json = json)
                    }
                }

                internal class Serializer : BaseSerializer<Content>(Content::class) {

                    override fun serialize(
                        value: Content,
                        generator: JsonGenerator,
                        provider: SerializerProvider,
                    ) {
                        when {
                            value.text != null -> generator.writeObject(value.text)
                            value.image != null -> generator.writeObject(value.image)
                            value._json != null -> generator.writeObject(value._json)
                            else -> throw IllegalStateException("Invalid Content")
                        }
                    }
                }
            }

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is ThoughtSummary &&
                    type == other.type &&
                    content == other.content &&
                    additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy { Objects.hash(type, content, additionalProperties) }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "ThoughtSummary{type=$type, content=$content, additionalProperties=$additionalProperties}"
        }

        class ThoughtSignature
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val type: JsonValue,
            private val signature: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
                @JsonProperty("signature")
                @ExcludeMissing
                signature: JsonField<String> = JsonMissing.of(),
            ) : this(type, signature, mutableMapOf())

            /**
             * Expected to always return the following:
             * ```java
             * JsonValue.from("thought_signature")
             * ```
             *
             * However, this method can be useful for debugging and logging (e.g. if the server
             * responded with an unexpected value).
             */
            @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

            /**
             * Signature to match the backend source to be part of the generation.
             *
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   (e.g. if the server responded with an unexpected value).
             */
            fun signature(): Optional<String> = signature.getOptional("signature")

            /**
             * Returns the raw JSON value of [signature].
             *
             * Unlike [signature], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("signature")
            @ExcludeMissing
            fun _signature(): JsonField<String> = signature

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

                /** Returns a mutable builder for constructing an instance of [ThoughtSignature]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [ThoughtSignature]. */
            class Builder internal constructor() {

                private var type: JsonValue = JsonValue.from("thought_signature")
                private var signature: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(thoughtSignature: ThoughtSignature) = apply {
                    type = thoughtSignature.type
                    signature = thoughtSignature.signature
                    additionalProperties = thoughtSignature.additionalProperties.toMutableMap()
                }

                /**
                 * Sets the field to an arbitrary JSON value.
                 *
                 * It is usually unnecessary to call this method because the field defaults to the
                 * following:
                 * ```java
                 * JsonValue.from("thought_signature")
                 * ```
                 *
                 * This method is primarily for setting the field to an undocumented or not yet
                 * supported value.
                 */
                fun type(type: JsonValue) = apply { this.type = type }

                /** Signature to match the backend source to be part of the generation. */
                fun signature(signature: String) = signature(JsonField.of(signature))

                /**
                 * Sets [Builder.signature] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.signature] with a well-typed [String] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun signature(signature: JsonField<String>) = apply { this.signature = signature }

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
                 * Returns an immutable instance of [ThoughtSignature].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 */
                fun build(): ThoughtSignature =
                    ThoughtSignature(type, signature, additionalProperties.toMutableMap())
            }

            private var validated: Boolean = false

            fun validate(): ThoughtSignature = apply {
                if (validated) {
                    return@apply
                }

                _type().let {
                    if (it != JsonValue.from("thought_signature")) {
                        throw GeminiNextGenApiInvalidDataException(
                            "'type' is invalid, received $it"
                        )
                    }
                }
                signature()
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
                type.let { if (it == JsonValue.from("thought_signature")) 1 else 0 } +
                    (if (signature.asKnown().isPresent) 1 else 0)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is ThoughtSignature &&
                    type == other.type &&
                    signature == other.signature &&
                    additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(type, signature, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "ThoughtSignature{type=$type, signature=$signature, additionalProperties=$additionalProperties}"
        }

        class FunctionCall
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val id: JsonField<String>,
            private val arguments: JsonField<Arguments>,
            private val name: JsonField<String>,
            private val type: JsonValue,
            private val signature: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
                @JsonProperty("arguments")
                @ExcludeMissing
                arguments: JsonField<Arguments> = JsonMissing.of(),
                @JsonProperty("name") @ExcludeMissing name: JsonField<String> = JsonMissing.of(),
                @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
                @JsonProperty("signature")
                @ExcludeMissing
                signature: JsonField<String> = JsonMissing.of(),
            ) : this(id, arguments, name, type, signature, mutableMapOf())

            /**
             * Required. A unique ID for this specific tool call.
             *
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   or is unexpectedly missing or null (e.g. if the server responded with an unexpected
             *   value).
             */
            fun id(): String = id.getRequired("id")

            /**
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   or is unexpectedly missing or null (e.g. if the server responded with an unexpected
             *   value).
             */
            fun arguments(): Arguments = arguments.getRequired("arguments")

            /**
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   or is unexpectedly missing or null (e.g. if the server responded with an unexpected
             *   value).
             */
            fun name(): String = name.getRequired("name")

            /**
             * Expected to always return the following:
             * ```java
             * JsonValue.from("function_call")
             * ```
             *
             * However, this method can be useful for debugging and logging (e.g. if the server
             * responded with an unexpected value).
             */
            @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

            /**
             * A signature hash for backend validation.
             *
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   (e.g. if the server responded with an unexpected value).
             */
            fun signature(): Optional<String> = signature.getOptional("signature")

            /**
             * Returns the raw JSON value of [id].
             *
             * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

            /**
             * Returns the raw JSON value of [arguments].
             *
             * Unlike [arguments], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("arguments")
            @ExcludeMissing
            fun _arguments(): JsonField<Arguments> = arguments

            /**
             * Returns the raw JSON value of [name].
             *
             * Unlike [name], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("name") @ExcludeMissing fun _name(): JsonField<String> = name

            /**
             * Returns the raw JSON value of [signature].
             *
             * Unlike [signature], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("signature")
            @ExcludeMissing
            fun _signature(): JsonField<String> = signature

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
                 * Returns a mutable builder for constructing an instance of [FunctionCall].
                 *
                 * The following fields are required:
                 * ```java
                 * .id()
                 * .arguments()
                 * .name()
                 * ```
                 */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [FunctionCall]. */
            class Builder internal constructor() {

                private var id: JsonField<String>? = null
                private var arguments: JsonField<Arguments>? = null
                private var name: JsonField<String>? = null
                private var type: JsonValue = JsonValue.from("function_call")
                private var signature: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(functionCall: FunctionCall) = apply {
                    id = functionCall.id
                    arguments = functionCall.arguments
                    name = functionCall.name
                    type = functionCall.type
                    signature = functionCall.signature
                    additionalProperties = functionCall.additionalProperties.toMutableMap()
                }

                /** Required. A unique ID for this specific tool call. */
                fun id(id: String) = id(JsonField.of(id))

                /**
                 * Sets [Builder.id] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.id] with a well-typed [String] value instead.
                 * This method is primarily for setting the field to an undocumented or not yet
                 * supported value.
                 */
                fun id(id: JsonField<String>) = apply { this.id = id }

                fun arguments(arguments: Arguments) = arguments(JsonField.of(arguments))

                /**
                 * Sets [Builder.arguments] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.arguments] with a well-typed [Arguments] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun arguments(arguments: JsonField<Arguments>) = apply {
                    this.arguments = arguments
                }

                fun name(name: String) = name(JsonField.of(name))

                /**
                 * Sets [Builder.name] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.name] with a well-typed [String] value instead.
                 * This method is primarily for setting the field to an undocumented or not yet
                 * supported value.
                 */
                fun name(name: JsonField<String>) = apply { this.name = name }

                /**
                 * Sets the field to an arbitrary JSON value.
                 *
                 * It is usually unnecessary to call this method because the field defaults to the
                 * following:
                 * ```java
                 * JsonValue.from("function_call")
                 * ```
                 *
                 * This method is primarily for setting the field to an undocumented or not yet
                 * supported value.
                 */
                fun type(type: JsonValue) = apply { this.type = type }

                /** A signature hash for backend validation. */
                fun signature(signature: String) = signature(JsonField.of(signature))

                /**
                 * Sets [Builder.signature] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.signature] with a well-typed [String] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun signature(signature: JsonField<String>) = apply { this.signature = signature }

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
                 * Returns an immutable instance of [FunctionCall].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 *
                 * The following fields are required:
                 * ```java
                 * .id()
                 * .arguments()
                 * .name()
                 * ```
                 *
                 * @throws IllegalStateException if any required field is unset.
                 */
                fun build(): FunctionCall =
                    FunctionCall(
                        checkRequired("id", id),
                        checkRequired("arguments", arguments),
                        checkRequired("name", name),
                        type,
                        signature,
                        additionalProperties.toMutableMap(),
                    )
            }

            private var validated: Boolean = false

            fun validate(): FunctionCall = apply {
                if (validated) {
                    return@apply
                }

                id()
                arguments().validate()
                name()
                _type().let {
                    if (it != JsonValue.from("function_call")) {
                        throw GeminiNextGenApiInvalidDataException(
                            "'type' is invalid, received $it"
                        )
                    }
                }
                signature()
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
                (if (id.asKnown().isPresent) 1 else 0) +
                    (arguments.asKnown().getOrNull()?.validity() ?: 0) +
                    (if (name.asKnown().isPresent) 1 else 0) +
                    type.let { if (it == JsonValue.from("function_call")) 1 else 0 } +
                    (if (signature.asKnown().isPresent) 1 else 0)

            class Arguments
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

                    /** Returns a mutable builder for constructing an instance of [Arguments]. */
                    @JvmStatic fun builder() = Builder()
                }

                /** A builder for [Arguments]. */
                class Builder internal constructor() {

                    private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                    @JvmSynthetic
                    internal fun from(arguments: Arguments) = apply {
                        additionalProperties = arguments.additionalProperties.toMutableMap()
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
                     * Returns an immutable instance of [Arguments].
                     *
                     * Further updates to this [Builder] will not mutate the returned instance.
                     */
                    fun build(): Arguments = Arguments(additionalProperties.toImmutable())
                }

                private var validated: Boolean = false

                fun validate(): Arguments = apply {
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
                    additionalProperties.count { (_, value) ->
                        !value.isNull() && !value.isMissing()
                    }

                override fun equals(other: Any?): Boolean {
                    if (this === other) {
                        return true
                    }

                    return other is Arguments && additionalProperties == other.additionalProperties
                }

                private val hashCode: Int by lazy { Objects.hash(additionalProperties) }

                override fun hashCode(): Int = hashCode

                override fun toString() = "Arguments{additionalProperties=$additionalProperties}"
            }

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is FunctionCall &&
                    id == other.id &&
                    arguments == other.arguments &&
                    name == other.name &&
                    type == other.type &&
                    signature == other.signature &&
                    additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(id, arguments, name, type, signature, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "FunctionCall{id=$id, arguments=$arguments, name=$name, type=$type, signature=$signature, additionalProperties=$additionalProperties}"
        }

        class CodeExecutionCall
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val id: JsonField<String>,
            private val arguments: JsonField<CodeExecutionCallArguments>,
            private val type: JsonValue,
            private val signature: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
                @JsonProperty("arguments")
                @ExcludeMissing
                arguments: JsonField<CodeExecutionCallArguments> = JsonMissing.of(),
                @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
                @JsonProperty("signature")
                @ExcludeMissing
                signature: JsonField<String> = JsonMissing.of(),
            ) : this(id, arguments, type, signature, mutableMapOf())

            /**
             * Required. A unique ID for this specific tool call.
             *
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   or is unexpectedly missing or null (e.g. if the server responded with an unexpected
             *   value).
             */
            fun id(): String = id.getRequired("id")

            /**
             * The arguments to pass to the code execution.
             *
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   or is unexpectedly missing or null (e.g. if the server responded with an unexpected
             *   value).
             */
            fun arguments(): CodeExecutionCallArguments = arguments.getRequired("arguments")

            /**
             * Expected to always return the following:
             * ```java
             * JsonValue.from("code_execution_call")
             * ```
             *
             * However, this method can be useful for debugging and logging (e.g. if the server
             * responded with an unexpected value).
             */
            @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

            /**
             * A signature hash for backend validation.
             *
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   (e.g. if the server responded with an unexpected value).
             */
            fun signature(): Optional<String> = signature.getOptional("signature")

            /**
             * Returns the raw JSON value of [id].
             *
             * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

            /**
             * Returns the raw JSON value of [arguments].
             *
             * Unlike [arguments], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("arguments")
            @ExcludeMissing
            fun _arguments(): JsonField<CodeExecutionCallArguments> = arguments

            /**
             * Returns the raw JSON value of [signature].
             *
             * Unlike [signature], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("signature")
            @ExcludeMissing
            fun _signature(): JsonField<String> = signature

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
                 * Returns a mutable builder for constructing an instance of [CodeExecutionCall].
                 *
                 * The following fields are required:
                 * ```java
                 * .id()
                 * .arguments()
                 * ```
                 */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [CodeExecutionCall]. */
            class Builder internal constructor() {

                private var id: JsonField<String>? = null
                private var arguments: JsonField<CodeExecutionCallArguments>? = null
                private var type: JsonValue = JsonValue.from("code_execution_call")
                private var signature: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(codeExecutionCall: CodeExecutionCall) = apply {
                    id = codeExecutionCall.id
                    arguments = codeExecutionCall.arguments
                    type = codeExecutionCall.type
                    signature = codeExecutionCall.signature
                    additionalProperties = codeExecutionCall.additionalProperties.toMutableMap()
                }

                /** Required. A unique ID for this specific tool call. */
                fun id(id: String) = id(JsonField.of(id))

                /**
                 * Sets [Builder.id] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.id] with a well-typed [String] value instead.
                 * This method is primarily for setting the field to an undocumented or not yet
                 * supported value.
                 */
                fun id(id: JsonField<String>) = apply { this.id = id }

                /** The arguments to pass to the code execution. */
                fun arguments(arguments: CodeExecutionCallArguments) =
                    arguments(JsonField.of(arguments))

                /**
                 * Sets [Builder.arguments] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.arguments] with a well-typed
                 * [CodeExecutionCallArguments] value instead. This method is primarily for setting
                 * the field to an undocumented or not yet supported value.
                 */
                fun arguments(arguments: JsonField<CodeExecutionCallArguments>) = apply {
                    this.arguments = arguments
                }

                /**
                 * Sets the field to an arbitrary JSON value.
                 *
                 * It is usually unnecessary to call this method because the field defaults to the
                 * following:
                 * ```java
                 * JsonValue.from("code_execution_call")
                 * ```
                 *
                 * This method is primarily for setting the field to an undocumented or not yet
                 * supported value.
                 */
                fun type(type: JsonValue) = apply { this.type = type }

                /** A signature hash for backend validation. */
                fun signature(signature: String) = signature(JsonField.of(signature))

                /**
                 * Sets [Builder.signature] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.signature] with a well-typed [String] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun signature(signature: JsonField<String>) = apply { this.signature = signature }

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
                 * Returns an immutable instance of [CodeExecutionCall].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 *
                 * The following fields are required:
                 * ```java
                 * .id()
                 * .arguments()
                 * ```
                 *
                 * @throws IllegalStateException if any required field is unset.
                 */
                fun build(): CodeExecutionCall =
                    CodeExecutionCall(
                        checkRequired("id", id),
                        checkRequired("arguments", arguments),
                        type,
                        signature,
                        additionalProperties.toMutableMap(),
                    )
            }

            private var validated: Boolean = false

            fun validate(): CodeExecutionCall = apply {
                if (validated) {
                    return@apply
                }

                id()
                arguments().validate()
                _type().let {
                    if (it != JsonValue.from("code_execution_call")) {
                        throw GeminiNextGenApiInvalidDataException(
                            "'type' is invalid, received $it"
                        )
                    }
                }
                signature()
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
                (if (id.asKnown().isPresent) 1 else 0) +
                    (arguments.asKnown().getOrNull()?.validity() ?: 0) +
                    type.let { if (it == JsonValue.from("code_execution_call")) 1 else 0 } +
                    (if (signature.asKnown().isPresent) 1 else 0)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is CodeExecutionCall &&
                    id == other.id &&
                    arguments == other.arguments &&
                    type == other.type &&
                    signature == other.signature &&
                    additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(id, arguments, type, signature, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "CodeExecutionCall{id=$id, arguments=$arguments, type=$type, signature=$signature, additionalProperties=$additionalProperties}"
        }

        class UrlContextCall
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val id: JsonField<String>,
            private val arguments: JsonField<UrlContextCallArguments>,
            private val type: JsonValue,
            private val signature: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
                @JsonProperty("arguments")
                @ExcludeMissing
                arguments: JsonField<UrlContextCallArguments> = JsonMissing.of(),
                @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
                @JsonProperty("signature")
                @ExcludeMissing
                signature: JsonField<String> = JsonMissing.of(),
            ) : this(id, arguments, type, signature, mutableMapOf())

            /**
             * Required. A unique ID for this specific tool call.
             *
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   or is unexpectedly missing or null (e.g. if the server responded with an unexpected
             *   value).
             */
            fun id(): String = id.getRequired("id")

            /**
             * The arguments to pass to the URL context.
             *
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   or is unexpectedly missing or null (e.g. if the server responded with an unexpected
             *   value).
             */
            fun arguments(): UrlContextCallArguments = arguments.getRequired("arguments")

            /**
             * Expected to always return the following:
             * ```java
             * JsonValue.from("url_context_call")
             * ```
             *
             * However, this method can be useful for debugging and logging (e.g. if the server
             * responded with an unexpected value).
             */
            @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

            /**
             * A signature hash for backend validation.
             *
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   (e.g. if the server responded with an unexpected value).
             */
            fun signature(): Optional<String> = signature.getOptional("signature")

            /**
             * Returns the raw JSON value of [id].
             *
             * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

            /**
             * Returns the raw JSON value of [arguments].
             *
             * Unlike [arguments], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("arguments")
            @ExcludeMissing
            fun _arguments(): JsonField<UrlContextCallArguments> = arguments

            /**
             * Returns the raw JSON value of [signature].
             *
             * Unlike [signature], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("signature")
            @ExcludeMissing
            fun _signature(): JsonField<String> = signature

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
                 * Returns a mutable builder for constructing an instance of [UrlContextCall].
                 *
                 * The following fields are required:
                 * ```java
                 * .id()
                 * .arguments()
                 * ```
                 */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [UrlContextCall]. */
            class Builder internal constructor() {

                private var id: JsonField<String>? = null
                private var arguments: JsonField<UrlContextCallArguments>? = null
                private var type: JsonValue = JsonValue.from("url_context_call")
                private var signature: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(urlContextCall: UrlContextCall) = apply {
                    id = urlContextCall.id
                    arguments = urlContextCall.arguments
                    type = urlContextCall.type
                    signature = urlContextCall.signature
                    additionalProperties = urlContextCall.additionalProperties.toMutableMap()
                }

                /** Required. A unique ID for this specific tool call. */
                fun id(id: String) = id(JsonField.of(id))

                /**
                 * Sets [Builder.id] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.id] with a well-typed [String] value instead.
                 * This method is primarily for setting the field to an undocumented or not yet
                 * supported value.
                 */
                fun id(id: JsonField<String>) = apply { this.id = id }

                /** The arguments to pass to the URL context. */
                fun arguments(arguments: UrlContextCallArguments) =
                    arguments(JsonField.of(arguments))

                /**
                 * Sets [Builder.arguments] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.arguments] with a well-typed
                 * [UrlContextCallArguments] value instead. This method is primarily for setting the
                 * field to an undocumented or not yet supported value.
                 */
                fun arguments(arguments: JsonField<UrlContextCallArguments>) = apply {
                    this.arguments = arguments
                }

                /**
                 * Sets the field to an arbitrary JSON value.
                 *
                 * It is usually unnecessary to call this method because the field defaults to the
                 * following:
                 * ```java
                 * JsonValue.from("url_context_call")
                 * ```
                 *
                 * This method is primarily for setting the field to an undocumented or not yet
                 * supported value.
                 */
                fun type(type: JsonValue) = apply { this.type = type }

                /** A signature hash for backend validation. */
                fun signature(signature: String) = signature(JsonField.of(signature))

                /**
                 * Sets [Builder.signature] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.signature] with a well-typed [String] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun signature(signature: JsonField<String>) = apply { this.signature = signature }

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
                 * Returns an immutable instance of [UrlContextCall].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 *
                 * The following fields are required:
                 * ```java
                 * .id()
                 * .arguments()
                 * ```
                 *
                 * @throws IllegalStateException if any required field is unset.
                 */
                fun build(): UrlContextCall =
                    UrlContextCall(
                        checkRequired("id", id),
                        checkRequired("arguments", arguments),
                        type,
                        signature,
                        additionalProperties.toMutableMap(),
                    )
            }

            private var validated: Boolean = false

            fun validate(): UrlContextCall = apply {
                if (validated) {
                    return@apply
                }

                id()
                arguments().validate()
                _type().let {
                    if (it != JsonValue.from("url_context_call")) {
                        throw GeminiNextGenApiInvalidDataException(
                            "'type' is invalid, received $it"
                        )
                    }
                }
                signature()
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
                (if (id.asKnown().isPresent) 1 else 0) +
                    (arguments.asKnown().getOrNull()?.validity() ?: 0) +
                    type.let { if (it == JsonValue.from("url_context_call")) 1 else 0 } +
                    (if (signature.asKnown().isPresent) 1 else 0)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is UrlContextCall &&
                    id == other.id &&
                    arguments == other.arguments &&
                    type == other.type &&
                    signature == other.signature &&
                    additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(id, arguments, type, signature, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "UrlContextCall{id=$id, arguments=$arguments, type=$type, signature=$signature, additionalProperties=$additionalProperties}"
        }

        class GoogleSearchCall
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val id: JsonField<String>,
            private val arguments: JsonField<GoogleSearchCallArguments>,
            private val type: JsonValue,
            private val signature: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
                @JsonProperty("arguments")
                @ExcludeMissing
                arguments: JsonField<GoogleSearchCallArguments> = JsonMissing.of(),
                @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
                @JsonProperty("signature")
                @ExcludeMissing
                signature: JsonField<String> = JsonMissing.of(),
            ) : this(id, arguments, type, signature, mutableMapOf())

            /**
             * Required. A unique ID for this specific tool call.
             *
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   or is unexpectedly missing or null (e.g. if the server responded with an unexpected
             *   value).
             */
            fun id(): String = id.getRequired("id")

            /**
             * The arguments to pass to Google Search.
             *
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   or is unexpectedly missing or null (e.g. if the server responded with an unexpected
             *   value).
             */
            fun arguments(): GoogleSearchCallArguments = arguments.getRequired("arguments")

            /**
             * Expected to always return the following:
             * ```java
             * JsonValue.from("google_search_call")
             * ```
             *
             * However, this method can be useful for debugging and logging (e.g. if the server
             * responded with an unexpected value).
             */
            @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

            /**
             * A signature hash for backend validation.
             *
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   (e.g. if the server responded with an unexpected value).
             */
            fun signature(): Optional<String> = signature.getOptional("signature")

            /**
             * Returns the raw JSON value of [id].
             *
             * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

            /**
             * Returns the raw JSON value of [arguments].
             *
             * Unlike [arguments], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("arguments")
            @ExcludeMissing
            fun _arguments(): JsonField<GoogleSearchCallArguments> = arguments

            /**
             * Returns the raw JSON value of [signature].
             *
             * Unlike [signature], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("signature")
            @ExcludeMissing
            fun _signature(): JsonField<String> = signature

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
                 * Returns a mutable builder for constructing an instance of [GoogleSearchCall].
                 *
                 * The following fields are required:
                 * ```java
                 * .id()
                 * .arguments()
                 * ```
                 */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [GoogleSearchCall]. */
            class Builder internal constructor() {

                private var id: JsonField<String>? = null
                private var arguments: JsonField<GoogleSearchCallArguments>? = null
                private var type: JsonValue = JsonValue.from("google_search_call")
                private var signature: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(googleSearchCall: GoogleSearchCall) = apply {
                    id = googleSearchCall.id
                    arguments = googleSearchCall.arguments
                    type = googleSearchCall.type
                    signature = googleSearchCall.signature
                    additionalProperties = googleSearchCall.additionalProperties.toMutableMap()
                }

                /** Required. A unique ID for this specific tool call. */
                fun id(id: String) = id(JsonField.of(id))

                /**
                 * Sets [Builder.id] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.id] with a well-typed [String] value instead.
                 * This method is primarily for setting the field to an undocumented or not yet
                 * supported value.
                 */
                fun id(id: JsonField<String>) = apply { this.id = id }

                /** The arguments to pass to Google Search. */
                fun arguments(arguments: GoogleSearchCallArguments) =
                    arguments(JsonField.of(arguments))

                /**
                 * Sets [Builder.arguments] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.arguments] with a well-typed
                 * [GoogleSearchCallArguments] value instead. This method is primarily for setting
                 * the field to an undocumented or not yet supported value.
                 */
                fun arguments(arguments: JsonField<GoogleSearchCallArguments>) = apply {
                    this.arguments = arguments
                }

                /**
                 * Sets the field to an arbitrary JSON value.
                 *
                 * It is usually unnecessary to call this method because the field defaults to the
                 * following:
                 * ```java
                 * JsonValue.from("google_search_call")
                 * ```
                 *
                 * This method is primarily for setting the field to an undocumented or not yet
                 * supported value.
                 */
                fun type(type: JsonValue) = apply { this.type = type }

                /** A signature hash for backend validation. */
                fun signature(signature: String) = signature(JsonField.of(signature))

                /**
                 * Sets [Builder.signature] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.signature] with a well-typed [String] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun signature(signature: JsonField<String>) = apply { this.signature = signature }

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
                 * Returns an immutable instance of [GoogleSearchCall].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 *
                 * The following fields are required:
                 * ```java
                 * .id()
                 * .arguments()
                 * ```
                 *
                 * @throws IllegalStateException if any required field is unset.
                 */
                fun build(): GoogleSearchCall =
                    GoogleSearchCall(
                        checkRequired("id", id),
                        checkRequired("arguments", arguments),
                        type,
                        signature,
                        additionalProperties.toMutableMap(),
                    )
            }

            private var validated: Boolean = false

            fun validate(): GoogleSearchCall = apply {
                if (validated) {
                    return@apply
                }

                id()
                arguments().validate()
                _type().let {
                    if (it != JsonValue.from("google_search_call")) {
                        throw GeminiNextGenApiInvalidDataException(
                            "'type' is invalid, received $it"
                        )
                    }
                }
                signature()
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
                (if (id.asKnown().isPresent) 1 else 0) +
                    (arguments.asKnown().getOrNull()?.validity() ?: 0) +
                    type.let { if (it == JsonValue.from("google_search_call")) 1 else 0 } +
                    (if (signature.asKnown().isPresent) 1 else 0)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is GoogleSearchCall &&
                    id == other.id &&
                    arguments == other.arguments &&
                    type == other.type &&
                    signature == other.signature &&
                    additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(id, arguments, type, signature, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "GoogleSearchCall{id=$id, arguments=$arguments, type=$type, signature=$signature, additionalProperties=$additionalProperties}"
        }

        class McpServerToolCall
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val id: JsonField<String>,
            private val arguments: JsonField<Arguments>,
            private val name: JsonField<String>,
            private val serverName: JsonField<String>,
            private val type: JsonValue,
            private val signature: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
                @JsonProperty("arguments")
                @ExcludeMissing
                arguments: JsonField<Arguments> = JsonMissing.of(),
                @JsonProperty("name") @ExcludeMissing name: JsonField<String> = JsonMissing.of(),
                @JsonProperty("server_name")
                @ExcludeMissing
                serverName: JsonField<String> = JsonMissing.of(),
                @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
                @JsonProperty("signature")
                @ExcludeMissing
                signature: JsonField<String> = JsonMissing.of(),
            ) : this(id, arguments, name, serverName, type, signature, mutableMapOf())

            /**
             * Required. A unique ID for this specific tool call.
             *
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   or is unexpectedly missing or null (e.g. if the server responded with an unexpected
             *   value).
             */
            fun id(): String = id.getRequired("id")

            /**
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   or is unexpectedly missing or null (e.g. if the server responded with an unexpected
             *   value).
             */
            fun arguments(): Arguments = arguments.getRequired("arguments")

            /**
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   or is unexpectedly missing or null (e.g. if the server responded with an unexpected
             *   value).
             */
            fun name(): String = name.getRequired("name")

            /**
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   or is unexpectedly missing or null (e.g. if the server responded with an unexpected
             *   value).
             */
            fun serverName(): String = serverName.getRequired("server_name")

            /**
             * Expected to always return the following:
             * ```java
             * JsonValue.from("mcp_server_tool_call")
             * ```
             *
             * However, this method can be useful for debugging and logging (e.g. if the server
             * responded with an unexpected value).
             */
            @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

            /**
             * A signature hash for backend validation.
             *
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   (e.g. if the server responded with an unexpected value).
             */
            fun signature(): Optional<String> = signature.getOptional("signature")

            /**
             * Returns the raw JSON value of [id].
             *
             * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

            /**
             * Returns the raw JSON value of [arguments].
             *
             * Unlike [arguments], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("arguments")
            @ExcludeMissing
            fun _arguments(): JsonField<Arguments> = arguments

            /**
             * Returns the raw JSON value of [name].
             *
             * Unlike [name], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("name") @ExcludeMissing fun _name(): JsonField<String> = name

            /**
             * Returns the raw JSON value of [serverName].
             *
             * Unlike [serverName], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("server_name")
            @ExcludeMissing
            fun _serverName(): JsonField<String> = serverName

            /**
             * Returns the raw JSON value of [signature].
             *
             * Unlike [signature], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("signature")
            @ExcludeMissing
            fun _signature(): JsonField<String> = signature

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
                 * Returns a mutable builder for constructing an instance of [McpServerToolCall].
                 *
                 * The following fields are required:
                 * ```java
                 * .id()
                 * .arguments()
                 * .name()
                 * .serverName()
                 * ```
                 */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [McpServerToolCall]. */
            class Builder internal constructor() {

                private var id: JsonField<String>? = null
                private var arguments: JsonField<Arguments>? = null
                private var name: JsonField<String>? = null
                private var serverName: JsonField<String>? = null
                private var type: JsonValue = JsonValue.from("mcp_server_tool_call")
                private var signature: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(mcpServerToolCall: McpServerToolCall) = apply {
                    id = mcpServerToolCall.id
                    arguments = mcpServerToolCall.arguments
                    name = mcpServerToolCall.name
                    serverName = mcpServerToolCall.serverName
                    type = mcpServerToolCall.type
                    signature = mcpServerToolCall.signature
                    additionalProperties = mcpServerToolCall.additionalProperties.toMutableMap()
                }

                /** Required. A unique ID for this specific tool call. */
                fun id(id: String) = id(JsonField.of(id))

                /**
                 * Sets [Builder.id] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.id] with a well-typed [String] value instead.
                 * This method is primarily for setting the field to an undocumented or not yet
                 * supported value.
                 */
                fun id(id: JsonField<String>) = apply { this.id = id }

                fun arguments(arguments: Arguments) = arguments(JsonField.of(arguments))

                /**
                 * Sets [Builder.arguments] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.arguments] with a well-typed [Arguments] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun arguments(arguments: JsonField<Arguments>) = apply {
                    this.arguments = arguments
                }

                fun name(name: String) = name(JsonField.of(name))

                /**
                 * Sets [Builder.name] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.name] with a well-typed [String] value instead.
                 * This method is primarily for setting the field to an undocumented or not yet
                 * supported value.
                 */
                fun name(name: JsonField<String>) = apply { this.name = name }

                fun serverName(serverName: String) = serverName(JsonField.of(serverName))

                /**
                 * Sets [Builder.serverName] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.serverName] with a well-typed [String] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun serverName(serverName: JsonField<String>) = apply {
                    this.serverName = serverName
                }

                /**
                 * Sets the field to an arbitrary JSON value.
                 *
                 * It is usually unnecessary to call this method because the field defaults to the
                 * following:
                 * ```java
                 * JsonValue.from("mcp_server_tool_call")
                 * ```
                 *
                 * This method is primarily for setting the field to an undocumented or not yet
                 * supported value.
                 */
                fun type(type: JsonValue) = apply { this.type = type }

                /** A signature hash for backend validation. */
                fun signature(signature: String) = signature(JsonField.of(signature))

                /**
                 * Sets [Builder.signature] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.signature] with a well-typed [String] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun signature(signature: JsonField<String>) = apply { this.signature = signature }

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
                 * Returns an immutable instance of [McpServerToolCall].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 *
                 * The following fields are required:
                 * ```java
                 * .id()
                 * .arguments()
                 * .name()
                 * .serverName()
                 * ```
                 *
                 * @throws IllegalStateException if any required field is unset.
                 */
                fun build(): McpServerToolCall =
                    McpServerToolCall(
                        checkRequired("id", id),
                        checkRequired("arguments", arguments),
                        checkRequired("name", name),
                        checkRequired("serverName", serverName),
                        type,
                        signature,
                        additionalProperties.toMutableMap(),
                    )
            }

            private var validated: Boolean = false

            fun validate(): McpServerToolCall = apply {
                if (validated) {
                    return@apply
                }

                id()
                arguments().validate()
                name()
                serverName()
                _type().let {
                    if (it != JsonValue.from("mcp_server_tool_call")) {
                        throw GeminiNextGenApiInvalidDataException(
                            "'type' is invalid, received $it"
                        )
                    }
                }
                signature()
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
                (if (id.asKnown().isPresent) 1 else 0) +
                    (arguments.asKnown().getOrNull()?.validity() ?: 0) +
                    (if (name.asKnown().isPresent) 1 else 0) +
                    (if (serverName.asKnown().isPresent) 1 else 0) +
                    type.let { if (it == JsonValue.from("mcp_server_tool_call")) 1 else 0 } +
                    (if (signature.asKnown().isPresent) 1 else 0)

            class Arguments
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

                    /** Returns a mutable builder for constructing an instance of [Arguments]. */
                    @JvmStatic fun builder() = Builder()
                }

                /** A builder for [Arguments]. */
                class Builder internal constructor() {

                    private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                    @JvmSynthetic
                    internal fun from(arguments: Arguments) = apply {
                        additionalProperties = arguments.additionalProperties.toMutableMap()
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
                     * Returns an immutable instance of [Arguments].
                     *
                     * Further updates to this [Builder] will not mutate the returned instance.
                     */
                    fun build(): Arguments = Arguments(additionalProperties.toImmutable())
                }

                private var validated: Boolean = false

                fun validate(): Arguments = apply {
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
                    additionalProperties.count { (_, value) ->
                        !value.isNull() && !value.isMissing()
                    }

                override fun equals(other: Any?): Boolean {
                    if (this === other) {
                        return true
                    }

                    return other is Arguments && additionalProperties == other.additionalProperties
                }

                private val hashCode: Int by lazy { Objects.hash(additionalProperties) }

                override fun hashCode(): Int = hashCode

                override fun toString() = "Arguments{additionalProperties=$additionalProperties}"
            }

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is McpServerToolCall &&
                    id == other.id &&
                    arguments == other.arguments &&
                    name == other.name &&
                    serverName == other.serverName &&
                    type == other.type &&
                    signature == other.signature &&
                    additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(id, arguments, name, serverName, type, signature, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "McpServerToolCall{id=$id, arguments=$arguments, name=$name, serverName=$serverName, type=$type, signature=$signature, additionalProperties=$additionalProperties}"
        }

        class FileSearchCall
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val id: JsonField<String>,
            private val type: JsonValue,
            private val signature: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
                @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
                @JsonProperty("signature")
                @ExcludeMissing
                signature: JsonField<String> = JsonMissing.of(),
            ) : this(id, type, signature, mutableMapOf())

            /**
             * Required. A unique ID for this specific tool call.
             *
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   or is unexpectedly missing or null (e.g. if the server responded with an unexpected
             *   value).
             */
            fun id(): String = id.getRequired("id")

            /**
             * Expected to always return the following:
             * ```java
             * JsonValue.from("file_search_call")
             * ```
             *
             * However, this method can be useful for debugging and logging (e.g. if the server
             * responded with an unexpected value).
             */
            @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

            /**
             * A signature hash for backend validation.
             *
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   (e.g. if the server responded with an unexpected value).
             */
            fun signature(): Optional<String> = signature.getOptional("signature")

            /**
             * Returns the raw JSON value of [id].
             *
             * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

            /**
             * Returns the raw JSON value of [signature].
             *
             * Unlike [signature], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("signature")
            @ExcludeMissing
            fun _signature(): JsonField<String> = signature

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
                 * Returns a mutable builder for constructing an instance of [FileSearchCall].
                 *
                 * The following fields are required:
                 * ```java
                 * .id()
                 * ```
                 */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [FileSearchCall]. */
            class Builder internal constructor() {

                private var id: JsonField<String>? = null
                private var type: JsonValue = JsonValue.from("file_search_call")
                private var signature: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(fileSearchCall: FileSearchCall) = apply {
                    id = fileSearchCall.id
                    type = fileSearchCall.type
                    signature = fileSearchCall.signature
                    additionalProperties = fileSearchCall.additionalProperties.toMutableMap()
                }

                /** Required. A unique ID for this specific tool call. */
                fun id(id: String) = id(JsonField.of(id))

                /**
                 * Sets [Builder.id] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.id] with a well-typed [String] value instead.
                 * This method is primarily for setting the field to an undocumented or not yet
                 * supported value.
                 */
                fun id(id: JsonField<String>) = apply { this.id = id }

                /**
                 * Sets the field to an arbitrary JSON value.
                 *
                 * It is usually unnecessary to call this method because the field defaults to the
                 * following:
                 * ```java
                 * JsonValue.from("file_search_call")
                 * ```
                 *
                 * This method is primarily for setting the field to an undocumented or not yet
                 * supported value.
                 */
                fun type(type: JsonValue) = apply { this.type = type }

                /** A signature hash for backend validation. */
                fun signature(signature: String) = signature(JsonField.of(signature))

                /**
                 * Sets [Builder.signature] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.signature] with a well-typed [String] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun signature(signature: JsonField<String>) = apply { this.signature = signature }

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
                 * Returns an immutable instance of [FileSearchCall].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 *
                 * The following fields are required:
                 * ```java
                 * .id()
                 * ```
                 *
                 * @throws IllegalStateException if any required field is unset.
                 */
                fun build(): FileSearchCall =
                    FileSearchCall(
                        checkRequired("id", id),
                        type,
                        signature,
                        additionalProperties.toMutableMap(),
                    )
            }

            private var validated: Boolean = false

            fun validate(): FileSearchCall = apply {
                if (validated) {
                    return@apply
                }

                id()
                _type().let {
                    if (it != JsonValue.from("file_search_call")) {
                        throw GeminiNextGenApiInvalidDataException(
                            "'type' is invalid, received $it"
                        )
                    }
                }
                signature()
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
                (if (id.asKnown().isPresent) 1 else 0) +
                    type.let { if (it == JsonValue.from("file_search_call")) 1 else 0 } +
                    (if (signature.asKnown().isPresent) 1 else 0)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is FileSearchCall &&
                    id == other.id &&
                    type == other.type &&
                    signature == other.signature &&
                    additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(id, type, signature, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "FileSearchCall{id=$id, type=$type, signature=$signature, additionalProperties=$additionalProperties}"
        }

        class GoogleMapsCall
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val id: JsonField<String>,
            private val type: JsonValue,
            private val arguments: JsonField<GoogleMapsCallArguments>,
            private val signature: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
                @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
                @JsonProperty("arguments")
                @ExcludeMissing
                arguments: JsonField<GoogleMapsCallArguments> = JsonMissing.of(),
                @JsonProperty("signature")
                @ExcludeMissing
                signature: JsonField<String> = JsonMissing.of(),
            ) : this(id, type, arguments, signature, mutableMapOf())

            /**
             * Required. A unique ID for this specific tool call.
             *
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   or is unexpectedly missing or null (e.g. if the server responded with an unexpected
             *   value).
             */
            fun id(): String = id.getRequired("id")

            /**
             * Expected to always return the following:
             * ```java
             * JsonValue.from("google_maps_call")
             * ```
             *
             * However, this method can be useful for debugging and logging (e.g. if the server
             * responded with an unexpected value).
             */
            @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

            /**
             * The arguments to pass to the Google Maps tool.
             *
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   (e.g. if the server responded with an unexpected value).
             */
            fun arguments(): Optional<GoogleMapsCallArguments> = arguments.getOptional("arguments")

            /**
             * A signature hash for backend validation.
             *
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   (e.g. if the server responded with an unexpected value).
             */
            fun signature(): Optional<String> = signature.getOptional("signature")

            /**
             * Returns the raw JSON value of [id].
             *
             * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

            /**
             * Returns the raw JSON value of [arguments].
             *
             * Unlike [arguments], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("arguments")
            @ExcludeMissing
            fun _arguments(): JsonField<GoogleMapsCallArguments> = arguments

            /**
             * Returns the raw JSON value of [signature].
             *
             * Unlike [signature], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("signature")
            @ExcludeMissing
            fun _signature(): JsonField<String> = signature

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
                 * Returns a mutable builder for constructing an instance of [GoogleMapsCall].
                 *
                 * The following fields are required:
                 * ```java
                 * .id()
                 * ```
                 */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [GoogleMapsCall]. */
            class Builder internal constructor() {

                private var id: JsonField<String>? = null
                private var type: JsonValue = JsonValue.from("google_maps_call")
                private var arguments: JsonField<GoogleMapsCallArguments> = JsonMissing.of()
                private var signature: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(googleMapsCall: GoogleMapsCall) = apply {
                    id = googleMapsCall.id
                    type = googleMapsCall.type
                    arguments = googleMapsCall.arguments
                    signature = googleMapsCall.signature
                    additionalProperties = googleMapsCall.additionalProperties.toMutableMap()
                }

                /** Required. A unique ID for this specific tool call. */
                fun id(id: String) = id(JsonField.of(id))

                /**
                 * Sets [Builder.id] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.id] with a well-typed [String] value instead.
                 * This method is primarily for setting the field to an undocumented or not yet
                 * supported value.
                 */
                fun id(id: JsonField<String>) = apply { this.id = id }

                /**
                 * Sets the field to an arbitrary JSON value.
                 *
                 * It is usually unnecessary to call this method because the field defaults to the
                 * following:
                 * ```java
                 * JsonValue.from("google_maps_call")
                 * ```
                 *
                 * This method is primarily for setting the field to an undocumented or not yet
                 * supported value.
                 */
                fun type(type: JsonValue) = apply { this.type = type }

                /** The arguments to pass to the Google Maps tool. */
                fun arguments(arguments: GoogleMapsCallArguments) =
                    arguments(JsonField.of(arguments))

                /**
                 * Sets [Builder.arguments] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.arguments] with a well-typed
                 * [GoogleMapsCallArguments] value instead. This method is primarily for setting the
                 * field to an undocumented or not yet supported value.
                 */
                fun arguments(arguments: JsonField<GoogleMapsCallArguments>) = apply {
                    this.arguments = arguments
                }

                /** A signature hash for backend validation. */
                fun signature(signature: String) = signature(JsonField.of(signature))

                /**
                 * Sets [Builder.signature] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.signature] with a well-typed [String] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun signature(signature: JsonField<String>) = apply { this.signature = signature }

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
                 * Returns an immutable instance of [GoogleMapsCall].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 *
                 * The following fields are required:
                 * ```java
                 * .id()
                 * ```
                 *
                 * @throws IllegalStateException if any required field is unset.
                 */
                fun build(): GoogleMapsCall =
                    GoogleMapsCall(
                        checkRequired("id", id),
                        type,
                        arguments,
                        signature,
                        additionalProperties.toMutableMap(),
                    )
            }

            private var validated: Boolean = false

            fun validate(): GoogleMapsCall = apply {
                if (validated) {
                    return@apply
                }

                id()
                _type().let {
                    if (it != JsonValue.from("google_maps_call")) {
                        throw GeminiNextGenApiInvalidDataException(
                            "'type' is invalid, received $it"
                        )
                    }
                }
                arguments().ifPresent { it.validate() }
                signature()
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
                (if (id.asKnown().isPresent) 1 else 0) +
                    type.let { if (it == JsonValue.from("google_maps_call")) 1 else 0 } +
                    (arguments.asKnown().getOrNull()?.validity() ?: 0) +
                    (if (signature.asKnown().isPresent) 1 else 0)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is GoogleMapsCall &&
                    id == other.id &&
                    type == other.type &&
                    arguments == other.arguments &&
                    signature == other.signature &&
                    additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(id, type, arguments, signature, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "GoogleMapsCall{id=$id, type=$type, arguments=$arguments, signature=$signature, additionalProperties=$additionalProperties}"
        }

        class FunctionResult
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val callId: JsonField<String>,
            private val result: JsonField<Result>,
            private val type: JsonValue,
            private val isError: JsonField<Boolean>,
            private val name: JsonField<String>,
            private val signature: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("call_id")
                @ExcludeMissing
                callId: JsonField<String> = JsonMissing.of(),
                @JsonProperty("result")
                @ExcludeMissing
                result: JsonField<Result> = JsonMissing.of(),
                @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
                @JsonProperty("is_error")
                @ExcludeMissing
                isError: JsonField<Boolean> = JsonMissing.of(),
                @JsonProperty("name") @ExcludeMissing name: JsonField<String> = JsonMissing.of(),
                @JsonProperty("signature")
                @ExcludeMissing
                signature: JsonField<String> = JsonMissing.of(),
            ) : this(callId, result, type, isError, name, signature, mutableMapOf())

            /**
             * Required. ID to match the ID from the function call block.
             *
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   or is unexpectedly missing or null (e.g. if the server responded with an unexpected
             *   value).
             */
            fun callId(): String = callId.getRequired("call_id")

            /**
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   or is unexpectedly missing or null (e.g. if the server responded with an unexpected
             *   value).
             */
            fun result(): Result = result.getRequired("result")

            /**
             * Expected to always return the following:
             * ```java
             * JsonValue.from("function_result")
             * ```
             *
             * However, this method can be useful for debugging and logging (e.g. if the server
             * responded with an unexpected value).
             */
            @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

            /**
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   (e.g. if the server responded with an unexpected value).
             */
            fun isError(): Optional<Boolean> = isError.getOptional("is_error")

            /**
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   (e.g. if the server responded with an unexpected value).
             */
            fun name(): Optional<String> = name.getOptional("name")

            /**
             * A signature hash for backend validation.
             *
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   (e.g. if the server responded with an unexpected value).
             */
            fun signature(): Optional<String> = signature.getOptional("signature")

            /**
             * Returns the raw JSON value of [callId].
             *
             * Unlike [callId], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("call_id") @ExcludeMissing fun _callId(): JsonField<String> = callId

            /**
             * Returns the raw JSON value of [result].
             *
             * Unlike [result], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("result") @ExcludeMissing fun _result(): JsonField<Result> = result

            /**
             * Returns the raw JSON value of [isError].
             *
             * Unlike [isError], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("is_error") @ExcludeMissing fun _isError(): JsonField<Boolean> = isError

            /**
             * Returns the raw JSON value of [name].
             *
             * Unlike [name], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("name") @ExcludeMissing fun _name(): JsonField<String> = name

            /**
             * Returns the raw JSON value of [signature].
             *
             * Unlike [signature], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("signature")
            @ExcludeMissing
            fun _signature(): JsonField<String> = signature

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
                 * Returns a mutable builder for constructing an instance of [FunctionResult].
                 *
                 * The following fields are required:
                 * ```java
                 * .callId()
                 * .result()
                 * ```
                 */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [FunctionResult]. */
            class Builder internal constructor() {

                private var callId: JsonField<String>? = null
                private var result: JsonField<Result>? = null
                private var type: JsonValue = JsonValue.from("function_result")
                private var isError: JsonField<Boolean> = JsonMissing.of()
                private var name: JsonField<String> = JsonMissing.of()
                private var signature: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(functionResult: FunctionResult) = apply {
                    callId = functionResult.callId
                    result = functionResult.result
                    type = functionResult.type
                    isError = functionResult.isError
                    name = functionResult.name
                    signature = functionResult.signature
                    additionalProperties = functionResult.additionalProperties.toMutableMap()
                }

                /** Required. ID to match the ID from the function call block. */
                fun callId(callId: String) = callId(JsonField.of(callId))

                /**
                 * Sets [Builder.callId] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.callId] with a well-typed [String] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun callId(callId: JsonField<String>) = apply { this.callId = callId }

                fun result(result: Result) = result(JsonField.of(result))

                /**
                 * Sets [Builder.result] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.result] with a well-typed [Result] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun result(result: JsonField<Result>) = apply { this.result = result }

                /** Alias for calling [result] with `Result.ofJsonValue(jsonValue)`. */
                fun result(jsonValue: JsonValue) = result(Result.ofJsonValue(jsonValue))

                /**
                 * Alias for calling [result] with
                 * `Result.ofFunctionResultSubcontentList(functionResultSubcontentList)`.
                 */
                fun resultOfFunctionResultSubcontentList(
                    functionResultSubcontentList: List<Result.FunctionResultSubcontent>
                ) = result(Result.ofFunctionResultSubcontentList(functionResultSubcontentList))

                /** Alias for calling [result] with `Result.ofString(string)`. */
                fun result(string: String) = result(Result.ofString(string))

                /**
                 * Sets the field to an arbitrary JSON value.
                 *
                 * It is usually unnecessary to call this method because the field defaults to the
                 * following:
                 * ```java
                 * JsonValue.from("function_result")
                 * ```
                 *
                 * This method is primarily for setting the field to an undocumented or not yet
                 * supported value.
                 */
                fun type(type: JsonValue) = apply { this.type = type }

                fun isError(isError: Boolean) = isError(JsonField.of(isError))

                /**
                 * Sets [Builder.isError] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.isError] with a well-typed [Boolean] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun isError(isError: JsonField<Boolean>) = apply { this.isError = isError }

                fun name(name: String) = name(JsonField.of(name))

                /**
                 * Sets [Builder.name] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.name] with a well-typed [String] value instead.
                 * This method is primarily for setting the field to an undocumented or not yet
                 * supported value.
                 */
                fun name(name: JsonField<String>) = apply { this.name = name }

                /** A signature hash for backend validation. */
                fun signature(signature: String) = signature(JsonField.of(signature))

                /**
                 * Sets [Builder.signature] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.signature] with a well-typed [String] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun signature(signature: JsonField<String>) = apply { this.signature = signature }

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
                 * Returns an immutable instance of [FunctionResult].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 *
                 * The following fields are required:
                 * ```java
                 * .callId()
                 * .result()
                 * ```
                 *
                 * @throws IllegalStateException if any required field is unset.
                 */
                fun build(): FunctionResult =
                    FunctionResult(
                        checkRequired("callId", callId),
                        checkRequired("result", result),
                        type,
                        isError,
                        name,
                        signature,
                        additionalProperties.toMutableMap(),
                    )
            }

            private var validated: Boolean = false

            fun validate(): FunctionResult = apply {
                if (validated) {
                    return@apply
                }

                callId()
                result().validate()
                _type().let {
                    if (it != JsonValue.from("function_result")) {
                        throw GeminiNextGenApiInvalidDataException(
                            "'type' is invalid, received $it"
                        )
                    }
                }
                isError()
                name()
                signature()
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
                (if (callId.asKnown().isPresent) 1 else 0) +
                    (result.asKnown().getOrNull()?.validity() ?: 0) +
                    type.let { if (it == JsonValue.from("function_result")) 1 else 0 } +
                    (if (isError.asKnown().isPresent) 1 else 0) +
                    (if (name.asKnown().isPresent) 1 else 0) +
                    (if (signature.asKnown().isPresent) 1 else 0)

            @JsonDeserialize(using = Result.Deserializer::class)
            @JsonSerialize(using = Result.Serializer::class)
            class Result
            private constructor(
                private val jsonValue: JsonValue? = null,
                private val functionResultSubcontentList: List<FunctionResultSubcontent>? = null,
                private val string: String? = null,
                private val _json: JsonValue? = null,
            ) {

                fun jsonValue(): Optional<JsonValue> = Optional.ofNullable(jsonValue)

                fun functionResultSubcontentList(): Optional<List<FunctionResultSubcontent>> =
                    Optional.ofNullable(functionResultSubcontentList)

                fun string(): Optional<String> = Optional.ofNullable(string)

                fun isJsonValue(): Boolean = jsonValue != null

                fun isFunctionResultSubcontentList(): Boolean = functionResultSubcontentList != null

                fun isString(): Boolean = string != null

                fun asJsonValue(): JsonValue = jsonValue.getOrThrow("jsonValue")

                fun asFunctionResultSubcontentList(): List<FunctionResultSubcontent> =
                    functionResultSubcontentList.getOrThrow("functionResultSubcontentList")

                fun asString(): String = string.getOrThrow("string")

                fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

                fun <T> accept(visitor: Visitor<T>): T =
                    when {
                        jsonValue != null -> visitor.visitJsonValue(jsonValue)
                        functionResultSubcontentList != null ->
                            visitor.visitFunctionResultSubcontentList(functionResultSubcontentList)
                        string != null -> visitor.visitString(string)
                        else -> visitor.unknown(_json)
                    }

                private var validated: Boolean = false

                fun validate(): Result = apply {
                    if (validated) {
                        return@apply
                    }

                    accept(
                        object : Visitor<Unit> {
                            override fun visitJsonValue(jsonValue: JsonValue) {}

                            override fun visitFunctionResultSubcontentList(
                                functionResultSubcontentList: List<FunctionResultSubcontent>
                            ) {
                                functionResultSubcontentList.forEach { it.validate() }
                            }

                            override fun visitString(string: String) {}
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
                            override fun visitJsonValue(jsonValue: JsonValue) = 1

                            override fun visitFunctionResultSubcontentList(
                                functionResultSubcontentList: List<FunctionResultSubcontent>
                            ) = functionResultSubcontentList.sumOf { it.validity().toInt() }

                            override fun visitString(string: String) = 1

                            override fun unknown(json: JsonValue?) = 0
                        }
                    )

                override fun equals(other: Any?): Boolean {
                    if (this === other) {
                        return true
                    }

                    return other is Result &&
                        jsonValue == other.jsonValue &&
                        functionResultSubcontentList == other.functionResultSubcontentList &&
                        string == other.string
                }

                override fun hashCode(): Int =
                    Objects.hash(jsonValue, functionResultSubcontentList, string)

                override fun toString(): String =
                    when {
                        jsonValue != null -> "Result{jsonValue=$jsonValue}"
                        functionResultSubcontentList != null ->
                            "Result{functionResultSubcontentList=$functionResultSubcontentList}"
                        string != null -> "Result{string=$string}"
                        _json != null -> "Result{_unknown=$_json}"
                        else -> throw IllegalStateException("Invalid Result")
                    }

                companion object {

                    @JvmStatic fun ofJsonValue(jsonValue: JsonValue) = Result(jsonValue = jsonValue)

                    @JvmStatic
                    fun ofFunctionResultSubcontentList(
                        functionResultSubcontentList: List<FunctionResultSubcontent>
                    ) =
                        Result(
                            functionResultSubcontentList =
                                functionResultSubcontentList.toImmutable()
                        )

                    @JvmStatic fun ofString(string: String) = Result(string = string)
                }

                /**
                 * An interface that defines how to map each variant of [Result] to a value of type
                 * [T].
                 */
                interface Visitor<out T> {

                    fun visitJsonValue(jsonValue: JsonValue): T

                    fun visitFunctionResultSubcontentList(
                        functionResultSubcontentList: List<FunctionResultSubcontent>
                    ): T

                    fun visitString(string: String): T

                    /**
                     * Maps an unknown variant of [Result] to a value of type [T].
                     *
                     * An instance of [Result] can contain an unknown variant if it was deserialized
                     * from data that doesn't match any known variant. For example, if the SDK is on
                     * an older version than the API, then the API may respond with new variants
                     * that the SDK is unaware of.
                     *
                     * @throws GeminiNextGenApiInvalidDataException in the default implementation.
                     */
                    fun unknown(json: JsonValue?): T {
                        throw GeminiNextGenApiInvalidDataException("Unknown Result: $json")
                    }
                }

                internal class Deserializer : BaseDeserializer<Result>(Result::class) {

                    override fun ObjectCodec.deserialize(node: JsonNode): Result {
                        val json = JsonValue.fromJsonNode(node)

                        val bestMatches =
                            sequenceOf(
                                    tryDeserialize(node, jacksonTypeRef<String>())?.let {
                                        Result(string = it, _json = json)
                                    },
                                    tryDeserialize(
                                            node,
                                            jacksonTypeRef<List<FunctionResultSubcontent>>(),
                                        )
                                        ?.let {
                                            Result(functionResultSubcontentList = it, _json = json)
                                        },
                                    tryDeserialize(node, jacksonTypeRef<JsonValue>())?.let {
                                        Result(jsonValue = it, _json = json)
                                    },
                                )
                                .filterNotNull()
                                .allMaxBy { it.validity() }
                                .toList()
                        return when (bestMatches.size) {
                            // This can happen if what we're deserializing is completely
                            // incompatible with all the possible variants.
                            0 -> Result(_json = json)
                            1 -> bestMatches.single()
                            // If there's more than one match with the highest validity, then use
                            // the first completely valid match, or simply the first match if none
                            // are completely valid.
                            else -> bestMatches.firstOrNull { it.isValid() } ?: bestMatches.first()
                        }
                    }
                }

                internal class Serializer : BaseSerializer<Result>(Result::class) {

                    override fun serialize(
                        value: Result,
                        generator: JsonGenerator,
                        provider: SerializerProvider,
                    ) {
                        when {
                            value.jsonValue != null -> generator.writeObject(value.jsonValue)
                            value.functionResultSubcontentList != null ->
                                generator.writeObject(value.functionResultSubcontentList)
                            value.string != null -> generator.writeObject(value.string)
                            value._json != null -> generator.writeObject(value._json)
                            else -> throw IllegalStateException("Invalid Result")
                        }
                    }
                }

                /** A text content block. */
                @JsonDeserialize(using = FunctionResultSubcontent.Deserializer::class)
                @JsonSerialize(using = FunctionResultSubcontent.Serializer::class)
                class FunctionResultSubcontent
                private constructor(
                    private val text: TextContent? = null,
                    private val image: ImageContent? = null,
                    private val _json: JsonValue? = null,
                ) {

                    /** A text content block. */
                    fun text(): Optional<TextContent> = Optional.ofNullable(text)

                    /** An image content block. */
                    fun image(): Optional<ImageContent> = Optional.ofNullable(image)

                    fun isText(): Boolean = text != null

                    fun isImage(): Boolean = image != null

                    /** A text content block. */
                    fun asText(): TextContent = text.getOrThrow("text")

                    /** An image content block. */
                    fun asImage(): ImageContent = image.getOrThrow("image")

                    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

                    fun <T> accept(visitor: Visitor<T>): T =
                        when {
                            text != null -> visitor.visitText(text)
                            image != null -> visitor.visitImage(image)
                            else -> visitor.unknown(_json)
                        }

                    private var validated: Boolean = false

                    fun validate(): FunctionResultSubcontent = apply {
                        if (validated) {
                            return@apply
                        }

                        accept(
                            object : Visitor<Unit> {
                                override fun visitText(text: TextContent) {
                                    text.validate()
                                }

                                override fun visitImage(image: ImageContent) {
                                    image.validate()
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
                                override fun visitText(text: TextContent) = text.validity()

                                override fun visitImage(image: ImageContent) = image.validity()

                                override fun unknown(json: JsonValue?) = 0
                            }
                        )

                    override fun equals(other: Any?): Boolean {
                        if (this === other) {
                            return true
                        }

                        return other is FunctionResultSubcontent &&
                            text == other.text &&
                            image == other.image
                    }

                    override fun hashCode(): Int = Objects.hash(text, image)

                    override fun toString(): String =
                        when {
                            text != null -> "FunctionResultSubcontent{text=$text}"
                            image != null -> "FunctionResultSubcontent{image=$image}"
                            _json != null -> "FunctionResultSubcontent{_unknown=$_json}"
                            else -> throw IllegalStateException("Invalid FunctionResultSubcontent")
                        }

                    companion object {

                        /** A text content block. */
                        @JvmStatic
                        fun ofText(text: TextContent) = FunctionResultSubcontent(text = text)

                        /** An image content block. */
                        @JvmStatic
                        fun ofImage(image: ImageContent) = FunctionResultSubcontent(image = image)
                    }

                    /**
                     * An interface that defines how to map each variant of
                     * [FunctionResultSubcontent] to a value of type [T].
                     */
                    interface Visitor<out T> {

                        /** A text content block. */
                        fun visitText(text: TextContent): T

                        /** An image content block. */
                        fun visitImage(image: ImageContent): T

                        /**
                         * Maps an unknown variant of [FunctionResultSubcontent] to a value of type
                         * [T].
                         *
                         * An instance of [FunctionResultSubcontent] can contain an unknown variant
                         * if it was deserialized from data that doesn't match any known variant.
                         * For example, if the SDK is on an older version than the API, then the API
                         * may respond with new variants that the SDK is unaware of.
                         *
                         * @throws GeminiNextGenApiInvalidDataException in the default
                         *   implementation.
                         */
                        fun unknown(json: JsonValue?): T {
                            throw GeminiNextGenApiInvalidDataException(
                                "Unknown FunctionResultSubcontent: $json"
                            )
                        }
                    }

                    internal class Deserializer :
                        BaseDeserializer<FunctionResultSubcontent>(
                            FunctionResultSubcontent::class
                        ) {

                        override fun ObjectCodec.deserialize(
                            node: JsonNode
                        ): FunctionResultSubcontent {
                            val json = JsonValue.fromJsonNode(node)
                            val type =
                                json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                            when (type) {
                                "text" -> {
                                    return tryDeserialize(node, jacksonTypeRef<TextContent>())
                                        ?.let { FunctionResultSubcontent(text = it, _json = json) }
                                        ?: FunctionResultSubcontent(_json = json)
                                }
                                "image" -> {
                                    return tryDeserialize(node, jacksonTypeRef<ImageContent>())
                                        ?.let { FunctionResultSubcontent(image = it, _json = json) }
                                        ?: FunctionResultSubcontent(_json = json)
                                }
                            }

                            return FunctionResultSubcontent(_json = json)
                        }
                    }

                    internal class Serializer :
                        BaseSerializer<FunctionResultSubcontent>(FunctionResultSubcontent::class) {

                        override fun serialize(
                            value: FunctionResultSubcontent,
                            generator: JsonGenerator,
                            provider: SerializerProvider,
                        ) {
                            when {
                                value.text != null -> generator.writeObject(value.text)
                                value.image != null -> generator.writeObject(value.image)
                                value._json != null -> generator.writeObject(value._json)
                                else ->
                                    throw IllegalStateException("Invalid FunctionResultSubcontent")
                            }
                        }
                    }
                }
            }

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is FunctionResult &&
                    callId == other.callId &&
                    result == other.result &&
                    type == other.type &&
                    isError == other.isError &&
                    name == other.name &&
                    signature == other.signature &&
                    additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(callId, result, type, isError, name, signature, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "FunctionResult{callId=$callId, result=$result, type=$type, isError=$isError, name=$name, signature=$signature, additionalProperties=$additionalProperties}"
        }

        class CodeExecutionResult
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val callId: JsonField<String>,
            private val result: JsonField<String>,
            private val type: JsonValue,
            private val isError: JsonField<Boolean>,
            private val signature: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("call_id")
                @ExcludeMissing
                callId: JsonField<String> = JsonMissing.of(),
                @JsonProperty("result")
                @ExcludeMissing
                result: JsonField<String> = JsonMissing.of(),
                @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
                @JsonProperty("is_error")
                @ExcludeMissing
                isError: JsonField<Boolean> = JsonMissing.of(),
                @JsonProperty("signature")
                @ExcludeMissing
                signature: JsonField<String> = JsonMissing.of(),
            ) : this(callId, result, type, isError, signature, mutableMapOf())

            /**
             * Required. ID to match the ID from the function call block.
             *
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   or is unexpectedly missing or null (e.g. if the server responded with an unexpected
             *   value).
             */
            fun callId(): String = callId.getRequired("call_id")

            /**
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   or is unexpectedly missing or null (e.g. if the server responded with an unexpected
             *   value).
             */
            fun result(): String = result.getRequired("result")

            /**
             * Expected to always return the following:
             * ```java
             * JsonValue.from("code_execution_result")
             * ```
             *
             * However, this method can be useful for debugging and logging (e.g. if the server
             * responded with an unexpected value).
             */
            @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

            /**
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   (e.g. if the server responded with an unexpected value).
             */
            fun isError(): Optional<Boolean> = isError.getOptional("is_error")

            /**
             * A signature hash for backend validation.
             *
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   (e.g. if the server responded with an unexpected value).
             */
            fun signature(): Optional<String> = signature.getOptional("signature")

            /**
             * Returns the raw JSON value of [callId].
             *
             * Unlike [callId], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("call_id") @ExcludeMissing fun _callId(): JsonField<String> = callId

            /**
             * Returns the raw JSON value of [result].
             *
             * Unlike [result], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("result") @ExcludeMissing fun _result(): JsonField<String> = result

            /**
             * Returns the raw JSON value of [isError].
             *
             * Unlike [isError], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("is_error") @ExcludeMissing fun _isError(): JsonField<Boolean> = isError

            /**
             * Returns the raw JSON value of [signature].
             *
             * Unlike [signature], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("signature")
            @ExcludeMissing
            fun _signature(): JsonField<String> = signature

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
                 * Returns a mutable builder for constructing an instance of [CodeExecutionResult].
                 *
                 * The following fields are required:
                 * ```java
                 * .callId()
                 * .result()
                 * ```
                 */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [CodeExecutionResult]. */
            class Builder internal constructor() {

                private var callId: JsonField<String>? = null
                private var result: JsonField<String>? = null
                private var type: JsonValue = JsonValue.from("code_execution_result")
                private var isError: JsonField<Boolean> = JsonMissing.of()
                private var signature: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(codeExecutionResult: CodeExecutionResult) = apply {
                    callId = codeExecutionResult.callId
                    result = codeExecutionResult.result
                    type = codeExecutionResult.type
                    isError = codeExecutionResult.isError
                    signature = codeExecutionResult.signature
                    additionalProperties = codeExecutionResult.additionalProperties.toMutableMap()
                }

                /** Required. ID to match the ID from the function call block. */
                fun callId(callId: String) = callId(JsonField.of(callId))

                /**
                 * Sets [Builder.callId] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.callId] with a well-typed [String] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun callId(callId: JsonField<String>) = apply { this.callId = callId }

                fun result(result: String) = result(JsonField.of(result))

                /**
                 * Sets [Builder.result] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.result] with a well-typed [String] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun result(result: JsonField<String>) = apply { this.result = result }

                /**
                 * Sets the field to an arbitrary JSON value.
                 *
                 * It is usually unnecessary to call this method because the field defaults to the
                 * following:
                 * ```java
                 * JsonValue.from("code_execution_result")
                 * ```
                 *
                 * This method is primarily for setting the field to an undocumented or not yet
                 * supported value.
                 */
                fun type(type: JsonValue) = apply { this.type = type }

                fun isError(isError: Boolean) = isError(JsonField.of(isError))

                /**
                 * Sets [Builder.isError] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.isError] with a well-typed [Boolean] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun isError(isError: JsonField<Boolean>) = apply { this.isError = isError }

                /** A signature hash for backend validation. */
                fun signature(signature: String) = signature(JsonField.of(signature))

                /**
                 * Sets [Builder.signature] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.signature] with a well-typed [String] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun signature(signature: JsonField<String>) = apply { this.signature = signature }

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
                 * Returns an immutable instance of [CodeExecutionResult].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 *
                 * The following fields are required:
                 * ```java
                 * .callId()
                 * .result()
                 * ```
                 *
                 * @throws IllegalStateException if any required field is unset.
                 */
                fun build(): CodeExecutionResult =
                    CodeExecutionResult(
                        checkRequired("callId", callId),
                        checkRequired("result", result),
                        type,
                        isError,
                        signature,
                        additionalProperties.toMutableMap(),
                    )
            }

            private var validated: Boolean = false

            fun validate(): CodeExecutionResult = apply {
                if (validated) {
                    return@apply
                }

                callId()
                result()
                _type().let {
                    if (it != JsonValue.from("code_execution_result")) {
                        throw GeminiNextGenApiInvalidDataException(
                            "'type' is invalid, received $it"
                        )
                    }
                }
                isError()
                signature()
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
                (if (callId.asKnown().isPresent) 1 else 0) +
                    (if (result.asKnown().isPresent) 1 else 0) +
                    type.let { if (it == JsonValue.from("code_execution_result")) 1 else 0 } +
                    (if (isError.asKnown().isPresent) 1 else 0) +
                    (if (signature.asKnown().isPresent) 1 else 0)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is CodeExecutionResult &&
                    callId == other.callId &&
                    result == other.result &&
                    type == other.type &&
                    isError == other.isError &&
                    signature == other.signature &&
                    additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(callId, result, type, isError, signature, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "CodeExecutionResult{callId=$callId, result=$result, type=$type, isError=$isError, signature=$signature, additionalProperties=$additionalProperties}"
        }

        class UrlContextResult
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val callId: JsonField<String>,
            private val result: JsonField<List<UrlContextResult>>,
            private val type: JsonValue,
            private val isError: JsonField<Boolean>,
            private val signature: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("call_id")
                @ExcludeMissing
                callId: JsonField<String> = JsonMissing.of(),
                @JsonProperty("result")
                @ExcludeMissing
                result: JsonField<List<UrlContextResult>> = JsonMissing.of(),
                @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
                @JsonProperty("is_error")
                @ExcludeMissing
                isError: JsonField<Boolean> = JsonMissing.of(),
                @JsonProperty("signature")
                @ExcludeMissing
                signature: JsonField<String> = JsonMissing.of(),
            ) : this(callId, result, type, isError, signature, mutableMapOf())

            /**
             * Required. ID to match the ID from the function call block.
             *
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   or is unexpectedly missing or null (e.g. if the server responded with an unexpected
             *   value).
             */
            fun callId(): String = callId.getRequired("call_id")

            /**
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   or is unexpectedly missing or null (e.g. if the server responded with an unexpected
             *   value).
             */
            fun result(): List<UrlContextResult> = result.getRequired("result")

            /**
             * Expected to always return the following:
             * ```java
             * JsonValue.from("url_context_result")
             * ```
             *
             * However, this method can be useful for debugging and logging (e.g. if the server
             * responded with an unexpected value).
             */
            @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

            /**
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   (e.g. if the server responded with an unexpected value).
             */
            fun isError(): Optional<Boolean> = isError.getOptional("is_error")

            /**
             * A signature hash for backend validation.
             *
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   (e.g. if the server responded with an unexpected value).
             */
            fun signature(): Optional<String> = signature.getOptional("signature")

            /**
             * Returns the raw JSON value of [callId].
             *
             * Unlike [callId], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("call_id") @ExcludeMissing fun _callId(): JsonField<String> = callId

            /**
             * Returns the raw JSON value of [result].
             *
             * Unlike [result], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("result")
            @ExcludeMissing
            fun _result(): JsonField<List<UrlContextResult>> = result

            /**
             * Returns the raw JSON value of [isError].
             *
             * Unlike [isError], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("is_error") @ExcludeMissing fun _isError(): JsonField<Boolean> = isError

            /**
             * Returns the raw JSON value of [signature].
             *
             * Unlike [signature], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("signature")
            @ExcludeMissing
            fun _signature(): JsonField<String> = signature

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
                 * Returns a mutable builder for constructing an instance of [UrlContextResult].
                 *
                 * The following fields are required:
                 * ```java
                 * .callId()
                 * .result()
                 * ```
                 */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [UrlContextResult]. */
            class Builder internal constructor() {

                private var callId: JsonField<String>? = null
                private var result: JsonField<MutableList<UrlContextResult>>? = null
                private var type: JsonValue = JsonValue.from("url_context_result")
                private var isError: JsonField<Boolean> = JsonMissing.of()
                private var signature: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(urlContextResult: UrlContextResult) = apply {
                    callId = urlContextResult.callId
                    result = urlContextResult.result.map { it.toMutableList() }
                    type = urlContextResult.type
                    isError = urlContextResult.isError
                    signature = urlContextResult.signature
                    additionalProperties = urlContextResult.additionalProperties.toMutableMap()
                }

                /** Required. ID to match the ID from the function call block. */
                fun callId(callId: String) = callId(JsonField.of(callId))

                /**
                 * Sets [Builder.callId] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.callId] with a well-typed [String] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun callId(callId: JsonField<String>) = apply { this.callId = callId }

                fun result(result: List<UrlContextResult>) = result(JsonField.of(result))

                /**
                 * Sets [Builder.result] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.result] with a well-typed
                 * `List<UrlContextResult>` value instead. This method is primarily for setting the
                 * field to an undocumented or not yet supported value.
                 */
                fun result(result: JsonField<List<UrlContextResult>>) = apply {
                    this.result = result.map { it.toMutableList() }
                }

                /**
                 * Adds a single [UrlContextResult] to [Builder.result].
                 *
                 * @throws IllegalStateException if the field was previously set to a non-list.
                 */
                fun addResult(result: UrlContextResult) = apply {
                    this.result =
                        (this.result ?: JsonField.of(mutableListOf())).also {
                            checkKnown("result", it).add(result)
                        }
                }

                /**
                 * Sets the field to an arbitrary JSON value.
                 *
                 * It is usually unnecessary to call this method because the field defaults to the
                 * following:
                 * ```java
                 * JsonValue.from("url_context_result")
                 * ```
                 *
                 * This method is primarily for setting the field to an undocumented or not yet
                 * supported value.
                 */
                fun type(type: JsonValue) = apply { this.type = type }

                fun isError(isError: Boolean) = isError(JsonField.of(isError))

                /**
                 * Sets [Builder.isError] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.isError] with a well-typed [Boolean] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun isError(isError: JsonField<Boolean>) = apply { this.isError = isError }

                /** A signature hash for backend validation. */
                fun signature(signature: String) = signature(JsonField.of(signature))

                /**
                 * Sets [Builder.signature] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.signature] with a well-typed [String] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun signature(signature: JsonField<String>) = apply { this.signature = signature }

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
                 * Returns an immutable instance of [UrlContextResult].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 *
                 * The following fields are required:
                 * ```java
                 * .callId()
                 * .result()
                 * ```
                 *
                 * @throws IllegalStateException if any required field is unset.
                 */
                fun build(): UrlContextResult =
                    UrlContextResult(
                        checkRequired("callId", callId),
                        checkRequired("result", result).map { it.toImmutable() },
                        type,
                        isError,
                        signature,
                        additionalProperties.toMutableMap(),
                    )
            }

            private var validated: Boolean = false

            fun validate(): UrlContextResult = apply {
                if (validated) {
                    return@apply
                }

                callId()
                result().forEach { it.validate() }
                _type().let {
                    if (it != JsonValue.from("url_context_result")) {
                        throw GeminiNextGenApiInvalidDataException(
                            "'type' is invalid, received $it"
                        )
                    }
                }
                isError()
                signature()
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
                (if (callId.asKnown().isPresent) 1 else 0) +
                    (result.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
                    type.let { if (it == JsonValue.from("url_context_result")) 1 else 0 } +
                    (if (isError.asKnown().isPresent) 1 else 0) +
                    (if (signature.asKnown().isPresent) 1 else 0)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is UrlContextResult &&
                    callId == other.callId &&
                    result == other.result &&
                    type == other.type &&
                    isError == other.isError &&
                    signature == other.signature &&
                    additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(callId, result, type, isError, signature, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "UrlContextResult{callId=$callId, result=$result, type=$type, isError=$isError, signature=$signature, additionalProperties=$additionalProperties}"
        }

        class GoogleSearchResult
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val callId: JsonField<String>,
            private val result: JsonField<List<GoogleSearchResult>>,
            private val type: JsonValue,
            private val isError: JsonField<Boolean>,
            private val signature: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("call_id")
                @ExcludeMissing
                callId: JsonField<String> = JsonMissing.of(),
                @JsonProperty("result")
                @ExcludeMissing
                result: JsonField<List<GoogleSearchResult>> = JsonMissing.of(),
                @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
                @JsonProperty("is_error")
                @ExcludeMissing
                isError: JsonField<Boolean> = JsonMissing.of(),
                @JsonProperty("signature")
                @ExcludeMissing
                signature: JsonField<String> = JsonMissing.of(),
            ) : this(callId, result, type, isError, signature, mutableMapOf())

            /**
             * Required. ID to match the ID from the function call block.
             *
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   or is unexpectedly missing or null (e.g. if the server responded with an unexpected
             *   value).
             */
            fun callId(): String = callId.getRequired("call_id")

            /**
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   or is unexpectedly missing or null (e.g. if the server responded with an unexpected
             *   value).
             */
            fun result(): List<GoogleSearchResult> = result.getRequired("result")

            /**
             * Expected to always return the following:
             * ```java
             * JsonValue.from("google_search_result")
             * ```
             *
             * However, this method can be useful for debugging and logging (e.g. if the server
             * responded with an unexpected value).
             */
            @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

            /**
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   (e.g. if the server responded with an unexpected value).
             */
            fun isError(): Optional<Boolean> = isError.getOptional("is_error")

            /**
             * A signature hash for backend validation.
             *
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   (e.g. if the server responded with an unexpected value).
             */
            fun signature(): Optional<String> = signature.getOptional("signature")

            /**
             * Returns the raw JSON value of [callId].
             *
             * Unlike [callId], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("call_id") @ExcludeMissing fun _callId(): JsonField<String> = callId

            /**
             * Returns the raw JSON value of [result].
             *
             * Unlike [result], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("result")
            @ExcludeMissing
            fun _result(): JsonField<List<GoogleSearchResult>> = result

            /**
             * Returns the raw JSON value of [isError].
             *
             * Unlike [isError], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("is_error") @ExcludeMissing fun _isError(): JsonField<Boolean> = isError

            /**
             * Returns the raw JSON value of [signature].
             *
             * Unlike [signature], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("signature")
            @ExcludeMissing
            fun _signature(): JsonField<String> = signature

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
                 * Returns a mutable builder for constructing an instance of [GoogleSearchResult].
                 *
                 * The following fields are required:
                 * ```java
                 * .callId()
                 * .result()
                 * ```
                 */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [GoogleSearchResult]. */
            class Builder internal constructor() {

                private var callId: JsonField<String>? = null
                private var result: JsonField<MutableList<GoogleSearchResult>>? = null
                private var type: JsonValue = JsonValue.from("google_search_result")
                private var isError: JsonField<Boolean> = JsonMissing.of()
                private var signature: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(googleSearchResult: GoogleSearchResult) = apply {
                    callId = googleSearchResult.callId
                    result = googleSearchResult.result.map { it.toMutableList() }
                    type = googleSearchResult.type
                    isError = googleSearchResult.isError
                    signature = googleSearchResult.signature
                    additionalProperties = googleSearchResult.additionalProperties.toMutableMap()
                }

                /** Required. ID to match the ID from the function call block. */
                fun callId(callId: String) = callId(JsonField.of(callId))

                /**
                 * Sets [Builder.callId] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.callId] with a well-typed [String] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun callId(callId: JsonField<String>) = apply { this.callId = callId }

                fun result(result: List<GoogleSearchResult>) = result(JsonField.of(result))

                /**
                 * Sets [Builder.result] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.result] with a well-typed
                 * `List<GoogleSearchResult>` value instead. This method is primarily for setting
                 * the field to an undocumented or not yet supported value.
                 */
                fun result(result: JsonField<List<GoogleSearchResult>>) = apply {
                    this.result = result.map { it.toMutableList() }
                }

                /**
                 * Adds a single [GoogleSearchResult] to [Builder.result].
                 *
                 * @throws IllegalStateException if the field was previously set to a non-list.
                 */
                fun addResult(result: GoogleSearchResult) = apply {
                    this.result =
                        (this.result ?: JsonField.of(mutableListOf())).also {
                            checkKnown("result", it).add(result)
                        }
                }

                /**
                 * Sets the field to an arbitrary JSON value.
                 *
                 * It is usually unnecessary to call this method because the field defaults to the
                 * following:
                 * ```java
                 * JsonValue.from("google_search_result")
                 * ```
                 *
                 * This method is primarily for setting the field to an undocumented or not yet
                 * supported value.
                 */
                fun type(type: JsonValue) = apply { this.type = type }

                fun isError(isError: Boolean) = isError(JsonField.of(isError))

                /**
                 * Sets [Builder.isError] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.isError] with a well-typed [Boolean] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun isError(isError: JsonField<Boolean>) = apply { this.isError = isError }

                /** A signature hash for backend validation. */
                fun signature(signature: String) = signature(JsonField.of(signature))

                /**
                 * Sets [Builder.signature] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.signature] with a well-typed [String] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun signature(signature: JsonField<String>) = apply { this.signature = signature }

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
                 * Returns an immutable instance of [GoogleSearchResult].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 *
                 * The following fields are required:
                 * ```java
                 * .callId()
                 * .result()
                 * ```
                 *
                 * @throws IllegalStateException if any required field is unset.
                 */
                fun build(): GoogleSearchResult =
                    GoogleSearchResult(
                        checkRequired("callId", callId),
                        checkRequired("result", result).map { it.toImmutable() },
                        type,
                        isError,
                        signature,
                        additionalProperties.toMutableMap(),
                    )
            }

            private var validated: Boolean = false

            fun validate(): GoogleSearchResult = apply {
                if (validated) {
                    return@apply
                }

                callId()
                result().forEach { it.validate() }
                _type().let {
                    if (it != JsonValue.from("google_search_result")) {
                        throw GeminiNextGenApiInvalidDataException(
                            "'type' is invalid, received $it"
                        )
                    }
                }
                isError()
                signature()
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
                (if (callId.asKnown().isPresent) 1 else 0) +
                    (result.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
                    type.let { if (it == JsonValue.from("google_search_result")) 1 else 0 } +
                    (if (isError.asKnown().isPresent) 1 else 0) +
                    (if (signature.asKnown().isPresent) 1 else 0)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is GoogleSearchResult &&
                    callId == other.callId &&
                    result == other.result &&
                    type == other.type &&
                    isError == other.isError &&
                    signature == other.signature &&
                    additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(callId, result, type, isError, signature, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "GoogleSearchResult{callId=$callId, result=$result, type=$type, isError=$isError, signature=$signature, additionalProperties=$additionalProperties}"
        }

        class McpServerToolResult
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val callId: JsonField<String>,
            private val result: JsonField<Result>,
            private val type: JsonValue,
            private val name: JsonField<String>,
            private val serverName: JsonField<String>,
            private val signature: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("call_id")
                @ExcludeMissing
                callId: JsonField<String> = JsonMissing.of(),
                @JsonProperty("result")
                @ExcludeMissing
                result: JsonField<Result> = JsonMissing.of(),
                @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
                @JsonProperty("name") @ExcludeMissing name: JsonField<String> = JsonMissing.of(),
                @JsonProperty("server_name")
                @ExcludeMissing
                serverName: JsonField<String> = JsonMissing.of(),
                @JsonProperty("signature")
                @ExcludeMissing
                signature: JsonField<String> = JsonMissing.of(),
            ) : this(callId, result, type, name, serverName, signature, mutableMapOf())

            /**
             * Required. ID to match the ID from the function call block.
             *
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   or is unexpectedly missing or null (e.g. if the server responded with an unexpected
             *   value).
             */
            fun callId(): String = callId.getRequired("call_id")

            /**
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   or is unexpectedly missing or null (e.g. if the server responded with an unexpected
             *   value).
             */
            fun result(): Result = result.getRequired("result")

            /**
             * Expected to always return the following:
             * ```java
             * JsonValue.from("mcp_server_tool_result")
             * ```
             *
             * However, this method can be useful for debugging and logging (e.g. if the server
             * responded with an unexpected value).
             */
            @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

            /**
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   (e.g. if the server responded with an unexpected value).
             */
            fun name(): Optional<String> = name.getOptional("name")

            /**
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   (e.g. if the server responded with an unexpected value).
             */
            fun serverName(): Optional<String> = serverName.getOptional("server_name")

            /**
             * A signature hash for backend validation.
             *
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   (e.g. if the server responded with an unexpected value).
             */
            fun signature(): Optional<String> = signature.getOptional("signature")

            /**
             * Returns the raw JSON value of [callId].
             *
             * Unlike [callId], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("call_id") @ExcludeMissing fun _callId(): JsonField<String> = callId

            /**
             * Returns the raw JSON value of [result].
             *
             * Unlike [result], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("result") @ExcludeMissing fun _result(): JsonField<Result> = result

            /**
             * Returns the raw JSON value of [name].
             *
             * Unlike [name], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("name") @ExcludeMissing fun _name(): JsonField<String> = name

            /**
             * Returns the raw JSON value of [serverName].
             *
             * Unlike [serverName], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("server_name")
            @ExcludeMissing
            fun _serverName(): JsonField<String> = serverName

            /**
             * Returns the raw JSON value of [signature].
             *
             * Unlike [signature], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("signature")
            @ExcludeMissing
            fun _signature(): JsonField<String> = signature

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
                 * Returns a mutable builder for constructing an instance of [McpServerToolResult].
                 *
                 * The following fields are required:
                 * ```java
                 * .callId()
                 * .result()
                 * ```
                 */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [McpServerToolResult]. */
            class Builder internal constructor() {

                private var callId: JsonField<String>? = null
                private var result: JsonField<Result>? = null
                private var type: JsonValue = JsonValue.from("mcp_server_tool_result")
                private var name: JsonField<String> = JsonMissing.of()
                private var serverName: JsonField<String> = JsonMissing.of()
                private var signature: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(mcpServerToolResult: McpServerToolResult) = apply {
                    callId = mcpServerToolResult.callId
                    result = mcpServerToolResult.result
                    type = mcpServerToolResult.type
                    name = mcpServerToolResult.name
                    serverName = mcpServerToolResult.serverName
                    signature = mcpServerToolResult.signature
                    additionalProperties = mcpServerToolResult.additionalProperties.toMutableMap()
                }

                /** Required. ID to match the ID from the function call block. */
                fun callId(callId: String) = callId(JsonField.of(callId))

                /**
                 * Sets [Builder.callId] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.callId] with a well-typed [String] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun callId(callId: JsonField<String>) = apply { this.callId = callId }

                fun result(result: Result) = result(JsonField.of(result))

                /**
                 * Sets [Builder.result] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.result] with a well-typed [Result] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun result(result: JsonField<Result>) = apply { this.result = result }

                /** Alias for calling [result] with `Result.ofJsonValue(jsonValue)`. */
                fun result(jsonValue: JsonValue) = result(Result.ofJsonValue(jsonValue))

                /**
                 * Alias for calling [result] with
                 * `Result.ofFunctionResultSubcontentList(functionResultSubcontentList)`.
                 */
                fun resultOfFunctionResultSubcontentList(
                    functionResultSubcontentList: List<Result.FunctionResultSubcontent>
                ) = result(Result.ofFunctionResultSubcontentList(functionResultSubcontentList))

                /** Alias for calling [result] with `Result.ofString(string)`. */
                fun result(string: String) = result(Result.ofString(string))

                /**
                 * Sets the field to an arbitrary JSON value.
                 *
                 * It is usually unnecessary to call this method because the field defaults to the
                 * following:
                 * ```java
                 * JsonValue.from("mcp_server_tool_result")
                 * ```
                 *
                 * This method is primarily for setting the field to an undocumented or not yet
                 * supported value.
                 */
                fun type(type: JsonValue) = apply { this.type = type }

                fun name(name: String) = name(JsonField.of(name))

                /**
                 * Sets [Builder.name] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.name] with a well-typed [String] value instead.
                 * This method is primarily for setting the field to an undocumented or not yet
                 * supported value.
                 */
                fun name(name: JsonField<String>) = apply { this.name = name }

                fun serverName(serverName: String) = serverName(JsonField.of(serverName))

                /**
                 * Sets [Builder.serverName] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.serverName] with a well-typed [String] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun serverName(serverName: JsonField<String>) = apply {
                    this.serverName = serverName
                }

                /** A signature hash for backend validation. */
                fun signature(signature: String) = signature(JsonField.of(signature))

                /**
                 * Sets [Builder.signature] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.signature] with a well-typed [String] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun signature(signature: JsonField<String>) = apply { this.signature = signature }

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
                 * Returns an immutable instance of [McpServerToolResult].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 *
                 * The following fields are required:
                 * ```java
                 * .callId()
                 * .result()
                 * ```
                 *
                 * @throws IllegalStateException if any required field is unset.
                 */
                fun build(): McpServerToolResult =
                    McpServerToolResult(
                        checkRequired("callId", callId),
                        checkRequired("result", result),
                        type,
                        name,
                        serverName,
                        signature,
                        additionalProperties.toMutableMap(),
                    )
            }

            private var validated: Boolean = false

            fun validate(): McpServerToolResult = apply {
                if (validated) {
                    return@apply
                }

                callId()
                result().validate()
                _type().let {
                    if (it != JsonValue.from("mcp_server_tool_result")) {
                        throw GeminiNextGenApiInvalidDataException(
                            "'type' is invalid, received $it"
                        )
                    }
                }
                name()
                serverName()
                signature()
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
                (if (callId.asKnown().isPresent) 1 else 0) +
                    (result.asKnown().getOrNull()?.validity() ?: 0) +
                    type.let { if (it == JsonValue.from("mcp_server_tool_result")) 1 else 0 } +
                    (if (name.asKnown().isPresent) 1 else 0) +
                    (if (serverName.asKnown().isPresent) 1 else 0) +
                    (if (signature.asKnown().isPresent) 1 else 0)

            @JsonDeserialize(using = Result.Deserializer::class)
            @JsonSerialize(using = Result.Serializer::class)
            class Result
            private constructor(
                private val jsonValue: JsonValue? = null,
                private val functionResultSubcontentList: List<FunctionResultSubcontent>? = null,
                private val string: String? = null,
                private val _json: JsonValue? = null,
            ) {

                fun jsonValue(): Optional<JsonValue> = Optional.ofNullable(jsonValue)

                fun functionResultSubcontentList(): Optional<List<FunctionResultSubcontent>> =
                    Optional.ofNullable(functionResultSubcontentList)

                fun string(): Optional<String> = Optional.ofNullable(string)

                fun isJsonValue(): Boolean = jsonValue != null

                fun isFunctionResultSubcontentList(): Boolean = functionResultSubcontentList != null

                fun isString(): Boolean = string != null

                fun asJsonValue(): JsonValue = jsonValue.getOrThrow("jsonValue")

                fun asFunctionResultSubcontentList(): List<FunctionResultSubcontent> =
                    functionResultSubcontentList.getOrThrow("functionResultSubcontentList")

                fun asString(): String = string.getOrThrow("string")

                fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

                fun <T> accept(visitor: Visitor<T>): T =
                    when {
                        jsonValue != null -> visitor.visitJsonValue(jsonValue)
                        functionResultSubcontentList != null ->
                            visitor.visitFunctionResultSubcontentList(functionResultSubcontentList)
                        string != null -> visitor.visitString(string)
                        else -> visitor.unknown(_json)
                    }

                private var validated: Boolean = false

                fun validate(): Result = apply {
                    if (validated) {
                        return@apply
                    }

                    accept(
                        object : Visitor<Unit> {
                            override fun visitJsonValue(jsonValue: JsonValue) {}

                            override fun visitFunctionResultSubcontentList(
                                functionResultSubcontentList: List<FunctionResultSubcontent>
                            ) {
                                functionResultSubcontentList.forEach { it.validate() }
                            }

                            override fun visitString(string: String) {}
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
                            override fun visitJsonValue(jsonValue: JsonValue) = 1

                            override fun visitFunctionResultSubcontentList(
                                functionResultSubcontentList: List<FunctionResultSubcontent>
                            ) = functionResultSubcontentList.sumOf { it.validity().toInt() }

                            override fun visitString(string: String) = 1

                            override fun unknown(json: JsonValue?) = 0
                        }
                    )

                override fun equals(other: Any?): Boolean {
                    if (this === other) {
                        return true
                    }

                    return other is Result &&
                        jsonValue == other.jsonValue &&
                        functionResultSubcontentList == other.functionResultSubcontentList &&
                        string == other.string
                }

                override fun hashCode(): Int =
                    Objects.hash(jsonValue, functionResultSubcontentList, string)

                override fun toString(): String =
                    when {
                        jsonValue != null -> "Result{jsonValue=$jsonValue}"
                        functionResultSubcontentList != null ->
                            "Result{functionResultSubcontentList=$functionResultSubcontentList}"
                        string != null -> "Result{string=$string}"
                        _json != null -> "Result{_unknown=$_json}"
                        else -> throw IllegalStateException("Invalid Result")
                    }

                companion object {

                    @JvmStatic fun ofJsonValue(jsonValue: JsonValue) = Result(jsonValue = jsonValue)

                    @JvmStatic
                    fun ofFunctionResultSubcontentList(
                        functionResultSubcontentList: List<FunctionResultSubcontent>
                    ) =
                        Result(
                            functionResultSubcontentList =
                                functionResultSubcontentList.toImmutable()
                        )

                    @JvmStatic fun ofString(string: String) = Result(string = string)
                }

                /**
                 * An interface that defines how to map each variant of [Result] to a value of type
                 * [T].
                 */
                interface Visitor<out T> {

                    fun visitJsonValue(jsonValue: JsonValue): T

                    fun visitFunctionResultSubcontentList(
                        functionResultSubcontentList: List<FunctionResultSubcontent>
                    ): T

                    fun visitString(string: String): T

                    /**
                     * Maps an unknown variant of [Result] to a value of type [T].
                     *
                     * An instance of [Result] can contain an unknown variant if it was deserialized
                     * from data that doesn't match any known variant. For example, if the SDK is on
                     * an older version than the API, then the API may respond with new variants
                     * that the SDK is unaware of.
                     *
                     * @throws GeminiNextGenApiInvalidDataException in the default implementation.
                     */
                    fun unknown(json: JsonValue?): T {
                        throw GeminiNextGenApiInvalidDataException("Unknown Result: $json")
                    }
                }

                internal class Deserializer : BaseDeserializer<Result>(Result::class) {

                    override fun ObjectCodec.deserialize(node: JsonNode): Result {
                        val json = JsonValue.fromJsonNode(node)

                        val bestMatches =
                            sequenceOf(
                                    tryDeserialize(node, jacksonTypeRef<String>())?.let {
                                        Result(string = it, _json = json)
                                    },
                                    tryDeserialize(
                                            node,
                                            jacksonTypeRef<List<FunctionResultSubcontent>>(),
                                        )
                                        ?.let {
                                            Result(functionResultSubcontentList = it, _json = json)
                                        },
                                    tryDeserialize(node, jacksonTypeRef<JsonValue>())?.let {
                                        Result(jsonValue = it, _json = json)
                                    },
                                )
                                .filterNotNull()
                                .allMaxBy { it.validity() }
                                .toList()
                        return when (bestMatches.size) {
                            // This can happen if what we're deserializing is completely
                            // incompatible with all the possible variants.
                            0 -> Result(_json = json)
                            1 -> bestMatches.single()
                            // If there's more than one match with the highest validity, then use
                            // the first completely valid match, or simply the first match if none
                            // are completely valid.
                            else -> bestMatches.firstOrNull { it.isValid() } ?: bestMatches.first()
                        }
                    }
                }

                internal class Serializer : BaseSerializer<Result>(Result::class) {

                    override fun serialize(
                        value: Result,
                        generator: JsonGenerator,
                        provider: SerializerProvider,
                    ) {
                        when {
                            value.jsonValue != null -> generator.writeObject(value.jsonValue)
                            value.functionResultSubcontentList != null ->
                                generator.writeObject(value.functionResultSubcontentList)
                            value.string != null -> generator.writeObject(value.string)
                            value._json != null -> generator.writeObject(value._json)
                            else -> throw IllegalStateException("Invalid Result")
                        }
                    }
                }

                /** A text content block. */
                @JsonDeserialize(using = FunctionResultSubcontent.Deserializer::class)
                @JsonSerialize(using = FunctionResultSubcontent.Serializer::class)
                class FunctionResultSubcontent
                private constructor(
                    private val text: TextContent? = null,
                    private val image: ImageContent? = null,
                    private val _json: JsonValue? = null,
                ) {

                    /** A text content block. */
                    fun text(): Optional<TextContent> = Optional.ofNullable(text)

                    /** An image content block. */
                    fun image(): Optional<ImageContent> = Optional.ofNullable(image)

                    fun isText(): Boolean = text != null

                    fun isImage(): Boolean = image != null

                    /** A text content block. */
                    fun asText(): TextContent = text.getOrThrow("text")

                    /** An image content block. */
                    fun asImage(): ImageContent = image.getOrThrow("image")

                    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

                    fun <T> accept(visitor: Visitor<T>): T =
                        when {
                            text != null -> visitor.visitText(text)
                            image != null -> visitor.visitImage(image)
                            else -> visitor.unknown(_json)
                        }

                    private var validated: Boolean = false

                    fun validate(): FunctionResultSubcontent = apply {
                        if (validated) {
                            return@apply
                        }

                        accept(
                            object : Visitor<Unit> {
                                override fun visitText(text: TextContent) {
                                    text.validate()
                                }

                                override fun visitImage(image: ImageContent) {
                                    image.validate()
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
                                override fun visitText(text: TextContent) = text.validity()

                                override fun visitImage(image: ImageContent) = image.validity()

                                override fun unknown(json: JsonValue?) = 0
                            }
                        )

                    override fun equals(other: Any?): Boolean {
                        if (this === other) {
                            return true
                        }

                        return other is FunctionResultSubcontent &&
                            text == other.text &&
                            image == other.image
                    }

                    override fun hashCode(): Int = Objects.hash(text, image)

                    override fun toString(): String =
                        when {
                            text != null -> "FunctionResultSubcontent{text=$text}"
                            image != null -> "FunctionResultSubcontent{image=$image}"
                            _json != null -> "FunctionResultSubcontent{_unknown=$_json}"
                            else -> throw IllegalStateException("Invalid FunctionResultSubcontent")
                        }

                    companion object {

                        /** A text content block. */
                        @JvmStatic
                        fun ofText(text: TextContent) = FunctionResultSubcontent(text = text)

                        /** An image content block. */
                        @JvmStatic
                        fun ofImage(image: ImageContent) = FunctionResultSubcontent(image = image)
                    }

                    /**
                     * An interface that defines how to map each variant of
                     * [FunctionResultSubcontent] to a value of type [T].
                     */
                    interface Visitor<out T> {

                        /** A text content block. */
                        fun visitText(text: TextContent): T

                        /** An image content block. */
                        fun visitImage(image: ImageContent): T

                        /**
                         * Maps an unknown variant of [FunctionResultSubcontent] to a value of type
                         * [T].
                         *
                         * An instance of [FunctionResultSubcontent] can contain an unknown variant
                         * if it was deserialized from data that doesn't match any known variant.
                         * For example, if the SDK is on an older version than the API, then the API
                         * may respond with new variants that the SDK is unaware of.
                         *
                         * @throws GeminiNextGenApiInvalidDataException in the default
                         *   implementation.
                         */
                        fun unknown(json: JsonValue?): T {
                            throw GeminiNextGenApiInvalidDataException(
                                "Unknown FunctionResultSubcontent: $json"
                            )
                        }
                    }

                    internal class Deserializer :
                        BaseDeserializer<FunctionResultSubcontent>(
                            FunctionResultSubcontent::class
                        ) {

                        override fun ObjectCodec.deserialize(
                            node: JsonNode
                        ): FunctionResultSubcontent {
                            val json = JsonValue.fromJsonNode(node)
                            val type =
                                json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                            when (type) {
                                "text" -> {
                                    return tryDeserialize(node, jacksonTypeRef<TextContent>())
                                        ?.let { FunctionResultSubcontent(text = it, _json = json) }
                                        ?: FunctionResultSubcontent(_json = json)
                                }
                                "image" -> {
                                    return tryDeserialize(node, jacksonTypeRef<ImageContent>())
                                        ?.let { FunctionResultSubcontent(image = it, _json = json) }
                                        ?: FunctionResultSubcontent(_json = json)
                                }
                            }

                            return FunctionResultSubcontent(_json = json)
                        }
                    }

                    internal class Serializer :
                        BaseSerializer<FunctionResultSubcontent>(FunctionResultSubcontent::class) {

                        override fun serialize(
                            value: FunctionResultSubcontent,
                            generator: JsonGenerator,
                            provider: SerializerProvider,
                        ) {
                            when {
                                value.text != null -> generator.writeObject(value.text)
                                value.image != null -> generator.writeObject(value.image)
                                value._json != null -> generator.writeObject(value._json)
                                else ->
                                    throw IllegalStateException("Invalid FunctionResultSubcontent")
                            }
                        }
                    }
                }
            }

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is McpServerToolResult &&
                    callId == other.callId &&
                    result == other.result &&
                    type == other.type &&
                    name == other.name &&
                    serverName == other.serverName &&
                    signature == other.signature &&
                    additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(
                    callId,
                    result,
                    type,
                    name,
                    serverName,
                    signature,
                    additionalProperties,
                )
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "McpServerToolResult{callId=$callId, result=$result, type=$type, name=$name, serverName=$serverName, signature=$signature, additionalProperties=$additionalProperties}"
        }

        class FileSearchResult
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val callId: JsonField<String>,
            private val result: JsonField<List<JsonValue>>,
            private val type: JsonValue,
            private val signature: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("call_id")
                @ExcludeMissing
                callId: JsonField<String> = JsonMissing.of(),
                @JsonProperty("result")
                @ExcludeMissing
                result: JsonField<List<JsonValue>> = JsonMissing.of(),
                @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
                @JsonProperty("signature")
                @ExcludeMissing
                signature: JsonField<String> = JsonMissing.of(),
            ) : this(callId, result, type, signature, mutableMapOf())

            /**
             * Required. ID to match the ID from the function call block.
             *
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   or is unexpectedly missing or null (e.g. if the server responded with an unexpected
             *   value).
             */
            fun callId(): String = callId.getRequired("call_id")

            /**
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   or is unexpectedly missing or null (e.g. if the server responded with an unexpected
             *   value).
             */
            fun result(): List<JsonValue> = result.getRequired("result")

            /**
             * Expected to always return the following:
             * ```java
             * JsonValue.from("file_search_result")
             * ```
             *
             * However, this method can be useful for debugging and logging (e.g. if the server
             * responded with an unexpected value).
             */
            @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

            /**
             * A signature hash for backend validation.
             *
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   (e.g. if the server responded with an unexpected value).
             */
            fun signature(): Optional<String> = signature.getOptional("signature")

            /**
             * Returns the raw JSON value of [callId].
             *
             * Unlike [callId], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("call_id") @ExcludeMissing fun _callId(): JsonField<String> = callId

            /**
             * Returns the raw JSON value of [result].
             *
             * Unlike [result], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("result")
            @ExcludeMissing
            fun _result(): JsonField<List<JsonValue>> = result

            /**
             * Returns the raw JSON value of [signature].
             *
             * Unlike [signature], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("signature")
            @ExcludeMissing
            fun _signature(): JsonField<String> = signature

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
                 * Returns a mutable builder for constructing an instance of [FileSearchResult].
                 *
                 * The following fields are required:
                 * ```java
                 * .callId()
                 * .result()
                 * ```
                 */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [FileSearchResult]. */
            class Builder internal constructor() {

                private var callId: JsonField<String>? = null
                private var result: JsonField<MutableList<JsonValue>>? = null
                private var type: JsonValue = JsonValue.from("file_search_result")
                private var signature: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(fileSearchResult: FileSearchResult) = apply {
                    callId = fileSearchResult.callId
                    result = fileSearchResult.result.map { it.toMutableList() }
                    type = fileSearchResult.type
                    signature = fileSearchResult.signature
                    additionalProperties = fileSearchResult.additionalProperties.toMutableMap()
                }

                /** Required. ID to match the ID from the function call block. */
                fun callId(callId: String) = callId(JsonField.of(callId))

                /**
                 * Sets [Builder.callId] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.callId] with a well-typed [String] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun callId(callId: JsonField<String>) = apply { this.callId = callId }

                fun result(result: List<JsonValue>) = result(JsonField.of(result))

                /**
                 * Sets [Builder.result] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.result] with a well-typed `List<JsonValue>`
                 * value instead. This method is primarily for setting the field to an undocumented
                 * or not yet supported value.
                 */
                fun result(result: JsonField<List<JsonValue>>) = apply {
                    this.result = result.map { it.toMutableList() }
                }

                /**
                 * Adds a single [JsonValue] to [Builder.result].
                 *
                 * @throws IllegalStateException if the field was previously set to a non-list.
                 */
                fun addResult(result: JsonValue) = apply {
                    this.result =
                        (this.result ?: JsonField.of(mutableListOf())).also {
                            checkKnown("result", it).add(result)
                        }
                }

                /**
                 * Sets the field to an arbitrary JSON value.
                 *
                 * It is usually unnecessary to call this method because the field defaults to the
                 * following:
                 * ```java
                 * JsonValue.from("file_search_result")
                 * ```
                 *
                 * This method is primarily for setting the field to an undocumented or not yet
                 * supported value.
                 */
                fun type(type: JsonValue) = apply { this.type = type }

                /** A signature hash for backend validation. */
                fun signature(signature: String) = signature(JsonField.of(signature))

                /**
                 * Sets [Builder.signature] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.signature] with a well-typed [String] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun signature(signature: JsonField<String>) = apply { this.signature = signature }

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
                 * Returns an immutable instance of [FileSearchResult].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 *
                 * The following fields are required:
                 * ```java
                 * .callId()
                 * .result()
                 * ```
                 *
                 * @throws IllegalStateException if any required field is unset.
                 */
                fun build(): FileSearchResult =
                    FileSearchResult(
                        checkRequired("callId", callId),
                        checkRequired("result", result).map { it.toImmutable() },
                        type,
                        signature,
                        additionalProperties.toMutableMap(),
                    )
            }

            private var validated: Boolean = false

            fun validate(): FileSearchResult = apply {
                if (validated) {
                    return@apply
                }

                callId()
                result()
                _type().let {
                    if (it != JsonValue.from("file_search_result")) {
                        throw GeminiNextGenApiInvalidDataException(
                            "'type' is invalid, received $it"
                        )
                    }
                }
                signature()
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
                (if (callId.asKnown().isPresent) 1 else 0) +
                    (result.asKnown().getOrNull()?.size ?: 0) +
                    type.let { if (it == JsonValue.from("file_search_result")) 1 else 0 } +
                    (if (signature.asKnown().isPresent) 1 else 0)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is FileSearchResult &&
                    callId == other.callId &&
                    result == other.result &&
                    type == other.type &&
                    signature == other.signature &&
                    additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(callId, result, type, signature, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "FileSearchResult{callId=$callId, result=$result, type=$type, signature=$signature, additionalProperties=$additionalProperties}"
        }

        class GoogleMapsResult
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val callId: JsonField<String>,
            private val type: JsonValue,
            private val result: JsonField<List<GoogleMapsResult>>,
            private val signature: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("call_id")
                @ExcludeMissing
                callId: JsonField<String> = JsonMissing.of(),
                @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
                @JsonProperty("result")
                @ExcludeMissing
                result: JsonField<List<GoogleMapsResult>> = JsonMissing.of(),
                @JsonProperty("signature")
                @ExcludeMissing
                signature: JsonField<String> = JsonMissing.of(),
            ) : this(callId, type, result, signature, mutableMapOf())

            /**
             * Required. ID to match the ID from the function call block.
             *
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   or is unexpectedly missing or null (e.g. if the server responded with an unexpected
             *   value).
             */
            fun callId(): String = callId.getRequired("call_id")

            /**
             * Expected to always return the following:
             * ```java
             * JsonValue.from("google_maps_result")
             * ```
             *
             * However, this method can be useful for debugging and logging (e.g. if the server
             * responded with an unexpected value).
             */
            @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

            /**
             * The results of the Google Maps.
             *
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   (e.g. if the server responded with an unexpected value).
             */
            fun result(): Optional<List<GoogleMapsResult>> = result.getOptional("result")

            /**
             * A signature hash for backend validation.
             *
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   (e.g. if the server responded with an unexpected value).
             */
            fun signature(): Optional<String> = signature.getOptional("signature")

            /**
             * Returns the raw JSON value of [callId].
             *
             * Unlike [callId], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("call_id") @ExcludeMissing fun _callId(): JsonField<String> = callId

            /**
             * Returns the raw JSON value of [result].
             *
             * Unlike [result], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("result")
            @ExcludeMissing
            fun _result(): JsonField<List<GoogleMapsResult>> = result

            /**
             * Returns the raw JSON value of [signature].
             *
             * Unlike [signature], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("signature")
            @ExcludeMissing
            fun _signature(): JsonField<String> = signature

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
                 * Returns a mutable builder for constructing an instance of [GoogleMapsResult].
                 *
                 * The following fields are required:
                 * ```java
                 * .callId()
                 * ```
                 */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [GoogleMapsResult]. */
            class Builder internal constructor() {

                private var callId: JsonField<String>? = null
                private var type: JsonValue = JsonValue.from("google_maps_result")
                private var result: JsonField<MutableList<GoogleMapsResult>>? = null
                private var signature: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(googleMapsResult: GoogleMapsResult) = apply {
                    callId = googleMapsResult.callId
                    type = googleMapsResult.type
                    result = googleMapsResult.result.map { it.toMutableList() }
                    signature = googleMapsResult.signature
                    additionalProperties = googleMapsResult.additionalProperties.toMutableMap()
                }

                /** Required. ID to match the ID from the function call block. */
                fun callId(callId: String) = callId(JsonField.of(callId))

                /**
                 * Sets [Builder.callId] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.callId] with a well-typed [String] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun callId(callId: JsonField<String>) = apply { this.callId = callId }

                /**
                 * Sets the field to an arbitrary JSON value.
                 *
                 * It is usually unnecessary to call this method because the field defaults to the
                 * following:
                 * ```java
                 * JsonValue.from("google_maps_result")
                 * ```
                 *
                 * This method is primarily for setting the field to an undocumented or not yet
                 * supported value.
                 */
                fun type(type: JsonValue) = apply { this.type = type }

                /** The results of the Google Maps. */
                fun result(result: List<GoogleMapsResult>) = result(JsonField.of(result))

                /**
                 * Sets [Builder.result] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.result] with a well-typed
                 * `List<GoogleMapsResult>` value instead. This method is primarily for setting the
                 * field to an undocumented or not yet supported value.
                 */
                fun result(result: JsonField<List<GoogleMapsResult>>) = apply {
                    this.result = result.map { it.toMutableList() }
                }

                /**
                 * Adds a single [GoogleMapsResult] to [Builder.result].
                 *
                 * @throws IllegalStateException if the field was previously set to a non-list.
                 */
                fun addResult(result: GoogleMapsResult) = apply {
                    this.result =
                        (this.result ?: JsonField.of(mutableListOf())).also {
                            checkKnown("result", it).add(result)
                        }
                }

                /** A signature hash for backend validation. */
                fun signature(signature: String) = signature(JsonField.of(signature))

                /**
                 * Sets [Builder.signature] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.signature] with a well-typed [String] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun signature(signature: JsonField<String>) = apply { this.signature = signature }

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
                 * Returns an immutable instance of [GoogleMapsResult].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 *
                 * The following fields are required:
                 * ```java
                 * .callId()
                 * ```
                 *
                 * @throws IllegalStateException if any required field is unset.
                 */
                fun build(): GoogleMapsResult =
                    GoogleMapsResult(
                        checkRequired("callId", callId),
                        type,
                        (result ?: JsonMissing.of()).map { it.toImmutable() },
                        signature,
                        additionalProperties.toMutableMap(),
                    )
            }

            private var validated: Boolean = false

            fun validate(): GoogleMapsResult = apply {
                if (validated) {
                    return@apply
                }

                callId()
                _type().let {
                    if (it != JsonValue.from("google_maps_result")) {
                        throw GeminiNextGenApiInvalidDataException(
                            "'type' is invalid, received $it"
                        )
                    }
                }
                result().ifPresent { it.forEach { it.validate() } }
                signature()
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
                (if (callId.asKnown().isPresent) 1 else 0) +
                    type.let { if (it == JsonValue.from("google_maps_result")) 1 else 0 } +
                    (result.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
                    (if (signature.asKnown().isPresent) 1 else 0)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is GoogleMapsResult &&
                    callId == other.callId &&
                    type == other.type &&
                    result == other.result &&
                    signature == other.signature &&
                    additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(callId, type, result, signature, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "GoogleMapsResult{callId=$callId, type=$type, result=$result, signature=$signature, additionalProperties=$additionalProperties}"
        }

        class TextAnnotation
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val type: JsonValue,
            private val annotations: JsonField<List<Annotation>>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
                @JsonProperty("annotations")
                @ExcludeMissing
                annotations: JsonField<List<Annotation>> = JsonMissing.of(),
            ) : this(type, annotations, mutableMapOf())

            /**
             * Expected to always return the following:
             * ```java
             * JsonValue.from("text_annotation")
             * ```
             *
             * However, this method can be useful for debugging and logging (e.g. if the server
             * responded with an unexpected value).
             */
            @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

            /**
             * Citation information for model-generated content.
             *
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   (e.g. if the server responded with an unexpected value).
             */
            fun annotations(): Optional<List<Annotation>> = annotations.getOptional("annotations")

            /**
             * Returns the raw JSON value of [annotations].
             *
             * Unlike [annotations], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("annotations")
            @ExcludeMissing
            fun _annotations(): JsonField<List<Annotation>> = annotations

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

                /** Returns a mutable builder for constructing an instance of [TextAnnotation]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [TextAnnotation]. */
            class Builder internal constructor() {

                private var type: JsonValue = JsonValue.from("text_annotation")
                private var annotations: JsonField<MutableList<Annotation>>? = null
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(textAnnotation: TextAnnotation) = apply {
                    type = textAnnotation.type
                    annotations = textAnnotation.annotations.map { it.toMutableList() }
                    additionalProperties = textAnnotation.additionalProperties.toMutableMap()
                }

                /**
                 * Sets the field to an arbitrary JSON value.
                 *
                 * It is usually unnecessary to call this method because the field defaults to the
                 * following:
                 * ```java
                 * JsonValue.from("text_annotation")
                 * ```
                 *
                 * This method is primarily for setting the field to an undocumented or not yet
                 * supported value.
                 */
                fun type(type: JsonValue) = apply { this.type = type }

                /** Citation information for model-generated content. */
                fun annotations(annotations: List<Annotation>) =
                    annotations(JsonField.of(annotations))

                /**
                 * Sets [Builder.annotations] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.annotations] with a well-typed
                 * `List<Annotation>` value instead. This method is primarily for setting the field
                 * to an undocumented or not yet supported value.
                 */
                fun annotations(annotations: JsonField<List<Annotation>>) = apply {
                    this.annotations = annotations.map { it.toMutableList() }
                }

                /**
                 * Adds a single [Annotation] to [annotations].
                 *
                 * @throws IllegalStateException if the field was previously set to a non-list.
                 */
                fun addAnnotation(annotation: Annotation) = apply {
                    annotations =
                        (annotations ?: JsonField.of(mutableListOf())).also {
                            checkKnown("annotations", it).add(annotation)
                        }
                }

                /**
                 * Alias for calling [addAnnotation] with `Annotation.ofUrlCitation(urlCitation)`.
                 */
                fun addAnnotation(urlCitation: UrlCitation) =
                    addAnnotation(Annotation.ofUrlCitation(urlCitation))

                /**
                 * Alias for calling [addAnnotation] with `Annotation.ofFileCitation(fileCitation)`.
                 */
                fun addAnnotation(fileCitation: FileCitation) =
                    addAnnotation(Annotation.ofFileCitation(fileCitation))

                /**
                 * Alias for calling [addAnnotation] with
                 * `Annotation.ofPlaceCitation(placeCitation)`.
                 */
                fun addAnnotation(placeCitation: PlaceCitation) =
                    addAnnotation(Annotation.ofPlaceCitation(placeCitation))

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
                 * Returns an immutable instance of [TextAnnotation].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 */
                fun build(): TextAnnotation =
                    TextAnnotation(
                        type,
                        (annotations ?: JsonMissing.of()).map { it.toImmutable() },
                        additionalProperties.toMutableMap(),
                    )
            }

            private var validated: Boolean = false

            fun validate(): TextAnnotation = apply {
                if (validated) {
                    return@apply
                }

                _type().let {
                    if (it != JsonValue.from("text_annotation")) {
                        throw GeminiNextGenApiInvalidDataException(
                            "'type' is invalid, received $it"
                        )
                    }
                }
                annotations().ifPresent { it.forEach { it.validate() } }
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
                type.let { if (it == JsonValue.from("text_annotation")) 1 else 0 } +
                    (annotations.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is TextAnnotation &&
                    type == other.type &&
                    annotations == other.annotations &&
                    additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(type, annotations, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "TextAnnotation{type=$type, annotations=$annotations, additionalProperties=$additionalProperties}"
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ContentDelta &&
            delta == other.delta &&
            eventType == other.eventType &&
            index == other.index &&
            eventId == other.eventId &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(delta, eventType, index, eventId, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "ContentDelta{delta=$delta, eventType=$eventType, index=$index, eventId=$eventId, additionalProperties=$additionalProperties}"
}
