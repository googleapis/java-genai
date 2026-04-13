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
import com.google.genai.interactions.core.allMaxBy
import com.google.genai.interactions.core.checkKnown
import com.google.genai.interactions.core.getOrThrow
import com.google.genai.interactions.core.toImmutable
import com.google.genai.interactions.errors.GeminiNextGenApiInvalidDataException
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** Configuration parameters for model interactions. */
class GenerationConfig
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val imageConfig: JsonField<ImageConfig>,
    private val maxOutputTokens: JsonField<Int>,
    private val seed: JsonField<Int>,
    private val speechConfig: JsonField<List<SpeechConfig>>,
    private val stopSequences: JsonField<List<String>>,
    private val temperature: JsonField<Float>,
    private val thinkingLevel: JsonField<ThinkingLevel>,
    private val thinkingSummaries: JsonField<ThinkingSummaries>,
    private val toolChoice: JsonField<ToolChoice>,
    private val topP: JsonField<Float>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("image_config")
        @ExcludeMissing
        imageConfig: JsonField<ImageConfig> = JsonMissing.of(),
        @JsonProperty("max_output_tokens")
        @ExcludeMissing
        maxOutputTokens: JsonField<Int> = JsonMissing.of(),
        @JsonProperty("seed") @ExcludeMissing seed: JsonField<Int> = JsonMissing.of(),
        @JsonProperty("speech_config")
        @ExcludeMissing
        speechConfig: JsonField<List<SpeechConfig>> = JsonMissing.of(),
        @JsonProperty("stop_sequences")
        @ExcludeMissing
        stopSequences: JsonField<List<String>> = JsonMissing.of(),
        @JsonProperty("temperature")
        @ExcludeMissing
        temperature: JsonField<Float> = JsonMissing.of(),
        @JsonProperty("thinking_level")
        @ExcludeMissing
        thinkingLevel: JsonField<ThinkingLevel> = JsonMissing.of(),
        @JsonProperty("thinking_summaries")
        @ExcludeMissing
        thinkingSummaries: JsonField<ThinkingSummaries> = JsonMissing.of(),
        @JsonProperty("tool_choice")
        @ExcludeMissing
        toolChoice: JsonField<ToolChoice> = JsonMissing.of(),
        @JsonProperty("top_p") @ExcludeMissing topP: JsonField<Float> = JsonMissing.of(),
    ) : this(
        imageConfig,
        maxOutputTokens,
        seed,
        speechConfig,
        stopSequences,
        temperature,
        thinkingLevel,
        thinkingSummaries,
        toolChoice,
        topP,
        mutableMapOf(),
    )

    /**
     * Configuration for image interaction.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun imageConfig(): Optional<ImageConfig> = imageConfig.getOptional("image_config")

    /**
     * The maximum number of tokens to include in the response.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun maxOutputTokens(): Optional<Int> = maxOutputTokens.getOptional("max_output_tokens")

    /**
     * Seed used in decoding for reproducibility.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun seed(): Optional<Int> = seed.getOptional("seed")

    /**
     * Configuration for speech interaction.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun speechConfig(): Optional<List<SpeechConfig>> = speechConfig.getOptional("speech_config")

    /**
     * A list of character sequences that will stop output interaction.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun stopSequences(): Optional<List<String>> = stopSequences.getOptional("stop_sequences")

    /**
     * Controls the randomness of the output.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun temperature(): Optional<Float> = temperature.getOptional("temperature")

    /**
     * The level of thought tokens that the model should generate.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun thinkingLevel(): Optional<ThinkingLevel> = thinkingLevel.getOptional("thinking_level")

    /**
     * Whether to include thought summaries in the response.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun thinkingSummaries(): Optional<ThinkingSummaries> =
        thinkingSummaries.getOptional("thinking_summaries")

    /**
     * The tool choice configuration.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun toolChoice(): Optional<ToolChoice> = toolChoice.getOptional("tool_choice")

    /**
     * The maximum cumulative probability of tokens to consider when sampling.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun topP(): Optional<Float> = topP.getOptional("top_p")

    /**
     * Returns the raw JSON value of [imageConfig].
     *
     * Unlike [imageConfig], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("image_config")
    @ExcludeMissing
    fun _imageConfig(): JsonField<ImageConfig> = imageConfig

    /**
     * Returns the raw JSON value of [maxOutputTokens].
     *
     * Unlike [maxOutputTokens], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("max_output_tokens")
    @ExcludeMissing
    fun _maxOutputTokens(): JsonField<Int> = maxOutputTokens

    /**
     * Returns the raw JSON value of [seed].
     *
     * Unlike [seed], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("seed") @ExcludeMissing fun _seed(): JsonField<Int> = seed

    /**
     * Returns the raw JSON value of [speechConfig].
     *
     * Unlike [speechConfig], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("speech_config")
    @ExcludeMissing
    fun _speechConfig(): JsonField<List<SpeechConfig>> = speechConfig

    /**
     * Returns the raw JSON value of [stopSequences].
     *
     * Unlike [stopSequences], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("stop_sequences")
    @ExcludeMissing
    fun _stopSequences(): JsonField<List<String>> = stopSequences

    /**
     * Returns the raw JSON value of [temperature].
     *
     * Unlike [temperature], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("temperature") @ExcludeMissing fun _temperature(): JsonField<Float> = temperature

    /**
     * Returns the raw JSON value of [thinkingLevel].
     *
     * Unlike [thinkingLevel], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("thinking_level")
    @ExcludeMissing
    fun _thinkingLevel(): JsonField<ThinkingLevel> = thinkingLevel

    /**
     * Returns the raw JSON value of [thinkingSummaries].
     *
     * Unlike [thinkingSummaries], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("thinking_summaries")
    @ExcludeMissing
    fun _thinkingSummaries(): JsonField<ThinkingSummaries> = thinkingSummaries

    /**
     * Returns the raw JSON value of [toolChoice].
     *
     * Unlike [toolChoice], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("tool_choice")
    @ExcludeMissing
    fun _toolChoice(): JsonField<ToolChoice> = toolChoice

    /**
     * Returns the raw JSON value of [topP].
     *
     * Unlike [topP], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("top_p") @ExcludeMissing fun _topP(): JsonField<Float> = topP

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

        /** Returns a mutable builder for constructing an instance of [GenerationConfig]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [GenerationConfig]. */
    class Builder internal constructor() {

        private var imageConfig: JsonField<ImageConfig> = JsonMissing.of()
        private var maxOutputTokens: JsonField<Int> = JsonMissing.of()
        private var seed: JsonField<Int> = JsonMissing.of()
        private var speechConfig: JsonField<MutableList<SpeechConfig>>? = null
        private var stopSequences: JsonField<MutableList<String>>? = null
        private var temperature: JsonField<Float> = JsonMissing.of()
        private var thinkingLevel: JsonField<ThinkingLevel> = JsonMissing.of()
        private var thinkingSummaries: JsonField<ThinkingSummaries> = JsonMissing.of()
        private var toolChoice: JsonField<ToolChoice> = JsonMissing.of()
        private var topP: JsonField<Float> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(generationConfig: GenerationConfig) = apply {
            imageConfig = generationConfig.imageConfig
            maxOutputTokens = generationConfig.maxOutputTokens
            seed = generationConfig.seed
            speechConfig = generationConfig.speechConfig.map { it.toMutableList() }
            stopSequences = generationConfig.stopSequences.map { it.toMutableList() }
            temperature = generationConfig.temperature
            thinkingLevel = generationConfig.thinkingLevel
            thinkingSummaries = generationConfig.thinkingSummaries
            toolChoice = generationConfig.toolChoice
            topP = generationConfig.topP
            additionalProperties = generationConfig.additionalProperties.toMutableMap()
        }

        /** Configuration for image interaction. */
        fun imageConfig(imageConfig: ImageConfig) = imageConfig(JsonField.of(imageConfig))

        /**
         * Sets [Builder.imageConfig] to an arbitrary JSON value.
         *
         * You should usually call [Builder.imageConfig] with a well-typed [ImageConfig] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun imageConfig(imageConfig: JsonField<ImageConfig>) = apply {
            this.imageConfig = imageConfig
        }

        /** The maximum number of tokens to include in the response. */
        fun maxOutputTokens(maxOutputTokens: Int) = maxOutputTokens(JsonField.of(maxOutputTokens))

        /**
         * Sets [Builder.maxOutputTokens] to an arbitrary JSON value.
         *
         * You should usually call [Builder.maxOutputTokens] with a well-typed [Int] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun maxOutputTokens(maxOutputTokens: JsonField<Int>) = apply {
            this.maxOutputTokens = maxOutputTokens
        }

        /** Seed used in decoding for reproducibility. */
        fun seed(seed: Int) = seed(JsonField.of(seed))

        /**
         * Sets [Builder.seed] to an arbitrary JSON value.
         *
         * You should usually call [Builder.seed] with a well-typed [Int] value instead. This method
         * is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun seed(seed: JsonField<Int>) = apply { this.seed = seed }

        /** Configuration for speech interaction. */
        fun speechConfig(speechConfig: List<SpeechConfig>) =
            speechConfig(JsonField.of(speechConfig))

        /**
         * Sets [Builder.speechConfig] to an arbitrary JSON value.
         *
         * You should usually call [Builder.speechConfig] with a well-typed `List<SpeechConfig>`
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun speechConfig(speechConfig: JsonField<List<SpeechConfig>>) = apply {
            this.speechConfig = speechConfig.map { it.toMutableList() }
        }

        /**
         * Adds a single [SpeechConfig] to [Builder.speechConfig].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addSpeechConfig(speechConfig: SpeechConfig) = apply {
            this.speechConfig =
                (this.speechConfig ?: JsonField.of(mutableListOf())).also {
                    checkKnown("speechConfig", it).add(speechConfig)
                }
        }

        /** A list of character sequences that will stop output interaction. */
        fun stopSequences(stopSequences: List<String>) = stopSequences(JsonField.of(stopSequences))

        /**
         * Sets [Builder.stopSequences] to an arbitrary JSON value.
         *
         * You should usually call [Builder.stopSequences] with a well-typed `List<String>` value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun stopSequences(stopSequences: JsonField<List<String>>) = apply {
            this.stopSequences = stopSequences.map { it.toMutableList() }
        }

        /**
         * Adds a single [String] to [stopSequences].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addStopSequence(stopSequence: String) = apply {
            stopSequences =
                (stopSequences ?: JsonField.of(mutableListOf())).also {
                    checkKnown("stopSequences", it).add(stopSequence)
                }
        }

        /** Controls the randomness of the output. */
        fun temperature(temperature: Float) = temperature(JsonField.of(temperature))

        /**
         * Sets [Builder.temperature] to an arbitrary JSON value.
         *
         * You should usually call [Builder.temperature] with a well-typed [Float] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun temperature(temperature: JsonField<Float>) = apply { this.temperature = temperature }

        /** The level of thought tokens that the model should generate. */
        fun thinkingLevel(thinkingLevel: ThinkingLevel) = thinkingLevel(JsonField.of(thinkingLevel))

        /**
         * Sets [Builder.thinkingLevel] to an arbitrary JSON value.
         *
         * You should usually call [Builder.thinkingLevel] with a well-typed [ThinkingLevel] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun thinkingLevel(thinkingLevel: JsonField<ThinkingLevel>) = apply {
            this.thinkingLevel = thinkingLevel
        }

        /** Whether to include thought summaries in the response. */
        fun thinkingSummaries(thinkingSummaries: ThinkingSummaries) =
            thinkingSummaries(JsonField.of(thinkingSummaries))

        /**
         * Sets [Builder.thinkingSummaries] to an arbitrary JSON value.
         *
         * You should usually call [Builder.thinkingSummaries] with a well-typed [ThinkingSummaries]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun thinkingSummaries(thinkingSummaries: JsonField<ThinkingSummaries>) = apply {
            this.thinkingSummaries = thinkingSummaries
        }

        /** The tool choice configuration. */
        fun toolChoice(toolChoice: ToolChoice) = toolChoice(JsonField.of(toolChoice))

        /**
         * Sets [Builder.toolChoice] to an arbitrary JSON value.
         *
         * You should usually call [Builder.toolChoice] with a well-typed [ToolChoice] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun toolChoice(toolChoice: JsonField<ToolChoice>) = apply { this.toolChoice = toolChoice }

        /** Alias for calling [toolChoice] with `ToolChoice.ofType(type)`. */
        fun toolChoice(type: ToolChoiceType) = toolChoice(ToolChoice.ofType(type))

        /** Alias for calling [toolChoice] with `ToolChoice.ofConfig(config)`. */
        fun toolChoice(config: ToolChoiceConfig) = toolChoice(ToolChoice.ofConfig(config))

        /** The maximum cumulative probability of tokens to consider when sampling. */
        fun topP(topP: Float) = topP(JsonField.of(topP))

        /**
         * Sets [Builder.topP] to an arbitrary JSON value.
         *
         * You should usually call [Builder.topP] with a well-typed [Float] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun topP(topP: JsonField<Float>) = apply { this.topP = topP }

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
         * Returns an immutable instance of [GenerationConfig].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): GenerationConfig =
            GenerationConfig(
                imageConfig,
                maxOutputTokens,
                seed,
                (speechConfig ?: JsonMissing.of()).map { it.toImmutable() },
                (stopSequences ?: JsonMissing.of()).map { it.toImmutable() },
                temperature,
                thinkingLevel,
                thinkingSummaries,
                toolChoice,
                topP,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): GenerationConfig = apply {
        if (validated) {
            return@apply
        }

        imageConfig().ifPresent { it.validate() }
        maxOutputTokens()
        seed()
        speechConfig().ifPresent { it.forEach { it.validate() } }
        stopSequences()
        temperature()
        thinkingLevel().ifPresent { it.validate() }
        thinkingSummaries().ifPresent { it.validate() }
        toolChoice().ifPresent { it.validate() }
        topP()
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
        (imageConfig.asKnown().getOrNull()?.validity() ?: 0) +
            (if (maxOutputTokens.asKnown().isPresent) 1 else 0) +
            (if (seed.asKnown().isPresent) 1 else 0) +
            (speechConfig.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (stopSequences.asKnown().getOrNull()?.size ?: 0) +
            (if (temperature.asKnown().isPresent) 1 else 0) +
            (thinkingLevel.asKnown().getOrNull()?.validity() ?: 0) +
            (thinkingSummaries.asKnown().getOrNull()?.validity() ?: 0) +
            (toolChoice.asKnown().getOrNull()?.validity() ?: 0) +
            (if (topP.asKnown().isPresent) 1 else 0)

    /** Whether to include thought summaries in the response. */
    class ThinkingSummaries @JsonCreator private constructor(private val value: JsonField<String>) :
        Enum {

        /**
         * Returns this class instance's raw value.
         *
         * This is usually only useful if this instance was deserialized from data that doesn't
         * match any known member, and you want to know that value. For example, if the SDK is on an
         * older version than the API, then the API may respond with new members that the SDK is
         * unaware of.
         */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val AUTO = of("auto")

            @JvmField val NONE = of("none")

            @JvmStatic fun of(value: String) = ThinkingSummaries(JsonField.of(value))
        }

        /** An enum containing [ThinkingSummaries]'s known values. */
        enum class Known {
            AUTO,
            NONE,
        }

        /**
         * An enum containing [ThinkingSummaries]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [ThinkingSummaries] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            AUTO,
            NONE,
            /**
             * An enum member indicating that [ThinkingSummaries] was instantiated with an unknown
             * value.
             */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         *
         * Use the [known] method instead if you're certain the value is always known or if you want
         * to throw for the unknown case.
         */
        fun value(): Value =
            when (this) {
                AUTO -> Value.AUTO
                NONE -> Value.NONE
                else -> Value._UNKNOWN
            }

        /**
         * Returns an enum member corresponding to this class instance's value.
         *
         * Use the [value] method instead if you're uncertain the value is always known and don't
         * want to throw for the unknown case.
         *
         * @throws GeminiNextGenApiInvalidDataException if this class instance's value is a not a
         *   known member.
         */
        fun known(): Known =
            when (this) {
                AUTO -> Known.AUTO
                NONE -> Known.NONE
                else ->
                    throw GeminiNextGenApiInvalidDataException("Unknown ThinkingSummaries: $value")
            }

        /**
         * Returns this class instance's primitive wire representation.
         *
         * This differs from the [toString] method because that method is primarily for debugging
         * and generally doesn't throw.
         *
         * @throws GeminiNextGenApiInvalidDataException if this class instance's value does not have
         *   the expected primitive type.
         */
        fun asString(): String =
            _value().asString().orElseThrow {
                GeminiNextGenApiInvalidDataException("Value is not a String")
            }

        private var validated: Boolean = false

        fun validate(): ThinkingSummaries = apply {
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

            return other is ThinkingSummaries && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    /** The tool choice configuration. */
    @JsonDeserialize(using = ToolChoice.Deserializer::class)
    @JsonSerialize(using = ToolChoice.Serializer::class)
    class ToolChoice
    private constructor(
        private val type: ToolChoiceType? = null,
        private val config: ToolChoiceConfig? = null,
        private val _json: JsonValue? = null,
    ) {

        fun type(): Optional<ToolChoiceType> = Optional.ofNullable(type)

        /** The tool choice configuration containing allowed tools. */
        fun config(): Optional<ToolChoiceConfig> = Optional.ofNullable(config)

        fun isType(): Boolean = type != null

        fun isConfig(): Boolean = config != null

        fun asType(): ToolChoiceType = type.getOrThrow("type")

        /** The tool choice configuration containing allowed tools. */
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
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
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

            /** The tool choice configuration containing allowed tools. */
            @JvmStatic fun ofConfig(config: ToolChoiceConfig) = ToolChoice(config = config)
        }

        /**
         * An interface that defines how to map each variant of [ToolChoice] to a value of type [T].
         */
        interface Visitor<out T> {

            fun visitType(type: ToolChoiceType): T

            /** The tool choice configuration containing allowed tools. */
            fun visitConfig(config: ToolChoiceConfig): T

            /**
             * Maps an unknown variant of [ToolChoice] to a value of type [T].
             *
             * An instance of [ToolChoice] can contain an unknown variant if it was deserialized
             * from data that doesn't match any known variant. For example, if the SDK is on an
             * older version than the API, then the API may respond with new variants that the SDK
             * is unaware of.
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
                    // This can happen if what we're deserializing is completely incompatible with
                    // all the possible variants (e.g. deserializing from boolean).
                    0 -> ToolChoice(_json = json)
                    1 -> bestMatches.single()
                    // If there's more than one match with the highest validity, then use the first
                    // completely valid match, or simply the first match if none are completely
                    // valid.
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

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is GenerationConfig &&
            imageConfig == other.imageConfig &&
            maxOutputTokens == other.maxOutputTokens &&
            seed == other.seed &&
            speechConfig == other.speechConfig &&
            stopSequences == other.stopSequences &&
            temperature == other.temperature &&
            thinkingLevel == other.thinkingLevel &&
            thinkingSummaries == other.thinkingSummaries &&
            toolChoice == other.toolChoice &&
            topP == other.topP &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            imageConfig,
            maxOutputTokens,
            seed,
            speechConfig,
            stopSequences,
            temperature,
            thinkingLevel,
            thinkingSummaries,
            toolChoice,
            topP,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "GenerationConfig{imageConfig=$imageConfig, maxOutputTokens=$maxOutputTokens, seed=$seed, speechConfig=$speechConfig, stopSequences=$stopSequences, temperature=$temperature, thinkingLevel=$thinkingLevel, thinkingSummaries=$thinkingSummaries, toolChoice=$toolChoice, topP=$topP, additionalProperties=$additionalProperties}"
}
