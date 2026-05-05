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
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.google.genai.interactions.core.BaseDeserializer
import com.google.genai.interactions.core.BaseSerializer
import com.google.genai.interactions.core.ExcludeMissing
import com.google.genai.interactions.core.JsonField
import com.google.genai.interactions.core.JsonMissing
import com.google.genai.interactions.core.JsonValue
import com.google.genai.interactions.core.allMaxBy
import com.google.genai.interactions.core.checkRequired
import com.google.genai.interactions.core.getOrThrow
import com.google.genai.interactions.core.toImmutable
import com.google.genai.interactions.errors.GeminiNextGenApiInvalidDataException
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** MCPServer tool result step. */
class McpServerToolResultStep
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val callId: JsonField<String>,
    private val result: JsonField<Result>,
    private val type: JsonValue,
    private val name: JsonField<String>,
    private val serverName: JsonField<String>,
    private val signature: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("call_id") @ExcludeMissing callId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("result") @ExcludeMissing result: JsonField<Result> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("name") @ExcludeMissing name: JsonField<String> = JsonMissing.of(),
        @JsonProperty("server_name")
        @ExcludeMissing
        serverName: JsonField<String> = JsonMissing.of(),
        @JsonProperty("signature") @ExcludeMissing signature: JsonField<String> = JsonMissing.of(),
    ) : this(callId, result, type, name, serverName, signature, mutableMapOf())

    /**
     * Required. ID to match the ID from the function call block.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun callId(): String = callId.getRequired("call_id")

    /**
     * The output from the MCP server call. Can be simple text or rich content.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun result(): Result = result.getRequired("result")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("mcp_server_tool_result")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Name of the tool which is called for this specific tool call.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun name(): Optional<String> = name.getOptional("name")

    /**
     * The name of the used MCP server.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun serverName(): Optional<String> = serverName.getOptional("server_name")

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
    @JsonProperty("result") @ExcludeMissing fun _result(): JsonField<Result> = result

    /**
     * Returns the raw JSON value of [name].
     *
     * Unlike [name], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("name") @ExcludeMissing fun _name(): JsonField<String> = name

    /**
     * Returns the raw JSON value of [serverName].
     *
     * Unlike [serverName], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("server_name") @ExcludeMissing fun _serverName(): JsonField<String> = serverName

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
         * Returns a mutable builder for constructing an instance of [McpServerToolResultStep].
         *
         * The following fields are required:
         * ```java
         * .callId()
         * .result()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [McpServerToolResultStep]. */
    class Builder internal constructor() {

        private var callId: JsonField<String>? = null
        private var result: JsonField<Result>? = null
        private var type: JsonValue = JsonValue.from("mcp_server_tool_result")
        private var name: JsonField<String> = JsonMissing.of()
        private var serverName: JsonField<String> = JsonMissing.of()
        private var signature: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(mcpServerToolResultStep: McpServerToolResultStep) = apply {
            callId = mcpServerToolResultStep.callId
            result = mcpServerToolResultStep.result
            type = mcpServerToolResultStep.type
            name = mcpServerToolResultStep.name
            serverName = mcpServerToolResultStep.serverName
            signature = mcpServerToolResultStep.signature
            additionalProperties = mcpServerToolResultStep.additionalProperties.toMutableMap()
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

        /** The output from the MCP server call. Can be simple text or rich content. */
        fun result(result: Result) = result(JsonField.of(result))

        /**
         * Sets [Builder.result] to an arbitrary JSON value.
         *
         * You should usually call [Builder.result] with a well-typed [Result] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun result(result: JsonField<Result>) = apply { this.result = result }

        /** Alias for calling [result] with `Result.ofJsonValue(jsonValue)`. */
        fun result(jsonValue: JsonValue) = result(Result.ofJsonValue(jsonValue))

        /** Alias for calling [result] with `Result.ofString(string)`. */
        fun result(string: String) = result(Result.ofString(string))

        /**
         * Alias for calling [result] with
         * `Result.ofFunctionResultSubcontentList(functionResultSubcontentList)`.
         */
        fun resultOfFunctionResultSubcontentList(
            functionResultSubcontentList: List<Result.FunctionResultSubcontent>
        ) = result(Result.ofFunctionResultSubcontentList(functionResultSubcontentList))

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("mcp_server_tool_result")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /** Name of the tool which is called for this specific tool call. */
        fun name(name: String) = name(JsonField.of(name))

        /**
         * Sets [Builder.name] to an arbitrary JSON value.
         *
         * You should usually call [Builder.name] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun name(name: JsonField<String>) = apply { this.name = name }

        /** The name of the used MCP server. */
        fun serverName(serverName: String) = serverName(JsonField.of(serverName))

        /**
         * Sets [Builder.serverName] to an arbitrary JSON value.
         *
         * You should usually call [Builder.serverName] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun serverName(serverName: JsonField<String>) = apply { this.serverName = serverName }

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
         * Returns an immutable instance of [McpServerToolResultStep].
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
        fun build(): McpServerToolResultStep =
            McpServerToolResultStep(
                checkRequired("callId", callId),
                checkRequired("result", result),
                type,
                name,
                serverName,
                signature,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): McpServerToolResultStep = apply {
        if (validated) {
            return@apply
        }

        callId()
        result().validate()
        _type().let {
            if (it != JsonValue.from("mcp_server_tool_result")) {
                throw GeminiNextGenApiInvalidDataException("'type' is invalid, received $it")
            }
        }
        name()
        serverName()
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
            (result.asKnown().getOrNull()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("mcp_server_tool_result")) 1 else 0 } +
            (if (name.asKnown().isPresent) 1 else 0) +
            (if (serverName.asKnown().isPresent) 1 else 0) +
            (if (signature.asKnown().isPresent) 1 else 0)

    /** The output from the MCP server call. Can be simple text or rich content. */
    @JsonDeserialize(using = Result.Deserializer::class)
    @JsonSerialize(using = Result.Serializer::class)
    class Result
    private constructor(
        private val jsonValue: JsonValue? = null,
        private val string: String? = null,
        private val functionResultSubcontentList: List<FunctionResultSubcontent>? = null,
        private val _json: JsonValue? = null,
    ) {

        fun jsonValue(): Optional<JsonValue> = Optional.ofNullable(jsonValue)

        fun string(): Optional<String> = Optional.ofNullable(string)

        fun functionResultSubcontentList(): Optional<List<FunctionResultSubcontent>> =
            Optional.ofNullable(functionResultSubcontentList)

        fun isJsonValue(): Boolean = jsonValue != null

        fun isString(): Boolean = string != null

        fun isFunctionResultSubcontentList(): Boolean = functionResultSubcontentList != null

        fun asJsonValue(): JsonValue = jsonValue.getOrThrow("jsonValue")

        fun asString(): String = string.getOrThrow("string")

        fun asFunctionResultSubcontentList(): List<FunctionResultSubcontent> =
            functionResultSubcontentList.getOrThrow("functionResultSubcontentList")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        fun <T> accept(visitor: Visitor<T>): T =
            when {
                jsonValue != null -> visitor.visitJsonValue(jsonValue)
                string != null -> visitor.visitString(string)
                functionResultSubcontentList != null ->
                    visitor.visitFunctionResultSubcontentList(functionResultSubcontentList)
                else -> visitor.unknown(_json)
            }

        private var validated: Boolean = false

        fun validate(): Result = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitJsonValue(jsonValue: JsonValue) {}

                    override fun visitString(string: String) {}

                    override fun visitFunctionResultSubcontentList(
                        functionResultSubcontentList: List<FunctionResultSubcontent>
                    ) {
                        functionResultSubcontentList.forEach { it.validate() }
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
                    override fun visitJsonValue(jsonValue: JsonValue) = 1

                    override fun visitString(string: String) = 1

                    override fun visitFunctionResultSubcontentList(
                        functionResultSubcontentList: List<FunctionResultSubcontent>
                    ) = functionResultSubcontentList.sumOf { it.validity().toInt() }

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Result &&
                jsonValue == other.jsonValue &&
                string == other.string &&
                functionResultSubcontentList == other.functionResultSubcontentList
        }

        override fun hashCode(): Int = Objects.hash(jsonValue, string, functionResultSubcontentList)

        override fun toString(): String =
            when {
                jsonValue != null -> "Result{jsonValue=$jsonValue}"
                string != null -> "Result{string=$string}"
                functionResultSubcontentList != null ->
                    "Result{functionResultSubcontentList=$functionResultSubcontentList}"
                _json != null -> "Result{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Result")
            }

        companion object {

            @JvmStatic fun ofJsonValue(jsonValue: JsonValue) = Result(jsonValue = jsonValue)

            @JvmStatic fun ofString(string: String) = Result(string = string)

            @JvmStatic
            fun ofFunctionResultSubcontentList(
                functionResultSubcontentList: List<FunctionResultSubcontent>
            ) = Result(functionResultSubcontentList = functionResultSubcontentList.toImmutable())
        }

        /** An interface that defines how to map each variant of [Result] to a value of type [T]. */
        interface Visitor<out T> {

            fun visitJsonValue(jsonValue: JsonValue): T

            fun visitString(string: String): T

            fun visitFunctionResultSubcontentList(
                functionResultSubcontentList: List<FunctionResultSubcontent>
            ): T

            /**
             * Maps an unknown variant of [Result] to a value of type [T].
             *
             * An instance of [Result] can contain an unknown variant if it was deserialized from
             * data that doesn't match any known variant. For example, if the SDK is on an older
             * version than the API, then the API may respond with new variants that the SDK is
             * unaware of.
             *
             * @throws GeminiNextGenApiInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw GeminiNextGenApiInvalidDataException("Unknown Result: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<Result>(Result::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Result {
                val json = JsonValue.fromJsonNode(node)

                val bestMatches =
                    sequenceOf(
                            tryDeserialize(node, jacksonTypeRef<String>())?.let {
                                Result(string = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<List<FunctionResultSubcontent>>())
                                ?.let { Result(functionResultSubcontentList = it, _json = json) },
                            tryDeserialize(node, jacksonTypeRef<JsonValue>())?.let {
                                Result(jsonValue = it, _json = json)
                            },
                        )
                        .filterNotNull()
                        .allMaxBy { it.validity() }
                        .toList()
                return when (bestMatches.size) {
                    // This can happen if what we're deserializing is completely incompatible with
                    // all the possible variants.
                    0 -> Result(_json = json)
                    1 -> bestMatches.single()
                    // If there's more than one match with the highest validity, then use the first
                    // completely valid match, or simply the first match if none are completely
                    // valid.
                    else -> bestMatches.firstOrNull { it.isValid() } ?: bestMatches.first()
                }
            }
        }

        internal class Serializer : BaseSerializer<Result>(Result::class) {

            override fun serialize(
                value: Result,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.jsonValue != null -> generator.writeObject(value.jsonValue)
                    value.string != null -> generator.writeObject(value.string)
                    value.functionResultSubcontentList != null ->
                        generator.writeObject(value.functionResultSubcontentList)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Result")
                }
            }
        }

        /** A text content block. */
        @JsonDeserialize(using = FunctionResultSubcontent.Deserializer::class)
        @JsonSerialize(using = FunctionResultSubcontent.Serializer::class)
        class FunctionResultSubcontent
        private constructor(
            private val text: TextContent? = null,
            private val image: ImageContent? = null,
            private val _json: JsonValue? = null,
        ) {

            /** A text content block. */
            fun text(): Optional<TextContent> = Optional.ofNullable(text)

            /** An image content block. */
            fun image(): Optional<ImageContent> = Optional.ofNullable(image)

            fun isText(): Boolean = text != null

            fun isImage(): Boolean = image != null

            /** A text content block. */
            fun asText(): TextContent = text.getOrThrow("text")

            /** An image content block. */
            fun asImage(): ImageContent = image.getOrThrow("image")

            fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

            fun <T> accept(visitor: Visitor<T>): T =
                when {
                    text != null -> visitor.visitText(text)
                    image != null -> visitor.visitImage(image)
                    else -> visitor.unknown(_json)
                }

            private var validated: Boolean = false

            fun validate(): FunctionResultSubcontent = apply {
                if (validated) {
                    return@apply
                }

                accept(
                    object : Visitor<Unit> {
                        override fun visitText(text: TextContent) {
                            text.validate()
                        }

                        override fun visitImage(image: ImageContent) {
                            image.validate()
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
                        override fun visitText(text: TextContent) = text.validity()

                        override fun visitImage(image: ImageContent) = image.validity()

                        override fun unknown(json: JsonValue?) = 0
                    }
                )

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is FunctionResultSubcontent &&
                    text == other.text &&
                    image == other.image
            }

            override fun hashCode(): Int = Objects.hash(text, image)

            override fun toString(): String =
                when {
                    text != null -> "FunctionResultSubcontent{text=$text}"
                    image != null -> "FunctionResultSubcontent{image=$image}"
                    _json != null -> "FunctionResultSubcontent{_unknown=$_json}"
                    else -> throw IllegalStateException("Invalid FunctionResultSubcontent")
                }

            companion object {

                /** A text content block. */
                @JvmStatic fun ofText(text: TextContent) = FunctionResultSubcontent(text = text)

                /** An image content block. */
                @JvmStatic
                fun ofImage(image: ImageContent) = FunctionResultSubcontent(image = image)
            }

            /**
             * An interface that defines how to map each variant of [FunctionResultSubcontent] to a
             * value of type [T].
             */
            interface Visitor<out T> {

                /** A text content block. */
                fun visitText(text: TextContent): T

                /** An image content block. */
                fun visitImage(image: ImageContent): T

                /**
                 * Maps an unknown variant of [FunctionResultSubcontent] to a value of type [T].
                 *
                 * An instance of [FunctionResultSubcontent] can contain an unknown variant if it
                 * was deserialized from data that doesn't match any known variant. For example, if
                 * the SDK is on an older version than the API, then the API may respond with new
                 * variants that the SDK is unaware of.
                 *
                 * @throws GeminiNextGenApiInvalidDataException in the default implementation.
                 */
                fun unknown(json: JsonValue?): T {
                    throw GeminiNextGenApiInvalidDataException(
                        "Unknown FunctionResultSubcontent: $json"
                    )
                }
            }

            internal class Deserializer :
                BaseDeserializer<FunctionResultSubcontent>(FunctionResultSubcontent::class) {

                override fun ObjectCodec.deserialize(node: JsonNode): FunctionResultSubcontent {
                    val json = JsonValue.fromJsonNode(node)
                    val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                    when (type) {
                        "text" -> {
                            return tryDeserialize(node, jacksonTypeRef<TextContent>())?.let {
                                FunctionResultSubcontent(text = it, _json = json)
                            } ?: FunctionResultSubcontent(_json = json)
                        }
                        "image" -> {
                            return tryDeserialize(node, jacksonTypeRef<ImageContent>())?.let {
                                FunctionResultSubcontent(image = it, _json = json)
                            } ?: FunctionResultSubcontent(_json = json)
                        }
                    }

                    return FunctionResultSubcontent(_json = json)
                }
            }

            internal class Serializer :
                BaseSerializer<FunctionResultSubcontent>(FunctionResultSubcontent::class) {

                override fun serialize(
                    value: FunctionResultSubcontent,
                    generator: JsonGenerator,
                    provider: SerializerProvider,
                ) {
                    when {
                        value.text != null -> generator.writeObject(value.text)
                        value.image != null -> generator.writeObject(value.image)
                        value._json != null -> generator.writeObject(value._json)
                        else -> throw IllegalStateException("Invalid FunctionResultSubcontent")
                    }
                }
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is McpServerToolResultStep &&
            callId == other.callId &&
            result == other.result &&
            type == other.type &&
            name == other.name &&
            serverName == other.serverName &&
            signature == other.signature &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(callId, result, type, name, serverName, signature, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "McpServerToolResultStep{callId=$callId, result=$result, type=$type, name=$name, serverName=$serverName, signature=$signature, additionalProperties=$additionalProperties}"
}
