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
import com.google.genai.interactions.core.jsonMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UrlCitationTest {

    @Test
    fun create() {
        val urlCitation =
            UrlCitation.builder().endIndex(0).startIndex(0).title("title").url("url").build()

        assertThat(urlCitation.endIndex()).contains(0)
        assertThat(urlCitation.startIndex()).contains(0)
        assertThat(urlCitation.title()).contains("title")
        assertThat(urlCitation.url()).contains("url")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val urlCitation =
            UrlCitation.builder().endIndex(0).startIndex(0).title("title").url("url").build()

        val roundtrippedUrlCitation =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(urlCitation),
                jacksonTypeRef<UrlCitation>(),
            )

        assertThat(roundtrippedUrlCitation).isEqualTo(urlCitation)
    }
}
