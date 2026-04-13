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

/** An audio content block. */
class AudioContent
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val type: JsonValue,
    private val channels: JsonField<Int>,
    private val data: JsonField<String>,
    private val mimeType: JsonField<MimeType>,
    private val rate: JsonField<Int>,
    private val uri: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("channels") @ExcludeMissing channels: JsonField<Int> = JsonMissing.of(),
        @JsonProperty("data") @ExcludeMissing data: JsonField<String> = JsonMissing.of(),
        @JsonProperty("mime_type") @ExcludeMissing mimeType: JsonField<MimeType> = JsonMissing.of(),
        @JsonProperty("rate") @ExcludeMissing rate: JsonField<Int> = JsonMissing.of(),
        @JsonProperty("uri") @ExcludeMissing uri: JsonField<String> = JsonMissing.of(),
    ) : this(type, channels, data, mimeType, rate, uri, mutableMapOf())

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
     * The number of audio channels.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun channels(): Optional<Int> = channels.getOptional("channels")

    /**
     * The audio content.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun data(): Optional<String> = data.getOptional("data")

    /**
     * The mime type of the audio.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun mimeType(): Optional<MimeType> = mimeType.getOptional("mime_type")

    /**
     * The sample rate of the audio.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun rate(): Optional<Int> = rate.getOptional("rate")

    /**
     * The URI of the audio.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun uri(): Optional<String> = uri.getOptional("uri")

    /**
     * Returns the raw JSON value of [channels].
     *
     * Unlike [channels], this method doesn't throw if the JSON field has an unexpected type.
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
     * Unlike [mimeType], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("mime_type") @ExcludeMissing fun _mimeType(): JsonField<MimeType> = mimeType

    /**
     * Returns the raw JSON value of [rate].
     *
     * Unlike [rate], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("rate") @ExcludeMissing fun _rate(): JsonField<Int> = rate

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

        /** Returns a mutable builder for constructing an instance of [AudioContent]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [AudioContent]. */
    class Builder internal constructor() {

        private var type: JsonValue = JsonValue.from("audio")
        private var channels: JsonField<Int> = JsonMissing.of()
        private var data: JsonField<String> = JsonMissing.of()
        private var mimeType: JsonField<MimeType> = JsonMissing.of()
        private var rate: JsonField<Int> = JsonMissing.of()
        private var uri: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(audioContent: AudioContent) = apply {
            type = audioContent.type
            channels = audioContent.channels
            data = audioContent.data
            mimeType = audioContent.mimeType
            rate = audioContent.rate
            uri = audioContent.uri
            additionalProperties = audioContent.additionalProperties.toMutableMap()
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

        /** The number of audio channels. */
        fun channels(channels: Int) = channels(JsonField.of(channels))

        /**
         * Sets [Builder.channels] to an arbitrary JSON value.
         *
         * You should usually call [Builder.channels] with a well-typed [Int] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun channels(channels: JsonField<Int>) = apply { this.channels = channels }

        /** The audio content. */
        fun data(data: String) = data(JsonField.of(data))

        /**
         * Sets [Builder.data] to an arbitrary JSON value.
         *
         * You should usually call [Builder.data] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun data(data: JsonField<String>) = apply { this.data = data }

        /** The mime type of the audio. */
        fun mimeType(mimeType: MimeType) = mimeType(JsonField.of(mimeType))

        /**
         * Sets [Builder.mimeType] to an arbitrary JSON value.
         *
         * You should usually call [Builder.mimeType] with a well-typed [MimeType] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun mimeType(mimeType: JsonField<MimeType>) = apply { this.mimeType = mimeType }

        /** The sample rate of the audio. */
        fun rate(rate: Int) = rate(JsonField.of(rate))

        /**
         * Sets [Builder.rate] to an arbitrary JSON value.
         *
         * You should usually call [Builder.rate] with a well-typed [Int] value instead. This method
         * is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun rate(rate: JsonField<Int>) = apply { this.rate = rate }

        /** The URI of the audio. */
        fun uri(uri: String) = uri(JsonField.of(uri))

        /**
         * Sets [Builder.uri] to an arbitrary JSON value.
         *
         * You should usually call [Builder.uri] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun uri(uri: JsonField<String>) = apply { this.uri = uri }

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
         * Returns an immutable instance of [AudioContent].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): AudioContent =
            AudioContent(
                type,
                channels,
                data,
                mimeType,
                rate,
                uri,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): AudioContent = apply {
        if (validated) {
            return@apply
        }

        _type().let {
            if (it != JsonValue.from("audio")) {
                throw GeminiNextGenApiInvalidDataException("'type' is invalid, received $it")
            }
        }
        channels()
        data()
        mimeType().ifPresent { it.validate() }
        rate()
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
     * Returns a score indicating how many valid values are contained in this object recursively.
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
            (if (uri.asKnown().isPresent) 1 else 0)

    /** The mime type of the audio. */
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

            @JvmField val AUDIO_WAV = of("audio/wav")

            @JvmField val AUDIO_MP3 = of("audio/mp3")

            @JvmField val AUDIO_AIFF = of("audio/aiff")

            @JvmField val AUDIO_AAC = of("audio/aac")

            @JvmField val AUDIO_OGG = of("audio/ogg")

            @JvmField val AUDIO_FLAC = of("audio/flac")

            @JvmField val AUDIO_MPEG = of("audio/mpeg")

            @JvmField val AUDIO_M4A = of("audio/m4a")

            @JvmField val AUDIO_L16 = of("audio/l16")

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
            AUDIO_WAV,
            AUDIO_MP3,
            AUDIO_AIFF,
            AUDIO_AAC,
            AUDIO_OGG,
            AUDIO_FLAC,
            AUDIO_MPEG,
            AUDIO_M4A,
            AUDIO_L16,
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
                AUDIO_WAV -> Value.AUDIO_WAV
                AUDIO_MP3 -> Value.AUDIO_MP3
                AUDIO_AIFF -> Value.AUDIO_AIFF
                AUDIO_AAC -> Value.AUDIO_AAC
                AUDIO_OGG -> Value.AUDIO_OGG
                AUDIO_FLAC -> Value.AUDIO_FLAC
                AUDIO_MPEG -> Value.AUDIO_MPEG
                AUDIO_M4A -> Value.AUDIO_M4A
                AUDIO_L16 -> Value.AUDIO_L16
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
                AUDIO_WAV -> Known.AUDIO_WAV
                AUDIO_MP3 -> Known.AUDIO_MP3
                AUDIO_AIFF -> Known.AUDIO_AIFF
                AUDIO_AAC -> Known.AUDIO_AAC
                AUDIO_OGG -> Known.AUDIO_OGG
                AUDIO_FLAC -> Known.AUDIO_FLAC
                AUDIO_MPEG -> Known.AUDIO_MPEG
                AUDIO_M4A -> Known.AUDIO_M4A
                AUDIO_L16 -> Known.AUDIO_L16
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

        return other is AudioContent &&
            type == other.type &&
            channels == other.channels &&
            data == other.data &&
            mimeType == other.mimeType &&
            rate == other.rate &&
            uri == other.uri &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(type, channels, data, mimeType, rate, uri, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "AudioContent{type=$type, channels=$channels, data=$data, mimeType=$mimeType, rate=$rate, uri=$uri, additionalProperties=$additionalProperties}"
}
