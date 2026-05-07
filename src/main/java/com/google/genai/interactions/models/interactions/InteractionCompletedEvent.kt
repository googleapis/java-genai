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

class InteractionCompletedEvent
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val eventType: JsonValue,
    private val interaction: JsonField<Interaction>,
    private val eventId: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("event_type") @ExcludeMissing eventType: JsonValue = JsonMissing.of(),
        @JsonProperty("interaction")
        @ExcludeMissing
        interaction: JsonField<Interaction> = JsonMissing.of(),
        @JsonProperty("event_id") @ExcludeMissing eventId: JsonField<String> = JsonMissing.of(),
    ) : this(eventType, interaction, eventId, mutableMapOf())

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("interaction.completed")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("event_type") @ExcludeMissing fun _eventType(): JsonValue = eventType

    /**
     * Required. The completed interaction with empty outputs to reduce the payload size. Use the
     * preceding ContentDelta events for the actual output.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun interaction(): Interaction = interaction.getRequired("interaction")

    /**
     * The event_id token to be used to resume the interaction stream, from this event.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun eventId(): Optional<String> = eventId.getOptional("event_id")

    /**
     * Returns the raw JSON value of [interaction].
     *
     * Unlike [interaction], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("interaction")
    @ExcludeMissing
    fun _interaction(): JsonField<Interaction> = interaction

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
         * Returns a mutable builder for constructing an instance of [InteractionCompletedEvent].
         *
         * The following fields are required:
         * ```java
         * .interaction()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [InteractionCompletedEvent]. */
    class Builder internal constructor() {

        private var eventType: JsonValue = JsonValue.from("interaction.completed")
        private var interaction: JsonField<Interaction>? = null
        private var eventId: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(interactionCompletedEvent: InteractionCompletedEvent) = apply {
            eventType = interactionCompletedEvent.eventType
            interaction = interactionCompletedEvent.interaction
            eventId = interactionCompletedEvent.eventId
            additionalProperties = interactionCompletedEvent.additionalProperties.toMutableMap()
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("interaction.completed")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun eventType(eventType: JsonValue) = apply { this.eventType = eventType }

        /**
         * Required. The completed interaction with empty outputs to reduce the payload size. Use
         * the preceding ContentDelta events for the actual output.
         */
        fun interaction(interaction: Interaction) = interaction(JsonField.of(interaction))

        /**
         * Sets [Builder.interaction] to an arbitrary JSON value.
         *
         * You should usually call [Builder.interaction] with a well-typed [Interaction] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun interaction(interaction: JsonField<Interaction>) = apply {
            this.interaction = interaction
        }

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
         * Returns an immutable instance of [InteractionCompletedEvent].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .interaction()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): InteractionCompletedEvent =
            InteractionCompletedEvent(
                eventType,
                checkRequired("interaction", interaction),
                eventId,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): InteractionCompletedEvent = apply {
        if (validated) {
            return@apply
        }

        _eventType().let {
            if (it != JsonValue.from("interaction.completed")) {
                throw GeminiNextGenApiInvalidDataException("'eventType' is invalid, received $it")
            }
        }
        interaction().validate()
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
        eventType.let { if (it == JsonValue.from("interaction.completed")) 1 else 0 } +
            (interaction.asKnown().getOrNull()?.validity() ?: 0) +
            (if (eventId.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is InteractionCompletedEvent &&
            eventType == other.eventType &&
            interaction == other.interaction &&
            eventId == other.eventId &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(eventType, interaction, eventId, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "InteractionCompletedEvent{eventType=$eventType, interaction=$interaction, eventId=$eventId, additionalProperties=$additionalProperties}"
}
