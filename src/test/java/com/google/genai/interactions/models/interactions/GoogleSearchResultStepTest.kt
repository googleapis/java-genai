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

internal class GoogleSearchResultStepTest {

    @Test
    fun create() {
        val googleSearchResultStep =
            GoogleSearchResultStep.builder()
                .callId("call_id")
                .addResult(
                    GoogleSearchResultStep.Result.builder()
                        .searchSuggestions("search_suggestions")
                        .build()
                )
                .isError(true)
                .signature("U3RhaW5sZXNzIHJvY2tz")
                .build()

        assertThat(googleSearchResultStep.callId()).isEqualTo("call_id")
        assertThat(googleSearchResultStep.result())
            .containsExactly(
                GoogleSearchResultStep.Result.builder()
                    .searchSuggestions("search_suggestions")
                    .build()
            )
        assertThat(googleSearchResultStep.isError()).contains(true)
        assertThat(googleSearchResultStep.signature()).contains("U3RhaW5sZXNzIHJvY2tz")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val googleSearchResultStep =
            GoogleSearchResultStep.builder()
                .callId("call_id")
                .addResult(
                    GoogleSearchResultStep.Result.builder()
                        .searchSuggestions("search_suggestions")
                        .build()
                )
                .isError(true)
                .signature("U3RhaW5sZXNzIHJvY2tz")
                .build()

        val roundtrippedGoogleSearchResultStep =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(googleSearchResultStep),
                jacksonTypeRef<GoogleSearchResultStep>(),
            )

        assertThat(roundtrippedGoogleSearchResultStep).isEqualTo(googleSearchResultStep)
    }
}
