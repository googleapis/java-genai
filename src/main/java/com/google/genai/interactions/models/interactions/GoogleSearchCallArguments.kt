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

/** The arguments to pass to Google Search. */
class GoogleSearchCallArguments
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val queries: JsonField<List<String>>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("queries") @ExcludeMissing queries: JsonField<List<String>> = JsonMissing.of()
    ) : this(queries, mutableMapOf())

    /**
     * Web search queries for the following-up web search.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
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

        /**
         * Returns a mutable builder for constructing an instance of [GoogleSearchCallArguments].
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [GoogleSearchCallArguments]. */
    class Builder internal constructor() {

        private var queries: JsonField<MutableList<String>>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(googleSearchCallArguments: GoogleSearchCallArguments) = apply {
            queries = googleSearchCallArguments.queries.map { it.toMutableList() }
            additionalProperties = googleSearchCallArguments.additionalProperties.toMutableMap()
        }

        /** Web search queries for the following-up web search. */
        fun queries(queries: List<String>) = queries(JsonField.of(queries))

        /**
         * Sets [Builder.queries] to an arbitrary JSON value.
         *
         * You should usually call [Builder.queries] with a well-typed `List<String>` value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
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
         * Returns an immutable instance of [GoogleSearchCallArguments].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): GoogleSearchCallArguments =
            GoogleSearchCallArguments(
                (queries ?: JsonMissing.of()).map { it.toImmutable() },
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): GoogleSearchCallArguments = apply {
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
     * Returns a score indicating how many valid values are contained in this object recursively.
     *
     * Used for best match union deserialization.
     */
    @JvmSynthetic internal fun validity(): Int = (queries.asKnown().getOrNull()?.size ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is GoogleSearchCallArguments &&
            queries == other.queries &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(queries, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "GoogleSearchCallArguments{queries=$queries, additionalProperties=$additionalProperties}"
}
