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

package com.google.genai.interactions.core.http

import com.google.genai.interactions.core.closeWhenPhantomReachable
import com.google.genai.interactions.core.http.AsyncStreamResponse.Handler
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor

/**
 * A delegating wrapper around an `AsyncStreamResponse` that closes it once it's only phantom
 * reachable.
 *
 * This class ensures the `AsyncStreamResponse` is closed even if the user forgets to close it.
 */
internal class PhantomReachableClosingAsyncStreamResponse<T>(
    private val asyncStreamResponse: AsyncStreamResponse<T>
) : AsyncStreamResponse<T> {

    /**
     * An object used for keeping `asyncStreamResponse` open while the object is still reachable.
     */
    private val reachabilityTracker = Object()

    init {
        closeWhenPhantomReachable(reachabilityTracker, asyncStreamResponse::close)
    }

    override fun subscribe(handler: Handler<T>): AsyncStreamResponse<T> = apply {
        asyncStreamResponse.subscribe(TrackedHandler(handler, reachabilityTracker))
    }

    override fun subscribe(handler: Handler<T>, executor: Executor): AsyncStreamResponse<T> =
        apply {
            asyncStreamResponse.subscribe(TrackedHandler(handler, reachabilityTracker), executor)
        }

    override fun onCompleteFuture(): CompletableFuture<Void?> =
        asyncStreamResponse.onCompleteFuture()

    override fun close() = asyncStreamResponse.close()
}

/**
 * A wrapper around a `Handler` that also references a `reachabilityTracker` object.
 *
 * Referencing the `reachabilityTracker` object prevents it from getting reclaimed while the handler
 * is still reachable.
 */
private class TrackedHandler<T>(
    private val handler: Handler<T>,
    private val reachabilityTracker: Any,
) : Handler<T> {
    override fun onNext(value: T) = handler.onNext(value)

    override fun onComplete(error: Optional<Throwable>) = handler.onComplete(error)
}
