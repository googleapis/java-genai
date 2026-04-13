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

/**
 * The model that will complete your prompt.\n\nSee
 * [models](https://ai.google.dev/gemini-api/docs/models) for additional details.
 */
class Model @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

    /**
     * Returns this class instance's raw value.
     *
     * This is usually only useful if this instance was deserialized from data that doesn't match
     * any known member, and you want to know that value. For example, if the SDK is on an older
     * version than the API, then the API may respond with new members that the SDK is unaware of.
     */
    @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

    companion object {

        /**
         * An agentic capability model designed for direct interface interaction, allowing Gemini to
         * perceive and navigate digital environments.
         */
        @JvmField
        val GEMINI_2_5_COMPUTER_USE_PREVIEW_10_2025 = of("gemini-2.5-computer-use-preview-10-2025")

        /**
         * Our first hybrid reasoning model which supports a 1M token context window and has
         * thinking budgets.
         */
        @JvmField val GEMINI_2_5_FLASH = of("gemini-2.5-flash")

        /**
         * Our native image generation model, optimized for speed, flexibility, and contextual
         * understanding. Text input and output is priced the same as 2.5 Flash.
         */
        @JvmField val GEMINI_2_5_FLASH_IMAGE = of("gemini-2.5-flash-image")

        /** Our smallest and most cost effective model, built for at scale usage. */
        @JvmField val GEMINI_2_5_FLASH_LITE = of("gemini-2.5-flash-lite")

        /**
         * The latest model based on Gemini 2.5 Flash lite optimized for cost-efficiency, high
         * throughput and high quality.
         */
        @JvmField
        val GEMINI_2_5_FLASH_LITE_PREVIEW_09_2025 = of("gemini-2.5-flash-lite-preview-09-2025")

        /**
         * Our native audio models optimized for higher quality audio outputs with better pacing,
         * voice naturalness, verbosity, and mood.
         */
        @JvmField
        val GEMINI_2_5_FLASH_NATIVE_AUDIO_PREVIEW_12_2025 =
            of("gemini-2.5-flash-native-audio-preview-12-2025")

        /**
         * The latest model based on the 2.5 Flash model. 2.5 Flash Preview is best for large scale
         * processing, low-latency, high volume tasks that require thinking, and agentic use cases.
         */
        @JvmField val GEMINI_2_5_FLASH_PREVIEW_09_2025 = of("gemini-2.5-flash-preview-09-2025")

        /**
         * Our 2.5 Flash text-to-speech model optimized for powerful, low-latency controllable
         * speech generation.
         */
        @JvmField val GEMINI_2_5_FLASH_PREVIEW_TTS = of("gemini-2.5-flash-preview-tts")

        /**
         * Our state-of-the-art multipurpose model, which excels at coding and complex reasoning
         * tasks.
         */
        @JvmField val GEMINI_2_5_PRO = of("gemini-2.5-pro")

        /**
         * Our 2.5 Pro text-to-speech audio model optimized for powerful, low-latency speech
         * generation for more natural outputs and easier to steer prompts.
         */
        @JvmField val GEMINI_2_5_PRO_PREVIEW_TTS = of("gemini-2.5-pro-preview-tts")

        /**
         * Our most intelligent model built for speed, combining frontier intelligence with superior
         * search and grounding.
         */
        @JvmField val GEMINI_3_FLASH_PREVIEW = of("gemini-3-flash-preview")

        /** State-of-the-art image generation and editing model. */
        @JvmField val GEMINI_3_PRO_IMAGE_PREVIEW = of("gemini-3-pro-image-preview")

        /**
         * Our most intelligent model with SOTA reasoning and multimodal understanding, and powerful
         * agentic and vibe coding capabilities.
         */
        @JvmField val GEMINI_3_PRO_PREVIEW = of("gemini-3-pro-preview")

        /**
         * Our latest SOTA reasoning model with unprecedented depth and nuance, and powerful
         * multimodal understanding and coding capabilities.
         */
        @JvmField val GEMINI_3_1_PRO_PREVIEW = of("gemini-3.1-pro-preview")

        /**
         * Pro-level visual intelligence with Flash-speed efficiency and reality-grounded generation
         * capabilities.
         */
        @JvmField val GEMINI_3_1_FLASH_IMAGE_PREVIEW = of("gemini-3.1-flash-image-preview")

        /**
         * Our most cost-efficient model, optimized for high-volume agentic tasks, translation, and
         * simple data processing.
         */
        @JvmField val GEMINI_3_1_FLASH_LITE_PREVIEW = of("gemini-3.1-flash-lite-preview")

        /**
         * Our low-latency, music generation model optimized for high-fidelity audio clips and
         * precise rhythmic control.
         */
        @JvmField val LYRIA_3_CLIP_PREVIEW = of("lyria-3-clip-preview")

        /**
         * Our advanced, full-song generative model with deep compositional understanding, optimized
         * for precise structural control and complex transitions across diverse musical styles.
         */
        @JvmField val LYRIA_3_PRO_PREVIEW = of("lyria-3-pro-preview")

        @JvmStatic fun of(value: String) = Model(JsonField.of(value))
    }

    /** An enum containing [Model]'s known values. */
    enum class Known {
        /**
         * An agentic capability model designed for direct interface interaction, allowing Gemini to
         * perceive and navigate digital environments.
         */
        GEMINI_2_5_COMPUTER_USE_PREVIEW_10_2025,
        /**
         * Our first hybrid reasoning model which supports a 1M token context window and has
         * thinking budgets.
         */
        GEMINI_2_5_FLASH,
        /**
         * Our native image generation model, optimized for speed, flexibility, and contextual
         * understanding. Text input and output is priced the same as 2.5 Flash.
         */
        GEMINI_2_5_FLASH_IMAGE,
        /** Our smallest and most cost effective model, built for at scale usage. */
        GEMINI_2_5_FLASH_LITE,
        /**
         * The latest model based on Gemini 2.5 Flash lite optimized for cost-efficiency, high
         * throughput and high quality.
         */
        GEMINI_2_5_FLASH_LITE_PREVIEW_09_2025,
        /**
         * Our native audio models optimized for higher quality audio outputs with better pacing,
         * voice naturalness, verbosity, and mood.
         */
        GEMINI_2_5_FLASH_NATIVE_AUDIO_PREVIEW_12_2025,
        /**
         * The latest model based on the 2.5 Flash model. 2.5 Flash Preview is best for large scale
         * processing, low-latency, high volume tasks that require thinking, and agentic use cases.
         */
        GEMINI_2_5_FLASH_PREVIEW_09_2025,
        /**
         * Our 2.5 Flash text-to-speech model optimized for powerful, low-latency controllable
         * speech generation.
         */
        GEMINI_2_5_FLASH_PREVIEW_TTS,
        /**
         * Our state-of-the-art multipurpose model, which excels at coding and complex reasoning
         * tasks.
         */
        GEMINI_2_5_PRO,
        /**
         * Our 2.5 Pro text-to-speech audio model optimized for powerful, low-latency speech
         * generation for more natural outputs and easier to steer prompts.
         */
        GEMINI_2_5_PRO_PREVIEW_TTS,
        /**
         * Our most intelligent model built for speed, combining frontier intelligence with superior
         * search and grounding.
         */
        GEMINI_3_FLASH_PREVIEW,
        /** State-of-the-art image generation and editing model. */
        GEMINI_3_PRO_IMAGE_PREVIEW,
        /**
         * Our most intelligent model with SOTA reasoning and multimodal understanding, and powerful
         * agentic and vibe coding capabilities.
         */
        GEMINI_3_PRO_PREVIEW,
        /**
         * Our latest SOTA reasoning model with unprecedented depth and nuance, and powerful
         * multimodal understanding and coding capabilities.
         */
        GEMINI_3_1_PRO_PREVIEW,
        /**
         * Pro-level visual intelligence with Flash-speed efficiency and reality-grounded generation
         * capabilities.
         */
        GEMINI_3_1_FLASH_IMAGE_PREVIEW,
        /**
         * Our most cost-efficient model, optimized for high-volume agentic tasks, translation, and
         * simple data processing.
         */
        GEMINI_3_1_FLASH_LITE_PREVIEW,
        /**
         * Our low-latency, music generation model optimized for high-fidelity audio clips and
         * precise rhythmic control.
         */
        LYRIA_3_CLIP_PREVIEW,
        /**
         * Our advanced, full-song generative model with deep compositional understanding, optimized
         * for precise structural control and complex transitions across diverse musical styles.
         */
        LYRIA_3_PRO_PREVIEW,
    }

    /**
     * An enum containing [Model]'s known values, as well as an [_UNKNOWN] member.
     *
     * An instance of [Model] can contain an unknown value in a couple of cases:
     * - It was deserialized from data that doesn't match any known member. For example, if the SDK
     *   is on an older version than the API, then the API may respond with new members that the SDK
     *   is unaware of.
     * - It was constructed with an arbitrary value using the [of] method.
     */
    enum class Value {
        /**
         * An agentic capability model designed for direct interface interaction, allowing Gemini to
         * perceive and navigate digital environments.
         */
        GEMINI_2_5_COMPUTER_USE_PREVIEW_10_2025,
        /**
         * Our first hybrid reasoning model which supports a 1M token context window and has
         * thinking budgets.
         */
        GEMINI_2_5_FLASH,
        /**
         * Our native image generation model, optimized for speed, flexibility, and contextual
         * understanding. Text input and output is priced the same as 2.5 Flash.
         */
        GEMINI_2_5_FLASH_IMAGE,
        /** Our smallest and most cost effective model, built for at scale usage. */
        GEMINI_2_5_FLASH_LITE,
        /**
         * The latest model based on Gemini 2.5 Flash lite optimized for cost-efficiency, high
         * throughput and high quality.
         */
        GEMINI_2_5_FLASH_LITE_PREVIEW_09_2025,
        /**
         * Our native audio models optimized for higher quality audio outputs with better pacing,
         * voice naturalness, verbosity, and mood.
         */
        GEMINI_2_5_FLASH_NATIVE_AUDIO_PREVIEW_12_2025,
        /**
         * The latest model based on the 2.5 Flash model. 2.5 Flash Preview is best for large scale
         * processing, low-latency, high volume tasks that require thinking, and agentic use cases.
         */
        GEMINI_2_5_FLASH_PREVIEW_09_2025,
        /**
         * Our 2.5 Flash text-to-speech model optimized for powerful, low-latency controllable
         * speech generation.
         */
        GEMINI_2_5_FLASH_PREVIEW_TTS,
        /**
         * Our state-of-the-art multipurpose model, which excels at coding and complex reasoning
         * tasks.
         */
        GEMINI_2_5_PRO,
        /**
         * Our 2.5 Pro text-to-speech audio model optimized for powerful, low-latency speech
         * generation for more natural outputs and easier to steer prompts.
         */
        GEMINI_2_5_PRO_PREVIEW_TTS,
        /**
         * Our most intelligent model built for speed, combining frontier intelligence with superior
         * search and grounding.
         */
        GEMINI_3_FLASH_PREVIEW,
        /** State-of-the-art image generation and editing model. */
        GEMINI_3_PRO_IMAGE_PREVIEW,
        /**
         * Our most intelligent model with SOTA reasoning and multimodal understanding, and powerful
         * agentic and vibe coding capabilities.
         */
        GEMINI_3_PRO_PREVIEW,
        /**
         * Our latest SOTA reasoning model with unprecedented depth and nuance, and powerful
         * multimodal understanding and coding capabilities.
         */
        GEMINI_3_1_PRO_PREVIEW,
        /**
         * Pro-level visual intelligence with Flash-speed efficiency and reality-grounded generation
         * capabilities.
         */
        GEMINI_3_1_FLASH_IMAGE_PREVIEW,
        /**
         * Our most cost-efficient model, optimized for high-volume agentic tasks, translation, and
         * simple data processing.
         */
        GEMINI_3_1_FLASH_LITE_PREVIEW,
        /**
         * Our low-latency, music generation model optimized for high-fidelity audio clips and
         * precise rhythmic control.
         */
        LYRIA_3_CLIP_PREVIEW,
        /**
         * Our advanced, full-song generative model with deep compositional understanding, optimized
         * for precise structural control and complex transitions across diverse musical styles.
         */
        LYRIA_3_PRO_PREVIEW,
        /** An enum member indicating that [Model] was instantiated with an unknown value. */
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
            GEMINI_2_5_COMPUTER_USE_PREVIEW_10_2025 -> Value.GEMINI_2_5_COMPUTER_USE_PREVIEW_10_2025
            GEMINI_2_5_FLASH -> Value.GEMINI_2_5_FLASH
            GEMINI_2_5_FLASH_IMAGE -> Value.GEMINI_2_5_FLASH_IMAGE
            GEMINI_2_5_FLASH_LITE -> Value.GEMINI_2_5_FLASH_LITE
            GEMINI_2_5_FLASH_LITE_PREVIEW_09_2025 -> Value.GEMINI_2_5_FLASH_LITE_PREVIEW_09_2025
            GEMINI_2_5_FLASH_NATIVE_AUDIO_PREVIEW_12_2025 ->
                Value.GEMINI_2_5_FLASH_NATIVE_AUDIO_PREVIEW_12_2025
            GEMINI_2_5_FLASH_PREVIEW_09_2025 -> Value.GEMINI_2_5_FLASH_PREVIEW_09_2025
            GEMINI_2_5_FLASH_PREVIEW_TTS -> Value.GEMINI_2_5_FLASH_PREVIEW_TTS
            GEMINI_2_5_PRO -> Value.GEMINI_2_5_PRO
            GEMINI_2_5_PRO_PREVIEW_TTS -> Value.GEMINI_2_5_PRO_PREVIEW_TTS
            GEMINI_3_FLASH_PREVIEW -> Value.GEMINI_3_FLASH_PREVIEW
            GEMINI_3_PRO_IMAGE_PREVIEW -> Value.GEMINI_3_PRO_IMAGE_PREVIEW
            GEMINI_3_PRO_PREVIEW -> Value.GEMINI_3_PRO_PREVIEW
            GEMINI_3_1_PRO_PREVIEW -> Value.GEMINI_3_1_PRO_PREVIEW
            GEMINI_3_1_FLASH_IMAGE_PREVIEW -> Value.GEMINI_3_1_FLASH_IMAGE_PREVIEW
            GEMINI_3_1_FLASH_LITE_PREVIEW -> Value.GEMINI_3_1_FLASH_LITE_PREVIEW
            LYRIA_3_CLIP_PREVIEW -> Value.LYRIA_3_CLIP_PREVIEW
            LYRIA_3_PRO_PREVIEW -> Value.LYRIA_3_PRO_PREVIEW
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
            GEMINI_2_5_COMPUTER_USE_PREVIEW_10_2025 -> Known.GEMINI_2_5_COMPUTER_USE_PREVIEW_10_2025
            GEMINI_2_5_FLASH -> Known.GEMINI_2_5_FLASH
            GEMINI_2_5_FLASH_IMAGE -> Known.GEMINI_2_5_FLASH_IMAGE
            GEMINI_2_5_FLASH_LITE -> Known.GEMINI_2_5_FLASH_LITE
            GEMINI_2_5_FLASH_LITE_PREVIEW_09_2025 -> Known.GEMINI_2_5_FLASH_LITE_PREVIEW_09_2025
            GEMINI_2_5_FLASH_NATIVE_AUDIO_PREVIEW_12_2025 ->
                Known.GEMINI_2_5_FLASH_NATIVE_AUDIO_PREVIEW_12_2025
            GEMINI_2_5_FLASH_PREVIEW_09_2025 -> Known.GEMINI_2_5_FLASH_PREVIEW_09_2025
            GEMINI_2_5_FLASH_PREVIEW_TTS -> Known.GEMINI_2_5_FLASH_PREVIEW_TTS
            GEMINI_2_5_PRO -> Known.GEMINI_2_5_PRO
            GEMINI_2_5_PRO_PREVIEW_TTS -> Known.GEMINI_2_5_PRO_PREVIEW_TTS
            GEMINI_3_FLASH_PREVIEW -> Known.GEMINI_3_FLASH_PREVIEW
            GEMINI_3_PRO_IMAGE_PREVIEW -> Known.GEMINI_3_PRO_IMAGE_PREVIEW
            GEMINI_3_PRO_PREVIEW -> Known.GEMINI_3_PRO_PREVIEW
            GEMINI_3_1_PRO_PREVIEW -> Known.GEMINI_3_1_PRO_PREVIEW
            GEMINI_3_1_FLASH_IMAGE_PREVIEW -> Known.GEMINI_3_1_FLASH_IMAGE_PREVIEW
            GEMINI_3_1_FLASH_LITE_PREVIEW -> Known.GEMINI_3_1_FLASH_LITE_PREVIEW
            LYRIA_3_CLIP_PREVIEW -> Known.LYRIA_3_CLIP_PREVIEW
            LYRIA_3_PRO_PREVIEW -> Known.LYRIA_3_PRO_PREVIEW
            else -> throw GeminiNextGenApiInvalidDataException("Unknown Model: $value")
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

    fun validate(): Model = apply {
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

        return other is Model && value == other.value
    }

    override fun hashCode() = value.hashCode()

    override fun toString() = value.toString()
}
