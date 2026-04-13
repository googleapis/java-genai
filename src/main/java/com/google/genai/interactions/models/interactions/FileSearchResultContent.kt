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
     * Required. The results of the File Search.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun result(): List<Result> = result.getRequired("result")

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
         * Returns a mutable builder for constructing an instance of [FileSearchResultContent].
         *
         * The following fields are required:
         * ```java
         * .callId()
         * .result()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [FileSearchResultContent]. */
    class Builder internal constructor() {

        private var callId: JsonField<String>? = null
        private var result: JsonField<MutableList<Result>>? = null
        private var type: JsonValue = JsonValue.from("file_search_result")
        private var signature: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(fileSearchResultContent: FileSearchResultContent) = apply {
            callId = fileSearchResultContent.callId
            result = fileSearchResultContent.result.map { it.toMutableList() }
            type = fileSearchResultContent.type
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

        /** Required. The results of the File Search. */
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
         * JsonValue.from("file_search_result")
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
         * Returns an immutable instance of [FileSearchResultContent].
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
        fun build(): FileSearchResultContent =
            FileSearchResultContent(
                checkRequired("callId", callId),
                checkRequired("result", result).map { it.toImmutable() },
                type,
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
        result().forEach { it.validate() }
        _type().let {
            if (it != JsonValue.from("file_search_result")) {
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
            type.let { if (it == JsonValue.from("file_search_result")) 1 else 0 } +
            (if (signature.asKnown().isPresent) 1 else 0)

    /** The result of the File Search. */
    class Result
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val customMetadata: JsonField<List<JsonValue>>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("custom_metadata")
            @ExcludeMissing
            customMetadata: JsonField<List<JsonValue>> = JsonMissing.of()
        ) : this(customMetadata, mutableMapOf())

        /**
         * User provided metadata about the FileSearchResult.
         *
         * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
         *   (e.g. if the server responded with an unexpected value).
         */
        fun customMetadata(): Optional<List<JsonValue>> =
            customMetadata.getOptional("custom_metadata")

        /**
         * Returns the raw JSON value of [customMetadata].
         *
         * Unlike [customMetadata], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("custom_metadata")
        @ExcludeMissing
        fun _customMetadata(): JsonField<List<JsonValue>> = customMetadata

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

            private var customMetadata: JsonField<MutableList<JsonValue>>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(result: Result) = apply {
                customMetadata = result.customMetadata.map { it.toMutableList() }
                additionalProperties = result.additionalProperties.toMutableMap()
            }

            /** User provided metadata about the FileSearchResult. */
            fun customMetadata(customMetadata: List<JsonValue>) =
                customMetadata(JsonField.of(customMetadata))

            /**
             * Sets [Builder.customMetadata] to an arbitrary JSON value.
             *
             * You should usually call [Builder.customMetadata] with a well-typed `List<JsonValue>`
             * value instead. This method is primarily for setting the field to an undocumented or
             * not yet supported value.
             */
            fun customMetadata(customMetadata: JsonField<List<JsonValue>>) = apply {
                this.customMetadata = customMetadata.map { it.toMutableList() }
            }

            /**
             * Adds a single [JsonValue] to [Builder.customMetadata].
             *
             * @throws IllegalStateException if the field was previously set to a non-list.
             */
            fun addCustomMetadata(customMetadata: JsonValue) = apply {
                this.customMetadata =
                    (this.customMetadata ?: JsonField.of(mutableListOf())).also {
                        checkKnown("customMetadata", it).add(customMetadata)
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
             * Returns an immutable instance of [Result].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): Result =
                Result(
                    (customMetadata ?: JsonMissing.of()).map { it.toImmutable() },
                    additionalProperties.toMutableMap(),
                )
        }

        private var validated: Boolean = false

        fun validate(): Result = apply {
            if (validated) {
                return@apply
            }

            customMetadata()
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
        internal fun validity(): Int = (customMetadata.asKnown().getOrNull()?.size ?: 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Result &&
                customMetadata == other.customMetadata &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(customMetadata, additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Result{customMetadata=$customMetadata, additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is FileSearchResultContent &&
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
        "FileSearchResultContent{callId=$callId, result=$result, type=$type, signature=$signature, additionalProperties=$additionalProperties}"
}
