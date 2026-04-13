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
import com.google.genai.interactions.errors.GeminiNextGenApiInvalidDataException
import java.util.Objects
import java.util.Optional

@JsonDeserialize(using = ToolChoice.Deserializer::class)
@JsonSerialize(using = ToolChoice.Serializer::class)
class ToolChoice
private constructor(
    private val type: ToolChoiceType? = null,
    private val config: ToolChoiceConfig? = null,
    private val _json: JsonValue? = null,
) {

    fun type(): Optional<ToolChoiceType> = Optional.ofNullable(type)

    fun config(): Optional<ToolChoiceConfig> = Optional.ofNullable(config)

    fun isType(): Boolean = type != null

    fun isConfig(): Boolean = config != null

    fun asType(): ToolChoiceType = type.getOrThrow("type")

    fun asConfig(): ToolChoiceConfig = config.getOrThrow("config")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T =
        when {
            type != null -> visitor.visitType(type)
            config != null -> visitor.visitConfig(config)
            else -> visitor.unknown(_json)
        }

    private var validated: Boolean = false

    fun validate(): ToolChoice = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitType(type: ToolChoiceType) {
                    type.validate()
                }

                override fun visitConfig(config: ToolChoiceConfig) {
                    config.validate()
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
                override fun visitType(type: ToolChoiceType) = type.validity()

                override fun visitConfig(config: ToolChoiceConfig) = config.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ToolChoice && type == other.type && config == other.config
    }

    override fun hashCode(): Int = Objects.hash(type, config)

    override fun toString(): String =
        when {
            type != null -> "ToolChoice{type=$type}"
            config != null -> "ToolChoice{config=$config}"
            _json != null -> "ToolChoice{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid ToolChoice")
        }

    companion object {

        @JvmStatic fun ofType(type: ToolChoiceType) = ToolChoice(type = type)

        @JvmStatic fun ofConfig(config: ToolChoiceConfig) = ToolChoice(config = config)
    }

    /** An interface that defines how to map each variant of [ToolChoice] to a value of type [T]. */
    interface Visitor<out T> {

        fun visitType(type: ToolChoiceType): T

        fun visitConfig(config: ToolChoiceConfig): T

        /**
         * Maps an unknown variant of [ToolChoice] to a value of type [T].
         *
         * An instance of [ToolChoice] can contain an unknown variant if it was deserialized from
         * data that doesn't match any known variant. For example, if the SDK is on an older version
         * than the API, then the API may respond with new variants that the SDK is unaware of.
         *
         * @throws GeminiNextGenApiInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw GeminiNextGenApiInvalidDataException("Unknown ToolChoice: $json")
        }
    }

    internal class Deserializer : BaseDeserializer<ToolChoice>(ToolChoice::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): ToolChoice {
            val json = JsonValue.fromJsonNode(node)

            val bestMatches =
                sequenceOf(
                        tryDeserialize(node, jacksonTypeRef<ToolChoiceType>())?.let {
                            ToolChoice(type = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<ToolChoiceConfig>())?.let {
                            ToolChoice(config = it, _json = json)
                        },
                    )
                    .filterNotNull()
                    .allMaxBy { it.validity() }
                    .toList()
            return when (bestMatches.size) {
                // This can happen if what we're deserializing is completely incompatible with all
                // the possible variants (e.g. deserializing from boolean).
                0 -> ToolChoice(_json = json)
                1 -> bestMatches.single()
                // If there's more than one match with the highest validity, then use the first
                // completely valid match, or simply the first match if none are completely valid.
                else -> bestMatches.firstOrNull { it.isValid() } ?: bestMatches.first()
            }
        }
    }

    internal class Serializer : BaseSerializer<ToolChoice>(ToolChoice::class) {

        override fun serialize(
            value: ToolChoice,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.type != null -> generator.writeObject(value.type)
                value.config != null -> generator.writeObject(value.config)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid ToolChoice")
            }
        }
    }
}
