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
import com.google.genai.interactions.core.Enum
import com.google.genai.interactions.core.ExcludeMissing
import com.google.genai.interactions.core.JsonField
import com.google.genai.interactions.core.JsonMissing
import com.google.genai.interactions.core.JsonValue
import com.google.genai.interactions.core.checkKnown
import com.google.genai.interactions.core.toImmutable
import com.google.genai.interactions.errors.GeminiNextGenApiInvalidDataException
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** Statistics on the interaction request's token usage. */
class Usage
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val cachedTokensByModality: JsonField<List<CachedTokensByModality>>,
    private val groundingToolCount: JsonField<List<GroundingToolCount>>,
    private val inputTokensByModality: JsonField<List<InputTokensByModality>>,
    private val outputTokensByModality: JsonField<List<OutputTokensByModality>>,
    private val toolUseTokensByModality: JsonField<List<ToolUseTokensByModality>>,
    private val totalCachedTokens: JsonField<Int>,
    private val totalInputTokens: JsonField<Int>,
    private val totalOutputTokens: JsonField<Int>,
    private val totalThoughtTokens: JsonField<Int>,
    private val totalTokens: JsonField<Int>,
    private val totalToolUseTokens: JsonField<Int>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("cached_tokens_by_modality")
        @ExcludeMissing
        cachedTokensByModality: JsonField<List<CachedTokensByModality>> = JsonMissing.of(),
        @JsonProperty("grounding_tool_count")
        @ExcludeMissing
        groundingToolCount: JsonField<List<GroundingToolCount>> = JsonMissing.of(),
        @JsonProperty("input_tokens_by_modality")
        @ExcludeMissing
        inputTokensByModality: JsonField<List<InputTokensByModality>> = JsonMissing.of(),
        @JsonProperty("output_tokens_by_modality")
        @ExcludeMissing
        outputTokensByModality: JsonField<List<OutputTokensByModality>> = JsonMissing.of(),
        @JsonProperty("tool_use_tokens_by_modality")
        @ExcludeMissing
        toolUseTokensByModality: JsonField<List<ToolUseTokensByModality>> = JsonMissing.of(),
        @JsonProperty("total_cached_tokens")
        @ExcludeMissing
        totalCachedTokens: JsonField<Int> = JsonMissing.of(),
        @JsonProperty("total_input_tokens")
        @ExcludeMissing
        totalInputTokens: JsonField<Int> = JsonMissing.of(),
        @JsonProperty("total_output_tokens")
        @ExcludeMissing
        totalOutputTokens: JsonField<Int> = JsonMissing.of(),
        @JsonProperty("total_thought_tokens")
        @ExcludeMissing
        totalThoughtTokens: JsonField<Int> = JsonMissing.of(),
        @JsonProperty("total_tokens")
        @ExcludeMissing
        totalTokens: JsonField<Int> = JsonMissing.of(),
        @JsonProperty("total_tool_use_tokens")
        @ExcludeMissing
        totalToolUseTokens: JsonField<Int> = JsonMissing.of(),
    ) : this(
        cachedTokensByModality,
        groundingToolCount,
        inputTokensByModality,
        outputTokensByModality,
        toolUseTokensByModality,
        totalCachedTokens,
        totalInputTokens,
        totalOutputTokens,
        totalThoughtTokens,
        totalTokens,
        totalToolUseTokens,
        mutableMapOf(),
    )

    /**
     * A breakdown of cached token usage by modality.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun cachedTokensByModality(): Optional<List<CachedTokensByModality>> =
        cachedTokensByModality.getOptional("cached_tokens_by_modality")

    /**
     * Grounding tool count.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun groundingToolCount(): Optional<List<GroundingToolCount>> =
        groundingToolCount.getOptional("grounding_tool_count")

    /**
     * A breakdown of input token usage by modality.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun inputTokensByModality(): Optional<List<InputTokensByModality>> =
        inputTokensByModality.getOptional("input_tokens_by_modality")

    /**
     * A breakdown of output token usage by modality.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun outputTokensByModality(): Optional<List<OutputTokensByModality>> =
        outputTokensByModality.getOptional("output_tokens_by_modality")

    /**
     * A breakdown of tool-use token usage by modality.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun toolUseTokensByModality(): Optional<List<ToolUseTokensByModality>> =
        toolUseTokensByModality.getOptional("tool_use_tokens_by_modality")

    /**
     * Number of tokens in the cached part of the prompt (the cached content).
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun totalCachedTokens(): Optional<Int> = totalCachedTokens.getOptional("total_cached_tokens")

    /**
     * Number of tokens in the prompt (context).
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun totalInputTokens(): Optional<Int> = totalInputTokens.getOptional("total_input_tokens")

    /**
     * Total number of tokens across all the generated responses.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun totalOutputTokens(): Optional<Int> = totalOutputTokens.getOptional("total_output_tokens")

    /**
     * Number of tokens of thoughts for thinking models.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun totalThoughtTokens(): Optional<Int> = totalThoughtTokens.getOptional("total_thought_tokens")

    /**
     * Total token count for the interaction request (prompt + responses + other internal tokens).
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun totalTokens(): Optional<Int> = totalTokens.getOptional("total_tokens")

    /**
     * Number of tokens present in tool-use prompt(s).
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun totalToolUseTokens(): Optional<Int> =
        totalToolUseTokens.getOptional("total_tool_use_tokens")

    /**
     * Returns the raw JSON value of [cachedTokensByModality].
     *
     * Unlike [cachedTokensByModality], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("cached_tokens_by_modality")
    @ExcludeMissing
    fun _cachedTokensByModality(): JsonField<List<CachedTokensByModality>> = cachedTokensByModality

    /**
     * Returns the raw JSON value of [groundingToolCount].
     *
     * Unlike [groundingToolCount], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("grounding_tool_count")
    @ExcludeMissing
    fun _groundingToolCount(): JsonField<List<GroundingToolCount>> = groundingToolCount

    /**
     * Returns the raw JSON value of [inputTokensByModality].
     *
     * Unlike [inputTokensByModality], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("input_tokens_by_modality")
    @ExcludeMissing
    fun _inputTokensByModality(): JsonField<List<InputTokensByModality>> = inputTokensByModality

    /**
     * Returns the raw JSON value of [outputTokensByModality].
     *
     * Unlike [outputTokensByModality], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("output_tokens_by_modality")
    @ExcludeMissing
    fun _outputTokensByModality(): JsonField<List<OutputTokensByModality>> = outputTokensByModality

    /**
     * Returns the raw JSON value of [toolUseTokensByModality].
     *
     * Unlike [toolUseTokensByModality], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("tool_use_tokens_by_modality")
    @ExcludeMissing
    fun _toolUseTokensByModality(): JsonField<List<ToolUseTokensByModality>> =
        toolUseTokensByModality

    /**
     * Returns the raw JSON value of [totalCachedTokens].
     *
     * Unlike [totalCachedTokens], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("total_cached_tokens")
    @ExcludeMissing
    fun _totalCachedTokens(): JsonField<Int> = totalCachedTokens

    /**
     * Returns the raw JSON value of [totalInputTokens].
     *
     * Unlike [totalInputTokens], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("total_input_tokens")
    @ExcludeMissing
    fun _totalInputTokens(): JsonField<Int> = totalInputTokens

    /**
     * Returns the raw JSON value of [totalOutputTokens].
     *
     * Unlike [totalOutputTokens], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("total_output_tokens")
    @ExcludeMissing
    fun _totalOutputTokens(): JsonField<Int> = totalOutputTokens

    /**
     * Returns the raw JSON value of [totalThoughtTokens].
     *
     * Unlike [totalThoughtTokens], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("total_thought_tokens")
    @ExcludeMissing
    fun _totalThoughtTokens(): JsonField<Int> = totalThoughtTokens

    /**
     * Returns the raw JSON value of [totalTokens].
     *
     * Unlike [totalTokens], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("total_tokens") @ExcludeMissing fun _totalTokens(): JsonField<Int> = totalTokens

    /**
     * Returns the raw JSON value of [totalToolUseTokens].
     *
     * Unlike [totalToolUseTokens], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("total_tool_use_tokens")
    @ExcludeMissing
    fun _totalToolUseTokens(): JsonField<Int> = totalToolUseTokens

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

        /** Returns a mutable builder for constructing an instance of [Usage]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [Usage]. */
    class Builder internal constructor() {

        private var cachedTokensByModality: JsonField<MutableList<CachedTokensByModality>>? = null
        private var groundingToolCount: JsonField<MutableList<GroundingToolCount>>? = null
        private var inputTokensByModality: JsonField<MutableList<InputTokensByModality>>? = null
        private var outputTokensByModality: JsonField<MutableList<OutputTokensByModality>>? = null
        private var toolUseTokensByModality: JsonField<MutableList<ToolUseTokensByModality>>? = null
        private var totalCachedTokens: JsonField<Int> = JsonMissing.of()
        private var totalInputTokens: JsonField<Int> = JsonMissing.of()
        private var totalOutputTokens: JsonField<Int> = JsonMissing.of()
        private var totalThoughtTokens: JsonField<Int> = JsonMissing.of()
        private var totalTokens: JsonField<Int> = JsonMissing.of()
        private var totalToolUseTokens: JsonField<Int> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(usage: Usage) = apply {
            cachedTokensByModality = usage.cachedTokensByModality.map { it.toMutableList() }
            groundingToolCount = usage.groundingToolCount.map { it.toMutableList() }
            inputTokensByModality = usage.inputTokensByModality.map { it.toMutableList() }
            outputTokensByModality = usage.outputTokensByModality.map { it.toMutableList() }
            toolUseTokensByModality = usage.toolUseTokensByModality.map { it.toMutableList() }
            totalCachedTokens = usage.totalCachedTokens
            totalInputTokens = usage.totalInputTokens
            totalOutputTokens = usage.totalOutputTokens
            totalThoughtTokens = usage.totalThoughtTokens
            totalTokens = usage.totalTokens
            totalToolUseTokens = usage.totalToolUseTokens
            additionalProperties = usage.additionalProperties.toMutableMap()
        }

        /** A breakdown of cached token usage by modality. */
        fun cachedTokensByModality(cachedTokensByModality: List<CachedTokensByModality>) =
            cachedTokensByModality(JsonField.of(cachedTokensByModality))

        /**
         * Sets [Builder.cachedTokensByModality] to an arbitrary JSON value.
         *
         * You should usually call [Builder.cachedTokensByModality] with a well-typed
         * `List<CachedTokensByModality>` value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun cachedTokensByModality(
            cachedTokensByModality: JsonField<List<CachedTokensByModality>>
        ) = apply {
            this.cachedTokensByModality = cachedTokensByModality.map { it.toMutableList() }
        }

        /**
         * Adds a single [CachedTokensByModality] to [Builder.cachedTokensByModality].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addCachedTokensByModality(cachedTokensByModality: CachedTokensByModality) = apply {
            this.cachedTokensByModality =
                (this.cachedTokensByModality ?: JsonField.of(mutableListOf())).also {
                    checkKnown("cachedTokensByModality", it).add(cachedTokensByModality)
                }
        }

        /** Grounding tool count. */
        fun groundingToolCount(groundingToolCount: List<GroundingToolCount>) =
            groundingToolCount(JsonField.of(groundingToolCount))

        /**
         * Sets [Builder.groundingToolCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.groundingToolCount] with a well-typed
         * `List<GroundingToolCount>` value instead. This method is primarily for setting the field
         * to an undocumented or not yet supported value.
         */
        fun groundingToolCount(groundingToolCount: JsonField<List<GroundingToolCount>>) = apply {
            this.groundingToolCount = groundingToolCount.map { it.toMutableList() }
        }

        /**
         * Adds a single [GroundingToolCount] to [Builder.groundingToolCount].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addGroundingToolCount(groundingToolCount: GroundingToolCount) = apply {
            this.groundingToolCount =
                (this.groundingToolCount ?: JsonField.of(mutableListOf())).also {
                    checkKnown("groundingToolCount", it).add(groundingToolCount)
                }
        }

        /** A breakdown of input token usage by modality. */
        fun inputTokensByModality(inputTokensByModality: List<InputTokensByModality>) =
            inputTokensByModality(JsonField.of(inputTokensByModality))

        /**
         * Sets [Builder.inputTokensByModality] to an arbitrary JSON value.
         *
         * You should usually call [Builder.inputTokensByModality] with a well-typed
         * `List<InputTokensByModality>` value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun inputTokensByModality(inputTokensByModality: JsonField<List<InputTokensByModality>>) =
            apply {
                this.inputTokensByModality = inputTokensByModality.map { it.toMutableList() }
            }

        /**
         * Adds a single [InputTokensByModality] to [Builder.inputTokensByModality].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addInputTokensByModality(inputTokensByModality: InputTokensByModality) = apply {
            this.inputTokensByModality =
                (this.inputTokensByModality ?: JsonField.of(mutableListOf())).also {
                    checkKnown("inputTokensByModality", it).add(inputTokensByModality)
                }
        }

        /** A breakdown of output token usage by modality. */
        fun outputTokensByModality(outputTokensByModality: List<OutputTokensByModality>) =
            outputTokensByModality(JsonField.of(outputTokensByModality))

        /**
         * Sets [Builder.outputTokensByModality] to an arbitrary JSON value.
         *
         * You should usually call [Builder.outputTokensByModality] with a well-typed
         * `List<OutputTokensByModality>` value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun outputTokensByModality(
            outputTokensByModality: JsonField<List<OutputTokensByModality>>
        ) = apply {
            this.outputTokensByModality = outputTokensByModality.map { it.toMutableList() }
        }

        /**
         * Adds a single [OutputTokensByModality] to [Builder.outputTokensByModality].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addOutputTokensByModality(outputTokensByModality: OutputTokensByModality) = apply {
            this.outputTokensByModality =
                (this.outputTokensByModality ?: JsonField.of(mutableListOf())).also {
                    checkKnown("outputTokensByModality", it).add(outputTokensByModality)
                }
        }

        /** A breakdown of tool-use token usage by modality. */
        fun toolUseTokensByModality(toolUseTokensByModality: List<ToolUseTokensByModality>) =
            toolUseTokensByModality(JsonField.of(toolUseTokensByModality))

        /**
         * Sets [Builder.toolUseTokensByModality] to an arbitrary JSON value.
         *
         * You should usually call [Builder.toolUseTokensByModality] with a well-typed
         * `List<ToolUseTokensByModality>` value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun toolUseTokensByModality(
            toolUseTokensByModality: JsonField<List<ToolUseTokensByModality>>
        ) = apply {
            this.toolUseTokensByModality = toolUseTokensByModality.map { it.toMutableList() }
        }

        /**
         * Adds a single [ToolUseTokensByModality] to [Builder.toolUseTokensByModality].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addToolUseTokensByModality(toolUseTokensByModality: ToolUseTokensByModality) = apply {
            this.toolUseTokensByModality =
                (this.toolUseTokensByModality ?: JsonField.of(mutableListOf())).also {
                    checkKnown("toolUseTokensByModality", it).add(toolUseTokensByModality)
                }
        }

        /** Number of tokens in the cached part of the prompt (the cached content). */
        fun totalCachedTokens(totalCachedTokens: Int) =
            totalCachedTokens(JsonField.of(totalCachedTokens))

        /**
         * Sets [Builder.totalCachedTokens] to an arbitrary JSON value.
         *
         * You should usually call [Builder.totalCachedTokens] with a well-typed [Int] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun totalCachedTokens(totalCachedTokens: JsonField<Int>) = apply {
            this.totalCachedTokens = totalCachedTokens
        }

        /** Number of tokens in the prompt (context). */
        fun totalInputTokens(totalInputTokens: Int) =
            totalInputTokens(JsonField.of(totalInputTokens))

        /**
         * Sets [Builder.totalInputTokens] to an arbitrary JSON value.
         *
         * You should usually call [Builder.totalInputTokens] with a well-typed [Int] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun totalInputTokens(totalInputTokens: JsonField<Int>) = apply {
            this.totalInputTokens = totalInputTokens
        }

        /** Total number of tokens across all the generated responses. */
        fun totalOutputTokens(totalOutputTokens: Int) =
            totalOutputTokens(JsonField.of(totalOutputTokens))

        /**
         * Sets [Builder.totalOutputTokens] to an arbitrary JSON value.
         *
         * You should usually call [Builder.totalOutputTokens] with a well-typed [Int] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun totalOutputTokens(totalOutputTokens: JsonField<Int>) = apply {
            this.totalOutputTokens = totalOutputTokens
        }

        /** Number of tokens of thoughts for thinking models. */
        fun totalThoughtTokens(totalThoughtTokens: Int) =
            totalThoughtTokens(JsonField.of(totalThoughtTokens))

        /**
         * Sets [Builder.totalThoughtTokens] to an arbitrary JSON value.
         *
         * You should usually call [Builder.totalThoughtTokens] with a well-typed [Int] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun totalThoughtTokens(totalThoughtTokens: JsonField<Int>) = apply {
            this.totalThoughtTokens = totalThoughtTokens
        }

        /**
         * Total token count for the interaction request (prompt + responses + other internal
         * tokens).
         */
        fun totalTokens(totalTokens: Int) = totalTokens(JsonField.of(totalTokens))

        /**
         * Sets [Builder.totalTokens] to an arbitrary JSON value.
         *
         * You should usually call [Builder.totalTokens] with a well-typed [Int] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun totalTokens(totalTokens: JsonField<Int>) = apply { this.totalTokens = totalTokens }

        /** Number of tokens present in tool-use prompt(s). */
        fun totalToolUseTokens(totalToolUseTokens: Int) =
            totalToolUseTokens(JsonField.of(totalToolUseTokens))

        /**
         * Sets [Builder.totalToolUseTokens] to an arbitrary JSON value.
         *
         * You should usually call [Builder.totalToolUseTokens] with a well-typed [Int] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun totalToolUseTokens(totalToolUseTokens: JsonField<Int>) = apply {
            this.totalToolUseTokens = totalToolUseTokens
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
         * Returns an immutable instance of [Usage].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): Usage =
            Usage(
                (cachedTokensByModality ?: JsonMissing.of()).map { it.toImmutable() },
                (groundingToolCount ?: JsonMissing.of()).map { it.toImmutable() },
                (inputTokensByModality ?: JsonMissing.of()).map { it.toImmutable() },
                (outputTokensByModality ?: JsonMissing.of()).map { it.toImmutable() },
                (toolUseTokensByModality ?: JsonMissing.of()).map { it.toImmutable() },
                totalCachedTokens,
                totalInputTokens,
                totalOutputTokens,
                totalThoughtTokens,
                totalTokens,
                totalToolUseTokens,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): Usage = apply {
        if (validated) {
            return@apply
        }

        cachedTokensByModality().ifPresent { it.forEach { it.validate() } }
        groundingToolCount().ifPresent { it.forEach { it.validate() } }
        inputTokensByModality().ifPresent { it.forEach { it.validate() } }
        outputTokensByModality().ifPresent { it.forEach { it.validate() } }
        toolUseTokensByModality().ifPresent { it.forEach { it.validate() } }
        totalCachedTokens()
        totalInputTokens()
        totalOutputTokens()
        totalThoughtTokens()
        totalTokens()
        totalToolUseTokens()
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
        (cachedTokensByModality.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (groundingToolCount.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (inputTokensByModality.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (outputTokensByModality.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (toolUseTokensByModality.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (if (totalCachedTokens.asKnown().isPresent) 1 else 0) +
            (if (totalInputTokens.asKnown().isPresent) 1 else 0) +
            (if (totalOutputTokens.asKnown().isPresent) 1 else 0) +
            (if (totalThoughtTokens.asKnown().isPresent) 1 else 0) +
            (if (totalTokens.asKnown().isPresent) 1 else 0) +
            (if (totalToolUseTokens.asKnown().isPresent) 1 else 0)

    /** The token count for a single response modality. */
    class CachedTokensByModality
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val modality: JsonField<Modality>,
        private val tokens: JsonField<Int>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("modality")
            @ExcludeMissing
            modality: JsonField<Modality> = JsonMissing.of(),
            @JsonProperty("tokens") @ExcludeMissing tokens: JsonField<Int> = JsonMissing.of(),
        ) : this(modality, tokens, mutableMapOf())

        /**
         * The modality associated with the token count.
         *
         * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
         *   (e.g. if the server responded with an unexpected value).
         */
        fun modality(): Optional<Modality> = modality.getOptional("modality")

        /**
         * Number of tokens for the modality.
         *
         * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
         *   (e.g. if the server responded with an unexpected value).
         */
        fun tokens(): Optional<Int> = tokens.getOptional("tokens")

        /**
         * Returns the raw JSON value of [modality].
         *
         * Unlike [modality], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("modality") @ExcludeMissing fun _modality(): JsonField<Modality> = modality

        /**
         * Returns the raw JSON value of [tokens].
         *
         * Unlike [tokens], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("tokens") @ExcludeMissing fun _tokens(): JsonField<Int> = tokens

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
             * Returns a mutable builder for constructing an instance of [CachedTokensByModality].
             */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [CachedTokensByModality]. */
        class Builder internal constructor() {

            private var modality: JsonField<Modality> = JsonMissing.of()
            private var tokens: JsonField<Int> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(cachedTokensByModality: CachedTokensByModality) = apply {
                modality = cachedTokensByModality.modality
                tokens = cachedTokensByModality.tokens
                additionalProperties = cachedTokensByModality.additionalProperties.toMutableMap()
            }

            /** The modality associated with the token count. */
            fun modality(modality: Modality) = modality(JsonField.of(modality))

            /**
             * Sets [Builder.modality] to an arbitrary JSON value.
             *
             * You should usually call [Builder.modality] with a well-typed [Modality] value
             * instead. This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun modality(modality: JsonField<Modality>) = apply { this.modality = modality }

            /** Number of tokens for the modality. */
            fun tokens(tokens: Int) = tokens(JsonField.of(tokens))

            /**
             * Sets [Builder.tokens] to an arbitrary JSON value.
             *
             * You should usually call [Builder.tokens] with a well-typed [Int] value instead. This
             * method is primarily for setting the field to an undocumented or not yet supported
             * value.
             */
            fun tokens(tokens: JsonField<Int>) = apply { this.tokens = tokens }

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
             * Returns an immutable instance of [CachedTokensByModality].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): CachedTokensByModality =
                CachedTokensByModality(modality, tokens, additionalProperties.toMutableMap())
        }

        private var validated: Boolean = false

        fun validate(): CachedTokensByModality = apply {
            if (validated) {
                return@apply
            }

            modality().ifPresent { it.validate() }
            tokens()
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
            (modality.asKnown().getOrNull()?.validity() ?: 0) +
                (if (tokens.asKnown().isPresent) 1 else 0)

        /** The modality associated with the token count. */
        class Modality @JsonCreator private constructor(private val value: JsonField<String>) :
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

                @JvmField val TEXT = of("text")

                @JvmField val IMAGE = of("image")

                @JvmField val AUDIO = of("audio")

                @JvmField val VIDEO = of("video")

                @JvmField val DOCUMENT = of("document")

                @JvmStatic fun of(value: String) = Modality(JsonField.of(value))
            }

            /** An enum containing [Modality]'s known values. */
            enum class Known {
                TEXT,
                IMAGE,
                AUDIO,
                VIDEO,
                DOCUMENT,
            }

            /**
             * An enum containing [Modality]'s known values, as well as an [_UNKNOWN] member.
             *
             * An instance of [Modality] can contain an unknown value in a couple of cases:
             * - It was deserialized from data that doesn't match any known member. For example, if
             *   the SDK is on an older version than the API, then the API may respond with new
             *   members that the SDK is unaware of.
             * - It was constructed with an arbitrary value using the [of] method.
             */
            enum class Value {
                TEXT,
                IMAGE,
                AUDIO,
                VIDEO,
                DOCUMENT,
                /**
                 * An enum member indicating that [Modality] was instantiated with an unknown value.
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
                    TEXT -> Value.TEXT
                    IMAGE -> Value.IMAGE
                    AUDIO -> Value.AUDIO
                    VIDEO -> Value.VIDEO
                    DOCUMENT -> Value.DOCUMENT
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
                    TEXT -> Known.TEXT
                    IMAGE -> Known.IMAGE
                    AUDIO -> Known.AUDIO
                    VIDEO -> Known.VIDEO
                    DOCUMENT -> Known.DOCUMENT
                    else -> throw GeminiNextGenApiInvalidDataException("Unknown Modality: $value")
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

            fun validate(): Modality = apply {
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

                return other is Modality && value == other.value
            }

            override fun hashCode() = value.hashCode()

            override fun toString() = value.toString()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is CachedTokensByModality &&
                modality == other.modality &&
                tokens == other.tokens &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(modality, tokens, additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "CachedTokensByModality{modality=$modality, tokens=$tokens, additionalProperties=$additionalProperties}"
    }

    /** The number of grounding tool counts. */
    class GroundingToolCount
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val count: JsonField<Int>,
        private val type: JsonField<Type>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("count") @ExcludeMissing count: JsonField<Int> = JsonMissing.of(),
            @JsonProperty("type") @ExcludeMissing type: JsonField<Type> = JsonMissing.of(),
        ) : this(count, type, mutableMapOf())

        /**
         * The number of grounding tool counts.
         *
         * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
         *   (e.g. if the server responded with an unexpected value).
         */
        fun count(): Optional<Int> = count.getOptional("count")

        /**
         * The grounding tool type associated with the count.
         *
         * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
         *   (e.g. if the server responded with an unexpected value).
         */
        fun type(): Optional<Type> = type.getOptional("type")

        /**
         * Returns the raw JSON value of [count].
         *
         * Unlike [count], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("count") @ExcludeMissing fun _count(): JsonField<Int> = count

        /**
         * Returns the raw JSON value of [type].
         *
         * Unlike [type], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<Type> = type

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

            /** Returns a mutable builder for constructing an instance of [GroundingToolCount]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [GroundingToolCount]. */
        class Builder internal constructor() {

            private var count: JsonField<Int> = JsonMissing.of()
            private var type: JsonField<Type> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(groundingToolCount: GroundingToolCount) = apply {
                count = groundingToolCount.count
                type = groundingToolCount.type
                additionalProperties = groundingToolCount.additionalProperties.toMutableMap()
            }

            /** The number of grounding tool counts. */
            fun count(count: Int) = count(JsonField.of(count))

            /**
             * Sets [Builder.count] to an arbitrary JSON value.
             *
             * You should usually call [Builder.count] with a well-typed [Int] value instead. This
             * method is primarily for setting the field to an undocumented or not yet supported
             * value.
             */
            fun count(count: JsonField<Int>) = apply { this.count = count }

            /** The grounding tool type associated with the count. */
            fun type(type: Type) = type(JsonField.of(type))

            /**
             * Sets [Builder.type] to an arbitrary JSON value.
             *
             * You should usually call [Builder.type] with a well-typed [Type] value instead. This
             * method is primarily for setting the field to an undocumented or not yet supported
             * value.
             */
            fun type(type: JsonField<Type>) = apply { this.type = type }

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
             * Returns an immutable instance of [GroundingToolCount].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): GroundingToolCount =
                GroundingToolCount(count, type, additionalProperties.toMutableMap())
        }

        private var validated: Boolean = false

        fun validate(): GroundingToolCount = apply {
            if (validated) {
                return@apply
            }

            count()
            type().ifPresent { it.validate() }
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
            (if (count.asKnown().isPresent) 1 else 0) +
                (type.asKnown().getOrNull()?.validity() ?: 0)

        /** The grounding tool type associated with the count. */
        class Type @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

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

                @JvmField val GOOGLE_SEARCH = of("google_search")

                @JvmField val GOOGLE_MAPS = of("google_maps")

                @JvmField val RETRIEVAL = of("retrieval")

                @JvmStatic fun of(value: String) = Type(JsonField.of(value))
            }

            /** An enum containing [Type]'s known values. */
            enum class Known {
                GOOGLE_SEARCH,
                GOOGLE_MAPS,
                RETRIEVAL,
            }

            /**
             * An enum containing [Type]'s known values, as well as an [_UNKNOWN] member.
             *
             * An instance of [Type] can contain an unknown value in a couple of cases:
             * - It was deserialized from data that doesn't match any known member. For example, if
             *   the SDK is on an older version than the API, then the API may respond with new
             *   members that the SDK is unaware of.
             * - It was constructed with an arbitrary value using the [of] method.
             */
            enum class Value {
                GOOGLE_SEARCH,
                GOOGLE_MAPS,
                RETRIEVAL,
                /** An enum member indicating that [Type] was instantiated with an unknown value. */
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
                    GOOGLE_SEARCH -> Value.GOOGLE_SEARCH
                    GOOGLE_MAPS -> Value.GOOGLE_MAPS
                    RETRIEVAL -> Value.RETRIEVAL
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
                    GOOGLE_SEARCH -> Known.GOOGLE_SEARCH
                    GOOGLE_MAPS -> Known.GOOGLE_MAPS
                    RETRIEVAL -> Known.RETRIEVAL
                    else -> throw GeminiNextGenApiInvalidDataException("Unknown Type: $value")
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

            fun validate(): Type = apply {
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

                return other is Type && value == other.value
            }

            override fun hashCode() = value.hashCode()

            override fun toString() = value.toString()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is GroundingToolCount &&
                count == other.count &&
                type == other.type &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(count, type, additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "GroundingToolCount{count=$count, type=$type, additionalProperties=$additionalProperties}"
    }

    /** The token count for a single response modality. */
    class InputTokensByModality
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val modality: JsonField<Modality>,
        private val tokens: JsonField<Int>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("modality")
            @ExcludeMissing
            modality: JsonField<Modality> = JsonMissing.of(),
            @JsonProperty("tokens") @ExcludeMissing tokens: JsonField<Int> = JsonMissing.of(),
        ) : this(modality, tokens, mutableMapOf())

        /**
         * The modality associated with the token count.
         *
         * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
         *   (e.g. if the server responded with an unexpected value).
         */
        fun modality(): Optional<Modality> = modality.getOptional("modality")

        /**
         * Number of tokens for the modality.
         *
         * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
         *   (e.g. if the server responded with an unexpected value).
         */
        fun tokens(): Optional<Int> = tokens.getOptional("tokens")

        /**
         * Returns the raw JSON value of [modality].
         *
         * Unlike [modality], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("modality") @ExcludeMissing fun _modality(): JsonField<Modality> = modality

        /**
         * Returns the raw JSON value of [tokens].
         *
         * Unlike [tokens], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("tokens") @ExcludeMissing fun _tokens(): JsonField<Int> = tokens

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
             * Returns a mutable builder for constructing an instance of [InputTokensByModality].
             */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [InputTokensByModality]. */
        class Builder internal constructor() {

            private var modality: JsonField<Modality> = JsonMissing.of()
            private var tokens: JsonField<Int> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(inputTokensByModality: InputTokensByModality) = apply {
                modality = inputTokensByModality.modality
                tokens = inputTokensByModality.tokens
                additionalProperties = inputTokensByModality.additionalProperties.toMutableMap()
            }

            /** The modality associated with the token count. */
            fun modality(modality: Modality) = modality(JsonField.of(modality))

            /**
             * Sets [Builder.modality] to an arbitrary JSON value.
             *
             * You should usually call [Builder.modality] with a well-typed [Modality] value
             * instead. This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun modality(modality: JsonField<Modality>) = apply { this.modality = modality }

            /** Number of tokens for the modality. */
            fun tokens(tokens: Int) = tokens(JsonField.of(tokens))

            /**
             * Sets [Builder.tokens] to an arbitrary JSON value.
             *
             * You should usually call [Builder.tokens] with a well-typed [Int] value instead. This
             * method is primarily for setting the field to an undocumented or not yet supported
             * value.
             */
            fun tokens(tokens: JsonField<Int>) = apply { this.tokens = tokens }

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
             * Returns an immutable instance of [InputTokensByModality].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): InputTokensByModality =
                InputTokensByModality(modality, tokens, additionalProperties.toMutableMap())
        }

        private var validated: Boolean = false

        fun validate(): InputTokensByModality = apply {
            if (validated) {
                return@apply
            }

            modality().ifPresent { it.validate() }
            tokens()
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
            (modality.asKnown().getOrNull()?.validity() ?: 0) +
                (if (tokens.asKnown().isPresent) 1 else 0)

        /** The modality associated with the token count. */
        class Modality @JsonCreator private constructor(private val value: JsonField<String>) :
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

                @JvmField val TEXT = of("text")

                @JvmField val IMAGE = of("image")

                @JvmField val AUDIO = of("audio")

                @JvmField val VIDEO = of("video")

                @JvmField val DOCUMENT = of("document")

                @JvmStatic fun of(value: String) = Modality(JsonField.of(value))
            }

            /** An enum containing [Modality]'s known values. */
            enum class Known {
                TEXT,
                IMAGE,
                AUDIO,
                VIDEO,
                DOCUMENT,
            }

            /**
             * An enum containing [Modality]'s known values, as well as an [_UNKNOWN] member.
             *
             * An instance of [Modality] can contain an unknown value in a couple of cases:
             * - It was deserialized from data that doesn't match any known member. For example, if
             *   the SDK is on an older version than the API, then the API may respond with new
             *   members that the SDK is unaware of.
             * - It was constructed with an arbitrary value using the [of] method.
             */
            enum class Value {
                TEXT,
                IMAGE,
                AUDIO,
                VIDEO,
                DOCUMENT,
                /**
                 * An enum member indicating that [Modality] was instantiated with an unknown value.
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
                    TEXT -> Value.TEXT
                    IMAGE -> Value.IMAGE
                    AUDIO -> Value.AUDIO
                    VIDEO -> Value.VIDEO
                    DOCUMENT -> Value.DOCUMENT
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
                    TEXT -> Known.TEXT
                    IMAGE -> Known.IMAGE
                    AUDIO -> Known.AUDIO
                    VIDEO -> Known.VIDEO
                    DOCUMENT -> Known.DOCUMENT
                    else -> throw GeminiNextGenApiInvalidDataException("Unknown Modality: $value")
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

            fun validate(): Modality = apply {
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

                return other is Modality && value == other.value
            }

            override fun hashCode() = value.hashCode()

            override fun toString() = value.toString()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is InputTokensByModality &&
                modality == other.modality &&
                tokens == other.tokens &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(modality, tokens, additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "InputTokensByModality{modality=$modality, tokens=$tokens, additionalProperties=$additionalProperties}"
    }

    /** The token count for a single response modality. */
    class OutputTokensByModality
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val modality: JsonField<Modality>,
        private val tokens: JsonField<Int>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("modality")
            @ExcludeMissing
            modality: JsonField<Modality> = JsonMissing.of(),
            @JsonProperty("tokens") @ExcludeMissing tokens: JsonField<Int> = JsonMissing.of(),
        ) : this(modality, tokens, mutableMapOf())

        /**
         * The modality associated with the token count.
         *
         * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
         *   (e.g. if the server responded with an unexpected value).
         */
        fun modality(): Optional<Modality> = modality.getOptional("modality")

        /**
         * Number of tokens for the modality.
         *
         * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
         *   (e.g. if the server responded with an unexpected value).
         */
        fun tokens(): Optional<Int> = tokens.getOptional("tokens")

        /**
         * Returns the raw JSON value of [modality].
         *
         * Unlike [modality], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("modality") @ExcludeMissing fun _modality(): JsonField<Modality> = modality

        /**
         * Returns the raw JSON value of [tokens].
         *
         * Unlike [tokens], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("tokens") @ExcludeMissing fun _tokens(): JsonField<Int> = tokens

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
             * Returns a mutable builder for constructing an instance of [OutputTokensByModality].
             */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [OutputTokensByModality]. */
        class Builder internal constructor() {

            private var modality: JsonField<Modality> = JsonMissing.of()
            private var tokens: JsonField<Int> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(outputTokensByModality: OutputTokensByModality) = apply {
                modality = outputTokensByModality.modality
                tokens = outputTokensByModality.tokens
                additionalProperties = outputTokensByModality.additionalProperties.toMutableMap()
            }

            /** The modality associated with the token count. */
            fun modality(modality: Modality) = modality(JsonField.of(modality))

            /**
             * Sets [Builder.modality] to an arbitrary JSON value.
             *
             * You should usually call [Builder.modality] with a well-typed [Modality] value
             * instead. This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun modality(modality: JsonField<Modality>) = apply { this.modality = modality }

            /** Number of tokens for the modality. */
            fun tokens(tokens: Int) = tokens(JsonField.of(tokens))

            /**
             * Sets [Builder.tokens] to an arbitrary JSON value.
             *
             * You should usually call [Builder.tokens] with a well-typed [Int] value instead. This
             * method is primarily for setting the field to an undocumented or not yet supported
             * value.
             */
            fun tokens(tokens: JsonField<Int>) = apply { this.tokens = tokens }

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
             * Returns an immutable instance of [OutputTokensByModality].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): OutputTokensByModality =
                OutputTokensByModality(modality, tokens, additionalProperties.toMutableMap())
        }

        private var validated: Boolean = false

        fun validate(): OutputTokensByModality = apply {
            if (validated) {
                return@apply
            }

            modality().ifPresent { it.validate() }
            tokens()
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
            (modality.asKnown().getOrNull()?.validity() ?: 0) +
                (if (tokens.asKnown().isPresent) 1 else 0)

        /** The modality associated with the token count. */
        class Modality @JsonCreator private constructor(private val value: JsonField<String>) :
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

                @JvmField val TEXT = of("text")

                @JvmField val IMAGE = of("image")

                @JvmField val AUDIO = of("audio")

                @JvmField val VIDEO = of("video")

                @JvmField val DOCUMENT = of("document")

                @JvmStatic fun of(value: String) = Modality(JsonField.of(value))
            }

            /** An enum containing [Modality]'s known values. */
            enum class Known {
                TEXT,
                IMAGE,
                AUDIO,
                VIDEO,
                DOCUMENT,
            }

            /**
             * An enum containing [Modality]'s known values, as well as an [_UNKNOWN] member.
             *
             * An instance of [Modality] can contain an unknown value in a couple of cases:
             * - It was deserialized from data that doesn't match any known member. For example, if
             *   the SDK is on an older version than the API, then the API may respond with new
             *   members that the SDK is unaware of.
             * - It was constructed with an arbitrary value using the [of] method.
             */
            enum class Value {
                TEXT,
                IMAGE,
                AUDIO,
                VIDEO,
                DOCUMENT,
                /**
                 * An enum member indicating that [Modality] was instantiated with an unknown value.
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
                    TEXT -> Value.TEXT
                    IMAGE -> Value.IMAGE
                    AUDIO -> Value.AUDIO
                    VIDEO -> Value.VIDEO
                    DOCUMENT -> Value.DOCUMENT
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
                    TEXT -> Known.TEXT
                    IMAGE -> Known.IMAGE
                    AUDIO -> Known.AUDIO
                    VIDEO -> Known.VIDEO
                    DOCUMENT -> Known.DOCUMENT
                    else -> throw GeminiNextGenApiInvalidDataException("Unknown Modality: $value")
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

            fun validate(): Modality = apply {
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

                return other is Modality && value == other.value
            }

            override fun hashCode() = value.hashCode()

            override fun toString() = value.toString()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is OutputTokensByModality &&
                modality == other.modality &&
                tokens == other.tokens &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(modality, tokens, additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "OutputTokensByModality{modality=$modality, tokens=$tokens, additionalProperties=$additionalProperties}"
    }

    /** The token count for a single response modality. */
    class ToolUseTokensByModality
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val modality: JsonField<Modality>,
        private val tokens: JsonField<Int>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("modality")
            @ExcludeMissing
            modality: JsonField<Modality> = JsonMissing.of(),
            @JsonProperty("tokens") @ExcludeMissing tokens: JsonField<Int> = JsonMissing.of(),
        ) : this(modality, tokens, mutableMapOf())

        /**
         * The modality associated with the token count.
         *
         * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
         *   (e.g. if the server responded with an unexpected value).
         */
        fun modality(): Optional<Modality> = modality.getOptional("modality")

        /**
         * Number of tokens for the modality.
         *
         * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type
         *   (e.g. if the server responded with an unexpected value).
         */
        fun tokens(): Optional<Int> = tokens.getOptional("tokens")

        /**
         * Returns the raw JSON value of [modality].
         *
         * Unlike [modality], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("modality") @ExcludeMissing fun _modality(): JsonField<Modality> = modality

        /**
         * Returns the raw JSON value of [tokens].
         *
         * Unlike [tokens], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("tokens") @ExcludeMissing fun _tokens(): JsonField<Int> = tokens

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
             * Returns a mutable builder for constructing an instance of [ToolUseTokensByModality].
             */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [ToolUseTokensByModality]. */
        class Builder internal constructor() {

            private var modality: JsonField<Modality> = JsonMissing.of()
            private var tokens: JsonField<Int> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(toolUseTokensByModality: ToolUseTokensByModality) = apply {
                modality = toolUseTokensByModality.modality
                tokens = toolUseTokensByModality.tokens
                additionalProperties = toolUseTokensByModality.additionalProperties.toMutableMap()
            }

            /** The modality associated with the token count. */
            fun modality(modality: Modality) = modality(JsonField.of(modality))

            /**
             * Sets [Builder.modality] to an arbitrary JSON value.
             *
             * You should usually call [Builder.modality] with a well-typed [Modality] value
             * instead. This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun modality(modality: JsonField<Modality>) = apply { this.modality = modality }

            /** Number of tokens for the modality. */
            fun tokens(tokens: Int) = tokens(JsonField.of(tokens))

            /**
             * Sets [Builder.tokens] to an arbitrary JSON value.
             *
             * You should usually call [Builder.tokens] with a well-typed [Int] value instead. This
             * method is primarily for setting the field to an undocumented or not yet supported
             * value.
             */
            fun tokens(tokens: JsonField<Int>) = apply { this.tokens = tokens }

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
             * Returns an immutable instance of [ToolUseTokensByModality].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): ToolUseTokensByModality =
                ToolUseTokensByModality(modality, tokens, additionalProperties.toMutableMap())
        }

        private var validated: Boolean = false

        fun validate(): ToolUseTokensByModality = apply {
            if (validated) {
                return@apply
            }

            modality().ifPresent { it.validate() }
            tokens()
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
            (modality.asKnown().getOrNull()?.validity() ?: 0) +
                (if (tokens.asKnown().isPresent) 1 else 0)

        /** The modality associated with the token count. */
        class Modality @JsonCreator private constructor(private val value: JsonField<String>) :
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

                @JvmField val TEXT = of("text")

                @JvmField val IMAGE = of("image")

                @JvmField val AUDIO = of("audio")

                @JvmField val VIDEO = of("video")

                @JvmField val DOCUMENT = of("document")

                @JvmStatic fun of(value: String) = Modality(JsonField.of(value))
            }

            /** An enum containing [Modality]'s known values. */
            enum class Known {
                TEXT,
                IMAGE,
                AUDIO,
                VIDEO,
                DOCUMENT,
            }

            /**
             * An enum containing [Modality]'s known values, as well as an [_UNKNOWN] member.
             *
             * An instance of [Modality] can contain an unknown value in a couple of cases:
             * - It was deserialized from data that doesn't match any known member. For example, if
             *   the SDK is on an older version than the API, then the API may respond with new
             *   members that the SDK is unaware of.
             * - It was constructed with an arbitrary value using the [of] method.
             */
            enum class Value {
                TEXT,
                IMAGE,
                AUDIO,
                VIDEO,
                DOCUMENT,
                /**
                 * An enum member indicating that [Modality] was instantiated with an unknown value.
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
                    TEXT -> Value.TEXT
                    IMAGE -> Value.IMAGE
                    AUDIO -> Value.AUDIO
                    VIDEO -> Value.VIDEO
                    DOCUMENT -> Value.DOCUMENT
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
                    TEXT -> Known.TEXT
                    IMAGE -> Known.IMAGE
                    AUDIO -> Known.AUDIO
                    VIDEO -> Known.VIDEO
                    DOCUMENT -> Known.DOCUMENT
                    else -> throw GeminiNextGenApiInvalidDataException("Unknown Modality: $value")
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

            fun validate(): Modality = apply {
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

                return other is Modality && value == other.value
            }

            override fun hashCode() = value.hashCode()

            override fun toString() = value.toString()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is ToolUseTokensByModality &&
                modality == other.modality &&
                tokens == other.tokens &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(modality, tokens, additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "ToolUseTokensByModality{modality=$modality, tokens=$tokens, additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is Usage &&
            cachedTokensByModality == other.cachedTokensByModality &&
            groundingToolCount == other.groundingToolCount &&
            inputTokensByModality == other.inputTokensByModality &&
            outputTokensByModality == other.outputTokensByModality &&
            toolUseTokensByModality == other.toolUseTokensByModality &&
            totalCachedTokens == other.totalCachedTokens &&
            totalInputTokens == other.totalInputTokens &&
            totalOutputTokens == other.totalOutputTokens &&
            totalThoughtTokens == other.totalThoughtTokens &&
            totalTokens == other.totalTokens &&
            totalToolUseTokens == other.totalToolUseTokens &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            cachedTokensByModality,
            groundingToolCount,
            inputTokensByModality,
            outputTokensByModality,
            toolUseTokensByModality,
            totalCachedTokens,
            totalInputTokens,
            totalOutputTokens,
            totalThoughtTokens,
            totalTokens,
            totalToolUseTokens,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "Usage{cachedTokensByModality=$cachedTokensByModality, groundingToolCount=$groundingToolCount, inputTokensByModality=$inputTokensByModality, outputTokensByModality=$outputTokensByModality, toolUseTokensByModality=$toolUseTokensByModality, totalCachedTokens=$totalCachedTokens, totalInputTokens=$totalInputTokens, totalOutputTokens=$totalOutputTokens, totalThoughtTokens=$totalThoughtTokens, totalTokens=$totalTokens, totalToolUseTokens=$totalToolUseTokens, additionalProperties=$additionalProperties}"
}
