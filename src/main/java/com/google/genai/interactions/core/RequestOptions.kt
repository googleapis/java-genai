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

class RequestOptions private constructor(val responseValidation: Boolean?, val timeout: Timeout?) {

    companion object {

        private val NONE = builder().build()

        @JvmStatic fun none() = NONE

        @JvmSynthetic
        internal fun from(clientOptions: ClientOptions): RequestOptions =
            builder()
                .responseValidation(clientOptions.responseValidation)
                .timeout(clientOptions.timeout)
                .build()

        @JvmStatic fun builder() = Builder()
    }

    fun applyDefaults(options: RequestOptions): RequestOptions =
        RequestOptions(
            responseValidation = responseValidation ?: options.responseValidation,
            timeout =
                if (options.timeout != null && timeout != null) timeout.assign(options.timeout)
                else timeout ?: options.timeout,
        )

    class Builder internal constructor() {

        private var responseValidation: Boolean? = null
        private var timeout: Timeout? = null

        fun responseValidation(responseValidation: Boolean) = apply {
            this.responseValidation = responseValidation
        }

        fun timeout(timeout: Timeout) = apply { this.timeout = timeout }

        fun timeout(timeout: Duration) = timeout(Timeout.builder().request(timeout).build())

        fun build(): RequestOptions = RequestOptions(responseValidation, timeout)
    }
}
