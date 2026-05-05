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
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
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
    private val start: InteractionStartEvent? = null,
    private val complete: InteractionCompleteEvent? = null,
    private val statusUpdate: InteractionStatusUpdate? = null,
    private val contentStart: ContentStart? = null,
    private val contentDelta: ContentDelta? = null,
    private val contentStop: ContentStop? = null,
    private val error: ErrorEvent? = null,
    private val stepStart: StepStart? = null,
    private val stepDelta: StepDelta? = null,
    private val stepStop: StepStop? = null,
    private val _json: JsonValue? = null,
) {

    fun start(): Optional<InteractionStartEvent> = Optional.ofNullable(start)

    fun complete(): Optional<InteractionCompleteEvent> = Optional.ofNullable(complete)

    fun statusUpdate(): Optional<InteractionStatusUpdate> = Optional.ofNullable(statusUpdate)

    fun contentStart(): Optional<ContentStart> = Optional.ofNullable(contentStart)

    fun contentDelta(): Optional<ContentDelta> = Optional.ofNullable(contentDelta)

    fun contentStop(): Optional<ContentStop> = Optional.ofNullable(contentStop)

    fun error(): Optional<ErrorEvent> = Optional.ofNullable(error)

    fun stepStart(): Optional<StepStart> = Optional.ofNullable(stepStart)

    fun stepDelta(): Optional<StepDelta> = Optional.ofNullable(stepDelta)

    fun stepStop(): Optional<StepStop> = Optional.ofNullable(stepStop)

    fun isStart(): Boolean = start != null

    fun isComplete(): Boolean = complete != null

    fun isStatusUpdate(): Boolean = statusUpdate != null

    fun isContentStart(): Boolean = contentStart != null

    fun isContentDelta(): Boolean = contentDelta != null

    fun isContentStop(): Boolean = contentStop != null

    fun isError(): Boolean = error != null

    fun isStepStart(): Boolean = stepStart != null

    fun isStepDelta(): Boolean = stepDelta != null

    fun isStepStop(): Boolean = stepStop != null

    fun asStart(): InteractionStartEvent = start.getOrThrow("start")

    fun asComplete(): InteractionCompleteEvent = complete.getOrThrow("complete")

    fun asStatusUpdate(): InteractionStatusUpdate = statusUpdate.getOrThrow("statusUpdate")

    fun asContentStart(): ContentStart = contentStart.getOrThrow("contentStart")

    fun asContentDelta(): ContentDelta = contentDelta.getOrThrow("contentDelta")

    fun asContentStop(): ContentStop = contentStop.getOrThrow("contentStop")

    fun asError(): ErrorEvent = error.getOrThrow("error")

    fun asStepStart(): StepStart = stepStart.getOrThrow("stepStart")

    fun asStepDelta(): StepDelta = stepDelta.getOrThrow("stepDelta")

    fun asStepStop(): StepStop = stepStop.getOrThrow("stepStop")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T =
        when {
            start != null -> visitor.visitStart(start)
            complete != null -> visitor.visitComplete(complete)
            statusUpdate != null -> visitor.visitStatusUpdate(statusUpdate)
            contentStart != null -> visitor.visitContentStart(contentStart)
            contentDelta != null -> visitor.visitContentDelta(contentDelta)
            contentStop != null -> visitor.visitContentStop(contentStop)
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
                override fun visitStart(start: InteractionStartEvent) {
                    start.validate()
                }

                override fun visitComplete(complete: InteractionCompleteEvent) {
                    complete.validate()
                }

                override fun visitStatusUpdate(statusUpdate: InteractionStatusUpdate) {
                    statusUpdate.validate()
                }

                override fun visitContentStart(contentStart: ContentStart) {
                    contentStart.validate()
                }

                override fun visitContentDelta(contentDelta: ContentDelta) {
                    contentDelta.validate()
                }

                override fun visitContentStop(contentStop: ContentStop) {
                    contentStop.validate()
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
                override fun visitStart(start: InteractionStartEvent) = start.validity()

                override fun visitComplete(complete: InteractionCompleteEvent) = complete.validity()

                override fun visitStatusUpdate(statusUpdate: InteractionStatusUpdate) =
                    statusUpdate.validity()

                override fun visitContentStart(contentStart: ContentStart) = contentStart.validity()

                override fun visitContentDelta(contentDelta: ContentDelta) = contentDelta.validity()

                override fun visitContentStop(contentStop: ContentStop) = contentStop.validity()

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
            start == other.start &&
            complete == other.complete &&
            statusUpdate == other.statusUpdate &&
            contentStart == other.contentStart &&
            contentDelta == other.contentDelta &&
            contentStop == other.contentStop &&
            error == other.error &&
            stepStart == other.stepStart &&
            stepDelta == other.stepDelta &&
            stepStop == other.stepStop
    }

    override fun hashCode(): Int =
        Objects.hash(
            start,
            complete,
            statusUpdate,
            contentStart,
            contentDelta,
            contentStop,
            error,
            stepStart,
            stepDelta,
            stepStop,
        )

    override fun toString(): String =
        when {
            start != null -> "InteractionSseEvent{start=$start}"
            complete != null -> "InteractionSseEvent{complete=$complete}"
            statusUpdate != null -> "InteractionSseEvent{statusUpdate=$statusUpdate}"
            contentStart != null -> "InteractionSseEvent{contentStart=$contentStart}"
            contentDelta != null -> "InteractionSseEvent{contentDelta=$contentDelta}"
            contentStop != null -> "InteractionSseEvent{contentStop=$contentStop}"
            error != null -> "InteractionSseEvent{error=$error}"
            stepStart != null -> "InteractionSseEvent{stepStart=$stepStart}"
            stepDelta != null -> "InteractionSseEvent{stepDelta=$stepDelta}"
            stepStop != null -> "InteractionSseEvent{stepStop=$stepStop}"
            _json != null -> "InteractionSseEvent{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid InteractionSseEvent")
        }

    companion object {

        @JvmStatic fun ofStart(start: InteractionStartEvent) = InteractionSseEvent(start = start)

        @JvmStatic
        fun ofComplete(complete: InteractionCompleteEvent) =
            InteractionSseEvent(complete = complete)

        @JvmStatic
        fun ofStatusUpdate(statusUpdate: InteractionStatusUpdate) =
            InteractionSseEvent(statusUpdate = statusUpdate)

        @JvmStatic
        fun ofContentStart(contentStart: ContentStart) =
            InteractionSseEvent(contentStart = contentStart)

        @JvmStatic
        fun ofContentDelta(contentDelta: ContentDelta) =
            InteractionSseEvent(contentDelta = contentDelta)

        @JvmStatic
        fun ofContentStop(contentStop: ContentStop) = InteractionSseEvent(contentStop = contentStop)

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

        fun visitStart(start: InteractionStartEvent): T

        fun visitComplete(complete: InteractionCompleteEvent): T

        fun visitStatusUpdate(statusUpdate: InteractionStatusUpdate): T

        fun visitContentStart(contentStart: ContentStart): T

        fun visitContentDelta(contentDelta: ContentDelta): T

        fun visitContentStop(contentStop: ContentStop): T

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
                "interaction.start" -> {
                    return tryDeserialize(node, jacksonTypeRef<InteractionStartEvent>())?.let {
                        InteractionSseEvent(start = it, _json = json)
                    } ?: InteractionSseEvent(_json = json)
                }
                "interaction.complete" -> {
                    return tryDeserialize(node, jacksonTypeRef<InteractionCompleteEvent>())?.let {
                        InteractionSseEvent(complete = it, _json = json)
                    } ?: InteractionSseEvent(_json = json)
                }
                "interaction.status_update" -> {
                    return tryDeserialize(node, jacksonTypeRef<InteractionStatusUpdate>())?.let {
                        InteractionSseEvent(statusUpdate = it, _json = json)
                    } ?: InteractionSseEvent(_json = json)
                }
                "content.start" -> {
                    return tryDeserialize(node, jacksonTypeRef<ContentStart>())?.let {
                        InteractionSseEvent(contentStart = it, _json = json)
                    } ?: InteractionSseEvent(_json = json)
                }
                "content.delta" -> {
                    return tryDeserialize(node, jacksonTypeRef<ContentDelta>())?.let {
                        InteractionSseEvent(contentDelta = it, _json = json)
                    } ?: InteractionSseEvent(_json = json)
                }
                "content.stop" -> {
                    return tryDeserialize(node, jacksonTypeRef<ContentStop>())?.let {
                        InteractionSseEvent(contentStop = it, _json = json)
                    } ?: InteractionSseEvent(_json = json)
                }
                "error" -> {
                    return tryDeserialize(node, jacksonTypeRef<ErrorEvent>())?.let {
                        InteractionSseEvent(error = it, _json = json)
                    } ?: InteractionSseEvent(_json = json)
                }
                "step.start" -> {
                    return tryDeserialize(node, jacksonTypeRef<StepStart>())?.let {
                        InteractionSseEvent(stepStart = it, _json = json)
                    } ?: InteractionSseEvent(_json = json)
                }
                "step.delta" -> {
                    return tryDeserialize(node, jacksonTypeRef<StepDelta>())?.let {
                        InteractionSseEvent(stepDelta = it, _json = json)
                    } ?: InteractionSseEvent(_json = json)
                }
                "step.stop" -> {
                    return tryDeserialize(node, jacksonTypeRef<StepStop>())?.let {
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
                value.start != null -> generator.writeObject(value.start)
                value.complete != null -> generator.writeObject(value.complete)
                value.statusUpdate != null -> generator.writeObject(value.statusUpdate)
                value.contentStart != null -> generator.writeObject(value.contentStart)
                value.contentDelta != null -> generator.writeObject(value.contentDelta)
                value.contentStop != null -> generator.writeObject(value.contentStop)
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
