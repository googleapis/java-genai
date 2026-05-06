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

internal class UsageTest {

    @Test
    fun create() {
        val usage =
            Usage.builder()
                .addCachedTokensByModality(
                    Usage.CachedTokensByModality.builder()
                        .modality(Usage.CachedTokensByModality.Modality.TEXT)
                        .tokens(0)
                        .build()
                )
                .addGroundingToolCount(
                    Usage.GroundingToolCount.builder()
                        .count(0)
                        .type(Usage.GroundingToolCount.Type.GOOGLE_SEARCH)
                        .build()
                )
                .addInputTokensByModality(
                    Usage.InputTokensByModality.builder()
                        .modality(Usage.InputTokensByModality.Modality.TEXT)
                        .tokens(0)
                        .build()
                )
                .addOutputTokensByModality(
                    Usage.OutputTokensByModality.builder()
                        .modality(Usage.OutputTokensByModality.Modality.TEXT)
                        .tokens(0)
                        .build()
                )
                .addToolUseTokensByModality(
                    Usage.ToolUseTokensByModality.builder()
                        .modality(Usage.ToolUseTokensByModality.Modality.TEXT)
                        .tokens(0)
                        .build()
                )
                .totalCachedTokens(0)
                .totalInputTokens(0)
                .totalOutputTokens(0)
                .totalThoughtTokens(0)
                .totalTokens(0)
                .totalToolUseTokens(0)
                .build()

        assertThat(usage.cachedTokensByModality().getOrNull())
            .containsExactly(
                Usage.CachedTokensByModality.builder()
                    .modality(Usage.CachedTokensByModality.Modality.TEXT)
                    .tokens(0)
                    .build()
            )
        assertThat(usage.groundingToolCount().getOrNull())
            .containsExactly(
                Usage.GroundingToolCount.builder()
                    .count(0)
                    .type(Usage.GroundingToolCount.Type.GOOGLE_SEARCH)
                    .build()
            )
        assertThat(usage.inputTokensByModality().getOrNull())
            .containsExactly(
                Usage.InputTokensByModality.builder()
                    .modality(Usage.InputTokensByModality.Modality.TEXT)
                    .tokens(0)
                    .build()
            )
        assertThat(usage.outputTokensByModality().getOrNull())
            .containsExactly(
                Usage.OutputTokensByModality.builder()
                    .modality(Usage.OutputTokensByModality.Modality.TEXT)
                    .tokens(0)
                    .build()
            )
        assertThat(usage.toolUseTokensByModality().getOrNull())
            .containsExactly(
                Usage.ToolUseTokensByModality.builder()
                    .modality(Usage.ToolUseTokensByModality.Modality.TEXT)
                    .tokens(0)
                    .build()
            )
        assertThat(usage.totalCachedTokens()).contains(0)
        assertThat(usage.totalInputTokens()).contains(0)
        assertThat(usage.totalOutputTokens()).contains(0)
        assertThat(usage.totalThoughtTokens()).contains(0)
        assertThat(usage.totalTokens()).contains(0)
        assertThat(usage.totalToolUseTokens()).contains(0)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val usage =
            Usage.builder()
                .addCachedTokensByModality(
                    Usage.CachedTokensByModality.builder()
                        .modality(Usage.CachedTokensByModality.Modality.TEXT)
                        .tokens(0)
                        .build()
                )
                .addGroundingToolCount(
                    Usage.GroundingToolCount.builder()
                        .count(0)
                        .type(Usage.GroundingToolCount.Type.GOOGLE_SEARCH)
                        .build()
                )
                .addInputTokensByModality(
                    Usage.InputTokensByModality.builder()
                        .modality(Usage.InputTokensByModality.Modality.TEXT)
                        .tokens(0)
                        .build()
                )
                .addOutputTokensByModality(
                    Usage.OutputTokensByModality.builder()
                        .modality(Usage.OutputTokensByModality.Modality.TEXT)
                        .tokens(0)
                        .build()
                )
                .addToolUseTokensByModality(
                    Usage.ToolUseTokensByModality.builder()
                        .modality(Usage.ToolUseTokensByModality.Modality.TEXT)
                        .tokens(0)
                        .build()
                )
                .totalCachedTokens(0)
                .totalInputTokens(0)
                .totalOutputTokens(0)
                .totalThoughtTokens(0)
                .totalTokens(0)
                .totalToolUseTokens(0)
                .build()

        val roundtrippedUsage =
            jsonMapper.readValue(jsonMapper.writeValueAsString(usage), jacksonTypeRef<Usage>())

        assertThat(roundtrippedUsage).isEqualTo(usage)
    }
}
