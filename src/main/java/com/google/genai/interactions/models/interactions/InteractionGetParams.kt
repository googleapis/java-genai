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

import com.google.genai.interactions.core.Params
import com.google.genai.interactions.core.http.Headers
import com.google.genai.interactions.core.http.QueryParams
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** Retrieves the full details of a single interaction based on its `Interaction.id`. */
class InteractionGetParams
private constructor(
    private val apiVersion: String?,
    private val id: String?,
    private val includeInput: Boolean?,
    private val lastEventId: String?,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    fun apiVersion(): Optional<String> = Optional.ofNullable(apiVersion)

    fun id(): Optional<String> = Optional.ofNullable(id)

    /** If set to true, includes the input in the response. */
    fun includeInput(): Optional<Boolean> = Optional.ofNullable(includeInput)

    /**
     * Optional. If set, resumes the interaction stream from the next chunk after the event marked
     * by the event id. Can only be used if `stream` is true.
     */
    fun lastEventId(): Optional<String> = Optional.ofNullable(lastEventId)

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun none(): InteractionGetParams = builder().build()

        /** Returns a mutable builder for constructing an instance of [InteractionGetParams]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [InteractionGetParams]. */
    class Builder internal constructor() {

        private var apiVersion: String? = null
        private var id: String? = null
        private var includeInput: Boolean? = null
        private var lastEventId: String? = null
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(interactionGetParams: InteractionGetParams) = apply {
            apiVersion = interactionGetParams.apiVersion
            id = interactionGetParams.id
            includeInput = interactionGetParams.includeInput
            lastEventId = interactionGetParams.lastEventId
            additionalHeaders = interactionGetParams.additionalHeaders.toBuilder()
            additionalQueryParams = interactionGetParams.additionalQueryParams.toBuilder()
        }

        fun apiVersion(apiVersion: String?) = apply { this.apiVersion = apiVersion }

        /** Alias for calling [Builder.apiVersion] with `apiVersion.orElse(null)`. */
        fun apiVersion(apiVersion: Optional<String>) = apiVersion(apiVersion.getOrNull())

        fun id(id: String?) = apply { this.id = id }

        /** Alias for calling [Builder.id] with `id.orElse(null)`. */
        fun id(id: Optional<String>) = id(id.getOrNull())

        /** If set to true, includes the input in the response. */
        fun includeInput(includeInput: Boolean?) = apply { this.includeInput = includeInput }

        /**
         * Alias for [Builder.includeInput].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun includeInput(includeInput: Boolean) = includeInput(includeInput as Boolean?)

        /** Alias for calling [Builder.includeInput] with `includeInput.orElse(null)`. */
        fun includeInput(includeInput: Optional<Boolean>) = includeInput(includeInput.getOrNull())

        /**
         * Optional. If set, resumes the interaction stream from the next chunk after the event
         * marked by the event id. Can only be used if `stream` is true.
         */
        fun lastEventId(lastEventId: String?) = apply { this.lastEventId = lastEventId }

        /** Alias for calling [Builder.lastEventId] with `lastEventId.orElse(null)`. */
        fun lastEventId(lastEventId: Optional<String>) = lastEventId(lastEventId.getOrNull())

        fun additionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.clear()
            putAllAdditionalHeaders(additionalHeaders)
        }

        fun additionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.clear()
            putAllAdditionalHeaders(additionalHeaders)
        }

        fun putAdditionalHeader(name: String, value: String) = apply {
            additionalHeaders.put(name, value)
        }

        fun putAdditionalHeaders(name: String, values: Iterable<String>) = apply {
            additionalHeaders.put(name, values)
        }

        fun putAllAdditionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.putAll(additionalHeaders)
        }

        fun putAllAdditionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.putAll(additionalHeaders)
        }

        fun replaceAdditionalHeaders(name: String, value: String) = apply {
            additionalHeaders.replace(name, value)
        }

        fun replaceAdditionalHeaders(name: String, values: Iterable<String>) = apply {
            additionalHeaders.replace(name, values)
        }

        fun replaceAllAdditionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.replaceAll(additionalHeaders)
        }

        fun replaceAllAdditionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.replaceAll(additionalHeaders)
        }

        fun removeAdditionalHeaders(name: String) = apply { additionalHeaders.remove(name) }

        fun removeAllAdditionalHeaders(names: Set<String>) = apply {
            additionalHeaders.removeAll(names)
        }

        fun additionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.clear()
            putAllAdditionalQueryParams(additionalQueryParams)
        }

        fun additionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) = apply {
            this.additionalQueryParams.clear()
            putAllAdditionalQueryParams(additionalQueryParams)
        }

        fun putAdditionalQueryParam(key: String, value: String) = apply {
            additionalQueryParams.put(key, value)
        }

        fun putAdditionalQueryParams(key: String, values: Iterable<String>) = apply {
            additionalQueryParams.put(key, values)
        }

        fun putAllAdditionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.putAll(additionalQueryParams)
        }

        fun putAllAdditionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) =
            apply {
                this.additionalQueryParams.putAll(additionalQueryParams)
            }

        fun replaceAdditionalQueryParams(key: String, value: String) = apply {
            additionalQueryParams.replace(key, value)
        }

        fun replaceAdditionalQueryParams(key: String, values: Iterable<String>) = apply {
            additionalQueryParams.replace(key, values)
        }

        fun replaceAllAdditionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.replaceAll(additionalQueryParams)
        }

        fun replaceAllAdditionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) =
            apply {
                this.additionalQueryParams.replaceAll(additionalQueryParams)
            }

        fun removeAdditionalQueryParams(key: String) = apply { additionalQueryParams.remove(key) }

        fun removeAllAdditionalQueryParams(keys: Set<String>) = apply {
            additionalQueryParams.removeAll(keys)
        }

        /**
         * Returns an immutable instance of [InteractionGetParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): InteractionGetParams =
            InteractionGetParams(
                apiVersion,
                id,
                includeInput,
                lastEventId,
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    fun _pathParam(index: Int): String =
        when (index) {
            0 -> apiVersion ?: ""
            1 -> id ?: ""
            else -> ""
        }

    override fun _headers(): Headers = additionalHeaders

    override fun _queryParams(): QueryParams =
        QueryParams.builder()
            .apply {
                includeInput?.let { put("include_input", it.toString()) }
                lastEventId?.let { put("last_event_id", it) }
                putAll(additionalQueryParams)
            }
            .build()

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is InteractionGetParams &&
            apiVersion == other.apiVersion &&
            id == other.id &&
            includeInput == other.includeInput &&
            lastEventId == other.lastEventId &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(
            apiVersion,
            id,
            includeInput,
            lastEventId,
            additionalHeaders,
            additionalQueryParams,
        )

    override fun toString() =
        "InteractionGetParams{apiVersion=$apiVersion, id=$id, includeInput=$includeInput, lastEventId=$lastEventId, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
