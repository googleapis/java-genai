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

/** File Search result content. */
class FileSearchResultContent
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val callId: JsonField<String>,
    private val type: JsonValue,
    private val result: JsonField<List<JsonValue>>,
    private val signature: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("call_id") @ExcludeMissing callId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("result")
        @ExcludeMissing
        result: JsonField<List<JsonValue>> = JsonMissing.of(),
        @JsonProperty("signature") @ExcludeMissing signature: JsonField<String> = JsonMissing.of(),
    ) : this(callId, type, result, signature, mutableMapOf())

    /**
     * Required. ID to match the ID from the function call block.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun callId(): String = callId.getRequired("call_id")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("file_search_result")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Optional. The results of the File Search.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun result(): Optional<List<JsonValue>> = result.getOptional("result")

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
    @JsonProperty("result") @ExcludeMissing fun _result(): JsonField<List<JsonValue>> = result

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
         * Returns a mutable builder for constructing an instance of [FileSearchResultContent].
         *
         * The following fields are required:
         * ```java
         * .callId()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [FileSearchResultContent]. */
    class Builder internal constructor() {

        private var callId: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("file_search_result")
        private var result: JsonField<MutableList<JsonValue>>? = null
        private var signature: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(fileSearchResultContent: FileSearchResultContent) = apply {
            callId = fileSearchResultContent.callId
            type = fileSearchResultContent.type
            result = fileSearchResultContent.result.map { it.toMutableList() }
            signature = fileSearchResultContent.signature
            additionalProperties = fileSearchResultContent.additionalProperties.toMutableMap()
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

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("file_search_result")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /** Optional. The results of the File Search. */
        fun result(result: List<JsonValue>) = result(JsonField.of(result))

        /**
         * Sets [Builder.result] to an arbitrary JSON value.
         *
         * You should usually call [Builder.result] with a well-typed `List<JsonValue>` value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun result(result: JsonField<List<JsonValue>>) = apply {
            this.result = result.map { it.toMutableList() }
        }

        /**
         * Adds a single [JsonValue] to [Builder.result].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addResult(result: JsonValue) = apply {
            this.result =
                (this.result ?: JsonField.of(mutableListOf())).also {
                    checkKnown("result", it).add(result)
                }
        }

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
         * Returns an immutable instance of [FileSearchResultContent].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .callId()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): FileSearchResultContent =
            FileSearchResultContent(
                checkRequired("callId", callId),
                type,
                (result ?: JsonMissing.of()).map { it.toImmutable() },
                signature,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): FileSearchResultContent = apply {
        if (validated) {
            return@apply
        }

        callId()
        _type().let {
            if (it != JsonValue.from("file_search_result")) {
                throw GeminiNextGenApiInvalidDataException("'type' is invalid, received $it")
            }
        }
        result()
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
            type.let { if (it == JsonValue.from("file_search_result")) 1 else 0 } +
            (result.asKnown().getOrNull()?.size ?: 0) +
            (if (signature.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is FileSearchResultContent &&
            callId == other.callId &&
            type == other.type &&
            result == other.result &&
            signature == other.signature &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(callId, type, result, signature, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "FileSearchResultContent{callId=$callId, type=$type, result=$result, signature=$signature, additionalProperties=$additionalProperties}"
}
