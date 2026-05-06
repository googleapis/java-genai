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

internal class GenerationConfigTest {

    @Test
    fun create() {
        val generationConfig =
            GenerationConfig.builder()
                .imageConfig(
                    ImageConfig.builder()
                        .aspectRatio(ImageConfig.AspectRatio._1_1)
                        .imageSize(ImageConfig.ImageSize._1_K)
                        .build()
                )
                .maxOutputTokens(0)
                .seed(0)
                .addSpeechConfig(
                    SpeechConfig.builder()
                        .language("language")
                        .speaker("speaker")
                        .voice("voice")
                        .build()
                )
                .addStopSequence("string")
                .temperature(0.0f)
                .thinkingLevel(ThinkingLevel.MINIMAL)
                .thinkingSummaries(GenerationConfig.ThinkingSummaries.AUTO)
                .toolChoice(ToolChoiceType.AUTO)
                .topP(0.0f)
                .build()

        assertThat(generationConfig.imageConfig())
            .contains(
                ImageConfig.builder()
                    .aspectRatio(ImageConfig.AspectRatio._1_1)
                    .imageSize(ImageConfig.ImageSize._1_K)
                    .build()
            )
        assertThat(generationConfig.maxOutputTokens()).contains(0)
        assertThat(generationConfig.seed()).contains(0)
        assertThat(generationConfig.speechConfig().getOrNull())
            .containsExactly(
                SpeechConfig.builder()
                    .language("language")
                    .speaker("speaker")
                    .voice("voice")
                    .build()
            )
        assertThat(generationConfig.stopSequences().getOrNull()).containsExactly("string")
        assertThat(generationConfig.temperature()).contains(0.0f)
        assertThat(generationConfig.thinkingLevel()).contains(ThinkingLevel.MINIMAL)
        assertThat(generationConfig.thinkingSummaries())
            .contains(GenerationConfig.ThinkingSummaries.AUTO)
        assertThat(generationConfig.toolChoice())
            .contains(GenerationConfig.ToolChoice.ofType(ToolChoiceType.AUTO))
        assertThat(generationConfig.topP()).contains(0.0f)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val generationConfig =
            GenerationConfig.builder()
                .imageConfig(
                    ImageConfig.builder()
                        .aspectRatio(ImageConfig.AspectRatio._1_1)
                        .imageSize(ImageConfig.ImageSize._1_K)
                        .build()
                )
                .maxOutputTokens(0)
                .seed(0)
                .addSpeechConfig(
                    SpeechConfig.builder()
                        .language("language")
                        .speaker("speaker")
                        .voice("voice")
                        .build()
                )
                .addStopSequence("string")
                .temperature(0.0f)
                .thinkingLevel(ThinkingLevel.MINIMAL)
                .thinkingSummaries(GenerationConfig.ThinkingSummaries.AUTO)
                .toolChoice(ToolChoiceType.AUTO)
                .topP(0.0f)
                .build()

        val roundtrippedGenerationConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(generationConfig),
                jacksonTypeRef<GenerationConfig>(),
            )

        assertThat(roundtrippedGenerationConfig).isEqualTo(generationConfig)
    }
}
