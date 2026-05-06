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

internal class AudioResponseFormatTest {

    @Test
    fun create() {
        val audioResponseFormat =
            AudioResponseFormat.builder()
                .bitRate(0)
                .delivery(AudioResponseFormat.Delivery.INLINE)
                .mimeType(AudioResponseFormat.MimeType.AUDIO_MP3)
                .sampleRate(0)
                .build()

        assertThat(audioResponseFormat.bitRate()).contains(0)
        assertThat(audioResponseFormat.delivery()).contains(AudioResponseFormat.Delivery.INLINE)
        assertThat(audioResponseFormat.mimeType()).contains(AudioResponseFormat.MimeType.AUDIO_MP3)
        assertThat(audioResponseFormat.sampleRate()).contains(0)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val audioResponseFormat =
            AudioResponseFormat.builder()
                .bitRate(0)
                .delivery(AudioResponseFormat.Delivery.INLINE)
                .mimeType(AudioResponseFormat.MimeType.AUDIO_MP3)
                .sampleRate(0)
                .build()

        val roundtrippedAudioResponseFormat =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(audioResponseFormat),
                jacksonTypeRef<AudioResponseFormat>(),
            )

        assertThat(roundtrippedAudioResponseFormat).isEqualTo(audioResponseFormat)
    }
}
