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

/** Configuration for audio output format. */
class AudioResponseFormat
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val type: JsonValue,
    private val bitRate: JsonField<Int>,
    private val delivery: JsonField<Delivery>,
    private val mimeType: JsonField<MimeType>,
    private val sampleRate: JsonField<Int>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("bitRate") @ExcludeMissing bitRate: JsonField<Int> = JsonMissing.of(),
        @JsonProperty("delivery") @ExcludeMissing delivery: JsonField<Delivery> = JsonMissing.of(),
        @JsonProperty("mimeType") @ExcludeMissing mimeType: JsonField<MimeType> = JsonMissing.of(),
        @JsonProperty("sampleRate") @ExcludeMissing sampleRate: JsonField<Int> = JsonMissing.of(),
    ) : this(type, bitRate, delivery, mimeType, sampleRate, mutableMapOf())

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("audio")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Bit rate in bits per second (bps). Only applicable for compressed formats (MP3, Opus).
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun bitRate(): Optional<Int> = bitRate.getOptional("bitRate")

    /**
     * The delivery mode for the audio output.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun delivery(): Optional<Delivery> = delivery.getOptional("delivery")

    /**
     * The MIME type of the audio output.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun mimeType(): Optional<MimeType> = mimeType.getOptional("mimeType")

    /**
     * Sample rate in Hz.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun sampleRate(): Optional<Int> = sampleRate.getOptional("sampleRate")

    /**
     * Returns the raw JSON value of [bitRate].
     *
     * Unlike [bitRate], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("bitRate") @ExcludeMissing fun _bitRate(): JsonField<Int> = bitRate

    /**
     * Returns the raw JSON value of [delivery].
     *
     * Unlike [delivery], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("delivery") @ExcludeMissing fun _delivery(): JsonField<Delivery> = delivery

    /**
     * Returns the raw JSON value of [mimeType].
     *
     * Unlike [mimeType], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("mimeType") @ExcludeMissing fun _mimeType(): JsonField<MimeType> = mimeType

    /**
     * Returns the raw JSON value of [sampleRate].
     *
     * Unlike [sampleRate], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("sampleRate") @ExcludeMissing fun _sampleRate(): JsonField<Int> = sampleRate

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

        /** Returns a mutable builder for constructing an instance of [AudioResponseFormat]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [AudioResponseFormat]. */
    class Builder internal constructor() {

        private var type: JsonValue = JsonValue.from("audio")
        private var bitRate: JsonField<Int> = JsonMissing.of()
        private var delivery: JsonField<Delivery> = JsonMissing.of()
        private var mimeType: JsonField<MimeType> = JsonMissing.of()
        private var sampleRate: JsonField<Int> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(audioResponseFormat: AudioResponseFormat) = apply {
            type = audioResponseFormat.type
            bitRate = audioResponseFormat.bitRate
            delivery = audioResponseFormat.delivery
            mimeType = audioResponseFormat.mimeType
            sampleRate = audioResponseFormat.sampleRate
            additionalProperties = audioResponseFormat.additionalProperties.toMutableMap()
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
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /**
         * Bit rate in bits per second (bps). Only applicable for compressed formats (MP3, Opus).
         */
        fun bitRate(bitRate: Int) = bitRate(JsonField.of(bitRate))

        /**
         * Sets [Builder.bitRate] to an arbitrary JSON value.
         *
         * You should usually call [Builder.bitRate] with a well-typed [Int] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun bitRate(bitRate: JsonField<Int>) = apply { this.bitRate = bitRate }

        /** The delivery mode for the audio output. */
        fun delivery(delivery: Delivery) = delivery(JsonField.of(delivery))

        /**
         * Sets [Builder.delivery] to an arbitrary JSON value.
         *
         * You should usually call [Builder.delivery] with a well-typed [Delivery] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun delivery(delivery: JsonField<Delivery>) = apply { this.delivery = delivery }

        /** The MIME type of the audio output. */
        fun mimeType(mimeType: MimeType) = mimeType(JsonField.of(mimeType))

        /**
         * Sets [Builder.mimeType] to an arbitrary JSON value.
         *
         * You should usually call [Builder.mimeType] with a well-typed [MimeType] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun mimeType(mimeType: JsonField<MimeType>) = apply { this.mimeType = mimeType }

        /** Sample rate in Hz. */
        fun sampleRate(sampleRate: Int) = sampleRate(JsonField.of(sampleRate))

        /**
         * Sets [Builder.sampleRate] to an arbitrary JSON value.
         *
         * You should usually call [Builder.sampleRate] with a well-typed [Int] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun sampleRate(sampleRate: JsonField<Int>) = apply { this.sampleRate = sampleRate }

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
         * Returns an immutable instance of [AudioResponseFormat].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): AudioResponseFormat =
            AudioResponseFormat(
                type,
                bitRate,
                delivery,
                mimeType,
                sampleRate,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): AudioResponseFormat = apply {
        if (validated) {
            return@apply
        }

        _type().let {
            if (it != JsonValue.from("audio")) {
                throw GeminiNextGenApiInvalidDataException("'type' is invalid, received $it")
            }
        }
        bitRate()
        delivery().ifPresent { it.validate() }
        mimeType().ifPresent { it.validate() }
        sampleRate()
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
        type.let { if (it == JsonValue.from("audio")) 1 else 0 } +
            (if (bitRate.asKnown().isPresent) 1 else 0) +
            (delivery.asKnown().getOrNull()?.validity() ?: 0) +
            (mimeType.asKnown().getOrNull()?.validity() ?: 0) +
            (if (sampleRate.asKnown().isPresent) 1 else 0)

    /** The delivery mode for the audio output. */
    class Delivery @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

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

            @JvmField val INLINE = of("inline")

            @JvmField val URL = of("url")

            @JvmStatic fun of(value: String) = Delivery(JsonField.of(value))
        }

        /** An enum containing [Delivery]'s known values. */
        enum class Known {
            INLINE,
            URL,
        }

        /**
         * An enum containing [Delivery]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Delivery] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            INLINE,
            URL,
            /** An enum member indicating that [Delivery] was instantiated with an unknown value. */
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
                INLINE -> Value.INLINE
                URL -> Value.URL
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
                INLINE -> Known.INLINE
                URL -> Known.URL
                else -> throw GeminiNextGenApiInvalidDataException("Unknown Delivery: $value")
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

        fun validate(): Delivery = apply {
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

            return other is Delivery && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    /** The MIME type of the audio output. */
    class MimeType @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

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

            @JvmField val AUDIO_MP3 = of("audio/mp3")

            @JvmField val AUDIO_OGG_OPUS = of("audio/ogg_opus")

            @JvmField val AUDIO_L16 = of("audio/l16")

            @JvmField val AUDIO_WAV = of("audio/wav")

            @JvmField val AUDIO_ALAW = of("audio/alaw")

            @JvmField val AUDIO_MULAW = of("audio/mulaw")

            @JvmStatic fun of(value: String) = MimeType(JsonField.of(value))
        }

        /** An enum containing [MimeType]'s known values. */
        enum class Known {
            AUDIO_MP3,
            AUDIO_OGG_OPUS,
            AUDIO_L16,
            AUDIO_WAV,
            AUDIO_ALAW,
            AUDIO_MULAW,
        }

        /**
         * An enum containing [MimeType]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [MimeType] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            AUDIO_MP3,
            AUDIO_OGG_OPUS,
            AUDIO_L16,
            AUDIO_WAV,
            AUDIO_ALAW,
            AUDIO_MULAW,
            /** An enum member indicating that [MimeType] was instantiated with an unknown value. */
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
                AUDIO_MP3 -> Value.AUDIO_MP3
                AUDIO_OGG_OPUS -> Value.AUDIO_OGG_OPUS
                AUDIO_L16 -> Value.AUDIO_L16
                AUDIO_WAV -> Value.AUDIO_WAV
                AUDIO_ALAW -> Value.AUDIO_ALAW
                AUDIO_MULAW -> Value.AUDIO_MULAW
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
                AUDIO_MP3 -> Known.AUDIO_MP3
                AUDIO_OGG_OPUS -> Known.AUDIO_OGG_OPUS
                AUDIO_L16 -> Known.AUDIO_L16
                AUDIO_WAV -> Known.AUDIO_WAV
                AUDIO_ALAW -> Known.AUDIO_ALAW
                AUDIO_MULAW -> Known.AUDIO_MULAW
                else -> throw GeminiNextGenApiInvalidDataException("Unknown MimeType: $value")
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

        return other is AudioResponseFormat &&
            type == other.type &&
            bitRate == other.bitRate &&
            delivery == other.delivery &&
            mimeType == other.mimeType &&
            sampleRate == other.sampleRate &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(type, bitRate, delivery, mimeType, sampleRate, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "AudioResponseFormat{type=$type, bitRate=$bitRate, delivery=$delivery, mimeType=$mimeType, sampleRate=$sampleRate, additionalProperties=$additionalProperties}"
}
