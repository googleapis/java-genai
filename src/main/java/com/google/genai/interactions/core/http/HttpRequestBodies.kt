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

@file:JvmName("HttpRequestBodies")

package com.google.genai.interactions.core.http

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.databind.node.JsonNodeType
import com.google.genai.interactions.core.MultipartField
import com.google.genai.interactions.core.toImmutable
import com.google.genai.interactions.errors.GeminiNextGenApiInvalidDataException
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.io.OutputStream
import java.util.UUID
import kotlin.jvm.optionals.getOrNull

@JvmSynthetic
internal inline fun <reified T> json(jsonMapper: JsonMapper, value: T): HttpRequestBody =
    object : HttpRequestBody {
        private val bytes: ByteArray by lazy { jsonMapper.writeValueAsBytes(value) }

        override fun writeTo(outputStream: OutputStream) = outputStream.write(bytes)

        override fun contentType(): String = "application/json"

        override fun contentLength(): Long = bytes.size.toLong()

        override fun repeatable(): Boolean = true

        override fun close() {}
    }

@JvmSynthetic
internal fun multipartFormData(
    jsonMapper: JsonMapper,
    fields: Map<String, MultipartField<*>>,
): HttpRequestBody =
    MultipartBody.Builder()
        .apply {
            fields.forEach { (name, field) ->
                val knownValue = field.value.asKnown().getOrNull()
                val parts =
                    if (knownValue is InputStream) {
                        // Read directly from the `InputStream` instead of reading it all
                        // into memory due to the `jsonMapper` serialization below.
                        sequenceOf(name to knownValue)
                    } else {
                        val node = jsonMapper.valueToTree<JsonNode>(field.value)
                        serializePart(name, node)
                    }

                parts.forEach { (name, bytes) ->
                    val partBody =
                        if (bytes is ByteArrayInputStream) {
                            val byteArray = bytes.readBytes()

                            object : HttpRequestBody {

                                override fun writeTo(outputStream: OutputStream) {
                                    outputStream.write(byteArray)
                                }

                                override fun contentType(): String = field.contentType

                                override fun contentLength(): Long = byteArray.size.toLong()

                                override fun repeatable(): Boolean = true

                                override fun close() {}
                            }
                        } else {
                            object : HttpRequestBody {

                                override fun writeTo(outputStream: OutputStream) {
                                    bytes.copyTo(outputStream)
                                }

                                override fun contentType(): String = field.contentType

                                override fun contentLength(): Long = -1L

                                override fun repeatable(): Boolean = false

                                override fun close() = bytes.close()
                            }
                        }

                    addPart(
                        MultipartBody.Part.create(
                            name,
                            field.filename().getOrNull(),
                            field.contentType,
                            partBody,
                        )
                    )
                }
            }
        }
        .build()

private fun serializePart(name: String, node: JsonNode): Sequence<Pair<String, InputStream>> =
    when (node.nodeType) {
        JsonNodeType.MISSING,
        JsonNodeType.NULL -> emptySequence()
        JsonNodeType.BINARY -> sequenceOf(name to node.binaryValue().inputStream())
        JsonNodeType.STRING -> sequenceOf(name to node.textValue().byteInputStream())
        JsonNodeType.BOOLEAN -> sequenceOf(name to node.booleanValue().toString().byteInputStream())
        JsonNodeType.NUMBER -> sequenceOf(name to node.numberValue().toString().byteInputStream())
        JsonNodeType.ARRAY ->
            sequenceOf(
                name to
                    node
                        .elements()
                        .asSequence()
                        .mapNotNull { element ->
                            when (element.nodeType) {
                                JsonNodeType.MISSING,
                                JsonNodeType.NULL -> null
                                JsonNodeType.STRING -> element.textValue()
                                JsonNodeType.BOOLEAN -> element.booleanValue().toString()
                                JsonNodeType.NUMBER -> element.numberValue().toString()
                                null,
                                JsonNodeType.BINARY,
                                JsonNodeType.ARRAY,
                                JsonNodeType.OBJECT,
                                JsonNodeType.POJO ->
                                    throw GeminiNextGenApiInvalidDataException(
                                        "Unexpected JsonNode type in array: ${element.nodeType}"
                                    )
                            }
                        }
                        .joinToString(",")
                        .byteInputStream()
            )
        JsonNodeType.OBJECT ->
            node.fields().asSequence().flatMap { (key, value) ->
                serializePart("$name[$key]", value)
            }
        JsonNodeType.POJO,
        null ->
            throw GeminiNextGenApiInvalidDataException("Unexpected JsonNode type: ${node.nodeType}")
    }

