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

/** A video content block. */
class VideoContent
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
     * JsonValue.from("video")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * The video content.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun data(): Optional<String> = data.getOptional("data")

    /**
     * The mime type of the video.
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
     * The URI of the video.
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

        /** Returns a mutable builder for constructing an instance of [VideoContent]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [VideoContent]. */
    class Builder internal constructor() {

        private var type: JsonValue = JsonValue.from("video")
        private var data: JsonField<String> = JsonMissing.of()
        private var mimeType: JsonField<MimeType> = JsonMissing.of()
        private var resolution: JsonField<Resolution> = JsonMissing.of()
        private var uri: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(videoContent: VideoContent) = apply {
            type = videoContent.type
            data = videoContent.data
            mimeType = videoContent.mimeType
            resolution = videoContent.resolution
            uri = videoContent.uri
            additionalProperties = videoContent.additionalProperties.toMutableMap()
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("video")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /** The video content. */
        fun data(data: String) = data(JsonField.of(data))

        /**
         * Sets [Builder.data] to an arbitrary JSON value.
         *
         * You should usually call [Builder.data] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun data(data: JsonField<String>) = apply { this.data = data }

        /** The mime type of the video. */
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

        /** The URI of the video. */
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
         * Returns an immutable instance of [VideoContent].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): VideoContent =
            VideoContent(type, data, mimeType, resolution, uri, additionalProperties.toMutableMap())
    }

    private var validated: Boolean = false

    fun validate(): VideoContent = apply {
        if (validated) {
            return@apply
        }

        _type().let {
            if (it != JsonValue.from("video")) {
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
        type.let { if (it == JsonValue.from("video")) 1 else 0 } +
            (if (data.asKnown().isPresent) 1 else 0) +
            (mimeType.asKnown().getOrNull()?.validity() ?: 0) +
            (resolution.asKnown().getOrNull()?.validity() ?: 0) +
            (if (uri.asKnown().isPresent) 1 else 0)

    /** The mime type of the video. */
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

            @JvmField val VIDEO_MP4 = of("video/mp4")

            @JvmField val VIDEO_MPEG = of("video/mpeg")

            @JvmField val VIDEO_MPG = of("video/mpg")

            @JvmField val VIDEO_MOV = of("video/mov")

            @JvmField val VIDEO_AVI = of("video/avi")

            @JvmField val VIDEO_X_FLV = of("video/x-flv")

            @JvmField val VIDEO_WEBM = of("video/webm")

            @JvmField val VIDEO_WMV = of("video/wmv")

            @JvmField val VIDEO_3GPP = of("video/3gpp")

            @JvmStatic fun of(value: String) = MimeType(JsonField.of(value))
        }

        /** An enum containing [MimeType]'s known values. */
        enum class Known {
            VIDEO_MP4,
            VIDEO_MPEG,
            VIDEO_MPG,
            VIDEO_MOV,
            VIDEO_AVI,
            VIDEO_X_FLV,
            VIDEO_WEBM,
            VIDEO_WMV,
            VIDEO_3GPP,
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
            VIDEO_MP4,
            VIDEO_MPEG,
            VIDEO_MPG,
            VIDEO_MOV,
            VIDEO_AVI,
            VIDEO_X_FLV,
            VIDEO_WEBM,
            VIDEO_WMV,
            VIDEO_3GPP,
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
                VIDEO_MP4 -> Value.VIDEO_MP4
                VIDEO_MPEG -> Value.VIDEO_MPEG
                VIDEO_MPG -> Value.VIDEO_MPG
                VIDEO_MOV -> Value.VIDEO_MOV
                VIDEO_AVI -> Value.VIDEO_AVI
                VIDEO_X_FLV -> Value.VIDEO_X_FLV
                VIDEO_WEBM -> Value.VIDEO_WEBM
                VIDEO_WMV -> Value.VIDEO_WMV
                VIDEO_3GPP -> Value.VIDEO_3GPP
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
                VIDEO_MP4 -> Known.VIDEO_MP4
                VIDEO_MPEG -> Known.VIDEO_MPEG
                VIDEO_MPG -> Known.VIDEO_MPG
                VIDEO_MOV -> Known.VIDEO_MOV
                VIDEO_AVI -> Known.VIDEO_AVI
                VIDEO_X_FLV -> Known.VIDEO_X_FLV
                VIDEO_WEBM -> Known.VIDEO_WEBM
                VIDEO_WMV -> Known.VIDEO_WMV
                VIDEO_3GPP -> Known.VIDEO_3GPP
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

        return other is VideoContent &&
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
        "VideoContent{type=$type, data=$data, mimeType=$mimeType, resolution=$resolution, uri=$uri, additionalProperties=$additionalProperties}"
}
