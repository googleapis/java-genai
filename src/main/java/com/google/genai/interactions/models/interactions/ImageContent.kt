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

/** An image content block. */
class ImageContent
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val type: JsonValue,
    private val data: JsonField<String>,
    private val mimeType: JsonField<MimeType>,
    private val resolution: JsonField<Resolution>,
    private val uri: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("data") @ExcludeMissing data: JsonField<String> = JsonMissing.of(),
        @JsonProperty("mime_type") @ExcludeMissing mimeType: JsonField<MimeType> = JsonMissing.of(),
        @JsonProperty("resolution")
        @ExcludeMissing
        resolution: JsonField<Resolution> = JsonMissing.of(),
        @JsonProperty("uri") @ExcludeMissing uri: JsonField<String> = JsonMissing.of(),
    ) : this(type, data, mimeType, resolution, uri, mutableMapOf())

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
     * The image content.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun data(): Optional<String> = data.getOptional("data")

    /**
     * The mime type of the image.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun mimeType(): Optional<MimeType> = mimeType.getOptional("mime_type")

    /**
     * The resolution of the media.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun resolution(): Optional<Resolution> = resolution.getOptional("resolution")

    /**
     * The URI of the image.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun uri(): Optional<String> = uri.getOptional("uri")

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
     * Returns the raw JSON value of [resolution].
     *
     * Unlike [resolution], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("resolution")
    @ExcludeMissing
    fun _resolution(): JsonField<Resolution> = resolution

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

        /** Returns a mutable builder for constructing an instance of [ImageContent]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [ImageContent]. */
    class Builder internal constructor() {

        private var type: JsonValue = JsonValue.from("image")
        private var data: JsonField<String> = JsonMissing.of()
        private var mimeType: JsonField<MimeType> = JsonMissing.of()
        private var resolution: JsonField<Resolution> = JsonMissing.of()
        private var uri: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(imageContent: ImageContent) = apply {
            type = imageContent.type
            data = imageContent.data
            mimeType = imageContent.mimeType
            resolution = imageContent.resolution
            uri = imageContent.uri
            additionalProperties = imageContent.additionalProperties.toMutableMap()
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

        /** The image content. */
        fun data(data: String) = data(JsonField.of(data))

        /**
         * Sets [Builder.data] to an arbitrary JSON value.
         *
         * You should usually call [Builder.data] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun data(data: JsonField<String>) = apply { this.data = data }

        /** The mime type of the image. */
        fun mimeType(mimeType: MimeType) = mimeType(JsonField.of(mimeType))

        /**
         * Sets [Builder.mimeType] to an arbitrary JSON value.
         *
         * You should usually call [Builder.mimeType] with a well-typed [MimeType] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun mimeType(mimeType: JsonField<MimeType>) = apply { this.mimeType = mimeType }

        /** The resolution of the media. */
        fun resolution(resolution: Resolution) = resolution(JsonField.of(resolution))

        /**
         * Sets [Builder.resolution] to an arbitrary JSON value.
         *
         * You should usually call [Builder.resolution] with a well-typed [Resolution] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun resolution(resolution: JsonField<Resolution>) = apply { this.resolution = resolution }

        /** The URI of the image. */
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
         * Returns an immutable instance of [ImageContent].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): ImageContent =
            ImageContent(type, data, mimeType, resolution, uri, additionalProperties.toMutableMap())
    }

    private var validated: Boolean = false

    fun validate(): ImageContent = apply {
        if (validated) {
            return@apply
        }

        _type().let {
            if (it != JsonValue.from("image")) {
                throw GeminiNextGenApiInvalidDataException("'type' is invalid, received $it")
            }
        }
        data()
        mimeType().ifPresent { it.validate() }
        resolution().ifPresent { it.validate() }
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
        type.let { if (it == JsonValue.from("image")) 1 else 0 } +
            (if (data.asKnown().isPresent) 1 else 0) +
            (mimeType.asKnown().getOrNull()?.validity() ?: 0) +
            (resolution.asKnown().getOrNull()?.validity() ?: 0) +
            (if (uri.asKnown().isPresent) 1 else 0)

    /** The mime type of the image. */
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

            @JvmField val IMAGE_PNG = of("image/png")

            @JvmField val IMAGE_JPEG = of("image/jpeg")

            @JvmField val IMAGE_WEBP = of("image/webp")

            @JvmField val IMAGE_HEIC = of("image/heic")

            @JvmField val IMAGE_HEIF = of("image/heif")

            @JvmField val IMAGE_GIF = of("image/gif")

            @JvmField val IMAGE_BMP = of("image/bmp")

            @JvmField val IMAGE_TIFF = of("image/tiff")

            @JvmStatic fun of(value: String) = MimeType(JsonField.of(value))
        }

        /** An enum containing [MimeType]'s known values. */
        enum class Known {
            IMAGE_PNG,
            IMAGE_JPEG,
            IMAGE_WEBP,
            IMAGE_HEIC,
            IMAGE_HEIF,
            IMAGE_GIF,
            IMAGE_BMP,
            IMAGE_TIFF,
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
            IMAGE_PNG,
            IMAGE_JPEG,
            IMAGE_WEBP,
            IMAGE_HEIC,
            IMAGE_HEIF,
            IMAGE_GIF,
            IMAGE_BMP,
            IMAGE_TIFF,
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
                IMAGE_PNG -> Value.IMAGE_PNG
                IMAGE_JPEG -> Value.IMAGE_JPEG
                IMAGE_WEBP -> Value.IMAGE_WEBP
                IMAGE_HEIC -> Value.IMAGE_HEIC
                IMAGE_HEIF -> Value.IMAGE_HEIF
                IMAGE_GIF -> Value.IMAGE_GIF
                IMAGE_BMP -> Value.IMAGE_BMP
                IMAGE_TIFF -> Value.IMAGE_TIFF
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
                IMAGE_PNG -> Known.IMAGE_PNG
                IMAGE_JPEG -> Known.IMAGE_JPEG
                IMAGE_WEBP -> Known.IMAGE_WEBP
                IMAGE_HEIC -> Known.IMAGE_HEIC
                IMAGE_HEIF -> Known.IMAGE_HEIF
                IMAGE_GIF -> Known.IMAGE_GIF
                IMAGE_BMP -> Known.IMAGE_BMP
                IMAGE_TIFF -> Known.IMAGE_TIFF
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

    /** The resolution of the media. */
    class Resolution @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

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

            @JvmField val LOW = of("low")

            @JvmField val MEDIUM = of("medium")

            @JvmField val HIGH = of("high")

            @JvmField val ULTRA_HIGH = of("ultra_high")

            @JvmStatic fun of(value: String) = Resolution(JsonField.of(value))
        }

        /** An enum containing [Resolution]'s known values. */
        enum class Known {
            LOW,
            MEDIUM,
            HIGH,
            ULTRA_HIGH,
        }

        /**
         * An enum containing [Resolution]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Resolution] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            LOW,
            MEDIUM,
            HIGH,
            ULTRA_HIGH,
            /**
             * An enum member indicating that [Resolution] was instantiated with an unknown value.
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
                LOW -> Value.LOW
                MEDIUM -> Value.MEDIUM
                HIGH -> Value.HIGH
                ULTRA_HIGH -> Value.ULTRA_HIGH
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
                LOW -> Known.LOW
                MEDIUM -> Known.MEDIUM
                HIGH -> Known.HIGH
                ULTRA_HIGH -> Known.ULTRA_HIGH
                else -> throw GeminiNextGenApiInvalidDataException("Unknown Resolution: $value")
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

        fun validate(): Resolution = apply {
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

            return other is Resolution && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ImageContent &&
            type == other.type &&
            data == other.data &&
            mimeType == other.mimeType &&
            resolution == other.resolution &&
            uri == other.uri &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(type, data, mimeType, resolution, uri, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "ImageContent{type=$type, data=$data, mimeType=$mimeType, resolution=$resolution, uri=$uri, additionalProperties=$additionalProperties}"
}
