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

package com.google.genai.interactions.errors

import com.google.genai.interactions.core.JsonValue
import com.google.genai.interactions.core.checkRequired
import com.google.genai.interactions.core.http.Headers
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

class SseException
private constructor(
    private val statusCode: Int,
    private val headers: Headers,
    private val body: JsonValue,
    cause: Throwable?,
) : GeminiNextGenApiServiceException("$statusCode: $body", cause) {

    override fun statusCode(): Int = statusCode

    override fun headers(): Headers = headers

    override fun body(): JsonValue = body

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [SseException].
         *
         * The following fields are required:
         * ```java
         * .statusCode()
         * .headers()
         * .body()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [SseException]. */
    class Builder internal constructor() {

        private var statusCode: Int? = null
        private var headers: Headers? = null
        private var body: JsonValue? = null
        private var cause: Throwable? = null

        @JvmSynthetic
        internal fun from(sseException: SseException) = apply {
            statusCode = sseException.statusCode
            headers = sseException.headers
            body = sseException.body
            cause = sseException.cause
        }

        fun statusCode(statusCode: Int) = apply { this.statusCode = statusCode }

        fun headers(headers: Headers) = apply { this.headers = headers }

        fun body(body: JsonValue) = apply { this.body = body }

        fun cause(cause: Throwable?) = apply { this.cause = cause }

        /** Alias for calling [Builder.cause] with `cause.orElse(null)`. */
        fun cause(cause: Optional<Throwable>) = cause(cause.getOrNull())

        /**
         * Returns an immutable instance of [SseException].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .statusCode()
         * .headers()
         * .body()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): SseException =
            SseException(
                checkRequired("statusCode", statusCode),
                checkRequired("headers", headers),
                checkRequired("body", body),
                cause,
            )
    }
}
