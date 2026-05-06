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
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class ContentTest {

    @Test
    fun ofText() {
        val text =
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

        val content = Content.ofText(text)

        assertThat(content.text()).contains(text)
        assertThat(content.image()).isEmpty
        assertThat(content.audio()).isEmpty
        assertThat(content.document()).isEmpty
        assertThat(content.video()).isEmpty
    }

    @Test
    fun ofTextRoundtrip() {
        val jsonMapper = jsonMapper()
        val content =
            Content.ofText(
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

        val roundtrippedContent =
            jsonMapper.readValue(jsonMapper.writeValueAsString(content), jacksonTypeRef<Content>())

        assertThat(roundtrippedContent).isEqualTo(content)
    }

    @Test
    fun ofImage() {
        val image =
            ImageContent.builder()
                .data("U3RhaW5sZXNzIHJvY2tz")
                .mimeType(ImageContent.MimeType.IMAGE_PNG)
                .resolution(ImageContent.Resolution.LOW)
                .uri("uri")
                .build()

        val content = Content.ofImage(image)

        assertThat(content.text()).isEmpty
        assertThat(content.image()).contains(image)
        assertThat(content.audio()).isEmpty
        assertThat(content.document()).isEmpty
        assertThat(content.video()).isEmpty
    }

    @Test
    fun ofImageRoundtrip() {
        val jsonMapper = jsonMapper()
        val content =
            Content.ofImage(
                ImageContent.builder()
                    .data("U3RhaW5sZXNzIHJvY2tz")
                    .mimeType(ImageContent.MimeType.IMAGE_PNG)
                    .resolution(ImageContent.Resolution.LOW)
                    .uri("uri")
                    .build()
            )

        val roundtrippedContent =
            jsonMapper.readValue(jsonMapper.writeValueAsString(content), jacksonTypeRef<Content>())

        assertThat(roundtrippedContent).isEqualTo(content)
    }

    @Test
    fun ofAudio() {
        val audio =
            AudioContent.builder()
                .channels(0)
                .data("U3RhaW5sZXNzIHJvY2tz")
                .mimeType(AudioContent.MimeType.AUDIO_WAV)
                .sampleRate(0)
                .uri("uri")
                .build()

        val content = Content.ofAudio(audio)

        assertThat(content.text()).isEmpty
        assertThat(content.image()).isEmpty
        assertThat(content.audio()).contains(audio)
        assertThat(content.document()).isEmpty
        assertThat(content.video()).isEmpty
    }

    @Test
    fun ofAudioRoundtrip() {
        val jsonMapper = jsonMapper()
        val content =
            Content.ofAudio(
                AudioContent.builder()
                    .channels(0)
                    .data("U3RhaW5sZXNzIHJvY2tz")
                    .mimeType(AudioContent.MimeType.AUDIO_WAV)
                    .sampleRate(0)
                    .uri("uri")
                    .build()
            )

        val roundtrippedContent =
            jsonMapper.readValue(jsonMapper.writeValueAsString(content), jacksonTypeRef<Content>())

        assertThat(roundtrippedContent).isEqualTo(content)
    }

    @Test
    fun ofDocument() {
        val document =
            DocumentContent.builder()
                .data("U3RhaW5sZXNzIHJvY2tz")
                .mimeType(DocumentContent.MimeType.APPLICATION_PDF)
                .uri("uri")
                .build()

        val content = Content.ofDocument(document)

        assertThat(content.text()).isEmpty
        assertThat(content.image()).isEmpty
        assertThat(content.audio()).isEmpty
        assertThat(content.document()).contains(document)
        assertThat(content.video()).isEmpty
    }

    @Test
    fun ofDocumentRoundtrip() {
        val jsonMapper = jsonMapper()
        val content =
            Content.ofDocument(
                DocumentContent.builder()
                    .data("U3RhaW5sZXNzIHJvY2tz")
                    .mimeType(DocumentContent.MimeType.APPLICATION_PDF)
                    .uri("uri")
                    .build()
            )

        val roundtrippedContent =
            jsonMapper.readValue(jsonMapper.writeValueAsString(content), jacksonTypeRef<Content>())

        assertThat(roundtrippedContent).isEqualTo(content)
    }

    @Test
    fun ofVideo() {
        val video =
            VideoContent.builder()
                .data("U3RhaW5sZXNzIHJvY2tz")
                .mimeType(VideoContent.MimeType.VIDEO_MP4)
                .resolution(VideoContent.Resolution.LOW)
                .uri("uri")
                .build()

        val content = Content.ofVideo(video)

        assertThat(content.text()).isEmpty
        assertThat(content.image()).isEmpty
        assertThat(content.audio()).isEmpty
        assertThat(content.document()).isEmpty
        assertThat(content.video()).contains(video)
    }

    @Test
    fun ofVideoRoundtrip() {
        val jsonMapper = jsonMapper()
        val content =
            Content.ofVideo(
                VideoContent.builder()
                    .data("U3RhaW5sZXNzIHJvY2tz")
                    .mimeType(VideoContent.MimeType.VIDEO_MP4)
                    .resolution(VideoContent.Resolution.LOW)
                    .uri("uri")
                    .build()
            )

        val roundtrippedContent =
            jsonMapper.readValue(jsonMapper.writeValueAsString(content), jacksonTypeRef<Content>())

        assertThat(roundtrippedContent).isEqualTo(content)
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
        val content = jsonMapper().convertValue(testCase.value, jacksonTypeRef<Content>())

        val e = assertThrows<GeminiNextGenApiInvalidDataException> { content.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
