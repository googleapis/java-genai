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
import com.google.genai.interactions.core.checkKnown
import com.google.genai.interactions.core.checkRequired
import com.google.genai.interactions.core.getOrThrow
import com.google.genai.interactions.core.toImmutable
import com.google.genai.interactions.errors.GeminiNextGenApiInvalidDataException
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

class StepDelta
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
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun delta(): Delta = delta.getRequired("delta")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("step.delta")
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
         * Returns a mutable builder for constructing an instance of [StepDelta].
         *
         * The following fields are required:
         * ```java
         * .delta()
         * .index()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [StepDelta]. */
    class Builder internal constructor() {

        private var delta: JsonField<Delta>? = null
        private var eventType: JsonValue = JsonValue.from("step.delta")
        private var index: JsonField<Int>? = null
        private var eventId: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(stepDelta: StepDelta) = apply {
            delta = stepDelta.delta
            eventType = stepDelta.eventType
            index = stepDelta.index
            eventId = stepDelta.eventId
            additionalProperties = stepDelta.additionalProperties.toMutableMap()
        }

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

        /** Alias for calling [delta] with `Delta.ofTextAnnotation(textAnnotation)`. */
        fun delta(textAnnotation: Delta.TextAnnotationDelta) =
            delta(Delta.ofTextAnnotation(textAnnotation))

        /** Alias for calling [delta] with `Delta.ofArguments(arguments)`. */
        fun delta(arguments: Delta.ArgumentsDelta) = delta(Delta.ofArguments(arguments))

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("step.delta")
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
         * Returns an immutable instance of [StepDelta].
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
        fun build(): StepDelta =
            StepDelta(
                checkRequired("delta", delta),
                eventType,
                checkRequired("index", index),
                eventId,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): StepDelta = apply {
        if (validated) {
            return@apply
        }

        delta().validate()
        _eventType().let {
            if (it != JsonValue.from("step.delta")) {
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
            eventType.let { if (it == JsonValue.from("step.delta")) 1 else 0 } +
            (if (index.asKnown().isPresent) 1 else 0) +
            (if (eventId.asKnown().isPresent) 1 else 0)

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
        private val textAnnotation: TextAnnotationDelta? = null,
        private val arguments: ArgumentsDelta? = null,
        private val _json: JsonValue? = null,
    ) {

        fun text(): Optional<Text> = Optional.ofNullable(text)

        fun image(): Optional<Image> = Optional.ofNullable(image)

        fun audio(): Optional<Audio> = Optional.ofNullable(audio)

        fun document(): Optional<Document> = Optional.ofNullable(document)

        fun video(): Optional<Video> = Optional.ofNullable(video)

        fun thoughtSummary(): Optional<ThoughtSummary> = Optional.ofNullable(thoughtSummary)

        fun thoughtSignature(): Optional<ThoughtSignature> = Optional.ofNullable(thoughtSignature)

        fun textAnnotation(): Optional<TextAnnotationDelta> = Optional.ofNullable(textAnnotation)

        fun arguments(): Optional<ArgumentsDelta> = Optional.ofNullable(arguments)

        fun isText(): Boolean = text != null

        fun isImage(): Boolean = image != null

        fun isAudio(): Boolean = audio != null

        fun isDocument(): Boolean = document != null

        fun isVideo(): Boolean = video != null

        fun isThoughtSummary(): Boolean = thoughtSummary != null

        fun isThoughtSignature(): Boolean = thoughtSignature != null

        fun isTextAnnotation(): Boolean = textAnnotation != null

        fun isArguments(): Boolean = arguments != null

        fun asText(): Text = text.getOrThrow("text")

        fun asImage(): Image = image.getOrThrow("image")

        fun asAudio(): Audio = audio.getOrThrow("audio")

        fun asDocument(): Document = document.getOrThrow("document")

        fun asVideo(): Video = video.getOrThrow("video")

        fun asThoughtSummary(): ThoughtSummary = thoughtSummary.getOrThrow("thoughtSummary")

        fun asThoughtSignature(): ThoughtSignature = thoughtSignature.getOrThrow("thoughtSignature")

        fun asTextAnnotation(): TextAnnotationDelta = textAnnotation.getOrThrow("textAnnotation")

        fun asArguments(): ArgumentsDelta = arguments.getOrThrow("arguments")

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
                textAnnotation != null -> visitor.visitTextAnnotation(textAnnotation)
                arguments != null -> visitor.visitArguments(arguments)
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

                    override fun visitTextAnnotation(textAnnotation: TextAnnotationDelta) {
                        textAnnotation.validate()
                    }

                    override fun visitArguments(arguments: ArgumentsDelta) {
                        arguments.validate()
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

                    override fun visitTextAnnotation(textAnnotation: TextAnnotationDelta) =
                        textAnnotation.validity()

                    override fun visitArguments(arguments: ArgumentsDelta) = arguments.validity()

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
                textAnnotation == other.textAnnotation &&
                arguments == other.arguments
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
                textAnnotation,
                arguments,
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
                textAnnotation != null -> "Delta{textAnnotation=$textAnnotation}"
                arguments != null -> "Delta{arguments=$arguments}"
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
            fun ofTextAnnotation(textAnnotation: TextAnnotationDelta) =
                Delta(textAnnotation = textAnnotation)

            @JvmStatic fun ofArguments(arguments: ArgumentsDelta) = Delta(arguments = arguments)
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

            fun visitTextAnnotation(textAnnotation: TextAnnotationDelta): T

            fun visitArguments(arguments: ArgumentsDelta): T

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
                    "text_annotation_delta" -> {
                        return tryDeserialize(node, jacksonTypeRef<TextAnnotationDelta>())?.let {
                            Delta(textAnnotation = it, _json = json)
                        } ?: Delta(_json = json)
                    }
                    "arguments_delta" -> {
                        return tryDeserialize(node, jacksonTypeRef<ArgumentsDelta>())?.let {
                            Delta(arguments = it, _json = json)
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
                    value.textAnnotation != null -> generator.writeObject(value.textAnnotation)
                    value.arguments != null -> generator.writeObject(value.arguments)
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
            private val sampleRate: JsonField<Int>,
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
                @JsonProperty("sample_rate")
                @ExcludeMissing
                sampleRate: JsonField<Int> = JsonMissing.of(),
                @JsonProperty("uri") @ExcludeMissing uri: JsonField<String> = JsonMissing.of(),
            ) : this(type, channels, data, mimeType, rate, sampleRate, uri, mutableMapOf())

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
             * Deprecated. Use sample_rate instead. The value is ignored.
             *
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   (e.g. if the server responded with an unexpected value).
             */
            @Deprecated("deprecated") fun rate(): Optional<Int> = rate.getOptional("rate")

            /**
             * The sample rate of the audio.
             *
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   (e.g. if the server responded with an unexpected value).
             */
            fun sampleRate(): Optional<Int> = sampleRate.getOptional("sample_rate")

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
            @Deprecated("deprecated")
            @JsonProperty("rate")
            @ExcludeMissing
            fun _rate(): JsonField<Int> = rate

            /**
             * Returns the raw JSON value of [sampleRate].
             *
             * Unlike [sampleRate], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("sample_rate")
            @ExcludeMissing
            fun _sampleRate(): JsonField<Int> = sampleRate

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
                private var sampleRate: JsonField<Int> = JsonMissing.of()
                private var uri: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(audio: Audio) = apply {
                    type = audio.type
                    channels = audio.channels
                    data = audio.data
                    mimeType = audio.mimeType
                    rate = audio.rate
                    sampleRate = audio.sampleRate
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

                /** Deprecated. Use sample_rate instead. The value is ignored. */
                @Deprecated("deprecated") fun rate(rate: Int) = rate(JsonField.of(rate))

                /**
                 * Sets [Builder.rate] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.rate] with a well-typed [Int] value instead.
                 * This method is primarily for setting the field to an undocumented or not yet
                 * supported value.
                 */
                @Deprecated("deprecated")
                fun rate(rate: JsonField<Int>) = apply { this.rate = rate }

                /** The sample rate of the audio. */
                fun sampleRate(sampleRate: Int) = sampleRate(JsonField.of(sampleRate))

                /**
                 * Sets [Builder.sampleRate] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.sampleRate] with a well-typed [Int] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun sampleRate(sampleRate: JsonField<Int>) = apply { this.sampleRate = sampleRate }

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
                        sampleRate,
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
                sampleRate()
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
                    (if (sampleRate.asKnown().isPresent) 1 else 0) +
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
                    sampleRate == other.sampleRate &&
                    uri == other.uri &&
                    additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(
                    type,
                    channels,
                    data,
                    mimeType,
                    rate,
                    sampleRate,
                    uri,
                    additionalProperties,
                )
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "Audio{type=$type, channels=$channels, data=$data, mimeType=$mimeType, rate=$rate, sampleRate=$sampleRate, uri=$uri, additionalProperties=$additionalProperties}"
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

        class TextAnnotationDelta
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
             * JsonValue.from("text_annotation_delta")
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

                /**
                 * Returns a mutable builder for constructing an instance of [TextAnnotationDelta].
                 */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [TextAnnotationDelta]. */
            class Builder internal constructor() {

                private var type: JsonValue = JsonValue.from("text_annotation_delta")
                private var annotations: JsonField<MutableList<Annotation>>? = null
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(textAnnotationDelta: TextAnnotationDelta) = apply {
                    type = textAnnotationDelta.type
                    annotations = textAnnotationDelta.annotations.map { it.toMutableList() }
                    additionalProperties = textAnnotationDelta.additionalProperties.toMutableMap()
                }

                /**
                 * Sets the field to an arbitrary JSON value.
                 *
                 * It is usually unnecessary to call this method because the field defaults to the
                 * following:
                 * ```java
                 * JsonValue.from("text_annotation_delta")
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
                 * Returns an immutable instance of [TextAnnotationDelta].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 */
                fun build(): TextAnnotationDelta =
                    TextAnnotationDelta(
                        type,
                        (annotations ?: JsonMissing.of()).map { it.toImmutable() },
                        additionalProperties.toMutableMap(),
                    )
            }

            private var validated: Boolean = false

            fun validate(): TextAnnotationDelta = apply {
                if (validated) {
                    return@apply
                }

                _type().let {
                    if (it != JsonValue.from("text_annotation_delta")) {
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
                type.let { if (it == JsonValue.from("text_annotation_delta")) 1 else 0 } +
                    (annotations.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is TextAnnotationDelta &&
                    type == other.type &&
                    annotations == other.annotations &&
                    additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(type, annotations, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "TextAnnotationDelta{type=$type, annotations=$annotations, additionalProperties=$additionalProperties}"
        }

        class ArgumentsDelta
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val type: JsonValue,
            private val partialArguments: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
                @JsonProperty("partial_arguments")
                @ExcludeMissing
                partialArguments: JsonField<String> = JsonMissing.of(),
            ) : this(type, partialArguments, mutableMapOf())

            /**
             * Expected to always return the following:
             * ```java
             * JsonValue.from("arguments_delta")
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
            fun partialArguments(): Optional<String> =
                partialArguments.getOptional("partial_arguments")

            /**
             * Returns the raw JSON value of [partialArguments].
             *
             * Unlike [partialArguments], this method doesn't throw if the JSON field has an
             * unexpected type.
             */
            @JsonProperty("partial_arguments")
            @ExcludeMissing
            fun _partialArguments(): JsonField<String> = partialArguments

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

                /** Returns a mutable builder for constructing an instance of [ArgumentsDelta]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [ArgumentsDelta]. */
            class Builder internal constructor() {

                private var type: JsonValue = JsonValue.from("arguments_delta")
                private var partialArguments: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(argumentsDelta: ArgumentsDelta) = apply {
                    type = argumentsDelta.type
                    partialArguments = argumentsDelta.partialArguments
                    additionalProperties = argumentsDelta.additionalProperties.toMutableMap()
                }

                /**
                 * Sets the field to an arbitrary JSON value.
                 *
                 * It is usually unnecessary to call this method because the field defaults to the
                 * following:
                 * ```java
                 * JsonValue.from("arguments_delta")
                 * ```
                 *
                 * This method is primarily for setting the field to an undocumented or not yet
                 * supported value.
                 */
                fun type(type: JsonValue) = apply { this.type = type }

                fun partialArguments(partialArguments: String) =
                    partialArguments(JsonField.of(partialArguments))

                /**
                 * Sets [Builder.partialArguments] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.partialArguments] with a well-typed [String]
                 * value instead. This method is primarily for setting the field to an undocumented
                 * or not yet supported value.
                 */
                fun partialArguments(partialArguments: JsonField<String>) = apply {
                    this.partialArguments = partialArguments
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
                 * Returns an immutable instance of [ArgumentsDelta].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 */
                fun build(): ArgumentsDelta =
                    ArgumentsDelta(type, partialArguments, additionalProperties.toMutableMap())
            }

            private var validated: Boolean = false

            fun validate(): ArgumentsDelta = apply {
                if (validated) {
                    return@apply
                }

                _type().let {
                    if (it != JsonValue.from("arguments_delta")) {
                        throw GeminiNextGenApiInvalidDataException(
                            "'type' is invalid, received $it"
                        )
                    }
                }
                partialArguments()
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
                type.let { if (it == JsonValue.from("arguments_delta")) 1 else 0 } +
                    (if (partialArguments.asKnown().isPresent) 1 else 0)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is ArgumentsDelta &&
                    type == other.type &&
                    partialArguments == other.partialArguments &&
                    additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(type, partialArguments, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "ArgumentsDelta{type=$type, partialArguments=$partialArguments, additionalProperties=$additionalProperties}"
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is StepDelta &&
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
        "StepDelta{delta=$delta, eventType=$eventType, index=$index, eventId=$eventId, additionalProperties=$additionalProperties}"
}
