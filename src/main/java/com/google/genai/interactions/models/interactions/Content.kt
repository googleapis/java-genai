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

    fun isText(): Boolean = text != null

    fun isImage(): Boolean = image != null

    fun isAudio(): Boolean = audio != null

    fun isDocument(): Boolean = document != null

    fun isVideo(): Boolean = video != null

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

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T =
        when {
            text != null -> visitor.visitText(text)
            image != null -> visitor.visitImage(image)
            audio != null -> visitor.visitAudio(audio)
            document != null -> visitor.visitDocument(document)
            video != null -> visitor.visitVideo(video)
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
            video == other.video
    }

    override fun hashCode(): Int = Objects.hash(text, image, audio, document, video)

    override fun toString(): String =
        when {
            text != null -> "Content{text=$text}"
            image != null -> "Content{image=$image}"
            audio != null -> "Content{audio=$audio}"
            document != null -> "Content{document=$document}"
            video != null -> "Content{video=$video}"
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
                    return tryDeserialize(node, object : TypeReference<TextContent>() {})?.let {
                        Content(text = it, _json = json)
                    } ?: Content(_json = json)
                }
                "image" -> {
                    return tryDeserialize(node, object : TypeReference<ImageContent>() {})?.let {
                        Content(image = it, _json = json)
                    } ?: Content(_json = json)
                }
                "audio" -> {
                    return tryDeserialize(node, object : TypeReference<AudioContent>() {})?.let {
                        Content(audio = it, _json = json)
                    } ?: Content(_json = json)
                }
                "document" -> {
                    return tryDeserialize(node, object : TypeReference<DocumentContent>() {})?.let {
                        Content(document = it, _json = json)
                    } ?: Content(_json = json)
                }
                "video" -> {
                    return tryDeserialize(node, object : TypeReference<VideoContent>() {})?.let {
                        Content(video = it, _json = json)
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
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid Content")
            }
        }
    }
}
