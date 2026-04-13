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

/** The result of the Google Search. */
class GoogleSearchResult
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val searchSuggestions: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("search_suggestions")
        @ExcludeMissing
        searchSuggestions: JsonField<String> = JsonMissing.of()
    ) : this(searchSuggestions, mutableMapOf())

    /**
     * Web content snippet that can be embedded in a web page or an app webview.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun searchSuggestions(): Optional<String> = searchSuggestions.getOptional("search_suggestions")

    /**
     * Returns the raw JSON value of [searchSuggestions].
     *
     * Unlike [searchSuggestions], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("search_suggestions")
    @ExcludeMissing
    fun _searchSuggestions(): JsonField<String> = searchSuggestions

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

        /** Returns a mutable builder for constructing an instance of [GoogleSearchResult]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [GoogleSearchResult]. */
    class Builder internal constructor() {

        private var searchSuggestions: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(googleSearchResult: GoogleSearchResult) = apply {
            searchSuggestions = googleSearchResult.searchSuggestions
            additionalProperties = googleSearchResult.additionalProperties.toMutableMap()
        }

        /** Web content snippet that can be embedded in a web page or an app webview. */
        fun searchSuggestions(searchSuggestions: String) =
            searchSuggestions(JsonField.of(searchSuggestions))

        /**
         * Sets [Builder.searchSuggestions] to an arbitrary JSON value.
         *
         * You should usually call [Builder.searchSuggestions] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun searchSuggestions(searchSuggestions: JsonField<String>) = apply {
            this.searchSuggestions = searchSuggestions
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
         * Returns an immutable instance of [GoogleSearchResult].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): GoogleSearchResult =
            GoogleSearchResult(searchSuggestions, additionalProperties.toMutableMap())
    }

    private var validated: Boolean = false

    fun validate(): GoogleSearchResult = apply {
        if (validated) {
            return@apply
        }

        searchSuggestions()
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
    internal fun validity(): Int = (if (searchSuggestions.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is GoogleSearchResult &&
            searchSuggestions == other.searchSuggestions &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(searchSuggestions, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "GoogleSearchResult{searchSuggestions=$searchSuggestions, additionalProperties=$additionalProperties}"
}
