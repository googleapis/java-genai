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
import com.google.genai.interactions.core.allMaxBy
import com.google.genai.interactions.core.getOrThrow
import com.google.genai.interactions.core.toImmutable
import com.google.genai.interactions.errors.GeminiNextGenApiInvalidDataException
import java.util.Objects
import java.util.Optional

@JsonDeserialize(using = TurnContent.Deserializer::class)
@JsonSerialize(using = TurnContent.Serializer::class)
class TurnContent
private constructor(
    private val contentList: List<Content>? = null,
    private val string: String? = null,
    private val _json: JsonValue? = null,
) {

    fun contentList(): Optional<List<Content>> = Optional.ofNullable(contentList)

    fun string(): Optional<String> = Optional.ofNullable(string)

    fun isContentList(): Boolean = contentList != null

    fun isString(): Boolean = string != null

    fun asContentList(): List<Content> = contentList.getOrThrow("contentList")

    fun asString(): String = string.getOrThrow("string")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T =
        when {
            contentList != null -> visitor.visitContentList(contentList)
            string != null -> visitor.visitString(string)
            else -> visitor.unknown(_json)
        }

    private var validated: Boolean = false

    fun validate(): TurnContent = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitContentList(contentList: List<Content>) {
                    contentList.forEach { it.validate() }
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
     * Returns a score indicating how many valid values are contained in this object recursively.
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

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is TurnContent && contentList == other.contentList && string == other.string
    }

    override fun hashCode(): Int = Objects.hash(contentList, string)

    override fun toString(): String =
        when {
            contentList != null -> "TurnContent{contentList=$contentList}"
            string != null -> "TurnContent{string=$string}"
            _json != null -> "TurnContent{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid TurnContent")
        }

    companion object {

        @JvmStatic
        fun ofContentList(contentList: List<Content>) =
            TurnContent(contentList = contentList.toImmutable())

        @JvmStatic fun ofString(string: String) = TurnContent(string = string)
    }

    /**
     * An interface that defines how to map each variant of [TurnContent] to a value of type [T].
     */
    interface Visitor<out T> {

        fun visitContentList(contentList: List<Content>): T

        fun visitString(string: String): T

        /**
         * Maps an unknown variant of [TurnContent] to a value of type [T].
         *
         * An instance of [TurnContent] can contain an unknown variant if it was deserialized from
         * data that doesn't match any known variant. For example, if the SDK is on an older version
         * than the API, then the API may respond with new variants that the SDK is unaware of.
         *
         * @throws GeminiNextGenApiInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw GeminiNextGenApiInvalidDataException("Unknown TurnContent: $json")
        }
    }

    internal class Deserializer : BaseDeserializer<TurnContent>(TurnContent::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): TurnContent {
            val json = JsonValue.fromJsonNode(node)

            val bestMatches =
                sequenceOf(
                        tryDeserialize(node, jacksonTypeRef<String>())?.let {
                            TurnContent(string = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<List<Content>>())?.let {
                            TurnContent(contentList = it, _json = json)
                        },
                    )
                    .filterNotNull()
                    .allMaxBy { it.validity() }
                    .toList()
            return when (bestMatches.size) {
                // This can happen if what we're deserializing is completely incompatible with all
                // the possible variants (e.g. deserializing from boolean).
                0 -> TurnContent(_json = json)
                1 -> bestMatches.single()
                // If there's more than one match with the highest validity, then use the first
                // completely valid match, or simply the first match if none are completely valid.
                else -> bestMatches.firstOrNull { it.isValid() } ?: bestMatches.first()
            }
        }
    }

    internal class Serializer : BaseSerializer<TurnContent>(TurnContent::class) {

        override fun serialize(
            value: TurnContent,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.contentList != null -> generator.writeObject(value.contentList)
                value.string != null -> generator.writeObject(value.string)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid TurnContent")
            }
        }
    }
}
