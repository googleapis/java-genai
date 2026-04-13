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

import com.google.genai.interactions.core.JsonArray
import com.google.genai.interactions.core.JsonBoolean
import com.google.genai.interactions.core.JsonMissing
import com.google.genai.interactions.core.JsonNull
import com.google.genai.interactions.core.JsonNumber
import com.google.genai.interactions.core.JsonObject
import com.google.genai.interactions.core.JsonString
import com.google.genai.interactions.core.JsonValue
import com.google.genai.interactions.core.toImmutable

class QueryParams
private constructor(
    private val map: Map<String, List<String>>,
    @get:JvmName("size") val size: Int,
) {

    fun isEmpty(): Boolean = map.isEmpty()

    fun keys(): Set<String> = map.keys

    fun values(key: String): List<String> = map[key].orEmpty()

    fun toBuilder(): Builder = Builder().putAll(map)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    class Builder internal constructor() {

        private val map: MutableMap<String, MutableList<String>> = mutableMapOf()
        private var size: Int = 0

        fun put(key: String, value: JsonValue): Builder = apply {
            when (value) {
                is JsonMissing,
                is JsonNull -> {}
                is JsonBoolean -> put(key, value.value.toString())
                is JsonNumber -> put(key, value.value.toString())
                is JsonString -> put(key, value.value)
                is JsonArray ->
                    put(
                        key,
                        value.values
                            .asSequence()
                            .mapNotNull {
                                when (it) {
                                    is JsonMissing,
                                    is JsonNull -> null
                                    is JsonBoolean -> it.value.toString()
                                    is JsonNumber -> it.value.toString()
                                    is JsonString -> it.value
                                    is JsonArray,
                                    is JsonObject ->
                                        throw IllegalArgumentException(
                                            "Cannot comma separate non-primitives in query params"
                                        )
                                }
                            }
                            .joinToString(","),
                    )
                is JsonObject ->
                    value.values.forEach { (nestedKey, value) -> put("$key[$nestedKey]", value) }
            }
        }

        fun put(key: String, value: String) = apply {
            map.getOrPut(key) { mutableListOf() }.add(value)
            size++
        }

        fun put(key: String, values: Iterable<String>) = apply { values.forEach { put(key, it) } }

        fun putAll(queryParams: Map<String, Iterable<String>>) = apply {
            queryParams.forEach(::put)
        }

        fun putAll(queryParams: QueryParams) = apply {
            queryParams.keys().forEach { put(it, queryParams.values(it)) }
        }

        fun replace(key: String, value: String) = apply {
            remove(key)
            put(key, value)
        }

        fun replace(key: String, values: Iterable<String>) = apply {
            remove(key)
            put(key, values)
        }

        fun replaceAll(queryParams: Map<String, Iterable<String>>) = apply {
            queryParams.forEach(::replace)
        }

        fun replaceAll(queryParams: QueryParams) = apply {
            queryParams.keys().forEach { replace(it, queryParams.values(it)) }
        }

        fun remove(key: String) = apply { size -= map.remove(key).orEmpty().size }

        fun removeAll(keys: Set<String>) = apply { keys.forEach(::remove) }

        fun clear() = apply {
            map.clear()
            size = 0
        }

        fun build() =
            QueryParams(map.mapValues { (_, values) -> values.toImmutable() }.toImmutable(), size)
    }

    override fun hashCode(): Int = map.hashCode()

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is QueryParams && map == other.map
    }

    override fun toString(): String = "QueryParams{map=$map}"
}
