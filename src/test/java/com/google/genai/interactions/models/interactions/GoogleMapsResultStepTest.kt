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

internal class GoogleMapsResultStepTest {

    @Test
    fun create() {
        val googleMapsResultStep =
            GoogleMapsResultStep.builder()
                .callId("call_id")
                .addResult(
                    GoogleMapsResultStep.Result.builder()
                        .addPlace(
                            GoogleMapsResultStep.Result.Place.builder()
                                .name("name")
                                .placeId("place_id")
                                .addReviewSnippet(
                                    GoogleMapsResultStep.Result.Place.ReviewSnippet.builder()
                                        .reviewId("review_id")
                                        .title("title")
                                        .url("url")
                                        .build()
                                )
                                .url("url")
                                .build()
                        )
                        .widgetContextToken("widget_context_token")
                        .build()
                )
                .signature("U3RhaW5sZXNzIHJvY2tz")
                .build()

        assertThat(googleMapsResultStep.callId()).isEqualTo("call_id")
        assertThat(googleMapsResultStep.result())
            .containsExactly(
                GoogleMapsResultStep.Result.builder()
                    .addPlace(
                        GoogleMapsResultStep.Result.Place.builder()
                            .name("name")
                            .placeId("place_id")
                            .addReviewSnippet(
                                GoogleMapsResultStep.Result.Place.ReviewSnippet.builder()
                                    .reviewId("review_id")
                                    .title("title")
                                    .url("url")
                                    .build()
                            )
                            .url("url")
                            .build()
                    )
                    .widgetContextToken("widget_context_token")
                    .build()
            )
        assertThat(googleMapsResultStep.signature()).contains("U3RhaW5sZXNzIHJvY2tz")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val googleMapsResultStep =
            GoogleMapsResultStep.builder()
                .callId("call_id")
                .addResult(
                    GoogleMapsResultStep.Result.builder()
                        .addPlace(
                            GoogleMapsResultStep.Result.Place.builder()
                                .name("name")
                                .placeId("place_id")
                                .addReviewSnippet(
                                    GoogleMapsResultStep.Result.Place.ReviewSnippet.builder()
                                        .reviewId("review_id")
                                        .title("title")
                                        .url("url")
                                        .build()
                                )
                                .url("url")
                                .build()
                        )
                        .widgetContextToken("widget_context_token")
                        .build()
                )
                .signature("U3RhaW5sZXNzIHJvY2tz")
                .build()

        val roundtrippedGoogleMapsResultStep =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(googleMapsResultStep),
                jacksonTypeRef<GoogleMapsResultStep>(),
            )

        assertThat(roundtrippedGoogleMapsResultStep).isEqualTo(googleMapsResultStep)
    }
}
