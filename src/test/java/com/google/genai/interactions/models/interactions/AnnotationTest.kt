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

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.google.genai.interactions.core.JsonValue
import com.google.genai.interactions.core.jsonMapper
import com.google.genai.interactions.errors.GeminiNextGenApiInvalidDataException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class AnnotationTest {

    @Test
    fun ofUrlCitation() {
        val urlCitation =
            UrlCitation.builder().endIndex(0).startIndex(0).title("title").url("url").build()

        val annotation = Annotation.ofUrlCitation(urlCitation)

        assertThat(annotation.urlCitation()).contains(urlCitation)
        assertThat(annotation.fileCitation()).isEmpty
        assertThat(annotation.placeCitation()).isEmpty
    }

    @Test
    fun ofUrlCitationRoundtrip() {
        val jsonMapper = jsonMapper()
        val annotation =
            Annotation.ofUrlCitation(
                UrlCitation.builder().endIndex(0).startIndex(0).title("title").url("url").build()
            )

        val roundtrippedAnnotation =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(annotation),
                jacksonTypeRef<Annotation>(),
            )

        assertThat(roundtrippedAnnotation).isEqualTo(annotation)
    }

    @Test
    fun ofFileCitation() {
        val fileCitation =
            FileCitation.builder()
                .customMetadata(
                    FileCitation.CustomMetadata.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .documentUri("document_uri")
                .endIndex(0)
                .fileName("file_name")
                .mediaId("media_id")
                .pageNumber(0)
                .source("source")
                .startIndex(0)
                .build()

        val annotation = Annotation.ofFileCitation(fileCitation)

        assertThat(annotation.urlCitation()).isEmpty
        assertThat(annotation.fileCitation()).contains(fileCitation)
        assertThat(annotation.placeCitation()).isEmpty
    }

    @Test
    fun ofFileCitationRoundtrip() {
        val jsonMapper = jsonMapper()
        val annotation =
            Annotation.ofFileCitation(
                FileCitation.builder()
                    .customMetadata(
                        FileCitation.CustomMetadata.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .documentUri("document_uri")
                    .endIndex(0)
                    .fileName("file_name")
                    .mediaId("media_id")
                    .pageNumber(0)
                    .source("source")
                    .startIndex(0)
                    .build()
            )

        val roundtrippedAnnotation =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(annotation),
                jacksonTypeRef<Annotation>(),
            )

        assertThat(roundtrippedAnnotation).isEqualTo(annotation)
    }

    @Test
    fun ofPlaceCitation() {
        val placeCitation =
            PlaceCitation.builder()
                .endIndex(0)
                .name("name")
                .placeId("place_id")
                .addReviewSnippet(
                    PlaceCitation.ReviewSnippet.builder()
                        .reviewId("review_id")
                        .title("title")
                        .url("url")
                        .build()
                )
                .startIndex(0)
                .url("url")
                .build()

        val annotation = Annotation.ofPlaceCitation(placeCitation)

        assertThat(annotation.urlCitation()).isEmpty
        assertThat(annotation.fileCitation()).isEmpty
        assertThat(annotation.placeCitation()).contains(placeCitation)
    }

    @Test
    fun ofPlaceCitationRoundtrip() {
        val jsonMapper = jsonMapper()
        val annotation =
            Annotation.ofPlaceCitation(
                PlaceCitation.builder()
                    .endIndex(0)
                    .name("name")
                    .placeId("place_id")
                    .addReviewSnippet(
                        PlaceCitation.ReviewSnippet.builder()
                            .reviewId("review_id")
                            .title("title")
                            .url("url")
                            .build()
                    )
                    .startIndex(0)
                    .url("url")
                    .build()
            )

        val roundtrippedAnnotation =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(annotation),
                jacksonTypeRef<Annotation>(),
            )

        assertThat(roundtrippedAnnotation).isEqualTo(annotation)
    }

    enum class IncompatibleJsonShapeTestCase(val value: JsonValue) {
        BOOLEAN(JsonValue.from(false)),
        STRING(JsonValue.from("invalid")),
        INTEGER(JsonValue.from(-1)),
        FLOAT(JsonValue.from(3.14)),
        ARRAY(JsonValue.from(listOf("invalid", "array"))),
    }

    @ParameterizedTest
    @EnumSource
    fun incompatibleJsonShapeDeserializesToUnknown(testCase: IncompatibleJsonShapeTestCase) {
        val annotation = jsonMapper().convertValue(testCase.value, jacksonTypeRef<Annotation>())

        val e = assertThrows<GeminiNextGenApiInvalidDataException> { annotation.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
