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

internal class ImageResponseFormatTest {

    @Test
    fun create() {
        val imageResponseFormat =
            ImageResponseFormat.builder()
                .aspectRatio(ImageResponseFormat.AspectRatio._1_1)
                .delivery(ImageResponseFormat.Delivery.INLINE)
                .imageSize(ImageResponseFormat.ImageSize._512)
                .mimeType(ImageResponseFormat.MimeType.IMAGE_JPEG)
                .build()

        assertThat(imageResponseFormat.aspectRatio()).contains(ImageResponseFormat.AspectRatio._1_1)
        assertThat(imageResponseFormat.delivery()).contains(ImageResponseFormat.Delivery.INLINE)
        assertThat(imageResponseFormat.imageSize()).contains(ImageResponseFormat.ImageSize._512)
        assertThat(imageResponseFormat.mimeType()).contains(ImageResponseFormat.MimeType.IMAGE_JPEG)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val imageResponseFormat =
            ImageResponseFormat.builder()
                .aspectRatio(ImageResponseFormat.AspectRatio._1_1)
                .delivery(ImageResponseFormat.Delivery.INLINE)
                .imageSize(ImageResponseFormat.ImageSize._512)
                .mimeType(ImageResponseFormat.MimeType.IMAGE_JPEG)
                .build()

        val roundtrippedImageResponseFormat =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(imageResponseFormat),
                jacksonTypeRef<ImageResponseFormat>(),
            )

        assertThat(roundtrippedImageResponseFormat).isEqualTo(imageResponseFormat)
    }
}
