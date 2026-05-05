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

class StepStart
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val eventType: JsonValue,
    private val index: JsonField<Int>,
    private val step: JsonField<Step>,
    private val eventId: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("event_type") @ExcludeMissing eventType: JsonValue = JsonMissing.of(),
        @JsonProperty("index") @ExcludeMissing index: JsonField<Int> = JsonMissing.of(),
        @JsonProperty("step") @ExcludeMissing step: JsonField<Step> = JsonMissing.of(),
        @JsonProperty("event_id") @ExcludeMissing eventId: JsonField<String> = JsonMissing.of(),
    ) : this(eventType, index, step, eventId, mutableMapOf())

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("step.start")
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
     * A step in the interaction.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun step(): Step = step.getRequired("step")

    /**
     * The event_id token to be used to resume the interaction stream, from this event.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun eventId(): Optional<String> = eventId.getOptional("event_id")

    /**
     * Returns the raw JSON value of [index].
     *
     * Unlike [index], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("index") @ExcludeMissing fun _index(): JsonField<Int> = index

    /**
     * Returns the raw JSON value of [step].
     *
     * Unlike [step], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("step") @ExcludeMissing fun _step(): JsonField<Step> = step

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
         * Returns a mutable builder for constructing an instance of [StepStart].
         *
         * The following fields are required:
         * ```java
         * .index()
         * .step()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [StepStart]. */
    class Builder internal constructor() {

        private var eventType: JsonValue = JsonValue.from("step.start")
        private var index: JsonField<Int>? = null
        private var step: JsonField<Step>? = null
        private var eventId: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(stepStart: StepStart) = apply {
            eventType = stepStart.eventType
            index = stepStart.index
            step = stepStart.step
            eventId = stepStart.eventId
            additionalProperties = stepStart.additionalProperties.toMutableMap()
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("step.start")
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

        /** A step in the interaction. */
        fun step(step: Step) = step(JsonField.of(step))

        /**
         * Sets [Builder.step] to an arbitrary JSON value.
         *
         * You should usually call [Builder.step] with a well-typed [Step] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun step(step: JsonField<Step>) = apply { this.step = step }

        /** Alias for calling [step] with `Step.ofUserInput(userInput)`. */
        fun step(userInput: UserInputStep) = step(Step.ofUserInput(userInput))

        /** Alias for calling [step] with `Step.ofModelOutput(modelOutput)`. */
        fun step(modelOutput: ModelOutputStep) = step(Step.ofModelOutput(modelOutput))

        /** Alias for calling [step] with `Step.ofThought(thought)`. */
        fun step(thought: ThoughtStep) = step(Step.ofThought(thought))

        /** Alias for calling [step] with `Step.ofFunctionCall(functionCall)`. */
        fun step(functionCall: FunctionCallStep) = step(Step.ofFunctionCall(functionCall))

        /** Alias for calling [step] with `Step.ofCodeExecutionCall(codeExecutionCall)`. */
        fun step(codeExecutionCall: CodeExecutionCallStep) =
            step(Step.ofCodeExecutionCall(codeExecutionCall))

        /** Alias for calling [step] with `Step.ofUrlContextCall(urlContextCall)`. */
        fun step(urlContextCall: UrlContextCallStep) = step(Step.ofUrlContextCall(urlContextCall))

        /** Alias for calling [step] with `Step.ofMcpServerToolCall(mcpServerToolCall)`. */
        fun step(mcpServerToolCall: McpServerToolCallStep) =
            step(Step.ofMcpServerToolCall(mcpServerToolCall))

        /** Alias for calling [step] with `Step.ofGoogleSearchCall(googleSearchCall)`. */
        fun step(googleSearchCall: GoogleSearchCallStep) =
            step(Step.ofGoogleSearchCall(googleSearchCall))

        /** Alias for calling [step] with `Step.ofFileSearchCall(fileSearchCall)`. */
        fun step(fileSearchCall: FileSearchCallStep) = step(Step.ofFileSearchCall(fileSearchCall))

        /**
         * Alias for calling [step] with the following:
         * ```java
         * FileSearchCallStep.builder()
         *     .id(id)
         *     .build()
         * ```
         */
        fun fileSearchCallStep(id: String) = step(FileSearchCallStep.builder().id(id).build())

        /** Alias for calling [step] with `Step.ofGoogleMapsCall(googleMapsCall)`. */
        fun step(googleMapsCall: GoogleMapsCallStep) = step(Step.ofGoogleMapsCall(googleMapsCall))

        /**
         * Alias for calling [step] with the following:
         * ```java
         * GoogleMapsCallStep.builder()
         *     .id(id)
         *     .build()
         * ```
         */
        fun googleMapsCallStep(id: String) = step(GoogleMapsCallStep.builder().id(id).build())

        /** Alias for calling [step] with `Step.ofFunctionResult(functionResult)`. */
        fun step(functionResult: FunctionResultStep) = step(Step.ofFunctionResult(functionResult))

        /** Alias for calling [step] with `Step.ofCodeExecutionResult(codeExecutionResult)`. */
        fun step(codeExecutionResult: CodeExecutionResultStep) =
            step(Step.ofCodeExecutionResult(codeExecutionResult))

        /** Alias for calling [step] with `Step.ofUrlContextResult(urlContextResult)`. */
        fun step(urlContextResult: UrlContextResultStep) =
            step(Step.ofUrlContextResult(urlContextResult))

        /** Alias for calling [step] with `Step.ofGoogleSearchResult(googleSearchResult)`. */
        fun step(googleSearchResult: GoogleSearchResultStep) =
            step(Step.ofGoogleSearchResult(googleSearchResult))

        /** Alias for calling [step] with `Step.ofMcpServerToolResult(mcpServerToolResult)`. */
        fun step(mcpServerToolResult: McpServerToolResultStep) =
            step(Step.ofMcpServerToolResult(mcpServerToolResult))

        /** Alias for calling [step] with `Step.ofFileSearchResult(fileSearchResult)`. */
        fun step(fileSearchResult: FileSearchResultStep) =
            step(Step.ofFileSearchResult(fileSearchResult))

        /**
         * Alias for calling [step] with the following:
         * ```java
         * FileSearchResultStep.builder()
         *     .callId(callId)
         *     .build()
         * ```
         */
        fun fileSearchResultStep(callId: String) =
            step(FileSearchResultStep.builder().callId(callId).build())

        /** Alias for calling [step] with `Step.ofGoogleMapsResult(googleMapsResult)`. */
        fun step(googleMapsResult: GoogleMapsResultStep) =
            step(Step.ofGoogleMapsResult(googleMapsResult))

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
         * Returns an immutable instance of [StepStart].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .index()
         * .step()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): StepStart =
            StepStart(
                eventType,
                checkRequired("index", index),
                checkRequired("step", step),
                eventId,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): StepStart = apply {
        if (validated) {
            return@apply
        }

        _eventType().let {
            if (it != JsonValue.from("step.start")) {
                throw GeminiNextGenApiInvalidDataException("'eventType' is invalid, received $it")
            }
        }
        index()
        step().validate()
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
        eventType.let { if (it == JsonValue.from("step.start")) 1 else 0 } +
            (if (index.asKnown().isPresent) 1 else 0) +
            (step.asKnown().getOrNull()?.validity() ?: 0) +
            (if (eventId.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is StepStart &&
            eventType == other.eventType &&
            index == other.index &&
            step == other.step &&
            eventId == other.eventId &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(eventType, index, step, eventId, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "StepStart{eventType=$eventType, index=$index, step=$step, eventId=$eventId, additionalProperties=$additionalProperties}"
}
