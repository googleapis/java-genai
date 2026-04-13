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
import com.google.genai.interactions.core.ExcludeMissing
import com.google.genai.interactions.core.JsonField
import com.google.genai.interactions.core.JsonMissing
import com.google.genai.interactions.core.JsonValue
import com.google.genai.interactions.errors.GeminiNextGenApiInvalidDataException
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** The tool choice configuration containing allowed tools. */
class ToolChoiceConfig
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val allowedTools: JsonField<AllowedTools>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("allowed_tools")
        @ExcludeMissing
        allowedTools: JsonField<AllowedTools> = JsonMissing.of()
    ) : this(allowedTools, mutableMapOf())

    /**
     * The allowed tools.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun allowedTools(): Optional<AllowedTools> = allowedTools.getOptional("allowed_tools")

    /**
     * Returns the raw JSON value of [allowedTools].
     *
     * Unlike [allowedTools], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("allowed_tools")
    @ExcludeMissing
    fun _allowedTools(): JsonField<AllowedTools> = allowedTools

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

        /** Returns a mutable builder for constructing an instance of [ToolChoiceConfig]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [ToolChoiceConfig]. */
    class Builder internal constructor() {

        private var allowedTools: JsonField<AllowedTools> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(toolChoiceConfig: ToolChoiceConfig) = apply {
            allowedTools = toolChoiceConfig.allowedTools
            additionalProperties = toolChoiceConfig.additionalProperties.toMutableMap()
        }

        /** The allowed tools. */
        fun allowedTools(allowedTools: AllowedTools) = allowedTools(JsonField.of(allowedTools))

        /**
         * Sets [Builder.allowedTools] to an arbitrary JSON value.
         *
         * You should usually call [Builder.allowedTools] with a well-typed [AllowedTools] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun allowedTools(allowedTools: JsonField<AllowedTools>) = apply {
            this.allowedTools = allowedTools
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
         * Returns an immutable instance of [ToolChoiceConfig].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): ToolChoiceConfig =
            ToolChoiceConfig(allowedTools, additionalProperties.toMutableMap())
    }

    private var validated: Boolean = false

    fun validate(): ToolChoiceConfig = apply {
        if (validated) {
            return@apply
        }

        allowedTools().ifPresent { it.validate() }
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
    internal fun validity(): Int = (allowedTools.asKnown().getOrNull()?.validity() ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ToolChoiceConfig &&
            allowedTools == other.allowedTools &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(allowedTools, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "ToolChoiceConfig{allowedTools=$allowedTools, additionalProperties=$additionalProperties}"
}
