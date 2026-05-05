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
import com.google.genai.interactions.core.checkKnown
import com.google.genai.interactions.core.checkRequired
import com.google.genai.interactions.core.toImmutable
import com.google.genai.interactions.errors.GeminiNextGenApiInvalidDataException
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** Google Search call step. */
class GoogleSearchCallStep
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val arguments: JsonField<Arguments>,
    private val type: JsonValue,
    private val searchType: JsonField<SearchType>,
    private val signature: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("arguments")
        @ExcludeMissing
        arguments: JsonField<Arguments> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("search_type")
        @ExcludeMissing
        searchType: JsonField<SearchType> = JsonMissing.of(),
        @JsonProperty("signature") @ExcludeMissing signature: JsonField<String> = JsonMissing.of(),
    ) : this(id, arguments, type, searchType, signature, mutableMapOf())

    /**
     * Required. A unique ID for this specific tool call.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    /**
     * Required. The arguments to pass to Google Search.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun arguments(): Arguments = arguments.getRequired("arguments")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("google_search_call")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * The type of search grounding enabled.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun searchType(): Optional<SearchType> = searchType.getOptional("search_type")

    /**
     * A signature hash for backend validation.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun signature(): Optional<String> = signature.getOptional("signature")

    /**
     * Returns the raw JSON value of [id].
     *
     * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    /**
     * Returns the raw JSON value of [arguments].
     *
     * Unlike [arguments], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("arguments") @ExcludeMissing fun _arguments(): JsonField<Arguments> = arguments

    /**
     * Returns the raw JSON value of [searchType].
     *
     * Unlike [searchType], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("search_type")
    @ExcludeMissing
    fun _searchType(): JsonField<SearchType> = searchType

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
         * Returns a mutable builder for constructing an instance of [GoogleSearchCallStep].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .arguments()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [GoogleSearchCallStep]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var arguments: JsonField<Arguments>? = null
        private var type: JsonValue = JsonValue.from("google_search_call")
        private var searchType: JsonField<SearchType> = JsonMissing.of()
        private var signature: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(googleSearchCallStep: GoogleSearchCallStep) = apply {
            id = googleSearchCallStep.id
            arguments = googleSearchCallStep.arguments
            type = googleSearchCallStep.type
            searchType = googleSearchCallStep.searchType
            signature = googleSearchCallStep.signature
            additionalProperties = googleSearchCallStep.additionalProperties.toMutableMap()
        }

        /** Required. A unique ID for this specific tool call. */
        fun id(id: String) = id(JsonField.of(id))

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

        /** Required. The arguments to pass to Google Search. */
        fun arguments(arguments: Arguments) = arguments(JsonField.of(arguments))

        /**
         * Sets [Builder.arguments] to an arbitrary JSON value.
         *
         * You should usually call [Builder.arguments] with a well-typed [Arguments] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun arguments(arguments: JsonField<Arguments>) = apply { this.arguments = arguments }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("google_search_call")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /** The type of search grounding enabled. */
        fun searchType(searchType: SearchType) = searchType(JsonField.of(searchType))

        /**
         * Sets [Builder.searchType] to an arbitrary JSON value.
         *
         * You should usually call [Builder.searchType] with a well-typed [SearchType] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun searchType(searchType: JsonField<SearchType>) = apply { this.searchType = searchType }

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
         * Returns an immutable instance of [GoogleSearchCallStep].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .arguments()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): GoogleSearchCallStep =
            GoogleSearchCallStep(
                checkRequired("id", id),
                checkRequired("arguments", arguments),
                type,
                searchType,
                signature,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): GoogleSearchCallStep = apply {
        if (validated) {
            return@apply
        }

        id()
        arguments().validate()
        _type().let {
            if (it != JsonValue.from("google_search_call")) {
                throw GeminiNextGenApiInvalidDataException("'type' is invalid, received $it")
            }
        }
        searchType().ifPresent { it.validate() }
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
        (if (id.asKnown().isPresent) 1 else 0) +
            (arguments.asKnown().getOrNull()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("google_search_call")) 1 else 0 } +
            (searchType.asKnown().getOrNull()?.validity() ?: 0) +
            (if (signature.asKnown().isPresent) 1 else 0)

    /** Required. The arguments to pass to Google Search. */
    class Arguments
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val queries: JsonField<List<String>>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("queries")
            @ExcludeMissing
            queries: JsonField<List<String>> = JsonMissing.of()
        ) : this(queries, mutableMapOf())

        /**
         * Web search queries for the following-up web search.
         *
         * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
         *   (e.g. if the server responded with an unexpected value).
         */
        fun queries(): Optional<List<String>> = queries.getOptional("queries")

        /**
         * Returns the raw JSON value of [queries].
         *
         * Unlike [queries], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("queries") @ExcludeMissing fun _queries(): JsonField<List<String>> = queries

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

            /** Returns a mutable builder for constructing an instance of [Arguments]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Arguments]. */
        class Builder internal constructor() {

            private var queries: JsonField<MutableList<String>>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(arguments: Arguments) = apply {
                queries = arguments.queries.map { it.toMutableList() }
                additionalProperties = arguments.additionalProperties.toMutableMap()
            }

            /** Web search queries for the following-up web search. */
            fun queries(queries: List<String>) = queries(JsonField.of(queries))

            /**
             * Sets [Builder.queries] to an arbitrary JSON value.
             *
             * You should usually call [Builder.queries] with a well-typed `List<String>` value
             * instead. This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun queries(queries: JsonField<List<String>>) = apply {
                this.queries = queries.map { it.toMutableList() }
            }

            /**
             * Adds a single [String] to [queries].
             *
             * @throws IllegalStateException if the field was previously set to a non-list.
             */
            fun addQuery(query: String) = apply {
                queries =
                    (queries ?: JsonField.of(mutableListOf())).also {
                        checkKnown("queries", it).add(query)
                    }
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
             * Returns an immutable instance of [Arguments].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): Arguments =
                Arguments(
                    (queries ?: JsonMissing.of()).map { it.toImmutable() },
                    additionalProperties.toMutableMap(),
                )
        }

        private var validated: Boolean = false

        fun validate(): Arguments = apply {
            if (validated) {
                return@apply
            }

            queries()
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
        @JvmSynthetic internal fun validity(): Int = (queries.asKnown().getOrNull()?.size ?: 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Arguments &&
                queries == other.queries &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(queries, additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Arguments{queries=$queries, additionalProperties=$additionalProperties}"
    }

    /** The type of search grounding enabled. */
    class SearchType @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

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

            @JvmField val WEB_SEARCH = of("web_search")

            @JvmField val IMAGE_SEARCH = of("image_search")

            @JvmField val ENTERPRISE_WEB_SEARCH = of("enterprise_web_search")

            @JvmStatic fun of(value: String) = SearchType(JsonField.of(value))
        }

        /** An enum containing [SearchType]'s known values. */
        enum class Known {
            WEB_SEARCH,
            IMAGE_SEARCH,
            ENTERPRISE_WEB_SEARCH,
        }

        /**
         * An enum containing [SearchType]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [SearchType] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            WEB_SEARCH,
            IMAGE_SEARCH,
            ENTERPRISE_WEB_SEARCH,
            /**
             * An enum member indicating that [SearchType] was instantiated with an unknown value.
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
                WEB_SEARCH -> Value.WEB_SEARCH
                IMAGE_SEARCH -> Value.IMAGE_SEARCH
                ENTERPRISE_WEB_SEARCH -> Value.ENTERPRISE_WEB_SEARCH
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
                WEB_SEARCH -> Known.WEB_SEARCH
                IMAGE_SEARCH -> Known.IMAGE_SEARCH
                ENTERPRISE_WEB_SEARCH -> Known.ENTERPRISE_WEB_SEARCH
                else -> throw GeminiNextGenApiInvalidDataException("Unknown SearchType: $value")
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

        fun validate(): SearchType = apply {
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

            return other is SearchType && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is GoogleSearchCallStep &&
            id == other.id &&
            arguments == other.arguments &&
            type == other.type &&
            searchType == other.searchType &&
            signature == other.signature &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(id, arguments, type, searchType, signature, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "GoogleSearchCallStep{id=$id, arguments=$arguments, type=$type, searchType=$searchType, signature=$signature, additionalProperties=$additionalProperties}"
}
