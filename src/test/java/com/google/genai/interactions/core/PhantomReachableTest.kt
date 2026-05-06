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

internal class PhantomReachableTest {

    @Test
    fun closeWhenPhantomReachable_whenObservedIsGarbageCollected_closesCloseable() {
        var closed = false
        val closeable = AutoCloseable { closed = true }

        closeWhenPhantomReachable(
            // Pass an inline object for the object to observe so that it becomes immediately
            // unreachable.
            Any(),
            closeable,
        )

        assertThat(closed).isFalse()

        System.gc()
        Thread.sleep(100)

        assertThat(closed).isTrue()
    }
}
