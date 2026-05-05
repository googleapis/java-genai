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
import com.google.genai.interactions.core.checkRequired
import com.google.genai.interactions.core.toImmutable
import com.google.genai.interactions.errors.GeminiNextGenApiInvalidDataException
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** Google Maps result step. */
class GoogleMapsResultStep
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val callId: JsonField<String>,
    private val result: JsonField<List<Result>>,
    private val type: JsonValue,
    private val signature: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("call_id") @ExcludeMissing callId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("result") @ExcludeMissing result: JsonField<List<Result>> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("signature") @ExcludeMissing signature: JsonField<String> = JsonMissing.of(),
    ) : this(callId, result, type, signature, mutableMapOf())

    /**
     * Required. ID to match the ID from the function call block.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun callId(): String = callId.getRequired("call_id")

    /**
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun result(): List<Result> = result.getRequired("result")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("google_maps_result")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * A signature hash for backend validation.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun signature(): Optional<String> = signature.getOptional("signature")

    /**
     * Returns the raw JSON value of [callId].
     *
     * Unlike [callId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("call_id") @ExcludeMissing fun _callId(): JsonField<String> = callId

    /**
     * Returns the raw JSON value of [result].
     *
     * Unlike [result], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("result") @ExcludeMissing fun _result(): JsonField<List<Result>> = result

    /**
     * Returns the raw JSON value of [signature].
     *
     * Unlike [signature], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("signature") @ExcludeMissing fun _signature(): JsonField<String> = signature

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

        /**
         * Returns a mutable builder for constructing an instance of [GoogleMapsResultStep].
         *
         * The following fields are required:
         * ```java
         * .callId()
         * .result()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [GoogleMapsResultStep]. */
    class Builder internal constructor() {

        private var callId: JsonField<String>? = null
        private var result: JsonField<MutableList<Result>>? = null
        private var type: JsonValue = JsonValue.from("google_maps_result")
        private var signature: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(googleMapsResultStep: GoogleMapsResultStep) = apply {
            callId = googleMapsResultStep.callId
            result = googleMapsResultStep.result.map { it.toMutableList() }
            type = googleMapsResultStep.type
            signature = googleMapsResultStep.signature
            additionalProperties = googleMapsResultStep.additionalProperties.toMutableMap()
        }

        /** Required. ID to match the ID from the function call block. */
        fun callId(callId: String) = callId(JsonField.of(callId))

        /**
         * Sets [Builder.callId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.callId] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun callId(callId: JsonField<String>) = apply { this.callId = callId }

        fun result(result: List<Result>) = result(JsonField.of(result))

        /**
         * Sets [Builder.result] to an arbitrary JSON value.
         *
         * You should usually call [Builder.result] with a well-typed `List<Result>` value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun result(result: JsonField<List<Result>>) = apply {
            this.result = result.map { it.toMutableList() }
        }

        /**
         * Adds a single [Result] to [Builder.result].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addResult(result: Result) = apply {
            this.result =
                (this.result ?: JsonField.of(mutableListOf())).also {
                    checkKnown("result", it).add(result)
                }
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("google_maps_result")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /** A signature hash for backend validation. */
        fun signature(signature: String) = signature(JsonField.of(signature))

        /**
         * Sets [Builder.signature] to an arbitrary JSON value.
         *
         * You should usually call [Builder.signature] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun signature(signature: JsonField<String>) = apply { this.signature = signature }

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
         * Returns an immutable instance of [GoogleMapsResultStep].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .callId()
         * .result()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): GoogleMapsResultStep =
            GoogleMapsResultStep(
                checkRequired("callId", callId),
                checkRequired("result", result).map { it.toImmutable() },
                type,
                signature,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): GoogleMapsResultStep = apply {
        if (validated) {
            return@apply
        }

        callId()
        result().forEach { it.validate() }
        _type().let {
            if (it != JsonValue.from("google_maps_result")) {
                throw GeminiNextGenApiInvalidDataException("'type' is invalid, received $it")
            }
        }
        signature()
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
        (if (callId.asKnown().isPresent) 1 else 0) +
            (result.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            type.let { if (it == JsonValue.from("google_maps_result")) 1 else 0 } +
            (if (signature.asKnown().isPresent) 1 else 0)

    /** The result of the Google Maps. */
    class Result
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val places: JsonField<List<Place>>,
        private val widgetContextToken: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("places")
            @ExcludeMissing
            places: JsonField<List<Place>> = JsonMissing.of(),
            @JsonProperty("widget_context_token")
            @ExcludeMissing
            widgetContextToken: JsonField<String> = JsonMissing.of(),
        ) : this(places, widgetContextToken, mutableMapOf())

        /**
         * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
         *   (e.g. if the server responded with an unexpected value).
         */
        fun places(): Optional<List<Place>> = places.getOptional("places")

        /**
         * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
         *   (e.g. if the server responded with an unexpected value).
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
         * Unlike [widgetContextToken], this method doesn't throw if the JSON field has an
         * unexpected type.
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

            /** Returns a mutable builder for constructing an instance of [Result]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Result]. */
        class Builder internal constructor() {

            private var places: JsonField<MutableList<Place>>? = null
            private var widgetContextToken: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(result: Result) = apply {
                places = result.places.map { it.toMutableList() }
                widgetContextToken = result.widgetContextToken
                additionalProperties = result.additionalProperties.toMutableMap()
            }

            fun places(places: List<Place>) = places(JsonField.of(places))

            /**
             * Sets [Builder.places] to an arbitrary JSON value.
             *
             * You should usually call [Builder.places] with a well-typed `List<Place>` value
             * instead. This method is primarily for setting the field to an undocumented or not yet
             * supported value.
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
             * Returns an immutable instance of [Result].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): Result =
                Result(
                    (places ?: JsonMissing.of()).map { it.toImmutable() },
                    widgetContextToken,
                    additionalProperties.toMutableMap(),
                )
        }

        private var validated: Boolean = false

        fun validate(): Result = apply {
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
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
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
                @JsonProperty("place_id")
                @ExcludeMissing
                placeId: JsonField<String> = JsonMissing.of(),
                @JsonProperty("review_snippets")
                @ExcludeMissing
                reviewSnippets: JsonField<List<ReviewSnippet>> = JsonMissing.of(),
                @JsonProperty("url") @ExcludeMissing url: JsonField<String> = JsonMissing.of(),
            ) : this(name, placeId, reviewSnippets, url, mutableMapOf())

            /**
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   (e.g. if the server responded with an unexpected value).
             */
            fun name(): Optional<String> = name.getOptional("name")

            /**
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   (e.g. if the server responded with an unexpected value).
             */
            fun placeId(): Optional<String> = placeId.getOptional("place_id")

            /**
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   (e.g. if the server responded with an unexpected value).
             */
            fun reviewSnippets(): Optional<List<ReviewSnippet>> =
                reviewSnippets.getOptional("review_snippets")

            /**
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
             * Unlike [reviewSnippets], this method doesn't throw if the JSON field has an
             * unexpected type.
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

                fun name(name: String) = name(JsonField.of(name))

                /**
                 * Sets [Builder.name] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.name] with a well-typed [String] value instead.
                 * This method is primarily for setting the field to an undocumented or not yet
                 * supported value.
                 */
                fun name(name: JsonField<String>) = apply { this.name = name }

                fun placeId(placeId: String) = placeId(JsonField.of(placeId))

                /**
                 * Sets [Builder.placeId] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.placeId] with a well-typed [String] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun placeId(placeId: JsonField<String>) = apply { this.placeId = placeId }

                fun reviewSnippets(reviewSnippets: List<ReviewSnippet>) =
                    reviewSnippets(JsonField.of(reviewSnippets))

                /**
                 * Sets [Builder.reviewSnippets] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.reviewSnippets] with a well-typed
                 * `List<ReviewSnippet>` value instead. This method is primarily for setting the
                 * field to an undocumented or not yet supported value.
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
             * Encapsulates a snippet of a user review that answers a question about the features of
             * a specific place in Google Maps.
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
                    @JsonProperty("title")
                    @ExcludeMissing
                    title: JsonField<String> = JsonMissing.of(),
                    @JsonProperty("url") @ExcludeMissing url: JsonField<String> = JsonMissing.of(),
                ) : this(reviewId, title, url, mutableMapOf())

                /**
                 * The ID of the review snippet.
                 *
                 * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected
                 *   type (e.g. if the server responded with an unexpected value).
                 */
                fun reviewId(): Optional<String> = reviewId.getOptional("review_id")

                /**
                 * Title of the review.
                 *
                 * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected
                 *   type (e.g. if the server responded with an unexpected value).
                 */
                fun title(): Optional<String> = title.getOptional("title")

                /**
                 * A link that corresponds to the user review on Google Maps.
                 *
                 * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected
                 *   type (e.g. if the server responded with an unexpected value).
                 */
                fun url(): Optional<String> = url.getOptional("url")

                /**
                 * Returns the raw JSON value of [reviewId].
                 *
                 * Unlike [reviewId], this method doesn't throw if the JSON field has an unexpected
                 * type.
                 */
                @JsonProperty("review_id")
                @ExcludeMissing
                fun _reviewId(): JsonField<String> = reviewId

                /**
                 * Returns the raw JSON value of [title].
                 *
                 * Unlike [title], this method doesn't throw if the JSON field has an unexpected
                 * type.
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

                    /**
                     * Returns a mutable builder for constructing an instance of [ReviewSnippet].
                     */
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
                     * instead. This method is primarily for setting the field to an undocumented or
                     * not yet supported value.
                     */
                    fun reviewId(reviewId: JsonField<String>) = apply { this.reviewId = reviewId }

                    /** Title of the review. */
                    fun title(title: String) = title(JsonField.of(title))

                    /**
                     * Sets [Builder.title] to an arbitrary JSON value.
                     *
                     * You should usually call [Builder.title] with a well-typed [String] value
                     * instead. This method is primarily for setting the field to an undocumented or
                     * not yet supported value.
                     */
                    fun title(title: JsonField<String>) = apply { this.title = title }

                    /** A link that corresponds to the user review on Google Maps. */
                    fun url(url: String) = url(JsonField.of(url))

                    /**
                     * Sets [Builder.url] to an arbitrary JSON value.
                     *
                     * You should usually call [Builder.url] with a well-typed [String] value
                     * instead. This method is primarily for setting the field to an undocumented or
                     * not yet supported value.
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

            return other is Result &&
                places == other.places &&
                widgetContextToken == other.widgetContextToken &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(places, widgetContextToken, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Result{places=$places, widgetContextToken=$widgetContextToken, additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is GoogleMapsResultStep &&
            callId == other.callId &&
            result == other.result &&
            type == other.type &&
            signature == other.signature &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(callId, result, type, signature, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "GoogleMapsResultStep{callId=$callId, result=$result, type=$type, signature=$signature, additionalProperties=$additionalProperties}"
}
