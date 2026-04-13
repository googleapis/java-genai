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

/** Citation information for model-generated content. */
@JsonDeserialize(using = Annotation.Deserializer::class)
@JsonSerialize(using = Annotation.Serializer::class)
class Annotation
private constructor(
    private val urlCitation: UrlCitation? = null,
    private val fileCitation: FileCitation? = null,
    private val placeCitation: PlaceCitation? = null,
    private val _json: JsonValue? = null,
) {

    /** A URL citation annotation. */
    fun urlCitation(): Optional<UrlCitation> = Optional.ofNullable(urlCitation)

    /** A file citation annotation. */
    fun fileCitation(): Optional<FileCitation> = Optional.ofNullable(fileCitation)

    /** A place citation annotation. */
    fun placeCitation(): Optional<PlaceCitation> = Optional.ofNullable(placeCitation)

    fun isUrlCitation(): Boolean = urlCitation != null

    fun isFileCitation(): Boolean = fileCitation != null

    fun isPlaceCitation(): Boolean = placeCitation != null

    /** A URL citation annotation. */
    fun asUrlCitation(): UrlCitation = urlCitation.getOrThrow("urlCitation")

    /** A file citation annotation. */
    fun asFileCitation(): FileCitation = fileCitation.getOrThrow("fileCitation")

    /** A place citation annotation. */
    fun asPlaceCitation(): PlaceCitation = placeCitation.getOrThrow("placeCitation")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T =
        when {
            urlCitation != null -> visitor.visitUrlCitation(urlCitation)
            fileCitation != null -> visitor.visitFileCitation(fileCitation)
            placeCitation != null -> visitor.visitPlaceCitation(placeCitation)
            else -> visitor.unknown(_json)
        }

    private var validated: Boolean = false

    fun validate(): Annotation = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitUrlCitation(urlCitation: UrlCitation) {
                    urlCitation.validate()
                }

                override fun visitFileCitation(fileCitation: FileCitation) {
                    fileCitation.validate()
                }

                override fun visitPlaceCitation(placeCitation: PlaceCitation) {
                    placeCitation.validate()
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
                override fun visitUrlCitation(urlCitation: UrlCitation) = urlCitation.validity()

                override fun visitFileCitation(fileCitation: FileCitation) = fileCitation.validity()

                override fun visitPlaceCitation(placeCitation: PlaceCitation) =
                    placeCitation.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is Annotation &&
            urlCitation == other.urlCitation &&
            fileCitation == other.fileCitation &&
            placeCitation == other.placeCitation
    }

    override fun hashCode(): Int = Objects.hash(urlCitation, fileCitation, placeCitation)

    override fun toString(): String =
        when {
            urlCitation != null -> "Annotation{urlCitation=$urlCitation}"
            fileCitation != null -> "Annotation{fileCitation=$fileCitation}"
            placeCitation != null -> "Annotation{placeCitation=$placeCitation}"
            _json != null -> "Annotation{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid Annotation")
        }

    companion object {

        /** A URL citation annotation. */
        @JvmStatic
        fun ofUrlCitation(urlCitation: UrlCitation) = Annotation(urlCitation = urlCitation)

        /** A file citation annotation. */
        @JvmStatic
        fun ofFileCitation(fileCitation: FileCitation) = Annotation(fileCitation = fileCitation)

        /** A place citation annotation. */
        @JvmStatic
        fun ofPlaceCitation(placeCitation: PlaceCitation) =
            Annotation(placeCitation = placeCitation)
    }

    /** An interface that defines how to map each variant of [Annotation] to a value of type [T]. */
    interface Visitor<out T> {

        /** A URL citation annotation. */
        fun visitUrlCitation(urlCitation: UrlCitation): T

        /** A file citation annotation. */
        fun visitFileCitation(fileCitation: FileCitation): T

        /** A place citation annotation. */
        fun visitPlaceCitation(placeCitation: PlaceCitation): T

        /**
         * Maps an unknown variant of [Annotation] to a value of type [T].
         *
         * An instance of [Annotation] can contain an unknown variant if it was deserialized from
         * data that doesn't match any known variant. For example, if the SDK is on an older version
         * than the API, then the API may respond with new variants that the SDK is unaware of.
         *
         * @throws GeminiNextGenApiInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw GeminiNextGenApiInvalidDataException("Unknown Annotation: $json")
        }
    }

    internal class Deserializer : BaseDeserializer<Annotation>(Annotation::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): Annotation {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "url_citation" -> {
                    return tryDeserialize(node, jacksonTypeRef<UrlCitation>())?.let {
                        Annotation(urlCitation = it, _json = json)
                    } ?: Annotation(_json = json)
                }
                "file_citation" -> {
                    return tryDeserialize(node, jacksonTypeRef<FileCitation>())?.let {
                        Annotation(fileCitation = it, _json = json)
                    } ?: Annotation(_json = json)
                }
                "place_citation" -> {
                    return tryDeserialize(node, jacksonTypeRef<PlaceCitation>())?.let {
                        Annotation(placeCitation = it, _json = json)
                    } ?: Annotation(_json = json)
                }
            }

            return Annotation(_json = json)
        }
    }

    internal class Serializer : BaseSerializer<Annotation>(Annotation::class) {

        override fun serialize(
            value: Annotation,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.urlCitation != null -> generator.writeObject(value.urlCitation)
                value.fileCitation != null -> generator.writeObject(value.fileCitation)
                value.placeCitation != null -> generator.writeObject(value.placeCitation)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid Annotation")
            }
        }
    }
}
