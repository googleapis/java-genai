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

package com.google.genai.interactions.core

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UtilsTest {
    @Test
    fun contentDeepEquals() {
        assertThat(42 contentEquals 42).isTrue()
        assertThat(42 contentEquals "Hello World!").isFalse()
        assertThat(byteArrayOf(1, 2, 3) contentEquals byteArrayOf(1, 2, 3)).isTrue()
        assertThat(byteArrayOf(1, 2, 3) contentEquals byteArrayOf(1, 2, 4)).isFalse()
        assertThat(
                arrayOf(byteArrayOf(1, 2), byteArrayOf(3)) contentEquals
                    arrayOf(byteArrayOf(1, 2), byteArrayOf(3))
            )
            .isTrue()
        assertThat(
                arrayOf(byteArrayOf(1, 2), byteArrayOf(3)) contentEquals
                    arrayOf(byteArrayOf(1), byteArrayOf(2, 3))
            )
            .isFalse()
    }

    @Test
    fun contentToString() {
        assertThat((42).contentToString()).isEqualTo("42")
        assertThat("Hello World!".contentToString()).isEqualTo("Hello World!")
        assertThat(byteArrayOf(1, 2, 3).contentToString()).isEqualTo("[1, 2, 3]")
        assertThat(arrayOf(byteArrayOf(1, 2), byteArrayOf(3)).contentToString())
            .isEqualTo("[[1, 2], [3]]")
    }
}
