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
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class GoogleMapsResultTest {

    @Test
    fun create() {
        val googleMapsResult =
            GoogleMapsResult.builder()
                .addPlace(
                    GoogleMapsResult.Place.builder()
                        .name("name")
                        .placeId("place_id")
                        .addReviewSnippet(
                            GoogleMapsResult.Place.ReviewSnippet.builder()
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

        assertThat(googleMapsResult.places().getOrNull())
            .containsExactly(
                GoogleMapsResult.Place.builder()
                    .name("name")
                    .placeId("place_id")
                    .addReviewSnippet(
                        GoogleMapsResult.Place.ReviewSnippet.builder()
                            .reviewId("review_id")
                            .title("title")
                            .url("url")
                            .build()
                    )
                    .url("url")
                    .build()
            )
        assertThat(googleMapsResult.widgetContextToken()).contains("widget_context_token")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val googleMapsResult =
            GoogleMapsResult.builder()
                .addPlace(
                    GoogleMapsResult.Place.builder()
                        .name("name")
                        .placeId("place_id")
                        .addReviewSnippet(
                            GoogleMapsResult.Place.ReviewSnippet.builder()
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

        val roundtrippedGoogleMapsResult =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(googleMapsResult),
                jacksonTypeRef<GoogleMapsResult>(),
            )

        assertThat(roundtrippedGoogleMapsResult).isEqualTo(googleMapsResult)
    }
}
