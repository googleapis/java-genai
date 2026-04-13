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
import java.util.TreeMap

class Headers
private constructor(
    private val map: Map<String, List<String>>,
    @get:JvmName("size") val size: Int,
) {

    fun isEmpty(): Boolean = map.isEmpty()

    fun names(): Set<String> = map.keys

    fun values(name: String): List<String> = map[name].orEmpty()

    fun toBuilder(): Builder = Builder().putAll(map)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    class Builder internal constructor() {

        private val map: MutableMap<String, MutableList<String>> =
            TreeMap(String.CASE_INSENSITIVE_ORDER)
        private var size: Int = 0

        fun put(name: String, value: JsonValue): Builder = apply {
            when (value) {
                is JsonMissing,
                is JsonNull -> {}
                is JsonBoolean -> put(name, value.value.toString())
                is JsonNumber -> put(name, value.value.toString())
                is JsonString -> put(name, value.value)
                is JsonArray -> value.values.forEach { put(name, it) }
                is JsonObject ->
                    value.values.forEach { (nestedName, value) -> put("$name.$nestedName", value) }
            }
        }

        fun put(name: String, value: String) = apply {
            map.getOrPut(name) { mutableListOf() }.add(value)
            size++
        }

        fun put(name: String, values: Iterable<String>) = apply { values.forEach { put(name, it) } }

        fun putAll(headers: Map<String, Iterable<String>>) = apply { headers.forEach(::put) }

        fun putAll(headers: Headers) = apply {
            headers.names().forEach { put(it, headers.values(it)) }
        }

        fun replace(name: String, value: String) = apply {
            remove(name)
            put(name, value)
        }

        fun replace(name: String, values: Iterable<String>) = apply {
            remove(name)
            put(name, values)
        }

        fun replaceAll(headers: Map<String, Iterable<String>>) = apply {
            headers.forEach(::replace)
        }

        fun replaceAll(headers: Headers) = apply {
            headers.names().forEach { replace(it, headers.values(it)) }
        }

        fun remove(name: String) = apply { size -= map.remove(name).orEmpty().size }

        fun removeAll(names: Set<String>) = apply { names.forEach(::remove) }

        fun clear() = apply {
            map.clear()
            size = 0
        }

        fun build() =
            Headers(
                map.mapValuesTo(TreeMap(String.CASE_INSENSITIVE_ORDER)) { (_, values) ->
                        values.toImmutable()
                    }
                    .toImmutable(),
                size,
            )
    }

    override fun hashCode(): Int = map.hashCode()

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is Headers && map == other.map
    }

    override fun toString(): String = "Headers{map=$map}"
}
