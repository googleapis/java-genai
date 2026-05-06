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

internal class GoogleSearchCallStepTest {

    @Test
    fun create() {
        val googleSearchCallStep =
            GoogleSearchCallStep.builder()
                .id("id")
                .arguments(GoogleSearchCallStep.Arguments.builder().addQuery("string").build())
                .searchType(GoogleSearchCallStep.SearchType.WEB_SEARCH)
                .signature("U3RhaW5sZXNzIHJvY2tz")
                .build()

        assertThat(googleSearchCallStep.id()).isEqualTo("id")
        assertThat(googleSearchCallStep.arguments())
            .isEqualTo(GoogleSearchCallStep.Arguments.builder().addQuery("string").build())
        assertThat(googleSearchCallStep.searchType())
            .contains(GoogleSearchCallStep.SearchType.WEB_SEARCH)
        assertThat(googleSearchCallStep.signature()).contains("U3RhaW5sZXNzIHJvY2tz")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val googleSearchCallStep =
            GoogleSearchCallStep.builder()
                .id("id")
                .arguments(GoogleSearchCallStep.Arguments.builder().addQuery("string").build())
                .searchType(GoogleSearchCallStep.SearchType.WEB_SEARCH)
                .signature("U3RhaW5sZXNzIHJvY2tz")
                .build()

        val roundtrippedGoogleSearchCallStep =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(googleSearchCallStep),
                jacksonTypeRef<GoogleSearchCallStep>(),
            )

        assertThat(roundtrippedGoogleSearchCallStep).isEqualTo(googleSearchCallStep)
    }
}