private class MultipartBody
private constructor(private val boundary: String, private val parts: List<Part>) : HttpRequestBody {
    private val boundaryBytes: ByteArray = boundary.toByteArray()
    private val contentType = "multipart/form-data; boundary=$boundary"

    // This must remain in sync with `contentLength`.
    override fun writeTo(outputStream: OutputStream) {
        parts.forEach { part ->
            outputStream.write(DASHDASH)
            outputStream.write(boundaryBytes)
            outputStream.write(CRLF)

            outputStream.write(CONTENT_DISPOSITION)
            outputStream.write(part.contentDisposition.toByteArray())
            outputStream.write(CRLF)

            outputStream.write(CONTENT_TYPE)
            outputStream.write(part.contentType.toByteArray())
            outputStream.write(CRLF)

            outputStream.write(CRLF)
            part.body.writeTo(outputStream)
            outputStream.write(CRLF)
        }

        outputStream.write(DASHDASH)
        outputStream.write(boundaryBytes)
        outputStream.write(DASHDASH)
        outputStream.write(CRLF)
    }

    override fun contentType(): String = contentType

    // This must remain in sync with `writeTo`.
    override fun contentLength(): Long {
        var byteCount = 0L

        parts.forEach { part ->
            val contentLength = part.body.contentLength()
            if (contentLength == -1L) {
                return -1L
            }

            byteCount +=
                DASHDASH.size +
                    boundaryBytes.size +
                    CRLF.size +
                    CONTENT_DISPOSITION.size +
                    part.contentDisposition.toByteArray().size +
                    CRLF.size +
                    CONTENT_TYPE.size +
                    part.contentType.toByteArray().size +
                    CRLF.size +
                    CRLF.size +
                    contentLength +
                    CRLF.size
        }

        byteCount += DASHDASH.size + boundaryBytes.size + DASHDASH.size + CRLF.size
        return byteCount
    }

    override fun repeatable(): Boolean = parts.all { it.body.repeatable() }

    override fun close() {
        parts.forEach { it.body.close() }
    }

    class Builder {
        private val boundary = UUID.randomUUID().toString()
        private val parts: MutableList<Part> = mutableListOf()

        fun addPart(part: Part) = apply { parts.add(part) }

        fun build() = MultipartBody(boundary, parts.toImmutable())
    }

    class Part
    private constructor(
        val contentDisposition: String,
        val contentType: String,
        val body: HttpRequestBody,
    ) {
        companion object {
            fun create(
                name: String,
                filename: String?,
                contentType: String,
                body: HttpRequestBody,
            ): Part {
                val disposition = buildString {
                    append("form-data; name=")
                    appendQuotedString(name)
                    if (filename != null) {
                        append("; filename=")
                        appendQuotedString(filename)
                    }
                }
                return Part(disposition, contentType, body)
            }
        }
    }

    companion object {
        private val CRLF = byteArrayOf('\r'.code.toByte(), '\n'.code.toByte())
        private val DASHDASH = byteArrayOf('-'.code.toByte(), '-'.code.toByte())
        private val CONTENT_DISPOSITION = "Content-Disposition: ".toByteArray()
        private val CONTENT_TYPE = "Content-Type: ".toByteArray()

        private fun StringBuilder.appendQuotedString(key: String) {
            append('"')
            for (ch in key) {
                when (ch) {
                    '\n' -> append("%0A")
                    '\r' -> append("%0D")
                    '"' -> append("%22")
                    else -> append(ch)
                }
            }
            append('"')
        }
    }
}
