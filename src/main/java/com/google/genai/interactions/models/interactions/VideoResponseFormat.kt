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

/** Configuration for video output format. */
class VideoResponseFormat
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val type: JsonValue,
    private val aspectRatio: JsonField<AspectRatio>,
    private val delivery: JsonField<Delivery>,
    private val duration: JsonField<String>,
    private val gcsUri: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("aspectRatio")
        @ExcludeMissing
        aspectRatio: JsonField<AspectRatio> = JsonMissing.of(),
        @JsonProperty("delivery") @ExcludeMissing delivery: JsonField<Delivery> = JsonMissing.of(),
        @JsonProperty("duration") @ExcludeMissing duration: JsonField<String> = JsonMissing.of(),
        @JsonProperty("gcsUri") @ExcludeMissing gcsUri: JsonField<String> = JsonMissing.of(),
    ) : this(type, aspectRatio, delivery, duration, gcsUri, mutableMapOf())

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
     * The aspect ratio for the video output.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun aspectRatio(): Optional<AspectRatio> = aspectRatio.getOptional("aspectRatio")

    /**
     * The delivery mode for the video output.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun delivery(): Optional<Delivery> = delivery.getOptional("delivery")

    /**
     * The duration for the video output.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun duration(): Optional<String> = duration.getOptional("duration")

    /**
     * The GCS URI to store the video output. Required for Vertex if delivery mode is URI.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun gcsUri(): Optional<String> = gcsUri.getOptional("gcsUri")

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
     * Returns the raw JSON value of [duration].
     *
     * Unlike [duration], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("duration") @ExcludeMissing fun _duration(): JsonField<String> = duration

    /**
     * Returns the raw JSON value of [gcsUri].
     *
     * Unlike [gcsUri], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("gcsUri") @ExcludeMissing fun _gcsUri(): JsonField<String> = gcsUri

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

        /** Returns a mutable builder for constructing an instance of [VideoResponseFormat]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [VideoResponseFormat]. */
    class Builder internal constructor() {

        private var type: JsonValue = JsonValue.from("video")
        private var aspectRatio: JsonField<AspectRatio> = JsonMissing.of()
        private var delivery: JsonField<Delivery> = JsonMissing.of()
        private var duration: JsonField<String> = JsonMissing.of()
        private var gcsUri: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(videoResponseFormat: VideoResponseFormat) = apply {
            type = videoResponseFormat.type
            aspectRatio = videoResponseFormat.aspectRatio
            delivery = videoResponseFormat.delivery
            duration = videoResponseFormat.duration
            gcsUri = videoResponseFormat.gcsUri
            additionalProperties = videoResponseFormat.additionalProperties.toMutableMap()
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

        /** The aspect ratio for the video output. */
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

        /** The delivery mode for the video output. */
        fun delivery(delivery: Delivery) = delivery(JsonField.of(delivery))

        /**
         * Sets [Builder.delivery] to an arbitrary JSON value.
         *
         * You should usually call [Builder.delivery] with a well-typed [Delivery] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun delivery(delivery: JsonField<Delivery>) = apply { this.delivery = delivery }

        /** The duration for the video output. */
        fun duration(duration: String) = duration(JsonField.of(duration))

        /**
         * Sets [Builder.duration] to an arbitrary JSON value.
         *
         * You should usually call [Builder.duration] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun duration(duration: JsonField<String>) = apply { this.duration = duration }

        /** The GCS URI to store the video output. Required for Vertex if delivery mode is URI. */
        fun gcsUri(gcsUri: String) = gcsUri(JsonField.of(gcsUri))

        /**
         * Sets [Builder.gcsUri] to an arbitrary JSON value.
         *
         * You should usually call [Builder.gcsUri] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun gcsUri(gcsUri: JsonField<String>) = apply { this.gcsUri = gcsUri }

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
         * Returns an immutable instance of [VideoResponseFormat].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): VideoResponseFormat =
            VideoResponseFormat(
                type,
                aspectRatio,
                delivery,
                duration,
                gcsUri,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): VideoResponseFormat = apply {
        if (validated) {
            return@apply
        }

        _type().let {
            if (it != JsonValue.from("video")) {
                throw GeminiNextGenApiInvalidDataException("'type' is invalid, received $it")
            }
        }
        aspectRatio().ifPresent { it.validate() }
        delivery().ifPresent { it.validate() }
        duration()
        gcsUri()
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
            (aspectRatio.asKnown().getOrNull()?.validity() ?: 0) +
            (delivery.asKnown().getOrNull()?.validity() ?: 0) +
            (if (duration.asKnown().isPresent) 1 else 0) +
            (if (gcsUri.asKnown().isPresent) 1 else 0)

    /** The aspect ratio for the video output. */
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

            @JvmField val _16_9 = of("16:9")

            @JvmField val _9_16 = of("9:16")

            @JvmStatic fun of(value: String) = AspectRatio(JsonField.of(value))
        }

        /** An enum containing [AspectRatio]'s known values. */
        enum class Known {
            _16_9,
            _9_16,
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
            _16_9,
            _9_16,
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
                _16_9 -> Value._16_9
                _9_16 -> Value._9_16
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
                _16_9 -> Known._16_9
                _9_16 -> Known._9_16
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

    /** The delivery mode for the video output. */
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

            @JvmField val URI = of("uri")

            @JvmStatic fun of(value: String) = Delivery(JsonField.of(value))
        }

        /** An enum containing [Delivery]'s known values. */
        enum class Known {
            INLINE,
            URI,
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
            URI,
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
                URI -> Value.URI
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
                URI -> Known.URI
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

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is VideoResponseFormat &&
            type == other.type &&
            aspectRatio == other.aspectRatio &&
            delivery == other.delivery &&
            duration == other.duration &&
            gcsUri == other.gcsUri &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(type, aspectRatio, delivery, duration, gcsUri, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "VideoResponseFormat{type=$type, aspectRatio=$aspectRatio, delivery=$delivery, duration=$duration, gcsUri=$gcsUri, additionalProperties=$additionalProperties}"
}
