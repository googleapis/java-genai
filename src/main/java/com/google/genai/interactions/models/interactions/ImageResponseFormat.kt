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

/** Configuration for image output format. */
class ImageResponseFormat
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val type: JsonValue,
    private val aspectRatio: JsonField<AspectRatio>,
    private val delivery: JsonField<Delivery>,
    private val imageSize: JsonField<ImageSize>,
    private val mimeType: JsonField<MimeType>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("aspectRatio")
        @ExcludeMissing
        aspectRatio: JsonField<AspectRatio> = JsonMissing.of(),
        @JsonProperty("delivery") @ExcludeMissing delivery: JsonField<Delivery> = JsonMissing.of(),
        @JsonProperty("imageSize")
        @ExcludeMissing
        imageSize: JsonField<ImageSize> = JsonMissing.of(),
        @JsonProperty("mimeType") @ExcludeMissing mimeType: JsonField<MimeType> = JsonMissing.of(),
    ) : this(type, aspectRatio, delivery, imageSize, mimeType, mutableMapOf())

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("image")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * The aspect ratio for the image output.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun aspectRatio(): Optional<AspectRatio> = aspectRatio.getOptional("aspectRatio")

    /**
     * The delivery mode for the image output.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun delivery(): Optional<Delivery> = delivery.getOptional("delivery")

    /**
     * The size of the image output.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun imageSize(): Optional<ImageSize> = imageSize.getOptional("imageSize")

    /**
     * The MIME type of the image output.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun mimeType(): Optional<MimeType> = mimeType.getOptional("mimeType")

    /**
     * Returns the raw JSON value of [aspectRatio].
     *
     * Unlike [aspectRatio], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("aspectRatio")
    @ExcludeMissing
    fun _aspectRatio(): JsonField<AspectRatio> = aspectRatio

    /**
     * Returns the raw JSON value of [delivery].
     *
     * Unlike [delivery], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("delivery") @ExcludeMissing fun _delivery(): JsonField<Delivery> = delivery

    /**
     * Returns the raw JSON value of [imageSize].
     *
     * Unlike [imageSize], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("imageSize") @ExcludeMissing fun _imageSize(): JsonField<ImageSize> = imageSize

    /**
     * Returns the raw JSON value of [mimeType].
     *
     * Unlike [mimeType], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("mimeType") @ExcludeMissing fun _mimeType(): JsonField<MimeType> = mimeType

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

        /** Returns a mutable builder for constructing an instance of [ImageResponseFormat]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [ImageResponseFormat]. */
    class Builder internal constructor() {

        private var type: JsonValue = JsonValue.from("image")
        private var aspectRatio: JsonField<AspectRatio> = JsonMissing.of()
        private var delivery: JsonField<Delivery> = JsonMissing.of()
        private var imageSize: JsonField<ImageSize> = JsonMissing.of()
        private var mimeType: JsonField<MimeType> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(imageResponseFormat: ImageResponseFormat) = apply {
            type = imageResponseFormat.type
            aspectRatio = imageResponseFormat.aspectRatio
            delivery = imageResponseFormat.delivery
            imageSize = imageResponseFormat.imageSize
            mimeType = imageResponseFormat.mimeType
            additionalProperties = imageResponseFormat.additionalProperties.toMutableMap()
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("image")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /** The aspect ratio for the image output. */
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

        /** The delivery mode for the image output. */
        fun delivery(delivery: Delivery) = delivery(JsonField.of(delivery))

        /**
         * Sets [Builder.delivery] to an arbitrary JSON value.
         *
         * You should usually call [Builder.delivery] with a well-typed [Delivery] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun delivery(delivery: JsonField<Delivery>) = apply { this.delivery = delivery }

        /** The size of the image output. */
        fun imageSize(imageSize: ImageSize) = imageSize(JsonField.of(imageSize))

        /**
         * Sets [Builder.imageSize] to an arbitrary JSON value.
         *
         * You should usually call [Builder.imageSize] with a well-typed [ImageSize] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun imageSize(imageSize: JsonField<ImageSize>) = apply { this.imageSize = imageSize }

        /** The MIME type of the image output. */
        fun mimeType(mimeType: MimeType) = mimeType(JsonField.of(mimeType))

        /**
         * Sets [Builder.mimeType] to an arbitrary JSON value.
         *
         * You should usually call [Builder.mimeType] with a well-typed [MimeType] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun mimeType(mimeType: JsonField<MimeType>) = apply { this.mimeType = mimeType }

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
         * Returns an immutable instance of [ImageResponseFormat].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): ImageResponseFormat =
            ImageResponseFormat(
                type,
                aspectRatio,
                delivery,
                imageSize,
                mimeType,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): ImageResponseFormat = apply {
        if (validated) {
            return@apply
        }

        _type().let {
            if (it != JsonValue.from("image")) {
                throw GeminiNextGenApiInvalidDataException("'type' is invalid, received $it")
            }
        }
        aspectRatio().ifPresent { it.validate() }
        delivery().ifPresent { it.validate() }
        imageSize().ifPresent { it.validate() }
        mimeType().ifPresent { it.validate() }
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
        type.let { if (it == JsonValue.from("image")) 1 else 0 } +
            (aspectRatio.asKnown().getOrNull()?.validity() ?: 0) +
            (delivery.asKnown().getOrNull()?.validity() ?: 0) +
            (imageSize.asKnown().getOrNull()?.validity() ?: 0) +
            (mimeType.asKnown().getOrNull()?.validity() ?: 0)

    /** The aspect ratio for the image output. */
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

    /** The delivery mode for the image output. */
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

    /** The size of the image output. */
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

            @JvmField val _512 = of("512")

            @JvmField val _1_K = of("1K")

            @JvmField val _2_K = of("2K")

            @JvmField val _4_K = of("4K")

            @JvmStatic fun of(value: String) = ImageSize(JsonField.of(value))
        }

        /** An enum containing [ImageSize]'s known values. */
        enum class Known {
            _512,
            _1_K,
            _2_K,
            _4_K,
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
            _512,
            _1_K,
            _2_K,
            _4_K,
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
                _512 -> Value._512
                _1_K -> Value._1_K
                _2_K -> Value._2_K
                _4_K -> Value._4_K
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
                _512 -> Known._512
                _1_K -> Known._1_K
                _2_K -> Known._2_K
                _4_K -> Known._4_K
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

    /** The MIME type of the image output. */
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

            @JvmField val IMAGE_JPEG = of("image/jpeg")

            @JvmStatic fun of(value: String) = MimeType(JsonField.of(value))
        }

        /** An enum containing [MimeType]'s known values. */
        enum class Known {
            IMAGE_JPEG
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
            IMAGE_JPEG,
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
                IMAGE_JPEG -> Value.IMAGE_JPEG
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
                IMAGE_JPEG -> Known.IMAGE_JPEG
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

        return other is ImageResponseFormat &&
            type == other.type &&
            aspectRatio == other.aspectRatio &&
            delivery == other.delivery &&
            imageSize == other.imageSize &&
            mimeType == other.mimeType &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(type, aspectRatio, delivery, imageSize, mimeType, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "ImageResponseFormat{type=$type, aspectRatio=$aspectRatio, delivery=$delivery, imageSize=$imageSize, mimeType=$mimeType, additionalProperties=$additionalProperties}"
}
