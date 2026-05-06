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

package com.google.genai.interactions.client.okhttp

import com.google.genai.interactions.core.http.HttpMethod
import com.google.genai.interactions.core.http.HttpRequest
import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo
import com.github.tomakehurst.wiremock.junit5.WireMockTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.ResourceLock

@WireMockTest
@ResourceLock("https://github.com/wiremock/wiremock/issues/169")
internal class OkHttpClientTest {

    private lateinit var baseUrl: String
    private lateinit var httpClient: OkHttpClient

    @BeforeEach
    fun beforeEach(wmRuntimeInfo: WireMockRuntimeInfo) {
        baseUrl = wmRuntimeInfo.httpBaseUrl
        httpClient = OkHttpClient.builder().build()
    }

    @Test
    fun executeAsync_whenFutureCancelled_cancelsUnderlyingCall() {
        stubFor(post(urlPathEqualTo("/something")).willReturn(ok()))
        val responseFuture =
            httpClient.executeAsync(
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(baseUrl)
                    .addPathSegment("something")
                    .build()
            )
        val call = httpClient.okHttpClient.dispatcher.runningCalls().single()

        responseFuture.cancel(false)

        // Should have cancelled the underlying call
        assertThat(call.isCanceled()).isTrue()
    }
}
