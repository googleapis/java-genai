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
import com.google.genai.interactions.core.ExcludeMissing
import com.google.genai.interactions.core.JsonField
import com.google.genai.interactions.core.JsonMissing
import com.google.genai.interactions.core.JsonValue
import com.google.genai.interactions.errors.GeminiNextGenApiInvalidDataException
import java.util.Collections
import java.util.Objects
import java.util.Optional

/** The configuration for speech interaction. */
class SpeechConfig
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val language: JsonField<String>,
    private val speaker: JsonField<String>,
    private val voice: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("language") @ExcludeMissing language: JsonField<String> = JsonMissing.of(),
        @JsonProperty("speaker") @ExcludeMissing speaker: JsonField<String> = JsonMissing.of(),
        @JsonProperty("voice") @ExcludeMissing voice: JsonField<String> = JsonMissing.of(),
    ) : this(language, speaker, voice, mutableMapOf())

    /**
     * The language of the speech.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun language(): Optional<String> = language.getOptional("language")

    /**
     * The speaker's name, it should match the speaker name given in the prompt.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun speaker(): Optional<String> = speaker.getOptional("speaker")

    /**
     * The voice of the speaker.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun voice(): Optional<String> = voice.getOptional("voice")

    /**
     * Returns the raw JSON value of [language].
     *
     * Unlike [language], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("language") @ExcludeMissing fun _language(): JsonField<String> = language

    /**
     * Returns the raw JSON value of [speaker].
     *
     * Unlike [speaker], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("speaker") @ExcludeMissing fun _speaker(): JsonField<String> = speaker

    /**
     * Returns the raw JSON value of [voice].
     *
     * Unlike [voice], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("voice") @ExcludeMissing fun _voice(): JsonField<String> = voice

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

        /** Returns a mutable builder for constructing an instance of [SpeechConfig]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [SpeechConfig]. */
    class Builder internal constructor() {

        private var language: JsonField<String> = JsonMissing.of()
        private var speaker: JsonField<String> = JsonMissing.of()
        private var voice: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(speechConfig: SpeechConfig) = apply {
            language = speechConfig.language
            speaker = speechConfig.speaker
            voice = speechConfig.voice
            additionalProperties = speechConfig.additionalProperties.toMutableMap()
        }

        /** The language of the speech. */
        fun language(language: String) = language(JsonField.of(language))

        /**
         * Sets [Builder.language] to an arbitrary JSON value.
         *
         * You should usually call [Builder.language] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun language(language: JsonField<String>) = apply { this.language = language }

        /** The speaker's name, it should match the speaker name given in the prompt. */
        fun speaker(speaker: String) = speaker(JsonField.of(speaker))

        /**
         * Sets [Builder.speaker] to an arbitrary JSON value.
         *
         * You should usually call [Builder.speaker] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun speaker(speaker: JsonField<String>) = apply { this.speaker = speaker }

        /** The voice of the speaker. */
        fun voice(voice: String) = voice(JsonField.of(voice))

        /**
         * Sets [Builder.voice] to an arbitrary JSON value.
         *
         * You should usually call [Builder.voice] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun voice(voice: JsonField<String>) = apply { this.voice = voice }

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
         * Returns an immutable instance of [SpeechConfig].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): SpeechConfig =
            SpeechConfig(language, speaker, voice, additionalProperties.toMutableMap())
    }

    private var validated: Boolean = false

    fun validate(): SpeechConfig = apply {
        if (validated) {
            return@apply
        }

        language()
        speaker()
        voice()
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
        (if (language.asKnown().isPresent) 1 else 0) +
            (if (speaker.asKnown().isPresent) 1 else 0) +
            (if (voice.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is SpeechConfig &&
            language == other.language &&
            speaker == other.speaker &&
            voice == other.voice &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(language, speaker, voice, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "SpeechConfig{language=$language, speaker=$speaker, voice=$voice, additionalProperties=$additionalProperties}"
}
