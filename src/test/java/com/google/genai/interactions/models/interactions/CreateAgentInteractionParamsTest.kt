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
import java.time.OffsetDateTime
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CreateAgentInteractionParamsTest {

    @Test
    fun create() {
        val createAgentInteractionParams =
            CreateAgentInteractionParams.builder()
                .agent(CreateAgentInteractionParams.Agent.DEEP_RESEARCH_PRO_PREVIEW_12_2025)
                .input(
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
                .id("id")
                .agentConfig(DynamicAgentConfig.builder().build())
                .background(true)
                .created(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .previousInteractionId("previous_interaction_id")
                .responseFormatOfList(
                    listOf(
                        CreateAgentInteractionParams.ResponseFormat.InnerResponseFormat.ofAudio(
                            AudioResponseFormat.builder()
                                .bitRate(0)
                                .delivery(AudioResponseFormat.Delivery.INLINE)
                                .mimeType(AudioResponseFormat.MimeType.AUDIO_MP3)
                                .sampleRate(0)
                                .build()
                        )
                    )
                )
                .responseMimeType("response_mime_type")
                .addResponseModality(CreateAgentInteractionParams.ResponseModality.TEXT)
                .role("role")
                .serviceTier(CreateAgentInteractionParams.ServiceTier.FLEX)
                .status(CreateAgentInteractionParams.Status.IN_PROGRESS)
                .store(true)
                .stream(true)
                .systemInstruction("system_instruction")
                .addTool(
                    Function.builder()
                        .description("description")
                        .name("name")
                        .parameters(JsonValue.from(mapOf<String, Any>()))
                        .build()
                )
                .updated(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .usage(
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
                )
                .webhookConfig(
                    CreateAgentInteractionParams.WebhookConfig.builder()
                        .addUris("string")
                        .userMetadata(
                            CreateAgentInteractionParams.WebhookConfig.UserMetadata.builder()
                                .putAdditionalProperty("foo", JsonValue.from("bar"))
                                .build()
                        )
                        .build()
                )
                .build()

        assertThat(createAgentInteractionParams.agent())
            .isEqualTo(CreateAgentInteractionParams.Agent.DEEP_RESEARCH_PRO_PREVIEW_12_2025)
        assertThat(createAgentInteractionParams.input())
            .isEqualTo(
                CreateAgentInteractionParams.Input.ofTextContent(
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
            )
        assertThat(createAgentInteractionParams.id()).contains("id")
        assertThat(createAgentInteractionParams.agentConfig())
            .contains(
                CreateAgentInteractionParams.AgentConfig.ofDynamic(
                    DynamicAgentConfig.builder().build()
                )
            )
        assertThat(createAgentInteractionParams.background()).contains(true)
        assertThat(createAgentInteractionParams.created())
            .contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(createAgentInteractionParams.previousInteractionId())
            .contains("previous_interaction_id")
        assertThat(createAgentInteractionParams.responseFormat())
            .contains(
                CreateAgentInteractionParams.ResponseFormat.ofList(
                    listOf(
                        CreateAgentInteractionParams.ResponseFormat.InnerResponseFormat.ofAudio(
                            AudioResponseFormat.builder()
                                .bitRate(0)
                                .delivery(AudioResponseFormat.Delivery.INLINE)
                                .mimeType(AudioResponseFormat.MimeType.AUDIO_MP3)
                                .sampleRate(0)
                                .build()
                        )
                    )
                )
            )
        assertThat(createAgentInteractionParams.responseMimeType()).contains("response_mime_type")
        assertThat(createAgentInteractionParams.responseModalities().getOrNull())
            .containsExactly(CreateAgentInteractionParams.ResponseModality.TEXT)
        assertThat(createAgentInteractionParams.role()).contains("role")
        assertThat(createAgentInteractionParams.serviceTier())
            .contains(CreateAgentInteractionParams.ServiceTier.FLEX)
        assertThat(createAgentInteractionParams.status())
            .contains(CreateAgentInteractionParams.Status.IN_PROGRESS)
        assertThat(createAgentInteractionParams.store()).contains(true)
        assertThat(createAgentInteractionParams.stream()).contains(true)
        assertThat(createAgentInteractionParams.systemInstruction()).contains("system_instruction")
        assertThat(createAgentInteractionParams.tools().getOrNull())
            .containsExactly(
                Tool.ofFunction(
                    Function.builder()
                        .description("description")
                        .name("name")
                        .parameters(JsonValue.from(mapOf<String, Any>()))
                        .build()
                )
            )
        assertThat(createAgentInteractionParams.updated())
            .contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(createAgentInteractionParams.usage())
            .contains(
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
            )
        assertThat(createAgentInteractionParams.webhookConfig())
            .contains(
                CreateAgentInteractionParams.WebhookConfig.builder()
                    .addUris("string")
                    .userMetadata(
                        CreateAgentInteractionParams.WebhookConfig.UserMetadata.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val createAgentInteractionParams =
            CreateAgentInteractionParams.builder()
                .agent(CreateAgentInteractionParams.Agent.DEEP_RESEARCH_PRO_PREVIEW_12_2025)
                .input(
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
                .id("id")
                .agentConfig(DynamicAgentConfig.builder().build())
                .background(true)
                .created(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .previousInteractionId("previous_interaction_id")
                .responseFormatOfList(
                    listOf(
                        CreateAgentInteractionParams.ResponseFormat.InnerResponseFormat.ofAudio(
                            AudioResponseFormat.builder()
                                .bitRate(0)
                                .delivery(AudioResponseFormat.Delivery.INLINE)
                                .mimeType(AudioResponseFormat.MimeType.AUDIO_MP3)
                                .sampleRate(0)
                                .build()
                        )
                    )
                )
                .responseMimeType("response_mime_type")
                .addResponseModality(CreateAgentInteractionParams.ResponseModality.TEXT)
                .role("role")
                .serviceTier(CreateAgentInteractionParams.ServiceTier.FLEX)
                .status(CreateAgentInteractionParams.Status.IN_PROGRESS)
                .store(true)
                .stream(true)
                .systemInstruction("system_instruction")
                .addTool(
                    Function.builder()
                        .description("description")
                        .name("name")
                        .parameters(JsonValue.from(mapOf<String, Any>()))
                        .build()
                )
                .updated(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .usage(
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
                )
                .webhookConfig(
                    CreateAgentInteractionParams.WebhookConfig.builder()
                        .addUris("string")
                        .userMetadata(
                            CreateAgentInteractionParams.WebhookConfig.UserMetadata.builder()
                                .putAdditionalProperty("foo", JsonValue.from("bar"))
                                .build()
                        )
                        .build()
                )
                .build()

        val roundtrippedCreateAgentInteractionParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(createAgentInteractionParams),
                jacksonTypeRef<CreateAgentInteractionParams>(),
            )

        assertThat(roundtrippedCreateAgentInteractionParams).isEqualTo(createAgentInteractionParams)
    }
}
