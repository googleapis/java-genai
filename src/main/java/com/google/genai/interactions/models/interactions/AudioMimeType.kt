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

import com.fasterxml.jackson.annotation.JsonCreator
import com.google.genai.interactions.core.Enum
import com.google.genai.interactions.core.JsonField
import com.google.genai.interactions.errors.GeminiNextGenApiInvalidDataException

/** The mime type of the audio. */
class AudioMimeType @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

    /**
     * Returns this class instance's raw value.
     *
     * This is usually only useful if this instance was deserialized from data that doesn't match
     * any known member, and you want to know that value. For example, if the SDK is on an older
     * version than the API, then the API may respond with new members that the SDK is unaware of.
     */
    @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

    companion object {

        @JvmField val AUDIO_WAV = of("audio/wav")

        @JvmField val AUDIO_MP3 = of("audio/mp3")

        @JvmField val AUDIO_AIFF = of("audio/aiff")

        @JvmField val AUDIO_AAC = of("audio/aac")

        @JvmField val AUDIO_OGG = of("audio/ogg")

        @JvmField val AUDIO_FLAC = of("audio/flac")

        @JvmStatic fun of(value: String) = AudioMimeType(JsonField.of(value))
    }

    /** An enum containing [AudioMimeType]'s known values. */
    enum class Known {
        AUDIO_WAV,
        AUDIO_MP3,
        AUDIO_AIFF,
        AUDIO_AAC,
        AUDIO_OGG,
        AUDIO_FLAC,
    }

    /**
     * An enum containing [AudioMimeType]'s known values, as well as an [_UNKNOWN] member.
     *
     * An instance of [AudioMimeType] can contain an unknown value in a couple of cases:
     * - It was deserialized from data that doesn't match any known member. For example, if the SDK
     *   is on an older version than the API, then the API may respond with new members that the SDK
     *   is unaware of.
     * - It was constructed with an arbitrary value using the [of] method.
     */
    enum class Value {
        AUDIO_WAV,
        AUDIO_MP3,
        AUDIO_AIFF,
        AUDIO_AAC,
        AUDIO_OGG,
        AUDIO_FLAC,
        /**
         * An enum member indicating that [AudioMimeType] was instantiated with an unknown value.
         */
        _UNKNOWN,
    }

    /**
     * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN] if
     * the class was instantiated with an unknown value.
     *
     * Use the [known] method instead if you're certain the value is always known or if you want to
     * throw for the unknown case.
     */
    fun value(): Value =
        when (this) {
            AUDIO_WAV -> Value.AUDIO_WAV
            AUDIO_MP3 -> Value.AUDIO_MP3
            AUDIO_AIFF -> Value.AUDIO_AIFF
            AUDIO_AAC -> Value.AUDIO_AAC
            AUDIO_OGG -> Value.AUDIO_OGG
            AUDIO_FLAC -> Value.AUDIO_FLAC
            else -> Value._UNKNOWN
        }

    /**
     * Returns an enum member corresponding to this class instance's value.
     *
     * Use the [value] method instead if you're uncertain the value is always known and don't want
     * to throw for the unknown case.
     *
     * @throws GeminiNextGenApiInvalidDataException if this class instance's value is a not a known
     *   member.
     */
    fun known(): Known =
        when (this) {
            AUDIO_WAV -> Known.AUDIO_WAV
            AUDIO_MP3 -> Known.AUDIO_MP3
            AUDIO_AIFF -> Known.AUDIO_AIFF
            AUDIO_AAC -> Known.AUDIO_AAC
            AUDIO_OGG -> Known.AUDIO_OGG
            AUDIO_FLAC -> Known.AUDIO_FLAC
            else -> throw GeminiNextGenApiInvalidDataException("Unknown AudioMimeType: $value")
        }

    /**
     * Returns this class instance's primitive wire representation.
     *
     * This differs from the [toString] method because that method is primarily for debugging and
     * generally doesn't throw.
     *
     * @throws GeminiNextGenApiInvalidDataException if this class instance's value does not have the
     *   expected primitive type.
     */
    fun asString(): String =
        _value().asString().orElseThrow {
            GeminiNextGenApiInvalidDataException("Value is not a String")
        }

    private var validated: Boolean = false

    fun validate(): AudioMimeType = apply {
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
     * Returns a score indicating how many valid values are contained in this object recursively.
     *
     * Used for best match union deserialization.
     */
    @JvmSynthetic internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is AudioMimeType && value == other.value
    }

    override fun hashCode() = value.hashCode()

    override fun toString() = value.toString()
}
