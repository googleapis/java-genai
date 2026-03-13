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

package com.google.genai.interactions.core.http

import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.core.type.TypeReference
import com.google.genai.interactions.errors.GeminiNextGenApiInvalidDataException
import java.util.Objects

internal class SseMessage
private constructor(
    val jsonMapper: JsonMapper,
    val event: String?,
    val data: String,
    val id: String?,
    val retry: Int?,
) {

    companion object {
        @JvmStatic fun builder() = Builder()
    }

    class Builder internal constructor() {

        private var jsonMapper: JsonMapper? = null
        private var event: String? = null
        private var data: String = ""
        private var id: String? = null
        private var retry: Int? = null

        fun jsonMapper(jsonMapper: JsonMapper) = apply { this.jsonMapper = jsonMapper }

        fun event(event: String?) = apply { this.event = event }

        fun data(data: String) = apply { this.data = data }

        fun id(id: String?) = apply { this.id = id }

        fun retry(retry: Int?) = apply { this.retry = retry }

        fun build(): SseMessage = SseMessage(jsonMapper!!, event, data, id, retry)
    }

    inline fun <reified T> json(): T =
        try {
            jsonMapper.readerFor(object : TypeReference<T>() {}).readValue(jsonNode)
        } catch (e: Exception) {
            throw GeminiNextGenApiInvalidDataException("Error reading response", e)
        }

    private val jsonNode by lazy {
        try {
            jsonMapper.readTree(data)
        } catch (e: Exception) {
            throw GeminiNextGenApiInvalidDataException("Error reading response", e)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is SseMessage &&
            event == other.event &&
            data == other.data &&
            id == other.id &&
            retry == other.retry
    }

    override fun hashCode(): Int = Objects.hash(event, data, id, retry)

    override fun toString(): String = "SseMessage{event=$event, data=$data, id=$id, retry=$retry}"
}
