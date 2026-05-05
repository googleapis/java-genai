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
import com.google.genai.interactions.core.ExcludeMissing
import com.google.genai.interactions.core.JsonField
import com.google.genai.interactions.core.JsonMissing
import com.google.genai.interactions.core.JsonValue
import com.google.genai.interactions.core.checkKnown
import com.google.genai.interactions.core.getOrThrow
import com.google.genai.interactions.core.toImmutable
import com.google.genai.interactions.errors.GeminiNextGenApiInvalidDataException
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** A thought step. */
class ThoughtStep
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val type: JsonValue,
    private val signature: JsonField<String>,
    private val summary: JsonField<List<Summary>>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("signature") @ExcludeMissing signature: JsonField<String> = JsonMissing.of(),
        @JsonProperty("summary")
        @ExcludeMissing
        summary: JsonField<List<Summary>> = JsonMissing.of(),
    ) : this(type, signature, summary, mutableMapOf())

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("thought")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * A signature hash for backend validation.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun signature(): Optional<String> = signature.getOptional("signature")

    /**
     * A summary of the thought.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun summary(): Optional<List<Summary>> = summary.getOptional("summary")

    /**
     * Returns the raw JSON value of [signature].
     *
     * Unlike [signature], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("signature") @ExcludeMissing fun _signature(): JsonField<String> = signature

    /**
     * Returns the raw JSON value of [summary].
     *
     * Unlike [summary], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("summary") @ExcludeMissing fun _summary(): JsonField<List<Summary>> = summary

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

        /** Returns a mutable builder for constructing an instance of [ThoughtStep]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [ThoughtStep]. */
    class Builder internal constructor() {

        private var type: JsonValue = JsonValue.from("thought")
        private var signature: JsonField<String> = JsonMissing.of()
        private var summary: JsonField<MutableList<Summary>>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(thoughtStep: ThoughtStep) = apply {
            type = thoughtStep.type
            signature = thoughtStep.signature
            summary = thoughtStep.summary.map { it.toMutableList() }
            additionalProperties = thoughtStep.additionalProperties.toMutableMap()
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("thought")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /** A signature hash for backend validation. */
        fun signature(signature: String) = signature(JsonField.of(signature))

        /**
         * Sets [Builder.signature] to an arbitrary JSON value.
         *
         * You should usually call [Builder.signature] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun signature(signature: JsonField<String>) = apply { this.signature = signature }

        /** A summary of the thought. */
        fun summary(summary: List<Summary>) = summary(JsonField.of(summary))

        /**
         * Sets [Builder.summary] to an arbitrary JSON value.
         *
         * You should usually call [Builder.summary] with a well-typed `List<Summary>` value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun summary(summary: JsonField<List<Summary>>) = apply {
            this.summary = summary.map { it.toMutableList() }
        }

        /**
         * Adds a single [Summary] to [Builder.summary].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addSummary(summary: Summary) = apply {
            this.summary =
                (this.summary ?: JsonField.of(mutableListOf())).also {
                    checkKnown("summary", it).add(summary)
                }
        }

        /** Alias for calling [addSummary] with `Summary.ofText(text)`. */
        fun addSummary(text: TextContent) = addSummary(Summary.ofText(text))

        /**
         * Alias for calling [addSummary] with the following:
         * ```java
         * TextContent.builder()
         *     .text(text)
         *     .build()
         * ```
         */
        fun addTextSummary(text: String) = addSummary(TextContent.builder().text(text).build())

        /** Alias for calling [addSummary] with `Summary.ofImage(image)`. */
        fun addSummary(image: ImageContent) = addSummary(Summary.ofImage(image))

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
         * Returns an immutable instance of [ThoughtStep].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): ThoughtStep =
            ThoughtStep(
                type,
                signature,
                (summary ?: JsonMissing.of()).map { it.toImmutable() },
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): ThoughtStep = apply {
        if (validated) {
            return@apply
        }

        _type().let {
            if (it != JsonValue.from("thought")) {
                throw GeminiNextGenApiInvalidDataException("'type' is invalid, received $it")
            }
        }
        signature()
        summary().ifPresent { it.forEach { it.validate() } }
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
        type.let { if (it == JsonValue.from("thought")) 1 else 0 } +
            (if (signature.asKnown().isPresent) 1 else 0) +
            (summary.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0)

    /** A text content block. */
    @JsonDeserialize(using = Summary.Deserializer::class)
    @JsonSerialize(using = Summary.Serializer::class)
    class Summary
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

        fun validate(): Summary = apply {
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

            return other is Summary && text == other.text && image == other.image
        }

        override fun hashCode(): Int = Objects.hash(text, image)

        override fun toString(): String =
            when {
                text != null -> "Summary{text=$text}"
                image != null -> "Summary{image=$image}"
                _json != null -> "Summary{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Summary")
            }

        companion object {

            /** A text content block. */
            @JvmStatic fun ofText(text: TextContent) = Summary(text = text)

            /** An image content block. */
            @JvmStatic fun ofImage(image: ImageContent) = Summary(image = image)
        }

        /**
         * An interface that defines how to map each variant of [Summary] to a value of type [T].
         */
        interface Visitor<out T> {

            /** A text content block. */
            fun visitText(text: TextContent): T

            /** An image content block. */
            fun visitImage(image: ImageContent): T

            /**
             * Maps an unknown variant of [Summary] to a value of type [T].
             *
             * An instance of [Summary] can contain an unknown variant if it was deserialized from
             * data that doesn't match any known variant. For example, if the SDK is on an older
             * version than the API, then the API may respond with new variants that the SDK is
             * unaware of.
             *
             * @throws GeminiNextGenApiInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw GeminiNextGenApiInvalidDataException("Unknown Summary: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<Summary>(Summary::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Summary {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "text" -> {
                        return tryDeserialize(node, jacksonTypeRef<TextContent>())?.let {
                            Summary(text = it, _json = json)
                        } ?: Summary(_json = json)
                    }
                    "image" -> {
                        return tryDeserialize(node, jacksonTypeRef<ImageContent>())?.let {
                            Summary(image = it, _json = json)
                        } ?: Summary(_json = json)
                    }
                }

                return Summary(_json = json)
            }
        }

        internal class Serializer : BaseSerializer<Summary>(Summary::class) {

            override fun serialize(
                value: Summary,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.text != null -> generator.writeObject(value.text)
                    value.image != null -> generator.writeObject(value.image)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Summary")
                }
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ThoughtStep &&
            type == other.type &&
            signature == other.signature &&
            summary == other.summary &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(type, signature, summary, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "ThoughtStep{type=$type, signature=$signature, summary=$summary, additionalProperties=$additionalProperties}"
}
