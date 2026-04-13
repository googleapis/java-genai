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
import com.google.genai.interactions.core.Enum
import com.google.genai.interactions.core.ExcludeMissing
import com.google.genai.interactions.core.JsonField
import com.google.genai.interactions.core.JsonMissing
import com.google.genai.interactions.core.JsonValue
import com.google.genai.interactions.core.checkKnown
import com.google.genai.interactions.core.getOrThrow
import com.google.genai.interactions.core.toImmutable
import com.google.genai.interactions.errors.GeminiNextGenApiInvalidDataException
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** A tool that can be used by the model. */
@JsonDeserialize(using = Tool.Deserializer::class)
@JsonSerialize(using = Tool.Serializer::class)
class Tool
private constructor(
    private val function: Function? = null,
    private val codeExecution: JsonValue? = null,
    private val urlContext: JsonValue? = null,
    private val computerUse: ComputerUse? = null,
    private val mcpServer: McpServer? = null,
    private val googleSearch: GoogleSearch? = null,
    private val fileSearch: FileSearch? = null,
    private val googleMaps: GoogleMaps? = null,
    private val retrieval: Retrieval? = null,
    private val _json: JsonValue? = null,
) {

    /** A tool that can be used by the model. */
    fun function(): Optional<Function> = Optional.ofNullable(function)

    /** A tool that can be used by the model to execute code. */
    fun codeExecution(): Optional<JsonValue> = Optional.ofNullable(codeExecution)

    /** A tool that can be used by the model to fetch URL context. */
    fun urlContext(): Optional<JsonValue> = Optional.ofNullable(urlContext)

    /** A tool that can be used by the model to interact with the computer. */
    fun computerUse(): Optional<ComputerUse> = Optional.ofNullable(computerUse)

    /** A MCPServer is a server that can be called by the model to perform actions. */
    fun mcpServer(): Optional<McpServer> = Optional.ofNullable(mcpServer)

    /** A tool that can be used by the model to search Google. */
    fun googleSearch(): Optional<GoogleSearch> = Optional.ofNullable(googleSearch)

    /** A tool that can be used by the model to search files. */
    fun fileSearch(): Optional<FileSearch> = Optional.ofNullable(fileSearch)

    /** A tool that can be used by the model to call Google Maps. */
    fun googleMaps(): Optional<GoogleMaps> = Optional.ofNullable(googleMaps)

    /** A tool that can be used by the model to retrieve files. */
    fun retrieval(): Optional<Retrieval> = Optional.ofNullable(retrieval)

    fun isFunction(): Boolean = function != null

    fun isCodeExecution(): Boolean = codeExecution != null

    fun isUrlContext(): Boolean = urlContext != null

    fun isComputerUse(): Boolean = computerUse != null

    fun isMcpServer(): Boolean = mcpServer != null

    fun isGoogleSearch(): Boolean = googleSearch != null

    fun isFileSearch(): Boolean = fileSearch != null

    fun isGoogleMaps(): Boolean = googleMaps != null

    fun isRetrieval(): Boolean = retrieval != null

    /** A tool that can be used by the model. */
    fun asFunction(): Function = function.getOrThrow("function")

    /** A tool that can be used by the model to execute code. */
    fun asCodeExecution(): JsonValue = codeExecution.getOrThrow("codeExecution")

    /** A tool that can be used by the model to fetch URL context. */
    fun asUrlContext(): JsonValue = urlContext.getOrThrow("urlContext")

    /** A tool that can be used by the model to interact with the computer. */
    fun asComputerUse(): ComputerUse = computerUse.getOrThrow("computerUse")

    /** A MCPServer is a server that can be called by the model to perform actions. */
    fun asMcpServer(): McpServer = mcpServer.getOrThrow("mcpServer")

    /** A tool that can be used by the model to search Google. */
    fun asGoogleSearch(): GoogleSearch = googleSearch.getOrThrow("googleSearch")

    /** A tool that can be used by the model to search files. */
    fun asFileSearch(): FileSearch = fileSearch.getOrThrow("fileSearch")

    /** A tool that can be used by the model to call Google Maps. */
    fun asGoogleMaps(): GoogleMaps = googleMaps.getOrThrow("googleMaps")

    /** A tool that can be used by the model to retrieve files. */
    fun asRetrieval(): Retrieval = retrieval.getOrThrow("retrieval")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T =
        when {
            function != null -> visitor.visitFunction(function)
            codeExecution != null -> visitor.visitCodeExecution(codeExecution)
            urlContext != null -> visitor.visitUrlContext(urlContext)
            computerUse != null -> visitor.visitComputerUse(computerUse)
            mcpServer != null -> visitor.visitMcpServer(mcpServer)
            googleSearch != null -> visitor.visitGoogleSearch(googleSearch)
            fileSearch != null -> visitor.visitFileSearch(fileSearch)
            googleMaps != null -> visitor.visitGoogleMaps(googleMaps)
            retrieval != null -> visitor.visitRetrieval(retrieval)
            else -> visitor.unknown(_json)
        }

    private var validated: Boolean = false

    fun validate(): Tool = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitFunction(function: Function) {
                    function.validate()
                }

                override fun visitCodeExecution(codeExecution: JsonValue) {
                    codeExecution.let {
                        if (it != JsonValue.from(mapOf("type" to "code_execution"))) {
                            throw GeminiNextGenApiInvalidDataException(
                                "'codeExecution' is invalid, received $it"
                            )
                        }
                    }
                }

                override fun visitUrlContext(urlContext: JsonValue) {
                    urlContext.let {
                        if (it != JsonValue.from(mapOf("type" to "url_context"))) {
                            throw GeminiNextGenApiInvalidDataException(
                                "'urlContext' is invalid, received $it"
                            )
                        }
                    }
                }

                override fun visitComputerUse(computerUse: ComputerUse) {
                    computerUse.validate()
                }

                override fun visitMcpServer(mcpServer: McpServer) {
                    mcpServer.validate()
                }

                override fun visitGoogleSearch(googleSearch: GoogleSearch) {
                    googleSearch.validate()
                }

                override fun visitFileSearch(fileSearch: FileSearch) {
                    fileSearch.validate()
                }

                override fun visitGoogleMaps(googleMaps: GoogleMaps) {
                    googleMaps.validate()
                }

                override fun visitRetrieval(retrieval: Retrieval) {
                    retrieval.validate()
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
     * Returns a score indicating how many valid values are contained in this object recursively.
     *
     * Used for best match union deserialization.
     */
    @JvmSynthetic
    internal fun validity(): Int =
        accept(
            object : Visitor<Int> {
                override fun visitFunction(function: Function) = function.validity()

                override fun visitCodeExecution(codeExecution: JsonValue) =
                    codeExecution.let {
                        if (it == JsonValue.from(mapOf("type" to "code_execution"))) 1 else 0
                    }

                override fun visitUrlContext(urlContext: JsonValue) =
                    urlContext.let {
                        if (it == JsonValue.from(mapOf("type" to "url_context"))) 1 else 0
                    }

                override fun visitComputerUse(computerUse: ComputerUse) = computerUse.validity()

                override fun visitMcpServer(mcpServer: McpServer) = mcpServer.validity()

                override fun visitGoogleSearch(googleSearch: GoogleSearch) = googleSearch.validity()

                override fun visitFileSearch(fileSearch: FileSearch) = fileSearch.validity()

                override fun visitGoogleMaps(googleMaps: GoogleMaps) = googleMaps.validity()

                override fun visitRetrieval(retrieval: Retrieval) = retrieval.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is Tool &&
            function == other.function &&
            codeExecution == other.codeExecution &&
            urlContext == other.urlContext &&
            computerUse == other.computerUse &&
            mcpServer == other.mcpServer &&
            googleSearch == other.googleSearch &&
            fileSearch == other.fileSearch &&
            googleMaps == other.googleMaps &&
            retrieval == other.retrieval
    }

    override fun hashCode(): Int =
        Objects.hash(
            function,
            codeExecution,
            urlContext,
            computerUse,
            mcpServer,
            googleSearch,
            fileSearch,
            googleMaps,
            retrieval,
        )

    override fun toString(): String =
        when {
            function != null -> "Tool{function=$function}"
            codeExecution != null -> "Tool{codeExecution=$codeExecution}"
            urlContext != null -> "Tool{urlContext=$urlContext}"
            computerUse != null -> "Tool{computerUse=$computerUse}"
            mcpServer != null -> "Tool{mcpServer=$mcpServer}"
            googleSearch != null -> "Tool{googleSearch=$googleSearch}"
            fileSearch != null -> "Tool{fileSearch=$fileSearch}"
            googleMaps != null -> "Tool{googleMaps=$googleMaps}"
            retrieval != null -> "Tool{retrieval=$retrieval}"
            _json != null -> "Tool{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid Tool")
        }

    companion object {

        /** A tool that can be used by the model. */
        @JvmStatic fun ofFunction(function: Function) = Tool(function = function)

        /** A tool that can be used by the model to execute code. */
        @JvmStatic
        fun ofCodeExecution() =
            Tool(codeExecution = JsonValue.from(mapOf("type" to "code_execution")))

        /** A tool that can be used by the model to fetch URL context. */
        @JvmStatic
        fun ofUrlContext() = Tool(urlContext = JsonValue.from(mapOf("type" to "url_context")))

        /** A tool that can be used by the model to interact with the computer. */
        @JvmStatic fun ofComputerUse(computerUse: ComputerUse) = Tool(computerUse = computerUse)

        /** A MCPServer is a server that can be called by the model to perform actions. */
        @JvmStatic fun ofMcpServer(mcpServer: McpServer) = Tool(mcpServer = mcpServer)

        /** A tool that can be used by the model to search Google. */
        @JvmStatic
        fun ofGoogleSearch(googleSearch: GoogleSearch) = Tool(googleSearch = googleSearch)

        /** A tool that can be used by the model to search files. */
        @JvmStatic fun ofFileSearch(fileSearch: FileSearch) = Tool(fileSearch = fileSearch)

        /** A tool that can be used by the model to call Google Maps. */
        @JvmStatic fun ofGoogleMaps(googleMaps: GoogleMaps) = Tool(googleMaps = googleMaps)

        /** A tool that can be used by the model to retrieve files. */
        @JvmStatic fun ofRetrieval(retrieval: Retrieval) = Tool(retrieval = retrieval)
    }

    /** An interface that defines how to map each variant of [Tool] to a value of type [T]. */
    interface Visitor<out T> {

        /** A tool that can be used by the model. */
        fun visitFunction(function: Function): T

        /** A tool that can be used by the model to execute code. */
        fun visitCodeExecution(codeExecution: JsonValue): T

        /** A tool that can be used by the model to fetch URL context. */
        fun visitUrlContext(urlContext: JsonValue): T

        /** A tool that can be used by the model to interact with the computer. */
        fun visitComputerUse(computerUse: ComputerUse): T

        /** A MCPServer is a server that can be called by the model to perform actions. */
        fun visitMcpServer(mcpServer: McpServer): T

        /** A tool that can be used by the model to search Google. */
        fun visitGoogleSearch(googleSearch: GoogleSearch): T

        /** A tool that can be used by the model to search files. */
        fun visitFileSearch(fileSearch: FileSearch): T

        /** A tool that can be used by the model to call Google Maps. */
        fun visitGoogleMaps(googleMaps: GoogleMaps): T

        /** A tool that can be used by the model to retrieve files. */
        fun visitRetrieval(retrieval: Retrieval): T

        /**
         * Maps an unknown variant of [Tool] to a value of type [T].
         *
         * An instance of [Tool] can contain an unknown variant if it was deserialized from data
         * that doesn't match any known variant. For example, if the SDK is on an older version than
         * the API, then the API may respond with new variants that the SDK is unaware of.
         *
         * @throws GeminiNextGenApiInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw GeminiNextGenApiInvalidDataException("Unknown Tool: $json")
        }
    }

    internal class Deserializer : BaseDeserializer<Tool>(Tool::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): Tool {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "function" -> {
                    return tryDeserialize(node, jacksonTypeRef<Function>())?.let {
                        Tool(function = it, _json = json)
                    } ?: Tool(_json = json)
                }
                "code_execution" -> {
                    return tryDeserialize(node, jacksonTypeRef<JsonValue>())
                        ?.let { Tool(codeExecution = it, _json = json) }
                        ?.takeIf { it.isValid() } ?: Tool(_json = json)
                }
                "url_context" -> {
                    return tryDeserialize(node, jacksonTypeRef<JsonValue>())
                        ?.let { Tool(urlContext = it, _json = json) }
                        ?.takeIf { it.isValid() } ?: Tool(_json = json)
                }
                "computer_use" -> {
                    return tryDeserialize(node, jacksonTypeRef<ComputerUse>())?.let {
                        Tool(computerUse = it, _json = json)
                    } ?: Tool(_json = json)
                }
                "mcp_server" -> {
                    return tryDeserialize(node, jacksonTypeRef<McpServer>())?.let {
                        Tool(mcpServer = it, _json = json)
                    } ?: Tool(_json = json)
                }
                "google_search" -> {
                    return tryDeserialize(node, jacksonTypeRef<GoogleSearch>())?.let {
                        Tool(googleSearch = it, _json = json)
                    } ?: Tool(_json = json)
                }
                "file_search" -> {
                    return tryDeserialize(node, jacksonTypeRef<FileSearch>())?.let {
                        Tool(fileSearch = it, _json = json)
                    } ?: Tool(_json = json)
                }
                "google_maps" -> {
                    return tryDeserialize(node, jacksonTypeRef<GoogleMaps>())?.let {
                        Tool(googleMaps = it, _json = json)
                    } ?: Tool(_json = json)
                }
                "retrieval" -> {
                    return tryDeserialize(node, jacksonTypeRef<Retrieval>())?.let {
                        Tool(retrieval = it, _json = json)
                    } ?: Tool(_json = json)
                }
            }

            return Tool(_json = json)
        }
    }

    internal class Serializer : BaseSerializer<Tool>(Tool::class) {

        override fun serialize(
            value: Tool,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.function != null -> generator.writeObject(value.function)
                value.codeExecution != null -> generator.writeObject(value.codeExecution)
                value.urlContext != null -> generator.writeObject(value.urlContext)
                value.computerUse != null -> generator.writeObject(value.computerUse)
                value.mcpServer != null -> generator.writeObject(value.mcpServer)
                value.googleSearch != null -> generator.writeObject(value.googleSearch)
                value.fileSearch != null -> generator.writeObject(value.fileSearch)
                value.googleMaps != null -> generator.writeObject(value.googleMaps)
                value.retrieval != null -> generator.writeObject(value.retrieval)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid Tool")
            }
        }
    }

    /** A tool that can be used by the model to interact with the computer. */
    class ComputerUse
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val type: JsonValue,
        private val environment: JsonField<Environment>,
        private val excludedPredefinedFunctions: JsonField<List<String>>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
            @JsonProperty("environment")
            @ExcludeMissing
            environment: JsonField<Environment> = JsonMissing.of(),
            @JsonProperty("excludedPredefinedFunctions")
            @ExcludeMissing
            excludedPredefinedFunctions: JsonField<List<String>> = JsonMissing.of(),
        ) : this(type, environment, excludedPredefinedFunctions, mutableMapOf())

        /**
         * Expected to always return the following:
         * ```java
         * JsonValue.from("computer_use")
         * ```
         *
         * However, this method can be useful for debugging and logging (e.g. if the server
         * responded with an unexpected value).
         */
        @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

        /**
         * The environment being operated.
         *
         * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
         *   (e.g. if the server responded with an unexpected value).
         */
        fun environment(): Optional<Environment> = environment.getOptional("environment")

        /**
         * The list of predefined functions that are excluded from the model call.
         *
         * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
         *   (e.g. if the server responded with an unexpected value).
         */
        fun excludedPredefinedFunctions(): Optional<List<String>> =
            excludedPredefinedFunctions.getOptional("excludedPredefinedFunctions")

        /**
         * Returns the raw JSON value of [environment].
         *
         * Unlike [environment], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("environment")
        @ExcludeMissing
        fun _environment(): JsonField<Environment> = environment

        /**
         * Returns the raw JSON value of [excludedPredefinedFunctions].
         *
         * Unlike [excludedPredefinedFunctions], this method doesn't throw if the JSON field has an
         * unexpected type.
         */
        @JsonProperty("excludedPredefinedFunctions")
        @ExcludeMissing
        fun _excludedPredefinedFunctions(): JsonField<List<String>> = excludedPredefinedFunctions

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

            /** Returns a mutable builder for constructing an instance of [ComputerUse]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [ComputerUse]. */
        class Builder internal constructor() {

            private var type: JsonValue = JsonValue.from("computer_use")
            private var environment: JsonField<Environment> = JsonMissing.of()
            private var excludedPredefinedFunctions: JsonField<MutableList<String>>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(computerUse: ComputerUse) = apply {
                type = computerUse.type
                environment = computerUse.environment
                excludedPredefinedFunctions =
                    computerUse.excludedPredefinedFunctions.map { it.toMutableList() }
                additionalProperties = computerUse.additionalProperties.toMutableMap()
            }

            /**
             * Sets the field to an arbitrary JSON value.
             *
             * It is usually unnecessary to call this method because the field defaults to the
             * following:
             * ```java
             * JsonValue.from("computer_use")
             * ```
             *
             * This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun type(type: JsonValue) = apply { this.type = type }

            /** The environment being operated. */
            fun environment(environment: Environment) = environment(JsonField.of(environment))

            /**
             * Sets [Builder.environment] to an arbitrary JSON value.
             *
             * You should usually call [Builder.environment] with a well-typed [Environment] value
             * instead. This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun environment(environment: JsonField<Environment>) = apply {
                this.environment = environment
            }

            /** The list of predefined functions that are excluded from the model call. */
            fun excludedPredefinedFunctions(excludedPredefinedFunctions: List<String>) =
                excludedPredefinedFunctions(JsonField.of(excludedPredefinedFunctions))

            /**
             * Sets [Builder.excludedPredefinedFunctions] to an arbitrary JSON value.
             *
             * You should usually call [Builder.excludedPredefinedFunctions] with a well-typed
             * `List<String>` value instead. This method is primarily for setting the field to an
             * undocumented or not yet supported value.
             */
            fun excludedPredefinedFunctions(excludedPredefinedFunctions: JsonField<List<String>>) =
                apply {
                    this.excludedPredefinedFunctions =
                        excludedPredefinedFunctions.map { it.toMutableList() }
                }

            /**
             * Adds a single [String] to [excludedPredefinedFunctions].
             *
             * @throws IllegalStateException if the field was previously set to a non-list.
             */
            fun addExcludedPredefinedFunction(excludedPredefinedFunction: String) = apply {
                excludedPredefinedFunctions =
                    (excludedPredefinedFunctions ?: JsonField.of(mutableListOf())).also {
                        checkKnown("excludedPredefinedFunctions", it)
                            .add(excludedPredefinedFunction)
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
             * Returns an immutable instance of [ComputerUse].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): ComputerUse =
                ComputerUse(
                    type,
                    environment,
                    (excludedPredefinedFunctions ?: JsonMissing.of()).map { it.toImmutable() },
                    additionalProperties.toMutableMap(),
                )
        }

        private var validated: Boolean = false

        fun validate(): ComputerUse = apply {
            if (validated) {
                return@apply
            }

            _type().let {
                if (it != JsonValue.from("computer_use")) {
                    throw GeminiNextGenApiInvalidDataException("'type' is invalid, received $it")
                }
            }
            environment().ifPresent { it.validate() }
            excludedPredefinedFunctions()
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
            type.let { if (it == JsonValue.from("computer_use")) 1 else 0 } +
                (environment.asKnown().getOrNull()?.validity() ?: 0) +
                (excludedPredefinedFunctions.asKnown().getOrNull()?.size ?: 0)

        /** The environment being operated. */
        class Environment @JsonCreator private constructor(private val value: JsonField<String>) :
            Enum {

            /**
             * Returns this class instance's raw value.
             *
             * This is usually only useful if this instance was deserialized from data that doesn't
             * match any known member, and you want to know that value. For example, if the SDK is
             * on an older version than the API, then the API may respond with new members that the
             * SDK is unaware of.
             */
            @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

            companion object {

                @JvmField val BROWSER = of("browser")

                @JvmStatic fun of(value: String) = Environment(JsonField.of(value))
            }

            /** An enum containing [Environment]'s known values. */
            enum class Known {
                BROWSER
            }

            /**
             * An enum containing [Environment]'s known values, as well as an [_UNKNOWN] member.
             *
             * An instance of [Environment] can contain an unknown value in a couple of cases:
             * - It was deserialized from data that doesn't match any known member. For example, if
             *   the SDK is on an older version than the API, then the API may respond with new
             *   members that the SDK is unaware of.
             * - It was constructed with an arbitrary value using the [of] method.
             */
            enum class Value {
                BROWSER,
                /**
                 * An enum member indicating that [Environment] was instantiated with an unknown
                 * value.
                 */
                _UNKNOWN,
            }

            /**
             * Returns an enum member corresponding to this class instance's value, or
             * [Value._UNKNOWN] if the class was instantiated with an unknown value.
             *
             * Use the [known] method instead if you're certain the value is always known or if you
             * want to throw for the unknown case.
             */
            fun value(): Value =
                when (this) {
                    BROWSER -> Value.BROWSER
                    else -> Value._UNKNOWN
                }

            /**
             * Returns an enum member corresponding to this class instance's value.
             *
             * Use the [value] method instead if you're uncertain the value is always known and
             * don't want to throw for the unknown case.
             *
             * @throws GeminiNextGenApiInvalidDataException if this class instance's value is a not
             *   a known member.
             */
            fun known(): Known =
                when (this) {
                    BROWSER -> Known.BROWSER
                    else ->
                        throw GeminiNextGenApiInvalidDataException("Unknown Environment: $value")
                }

            /**
             * Returns this class instance's primitive wire representation.
             *
             * This differs from the [toString] method because that method is primarily for
             * debugging and generally doesn't throw.
             *
             * @throws GeminiNextGenApiInvalidDataException if this class instance's value does not
             *   have the expected primitive type.
             */
            fun asString(): String =
                _value().asString().orElseThrow {
                    GeminiNextGenApiInvalidDataException("Value is not a String")
                }

            private var validated: Boolean = false

            fun validate(): Environment = apply {
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

                return other is Environment && value == other.value
            }

            override fun hashCode() = value.hashCode()

            override fun toString() = value.toString()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is ComputerUse &&
                type == other.type &&
                environment == other.environment &&
                excludedPredefinedFunctions == other.excludedPredefinedFunctions &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(type, environment, excludedPredefinedFunctions, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "ComputerUse{type=$type, environment=$environment, excludedPredefinedFunctions=$excludedPredefinedFunctions, additionalProperties=$additionalProperties}"
    }

    /** A MCPServer is a server that can be called by the model to perform actions. */
    class McpServer
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val type: JsonValue,
        private val allowedTools: JsonField<List<AllowedTools>>,
        private val headers: JsonField<Headers>,
        private val name: JsonField<String>,
        private val url: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
            @JsonProperty("allowed_tools")
            @ExcludeMissing
            allowedTools: JsonField<List<AllowedTools>> = JsonMissing.of(),
            @JsonProperty("headers") @ExcludeMissing headers: JsonField<Headers> = JsonMissing.of(),
            @JsonProperty("name") @ExcludeMissing name: JsonField<String> = JsonMissing.of(),
            @JsonProperty("url") @ExcludeMissing url: JsonField<String> = JsonMissing.of(),
        ) : this(type, allowedTools, headers, name, url, mutableMapOf())

        /**
         * Expected to always return the following:
         * ```java
         * JsonValue.from("mcp_server")
         * ```
         *
         * However, this method can be useful for debugging and logging (e.g. if the server
         * responded with an unexpected value).
         */
        @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

        /**
         * The allowed tools.
         *
         * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
         *   (e.g. if the server responded with an unexpected value).
         */
        fun allowedTools(): Optional<List<AllowedTools>> = allowedTools.getOptional("allowed_tools")

        /**
         * Optional: Fields for authentication headers, timeouts, etc., if needed.
         *
         * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
         *   (e.g. if the server responded with an unexpected value).
         */
        fun headers(): Optional<Headers> = headers.getOptional("headers")

        /**
         * The name of the MCPServer.
         *
         * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
         *   (e.g. if the server responded with an unexpected value).
         */
        fun name(): Optional<String> = name.getOptional("name")

        /**
         * The full URL for the MCPServer endpoint. Example: "https://api.example.com/mcp"
         *
         * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
         *   (e.g. if the server responded with an unexpected value).
         */
        fun url(): Optional<String> = url.getOptional("url")

        /**
         * Returns the raw JSON value of [allowedTools].
         *
         * Unlike [allowedTools], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("allowed_tools")
        @ExcludeMissing
        fun _allowedTools(): JsonField<List<AllowedTools>> = allowedTools

        /**
         * Returns the raw JSON value of [headers].
         *
         * Unlike [headers], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("headers") @ExcludeMissing fun _headers(): JsonField<Headers> = headers

        /**
         * Returns the raw JSON value of [name].
         *
         * Unlike [name], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("name") @ExcludeMissing fun _name(): JsonField<String> = name

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

            /** Returns a mutable builder for constructing an instance of [McpServer]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [McpServer]. */
        class Builder internal constructor() {

            private var type: JsonValue = JsonValue.from("mcp_server")
            private var allowedTools: JsonField<MutableList<AllowedTools>>? = null
            private var headers: JsonField<Headers> = JsonMissing.of()
            private var name: JsonField<String> = JsonMissing.of()
            private var url: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(mcpServer: McpServer) = apply {
                type = mcpServer.type
                allowedTools = mcpServer.allowedTools.map { it.toMutableList() }
                headers = mcpServer.headers
                name = mcpServer.name
                url = mcpServer.url
                additionalProperties = mcpServer.additionalProperties.toMutableMap()
            }

            /**
             * Sets the field to an arbitrary JSON value.
             *
             * It is usually unnecessary to call this method because the field defaults to the
             * following:
             * ```java
             * JsonValue.from("mcp_server")
             * ```
             *
             * This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun type(type: JsonValue) = apply { this.type = type }

            /** The allowed tools. */
            fun allowedTools(allowedTools: List<AllowedTools>) =
                allowedTools(JsonField.of(allowedTools))

            /**
             * Sets [Builder.allowedTools] to an arbitrary JSON value.
             *
             * You should usually call [Builder.allowedTools] with a well-typed `List<AllowedTools>`
             * value instead. This method is primarily for setting the field to an undocumented or
             * not yet supported value.
             */
            fun allowedTools(allowedTools: JsonField<List<AllowedTools>>) = apply {
                this.allowedTools = allowedTools.map { it.toMutableList() }
            }

            /**
             * Adds a single [AllowedTools] to [allowedTools].
             *
             * @throws IllegalStateException if the field was previously set to a non-list.
             */
            fun addAllowedTool(allowedTool: AllowedTools) = apply {
                allowedTools =
                    (allowedTools ?: JsonField.of(mutableListOf())).also {
                        checkKnown("allowedTools", it).add(allowedTool)
                    }
            }

            /** Optional: Fields for authentication headers, timeouts, etc., if needed. */
            fun headers(headers: Headers) = headers(JsonField.of(headers))

            /**
             * Sets [Builder.headers] to an arbitrary JSON value.
             *
             * You should usually call [Builder.headers] with a well-typed [Headers] value instead.
             * This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun headers(headers: JsonField<Headers>) = apply { this.headers = headers }

            /** The name of the MCPServer. */
            fun name(name: String) = name(JsonField.of(name))

            /**
             * Sets [Builder.name] to an arbitrary JSON value.
             *
             * You should usually call [Builder.name] with a well-typed [String] value instead. This
             * method is primarily for setting the field to an undocumented or not yet supported
             * value.
             */
            fun name(name: JsonField<String>) = apply { this.name = name }

            /** The full URL for the MCPServer endpoint. Example: "https://api.example.com/mcp" */
            fun url(url: String) = url(JsonField.of(url))

            /**
             * Sets [Builder.url] to an arbitrary JSON value.
             *
             * You should usually call [Builder.url] with a well-typed [String] value instead. This
             * method is primarily for setting the field to an undocumented or not yet supported
             * value.
             */
            fun url(url: JsonField<String>) = apply { this.url = url }

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
             * Returns an immutable instance of [McpServer].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): McpServer =
                McpServer(
                    type,
                    (allowedTools ?: JsonMissing.of()).map { it.toImmutable() },
                    headers,
                    name,
                    url,
                    additionalProperties.toMutableMap(),
                )
        }

        private var validated: Boolean = false

        fun validate(): McpServer = apply {
            if (validated) {
                return@apply
            }

            _type().let {
                if (it != JsonValue.from("mcp_server")) {
                    throw GeminiNextGenApiInvalidDataException("'type' is invalid, received $it")
                }
            }
            allowedTools().ifPresent { it.forEach { it.validate() } }
            headers().ifPresent { it.validate() }
            name()
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
            type.let { if (it == JsonValue.from("mcp_server")) 1 else 0 } +
                (allowedTools.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
                (headers.asKnown().getOrNull()?.validity() ?: 0) +
                (if (name.asKnown().isPresent) 1 else 0) +
                (if (url.asKnown().isPresent) 1 else 0)

        /** Optional: Fields for authentication headers, timeouts, etc., if needed. */
        class Headers
        @JsonCreator
        private constructor(
            @com.fasterxml.jackson.annotation.JsonValue
            private val additionalProperties: Map<String, JsonValue>
        ) {

            @JsonAnyGetter
            @ExcludeMissing
            fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

            fun toBuilder() = Builder().from(this)

            companion object {

                /** Returns a mutable builder for constructing an instance of [Headers]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [Headers]. */
            class Builder internal constructor() {

                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(headers: Headers) = apply {
                    additionalProperties = headers.additionalProperties.toMutableMap()
                }

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
                 * Returns an immutable instance of [Headers].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 */
                fun build(): Headers = Headers(additionalProperties.toImmutable())
            }

            private var validated: Boolean = false

            fun validate(): Headers = apply {
                if (validated) {
                    return@apply
                }

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
                additionalProperties.count { (_, value) -> !value.isNull() && !value.isMissing() }

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is Headers && additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy { Objects.hash(additionalProperties) }

            override fun hashCode(): Int = hashCode

            override fun toString() = "Headers{additionalProperties=$additionalProperties}"
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is McpServer &&
                type == other.type &&
                allowedTools == other.allowedTools &&
                headers == other.headers &&
                name == other.name &&
                url == other.url &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(type, allowedTools, headers, name, url, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "McpServer{type=$type, allowedTools=$allowedTools, headers=$headers, name=$name, url=$url, additionalProperties=$additionalProperties}"
    }

    /** A tool that can be used by the model to search Google. */
    class GoogleSearch
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val type: JsonValue,
        private val searchTypes: JsonField<List<SearchType>>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
            @JsonProperty("search_types")
            @ExcludeMissing
            searchTypes: JsonField<List<SearchType>> = JsonMissing.of(),
        ) : this(type, searchTypes, mutableMapOf())

        /**
         * Expected to always return the following:
         * ```java
         * JsonValue.from("google_search")
         * ```
         *
         * However, this method can be useful for debugging and logging (e.g. if the server
         * responded with an unexpected value).
         */
        @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

        /**
         * The types of search grounding to enable.
         *
         * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
         *   (e.g. if the server responded with an unexpected value).
         */
        fun searchTypes(): Optional<List<SearchType>> = searchTypes.getOptional("search_types")

        /**
         * Returns the raw JSON value of [searchTypes].
         *
         * Unlike [searchTypes], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("search_types")
        @ExcludeMissing
        fun _searchTypes(): JsonField<List<SearchType>> = searchTypes

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

            /** Returns a mutable builder for constructing an instance of [GoogleSearch]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [GoogleSearch]. */
        class Builder internal constructor() {

            private var type: JsonValue = JsonValue.from("google_search")
            private var searchTypes: JsonField<MutableList<SearchType>>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(googleSearch: GoogleSearch) = apply {
                type = googleSearch.type
                searchTypes = googleSearch.searchTypes.map { it.toMutableList() }
                additionalProperties = googleSearch.additionalProperties.toMutableMap()
            }

            /**
             * Sets the field to an arbitrary JSON value.
             *
             * It is usually unnecessary to call this method because the field defaults to the
             * following:
             * ```java
             * JsonValue.from("google_search")
             * ```
             *
             * This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun type(type: JsonValue) = apply { this.type = type }

            /** The types of search grounding to enable. */
            fun searchTypes(searchTypes: List<SearchType>) = searchTypes(JsonField.of(searchTypes))

            /**
             * Sets [Builder.searchTypes] to an arbitrary JSON value.
             *
             * You should usually call [Builder.searchTypes] with a well-typed `List<SearchType>`
             * value instead. This method is primarily for setting the field to an undocumented or
             * not yet supported value.
             */
            fun searchTypes(searchTypes: JsonField<List<SearchType>>) = apply {
                this.searchTypes = searchTypes.map { it.toMutableList() }
            }

            /**
             * Adds a single [SearchType] to [searchTypes].
             *
             * @throws IllegalStateException if the field was previously set to a non-list.
             */
            fun addSearchType(searchType: SearchType) = apply {
                searchTypes =
                    (searchTypes ?: JsonField.of(mutableListOf())).also {
                        checkKnown("searchTypes", it).add(searchType)
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
             * Returns an immutable instance of [GoogleSearch].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): GoogleSearch =
                GoogleSearch(
                    type,
                    (searchTypes ?: JsonMissing.of()).map { it.toImmutable() },
                    additionalProperties.toMutableMap(),
                )
        }

        private var validated: Boolean = false

        fun validate(): GoogleSearch = apply {
            if (validated) {
                return@apply
            }

            _type().let {
                if (it != JsonValue.from("google_search")) {
                    throw GeminiNextGenApiInvalidDataException("'type' is invalid, received $it")
                }
            }
            searchTypes().ifPresent { it.forEach { it.validate() } }
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
            type.let { if (it == JsonValue.from("google_search")) 1 else 0 } +
                (searchTypes.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0)

        class SearchType @JsonCreator private constructor(private val value: JsonField<String>) :
            Enum {

            /**
             * Returns this class instance's raw value.
             *
             * This is usually only useful if this instance was deserialized from data that doesn't
             * match any known member, and you want to know that value. For example, if the SDK is
             * on an older version than the API, then the API may respond with new members that the
             * SDK is unaware of.
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
             * - It was deserialized from data that doesn't match any known member. For example, if
             *   the SDK is on an older version than the API, then the API may respond with new
             *   members that the SDK is unaware of.
             * - It was constructed with an arbitrary value using the [of] method.
             */
            enum class Value {
                WEB_SEARCH,
                IMAGE_SEARCH,
                ENTERPRISE_WEB_SEARCH,
                /**
                 * An enum member indicating that [SearchType] was instantiated with an unknown
                 * value.
                 */
                _UNKNOWN,
            }

            /**
             * Returns an enum member corresponding to this class instance's value, or
             * [Value._UNKNOWN] if the class was instantiated with an unknown value.
             *
             * Use the [known] method instead if you're certain the value is always known or if you
             * want to throw for the unknown case.
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
             * Use the [value] method instead if you're uncertain the value is always known and
             * don't want to throw for the unknown case.
             *
             * @throws GeminiNextGenApiInvalidDataException if this class instance's value is a not
             *   a known member.
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
             * This differs from the [toString] method because that method is primarily for
             * debugging and generally doesn't throw.
             *
             * @throws GeminiNextGenApiInvalidDataException if this class instance's value does not
             *   have the expected primitive type.
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

            return other is GoogleSearch &&
                type == other.type &&
                searchTypes == other.searchTypes &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(type, searchTypes, additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "GoogleSearch{type=$type, searchTypes=$searchTypes, additionalProperties=$additionalProperties}"
    }

    /** A tool that can be used by the model to search files. */
    class FileSearch
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val type: JsonValue,
        private val fileSearchStoreNames: JsonField<List<String>>,
        private val metadataFilter: JsonField<String>,
        private val topK: JsonField<Int>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
            @JsonProperty("file_search_store_names")
            @ExcludeMissing
            fileSearchStoreNames: JsonField<List<String>> = JsonMissing.of(),
            @JsonProperty("metadata_filter")
            @ExcludeMissing
            metadataFilter: JsonField<String> = JsonMissing.of(),
            @JsonProperty("top_k") @ExcludeMissing topK: JsonField<Int> = JsonMissing.of(),
        ) : this(type, fileSearchStoreNames, metadataFilter, topK, mutableMapOf())

        /**
         * Expected to always return the following:
         * ```java
         * JsonValue.from("file_search")
         * ```
         *
         * However, this method can be useful for debugging and logging (e.g. if the server
         * responded with an unexpected value).
         */
        @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

        /**
         * The file search store names to search.
         *
         * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
         *   (e.g. if the server responded with an unexpected value).
         */
        fun fileSearchStoreNames(): Optional<List<String>> =
            fileSearchStoreNames.getOptional("file_search_store_names")

        /**
         * Metadata filter to apply to the semantic retrieval documents and chunks.
         *
         * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
         *   (e.g. if the server responded with an unexpected value).
         */
        fun metadataFilter(): Optional<String> = metadataFilter.getOptional("metadata_filter")

        /**
         * The number of semantic retrieval chunks to retrieve.
         *
         * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
         *   (e.g. if the server responded with an unexpected value).
         */
        fun topK(): Optional<Int> = topK.getOptional("top_k")

        /**
         * Returns the raw JSON value of [fileSearchStoreNames].
         *
         * Unlike [fileSearchStoreNames], this method doesn't throw if the JSON field has an
         * unexpected type.
         */
        @JsonProperty("file_search_store_names")
        @ExcludeMissing
        fun _fileSearchStoreNames(): JsonField<List<String>> = fileSearchStoreNames

        /**
         * Returns the raw JSON value of [metadataFilter].
         *
         * Unlike [metadataFilter], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("metadata_filter")
        @ExcludeMissing
        fun _metadataFilter(): JsonField<String> = metadataFilter

        /**
         * Returns the raw JSON value of [topK].
         *
         * Unlike [topK], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("top_k") @ExcludeMissing fun _topK(): JsonField<Int> = topK

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

            /** Returns a mutable builder for constructing an instance of [FileSearch]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [FileSearch]. */
        class Builder internal constructor() {

            private var type: JsonValue = JsonValue.from("file_search")
            private var fileSearchStoreNames: JsonField<MutableList<String>>? = null
            private var metadataFilter: JsonField<String> = JsonMissing.of()
            private var topK: JsonField<Int> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(fileSearch: FileSearch) = apply {
                type = fileSearch.type
                fileSearchStoreNames = fileSearch.fileSearchStoreNames.map { it.toMutableList() }
                metadataFilter = fileSearch.metadataFilter
                topK = fileSearch.topK
                additionalProperties = fileSearch.additionalProperties.toMutableMap()
            }

            /**
             * Sets the field to an arbitrary JSON value.
             *
             * It is usually unnecessary to call this method because the field defaults to the
             * following:
             * ```java
             * JsonValue.from("file_search")
             * ```
             *
             * This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun type(type: JsonValue) = apply { this.type = type }

            /** The file search store names to search. */
            fun fileSearchStoreNames(fileSearchStoreNames: List<String>) =
                fileSearchStoreNames(JsonField.of(fileSearchStoreNames))

            /**
             * Sets [Builder.fileSearchStoreNames] to an arbitrary JSON value.
             *
             * You should usually call [Builder.fileSearchStoreNames] with a well-typed
             * `List<String>` value instead. This method is primarily for setting the field to an
             * undocumented or not yet supported value.
             */
            fun fileSearchStoreNames(fileSearchStoreNames: JsonField<List<String>>) = apply {
                this.fileSearchStoreNames = fileSearchStoreNames.map { it.toMutableList() }
            }

            /**
             * Adds a single [String] to [fileSearchStoreNames].
             *
             * @throws IllegalStateException if the field was previously set to a non-list.
             */
            fun addFileSearchStoreName(fileSearchStoreName: String) = apply {
                fileSearchStoreNames =
                    (fileSearchStoreNames ?: JsonField.of(mutableListOf())).also {
                        checkKnown("fileSearchStoreNames", it).add(fileSearchStoreName)
                    }
            }

            /** Metadata filter to apply to the semantic retrieval documents and chunks. */
            fun metadataFilter(metadataFilter: String) =
                metadataFilter(JsonField.of(metadataFilter))

            /**
             * Sets [Builder.metadataFilter] to an arbitrary JSON value.
             *
             * You should usually call [Builder.metadataFilter] with a well-typed [String] value
             * instead. This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun metadataFilter(metadataFilter: JsonField<String>) = apply {
                this.metadataFilter = metadataFilter
            }

            /** The number of semantic retrieval chunks to retrieve. */
            fun topK(topK: Int) = topK(JsonField.of(topK))

            /**
             * Sets [Builder.topK] to an arbitrary JSON value.
             *
             * You should usually call [Builder.topK] with a well-typed [Int] value instead. This
             * method is primarily for setting the field to an undocumented or not yet supported
             * value.
             */
            fun topK(topK: JsonField<Int>) = apply { this.topK = topK }

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
             * Returns an immutable instance of [FileSearch].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): FileSearch =
                FileSearch(
                    type,
                    (fileSearchStoreNames ?: JsonMissing.of()).map { it.toImmutable() },
                    metadataFilter,
                    topK,
                    additionalProperties.toMutableMap(),
                )
        }

        private var validated: Boolean = false

        fun validate(): FileSearch = apply {
            if (validated) {
                return@apply
            }

            _type().let {
                if (it != JsonValue.from("file_search")) {
                    throw GeminiNextGenApiInvalidDataException("'type' is invalid, received $it")
                }
            }
            fileSearchStoreNames()
            metadataFilter()
            topK()
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
            type.let { if (it == JsonValue.from("file_search")) 1 else 0 } +
                (fileSearchStoreNames.asKnown().getOrNull()?.size ?: 0) +
                (if (metadataFilter.asKnown().isPresent) 1 else 0) +
                (if (topK.asKnown().isPresent) 1 else 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is FileSearch &&
                type == other.type &&
                fileSearchStoreNames == other.fileSearchStoreNames &&
                metadataFilter == other.metadataFilter &&
                topK == other.topK &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(type, fileSearchStoreNames, metadataFilter, topK, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "FileSearch{type=$type, fileSearchStoreNames=$fileSearchStoreNames, metadataFilter=$metadataFilter, topK=$topK, additionalProperties=$additionalProperties}"
    }

    /** A tool that can be used by the model to call Google Maps. */
    class GoogleMaps
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val type: JsonValue,
        private val enableWidget: JsonField<Boolean>,
        private val latitude: JsonField<Double>,
        private val longitude: JsonField<Double>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
            @JsonProperty("enable_widget")
            @ExcludeMissing
            enableWidget: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("latitude")
            @ExcludeMissing
            latitude: JsonField<Double> = JsonMissing.of(),
            @JsonProperty("longitude")
            @ExcludeMissing
            longitude: JsonField<Double> = JsonMissing.of(),
        ) : this(type, enableWidget, latitude, longitude, mutableMapOf())

        /**
         * Expected to always return the following:
         * ```java
         * JsonValue.from("google_maps")
         * ```
         *
         * However, this method can be useful for debugging and logging (e.g. if the server
         * responded with an unexpected value).
         */
        @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

        /**
         * Whether to return a widget context token in the tool call result of the response.
         *
         * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
         *   (e.g. if the server responded with an unexpected value).
         */
        fun enableWidget(): Optional<Boolean> = enableWidget.getOptional("enable_widget")

        /**
         * The latitude of the user's location.
         *
         * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
         *   (e.g. if the server responded with an unexpected value).
         */
        fun latitude(): Optional<Double> = latitude.getOptional("latitude")

        /**
         * The longitude of the user's location.
         *
         * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
         *   (e.g. if the server responded with an unexpected value).
         */
        fun longitude(): Optional<Double> = longitude.getOptional("longitude")

        /**
         * Returns the raw JSON value of [enableWidget].
         *
         * Unlike [enableWidget], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("enable_widget")
        @ExcludeMissing
        fun _enableWidget(): JsonField<Boolean> = enableWidget

        /**
         * Returns the raw JSON value of [latitude].
         *
         * Unlike [latitude], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("latitude") @ExcludeMissing fun _latitude(): JsonField<Double> = latitude

        /**
         * Returns the raw JSON value of [longitude].
         *
         * Unlike [longitude], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("longitude") @ExcludeMissing fun _longitude(): JsonField<Double> = longitude

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

            /** Returns a mutable builder for constructing an instance of [GoogleMaps]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [GoogleMaps]. */
        class Builder internal constructor() {

            private var type: JsonValue = JsonValue.from("google_maps")
            private var enableWidget: JsonField<Boolean> = JsonMissing.of()
            private var latitude: JsonField<Double> = JsonMissing.of()
            private var longitude: JsonField<Double> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(googleMaps: GoogleMaps) = apply {
                type = googleMaps.type
                enableWidget = googleMaps.enableWidget
                latitude = googleMaps.latitude
                longitude = googleMaps.longitude
                additionalProperties = googleMaps.additionalProperties.toMutableMap()
            }

            /**
             * Sets the field to an arbitrary JSON value.
             *
             * It is usually unnecessary to call this method because the field defaults to the
             * following:
             * ```java
             * JsonValue.from("google_maps")
             * ```
             *
             * This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun type(type: JsonValue) = apply { this.type = type }

            /** Whether to return a widget context token in the tool call result of the response. */
            fun enableWidget(enableWidget: Boolean) = enableWidget(JsonField.of(enableWidget))

            /**
             * Sets [Builder.enableWidget] to an arbitrary JSON value.
             *
             * You should usually call [Builder.enableWidget] with a well-typed [Boolean] value
             * instead. This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun enableWidget(enableWidget: JsonField<Boolean>) = apply {
                this.enableWidget = enableWidget
            }

            /** The latitude of the user's location. */
            fun latitude(latitude: Double) = latitude(JsonField.of(latitude))

            /**
             * Sets [Builder.latitude] to an arbitrary JSON value.
             *
             * You should usually call [Builder.latitude] with a well-typed [Double] value instead.
             * This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun latitude(latitude: JsonField<Double>) = apply { this.latitude = latitude }

            /** The longitude of the user's location. */
            fun longitude(longitude: Double) = longitude(JsonField.of(longitude))

            /**
             * Sets [Builder.longitude] to an arbitrary JSON value.
             *
             * You should usually call [Builder.longitude] with a well-typed [Double] value instead.
             * This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun longitude(longitude: JsonField<Double>) = apply { this.longitude = longitude }

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
             * Returns an immutable instance of [GoogleMaps].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): GoogleMaps =
                GoogleMaps(
                    type,
                    enableWidget,
                    latitude,
                    longitude,
                    additionalProperties.toMutableMap(),
                )
        }

        private var validated: Boolean = false

        fun validate(): GoogleMaps = apply {
            if (validated) {
                return@apply
            }

            _type().let {
                if (it != JsonValue.from("google_maps")) {
                    throw GeminiNextGenApiInvalidDataException("'type' is invalid, received $it")
                }
            }
            enableWidget()
            latitude()
            longitude()
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
            type.let { if (it == JsonValue.from("google_maps")) 1 else 0 } +
                (if (enableWidget.asKnown().isPresent) 1 else 0) +
                (if (latitude.asKnown().isPresent) 1 else 0) +
                (if (longitude.asKnown().isPresent) 1 else 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is GoogleMaps &&
                type == other.type &&
                enableWidget == other.enableWidget &&
                latitude == other.latitude &&
                longitude == other.longitude &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(type, enableWidget, latitude, longitude, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "GoogleMaps{type=$type, enableWidget=$enableWidget, latitude=$latitude, longitude=$longitude, additionalProperties=$additionalProperties}"
    }

    /** A tool that can be used by the model to retrieve files. */
    class Retrieval
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val type: JsonValue,
        private val retrievalTypes: JsonField<List<RetrievalType>>,
        private val vertexAiSearchConfig: JsonField<VertexAiSearchConfig>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
            @JsonProperty("retrieval_types")
            @ExcludeMissing
            retrievalTypes: JsonField<List<RetrievalType>> = JsonMissing.of(),
            @JsonProperty("vertex_ai_search_config")
            @ExcludeMissing
            vertexAiSearchConfig: JsonField<VertexAiSearchConfig> = JsonMissing.of(),
        ) : this(type, retrievalTypes, vertexAiSearchConfig, mutableMapOf())

        /**
         * Expected to always return the following:
         * ```java
         * JsonValue.from("retrieval")
         * ```
         *
         * However, this method can be useful for debugging and logging (e.g. if the server
         * responded with an unexpected value).
         */
        @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

        /**
         * The types of file retrieval to enable.
         *
         * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
         *   (e.g. if the server responded with an unexpected value).
         */
        fun retrievalTypes(): Optional<List<RetrievalType>> =
            retrievalTypes.getOptional("retrieval_types")

        /**
         * Used to specify configuration for VertexAISearch.
         *
         * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
         *   (e.g. if the server responded with an unexpected value).
         */
        fun vertexAiSearchConfig(): Optional<VertexAiSearchConfig> =
            vertexAiSearchConfig.getOptional("vertex_ai_search_config")

        /**
         * Returns the raw JSON value of [retrievalTypes].
         *
         * Unlike [retrievalTypes], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("retrieval_types")
        @ExcludeMissing
        fun _retrievalTypes(): JsonField<List<RetrievalType>> = retrievalTypes

        /**
         * Returns the raw JSON value of [vertexAiSearchConfig].
         *
         * Unlike [vertexAiSearchConfig], this method doesn't throw if the JSON field has an
         * unexpected type.
         */
        @JsonProperty("vertex_ai_search_config")
        @ExcludeMissing
        fun _vertexAiSearchConfig(): JsonField<VertexAiSearchConfig> = vertexAiSearchConfig

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

            /** Returns a mutable builder for constructing an instance of [Retrieval]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Retrieval]. */
        class Builder internal constructor() {

            private var type: JsonValue = JsonValue.from("retrieval")
            private var retrievalTypes: JsonField<MutableList<RetrievalType>>? = null
            private var vertexAiSearchConfig: JsonField<VertexAiSearchConfig> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(retrieval: Retrieval) = apply {
                type = retrieval.type
                retrievalTypes = retrieval.retrievalTypes.map { it.toMutableList() }
                vertexAiSearchConfig = retrieval.vertexAiSearchConfig
                additionalProperties = retrieval.additionalProperties.toMutableMap()
            }

            /**
             * Sets the field to an arbitrary JSON value.
             *
             * It is usually unnecessary to call this method because the field defaults to the
             * following:
             * ```java
             * JsonValue.from("retrieval")
             * ```
             *
             * This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun type(type: JsonValue) = apply { this.type = type }

            /** The types of file retrieval to enable. */
            fun retrievalTypes(retrievalTypes: List<RetrievalType>) =
                retrievalTypes(JsonField.of(retrievalTypes))

            /**
             * Sets [Builder.retrievalTypes] to an arbitrary JSON value.
             *
             * You should usually call [Builder.retrievalTypes] with a well-typed
             * `List<RetrievalType>` value instead. This method is primarily for setting the field
             * to an undocumented or not yet supported value.
             */
            fun retrievalTypes(retrievalTypes: JsonField<List<RetrievalType>>) = apply {
                this.retrievalTypes = retrievalTypes.map { it.toMutableList() }
            }

            /**
             * Adds a single [RetrievalType] to [retrievalTypes].
             *
             * @throws IllegalStateException if the field was previously set to a non-list.
             */
            fun addRetrievalType(retrievalType: RetrievalType) = apply {
                retrievalTypes =
                    (retrievalTypes ?: JsonField.of(mutableListOf())).also {
                        checkKnown("retrievalTypes", it).add(retrievalType)
                    }
            }

            /** Used to specify configuration for VertexAISearch. */
            fun vertexAiSearchConfig(vertexAiSearchConfig: VertexAiSearchConfig) =
                vertexAiSearchConfig(JsonField.of(vertexAiSearchConfig))

            /**
             * Sets [Builder.vertexAiSearchConfig] to an arbitrary JSON value.
             *
             * You should usually call [Builder.vertexAiSearchConfig] with a well-typed
             * [VertexAiSearchConfig] value instead. This method is primarily for setting the field
             * to an undocumented or not yet supported value.
             */
            fun vertexAiSearchConfig(vertexAiSearchConfig: JsonField<VertexAiSearchConfig>) =
                apply {
                    this.vertexAiSearchConfig = vertexAiSearchConfig
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
             * Returns an immutable instance of [Retrieval].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): Retrieval =
                Retrieval(
                    type,
                    (retrievalTypes ?: JsonMissing.of()).map { it.toImmutable() },
                    vertexAiSearchConfig,
                    additionalProperties.toMutableMap(),
                )
        }

        private var validated: Boolean = false

        fun validate(): Retrieval = apply {
            if (validated) {
                return@apply
            }

            _type().let {
                if (it != JsonValue.from("retrieval")) {
                    throw GeminiNextGenApiInvalidDataException("'type' is invalid, received $it")
                }
            }
            retrievalTypes().ifPresent { it.forEach { it.validate() } }
            vertexAiSearchConfig().ifPresent { it.validate() }
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
            type.let { if (it == JsonValue.from("retrieval")) 1 else 0 } +
                (retrievalTypes.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
                (vertexAiSearchConfig.asKnown().getOrNull()?.validity() ?: 0)

        class RetrievalType @JsonCreator private constructor(private val value: JsonField<String>) :
            Enum {

            /**
             * Returns this class instance's raw value.
             *
             * This is usually only useful if this instance was deserialized from data that doesn't
             * match any known member, and you want to know that value. For example, if the SDK is
             * on an older version than the API, then the API may respond with new members that the
             * SDK is unaware of.
             */
            @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

            companion object {

                @JvmField val VERTEX_AI_SEARCH = of("vertex_ai_search")

                @JvmStatic fun of(value: String) = RetrievalType(JsonField.of(value))
            }

            /** An enum containing [RetrievalType]'s known values. */
            enum class Known {
                VERTEX_AI_SEARCH
            }

            /**
             * An enum containing [RetrievalType]'s known values, as well as an [_UNKNOWN] member.
             *
             * An instance of [RetrievalType] can contain an unknown value in a couple of cases:
             * - It was deserialized from data that doesn't match any known member. For example, if
             *   the SDK is on an older version than the API, then the API may respond with new
             *   members that the SDK is unaware of.
             * - It was constructed with an arbitrary value using the [of] method.
             */
            enum class Value {
                VERTEX_AI_SEARCH,
                /**
                 * An enum member indicating that [RetrievalType] was instantiated with an unknown
                 * value.
                 */
                _UNKNOWN,
            }

            /**
             * Returns an enum member corresponding to this class instance's value, or
             * [Value._UNKNOWN] if the class was instantiated with an unknown value.
             *
             * Use the [known] method instead if you're certain the value is always known or if you
             * want to throw for the unknown case.
             */
            fun value(): Value =
                when (this) {
                    VERTEX_AI_SEARCH -> Value.VERTEX_AI_SEARCH
                    else -> Value._UNKNOWN
                }

            /**
             * Returns an enum member corresponding to this class instance's value.
             *
             * Use the [value] method instead if you're uncertain the value is always known and
             * don't want to throw for the unknown case.
             *
             * @throws GeminiNextGenApiInvalidDataException if this class instance's value is a not
             *   a known member.
             */
            fun known(): Known =
                when (this) {
                    VERTEX_AI_SEARCH -> Known.VERTEX_AI_SEARCH
                    else ->
                        throw GeminiNextGenApiInvalidDataException("Unknown RetrievalType: $value")
                }

            /**
             * Returns this class instance's primitive wire representation.
             *
             * This differs from the [toString] method because that method is primarily for
             * debugging and generally doesn't throw.
             *
             * @throws GeminiNextGenApiInvalidDataException if this class instance's value does not
             *   have the expected primitive type.
             */
            fun asString(): String =
                _value().asString().orElseThrow {
                    GeminiNextGenApiInvalidDataException("Value is not a String")
                }

            private var validated: Boolean = false

            fun validate(): RetrievalType = apply {
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

                return other is RetrievalType && value == other.value
            }

            override fun hashCode() = value.hashCode()

            override fun toString() = value.toString()
        }

        /** Used to specify configuration for VertexAISearch. */
        class VertexAiSearchConfig
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val datastores: JsonField<List<String>>,
            private val engine: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("datastores")
                @ExcludeMissing
                datastores: JsonField<List<String>> = JsonMissing.of(),
                @JsonProperty("engine") @ExcludeMissing engine: JsonField<String> = JsonMissing.of(),
            ) : this(datastores, engine, mutableMapOf())

            /**
             * Optional. Used to specify Vertex AI Search datastores.
             *
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   (e.g. if the server responded with an unexpected value).
             */
            fun datastores(): Optional<List<String>> = datastores.getOptional("datastores")

            /**
             * Optional. Used to specify Vertex AI Search engine.
             *
             * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
             *   (e.g. if the server responded with an unexpected value).
             */
            fun engine(): Optional<String> = engine.getOptional("engine")

            /**
             * Returns the raw JSON value of [datastores].
             *
             * Unlike [datastores], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("datastores")
            @ExcludeMissing
            fun _datastores(): JsonField<List<String>> = datastores

            /**
             * Returns the raw JSON value of [engine].
             *
             * Unlike [engine], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("engine") @ExcludeMissing fun _engine(): JsonField<String> = engine

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
                 * Returns a mutable builder for constructing an instance of [VertexAiSearchConfig].
                 */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [VertexAiSearchConfig]. */
            class Builder internal constructor() {

                private var datastores: JsonField<MutableList<String>>? = null
                private var engine: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(vertexAiSearchConfig: VertexAiSearchConfig) = apply {
                    datastores = vertexAiSearchConfig.datastores.map { it.toMutableList() }
                    engine = vertexAiSearchConfig.engine
                    additionalProperties = vertexAiSearchConfig.additionalProperties.toMutableMap()
                }

                /** Optional. Used to specify Vertex AI Search datastores. */
                fun datastores(datastores: List<String>) = datastores(JsonField.of(datastores))

                /**
                 * Sets [Builder.datastores] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.datastores] with a well-typed `List<String>`
                 * value instead. This method is primarily for setting the field to an undocumented
                 * or not yet supported value.
                 */
                fun datastores(datastores: JsonField<List<String>>) = apply {
                    this.datastores = datastores.map { it.toMutableList() }
                }

                /**
                 * Adds a single [String] to [datastores].
                 *
                 * @throws IllegalStateException if the field was previously set to a non-list.
                 */
                fun addDatastore(datastore: String) = apply {
                    datastores =
                        (datastores ?: JsonField.of(mutableListOf())).also {
                            checkKnown("datastores", it).add(datastore)
                        }
                }

                /** Optional. Used to specify Vertex AI Search engine. */
                fun engine(engine: String) = engine(JsonField.of(engine))

                /**
                 * Sets [Builder.engine] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.engine] with a well-typed [String] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun engine(engine: JsonField<String>) = apply { this.engine = engine }

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
                 * Returns an immutable instance of [VertexAiSearchConfig].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 */
                fun build(): VertexAiSearchConfig =
                    VertexAiSearchConfig(
                        (datastores ?: JsonMissing.of()).map { it.toImmutable() },
                        engine,
                        additionalProperties.toMutableMap(),
                    )
            }

            private var validated: Boolean = false

            fun validate(): VertexAiSearchConfig = apply {
                if (validated) {
                    return@apply
                }

                datastores()
                engine()
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
                (datastores.asKnown().getOrNull()?.size ?: 0) +
                    (if (engine.asKnown().isPresent) 1 else 0)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is VertexAiSearchConfig &&
                    datastores == other.datastores &&
                    engine == other.engine &&
                    additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(datastores, engine, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "VertexAiSearchConfig{datastores=$datastores, engine=$engine, additionalProperties=$additionalProperties}"
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Retrieval &&
                type == other.type &&
                retrievalTypes == other.retrievalTypes &&
                vertexAiSearchConfig == other.vertexAiSearchConfig &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(type, retrievalTypes, vertexAiSearchConfig, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Retrieval{type=$type, retrievalTypes=$retrievalTypes, vertexAiSearchConfig=$vertexAiSearchConfig, additionalProperties=$additionalProperties}"
    }
}
