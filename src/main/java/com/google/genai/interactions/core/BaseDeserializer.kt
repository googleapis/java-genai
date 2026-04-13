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

package com.google.genai.interactions.core

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.BeanProperty
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JavaType
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.ContextualDeserializer
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import kotlin.reflect.KClass

abstract class BaseDeserializer<T : Any>(type: KClass<T>) :
    StdDeserializer<T>(type.java), ContextualDeserializer {

    override fun createContextual(
        context: DeserializationContext,
        property: BeanProperty?,
    ): JsonDeserializer<T> {
        return this
    }

    override fun deserialize(parser: JsonParser, context: DeserializationContext): T {
        return parser.codec.deserialize(parser.readValueAsTree())
    }

    protected abstract fun ObjectCodec.deserialize(node: JsonNode): T

    protected fun <T> ObjectCodec.tryDeserialize(node: JsonNode, type: TypeReference<T>): T? =
        try {
            readValue(treeAsTokens(node), type)
        } catch (e: Exception) {
            null
        }

    protected fun <T> ObjectCodec.tryDeserialize(node: JsonNode, type: JavaType): T? =
        try {
            readValue(treeAsTokens(node), type)
        } catch (e: Exception) {
            null
        }
}
