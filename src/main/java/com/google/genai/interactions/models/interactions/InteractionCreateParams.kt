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

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.core.type.TypeReference
import com.google.genai.interactions.core.BaseDeserializer
import com.google.genai.interactions.core.BaseSerializer
import com.google.genai.interactions.core.JsonValue
import com.google.genai.interactions.core.Params
import com.google.genai.interactions.core.allMaxBy
import com.google.genai.interactions.core.checkRequired
import com.google.genai.interactions.core.getOrThrow
import com.google.genai.interactions.core.http.Headers
import com.google.genai.interactions.core.http.QueryParams
import com.google.genai.interactions.errors.GeminiNextGenApiInvalidDataException
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** Creates a new interaction. */
class InteractionCreateParams
private constructor(
    private val apiVersion: String?,
    private val body: Body,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    fun apiVersion(): Optional<String> = Optional.ofNullable(apiVersion)

    /** Parameters for creating model interactions */
    fun body(): Body = body

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [InteractionCreateParams].
         *
         * The following fields are required:
         * ```java
         * .body()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [InteractionCreateParams]. */
    class Builder internal constructor() {

        private var apiVersion: String? = null
        private var body: Body? = null
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(interactionCreateParams: InteractionCreateParams) = apply {
            apiVersion = interactionCreateParams.apiVersion
            body = interactionCreateParams.body
            additionalHeaders = interactionCreateParams.additionalHeaders.toBuilder()
            additionalQueryParams = interactionCreateParams.additionalQueryParams.toBuilder()
        }

        fun apiVersion(apiVersion: String?) = apply { this.apiVersion = apiVersion }

        /** Alias for calling [Builder.apiVersion] with `apiVersion.orElse(null)`. */
        fun apiVersion(apiVersion: Optional<String>) = apiVersion(apiVersion.getOrNull())

        /** Parameters for creating model interactions */
        fun body(body: Body) = apply { this.body = body }

        /**
         * Alias for calling [body] with
         * `Body.ofCreateModelInteractionParams(createModelInteractionParams)`.
         */
        fun body(createModelInteractionParams: CreateModelInteractionParams) =
            body(Body.ofCreateModelInteractionParams(createModelInteractionParams))

        /**
         * Alias for calling [body] with
         * `Body.ofCreateAgentInteractionParams(createAgentInteractionParams)`.
         */
        fun body(createAgentInteractionParams: CreateAgentInteractionParams) =
            body(Body.ofCreateAgentInteractionParams(createAgentInteractionParams))

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
         * Returns an immutable instance of [InteractionCreateParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .body()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): InteractionCreateParams =
            InteractionCreateParams(
                apiVersion,
                checkRequired("body", body),
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    fun _body(): Body = body

    fun _pathParam(index: Int): String =
        when (index) {
            0 -> apiVersion ?: ""
            else -> ""
        }

    override fun _headers(): Headers = additionalHeaders

    override fun _queryParams(): QueryParams = additionalQueryParams

    /** Parameters for creating model interactions */
    @JsonDeserialize(using = Body.Deserializer::class)
    @JsonSerialize(using = Body.Serializer::class)
    class Body
    private constructor(
        private val createModelInteractionParams: CreateModelInteractionParams? = null,
        private val createAgentInteractionParams: CreateAgentInteractionParams? = null,
        private val _json: JsonValue? = null,
    ) {

        /** Parameters for creating model interactions */
        fun createModelInteractionParams(): Optional<CreateModelInteractionParams> =
            Optional.ofNullable(createModelInteractionParams)

        /** Parameters for creating agent interactions */
        fun createAgentInteractionParams(): Optional<CreateAgentInteractionParams> =
            Optional.ofNullable(createAgentInteractionParams)

        fun isCreateModelInteractionParams(): Boolean = createModelInteractionParams != null

        fun isCreateAgentInteractionParams(): Boolean = createAgentInteractionParams != null

        /** Parameters for creating model interactions */
        fun asCreateModelInteractionParams(): CreateModelInteractionParams =
            createModelInteractionParams.getOrThrow("createModelInteractionParams")

        /** Parameters for creating agent interactions */
        fun asCreateAgentInteractionParams(): CreateAgentInteractionParams =
            createAgentInteractionParams.getOrThrow("createAgentInteractionParams")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        fun <T> accept(visitor: Visitor<T>): T =
            when {
                createModelInteractionParams != null ->
                    visitor.visitCreateModelInteractionParams(createModelInteractionParams)
                createAgentInteractionParams != null ->
                    visitor.visitCreateAgentInteractionParams(createAgentInteractionParams)
                else -> visitor.unknown(_json)
            }

        private var validated: Boolean = false

        fun validate(): Body = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitCreateModelInteractionParams(
                        createModelInteractionParams: CreateModelInteractionParams
                    ) {
                        createModelInteractionParams.validate()
                    }

                    override fun visitCreateAgentInteractionParams(
                        createAgentInteractionParams: CreateAgentInteractionParams
                    ) {
                        createAgentInteractionParams.validate()
                    }
                }
            )
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
            accept(
                object : Visitor<Int> {
                    override fun visitCreateModelInteractionParams(
                        createModelInteractionParams: CreateModelInteractionParams
                    ) = createModelInteractionParams.validity()

                    override fun visitCreateAgentInteractionParams(
                        createAgentInteractionParams: CreateAgentInteractionParams
                    ) = createAgentInteractionParams.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Body &&
                createModelInteractionParams == other.createModelInteractionParams &&
                createAgentInteractionParams == other.createAgentInteractionParams
        }

        override fun hashCode(): Int =
            Objects.hash(createModelInteractionParams, createAgentInteractionParams)

        override fun toString(): String =
            when {
                createModelInteractionParams != null ->
                    "Body{createModelInteractionParams=$createModelInteractionParams}"
                createAgentInteractionParams != null ->
                    "Body{createAgentInteractionParams=$createAgentInteractionParams}"
                _json != null -> "Body{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Body")
            }

        companion object {

            /** Parameters for creating model interactions */
            @JvmStatic
            fun ofCreateModelInteractionParams(
                createModelInteractionParams: CreateModelInteractionParams
            ) = Body(createModelInteractionParams = createModelInteractionParams)

            /** Parameters for creating agent interactions */
            @JvmStatic
            fun ofCreateAgentInteractionParams(
                createAgentInteractionParams: CreateAgentInteractionParams
            ) = Body(createAgentInteractionParams = createAgentInteractionParams)
        }

        /** An interface that defines how to map each variant of [Body] to a value of type [T]. */
        interface Visitor<out T> {

            /** Parameters for creating model interactions */
            fun visitCreateModelInteractionParams(
                createModelInteractionParams: CreateModelInteractionParams
            ): T

            /** Parameters for creating agent interactions */
            fun visitCreateAgentInteractionParams(
                createAgentInteractionParams: CreateAgentInteractionParams
            ): T

            /**
             * Maps an unknown variant of [Body] to a value of type [T].
             *
             * An instance of [Body] can contain an unknown variant if it was deserialized from data
             * that doesn't match any known variant. For example, if the SDK is on an older version
             * than the API, then the API may respond with new variants that the SDK is unaware of.
             *
             * @throws GeminiNextGenApiInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw GeminiNextGenApiInvalidDataException("Unknown Body: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<Body>(Body::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Body {
                val json = JsonValue.fromJsonNode(node)

                val bestMatches =
                    sequenceOf(
                            tryDeserialize(node, object : TypeReference<CreateModelInteractionParams>() {})
                                ?.let { Body(createModelInteractionParams = it, _json = json) },
                            tryDeserialize(node, object : TypeReference<CreateAgentInteractionParams>() {})
                                ?.let { Body(createAgentInteractionParams = it, _json = json) },
                        )
                        .filterNotNull()
                        .allMaxBy { it.validity() }
                        .toList()
                return when (bestMatches.size) {
                    // This can happen if what we're deserializing is completely incompatible with
                    // all the possible variants (e.g. deserializing from boolean).
                    0 -> Body(_json = json)
                    1 -> bestMatches.single()
                    // If there's more than one match with the highest validity, then use the first
                    // completely valid match, or simply the first match if none are completely
                    // valid.
                    else -> bestMatches.firstOrNull { it.isValid() } ?: bestMatches.first()
                }
            }
        }

        internal class Serializer : BaseSerializer<Body>(Body::class) {

            override fun serialize(
                value: Body,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.createModelInteractionParams != null ->
                        generator.writeObject(value.createModelInteractionParams)
                    value.createAgentInteractionParams != null ->
                        generator.writeObject(value.createAgentInteractionParams)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Body")
                }
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is InteractionCreateParams &&
            apiVersion == other.apiVersion &&
            body == other.body &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(apiVersion, body, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "InteractionCreateParams{apiVersion=$apiVersion, body=$body, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
