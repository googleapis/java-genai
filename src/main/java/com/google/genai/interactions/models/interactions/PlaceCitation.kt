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
import com.google.genai.interactions.core.checkKnown
import com.google.genai.interactions.core.toImmutable
import com.google.genai.interactions.errors.GeminiNextGenApiInvalidDataException
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** A place citation annotation. */
class PlaceCitation
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val type: JsonValue,
    private val endIndex: JsonField<Int>,
    private val name: JsonField<String>,
    private val placeId: JsonField<String>,
    private val reviewSnippets: JsonField<List<ReviewSnippet>>,
    private val startIndex: JsonField<Int>,
    private val url: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("end_index") @ExcludeMissing endIndex: JsonField<Int> = JsonMissing.of(),
        @JsonProperty("name") @ExcludeMissing name: JsonField<String> = JsonMissing.of(),
        @JsonProperty("place_id") @ExcludeMissing placeId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("review_snippets")
        @ExcludeMissing
        reviewSnippets: JsonField<List<ReviewSnippet>> = JsonMissing.of(),
        @JsonProperty("start_index") @ExcludeMissing startIndex: JsonField<Int> = JsonMissing.of(),
        @JsonProperty("url") @ExcludeMissing url: JsonField<String> = JsonMissing.of(),
    ) : this(type, endIndex, name, placeId, reviewSnippets, startIndex, url, mutableMapOf())

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("place_citation")
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
     * Title of the place.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun name(): Optional<String> = name.getOptional("name")

    /**
     * The ID of the place, in `places/{place_id}` format.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun placeId(): Optional<String> = placeId.getOptional("place_id")

    /**
     * Snippets of reviews that are used to generate answers about the features of a given place in
     * Google Maps.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun reviewSnippets(): Optional<List<ReviewSnippet>> =
        reviewSnippets.getOptional("review_snippets")

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
     * URI reference of the place.
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
     * Returns the raw JSON value of [name].
     *
     * Unlike [name], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("name") @ExcludeMissing fun _name(): JsonField<String> = name

    /**
     * Returns the raw JSON value of [placeId].
     *
     * Unlike [placeId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("place_id") @ExcludeMissing fun _placeId(): JsonField<String> = placeId

    /**
     * Returns the raw JSON value of [reviewSnippets].
     *
     * Unlike [reviewSnippets], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("review_snippets")
    @ExcludeMissing
    fun _reviewSnippets(): JsonField<List<ReviewSnippet>> = reviewSnippets

    /**
     * Returns the raw JSON value of [startIndex].
     *
     * Unlike [startIndex], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("start_index") @ExcludeMissing fun _startIndex(): JsonField<Int> = startIndex

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

        /** Returns a mutable builder for constructing an instance of [PlaceCitation]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [PlaceCitation]. */
    class Builder internal constructor() {

        private var type: JsonValue = JsonValue.from("place_citation")
        private var endIndex: JsonField<Int> = JsonMissing.of()
        private var name: JsonField<String> = JsonMissing.of()
        private var placeId: JsonField<String> = JsonMissing.of()
        private var reviewSnippets: JsonField<MutableList<ReviewSnippet>>? = null
        private var startIndex: JsonField<Int> = JsonMissing.of()
        private var url: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(placeCitation: PlaceCitation) = apply {
            type = placeCitation.type
            endIndex = placeCitation.endIndex
            name = placeCitation.name
            placeId = placeCitation.placeId
            reviewSnippets = placeCitation.reviewSnippets.map { it.toMutableList() }
            startIndex = placeCitation.startIndex
            url = placeCitation.url
            additionalProperties = placeCitation.additionalProperties.toMutableMap()
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("place_citation")
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

        /** Title of the place. */
        fun name(name: String) = name(JsonField.of(name))

        /**
         * Sets [Builder.name] to an arbitrary JSON value.
         *
         * You should usually call [Builder.name] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun name(name: JsonField<String>) = apply { this.name = name }

        /** The ID of the place, in `places/{place_id}` format. */
        fun placeId(placeId: String) = placeId(JsonField.of(placeId))

        /**
         * Sets [Builder.placeId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.placeId] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun placeId(placeId: JsonField<String>) = apply { this.placeId = placeId }

        /**
         * Snippets of reviews that are used to generate answers about the features of a given place
         * in Google Maps.
         */
        fun reviewSnippets(reviewSnippets: List<ReviewSnippet>) =
            reviewSnippets(JsonField.of(reviewSnippets))

        /**
         * Sets [Builder.reviewSnippets] to an arbitrary JSON value.
         *
         * You should usually call [Builder.reviewSnippets] with a well-typed `List<ReviewSnippet>`
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun reviewSnippets(reviewSnippets: JsonField<List<ReviewSnippet>>) = apply {
            this.reviewSnippets = reviewSnippets.map { it.toMutableList() }
        }

        /**
         * Adds a single [ReviewSnippet] to [reviewSnippets].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addReviewSnippet(reviewSnippet: ReviewSnippet) = apply {
            reviewSnippets =
                (reviewSnippets ?: JsonField.of(mutableListOf())).also {
                    checkKnown("reviewSnippets", it).add(reviewSnippet)
                }
        }

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

        /** URI reference of the place. */
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
         * Returns an immutable instance of [PlaceCitation].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): PlaceCitation =
            PlaceCitation(
                type,
                endIndex,
                name,
                placeId,
                (reviewSnippets ?: JsonMissing.of()).map { it.toImmutable() },
                startIndex,
                url,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): PlaceCitation = apply {
        if (validated) {
            return@apply
        }

        _type().let {
            if (it != JsonValue.from("place_citation")) {
                throw GeminiNextGenApiInvalidDataException("'type' is invalid, received $it")
            }
        }
        endIndex()
        name()
        placeId()
        reviewSnippets().ifPresent { it.forEach { it.validate() } }
        startIndex()
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
        type.let { if (it == JsonValue.from("place_citation")) 1 else 0 } +
            (if (endIndex.asKnown().isPresent) 1 else 0) +
            (if (name.asKnown().isPresent) 1 else 0) +
            (if (placeId.asKnown().isPresent) 1 else 0) +
            (reviewSnippets.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (if (startIndex.asKnown().isPresent) 1 else 0) +
            (if (url.asKnown().isPresent) 1 else 0)

    /**
     * Encapsulates a snippet of a user review that answers a question about the features of a
     * specific place in Google Maps.
     */
    class ReviewSnippet
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val reviewId: JsonField<String>,
        private val title: JsonField<String>,
        private val url: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("review_id")
            @ExcludeMissing
            reviewId: JsonField<String> = JsonMissing.of(),
            @JsonProperty("title") @ExcludeMissing title: JsonField<String> = JsonMissing.of(),
            @JsonProperty("url") @ExcludeMissing url: JsonField<String> = JsonMissing.of(),
        ) : this(reviewId, title, url, mutableMapOf())

        /**
         * The ID of the review snippet.
         *
         * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
         *   (e.g. if the server responded with an unexpected value).
         */
        fun reviewId(): Optional<String> = reviewId.getOptional("review_id")

        /**
         * Title of the review.
         *
         * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
         *   (e.g. if the server responded with an unexpected value).
         */
        fun title(): Optional<String> = title.getOptional("title")

        /**
         * A link that corresponds to the user review on Google Maps.
         *
         * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
         *   (e.g. if the server responded with an unexpected value).
         */
        fun url(): Optional<String> = url.getOptional("url")

        /**
         * Returns the raw JSON value of [reviewId].
         *
         * Unlike [reviewId], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("review_id") @ExcludeMissing fun _reviewId(): JsonField<String> = reviewId

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

            /** Returns a mutable builder for constructing an instance of [ReviewSnippet]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [ReviewSnippet]. */
        class Builder internal constructor() {

            private var reviewId: JsonField<String> = JsonMissing.of()
            private var title: JsonField<String> = JsonMissing.of()
            private var url: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(reviewSnippet: ReviewSnippet) = apply {
                reviewId = reviewSnippet.reviewId
                title = reviewSnippet.title
                url = reviewSnippet.url
                additionalProperties = reviewSnippet.additionalProperties.toMutableMap()
            }

            /** The ID of the review snippet. */
            fun reviewId(reviewId: String) = reviewId(JsonField.of(reviewId))

            /**
             * Sets [Builder.reviewId] to an arbitrary JSON value.
             *
             * You should usually call [Builder.reviewId] with a well-typed [String] value instead.
             * This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun reviewId(reviewId: JsonField<String>) = apply { this.reviewId = reviewId }

            /** Title of the review. */
            fun title(title: String) = title(JsonField.of(title))

            /**
             * Sets [Builder.title] to an arbitrary JSON value.
             *
             * You should usually call [Builder.title] with a well-typed [String] value instead.
             * This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun title(title: JsonField<String>) = apply { this.title = title }

            /** A link that corresponds to the user review on Google Maps. */
            fun url(url: String) = url(JsonField.of(url))

            /**
             * Sets [Builder.url] to an arbitrary JSON value.
             *
             * You should usually call [Builder.url] with a well-typed [String] value instead. This
             * method is primarily for setting the field to an undocumented or not yet supported
             * value.
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
             * Returns an immutable instance of [ReviewSnippet].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): ReviewSnippet =
                ReviewSnippet(reviewId, title, url, additionalProperties.toMutableMap())
        }

        private var validated: Boolean = false

        fun validate(): ReviewSnippet = apply {
            if (validated) {
                return@apply
            }

            reviewId()
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
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic
        internal fun validity(): Int =
            (if (reviewId.asKnown().isPresent) 1 else 0) +
                (if (title.asKnown().isPresent) 1 else 0) +
                (if (url.asKnown().isPresent) 1 else 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is ReviewSnippet &&
                reviewId == other.reviewId &&
                title == other.title &&
                url == other.url &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(reviewId, title, url, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "ReviewSnippet{reviewId=$reviewId, title=$title, url=$url, additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is PlaceCitation &&
            type == other.type &&
            endIndex == other.endIndex &&
            name == other.name &&
            placeId == other.placeId &&
            reviewSnippets == other.reviewSnippets &&
            startIndex == other.startIndex &&
            url == other.url &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            type,
            endIndex,
            name,
            placeId,
            reviewSnippets,
            startIndex,
            url,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "PlaceCitation{type=$type, endIndex=$endIndex, name=$name, placeId=$placeId, reviewSnippets=$reviewSnippets, startIndex=$startIndex, url=$url, additionalProperties=$additionalProperties}"
}
