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
import com.google.genai.interactions.core.ExcludeMissing
import com.google.genai.interactions.core.JsonField
import com.google.genai.interactions.core.JsonMissing
import com.google.genai.interactions.core.JsonValue
import com.google.genai.interactions.core.checkRequired
import com.google.genai.interactions.errors.GeminiNextGenApiInvalidDataException
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

class ContentStart
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val content: JsonField<Content>,
    private val eventType: JsonValue,
    private val index: JsonField<Int>,
    private val eventId: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("content") @ExcludeMissing content: JsonField<Content> = JsonMissing.of(),
        @JsonProperty("event_type") @ExcludeMissing eventType: JsonValue = JsonMissing.of(),
        @JsonProperty("index") @ExcludeMissing index: JsonField<Int> = JsonMissing.of(),
        @JsonProperty("event_id") @ExcludeMissing eventId: JsonField<String> = JsonMissing.of(),
    ) : this(content, eventType, index, eventId, mutableMapOf())

    /**
     * The content of the response.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun content(): Content = content.getRequired("content")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("content.start")
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
     * Returns the raw JSON value of [content].
     *
     * Unlike [content], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("content") @ExcludeMissing fun _content(): JsonField<Content> = content

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
         * Returns a mutable builder for constructing an instance of [ContentStart].
         *
         * The following fields are required:
         * ```java
         * .content()
         * .index()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [ContentStart]. */
    class Builder internal constructor() {

        private var content: JsonField<Content>? = null
        private var eventType: JsonValue = JsonValue.from("content.start")
        private var index: JsonField<Int>? = null
        private var eventId: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(contentStart: ContentStart) = apply {
            content = contentStart.content
            eventType = contentStart.eventType
            index = contentStart.index
            eventId = contentStart.eventId
            additionalProperties = contentStart.additionalProperties.toMutableMap()
        }

        /** The content of the response. */
        fun content(content: Content) = content(JsonField.of(content))

        /**
         * Sets [Builder.content] to an arbitrary JSON value.
         *
         * You should usually call [Builder.content] with a well-typed [Content] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
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

        /** Alias for calling [content] with `Content.ofAudio(audio)`. */
        fun content(audio: AudioContent) = content(Content.ofAudio(audio))

        /** Alias for calling [content] with `Content.ofDocument(document)`. */
        fun content(document: DocumentContent) = content(Content.ofDocument(document))

        /** Alias for calling [content] with `Content.ofVideo(video)`. */
        fun content(video: VideoContent) = content(Content.ofVideo(video))

        /** Alias for calling [content] with `Content.ofThought(thought)`. */
        fun content(thought: ThoughtContent) = content(Content.ofThought(thought))

        /** Alias for calling [content] with `Content.ofFunctionCall(functionCall)`. */
        fun content(functionCall: FunctionCallContent) =
            content(Content.ofFunctionCall(functionCall))

        /** Alias for calling [content] with `Content.ofCodeExecutionCall(codeExecutionCall)`. */
        fun content(codeExecutionCall: CodeExecutionCallContent) =
            content(Content.ofCodeExecutionCall(codeExecutionCall))

        /** Alias for calling [content] with `Content.ofUrlContextCall(urlContextCall)`. */
        fun content(urlContextCall: UrlContextCallContent) =
            content(Content.ofUrlContextCall(urlContextCall))

        /** Alias for calling [content] with `Content.ofMcpServerToolCall(mcpServerToolCall)`. */
        fun content(mcpServerToolCall: McpServerToolCallContent) =
            content(Content.ofMcpServerToolCall(mcpServerToolCall))

        /** Alias for calling [content] with `Content.ofGoogleSearchCall(googleSearchCall)`. */
        fun content(googleSearchCall: GoogleSearchCallContent) =
            content(Content.ofGoogleSearchCall(googleSearchCall))

        /** Alias for calling [content] with `Content.ofFileSearchCall(fileSearchCall)`. */
        fun content(fileSearchCall: FileSearchCallContent) =
            content(Content.ofFileSearchCall(fileSearchCall))

        /**
         * Alias for calling [content] with the following:
         * ```java
         * FileSearchCallContent.builder()
         *     .id(id)
         *     .build()
         * ```
         */
        fun fileSearchCallContent(id: String) =
            content(FileSearchCallContent.builder().id(id).build())

        /** Alias for calling [content] with `Content.ofGoogleMapsCall(googleMapsCall)`. */
        fun content(googleMapsCall: GoogleMapsCallContent) =
            content(Content.ofGoogleMapsCall(googleMapsCall))

        /**
         * Alias for calling [content] with the following:
         * ```java
         * GoogleMapsCallContent.builder()
         *     .id(id)
         *     .build()
         * ```
         */
        fun googleMapsCallContent(id: String) =
            content(GoogleMapsCallContent.builder().id(id).build())

        /** Alias for calling [content] with `Content.ofFunctionResult(functionResult)`. */
        fun content(functionResult: FunctionResultContent) =
            content(Content.ofFunctionResult(functionResult))

        /**
         * Alias for calling [content] with `Content.ofCodeExecutionResult(codeExecutionResult)`.
         */
        fun content(codeExecutionResult: CodeExecutionResultContent) =
            content(Content.ofCodeExecutionResult(codeExecutionResult))

        /** Alias for calling [content] with `Content.ofUrlContextResult(urlContextResult)`. */
        fun content(urlContextResult: UrlContextResultContent) =
            content(Content.ofUrlContextResult(urlContextResult))

        /** Alias for calling [content] with `Content.ofGoogleSearchResult(googleSearchResult)`. */
        fun content(googleSearchResult: GoogleSearchResultContent) =
            content(Content.ofGoogleSearchResult(googleSearchResult))

        /**
         * Alias for calling [content] with `Content.ofMcpServerToolResult(mcpServerToolResult)`.
         */
        fun content(mcpServerToolResult: McpServerToolResultContent) =
            content(Content.ofMcpServerToolResult(mcpServerToolResult))

        /** Alias for calling [content] with `Content.ofFileSearchResult(fileSearchResult)`. */
        fun content(fileSearchResult: FileSearchResultContent) =
            content(Content.ofFileSearchResult(fileSearchResult))

        /** Alias for calling [content] with `Content.ofGoogleMapsResult(googleMapsResult)`. */
        fun content(googleMapsResult: GoogleMapsResultContent) =
            content(Content.ofGoogleMapsResult(googleMapsResult))

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("content.start")
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
         * Returns an immutable instance of [ContentStart].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .content()
         * .index()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): ContentStart =
            ContentStart(
                checkRequired("content", content),
                eventType,
                checkRequired("index", index),
                eventId,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): ContentStart = apply {
        if (validated) {
            return@apply
        }

        content().validate()
        _eventType().let {
            if (it != JsonValue.from("content.start")) {
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
        (content.asKnown().getOrNull()?.validity() ?: 0) +
            eventType.let { if (it == JsonValue.from("content.start")) 1 else 0 } +
            (if (index.asKnown().isPresent) 1 else 0) +
            (if (eventId.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ContentStart &&
            content == other.content &&
            eventType == other.eventType &&
            index == other.index &&
            eventId == other.eventId &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(content, eventType, index, eventId, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "ContentStart{content=$content, eventType=$eventType, index=$index, eventId=$eventId, additionalProperties=$additionalProperties}"
}
