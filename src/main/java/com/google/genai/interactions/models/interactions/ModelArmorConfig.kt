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

/**
 * Configuration for Model Armor.
 *
 * Model Armor is a Google Cloud service that provides safety and security filtering for prompts and
 * responses. It helps protect your AI applications from risks such as harmful content, sensitive
 * data leakage, and prompt injection attacks.
 */
class ModelArmorConfig
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val promptTemplateName: JsonField<String>,
    private val responseTemplateName: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("promptTemplateName")
        @ExcludeMissing
        promptTemplateName: JsonField<String> = JsonMissing.of(),
        @JsonProperty("responseTemplateName")
        @ExcludeMissing
        responseTemplateName: JsonField<String> = JsonMissing.of(),
    ) : this(promptTemplateName, responseTemplateName, mutableMapOf())

    /**
     * The resource name of the Model Armor template to use for prompt screening.
     *
     * A Model Armor template is a set of customized filters and thresholds that define how Model
     * Armor screens content. If specified, Model Armor will use this template to check the user's
     * prompt for safety and security risks before it is sent to the model.
     *
     * The name must be in the format
     * `projects/{project}/locations/{location}/templates/{template}`.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun promptTemplateName(): Optional<String> =
        promptTemplateName.getOptional("promptTemplateName")

    /**
     * The resource name of the Model Armor template to use for response screening.
     *
     * A Model Armor template is a set of customized filters and thresholds that define how Model
     * Armor screens content. If specified, Model Armor will use this template to check the model's
     * response for safety and security risks before it is returned to the user.
     *
     * The name must be in the format
     * `projects/{project}/locations/{location}/templates/{template}`.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun responseTemplateName(): Optional<String> =
        responseTemplateName.getOptional("responseTemplateName")

    /**
     * Returns the raw JSON value of [promptTemplateName].
     *
     * Unlike [promptTemplateName], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("promptTemplateName")
    @ExcludeMissing
    fun _promptTemplateName(): JsonField<String> = promptTemplateName

    /**
     * Returns the raw JSON value of [responseTemplateName].
     *
     * Unlike [responseTemplateName], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("responseTemplateName")
    @ExcludeMissing
    fun _responseTemplateName(): JsonField<String> = responseTemplateName

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

        /** Returns a mutable builder for constructing an instance of [ModelArmorConfig]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [ModelArmorConfig]. */
    class Builder internal constructor() {

        private var promptTemplateName: JsonField<String> = JsonMissing.of()
        private var responseTemplateName: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(modelArmorConfig: ModelArmorConfig) = apply {
            promptTemplateName = modelArmorConfig.promptTemplateName
            responseTemplateName = modelArmorConfig.responseTemplateName
            additionalProperties = modelArmorConfig.additionalProperties.toMutableMap()
        }

        /**
         * The resource name of the Model Armor template to use for prompt screening.
         *
         * A Model Armor template is a set of customized filters and thresholds that define how
         * Model Armor screens content. If specified, Model Armor will use this template to check
         * the user's prompt for safety and security risks before it is sent to the model.
         *
         * The name must be in the format
         * `projects/{project}/locations/{location}/templates/{template}`.
         */
        fun promptTemplateName(promptTemplateName: String) =
            promptTemplateName(JsonField.of(promptTemplateName))

        /**
         * Sets [Builder.promptTemplateName] to an arbitrary JSON value.
         *
         * You should usually call [Builder.promptTemplateName] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun promptTemplateName(promptTemplateName: JsonField<String>) = apply {
            this.promptTemplateName = promptTemplateName
        }

        /**
         * The resource name of the Model Armor template to use for response screening.
         *
         * A Model Armor template is a set of customized filters and thresholds that define how
         * Model Armor screens content. If specified, Model Armor will use this template to check
         * the model's response for safety and security risks before it is returned to the user.
         *
         * The name must be in the format
         * `projects/{project}/locations/{location}/templates/{template}`.
         */
        fun responseTemplateName(responseTemplateName: String) =
            responseTemplateName(JsonField.of(responseTemplateName))

        /**
         * Sets [Builder.responseTemplateName] to an arbitrary JSON value.
         *
         * You should usually call [Builder.responseTemplateName] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun responseTemplateName(responseTemplateName: JsonField<String>) = apply {
            this.responseTemplateName = responseTemplateName
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
         * Returns an immutable instance of [ModelArmorConfig].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): ModelArmorConfig =
            ModelArmorConfig(
                promptTemplateName,
                responseTemplateName,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): ModelArmorConfig = apply {
        if (validated) {
            return@apply
        }

        promptTemplateName()
        responseTemplateName()
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
        (if (promptTemplateName.asKnown().isPresent) 1 else 0) +
            (if (responseTemplateName.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ModelArmorConfig &&
            promptTemplateName == other.promptTemplateName &&
            responseTemplateName == other.responseTemplateName &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(promptTemplateName, responseTemplateName, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "ModelArmorConfig{promptTemplateName=$promptTemplateName, responseTemplateName=$responseTemplateName, additionalProperties=$additionalProperties}"
}
