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

package com.google.genai.interactions.services

import com.google.genai.interactions.client.GeminiNextGenApiClient
import com.google.genai.interactions.client.okhttp.GeminiNextGenApiOkHttpClient
import com.google.genai.interactions.core.JsonValue
import com.google.genai.interactions.models.interactions.AudioResponseFormat
import com.google.genai.interactions.models.interactions.CreateModelInteractionParams
import com.google.genai.interactions.models.interactions.Function
import com.google.genai.interactions.models.interactions.GenerationConfig
import com.google.genai.interactions.models.interactions.ImageConfig
import com.google.genai.interactions.models.interactions.InteractionCreateParams
import com.google.genai.interactions.models.interactions.Model
import com.google.genai.interactions.models.interactions.SpeechConfig
import com.google.genai.interactions.models.interactions.TextContent
import com.google.genai.interactions.models.interactions.ThinkingLevel
import com.google.genai.interactions.models.interactions.ToolChoiceType
import com.google.genai.interactions.models.interactions.UrlCitation
import com.google.genai.interactions.models.interactions.Usage
import com.github.tomakehurst.wiremock.client.WireMock.anyUrl
import com.github.tomakehurst.wiremock.client.WireMock.equalTo
import com.github.tomakehurst.wiremock.client.WireMock.ok
import com.github.tomakehurst.wiremock.client.WireMock.post
import com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.client.WireMock.verify
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo
import com.github.tomakehurst.wiremock.junit5.WireMockTest
import java.time.OffsetDateTime
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.ResourceLock

@WireMockTest
@ResourceLock("https://github.com/wiremock/wiremock/issues/169")
internal class ServiceParamsTest {

    private lateinit var client: GeminiNextGenApiClient

    @BeforeEach
    fun beforeEach(wmRuntimeInfo: WireMockRuntimeInfo) {
        client =
            GeminiNextGenApiOkHttpClient.builder()
                .baseUrl(wmRuntimeInfo.httpBaseUrl)
                .apiKey("My API Key")
                .build()
    }

    @Disabled("Mock server tests are disabled")
    @Test
    fun create() {
        val interactionService = client.interactions()
        stubFor(post(anyUrl()).willReturn(ok("{}")))

        interactionService.create(
            InteractionCreateParams.builder()
                .apiVersion("api_version")
                .body(
                    CreateModelInteractionParams.builder()
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
                        .model(Model.GEMINI_2_5_COMPUTER_USE_PREVIEW_10_2025)
                        .id("id")
                        .background(true)
                        .created(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
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
                        .previousInteractionId("previous_interaction_id")
                        .responseFormatOfList(
                            listOf(
                                CreateModelInteractionParams.ResponseFormat.InnerResponseFormat
                                    .ofAudio(
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
                        .addResponseModality(CreateModelInteractionParams.ResponseModality.TEXT)
                        .role("role")
                        .serviceTier(CreateModelInteractionParams.ServiceTier.FLEX)
                        .status(CreateModelInteractionParams.Status.IN_PROGRESS)
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
                            CreateModelInteractionParams.WebhookConfig.builder()
                                .addUris("string")
                                .userMetadata(
                                    CreateModelInteractionParams.WebhookConfig.UserMetadata
                                        .builder()
                                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                                        .build()
                                )
                                .build()
                        )
                        .build()
                )
                .putAdditionalHeader("Secret-Header", "42")
                .putAdditionalQueryParam("secret_query_param", "42")
                .build()
        )

        verify(
            postRequestedFor(anyUrl())
                .withHeader("Secret-Header", equalTo("42"))
                .withQueryParam("secret_query_param", equalTo("42"))
        )
    }
}
