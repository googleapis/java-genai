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

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.core.type.TypeReference
import com.google.genai.interactions.core.BaseDeserializer
import com.google.genai.interactions.core.BaseSerializer
import com.google.genai.interactions.core.JsonValue
import com.google.genai.interactions.core.getOrThrow
import com.google.genai.interactions.errors.GeminiNextGenApiInvalidDataException
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

@JsonDeserialize(using = InteractionSseEvent.Deserializer::class)
@JsonSerialize(using = InteractionSseEvent.Serializer::class)
class InteractionSseEvent
private constructor(
    private val created: InteractionCreatedEvent? = null,
    private val completed: InteractionCompletedEvent? = null,
    private val statusUpdate: InteractionStatusUpdate? = null,
    private val error: ErrorEvent? = null,
    private val stepStart: StepStart? = null,
    private val stepDelta: StepDelta? = null,
    private val stepStop: StepStop? = null,
    private val _json: JsonValue? = null,
) {

    fun created(): Optional<InteractionCreatedEvent> = Optional.ofNullable(created)

    fun completed(): Optional<InteractionCompletedEvent> = Optional.ofNullable(completed)

    fun statusUpdate(): Optional<InteractionStatusUpdate> = Optional.ofNullable(statusUpdate)

    fun error(): Optional<ErrorEvent> = Optional.ofNullable(error)

    fun stepStart(): Optional<StepStart> = Optional.ofNullable(stepStart)

    fun stepDelta(): Optional<StepDelta> = Optional.ofNullable(stepDelta)

    fun stepStop(): Optional<StepStop> = Optional.ofNullable(stepStop)

    fun isCreated(): Boolean = created != null

    fun isCompleted(): Boolean = completed != null

    fun isStatusUpdate(): Boolean = statusUpdate != null

    fun isError(): Boolean = error != null

    fun isStepStart(): Boolean = stepStart != null

    fun isStepDelta(): Boolean = stepDelta != null

    fun isStepStop(): Boolean = stepStop != null

    fun asCreated(): InteractionCreatedEvent = created.getOrThrow("created")

    fun asCompleted(): InteractionCompletedEvent = completed.getOrThrow("completed")

    fun asStatusUpdate(): InteractionStatusUpdate = statusUpdate.getOrThrow("statusUpdate")

    fun asError(): ErrorEvent = error.getOrThrow("error")

    fun asStepStart(): StepStart = stepStart.getOrThrow("stepStart")

    fun asStepDelta(): StepDelta = stepDelta.getOrThrow("stepDelta")

    fun asStepStop(): StepStop = stepStop.getOrThrow("stepStop")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T =
        when {
            created != null -> visitor.visitCreated(created)
            completed != null -> visitor.visitCompleted(completed)
            statusUpdate != null -> visitor.visitStatusUpdate(statusUpdate)
            error != null -> visitor.visitError(error)
            stepStart != null -> visitor.visitStepStart(stepStart)
            stepDelta != null -> visitor.visitStepDelta(stepDelta)
            stepStop != null -> visitor.visitStepStop(stepStop)
            else -> visitor.unknown(_json)
        }

    private var validated: Boolean = false

    fun validate(): InteractionSseEvent = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitCreated(created: InteractionCreatedEvent) {
                    created.validate()
                }

                override fun visitCompleted(completed: InteractionCompletedEvent) {
                    completed.validate()
                }

                override fun visitStatusUpdate(statusUpdate: InteractionStatusUpdate) {
                    statusUpdate.validate()
                }

                override fun visitError(error: ErrorEvent) {
                    error.validate()
                }

                override fun visitStepStart(stepStart: StepStart) {
                    stepStart.validate()
                }

                override fun visitStepDelta(stepDelta: StepDelta) {
                    stepDelta.validate()
                }

                override fun visitStepStop(stepStop: StepStop) {
                    stepStop.validate()
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
     * Returns a score indicating how many valid values are contained in this object recursively.
     *
     * Used for best match union deserialization.
     */
    @JvmSynthetic
    internal fun validity(): Int =
        accept(
            object : Visitor<Int> {
                override fun visitCreated(created: InteractionCreatedEvent) = created.validity()

                override fun visitCompleted(completed: InteractionCompletedEvent) =
                    completed.validity()

                override fun visitStatusUpdate(statusUpdate: InteractionStatusUpdate) =
                    statusUpdate.validity()

                override fun visitError(error: ErrorEvent) = error.validity()

                override fun visitStepStart(stepStart: StepStart) = stepStart.validity()

                override fun visitStepDelta(stepDelta: StepDelta) = stepDelta.validity()

                override fun visitStepStop(stepStop: StepStop) = stepStop.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is InteractionSseEvent &&
            created == other.created &&
            completed == other.completed &&
            statusUpdate == other.statusUpdate &&
            error == other.error &&
            stepStart == other.stepStart &&
            stepDelta == other.stepDelta &&
            stepStop == other.stepStop
    }

    override fun hashCode(): Int =
        Objects.hash(created, completed, statusUpdate, error, stepStart, stepDelta, stepStop)

    override fun toString(): String =
        when {
            created != null -> "InteractionSseEvent{created=$created}"
            completed != null -> "InteractionSseEvent{completed=$completed}"
            statusUpdate != null -> "InteractionSseEvent{statusUpdate=$statusUpdate}"
            error != null -> "InteractionSseEvent{error=$error}"
            stepStart != null -> "InteractionSseEvent{stepStart=$stepStart}"
            stepDelta != null -> "InteractionSseEvent{stepDelta=$stepDelta}"
            stepStop != null -> "InteractionSseEvent{stepStop=$stepStop}"
            _json != null -> "InteractionSseEvent{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid InteractionSseEvent")
        }

    companion object {

        @JvmStatic
        fun ofCreated(created: InteractionCreatedEvent) = InteractionSseEvent(created = created)

        @JvmStatic
        fun ofCompleted(completed: InteractionCompletedEvent) =
            InteractionSseEvent(completed = completed)

        @JvmStatic
        fun ofStatusUpdate(statusUpdate: InteractionStatusUpdate) =
            InteractionSseEvent(statusUpdate = statusUpdate)

        @JvmStatic fun ofError(error: ErrorEvent) = InteractionSseEvent(error = error)

        @JvmStatic
        fun ofStepStart(stepStart: StepStart) = InteractionSseEvent(stepStart = stepStart)

        @JvmStatic
        fun ofStepDelta(stepDelta: StepDelta) = InteractionSseEvent(stepDelta = stepDelta)

        @JvmStatic fun ofStepStop(stepStop: StepStop) = InteractionSseEvent(stepStop = stepStop)
    }

    /**
     * An interface that defines how to map each variant of [InteractionSseEvent] to a value of type
     * [T].
     */
    interface Visitor<out T> {

        fun visitCreated(created: InteractionCreatedEvent): T

        fun visitCompleted(completed: InteractionCompletedEvent): T

        fun visitStatusUpdate(statusUpdate: InteractionStatusUpdate): T

        fun visitError(error: ErrorEvent): T

        fun visitStepStart(stepStart: StepStart): T

        fun visitStepDelta(stepDelta: StepDelta): T

        fun visitStepStop(stepStop: StepStop): T

        /**
         * Maps an unknown variant of [InteractionSseEvent] to a value of type [T].
         *
         * An instance of [InteractionSseEvent] can contain an unknown variant if it was
         * deserialized from data that doesn't match any known variant. For example, if the SDK is
         * on an older version than the API, then the API may respond with new variants that the SDK
         * is unaware of.
         *
         * @throws GeminiNextGenApiInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw GeminiNextGenApiInvalidDataException("Unknown InteractionSseEvent: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<InteractionSseEvent>(InteractionSseEvent::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): InteractionSseEvent {
            val json = JsonValue.fromJsonNode(node)
            val eventType = json.asObject().getOrNull()?.get("event_type")?.asString()?.getOrNull()

            when (eventType) {
                "interaction.created" -> {
                    return tryDeserialize(node, object : TypeReference<InteractionCreatedEvent>() {})?.let {
                        InteractionSseEvent(created = it, _json = json)
                    } ?: InteractionSseEvent(_json = json)
                }
                "interaction.completed" -> {
                    return tryDeserialize(node, object : TypeReference<InteractionCompletedEvent>() {})?.let {
                        InteractionSseEvent(completed = it, _json = json)
                    } ?: InteractionSseEvent(_json = json)
                }
                "interaction.status_update" -> {
                    return tryDeserialize(node, object : TypeReference<InteractionStatusUpdate>() {})?.let {
                        InteractionSseEvent(statusUpdate = it, _json = json)
                    } ?: InteractionSseEvent(_json = json)
                }
                "error" -> {
                    return tryDeserialize(node, object : TypeReference<ErrorEvent>() {})?.let {
                        InteractionSseEvent(error = it, _json = json)
                    } ?: InteractionSseEvent(_json = json)
                }
                "step.start" -> {
                    return tryDeserialize(node, object : TypeReference<StepStart>() {})?.let {
                        InteractionSseEvent(stepStart = it, _json = json)
                    } ?: InteractionSseEvent(_json = json)
                }
                "step.delta" -> {
                    return tryDeserialize(node, object : TypeReference<StepDelta>() {})?.let {
                        InteractionSseEvent(stepDelta = it, _json = json)
                    } ?: InteractionSseEvent(_json = json)
                }
                "step.stop" -> {
                    return tryDeserialize(node, object : TypeReference<StepStop>() {})?.let {
                        InteractionSseEvent(stepStop = it, _json = json)
                    } ?: InteractionSseEvent(_json = json)
                }
            }

            return InteractionSseEvent(_json = json)
        }
    }

    internal class Serializer : BaseSerializer<InteractionSseEvent>(InteractionSseEvent::class) {

        override fun serialize(
            value: InteractionSseEvent,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.created != null -> generator.writeObject(value.created)
                value.completed != null -> generator.writeObject(value.completed)
                value.statusUpdate != null -> generator.writeObject(value.statusUpdate)
                value.error != null -> generator.writeObject(value.error)
                value.stepStart != null -> generator.writeObject(value.stepStart)
                value.stepDelta != null -> generator.writeObject(value.stepDelta)
                value.stepStop != null -> generator.writeObject(value.stepStop)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid InteractionSseEvent")
            }
        }
    }
}
