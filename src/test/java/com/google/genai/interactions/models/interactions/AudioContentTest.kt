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

internal class AudioContentTest {

    @Test
    fun create() {
        val audioContent =
            AudioContent.builder()
                .channels(0)
                .data("U3RhaW5sZXNzIHJvY2tz")
                .mimeType(AudioContent.MimeType.AUDIO_WAV)
                .sampleRate(0)
                .uri("uri")
                .build()

        assertThat(audioContent.channels()).contains(0)
        assertThat(audioContent.data()).contains("U3RhaW5sZXNzIHJvY2tz")
        assertThat(audioContent.mimeType()).contains(AudioContent.MimeType.AUDIO_WAV)
        assertThat(audioContent.sampleRate()).contains(0)
        assertThat(audioContent.uri()).contains("uri")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val audioContent =
            AudioContent.builder()
                .channels(0)
                .data("U3RhaW5sZXNzIHJvY2tz")
                .mimeType(AudioContent.MimeType.AUDIO_WAV)
                .sampleRate(0)
                .uri("uri")
                .build()

        val roundtrippedAudioContent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(audioContent),
                jacksonTypeRef<AudioContent>(),
            )

        assertThat(roundtrippedAudioContent).isEqualTo(audioContent)
    }
}
