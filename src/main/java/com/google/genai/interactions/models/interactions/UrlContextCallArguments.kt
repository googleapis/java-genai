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

/** The arguments to pass to the URL context. */
class UrlContextCallArguments
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val urls: JsonField<List<String>>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("urls") @ExcludeMissing urls: JsonField<List<String>> = JsonMissing.of()
    ) : this(urls, mutableMapOf())

    /**
     * The URLs to fetch.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun urls(): Optional<List<String>> = urls.getOptional("urls")

    /**
     * Returns the raw JSON value of [urls].
     *
     * Unlike [urls], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("urls") @ExcludeMissing fun _urls(): JsonField<List<String>> = urls

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

        /** Returns a mutable builder for constructing an instance of [UrlContextCallArguments]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [UrlContextCallArguments]. */
    class Builder internal constructor() {

        private var urls: JsonField<MutableList<String>>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(urlContextCallArguments: UrlContextCallArguments) = apply {
            urls = urlContextCallArguments.urls.map { it.toMutableList() }
            additionalProperties = urlContextCallArguments.additionalProperties.toMutableMap()
        }

        /** The URLs to fetch. */
        fun urls(urls: List<String>) = urls(JsonField.of(urls))

        /**
         * Sets [Builder.urls] to an arbitrary JSON value.
         *
         * You should usually call [Builder.urls] with a well-typed `List<String>` value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun urls(urls: JsonField<List<String>>) = apply {
            this.urls = urls.map { it.toMutableList() }
        }

        /**
         * Adds a single [String] to [urls].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addUrl(url: String) = apply {
            urls = (urls ?: JsonField.of(mutableListOf())).also { checkKnown("urls", it).add(url) }
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
         * Returns an immutable instance of [UrlContextCallArguments].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): UrlContextCallArguments =
            UrlContextCallArguments(
                (urls ?: JsonMissing.of()).map { it.toImmutable() },
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): UrlContextCallArguments = apply {
        if (validated) {
            return@apply
        }

        urls()
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
    @JvmSynthetic internal fun validity(): Int = (urls.asKnown().getOrNull()?.size ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is UrlContextCallArguments &&
            urls == other.urls &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(urls, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "UrlContextCallArguments{urls=$urls, additionalProperties=$additionalProperties}"
}
