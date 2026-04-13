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

/** The result of the Google Maps. */
class GoogleMapsResult
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val places: JsonField<List<Place>>,
    private val widgetContextToken: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("places") @ExcludeMissing places: JsonField<List<Place>> = JsonMissing.of(),
        @JsonProperty("widget_context_token")
        @ExcludeMissing
        widgetContextToken: JsonField<String> = JsonMissing.of(),
    ) : this(places, widgetContextToken, mutableMapOf())

    /**
     * The places that were found.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun places(): Optional<List<Place>> = places.getOptional("places")

    /**
     * Resource name of the Google Maps widget context token.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun widgetContextToken(): Optional<String> =
        widgetContextToken.getOptional("widget_context_token")

    /**
     * Returns the raw JSON value of [places].
     *
     * Unlike [places], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("places") @ExcludeMissing fun _places(): JsonField<List<Place>> = places

    /**
     * Returns the raw JSON value of [widgetContextToken].
     *
     * Unlike [widgetContextToken], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("widget_context_token")
    @ExcludeMissing
    fun _widgetContextToken(): JsonField<String> = widgetContextToken

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

        /** Returns a mutable builder for constructing an instance of [GoogleMapsResult]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [GoogleMapsResult]. */
    class Builder internal constructor() {

        private var places: JsonField<MutableList<Place>>? = null
        private var widgetContextToken: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(googleMapsResult: GoogleMapsResult) = apply {
            places = googleMapsResult.places.map { it.toMutableList() }
            widgetContextToken = googleMapsResult.widgetContextToken
            additionalProperties = googleMapsResult.additionalProperties.toMutableMap()
        }

        /** The places that were found. */
        fun places(places: List<Place>) = places(JsonField.of(places))

        /**
         * Sets [Builder.places] to an arbitrary JSON value.
         *
         * You should usually call [Builder.places] with a well-typed `List<Place>` value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun places(places: JsonField<List<Place>>) = apply {
            this.places = places.map { it.toMutableList() }
        }

        /**
         * Adds a single [Place] to [places].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addPlace(place: Place) = apply {
            places =
                (places ?: JsonField.of(mutableListOf())).also {
                    checkKnown("places", it).add(place)
                }
        }

        /** Resource name of the Google Maps widget context token. */
        fun widgetContextToken(widgetContextToken: String) =
            widgetContextToken(JsonField.of(widgetContextToken))

        /**
         * Sets [Builder.widgetContextToken] to an arbitrary JSON value.
         *
         * You should usually call [Builder.widgetContextToken] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun widgetContextToken(widgetContextToken: JsonField<String>) = apply {
            this.widgetContextToken = widgetContextToken
        }

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
         * Returns an immutable instance of [GoogleMapsResult].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): GoogleMapsResult =
            GoogleMapsResult(
                (places ?: JsonMissing.of()).map { it.toImmutable() },
                widgetContextToken,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): GoogleMapsResult = apply {
        if (validated) {
            return@apply
        }

        places().ifPresent { it.forEach { it.validate() } }
        widgetContextToken()
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
        (places.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (if (widgetContextToken.asKnown().isPresent) 1 else 0)

    class Place
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val name: JsonField<String>,
        private val placeId: JsonField<String>,
        private val reviewSnippets: JsonField<List<ReviewSnippet>>,
        private val url: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("name") @ExcludeMissing name: JsonField<String> = JsonMissing.of(),
            @JsonProperty("place_id") @ExcludeMissing placeId: JsonField<String> = JsonMissing.of(),
            @JsonProperty("review_snippets")
            @ExcludeMissing
            reviewSnippets: JsonField<List<ReviewSnippet>> = JsonMissing.of(),
            @JsonProperty("url") @ExcludeMissing url: JsonField<String> = JsonMissing.of(),
        ) : this(name, placeId, reviewSnippets, url, mutableMapOf())

        /**
         * Title of the place.
         *
         * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
         *   (e.g. if the server responded with an unexpected value).
         */
        fun name(): Optional<String> = name.getOptional("name")

        /**
         * The ID of the place, in `places/{place_id}` format.
         *
         * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
         *   (e.g. if the server responded with an unexpected value).
         */
        fun placeId(): Optional<String> = placeId.getOptional("place_id")

        /**
         * Snippets of reviews that are used to generate answers about the features of a given place
         * in Google Maps.
         *
         * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
         *   (e.g. if the server responded with an unexpected value).
         */
        fun reviewSnippets(): Optional<List<ReviewSnippet>> =
            reviewSnippets.getOptional("review_snippets")

        /**
         * URI reference of the place.
         *
         * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
         *   (e.g. if the server responded with an unexpected value).
         */
        fun url(): Optional<String> = url.getOptional("url")

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
         * Unlike [reviewSnippets], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("review_snippets")
        @ExcludeMissing
        fun _reviewSnippets(): JsonField<List<ReviewSnippet>> = reviewSnippets

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

            /** Returns a mutable builder for constructing an instance of [Place]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Place]. */
        class Builder internal constructor() {

            private var name: JsonField<String> = JsonMissing.of()
            private var placeId: JsonField<String> = JsonMissing.of()
            private var reviewSnippets: JsonField<MutableList<ReviewSnippet>>? = null
            private var url: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(place: Place) = apply {
                name = place.name
                placeId = place.placeId
                reviewSnippets = place.reviewSnippets.map { it.toMutableList() }
                url = place.url
                additionalProperties = place.additionalProperties.toMutableMap()
            }

            /** Title of the place. */
            fun name(name: String) = name(JsonField.of(name))

            /**
             * Sets [Builder.name] to an arbitrary JSON value.
             *
             * You should usually call [Builder.name] with a well-typed [String] value instead. This
             * method is primarily for setting the field to an undocumented or not yet supported
             * value.
             */
            fun name(name: JsonField<String>) = apply { this.name = name }

            /** The ID of the place, in `places/{place_id}` format. */
            fun placeId(placeId: String) = placeId(JsonField.of(placeId))

            /**
             * Sets [Builder.placeId] to an arbitrary JSON value.
             *
             * You should usually call [Builder.placeId] with a well-typed [String] value instead.
             * This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun placeId(placeId: JsonField<String>) = apply { this.placeId = placeId }

            /**
             * Snippets of reviews that are used to generate answers about the features of a given
             * place in Google Maps.
             */
            fun reviewSnippets(reviewSnippets: List<ReviewSnippet>) =
                reviewSnippets(JsonField.of(reviewSnippets))

            /**
             * Sets [Builder.reviewSnippets] to an arbitrary JSON value.
             *
             * You should usually call [Builder.reviewSnippets] with a well-typed
             * `List<ReviewSnippet>` value instead. This method is primarily for setting the field
             * to an undocumented or not yet supported value.
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

            /** URI reference of the place. */
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
             * Returns an immutable instance of [Place].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): Place =
                Place(
                    name,
                    placeId,
                    (reviewSnippets ?: JsonMissing.of()).map { it.toImmutable() },
                    url,
                    additionalProperties.toMutableMap(),
                )
        }

        private var validated: Boolean = false

        fun validate(): Place = apply {
            if (validated) {
                return@apply
            }

            name()
            placeId()
            reviewSnippets().ifPresent { it.forEach { it.validate() } }
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
            (if (name.asKnown().isPresent) 1 else 0) +
                (if (placeId.asKnown().isPresent) 1 else 0) +
                (reviewSnippets.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
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
             * Unlike [reviewId], this method doesn't throw if the JSON field has an unexpected
             * type.
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
                 * You should usually call [Builder.reviewId] with a well-typed [String] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
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
                 * You should usually call [Builder.url] with a well-typed [String] value instead.
                 * This method is primarily for setting the field to an undocumented or not yet
                 * supported value.
                 */
                fun url(url: JsonField<String>) = apply { this.url = url }

                fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                    this.additionalProperties.clear()
                    putAllAdditionalProperties(additionalProperties)
                }

                fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                    additionalProperties.put(key, value)
                }

                fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) =
                    apply {
                        this.additionalProperties.putAll(additionalProperties)
                    }

                fun removeAdditionalProperty(key: String) = apply {
                    additionalProperties.remove(key)
                }

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

            return other is Place &&
                name == other.name &&
                placeId == other.placeId &&
                reviewSnippets == other.reviewSnippets &&
                url == other.url &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(name, placeId, reviewSnippets, url, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Place{name=$name, placeId=$placeId, reviewSnippets=$reviewSnippets, url=$url, additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is GoogleMapsResult &&
            places == other.places &&
            widgetContextToken == other.widgetContextToken &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(places, widgetContextToken, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "GoogleMapsResult{places=$places, widgetContextToken=$widgetContextToken, additionalProperties=$additionalProperties}"
}
