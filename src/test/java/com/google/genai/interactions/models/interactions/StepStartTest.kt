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

internal class StepStartTest {

    @Test
    fun create() {
        val stepStart =
            StepStart.builder()
                .index(0)
                .step(
                    UserInputStep.builder()
                        .addContent(
                            TextContent.builder()
                                .text("text")
                                .addAnnotation(
                                    UrlCitation.builder()
                                        .endIndex(0)
                                        .startIndex(0)
                                        .title("title")
                                        .url("url")
                                        .build()
                                )
                                .build()
                        )
                        .build()
                )
                .eventId("event_id")
                .build()

        assertThat(stepStart.index()).isEqualTo(0)
        assertThat(stepStart.step())
            .isEqualTo(
                Step.ofUserInput(
                    UserInputStep.builder()
                        .addContent(
                            TextContent.builder()
                                .text("text")
                                .addAnnotation(
                                    UrlCitation.builder()
                                        .endIndex(0)
                                        .startIndex(0)
                                        .title("title")
                                        .url("url")
                                        .build()
                                )
                                .build()
                        )
                        .build()
                )
            )
        assertThat(stepStart.eventId()).contains("event_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val stepStart =
            StepStart.builder()
                .index(0)
                .step(
                    UserInputStep.builder()
                        .addContent(
                            TextContent.builder()
                                .text("text")
                                .addAnnotation(
                                    UrlCitation.builder()
                                        .endIndex(0)
                                        .startIndex(0)
                                        .title("title")
                                        .url("url")
                                        .build()
                                )
                                .build()
                        )
                        .build()
                )
                .eventId("event_id")
                .build()

        val roundtrippedStepStart =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(stepStart),
                jacksonTypeRef<StepStart>(),
            )

        assertThat(roundtrippedStepStart).isEqualTo(stepStart)
    }
}
