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
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.google.genai.interactions.core.BaseDeserializer
import com.google.genai.interactions.core.BaseSerializer
import com.google.genai.interactions.core.JsonValue
import com.google.genai.interactions.core.getOrThrow
import com.google.genai.interactions.errors.GeminiNextGenApiInvalidDataException
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** The content of the response. */
@JsonDeserialize(using = Content.Deserializer::class)
@JsonSerialize(using = Content.Serializer::class)
class Content
private constructor(
    private val text: TextContent? = null,
    private val image: ImageContent? = null,
    private val audio: AudioContent? = null,
    private val document: DocumentContent? = null,
    private val video: VideoContent? = null,
    private val thought: ThoughtContent? = null,
    private val functionCall: FunctionCallContent? = null,
    private val codeExecutionCall: CodeExecutionCallContent? = null,
    private val urlContextCall: UrlContextCallContent? = null,
    private val mcpServerToolCall: McpServerToolCallContent? = null,
    private val googleSearchCall: GoogleSearchCallContent? = null,
    private val fileSearchCall: FileSearchCallContent? = null,
    private val googleMapsCall: GoogleMapsCallContent? = null,
    private val functionResult: FunctionResultContent? = null,
    private val codeExecutionResult: CodeExecutionResultContent? = null,
    private val urlContextResult: UrlContextResultContent? = null,
    private val googleSearchResult: GoogleSearchResultContent? = null,
    private val mcpServerToolResult: McpServerToolResultContent? = null,
    private val fileSearchResult: FileSearchResultContent? = null,
    private val googleMapsResult: GoogleMapsResultContent? = null,
    private val _json: JsonValue? = null,
) {

    /** A text content block. */
    fun text(): Optional<TextContent> = Optional.ofNullable(text)

    /** An image content block. */
    fun image(): Optional<ImageContent> = Optional.ofNullable(image)

    /** An audio content block. */
    fun audio(): Optional<AudioContent> = Optional.ofNullable(audio)

    /** A document content block. */
    fun document(): Optional<DocumentContent> = Optional.ofNullable(document)

    /** A video content block. */
    fun video(): Optional<VideoContent> = Optional.ofNullable(video)

    /** A thought content block. */
    fun thought(): Optional<ThoughtContent> = Optional.ofNullable(thought)

    /** A function tool call content block. */
    fun functionCall(): Optional<FunctionCallContent> = Optional.ofNullable(functionCall)

    /** Code execution content. */
    fun codeExecutionCall(): Optional<CodeExecutionCallContent> =
        Optional.ofNullable(codeExecutionCall)

    /** URL context content. */
    fun urlContextCall(): Optional<UrlContextCallContent> = Optional.ofNullable(urlContextCall)

    /** MCPServer tool call content. */
    fun mcpServerToolCall(): Optional<McpServerToolCallContent> =
        Optional.ofNullable(mcpServerToolCall)

    /** Google Search content. */
    fun googleSearchCall(): Optional<GoogleSearchCallContent> =
        Optional.ofNullable(googleSearchCall)

    /** File Search content. */
    fun fileSearchCall(): Optional<FileSearchCallContent> = Optional.ofNullable(fileSearchCall)

    /** Google Maps content. */
    fun googleMapsCall(): Optional<GoogleMapsCallContent> = Optional.ofNullable(googleMapsCall)

    /** A function tool result content block. */
    fun functionResult(): Optional<FunctionResultContent> = Optional.ofNullable(functionResult)

    /** Code execution result content. */
    fun codeExecutionResult(): Optional<CodeExecutionResultContent> =
        Optional.ofNullable(codeExecutionResult)

    /** URL context result content. */
    fun urlContextResult(): Optional<UrlContextResultContent> =
        Optional.ofNullable(urlContextResult)

    /** Google Search result content. */
    fun googleSearchResult(): Optional<GoogleSearchResultContent> =
        Optional.ofNullable(googleSearchResult)

    /** MCPServer tool result content. */
    fun mcpServerToolResult(): Optional<McpServerToolResultContent> =
        Optional.ofNullable(mcpServerToolResult)

    /** File Search result content. */
    fun fileSearchResult(): Optional<FileSearchResultContent> =
        Optional.ofNullable(fileSearchResult)

    /** Google Maps result content. */
    fun googleMapsResult(): Optional<GoogleMapsResultContent> =
        Optional.ofNullable(googleMapsResult)

    fun isText(): Boolean = text != null

    fun isImage(): Boolean = image != null

    fun isAudio(): Boolean = audio != null

    fun isDocument(): Boolean = document != null

    fun isVideo(): Boolean = video != null

    fun isThought(): Boolean = thought != null

    fun isFunctionCall(): Boolean = functionCall != null

    fun isCodeExecutionCall(): Boolean = codeExecutionCall != null

    fun isUrlContextCall(): Boolean = urlContextCall != null

    fun isMcpServerToolCall(): Boolean = mcpServerToolCall != null

    fun isGoogleSearchCall(): Boolean = googleSearchCall != null

    fun isFileSearchCall(): Boolean = fileSearchCall != null

    fun isGoogleMapsCall(): Boolean = googleMapsCall != null

    fun isFunctionResult(): Boolean = functionResult != null

    fun isCodeExecutionResult(): Boolean = codeExecutionResult != null

    fun isUrlContextResult(): Boolean = urlContextResult != null

    fun isGoogleSearchResult(): Boolean = googleSearchResult != null

    fun isMcpServerToolResult(): Boolean = mcpServerToolResult != null

    fun isFileSearchResult(): Boolean = fileSearchResult != null

    fun isGoogleMapsResult(): Boolean = googleMapsResult != null

    /** A text content block. */
    fun asText(): TextContent = text.getOrThrow("text")

    /** An image content block. */
    fun asImage(): ImageContent = image.getOrThrow("image")

    /** An audio content block. */
    fun asAudio(): AudioContent = audio.getOrThrow("audio")

    /** A document content block. */
    fun asDocument(): DocumentContent = document.getOrThrow("document")

    /** A video content block. */
    fun asVideo(): VideoContent = video.getOrThrow("video")

    /** A thought content block. */
    fun asThought(): ThoughtContent = thought.getOrThrow("thought")

    /** A function tool call content block. */
    fun asFunctionCall(): FunctionCallContent = functionCall.getOrThrow("functionCall")

    /** Code execution content. */
    fun asCodeExecutionCall(): CodeExecutionCallContent =
        codeExecutionCall.getOrThrow("codeExecutionCall")

    /** URL context content. */
    fun asUrlContextCall(): UrlContextCallContent = urlContextCall.getOrThrow("urlContextCall")

    /** MCPServer tool call content. */
    fun asMcpServerToolCall(): McpServerToolCallContent =
        mcpServerToolCall.getOrThrow("mcpServerToolCall")

    /** Google Search content. */
    fun asGoogleSearchCall(): GoogleSearchCallContent =
        googleSearchCall.getOrThrow("googleSearchCall")

    /** File Search content. */
    fun asFileSearchCall(): FileSearchCallContent = fileSearchCall.getOrThrow("fileSearchCall")

    /** Google Maps content. */
    fun asGoogleMapsCall(): GoogleMapsCallContent = googleMapsCall.getOrThrow("googleMapsCall")

    /** A function tool result content block. */
    fun asFunctionResult(): FunctionResultContent = functionResult.getOrThrow("functionResult")

    /** Code execution result content. */
    fun asCodeExecutionResult(): CodeExecutionResultContent =
        codeExecutionResult.getOrThrow("codeExecutionResult")

    /** URL context result content. */
    fun asUrlContextResult(): UrlContextResultContent =
        urlContextResult.getOrThrow("urlContextResult")

    /** Google Search result content. */
    fun asGoogleSearchResult(): GoogleSearchResultContent =
        googleSearchResult.getOrThrow("googleSearchResult")

    /** MCPServer tool result content. */
    fun asMcpServerToolResult(): McpServerToolResultContent =
        mcpServerToolResult.getOrThrow("mcpServerToolResult")

    /** File Search result content. */
    fun asFileSearchResult(): FileSearchResultContent =
        fileSearchResult.getOrThrow("fileSearchResult")

    /** Google Maps result content. */
    fun asGoogleMapsResult(): GoogleMapsResultContent =
        googleMapsResult.getOrThrow("googleMapsResult")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T =
        when {
            text != null -> visitor.visitText(text)
            image != null -> visitor.visitImage(image)
            audio != null -> visitor.visitAudio(audio)
            document != null -> visitor.visitDocument(document)
            video != null -> visitor.visitVideo(video)
            thought != null -> visitor.visitThought(thought)
            functionCall != null -> visitor.visitFunctionCall(functionCall)
            codeExecutionCall != null -> visitor.visitCodeExecutionCall(codeExecutionCall)
            urlContextCall != null -> visitor.visitUrlContextCall(urlContextCall)
            mcpServerToolCall != null -> visitor.visitMcpServerToolCall(mcpServerToolCall)
            googleSearchCall != null -> visitor.visitGoogleSearchCall(googleSearchCall)
            fileSearchCall != null -> visitor.visitFileSearchCall(fileSearchCall)
            googleMapsCall != null -> visitor.visitGoogleMapsCall(googleMapsCall)
            functionResult != null -> visitor.visitFunctionResult(functionResult)
            codeExecutionResult != null -> visitor.visitCodeExecutionResult(codeExecutionResult)
            urlContextResult != null -> visitor.visitUrlContextResult(urlContextResult)
            googleSearchResult != null -> visitor.visitGoogleSearchResult(googleSearchResult)
            mcpServerToolResult != null -> visitor.visitMcpServerToolResult(mcpServerToolResult)
            fileSearchResult != null -> visitor.visitFileSearchResult(fileSearchResult)
            googleMapsResult != null -> visitor.visitGoogleMapsResult(googleMapsResult)
            else -> visitor.unknown(_json)
        }

    private var validated: Boolean = false

    fun validate(): Content = apply {
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

                override fun visitAudio(audio: AudioContent) {
                    audio.validate()
                }

                override fun visitDocument(document: DocumentContent) {
                    document.validate()
                }

                override fun visitVideo(video: VideoContent) {
                    video.validate()
                }

                override fun visitThought(thought: ThoughtContent) {
                    thought.validate()
                }

                override fun visitFunctionCall(functionCall: FunctionCallContent) {
                    functionCall.validate()
                }

                override fun visitCodeExecutionCall(codeExecutionCall: CodeExecutionCallContent) {
                    codeExecutionCall.validate()
                }

                override fun visitUrlContextCall(urlContextCall: UrlContextCallContent) {
                    urlContextCall.validate()
                }

                override fun visitMcpServerToolCall(mcpServerToolCall: McpServerToolCallContent) {
                    mcpServerToolCall.validate()
                }

                override fun visitGoogleSearchCall(googleSearchCall: GoogleSearchCallContent) {
                    googleSearchCall.validate()
                }

                override fun visitFileSearchCall(fileSearchCall: FileSearchCallContent) {
                    fileSearchCall.validate()
                }

                override fun visitGoogleMapsCall(googleMapsCall: GoogleMapsCallContent) {
                    googleMapsCall.validate()
                }

                override fun visitFunctionResult(functionResult: FunctionResultContent) {
                    functionResult.validate()
                }

                override fun visitCodeExecutionResult(
                    codeExecutionResult: CodeExecutionResultContent
                ) {
                    codeExecutionResult.validate()
                }

                override fun visitUrlContextResult(urlContextResult: UrlContextResultContent) {
                    urlContextResult.validate()
                }

                override fun visitGoogleSearchResult(
                    googleSearchResult: GoogleSearchResultContent
                ) {
                    googleSearchResult.validate()
                }

                override fun visitMcpServerToolResult(
                    mcpServerToolResult: McpServerToolResultContent
                ) {
                    mcpServerToolResult.validate()
                }

                override fun visitFileSearchResult(fileSearchResult: FileSearchResultContent) {
                    fileSearchResult.validate()
                }

                override fun visitGoogleMapsResult(googleMapsResult: GoogleMapsResultContent) {
                    googleMapsResult.validate()
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
                override fun visitText(text: TextContent) = text.validity()

                override fun visitImage(image: ImageContent) = image.validity()

                override fun visitAudio(audio: AudioContent) = audio.validity()

                override fun visitDocument(document: DocumentContent) = document.validity()

                override fun visitVideo(video: VideoContent) = video.validity()

                override fun visitThought(thought: ThoughtContent) = thought.validity()

                override fun visitFunctionCall(functionCall: FunctionCallContent) =
                    functionCall.validity()

                override fun visitCodeExecutionCall(codeExecutionCall: CodeExecutionCallContent) =
                    codeExecutionCall.validity()

                override fun visitUrlContextCall(urlContextCall: UrlContextCallContent) =
                    urlContextCall.validity()

                override fun visitMcpServerToolCall(mcpServerToolCall: McpServerToolCallContent) =
                    mcpServerToolCall.validity()

                override fun visitGoogleSearchCall(googleSearchCall: GoogleSearchCallContent) =
                    googleSearchCall.validity()

                override fun visitFileSearchCall(fileSearchCall: FileSearchCallContent) =
                    fileSearchCall.validity()

                override fun visitGoogleMapsCall(googleMapsCall: GoogleMapsCallContent) =
                    googleMapsCall.validity()

                override fun visitFunctionResult(functionResult: FunctionResultContent) =
                    functionResult.validity()

                override fun visitCodeExecutionResult(
                    codeExecutionResult: CodeExecutionResultContent
                ) = codeExecutionResult.validity()

                override fun visitUrlContextResult(urlContextResult: UrlContextResultContent) =
                    urlContextResult.validity()

                override fun visitGoogleSearchResult(
                    googleSearchResult: GoogleSearchResultContent
                ) = googleSearchResult.validity()

                override fun visitMcpServerToolResult(
                    mcpServerToolResult: McpServerToolResultContent
                ) = mcpServerToolResult.validity()

                override fun visitFileSearchResult(fileSearchResult: FileSearchResultContent) =
                    fileSearchResult.validity()

                override fun visitGoogleMapsResult(googleMapsResult: GoogleMapsResultContent) =
                    googleMapsResult.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is Content &&
            text == other.text &&
            image == other.image &&
            audio == other.audio &&
            document == other.document &&
            video == other.video &&
            thought == other.thought &&
            functionCall == other.functionCall &&
            codeExecutionCall == other.codeExecutionCall &&
            urlContextCall == other.urlContextCall &&
            mcpServerToolCall == other.mcpServerToolCall &&
            googleSearchCall == other.googleSearchCall &&
            fileSearchCall == other.fileSearchCall &&
            googleMapsCall == other.googleMapsCall &&
            functionResult == other.functionResult &&
            codeExecutionResult == other.codeExecutionResult &&
            urlContextResult == other.urlContextResult &&
            googleSearchResult == other.googleSearchResult &&
            mcpServerToolResult == other.mcpServerToolResult &&
            fileSearchResult == other.fileSearchResult &&
            googleMapsResult == other.googleMapsResult
    }

    override fun hashCode(): Int =
        Objects.hash(
            text,
            image,
            audio,
            document,
            video,
            thought,
            functionCall,
            codeExecutionCall,
            urlContextCall,
            mcpServerToolCall,
            googleSearchCall,
            fileSearchCall,
            googleMapsCall,
            functionResult,
            codeExecutionResult,
            urlContextResult,
            googleSearchResult,
            mcpServerToolResult,
            fileSearchResult,
            googleMapsResult,
        )

    override fun toString(): String =
        when {
            text != null -> "Content{text=$text}"
            image != null -> "Content{image=$image}"
            audio != null -> "Content{audio=$audio}"
            document != null -> "Content{document=$document}"
            video != null -> "Content{video=$video}"
            thought != null -> "Content{thought=$thought}"
            functionCall != null -> "Content{functionCall=$functionCall}"
            codeExecutionCall != null -> "Content{codeExecutionCall=$codeExecutionCall}"
            urlContextCall != null -> "Content{urlContextCall=$urlContextCall}"
            mcpServerToolCall != null -> "Content{mcpServerToolCall=$mcpServerToolCall}"
            googleSearchCall != null -> "Content{googleSearchCall=$googleSearchCall}"
            fileSearchCall != null -> "Content{fileSearchCall=$fileSearchCall}"
            googleMapsCall != null -> "Content{googleMapsCall=$googleMapsCall}"
            functionResult != null -> "Content{functionResult=$functionResult}"
            codeExecutionResult != null -> "Content{codeExecutionResult=$codeExecutionResult}"
            urlContextResult != null -> "Content{urlContextResult=$urlContextResult}"
            googleSearchResult != null -> "Content{googleSearchResult=$googleSearchResult}"
            mcpServerToolResult != null -> "Content{mcpServerToolResult=$mcpServerToolResult}"
            fileSearchResult != null -> "Content{fileSearchResult=$fileSearchResult}"
            googleMapsResult != null -> "Content{googleMapsResult=$googleMapsResult}"
            _json != null -> "Content{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid Content")
        }

    companion object {

        /** A text content block. */
        @JvmStatic fun ofText(text: TextContent) = Content(text = text)

        /** An image content block. */
        @JvmStatic fun ofImage(image: ImageContent) = Content(image = image)

        /** An audio content block. */
        @JvmStatic fun ofAudio(audio: AudioContent) = Content(audio = audio)

        /** A document content block. */
        @JvmStatic fun ofDocument(document: DocumentContent) = Content(document = document)

        /** A video content block. */
        @JvmStatic fun ofVideo(video: VideoContent) = Content(video = video)

        /** A thought content block. */
        @JvmStatic fun ofThought(thought: ThoughtContent) = Content(thought = thought)

        /** A function tool call content block. */
        @JvmStatic
        fun ofFunctionCall(functionCall: FunctionCallContent) = Content(functionCall = functionCall)

        /** Code execution content. */
        @JvmStatic
        fun ofCodeExecutionCall(codeExecutionCall: CodeExecutionCallContent) =
            Content(codeExecutionCall = codeExecutionCall)

        /** URL context content. */
        @JvmStatic
        fun ofUrlContextCall(urlContextCall: UrlContextCallContent) =
            Content(urlContextCall = urlContextCall)

        /** MCPServer tool call content. */
        @JvmStatic
        fun ofMcpServerToolCall(mcpServerToolCall: McpServerToolCallContent) =
            Content(mcpServerToolCall = mcpServerToolCall)

        /** Google Search content. */
        @JvmStatic
        fun ofGoogleSearchCall(googleSearchCall: GoogleSearchCallContent) =
            Content(googleSearchCall = googleSearchCall)

        /** File Search content. */
        @JvmStatic
        fun ofFileSearchCall(fileSearchCall: FileSearchCallContent) =
            Content(fileSearchCall = fileSearchCall)

        /** Google Maps content. */
        @JvmStatic
        fun ofGoogleMapsCall(googleMapsCall: GoogleMapsCallContent) =
            Content(googleMapsCall = googleMapsCall)

        /** A function tool result content block. */
        @JvmStatic
        fun ofFunctionResult(functionResult: FunctionResultContent) =
            Content(functionResult = functionResult)

        /** Code execution result content. */
        @JvmStatic
        fun ofCodeExecutionResult(codeExecutionResult: CodeExecutionResultContent) =
            Content(codeExecutionResult = codeExecutionResult)

        /** URL context result content. */
        @JvmStatic
        fun ofUrlContextResult(urlContextResult: UrlContextResultContent) =
            Content(urlContextResult = urlContextResult)

        /** Google Search result content. */
        @JvmStatic
        fun ofGoogleSearchResult(googleSearchResult: GoogleSearchResultContent) =
            Content(googleSearchResult = googleSearchResult)

        /** MCPServer tool result content. */
        @JvmStatic
        fun ofMcpServerToolResult(mcpServerToolResult: McpServerToolResultContent) =
            Content(mcpServerToolResult = mcpServerToolResult)

        /** File Search result content. */
        @JvmStatic
        fun ofFileSearchResult(fileSearchResult: FileSearchResultContent) =
            Content(fileSearchResult = fileSearchResult)

        /** Google Maps result content. */
        @JvmStatic
        fun ofGoogleMapsResult(googleMapsResult: GoogleMapsResultContent) =
            Content(googleMapsResult = googleMapsResult)
    }

    /** An interface that defines how to map each variant of [Content] to a value of type [T]. */
    interface Visitor<out T> {

        /** A text content block. */
        fun visitText(text: TextContent): T

        /** An image content block. */
        fun visitImage(image: ImageContent): T

        /** An audio content block. */
        fun visitAudio(audio: AudioContent): T

        /** A document content block. */
        fun visitDocument(document: DocumentContent): T

        /** A video content block. */
        fun visitVideo(video: VideoContent): T

        /** A thought content block. */
        fun visitThought(thought: ThoughtContent): T

        /** A function tool call content block. */
        fun visitFunctionCall(functionCall: FunctionCallContent): T

        /** Code execution content. */
        fun visitCodeExecutionCall(codeExecutionCall: CodeExecutionCallContent): T

        /** URL context content. */
        fun visitUrlContextCall(urlContextCall: UrlContextCallContent): T

        /** MCPServer tool call content. */
        fun visitMcpServerToolCall(mcpServerToolCall: McpServerToolCallContent): T

        /** Google Search content. */
        fun visitGoogleSearchCall(googleSearchCall: GoogleSearchCallContent): T

        /** File Search content. */
        fun visitFileSearchCall(fileSearchCall: FileSearchCallContent): T

        /** Google Maps content. */
        fun visitGoogleMapsCall(googleMapsCall: GoogleMapsCallContent): T

        /** A function tool result content block. */
        fun visitFunctionResult(functionResult: FunctionResultContent): T

        /** Code execution result content. */
        fun visitCodeExecutionResult(codeExecutionResult: CodeExecutionResultContent): T

        /** URL context result content. */
        fun visitUrlContextResult(urlContextResult: UrlContextResultContent): T

        /** Google Search result content. */
        fun visitGoogleSearchResult(googleSearchResult: GoogleSearchResultContent): T

        /** MCPServer tool result content. */
        fun visitMcpServerToolResult(mcpServerToolResult: McpServerToolResultContent): T

        /** File Search result content. */
        fun visitFileSearchResult(fileSearchResult: FileSearchResultContent): T

        /** Google Maps result content. */
        fun visitGoogleMapsResult(googleMapsResult: GoogleMapsResultContent): T

        /**
         * Maps an unknown variant of [Content] to a value of type [T].
         *
         * An instance of [Content] can contain an unknown variant if it was deserialized from data
         * that doesn't match any known variant. For example, if the SDK is on an older version than
         * the API, then the API may respond with new variants that the SDK is unaware of.
         *
         * @throws GeminiNextGenApiInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw GeminiNextGenApiInvalidDataException("Unknown Content: $json")
        }
    }

    internal class Deserializer : BaseDeserializer<Content>(Content::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): Content {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "text" -> {
                    return tryDeserialize(node, jacksonTypeRef<TextContent>())?.let {
                        Content(text = it, _json = json)
                    } ?: Content(_json = json)
                }
                "image" -> {
                    return tryDeserialize(node, jacksonTypeRef<ImageContent>())?.let {
                        Content(image = it, _json = json)
                    } ?: Content(_json = json)
                }
                "audio" -> {
                    return tryDeserialize(node, jacksonTypeRef<AudioContent>())?.let {
                        Content(audio = it, _json = json)
                    } ?: Content(_json = json)
                }
                "document" -> {
                    return tryDeserialize(node, jacksonTypeRef<DocumentContent>())?.let {
                        Content(document = it, _json = json)
                    } ?: Content(_json = json)
                }
                "video" -> {
                    return tryDeserialize(node, jacksonTypeRef<VideoContent>())?.let {
                        Content(video = it, _json = json)
                    } ?: Content(_json = json)
                }
                "thought" -> {
                    return tryDeserialize(node, jacksonTypeRef<ThoughtContent>())?.let {
                        Content(thought = it, _json = json)
                    } ?: Content(_json = json)
                }
                "function_call" -> {
                    return tryDeserialize(node, jacksonTypeRef<FunctionCallContent>())?.let {
                        Content(functionCall = it, _json = json)
                    } ?: Content(_json = json)
                }
                "code_execution_call" -> {
                    return tryDeserialize(node, jacksonTypeRef<CodeExecutionCallContent>())?.let {
                        Content(codeExecutionCall = it, _json = json)
                    } ?: Content(_json = json)
                }
                "url_context_call" -> {
                    return tryDeserialize(node, jacksonTypeRef<UrlContextCallContent>())?.let {
                        Content(urlContextCall = it, _json = json)
                    } ?: Content(_json = json)
                }
                "mcp_server_tool_call" -> {
                    return tryDeserialize(node, jacksonTypeRef<McpServerToolCallContent>())?.let {
                        Content(mcpServerToolCall = it, _json = json)
                    } ?: Content(_json = json)
                }
                "google_search_call" -> {
                    return tryDeserialize(node, jacksonTypeRef<GoogleSearchCallContent>())?.let {
                        Content(googleSearchCall = it, _json = json)
                    } ?: Content(_json = json)
                }
                "file_search_call" -> {
                    return tryDeserialize(node, jacksonTypeRef<FileSearchCallContent>())?.let {
                        Content(fileSearchCall = it, _json = json)
                    } ?: Content(_json = json)
                }
                "google_maps_call" -> {
                    return tryDeserialize(node, jacksonTypeRef<GoogleMapsCallContent>())?.let {
                        Content(googleMapsCall = it, _json = json)
                    } ?: Content(_json = json)
                }
                "function_result" -> {
                    return tryDeserialize(node, jacksonTypeRef<FunctionResultContent>())?.let {
                        Content(functionResult = it, _json = json)
                    } ?: Content(_json = json)
                }
                "code_execution_result" -> {
                    return tryDeserialize(node, jacksonTypeRef<CodeExecutionResultContent>())?.let {
                        Content(codeExecutionResult = it, _json = json)
                    } ?: Content(_json = json)
                }
                "url_context_result" -> {
                    return tryDeserialize(node, jacksonTypeRef<UrlContextResultContent>())?.let {
                        Content(urlContextResult = it, _json = json)
                    } ?: Content(_json = json)
                }
                "google_search_result" -> {
                    return tryDeserialize(node, jacksonTypeRef<GoogleSearchResultContent>())?.let {
                        Content(googleSearchResult = it, _json = json)
                    } ?: Content(_json = json)
                }
                "mcp_server_tool_result" -> {
                    return tryDeserialize(node, jacksonTypeRef<McpServerToolResultContent>())?.let {
                        Content(mcpServerToolResult = it, _json = json)
                    } ?: Content(_json = json)
                }
                "file_search_result" -> {
                    return tryDeserialize(node, jacksonTypeRef<FileSearchResultContent>())?.let {
                        Content(fileSearchResult = it, _json = json)
                    } ?: Content(_json = json)
                }
                "google_maps_result" -> {
                    return tryDeserialize(node, jacksonTypeRef<GoogleMapsResultContent>())?.let {
                        Content(googleMapsResult = it, _json = json)
                    } ?: Content(_json = json)
                }
            }

            return Content(_json = json)
        }
    }

    internal class Serializer : BaseSerializer<Content>(Content::class) {

        override fun serialize(
            value: Content,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.text != null -> generator.writeObject(value.text)
                value.image != null -> generator.writeObject(value.image)
                value.audio != null -> generator.writeObject(value.audio)
                value.document != null -> generator.writeObject(value.document)
                value.video != null -> generator.writeObject(value.video)
                value.thought != null -> generator.writeObject(value.thought)
                value.functionCall != null -> generator.writeObject(value.functionCall)
                value.codeExecutionCall != null -> generator.writeObject(value.codeExecutionCall)
                value.urlContextCall != null -> generator.writeObject(value.urlContextCall)
                value.mcpServerToolCall != null -> generator.writeObject(value.mcpServerToolCall)
                value.googleSearchCall != null -> generator.writeObject(value.googleSearchCall)
                value.fileSearchCall != null -> generator.writeObject(value.fileSearchCall)
                value.googleMapsCall != null -> generator.writeObject(value.googleMapsCall)
                value.functionResult != null -> generator.writeObject(value.functionResult)
                value.codeExecutionResult != null ->
                    generator.writeObject(value.codeExecutionResult)
                value.urlContextResult != null -> generator.writeObject(value.urlContextResult)
                value.googleSearchResult != null -> generator.writeObject(value.googleSearchResult)
                value.mcpServerToolResult != null ->
                    generator.writeObject(value.mcpServerToolResult)
                value.fileSearchResult != null -> generator.writeObject(value.fileSearchResult)
                value.googleMapsResult != null -> generator.writeObject(value.googleMapsResult)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid Content")
            }
        }
    }
}
