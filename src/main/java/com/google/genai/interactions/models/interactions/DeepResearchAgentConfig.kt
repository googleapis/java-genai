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
import com.google.genai.interactions.core.Enum
import com.google.genai.interactions.core.ExcludeMissing
import com.google.genai.interactions.core.JsonField
import com.google.genai.interactions.core.JsonMissing
import com.google.genai.interactions.core.JsonValue
import com.google.genai.interactions.errors.GeminiNextGenApiInvalidDataException
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** Configuration for the Deep Research agent. */
class DeepResearchAgentConfig
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val type: JsonValue,
    private val collaborativePlanning: JsonField<Boolean>,
    private val thinkingSummaries: JsonField<ThinkingSummaries>,
    private val visualization: JsonField<Visualization>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("collaborative_planning")
        @ExcludeMissing
        collaborativePlanning: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("thinking_summaries")
        @ExcludeMissing
        thinkingSummaries: JsonField<ThinkingSummaries> = JsonMissing.of(),
        @JsonProperty("visualization")
        @ExcludeMissing
        visualization: JsonField<Visualization> = JsonMissing.of(),
    ) : this(type, collaborativePlanning, thinkingSummaries, visualization, mutableMapOf())

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("deep-research")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Enables human-in-the-loop planning for the Deep Research agent. If set to true, the Deep
     * Research agent will provide a research plan in its response. The agent will then proceed only
     * if the user confirms the plan in the next turn. Relevant issue: b/482352502.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun collaborativePlanning(): Optional<Boolean> =
        collaborativePlanning.getOptional("collaborative_planning")

    /**
     * Whether to include thought summaries in the response.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun thinkingSummaries(): Optional<ThinkingSummaries> =
        thinkingSummaries.getOptional("thinking_summaries")

    /**
     * Whether to include visualizations in the response.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun visualization(): Optional<Visualization> = visualization.getOptional("visualization")

    /**
     * Returns the raw JSON value of [collaborativePlanning].
     *
     * Unlike [collaborativePlanning], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("collaborative_planning")
    @ExcludeMissing
    fun _collaborativePlanning(): JsonField<Boolean> = collaborativePlanning

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
     * Returns the raw JSON value of [visualization].
     *
     * Unlike [visualization], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("visualization")
    @ExcludeMissing
    fun _visualization(): JsonField<Visualization> = visualization

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

        /** Returns a mutable builder for constructing an instance of [DeepResearchAgentConfig]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [DeepResearchAgentConfig]. */
    class Builder internal constructor() {

        private var type: JsonValue = JsonValue.from("deep-research")
        private var collaborativePlanning: JsonField<Boolean> = JsonMissing.of()
        private var thinkingSummaries: JsonField<ThinkingSummaries> = JsonMissing.of()
        private var visualization: JsonField<Visualization> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(deepResearchAgentConfig: DeepResearchAgentConfig) = apply {
            type = deepResearchAgentConfig.type
            collaborativePlanning = deepResearchAgentConfig.collaborativePlanning
            thinkingSummaries = deepResearchAgentConfig.thinkingSummaries
            visualization = deepResearchAgentConfig.visualization
            additionalProperties = deepResearchAgentConfig.additionalProperties.toMutableMap()
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("deep-research")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /**
         * Enables human-in-the-loop planning for the Deep Research agent. If set to true, the Deep
         * Research agent will provide a research plan in its response. The agent will then proceed
         * only if the user confirms the plan in the next turn. Relevant issue: b/482352502.
         */
        fun collaborativePlanning(collaborativePlanning: Boolean) =
            collaborativePlanning(JsonField.of(collaborativePlanning))

        /**
         * Sets [Builder.collaborativePlanning] to an arbitrary JSON value.
         *
         * You should usually call [Builder.collaborativePlanning] with a well-typed [Boolean] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun collaborativePlanning(collaborativePlanning: JsonField<Boolean>) = apply {
            this.collaborativePlanning = collaborativePlanning
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

        /** Whether to include visualizations in the response. */
        fun visualization(visualization: Visualization) = visualization(JsonField.of(visualization))

        /**
         * Sets [Builder.visualization] to an arbitrary JSON value.
         *
         * You should usually call [Builder.visualization] with a well-typed [Visualization] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun visualization(visualization: JsonField<Visualization>) = apply {
            this.visualization = visualization
        }

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
         * Returns an immutable instance of [DeepResearchAgentConfig].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): DeepResearchAgentConfig =
            DeepResearchAgentConfig(
                type,
                collaborativePlanning,
                thinkingSummaries,
                visualization,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): DeepResearchAgentConfig = apply {
        if (validated) {
            return@apply
        }

        _type().let {
            if (it != JsonValue.from("deep-research")) {
                throw GeminiNextGenApiInvalidDataException("'type' is invalid, received $it")
            }
        }
        collaborativePlanning()
        thinkingSummaries().ifPresent { it.validate() }
        visualization().ifPresent { it.validate() }
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
        type.let { if (it == JsonValue.from("deep-research")) 1 else 0 } +
            (if (collaborativePlanning.asKnown().isPresent) 1 else 0) +
            (thinkingSummaries.asKnown().getOrNull()?.validity() ?: 0) +
            (visualization.asKnown().getOrNull()?.validity() ?: 0)

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

    /** Whether to include visualizations in the response. */
    class Visualization @JsonCreator private constructor(private val value: JsonField<String>) :
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

            @JvmField val OFF = of("off")

            @JvmField val AUTO = of("auto")

            @JvmStatic fun of(value: String) = Visualization(JsonField.of(value))
        }

        /** An enum containing [Visualization]'s known values. */
        enum class Known {
            OFF,
            AUTO,
        }

        /**
         * An enum containing [Visualization]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Visualization] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            OFF,
            AUTO,
            /**
             * An enum member indicating that [Visualization] was instantiated with an unknown
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
                OFF -> Value.OFF
                AUTO -> Value.AUTO
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
                OFF -> Known.OFF
                AUTO -> Known.AUTO
                else -> throw GeminiNextGenApiInvalidDataException("Unknown Visualization: $value")
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

        fun validate(): Visualization = apply {
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

            return other is Visualization && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is DeepResearchAgentConfig &&
            type == other.type &&
            collaborativePlanning == other.collaborativePlanning &&
            thinkingSummaries == other.thinkingSummaries &&
            visualization == other.visualization &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            type,
            collaborativePlanning,
            thinkingSummaries,
            visualization,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "DeepResearchAgentConfig{type=$type, collaborativePlanning=$collaborativePlanning, thinkingSummaries=$thinkingSummaries, visualization=$visualization, additionalProperties=$additionalProperties}"
}
