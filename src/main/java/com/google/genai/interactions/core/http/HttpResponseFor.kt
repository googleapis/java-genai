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

import java.io.InputStream

interface HttpResponseFor<T> : HttpResponse {

    fun parse(): T
}

@JvmSynthetic
internal fun <T> HttpResponse.parseable(parse: () -> T): HttpResponseFor<T> =
    object : HttpResponseFor<T> {

        private val parsed: T by lazy { parse() }

        override fun parse(): T = parsed

        override fun statusCode(): Int = this@parseable.statusCode()

        override fun headers(): Headers = this@parseable.headers()

        override fun body(): InputStream = this@parseable.body()

        override fun close() = this@parseable.close()
    }
