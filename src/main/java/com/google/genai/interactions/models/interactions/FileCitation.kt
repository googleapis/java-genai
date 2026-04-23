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
import com.google.genai.interactions.core.toImmutable
import com.google.genai.interactions.errors.GeminiNextGenApiInvalidDataException
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** A file citation annotation. */
class FileCitation
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val type: JsonValue,
    private val customMetadata: JsonField<CustomMetadata>,
    private val documentUri: JsonField<String>,
    private val endIndex: JsonField<Int>,
    private val fileName: JsonField<String>,
    private val mediaId: JsonField<String>,
    private val pageNumber: JsonField<Int>,
    private val source: JsonField<String>,
    private val startIndex: JsonField<Int>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("custom_metadata")
        @ExcludeMissing
        customMetadata: JsonField<CustomMetadata> = JsonMissing.of(),
        @JsonProperty("document_uri")
        @ExcludeMissing
        documentUri: JsonField<String> = JsonMissing.of(),
        @JsonProperty("end_index") @ExcludeMissing endIndex: JsonField<Int> = JsonMissing.of(),
        @JsonProperty("file_name") @ExcludeMissing fileName: JsonField<String> = JsonMissing.of(),
        @JsonProperty("media_id") @ExcludeMissing mediaId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("page_number") @ExcludeMissing pageNumber: JsonField<Int> = JsonMissing.of(),
        @JsonProperty("source") @ExcludeMissing source: JsonField<String> = JsonMissing.of(),
        @JsonProperty("start_index") @ExcludeMissing startIndex: JsonField<Int> = JsonMissing.of(),
    ) : this(
        type,
        customMetadata,
        documentUri,
        endIndex,
        fileName,
        mediaId,
        pageNumber,
        source,
        startIndex,
        mutableMapOf(),
    )

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("file_citation")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * User provided metadata about the retrieved context.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun customMetadata(): Optional<CustomMetadata> = customMetadata.getOptional("custom_metadata")

    /**
     * The URI of the file.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun documentUri(): Optional<String> = documentUri.getOptional("document_uri")

    /**
     * End of the attributed segment, exclusive.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun endIndex(): Optional<Int> = endIndex.getOptional("end_index")

    /**
     * The name of the file.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun fileName(): Optional<String> = fileName.getOptional("file_name")

    /**
     * Media ID in-case of image citations, if applicable.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun mediaId(): Optional<String> = mediaId.getOptional("media_id")

    /**
     * Page number of the cited document, if applicable.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun pageNumber(): Optional<Int> = pageNumber.getOptional("page_number")

    /**
     * Source attributed for a portion of the text.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun source(): Optional<String> = source.getOptional("source")

    /**
     * Start of segment of the response that is attributed to this source.
     *
     * Index indicates the start of the segment, measured in bytes.
     *
     * @throws GeminiNextGenApiInvalidDataException if the JSON field has an unexpected type (e.g.
     *   if the server responded with an unexpected value).
     */
    fun startIndex(): Optional<Int> = startIndex.getOptional("start_index")

    /**
     * Returns the raw JSON value of [customMetadata].
     *
     * Unlike [customMetadata], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("custom_metadata")
    @ExcludeMissing
    fun _customMetadata(): JsonField<CustomMetadata> = customMetadata

    /**
     * Returns the raw JSON value of [documentUri].
     *
     * Unlike [documentUri], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("document_uri")
    @ExcludeMissing
    fun _documentUri(): JsonField<String> = documentUri

    /**
     * Returns the raw JSON value of [endIndex].
     *
     * Unlike [endIndex], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("end_index") @ExcludeMissing fun _endIndex(): JsonField<Int> = endIndex

    /**
     * Returns the raw JSON value of [fileName].
     *
     * Unlike [fileName], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("file_name") @ExcludeMissing fun _fileName(): JsonField<String> = fileName

    /**
     * Returns the raw JSON value of [mediaId].
     *
     * Unlike [mediaId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("media_id") @ExcludeMissing fun _mediaId(): JsonField<String> = mediaId

    /**
     * Returns the raw JSON value of [pageNumber].
     *
     * Unlike [pageNumber], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("page_number") @ExcludeMissing fun _pageNumber(): JsonField<Int> = pageNumber

    /**
     * Returns the raw JSON value of [source].
     *
     * Unlike [source], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("source") @ExcludeMissing fun _source(): JsonField<String> = source

    /**
     * Returns the raw JSON value of [startIndex].
     *
     * Unlike [startIndex], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("start_index") @ExcludeMissing fun _startIndex(): JsonField<Int> = startIndex

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

        /** Returns a mutable builder for constructing an instance of [FileCitation]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [FileCitation]. */
    class Builder internal constructor() {

        private var type: JsonValue = JsonValue.from("file_citation")
        private var customMetadata: JsonField<CustomMetadata> = JsonMissing.of()
        private var documentUri: JsonField<String> = JsonMissing.of()
        private var endIndex: JsonField<Int> = JsonMissing.of()
        private var fileName: JsonField<String> = JsonMissing.of()
        private var mediaId: JsonField<String> = JsonMissing.of()
        private var pageNumber: JsonField<Int> = JsonMissing.of()
        private var source: JsonField<String> = JsonMissing.of()
        private var startIndex: JsonField<Int> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(fileCitation: FileCitation) = apply {
            type = fileCitation.type
            customMetadata = fileCitation.customMetadata
            documentUri = fileCitation.documentUri
            endIndex = fileCitation.endIndex
            fileName = fileCitation.fileName
            mediaId = fileCitation.mediaId
            pageNumber = fileCitation.pageNumber
            source = fileCitation.source
            startIndex = fileCitation.startIndex
            additionalProperties = fileCitation.additionalProperties.toMutableMap()
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("file_citation")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /** User provided metadata about the retrieved context. */
        fun customMetadata(customMetadata: CustomMetadata) =
            customMetadata(JsonField.of(customMetadata))

        /**
         * Sets [Builder.customMetadata] to an arbitrary JSON value.
         *
         * You should usually call [Builder.customMetadata] with a well-typed [CustomMetadata] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun customMetadata(customMetadata: JsonField<CustomMetadata>) = apply {
            this.customMetadata = customMetadata
        }

        /** The URI of the file. */
        fun documentUri(documentUri: String) = documentUri(JsonField.of(documentUri))

        /**
         * Sets [Builder.documentUri] to an arbitrary JSON value.
         *
         * You should usually call [Builder.documentUri] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun documentUri(documentUri: JsonField<String>) = apply { this.documentUri = documentUri }

        /** End of the attributed segment, exclusive. */
        fun endIndex(endIndex: Int) = endIndex(JsonField.of(endIndex))

        /**
         * Sets [Builder.endIndex] to an arbitrary JSON value.
         *
         * You should usually call [Builder.endIndex] with a well-typed [Int] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun endIndex(endIndex: JsonField<Int>) = apply { this.endIndex = endIndex }

        /** The name of the file. */
        fun fileName(fileName: String) = fileName(JsonField.of(fileName))

        /**
         * Sets [Builder.fileName] to an arbitrary JSON value.
         *
         * You should usually call [Builder.fileName] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun fileName(fileName: JsonField<String>) = apply { this.fileName = fileName }

        /** Media ID in-case of image citations, if applicable. */
        fun mediaId(mediaId: String) = mediaId(JsonField.of(mediaId))

        /**
         * Sets [Builder.mediaId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.mediaId] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun mediaId(mediaId: JsonField<String>) = apply { this.mediaId = mediaId }

        /** Page number of the cited document, if applicable. */
        fun pageNumber(pageNumber: Int) = pageNumber(JsonField.of(pageNumber))

        /**
         * Sets [Builder.pageNumber] to an arbitrary JSON value.
         *
         * You should usually call [Builder.pageNumber] with a well-typed [Int] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun pageNumber(pageNumber: JsonField<Int>) = apply { this.pageNumber = pageNumber }

        /** Source attributed for a portion of the text. */
        fun source(source: String) = source(JsonField.of(source))

        /**
         * Sets [Builder.source] to an arbitrary JSON value.
         *
         * You should usually call [Builder.source] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun source(source: JsonField<String>) = apply { this.source = source }

        /**
         * Start of segment of the response that is attributed to this source.
         *
         * Index indicates the start of the segment, measured in bytes.
         */
        fun startIndex(startIndex: Int) = startIndex(JsonField.of(startIndex))

        /**
         * Sets [Builder.startIndex] to an arbitrary JSON value.
         *
         * You should usually call [Builder.startIndex] with a well-typed [Int] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun startIndex(startIndex: JsonField<Int>) = apply { this.startIndex = startIndex }

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
         * Returns an immutable instance of [FileCitation].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): FileCitation =
            FileCitation(
                type,
                customMetadata,
                documentUri,
                endIndex,
                fileName,
                mediaId,
                pageNumber,
                source,
                startIndex,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): FileCitation = apply {
        if (validated) {
            return@apply
        }

        _type().let {
            if (it != JsonValue.from("file_citation")) {
                throw GeminiNextGenApiInvalidDataException("'type' is invalid, received $it")
            }
        }
        customMetadata().ifPresent { it.validate() }
        documentUri()
        endIndex()
        fileName()
        mediaId()
        pageNumber()
        source()
        startIndex()
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
        type.let { if (it == JsonValue.from("file_citation")) 1 else 0 } +
            (customMetadata.asKnown().getOrNull()?.validity() ?: 0) +
            (if (documentUri.asKnown().isPresent) 1 else 0) +
            (if (endIndex.asKnown().isPresent) 1 else 0) +
            (if (fileName.asKnown().isPresent) 1 else 0) +
            (if (mediaId.asKnown().isPresent) 1 else 0) +
            (if (pageNumber.asKnown().isPresent) 1 else 0) +
            (if (source.asKnown().isPresent) 1 else 0) +
            (if (startIndex.asKnown().isPresent) 1 else 0)

    /** User provided metadata about the retrieved context. */
    class CustomMetadata
    @JsonCreator
    private constructor(
        @com.fasterxml.jackson.annotation.JsonValue
        private val additionalProperties: Map<String, JsonValue>
    ) {

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

        fun toBuilder() = Builder().from(this)

        companion object {

            /** Returns a mutable builder for constructing an instance of [CustomMetadata]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [CustomMetadata]. */
        class Builder internal constructor() {

            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(customMetadata: CustomMetadata) = apply {
                additionalProperties = customMetadata.additionalProperties.toMutableMap()
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
             * Returns an immutable instance of [CustomMetadata].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): CustomMetadata = CustomMetadata(additionalProperties.toImmutable())
        }

        private var validated: Boolean = false

        fun validate(): CustomMetadata = apply {
            if (validated) {
                return@apply
            }

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
            additionalProperties.count { (_, value) -> !value.isNull() && !value.isMissing() }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is CustomMetadata && additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() = "CustomMetadata{additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is FileCitation &&
            type == other.type &&
            customMetadata == other.customMetadata &&
            documentUri == other.documentUri &&
            endIndex == other.endIndex &&
            fileName == other.fileName &&
            mediaId == other.mediaId &&
            pageNumber == other.pageNumber &&
            source == other.source &&
            startIndex == other.startIndex &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            type,
            customMetadata,
            documentUri,
            endIndex,
            fileName,
            mediaId,
            pageNumber,
            source,
            startIndex,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "FileCitation{type=$type, customMetadata=$customMetadata, documentUri=$documentUri, endIndex=$endIndex, fileName=$fileName, mediaId=$mediaId, pageNumber=$pageNumber, source=$source, startIndex=$startIndex, additionalProperties=$additionalProperties}"
}
