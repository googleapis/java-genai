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

import java.time.Duration
import java.util.concurrent.CompletableFuture

/**
 * An interface for delaying execution for a specified amount of time.
 *
 * Useful for testing and cleaning up resources.
 */
interface Sleeper : AutoCloseable {

    /** Synchronously pauses execution for the given [duration]. */
    fun sleep(duration: Duration)

    /** Asynchronously pauses execution for the given [duration]. */
    fun sleepAsync(duration: Duration): CompletableFuture<Void>

    /** Overridden from [AutoCloseable] to not have a checked exception in its signature. */
    override fun close()
}
