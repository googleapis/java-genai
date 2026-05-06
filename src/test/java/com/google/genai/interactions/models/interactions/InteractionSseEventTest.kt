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
import com.google.genai.interactions.errors.GeminiNextGenApiInvalidDataException
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class InteractionSseEventTest {

    @Test
    fun ofCreated() {
        val created =
            InteractionCreatedEvent.builder()
                .interaction(
                    Interaction.builder()
                        .id(
                            "v1_ChdXS0l4YWZXTk9xbk0xZThQczhEcmlROBIXV0tJeGFmV05PcW5NMWU4UHM4RHJpUTg"
                        )
                        .created(OffsetDateTime.parse("2025-12-04T15:01:45Z"))
                        .status(Interaction.Status.COMPLETED)
                        .addStep(
                            ModelOutputStep.builder()
                                .addContent(
                                    TextContent.builder()
                                        .text(
                                            "Hello! I'm doing well, functioning as expected. Thank you for asking! How are you doing today?"
                                        )
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
                        .updated(OffsetDateTime.parse("2025-12-04T15:01:45Z"))
                        .agent(Interaction.Agent.DEEP_RESEARCH_PRO_PREVIEW_12_2025)
                        .agentConfig(DynamicAgentConfig.builder().build())
                        .generationConfig(
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
                        )
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
                        .model(Model.GEMINI_3_FLASH_PREVIEW)
                        .previousInteractionId("previous_interaction_id")
                        .responseFormatOfList(
                            listOf(
                                Interaction.ResponseFormat.InnerResponseFormat.ofAudio(
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
                        .addResponseModality(Interaction.ResponseModality.TEXT)
                        .role("role")
                        .serviceTier(Interaction.ServiceTier.FLEX)
                        .systemInstruction("system_instruction")
                        .addTool(
                            Function.builder()
                                .description("description")
                                .name("name")
                                .parameters(JsonValue.from(mapOf<String, Any>()))
                                .build()
                        )
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
                                        .tokens(7)
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
                                .totalInputTokens(7)
                                .totalOutputTokens(23)
                                .totalThoughtTokens(49)
                                .totalTokens(79)
                                .totalToolUseTokens(0)
                                .build()
                        )
                        .webhookConfig(
                            Interaction.WebhookConfig.builder()
                                .addUris("string")
                                .userMetadata(
                                    Interaction.WebhookConfig.UserMetadata.builder()
                                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                                        .build()
                                )
                                .build()
                        )
                        .build()
                )
                .eventId("event_id")
                .build()

        val interactionSseEvent = InteractionSseEvent.ofCreated(created)

        assertThat(interactionSseEvent.created()).contains(created)
        assertThat(interactionSseEvent.completed()).isEmpty
        assertThat(interactionSseEvent.statusUpdate()).isEmpty
        assertThat(interactionSseEvent.error()).isEmpty
        assertThat(interactionSseEvent.stepStart()).isEmpty
        assertThat(interactionSseEvent.stepDelta()).isEmpty
        assertThat(interactionSseEvent.stepStop()).isEmpty
    }

    @Test
    fun ofCreatedRoundtrip() {
        val jsonMapper = jsonMapper()
        val interactionSseEvent =
            InteractionSseEvent.ofCreated(
                InteractionCreatedEvent.builder()
                    .interaction(
                        Interaction.builder()
                            .id(
                                "v1_ChdXS0l4YWZXTk9xbk0xZThQczhEcmlROBIXV0tJeGFmV05PcW5NMWU4UHM4RHJpUTg"
                            )
                            .created(OffsetDateTime.parse("2025-12-04T15:01:45Z"))
                            .status(Interaction.Status.COMPLETED)
                            .addStep(
                                ModelOutputStep.builder()
                                    .addContent(
                                        TextContent.builder()
                                            .text(
                                                "Hello! I'm doing well, functioning as expected. Thank you for asking! How are you doing today?"
                                            )
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
                            .updated(OffsetDateTime.parse("2025-12-04T15:01:45Z"))
                            .agent(Interaction.Agent.DEEP_RESEARCH_PRO_PREVIEW_12_2025)
                            .agentConfig(DynamicAgentConfig.builder().build())
                            .generationConfig(
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
                            )
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
                            .model(Model.GEMINI_3_FLASH_PREVIEW)
                            .previousInteractionId("previous_interaction_id")
                            .responseFormatOfList(
                                listOf(
                                    Interaction.ResponseFormat.InnerResponseFormat.ofAudio(
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
                            .addResponseModality(Interaction.ResponseModality.TEXT)
                            .role("role")
                            .serviceTier(Interaction.ServiceTier.FLEX)
                            .systemInstruction("system_instruction")
                            .addTool(
                                Function.builder()
                                    .description("description")
                                    .name("name")
                                    .parameters(JsonValue.from(mapOf<String, Any>()))
                                    .build()
                            )
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
                                            .tokens(7)
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
                                    .totalInputTokens(7)
                                    .totalOutputTokens(23)
                                    .totalThoughtTokens(49)
                                    .totalTokens(79)
                                    .totalToolUseTokens(0)
                                    .build()
                            )
                            .webhookConfig(
                                Interaction.WebhookConfig.builder()
                                    .addUris("string")
                                    .userMetadata(
                                        Interaction.WebhookConfig.UserMetadata.builder()
                                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                                            .build()
                                    )
                                    .build()
                            )
                            .build()
                    )
                    .eventId("event_id")
                    .build()
            )

        val roundtrippedInteractionSseEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(interactionSseEvent),
                jacksonTypeRef<InteractionSseEvent>(),
            )

        assertThat(roundtrippedInteractionSseEvent).isEqualTo(interactionSseEvent)
    }

    @Test
    fun ofCompleted() {
        val completed =
            InteractionCompletedEvent.builder()
                .interaction(
                    Interaction.builder()
                        .id(
                            "v1_ChdXS0l4YWZXTk9xbk0xZThQczhEcmlROBIXV0tJeGFmV05PcW5NMWU4UHM4RHJpUTg"
                        )
                        .created(OffsetDateTime.parse("2025-12-04T15:01:45Z"))
                        .status(Interaction.Status.COMPLETED)
                        .addStep(
                            ModelOutputStep.builder()
                                .addContent(
                                    TextContent.builder()
                                        .text(
                                            "Hello! I'm doing well, functioning as expected. Thank you for asking! How are you doing today?"
                                        )
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
                        .updated(OffsetDateTime.parse("2025-12-04T15:01:45Z"))
                        .agent(Interaction.Agent.DEEP_RESEARCH_PRO_PREVIEW_12_2025)
                        .agentConfig(DynamicAgentConfig.builder().build())
                        .generationConfig(
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
                        )
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
                        .model(Model.GEMINI_3_FLASH_PREVIEW)
                        .previousInteractionId("previous_interaction_id")
                        .responseFormatOfList(
                            listOf(
                                Interaction.ResponseFormat.InnerResponseFormat.ofAudio(
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
                        .addResponseModality(Interaction.ResponseModality.TEXT)
                        .role("role")
                        .serviceTier(Interaction.ServiceTier.FLEX)
                        .systemInstruction("system_instruction")
                        .addTool(
                            Function.builder()
                                .description("description")
                                .name("name")
                                .parameters(JsonValue.from(mapOf<String, Any>()))
                                .build()
                        )
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
                                        .tokens(7)
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
                                .totalInputTokens(7)
                                .totalOutputTokens(23)
                                .totalThoughtTokens(49)
                                .totalTokens(79)
                                .totalToolUseTokens(0)
                                .build()
                        )
                        .webhookConfig(
                            Interaction.WebhookConfig.builder()
                                .addUris("string")
                                .userMetadata(
                                    Interaction.WebhookConfig.UserMetadata.builder()
                                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                                        .build()
                                )
                                .build()
                        )
                        .build()
                )
                .eventId("event_id")
                .build()

        val interactionSseEvent = InteractionSseEvent.ofCompleted(completed)

        assertThat(interactionSseEvent.created()).isEmpty
        assertThat(interactionSseEvent.completed()).contains(completed)
        assertThat(interactionSseEvent.statusUpdate()).isEmpty
        assertThat(interactionSseEvent.error()).isEmpty
        assertThat(interactionSseEvent.stepStart()).isEmpty
        assertThat(interactionSseEvent.stepDelta()).isEmpty
        assertThat(interactionSseEvent.stepStop()).isEmpty
    }

    @Test
    fun ofCompletedRoundtrip() {
        val jsonMapper = jsonMapper()
        val interactionSseEvent =
            InteractionSseEvent.ofCompleted(
                InteractionCompletedEvent.builder()
                    .interaction(
                        Interaction.builder()
                            .id(
                                "v1_ChdXS0l4YWZXTk9xbk0xZThQczhEcmlROBIXV0tJeGFmV05PcW5NMWU4UHM4RHJpUTg"
                            )
                            .created(OffsetDateTime.parse("2025-12-04T15:01:45Z"))
                            .status(Interaction.Status.COMPLETED)
                            .addStep(
                                ModelOutputStep.builder()
                                    .addContent(
                                        TextContent.builder()
                                            .text(
                                                "Hello! I'm doing well, functioning as expected. Thank you for asking! How are you doing today?"
                                            )
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
                            .updated(OffsetDateTime.parse("2025-12-04T15:01:45Z"))
                            .agent(Interaction.Agent.DEEP_RESEARCH_PRO_PREVIEW_12_2025)
                            .agentConfig(DynamicAgentConfig.builder().build())
                            .generationConfig(
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
                            )
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
                            .model(Model.GEMINI_3_FLASH_PREVIEW)
                            .previousInteractionId("previous_interaction_id")
                            .responseFormatOfList(
                                listOf(
                                    Interaction.ResponseFormat.InnerResponseFormat.ofAudio(
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
                            .addResponseModality(Interaction.ResponseModality.TEXT)
                            .role("role")
                            .serviceTier(Interaction.ServiceTier.FLEX)
                            .systemInstruction("system_instruction")
                            .addTool(
                                Function.builder()
                                    .description("description")
                                    .name("name")
                                    .parameters(JsonValue.from(mapOf<String, Any>()))
                                    .build()
                            )
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
                                            .tokens(7)
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
                                    .totalInputTokens(7)
                                    .totalOutputTokens(23)
                                    .totalThoughtTokens(49)
                                    .totalTokens(79)
                                    .totalToolUseTokens(0)
                                    .build()
                            )
                            .webhookConfig(
                                Interaction.WebhookConfig.builder()
                                    .addUris("string")
                                    .userMetadata(
                                        Interaction.WebhookConfig.UserMetadata.builder()
                                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                                            .build()
                                    )
                                    .build()
                            )
                            .build()
                    )
                    .eventId("event_id")
                    .build()
            )

        val roundtrippedInteractionSseEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(interactionSseEvent),
                jacksonTypeRef<InteractionSseEvent>(),
            )

        assertThat(roundtrippedInteractionSseEvent).isEqualTo(interactionSseEvent)
    }

    @Test
    fun ofStatusUpdate() {
        val statusUpdate =
            InteractionStatusUpdate.builder()
                .interactionId("interaction_id")
                .status(InteractionStatusUpdate.Status.IN_PROGRESS)
                .eventId("event_id")
                .build()

        val interactionSseEvent = InteractionSseEvent.ofStatusUpdate(statusUpdate)

        assertThat(interactionSseEvent.created()).isEmpty
        assertThat(interactionSseEvent.completed()).isEmpty
        assertThat(interactionSseEvent.statusUpdate()).contains(statusUpdate)
        assertThat(interactionSseEvent.error()).isEmpty
        assertThat(interactionSseEvent.stepStart()).isEmpty
        assertThat(interactionSseEvent.stepDelta()).isEmpty
        assertThat(interactionSseEvent.stepStop()).isEmpty
    }

    @Test
    fun ofStatusUpdateRoundtrip() {
        val jsonMapper = jsonMapper()
        val interactionSseEvent =
            InteractionSseEvent.ofStatusUpdate(
                InteractionStatusUpdate.builder()
                    .interactionId("interaction_id")
                    .status(InteractionStatusUpdate.Status.IN_PROGRESS)
                    .eventId("event_id")
                    .build()
            )

        val roundtrippedInteractionSseEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(interactionSseEvent),
                jacksonTypeRef<InteractionSseEvent>(),
            )

        assertThat(roundtrippedInteractionSseEvent).isEqualTo(interactionSseEvent)
    }

    @Test
    fun ofError() {
        val error =
            ErrorEvent.builder()
                .error(ErrorEvent.Error.builder().code("code").message("message").build())
                .eventId("event_id")
                .build()

        val interactionSseEvent = InteractionSseEvent.ofError(error)

        assertThat(interactionSseEvent.created()).isEmpty
        assertThat(interactionSseEvent.completed()).isEmpty
        assertThat(interactionSseEvent.statusUpdate()).isEmpty
        assertThat(interactionSseEvent.error()).contains(error)
        assertThat(interactionSseEvent.stepStart()).isEmpty
        assertThat(interactionSseEvent.stepDelta()).isEmpty
        assertThat(interactionSseEvent.stepStop()).isEmpty
    }

    @Test
    fun ofErrorRoundtrip() {
        val jsonMapper = jsonMapper()
        val interactionSseEvent =
            InteractionSseEvent.ofError(
                ErrorEvent.builder()
                    .error(ErrorEvent.Error.builder().code("code").message("message").build())
                    .eventId("event_id")
                    .build()
            )

        val roundtrippedInteractionSseEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(interactionSseEvent),
                jacksonTypeRef<InteractionSseEvent>(),
            )

        assertThat(roundtrippedInteractionSseEvent).isEqualTo(interactionSseEvent)
    }

    @Test
    fun ofStepStart() {
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

        val interactionSseEvent = InteractionSseEvent.ofStepStart(stepStart)

        assertThat(interactionSseEvent.created()).isEmpty
        assertThat(interactionSseEvent.completed()).isEmpty
        assertThat(interactionSseEvent.statusUpdate()).isEmpty
        assertThat(interactionSseEvent.error()).isEmpty
        assertThat(interactionSseEvent.stepStart()).contains(stepStart)
        assertThat(interactionSseEvent.stepDelta()).isEmpty
        assertThat(interactionSseEvent.stepStop()).isEmpty
    }

    @Test
    fun ofStepStartRoundtrip() {
        val jsonMapper = jsonMapper()
        val interactionSseEvent =
            InteractionSseEvent.ofStepStart(
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
            )

        val roundtrippedInteractionSseEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(interactionSseEvent),
                jacksonTypeRef<InteractionSseEvent>(),
            )

        assertThat(roundtrippedInteractionSseEvent).isEqualTo(interactionSseEvent)
    }

    @Test
    fun ofStepDelta() {
        val stepDelta = StepDelta.builder().textDelta("text").index(0).eventId("event_id").build()

        val interactionSseEvent = InteractionSseEvent.ofStepDelta(stepDelta)

        assertThat(interactionSseEvent.created()).isEmpty
        assertThat(interactionSseEvent.completed()).isEmpty
        assertThat(interactionSseEvent.statusUpdate()).isEmpty
        assertThat(interactionSseEvent.error()).isEmpty
        assertThat(interactionSseEvent.stepStart()).isEmpty
        assertThat(interactionSseEvent.stepDelta()).contains(stepDelta)
        assertThat(interactionSseEvent.stepStop()).isEmpty
    }

    @Test
    fun ofStepDeltaRoundtrip() {
        val jsonMapper = jsonMapper()
        val interactionSseEvent =
            InteractionSseEvent.ofStepDelta(
                StepDelta.builder().textDelta("text").index(0).eventId("event_id").build()
            )

        val roundtrippedInteractionSseEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(interactionSseEvent),
                jacksonTypeRef<InteractionSseEvent>(),
            )

        assertThat(roundtrippedInteractionSseEvent).isEqualTo(interactionSseEvent)
    }

    @Test
    fun ofStepStop() {
        val stepStop = StepStop.builder().index(0).eventId("event_id").build()

        val interactionSseEvent = InteractionSseEvent.ofStepStop(stepStop)

        assertThat(interactionSseEvent.created()).isEmpty
        assertThat(interactionSseEvent.completed()).isEmpty
        assertThat(interactionSseEvent.statusUpdate()).isEmpty
        assertThat(interactionSseEvent.error()).isEmpty
        assertThat(interactionSseEvent.stepStart()).isEmpty
        assertThat(interactionSseEvent.stepDelta()).isEmpty
        assertThat(interactionSseEvent.stepStop()).contains(stepStop)
    }

    @Test
    fun ofStepStopRoundtrip() {
        val jsonMapper = jsonMapper()
        val interactionSseEvent =
            InteractionSseEvent.ofStepStop(StepStop.builder().index(0).eventId("event_id").build())

        val roundtrippedInteractionSseEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(interactionSseEvent),
                jacksonTypeRef<InteractionSseEvent>(),
            )

        assertThat(roundtrippedInteractionSseEvent).isEqualTo(interactionSseEvent)
    }

    enum class IncompatibleJsonShapeTestCase(val value: JsonValue) {
        BOOLEAN(JsonValue.from(false)),
        STRING(JsonValue.from("invalid")),
        INTEGER(JsonValue.from(-1)),
        FLOAT(JsonValue.from(3.14)),
        ARRAY(JsonValue.from(listOf("invalid", "array"))),
    }

    @ParameterizedTest
    @EnumSource
    fun incompatibleJsonShapeDeserializesToUnknown(testCase: IncompatibleJsonShapeTestCase) {
        val interactionSseEvent =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<InteractionSseEvent>())

        val e =
            assertThrows<GeminiNextGenApiInvalidDataException> { interactionSseEvent.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
