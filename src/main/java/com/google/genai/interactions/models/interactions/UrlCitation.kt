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

/** A URL citation annotation. */
class UrlCitation
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val type: JsonValue,
    private val endIndex: JsonField<Int>,
    private val startIndex: JsonField<Int>,
    private val title: JsonField<String>,
    private val url: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("end_index") @ExcludeMissing endIndex: JsonField<Int> = JsonMissing.of(),
        @JsonProperty("start_index") @ExcludeMissing startIndex: JsonField<Int> = JsonMissing.of(),
        @JsonProperty("title") @ExcludeMissing title: JsonField<String> = JsonMissing.of(),
        @JsonProperty("url") @ExcludeMissing url: JsonField<String> = JsonMissing.of(),
    ) : this(type, endIndex, startIndex, title, url, mutableMapOf())

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("url_citation")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * End of the attributed segment, exclusive.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun endIndex(): Optional<Int> = endIndex.getOptional("end_index")

    /**
     * Start of segment of the response that is attributed to this source.
     *
     * Index indicates the start of the segment, measured in bytes.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun startIndex(): Optional<Int> = startIndex.getOptional("start_index")

    /**
     * The title of the URL.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun title(): Optional<String> = title.getOptional("title")

    /**
     * The URL.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun url(): Optional<String> = url.getOptional("url")

    /**
     * Returns the raw JSON value of [endIndex].
     *
     * Unlike [endIndex], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("end_index") @ExcludeMissing fun _endIndex(): JsonField<Int> = endIndex

    /**
     * Returns the raw JSON value of [startIndex].
     *
     * Unlike [startIndex], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("start_index") @ExcludeMissing fun _startIndex(): JsonField<Int> = startIndex

    /**
     * Returns the raw JSON value of [title].
     *
     * Unlike [title], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("title") @ExcludeMissing fun _title(): JsonField<String> = title

    /**
     * Returns the raw JSON value of [url].
     *
     * Unlike [url], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("url") @ExcludeMissing fun _url(): JsonField<String> = url

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

        /** Returns a mutable builder for constructing an instance of [UrlCitation]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [UrlCitation]. */
    class Builder internal constructor() {

        private var type: JsonValue = JsonValue.from("url_citation")
        private var endIndex: JsonField<Int> = JsonMissing.of()
        private var startIndex: JsonField<Int> = JsonMissing.of()
        private var title: JsonField<String> = JsonMissing.of()
        private var url: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(urlCitation: UrlCitation) = apply {
            type = urlCitation.type
            endIndex = urlCitation.endIndex
            startIndex = urlCitation.startIndex
            title = urlCitation.title
            url = urlCitation.url
            additionalProperties = urlCitation.additionalProperties.toMutableMap()
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("url_citation")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /** End of the attributed segment, exclusive. */
        fun endIndex(endIndex: Int) = endIndex(JsonField.of(endIndex))

        /**
         * Sets [Builder.endIndex] to an arbitrary JSON value.
         *
         * You should usually call [Builder.endIndex] with a well-typed [Int] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun endIndex(endIndex: JsonField<Int>) = apply { this.endIndex = endIndex }

        /**
         * Start of segment of the response that is attributed to this source.
         *
         * Index indicates the start of the segment, measured in bytes.
         */
        fun startIndex(startIndex: Int) = startIndex(JsonField.of(startIndex))

        /**
         * Sets [Builder.startIndex] to an arbitrary JSON value.
         *
         * You should usually call [Builder.startIndex] with a well-typed [Int] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun startIndex(startIndex: JsonField<Int>) = apply { this.startIndex = startIndex }

        /** The title of the URL. */
        fun title(title: String) = title(JsonField.of(title))

        /**
         * Sets [Builder.title] to an arbitrary JSON value.
         *
         * You should usually call [Builder.title] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun title(title: JsonField<String>) = apply { this.title = title }

        /** The URL. */
        fun url(url: String) = url(JsonField.of(url))

        /**
         * Sets [Builder.url] to an arbitrary JSON value.
         *
         * You should usually call [Builder.url] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun url(url: JsonField<String>) = apply { this.url = url }

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
         * Returns an immutable instance of [UrlCitation].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): UrlCitation =
            UrlCitation(type, endIndex, startIndex, title, url, additionalProperties.toMutableMap())
    }

    private var validated: Boolean = false

    fun validate(): UrlCitation = apply {
        if (validated) {
            return@apply
        }

        _type().let {
            if (it != JsonValue.from("url_citation")) {
                throw GeminiNextGenApiInvalidDataException("'type' is invalid, received $it")
            }
        }
        endIndex()
        startIndex()
        title()
        url()
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
        type.let { if (it == JsonValue.from("url_citation")) 1 else 0 } +
            (if (endIndex.asKnown().isPresent) 1 else 0) +
            (if (startIndex.asKnown().isPresent) 1 else 0) +
            (if (title.asKnown().isPresent) 1 else 0) +
            (if (url.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is UrlCitation &&
            type == other.type &&
            endIndex == other.endIndex &&
            startIndex == other.startIndex &&
            title == other.title &&
            url == other.url &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(type, endIndex, startIndex, title, url, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "UrlCitation{type=$type, endIndex=$endIndex, startIndex=$startIndex, title=$title, url=$url, additionalProperties=$additionalProperties}"
}
