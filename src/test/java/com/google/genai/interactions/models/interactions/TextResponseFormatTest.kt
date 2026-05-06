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

internal class TextResponseFormatTest {

    @Test
    fun create() {
        val textResponseFormat =
            TextResponseFormat.builder()
                .mimeType(TextResponseFormat.MimeType.APPLICATION_JSON)
                .schema(
                    TextResponseFormat.Schema.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .build()

        assertThat(textResponseFormat.mimeType())
            .contains(TextResponseFormat.MimeType.APPLICATION_JSON)
        assertThat(textResponseFormat.schema())
            .contains(
                TextResponseFormat.Schema.builder()
                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val textResponseFormat =
            TextResponseFormat.builder()
                .mimeType(TextResponseFormat.MimeType.APPLICATION_JSON)
                .schema(
                    TextResponseFormat.Schema.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .build()

        val roundtrippedTextResponseFormat =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(textResponseFormat),
                jacksonTypeRef<TextResponseFormat>(),
            )

        assertThat(roundtrippedTextResponseFormat).isEqualTo(textResponseFormat)
    }
}
