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
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class FileCitationTest {

    @Test
    fun create() {
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

        assertThat(fileCitation.customMetadata())
            .contains(
                FileCitation.CustomMetadata.builder()
                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                    .build()
            )
        assertThat(fileCitation.documentUri()).contains("document_uri")
        assertThat(fileCitation.endIndex()).contains(0)
        assertThat(fileCitation.fileName()).contains("file_name")
        assertThat(fileCitation.mediaId()).contains("media_id")
        assertThat(fileCitation.pageNumber()).contains(0)
        assertThat(fileCitation.source()).contains("source")
        assertThat(fileCitation.startIndex()).contains(0)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
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

        val roundtrippedFileCitation =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(fileCitation),
                jacksonTypeRef<FileCitation>(),
            )

        assertThat(roundtrippedFileCitation).isEqualTo(fileCitation)
    }
}
