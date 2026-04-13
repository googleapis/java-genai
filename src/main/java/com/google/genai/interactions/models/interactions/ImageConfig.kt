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

/** The configuration for image interaction. */
class ImageConfig
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val aspectRatio: JsonField<AspectRatio>,
    private val imageSize: JsonField<ImageSize>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("aspect_ratio")
        @ExcludeMissing
        aspectRatio: JsonField<AspectRatio> = JsonMissing.of(),
        @JsonProperty("image_size")
        @ExcludeMissing
        imageSize: JsonField<ImageSize> = JsonMissing.of(),
    ) : this(aspectRatio, imageSize, mutableMapOf())

    /**
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun aspectRatio(): Optional<AspectRatio> = aspectRatio.getOptional("aspect_ratio")

    /**
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun imageSize(): Optional<ImageSize> = imageSize.getOptional("image_size")

    /**
     * Returns the raw JSON value of [aspectRatio].
     *
     * Unlike [aspectRatio], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("aspect_ratio")
    @ExcludeMissing
    fun _aspectRatio(): JsonField<AspectRatio> = aspectRatio

    /**
     * Returns the raw JSON value of [imageSize].
     *
     * Unlike [imageSize], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("image_size") @ExcludeMissing fun _imageSize(): JsonField<ImageSize> = imageSize

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

        /** Returns a mutable builder for constructing an instance of [ImageConfig]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [ImageConfig]. */
    class Builder internal constructor() {

        private var aspectRatio: JsonField<AspectRatio> = JsonMissing.of()
        private var imageSize: JsonField<ImageSize> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(imageConfig: ImageConfig) = apply {
            aspectRatio = imageConfig.aspectRatio
            imageSize = imageConfig.imageSize
            additionalProperties = imageConfig.additionalProperties.toMutableMap()
        }

        fun aspectRatio(aspectRatio: AspectRatio) = aspectRatio(JsonField.of(aspectRatio))

        /**
         * Sets [Builder.aspectRatio] to an arbitrary JSON value.
         *
         * You should usually call [Builder.aspectRatio] with a well-typed [AspectRatio] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun aspectRatio(aspectRatio: JsonField<AspectRatio>) = apply {
            this.aspectRatio = aspectRatio
        }

        fun imageSize(imageSize: ImageSize) = imageSize(JsonField.of(imageSize))

        /**
         * Sets [Builder.imageSize] to an arbitrary JSON value.
         *
         * You should usually call [Builder.imageSize] with a well-typed [ImageSize] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun imageSize(imageSize: JsonField<ImageSize>) = apply { this.imageSize = imageSize }

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
         * Returns an immutable instance of [ImageConfig].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): ImageConfig =
            ImageConfig(aspectRatio, imageSize, additionalProperties.toMutableMap())
    }

    private var validated: Boolean = false

    fun validate(): ImageConfig = apply {
        if (validated) {
            return@apply
        }

        aspectRatio().ifPresent { it.validate() }
        imageSize().ifPresent { it.validate() }
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
        (aspectRatio.asKnown().getOrNull()?.validity() ?: 0) +
            (imageSize.asKnown().getOrNull()?.validity() ?: 0)

    class AspectRatio @JsonCreator private constructor(private val value: JsonField<String>) :
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

            @JvmField val _1_1 = of("1:1")

            @JvmField val _2_3 = of("2:3")

            @JvmField val _3_2 = of("3:2")

            @JvmField val _3_4 = of("3:4")

            @JvmField val _4_3 = of("4:3")

            @JvmField val _4_5 = of("4:5")

            @JvmField val _5_4 = of("5:4")

            @JvmField val _9_16 = of("9:16")

            @JvmField val _16_9 = of("16:9")

            @JvmField val _21_9 = of("21:9")

            @JvmField val _1_8 = of("1:8")

            @JvmField val _8_1 = of("8:1")

            @JvmField val _1_4 = of("1:4")

            @JvmField val _4_1 = of("4:1")

            @JvmStatic fun of(value: String) = AspectRatio(JsonField.of(value))
        }

        /** An enum containing [AspectRatio]'s known values. */
        enum class Known {
            _1_1,
            _2_3,
            _3_2,
            _3_4,
            _4_3,
            _4_5,
            _5_4,
            _9_16,
            _16_9,
            _21_9,
            _1_8,
            _8_1,
            _1_4,
            _4_1,
        }

        /**
         * An enum containing [AspectRatio]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [AspectRatio] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            _1_1,
            _2_3,
            _3_2,
            _3_4,
            _4_3,
            _4_5,
            _5_4,
            _9_16,
            _16_9,
            _21_9,
            _1_8,
            _8_1,
            _1_4,
            _4_1,
            /**
             * An enum member indicating that [AspectRatio] was instantiated with an unknown value.
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
                _1_1 -> Value._1_1
                _2_3 -> Value._2_3
                _3_2 -> Value._3_2
                _3_4 -> Value._3_4
                _4_3 -> Value._4_3
                _4_5 -> Value._4_5
                _5_4 -> Value._5_4
                _9_16 -> Value._9_16
                _16_9 -> Value._16_9
                _21_9 -> Value._21_9
                _1_8 -> Value._1_8
                _8_1 -> Value._8_1
                _1_4 -> Value._1_4
                _4_1 -> Value._4_1
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
                _1_1 -> Known._1_1
                _2_3 -> Known._2_3
                _3_2 -> Known._3_2
                _3_4 -> Known._3_4
                _4_3 -> Known._4_3
                _4_5 -> Known._4_5
                _5_4 -> Known._5_4
                _9_16 -> Known._9_16
                _16_9 -> Known._16_9
                _21_9 -> Known._21_9
                _1_8 -> Known._1_8
                _8_1 -> Known._8_1
                _1_4 -> Known._1_4
                _4_1 -> Known._4_1
                else -> throw GeminiNextGenApiInvalidDataException("Unknown AspectRatio: $value")
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

        fun validate(): AspectRatio = apply {
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

            return other is AspectRatio && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    class ImageSize @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

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

            @JvmField val _1_K = of("1K")

            @JvmField val _2_K = of("2K")

            @JvmField val _4_K = of("4K")

            @JvmField val _512 = of("512")

            @JvmStatic fun of(value: String) = ImageSize(JsonField.of(value))
        }

        /** An enum containing [ImageSize]'s known values. */
        enum class Known {
            _1_K,
            _2_K,
            _4_K,
            _512,
        }

        /**
         * An enum containing [ImageSize]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [ImageSize] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            _1_K,
            _2_K,
            _4_K,
            _512,
            /**
             * An enum member indicating that [ImageSize] was instantiated with an unknown value.
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
                _1_K -> Value._1_K
                _2_K -> Value._2_K
                _4_K -> Value._4_K
                _512 -> Value._512
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
                _1_K -> Known._1_K
                _2_K -> Known._2_K
                _4_K -> Known._4_K
                _512 -> Known._512
                else -> throw GeminiNextGenApiInvalidDataException("Unknown ImageSize: $value")
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

        fun validate(): ImageSize = apply {
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

            return other is ImageSize && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ImageConfig &&
            aspectRatio == other.aspectRatio &&
            imageSize == other.imageSize &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(aspectRatio, imageSize, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "ImageConfig{aspectRatio=$aspectRatio, imageSize=$imageSize, additionalProperties=$additionalProperties}"
}
